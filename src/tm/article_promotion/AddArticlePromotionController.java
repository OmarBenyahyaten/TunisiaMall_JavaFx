/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.article_promotion;

import static java.awt.Color.red;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tm.dao.classes.ArticlePromotionDAO;
import tm.dao.classes.PromotionDAO;
import tm.dao.classes.StockDAO;
import tm.dao.interfaces.IArticlePromotionDAO;
import tm.dao.interfaces.IStockDAO;
import tm.entities.ArticlePromotion;
import tm.entities.Stock;
import tm.technique.Session;

/**
 * FXML Controller class
 *
 * @author Mahmoud
 */
public class AddArticlePromotionController implements Initializable {

    @FXML Button btnAjouter;
    @FXML ComboBox cbArticle;
    @FXML ComboBox cbPromotion;
        @FXML Button btnClose;
         @FXML TextField tfArticle;
        @FXML TextField tfPromotion;
        @FXML TextField tfRedG;
        @FXML TextField tfRedD;
        @FXML TextField tfPoint;
        @FXML Label gros;
        @FXML Label detail;
        @FXML Label fidele;
        @FXML Label article;
        @FXML Label promotion;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<String> listarticle=null;
        List<String> listpromotion=null;
        ArticlePromotionDAO artp= new ArticlePromotionDAO();
        StockDAO std=new StockDAO();
        listarticle= artp.DisplayCBArticle();
        listpromotion= artp.DisplayCBPromotion();
        cbArticle.getItems().addAll(listarticle);
        cbPromotion.getItems().addAll(listpromotion);
        
      //  ObservableList<Stock> dataList=std.DisplayStocks() ;
        
         cbArticle.setValue("Veuillez choirsir l'article");
                        cbPromotion.setValue("Veuillez choirsir la promotion");

      
        
         
         
    }   
   

     @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
    
    
    
    
     @FXML
     private void btnAjouterOnAction(ActionEvent event) {
     if(control()==true)
     {
        
        ArticlePromotionDAO std= new ArticlePromotionDAO();
        int article=std.DisplayCBArticle2(cbArticle.getValue().toString());
        int promotion=std.DisplayCBPromotion2(cbPromotion.getValue().toString());
        String gros=tfRedD.getText();
        String detail=tfRedG.getText();
        String point=tfPoint.getText();
             
        

        
        
        Session s = Session.getInstance();

        ArticlePromotion artpromo=new ArticlePromotion();
        artpromo.setIdArticle(article);
        artpromo.setIdPromotion(promotion);
        artpromo.setTauxReductionGros(Float.parseFloat(gros));
        artpromo.setTauxReductionDetail(Float.parseFloat(detail));
        artpromo.setNombrePointFidele(Integer.parseInt(point));
        
        
        IArticlePromotionDAO ArticlePromotionDAO = new ArticlePromotionDAO();
        ArticlePromotionDAO.insertArticlePromotion(artpromo);
               
     }          
    }
     public boolean control()
    {
        boolean test = true ;
        System.out.println("***************");
                  System.out.println(test);
                  
                  
                  if (cbArticle.getValue().toString().equals("Veuillez choirsir l'article"))
                  { cbArticle.setStyle("-fx-border-color: red ;");
         article.setText("Selectionner l'article");
         article.setTextFill(Color.web("#FF0000"));
         test = false ;
                  }else{ cbArticle.setStyle("-fx-border-color: green ;");
        article.setText("");}
                  
                    if (cbPromotion.getValue().toString().equals("Veuillez choirsir la promotion"))
                  { cbPromotion.setStyle("-fx-border-color: red ;");
         promotion.setText("Selectionner l'article");
         promotion.setTextFill(Color.web("#FF0000"));
         test = false ;
                  }else{ cbPromotion.setStyle("-fx-border-color: green ;");
        promotion.setText("");}
                
if(tfRedG.getText().isEmpty())
    {
        tfRedG.setStyle("-fx-border-color: red ;");
         gros.setText("Champs vide");
         gros.setTextFill(Color.web("#FF0000"));
      
    test=false;}else {
    try {
       float igros = Float.parseFloat(tfRedG.getText());
 tfRedG.setStyle("-fx-border-color: green ;");
        gros.setText("");
    } catch (NumberFormatException e) {
         tfRedG.setStyle("-fx-border-color: red ;");
                 gros.setText("Caractere invalide");
                 gros.setTextFill(Color.web("#FF0000"));
    test=false;
    }}
            System.out.println(test);
if( tfRedD.getText().isEmpty())
    {
        tfRedD.setStyle("-fx-border-color: red ;");
        detail.setText("Champs Vide");
        detail.setTextFill(Color.web("#FF0000"));
    test=false;
    
    }else {
     try {
       Float idetail = Float.parseFloat(tfRedD.getText());
        tfRedD.setStyle("-fx-border-color: green ;");
        detail.setText("");
    } catch (NumberFormatException e) {
    test=false;
            detail.setText("Caractere invalide");
     tfRedD.setStyle("-fx-border-color: red ;");
     detail.setTextFill(Color.web("#FF0000"));
    }}
             System.out.println(test);
             
             
             if( tfPoint.getText().isEmpty())
        { tfPoint.setStyle("-fx-border-color: red ;");
        fidele.setText("Champs Vide");
        fidele.setTextFill(Color.web("#FF0000"));
        test=false;
        }
        else  {
           
    
      try {
       int num = Integer.parseInt(tfPoint.getText());
        tfPoint.setStyle("-fx-border-color: green ;");
                                fidele.setText("");
    } catch (NumberFormatException e) {
    test=false;
            tfPoint.setStyle("-fx-border-color: red ;");
                        fidele.setText("Caractere invalide");
                        fidele.setTextFill(Color.web("#FF0000"));
    }}
              System.out.println(test);
              return test ;
 
    }
     
    
}
