/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import java.sql.Connection;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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
     * Test of setConnexion method, of class Manager_DAO.
     */
    @Test
    public void testSetConnexion() throws Exception
      {
        System.out.println("setConnexion");
        Connection connexion = null;
        Manager_DAO instance = new Manager_DAO("Oracle");
        instance.setConnexion(connexion);
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
        String classe = "MATERIELMEDICAL";
        ArrayList<String> fields = null;
        ArrayList<String> values = null;
        ArrayList<Object> expResult = null;
        ArrayList<Object> result = manager.lister(classe, fields, values);
        //assertEquals(expResult, result);
      }
    
  }
