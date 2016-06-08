/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.enseigne;



import tm.entities.Enseigne;
import tm.dao.classes.EnseigneDAO;
import tm.dao.interfaces.IEnseigneDAO;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tm.technique.MultipartUtility;
import tm.technique.Session;

/**
 * FXML Controller class
 *
 * @author Yosr
 */
public class AddEnseigneController implements Initializable {

    @FXML Label lbDescription;
    @FXML Label lbNom;
    @FXML Label lbEntreprise;
    @FXML Label lbStore;
    @FXML Label lbHeureDebut;
    @FXML Label lbHeureFin;
    @FXML Label lbLogo;
    @FXML Label lbTel;
    @FXML Label lbCategorie;
    

    
    
        @FXML Button btnClose;
        @FXML Button btnSource;
        @FXML Button btnAjouter;

        
        @FXML TextField tfNom;
        @FXML TextField tfEntreprise;
        @FXML TextField tfStore;
        @FXML TextField tfResponsable;
        @FXML TextField tfLogo;
        @FXML TextField tfTel;

        
        @FXML TextArea taDescription;
                
        @FXML ComboBox cbOuverture;
        @FXML ComboBox cbFermetture;
        @FXML ComboBox cbCategorie;

        @FXML
        private TableView<Enseigne> tblViewEnseigne;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cbCategorie.getItems().addAll("Vêtements","Lingerie","Sport","Décoration & Meuble","Electroménager");
        cbOuverture.getItems().addAll("8 H","9 H","10 H","11 H","12 H","13 H", "14 H") ;     
        cbFermetture.getItems().addAll("12 H","13 H","14 H","15 H","16 H","17 H", "18 H", "19 H","20 H", "21 H","22 H") ; 
    }    
    
    
    EnseigneDAO EnseigneDAO= new EnseigneDAO();
          
    
      
    @FXML
     private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
        
     public boolean control()
     {
         IEnseigneDAO en = new EnseigneDAO();
     boolean test=true;
        if( tfNom.getText().isEmpty())
    {
        tfNom.setStyle("-fx-border-color: red ;");
         lbNom.setText("Nom Obligatoire");
        
    test=false;}else {tfNom.setStyle("-fx-border-color: green ;");lbNom.setText("");
}
        if( tfTel.getText().isEmpty())
    {
        tfTel.setStyle("-fx-border-color: red ;");
         lbTel.setText("Téléphone Obligatoire");
        
    test=false;}
        else {tfTel.setStyle("-fx-border-color: green ;");lbTel.setText("");}
         if( tfEntreprise.getText().isEmpty())
    {
        tfEntreprise.setStyle("-fx-border-color: red ;");
         lbEntreprise.setText("Entreprise Obligatoire");
        
    test=false;}else {tfEntreprise.setStyle("-fx-border-color: green ;");lbEntreprise.setText("");
}
         if( tfStore.getText().isEmpty())
    {
        tfStore.setStyle("-fx-border-color: red ;");
         lbStore.setText("Store Obligatoire");
        
    test=false;}else { try {
       int num = Integer.parseInt(tfStore.getText());
 tfStore.setStyle("-fx-border-color: green ;");
        lbStore.setText("");
    } catch (NumberFormatException e) {
         tfStore.setStyle("-fx-border-color: red ;");
                 lbStore.setText("Veuillez saisir un NOMBRE");


    test=false;
    }}
         if(en.verifStore(tfStore.getText())) {
             tfStore.setStyle("-fx-border-color: red ;");lbStore.setText("Store Indisponible"); test=false ;
} else {
        tfStore.setStyle("-fx-border-color: red ;");lbStore.setText("");
         }
         
          if(cbOuverture.getSelectionModel().isEmpty()){
        cbOuverture.setStyle("-fx-border-color: red ;");
               lbHeureDebut.setText("Heure d'Ouverture Obligatoire");

    test=false;
    } 
    else {cbOuverture.setStyle("-fx-border-color: green ;");
                   lbHeureDebut.setText("");
}
          if(cbFermetture.getSelectionModel().isEmpty()){
        cbFermetture.setStyle("-fx-border-color: red ;");
               lbHeureFin.setText("Heure de Fermeture Obligatoire");

    test=false;
    } 
    else {cbFermetture.setStyle("-fx-border-color: green ;");
                   lbHeureFin.setText("");
}
    if(cbCategorie.getSelectionModel().isEmpty()){
        cbCategorie.setStyle("-fx-border-color: red ;");
               lbCategorie.setText("Catégorie Obligatoire");

    test=false;
    } 
    else {cbCategorie.setStyle("-fx-border-color: green ;");
                   lbCategorie.setText("");
}         
           if( taDescription.getText().isEmpty())
    {
        taDescription.setStyle("-fx-border-color: red ;");
         lbDescription.setText("Description Obligatoire");
        
    test=false;}else {taDescription.setStyle("-fx-border-color: green ;");lbDescription.setText("");
}
         if( tfLogo.getText().isEmpty())
    {
        tfLogo.setStyle("-fx-border-color: red ;");
         lbLogo.setText("Logo Obligatoire");
        
    test=false;}else {tfLogo.setStyle("-fx-border-color: green ;");lbLogo.setText("");
}
        return test ;
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
     private void btnAjouterOnAction(ActionEvent event) throws IOException {
         if (control()){
        String nom=tfNom.getText();      
        String entreprise=tfEntreprise.getText();      
        String heureOuverture=cbOuverture.getValue().toString();
        String heureFermetture=cbFermetture.getValue().toString();
        String categorie=cbCategorie.getValue().toString();
        String description=taDescription.getText();      

        
        
        List l= MultipartUtility.up(tfLogo.getText());
       
        String path =l.toString();
        path=path.substring(0,path.indexOf("[")) + path.substring(path.indexOf("[") +1);
      path=path.substring(0,path.indexOf("]")) + path.substring(path.indexOf("]") +1);
        
        Session s = Session.getInstance();

        Enseigne enseigne=new Enseigne();
        
        enseigne.setNom(nom);
        enseigne.setEntreprise(entreprise);
        enseigne.setTel(Integer.parseInt(tfTel.getText()));
        enseigne.setStore(Integer.parseInt(tfStore.getText()));
        enseigne.setHeureOuverture(heureOuverture);
        enseigne.setHeureFermetture(heureFermetture);
        enseigne.setCategorie(categorie);
        enseigne.setDescription(description);
        enseigne.setSrcLogo(path);    
        enseigne.setIdResponsableEnseigne(s.getId());
        
        IEnseigneDAO EnseigneDAO = new EnseigneDAO();
         if(EnseigneDAO.insertEnseigne(enseigne)){Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Ajout avec succes");

            alert.showAndWait();}
         else {
         Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Erreur lors de l'ajout");

            alert.showAndWait();}
        
       
        
         }         
    }
     

     
}
