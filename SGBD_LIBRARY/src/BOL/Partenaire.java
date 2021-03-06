/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BOL;

/**
 *
 * @author Kevin
 */
public abstract class Partenaire {
    private int idPartenaire;
    private String nom;
    private String prenom;
    private String dateDeNaissance;
    private String telephonePartenaire;
    private String mail;
    
    public Partenaire(int idPartenaire,String nom, String prenom,String dateDeNaissance,String telephonePartenaire,String mail)
      {
        this.idPartenaire = idPartenaire;
        this.nom = nom;
        this.prenom = prenom;
        this.dateDeNaissance = dateDeNaissance;
        this.telephonePartenaire = telephonePartenaire;
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
     * @return the telephonePartenaire
     */
    public String get_telephonePartenaire() {
        return telephonePartenaire;
    }

    /**
     * @param telephonePartenaire the telephonePartenaire to set
     */
    public void setTelephonePartenaire(String telephonePartenaire) {
        this.telephonePartenaire = telephonePartenaire;
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
