/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JOHN
 */
public class ConstructSqlTable implements Runnable
  {

    final Connection connexion = Oracle_connexion.getInstance();
    private volatile String result;

    final Statement statement;

    public ConstructSqlTable() throws SQLException
      {
        this.statement = connexion.createStatement();
      }

    public String getResult()
      {
        return result;
      }

    @Override
    public void run()
      {
        System.out.println("RUN RUN RUN");
        try
          {
            System.out.println("TRY TRY TRY MOTHER FUCKER STP MARCHE");
            ArrayList<String> listeTables = new ArrayList<>();
            /* Requête permettant de récupérer tous les noms de table */

            /* Requête permettant de récupérer tous les noms de table */
            ResultSet resultSet = statement.executeQuery("SELECT table_name FROM user_tables");
            while (resultSet.next())
              {
                String nomTable = resultSet.getString("TABLE_NAME");
                listeTables.add(nomTable);
              }
            //String builder permettra de récupérer la chaine à retourner par la focntion
            StringBuilder sBuilderTable = new StringBuilder();

            String entete = "-------------------\r\n";
            entete += "--Dump de la base--\r\n";
            entete += "------------------\r\n\n\n";

            String creation = "";
            String insertion = "\r\n\n";
            //Pour chaque tables
            for (String table : listeTables)
              {
                creation += "-- -----------------\r\n";
                creation += "-- creation de la table --" + table + " --\r\n";
                creation += "----------------------\r\n";
                // Requête permettant de récupérer tous les create table 
                ResultSet resultSetTable = statement.executeQuery("select dbms_metadata.get_ddl('TABLE','" + table + "')from dual");

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

            sBuilderTable.append(entete).append("\n").append(creation).append("\n").append(insertion);
              System.out.println(sBuilderTable.toString());
          } catch (SQLException ex)
          {
            Logger.getLogger(ConstructSqlTable.class.getName()).log(Level.SEVERE, null, ex);
          }
      }

    public void writeDumpFile(String contenu, String chemin) throws IOException
      {

        Date date = new Date();
        String dateFichier = new SimpleDateFormat("dd-MM-yyyy").format(date);
        File fichier = new File(chemin + "dump_" + dateFichier + ".sql");

        FileWriter filewriter = new FileWriter(fichier);
        try (BufferedWriter bufferwriter = new BufferedWriter(filewriter))
          {
            bufferwriter.write(contenu);
          }

      }
  }
