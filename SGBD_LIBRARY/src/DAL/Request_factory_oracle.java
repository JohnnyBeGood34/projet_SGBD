/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.util.ArrayList;

/**
 *
 * @author JOHNNY Fabrique de requetes pour le DAO_Manager
 */
public class Request_factory_oracle implements IBDD
  {
    /*
     *Contient la requete sous forme de string
     */
    private String _requete;

    public String getRequeteString()
      {
        return this._requete;
      }
    /*
     * Construction de requete pour lister 1 ou plusiseurs enregistrements
     * Parametre d'entrée, un nom de classe, un arrayList de champs avec leurs clauses respectives
     */

    @Override
    public void requeteLister(String classe, ArrayList<String> fields, ArrayList<String> value)
      {
        String sql;
        String table = classe; //Nom de la table, qui correspond au nom de la classe et inversement.
        if (value == null && fields == null)//fields contient les champs sur lesquels va se faire la restriction where
          {
            sql = "SELECT * FROM " + table;
          } else
          {
            String whereClause = " WHERE ";
            for (int i = 0; i < fields.size(); i++) //Pour chaque entrée de fields
              {
                //Tant que l'on est pas au dernier tour de boucle
                if (i != fields.size() - 1)
                  {
                    whereClause += fields.get(i) + " = '" + value.get(i) + "' AND ";
                  } else
                  {
                    whereClause = whereClause + fields.get(i) + " = '" + value.get(i) + "';";
                  }
              }
            sql = "SELECT * FROM " + table + whereClause;
          }
        this._requete = sql;
      }

    @Override
    public void requeteAjouter(Object objet)
      {
        //Récupération du nom de la classe de l'objet recut
        String classe = objet.getClass().getName();
        
      }

    @Override
    public void requeteMiseAJour(Object objet)
      {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      }

    @Override
    public void requeteSupprimer(Object objet)
      {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      }
  }