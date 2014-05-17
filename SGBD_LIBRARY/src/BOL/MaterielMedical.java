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
    private int idMateriel;
    private String libelleMaterielMedical;
    private String marque;
    private Float prixHT;
    private String numeroSerie;
    private String dimensions;
    private String poid;
    private String uniteVente;
    private String description;

    public MaterielMedical(int idMateriel, String libelleMaterielMedical, String marque, Float prixHT, String numeroSerie, String dimensions, String poid, String uniteVente, String description)
      {
        this.idMateriel = idMateriel;
        this.libelleMaterielMedical = libelleMaterielMedical;
        this.marque = marque;
        this.prixHT = prixHT;
        this.numeroSerie = numeroSerie;
        this.dimensions = dimensions;
        this.poid = poid;
        this.uniteVente = uniteVente;
        this.description = description;
      }
    
    /**
     * @return the IdMateriel
     */
    public int get_idMateriel() {
        return idMateriel;
    }

    /**
     * @param IdMateriel the IdMateriel to set
     */
    public void setIdMateriel(int IdMateriel) {
        this.idMateriel = IdMateriel;
    }

    /**
     * @return the LibelleMaterielMedical
     */
    public String get_libelleMaterielMedical() {
        return libelleMaterielMedical;
    }

    /**
     * @param LibelleMaterielMedical the LibelleMaterielMedical to set
     */
    public void set_libelleMaterielMedical(String LibelleMaterielMedical) {
        this.libelleMaterielMedical = LibelleMaterielMedical;
    }

    /**
     * @return the marque
     */
    public String get_marque() {
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
    public Float get_prixHT() {
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
    public String get_numeroSerie() {
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
    public String get_dimensions() {
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
    public String get_poid() {
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
    public String get_uniteVente() {
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
    public String get_description() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
}
