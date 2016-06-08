package tm.enseigne;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tm.dao.classes.EnseigneDAO;
import tm.dao.classes.ResponsableBoutiqueDAO;
import tm.dao.classes.TypeArticleDAO;
import tm.dao.interfaces.IEnseigneDAO;
import tm.dao.interfaces.IResponsableBoutiqueDAO;
import tm.dao.interfaces.ITypeArticleDAO;
import tm.entities.Enseigne;
import tm.entities.ResponsableBoutique;


public class GResponsableBoutiqueController implements Initializable{

    
     @FXML
    private TextField tfLogin;

    @FXML
    private TextField tfPrenom;

    @FXML
    private TextArea taAdresse;

    @FXML
    private TextField tfTelephone;

    @FXML
    private TextField tfNom;

    @FXML
    private TextField tfPwd;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfEnseigne;
    
    
    @FXML
    private ComboBox<String> cbDebut;
    @FXML
    private ComboBox<String> cbFin;
    
    @FXML
    private TableColumn<ResponsableBoutique, String> tblClmDebut;

    @FXML
    private TableColumn<ResponsableBoutique, Integer> tblClmTelephone;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<ResponsableBoutique, String> tblClmNom;

    @FXML
    private TableColumn<ResponsableBoutique, String> tblClmFin;

    @FXML
    private TableColumn<ResponsableBoutique, String> tblClmAdresse;

    @FXML
    private ComboBox<String> cbSoteViewEnseigne;

  

    @FXML
    private Button btnRefresh;

    @FXML
    private Button btnAddNew;

    @FXML
    private TableColumn<ResponsableBoutique, Integer> tblClmId;

    @FXML
    private TableColumn<ResponsableBoutique, String> tblClmEnseigne;

    @FXML
    private TextField tfSearch;

    @FXML
    private TableView<ResponsableBoutique> tblViewResponsable;

    @FXML
    private Button btnSupprimer;

    @FXML
    private TableColumn<ResponsableBoutique, String> tblClmPrenom;
    
    ObservableList<ResponsableBoutique> listeResponsables;            

    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        viewAllResponsables();    
    }
    
    
    @FXML
    void btnAddNewOnAction(ActionEvent event) {
       
        AddRespBoutiqueController apc = new AddRespBoutiqueController();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/tm/enseigne/AddRespBoutique.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            AddRespBoutiqueController addRespBoutiqueController = fxmlLoader.getController();
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
    void btnModifierOnAction(ActionEvent event) {
        UpdateResponsableController uec  =new UpdateResponsableController();
        
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/tm/enseigne/UpdateResponsable.fxml"));
        
        
        try {
          
            
            if(!tblViewResponsable.getSelectionModel().isEmpty()){
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            
            
            UpdateResponsableController updateResponsableController = fxmlLoader.getController();
            
            
            updateResponsableController.tfid.setText(Integer.toString(tblViewResponsable.getSelectionModel().getSelectedItem().getId()));

            updateResponsableController.tfNom.setText(tblViewResponsable.getSelectionModel().getSelectedItem().getNom());
            updateResponsableController.tfPrenom.setText(tblViewResponsable.getSelectionModel().getSelectedItem().getPrenom());
            updateResponsableController.tfTelephone.setText(Integer.toString(tblViewResponsable.getSelectionModel().getSelectedItem().getTel()));
            updateResponsableController.cbDebut.setValue(tblViewResponsable.getSelectionModel().getSelectedItem().getHeureDebut());
            updateResponsableController.cbFin.setValue(tblViewResponsable.getSelectionModel().getSelectedItem().getHeureFin());
            updateResponsableController.taAdresse.setText(tblViewResponsable.getSelectionModel().getSelectedItem().getAdresse());
            updateResponsableController.cbEnseigne.setValue(tblViewResponsable.getSelectionModel().getSelectedItem().getEnseigne());

            Stage nStage = new Stage();
//            addProductController.addSupplyerStage(nStage);
            nStage.setScene(scene);
            nStage.initModality(Modality.APPLICATION_MODAL);
            nStage.initStyle(StageStyle.TRANSPARENT);
            nStage.show();
        }
            else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Veuillez Selectionner une enseigne !");

            alert.showAndWait();
        }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        


    }

    @FXML
    void btnSupprimerOnAction(ActionEvent event) {
        if(!tblViewResponsable.getSelectionModel().isEmpty()){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Etes-vous sûr de vouloir supprimer?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            IResponsableBoutiqueDAO respBoutiqueDAO = new ResponsableBoutiqueDAO();
            respBoutiqueDAO.deleteResponsableBoutique(tblViewResponsable.getSelectionModel().getSelectedItem().getId());
            viewAllResponsables();
        } 
        else {
    // ... user chose CANCEL or closed the dialog
        }
        }
        else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Veuillez Selectionner une enseigne !");

            alert.showAndWait();
        }
            
        
    }

    @FXML
    void tfSearchOnKeyReleased(Event event) {
         ResponsableBoutiqueDAO respDAO=new  ResponsableBoutiqueDAO();
        //listeResponsables = respDAO.findResponsable(tfSearch.getText());
        tblViewResponsable.setItems(respDAO.findResponsable(tfSearch.getText()));
        tblViewResponsable.refresh();

    }

    


   @FXML
    private void btnRefreshOnAction(ActionEvent event) {
             
        viewAllResponsables();
        tblViewResponsable.refresh();
    }

    
    
    public void viewAllResponsables() {
            ResponsableBoutique respBoutique=new ResponsableBoutique();
            IResponsableBoutiqueDAO respBoutiqueDAO=new ResponsableBoutiqueDAO();
            
            tblViewResponsable.setItems(respBoutiqueDAO.DisplayAllResponsableBoutique());
            
            tblClmId.setCellValueFactory(new PropertyValueFactory<>("id"));
            tblClmNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            tblClmAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            tblClmPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            tblClmTelephone.setCellValueFactory(new PropertyValueFactory<>("tel"));
            tblClmDebut.setCellValueFactory(new PropertyValueFactory<>("heureDebut"));
            tblClmFin.setCellValueFactory(new PropertyValueFactory<>("heureFin"));
            tblClmEnseigne.setCellValueFactory(new PropertyValueFactory<>("Enseigne"));


        }

    
    
    
    @FXML
    public void cbSoteViewEnseigneOnClick(MouseEvent event) {
        cbSoteViewEnseigne.getItems().clear();
        cbSoteViewEnseigne.getItems().removeAll();
        cbSoteViewEnseigne.setPromptText("Enseigne");
        IEnseigneDAO enseigneDAO = new EnseigneDAO();
   
        
        List<String> listeNom = new ArrayList<>();
        listeNom = enseigneDAO.DisplayCBNom();
                    
            for (String type : listeNom) {
               cbSoteViewEnseigne.getItems().addAll(type);
            }  

                                
    }
    
     @FXML
    public void cbSoteViewEnseigneOnAction(ActionEvent event) {

        
        //ResponsableBoutique respBoutique=new ResponsableBoutique();
            IResponsableBoutiqueDAO respBoutiqueDAO=new ResponsableBoutiqueDAO();
           
           System.out.println(cbSoteViewEnseigne.getSelectionModel().getSelectedItem());
            tblViewResponsable.setItems(respBoutiqueDAO.DisplayResponsableBoutique(cbSoteViewEnseigne.getSelectionModel().getSelectedItem()));
            
            tblClmId.setCellValueFactory(new PropertyValueFactory<>("id"));
            tblClmNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            tblClmAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            tblClmPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            tblClmTelephone.setCellValueFactory(new PropertyValueFactory<>("tel"));
            tblClmDebut.setCellValueFactory(new PropertyValueFactory<>("heureDebut"));
            tblClmFin.setCellValueFactory(new PropertyValueFactory<>("heureFin"));
            tblClmEnseigne.setCellValueFactory(new PropertyValueFactory<>("Enseigne"));

                    
       
        
        
    }
    
    
    
}
