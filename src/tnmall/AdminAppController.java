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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import tm.admin.GAdminController;
import tm.enseigne.GEnseigneController;
import tm.technique.Session;

/**
 * FXML Controller class
 *
 * @author omarblythe
 */


public class AdminAppController implements Initializable {

    /**
     * Initializes the controller class.
     */
            @FXML StackPane spMainContent;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void btnClientOnAction(ActionEvent event) throws IOException {
        
            GAdminController sc = new GAdminController();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/tm/admin/GAdmin.fxml").openStream());
    
        AnchorPane acPane = fXMLLoader.getRoot();

       spMainContent.getChildren().clear();
        spMainContent.getChildren().add(acPane);
    }
    
    @FXML
    private void disconnectAction(ActionEvent event) throws IOException {
        Session s=Session.getInstance();
        s.deleteInstance();
            ((Node) (event.getSource())).getScene().getWindow().hide();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();
    }
    
    
}
