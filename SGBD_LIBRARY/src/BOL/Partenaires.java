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
public abstract class Partenaires {
    private int idPartenaire;
    private String nom;
    private String Prenom;
    private Date dateDeNaissance;
    private String telephonePartenaires;
    private String mail;

    /**
     * @return the idPartenaire
     */
    public int getIdPartenaire() {
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
    public String getNom() {
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
    public String getPrenom() {
        return Prenom;
    }

    /**
     * @param Prenom the Prenom to set
     */
    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    /**
     * @return the dateDeNaissance
     */
    public Date getDateDeNaissance() {
        return dateDeNaissance;
    }

    /**
     * @param dateDeNaissance the dateDeNaissance to set
     */
    public void setDateDeNaissance(Date dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    /**
     * @return the telephonePartenaires
     */
    public String getTelephonePartenaires() {
        return telephonePartenaires;
    }

    /**
     * @param telephonePartenaires the telephonePartenaires to set
     */
    public void setTelephonePartenaires(String telephonePartenaires) {
        this.telephonePartenaires = telephonePartenaires;
    }

    /**
     * @return the mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * @param mail the mail to set
     */
    public void setMail(String mail) {
        this.mail = mail;
    }
    
}
