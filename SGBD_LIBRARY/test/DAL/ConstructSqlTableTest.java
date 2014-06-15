/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.sql.SQLException;
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
public class ConstructSqlTableTest
  {

    public ConstructSqlTableTest()
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
     * Test of getResult method, of class ConstructSqlTable.
     */
    @Test
    public void testGetResult() throws SQLException
      {
        System.out.println("getResult");
        ConstructSqlTable instance = new ConstructSqlTable();
        String expResult = "";
        String result = instance.getResult();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
      }

    /**
     * Test of run method, of class ConstructSqlTable.
     */
    @Test
    public void testRun() throws SQLException
      {
        ConstructSqlTable test = new ConstructSqlTable();
        new Thread(test).start();
      }

  }
