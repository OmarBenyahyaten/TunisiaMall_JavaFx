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
import tm.dao.interfaces.ICarteFideliteDAO;
import tm.entities.CarteFidelite;
import tm.entities.ClientFideleDesktop;
import tm.technique.DataSource;


/**
 *
 * @author omarblythe
 */
public class CarteFideliteDAO implements ICarteFideliteDAO{
    private  Connection connection;

    public CarteFideliteDAO() {
        connection = DataSource.getInstance().getConnection();
    }
    
    @Override
    public void insertCarteFidelite(CarteFidelite carte) {
      String requete = "insert into cartefidelite (idClientFideleDesktop,idEnseigne,nombrePointFidele,dateCreationCarteFidelite) values (?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, carte.getIdClientFideleDesktop());
            ps.setInt(2, carte.getIdEnseigne());
            ps.setInt(3, carte.getNombrePointFidele());
            ps.setString(4,  carte.getDateCreationCarteFidelite());
            System.out.println(ps.toString());
            ps.executeUpdate();
            System.out.println("Ajout effectué avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'insertion " + ex.getMessage());
        }
    }

    @Override
    public void updateCarteFidelite(CarteFidelite carte) {
   String requete = "update cartefidelite set nombrePointFidele=?,idClientFideleDesktop=?,idEnseigne=?,dateCreationCarteFidelite=? where idCarteFidelite=? ";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, carte.getNombrePointFidele());
            ps.setInt(2, carte.getIdClientFideleDesktop());
            ps.setInt(3, carte.getIdEnseigne());
            ps.setString(4,  carte.getDateCreationCarteFidelite());
            ps.setInt(5, carte.getIdCarteFidelite());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    @Override
    public void deleteCarteFidelite(int id) {
String requete = "delete from cartefidelite where idCarteFidelite=? ";
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
    public ObservableList<CarteFidelite> DisplayAllCarteClientFidele() {
        ObservableList<CarteFidelite> listecarte = FXCollections.observableArrayList();
        String requete = "select * from cartefidelite";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);
            while (resultat.next()) {
                CarteFidelite carte = new CarteFidelite();
                carte.setIdCarteFidelite(resultat.getInt(1));
                carte.setIdClientFideleDesktop(resultat.getInt(2));
                carte.setIdEnseigne(resultat.getInt(3));
                carte.setNombrePointFidele(resultat.getInt(4));
                carte.setDateCreationCarteFidelite(resultat.getString(5));

                listecarte.add(carte);
            }
            return listecarte;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des carte fidelites " + ex.getMessage());
            return null;
        }
        
    }
    
    @Override
    public List<String> Displaylistclientf()
    {
        ArrayList<String> listclientf =new ArrayList<>();
    String requete = "select * from clientfideledesktop";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);
            while (resultat.next()) {
                String client;
                client=resultat.getString("idClientFideleDesktop");
                 listclientf.add(client);
            }
            return listclientf;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des carte fidelites " + ex.getMessage());
            return null;
        }
    }
         @Override
    public List<String> Displaylistenseigne()
    {
        ArrayList<String> listclientf =new ArrayList<>();
    String requete = "select * from enseigne";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);
            while (resultat.next()) {
                String client;
                client=resultat.getString("idEnseigne");
                 listclientf.add(client);
            }
            return listclientf;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des carte fidelites " + ex.getMessage());
            return null;
        }
    
    
    
    }

    
    public ObservableList<CarteFidelite> findCarteFideliteBynombrePointFidele(int nombrePointFidele ) {
        
        ObservableList<CarteFidelite> listcartefidelite = FXCollections.observableArrayList();
        try {
            String sql = "select * from cartefidelite where nombrePointFidele like '%"+nombrePointFidele+"%'";
            ResultSet resultat =connection.createStatement().executeQuery(sql);
            System.out.println("*******************");
            while (resultat.next()) {   
                CarteFidelite carte = new CarteFidelite();
                carte.setIdCarteFidelite(resultat.getInt(1));
                carte.setIdClientFideleDesktop(resultat.getInt(2));
                carte.setIdEnseigne(resultat.getInt(3));
                carte.setNombrePointFidele(resultat.getInt(4));
                 carte.setDateCreationCarteFidelite(resultat.getString(5));
                  
                
               listcartefidelite.add(carte);
            }
        } catch (Exception ex) {
            Logger.getLogger(CarteFideliteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  listcartefidelite;
    }
    
    
   

}

   

