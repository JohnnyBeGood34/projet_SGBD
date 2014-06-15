/*
 * Projet EPSI Montpellier 2014.
 * Jonathan Affre, Stephane Dupre, Kevin Salles, Cyrille Chanssang.
 */
package DAL;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JOHN
 */
public class Oracle_connexion
  {
    private Oracle_connexion(){}
    /*
     *Identifients de connexion pour bdd Oracle
     */
    private static final Properties properties = new Properties();
    /**
     * Singleton de connexion.
     */
    private static Connection connection = null;

    /**
     *
     * @return une unique instance de l'objet Connexion.
     */
    public static Connection getInstance()
      {

        if (connection == null)
          {
            synchronized (Oracle_connexion.class)
              {
                try
                  {
                    properties.load(Oracle_connexion.class.getClassLoader().getResourceAsStream("connexion.properties"));
                    connection = DriverManager.getConnection(properties.getProperty("dba_access"), properties.getProperty("dba_user"), properties.getProperty("dba_password"));
                  } catch (SQLException ex)
                  {
                    Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
                  } catch (IOException ex)
                  {
                    Logger.getLogger(Oracle_connexion.class.getName()).log(Level.SEVERE, null, ex);
                  }
              }
          }

        return connection;
      }
  }
