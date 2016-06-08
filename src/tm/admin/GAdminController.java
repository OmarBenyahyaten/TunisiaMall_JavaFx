/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.admin;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
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
import tm.dao.classes.PromotionDAO;
import tm.dao.classes.UserDAO;
import tm.dao.interfaces.IPromotionDAO;
import tm.dao.interfaces.IUserDAO;
import tm.entities.Promotion;
import tm.entities.Promotion;
import tm.entities.Promotion;
import tm.entities.User;
import tm.stock.AddStockController;
//import org.controlsfx.*;

/**
 * FXML Controller class
 *
 * @author omarblythe
 */
public class GAdminController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<User> tblViewPromotion;

    @FXML
    private ComboBox cbSoteViewNom;

    @FXML
    private ComboBox cbSoteViewDebut;
    @FXML
    private ComboBox cbSoteViewFin;

    @FXML
    private TextField tfSearch;

    @FXML
    private TableColumn<User, Integer> tblClmId;

    @FXML
    private TableColumn<User, String> tblClmDescription;

    @FXML
    private TableColumn<User, String> tblClmNom;

    @FXML
    private TableColumn<User, Date> tblClmDebut;

    @FXML
    private TableColumn<User, String> tblClmFin;
    @FXML
    private TableColumn<User, String> tblClmSlogan;
    @FXML
    private Button refresh;
    
    IUserDAO crudData = new UserDAO();

    ObservableList<Promotion> listData;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        viewAllUser();
        // TODO
    }


    /*  @FXML
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
     //        UpdatePromotionController updatePromotionController = fxmlLoader.getController();

     //    updatePromotionController.tfId.setText(Integer.toString(tblViewPromotion.getSelectionModel().getSelectedItem().getIdPromotion()));
     //      updatePromotionController.tfNom.setText(tblViewPromotion.getSelectionModel().getSelectedItem().getNom());
     //   updatePromotionController.tfSlogan.setText(tblViewPromotion.getSelectionModel().getSelectedItem().getSlogan());
     //  updatePromotionController.tfDebut.setText(tblViewPromotion.getSelectionModel().getSelectedItem().getDateDebut());
     // updatePromotionController.tfFin.setText(tblViewPromotion.getSelectionModel().getSelectedItem().getDateFin());
     // updatePromotionController.taDescription.setText(tblViewPromotion.getSelectionModel().getSelectedItem().getDescription());
            
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
     */
    @FXML
    private void btnSupprimerOnAction(ActionEvent event) {
        
          
            
            if(!tblViewPromotion.getSelectionModel().isEmpty()){
            Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("delete");
        alert.setHeaderText("do you wanna delete "+tblViewPromotion.getSelectionModel().getSelectedItem().getUsername());
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
        IUserDAO dao =new UserDAO();
        dao.deleteUser(tblViewPromotion.getSelectionModel().getSelectedItem().getId());
        viewAllUser();
        } 

            
        }
        
    

       
        }
    
@FXML
    private void btnEnableOnAction(ActionEvent event) {
        
          
            
            if(!tblViewPromotion.getSelectionModel().isEmpty()){
            Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("delete");
        alert.setHeaderText("do you wanna enable "+tblViewPromotion.getSelectionModel().getSelectedItem().getUsername());
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
        IUserDAO dao =new UserDAO();
        dao.enableUser(tblViewPromotion.getSelectionModel().getSelectedItem().getId());
        viewAllUser();
        } 

            
        }
        
    

       
        }
    public void viewAllUser() {
        IUserDAO dao = new UserDAO();
        Promotion promotion = new Promotion();

        tblViewPromotion.setItems(dao.DisplayAllUserObsDisabled());

        tblClmId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblClmNom.setCellValueFactory(new PropertyValueFactory<>("username"));
        tblClmSlogan.setCellValueFactory(new PropertyValueFactory<>("email"));

       // tblClmDebut.setCellValueFactory(new PropertyValueFactory<>("lastLogin"));
        //tblClmFin.setCellValueFactory(new PropertyValueFactory<>("password"));
//                        tblClmDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

    }
@FXML
private void refreshAction(Event event)
{
    viewAllUser();
}
IUserDAO promo = new UserDAO();
     ObservableList<User> listuser;
@FXML
    private void actionSearch(KeyEvent event) {
        listuser = promo.findUserByUsernameObs(tfSearch.getText());
        tblViewPromotion.setItems(listuser);
    }
}
