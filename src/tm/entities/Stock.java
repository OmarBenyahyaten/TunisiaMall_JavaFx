/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.entities;


public class Stock {
    
    int idArticle;
    String nomArticle;
    float prixAchat;
    float prixVenteGros;
    float prixVenteDetail;
    int quantite;
    int tva;
    int idType;
    String Enseigne;
    String imageF;
    String imageS;

    public String getImageF() {
        return imageF;
    }

    public void setImageF(String imageF) {
        this.imageF = imageF;
    }

    public String getImageS() {
        return imageS;
    }

    public void setImageS(String imageS) {
        this.imageS = imageS;
    }

    
    
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.idArticle;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Stock other = (Stock) obj;
        if (this.idArticle != other.idArticle) {
            return false;
        }
        return true;
    }

    public void setNomArticle(String nomArticle) {
        this.nomArticle = nomArticle;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setTva(int tva) {
        this.tva = tva;
    }

    public String getNomArticle() {
        return nomArticle;
    }

    public int getQuantite() {
        return quantite;
    }

    public int getTva() {
        return tva;
    }

    public Stock() {
    }

    public Stock(int idArticle, float prixAchat, float prixVenteGros, float prixVenteDetail, int idType, String Enseigne) {
        this.idArticle = idArticle;
        this.prixAchat = prixAchat;
        this.prixVenteGros = prixVenteGros;
        this.prixVenteDetail = prixVenteDetail;
        this.idType = idType;
        this.Enseigne = Enseigne;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public float getPrixAchat() {
        return prixAchat;
    }

    public float getPrixVenteGros() {
        return prixVenteGros;
    }

    public float getPrixVenteDetail() {
        return prixVenteDetail;
    }

    public int getIdType() {
        return idType;
    }

    public String getEnseigne() {
        return Enseigne;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public void setPrixAchat(float prixAchat) {
        this.prixAchat = prixAchat;
    }

    public void setPrixVenteGros(float prixVenteGros) {
        this.prixVenteGros = prixVenteGros;
    }

    public void setPrixVenteDetail(float prixVenteDetail) {
        this.prixVenteDetail = prixVenteDetail;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public void setEnseigne(String Enseigne) {
        this.Enseigne = Enseigne;
    }

    @Override
    public String toString() {
        return "Stock{" + "idArticle=" + idArticle + ", prixAchat=" + prixAchat + ", prixVenteGros=" + prixVenteGros + ", prixVenteDetail=" + prixVenteDetail + ", idType=" + idType + ", idEnseigne=" + Enseigne + '}';
    }
    
    

   
}
