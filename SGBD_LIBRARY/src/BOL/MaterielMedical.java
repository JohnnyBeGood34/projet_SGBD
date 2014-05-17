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
public abstract class MaterielMedical {
    private int IdMateriel;
    private String LibelleMaterielMedical;
    private String marque;
    private Float prixHT;
    private String numeroSerie;
    private String dimensions;
    private String poid;
    private String uniteVente;
    private String description;

    /**
     * @return the IdMateriel
     */
    public int getIdMateriel() {
        return IdMateriel;
    }

    /**
     * @param IdMateriel the IdMateriel to set
     */
    public void setIdMateriel(int IdMateriel) {
        this.IdMateriel = IdMateriel;
    }

    /**
     * @return the LibelleMaterielMedical
     */
    public String getLibelleMaterielMedical() {
        return LibelleMaterielMedical;
    }

    /**
     * @param LibelleMaterielMedical the LibelleMaterielMedical to set
     */
    public void setLibelleMaterielMedical(String LibelleMaterielMedical) {
        this.LibelleMaterielMedical = LibelleMaterielMedical;
    }

    /**
     * @return the marque
     */
    public String getMarque() {
        return marque;
    }

    /**
     * @param marque the marque to set
     */
    public void setMarque(String marque) {
        this.marque = marque;
    }

    /**
     * @return the prixHT
     */
    public Float getPrixHT() {
        return prixHT;
    }

    /**
     * @param prixHT the prixHT to set
     */
    public void setPrixHT(Float prixHT) {
        this.prixHT = prixHT;
    }

    /**
     * @return the numeroSerie
     */
    public String getNumeroSerie() {
        return numeroSerie;
    }

    /**
     * @param numeroSerie the numeroSerie to set
     */
    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    /**
     * @return the dimensions
     */
    public String getDimensions() {
        return dimensions;
    }

    /**
     * @param dimensions the dimensions to set
     */
    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    /**
     * @return the poid
     */
    public String getPoid() {
        return poid;
    }

    /**
     * @param poid the poid to set
     */
    public void setPoid(String poid) {
        this.poid = poid;
    }

    /**
     * @return the uniteVente
     */
    public String getUniteVente() {
        return uniteVente;
    }

    /**
     * @param uniteVente the uniteVente to set
     */
    public void setUniteVente(String uniteVente) {
        this.uniteVente = uniteVente;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
}
