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
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import tm.promotion.GPromotionController;

/**
 * FXML Controller class
 *
 * @author omarblythe
 */
public class ChatController implements Initializable {

    @FXML
    private ToggleButton btnRepoerts;

    @FXML
    private AnchorPane acHeadStore;

    @FXML
    private ToggleButton btnChat;

    @FXML
    private StackPane spMainContent;

    @FXML
    private BorderPane bpStore;

    @FXML
    private Label lblHeader;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    @FXML
    void btnChat(ActionEvent event) throws IOException {
  GPromotionController sc = new GPromotionController();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/tm/chat/GChat.fxml").openStream());
    
        AnchorPane acPane = fXMLLoader.getRoot();

       spMainContent.getChildren().clear();
        spMainContent.getChildren().add(acPane);
    }

    
}
