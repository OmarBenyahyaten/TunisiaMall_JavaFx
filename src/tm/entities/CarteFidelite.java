/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.entities;

import java.util.Date;

/**
 *
 * @author omarblythe
 */
public class CarteFidelite {
    int idCarteFidelite;
    int idClientFideleDesktop;
    int idEnseigne;
    int nombrePointFidele;
    String dateCreationCarteFidelite;

    public CarteFidelite() {
    }

    public CarteFidelite(int idCarteFidelite, int idClientFideleDesktop, int idEnseigne, int nombrePointFidele, String dateCreationCarteFidelite) {
        this.idCarteFidelite = idCarteFidelite;
        this.idClientFideleDesktop = idClientFideleDesktop;
        this.idEnseigne = idEnseigne;
        this.nombrePointFidele = nombrePointFidele;
        this.dateCreationCarteFidelite = dateCreationCarteFidelite;
    }

    public int getIdCarteFidelite() {
        return idCarteFidelite;
    }

    public int getIdClientFideleDesktop() {
        return idClientFideleDesktop;
    }

    public int getIdEnseigne() {
        return idEnseigne;
    }

    public int getNombrePointFidele() {
        return nombrePointFidele;
    }

    public String getDateCreationCarteFidelite() {
        return dateCreationCarteFidelite;
    }

    public void setIdCarteFidelite(int idCarteFidelite) {
        this.idCarteFidelite = idCarteFidelite;
    }

    public void setIdClientFideleDesktop(int idClientFideleDesktop) {
        this.idClientFideleDesktop = idClientFideleDesktop;
    }

    public void setIdEnseigne(int idEnseigne) {
        this.idEnseigne = idEnseigne;
    }

    public void setNombrePointFidele(int nombrePointFidele) {
        this.nombrePointFidele = nombrePointFidele;
    }

    public void setDateCreationCarteFidelite(String dateCreationCarteFidelite) {
        this.dateCreationCarteFidelite = dateCreationCarteFidelite;
    }

    @Override
    public String toString() {
        return "CarteFidelite{" + "idCarteFidelite=" + idCarteFidelite + ", idClientFideleDesktop=" + idClientFideleDesktop + ", idEnseigne=" + idEnseigne + ", nombrePointFidele=" + nombrePointFidele + ", dateCreationCarteFidelite=" + dateCreationCarteFidelite + '}';
    }
    
    
    
    
}
