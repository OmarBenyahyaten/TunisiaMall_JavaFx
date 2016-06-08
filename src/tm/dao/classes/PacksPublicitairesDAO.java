package tm.dao.classes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tm.dao.interfaces.IEnseigneDAO;
import tm.dao.interfaces.IPacksPublicitairesDAO;
import tm.entities.Enseigne;
import tm.entities.PackPublicitaire;
import tm.technique.DataSource;
import tm.technique.Session;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Z500
 */
public class PacksPublicitairesDAO implements IPacksPublicitairesDAO {
    
     private Connection connection;

    public PacksPublicitairesDAO() {
                        connection = DataSource.getInstance().getConnection();

    }

   
  
    
    
    
   
    @Override
    public ObservableList<PackPublicitaire> DisplayAllPacks() {
        ObservableList<PackPublicitaire> listePacks = FXCollections.observableArrayList();
        

        String requete = "select * from packpublicitaire ";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
          
            
            ResultSet resultat = ps.executeQuery();
            IPacksPublicitairesDAO packDAO = new PacksPublicitairesDAO();
            while (resultat.next()) {
                PackPublicitaire pack = new PackPublicitaire();
                pack.setIdPackPublicitaire(resultat.getInt(1));
                pack.setDateDebut(resultat.getDate(2));
                pack.setDateFin(resultat.getDate(3));
                pack.setPosition(resultat.getString(4));
                pack.setPage(resultat.getString(5));
                pack.setPrix(resultat.getFloat(6));
                

                listePacks.add(pack);
            }
            return listePacks;
        } catch (SQLException ex) {
            System.out.println("Erreur lors du chargement des packs " + ex.getMessage());
            return null;
        }    
    }

    @Override
    public PackPublicitaire findPackPublicitaire(int id) {
  
        try {        
            String requete = "select * from packpublicitaire where idPackPublicitaire= ? ";
           
            PreparedStatement ps = connection.prepareStatement(requete);
             ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
                       
            PackPublicitaire pack = new PackPublicitaire();

            while (resultat.next()) {

                pack.setIdPackPublicitaire(resultat.getInt(1));
                pack.setDateDebut(resultat.getDate(2));
                pack.setDateFin(resultat.getDate(3));
                pack.setPosition(resultat.getString(4));
                pack.setPage(resultat.getString(5));
                pack.setPrix(resultat.getFloat(6));
            }
            return pack;
        } catch (SQLException ex) {
            System.out.println("Erreur lors du chargement" + ex.getMessage());
            return null;
        }
    }
        
    
    
   
    
}
