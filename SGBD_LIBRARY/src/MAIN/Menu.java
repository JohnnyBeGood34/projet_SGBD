/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MAIN;

import BOL.MaterielMedicalMaterielAchat;
import BOL.MaterielMedicalMaterielLoue;
import BOL.PartenairePatient;
import BOL.PartenairePrescripteur;
import DAL.Manager_DAO;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;

/**
 *
 * @author Kevin
 */
public class Menu {

    private int _intChoixClasse;
    private int _intChoixMethode;
    private String _nomClasse;
    private String _restrictionSaisi;
    private String _valuesSaisi;
    private String _fieldsSaisi;
    private final ArrayList<String> restriction = new ArrayList();
    private final ArrayList<String> values = new ArrayList();
    private final ArrayList<String> fields = new ArrayList();
    private final ArrayList<String> listChampsClass = new ArrayList<>();
    
    
    public void ChoixClasse() throws ClassNotFoundException {
        clearList();
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
                    _nomClasse = "MaterielMedicalMaterielLoue";
                }
                if (_intChoixClasse == 3) {
                    _nomClasse = "PartenairePatient";
                }
                if (_intChoixClasse == 4) {
                    _nomClasse = "PartenairePrescripteur";
                }
                menuChoixMethode();
            } else if (_intChoixClasse == 5) {
                System.exit(0);
            } else {
                erreur();
                ChoixClasse();
            }
        } while (_intChoixClasse != 5);
    }
    
/*******************************************************************FONCTION DE MENU ******************************************************/
    
    // Fonction permettande de choisir la modif à faire en base
    private void menuChoixMethode() throws ClassNotFoundException{
        do {
            System.out.println(_nomClasse);
            System.out.println("1 - Lister");
            System.out.println("2 - Ajouter");
            System.out.println("3 - Modifier");
            System.out.println("4 - Supprimer");
            System.out.println("5 - Retour aux choix des classes");
            _intChoixMethode = ConsoleReader.readInt("Choix numéro ");
            if (_intChoixMethode == 1) {methodeLister();}
            else if (_intChoixMethode == 2) {methodeAjouter();}
            else if (_intChoixMethode == 3) {methodeModifier();}
            else if (_intChoixMethode == 4) {methodeSupprimer();}
            else if (_intChoixMethode == 5) {erreur(); menuChoixMethode();}
        } while (_intChoixMethode != 5);
    }
    
    
    
/******************************************************FONCTION LISTER**************************************************************************/
    private void methodeLister(){
        saisiFields();
        saisiRestriction();
        saisiValues();
    }
/******************************************************FONCTION AJOUTER**************************************************************************/
    private void methodeAjouter() throws ClassNotFoundException{
        Manager_DAO manager = new Manager_DAO("Oracle");
        Class classCourante = Class.forName("BOL." + _nomClasse);
        Field[] fieldsSuperClass = classCourante.getSuperclass().getDeclaredFields();
        Field[] fieldsClass = classCourante.getDeclaredFields();
        for (int j = 0, m = fieldsSuperClass.length; j < m; j++) {
            listChampsClass.add(ConsoleReader.readString(fieldsSuperClass[j].getName()));
        }
        for (int i = 0, k = fieldsClass.length; i < k; i++) {
            listChampsClass.add(ConsoleReader.readString(fieldsClass[i].getName()));
        }
        /************ SI MaterielMedicalMaterielAchat *********************/
        if (_intChoixClasse == 1) {
           MaterielMedicalMaterielAchat objet = new MaterielMedicalMaterielAchat(0, Integer.parseInt(listChampsClass.get(1)), Integer.parseInt(listChampsClass.get(2)),
                   listChampsClass.get(3), listChampsClass.get(4), 
                    Float.parseFloat(listChampsClass.get(5)), Integer.parseInt(listChampsClass.get(6)), listChampsClass.get(7), Float.parseFloat(listChampsClass.get(8)), listChampsClass.get(9),
                    Integer.parseInt(listChampsClass.get(10)),  Integer.parseInt(listChampsClass.get(11)));
            try {
                manager.insert(objet);
                /*IL fau maintenant appeller la méthode avec les valeurs rentré*/
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        /************ SI MaterielMedicalMaterielLoue *********************/
        if (_intChoixClasse == 2) {
            MaterielMedicalMaterielLoue objet = new MaterielMedicalMaterielLoue(0, Integer.parseInt(listChampsClass.get(1)), Integer.parseInt(listChampsClass.get(2)),
                   listChampsClass.get(3), listChampsClass.get(4), 
                    Float.parseFloat(listChampsClass.get(5)), Integer.parseInt(listChampsClass.get(6)), listChampsClass.get(7), Float.parseFloat(listChampsClass.get(8)), listChampsClass.get(9),
                    Integer.parseInt(listChampsClass.get(10)), listChampsClass.get(11), Integer.parseInt(listChampsClass.get(12)));
            try {
                manager.insert(objet);
                /*IL fau maintenant appeller la méthode avec les valeurs rentré*/
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        /************ SI PartenairePatient *********************/
        if (_intChoixClasse == 3) {
            PartenairePatient objet = new PartenairePatient(0, listChampsClass.get(1), listChampsClass.get(2), listChampsClass.get(3), listChampsClass.get(4), listChampsClass.get(5),
                    listChampsClass.get(6),listChampsClass.get(7));
            try {
                manager.insert(objet);
                /*IL fau maintenant appeller la méthode avec les valeurs rentré*/
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        /************ SI PartenairePrescripteur *********************/
        if (_intChoixClasse == 4) {
            PartenairePrescripteur objet = new PartenairePrescripteur(0, listChampsClass.get(1), listChampsClass.get(2), listChampsClass.get(3), listChampsClass.get(4), listChampsClass.get(5),
                    listChampsClass.get(6),listChampsClass.get(7));
            try {
                manager.insert(objet);
                /*IL fau maintenant appeller la méthode avec les valeurs rentré*/
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        clearList();
    }
/******************************************************FONCTION MODIFIER**************************************************************************/
    private void methodeModifier() throws ClassNotFoundException{
        saisiFields();
        saisiRestriction();
        saisiValues();
        Manager_DAO manager = new Manager_DAO("Oracle");
        /************ SI MaterielMedicalMaterielAchat *********************/
        if (_intChoixClasse == 1) {
            fields.add("IDMateriel");
            try {
                JSONObject objetJsonSelect = manager.select(_nomClasse, fields, restriction, values);
                System.out.println(objetJsonSelect.toJSONString());
                MaterielMedicalMaterielAchat objet = new MaterielMedicalMaterielAchat(Integer.parseInt(fields.get(0)), Integer.parseInt(listChampsClass.get(1)), Integer.parseInt(listChampsClass.get(2)),
                   listChampsClass.get(3), listChampsClass.get(4), 
                    Float.parseFloat(listChampsClass.get(5)), Integer.parseInt(listChampsClass.get(6)), listChampsClass.get(7), Float.parseFloat(listChampsClass.get(8)), listChampsClass.get(9),
                    Integer.parseInt(listChampsClass.get(10)),  Integer.parseInt(listChampsClass.get(11)));
                try {
                manager.update(objet);
                } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (NoSuchMethodException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        /************ SI MaterielMedicalMaterielLoue *********************/
        if (_intChoixClasse == 2) {

        }
        /************ SI PartenairePatient *********************/
        if (_intChoixClasse == 3) {

        }
        /************ SI PartenairePrescripteur *********************/
        if (_intChoixClasse == 4) {

        }
        //Je nettoie la listChampsClass pour de prochaine modification
        clearList();
    }
/******************************************************FONCTION SUPPRIMER**************************************************************************/
    private void methodeSupprimer(){
        Manager_DAO manager = new Manager_DAO("Oracle");
        saisiFields();
        saisiRestriction();
        saisiValues();
        try {
            manager.delete(_nomClasse, fields, restriction, values);
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        clearList();
    }
/*******************************************************************FONCTION DE SAISI DE VALEUR ******************************************************/
    //Fonction d'enregistrement du champs saisi par l'utilisateur
    private void saisiFields(){
        _fieldsSaisi = ConsoleReader.readString("Quel champ voulez-vous utiliser?  ");
        fields.add(_fieldsSaisi);
    }
    //Fonction d'enregistrement de la values saisi par l'utilisateur
    private void saisiValues(){
        _valuesSaisi = ConsoleReader.readString("Quel valeur voulez-vous utiliser?  ");
        values.add(_valuesSaisi);
    }
    //Fonction d'enregistrement de la restriction saisi par l'utilisateur
    private void saisiRestriction(){
        _restrictionSaisi = ConsoleReader.readString("Quel restriction voulez-vous faire?");
        restriction.add(_restrictionSaisi);
    }
    
/*******************************************************************FONCTION DE NETTOYAGE ******************************************************/
    //Fonction qui nettoie toutes les listes pour les prochaines utilisations.
    private void clearList(){
        listChampsClass.clear();
        fields.clear();
        restriction.clear();
        values.clear();
    }
/*******************************************************************FONCTION DE GESTION D'ERREUR ******************************************************/
    //Fonction qui génère à l'utilisateur une erreur de saisie
    private void erreur() {
        System.out.println("Valeur incorrecte saisie, veuillez recommencer");
    }
}
