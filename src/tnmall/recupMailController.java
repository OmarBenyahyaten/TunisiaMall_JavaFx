/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tnmall;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
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
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tm.dao.classes.UserDAO;
import tm.dao.interfaces.IUserDAO;
import tm.entities.EmailValidator;
import tm.entities.PasswordValidator;
import tm.entities.SMTP;
import tm.entities.User;
import tm.entities.UsernameValidator;
import tm.technique.SendMailTLS;

/**
 * FXML Controller class
 *
 * @author omarblythe
 */
public class recupMailController implements Initializable {

    @FXML
    private Button btnClose;

    @FXML
    private Button btnSignup;

    @FXML
    private TextField tfUser;

    @FXML
    private TextField tfMail;

    @FXML
    private Label lbLogin;

    @FXML
    private Label lbEmail;

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
    private boolean RecupUsernameOnAction(Event event) {
        //UsernameValidator v=new UsernameValidator();
        IUserDAO dao = new UserDAO();

        if (dao.findUserByUsername(tfUser.getText()) != null) {
            lbLogin.setText("available");
            lbLogin.setTextFill(Color.web("#00ff00"));
            return true;
        } else {
            lbLogin.setText("not available");
            lbLogin.setTextFill(Color.web("#ff0000"));
            return false;
        }

    }

    @FXML
    private void FindAction(Event event) {
        IUserDAO dao = new UserDAO();
        User u = dao.findUserByUsername(tfUser.getText());
        if(u.getEmail().compareTo(tfMail.getText())==0)
        {
        Random rn = new Random();
        String pass="new pass";
        try {
            pass = u.base_convert(u.sha1(u.uniqid(Long.toString(rn.nextInt()), true)), 16, 36);
            System.out.println(pass.substring(8));
            dao.changePassword(u.getId(), pass.substring(8));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       SendMailTLS.recupMail(tfMail.getText(),pass.substring(8));
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
        }
        else
        {
            tfMail.setText("not found account");
        }

    }
}
