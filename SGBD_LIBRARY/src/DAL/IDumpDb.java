/*
 * Projet EPSI Montpellier 2014.
 * Jonathan Affre, Stephane Dupre, Kevin Salles, Cyrille Chanssang.
 */

package DAL;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Interface pour les DumpDb permettant de renseigner les différentes methodes pour chaque SGBD
 * @author JOHN
 */
public interface IDumpDb
  {

    /**
     * dumpDB permet de creer la requete de dump de la base
     * @return la requete de dump de la base de données sous forme de string
     * @throws SQLException exception en cas de fail requete SQL
     * @throws IOException exception en cas de fail entrée/sorties
     */
    abstract String dumpDb() throws SQLException, IOException;

    /**
     * writeDumpFile permet d'écrire du contenu dans un fichier .sql
     * @param contenu, du contenu à écrire dans un fichier .sql
     * @param chemin, le chemin ou placer le fichier .sql sur la machine
     * @throws IOException exception en cas de fail entrée/sorties
     */
    abstract void writeDumpFile(String contenu, String chemin) throws IOException;

    /**
     * listerTable, retourne la liste des tables de la base de données
     * @return ArrayList de noms de tables de la base de données
     * @throws SQLException exception en cas de fail requete SQL
     */
    abstract ArrayList<String> listerTables() throws SQLException;

    /**
     * listerVues, retourne la liste des vues de la base de données
     * @return un arrayList contenant les noms des vues de la table
     * @throws SQLException exception en cas de fail requete SQL
     */
    abstract ArrayList<String> listerVues() throws SQLException;

    /**
     * listerTriggers retourne la liste des triggers de la base de données
     * @return un ArrayList des triggers de la base de données
     * @throws SQLException exception en cas de fail requete SQL
     */
    abstract ArrayList<String> listerTriggers() throws SQLException;

    /**
     * listerSequences retourne la liste des séquences de la base de données
     * @return un ArrayList des séquences de la base de données
     * @throws SQLException exception en cas de fail requete SQL
     */ 
    abstract ArrayList<String> listerSequences() throws SQLException;

    /**
     * listerProcedures renvoi la liste des procédures de la base de données
     * @return un ArrayList des procédures de la base de données
     * @throws SQLException exception en cas de fail requete SQL
     */
    abstract ArrayList<String> listerProcedures() throws SQLException;
  }
