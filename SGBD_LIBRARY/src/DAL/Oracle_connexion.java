/*
 * Projet EPSI Montpellier 2014.
 * Jonathan Affre, Stephane Dupre, Kevin Salles, Cyrille Chanssang.
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
    /**
     * Singleton de connexion.
     */
    private static Connection connection;
    /**
     * 
     * @return une unique instance de l'objet Connexion.
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
