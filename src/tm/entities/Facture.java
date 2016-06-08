/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.entities;

import java.sql.Date;

/**
 *
 * @author Z500
 */
public class Facture {
    int idFacture;
    float prixTotal;
    String dateAchat;

    public Facture() {
    }

    public Facture(int idFacture, float prixTotal, String dateAchat) {
        this.idFacture = idFacture;
        this.prixTotal = prixTotal;
        this.dateAchat = dateAchat;
    }

    public int getIdFacture() {
        return idFacture;
    }

    public float getPrixTotal() {
        return prixTotal;
    }

    public String getDateAchat() {
        return dateAchat;
    }

    public void setIdFacture(int idFacture) {
        this.idFacture = idFacture;
    }

    public void setPrixTotal(float prixTotal) {
        this.prixTotal = prixTotal;
    }

    public void setDateAchat(String dateAchat) {
        this.dateAchat = dateAchat;
    }

    @Override
    public String toString() {
        return "Facture{" + "idFacture=" + idFacture + ", prixTotal=" + prixTotal + ", dateAchat=" + dateAchat + '}';
    }
    
    
    
}
