/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tnmall;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import tm.enseigne.GEnseigneController;

/**
 * FXML Controller class
 *
 * @author MANAI Yosr
 */
public class EnseigneController implements Initializable {

    /**
     * Initializes the controller class.
     */
        @FXML StackPane spMainContent;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
 
     @FXML
    private void btnEnseigneOnAction(ActionEvent event) throws IOException {
        
            GEnseigneController sc = new GEnseigneController();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/tm/enseigne/GEnseigne.fxml").openStream());
    
        AnchorPane acPane = fXMLLoader.getRoot();

       spMainContent.getChildren().clear();
        spMainContent.getChildren().add(acPane);
        
    }
    @FXML
    private void btnResponsableBoutiqueOnAction(ActionEvent event) throws IOException {
         GEnseigneController sc = new GEnseigneController();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/tm/enseigne/GResponsableBoutique.fxml").openStream());
    
        AnchorPane acPane = fXMLLoader.getRoot();

       spMainContent.getChildren().clear();
        spMainContent.getChildren().add(acPane);
    }
}
