/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.dao.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tm.dao.interfaces.ITypeArticleDAO;
import tm.entities.TypeArticle;
import tm.technique.DataSource;

/**
 *
 * @author omarblythe
 */
public class TypeArticleDAO implements ITypeArticleDAO{
    private Connection connection;

    public TypeArticleDAO() {
                        connection = DataSource.getInstance().getConnection();

    }

    @Override
    public void insertTypeArticle(TypeArticle typeArticle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateTypeArticle(TypeArticle typeArticle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteTypeArticle(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> DisplayCBGenre() {

        List<String> listeGenre = new ArrayList<>();
        String requete = "select distinct genre from typeproduit";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);
            while (resultat.next()) {
                String type ;
                type=resultat.getString(1);
                listeGenre.add(type);
            }
            return listeGenre;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des stocks " + ex.getMessage());
            return null;
        }
    }   
    
    @Override
    public List<String> DisplayCBCategorie(String genre) {
        List<String> listeGenre = new ArrayList<>();
        String requete = "select distinct categorie from typeproduit where genre=?";
        try {
          PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1,genre);

                        ResultSet resultat = ps.executeQuery();
                        
                            //System.out.println(ps.toString());
            while (resultat.next()) {
                String type ;
                type=resultat.getString(1);
                listeGenre.add(type);
            }
            return listeGenre;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des stocks " + ex.getMessage());
            return null;
        }
    }   
    
    @Override
    public List<String> DisplayCBTaille(String genre,String categorie) {
        List<String> listeGenre = new ArrayList<>();
        String requete = "select distinct taille from typeproduit where genre='"+genre+"' && categorie='"+categorie+"'";
        try {
          PreparedStatement ps = connection.prepareStatement(requete);
                        ResultSet resultat = ps.executeQuery(requete);
            while (resultat.next()) {
                String type ;
                type=resultat.getString(1);
                listeGenre.add(type);
            }
            return listeGenre;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des stocks " + ex.getMessage());
            return null;
        }
    }   
    
    
    @Override
    public List<TypeArticle> DisplayAllTypeArticle() {

        List<TypeArticle> listeType = new ArrayList<>();
        String requete = "select * from typeproduit";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);
            ITypeArticleDAO typeArticleDAO = new TypeArticleDAO();
            while (resultat.next()) {
                TypeArticle type = new TypeArticle();
                type.setIdType(resultat.getInt(1));
                type.setGenre(resultat.getString(2));
                type.setCategorie(resultat.getString(3));
                type.setTaille(resultat.getString(4));

                listeType.add(type);
            }
            return listeType;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des stocks " + ex.getMessage());
            return null;
        }
    }    

    @Override
    public int getTypeByGCT(String genre, String categorie, String taille) {
        String requete = "SELECT * FROM `typeproduit` WHERE `genre` LIKE ? AND `categorie` LIKE ? AND `taille` LIKE ?";
          try {
          PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1,genre);
            ps.setString(2, categorie);
            ps.setString(3, taille);
                 ResultSet resultat = ps.executeQuery();
                 resultat.next();
           return resultat.getInt(1);
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des stocks " + ex.getMessage());
        }
    
    return -1;
    }

    @Override
    public TypeArticle getTypeByID(int id) {
String requete = "SELECT * FROM `typeproduit` WHERE idType=?";
          try {
          PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1,id);
          
                 ResultSet resultat = ps.executeQuery();
                 resultat.next();
                 TypeArticle t = new TypeArticle();
            t.setGenre(resultat.getString(2));
                        t.setCategorie(resultat.getString(3));
            t.setTaille(resultat.getString(4));
            return t;

            
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des stocks " + ex.getMessage());
        }
    return null;
    }

}
    

