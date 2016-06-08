/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.dao.classes;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tm.dao.interfaces.IArticlePromotionDAO;
import tm.entities.ArticlePromotion;
import tm.entities.Enseigne;
import tm.entities.Promotion;
import tm.technique.DataSource;

/**
 *
 * @author omarblythe
 */
public class ArticlePromotionDAO implements IArticlePromotionDAO {

        private Connection connection;

    public ArticlePromotionDAO() {
                connection = DataSource.getInstance().getConnection();

    }

    @Override
    public void insertArticlePromotion(ArticlePromotion Apr) {
 String requete = "insert into articlepromotion (idArticle,idPromotion,tauxReductionGros,tauxReductionDetail,nombrePointFidele) values (?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1,Apr.getIdArticle());
            ps.setInt(2,Apr.getIdPromotion());
            ps.setFloat(3,Apr.getTauxReductionGros());
            ps.setFloat(4,Apr.getTauxReductionDetail());
            ps.setInt(5, Apr.getNombrePointFidele());
            ps.executeUpdate();
            System.out.println("Ajout effectuÃ© avec succÃ¨s");
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'insertion " + ex.getMessage());
        }
    }

    @Override
    public void updateArticlePromotion(ArticlePromotion Apr) {
String requete = "update articlepromotion set tauxReductionGros=?, tauxReductionDetail=?, nombrePointFidele=? where idPromotion=? and idArticle=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
             ps.setFloat(1,Apr.getTauxReductionGros());
            ps.setFloat(2,Apr.getTauxReductionDetail());
            ps.setInt(3, Apr.getNombrePointFidele());
            ps.setInt(5, Apr.getIdPromotion());
            ps.setInt(4, Apr.getIdArticle());
            System.out.println(ps.toString());
            ps.executeUpdate();
            System.out.println("Mise Ã  jour effectuÃ©e avec succÃ¨s");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise Ã  jour " + ex.getMessage());
        } 
    }

    @Override
    public void deleteArticlePromotion(int id,int id2) {
String requete = "delete from articlepromotion where idPromotion=? and idArticle=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ps.setInt(2, id2);
            ps.executeUpdate();
            System.out.println("Suppression effectuÃ©e avec succÃ¨s");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        } 
    }

    @Override
    public ObservableList<ArticlePromotion> DisplayAllArticlePromotion() {

        ObservableList<ArticlePromotion> listearticlepromo =FXCollections.observableArrayList() ;
        String requete = "select * from articlepromotion";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);
            while (resultat.next()) {
                ArticlePromotion apr = new ArticlePromotion();
                apr.setIdPromotion(resultat.getInt(1));
                apr.setIdArticle(resultat.getInt(2));
                apr.setTauxReductionDetail(resultat.getFloat(3));
                apr.setTauxReductionGros(resultat.getFloat(4));
                apr.setNombrePointFidele(resultat.getInt(5));

                listearticlepromo.add(apr);
            }
            return listearticlepromo;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des articles en promotion " + ex.getMessage());
           return null;
        }
    }
    @Override
    public List<String> DisplayCBArticle() {
        ArrayList<String> listeArticle = new ArrayList<>();
        String requete = "select * from stock";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);
            while (resultat.next()) {
                String article ;
                article=resultat.getString("nomArticle");
                listeArticle.add(article);
            }
            return listeArticle;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des enseignes " + ex.getMessage());
            return null;
        }
    }
    @Override
    public List<String> DisplayCBPromotion() {
        ArrayList<String> listeArticle = new ArrayList<>();
        String requete = "select * from promotion";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);
            while (resultat.next()) {
                String article ;
                article=resultat.getString("nom");
                listeArticle.add(article);
            }
            return listeArticle;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des enseignes " + ex.getMessage());
            return null;
        }
    }
     @Override
    public int DisplayCBArticle2(String c) {
        ArrayList<String> listeArticle = new ArrayList<>();
        String requete = "select idArticle from stock where nomArticle=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            
            ps.setString(1, c);
            ResultSet resultat = ps.executeQuery();
            resultat.next();
            int id=resultat.getInt(1);
            System.out.println("combo box article ok");
            return id;
        } catch (SQLException ex) {
            System.out.println("erreur lors de combo box article " + ex.getMessage());
            return -1;
        } 
        
    }
         @Override
    public int DisplayCBPromotion2(String c) {
        ArrayList<String> listeArticle = new ArrayList<>();
        String requete = "select idPromotion from promotion where nom=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            
            ps.setString(1, c);
            ResultSet resultat = ps.executeQuery();
            resultat.next();
            int id=resultat.getInt(1);
            System.out.println("combo box promotion ok");
            return id;
        } catch (SQLException ex) {
            System.out.println("erreur lors de lcombo box promotion " + ex.getMessage());
            return -1;
        } 
        
    }
    @Override
    public List<String> DisplayPromotion() {

        List<String> listeNom = new ArrayList<>();
        String requete = "select distinct idPromotion from articlepromotion";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);
            while (resultat.next()) {
                String nomEnseigne ;
                nomEnseigne=resultat.getString(1);
                listeNom.add(nomEnseigne);
            }
            return listeNom;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des enseignes " + ex.getMessage());
            return null;
        }
    }  

    @Override
    public ArticlePromotion findEnseigneByNom(String nom) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<ArticlePromotion> DisplayRechPromotion(int id) {
        ObservableList<ArticlePromotion> listearticlepromo =FXCollections.observableArrayList() ;
        String requete = "select * from articlepromotion where idPromotion ='"+id+"';";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);
            while (resultat.next()) {
                ArticlePromotion apr = new ArticlePromotion();
                apr.setIdPromotion(resultat.getInt(1));
                apr.setIdArticle(resultat.getInt(2));
                apr.setTauxReductionDetail(resultat.getFloat(3));
                apr.setTauxReductionGros(resultat.getFloat(4));
                apr.setNombrePointFidele(resultat.getInt(5));

                listearticlepromo.add(apr);
            }
            return listearticlepromo;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des articles en promotion " + ex.getMessage());
           return null;
        }
    }

    
    @Override
    public int getRemise(int id) {
int remise=0 ;
        String requete = "SELECT tauxReductionDetail FROM `articlepromotion` WHERE `idArticle` = ?;";
        try {
 PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1,id);
             ResultSet resultat = ps.executeQuery();

            if(resultat.next());
            {
                remise=resultat.getInt(1);
                            return remise;}

            } catch (SQLException ex) {
                Logger.getLogger(ArticlePromotionDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        return remise;
    }
     @Override
    public int getRemiseGros(int id) {
int remise=0 ;
        String requete = "SELECT tauxReductionGros FROM `articlepromotion` WHERE `idArticle` = ?;";
        try {
 PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1,id);
             ResultSet resultat = ps.executeQuery();

            resultat.next();
                remise=resultat.getInt(1);
                            return remise;

            } catch (SQLException ex) {
                Logger.getLogger(ArticlePromotionDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        return remise;
    }
    
}
    

