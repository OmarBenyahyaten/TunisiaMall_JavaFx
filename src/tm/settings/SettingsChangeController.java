/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.settings;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Pair;
import tm.dao.classes.StockDAO;
import tm.dao.classes.TypeArticleDAO;
import tm.dao.classes.UserDAO;
import tm.dao.interfaces.IStockDAO;
import tm.dao.interfaces.ITypeArticleDAO;
import tm.dao.interfaces.IUserDAO;
import tm.entities.*;
import tm.entities.UsernameValidator;
import tm.technique.Session;
import tm.settings.confirmPasswordController;

public class SettingsChangeController implements Initializable {

    @FXML
    private Button save;
    @FXML
    private TextField changeUsername;
    @FXML
    private Label lbPasswordConfirm;

    @FXML
    private Label lbPassword;
    @FXML
    private PasswordField tfPassword;

    @FXML
    private Label changeEmailEtat;

    @FXML
    private Label changeUsernameEtat;

    @FXML
    private PasswordField tfPasswordConfirm;

    @FXML
    private TextField changeEmail;

    @FXML
    private StackPane spProductContent;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        IUserDAO dao = new UserDAO();
        Session s = Session.getInstance();
        User u = dao.findUserByUsername(s.getUsername());
        changeUsername.setText(u.getUsername());
        changeEmail.setText(u.getEmail());
    }

    @FXML
    private boolean userNameModifyAction(Event event) {
        UsernameValidator v = new UsernameValidator();
        IUserDAO dao = new UserDAO();
        if (changeUsername.getText().length() >= 3) {
            if (v.validate(changeUsername.getText()) == false) {
                changeUsernameEtat.setText("wrong username");
                changeUsernameEtat.setTextFill(Color.web("#0000ff"));
            } else if (dao.findUserByUsername(changeUsername.getText()) != null) {
                changeUsernameEtat.setText("not available");
                changeUsernameEtat.setTextFill(Color.web("#ff0000"));
            } else {
                changeUsernameEtat.setText("available");
                changeUsernameEtat.setTextFill(Color.web("#00ff00"));
                return true;
            }
        } else {
            changeUsernameEtat.setText("username should contain 3 letters and/or numbers ");
            changeUsernameEtat.setTextFill(Color.web("#00ff00"));
        }

        return false;
    }

    @FXML
    private boolean EmailModifyLAction(Event event) {
        EmailValidator v = new EmailValidator();
        //SMTP s=new SMTP();
        if (v.validate(changeEmail.getText())) {
            //System.out.println(SMTP.isAddressValid(changeEmail.getText()));

            changeEmailEtat.setText("Email correcte");
            changeEmailEtat.setTextFill(Color.web("#00ff00"));
            return true;
        } else {
            changeEmailEtat.setText("Email incorrecte");
            changeEmailEtat.setTextFill(Color.web("#ff0000"));
        }
        return false;
    }

    @FXML
    private boolean PassWordChangeAction(Event event) {
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
    private boolean PassWordConfirmAction(Event event) {
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
    /*@FXML
     void saveAction(ActionEvent event) {
     confirmPasswordController apc = new confirmPasswordController();
     FXMLLoader fxmlLoader = new FXMLLoader();
     fxmlLoader.setLocation(getClass().getResource("/tm/settings/confirmPass.fxml"));
     try {
     fxmlLoader.load();
     Parent parent = fxmlLoader.getRoot();
     Scene scene = new Scene(parent);
     confirmPasswordController conPass = fxmlLoader.getController();
     Stage nStage = new Stage();
     //            addProductController.addSupplyerStage(nStage);
     scene.setFill(Color.TRANSPARENT);
     nStage.setScene(scene);
     nStage.initModality(Modality.APPLICATION_MODAL);

     nStage.initStyle(StageStyle.TRANSPARENT);
     nStage.show();
     } catch (IOException e) {
     e.printStackTrace();
     }   

     }*/

    @FXML
    void saveAction(ActionEvent event) {
        // Create the custom dialog.
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Login Dialog");
        dialog.setHeaderText("Look, a Custom Login Dialog");

// Set the icon (must be included in the project).
//        dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));
// Set the button types.
        ButtonType loginButtonType = new ButtonType("Login", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

// Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField username = new TextField();
        username.setPromptText("Username");
        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        grid.add(new Label("Username:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(password, 1, 1);

// Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

// Do some validation (using the Java 8 lambda syntax).
        username.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

// Request focus on the username field by default.
        Platform.runLater(() -> username.requestFocus());

// Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(username.getText(), password.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(new Consumer<Pair<String, String>>() {

            public void accept(Pair<String, String> usernamePassword) {
                System.out.println("Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue());
                IUserDAO dao = new UserDAO();
                Session s = Session.getInstance();
                if (dao.connect(usernamePassword.getKey(), usernamePassword.getValue())) {
                    User u = dao.findUserByUsername(usernamePassword.getKey());
                    System.out.println("xxxxxxxxxxxxxxxxxx");
                    dao.updateUserName(changeUsername.getText(), s.getId());
                    System.out.println("aaaaaaaaaaaaaaaaaaa");
                    dao.updateEmail(changeEmail.getText(), s.getId());
                    PasswordValidator v = new PasswordValidator();
                    if ((tfPasswordConfirm.getText().compareTo(tfPassword.getText()) == 0)) {
                        if (v.validate(tfPassword.getText())) {
                            dao.changePassword(s.getId(), tfPassword.getText());
                        }
                    }
                    Session.setInstance(changeUsername.getText(), s.getId());

                }
            }
        });

    }
}
