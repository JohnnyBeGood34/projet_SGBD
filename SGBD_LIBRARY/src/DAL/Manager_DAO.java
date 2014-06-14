/*
 * Projet EPSI Montpellier 2014.
 * Jonathan Affre, Stephane Dupre, Kevin Salles, Cyrille Chanssang.
 */
package DAL;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.json.simple.JSONObject;

/**
 *
 * @author JOHN
 */
public class Manager_DAO
  {

    //requestFactory, oracle par defaut.
    private IBDD requestFactory;
    //Connexion Singleton
    private String bddType;

    /*
     //File d'attente de requetes, resultats <requete,resultat requete>
     private static HashMap<String, ArrayList<Object>> _requestQueue;
     */
    /**
     * Constructeur de la classe Manager_DAO.
     *
     * @param bddTypeName String, qui est le nom de la base de données que l'on
     * veut utiliser
     */
    public Manager_DAO(String bddTypeName)
      {
        /**
         * La request factory spécifique est chargée en fonction du type de base
         * de données
         */
        if (bddTypeName.equals("Oracle"))
          {
            this.bddType = bddTypeName;
            this.requestFactory = new Request_factory_oracle();
          }
      }

    /**
     * SetrequestFactory, permet de changer le type de fabrique de requete
     *
     * @param requestFactory une fabrique de requetes de type InterfaceBDD
     */
    public void setRequestFactory(IBDD requestFactory)
      {
        this.requestFactory = requestFactory;
      }

    /**
     * Fonction privée permettant de renvoyer l'instance de connexion courante.
     *
     * @return instance de la connexion courante
     */
    private Connection getConnexion()
      {
        Connection connexion = null;
        switch (this.bddType)
          {
            case "Oracle":
                connexion = Oracle_connexion.getInstance();
                break;
          }
        return connexion;
      }
    /*
     * Fonction permettant de crer le fichier .sql du dump de la base de données
     */
    public void dumpDb(String cheminFichierDump)
      {
        requestFactory.dumpDb(cheminFichierDump);
      }
    
    /*
     * Fonction permettant de récupérer le dump de la base de données sous forme de String
     */
    public String getDumpDb()
      {
        return requestFactory.getDumpDb();
      }
    
    /**
     * Permet d'executer une requete lister et de renvoyer une liste de
     * résultats dans un objet JSON.
     *
     * @param classe un nom de classe sur laquelle on veut faire la requete
     * @param fields un arrayList de champs pour les restrictions
     * @param restriction
     * @param values un arraylist de valeur pour les restrictions
     * @return Objet JSON avec les resultats de la requete
     */
    public JSONObject select(String classe, ArrayList<String> fields, ArrayList<String> restriction, ArrayList<String> values) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException
      {
        //Récupération de la connexion
        Connection connexion = this.getConnexion();
        //Objet JSON qui contiendra le résultat
        JSONObject resultat = new JSONObject();
        //Creation de la requete lister
        requestFactory.requeteLister(classe, fields, restriction, values);
        //Récupération de la requete
        String requete = requestFactory.getRequeteString();

        //Creation du statement bdd
        Statement statement = connexion.createStatement();
        //Exécution de la requete lister
        ResultSet resultSet = statement.executeQuery(requete);
        //Récupération des noms de colonnes
        ResultSetMetaData metadata = resultSet.getMetaData();
        //Récupération du nb de lignes 
        int columnCount = metadata.getColumnCount();
        ArrayList<String> columns = new ArrayList();
        //On récupère le nom des colonnes avec les metadatas
        for (int i = 1; i < columnCount; i++)
          {
            String columnName = metadata.getColumnName(i);
            columns.add(columnName);
          }
        //Tant qu'il y a des enregistrements
        int compteur = 0;
        while (resultSet.next())
          {
            //On déclare un objet JSON qui va récupérer les résultats
            JSONObject jsonResult = new JSONObject();
            for (String columnName : columns)
              {
                //On récupère le résultat pour la colonne
                String value = resultSet.getString(columnName);
                //On met dans la JSON déclaré au dessus nomColonne : Valeur
                jsonResult.put(columnName, value);
              }
            resultat.put(compteur, jsonResult);
            compteur++;
          }
        connexion = null;
        statement.close();
        return resultat;
      }

    /**
     * Fonction permettant de faire une requete d'insertion à partir d'un objet.
     * Cela implique que les classes portent le même nom que les tables en base
     * de données. Les attributs de classes doivent aussi porter le même nom que
     * les champs en base de données. Les accesseurs doivent être écrits sous la
     * forme get_nomAttribut.
     *
     * @param objet, un objet métier.
     * @return Un JSON contenant l'id de l'objet inséré.
     * @throws SQLException
     */
    public JSONObject insert(Object objet) throws SQLException
      {

        JSONObject resultat = new JSONObject();
        Connection connexion = null;
        PreparedStatement prepare = null;
        try
          {
            connexion = this.getConnexion();
            //La factory renvoi la requete insert préparée avec les valeurs dans un array
            requestFactory.requeteAjouter(objet);

            //On récupère la requete pour l'exécuter ainsi que l'array de valeurs
            String requete = requestFactory.getRequeteString();
            //On récupère la liste des paramètres poru la requete préparée
            ArrayList<String> parametresRequete = requestFactory.getParametres();

            prepare = connexion.prepareStatement(requete);
            //Pour chaque entrée de l'array de parametres
            for (int i = 1; i <= parametresRequete.size(); i++)
              {
                prepare.setString(i, parametresRequete.get(i - 1));
              }
            prepare.executeUpdate();
            try ( /* On récupère le dernier ID inséré */Statement statement = connexion.createStatement())
              {
                /**
                 * A METTRE DANS UN TRANSACTION
                 */
                String lastId = "SELECT * FROM ( SELECT * FROM " + objet.getClass().getSimpleName() + " ORDER BY 1 DESC ) WHERE ROWNUM = 1 ";
                ResultSet rs = statement.executeQuery(lastId);

                while (rs.next())
                  {
                    resultat.put("last_id", rs.getLong(1));
                  }
                statement.close();
              }
            prepare.close();
          } catch (SQLException e)
          {
            System.out.println(e.getMessage());
          } finally
          {
            if (prepare != null)
              {
                prepare.close();
              }

            if (connexion != null)
              {
                connexion = null;
              }
            
          }
        return resultat;
      }

    /**
     * Fonction permettant d'effectuer un update sur la base de données en
     * fonction de l'objet reçut en parametre. Cela implique que les classes
     * portent le même nom que les tables en base de données. Les attributs de
     * classes doivent aussi porter le même nom que les champs en base de
     * données. Les accesseurs doivent être écrits sous la forme get_nomAttribut
     *
     * @param objet, un objet métier.
     * @return
     * @throws SQLException
     */
    public JSONObject update(Object objet) throws SQLException
      {

        JSONObject resultat = new JSONObject();
        Connection connexion = null;
        PreparedStatement prepare = null;
        try
          {
            connexion = this.getConnexion();
            //La factory renvoi la requete insert préparée avec les valeurs dans un array
            requestFactory.requeteMiseAJour(objet);
            //On récupère la requete pour l'exécuter ainsi que l'array de valeurs
            String requete = requestFactory.getRequeteString();
            ArrayList<String> parametresRequete = requestFactory.getParametres();

            prepare = connexion.prepareStatement(requete);

            //On enlève l'id pour le mettre en dernier de la liste des parametres de requete preparees
            String id = parametresRequete.get(0);
            parametresRequete.remove(0);
            parametresRequete.add(id);
            
            //Pour chaque entrée de l'array de parametres
            for (int i = 1; i <= parametresRequete.size(); i++)
              {
                //Je transforme tout les  "." pour que le "." des floats devienne "," pour que oracle le reconnaise en tant que float
                String nouveauFloat = parametresRequete.get(i - 1).replaceAll("\\.", ",");
                prepare.setString(i, nouveauFloat);
              }
            
            prepare.executeUpdate();
            prepare.close();
          } catch (SQLException e)
          {
            System.out.println(e.getMessage());

          } finally
          {
            if (prepare != null)
              {
                prepare.close();
              }

            if (connexion != null)
              {
                connexion = null;
              }
          }
        return resultat;
      }

    /**
     * Permet de faire un DELETE en base de données en focntion des paramètres
     * reçuts.
     *
     * @param classe, le nom de la classe de l'objet que l'on veut supprimer
     * @param fields, un ArrayList de champs sur lesquels on veut faire la
     * @param restriction, arrayList de restriction pour la construction de la
     * requete
     * @param values, un arrayList de valeurs, correspondants aux champs
     * permettant de faire la restriction
     * @return un JSON disant si le delete s'est bien passé.
     */
    public JSONObject delete(String classe, ArrayList<String> fields, ArrayList<String> restriction, ArrayList<String> values) throws SQLException
      {
        JSONObject resultat = new JSONObject();
        Connection connexion = this.getConnexion();
        requestFactory.requeteSupprimer(classe, fields, restriction, values);
        String requete = requestFactory.getRequeteString();
        
        try (Statement statement = connexion.createStatement())
          {
            ResultSet resultSet = statement.executeQuery(requete);
            
            /*RECUPERER LE MESSAGE DU RESULTSET A LA PLACE DU OK*/
            resultat.put("result", resultSet.getString(1));
            statement.close();
          }
        return resultat;
      }
  }
