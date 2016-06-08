/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.client;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static jdk.nashorn.internal.objects.NativeDate.getDate;
import org.controlsfx.dialog.Dialogs;
import tm.dao.classes.CarteFideliteDAO;
import tm.dao.classes.ClientFideleDesktopDAO;
import tm.dao.classes.StockDAO;
import tm.dao.interfaces.ICarteFideliteDAO;
import tm.dao.interfaces.IClientFideleDesktopDAO;
import tm.dao.interfaces.IStockDAO;
import tm.enseigne.AddEnseigneController;
import tm.entities.CarteFidelite;
import tm.entities.ClientFideleDesktop;

/**
 * FXML Controller class
 *
 * @author dhia
 */
public class G_CarteFideliteClientController implements Initializable {

     @FXML
    private TableView<CarteFidelite> tblViewCarteClientFidele;

    @FXML
    private TableColumn<CarteFidelite, Integer> tblClmidCarteFidelite;

     @FXML
    private TableColumn<CarteFidelite, Integer> tblClmidClientFideleDesktop;
 
     @FXML
    private TableColumn<CarteFidelite,Integer > tblClmidEnseigne;     
     @FXML
    private TableColumn<CarteFidelite, Integer> tblClmnombrePointFidele;
     @FXML
    private TableColumn<CarteFidelite, String> tblClmdateCreationCarteFidelite;
    @FXML
    private StackPane spProductContent;
    @FXML
    private TextField tfSearch;
    @FXML
    private Button btnRefresh;
    
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;
    @FXML
    private MenuItem miSellSelected;
     
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      viewCarteClientFidele();
    }    

    @FXML
    private void btnAjouterOnAction(ActionEvent event) {
               AddEnseigneController apc = new AddEnseigneController();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/tm/client/AddCarteFidelite.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            AddCarteFideliteController addCarteFideliteController = fxmlLoader.getController();
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

    @FXML
    private void btnModifierOnAction(ActionEvent event) {
        UpdateCarteFideliteController apc = new UpdateCarteFideliteController();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/tm/client/UpdateCarteFidelite.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            UpdateCarteFideliteController updateCarteFideliteController = fxmlLoader.getController();
            updateCarteFideliteController.tfidCarteFidelite.setText(Integer.toString(tblViewCarteClientFidele.getSelectionModel().getSelectedItem().getIdCarteFidelite()));
            updateCarteFideliteController.tfidClientFideleDesktop.setText(Integer.toString(tblViewCarteClientFidele.getSelectionModel().getSelectedItem().getIdClientFideleDesktop()));
            updateCarteFideliteController.tfidEnseigne.setText(Integer.toString(tblViewCarteClientFidele.getSelectionModel().getSelectedItem().getIdEnseigne()));
            updateCarteFideliteController.tfnombrePointFidelite.setText(Integer.toString(tblViewCarteClientFidele.getSelectionModel().getSelectedItem().getNombrePointFidele()));
            updateCarteFideliteController.tfdateCreationPointFidele.setValue(LocalDate.parse(tblViewCarteClientFidele.getSelectionModel().getSelectedItem().getDateCreationCarteFidelite()));
            
           
            

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

    @FXML
    private void btnSupprimerOnAction(ActionEvent event) {
      
            
            
            
            if (tblViewCarteClientFidele.getSelectionModel().isEmpty())
        {Alert alert = new Alert(Alert.AlertType.ERROR);
alert.setContentText("Veuillez selectionner un client");
Optional<ButtonType> result = alert.showAndWait();}
        else{
        Alert alert = new Alert(Alert.AlertType.WARNING);
alert.setContentText("etes vous sur de vouloir supprimer le client ?");
Optional<ButtonType> result = alert.showAndWait();
       if (result.get() == ButtonType.OK){
            int id = tblViewCarteClientFidele.getSelectionModel().getSelectedItem().getIdCarteFidelite();
            ICarteFideliteDAO carteDAO = new CarteFideliteDAO();
            carteDAO.deleteCarteFidelite(id);
            viewCarteClientFidele();
       }
    }
    }
    

    


    
    public void viewCarteClientFidele(){
        
        ICarteFideliteDAO carteDAO= new CarteFideliteDAO();
            tblClmidCarteFidelite.setCellValueFactory(new PropertyValueFactory<>("idCarteFidelite"));
            tblClmidClientFideleDesktop.setCellValueFactory(new PropertyValueFactory<>("idClientFideleDesktop"));
            tblClmidEnseigne.setCellValueFactory(new PropertyValueFactory<>("idEnseigne"));
            tblClmnombrePointFidele.setCellValueFactory(new PropertyValueFactory<>("nombrePointFidele"));
            tblClmdateCreationCarteFidelite.setCellValueFactory(new PropertyValueFactory<>("dateCreationCarteFidelite"));
            
            
            tblViewCarteClientFidele.setItems(carteDAO.DisplayAllCarteClientFidele());
           // tblView.getColumns().addAll(tblClmIDArticle);
    }
    CarteFideliteDAO carte = new CarteFideliteDAO();
     ObservableList<CarteFidelite> listcartefidelite;



  @FXML
    private void actionsearch(KeyEvent event) {
         listcartefidelite= carte.findCarteFideliteBynombrePointFidele(Integer.parseInt(tfSearch.getText()));
        tblViewCarteClientFidele.setItems(listcartefidelite);
        
    }
    
    @FXML
    private void RefreshOnAction(ActionEvent event) {
viewCarteClientFidele();
tblViewCarteClientFidele.refresh();
    }
}
   