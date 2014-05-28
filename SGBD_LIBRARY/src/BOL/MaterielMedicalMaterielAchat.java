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
    private int qteMaterielAchete;

    public MaterielMedicalMaterielAchat(int idMateriel, String libelleMaterielMedical, String marque, Float prixHT, String numeroSerie, String dimensions, String poid, String uniteVente, String description,int qte)
      {
        super(idMateriel, libelleMaterielMedical, marque, prixHT, numeroSerie, dimensions, poid, uniteVente, description);
        this.qteMaterielAchete = qte;
      }

    /**
     * @return the qteMaterielAchete
     */
    public int get_qteMaterielAchete() {
        return qteMaterielAchete;
    }

    /**
     * @param qteMaterielAchete the qteMaterielAchete to set
     */
    public void setQteMaterielAchete(int qteMaterielAchete) {
        this.qteMaterielAchete = qteMaterielAchete;
    }
}
