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
public class PackPublicitaireAchete {
    int idPackPublicitaire;
    int idEnseigne;

    public PackPublicitaireAchete() {
    }

    public PackPublicitaireAchete(int idPackPublicitaire, int idEnseigne) {
        this.idPackPublicitaire = idPackPublicitaire;
        this.idEnseigne = idEnseigne;
    }

    public int getIdPackPublicitaire() {
        return idPackPublicitaire;
    }

    public int getIdEnseigne() {
        return idEnseigne;
    }

    public void setIdPackPublicitaire(int idPackPublicitaire) {
        this.idPackPublicitaire = idPackPublicitaire;
    }

    public void setIdEnseigne(int idEnseigne) {
        this.idEnseigne = idEnseigne;
    }

    @Override
    public String toString() {
        return "PackPublicitaireAchete{" + "idPackPublicitaire=" + idPackPublicitaire + ", idEnseigne=" + idEnseigne + '}';
    }
    
    
    
}
