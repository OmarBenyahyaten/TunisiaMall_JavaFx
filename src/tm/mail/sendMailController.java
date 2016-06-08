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
import tm.dao.classes.UserDAO;
import tm.dao.interfaces.IUserDAO;
import tm.entities.EmailValidator;
import tm.entities.PasswordValidator;
import tm.entities.SMTP;
import tm.entities.User;
import tm.entities.UsernameValidator;
import tm.technique.MultipartUtility;
import tm.technique.SendMailTLS;

/**
 * FXML Controller class
 *
 * @author omarblythe
 */
public class sendMailController implements Initializable {
      @FXML
    private PasswordField pass;
  @FXML
    private Button btnClose;

    @FXML
    private Button btnSignup;

    @FXML
    private TextArea tfText;

    @FXML
    private TextField tfMail;

    @FXML
    private TextField tfsubject;

  

    @FXML
    void btnCloseOnAction(ActionEvent event) {
 Stage stage = (Stage) btnClose.getScene().getWindow();
        
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
    }
@FXML
public void sendAction(Event event)
{
    IUserDAO dao =new UserDAO();
    
    if(SendMailTLS.sendMail(tfMail.getText(), tfsubject.getText(), tfText.getText(),pass.getText()))
    {
       Stage stage = (Stage) btnClose.getScene().getWindow();
        
        stage.close(); 
    }
    
}


    
}
