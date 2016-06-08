/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.promotion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextAreaBuilder;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tm.dao.classes.PromotionDAO;
import tm.dao.interfaces.IPromotionDAO;
import tm.entities.Promotion;
import tm.technique.Session;

/**
 * FXML Controller class
 *
 * @author omarblythe
 */

public class AddPromotionController implements Initializable {

    /**
     * Initializes the controller class.
     */
         
        @FXML Button btnAjouter;
        @FXML Button btnClose;
        @FXML Button desc;

        
        @FXML TextField tfNom;
        @FXML TextField tfSlogan;
        @FXML DatePicker tfDebut;
        @FXML DatePicker tfFin;
        
        

        
        @FXML TextArea taDescription;
        @FXML TextArea tafichier;
        

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tfDebut.setValue(LocalDate.of(2016, 02, 12));
        tfFin.setValue(LocalDate.of(2016, 02, 12));
    }
    @FXML
    public void btnsaveOnAction()
    {
     Stage primaryStage = new Stage();
         Group root = new Group();
         Button buttonSave = new Button("Save");
          buttonSave.setOnAction((ActionEvent event) -> {
            FileChooser fileChooser = new FileChooser();
             
            //Set extension filter
            FileChooser.ExtensionFilter extFilter = 
                new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);
             
            //Show save file dialog
            File file = fileChooser.showSaveDialog(primaryStage);
             
            if(file != null){
                SaveFile(tafichier.getText(), file);
            }
        });
         
        VBox vBox = new VBox();
        vBox.getChildren().addAll(tafichier, buttonSave);
         
        root.getChildren().add(vBox);
         
        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.show();
    }
    
 
    
private void SaveFile(String content, File file){
        try {
            FileWriter fileWriter;
              
            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(AddPromotionController.class
                .getName()).log(Level.SEVERE, null, ex);
        }
          
    }
    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
     @FXML
     private void btnAjouterOnAction(ActionEvent event) {
         
        String nom=tfNom.getText();      
        String slogan=tfSlogan.getText();      
        Date debut=(Date.valueOf(tfDebut.getValue()));  
        Date fin=(Date.valueOf(tfFin.getValue())); 
        String description=taDescription.getText();      
        
            

        
        
        Session s = Session.getInstance();

        Promotion promotion=new Promotion();
        
        promotion.setNom(nom);
        promotion.setSlogan(slogan);
       promotion.setDateDebut(Date.valueOf(tfDebut.getValue()));
       promotion.setDateFin(Date.valueOf(tfFin.getValue()));
       promotion.setDescription(description);
        
        IPromotionDAO PromotionDAO = new PromotionDAO();
        PromotionDAO.insertPromotion(promotion);
        
                
    }
    
     
    
        
     
}
