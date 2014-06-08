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
public class PartenairePatient extends Partenaire{
    private String numeroSecu;
    private String numeroMutuelle;

    public PartenairePatient(int idPartenaire, String nom, String prenom, String dateDeNaissance, String telephone, String mail,String numeroSecu,String numeroMutuelle)
      {
        super(idPartenaire, nom, prenom, dateDeNaissance, telephone, mail);
        this.numeroMutuelle = numeroMutuelle;
        this.numeroSecu = numeroSecu;
      }
    /**
     * @return the numeroSecu
     */
    public String get_numeroSecu() {
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
    public String get_numeroMutuelle() {
        return numeroMutuelle;
    }

    /**
     * @param numeroMutuelle the numeroMutuelle to set
     */
    public void setNumeroMutuelle(String numeroMutuelle) {
        this.numeroMutuelle = numeroMutuelle;
    }
}
