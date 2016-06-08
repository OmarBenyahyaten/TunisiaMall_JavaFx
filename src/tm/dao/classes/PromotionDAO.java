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
import tm.dao.interfaces.IPromotionDAO;
import tm.entities.Enseigne;
import tm.entities.Promotion;
import tm.technique.DataSource;

/**
 *
 * @author omarblythe
 */
public class PromotionDAO implements IPromotionDAO{
    private Connection connection;

    public PromotionDAO() {
        connection = DataSource.getInstance().getConnection();

    }

    @Override
    public void insertPromotion(Promotion pr) {
       String requete = "insert into promotion (nom,slogan,dateDebut,dateFin,description) values (?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
           ps.setString(1, pr.getNom());
           ps.setString(2, pr.getSlogan());
             ps.setDate(3, (Date)pr.getDateDebut());
              ps.setDate(4, (Date)pr.getDateFin());
               ps.setString(5, pr.getDescription());
            ps.executeUpdate();
            System.out.println("Ajout effectué avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'insertion " + ex.getMessage());
        }
    }

    @Override
    public void updatePromotion(Promotion pr) {
         String requete = "update promotion set nom=?,slogan=?,dateDebut=?, dateFin=?,description=? where idPromotion=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, pr.getNom());
             ps.setString(2, pr.getSlogan());
            ps.setDate(3, (Date) pr.getDateDebut());
            ps.setDate(4, (Date) pr.getDateFin());
            ps.setString(5, pr.getDescription());
           
            ps.setInt(6, pr.getIdPromotion());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    @Override
    public void deletePromotion(int id) {
        String requete = "delete from promotion where idPromotion=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    @Override
    public ObservableList<Promotion> DisplayAllPromotion() {
      ObservableList<Promotion> listepromo = FXCollections.observableArrayList();
        String requete = "select * from promotion";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);
            while (resultat.next()) {
                Promotion pro = new Promotion();
                pro.setIdPromotion(resultat.getInt(1));
                pro.setNom(resultat.getString(2));
                pro.setSlogan(resultat.getString(3));
                pro.setDateDebut(resultat.getDate(4));
                pro.setDateFin(resultat.getDate(5));
                pro.setDescription(resultat.getString(6));

                listepromo.add(pro);
            }
            return listepromo;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des promotions " + ex.getMessage());
            return null;
        }
    }
    @Override
    public ObservableList<Promotion> findPromotionByNom(String nom) {
        
        ObservableList<Promotion> listpromo = FXCollections.observableArrayList();
        try {
            String sql = "select * from promotion where nom like '%"+nom+"%'";
            ResultSet resultat =connection.createStatement().executeQuery(sql);
            while (resultat.next()) {   
                Promotion pro = new Promotion();
                pro.setIdPromotion(resultat.getInt(1));
                pro.setNom(resultat.getString(2));
                pro.setSlogan(resultat.getString(3));
                pro.setDateDebut(resultat.getDate(4));
                pro.setDateFin(resultat.getDate(5));
                pro.setDescription(resultat.getString(6));
                listpromo.add(pro);
            }
        } catch (Exception ex) {
            Logger.getLogger(PromotionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listpromo;
    }

}