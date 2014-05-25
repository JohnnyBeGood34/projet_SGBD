/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BOL.PartenairePrescripteur;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
     * Test of setRequestFactory method, of class Manager_DAO.
     */
    @Test
    public void testSetRequestFactory()
      {
        System.out.println("setRequestFactory");
        IBDD requestFactory = null;
        Manager_DAO instance = new Manager_DAO("Oracle");
        instance.setRequestFactory(requestFactory);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
      }

    /**
     * Test of lister method, of class Manager_DAO.
     */
    @Test
    public void testLister() throws Exception
      {
        System.out.println("lister");
        Manager_DAO manager = new Manager_DAO("Oracle");
        String classe = "PartenairePatient";
        ArrayList<String> fields = new ArrayList();
        fields.add("nom");
        fields.add("prenom");
        ArrayList<String> values = new ArrayList();
        values.add("Brady");
        values.add("Ingram");
        JSONObject result = manager.select(classe, fields, values);

        System.out.println("JSON RESULTAT" + result.toJSONString());

        //assertEquals(expResult, result);
      }

    /**
     * Test of lister method, of class Manager_DAO.
     */
    @Test
    public void testAjouter() throws Exception
      {
        System.out.println("Ajouter");
        Manager_DAO manager = new Manager_DAO("Oracle");
        Date date = new Date();
        Object objet = new PartenairePrescripteur(1, "nom", "prenom", date, "0612457889", "jonatyhan@gmail.com", "aze", "azeaz");
        manager.insert(objet);
        //assertEquals(expResult, result);
      }

    /**
     * Test of lister method, of class Manager_DAO.
     */
    @Test
    public void testUpdate() throws Exception
      {
        System.out.println("Update");
        Manager_DAO manager = new Manager_DAO("Oracle");
        Date date = new Date();
        Object objet = new PartenairePrescripteur(1, "nom", "prenom", date, "0612457889", "jonatyhan@gmail.com", "aze", "azeaz");
        manager.update(objet);
        //assertEquals(expResult, result);
        
      }
  }
