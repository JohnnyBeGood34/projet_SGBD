/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private ArrayList<String> _parametres = new ArrayList();

    public String getRequeteString()
      {
        return this._requete;
      }

    public ArrayList<String> getParametres()
      {
        return this._parametres;
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
    /*
     * Construit une requete d'insertion préparée
     * prete à l'emploi pour ojdbc
     */

    @Override
    public void requeteAjouter(Object objet)
      {
        Method methode;
        //Récupération du nom de la classe de l'objet recut
        String classe = objet.getClass().getSimpleName();
        String table = classe;
        //Base de la requetes insert
        String sql = "INSERT INTO " + classe;
        String champs = "(";
        String values = "VALUES(";
        Class nomClasse = objet.getClass();
        //Array de nom d'attributs de la classe de l'objet recut
        ArrayList<Field[]> fields = new ArrayList();
        //Si l'objet recut n'hérite d'aucunes classes on récupère ses attributs, sinon onrécupère ses attributs
        //Et ceux de sa classe mère.
        if (!objet.getClass().getSuperclass().getSimpleName().equals("Object"))
          {
            fields.add(objet.getClass().getSuperclass().getDeclaredFields());
            fields.add(objet.getClass().getDeclaredFields());
          } else
          {
            fields.add(objet.getClass().getDeclaredFields());
          }
        //Construction d'un arrayList d'attributs sous forme de string
        //Pour les utiliser dans la construction de la requete
        ArrayList<String> fieldsString = getFieldstoString(fields);
        int compteur = 0;
        for (String field : fieldsString)
          {
            if (compteur != fieldsString.size() - 1)
              {
                champs = champs + field + ",";
                values = values + "?,";
              } 
              else
              {
                champs = champs + field;
                values = values + "?";
              }
            String nomMethode = "get_" + field;
            System.out.println("NOM METHODE ----------------"+nomMethode);
            try
              {
                methode = nomClasse.getMethod(nomMethode);
                try
                  {
                    this._parametres.add(String.valueOf(methode.invoke(objet)));
                  } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                  {
                    Logger.getLogger(Request_factory_oracle.class.getName()).log(Level.SEVERE, null, ex);
                  }
              } catch (NoSuchMethodException | SecurityException ex)
              {
                Logger.getLogger(Request_factory_oracle.class.getName()).log(Level.SEVERE, null, ex);
              }
            compteur++;
          }
        values = values + ")"; //Fermer les values
        champs = champs + ")"; // fermer la liste de champs
        sql = sql + champs + values; // construction de la requete complete
        this._requete = sql;
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

    private ArrayList<String> getFieldstoString(ArrayList<Field[]> fields)
      {
        ArrayList<String> array = new ArrayList();
        for (Field[] arrayFields : fields)
          {
            for(Field field : arrayFields)
              {
                array.add(field.getName());
              }
          }
        return array;
      }
  }
