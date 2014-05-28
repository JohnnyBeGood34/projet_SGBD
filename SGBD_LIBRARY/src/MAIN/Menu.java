/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MAIN;

import java.lang.reflect.Field;
import java.util.ArrayList;

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
            System.out.println("2 - MaterielLoue");
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
                ArrayList<String> newVariable = new ArrayList<>();
                for (int j = 0, m = fieldsSuperClass.length; j < m; j++) {
                    newVariable.add(ConsoleReader.readString(fieldsSuperClass[j].getName()));
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
