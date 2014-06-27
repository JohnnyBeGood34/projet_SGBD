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
     * RequeteLister permet de générer automatiquement une requete rpéparée en fonction des parametres reçut.
     * @param classe, nom de la table à requeter
     * @param fields, ArrayList de chamos pour faire la restriction, peut être null
     * @param restriction, ArrayList de restriction correspondants respectivement aux champs et aux valeurs
     * @param value, ArrayList de valeurs permettant de faire la restriction
     */
    public abstract void requeteLister(String classe, ArrayList<String> fields,ArrayList<String> restriction,ArrayList<String> value);

    /**
     * requeteajouter permet de faire une insertion en fonction de l'objet reçut en parametre
     * @param objet, un objet du BOL
     */
    public abstract void requeteAjouter(Object objet);

    /**
     * requeteMiseAJour permet de faire une mise à jour sur une table en fonction de l'objet reçut
     * @param objet, un objet du BOL
     */
    public abstract void requeteMiseAJour(Object objet);

    /**
     * requeteSupprimer, permet de construire une requete préparée de suppression en fonction des parametres reçut
     * @param classe, nom de la table
     * @param fields, ArrayList de nom de champs pour faire la restriction
     * @param restriction, ArrayList de restriction
     * @param value, ArrayList de valeur pour les restrictions
     */
    public abstract void requeteSupprimer(String classe, ArrayList<String> fields,ArrayList<String> restriction,ArrayList<String> value);

    /**
     * getRequeteString, renvoi la requete créée par un appel de methode Ajout,Update,Select,Delete
     * @return la requete SQL sous forme de String
     */
    public abstract String getRequeteString();

    /**
     * getParametres, permet de récupérer les parametres des requetes préparées (insert, update,delete)
     * @return ArrayList de parametres à injecter dans la requete préparée
     */
    public abstract ArrayList<String> getParametres();

    /**
     * dumpDb permet de générer le fichier SQL du dump de la base de données
     * @param chemin, le chemin vers lequel on veut envoyer le fichier .sql
     * @throws SQLException exception en cas de fail requete SQL
     * @throws IOException exception en cas de fail entrée/sortie
     */
    public abstract void dumpDb(String chemin) throws SQLException, IOException;

    /**
     * getDumpDb, permet de renvoyer le SQL du dump de la base de données sous la forme de string
     * @return la requete de dump sous forme de string
     */
    public abstract String getDumpDb();

    /**
     * procedurelister, permet de construire l'appel à une procédure lister. La procédure doit être sous la forme
     * listerNomClasse
     * @param classe, la classe (table) sur laquelle on veut appeler la procédure
     * @param values, un arrayList de valeurs
     */
    public abstract void procedureLister(String classe,ArrayList<String> values);

    /**
     * procedureAjouter, permet de construire l'appel à une procédure pour ajouter
     * la procédure en base de données doit être sous la forme ajouterNomClasseObjetBol
     * @param objet, un obejt du BOL
     */
    public abstract void procedureAjouter(Object objet);

    /**
     * procedureModifier, permet de construire l'appel à une rpocédure pour mêtre à jour
     * la procédure doit être sous la forme modifierNomClasseObejetBol
     * @param objet, un objet du BOL
     */
    public abstract void procedureModifier(Object objet);

    /**
     * procedureSupprimer, permet de construire l'appel à une procédure pour supprimer
     * la procédure doit petre sous la forme supprimerNomClasse
     * @param classe, la table sur laquel on appele la procédure
     * @param values, values, les valeurs de restrictions
     */
    public abstract void procedureSupprimer(String classe,ArrayList<String> values);
}
