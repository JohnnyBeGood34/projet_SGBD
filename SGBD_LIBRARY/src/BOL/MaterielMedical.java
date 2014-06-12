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
    private int idTVA;
    private int idTypeMateriel;
    private String libelle;
    private String marque;
    private Float prixHT;
    private int numeroSerie;
    private String dimensions;
    private Float poids;
    private String description;
    private int uniteVente;
    
    public MaterielMedical(int idMateriel, int idTVA, int idTypeMateriel, String libelle, String marque, Float prixHT, int numeroSerie,
            String dimensions, Float poids, String description, int uniteVente)
      {
        this.idMateriel = idMateriel;
        this.idTVA = idTVA;
        this.idTypeMateriel = idTypeMateriel;
        this.libelle = libelle;
        this.marque = marque;
        this.prixHT = prixHT;
        this.numeroSerie = numeroSerie;
        this.dimensions = dimensions;
        this.poids = poids;
        this.description = description;
        this.uniteVente = uniteVente;
      }

    /**
     * @return the idMateriel
     */
    public int get_idMateriel() {
        return idMateriel;
    }

    /**
     * @return the idTVA
     */
    public int get_idTVA() {
        return idTVA;
    }

    /**
     * @return the idTypeMateriel
     */
    public int get_idTypeMateriel() {
        return idTypeMateriel;
    }

    /**
     * @return the libelle
     */
    public String get_libelle() {
        return libelle;
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
    public int get_numeroSerie() {
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
    public Float get_poids() {
        return poids;
    }

    /**
     * @return the uniteVente
     */
    public int get_uniteVente() {
        return uniteVente;
    }

    /**
     * @return the description
     */
    public String get_description() {
        return description;
    }

    /**
     * @param idMateriel
     * the idMateriel to set
     */
    public void setIdMateriel(int idMateriel) {
        this.idMateriel = idMateriel;
    }

    /**
     * @param idTVA the idTVA to set
     */
    public void setIdTVA(int idTVA) {
        this.idTVA = idTVA;
    }

    /**
     * @param idTypeMateriel the idTypeMateriel to set
     */
    public void setIdTypeMateriel(int idTypeMateriel) {
        this.idTypeMateriel = idTypeMateriel;
    }

    /**
     * @param libelle the libelleMaterielMedical to set
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
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
    public void setNumeroSerie(int numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    /**
     * @param dimensions the dimensions to set
     */
    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    /**
     * @param poids the poid to set
     */
    public void setPoids(Float poids) {
        this.poids = poids;
    }

    /**
     * @param uniteVente the uniteVente to set
     */
    public void setUniteVente(int uniteVente) {
        this.uniteVente = uniteVente;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
