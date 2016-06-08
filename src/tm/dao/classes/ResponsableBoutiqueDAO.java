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
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sun.security.jca.GetInstance;
import tm.dao.interfaces.IResponsableBoutiqueDAO;
import tm.entities.Enseigne;
import tm.entities.ResponsableBoutique;
import tm.entities.User;
import tm.technique.DataSource;
import tm.technique.Session;

/**
 *
 * @author MANAI Yosr
 */
public class ResponsableBoutiqueDAO implements IResponsableBoutiqueDAO{
    private Connection connection;

    public ResponsableBoutiqueDAO() {
                        connection = DataSource.getInstance().getConnection();

    }

    @Override
    public void insertResponsableBoutique(ResponsableBoutique resp) {

        
    String requete = "insert into responsableboutique (nom,prenom,telephone,adresse,heureDebut,heureFin ,Enseigne) values (?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, resp.getNom());
            ps.setString(2,resp.getPrenom());
            ps.setInt(3, resp.getTel());
            ps.setString(4, resp.getAdresse());
            ps.setString(5, resp.getHeureDebut());
            ps.setString(6, resp.getHeureFin());
            ps.setString(7, resp.getEnseigne());
            ps.executeUpdate();
            System.out.println("Ajout effectué avec succès");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de l'insertion " + ex.getMessage());
        }
    }

    @Override
    public void updateResponsableBoutique(ResponsableBoutique resp) {
    String requete = "update responsableboutique set nom=?, prenom=?, telephone=?, adresse=?, heureDebut=?, heureFin=? , Enseigne=? where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, resp.getNom());
            ps.setString(2, resp.getPrenom());
            ps.setInt(3, resp.getTel());
            ps.setString(4, resp.getAdresse());
            ps.setString(5, resp.getHeureDebut());
            ps.setString(6, resp.getHeureFin());
            ps.setString(7, resp.getEnseigne());
            ps.setInt(8, resp.getId());
            
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la mise à jour " + ex.getMessage());
        }    }

    @Override
    public void deleteResponsableBoutique(int id) {
String requete = "delete from responsableboutique where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la suppression " + ex.getMessage());
        }    }

    @Override
    public ObservableList<ResponsableBoutique> DisplayAllResponsableBoutique() {
        ObservableList<ResponsableBoutique> listeResponsables = FXCollections.observableArrayList();
        
        Session s = Session.getInstance();

        String requete = "select * from responsableboutique ";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);
            IResponsableBoutiqueDAO respBoutiqueDAO = new ResponsableBoutiqueDAO();
            while (resultat.next()) {
                ResponsableBoutique respBoutique = new ResponsableBoutique();
                respBoutique.setId(resultat.getInt(1));
                respBoutique.setNom(resultat.getString(2));
                respBoutique.setPrenom(resultat.getString(3));
                respBoutique.setTel(resultat.getInt(4));
                respBoutique.setAdresse(resultat.getString(5));
                respBoutique.setHeureDebut(resultat.getString(6));
                respBoutique.setHeureFin(resultat.getString(7));
                respBoutique.setEnseigne(resultat.getString(8));

                listeResponsables.add(respBoutique);
            }
            return listeResponsables;
        } catch (SQLException ex) {
            System.out.println("Erreur lors du chargement des Responsables " + ex.getMessage());
            return null;
        }    
    }

    @Override
    public ObservableList<ResponsableBoutique> findByEnseigne(int idEnseigne) {
            
        ResponsableBoutique resp=new ResponsableBoutique();

        try {
                
            String requete = "select * from responsableboutique where id=?";

            ObservableList<ResponsableBoutique> listResponsables = FXCollections.observableArrayList();    

            PreparedStatement ps = connection.prepareStatement(requete);
            
          

            ps.setInt(1, idEnseigne);

            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
            

                resp.setId(resultat.getInt(1));
                resp.setNom(resultat.getString(2));
                resp.setPrenom(resultat.getString(3));
                resp.setTel(resultat.getInt(4));
                resp.setAdresse(resultat.getString(5));
                resp.setHeureDebut(resultat.getString(6));
                resp.setHeureFin(resultat.getString(7));
                resp.setEnseigne(resultat.getString(8));
                listResponsables.add(resp);
            }
            return listResponsables;
        } catch (SQLException ex) {
            System.out.println("Erreur lors du chargement" + ex.getMessage());
            return null;
        }
    }
    
    
     @Override
    public ObservableList<ResponsableBoutique> findResponsable(String search) {
  
        Session s = Session.getInstance();

        
        try {        
            String requete = "select * from responsableboutique where nom like '%"+search+"%' or prenom like '%"+search+"%' or heureDebut like '%"+search+"%' or heureFin like '%"+search+"%';";


            ObservableList<ResponsableBoutique> listResponsables = FXCollections.observableArrayList();  
            
            
            //ResultSet resultat = statement.executeQuery(requete);
            
            PreparedStatement ps = connection.prepareStatement(requete);
            System.out.println(ps);
            
            ResultSet resultat = ps.executeQuery();
            System.out.println(ps.toString());
            
            
            
            
            // ResultSet resultat =connection.createStatement().executeQuery(requete);
            while (resultat.next()) {

                ResponsableBoutique resp = new ResponsableBoutique();


                resp.setId(resultat.getInt(1));
                resp.setNom(resultat.getString(2));
                resp.setPrenom(resultat.getString(3));
                resp.setTel(resultat.getInt(4));
                resp.setAdresse(resultat.getString(5));
                resp.setHeureDebut(resultat.getString(6));
                resp.setHeureFin(resultat.getString(7));
                resp.setEnseigne(resultat.getString(8));
                System.out.println(resp.toString());
                listResponsables.add(resp);
            }
            return listResponsables;
        } catch (SQLException ex) {
            System.out.println("Erreur lors du chargement" + ex.getMessage());
            return null;
        }
    }
   
    
    @Override
    public boolean verifTel(String text) {
        

        String requete = "select * from responsableboutique where telephone=?;";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, text);
                        System.out.println(ps);

            ResultSet resultat = ps.executeQuery();
            resultat.next();
            int id =resultat.getInt(4);
            if(id == Integer.parseInt(text))
                
            return true;
            else 
                return false ;
        } catch (SQLException ex) {
            System.out.println("Erreur lors du chargement du telephone " + ex.getMessage());
            return false;
        }
    }    

    @Override
    public int getIdEnseigne(String toString) {
        
        String requete = "select idEnseigne from enseigne where idEnseigne=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            
            ps.setString(1, toString);
            ResultSet resultat = ps.executeQuery();
            resultat.next();
            int id=resultat.getInt(1);
            return id;
        } catch (SQLException ex) {
            System.out.println("erreur lors de lcombo box promotion " + ex.getMessage());
            return -1;
        } 
        
       
    }    

    @Override
    public ObservableList<ResponsableBoutique> DisplayResponsableBoutique(String id) {
 ObservableList<ResponsableBoutique> listeResponsables =FXCollections.observableArrayList() ;
        String requete = "select * from responsableboutique where Enseigne ='"+id+"';";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);
            while (resultat.next()) {
               ResponsableBoutique respBoutique = new ResponsableBoutique();
                respBoutique.setId(resultat.getInt(1));
                respBoutique.setNom(resultat.getString(2));
                respBoutique.setPrenom(resultat.getString(3));
                respBoutique.setTel(resultat.getInt(4));
                respBoutique.setAdresse(resultat.getString(5));
                respBoutique.setHeureDebut(resultat.getString(6));
                respBoutique.setHeureFin(resultat.getString(7));
                respBoutique.setEnseigne(resultat.getString(8));

                listeResponsables.add(respBoutique);
            }
            return listeResponsables;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des articles en promotion " + ex.getMessage());
           return null;
        }    }
       
}
