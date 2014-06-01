/*
 * Projet EPSI Montpellier 2014.
 * Jonathan Affre, Stephane Dupre, Kevin Salles, Cyrille Chanssang.
 */
package DAL;

import MAIN.Menu;
import MAIN.SGBD_LIBRARY;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe permettant de faire un export de la base de données oracle
 * @author JOHN
 */
public class DumpOracleDB
  {
    
      //Get instance du singleton de connexion
      private  Connection connexion = Oracle_connexion.getInstance();
    public static String dumpOracleDB()
      {
        //Requete renvoyant le nom des tables de la base de données
        //select dbms_metadata.get_ddl('TABLE','MATERIELMEDICAL')from dual
        String requeteTables = "SELECT table_name FROM user_tables;";
        
        return "yoyo les couilles a Jeano";
      }
    
   /**
    * Methode retournant la liste des Tables de la BD
    * @param connection type Connection, la connection pour la BD
    * @return ArrayList<String> la liste des tables de la BD
     * @throws java.sql.SQLException
    */
    public ArrayList<String> listerTables(Connection connection) throws SQLException{
        
        ArrayList<String> listeTables=new ArrayList<>();
        Statement statement=connection.createStatement();
        
        /* Requête permettant de récupérer tous les noms de table */
        ResultSet resultSet=statement.executeQuery("SELECT table_name FROM user_tables");
        
        while(resultSet.next()){
            String nomTable=resultSet.getString("TABLE_NAME");
            listeTables.add(nomTable);            
        }
        
        return listeTables;
    }
    
    /**
     * Méthode retournant la liste des colonnes d'une table
     * @param table type String, nom de la table
     * @return ArrayList<String> 
    */
    public ArrayList<String> listerColonnes(String table){
        ArrayList<String> listeColonnes=new ArrayList();
        
        return listeColonnes;
    }
    }
  
