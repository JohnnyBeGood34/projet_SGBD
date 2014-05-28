/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BOL;

import java.util.Date;

/**
 *
 * @author Kevin
 */
public abstract class Partenaire {
    private int idPartenaire;
    private String nom;
    private String prenom;
    private String dateDeNaissance;
    private String telephone;
    private String mail;
    
    public Partenaire(int id,String nom, String prenom,String dateNaissance,String telephone,String mail)
      {
        this.idPartenaire = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateDeNaissance = dateNaissance;
        this.telephone = telephone;
        this.mail = mail;
      }
    /**
     * @return the idPartenaire
     */
    public int get_idPartenaire() {
        return idPartenaire;
    }

    /**
     * @param idPartenaire the idPartenaire to set
     */
    public void setIdPartenaire(int idPartenaire) {
        this.idPartenaire = idPartenaire;
    }

    /**
     * @return the nom
     */
    public String get_nom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the Prenom
     */
    public String get_prenom() {
        return prenom;
    }

    /**
     * @param Prenom the Prenom to set
     */
    public void setPrenom(String Prenom) {
        this.prenom = Prenom;
    }

    /**
     * @return the dateDeNaissance
     */
    public String get_dateDeNaissance() {
        return dateDeNaissance;
    }

    /**
     * @param dateDeNaissance the dateDeNaissance to set
     */
    public void setDateDeNaissance(String dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    /**
     * @return the telephonePartenaires
     */
    public String get_telephone() {
        return telephone;
    }

    /**
     * @param telephonePartenaires the telephonePartenaires to set
     */
    public void setTelephone(String telephonePartenaires) {
        this.telephone = telephonePartenaires;
    }

    /**
     * @return the mail
     */
    public String get_mail() {
        return mail;
    }

    /**
     * @param mail the mail to set
     */
    public void setMail(String mail) {
        this.mail = mail;
    }
    
}
