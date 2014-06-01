/*
 * Projet EPSI Montpellier 2014.
 * Jonathan Affre, Stephane Dupre, Kevin Salles, Cyrille Chanssang.
 */
package DAL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Classe permettant de faire un export de la base de données oracle
 *
 * @author JOHN
 */
public class DumpOracleDB
  {

    //Get instance du singleton de connexion
    private Connection connexion = Oracle_connexion.getInstance();

    public String dumpOracleDB() throws SQLException
      {
        //Requete renvoyant le nom des tables de la base de données
        //select dbms_metadata.get_ddl('TABLE','MATERIELMEDICAL')from dual
        ArrayList<String> listeTables = listerTables();
        return "yoyo les couilles a Jeano";
      }

    /**
     * Methode retournant la liste des Tables de la BD
     *
     * @param connection type Connection, la connection pour la BD
     * @return ArrayList<String> la liste des tables de la BD
     * @throws java.sql.SQLException
     */
    private ArrayList<String> listerTables() throws SQLException
      {

        ArrayList<String> listeTables = new ArrayList<>();
        Statement statement = connexion.createStatement();

        /* Requête permettant de récupérer tous les noms de table */
        ResultSet resultSet = statement.executeQuery("SELECT table_name FROM user_tables");

        while (resultSet.next())
          {
            String nomTable = resultSet.getString("TABLE_NAME");
            listeTables.add(nomTable);
          }

        return listeTables;
      }

    /**
     * Méthode retournant la liste des colonnes d'une table
     *
     * @param table type String, nom de la table
     * @return ArrayList<String>
     */
    public ArrayList<String> listerColonnes(String table)
      {
        ArrayList<String> listeColonnes = new ArrayList();

        return listeColonnes;
      }
  }
