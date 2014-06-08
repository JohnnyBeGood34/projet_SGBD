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

    public MaterielMedicalMaterielAchat(int idMateriel, int idTVA, int idTypeMaterielMedical,  String libelleMaterielMedical, String marque, Float prixHT, int numeroSerie, String dimensions, Float poids, String description, int uniteVente,int quantite)
      {
        super(idMateriel, idTVA, idTypeMaterielMedical, libelleMaterielMedical, marque, prixHT, numeroSerie, dimensions, poids, description, uniteVente);
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
