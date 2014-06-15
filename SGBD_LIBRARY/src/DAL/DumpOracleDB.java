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
public class DumpOracleDB implements IDumpDb
  {

    //Get instance du singleton de connexion
    private Connection connexion = Oracle_connexion.getInstance();
    
    @Override
    public String dumpDb() throws SQLException, IOException      
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

        String creationVue = "";
        ArrayList<String> listeVues = listerVues();
        /*
         * Pour chaque vue
         */
        for (String vue : listeVues)
          {
            if (vue != null)
              {
                creationVue += "-- -----------------\r\n";
                creationVue += "-- creation de la vue --" + vue + " --\r\n";
                creationVue += "----------------------\r\n";
                ResultSet resultSetVue = statement.executeQuery("select dbms_metadata.get_ddl('VIEW','" + vue + "')from dual");
                while (resultSetVue.next())
                  {
                    //On récupère le create et on le concatene
                    creationVue += resultSetVue.getString(1) + "\r\n\n";
                  }
              }
          }

        String creationTrigger = "";
        ArrayList<String> listeTriggers = listerTriggers();
        /*
         * Pour chaque trigger
         */
        for (String trigger : listeTriggers)
          {
            if (trigger != null)
              {
                creationTrigger += "-- -----------------\r\n";
                creationTrigger += "-- creation du trigger --" + trigger + " --\r\n";
                creationTrigger += "----------------------\r\n";
                ResultSet resultSetTrigger = statement.executeQuery("select dbms_metadata.get_ddl('TRIGGER','" + trigger + "')from dual");
                while (resultSetTrigger.next())
                  {
                    //On récupère le create et on le concatene
                    creationTrigger += resultSetTrigger.getString(1) + "\r\n\n";
                  }
              }
          }
        String creationSequence = "";
        ArrayList<String> listeSequences = listerSequences();
        /*
         * Pour chaque sequence
         */
        for (String sequence : listeSequences)
          {
            if (sequence != null)
              {
                creationSequence += "-- -----------------\r\n";
                creationSequence += "-- creation de la sequence --" + sequence + " --\r\n";
                creationSequence += "----------------------\r\n";
                ResultSet resultSetSequence = statement.executeQuery("select dbms_metadata.get_ddl('SEQUENCE','" + sequence + "')from dual");
                while (resultSetSequence.next())
                  {
                    //On récupère le create et on le concatene
                    creationSequence += resultSetSequence.getString(1) + "\r\n\n";
                  }
              }
          }

        String creationProcedure = "";
        ArrayList<String> listeProcedures = listerProcedures();
        /*
         * Pour chaque sequence
         */
        for (String procedure : listeProcedures)
          {
            if (procedure != null)
              {
                creationProcedure += "-- -----------------\r\n";
                creationProcedure += "-- creation de la procedure --" + procedure + " --\r\n";
                creationProcedure += "----------------------\r\n";
                ResultSet resultSetProcedure = statement.executeQuery("select dbms_metadata.get_ddl('PROCEDURE','" + procedure + "')from dual");
                while (resultSetProcedure.next())
                  {
                    //On récupère le create et on le concatene
                    creationProcedure += resultSetProcedure.getString(1) + "\r\n\n";
                  }
              }
          }
        statement.close();
        //Construction de la chaine de requete
        String dumpDb = entete + "\n" + creation + "\n" + insertion + "\n" + creationVue + "\n" + creationSequence + "\n" + creationTrigger + "\n" + creationProcedure + "\n";
        return dumpDb;
      }

    /**
     * Méthode permettant d'écrire le fichier de dump de la BD
     *
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

          }

      }

    /**
     * Methode retournant la liste des Tables de la BD
     *
     *
     * @return ArrayList<String> la liste des tables de la BD
     * @throws java.sql.SQLException
     */
    @Override
    public ArrayList<String> listerTables() throws SQLException
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
            statement.close();
          }
        return listeTables;
      }
    /**
     * @return liste des vues de la base de données
     * @throws SQLException 
     */
    @Override
    public ArrayList<String> listerVues() throws SQLException
      {
        ArrayList<String> listeVues = new ArrayList();
        try (Statement statement = connexion.createStatement())
          {
            ResultSet resultSet = statement.executeQuery("select view_name from user_views");
            while (resultSet.next())
              {
                String nomVue = resultSet.getString("VIEW_NAME");
                listeVues.add(nomVue);
              }
            statement.close();
          }
        return listeVues;
      }
    /**
     * @return liste des triggers de la base de données
     * @throws SQLException 
     */
    @Override
    public ArrayList<String> listerTriggers() throws SQLException
      {
        ArrayList<String> listeTriggers = new ArrayList();
        try (Statement statement = connexion.createStatement())
          {
            ResultSet resultSet = statement.executeQuery("select trigger_name from user_triggers");
            while (resultSet.next())
              {
                String nomVue = resultSet.getString("TRIGGER_NAME");
                listeTriggers.add(nomVue);
              }
            statement.close();
          }

        return listeTriggers;
      }
    /**
     * @return liste s"quences de la base de données
     * @throws SQLException 
     */
    @Override
    public ArrayList<String> listerSequences() throws SQLException
      {
        ArrayList<String> listeSequence = new ArrayList();
        try (Statement statement = connexion.createStatement())
          {
            ResultSet resultSet = statement.executeQuery("select sequence_name from user_sequences");
            while (resultSet.next())
              {
                String nomSequence = resultSet.getString("SEQUENCE_NAME");
                listeSequence.add(nomSequence);
              }
            statement.close();
          }
        return listeSequence;
      }
    /**
     * @return liste des procédures de la base de données
     * @throws SQLException 
     */
    @Override
    public ArrayList<String> listerProcedures() throws SQLException
      {
        ArrayList<String> listeProcedures = new ArrayList();
        try (Statement statement = connexion.createStatement())
          {
            ResultSet resultSet = statement.executeQuery("select procedure_name from user_procedures");
            while (resultSet.next())
              {
                String nomProcedure = resultSet.getString("PROCEDURE_NAME");
                listeProcedures.add(nomProcedure);
              }
            statement.close();
          }
        return listeProcedures;
      }

    

  }
