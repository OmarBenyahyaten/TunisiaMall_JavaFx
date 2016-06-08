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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sun.security.jca.GetInstance;
import tm.dao.interfaces.IEnseigneDAO;
import tm.entities.Enseigne;
import tm.technique.DataSource;
import tm.technique.Session;

/**
 *
 * @author MANAI Yosr
 */
public class EnseigneDAO implements IEnseigneDAO{
    private Connection connection;

    public EnseigneDAO() {
                        connection = DataSource.getInstance().getConnection();

    }

   
   @Override
    public boolean insertEnseigne(Enseigne ens) {

        String requete = "insert into enseigne (nom,entreprise,store,tel,heureOuverture,heureFermetture,categorie,description,srcLogo, idResponsableEnseigne,dateCreationEnseigne) values (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, ens.getNom());
            ps.setString(2,ens.getEntreprise());
            ps.setInt(3, ens.getStore());
            ps.setInt(4, ens.getTel());
            ps.setString(5, ens.getHeureOuverture());
            ps.setString(6, ens.getHeureFermetture());
            ps.setString(7, ens.getCategorie());
            ps.setString(8, ens.getDescription());
            ps.setString(9, ens.getSrcLogo());
            ps.setInt(10, ens.getIdResponsableEnseigne());
            ps.setString(11, new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime()));
            ps.executeUpdate();
            System.out.println("Ajout effectué avec succès");
            return true;
        } catch (SQLException ex) {
            System.out.println("Erreur lors de l'insertion " + ex.getMessage());
            return false ;
        }
    }

   @Override
    public boolean updateEnseigne(Enseigne ens) {
        String requete = "update enseigne set nom=?, entreprise=?, store=?, tel=?,  heureOuverture=?, heureFermetture=?, categorie=?, description=? , srcLogo=? where idEnseigne=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, ens.getNom());
            ps.setString(2, ens.getEntreprise());
            ps.setInt(3, ens.getStore());
            ps.setInt(4, ens.getTel());
            ps.setString(5, ens.getHeureOuverture());
            ps.setString(6, ens.getHeureFermetture());
            ps.setString(7, ens.getCategorie());
            ps.setString(8, ens.getDescription());
            ps.setString(9, ens.getSrcLogo());
            ps.setInt(10, ens.getIdEnseigne());
            
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
            return true ;
        } catch (SQLException ex) {
            
            System.out.println("Erreur lors de la mise à jour " + ex.getMessage());
            return false ;
        }
    }


    @Override
    public void deleteEnseigne(int id) {

        String requete = "delete from enseigne where idEnseigne=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la suppression " + ex.getMessage());
        }
    }

    

    @Override
    public ObservableList<Enseigne> DisplayAllEnseignes() {

        ObservableList<Enseigne> listeEnseignes = FXCollections.observableArrayList();
        
        Session s = Session.getInstance();

        String requete = "select * from enseigne where idResponsableEnseigne=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, s.getId());
            
            ResultSet resultat = ps.executeQuery();
            IEnseigneDAO enseigneDAO = new EnseigneDAO();
            while (resultat.next()) {
                Enseigne enseigne = new Enseigne();
                enseigne.setIdEnseigne(resultat.getInt(1));
                enseigne.setNom(resultat.getString(2));
                enseigne.setEntreprise(resultat.getString(3));
                enseigne.setStore(resultat.getInt(4));
                enseigne.setTel(resultat.getInt(5));
                enseigne.setHeureOuverture(resultat.getString(6));
                enseigne.setHeureFermetture(resultat.getString(7));
                enseigne.setCategorie(resultat.getString(8));
                enseigne.setDescription(resultat.getString(9));
                enseigne.setSrcLogo(resultat.getString(10));
                enseigne.setIdResponsableEnseigne(s.getId());

                listeEnseignes.add(enseigne);
            }
            return listeEnseignes;
        } catch (SQLException ex) {
            System.out.println("Erreur lors du chargement des stocks " + ex.getMessage());
            return null;
        }
    }

    
    
    @Override
    public ObservableList<Enseigne> findEnseigne(String search) {

        
               
        Session s = Session.getInstance();

        
        try {        
            String requete = "select * from enseigne where idResponsableEnseigne= ? and (nom like '%"+search+"%' or store like '%"+search+"%'  or entreprise like '%"+search+"%' or heureOuverture like '%"+search+"%' or heureFermetture like '%"+search+"%' or description like '%"+search+"%')";


            ObservableList<Enseigne> listEnseignes = FXCollections.observableArrayList();  
            
            
            //ResultSet resultat = statement.executeQuery(requete);
            
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, s.getId());
            
            ResultSet resultat = ps.executeQuery();
            
            
            
            
            
            // ResultSet resultat =connection.createStatement().executeQuery(requete);
            while (resultat.next()) {

                Enseigne enseigne = new Enseigne();


                 enseigne.setIdEnseigne(resultat.getInt(1));
                enseigne.setNom(resultat.getString(2));
                enseigne.setEntreprise(resultat.getString(3));
                enseigne.setStore(resultat.getInt(4));
                enseigne.setTel(resultat.getInt(5));
                enseigne.setHeureOuverture(resultat.getString(6));
                enseigne.setHeureFermetture(resultat.getString(7));
                enseigne.setCategorie(resultat.getString(8));
                enseigne.setDescription(resultat.getString(9));
                enseigne.setSrcLogo(resultat.getString(10));
                enseigne.setIdResponsableEnseigne(s.getId());
                listEnseignes.add(enseigne);
            }
            return listEnseignes;
        } catch (SQLException ex) {
            System.out.println("Erreur lors du chargement" + ex.getMessage());
            return null;
        }
    }
    
    
    
    @Override
    public ObservableList<Enseigne> findEnseigneByNom(String nom) {
        
        Session s = Session.getInstance();

        
        
     ObservableList<Enseigne> listEnseignes = FXCollections.observableArrayList();    

        try {
                
            String requete = "select * from enseigne where idResponsableEnseigne=? and nom=?";

            PreparedStatement ps = connection.prepareStatement(requete);
            
            ps.setInt(1, s.getId());

            ps.setString(2, nom);

            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
            
                Enseigne enseigne = new Enseigne();

                enseigne.setIdEnseigne(resultat.getInt(1));
                enseigne.setNom(resultat.getString(2));
                enseigne.setEntreprise(resultat.getString(3));
                enseigne.setStore(resultat.getInt(4));
                enseigne.setTel(resultat.getInt(5));
                enseigne.setHeureOuverture(resultat.getString(6));
                enseigne.setHeureFermetture(resultat.getString(7));
                enseigne.setCategorie(resultat.getString(8));
                enseigne.setDescription(resultat.getString(9));
                enseigne.setSrcLogo(resultat.getString(10));
                enseigne.setIdResponsableEnseigne(s.getId());
                listEnseignes.add(enseigne);
            }
            return listEnseignes;
        } catch (SQLException ex) {
            System.out.println("Erreur lors du chargement" + ex.getMessage());
            return null;
        }
    }
    
    
    @Override
    public ObservableList<Enseigne> findEnseigneByEntreprise(String entreprise) {
        Session s = Session.getInstance();

        try {

            String requete = "select * from enseigne where entreprise=? and idResponsableEnseigne=?";

            ObservableList<Enseigne> listEnseignes = FXCollections.observableArrayList();    

            PreparedStatement ps = connection.prepareStatement(requete);
                        ps.setString(1, entreprise);
                                                ps.setInt(2, s.getId());


            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
            
                Enseigne enseigne = new Enseigne();

                 enseigne.setIdEnseigne(resultat.getInt(1));
                enseigne.setNom(resultat.getString(2));
                enseigne.setEntreprise(resultat.getString(3));
                enseigne.setStore(resultat.getInt(4));
                enseigne.setTel(resultat.getInt(5));
                enseigne.setHeureOuverture(resultat.getString(6));
                enseigne.setHeureFermetture(resultat.getString(7));
                enseigne.setCategorie(resultat.getString(8));
                enseigne.setDescription(resultat.getString(9));
                enseigne.setSrcLogo(resultat.getString(10));
                enseigne.setIdResponsableEnseigne(s.getId());
               listEnseignes.add(enseigne);
            }
            return listEnseignes;
        } catch (SQLException ex) {
            System.out.println("Erreur lors du chargement" + ex.getMessage());
            return null;
        }
    }
    
    @Override
    public ObservableList<Enseigne> findEnseigneByHeureOuverture(String ho) {
        Session s = Session.getInstance();

        try {
                  
            String requete = "select * from enseigne where heureOuverture=? and idResponsableEnseigne=?";

            ObservableList<Enseigne> listEnseignes = FXCollections.observableArrayList();    

            PreparedStatement ps = connection.prepareStatement(requete);
                   
            ps.setString(1, ho);
            
            ps.setInt(2, s.getId());


            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
            
                Enseigne enseigne = new Enseigne();

                enseigne.setIdEnseigne(resultat.getInt(1));
                enseigne.setNom(resultat.getString(2));
                enseigne.setEntreprise(resultat.getString(3));
                enseigne.setStore(resultat.getInt(4));
                enseigne.setTel(resultat.getInt(5));
                enseigne.setHeureOuverture(resultat.getString(6));
                enseigne.setHeureFermetture(resultat.getString(7));
                enseigne.setCategorie(resultat.getString(8));
                enseigne.setDescription(resultat.getString(9));
                enseigne.setSrcLogo(resultat.getString(10));
                enseigne.setIdResponsableEnseigne(s.getId());
                listEnseignes.add(enseigne);
            }
            return listEnseignes;
        } catch (SQLException ex) {
            System.out.println("Erreur lors du chargement" + ex.getMessage());
            return null;
        }
    }
    
    @Override
    public ObservableList<Enseigne> findEnseigneByHeureFermetture(String hf) {
        Session s = Session.getInstance();

        try {        
            String requete = "select * from enseigne where heureFermetture=? and idResponsableEnseigne=?";


            ObservableList<Enseigne> listEnseignes = FXCollections.observableArrayList();    

            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, hf);
            
            ps.setInt(2, s.getId());

            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {

                Enseigne enseigne = new Enseigne();


                 enseigne.setIdEnseigne(resultat.getInt(1));
                enseigne.setNom(resultat.getString(2));
                enseigne.setEntreprise(resultat.getString(3));
                enseigne.setStore(resultat.getInt(4));
                enseigne.setTel(resultat.getInt(5));
                enseigne.setHeureOuverture(resultat.getString(6));
                enseigne.setHeureFermetture(resultat.getString(7));
                enseigne.setCategorie(resultat.getString(8));
                enseigne.setDescription(resultat.getString(9));
                enseigne.setSrcLogo(resultat.getString(10));
                enseigne.setIdResponsableEnseigne(s.getId());
                listEnseignes.add(enseigne);
            }
            return listEnseignes;
        } catch (SQLException ex) {
            System.out.println("Erreur lors du chargement" + ex.getMessage());
            return null;
        }
    }
    
    @Override
    public ObservableList<Enseigne> findEnseigneByStore(String store) {
        Session s = Session.getInstance();

        try {

            String requete = "select * from enseigne where store=? and idResponsableEnseigne=?";

            
            ObservableList<Enseigne> listEnseignes = FXCollections.observableArrayList();    

            PreparedStatement ps = connection.prepareStatement(requete);
            
            ps.setString(1, store);
            
            ps.setInt(2, s.getId());

            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
            
                Enseigne enseigne = new Enseigne();

                 enseigne.setIdEnseigne(resultat.getInt(1));
                enseigne.setNom(resultat.getString(2));
                enseigne.setEntreprise(resultat.getString(3));
                enseigne.setStore(resultat.getInt(4));
                enseigne.setTel(resultat.getInt(5));
                enseigne.setHeureOuverture(resultat.getString(6));
                enseigne.setHeureFermetture(resultat.getString(7));
                enseigne.setCategorie(resultat.getString(8));
                enseigne.setDescription(resultat.getString(9));
                enseigne.setSrcLogo(resultat.getString(10));
                enseigne.setIdResponsableEnseigne(s.getId());
                listEnseignes.add(enseigne);
            }
            return listEnseignes;
        } catch (SQLException ex) {
            System.out.println("Erreur lors du chargement" + ex.getMessage());
            return null;
        }
    }
    
    

    @Override
    public List<String> getListEnseigneByID(int id) {
 List<String> listeEnseigne = new ArrayList<>();
        String requete = "select nom from enseigne where idResponsableEnseigne=?";
        try {
 PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1,id);
            
            
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {
                String type ;
                type=resultat.getString(1);
                listeEnseigne.add(type);
            }
            return listeEnseigne;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des stocks " + ex.getMessage());
            return null;
        }
    }

    @Override
    public int getIDbyEnseigne(String enseigne) {
        
        String requete = "select idEnseigne from enseigne where nom LIKE ?";
        try {
 PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1,enseigne);
             ResultSet resultat = ps.executeQuery();

            resultat.next();
               return resultat.getInt(1);
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des stocks " + ex.getMessage());
            return -1;
        }
        
    }

    @Override
    public List<String> DisplayCBNom() {

        Session s=Session.getInstance();
        List<String> listeNom = new ArrayList<>();
        String requete = "select distinct nom from enseigne where idResponsableEnseigne=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, s.getId());
            
            ResultSet resultat = ps.executeQuery();
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
    public List<String> DisplayCBEntreprise() {
        List<String> listeEntreprise = new ArrayList<>();
        Session s=Session.getInstance();
        String requete = "select distinct entreprise from enseigne where idResponsableEnseigne=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, s.getId());
            
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                String enseigne ;
                enseigne=resultat.getString(1);
                listeEntreprise.add(enseigne);
            }
            return listeEntreprise;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des enseignes " + ex.getMessage());
            return null;
        }
    }

    @Override
    public List<String> DisplayCBStore() {
        List<String> listeStore = new ArrayList<>();
        Session s=Session.getInstance();
        String requete = "select distinct store from enseigne where idResponsableEnseigne=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, s.getId());
            
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                String enseigne ;
                enseigne=resultat.getString(1);
                listeStore.add(enseigne);
            }
            return listeStore;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des enseignes " + ex.getMessage());
            return null;
        }
    }

    @Override
    public List<String> DisplayCBOuverture() {
        List<String> listeOuverture = new ArrayList<>();
        Session s= Session.getInstance();
        String requete = "select distinct heureOuverture from enseigne where idResponsableEnseigne=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, s.getId());
            
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                String enseigne ;
                enseigne=resultat.getString(1);
                listeOuverture.add(enseigne);
            }
            return listeOuverture;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des enseignes " + ex.getMessage());
            return null;
        }
    }

    @Override
    public List<String> DisplayCBFermetture() {
        List<String> listeFermetture = new ArrayList<>();
        Session s =Session.getInstance();
        String requete = "select distinct heureFermetture from enseigne where idResponsableEnseigne=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, s.getId());
            
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                String enseigne ;
                enseigne=resultat.getString(1);
                listeFermetture.add(enseigne);
            }
            return listeFermetture;
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des enseignes " + ex.getMessage());
            return null;
        }
    }

    @Override
    public boolean verifStore(String text) {
        

        String requete = "select * from enseigne where store=?;";
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
            System.out.println("Erreur lors du chargement des stocks " + ex.getMessage());
            return false;
        }
    }    
    
    @Override
    public Enseigne findByNom(String nom) {
        
        Session s = Session.getInstance();

        
        
     Enseigne enseigne=new Enseigne();

        try {
                
            String requete = "select * from enseigne where nom=?";

            PreparedStatement ps = connection.prepareStatement(requete);
            
          

            ps.setString(1, nom);

            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
            

                enseigne.setIdEnseigne(resultat.getInt(1));
                enseigne.setNom(resultat.getString(2));
                enseigne.setEntreprise(resultat.getString(3));
                enseigne.setStore(resultat.getInt(4));
                enseigne.setTel(resultat.getInt(5));
                enseigne.setHeureOuverture(resultat.getString(6));
                enseigne.setHeureFermetture(resultat.getString(7));
                enseigne.setCategorie(resultat.getString(8));
                enseigne.setDescription(resultat.getString(9));
                enseigne.setSrcLogo(resultat.getString(10));
                enseigne.setIdResponsableEnseigne(s.getId());
                
            }
            return enseigne;
        } catch (SQLException ex) {
            System.out.println("Erreur lors du chargement" + ex.getMessage());
            return null;
        }
    }
    
       
}
