/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import BOL.Partenaire;
import BOL.PartenairePatient;
import java.util.ArrayList;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kevin
 */
public class Request_factory_oracleTest {
    
    public Request_factory_oracleTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getRequeteString method, of class Request_factory_oracle.
     */
    @Test
    public void testGetRequeteString() {
    }

    /**
     * Test of getParametres method, of class Request_factory_oracle.
     */
    @Test
    public void testGetParametres() {
    }

    /**
     * Test of dumpDb method, of class Request_factory_oracle.
     */
    @Test
    public void testDumpDb() throws Exception {
        System.out.println("TEST SUR LA METHODE ---dumpDb---");
        String cheminFichierDump = "C:/Projet_SGBD/projet_SGBD/SGBD_LIBRARY/";
        /*Request_factory_oracle instance = new Request_factory_oracle();
        instance.dumpDb(cheminFichierDump);
        System.out.println("FINI, le fichier ce trouve ici: " + cheminFichierDump);*/
    }

    /**
     * Test of getDumpDb method, of class Request_factory_oracle.
     */
    @Test
    public void testGetDumpDb() {
        System.out.println("TEST SUR LA METHODE ---getDumpDb--");
        /*Request_factory_oracle instance = new Request_factory_oracle();
        String result = instance.getDumpDb();
        System.out.println(result);*/
    }

    /**
     * Test of requeteLister method, of class Request_factory_oracle.
     */
    @Test
    public void testRequeteLister() {
        System.out.println("TEST SUR LA METHODE ---requeteLister--");
        String classe = "PartenairePatient";
        ArrayList<String> fields = new ArrayList();
        ArrayList<String> restriction = new ArrayList();
        ArrayList<String> values = new ArrayList();
        fields.add("nom");
        restriction.add("=");
        values.add("Neil");
        Request_factory_oracle instance = new Request_factory_oracle();
        instance.requeteLister(classe, fields, restriction, values);
        String result = instance.getRequeteString();
        System.out.println(result);
        assertEquals("SELECT * FROM PartenairePatient WHERE nom = 'Neil'", result);
    }

    /**
     * Test of requeteAjouter method, of class Request_factory_oracle.
     */
    @Test
    public void testRequeteAjouter() {
        System.out.println("TEST SUR LA METHODE ---RequeteAjouter---");
        //TEST REQUETE NORMALE
        Partenaire objet = new PartenairePatient(10, "requete", "simple", "16/06/1998", "0612457889", "mail@factisse.fr", "0612454547897", "654987654");
        Request_factory_oracle instance = new Request_factory_oracle();
        instance.requeteAjouter(objet);
        String result = instance.getRequeteString();
        System.out.println(result);
        assertEquals("INSERT INTO PartenairePatient(idPartenaire,nom,prenom,dateDeNaissance,telephonePartenaire,mail,numeroSecu,numeroMutuelle)VALUES(?,?,?,?,?,?,?,?)", result);
    }

    /**
     * Test of requeteMiseAJour method, of class Request_factory_oracle.
     */
    @Test
    public void testRequeteMiseAJour() {
        System.out.println("TEST SUR LA METHODE ---RequeteMiseAJour---");
        //TEST REQUETE NORMALE
        Partenaire objet = new PartenairePatient(10, "requete", "simple", "16/06/1998", "69", "mail@factisse.fr", "0612454547897", "654987654");
        Request_factory_oracle instance = new Request_factory_oracle();
        instance.requeteMiseAJour(objet);
        String result = instance.getRequeteString();
        System.out.println(result);
        assertEquals("UPDATE PartenairePatient SET nom=?,prenom=?,dateDeNaissance=?,telephonePartenaire=?,mail=?,numeroSecu=?,numeroMutuelle=? WHERE idPartenaire=?", result);
    }

    /**
     * Test of requeteSupprimer method, of class Request_factory_oracle.
     */
    @Test
    public void testRequeteSupprimer() {
        System.out.println("TEST SUR LA METHODE ---RequeteSupprimer---");
        String classe = "PartenairePatient";
        ArrayList<String> fields = new ArrayList();
        ArrayList<String> restriction = new ArrayList();
        ArrayList<String> values = new ArrayList();
        fields.add("nom");
        restriction.add("=");
        values.add("Neil");
        Request_factory_oracle instance = new Request_factory_oracle();
        instance.requeteSupprimer(classe, fields, restriction, values);
        String result = instance.getRequeteString();
        System.out.println(result);
        assertEquals("DELETE FROM PartenairePatient WHERE nom = 'Neil'", result);
    }


    /**
     * Test of procedureModifier method, of class Request_factory_oracle.
     */
    @Test
    public void testProcedureModifier() {
        System.out.println("TEST SUR LA METHODE ---ProcedureModifier---");
        PartenairePatient objet = new PartenairePatient(10, "requete", "simple", "16/06/1998", "0612457889", "mail@factisse.fr", "0612454547897", "654987654");
        Request_factory_oracle instance = new Request_factory_oracle();
        instance.procedureModifier(objet);
        String result = instance.getRequeteString();
        System.out.println(result);
        assertEquals("{CALL modifierPartenairePatient(?,?,?,?,?,?,?,?)}", result);
    }


    /**
     * Test of procedureAjouter method, of class Request_factory_oracle.
     */
    @Test
    public void testProcedureAjouter() {
        System.out.println("TEST SUR LA METHODE ---ProcedureAjouter---");
        PartenairePatient objet = new PartenairePatient(10, "requete", "simple", "16/06/1998", "0612457889", "mail@factisse.fr", "0612454547897", "654987654");
        Request_factory_oracle instance = new Request_factory_oracle();
        instance.procedureAjouter(objet);
        String result = instance.getRequeteString();
        System.out.println(result);
        assertEquals("{CALL ajouterPartenairePatient(?,?,?,?,?,?,?,?)}", result);
    }
    
}
