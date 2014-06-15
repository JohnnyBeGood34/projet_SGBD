/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JOHN
 */
public class ConstructSqlTable implements Runnable
  {

    final Connection connexion = Oracle_connexion.getInstance();
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
    public void run()
      {
        System.out.println("RUN RUN RUN");
        try
          {
            System.out.println("TRY TRY TRY MOTHER FUCKER STP MARCHE");
            ArrayList<String> listeTables = new ArrayList<>();
            /* Requête permettant de récupérer tous les noms de table */
            try (ResultSet resultSet = statement.executeQuery("SELECT table_name FROM user_tables"))
              {
                System.out.println(resultSet.toString());
                while (resultSet.next())
                  {
                    String nomTable = resultSet.getString("TABLE_NAME");
                    listeTables.add(nomTable);
                  }
              }
            System.out.println(listeTables);
            
          } catch (SQLException ex)
          {
            Logger.getLogger(ConstructSqlTable.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
  }
