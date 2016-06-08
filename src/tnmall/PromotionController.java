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
import tm.article_promotion.GArticlePromotionController;
import tm.promotion.GPromotionController;

/**
 * FXML Controller class
 *
 * @author omarblythe
 */
public class PromotionController implements Initializable {

    /**
     * Initializes the controller class.
     */
            @FXML StackPane spMainContent;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
       @FXML
    private void btnPromotionOnAction(ActionEvent event) throws IOException {
        
            GPromotionController sc = new GPromotionController();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/tm/promotion/GPromotion.fxml").openStream());
    
        AnchorPane acPane = fXMLLoader.getRoot();

       spMainContent.getChildren().clear();
        spMainContent.getChildren().add(acPane);
    }
    
          @FXML
    private void btnArticlePromotionOnAction(ActionEvent event) throws IOException {
        
              GArticlePromotionController sc = new GArticlePromotionController();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/tm/article_promotion/GArticlePromotion.fxml").openStream());
    
        AnchorPane acPane = fXMLLoader.getRoot();

       spMainContent.getChildren().clear();
        spMainContent.getChildren().add(acPane);
    }
    
}
