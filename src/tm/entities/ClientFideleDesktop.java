/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.entities;

/**
 *
 * @author omarblythe
 */
public class ClientFideleDesktop {
    int idClientFideleDesktop;
    String nom;
    String prenom;
    String telephone;
    String adresse;
    String email;

    public ClientFideleDesktop() {
    }

    public ClientFideleDesktop(int idClientFideleDesktop, String nom, String prenom, String telephone, String adresse, String email) {
        this.idClientFideleDesktop = idClientFideleDesktop;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.adresse = adresse;
        this.email = email;
    }

    public int getIdClientFideleDesktop() {
        return idClientFideleDesktop;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setIdClientFideleDesktop(int idClientFideleDesktop) {
        this.idClientFideleDesktop = idClientFideleDesktop;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ClientFideleDesktop{" + "idClientFideleDesktop=" + idClientFideleDesktop + ", nom=" + nom + ", prenom=" + prenom + ", telephone=" + telephone + ", adresse=" + adresse + ", email=" + email + '}';
    }
    
    
    
    
}
