/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.client;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
public class AddCarteFideliteController implements Initializable {
@FXML
    private ComboBox cbidclientfidele;
@FXML
     private ComboBox cbidenseigne;

    @FXML
    private TextField tfnombrePointFidele;

    @FXML
    private DatePicker tfdateCreationPointFidele;

    @FXML
    private Label lnbrPF;

    @FXML
    private Button btnAjouter;
    
           @FXML Button btnClose;
            
            
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<String> listclientf=null;
                List<String> listenseigne=null;
                CarteFideliteDAO cf=new CarteFideliteDAO();
                listclientf=cf.Displaylistclientf();
                listenseigne=cf.Displaylistenseigne();
                cbidclientfidele.getItems().addAll(listclientf);
                 cbidenseigne.getItems().addAll(listenseigne);
                
    }    
    
    @FXML
     private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
    @FXML
     private void btnAjouterOnAction(ActionEvent event) {
         if (control()==true){
        CarteFidelite c=new CarteFidelite();
       
        c.setIdClientFideleDesktop(Integer.parseInt(cbidclientfidele.getValue().toString()));
        c.setIdEnseigne(Integer.parseInt(cbidenseigne.getValue().toString()));
        c.setNombrePointFidele(Integer.parseInt(tfnombrePointFidele.getText()));
        c.setDateCreationCarteFidelite(tfdateCreationPointFidele.getValue().toString());
         ICarteFideliteDAO carte=new CarteFideliteDAO();
                 System.out.println(c.toString());

         carte.insertCarteFidelite(c);
    
     }
     
     }
     public boolean control()
    {
        boolean test = true ;
        System.out.println("***************");
                  System.out.println(test);
                
if(tfnombrePointFidele.getText().isEmpty())
    {
        tfnombrePointFidele.setStyle("-fx-border-color: red ;");
         lnbrPF.setText("veuillez saisir nombre points fidelites");
      
    test=false;}else {
    try {
       int ilnbrPF = Integer.parseInt(tfnombrePointFidele.getText());
 tfnombrePointFidele.setStyle("-fx-border-color: green ;");
        lnbrPF.setText("");
    } catch (NumberFormatException e) {
         tfnombrePointFidele.setStyle("-fx-border-color: red ;");
                 lnbrPF.setText("veuillez saisir  nombre points fidelites");
                lnbrPF.setStyle("color: red ;");
    test=false;
    }}
            System.out.println(test);
             return test ;
}
    
}
