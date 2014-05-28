/*
 * Projet EPSI Montpellier 2014.
 * Jonathan Affre, Stephane Dupre, Kevin Salles, Cyrille Chanssang.
 */
package DAL;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author JOHN Request_factory_oracle implémente l'interface BDD Cette classe
 * est une fabrique de requetes pour la base de données Oracle. Pour la
 * construction dynamique de requetes, la classe se base sur le principe que les
 * classes ont le même nom que la table en base de données, et que les attributs
 * ont le même nom que les champs de la table en base de données.
 */
public class Request_factory_oracle implements IBDD
  {
    /*
     *Contient la requete sous forme de string
     */

    private String _requete;
    /*
     * Contient les parametres pour les requetes préparées
     */
    private ArrayList<String> _parametres = new ArrayList();

    /**
     * @return _requete, qui contient la requete que l'on vient de construire
     */
    @Override
    public String getRequeteString()
      {
        return this._requete;
      }

    /**
     * ArrayList<String>, contenant les paramètre permettant d'exécuter les
     * requetes préparées.
     *
     * @return ArrayList<String>
     */
    @Override
    public ArrayList<String> getParametres()
      {
        return this._parametres;
      }

    /**
     * requeteLister sert à construire une requete de selection dynamique avec
     *
     * @param classe type String, qui est le nom de la classe (table) que l'on
     * veut attaquer en base.
     * @param fields type ArrayList<String>, peut être null, représente les
     * champs de restriction
     * @param restriction
     * @param value type ArrayList<String>, peut être null, représente les
     * valeurs de restriction correspondant aux champs
     */
    @Override
    public void requeteLister(String classe, ArrayList<String> fields, ArrayList<String> restriction, ArrayList<String> value)
      {
        String sql;
        String table = classe; //Nom de la table, qui correspond au nom de la classe et inversement.
        if (value == null && fields == null && restriction == null)//fields contient les champs sur lesquels va se faire la restriction where
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
                    whereClause += fields.get(i) + " " + restriction.get(i) + " '" + value.get(i) + "' AND ";
                  } else
                  {
                    whereClause = whereClause + fields.get(i) + " " + restriction.get(i) + " '" + value.get(i) + "'";
                  }
              }
            sql = "SELECT * FROM " + table + whereClause;
          }
        this._requete = sql;
      }

    /**
     * @param objet, est un objet métier. La méthode requeteAjouter permet de
     * construire une requete d'insertion (préparées) en fonction de l'objet
     * reçut. Peu importe l'objet reçut, la methode créé la bonne requete. Il
     * faut que la classe de l'objet respecte les règles de construction pour la
     * factory.
     */
    @Override
    public void requeteAjouter(Object objet)
      {
        Method methode;
        //Récupération du nom de la classe de l'objet recut
        String classe = objet.getClass().getSimpleName();
        String table = classe;
        //Base de la requetes insert
        String sql = "INSERT INTO " + table;
        String champs = "(";
        String values = "VALUES(";
        Class nomClasse = objet.getClass();
        //Array de nom d'attributs de la classe de l'objet recut
        ArrayList<Field[]> fields = new ArrayList();
        //Si l'objet recut n'hérite d'aucunes classes on récupère ses attributs, sinon onrécupère ses attributs
        //Et ceux de sa classe mère.
        if (!objet.getClass().getSuperclass().getSimpleName().equals("Object"))
          {
            //On récupère les attributs de la superClasse ainsi que ceux de la classe de l'objet
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
              } else
              {
                champs = champs + field;
                values = values + "?";
              }
            String nomMethode = "get_" + field;
            try
              {
                //On récupère la methode de l'objet
                methode = nomClasse.getMethod(nomMethode);
                try
                  {
                    //methode.invoke permet d'appeller une methode construite en string
                    this._parametres.add(String.valueOf(methode.invoke(objet)));
                  } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                  {
                    Logger.getLogger(Request_factory_oracle.class.getName()).log(Level.SEVERE, null, ex);
                  }
              } 
            catch (NoSuchMethodException | SecurityException ex)
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

    /**
     * @param objet est un objet métier. La methode requeteMiseAJour, permet de
     * construire une requete d'update (préparées) en fonction de l'objet reçut.
     */
    @Override
    public void requeteMiseAJour(Object objet)
      {
        Method methode;
        //Récupération du nom de la classe de l'objet recut
        String classe = objet.getClass().getSimpleName();
        String table = classe;
        //Base pour la requete d'update
        String where = " WHERE ";//Sous chaine clause where
        String sql = "UPDATE " + table + " SET ";
        Class nomClasse = objet.getClass();
        //Array de nom d'attributs de la classe de l'objet recut
        ArrayList<Field[]> fields = new ArrayList();
        //Si l'objet recut n'hérite d'aucunes classes on récupère ses attributs, sinon onrécupère ses attributs
        //Et ceux de sa classe mère.
        if (!objet.getClass().getSuperclass().getSimpleName().equals("Object"))
          {
            //On récupère les attributs de la superClasse ainsi que ceux de la classe de l'objet
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
            String nomMethode = "get_" + field;
            if (!field.subSequence(0, 2).equals("id")) //On écarte l'id PAR CONTRE A REVOIR
              {
                //Tant qu'on est pas au dernier tour de boucle
                if (compteur != fieldsString.size() - 1)
                  {
                    sql = sql + field + "=?,";
                  } else //Quand on arrive au dernier tour de boucle on enleve la virgule apres le ?
                  {
                    sql = sql + field + "=?";
                  }
              } else
              {
                where = where + field + "=?";
              }

            try
              {
                methode = nomClasse.getMethod(nomMethode);
                this._parametres.add(String.valueOf(methode.invoke(objet)));
              } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
              {
                Logger.getLogger(Request_factory_oracle.class.getName()).log(Level.SEVERE, null, ex);
              }
            compteur++;
          }
        sql = sql + where;
        this._requete = sql;
      }

    /**
     * requeteSupprimer sert à construire une requete de suppression dynamique
     * avec
     *
     * @param classe type String, qui est le nom de la classe (table) que l'on
     * veut attaquer en base.
     * @param fields type ArrayList<String>, peut être null, représente les
     * champs de restriction
     * @param value type ArrayList<String>, peut être null, représente les
     * valeurs de restriction correspondant aux champs
     */
    @Override
    public void requeteSupprimer(String classe, ArrayList<String> fields, ArrayList<String> restriction, ArrayList<String> value)
      {
        String table = classe;
        String sql;
        if (value == null && fields == null)
          {
            sql = "DELETE FROM " + table;
          } else
          {
            String whereClause = " WHERE ";
            for (int i = 0; i < fields.size(); i++) //Pour chaque entrée de fields
              {
                //Tant que l'on est pas au dernier tour de boucle
                if (i != fields.size() - 1)
                  {
                    whereClause += fields.get(i) + " " + restriction.get(i) + " '" + value.get(i) + "' AND ";
                  } else
                  {
                    whereClause = whereClause + fields.get(i) + " " + restriction.get(i) + " '" + value.get(i) + "'";
                  }
              }
            sql = "DELETE FROM " + table + whereClause;
          }
        this._requete = sql;
      }

    /**
     * @param fields, est un ArrayList de tableau de champs (Fields).
     * @return ArrayList<Srting>, contenant les champs sous forme de Strings.
     */
    private ArrayList<String> getFieldstoString(ArrayList<Field[]> fields)
      {
        ArrayList<String> array = new ArrayList();
        for (Field[] arrayFields : fields)
          {
            for (Field field : arrayFields)
              {
                array.add(field.getName());
              }
          }
        return array;
      }
  }
