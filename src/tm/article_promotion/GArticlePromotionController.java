/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.article_promotion;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tm.dao.classes.ArticlePromotionDAO;
import tm.dao.classes.PromotionDAO;
import tm.dao.classes.StockDAO;
import tm.dao.interfaces.IArticlePromotionDAO;
import tm.dao.interfaces.IPromotionDAO;
import tm.dao.interfaces.IStockDAO;
import tm.entities.ArticlePromotion;
import tm.entities.Promotion;
import tm.promotion.UpdatePromotionController;

/**
 * FXML Controller class
 *
 * @author Mahmoud
 */
public class GArticlePromotionController implements Initializable {

    @FXML
    private TableView<ArticlePromotion> tblViewArtPromo;
    @FXML
    private ComboBox cbPromotion;

    @FXML
    private ComboBox cbSoteViewDebut;
    @FXML
    private ComboBox cbSoteViewFin;

    @FXML
    private TextField tfSearch;
    @FXML
    private TableColumn<ArticlePromotion, Integer> tblClmIDPr;

    @FXML
    private TableColumn<ArticlePromotion, Integer> tblClmIDAr;

    @FXML
    private TableColumn<ArticlePromotion, Float> tblClmRedG;

    @FXML
    private TableColumn<ArticlePromotion, Float> tblClmRedD;
    @FXML
    private TableColumn<ArticlePromotion, Integer> tblClmPoint;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       List<String> listpromotion=null;
        ArticlePromotionDAO artp= new ArticlePromotionDAO();
        listpromotion= artp.DisplayCBPromotion();
        cbPromotion.getItems().addAll(listpromotion);
        viewAllArticlePromotions();
    }

    @FXML
    private void btnAddNewOnAction(ActionEvent event) {
        AddArticlePromotionController apc = new AddArticlePromotionController();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/tm/article_promotion/AddArticlePromotion.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            AddArticlePromotionController addArticlePromotionController = fxmlLoader.getController();
            Stage nStage = new Stage();
//            addProductController.addSupplyerStage(nStage);
            nStage.setScene(scene);
            nStage.initModality(Modality.APPLICATION_MODAL);
            nStage.initStyle(StageStyle.TRANSPARENT);
            nStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btnModifierOnAction(ActionEvent event) {
        UpdateArticlePromotionController uec = new UpdateArticlePromotionController();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/tm/article_promotion/UpdateArticlePromotion.fxml"));

        try {

            if (!tblViewArtPromo.getSelectionModel().isEmpty()) {
                fxmlLoader.load();
                Parent parent = fxmlLoader.getRoot();
                Scene scene = new Scene(parent);
                scene.setFill(new Color(0, 0, 0, 0));
                UpdateArticlePromotionController updatePromotionController = fxmlLoader.getController();

                updatePromotionController.tfArticle.setText(Integer.toString(tblViewArtPromo.getSelectionModel().getSelectedItem().getIdArticle()));
                updatePromotionController.tfPromotion.setText(Integer.toString(tblViewArtPromo.getSelectionModel().getSelectedItem().getIdPromotion()));
                updatePromotionController.tfRedG.setText(Float.toString(tblViewArtPromo.getSelectionModel().getSelectedItem().getTauxReductionGros()));
                updatePromotionController.tfRedD.setText(Float.toString(tblViewArtPromo.getSelectionModel().getSelectedItem().getTauxReductionDetail()));
                updatePromotionController.tfPoint.setText(Integer.toString(tblViewArtPromo.getSelectionModel().getSelectedItem().getNombrePointFidele()));

                Stage nStage = new Stage();
//            addProductController.addSupplyerStage(nStage);
                nStage.setScene(scene);
                nStage.initModality(Modality.APPLICATION_MODAL);
                nStage.initStyle(StageStyle.TRANSPARENT);
                nStage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btnSupprimerOnAction(ActionEvent event) {

        IArticlePromotionDAO promotionDAO = new ArticlePromotionDAO();
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation");
        alert.setContentText("voulez vous reellement supprimer ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            promotionDAO.deleteArticlePromotion(tblViewArtPromo.getSelectionModel().getSelectedItem().getIdArticle(), tblViewArtPromo.getSelectionModel().getSelectedItem().getIdPromotion());
            
            viewAllArticlePromotions();
        } else {
    // ... user chose CANCEL or closed the dialog
        }

    }

    public void viewAllArticlePromotions() {
        IArticlePromotionDAO articlepromotionDAO = new ArticlePromotionDAO();
        ArticlePromotion articlepromotion = new ArticlePromotion();

        tblViewArtPromo.setItems(articlepromotionDAO.DisplayAllArticlePromotion());
        tblClmIDPr.setCellValueFactory(new PropertyValueFactory<>("idPromotion"));
        tblClmIDAr.setCellValueFactory(new PropertyValueFactory<>("idArticle"));
        tblClmRedG.setCellValueFactory(new PropertyValueFactory<>("tauxReductionGros"));
        tblClmRedD.setCellValueFactory(new PropertyValueFactory<>("tauxReductionDetail"));
        tblClmPoint.setCellValueFactory(new PropertyValueFactory<>("nombrePointFidele"));

    }

    @FXML
    void cbPromoOnAction(ActionEvent event) {
                IArticlePromotionDAO articlepromotionDAO = new ArticlePromotionDAO();
                int id = articlepromotionDAO.DisplayCBPromotion2(cbPromotion.getSelectionModel().getSelectedItem().toString());
              tblViewArtPromo.setItems(articlepromotionDAO.DisplayRechPromotion(id));
              
           
              tblClmIDPr.setCellValueFactory(new PropertyValueFactory<>("idPromotion"));
        tblClmIDAr.setCellValueFactory(new PropertyValueFactory<>("idArticle"));
        tblClmRedG.setCellValueFactory(new PropertyValueFactory<>("tauxReductionGros"));
        tblClmRedD.setCellValueFactory(new PropertyValueFactory<>("tauxReductionDetail"));
        tblClmPoint.setCellValueFactory(new PropertyValueFactory<>("nombrePointFidele"));
        tblViewArtPromo.refresh();
 
    }

@FXML
    private void RefreshOnAction(ActionEvent event) {
viewAllArticlePromotions();
tblViewArtPromo.refresh();
    }
      @FXML
    private void btnImprimerOnAction(ActionEvent event) throws DocumentException, FileNotFoundException {
         String s= cbPromotion.getSelectionModel().getSelectedItem().toString();
         Document PDFLogReport = new Document();  
         PdfWriter.getInstance(PDFLogReport, new FileOutputStream("promotion/promotion"+s+".pdf"));  
         PDFLogReport.open();        
         PdfPTable LogTable = new PdfPTable(5);  
         PdfPCell table_cell;  
        
                 table_cell=new PdfPCell(new Phrase("Id Promotion")); 
                 LogTable.addCell(table_cell);  
                 table_cell=new PdfPCell(new Phrase("Id Article")); 
                 LogTable.addCell(table_cell);
                 table_cell=new PdfPCell(new Phrase("Taux Reduction Gros"));
                 LogTable.addCell(table_cell);
                 table_cell=new PdfPCell(new Phrase("Taux Reduction Detail"));
                 LogTable.addCell(table_cell);
                 table_cell=new PdfPCell(new Phrase("Nombre Point"));
                 LogTable.addCell(table_cell);
                 
                 ObservableList<ArticlePromotion> list = FXCollections.observableArrayList();
                 list = tblViewArtPromo.getItems();
                 
                 for (ArticlePromotion ar : list )
                 {
                  table_cell=new PdfPCell(new Phrase(Integer.toString(ar.getIdPromotion()))); 
                 LogTable.addCell(table_cell);  
                 table_cell=new PdfPCell(new Phrase(Integer.toString(ar.getIdArticle()))); 
                 LogTable.addCell(table_cell);
                 table_cell=new PdfPCell(new Phrase(Float.toString(ar.getTauxReductionGros()))); 
                 LogTable.addCell(table_cell);
                 table_cell=new PdfPCell(new Phrase(Float.toString(ar.getTauxReductionDetail()))); 
                 LogTable.addCell(table_cell);
                 table_cell=new PdfPCell(new Phrase(Integer.toString(ar.getNombrePointFidele()))); 
                 LogTable.addCell(table_cell);
                 }
                
               table_cell.setBorder(4);
                 table_cell.setColspan(6);

         PDFLogReport.add(LogTable);              
         PDFLogReport.close();   
                 
    }
    
    
    
    
    
}
