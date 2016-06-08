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
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tm.dao.interfaces.IVenteDAO;
import tm.entities.Stock;
import tm.entities.Vente;
import tm.technique.DataSource;

/**
 *
 * @author omarblythe
 */
public class VenteDAO implements IVenteDAO{
    private Connection connection;

    public VenteDAO() {
                        connection = DataSource.getInstance().getConnection();

    }

    @Override
    public void insertVente(ObservableList<Vente> ventes) {
        
    String requete = "INSERT INTO `vente` (`idVente`, `idArticle`, `nomArticle`, `prixUnitaire`, `tva`, `quantite`, `totalTVA`, `remise`, `totalRemise`, `TotalHT`, `TotalTTC`, `idFacture`) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            
            PreparedStatement ps = connection.prepareStatement(requete);
            for (Vente v : ventes){
            ps.setInt(1, v.getIdArticle());
            ps.setString(2, v.getNomArticle());
            ps.setFloat(3, v.getPrixUnitaire());
            ps.setInt(4, v.getTva());
            ps.setInt(5, v.getQuantite());
            ps.setFloat(6, v.getTotalTVA());
            ps.setFloat(7, v.getRemise());
            ps.setFloat(8, v.getTotalRemise());
            ps.setFloat(9, v.getTotalHT());
           ps.setFloat(10, v.getTotalTTC());
            ps.setInt(11, v.getIdFacture());
                        System.out.println(ps.toString());

            ps.executeUpdate();}
            System.out.println("Ajout effectué avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'insertion " + ex.getMessage());
        }
    }

    @Override
    public void updateVente(Vente vente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteVente(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Vente> DisplayAllVente() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<Vente> DisplayVenteByFacture(int id) {
ObservableList<Vente> vente =  FXCollections.observableArrayList();
        String requete = "select * from vente where idFacture='"+id+"';";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);
            while (resultat.next()) {
                Vente s = new Vente();
                s.setIdArticle(resultat.getInt(2));
                s.setNomArticle(resultat.getString(3));
                s.setPrixUnitaire(resultat.getFloat(4));
                s.setTva(resultat.getInt(5));
                s.setQuantite(resultat.getInt(6));
                s.setTotalTVA(resultat.getFloat(7));
                s.setRemise(resultat.getFloat(8));
                s.setTotalRemise(resultat.getFloat(9));
               s.setTotalHT(resultat.getFloat(10));
               s.setTotalTTC(resultat.getFloat(11));


                vente.add(s);

            }
                                        return vente;

        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des stocks " + ex.getMessage());
            return null;
        }

}   

}
    

