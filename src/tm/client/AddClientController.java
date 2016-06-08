/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.client;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tm.dao.classes.ClientFideleDesktopDAO;
import tm.dao.interfaces.IClientFideleDesktopDAO;
import tm.entities.ClientFideleDesktop;

/**
 * FXML Controller class
 *
 * @author omarblythe
 */
public class AddClientController implements Initializable {

    @FXML
    private Label lNom;
    
    @FXML
    private Label lPrenom;
    @FXML
    private Label lAdresse;
    @FXML
    private Label lTelephone;
    @FXML
    private Label lEmail;
    
      @FXML
    private TextArea tfAdresse;

    @FXML
    private TextField tfPrenom;

    @FXML
    private TextField tfTelephone;

    @FXML
    private TextField tfNom;

    @FXML
    private TextField tfEmail;

    @FXML
    private Button btnAjouter;
            @FXML Button btnClose;

    
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
     private void btnAjouterOnAction(ActionEvent event) {
       
         
         if (control()==true){
             ClientFideleDesktop c=new ClientFideleDesktop();
        c.setNom(tfNom.getText());
        c.setPrenom(tfPrenom.getText());
        c.setAdresse(tfAdresse.getText());
        c.setEmail(tfEmail.getText());
        c.setTelephone(tfTelephone.getText());
         IClientFideleDesktopDAO client=new ClientFideleDesktopDAO();
                 System.out.println(c.toString());

         client.insertClientFideleDesktop(c);
             
         } 
    
     }
     
     
     
     public boolean control()
    {
        boolean test = true ;
        System.out.println("***************");
                  System.out.println(test);
                
if(tfNom.getText().isEmpty())
    {
        tfNom.setStyle("-fx-border-color: red ;");
        lNom.setText("veuillez saisir Nom");
      
    test=false;
    }


if(tfPrenom.getText().isEmpty())
    {
        tfPrenom.setStyle("-fx-border-color: red ;");
        lPrenom.setText("veuillez saisir Prenom");
      
    test=false;
    }
if(tfAdresse.getText().isEmpty())
    {
        tfAdresse.setStyle("-fx-border-color: red ;");
        lAdresse.setText("veuillez saisir Adresse");
      
    test=false;
    }
if(tfTelephone.getText().isEmpty())
    {
        tfTelephone.setStyle("-fx-border-color: red ;");
        lTelephone.setText("veuillez saisir Telephone");
      
    test=false;
    }
if(tfEmail.getText().isEmpty())
    {
        tfEmail.setStyle("-fx-border-color: red ;");
        lEmail.setText("veuillez saisir Email");
      
    test=false;
    }


return test;
    }
}

    


