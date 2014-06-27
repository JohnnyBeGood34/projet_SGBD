/*
 * Projet EPSI Montpellier 2014.
 * Jonathan Affre, Stephane Dupre, Kevin Salles, Cyrille Chanssang.
 */

package DAL;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author JOHN
 * Interface pour les Request_factory_SGBD qui seront des fabriques de requetes pour le sgbd que l'on souhaite
 */
public interface IBDD {

    /**
     *
     * @param classe
     * @param fields
     * @param restriction
     * @param value
     */
    public abstract void requeteLister(String classe, ArrayList<String> fields,ArrayList<String> restriction,ArrayList<String> value);

    /**
     *
     * @param objet
     */
    public abstract void requeteAjouter(Object objet);

    /**
     *
     * @param objet
     */
    public abstract void requeteMiseAJour(Object objet);

    /**
     *
     * @param classe
     * @param fields
     * @param restriction
     * @param value
     */
    public abstract void requeteSupprimer(String classe, ArrayList<String> fields,ArrayList<String> restriction,ArrayList<String> value);

    /**
     *
     * @return
     */
    public abstract String getRequeteString();

    /**
     *
     * @return
     */
    public abstract ArrayList<String> getParametres();

    /**
     *
     * @param chemin
     * @throws SQLException
     * @throws IOException
     */
    public abstract void dumpDb(String chemin) throws SQLException, IOException;

    /**
     *
     * @return
     */
    public abstract String getDumpDb();

    /**
     *
     * @param classe
     * @param values
     */
    public abstract void procedureLister(String classe,ArrayList<String> values);

    /**
     *
     * @param objet
     */
    public abstract void procedureAjouter(Object objet);

    /**
     *
     * @param objet
     */
    public abstract void procedureModifier(Object objet);

    /**
     *
     * @param classe
     * @param values
     */
    public abstract void procedureSupprimer(String classe,ArrayList<String> values);
}
