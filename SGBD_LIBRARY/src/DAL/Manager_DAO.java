/*
 * Projet EPSI Montpellier 2014.
 * Jonathan Affre, Stephane Dupre, Kevin Salles, Cyrille Chanssang.
 */
package DAL;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import static java.sql.Connection.TRANSACTION_SERIALIZABLE;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.json.simple.JSONObject;

/**
 * Manager DAO, permet d'effectuer des actions en base de données dynamiquement en fonction des objets
 * reçut dans les methodes.
 * @author JOHN
 */
public class Manager_DAO
  {
    //requestFactory, oracle par defaut.
    private IBDD requestFactory;
    //Connexion Singleton
    private String bddType;
    private Connection connexion = null;

    /**
     * Constructeur de la classe Manager_DAO.
     *
     * @param bddTypeName String, qui est le nom de la base de données que l'on
     * veut utiliser sous forme de String, Oracle, Mysql, SqlServer
     */
    public Manager_DAO(String bddTypeName)
      {
        /**
         * La request factory spécifique est chargée en fonction du type de base
         * de données
         */
        switch (bddTypeName)
          {
            case "Oracle":
                this.bddType = bddTypeName;
                this.requestFactory = new Request_factory_oracle();
                break;
            case "MySql":
                this.bddType = bddTypeName;
                this.requestFactory = new Request_factory_mysql();
                break;
          }
      }

    /**
     * SetrequestFactory, permet de changer le type de fabrique de requete
     * methode privée, accessible uniquement depuis le manager lui même
     * @param requestFactory une fabrique de requetes de type InterfaceBDD
     */
    private void setRequestFactory(IBDD requestFactory)
      {
        this.requestFactory = requestFactory;
      }

    /**
     * setBdd, permet de changer de type de base de données à la volée.
     * Quand on change le type de base de donnée, la fabrique correspondante est chargée
     * @param bddName le nom du SGBD à utiliser
     */
    public void setBdd(String bddName)
      {
        this.bddType = bddName;
        switch (bddName)
          {
            case "Oracle":
                IBDD requestFactoryOracle = new Request_factory_oracle();
                setRequestFactory(requestFactoryOracle);
                break;
            case "MySql":
                IBDD requestFactoryMysql = new Request_factory_mysql();
                setRequestFactory(requestFactoryMysql);
                break;
            case "SqlServer":
                break;
          }
      }

    /**
     * Fonction privée permettant de renvoyer l'instance de connexion courante.
     *
     * @return instance de la connexion courante
     */
    private Connection getConnexion()
      {
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

    /**
     * dumpDb permet de faire un dump complet de la base de données
     * @param cheminFichierDump, chemin du dossier dans lequel on souhaite récupérer le fichier .sql
     * @throws SQLException exception en cas de fail requete SQL
     * @throws IOException exception en cas de fail entrée/sorties
     */
    public void dumpDb(String cheminFichierDump) throws SQLException, IOException
      {
        requestFactory.dumpDb(cheminFichierDump);
      }

    /**
     * Fonction permettant de récupérer le dump de la base de données sous forme de String
     * @return String, le sql du dump de la base de données sous forme de String
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
     * @param restriction, une restriction
     * @param values un arraylist de valeur pour les restrictions
     * @return Objet JSON avec les resultats de la requete
     * @throws java.lang.ClassNotFoundException exception si le systeme de trouve pas la classe de l'objet recut
     * @throws java.lang.NoSuchMethodException exception si on ne trouve pas la methode que l'on veut appeller
     * @throws java.lang.IllegalAccessException exception SQL Acces
     * @throws java.lang.InstantiationException exception sur instanciation d'un objet
     * @throws java.lang.reflect.InvocationTargetException exception sur invocation de methode dynamique
     * @throws java.sql.SQLException exception en cas de fail requete SQL
     */
    public JSONObject select(String classe, ArrayList<String> fields, ArrayList<String> restriction, ArrayList<String> values) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException
      {
        //Récupération de la connexion
        Connection connexionFonction = this.getConnexion();
        //Objet JSON qui contiendra le résultat
        JSONObject resultat = new JSONObject();
        //Creation de la requete lister
        requestFactory.requeteLister(classe, fields, restriction, values);
        //Récupération de la requete
        String requete = requestFactory.getRequeteString();

        //Exécution de la requete lister
        //Creation du statement bdd
        try (Statement statement = connexionFonction.createStatement())
          {
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
            connexionFonction = null;
            statement.close();
          }
        return resultat;
      }

    /**
     * Fonction permettant de faire une requete d'insertion à partir d'un objet.
     * Cela implique que les classes portent le même nom que les tables en base
     * de données. Les attributs de classes doivent aussi porter le même nom que
     * les champs en base de données. Les accesseurs doivent être écrits sous la
     * forme get_nomAttribut.
     * @param objet, un objet métier.
     * @param isProcedure, un booleein permettant d'indiquer si l'insertion doit être faite avec appel de procédure
     * Cela implique que la procédure d'ajout s'appelle ajouterNomClasse
     * @return Un JSON contenant l'id de l'objet inséré.
     * @throws SQLException exception en cas de fail de requete SQL
     */
    public JSONObject insert(Object objet, boolean isProcedure) throws SQLException
      {
        JSONObject resultat = new JSONObject();
        Connection connexionFonction = null;
        PreparedStatement prepare = null;
        try
          {
            connexionFonction = this.getConnexion();
            //Mode transaction sérializable pour récupérer l'id que l'on vient d'inserer
            connexionFonction.setTransactionIsolation(TRANSACTION_SERIALIZABLE);
            if (!isProcedure)
              {
                //La factory renvoi la requete insert préparée avec les valeurs dans un array
                requestFactory.requeteAjouter(objet);
              } else
              {
                //Si c'est un appel de procedure
                requestFactory.procedureAjouter(objet);
              }
            //On récupère la requete pour l'exécuter ainsi que l'array de valeurs
            String requete = requestFactory.getRequeteString();
            //On récupère la liste des paramètres poru la requete préparée
            ArrayList<String> parametresRequete = requestFactory.getParametres();

            prepare = connexionFonction.prepareStatement(requete);
            //Pour chaque entrée de l'array de parametres
            for (int i = 1; i <= parametresRequete.size(); i++)
              {
                prepare.setString(i, parametresRequete.get(i - 1));
              }
            prepare.executeUpdate();

            prepare.close();
            /* On récupère le dernier ID inséré */
            try (Statement statement = connexionFonction.createStatement())
              {
                String lastId = "SELECT * FROM ( SELECT * FROM " + objet.getClass().getSimpleName() + " ORDER BY 1 DESC ) WHERE ROWNUM = 1 ";
                ResultSet rs = statement.executeQuery(lastId);

                while (rs.next())
                  {
                    resultat.put("last_id", rs.getLong(1));
                  }
                statement.close();
              }
          } catch (SQLException e)
          {
            System.out.println(e.getMessage());
          } finally
          {
            if (prepare != null)
              {
                prepare.close();
              }

            if (connexionFonction != null)
              {
                connexionFonction = null;
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
     * @param objet, un objet métier.
     * @param isProcedure, permet de savoir si la mise a jour doit être effectué avec appel de procédure
     * Cela implique que la procédure de mise à jour doit s'appeller modifierNomClasse
     * @return un objet JSON
     * @throws SQLException exception en cas de fail de requete SQL
     */
    public JSONObject update(Object objet, boolean isProcedure) throws SQLException
      {
        JSONObject resultat = new JSONObject();
        Connection connexionFonction = null;
        PreparedStatement prepare = null;
        try
          {
            connexionFonction = this.getConnexion();
            if (!isProcedure)
              {
                //La factory renvoi la requete insert préparée avec les valeurs dans un array
                requestFactory.requeteMiseAJour(objet);
              } else
              {
                //Si c'est un appel de procedure
                requestFactory.procedureAjouter(objet);
              }

            //On récupère la requete pour l'exécuter ainsi que l'array de valeurs
            String requete = requestFactory.getRequeteString();
            ArrayList<String> parametresRequete = requestFactory.getParametres();

            prepare = connexionFonction.prepareStatement(requete);

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

            if (connexionFonction != null)
              {
                connexionFonction = null;
              }
          }
        return resultat;
      }

    /**
     * Permet de faire un DELETE en base de données en focntion des paramètres
     * reçuts.
     * @param classe, le nom de la classe de l'objet que l'on veut supprimer
     * @param fields, un ArrayList de champs sur lesquels on veut faire la
     * @param restriction, arrayList de restriction pour la construction de la requete
     * @param values, un arrayList de valeurs, correspondants aux champs
     * permettant de faire la restriction
     * @param isProcedure, permet d'indiquer si la suppression doit être effectué avec appel de procédure
     * @return un JSON disant si le delete s'est bien passé.
     * @throws java.sql.SQLException exception en cas de fail de requete SQL
     */
    public JSONObject delete(String classe, ArrayList<String> fields, ArrayList<String> restriction, ArrayList<String> values, Boolean isProcedure) throws SQLException
      {
        JSONObject resultat = new JSONObject();
        Connection connexionFonction = this.getConnexion();
        requestFactory.requeteSupprimer(classe, fields, restriction, values);
        String requete = requestFactory.getRequeteString();
          System.out.println("REQUETE SUPPRIMER++++++++++++++++++"+requete);
        try (Statement statement = connexionFonction.createStatement())
          {
            try (ResultSet resultSet = statement.executeQuery(requete))
              {
                resultat.put("result", fields + " " + restriction + " " + values + " a bien été supprimé!");
                resultSet.close();
              }
            statement.close();
          }
        return resultat;
      }
  }
