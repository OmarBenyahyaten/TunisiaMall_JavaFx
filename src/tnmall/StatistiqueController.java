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
 * @author omarblythe
 */
public class StatistiqueController implements Initializable {

    /**
     * Initializes the controller class.
     */
            @FXML StackPane spMainContent;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
     @FXML
    private void btnTauxOnAction(ActionEvent event) throws IOException {
        
            GEnseigneController sc = new GEnseigneController();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/tm/stat/StatTauxVente.fxml").openStream());
    
        StackPane acPane = fXMLLoader.getRoot();

       spMainContent.getChildren().clear();
        spMainContent.getChildren().add(acPane);
        
    }
     @FXML
    private void btnNouvelEnseigneOnAction(ActionEvent event) throws IOException {
        
            GEnseigneController sc = new GEnseigneController();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/tm/stat/StatNouvelEnseigne.fxml").openStream());
    
        AnchorPane acPane = fXMLLoader.getRoot();

       spMainContent.getChildren().clear();
        spMainContent.getChildren().add(acPane);
        
    }
     @FXML
    private void btnClientOnAction(ActionEvent event) throws IOException {
        
            GEnseigneController sc = new GEnseigneController();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/tm/stat/StatClientFidel.fxml").openStream());
    
        AnchorPane acPane = fXMLLoader.getRoot();

       spMainContent.getChildren().clear();
        spMainContent.getChildren().add(acPane);
        
    }
    
}
