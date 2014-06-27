/*
 * Projet EPSI Montpellier 2014.
 * Jonathan Affre, Stephane Dupre, Kevin Salles, Cyrille Chanssang.
 */
package DAL;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe de test pour le dump de la base de données oracle
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
     * Test of dumpDb method, of class DumpOracleDB.
     */
    @Test
    public void testDumpDb() throws Exception
      {
        System.out.println("TEST SUR LA METHODE ---dumpDb---");
        DumpOracleDB instance = new DumpOracleDB();
        String result = instance.dumpDb();
        System.out.println(result);
      }

    /**
     * Test of writeDumpFile method, of class DumpOracleDB.
     */
    @Test
    public void testWriteDumpFile() throws Exception
      {
        System.out.println("TEST SUR LA METHODE ---writeDumpFile---");
        DumpOracleDB instance = new DumpOracleDB();
        String contenu = instance.dumpDb();
        /*String chemin = "a:/dev/";
        // test pour voir si le fichier de dump est bien créé et déplacé dans le bon dossier
        instance.writeDumpFile(contenu, chemin);*/
      }

    /**
     * Test of listerTables method, of class DumpOracleDB.
     */
    @Test
    public void testListerTables() throws Exception
      {
        System.out.println("TEST SUR LA METHODE ---listerTables---");
        DumpOracleDB instance = new DumpOracleDB();
        ArrayList<String> result = instance.listerTables();
        //Liste des tables de la base de données
        System.out.println(result);
      }

    /**
     * Test of listerVues method, of class DumpOracleDB.
     */
    @Test
    public void testListerVues() throws Exception
      {
        System.out.println("TEST SUR LA METHODE ---listerVues---");
        DumpOracleDB instance = new DumpOracleDB();
        ArrayList<String> result = instance.listerVues();
        System.out.println(result);
      }

    /**
     * Test of listerTriggers method, of class DumpOracleDB.
     */
    @Test
    public void testListerTriggers() throws Exception
      {
        System.out.println("TEST SUR LA METHODE ---listerTriggers---");
        DumpOracleDB instance = new DumpOracleDB();
        ArrayList<String> result = instance.listerTriggers();
        System.out.println(result);
      }

    /**
     * Test of listerSequences method, of class DumpOracleDB.
     */
    @Test
    public void testListerSequences() throws Exception
      {
        System.out.println("TEST SUR LA METHODE ---listerSequences---");
        DumpOracleDB instance = new DumpOracleDB();
        ArrayList<String> result = instance.listerSequences();
        System.out.println(result);
      }

    /**
     * Test of listerProcedures method, of class DumpOracleDB.
     */
    @Test
    public void testListerProcedures() throws Exception
      {
        System.out.println("TEST SUR LA METHODE ---listerProcedures---");
        DumpOracleDB instance = new DumpOracleDB();
        ArrayList<String> result = instance.listerProcedures();
        System.out.println(result);
      }

  }
