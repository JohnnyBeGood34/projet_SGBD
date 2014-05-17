/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import java.sql.Connection;

/**
 *
 * @author JOHN
 */
public class Manager_DAO
  {
    private IBDD requestFactory;
    public void Manager_DAO()
      {
        //Initilisation de la factory en oracle
        this.requestFactory = new Request_factory_oracle();
      }
  }
