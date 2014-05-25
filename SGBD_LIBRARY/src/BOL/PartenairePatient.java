/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BOL;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author Kevin
 */
public class PartenairePatient extends Partenaire{
    private String numeroSecu;
    private String numeroMutuelle;

    public PartenairePatient(int id, String nom, String prenom, Date dateNaissance, String telephone, String mail,String numeroSecu,String numeroMutuelle)
      {
        super(id, nom, prenom, dateNaissance, telephone, mail);
        this.numeroMutuelle = numeroMutuelle;
        this.numeroSecu = numeroSecu;
      }
    /**
     * @return the numeroSecu
     */
    public String getNumeroSecu() {
        return numeroSecu;
        
    }

    /**
     * @param numeroSecu the numeroSecu to set
     */
    public void setNumeroSecu(String numeroSecu) {
        this.numeroSecu = numeroSecu;
    }

    /**
     * @return the numeroMutuelle
     */
    public String getNumeroMutuelle() {
        return numeroMutuelle;
    }

    /**
     * @param numeroMutuelle the numeroMutuelle to set
     */
    public void setNumeroMutuelle(String numeroMutuelle) {
        this.numeroMutuelle = numeroMutuelle;
    }
}
