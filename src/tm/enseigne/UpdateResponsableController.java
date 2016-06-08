/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.enseigne;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tm.dao.classes.EnseigneDAO;
import tm.dao.classes.ResponsableBoutiqueDAO;
import tm.dao.interfaces.IEnseigneDAO;
import tm.dao.interfaces.IResponsableBoutiqueDAO;
import tm.entities.Enseigne;
import tm.entities.ResponsableBoutique;
import tm.technique.Session;

/**
 * FXML Controller class
 *
 * @author MANAI Yosr
 */
public class UpdateResponsableController implements Initializable {

    
      
    @FXML
     TextField tfLogin;

    @FXML
     TextField tfPrenom;

    @FXML
     TextArea taAdresse;

    @FXML
     TextField tfTelephone;

    @FXML
     TextField tfNom;

    @FXML
     TextField tfPwd;

    @FXML
     TextField tfEmail;

    @FXML
     TextField tfid;
    
    
    @FXML
     ComboBox<String> cbDebut;
    @FXML
     ComboBox<String> cbFin;
    
    

    @FXML
    private Button btnModifier;

    @FXML
    private Button btnClose;
@FXML  ComboBox<String> cbEnseigne;

    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbDebut.getItems().addAll("8 H","9 H","10 H","11 H","12 H","13 H", "14 H") ;     
        cbFin.getItems().addAll("12 H","13 H","14 H","15 H","16 H","17 H", "18 H", "19 H","20 H", "21 H","22 H") ;  
    }
    
        ResponsableBoutiqueDAO RespBoutiqueDAO= new ResponsableBoutiqueDAO();

        
    
    @FXML
     private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
    
     
    @FXML
     private void cbOuvertureOnAction(ActionEvent event) {
        final ComboBox<Enseigne> comboBox = new ComboBox(); 
        comboBox.getItems().setAll();
    } 
    
     @FXML
     private void cbFermettureOnAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    } 
     
     @FXML
     private void cbResponsableOnAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    } 
     
    
     
     @FXML
     private void btnModifierOnAction(ActionEvent event) {
         
         
         FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/tm/enseigne/GResponsableBoutique.fxml"));
        
        int id=Integer.parseInt(tfid.getText());
        String nom=tfNom.getText();      
        String prenom=tfPrenom.getText();      
        String heureDebut=cbDebut.getValue().toString();
        String heureFin=cbFin.getValue().toString();
        String adresse=taAdresse.getText();      
        Integer telephone=Integer.parseInt(tfTelephone.getText());
        
        EnseigneDAO enseigneDAO =new EnseigneDAO();
        
        
        Session s = Session.getInstance();

        ResponsableBoutique respBoutique=new ResponsableBoutique();
        
        respBoutique.setId(id);
        respBoutique.setNom(nom);
        respBoutique.setPrenom(prenom);
        respBoutique.setAdresse(adresse);
        respBoutique.setHeureDebut(heureDebut);
        respBoutique.setHeureFin(heureFin);
        respBoutique.setTel(telephone);
        respBoutique.setEnseigne(cbEnseigne.getSelectionModel().getSelectedItem().toString());
 
        IResponsableBoutiqueDAO responsableBoutiqueDAO = new ResponsableBoutiqueDAO();
        responsableBoutiqueDAO.updateResponsableBoutique(respBoutique);
         
        Stage stage = (Stage) btnModifier.getScene().getWindow();
        stage.close();      
               
                
    }
     
         
     @FXML
    public void cbSoteViewNomOnClick(Event event) {
        cbEnseigne.getItems().clear();
        cbEnseigne.getItems().removeAll();
        cbEnseigne.setPromptText("Enseignes à Affecter");
        IEnseigneDAO enseigneDAO = new EnseigneDAO();
   
        
        List<String> listeNom = new ArrayList<>();
        listeNom = enseigneDAO.DisplayCBNom();
                    
            for (String type : listeNom) {
               cbEnseigne.getItems().addAll(type);
               
               
            }  

                                
    }
   
   
}
