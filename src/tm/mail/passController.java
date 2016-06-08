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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import tm.enseigne.GEnseigneController;
import tm.technique.EmailReceiveTest;
import tm.technique.Session;
import tnmall.SignUpController;
//import org.controlsfx.*;

/**
 * FXML Controller class
 *
 * @author omarblythe
 */
public class passController implements Initializable {

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
    @FXML
    private StackPane spProductContent;


     //private TableColumn<mail, LocalDate> birthdayColumn;
    
    @FXML
    private Button btnPass;

    @FXML
    private TextField pass;
       @FXML
    private AnchorPane passpp;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        }
        
 
       
    

   @FXML
    void passAction(ActionEvent event) throws IOException {
        System.out.println(pass.getText());
        Session s=Session.getInstance();
        s.setPass(pass.getText());
            FXMLLoader fxmlLoader = new FXMLLoader();
        
          sendMailController apc = new sendMailController();
            GEnseigneController sc = new GEnseigneController();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/tm/mail/mail.fxml").openStream());
    
        AnchorPane acPane = fXMLLoader.getRoot();

      spProductContent.getChildren().clear();
        spProductContent.getChildren().add(acPane);
        
        
    }

   
       
    
 
    

}
