/*
 * Projet EPSI Montpellier 2014.
 * Jonathan Affre, Stephane Dupre, Kevin Salles, Cyrille Chanssang.
 */
package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    //File d'attente de requetes, resultats <requete,resultat requete>
    //Permet, de ne pas refaire un appel à la base si on a déjà utilisé la requete
    private static HashMap<String, ArrayList<Object>> _requestQueue;
    public Manager_DAO(String bddTypeName)
      {
        if(bddTypeName.equals("Oracle"))
          {
            this.bddType = bddTypeName;
            this.requestFactory = new Request_factory_oracle();
          }
      }
    
    public void setRequestFactory(IBDD requestFactory)
      {
        this.requestFactory = requestFactory;
      }

    private Connection getConnexion()
      {
        Connection connexion = null;
        switch(this.bddType)
          {
            case "Oracle":
                connexion = Oracle_connexion.getInstance();
                break;
          }
        return connexion;
      }
    /**
     * Permet d'executer une requete lister et de renvoyer une liste d'objet
     * dans un ArrayList
     *
     * @param classe un nom de classe sur laquelle on veut faire la requete
     * @param fields un arrayList de champs pour les restrictions
     * @param values un arraylist de valeur pour les restrictions
     * @return ArrayList d'objet résultat de la requete lister
     */
    public ArrayList<Object> lister(String classe, ArrayList<String> fields, ArrayList<String> values) throws ClassNotFoundException
      {
        Connection connexion  = this.getConnexion();
        //Récupération de la classe
        //Class classeSelection = Class.forName(classe);
        ArrayList<Object> arrayResult = new ArrayList();
        requestFactory.requeteLister(classe, fields, values);
        String requete = requestFactory.getRequeteString();
        System.out.println("REQUETE MANAGER DAO RETOURNEE----------------------" + requete);
        try
          {
            Statement statement = connexion.createStatement();
            ResultSet resultSet = statement.executeQuery(requete);
            //ResultSetMetaData resultMeta = resultSet.getMetaData();
            int i = 0;
            while (resultSet.next())
              {
                System.out.println(resultSet.getString(i));
                i++;
              }
          } catch (SQLException ex)
          {
              System.out.println("ERRUR LISTER SQL---------"+ex.toString());
          }
        return arrayResult;
      }
  }
