/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.settings;

import java.net.URL;
import java.util.ResourceBundle;
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
import tm.technique.Session;

/**
 * FXML Controller class
 *
 * @author omarblythe
 */
public class confirmPasswordController implements Initializable {
@FXML
    private PasswordField tfPassword;

    @FXML
    private Button btnClose;

    @FXML
    private Label lbPassword;

    @FXML
    private Button btnSignup;

    @FXML
    void close(ActionEvent event) {
Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @FXML
    private void FindAction(Event event) {
    Session s = Session.getInstance();
    IUserDAO dao=new UserDAO();
    if(dao.connect(s.getUsername(), tfPassword.getText()))
    {
        s.setAccessToModify(1);
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
    else
    {
        lbPassword.setText("wrong password");
    }

}}
