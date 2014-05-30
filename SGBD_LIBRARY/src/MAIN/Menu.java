/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MAIN;

import BOL.MaterielMedicalMaterielAchat;
import DAL.Manager_DAO;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kevin
 */
public class Menu {

    private int _intChoixClasse;
    private String _nomClasse;

    public void ChoixClasse() throws ClassNotFoundException {
        do {
            System.out.println("BIENVENUE DANS LE PROJET SGBD DE DUPRE_S, AFFRE_J, CHASSANG_C ET SALLES_K");
            System.out.println("1 - MaterielMedicalMaterielAchat");
            System.out.println("2 - MaterielMedicalMaterielLoue");
            System.out.println("3 - PartenairePatient");
            System.out.println("4 - PartenairePrescripteur");
            System.out.println("5 - Quitter");
            _intChoixClasse = ConsoleReader.readInt("Choix numéro ");
            if (_intChoixClasse == 1 || _intChoixClasse == 2 || _intChoixClasse == 3 || _intChoixClasse == 4) {
                if (_intChoixClasse == 1) {
                    _nomClasse = "MaterielMedicalMaterielAchat";
                }
                if (_intChoixClasse == 2) {
                    _nomClasse = "MaterielLoue";
                }
                if (_intChoixClasse == 3) {
                    _nomClasse = "PartenairePatient";
                }
                if (_intChoixClasse == 4) {
                    _nomClasse = "PartenairePrescripteur";
                }
                ChoixMethode();
            } else if (_intChoixClasse == 5) {
                System.exit(0);
            } else {
                erreur();
                ChoixClasse();
            }
        } while (_intChoixClasse != 5);
    }

    private void ChoixMethode() throws ClassNotFoundException {
        Manager_DAO manager = new Manager_DAO("Oracle");
        int _intChoixMethode;
        Class classCourante = Class.forName("BOL." + _nomClasse);
        Field[] fieldsSuperClass = classCourante.getSuperclass().getDeclaredFields();
        Field[] fieldsClass = classCourante.getDeclaredFields();
        
        do {
            System.out.println(_nomClasse);
            System.out.println("1 - Lister");
            System.out.println("2 - Ajouter");
            System.out.println("3 - Modifier");
            System.out.println("4 - Supprimer");
            System.out.println("5 - Retour aux choix des classes");
            _intChoixMethode = ConsoleReader.readInt("Choix numéro ");
            if (_intChoixMethode == 1) {

            } else if (_intChoixMethode == 2) {
                ArrayList<String> listChampsClass = new ArrayList<>();
                for (int j = 0, m = fieldsSuperClass.length; j < m; j++) {
                    listChampsClass.add(ConsoleReader.readString(fieldsSuperClass[j].getName()));
                }
                for (int i = 0, k = fieldsClass.length; i < k; i++) {
                    listChampsClass.add(ConsoleReader.readString(fieldsClass[i].getName()));
                }
                if (_intChoixClasse == 1) {
                   MaterielMedicalMaterielAchat objet = new MaterielMedicalMaterielAchat(0, Integer.parseInt(listChampsClass.get(1)), Integer.parseInt(listChampsClass.get(2)),
                           listChampsClass.get(3), listChampsClass.get(4), 
                            Float.parseFloat(listChampsClass.get(5)), listChampsClass.get(6), listChampsClass.get(7), listChampsClass.get(8), listChampsClass.get(9),
                            listChampsClass.get(10),  Integer.parseInt(listChampsClass.get(11)));
                    try {
                        manager.insert(objet);
                        /*IL fau maintenant appeller la méthode avec les valeurs rentré*/
                    } catch (SQLException ex) {
                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (_intChoixClasse == 2) {
                }
                if (_intChoixClasse == 3) {
                }
                if (_intChoixClasse == 4) {
                }
            } else if (_intChoixMethode == 3) {
            } else if (_intChoixMethode == 4) {
            } else if (_intChoixMethode == 5) {
                ChoixClasse();
            } else {
                erreur();
                ChoixMethode();
            }
        } while (_intChoixMethode != 5);
    }

    private void erreur() {
        System.out.println("Valeur incorrecte saisie, veuillez recommencer");
    }
}
