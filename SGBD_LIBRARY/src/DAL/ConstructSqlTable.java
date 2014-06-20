/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JOHN
 */
public class ConstructSqlTable extends Thread
  {


     Connection connexion = Oracle_connexion.getInstance();
    private volatile String result;

    final Statement statement;

    public ConstructSqlTable() throws SQLException
      {
        this.statement = connexion.createStatement();
      }

    public String getResult()
      {
        return result;
      }


    @Override
    public  void run()
      {
        try
          {
            System.out.println("ENTREE");
            Statement statement = connexion.createStatement();
            System.out.println("APRES CONNEXION");
            ArrayList<String> listeTables = new ArrayList<>();
            /* Requête permettant de récupérer tous les noms de table */
            /* Requête permettant de récupérer tous les noms de table */
            ResultSet resultSet = statement.executeQuery("SELECT table_name FROM user_tables");

            while (resultSet.next())
              {
                String nomTable = resultSet.getString("TABLE_NAME");
                listeTables.add(nomTable);
              }
            statement.close();
            resultSet.close();
            System.out.println("LISTE TABLES**************"+listeTables);
          } catch (SQLException ex)
          {
            Logger.getLogger(ConstructSqlTable.class.getName()).log(Level.SEVERE, null, ex);
          }
      }

  }
