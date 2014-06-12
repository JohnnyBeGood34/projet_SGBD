/*
 * Projet EPSI Montpellier 2014.
 * Jonathan Affre, Stephane Dupre, Kevin Salles, Cyrille Chanssang.
 */
package DAL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
            creation += "-- -----------------\n";
            creation += "-- creation de la table --" + table + " --\n";
            creation += "----------------------\n";
            /* Requête permettant de récupérer tous les create table */
            ResultSet resultSetTable = statement.executeQuery("select dbms_metadata.get_ddl('TABLE','" + table + "')from dual");
            ResultSetMetaData metaTable = resultSetTable.getMetaData();
            int nbColonnesTable = metaTable.getColumnCount();
            System.out.println("NB COLONNES-------------" + nbColonnesTable);
            //Pour chaques create table
            while (resultSetTable.next())
              {
                //On récupère le create table et on le concatene
                creation += resultSetTable.getString(1) + "\n\n";
              }
            //Récupération des enregistrements de la table en question
            ResultSet resultSetSelect = statement.executeQuery("SELECT * FROM " + table);
            insertion += "-- -------------\n";
            insertion += "-- insertion dans la table " + table + " --\n";
            insertion += "---------------------\n";
            //Pour chaque enregistrements
            ResultSetMetaData meta = resultSetSelect.getMetaData();
            int nbColonnes = meta.getColumnCount();
            while (resultSetSelect.next())
              {
                insertion += "INSERT INTO " + table + " VALUES(";
                for (int compteur = 1; compteur < nbColonnes; compteur++)
                  {
                    if (compteur != 1)
                      {
                        insertion += ",";
                      }
                    insertion += "'" + resultSetSelect.getString(compteur) + "'";
                  }
                insertion += ");\n";
              }
            insertion += "\n";
          }
        System.out.println(entete + "\n" + creation + "\n" + insertion);
        String nomfichier = "dump.sql";
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
