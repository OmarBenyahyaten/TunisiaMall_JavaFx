/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.mail;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import static java.time.LocalDate.from;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import tm.dao.classes.PromotionDAO;
import tm.dao.classes.UserDAO;
import tm.dao.interfaces.IPromotionDAO;
import tm.dao.interfaces.IUserDAO;
import tm.entities.Promotion;
import tm.entities.Promotion;
import tm.entities.Promotion;
import tm.entities.User;
import tm.entities.mail;
import tm.stock.AddStockController;
import static tm.technique.EmailReceiveTest.receiveEmail;
import java.*;
import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableRow;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import tm.technique.EmailReceiveTest;
import tm.technique.Session;
import tnmall.SignUpController;
//import org.controlsfx.*;

/**
 * FXML Controller class
 *
 * @author omarblythe
 */
public class mailController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
     TableView<mail> tblViewPromotion;

    @FXML
    private TableColumn<mail, String> tblClmId;

    @FXML
    private TableColumn<mail, String> tblClmSlogan;

    @FXML
    private TableColumn<mail, String> tblClmNom;
    @FXML
    private TableColumn<mail, String> tblClmDebut;

     //private TableColumn<mail, LocalDate> birthdayColumn;
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            viewAllUser();
         
        } catch (MessagingException ex) {
            Logger.getLogger(mailController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(mailController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tblViewPromotion.setRowFactory( tv -> {
    TableRow<mail> row = new TableRow<>();
    row.setOnMouseClicked(event -> {
      viewMailController apc = new viewMailController();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("viewMail.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            viewMailController signUpController = fxmlLoader.getController();
            Stage nStage = new Stage();
            Session s=Session.getInstance();
            mail m =EmailReceiveTest.receive("mail.gmail.com","",s.getEmail(),s.getPass(),tblViewPromotion.getSelectionModel().getSelectedItem().getI()-1);
            signUpController.tfMail.setText(m.getFrom());
            signUpController.tfUser.setText(m.getSubject());
            signUpController.tfText.setText(m.getText());
            signUpController.tfMail.setDisable(true);
              signUpController.tfUser.setDisable(true);
                signUpController.tfText.setDisable(true);
//            addProductController.addSupplyerStage(nStage);
            scene.setFill(Color.TRANSPARENT);
            nStage.setScene(scene);
            nStage.initModality(Modality.APPLICATION_MODAL);

            nStage.initStyle(StageStyle.TRANSPARENT);
            nStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MessagingException ex) {
            Logger.getLogger(mailController.class.getName()).log(Level.SEVERE, null, ex);
        }
    });
    return row ;
});}
        
 
       
    

    @FXML
    private void actionSearch(KeyEvent event) {
//        listData = crudData.likeBynom(tfSearch.getText());
        // tblViewPromotion.setItems(listData);
    }

    public void viewAllUser() throws MessagingException, NoSuchProviderException, IOException {
                Session s=Session.getInstance();
                 String mailPop3Host = "pop.gmail.com";
		String mailStoreType = "pop3";
                
		String mailUser = s.getEmail();
                
		String mailPassword = s.getPass();
System.out.println(mailUser+"   "+mailPassword);
	ObservableList<mail> m=	receiveEmail(mailPop3Host, mailStoreType, mailUser, mailPassword);

        tblViewPromotion.setItems(m);
        //m.stream().forEach(e->System.out.println(e.toString()));
        tblClmId.setCellValueFactory(new PropertyValueFactory<>("from"));
        tblClmNom.setCellValueFactory(new PropertyValueFactory<>("subject"));
      tblClmSlogan.setCellValueFactory(new PropertyValueFactory<>("text"));
      tblClmDebut.setCellValueFactory(new PropertyValueFactory<>("seen"));
       
     tblClmDebut.setCellFactory(new Callback<TableColumn<mail, String>, TableCell<mail, String>>() {
            @Override
            public TableCell<mail, String> call(TableColumn<mail, String> col) {
                final TableCell<mail, String> cell = new TableCell<mail, String>() {
                    @Override
                    public void updateItem(String firstName, boolean empty) {
                        super.updateItem(firstName, empty);
                        if (empty) {
                            setText(null);
                        } else {
                         if(firstName.compareTo("unseen")==0){
                              //int row=getTableRow().getIndex();
                              this.getTableRow().setStyle("-fx-background-color:red;-fx-border-color:green;");
                              
                             
                         }
                         else 
                         {
                             this.getTableRow().setStyle("-fx-background-color:yellow;-fx-border-color:white;");
                         }
                        }
                                    
                                    
                                    
                        
                    }
                };
return cell; }});

           
            
       
    }
 
    

}
