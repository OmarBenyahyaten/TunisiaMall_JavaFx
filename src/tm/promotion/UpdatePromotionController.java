/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.promotion;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tm.dao.classes.PromotionDAO;
import tm.dao.interfaces.IPromotionDAO;
import tm.entities.Promotion;
import tm.technique.Session;
import java.sql.Date.*;

/**
 * FXML Controller class
 *
 * @author Z500
 */
public class UpdatePromotionController implements Initializable {

    
        

    
    @FXML Button btnAjouter;
        @FXML Button btnClose;

        @FXML TextField tfId;      
        @FXML TextField tfNom;
        @FXML TextField tfSlogan;
        @FXML DatePicker tfDebut;
        @FXML DatePicker tfFin;
        

        
        @FXML TextArea taDescription;
        

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
     private void btnModifierOnAction(ActionEvent event) {
         
         
         
        int idPromotion=Integer.parseInt(tfId.getText());
        String nom=tfNom.getText(); 
        String slogan=tfSlogan.getText();
        Date debut=(java.sql.Date.valueOf(tfDebut.getValue())); 
        Date dateFin=(java.sql.Date.valueOf(tfFin.getValue()));
        String description=taDescription.getText();      

        
        
        Session s = Session.getInstance();

        Promotion promotion=new Promotion();
        
        promotion.setIdPromotion(idPromotion);
        promotion.setNom(nom);
        promotion.setSlogan(slogan);
       promotion.setDateDebut(java.sql.Date.valueOf(tfDebut.getValue()));
        promotion.setDateFin(java.sql.Date.valueOf(tfDebut.getValue()));
        promotion.setDescription(description);
        
        IPromotionDAO PromotionDAO = new PromotionDAO();
        PromotionDAO.updatePromotion(promotion);
               
                
    }
}
