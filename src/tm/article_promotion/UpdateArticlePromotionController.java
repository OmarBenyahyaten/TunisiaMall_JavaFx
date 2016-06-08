/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.article_promotion;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tm.dao.classes.ArticlePromotionDAO;
import tm.dao.interfaces.IArticlePromotionDAO;
import tm.entities.ArticlePromotion;

/**
 * FXML Controller class
 *
 * @author Mahmoud
 */
public class UpdateArticlePromotionController implements Initializable {

   @FXML Button btnClose;
   @FXML Button btnModifier;
         @FXML TextField tfArticle;
        @FXML TextField tfPromotion;
        @FXML TextField tfRedG;
        @FXML TextField tfRedD;
        @FXML TextField tfPoint;
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
       int article=Integer.parseInt(tfArticle.getText());      
        int promotion=Integer.parseInt(tfPromotion.getText());
        float gros=Float.parseFloat(tfRedG.getText());
        float detail=Float.parseFloat(tfRedD.getText());
        int point=Integer.parseInt(tfPoint.getText());
                
           // Session s = Session.getInstance();

        ArticlePromotion artpromo=new ArticlePromotion();
        artpromo.setIdArticle(article);
        artpromo.setIdPromotion(promotion);
        artpromo.setTauxReductionGros(gros);
        artpromo.setTauxReductionDetail(detail);
        artpromo.setNombrePointFidele(point);
        
        
        IArticlePromotionDAO ArticlePromotionDAO = new ArticlePromotionDAO();
        ArticlePromotionDAO.updateArticlePromotion(artpromo);
               
                
    }
    
}
