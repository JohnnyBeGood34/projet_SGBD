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
import java.io.IOException;
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
    private final ArrayList<String> restriction = new ArrayList<>();
    private final ArrayList<String> values = new ArrayList<>();
    private final ArrayList<String> fields = new ArrayList<>();
    private final ArrayList<String> listChampsClass = new ArrayList<>();
    private Manager_DAO manager ;
    private boolean boolProcedure = false;
    
    
   
    public void ChoixClasse() throws ClassNotFoundException {
/*******************************************************************FONCTION DE MENU ******************************************************/
 
        do {
            //Je nettoie toutes les listes avant de faire de nouvelle modification
            clearList();
            System.out.println("BIENVENUE DANS LE PROJET SGBD DE DUPRE_S, AFFRE_J, CHASSANG_C ET SALLES_K");
            System.out.println("1 - MaterielMedicalMaterielAchat");
            System.out.println("2 - MaterielMedicalMaterielLoue");
            System.out.println("3 - PartenairePatient");
            System.out.println("4 - PartenairePrescripteur");
            System.out.println("5 - Dump de la base");
            System.out.println("6 - Quitter");
            
            _intChoixClasse = ConsoleReader.readInt("Choix numéro ?");
            
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
            }
            else if(_intChoixClasse == 5){
                menuDumpBD();
            }
            else if (_intChoixClasse == 6) {
                System.exit(0);
            } else {
                erreur();
            }
        } while (_intChoixClasse != 6);
    }
    
/*******************************************************************FONCTION DE SOUS MENU ******************************************************/
    
    // Fonction permettant de choisir la modif à faire en base
    private void menuChoixMethode() throws ClassNotFoundException{
        do {
            //Je nettoie toutes les listes avant de faire de nouvelle modification
            clearList();
            //Je déclare ici mon manager afin de le réinstancié à chaque chargement du sous menu pour de pas concaténer les objets
            manager = new Manager_DAO("Oracle");
            System.out.println(_nomClasse);
            System.out.println("1 - Lister");
            System.out.println("2 - Ajouter");
            System.out.println("3 - Modifier");
            System.out.println("4 - Supprimer");
            System.out.println("5 - Retour aux choix des classes");
            _intChoixMethode = ConsoleReader.readInt("Choix numéro ?");
            if (_intChoixMethode == 1) {methodeLister();}
            else if (_intChoixMethode == 2) {methodeAjouter();}
            else if (_intChoixMethode == 3) {methodeModifier();}
            else if (_intChoixMethode == 4) {methodeSupprimer();}
            else if (_intChoixMethode == 5) {ChoixClasse();}
            else{erreur();}
        } while (_intChoixMethode != 5);
    }
/******************************************************************FONCTION DE MENU DUMP*********************************************************/
     
    //Fonction permettant de choisir le dump à faire
    private void menuDumpBD() throws ClassNotFoundException{
        int _intChoixDump;
        do{
            System.out.println("1 - Dump de la base vers un chemin");
            System.out.println("2 - Dump sous forme de String");
            System.out.println("3 - Retour");
            _intChoixDump = ConsoleReader.readInt("Choix numéro ?"); 
            
            if (_intChoixDump == 1) {                                                    
                    dumpBD();  
                }  
            else if(_intChoixDump == 2){
                getDumpBD();
            }
            else{
                erreur();
            }       
        }while(_intChoixDump !=3);        
    }
/******************************************************************FONCTION DE MENU PROCEDURE*********************************************************/
     
    //Fonction permettant de choisir si on passe par une procédure
    private void menuProcedure() throws ClassNotFoundException{
        System.out.println("Voulez-vous faire appel à une procédure ?");
        String _reponse =ConsoleReader.readString("O/N");
        if(null != _reponse.toUpperCase())switch (_reponse.toUpperCase()) {
            
            case "O":                                                                       
                this.boolProcedure = true;
                break;
            case "N":                                                                    
                this.boolProcedure = false;
                break;
            default:
                erreur();
                menuProcedure();
                break;
        }
    }
      
    
/******************************************************FONCTION LISTER**************************************************************************/
/******************************************CA MARCHE POUR UN RESULTAT DE SORTIE MAIS PAS POUR UNE LISTE*******************************************/
    
    private void methodeLister(){
        saisiFields();
        saisiRestriction();
        saisiValues();
        JSONObject resultat = new JSONObject();
        
        try {
            resultat = manager.select(_nomClasse, fields, restriction, values);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("JSON RESULTAT" + resultat.toJSONString());
    }
    
    
/******************************************************FONCTION AJOUTER**************************************************************************/
    private void methodeAjouter() throws ClassNotFoundException{
        menuProcedure();
        Class classCourante = Class.forName("BOL." + _nomClasse);
        Field[] fieldsSuperClass = classCourante.getSuperclass().getDeclaredFields();
        Field[] fieldsClass = classCourante.getDeclaredFields();
        // j = 1 pour ne pas afficher l'ID de la classe mère
        for (int j = 1, m = fieldsSuperClass.length; j < m; j++) {
            listChampsClass.add(ConsoleReader.readString(fieldsSuperClass[j].getName()));
        }
        for (int i = 0, k = fieldsClass.length; i < k; i++) {
            listChampsClass.add(ConsoleReader.readString(fieldsClass[i].getName()));
        }
        /************ SI MaterielMedicalMaterielAchat *********************/
        if (_intChoixClasse == 1) {
           MaterielMedicalMaterielAchat objet = new MaterielMedicalMaterielAchat(0, Integer.parseInt(listChampsClass.get(0)), Integer.parseInt(listChampsClass.get(1)),
                   listChampsClass.get(2), listChampsClass.get(3), Float.parseFloat(listChampsClass.get(4)), Integer.parseInt(listChampsClass.get(5)), listChampsClass.get(6),
                   Float.parseFloat(listChampsClass.get(7)), listChampsClass.get(8),Integer.parseInt(listChampsClass.get(9)),  Integer.parseInt(listChampsClass.get(10)));
            try {
                System.out.println("resultat boool**************" + boolProcedure);
                manager.insert(objet,boolProcedure);
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        /************ SI MaterielMedicalMaterielLoue *********************/
        if (_intChoixClasse == 2) {
            MaterielMedicalMaterielLoue objet = new MaterielMedicalMaterielLoue(0, Integer.parseInt(listChampsClass.get(0)), Integer.parseInt(listChampsClass.get(1)),
                   listChampsClass.get(2), listChampsClass.get(3), 
                    Float.parseFloat(listChampsClass.get(4)), Integer.parseInt(listChampsClass.get(5)), listChampsClass.get(6), Float.parseFloat(listChampsClass.get(7)), listChampsClass.get(8),
                    Integer.parseInt(listChampsClass.get(9)), listChampsClass.get(10), Integer.parseInt(listChampsClass.get(11)));
            try {
                manager.insert(objet,boolProcedure);
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        /************ SI PartenairePatient *********************/
        if (_intChoixClasse == 3) {
            PartenairePatient objet = new PartenairePatient(0, listChampsClass.get(0), listChampsClass.get(1), listChampsClass.get(2), listChampsClass.get(3), listChampsClass.get(4),
                    listChampsClass.get(5),listChampsClass.get(6));
            try {
                manager.insert(objet,boolProcedure);
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        /************ SI PartenairePrescripteur *********************/
        if (_intChoixClasse == 4) {
            PartenairePrescripteur objet = new PartenairePrescripteur(0, listChampsClass.get(0), listChampsClass.get(1), listChampsClass.get(2), listChampsClass.get(3), listChampsClass.get(4),
                    listChampsClass.get(5),listChampsClass.get(6));
            try {
                manager.insert(objet,boolProcedure);
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
/******************************************************FONCTION MODIFIER**************************************************************************/
    
    private void methodeModifier() throws ClassNotFoundException{
        menuProcedure();
        saisiValues();
        Class classCourante = Class.forName("BOL." + _nomClasse);
        Field[] fieldsSuperClass = classCourante.getSuperclass().getDeclaredFields();
        Field[] fieldsClass = classCourante.getDeclaredFields();
        // j = 1 pour ne pas afficher l'ID de la classe mère
        for (int j = 1, m = fieldsSuperClass.length; j < m; j++) {
            listChampsClass.add(ConsoleReader.readString(fieldsSuperClass[j].getName()));
        }
        // i = 0 car il s'agit de la classe fille donc pas d'id
        for (int i = 0, k = fieldsClass.length; i < k; i++) {
            listChampsClass.add(ConsoleReader.readString(fieldsClass[i].getName()));
        }
        /************ SI MaterielMedicalMaterielAchat *********************/
        if (_intChoixClasse == 1) {
                MaterielMedicalMaterielAchat objet = new MaterielMedicalMaterielAchat(Integer.parseInt(values.get(0)), Integer.parseInt(listChampsClass.get(0)), 
                        Integer.parseInt(listChampsClass.get(1)),listChampsClass.get(2), listChampsClass.get(3), 
                    Float.parseFloat(listChampsClass.get(4)), Integer.parseInt(listChampsClass.get(5)), listChampsClass.get(6), 
                        Float.parseFloat(listChampsClass.get(7)), listChampsClass.get(8),
                    Integer.parseInt(listChampsClass.get(9)),  Integer.parseInt(listChampsClass.get(10)));
                try {
                    manager.update(objet,boolProcedure);
                } catch (SQLException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        /************ SI MaterielMedicalMaterielLoue *********************/
        if (_intChoixClasse == 2) {
            MaterielMedicalMaterielLoue objet = new MaterielMedicalMaterielLoue(Integer.parseInt(values.get(0)), Integer.parseInt(listChampsClass.get(0)),
                    Integer.parseInt(listChampsClass.get(1)), listChampsClass.get(2), listChampsClass.get(3), 
                Float.parseFloat(listChampsClass.get(4)), Integer.parseInt(listChampsClass.get(5)), listChampsClass.get(6), Float.parseFloat(listChampsClass.get(7)),
                    listChampsClass.get(8),
                Integer.parseInt(listChampsClass.get(9)), listChampsClass.get(10),Integer.parseInt(listChampsClass.get(11)));
            try {
                manager.update(objet,boolProcedure);
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        /************ SI PartenairePatient *********************/
        /**********CA MARCHE************/
        if (_intChoixClasse == 3) {
            PartenairePatient objet = new PartenairePatient(Integer.parseInt(values.get(0)), listChampsClass.get(0), listChampsClass.get(1),
                listChampsClass.get(2), listChampsClass.get(3), listChampsClass.get(4), listChampsClass.get(5), listChampsClass.get(6));
            try {
                manager.update(objet,boolProcedure);
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        /************ SI PartenairePrescripteur *********************/
        /**********CA MARCHE************/
        if (_intChoixClasse == 4) {
            PartenairePrescripteur objet = new PartenairePrescripteur(Integer.parseInt(values.get(0)), listChampsClass.get(0), listChampsClass.get(1),
                listChampsClass.get(2), listChampsClass.get(3), listChampsClass.get(4), listChampsClass.get(5), listChampsClass.get(6));
            try {
                manager.update(objet,boolProcedure);
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
/******************************************************FONCTION SUPPRIMER**************************************************************************/
    
    private void methodeSupprimer() throws ClassNotFoundException{
        menuProcedure();
        saisiFields();
        saisiRestriction();
        saisiValues();
        try {
            JSONObject resultat = manager.delete(_nomClasse, fields, restriction, values,boolProcedure);
            System.out.println(resultat.get("result"));
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
/******************************TOUT LE RESTE SONT DES FONCTIONS D'APPEL GENERALISER**********************************************/
    
    
/*******************************************************************FONCTION DE SAISI DE VALEUR ******************************************************/
    //Fonction d'enregistrement du champs saisi par l'utilisateur
    private void saisiFields(){
        _fieldsSaisi = ConsoleReader.readString("Quel champ de la table voulez-vous utiliser?  ");
        fields.add(_fieldsSaisi);
    }
    //Fonction d'enregistrement de la values saisi par l'utilisateur
    private void saisiValues(){
        _valuesSaisi = ConsoleReader.readString("Quel id voulez-vous utiliser?  ");
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
    
/********************************************************************FONCTION DUMB DE LA BASE AVEC CHEMIN D'ACCES*************************************/  
    //Fonction qui génère un fichier .sql de l'ensemble de la base de donnée est le met sur un chemin spécifique
    private void dumpBD() {
        manager = new Manager_DAO("Oracle");
       final String _chemin =ConsoleReader.readString("Chemin d'enregistrement du fichier ?");
        System.out.println("Confirmez-vous la création du fichier de dump de la BD vers "+ _chemin + " ?");
        String _reponse =ConsoleReader.readString("O/N");

        if(null != _reponse.toUpperCase())switch (_reponse.toUpperCase()) {
            
            case "O":                                                                       
                System.out.println("En cours de création... ");
                
                Thread threadDump=new Thread( new Runnable(
                        
                ) {
                    @Override
                    public void run() {
                        
                    try{                    
                    long begin=System.currentTimeMillis();
                    manager.dumpDb(_chemin);
                    long end=System.currentTimeMillis();
                    System.out.println("Fichier cree en " + (end-begin)/1000+" secondes ! ");
                
                  }
                   catch(SQLException | IOException e){
                     System.out.println("Probleme d'enregistrement du fichier. Veuillez recommencer. ");
                  }    
                }
                }                
                );
                threadDump.start();
                break;
                
            case "N":
                System.out.println("Veuillez recommencer");
                break;
            default:
                erreur();
                break;
        }
    }
/********************************************************************FONCTION DUMB DE LA BASE SOUS FORME DE STRING*************************************/  
    private void getDumpBD(){
        manager = new Manager_DAO("Oracle");
        System.out.println("En cours de création...");
        String _responseDump = manager.getDumpDb();
        System.out.println(_responseDump);
    }
   
}
