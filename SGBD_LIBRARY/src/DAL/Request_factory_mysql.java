/*
 * Projet EPSI Montpellier 2014.
 * Jonathan Affre, Stephane Dupre, Kevin Salles, Cyrille Chanssang.
 */

package DAL;

import java.util.ArrayList;

/**
 *
 * @author JOHN
 */
public class Request_factory_mysql implements IBDD
  {

    @Override
    public void requeteLister(String classe, ArrayList<String> fields, ArrayList<String> restriction, ArrayList<String> value)
      {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      }

    @Override
    public void requeteAjouter(Object objet)
      {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      }

    @Override
    public void requeteMiseAJour(Object objet)
      {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      }

    @Override
    public void requeteSupprimer(String classe, ArrayList<String> fields, ArrayList<String> restriction, ArrayList<String> value)
      {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      }

    @Override
    public String getRequeteString()
      {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      }

    @Override
    public ArrayList<String> getParametres()
      {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      }

    @Override
    public void dumpDb(String chemin)
      {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      }

    @Override
    public String getDumpDb()
      {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      }

    @Override
    public void procedureLister(String classe, ArrayList<String> values)
      {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      }

    @Override
    public void procedureAjouter(Object objet)
      {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      }

    @Override
    public void procedureModifier(Object objet)
      {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      }

    @Override
    public void procedureSupprimer(String classe, ArrayList<String> values)
      {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      }
    
  }
