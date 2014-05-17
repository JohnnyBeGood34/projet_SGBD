/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JOHN
 */
public class Oracle_connexion
  {
    
    /*
     *Identifients de connexion pour bdd Oracle
     */

    private final static String _BDDACCESS = "jdbc:oracle:thin:@svroracle.montpellier.epsi.fr:1521";
    private final static String _BDDUSER = "BD_MM";
    private final static String _BDDPASSWORD = "mmACDS2014";
    private static Connection connection;
    /*
     *Retourne une unique instance de connexion
     */
    public static Connection getInstance()
      {

        if (connection == null)
          {
            try
              {
                connection = DriverManager.getConnection(_BDDACCESS, _BDDUSER, _BDDPASSWORD);
              } catch (SQLException ex)
              {
                Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
              }
          }

        return connection;
      }
  }
