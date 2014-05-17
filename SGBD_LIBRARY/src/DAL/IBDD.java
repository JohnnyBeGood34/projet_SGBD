/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import java.util.ArrayList;

/**
 *
 * @author JOHN
 * Interface pour les Request_factory_SGBD qui seront des fabriques de requetes pour le sgbd que l'on souhaite
 */
public interface IBDD {
    public abstract void requeteLister(String classe, ArrayList<String> fields,ArrayList<String> value);
    public abstract void requeteAjouter(Object objet);
    public abstract void requeteMiseAJour(Object objet);
    public abstract void requeteSupprimer(Object objet);
}
