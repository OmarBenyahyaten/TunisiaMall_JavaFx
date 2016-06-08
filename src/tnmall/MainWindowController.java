/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tnmall;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tm.client.AddClientController;
import tm.dao.classes.UserDAO;
import tm.dao.interfaces.IUserDAO;
import tm.enseigne.AddEnseigneController;
import tm.entities.*;
import tm.mail.sendMailController;
import tm.technique.HttpDownloadUtility;
import tm.technique.Session;

/**
 * FXML Controller class
 *
 * @author omarblythe
 */
public class MainWindowController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Pane login;

    @FXML
    private TextField tfUser;
    @FXML
    private TextField tfPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Button mail;
    @FXML
    private Button aaaaa;
    @FXML
    private StackPane window;
    @FXML
    private Button facebook;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
tfPassword.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {

    public void handle(KeyEvent event) {
        if(event.getCode().toString().compareTo("ENTER")==0)
        {
            ActionEvent e=new ActionEvent(login, tfUser);
            try {
                btnLoginOnAction(e);
            } catch (IOException ex) {
                Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
});
    }

    @FXML
    @SuppressWarnings("empty-statement")
    private void btnLoginOnAction(ActionEvent event) throws IOException, InterruptedException {
        IUserDAO daoUser = new UserDAO();
        System.out.println(tfUser.getText() + "|" + tfPassword.getText());
        System.out.println(daoUser.connect(tfUser.getText(), tfPassword.getText()));
        if (daoUser.connect(tfUser.getText(), tfPassword.getText())) {
            User u = daoUser.findUserByUsername(tfUser.getText());
            if ((u.getRoles().compareTo("sannou3") == 0) || (u.getRoles().contains("ROLE_AGENT")) == true) {
                String fileURL = "https://tunmall.azurewebsites.net/tnmall/uploads/"+u.getPath();
                System.out.println(fileURL);
                String saveDir = System.getProperty("user.dir");
                saveDir=saveDir+"\\src\\images";
                System.out.println(saveDir);
                File f=new File("src/images/"+u.getPath());
                if (f.exists())
                {
                    System.out.println("exist");
                    daoUser.updateLastLogin(u);
                Session s = Session.setInstance(tfUser.getText(), u.getId());
                s.setEmail(u.getEmail());
                System.out.println(s.getUsername() + "|" + s.getId());
                ((Node) (event.getSource())).getScene().getWindow().hide();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AppEnseigne.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();  
                    
                }
                else 
                {
                 HttpDownloadUtility.downloadFile(fileURL, saveDir);
                  daoUser.updateLastLogin(u);
                Session s = Session.setInstance(tfUser.getText(), u.getId());
                s.setEmail(u.getEmail());
                System.out.println(s.getUsername() + "|" + s.getId());
                ((Node) (event.getSource())).getScene().getWindow().hide();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AppEnseigne.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();  
                }
                
            }
            if ((u.getRoles().contains("ROLE_SUPER_ADMIN") == true)) {
                daoUser.updateLastLogin(u);
                Session s = Session.setInstance(tfUser.getText(), u.getId());
                s.setEmail(u.getEmail());
                System.out.println(s.getUsername() + "|" + s.getId());
                ((Node) (event.getSource())).getScene().getWindow().hide();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AdminApp.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();
            }
            if ((u.getRoles().contains("BOUTIQUE")==true)) {
                daoUser.updateLastLogin(u);
                Session s = Session.setInstance(tfUser.getText(), u.getId());
                s.setEmail(u.getEmail());
                System.out.println(s.getUsername() + "|" + s.getId());
                ((Node) (event.getSource())).getScene().getWindow().hide();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AppBoutique.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();
            }

        } else {
            Button btn = new Button("forget password !");
            HBox hbBtn = new HBox(20);
            hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
            hbBtn.getChildren().add(btn);
            btn.setLayoutX(778);
            btn.setLayoutY(450);
            login.getChildren().add(btn);
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    SignUpController apc = new SignUpController();
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("recupMail.fxml"));
                    try {
                        fxmlLoader.load();
                        Parent parent = fxmlLoader.getRoot();
                        Scene scene = new Scene(parent);
                        recupMailController recipmailController = fxmlLoader.getController();
                        Stage nStage = new Stage();
//            addProductController.addSupplyerStage(nStage);
                        scene.setFill(Color.TRANSPARENT);
                        nStage.setScene(scene);
                        nStage.initModality(Modality.APPLICATION_MODAL);

                        nStage.initStyle(StageStyle.TRANSPARENT);
                        nStage.show();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            Paint value0 = Paint.valueOf("FFFFFF");
            tfUser.setStyle("-fx-border-color: #DC5151;");
            tfPassword.setStyle("-fx-border-color: #DC5151;");
            System.out.println("disconnected");
        }
    }
    

    @FXML
    private void btnSignUpOnAction(ActionEvent event) {
        SignUpController apc = new SignUpController();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("SignUp.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            SignUpController signUpController = fxmlLoader.getController();
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

    }
    @FXML
    private void mailAction(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/tm/mail/mail.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();
        
    }
    @FXML
    private void aAction(ActionEvent event) throws IOException {
         sendMailController apc = new sendMailController();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/tm/mail/sendMail.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            sendMailController signUpController = fxmlLoader.getController();
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
        
    }
    
}
