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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tm.dao.classes.ClientFideleDesktopDAO;
import tm.dao.interfaces.IClientFideleDesktopDAO;
import tm.entities.ClientFideleDesktop;

/**
 * FXML Controller class
 *
 * @author dhia
 */
public class UpdateClientController implements Initializable {

    
          @FXML
     TextArea tfAdresse;

    @FXML
     TextField tfPrenom;

    @FXML
     TextField tfTelephone;

    @FXML
     TextField tfNom;

    @FXML
     TextField tfEmail;
    
    @FXML
     TextField tfID;

    @FXML
     Button btnAjouter;
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
        ClientFideleDesktop c=new ClientFideleDesktop();
        c.setNom(tfNom.getText());
        c.setPrenom(tfPrenom.getText());
        c.setAdresse(tfAdresse.getText());
        c.setEmail(tfEmail.getText());
        c.setTelephone(tfTelephone.getText());
        c.setIdClientFideleDesktop(Integer.parseInt(tfID.getText()));
         IClientFideleDesktopDAO client=new ClientFideleDesktopDAO();
                 System.out.println(c.toString());

         client.updateClientFideleDesktop(c);
    
     }
}
