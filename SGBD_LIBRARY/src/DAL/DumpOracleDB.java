/*
 * Projet EPSI Montpellier 2014.
 * Jonathan Affre, Stephane Dupre, Kevin Salles, Cyrille Chanssang.
 */
package DAL;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
        String entete = "-------------------\r\n";
        entete += "--Dump de la base--\r\n";
        entete += "------------------\r\n\n\n";
        String creation = "";
        String insertion = "\r\n\n";
        ArrayList<String> listeTables = listerTables();
        //Statement
        Statement statement = connexion.createStatement();
        //Pour chaque tables
        for (String table : listeTables)
          {
            creation += "-- -----------------\r\n";
            creation += "-- creation de la table --" + table + " --\r\n";
            creation += "----------------------\r\n";
            /* Requête permettant de récupérer tous les create table */
            ResultSet resultSetTable = statement.executeQuery("select dbms_metadata.get_ddl('TABLE','" + table + "')from dual");
            ResultSetMetaData metaTable = resultSetTable.getMetaData();
            int nbColonnesTable = metaTable.getColumnCount();
            System.out.println("NB COLONNES-------------" + nbColonnesTable);
            //Pour chaques create table
            while (resultSetTable.next())
              {
                //On récupère le create table et on le concatene
                creation += resultSetTable.getString(1) + "\r\n\n";
              }
            //Récupération des enregistrements de la table en question
            ResultSet resultSetSelect = statement.executeQuery("SELECT * FROM " + table);
            insertion += "-- -------------\r\n";
            insertion += "-- insertion dans la table " + table + " --\r\n";
            insertion += "---------------------\r\n";
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
                insertion += ");\r\n";
              }
            insertion += "\r\n";
          }
        //System.out.println(entete + "\n" + creation + "\n" + insertion);
        return (entete + "\r\n" + creation + "\r\n" + insertion);
      }
    
    /**
     * Méthode permettant d'écrire le fichier de dump de la BD
     * @param contenu type String Contenu du fichier à écrire
     * @param chemin type String Chemin où enregitrer le fichier
     * @throws java.io.IOException
    */
    public void writeDumpFile (String contenu,String chemin) throws IOException{
      
      Date date =new Date(); 
      String dateFichier=new SimpleDateFormat("dd-MM-yyyy").format(date);
      File fichier = new File(chemin+"dump_"+dateFichier+".sql");  
      
      FileWriter filewriter=new FileWriter(fichier);
        try (BufferedWriter bufferwriter = new BufferedWriter(filewriter)) {
            bufferwriter.write(contenu);
        }
       
    }

    /**
     * Methode retournant la liste des Tables de la BD
     *
     * 
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
