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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tm.dao.classes.CarteFideliteDAO;
import tm.dao.classes.ClientFideleDesktopDAO;
import tm.dao.interfaces.ICarteFideliteDAO;
import tm.dao.interfaces.IClientFideleDesktopDAO;
import tm.entities.CarteFidelite;
import tm.entities.ClientFideleDesktop;

/**
 * FXML Controller class
 *
 * @author dhia
 */
public class UpdateCarteFideliteController implements Initializable {

    @FXML
    TextField tfidCarteFidelite;

     @FXML
     TextField tfidClientFideleDesktop;

    @FXML
     TextField tfidEnseigne;

    @FXML
     TextField tfnombrePointFidelite;

    @FXML
     DatePicker tfdateCreationPointFidele;


    @FXML
     Button btnModofier;
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
     private void btnModifierOnAction(ActionEvent event) {
        CarteFidelite c=new CarteFidelite();
        c.setIdCarteFidelite(Integer.parseInt(tfidCarteFidelite.getText()));
        c.setIdClientFideleDesktop(Integer.parseInt(tfidClientFideleDesktop.getText()));
        c.setIdEnseigne(Integer.parseInt(tfidEnseigne.getText()));
        c.setNombrePointFidele(Integer.parseInt(tfnombrePointFidelite.getText()));
        c.setDateCreationCarteFidelite(tfdateCreationPointFidele.getValue().toString());
        
        
         ICarteFideliteDAO carte=new CarteFideliteDAO();
                 System.out.println(c.toString());

         carte.updateCarteFidelite(c);
    
     }
    
}
