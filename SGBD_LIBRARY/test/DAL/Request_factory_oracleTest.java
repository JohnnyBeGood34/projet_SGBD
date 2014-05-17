/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import BOL.MaterielAchete;
import BOL.Prescripteurs;
import java.util.ArrayList;
import java.util.Date;
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
public class Request_factory_oracleTest
  {
    
    public Request_factory_oracleTest()
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
     * Test of getRequeteString method, of class Request_factory_oracle.
     */
    @Test
    public void testGetRequeteString()
      {
        System.out.println("getRequeteString");
        Request_factory_oracle instance = new Request_factory_oracle();
        String expResult = "";
        String result = instance.getRequeteString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
      }

    /**
     * Test of requeteLister method, of class Request_factory_oracle.
     */
    @Test
    public void testRequeteLister()
      {
        System.out.println("requeteLister test junit");
        String classe = "patient";
        String expResult = "SELECT * FROM patient WHERE numero = '1' AND date = '0';";
        ArrayList<String> fields = new ArrayList();
        ArrayList<String> value = new ArrayList();
        fields.add("numero");
        fields.add("date");
        value.add("1");
        value.add("0");
        Request_factory_oracle instance = new Request_factory_oracle();
        instance.requeteLister(classe, fields, value);
        System.out.println(instance.getRequeteString());
        assertTrue(instance.getRequeteString().equals(expResult));
      }

    /**
     * Test of requeteAjouter method, of class Request_factory_oracle.
     */
    @Test
    public void testRequeteAjouter()
      {
        System.out.println("TEST requeteAjouter---------");
        Date date = new Date();
        Object objet = new Prescripteurs(1,"nom","prenom",date,"0612457889","jonatyhan@gmail.com","aze","azeaz");
        Request_factory_oracle instance = new Request_factory_oracle();
        instance.requeteAjouter(objet);
          System.out.println("REQUETE+++++++++"+instance.getRequeteString());
          System.out.println("PARAMETRES++++++++"+instance.getParametres().toString());
      }

    /**
     * Test of requeteMiseAJour method, of class Request_factory_oracle.
     */
    @Test
    public void testRequeteMiseAJour()
      {
        System.out.println("TEST requete mise a jour---------");
        Date date = new Date();
        Object objet = new Prescripteurs(1,"nom","prenom",date,"0612457889","jonatyhan@gmail.com","aze","azeaz");
        Request_factory_oracle instance = new Request_factory_oracle();
        instance.requeteMiseAJour(objet);
          System.out.println("REQUETE+++++++++"+instance.getRequeteString());
          System.out.println("PARAMETRES++++++++"+instance.getParametres().toString());
      }

    /**
     * Test of requeteSupprimer method, of class Request_factory_oracle.
     */
    @Test
    public void testRequeteSupprimer()
      {
        System.out.println("requeteSupprimer");
        String classe = "patient";
        String expResult = "SELECT * FROM patient WHERE numero = '1' AND date = '0';";
        ArrayList<String> fields = new ArrayList();
        ArrayList<String> value = new ArrayList();
        fields.add("numero");
        fields.add("date");
        value.add("1");
        value.add("0");
        Request_factory_oracle instance = new Request_factory_oracle();
        instance.requeteSupprimer(classe, fields, value);
        //instance.requeteSupprimer(objet);
          System.out.println(instance.getRequeteString());
      }
    
  }
