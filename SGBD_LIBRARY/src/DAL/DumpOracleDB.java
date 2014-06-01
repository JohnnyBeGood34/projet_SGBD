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
        String entete = "-------------------\n";
        entete += "--Dump de la base--\n";
        entete += "------------------\n\n\n";
        String creation = "";
        String insertion = "\n\n";
        ArrayList<String> listeTables = listerTables();
        //Statement
        Statement statement = connexion.createStatement();
        //Pour chaque tables
        for (String table : listeTables)
          {
            creation = "-- -----------------\n";
            creation += "-- creation de la table --" + table + " --\n";
            creation += "----------------------\n";
            /* Requête permettant de récupérer tous les create table */
            ResultSet resultSet = statement.executeQuery("select dbms_metadata.get_ddl('TABLE','" + table + "')from dual");
            //Pour chaques create table
            while (resultSet.next())
              {
                //On récupère le create table et on le concatene
                creation += resultSet.getString(1) + "\n\n";
              }
                
          }
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
        /* Requête permettant de récupérer tous les noms de table */
        try (Statement statement = connexion.createStatement())
          {
            /* Requête permettant de récupérer tous les noms de table */
            ResultSet resultSet = statement.executeQuery("SELECT table_name FROM user_tables");

            while (resultSet.next())
              {
                String nomTable = resultSet.getString("TABLE_NAME");
                listeTables.add(nomTable);
              }
          }
        return listeTables;
      }
  }
