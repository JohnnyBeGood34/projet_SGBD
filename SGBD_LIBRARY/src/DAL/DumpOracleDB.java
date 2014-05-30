/*
 * Projet EPSI Montpellier 2014.
 * Jonathan Affre, Stephane Dupre, Kevin Salles, Cyrille Chanssang.
 */
package DAL;

import java.sql.Connection;

/**
 * Classe permettant de faire un export de la base de données oracle
 * @author JOHN
 */
public class DumpOracleDB
  {
    public String dumpOracleDB()
      {
        //Get instance du singleton de connexion
        Connection connexion = Oracle_connexion.getInstance();
        //Requete renvoyant le nom des tables de la base de données
        //select dbms_metadata.get_ddl('TABLE','MATERIELMEDICAL')from dual
        String requeteTables = "SELECT table_name FROM user_tables;";
        
        return "yoyo les couilles a Jeano";
      }
  }
