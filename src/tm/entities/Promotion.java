/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.entities;

import java.util.Date;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;


/**
 *
 * @author omarblythe
 */
public class Promotion {
    
    int idPromotion;
    Date dateDebut ; 
    Date dateFin ; 
    String nom;
    String slogan;
    String description;
    

    public String getDescription() {
        return description;
    }
    

    public void setDescription(String description) {
        this.description = description;
    }
    

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public Promotion() {
    }

    public Promotion(int idPromotion, Date dateDebut, Date dateFin, String nomPromotion) {
        this.idPromotion = idPromotion;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nom = nomPromotion;
    }

    public int getIdPromotion() {
        return idPromotion;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public String getNom() {
        return nom;
    }

    public void setIdPromotion(int idPromotion) {
        this.idPromotion = idPromotion;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Promotion{" + "idPromotion=" + idPromotion + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", nom=" + nom + ", slogan=" + slogan + ", description=" + description + '}';
    }

    

    
    
    
    
    
}
