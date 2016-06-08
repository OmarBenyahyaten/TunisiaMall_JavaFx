/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.entities;

import java.util.Objects;

/**
 *
 * @author omarblythe
 */
public class Vente{
     int idVente;
     int idArticle;
String nomArticle;
float prixUnitaire;
int tva;
     int quantite;
     float totalTVA;
    float remise ;
    float totalRemise;
    float totalHT;
    float totalTTC;
    int idFacture;
    

    public Vente() {
    }

    public Vente(int idVente, int idArticle, String nomArticle, float prixUnitaire, int tva, int quantite, float totalTVA, float remise, float totalRemise, float totalHT, float totalTTC, int idFacture) {
        this.idVente = idVente;
        this.idArticle = idArticle;
        this.nomArticle = nomArticle;
        this.prixUnitaire = prixUnitaire;
        this.tva = tva;
        this.quantite = quantite;
        this.totalTVA = totalTVA;
        this.remise = remise;
        this.totalRemise = totalRemise;
        this.totalHT = totalHT;
        this.totalTTC = totalTTC;
        this.idFacture = idFacture;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.idArticle;
       
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vente other = (Vente) obj;
        if (this.idArticle != other.idArticle) {
            return false;
        }
        return true;
    }

    public int getIdVente() {
        return idVente;
    }

    public void setIdVente(int idVente) {
        this.idVente = idVente;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public String getNomArticle() {
        return nomArticle;
    }

    public void setNomArticle(String nomArticle) {
        this.nomArticle = nomArticle;
    }

    public float getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(float prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public int getTva() {
        return tva;
    }

    public void setTva(int tva) {
        this.tva = tva;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public float getTotalTVA() {
        return totalTVA;
    }

    public void setTotalTVA(float totalTVA) {
        this.totalTVA = totalTVA;
    }

    public float getRemise() {
        return remise;
    }

    public void setRemise(float remise) {
        this.remise = remise;
    }

    public float getTotalRemise() {
        return totalRemise;
    }

    public void setTotalRemise(float totalRemise) {
        this.totalRemise = totalRemise;
    }

    public float getTotalHT() {
        return totalHT;
    }

    public void setTotalHT(float totalHT) {
        this.totalHT = totalHT;
    }

    public float getTotalTTC() {
        return totalTTC;
    }

    public void setTotalTTC(float totalTTC) {
        this.totalTTC = totalTTC;
    }

    public int getIdFacture() {
        return idFacture;
    }

    public void setIdFacture(int idFacture) {
        this.idFacture = idFacture;
    }
    
    
    
}

 
    