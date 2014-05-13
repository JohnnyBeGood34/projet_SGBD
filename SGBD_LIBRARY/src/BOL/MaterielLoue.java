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

    /**
     * @return the etat
     */
    public String getEtat() {
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
    public int getQteMaterielLoue() {
        return qteMaterielLoue;
    }

    /**
     * @param QteMaterielLoue the QteMaterielLoue to set
     */
    public void setQteMaterielLoue(int QteMaterielLoue) {
        this.qteMaterielLoue = QteMaterielLoue;
    }
}
