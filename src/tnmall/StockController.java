/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tnmall;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tm.dao.classes.UserDAO;
import tm.dao.interfaces.IUserDAO;
import tm.enseigne.GEnseigneController;
import tm.entities.User;
import tm.mail.passController;
import tm.mail.sendMailController;
import tm.stock.GStockController;
import tm.stock.GventeController;
import tm.technique.Session;

/**
 * FXML Controller class
 *
 * @author omarblythe
 */

public class StockController implements Initializable {
    @FXML
    private ToggleButton btnUsername;
@FXML
    private ToggleButton btnsend;
 @FXML
    private ImageView profilImg;
@FXML
    private ToggleButton btnVente;

    @FXML
    private ToggleButton btnFacture;
    
    @FXML
    private ToggleButton btnStock;
    /**
     * Initializes the controller class.
     */
    
    @FXML StackPane spMainContent;
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Session s=Session.getInstance();
        btnUsername.setText(s.getUsername());
        IUserDAO dao=new UserDAO();
        User u=dao.findUserByUsername(s.getUsername());
        String saveDir = System.getProperty("user.dir");
        
       System.out.println(saveDir+"\\img\\");
        File ff=new File("src/images/"+u.getPath());
                                Image profil = null;
        try {
            profil = new Image(ff.toURI().toURL().toExternalForm());
        } catch (MalformedURLException ex) {
            Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
        }
                                
        
        profilImg.setImage(profil);  
    }     
    @FXML
    private void btnStockOnAction(ActionEvent event) throws IOException {
        
        btnStock.setSelected(true);
        btnFacture.setSelected(false);
        btnVente.setSelected(false);
            GStockController sc = new GStockController();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/tm/stock/GStock.fxml").openStream());
    
        AnchorPane acPane = fXMLLoader.getRoot();

       spMainContent.getChildren().clear();
        spMainContent.getChildren().add(acPane);
    }
    @FXML
    private void btnVenteOnAction(ActionEvent event) throws IOException {
         btnStock.setSelected(false);
        btnFacture.setSelected(false);
        btnVente.setSelected(true);
        
            GventeController sc = new GventeController();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/tm/stock/Gvente.fxml").openStream());
    
        AnchorPane acPane = fXMLLoader.getRoot();

       spMainContent.getChildren().clear();
        spMainContent.getChildren().add(acPane);
    }
    @FXML
    private void btnFactureOnAction(ActionEvent event) throws IOException {
        
         btnStock.setSelected(false);
        btnFacture.setSelected(true);
        btnVente.setSelected(false);
            GStockController sc = new GStockController();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/tm/stock/GFacture.fxml").openStream());
    
        AnchorPane acPane = fXMLLoader.getRoot();

       spMainContent.getChildren().clear();
        spMainContent.getChildren().add(acPane);
    }
    
    
     @FXML
   private void profilAction(ActionEvent event) throws IOException {
       FXMLLoader fxmlLoader = new FXMLLoader();
        
          passController apc = new passController();
            GEnseigneController sc = new GEnseigneController();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/tm/mail/pass.fxml").openStream());
    
        AnchorPane acPane = fXMLLoader.getRoot();

       spMainContent.getChildren().clear();
        spMainContent.getChildren().add(acPane);
     }
//     @FXML
//    private void profilAction(ActionEvent event) throws IOException {
//       FXMLLoader fxmlLoader = new FXMLLoader();
//        
//          sendMailController apc = new sendMailController();
//            GEnseigneController sc = new GEnseigneController();
//        FXMLLoader fXMLLoader = new FXMLLoader();
//        fXMLLoader.load(getClass().getResource("/tm/mail/mail.fxml").openStream());
//    
//        AnchorPane acPane = fXMLLoader.getRoot();
//
//       spMainContent.getChildren().clear();
//        spMainContent.getChildren().add(acPane);
//        
//       
//    }
    
    @FXML
    private void sendAction(ActionEvent event) throws IOException {
         sendMailController apc = new sendMailController();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/tm/mail/sendMail.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            sendMailController signUpController = fxmlLoader.getController();
            Stage nStage = new Stage();
//            addProductController.addSupplyerStage(nStage);
            scene.setFill(Color.TRANSPARENT);
            nStage.setScene(scene);
            nStage.initModality(Modality.APPLICATION_MODAL);

            nStage.initStyle(StageStyle.TRANSPARENT);
            nStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
