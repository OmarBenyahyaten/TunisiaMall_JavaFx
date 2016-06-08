/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.dao.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import tm.dao.interfaces.IPackPublicitaireAcheteDAO;
import tm.entities.Enseigne;
import tm.entities.PackPublicitaire;
import tm.entities.PackPublicitaireAchete;
import tm.technique.DataSource;


/**
 *
 * @author Z500
 */
public class PackPublicitaireAcheteDAO implements IPackPublicitaireAcheteDAO{
 
    private Connection connection;

    public PackPublicitaireAcheteDAO() {
                        connection = DataSource.getInstance().getConnection();

    }
     @Override
    public boolean abonnerEnseigne(PackPublicitaireAchete packAchete) {
    String requete = "insert into packpublicitaireachete (idPackPublicitaire,idEnseigne) values (?,?)";
    try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, packAchete.getIdPackPublicitaire());
            ps.setInt(2,packAchete.getIdEnseigne());
            
            ps.executeUpdate();
            System.out.println("Abonnement effectué avec succès");
            return true ;
        } catch (SQLException ex) {
            System.out.println("Erreur lors de l'insertion " + ex.getMessage());
            return false ;
        }
    
    }

    
}
