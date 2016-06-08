/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.enseigne;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tm.dao.classes.EnseigneDAO;
import tm.dao.interfaces.IEnseigneDAO;
import tm.entities.Enseigne;
import tm.technique.MultipartUtility;
import tm.technique.Session;

/**
 * FXML Controller class
 *
 * @author MANAI Yosr
 */
public class UpdateEnseigneController implements Initializable {

    
        @FXML Button btnClose;
        @FXML Button btnSource;
        @FXML Button btnAjouter;

        
        @FXML TextField tfNom;
        @FXML TextField tfTel;
        @FXML TextField tfEntreprise;
        @FXML TextField tfStore;
        @FXML TextField tfResponsable;
        @FXML TextField tfLogo;
        @FXML TextField tfidEnseigne;

        
        @FXML TextArea taDescription;
        
        @FXML ComboBox cbOuverture;
        @FXML ComboBox cbFermetture;
        @FXML ComboBox cbCategorie;

    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
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
     private void btnSourceOnAction(ActionEvent event) {
        Stage stage = (Stage) btnSource.getScene().getWindow();
        FileChooser filechooser= new FileChooser();
        filechooser.setTitle("Ouvrir la Source de l'Image");
        filechooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
        File file=filechooser.showOpenDialog(stage);
        tfLogo.setText(file.toString());
        
        
        
    }
     
     
     @FXML
     private void btnModifierOnAction(ActionEvent event) throws IOException {
         
        
         
                 int idEnseigne=Integer.parseInt(tfidEnseigne.getText());

        int tel=Integer.parseInt(tfTel.getText());
        String nom=tfNom.getText();      
        String entreprise=tfEntreprise.getText();      
        String heureOuverture=cbOuverture.getValue().toString();
        String heureFermetture=cbFermetture.getValue().toString();
        String categorie=cbCategorie.getValue().toString();
        String description=taDescription.getText();      

        
        
        Session s = Session.getInstance();

        Enseigne enseigne=new Enseigne();
                enseigne.setIdEnseigne(idEnseigne);

        enseigne.setTel(tel);
        enseigne.setNom(nom);
        enseigne.setEntreprise(entreprise);
        enseigne.setStore(Integer.parseInt(tfStore.getText()));
        enseigne.setHeureOuverture(heureOuverture);
        enseigne.setHeureFermetture(heureFermetture);
        enseigne.setDescription(description);
        enseigne.setCategorie(categorie);
                enseigne.setSrcLogo(tfLogo.getText());    

        IEnseigneDAO EnseigneDAO = new EnseigneDAO();
 if(EnseigneDAO.updateEnseigne(enseigne)){Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Modification avec succes");

            alert.showAndWait();}
         else {
         Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Erreur lors de modification");

            alert.showAndWait();}
        
       
        
                
    }        
               
                
    
}
