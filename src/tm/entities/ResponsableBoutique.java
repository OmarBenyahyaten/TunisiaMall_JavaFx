/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.entities;

import javax.print.DocFlavor;

/**
 *
 * @author MANAI Yosr
 */
public class ResponsableBoutique {

    private int id;
    private String Enseigne;
    private String nom;
    private String prenom;
    private int tel;
    private String adresse;
    private String heureDebut;
    private String heureFin;

    public ResponsableBoutique() {
    }

    public ResponsableBoutique(int id, String Enseigne, String nom, String prenom, int tel, String adresse, String heureDebut, String heureFin) {
        this.id = id;
        this.Enseigne = Enseigne;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.adresse = adresse;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
    }

   

    public int getId() {
        return id;
    }

    public String getEnseigne() {
        return Enseigne;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getTel() {
        return tel;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEnseigne(String Enseigne) {
        this.Enseigne = Enseigne;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "ResponsableBoutique{" + "id=" + id + ", idEnseigne=" + Enseigne + ", nom=" + nom + ", prenom=" + prenom + ", tel=" + tel + ", adresse=" + adresse + ", heureDebut=" + heureDebut + ", heureFin=" + heureFin + '}';
    }

   

    public String getHeureDebut() {
        return heureDebut;
    }

    public String getHeureFin() {
        return heureFin;
    }

    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }

    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }
       
 
}
