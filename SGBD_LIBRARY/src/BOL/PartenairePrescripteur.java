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
public class PartenairePrescripteur extends Partenaire{
    private String specialite;
    private String numeroAdeli;
    
    public PartenairePrescripteur(int idPartenaire,String nom, String prenom,String dateDeNaissance,String telephone,String mail,String specialite,String adeli)
      {
        super(idPartenaire, nom,prenom,dateDeNaissance,telephone,mail);
        this.specialite = specialite;
        this.numeroAdeli = adeli;
      }
    /**
     * @return the specialite
     */
    public String get_specialite() {
        return specialite;
    }

    /**
     * @param specialite the specialite to set
     */
    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    /**
     * @return the numeroAdeli
     */
    public String get_numeroAdeli() {
        return numeroAdeli;
    }

    /**
     * @param numeroAdeli the numeroAdeli to set
     */
    public void setNumeroAdeli(String numeroAdeli) {
        this.numeroAdeli = numeroAdeli;
    }
}
