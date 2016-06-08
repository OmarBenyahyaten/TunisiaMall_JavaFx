/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tnmall;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author omarblythe
 */
public class AppBoutiqueController implements Initializable {

    
    /**
     * Initializes the controller class.
     */ 
                         @FXML private StackPane content;
                         @FXML ToggleButton sideMenuToogleBtn;
                         @FXML AnchorPane acDashBord;
                         @FXML private ScrollPane leftSideBarScroolPan;
                         @FXML private ImageView imgMenuBtn;
                         @FXML private ImageView imgStockBtn;
                         @FXML private ImageView imgPromotionBtn;
                         @FXML private ImageView imgStatistiqueBtn;
                         @FXML private ImageView imgEnseigneBtn;
                         @FXML private ImageView imgClientBtn;
                         @FXML private ImageView imgParametreBtn;
                         @FXML private ImageView imgPubBtn;


                         
                         
                         
 Image menuImageRed = new Image("/images/menu_hover.png");
 Image menuImage = new Image("/images/menu.png");
 
 Image stockImageRed = new Image("/images/stock_hover.png");
 Image stockImage = new Image("/images/stock.png");

 Image parametreImageRed = new Image("/images/settings_hover.png");
 Image parametreImage = new Image("/images/settings.png");
 
 Image clientImageRed = new Image("/images/client_hover.png");
 Image clientImage = new Image("/images/client.png");
 
 Image enseigneImageRed = new Image("/images/enseigne_hover.png");
 Image enseigneImage = new Image("/images/enseigne.png");


 Image promotionImageRed = new Image("/images/promotion_hover.png");
 Image promotionImage = new Image("/images/promotion.png");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        sideMenuToogleBtn.setSelected(true);
imgMenuBtn.setImage(menuImageRed);
            TranslateTransition sideMenu = new TranslateTransition(Duration.millis(200.0), acDashBord);
            sideMenu.setByX(-130);
            sideMenu.play();    
            
    }    
    
     @FXML
    private void sideMenuToogleBtnOnCLick(ActionEvent event) throws IOException {
        if (sideMenuToogleBtn.isSelected()) {
           imgMenuBtn.setImage(menuImageRed);
            TranslateTransition sideMenu = new TranslateTransition(Duration.millis(200.0), acDashBord);
            sideMenu.setByX(-130);
            sideMenu.play();
//             TranslateTransition center = new TranslateTransition(Duration.millis(200.0), content);
//            center.setByX(-130);
//            center.play();
            
          // acDashBord.getChildren().clear();
         //  content.getChildren().add(content);

        } else {
            imgMenuBtn.setImage(menuImage);
            TranslateTransition sideMenu = new TranslateTransition(Duration.millis(200.0), acDashBord);
            sideMenu.setByX(130);
            sideMenu.play();
//              TranslateTransition center = new TranslateTransition(Duration.millis(200.0), content);
//            center.setByX(130);
//            center.play();
           
           // acDashBord.getChildren().add(leftSideBarScroolPan);
        }
    }
    
    @FXML
    private void btnStockOnClick(ActionEvent event) throws IOException {
        stockActive();
         StockController sc = new StockController();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("Stock.fxml").openStream());
    
        AnchorPane acPane = fXMLLoader.getRoot();

       content.getChildren().clear();
        content.getChildren().add(acPane);
        
    }
    
    
     @FXML
    private void btnEnseigneOnClick(ActionEvent event) throws IOException {
        enseigneActive();
         EnseigneController sc = new EnseigneController();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("Enseigne.fxml").openStream());
    
        AnchorPane acPane = fXMLLoader.getRoot();

       content.getChildren().clear();
        content.getChildren().add(acPane);
    }
     @FXML
    private void btnPromotionOnClick(ActionEvent event) throws IOException {
        promotionActive();
        PromotionController sc = new PromotionController();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("Promotion.fxml").openStream());
    
        AnchorPane acPane = fXMLLoader.getRoot();

       content.getChildren().clear();
        content.getChildren().add(acPane);
        
    }
     @FXML
    private void btnClientOnClick(ActionEvent event) throws IOException {
        clientActive();
        ClientController sc = new ClientController();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("Client.fxml").openStream());
    
        AnchorPane acPane = fXMLLoader.getRoot();

       content.getChildren().clear();
        content.getChildren().add(acPane);
    }
     @FXML
    private void btnPubOnClick(ActionEvent event) {
        pubActive();
    }
     @FXML
    private void btnStatistiqueOnClick(ActionEvent event) {
        statistiqueActive();
    }
     @FXML
    private void btnParametreOnClick(ActionEvent event) {
 parametreActive();
    }
    
    private void stockActive() {
        imgStockBtn.setImage(stockImageRed);
        imgEnseigneBtn.setImage(enseigneImage);
        imgPromotionBtn.setImage(promotionImage);
        imgClientBtn.setImage(clientImage);
        imgParametreBtn.setImage(parametreImage);  
    }
     private void enseigneActive() {
        imgStockBtn.setImage(stockImage);
        imgEnseigneBtn.setImage(enseigneImageRed);
        imgPromotionBtn.setImage(promotionImage);
        imgClientBtn.setImage(clientImage);
        imgParametreBtn.setImage(parametreImage);  
    }
      private void promotionActive() {
        imgStockBtn.setImage(stockImage);
        imgEnseigneBtn.setImage(enseigneImage);
        imgPromotionBtn.setImage(promotionImageRed);
        imgClientBtn.setImage(clientImage);
        imgParametreBtn.setImage(parametreImage);  
    }
       private void clientActive() {
        imgStockBtn.setImage(stockImage);
        imgEnseigneBtn.setImage(enseigneImage);
        imgPromotionBtn.setImage(promotionImage);
        imgClientBtn.setImage(clientImageRed);
        imgParametreBtn.setImage(parametreImage);  
    }
        private void pubActive() {
        imgStockBtn.setImage(stockImage);
        imgEnseigneBtn.setImage(enseigneImage);
        imgPromotionBtn.setImage(promotionImage);
        imgClientBtn.setImage(clientImage);
        imgParametreBtn.setImage(parametreImage);  
    }
         private void statistiqueActive() {
        imgStockBtn.setImage(stockImage);
        imgEnseigneBtn.setImage(enseigneImage);
        imgPromotionBtn.setImage(promotionImage);
        imgClientBtn.setImage(clientImage);
        imgParametreBtn.setImage(parametreImage);  
    }
          private void parametreActive() {
        imgStockBtn.setImage(stockImage);
        imgEnseigneBtn.setImage(enseigneImage);
        imgPromotionBtn.setImage(promotionImage);
        imgClientBtn.setImage(clientImage);
        imgParametreBtn.setImage(parametreImageRed);  
    }

    
    
}
