/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.entities;

import java.util.Date;

/**
 *
 * @author Z500
 */
public class PackPublicitaire {
    int idPackPublicitaire;
    String position;
    String page;
    float prix;
    Date dateDebut;
    Date dateFin;

    public PackPublicitaire() {
    }

    public PackPublicitaire(int idPackPublicitaire, String position, String page, float prix, Date dateDebut, Date dateFin) {
        this.idPackPublicitaire = idPackPublicitaire;
        this.position = position;
        this.page = page;
        this.prix = prix;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public int getIdPackPublicitaire() {
        return idPackPublicitaire;
    }

    public String getPosition() {
        return position;
    }

    public String getPage() {
        return page;
    }

    public float getPrix() {
        return prix;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setIdPackPublicitaire(int idPackPublicitaire) {
        this.idPackPublicitaire = idPackPublicitaire;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    @Override
    public String toString() {
        return "PackPublicitaire{" + "idPackPublicitaire=" + idPackPublicitaire + ", position=" + position + ", page=" + page + ", prix=" + prix + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + '}';
    }
    
    
    
    
    
    
}
