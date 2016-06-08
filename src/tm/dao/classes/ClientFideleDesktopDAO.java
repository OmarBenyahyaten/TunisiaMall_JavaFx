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
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tm.dao.interfaces.IClientFideleDesktopDAO;
import tm.entities.ClientFideleDesktop;
import tm.technique.DataSource;

/**
 *
 * @author omarblythe
 */
public class ClientFideleDesktopDAO implements IClientFideleDesktopDAO{
    private Connection connection;

    public ClientFideleDesktopDAO() {
            connection = DataSource.getInstance().getConnection();

    }

    @Override
    public void insertClientFideleDesktop(ClientFideleDesktop clFidele) {
   
        String requete = "insert into clientfideledesktop (nom,prenom,telephone,adresse,email) values (?,?,?,?,?);";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
          ps.setString(1, clFidele.getNom());
                    ps.setString(2, clFidele.getPrenom());

                             ps.setString(3, clFidele.getTelephone());
          ps.setString(4, clFidele.getAdresse());
          ps.setString(5, clFidele.getEmail());

            ps.executeUpdate();
            System.out.println("Ajout effectué avec succès");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de l'insertion " + ex.getMessage());
        }
    }

    @Override
    public void updateClientFideleDesktop(ClientFideleDesktop clFidele) {
    String requete = "update clientfideledesktop set nom=? ,prenom=? ,telephone=? ,adresse=? ,email=? where idClientFideleDesktop=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
           ps.setString(1, clFidele.getNom());
            ps.setString(2, clFidele.getPrenom());
            ps.setString(3, clFidele.getTelephone());
            ps.setString(4,clFidele.getAdresse());
            ps.setString(5,clFidele.getEmail());
            ps.setInt(6,clFidele.getIdClientFideleDesktop());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    @Override
    public void deleteClientFideleDesktop(int id) {
    String requete = "delete from clientfideledesktop where idClientFideleDesktop=?";
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
    public ObservableList<ClientFideleDesktop> DisplayAllClientFideleDesktop() {
ObservableList<ClientFideleDesktop> listeclientF = FXCollections.observableArrayList();
        String requete = "select * from clientfideledesktop";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);
            while (resultat.next()) {
                ClientFideleDesktop clientF = new ClientFideleDesktop();
                clientF.setIdClientFideleDesktop(resultat.getInt(1));
                clientF.setNom(resultat.getString(2));
                clientF.setPrenom(resultat.getString(3));
                clientF.setTelephone(resultat.getString(4));
                clientF.setAdresse(resultat.getString(5));
                clientF.setEmail(resultat.getString(6));
                listeclientF.add(clientF);
            }
            return listeclientF;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des clients fideles " + ex.getMessage());
            return null;
        }
    }
    public ObservableList<ClientFideleDesktop> findClientFideleDesktopByNom(String nom) {
        
        ObservableList<ClientFideleDesktop> listclientfidele = FXCollections.observableArrayList();
        try {
            String sql = "select * from clientfideledesktop where nom like '%"+nom+"%'";
            ResultSet resultat =connection.createStatement().executeQuery(sql);
            System.out.println("*******************");
            while (resultat.next()) {   
                ClientFideleDesktop client = new ClientFideleDesktop();
                client.setIdClientFideleDesktop(resultat.getInt(1));
                client.setNom(resultat.getString(2));
                client.setPrenom(resultat.getString(3));
                client.setTelephone(resultat.getString(4));
                 client.setAdresse(resultat.getString(5));
                  client.setEmail(resultat.getString(5));
                
               listclientfidele.add(client);
            }
        } catch (Exception ex) {
            Logger.getLogger(ClientFideleDesktopDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  listclientfidele;
    }
    
}
