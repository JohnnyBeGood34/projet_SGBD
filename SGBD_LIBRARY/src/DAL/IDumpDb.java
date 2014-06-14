/*
 * Projet EPSI Montpellier 2014.
 * Jonathan Affre, Stephane Dupre, Kevin Salles, Cyrille Chanssang.
 */

package DAL;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Interface pour les DumpDb permettant de renseigner les différentes methodes pour chaqu SGBD
 * @author JOHN
 */
public interface IDumpDb
  {
    abstract String dumpDb() throws SQLException, IOException;
    abstract void writeDumpFile(String contenu, String chemin) throws IOException;
    abstract ArrayList<String> listerTables() throws SQLException;
    abstract ArrayList<String> listerVues() throws SQLException;
    abstract ArrayList<String> listerTriggers() throws SQLException;
    abstract ArrayList<String> listerSequences() throws SQLException;
    abstract ArrayList<String> listerProcedures() throws SQLException;
  }
