/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.dao.classes;

import tm.dao.interfaces.IStockDAO;
import tm.entities.Stock;
import tm.technique.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.*;
import tm.entities.Enseigne;
import tm.technique.Session;


/**
 *
 * @author Karray
 */
public class StockDAO implements IStockDAO {

    private Connection connection;

    public StockDAO() {
        connection = DataSource.getInstance().getConnection();
    }

     @Override
    public boolean insertStock(Stock s) {
         String requete = "INSERT INTO `stock` (`idArticle`, `nomArticle`, `prixAchat`, `prixVenteGros`, `prixVenteDetail`, `tva` , `quantite`, `idType`, `enseigne`,`imageF`,`imageS`) VALUES (NULL, ?,?,?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, s.getNomArticle());
            ps.setFloat(2, s.getPrixAchat());
            ps.setFloat(3, s.getPrixVenteGros());
            ps.setFloat(4, s.getPrixVenteDetail());
            ps.setInt(5, s.getTva());
            ps.setInt(6, s.getQuantite());
            ps.setInt(7, s.getIdType());
            ps.setString(8, s.getEnseigne());
            ps.setString(9, s.getImageF());
            ps.setString(10, s.getImageS());

            
            ps.executeUpdate();
return true ;        } catch (SQLException ex) {
            System.out.println("erreur lors de l'insertion " + ex.getMessage());
return false ;        }

    }

    @Override
    public boolean updateStock(Stock s) {
             String requete = "UPDATE `stock` SET `nomArticle` = ?, `prixAchat` = ?, `prixVenteGros` = ?, `prixVenteDetail` = ?, `tva` = ?, `quantite` = ?, `idType` = ?, `enseigne` = ? WHERE `stock`.`idArticle` = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, s.getNomArticle());
            ps.setFloat(2, s.getPrixAchat());
            ps.setFloat(3, s.getPrixVenteGros());
            ps.setFloat(4, s.getPrixVenteDetail());
            ps.setInt(5, s.getTva());
            ps.setInt(6, s.getQuantite());
            ps.setInt(7, s.getIdType());
            ps.setString(8, s.getEnseigne());
            ps.setInt(9, s.getIdArticle());
            System.out.println(ps);
            
            ps.executeUpdate();
            System.out.println("Ajout effectué avec succès");
            return true;
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'insertion " + ex.getMessage());
            return false;
        }

    }

    @Override
    public void deleteSotck(int id) {
String requete = "delete from stock where idArticle=?";
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
    public Stock findStockByNum(int num) {
        return null;

    }

    @Override
    public ObservableList<Stock> DisplayStocks(String search) {
        ObservableList<Stock> stock =  FXCollections.observableArrayList();
                Session sess = Session.getInstance();

        String requete = "SELECT DISTINCT stock.idArticle,stock.nomArticle,stock.prixAchat,stock.prixVenteGros,stock.prixVenteDetail,stock.tva,stock.quantite,stock.idType,stock.enseigne,stock.imageF,stock.imageS FROM stock,enseigne WHERE stock.enseigne=enseigne.nom AND enseigne.idResponsableEnseigne=? AND (idArticle like '%"+search+"%' OR enseigne like '%"+search+"%' OR nomArticle like '%"+search+"%');";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
                        ps.setInt(1, sess.getId());
                        System.out.println(ps.toString());
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                Stock s = new Stock();
                s.setIdArticle(resultat.getInt(1));
                s.setNomArticle(resultat.getString(2));
                s.setPrixAchat(resultat.getFloat(3));
                s.setPrixVenteGros(resultat.getFloat(4));
                s.setPrixVenteDetail(resultat.getFloat(5));
                s.setTva(resultat.getInt(6));
                s.setQuantite(resultat.getInt(7));
                s.setIdType(resultat.getInt(8));
               s.setEnseigne(resultat.getString(9));
               s.setImageF(resultat.getString(10));
               s.setImageS(resultat.getString(11));




                stock.add(s);

            }
                                        return stock;

        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des stocks " + ex.getMessage());
            return null;
        }

}
    
    @Override
    public ObservableList<Stock> findStock(String search) {

        
               
        Session s = Session.getInstance();

        
        try {        
            String requete = "select * from enseigne where idResponsableEnseigne= ? and nom like '%"+search+"%' or adresse like '%"+search+"%'  or entreprise like '%"+search+"%' or heureOuverture like '%"+search+"%' or heureFermetture like '%"+search+"%' or description like '%"+search+"%'";


            ObservableList<Stock> listEnseignes = FXCollections.observableArrayList();  
            
            
            //ResultSet resultat = statement.executeQuery(requete);
            
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, s.getId());
            
            ResultSet resultat = ps.executeQuery();
            
            
            
            
            
            // ResultSet resultat =connection.createStatement().executeQuery(requete);
            while (resultat.next()) {
                Stock stock = new Stock();
                Enseigne enseigne = new Enseigne();


                enseigne.setIdEnseigne(resultat.getInt(1));
                enseigne.setNom(resultat.getString(2));
                enseigne.setEntreprise(resultat.getString(3));
                enseigne.setStore(resultat.getInt(4));
                enseigne.setHeureOuverture(resultat.getString(5));
                enseigne.setHeureFermetture(resultat.getString(6));
                enseigne.setDescription(resultat.getString(7));
                enseigne.setSrcLogo(resultat.getString(8));
                enseigne.setIdResponsableEnseigne(s.getId());
                listEnseignes.add(stock);
            }
            return listEnseignes;
        } catch (SQLException ex) {
            System.out.println("Erreur lors du chargement" + ex.getMessage());
            return null;
        }
    }

    @Override
    public String getStock() {
    
 String requete = "SELECT idArticle FROM stock\n" +
"ORDER BY idArticle DESC\n" +
"LIMIT 1;";
        try {
 PreparedStatement ps = connection.prepareStatement(requete);
             ResultSet resultat = ps.executeQuery();

            resultat.next();
               return resultat.getString(1);
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des stocks " + ex.getMessage());
            return null;
        }   
    }    
    
}