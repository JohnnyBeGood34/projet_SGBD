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
    private int IDMateriel;
    private int idTVA;
    private int idTypeMaterielMedical;
    private String libelleMaterielMedical;
    private String marque;
    private Float prixHT;
    private String numeroSerie;
    private String dimensions;
    private String poid;
    private String uniteVente;
    private String description;
    
    public MaterielMedical(int IDMateriel, int idTVA, int idTypeMaterielMedical, String libelleMaterielMedical, String marque, Float prixHT, String numeroSerie, String dimensions, String poid, String uniteVente, String description)
      {
        this.IDMateriel = IDMateriel;
        this.idTVA = idTVA;
        this.idTypeMaterielMedical = idTypeMaterielMedical;
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
     * @return the idMateriel
     */
    public int get_IDMateriel() {
        return IDMateriel;
    }

    /**
     * @return the idTVA
     */
    public int get_idTVA() {
        return idTVA;
    }

    /**
     * @return the idTypeMaterielMedical
     */
    public int get_idTypeMaterielMedical() {
        return idTypeMaterielMedical;
    }

    /**
     * @return the libelleMaterielMedical
     */
    public String get_libelleMaterielMedical() {
        return libelleMaterielMedical;
    }

    /**
     * @return the marque
     */
    public String get_marque() {
        return marque;
    }

    /**
     * @return the prixHT
     */
    public Float get_prixHT() {
        return prixHT;
    }

    /**
     * @return the numeroSerie
     */
    public String get_numeroSerie() {
        return numeroSerie;
    }

    /**
     * @return the dimensions
     */
    public String get_dimensions() {
        return dimensions;
    }

    /**
     * @return the poid
     */
    public String get_poid() {
        return poid;
    }

    /**
     * @return the uniteVente
     */
    public String get_uniteVente() {
        return uniteVente;
    }

    /**
     * @return the description
     */
    public String get_description() {
        return description;
    }

    /**
     * @param IDMateriel the idMateriel to set
     */
    public void setIDMateriel(int IDMateriel) {
        this.IDMateriel = IDMateriel;
    }

    /**
     * @param idTVA the idTVA to set
     */
    public void setIdTVA(int idTVA) {
        this.idTVA = idTVA;
    }

    /**
     * @param idTypeMaterielMedical the idTypeMaterielMedical to set
     */
    public void setIdTypeMaterielMedical(int idTypeMaterielMedical) {
        this.idTypeMaterielMedical = idTypeMaterielMedical;
    }

    /**
     * @param libelleMaterielMedical the libelleMaterielMedical to set
     */
    public void setLibelleMaterielMedical(String libelleMaterielMedical) {
        this.libelleMaterielMedical = libelleMaterielMedical;
    }

    /**
     * @param marque the marque to set
     */
    public void setMarque(String marque) {
        this.marque = marque;
    }

    /**
     * @param prixHT the prixHT to set
     */
    public void setPrixHT(Float prixHT) {
        this.prixHT = prixHT;
    }

    /**
     * @param numeroSerie the numeroSerie to set
     */
    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    /**
     * @param dimensions the dimensions to set
     */
    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    /**
     * @param poid the poid to set
     */
    public void setPoid(String poid) {
        this.poid = poid;
    }

    /**
     * @param uniteVente the uniteVente to set
     */
    public void setUniteVente(String uniteVente) {
        this.uniteVente = uniteVente;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
