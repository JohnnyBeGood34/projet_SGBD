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
public class MaterielLoue extends MaterielMedical{
    private String etat;
    private int qteMaterielLoue;

    public MaterielLoue(int idMateriel, String libelleMaterielMedical, String marque, Float prixHT, String numeroSerie, String dimensions, String poid, String uniteVente, String description,String etat,int qte)
      {
        super(idMateriel, libelleMaterielMedical, marque, prixHT, numeroSerie, dimensions, poid, uniteVente, description);
        this.etat = etat;
        this.qteMaterielLoue = qte;
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
     * @return the QteMaterielLoue
     */
    public int get_qteMaterielLoue() {
        return qteMaterielLoue;
    }

    /**
     * @param QteMaterielLoue the QteMaterielLoue to set
     */
    public void setQteMaterielLoue(int QteMaterielLoue) {
        this.qteMaterielLoue = QteMaterielLoue;
    }
}
