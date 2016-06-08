/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.mail;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import tm.dao.classes.UserDAO;
import tm.dao.interfaces.IUserDAO;
import tm.entities.EmailValidator;
import tm.entities.PasswordValidator;
import tm.entities.SMTP;
import tm.entities.User;
import tm.entities.UsernameValidator;
import tm.entities.mail;
import static tm.technique.EmailReceiveTest.receive;
import tm.technique.MultipartUtility;

/**
 * FXML Controller class
 *
 * @author omarblythe
 */
public class viewMailController implements Initializable {

     @FXML
    private Button btnClose;

    @FXML
     TextField tfUser;

    @FXML
    TextField tfMail;
     @FXML
     TextArea tfText;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("eeeeeeeeeeeeee");
      // int i= Integer.parseInt(tfMail.getText());
       // System.out.println(i);
       }
@FXML
    private void btnCloseOnAction(ActionEvent event) throws MessagingException, NoSuchProviderException, IOException {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        
        stage.close();
       
    }



}
