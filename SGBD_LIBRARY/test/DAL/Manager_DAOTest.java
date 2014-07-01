/*
 * Projet EPSI Montpellier 2014.
 * Jonathan Affre, Stephane Dupre, Kevin Salles, Cyrille Chanssang.
 */
package DAL;

import BOL.PartenairePatient;
import BOL.PartenairePrescripteur;
import java.util.ArrayList;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author JOHN
 */
public class Manager_DAOTest
  {

    public Manager_DAOTest()
      {
      }

    @BeforeClass
    public static void setUpClass()
      {
      }

    @AfterClass
    public static void tearDownClass()
      {
      }

    @Before
    public void setUp()
      {
      }

    @After
    public void tearDown()
      {
      }

    /**
     * Test of dumpDb method, of class Manager_DAO.
     */
    @Test
    public void testDumpDb() throws Exception
      {
        System.out.println("TEST SUR LA METHODE ---dumpDb---");
        /*String cheminFichierDump = "a:/dev/";
         Manager_DAO instance = new Manager_DAO("Oracle");
         //Check si el fichier à bien été créé au bon endroit
         instance.dumpDb(cheminFichierDump);*/
      }

    /**
     * Test of getDumpDb method, of class Manager_DAO.
     */
    @Test
    public void testGetDumpDb()
      {
        System.out.println("TEST SUR LA METHODE ---getDumpDb--");
        Manager_DAO instance = new Manager_DAO("Oracle");
        String result = instance.getDumpDb();
        //System.out.println(result);
      }

    /**
     * Test of select method, of class Manager_DAO.
     */
    @Test
    public void testSelect() throws Exception
      {
        System.out.println("TEST SUR LA METHODE ---select--");
        String classe = "PartenairePatient";
        ArrayList<String> fields = new ArrayList();
        ArrayList<String> restriction = new ArrayList();
        ArrayList<String> values = new ArrayList();
        fields.add("nom");
        restriction.add("=");
        values.add("Neil");
        Manager_DAO instance = new Manager_DAO("Oracle");
        JSONObject result = instance.select(classe, fields, restriction, values);
        //assertEquals("{\"0\":{\"DATEDENAISSANCE\":\"2049-11-01 00:00:00.0\",\"MAIL\":\"pede.malesuada@vitaeerat.net\",\"IDPARTENAIRE\":\"4\",\"NOM\":\"Neil\",\"PRENOM\":\"Dillon\",\"TELEPHONEPARTENAIRE\":\"0259408706\",\"NUMEROSECU\":\"1552264107184277\"}}", result.toJSONString());
          System.out.println(result.toJSONString());
      }

    /**
     * Test of insert method, of class Manager_DAO.
     */
    @Test
    public void testInsert() throws Exception
      {
        System.out.println("TEST SUR LA METHODE ---insert---");
        //TEST REQUETE NORMALE
        PartenairePrescripteur objet = new PartenairePrescripteur(10, "requete", "simple", "16/06/1998", "0612457889", "mail@factisse.fr", "0612454547897", "654987654");
         boolean isProcedure = false;
         Manager_DAO instance = new Manager_DAO("Oracle");
         /*JSONObject result = instance.insert(objet, isProcedure);
         System.out.println("REQUETE INSERT NORMALE++++++++++++" + result.toJSONString());*/
         //TEST PROCEDURE
         PartenairePrescripteur objetProcedure = new PartenairePrescripteur(20, "requete", "procedure", "16/06/1998", "0612457889", "mail@factisse.fr", "0612454547897", "654987654");
         boolean isProcedureProc = true;
         /*JSONObject resultProcedure = instance.insert(objetProcedure, isProcedureProc);
         System.out.println("REQUETE INSERT PAR PROCEDURE++++++++++" + resultProcedure);*/
      }

    /**
     * Test of update method, of class Manager_DAO.
     */
    @Test
    public void testUpdate() throws Exception
      {
        System.out.println("TEST SUR LA METHODE ---update---");
        PartenairePrescripteur objet = new PartenairePrescripteur(174, "updated by simple request", "simple", "16/06/1998", "0612457889", "mail@factisse.fr", "0612454547897", "654987654");
         boolean isProcedure = false;
         Manager_DAO instance = new Manager_DAO("Oracle");
         /*JSONObject result = instance.update(objet, isProcedure);

         System.out.println("RESULT UPDATE SIMPLE------" + result);*/
         PartenairePrescripteur objet2 = new PartenairePrescripteur(173, "updated by simple request", "simple", "16/06/1998", "0612457889", "mail@factisse.fr", "0612454547897", "654987654");
         /*JSONObject resultProcedure = instance.update(objet2, true);
         System.out.println("RESULT PROCEDURE UPDATE-----------" + resultProcedure);*/
      }

    /**
     * Test of delete method, of class Manager_DAO.
     */
    @Test
    public void testDelete() throws Exception
      {
        System.out.println("TEST SUR LA METHODE ---delete---");
        String classe = "PartenairePrescripteur";
        ArrayList<String> fields = new ArrayList();
        ArrayList<String> restriction = new ArrayList();
        ArrayList<String> values = new ArrayList();
        fields.add("nom");
        restriction.add("=");
        values.add("updated by simple request");
        Boolean isProcedure = false;
        Manager_DAO instance = new Manager_DAO("Oracle");
        /*JSONObject result = instance.delete(classe, fields, restriction, values, isProcedure);
        System.out.println(result);*/
      }

  }
