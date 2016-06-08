/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.client;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.dialog.Dialogs;
import tm.dao.classes.CarteFideliteDAO;
import tm.dao.classes.ClientFideleDesktopDAO;
import tm.dao.classes.StockDAO;
import tm.dao.classes.TypeArticleDAO;
import tm.dao.interfaces.ICarteFideliteDAO;
import tm.dao.interfaces.IClientFideleDesktopDAO;
import tm.dao.interfaces.IStockDAO;
import tm.dao.interfaces.ITypeArticleDAO;
import tm.enseigne.AddEnseigneController;
import tm.entities.ClientFideleDesktop;
import tm.entities.TypeArticle;
import tm.stock.UpdateStockController;

/**
 * FXML Controller class
 *
 * @author omarblythe
 */
public class GClientController implements Initializable {
    @FXML
    private TableView<ClientFideleDesktop> tblViewClient;

    @FXML
    private TableColumn<ClientFideleDesktop, Integer> tblClmID;

     @FXML
    private TableColumn<ClientFideleDesktop, String> tblClmTelephone;
 
     @FXML
    private TableColumn<ClientFideleDesktop,String > tblClmNom;

   
     @FXML
    private TableColumn<ClientFideleDesktop, String> tblClmAdresse;
    @FXML
    private TableColumn<ClientFideleDesktop, String> tblClmEmail;
    @FXML
    private TableColumn<ClientFideleDesktop, String> tblClmPrenom;
    @FXML
    TextField tfSearch ;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        viewClient();
    } 
    
    @FXML
    private void btnAddNewOnAction(ActionEvent event) {
        AddEnseigneController apc = new AddEnseigneController();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/tm/client/AddClient.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            AddClientController addClientController = fxmlLoader.getController();
            Stage nStage = new Stage();
//            addProductController.addSupplyerStage(nStage);
            nStage.setScene(scene);
            nStage.initModality(Modality.APPLICATION_MODAL);
            nStage.initStyle(StageStyle.TRANSPARENT);
            nStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    public void viewClient(){
        
        IClientFideleDesktopDAO clientDAO = new ClientFideleDesktopDAO();
            tblClmID.setCellValueFactory(new PropertyValueFactory<>("idClientFideleDesktop"));
            tblClmTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
            tblClmNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            tblClmAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            tblClmEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            tblClmPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            
            tblViewClient.setItems(clientDAO.DisplayAllClientFideleDesktop());
           // tblView.getColumns().addAll(tblClmIDArticle);
    }
    @FXML
    private void btnDeleteOnAction(ActionEvent event) {
      
            
        if (tblViewClient.getSelectionModel().isEmpty())
        {Alert alert = new Alert(Alert.AlertType.ERROR);
alert.setContentText("Veuillez selectionner un client");
Optional<ButtonType> result = alert.showAndWait();}
        else{
        Alert alert = new Alert(Alert.AlertType.WARNING);
alert.setContentText("etes vous sur de vouloir supprimer le client ?");
Optional<ButtonType> result = alert.showAndWait();
       if (result.get() == ButtonType.OK){
            int id = tblViewClient.getSelectionModel().getSelectedItem().getIdClientFideleDesktop();
            IClientFideleDesktopDAO clientDAO = new ClientFideleDesktopDAO();
            clientDAO.deleteClientFideleDesktop(id);
            viewClient();
       }
    }
  
}
    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
        UpdateClientController apc = new UpdateClientController();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/tm/client/UpdateClient.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            UpdateClientController updateClientController = fxmlLoader.getController();
            updateClientController.tfID.setText(Integer.toString(tblViewClient.getSelectionModel().getSelectedItem().getIdClientFideleDesktop()));
            updateClientController.tfNom.setText(tblViewClient.getSelectionModel().getSelectedItem().getNom());
            updateClientController.tfPrenom.setText(tblViewClient.getSelectionModel().getSelectedItem().getPrenom());
            updateClientController.tfAdresse.setText(tblViewClient.getSelectionModel().getSelectedItem().getAdresse());
            updateClientController.tfEmail.setText(tblViewClient.getSelectionModel().getSelectedItem().getEmail());
            updateClientController.tfTelephone.setText(tblViewClient.getSelectionModel().getSelectedItem().getTelephone());
           
            

            Stage nStage = new Stage();
//            addProductController.addSupplyerStage(nStage);
            nStage.setScene(scene);
            nStage.initModality(Modality.APPLICATION_MODAL);
            nStage.initStyle(StageStyle.TRANSPARENT);
            nStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    ClientFideleDesktopDAO client = new ClientFideleDesktopDAO();
     ObservableList<ClientFideleDesktop> listclientfidele;



  @FXML
    private void actionSearch(KeyEvent event) {
         listclientfidele= client.findClientFideleDesktopByNom(tfSearch.getText());
        tblViewClient.setItems(listclientfidele);
        
    }
    
    @FXML
    private void RefreshOnAction(ActionEvent event) {
viewClient();
tblViewClient.refresh();
    }
    
}