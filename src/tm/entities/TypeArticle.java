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
public class TypeArticle {
    int idType;
    String genre ;
    String categorie;
    String taille;


    public TypeArticle() {
    }

    public TypeArticle(int idType, String taille, String genre, String categorie) {
        this.idType = idType;
        this.genre = genre;
        this.categorie = categorie;
    }

    public int getIdType() {
        return idType;
    }

    public String getGenre() {
        return genre;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getTaille() {
        return taille;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    @Override
    public String toString() {
        return "TypeArticle{" + "idType=" + idType + ", genre=" + genre + ", categorie=" + categorie + ", taille=" + taille + '}';
    }

   
    
    
    
}
