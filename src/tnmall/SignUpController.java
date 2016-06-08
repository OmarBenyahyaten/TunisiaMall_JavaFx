/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tnmall;

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

/**
 * FXML Controller class
 *
 * @author omarblythe
 */
public class SignUpController implements Initializable {

    @FXML
    private PasswordField tfPassword;

    @FXML
    private Button btnClose;
    @FXML
    private Button photo;

    @FXML
    private Label lbPassword;

    @FXML
    private Button btnSignup;

    @FXML
    private PasswordField tfPasswordConfirm;

    @FXML
    private TextField tfUser;

    @FXML
    private TextField tfMail;

    @FXML
    private Label lbPasswordConfirm;

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
    private boolean onKeyReleaseLoginOnAction(Event event) {
        UsernameValidator v = new UsernameValidator();
        IUserDAO dao = new UserDAO();
        if (tfUser.getText().length() >= 3) {
            if (v.validate(tfUser.getText()) == false) {
                lbLogin.setText("wrong username");
                lbLogin.setTextFill(Color.web("#0000ff"));
            } else if (dao.findUserByUsername(tfUser.getText()) != null) {
                lbLogin.setText("not available");
                lbLogin.setTextFill(Color.web("#ff0000"));
            } else {
                lbLogin.setText("available");
                lbLogin.setTextFill(Color.web("#00ff00"));
                return true;
            }
        } else {
            lbLogin.setText("username should contain 3 letters and/or numbers ");
            lbLogin.setTextFill(Color.web("#00ff00"));
        }

        return false;
    }

    @FXML
    private boolean onKeyPassWordLoginOnAction(Event event) {
        PasswordValidator v = new PasswordValidator();
        if (v.validate(tfPassword.getText())) {
            lbPassword.setText("correct");
            lbPassword.setTextFill(Color.web("#00ff00"));
            return true;
        } else {
            lbPassword.setText("should contain 1 upper lower number special caracter");
            lbPassword.setTextFill(Color.web("#00ff00"));
        }
        return false;
    }

    @FXML
    private boolean onKeyPassWordConfirmLoginOnAction(Event event) {
        // lbPasswordConfirm.setText("a");
        //lbPasswordConfirm.setTextFill(Color.web("#00ff00"));
        if ((tfPassword.getText().length() == tfPasswordConfirm.getText().length()) && (tfPassword.getText().length() >= 8)) {
            if (tfPassword.getText().compareTo(tfPasswordConfirm.getText()) == 0) {
                lbPasswordConfirm.setText("compatible password");
                lbPasswordConfirm.setTextFill(Color.web("#00ff00"));
                return true;
            } else {
                lbPasswordConfirm.setText("incompatible password");
                lbPasswordConfirm.setTextFill(Color.web("#ff0000"));
            }

        }
        return false;
    }

    @FXML
    private boolean onKeyEmailLoginOnAction(Event event) {
        EmailValidator v = new EmailValidator();
        //SMTP s=new SMTP();
        if (v.validate(tfMail.getText())) {
            //System.out.println(SMTP.isAddressValid(tfMail.getText()));

            lbEmail.setText("Email correcte");
            lbEmail.setTextFill(Color.web("#00ff00"));
            return true;
        } else {
            lbEmail.setText("Email incorrecte");
            lbEmail.setTextFill(Color.web("#ff0000"));
        }
        return false;
    }

    @FXML
    private void onSignUpOnAction(Event event) throws IOException {

        boolean emailEtat = onKeyEmailLoginOnAction(event);
        boolean usernameEtat = onKeyReleaseLoginOnAction(event);
        boolean passwordEtat = onKeyPassWordLoginOnAction(event);
        boolean passwordConfirmEtat = onKeyPassWordConfirmLoginOnAction(event);
        String path = btnSourceOnAction();
        if (emailEtat && usernameEtat && passwordConfirmEtat && passwordEtat) {
            User u = new User(tfUser.getText(), tfMail.getText(), tfPassword.getText(),path);
            IUserDAO dao = new UserDAO();
            dao.insertUser(u);
            Stage stage = (Stage) btnClose.getScene().getWindow();
            stage.close();
        }
    }
    @FXML
     private String btnSourceOnAction() throws IOException {
        Stage stage = (Stage) btnSignup.getScene().getWindow();
        FileChooser filechooser= new FileChooser();
        filechooser.setTitle("Ouvrir la Source de l'Image");
        
        File file=filechooser.showOpenDialog(stage);
        System.out.println("aaaaaaa");
    System.out.println(file.getPath());
       List l= MultipartUtility.up(file.getPath());
       
        String path =l.toString();
        path=path.substring(0,path.indexOf("[")) + path.substring(path.indexOf("[") +1);
      path=path.substring(0,path.indexOf("]")) + path.substring(path.indexOf("]") +1);
       return path;
       // tfLogo.setText(file.toString());
        
        
        
    }
}
