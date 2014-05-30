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
public class MaterielMedicalMaterielAchat extends MaterielMedical{
    private int quantite;

    public MaterielMedicalMaterielAchat(int IDMateriel, int idTVA, int idTypeMaterielMedical,  String libelleMaterielMedical, String marque, Float prixHT, String numeroSerie, String dimensions, String poid, String uniteVente, String description,int quantite)
      {
        super(IDMateriel, idTVA, idTypeMaterielMedical, libelleMaterielMedical, marque, prixHT, numeroSerie, dimensions, poid, uniteVente, description);
        this.quantite = quantite;
      }

    /**
     * @return the quantite
     */
    public int get_quantite() {
        return quantite;
    }

    /**
     * @param quantite the quantite to set
     */
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
