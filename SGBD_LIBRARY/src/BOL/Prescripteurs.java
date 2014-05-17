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
public class Prescripteurs extends Partenaires{
    private String specialite;
    private String numeroAdeli;

    /**
     * @return the specialite
     */
    public String getSpecialite() {
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
    public String getNumeroAdeli() {
        return numeroAdeli;
    }

    /**
     * @param numeroAdeli the numeroAdeli to set
     */
    public void setNumeroAdeli(String numeroAdeli) {
        this.numeroAdeli = numeroAdeli;
    }
}
