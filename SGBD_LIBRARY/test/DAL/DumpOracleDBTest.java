/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class DumpOracleDBTest
  {
    
    public DumpOracleDBTest()
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
     * Test of dumpOracleDB method, of class DumpOracleDB.
     */
    @Test
    public void testDumpOracleDB() throws Exception
      {
        System.out.println("dumpOracleDB");
        DumpOracleDB instance = new DumpOracleDB();
        String expResult = "";
        //String result = instance.dumpOracleDB();
          //System.out.println(result);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
      }

    /**
     * Test of writeDumpFile method, of class DumpOracleDB.
     */
    @Test
    public void testWriteDumpFile() throws IOException {
        System.out.println("writeDumpFile");
        //String = "";
        DumpOracleDB instance = new DumpOracleDB();
        //String dump = instance.dumpDB();
       // instance.writeDumpFile(dump, "a:/dev/");
    }
    
  }
