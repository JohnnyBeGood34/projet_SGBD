/*
 * Projet EPSI Montpellier 2014.
 * Jonathan Affre, Stephane Dupre, Kevin Salles, Cyrille Chanssang.
 */

package DAL;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * @author JOHN
 * Interface pour les Request_factory_SGBD qui seront des fabriques de requetes pour le sgbd que l'on souhaite
 */
public interface IBDD {
    public abstract void requeteLister(String classe, ArrayList<String> fields,ArrayList<String> restriction,ArrayList<String> value);
    public abstract void requeteAjouter(Object objet);
    public abstract void requeteMiseAJour(Object objet);
    public abstract void requeteSupprimer(String classe, ArrayList<String> fields,ArrayList<String> restriction,ArrayList<String> value);
    public abstract String getRequeteString();
    public abstract ArrayList<String> getParametres();
}
