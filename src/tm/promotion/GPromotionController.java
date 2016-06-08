/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.promotion;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
import org.controlsfx.dialog.Dialogs;
import tm.dao.classes.PromotionDAO;
import tm.dao.interfaces.IPromotionDAO;
import tm.entities.Promotion;
import tm.entities.Promotion;
import tm.entities.Promotion;


/**
 * FXML Controller class
 *
 * @author omarblythe
 */
public class GPromotionController implements Initializable {

    
    /**
     * Initializes the controller class.
     */
    
     @FXML
private TableView<Promotion> tblViewPromotion;
     @FXML
    private ComboBox cbSoteViewNom;
    
    @FXML
    private ComboBox cbSoteViewDebut;
    @FXML
    private ComboBox cbSoteViewFin;
    
    
    @FXML
    private TextField tfSearch;
    
     
     
     
     
     
     
      @FXML
    private TableColumn<Promotion, Integer> tblClmId;
    
    @FXML
    private TableColumn<Promotion, String> tblClmDescription;
    
    @FXML
    private TableColumn<Promotion, String> tblClmNom;

    @FXML
    private TableColumn<Promotion, Date> tblClmDebut;
    
    @FXML
    private TableColumn<Promotion, Date> tblClmFin;
    @FXML
    private TableColumn<Promotion, String> tblClmSlogan;
   
     IPromotionDAO promo = new PromotionDAO();
     ObservableList<Promotion> listpromo;
    
   
     
     
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        viewAllPromotions();
      
         
        // TODO
    } 
   
  
  
  
  
  
  @FXML
    private void actionSearch(KeyEvent event) {
        listpromo = promo.findPromotionByNom(tfSearch.getText());
        tblViewPromotion.setItems(listpromo);
    }
    
        @FXML
    private void btnAddNewOnAction(ActionEvent event) {
        AddPromotionController apc = new AddPromotionController();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/tm/promotion/AddPromotion.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            AddPromotionController addPromotionController = fxmlLoader.getController();
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
        UpdatePromotionController uec  =new UpdatePromotionController();
        
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/tm/promotion/UpdatePromotion.fxml"));
        
        
       
        try {
          
            
            if(!tblViewPromotion.getSelectionModel().isEmpty()){
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            UpdatePromotionController updatePromotionController = fxmlLoader.getController();

            updatePromotionController.tfId.setText(Integer.toString(tblViewPromotion.getSelectionModel().getSelectedItem().getIdPromotion()));
            updatePromotionController.tfNom.setText(tblViewPromotion.getSelectionModel().getSelectedItem().getNom());
            updatePromotionController.tfSlogan.setText(tblViewPromotion.getSelectionModel().getSelectedItem().getSlogan());
            updatePromotionController.tfDebut.setValue(LocalDate.parse( tblViewPromotion.getSelectionModel().getSelectedItem().getDateDebut().toString()));
            updatePromotionController.tfFin.setValue(LocalDate.parse(tblViewPromotion.getSelectionModel().getSelectedItem().getDateFin().toString()));
            updatePromotionController.taDescription.setText(tblViewPromotion.getSelectionModel().getSelectedItem().getDescription());
            
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
    
//       @FXML
//    private void btnSupprimerOnAction(ActionEvent event) {
//        
//        org.controlsfx.control.action.Action delete = Dialogs.create().title("").masthead("Comfirm").message("Are you sure").styleClass(org.controlsfx.dialog.Dialog.STYLE_CLASS_UNDECORATED).actions(org.controlsfx.dialog.Dialog.ACTION_YES, org.controlsfx.dialog.Dialog.ACTION_NO).showWarning();
//        if (delete == org.controlsfx.dialog.Dialog.ACTION_YES) {
//        IPromotionDAO promotionDAO = new PromotionDAO();
//        
//        promotionDAO.deletePromotion(tblViewPromotion.getSelectionModel().getSelectedItem().getIdPromotion());
//        viewAllPromotions();
//        
//        }
//    }

@FXML
    private void btnSupprimerOnAction(ActionEvent event) {
        
    
        IPromotionDAO promotionDAO = new PromotionDAO();
        Alert alert = new Alert(AlertType.WARNING);
alert.setTitle("Confirmation");
alert.setHeaderText("Confirmation");
alert.setContentText("voulez vous reellement supprimer ?");

Optional<ButtonType> result = alert.showAndWait();
if (result.get() == ButtonType.OK){
    promotionDAO.deletePromotion(tblViewPromotion.getSelectionModel().getSelectedItem().getIdPromotion());
        viewAllPromotions();
} else {
    // ... user chose CANCEL or closed the dialog
}
        
       
        
        
    }
  
    
     public void viewAllPromotions() {
            IPromotionDAO promotionDAO=new PromotionDAO();
            Promotion promotion=new Promotion();
            
            tblViewPromotion.setItems(promotionDAO.DisplayAllPromotion());
            
            
            tblClmId.setCellValueFactory(new PropertyValueFactory<>("idPromotion"));
            tblClmNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
                        tblClmSlogan.setCellValueFactory(new PropertyValueFactory<>("slogan"));

            tblClmDebut.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
            tblClmFin.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
                        tblClmDescription.setCellValueFactory(new PropertyValueFactory<>("description"));


}
    
    
}
