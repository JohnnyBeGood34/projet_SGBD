/*
 * Projet EPSI Montpellier 2014.
 * Jonathan Affre, Stephane Dupre, Kevin Salles, Cyrille Chanssang.
 */
package DAL;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author JOHN Request_factory_oracle implémente l'interface BDD Cette classe
 * est une fabrique de requetes pour la base de données Oracle. Pour la
 * construction dynamique de requetes, la classe se base sur le principe que les
 * classes ont le même nom que la table en base de données, et que les attributs
 * ont le même nom que les champs de la table en base de données.
 * Elle facilite l'appel de procédures stockées par déduction de leurs noms (verbe + classe) ex : supprimerClient
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
     * ArrayList contenant les paramètre permettant d'exécuter les
     * requetes préparées.
     * @return ArrayList de parametres pour la requete préparée
     */
    @Override
    public ArrayList<String> getParametres()
      {
        return this._parametres;
      }

    /**
     * Fonction permettant de creer le fichier .sql du dump de la base de données
     * @param chemin, chemin vers lequel on veut générer le fichier .sql
     * @throws SQLException exception en cas de fail de requete SQL
     * @throws IOException exception en cas de fail entrée/sorties
     */
    @Override
    public void dumpDb(String chemin) throws SQLException, IOException
      {
        DumpOracleDB dumpClass = new DumpOracleDB();
        
            String dump = dumpClass.dumpDb();
            dumpClass.writeDumpFile(dump, chemin);
                                  

      }
    /**
     * Fonction permettant d'obtenir le dump de la base de données sous forme de String
     * @return la requete de dump de la base de données sous forme de string
     */
    @Override
    public String getDumpDb()
      {
        String dump = "";
        DumpOracleDB dumpClass = new DumpOracleDB();
        try
          {
            dump = dumpClass.dumpDb();
          } catch (SQLException | IOException ex)
          {
            Logger.getLogger(Request_factory_oracle.class.getName()).log(Level.SEVERE, null, ex);
          }
        return dump;
      }

    /**
     ***********************************************
     ******************REQUETES*********************
     ***********************************************/
    
    /**
     * requeteLister sert à construire une requete de selection dynamique avec
     * @param classe type String, qui est le nom de la classe (table) que l'on
     * veut attaquer en base.
     * @param fields type ArrayList, peut être null, représente les
     * champs de restriction
     * @param restriction, les restriction à appliquer dans les clauses where
     * @param value type ArrayList, peut être null, représente les
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
                    String resultMethode;
                    //methode.invoke permet d'appeller une methode construite en string
                    //Remplace les . par des virgules si il y en a, permettant à la base de données oracle d'interpreter correctement les float
                    //Si l'attribut est de type Float, on enleve le point on le remplace par une virgule
                    if (methode.invoke(objet) instanceof Float)
                      {
                        resultMethode = String.valueOf(methode.invoke(objet)).replaceAll("\\.", ",");
                      } else
                      {
                        resultMethode = String.valueOf(methode.invoke(objet));
                      }

                    this._parametres.add(resultMethode);
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
            if (!field.equals(fieldsString.get(0)))
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
                String resultMethode;
                if (methode.invoke(objet) instanceof Float)
                  {
                    resultMethode = String.valueOf(methode.invoke(objet)).replaceAll("\\.", ",");
                  } else
                  {
                    resultMethode = String.valueOf(methode.invoke(objet));
                  }
                this._parametres.add(resultMethode);
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
     * @param classe type String, qui est le nom de la classe (table) que l'on
     * veut attaquer en base.
     * @param fields type ArrayList peut être null, représente les
     * champs de restriction
     * @param restriction arrayList de d'opérateurs de restrictions pour les
     * resutes
     * @param value type ArrayList peut être null, représente les
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
     * *********************************************
     *************PROCEDURES STOQUEES***************
        **********************************************
     */
    /**
     * Permet de retourner une procédure stockée pour lister Cela implique que la
     * procédure pour lister doit être sous la forme "listerNomclasse"
     * @param classe nom de la classe = nom de la tale en bdd
     * @param values les valeurs de restrictions
     */
    @Override
    public void procedureLister(String classe, ArrayList<String> values)
      {
        String nomProcedure;
        //Détermination du nom de la procédure à appeler
        nomProcedure = "{CALL lister" + classe + " (?)}";
        //On stoque le nom de la procédure dans la requete
        this._requete = nomProcedure;
        //les parametres de restriction dans les paramètres
        this._parametres = values;
      }

    /**
     * Methode permettant de construire l'appel de la procédure stockée
     * pour modifier (update)
     *
     * @param objet, un objet du BOL
     */
    @Override
    public void procedureModifier(Object objet)
      {
        Method methode;
        //Exemple de procédure ajouterMaterielmedical => ajouter + nom de la classe
        //Récupération du com de la classe
        String classe = objet.getClass().getSimpleName();

        String nomProcedure = "{CALL modifier" + classe + "(";

        //ArrayList contiendra la liste des attributs de la table (Classe)
        ArrayList<Field[]> fields = new ArrayList();
        Class nomClasse = objet.getClass();
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

        for (String attribut : fieldsString)
          {
            //Tant qu'on est pas au dernier tour de boucle
            if (compteur != fieldsString.size() - 1)
              {
                nomProcedure += "?,";
              } else
              {
                nomProcedure += "?)}";
              }
            String nomMethode = "get_" + attribut;
            try
              {
                //On récupère la methode de l'objet
                methode = nomClasse.getMethod(nomMethode);
                try
                  {
                    String resultMethode;
                    //methode.invoke permet d'appeller une methode construite en string
                    //Remplace les . par des virgules si il y en a, permettant à la base de données oracle d'interpreter correctement les float
                    //Si l'attribut est de type Float, on enleve le point on le remplace par une virgule
                    if (methode.invoke(objet) instanceof Float)
                      {
                        resultMethode = String.valueOf(methode.invoke(objet)).replaceAll("\\.", ",");
                      } else
                      {
                        resultMethode = String.valueOf(methode.invoke(objet));
                      }

                    this._parametres.add(resultMethode);
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
        //La requete prends la procédure
        this._requete = nomProcedure;
      }

    /**
     * Focntion permettant de construire l'appel de la procédure pour supprimer
     *
     * @param classe Nom de la classe = nom de la table en bdd
     * @param values Valeurs de restrictions
     */
    @Override
    public void procedureSupprimer(String classe, ArrayList<String> values)
      {
        String nomProcedure = "CALL {supprimer" + classe + " (?)}";
        //On stoque le nom de la procédure dans la requete
        this._requete = nomProcedure;
        //les parametres de restriction dans les paramètres
        this._parametres = values;
      }

    /**
     * Fonction permettant de retourner un appel de procédure pour insérer
     *
     * @param objet un objet à insérer en procédure stockée
     */
    @Override
    public void procedureAjouter(Object objet)
      {
        Method methode;
        //Exemple de procédure ajouterMaterielmedical => ajouter + nom de la classe
        //Récupération du com de la classe
        String classe = objet.getClass().getSimpleName();

        String nomProcedure = "{CALL ajouter" + classe + "(";

        //ArrayList contiendra la liste des attributs de la table (Classe)
        ArrayList<Field[]> fields = new ArrayList();
        Class nomClasse = objet.getClass();
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

        for (String attribut : fieldsString)
          {
            //Tant qu'on est pas au dernier tour de boucle
            if (compteur != fieldsString.size() - 1)
              {
                nomProcedure += "?,";
              } else
              {
                nomProcedure += "?)}";
              }
            String nomMethode = "get_" + attribut;
            try
              {
                //On récupère la methode de l'objet
                methode = nomClasse.getMethod(nomMethode);
                try
                  {
                    String resultMethode;
                    //methode.invoke permet d'appeller une methode construite en string
                    //Remplace les . par des virgules si il y en a, permettant à la base de données oracle d'interpreter correctement les float
                    //Si l'attribut est de type Float, on enleve le point on le remplace par une virgule
                    if (methode.invoke(objet) instanceof Float)
                      {
                        resultMethode = String.valueOf(methode.invoke(objet)).replaceAll("\\.", ",");
                      } else
                      {
                        resultMethode = String.valueOf(methode.invoke(objet));
                      }

                    this._parametres.add(resultMethode);
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
        //La requete prends la procédure
        this._requete = nomProcedure;
      }

    /**
     * @param fields, est un ArrayList de tableau de champs (Fields).
     * @return ArrayList contenant les champs sous forme de Strings.
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
