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
public class MaterielMedicalMaterielLoue extends MaterielMedical{
    private String etat;
    private int quantite;

    public MaterielMedicalMaterielLoue(int idMateriel, int idTVA, int idTypeMaterielMedical, String libelleMaterielMedical, String marque, Float prixHT, String numeroSerie, String dimensions, String poid, String uniteVente, String description,String etat,int quantite)
      {
        super(idMateriel, idTVA, idTypeMaterielMedical, libelleMaterielMedical, marque, prixHT, numeroSerie, dimensions, poid, uniteVente, description);
        this.etat = etat;
        this.quantite = quantite;
      }

    /**
     * @return the etat
     */
    public String get_etat() {
        return etat;
    }

    /**
     * @param etat the etat to set
     */
    public void setEtat(String etat) {
        this.etat = etat;
    }

    /**
     * @return the quantite
     */
    public int get_quantite() {
        return quantite;
    }

    /**
     * @param quantite the QteMaterielLoue to set
     */
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
