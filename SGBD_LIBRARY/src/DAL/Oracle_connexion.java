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
 * Classe permettant de retourner une instance unique de connexion pour une base de données oracle
 * Pour récupérer les identifiants de connexion, la classe va chercher un fichier .properties qui s'appelle
 * connexion_oracle.properties. Le fichier doit absolument porter le même nom.
 * @author JOHN
 */
public class Oracle_connexion
  {
    private Oracle_connexion(){}
    /*
     *Identifients de connexion pour bdd Oracle stockés dans un fichier .properties
     */
    private static final Properties properties = new Properties();
    /**
     * Singleton de connexion.
     */
    private static Connection connection = null;

    /**
     * getInstance retourne une instance unique de connexion
     * Le fichier .properties doit contenir le propriétées dba_access,dba_user et dba_password obligatoirement 
     * pour avoir les identifients de connexion.
     * @return une unique instance de l'objet Connexion synchronisée
     */
    public static Connection getInstance()
      {

        if (connection == null)
          {
            synchronized (Oracle_connexion.class)
              {
                try
                  {
                    properties.load(Oracle_connexion.class.getClassLoader().getResourceAsStream("connexion_oracle.properties"));
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
