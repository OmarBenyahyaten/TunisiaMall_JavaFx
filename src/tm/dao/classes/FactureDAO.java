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
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tm.dao.interfaces.IFactureDAO;
import tm.entities.Facture;
import tm.entities.Stock;
import tm.technique.DataSource;

/**
 *
 * @author omarblythe
 */
public class FactureDAO implements IFactureDAO{
    private Connection connection;

    public FactureDAO() {
                        connection = DataSource.getInstance().getConnection();

    }

    @Override
    public void insertFacture(Facture facture) {
  String requete = "INSERT INTO `facture` (`idFacture`, `prixTotal`, `dateAchat`) VALUES (NULL, ?, ?);";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setFloat(1, facture.getPrixTotal());
            ps.setString(2, facture.getDateAchat());
          

            
            ps.executeUpdate();
            System.out.println("Ajout effectué avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'insertion " + ex.getMessage());
        }
    }

    @Override
    public void updateFacture(Facture facture) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteFacture(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Facture> DisplayAllCarteFacture() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getFacture() {
 String requete = "SELECT idFacture FROM facture\n" +
"ORDER BY idFacture DESC\n" +
"LIMIT 1;";
        try {
 PreparedStatement ps = connection.prepareStatement(requete);
             ResultSet resultat = ps.executeQuery();

            resultat.next();
               return resultat.getInt(1);
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des stocks " + ex.getMessage());
            return -1;
        }   
    }
    
    @Override
    public ObservableList<Facture> DisplayFacture() {
        ObservableList<Facture> facture =  FXCollections.observableArrayList();
        String requete = "select * from facture";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);
            while (resultat.next()) {
                Facture s = new Facture();
                s.setIdFacture(resultat.getInt(1));
                s.setPrixTotal(resultat.getFloat(2));               
               s.setDateAchat(resultat.getString(3));



                facture.add(s);
            }
                                        return facture;

        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des stocks " + ex.getMessage());
            return null;
        }
    
}
}
