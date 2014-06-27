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

    /**
     *
     * @return
     * @throws SQLException
     * @throws IOException
     */
    abstract String dumpDb() throws SQLException, IOException;

    /**
     *
     * @param contenu
     * @param chemin
     * @throws IOException
     */
    abstract void writeDumpFile(String contenu, String chemin) throws IOException;

    /**
     *
     * @return
     * @throws SQLException
     */
    abstract ArrayList<String> listerTables() throws SQLException;

    /**
     *
     * @return
     * @throws SQLException
     */
    abstract ArrayList<String> listerVues() throws SQLException;

    /**
     *
     * @return
     * @throws SQLException
     */
    abstract ArrayList<String> listerTriggers() throws SQLException;

    /**
     *
     * @return
     * @throws SQLException
     */
    abstract ArrayList<String> listerSequences() throws SQLException;

    /**
     *
     * @return
     * @throws SQLException
     */
    abstract ArrayList<String> listerProcedures() throws SQLException;
  }
