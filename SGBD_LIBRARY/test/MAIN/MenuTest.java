/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MAIN;

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
public class MenuTest
  {
    
    public MenuTest()
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
     * Test of ChoixClasse method, of class Menu.
     */
    @Test
    public void testChoixClasse() throws Exception
      {
        System.out.println("ChoixClasse");
        Menu instance = new Menu();
        instance.ChoixClasse();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
      }
    
  }
