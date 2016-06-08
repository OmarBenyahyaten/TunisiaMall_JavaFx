/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.entities;

import java.util.Date;



/**
 *
 * @author MANAI Yosr
 */
public class Enseigne {
    int idEnseigne;
    String nom;
    String entreprise;
    int store;
    int tel;
    String heureOuverture;
    String heureFermetture;
    String categorie;
    String description;
    String srcLogo;
    int idResponsableEnseigne;
    Date dateCreation;

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }
    

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Enseigne() {
    }

    public Enseigne(int idEnseigne, String nom, String entreprise, int store, String heureOuverture, String heureFermetture, String description, String srcLogo, int idResponsableEnseigne) {
        this.idEnseigne = idEnseigne;
        this.nom = nom;
        this.entreprise = entreprise;
        this.store = store;
        this.heureOuverture = heureOuverture;
        this.heureFermetture = heureFermetture;
        this.description = description;
        this.srcLogo = srcLogo;
        this.idResponsableEnseigne = idResponsableEnseigne;
    }

    
    
    public Enseigne(int idEnseigne, String nom, String entreprise, int store, String heureOuverture, String heureFermetture, String description, String srcLogo) {
        this.idEnseigne = idEnseigne;
        this.nom = nom;
        this.entreprise = entreprise;
        this.store = store;
        this.heureOuverture = heureOuverture;
        this.heureFermetture = heureFermetture;
        this.description = description;
        this.srcLogo = srcLogo;
    }
    
    
    
    public int getIdEnseigne() {
        return idEnseigne;
    }

    public String getNom() {
        return nom;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public int getStore() {
        return store;
    }

    public String getHeureOuverture() {
        return heureOuverture;
    }

    public String getHeureFermetture() {
        return heureFermetture;
    }

    public String getDescription() {
        return description;
    }

    public String getSrcLogo() {
        return srcLogo;
    }

    public int getIdResponsableEnseigne() {
        return idResponsableEnseigne;
    }

    public void setIdEnseigne(int idEnseigne) {
        this.idEnseigne = idEnseigne;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public void setStore(int store) {
        this.store = store;
    }

    public void setHeureOuverture(String heureOuverture) {
        this.heureOuverture = heureOuverture;
    }

    public void setHeureFermetture(String heureFermetture) {
        this.heureFermetture = heureFermetture;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSrcLogo(String srcLogo) {
        this.srcLogo = srcLogo;
    }

    public void setIdResponsableEnseigne(int idResponsableEnseigne) {
        this.idResponsableEnseigne = idResponsableEnseigne;
    }

    @Override
    public String toString() {
        return "Enseigne{" + "idEnseigne=" + idEnseigne + ", nom=" + nom + ", entreprise=" + entreprise + ", store=" + store + ", heureOuverture=" + heureOuverture + ", heureFermetture=" + heureFermetture + ", description=" + description + ", srcLogo=" + srcLogo + ", idResponsableEnseigne=" + idResponsableEnseigne + '}';
    }

    
    
    
    
}
