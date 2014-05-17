/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JOHN
 */
public class Manager_DAO
  {
    private IBDD requestFactory;
    public Manager_DAO()
      {
        //Initilisation de la factory pour oracle
        this.requestFactory = new Request_factory_oracle();
      }
    public void setRequestFactory(IBDD requestFactory)
      {
        this.requestFactory = requestFactory;
      }
    public static ArrayList<String> lister(String classe,ArrayList<String> fields,ArrayList<String> values)
      {
        //Connexion Singleton
        Connection connexion = Oracle_connexion.getInstance();
        Request_factory_oracle fabrique = new Request_factory_oracle();
        fabrique.requeteLister(classe, fields, values);
        String requete = fabrique.getRequeteString();
        try
          {
            Statement statement = connexion.createStatement();
            connexion.prepareCall(requete);
          } catch (SQLException ex)
          {
            Logger.getLogger(Manager_DAO.class.getName()).log(Level.SEVERE, null, ex);
          }
        
        return new ArrayList<String>();
      }
  }
