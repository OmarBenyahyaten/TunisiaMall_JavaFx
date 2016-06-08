/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.enseigne;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import org.controlsfx.dialog.Dialogs;
import tm.dao.classes.EnseigneDAO;
import tm.dao.classes.UserDAO;
import tm.dao.interfaces.IEnseigneDAO;
import tm.dao.interfaces.IUserDAO;
import tm.entities.Enseigne;
import tm.entities.User;
import tm.technique.HttpDownloadUtility;
import tm.technique.Session;

/**
 * FXML Controller class
 *
 * @author MANAI Yosr
 */
public class GEnseigneController implements Initializable {

    
    @FXML
    private TableView<Enseigne> tblViewEnseigne;
    
    @FXML
    private TextField tfSearch;
    
    
    @FXML
    private ComboBox cbSoteViewNom;
    @FXML
    private ComboBox cbSoteViewEntreprise;
    @FXML
    private ComboBox cbSoteViewStore;
    @FXML
    private ComboBox cbSoteViewOuverture;
     @FXML
    private ComboBox cbSoteViewFermetture;
    
    
    
    @FXML
    private TableColumn<Enseigne, Integer> tblClmId;
    
    @FXML
    private TableColumn<Enseigne, String> tblClmDescription;
    
    @FXML
    private TableColumn<Enseigne, String> tblClmNom;

    @FXML
    private TableColumn<Enseigne, String> tblClmStore;
    
    @FXML
    private TableColumn<Enseigne, String> tblClmLogo;
    @FXML
    private TableColumn<Enseigne, String> tblClmFermetture;

    @FXML
    private TableColumn<Enseigne, String> tblClmOuverture;

    @FXML
    private TableColumn<Enseigne, String> tblClmEntreprise;
     
      @FXML
    private TableColumn<Enseigne, String> tblClmCategorie;
       @FXML
    private TableColumn<Enseigne, String> tblClmTel;
    
    ObservableList<Enseigne> listeEnseignes;            

    
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        viewAllEnseignes();
                tblViewEnseigne.refresh();

    }    
    
    
    @FXML
    private void btnRefreshOnAction(ActionEvent event) {
             
        viewAllEnseignes();
        tblViewEnseigne.refresh();
    }
    
    
    
    @FXML
    private void tfSearchOnKeyReleased(KeyEvent event) {
        EnseigneDAO enseigneDAO=new  EnseigneDAO();
        listeEnseignes = enseigneDAO.findEnseigne(tfSearch.getText());
        tblViewEnseigne.setItems(listeEnseignes);
        tblViewEnseigne.refresh();
    }
    
    @FXML
    private void btnAddNewOnAction(ActionEvent event) {
        AddEnseigneController apc = new AddEnseigneController();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/tm/enseigne/AddEnseigne.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            AddEnseigneController addEnseigneController = fxmlLoader.getController();
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
        
        UpdateEnseigneController uec  =new UpdateEnseigneController();
        
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/tm/enseigne/UpdateEnseigne.fxml"));
        
        
       
        try {
          
            
            if(!tblViewEnseigne.getSelectionModel().isEmpty()){
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            UpdateEnseigneController updateEnseigneController = fxmlLoader.getController();
//Image i = new Image("");
            updateEnseigneController.tfidEnseigne.setText(Integer.toString(tblViewEnseigne.getSelectionModel().getSelectedItem().getIdEnseigne()));
            updateEnseigneController.tfNom.setText(tblViewEnseigne.getSelectionModel().getSelectedItem().getNom());
            updateEnseigneController.tfTel.setText(Integer.toString(tblViewEnseigne.getSelectionModel().getSelectedItem().getTel()));
            updateEnseigneController.tfEntreprise.setText(tblViewEnseigne.getSelectionModel().getSelectedItem().getEntreprise());
            updateEnseigneController.tfStore.setText(Integer.toString(tblViewEnseigne.getSelectionModel().getSelectedItem().getStore()));
            updateEnseigneController.cbOuverture.setValue(tblViewEnseigne.getSelectionModel().getSelectedItem().getHeureOuverture());
            updateEnseigneController.cbFermetture.setValue(tblViewEnseigne.getSelectionModel().getSelectedItem().getHeureFermetture());
            updateEnseigneController.cbCategorie.setValue(tblViewEnseigne.getSelectionModel().getSelectedItem().getCategorie());
            updateEnseigneController.taDescription.setText(tblViewEnseigne.getSelectionModel().getSelectedItem().getDescription());
            updateEnseigneController.tfLogo.setText(tblViewEnseigne.getSelectionModel().getSelectedItem().getSrcLogo());
            
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
    
    public void download(){ 

    Enseigne enseigne=new Enseigne();
            IEnseigneDAO enseigneDAO=new EnseigneDAO();
            listeEnseignes = enseigneDAO.DisplayAllEnseignes();
for (Enseigne item : listeEnseignes) {

                        String fileURL = "https://tunmall.azurewebsites.net/tnmall/uploads/"+item.getSrcLogo();
                System.out.println(fileURL);
                String saveDir = System.getProperty("user.dir");
                saveDir=saveDir+"\\src\\images";
                System.out.println(saveDir);
                File f =  new File("src/images/"+item.getSrcLogo());
                
                            System.out.println(f.exists());
if (!f.exists())
                   {
                  

                            try {
                                if(HttpDownloadUtility.downloadFile(fileURL, saveDir))
                                {
                                     saveDir = System.getProperty("user.dir");
                             }
                            } catch (IOException ex) {
                                Logger.getLogger(GEnseigneController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
    }}
    
    public void viewAllEnseignes() {
        download();
            Enseigne enseigne=new Enseigne();
            IEnseigneDAO enseigneDAO=new EnseigneDAO();
            
            tblViewEnseigne.setItems(enseigneDAO.DisplayAllEnseignes());
            
            tblClmId.setCellValueFactory(new PropertyValueFactory<>("idEnseigne"));
            tblClmNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            tblClmEntreprise.setCellValueFactory(new PropertyValueFactory<>("entreprise"));
            tblClmStore.setCellValueFactory(new PropertyValueFactory<>("store"));
            tblClmTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
            tblClmOuverture.setCellValueFactory(new PropertyValueFactory<>("heureOuverture"));
            tblClmFermetture.setCellValueFactory(new PropertyValueFactory<>("heureFermetture"));
            tblClmCategorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
            tblClmDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

            tblClmLogo.setCellValueFactory(new PropertyValueFactory<>("srcLogo"));

           tblClmLogo.setCellFactory(new Callback<TableColumn<Enseigne, String>, TableCell<Enseigne, String>>() {
            @Override
            public TableCell<Enseigne, String> call(TableColumn<Enseigne, String> col) {
                final TableCell<Enseigne, String> cell = new TableCell<Enseigne, String>() {
                    @Override
                    public void updateItem(String firstName, boolean empty) {
                        super.updateItem(firstName, empty);
                        if (empty) {
                            setText(null);
                        } else {
                            try {
                                System.out.println("aaaaa :"+firstName);
                               
                                File ff=new File("src/images/"+firstName);
                                Image profil =new Image(ff.toURI().toURL().toExternalForm());
                                
                                ImageView imageView = new ImageView(profil);
                                imageView.setFitHeight(50);
                                imageView.setFitWidth(50);  
                                setGraphic(imageView);
                            } catch (MalformedURLException ex) {
                                Logger.getLogger(GEnseigneController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                };
return cell; }});

            
        }
    
      
    @FXML
    private void btnSupprimerOnAction(ActionEvent event) {
      if (tblViewEnseigne.getSelectionModel().isEmpty())
      {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Veuillez Selectionner une enseigne !");

            alert.showAndWait();}else{
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Etes-vous sûr de vouloir supprimer?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            IEnseigneDAO enseigneDAO = new EnseigneDAO();
            enseigneDAO.deleteEnseigne(tblViewEnseigne.getSelectionModel().getSelectedItem().getIdEnseigne());
            viewAllEnseignes();
        } 
        else {
    // ... user chose CANCEL or closed the dialog
        }
        
        
      }
        
    }
        
    
    
//     @FXML
//    private void btnSearchByNomOnAction(ActionEvent event) {
//        IEnseigneDAO enseigneDAO = new EnseigneDAO();
//        
//        enseigneDAO.findEnseigneByNom(tblViewEnseigne.getSelectionModel().getSelectedItem().getNom());
//        viewAllEnseignes();
//        
//    }
    
    @FXML
    public void cbSoteViewNomOnClick(Event event) {
        cbSoteViewNom.getItems().clear();
        cbSoteViewNom.getItems().removeAll();
        cbSoteViewNom.setPromptText("             Nom");
        IEnseigneDAO enseigneDAO = new EnseigneDAO();
   
        
        List<String> listeNom = new ArrayList<>();
        listeNom = enseigneDAO.DisplayCBNom();
                    
            for (String type : listeNom) {
               cbSoteViewNom.getItems().addAll(type);
               
               
            }  

                                
    }
    
     @FXML
    public void cbSoteViewNomOnAction(Event event) {

        
        
        EnseigneDAO enseigneDAO=new EnseigneDAO();
        
                    
        
        listeEnseignes= enseigneDAO.findEnseigneByNom(cbSoteViewNom.getSelectionModel().getSelectedItem().toString());
         cbSoteViewNomOnClick(event);
        tblViewEnseigne.setItems(listeEnseignes);
        tblViewEnseigne.refresh();
        
        
    }
    
    
    @FXML
    public void cbSoteViewEntrepriseOnClick(Event event) {
        cbSoteViewEntreprise.getItems().clear();
        cbSoteViewEntreprise.getItems().removeAll();
        cbSoteViewEntreprise.setPromptText("Entreprise");
        IEnseigneDAO enseigneDAO = new EnseigneDAO();
                    
        List<String> listeEnseigne = new ArrayList<>();
        listeEnseigne = enseigneDAO.DisplayCBEntreprise();
                    
            for (String type : listeEnseigne) {
               cbSoteViewEntreprise.getItems().addAll(type);
               
            }       
    
    }
    
    @FXML
    public void cbSoteViewEntrepriseOnAction(Event event) {
     
         
        EnseigneDAO enseigneDAO=new EnseigneDAO();
                
        listeEnseignes= enseigneDAO.findEnseigneByEntreprise(cbSoteViewEntreprise.getSelectionModel().getSelectedItem().toString());

        cbSoteViewEntrepriseOnClick(event);

        tblViewEnseigne.setItems(listeEnseignes);
    }
    
    
    
     @FXML
    public void cbSoteViewStoreOnClick(Event event) {
        cbSoteViewStore.getItems().clear();
        cbSoteViewStore.getItems().removeAll();
        cbSoteViewStore.setPromptText("      Store");
        IEnseigneDAO enseigneDAO = new EnseigneDAO();
                    
        List<String> listeEnseigne = new ArrayList<>();
        listeEnseigne = enseigneDAO.DisplayCBStore();
                    
            for (String type : listeEnseigne) {
               cbSoteViewStore.getItems().addAll(type);
            }       
    
    }
    
    
    @FXML
    public void cbSoteViewStoreOnAction(Event event) {
   
      
        
        
        
        EnseigneDAO enseigneDAO=new EnseigneDAO();
        
        listeEnseignes= enseigneDAO.findEnseigneByStore(cbSoteViewStore.getSelectionModel().getSelectedItem().toString());
        
        cbSoteViewStoreOnClick(event);

        tblViewEnseigne.setItems(listeEnseignes);
    }
    
    
    
    @FXML
    public void cbSoteViewOuvertureOnClick(Event event) {
        cbSoteViewOuverture.getItems().clear();
        cbSoteViewOuverture.getItems().removeAll();
        cbSoteViewOuverture.setPromptText("Hr. Ouverture");
        IEnseigneDAO enseigneDAO = new EnseigneDAO();
                    
        List<String> listeEnseigne = new ArrayList<>();
        listeEnseigne = enseigneDAO.DisplayCBOuverture();
                    
            for (String type : listeEnseigne) {
               cbSoteViewOuverture.getItems().addAll(type);
            }       
    
    }
    
    
    @FXML
    public void cbSoteViewOuvertureOnAction(Event event) {
   
        
        
        EnseigneDAO enseigneDAO=new EnseigneDAO();
        
        listeEnseignes= enseigneDAO.findEnseigneByHeureOuverture(cbSoteViewOuverture.getSelectionModel().getSelectedItem().toString());
         
        cbSoteViewOuvertureOnClick(event);

        tblViewEnseigne.setItems(listeEnseignes);
    }
    
    
    @FXML
    public void cbSoteViewFermettureOnClick(Event event) {
        cbSoteViewFermetture.getItems().clear();
        cbSoteViewFermetture.getItems().removeAll();
        cbSoteViewFermetture.setPromptText("Hr. Fermeture");
        IEnseigneDAO enseigneDAO = new EnseigneDAO();
                    
        List<String> listeEnseigne = new ArrayList<>();
        listeEnseigne = enseigneDAO.DisplayCBFermetture();
                    
            for (String type : listeEnseigne) {
               cbSoteViewFermetture.getItems().addAll(type);
            }       
    
    }
    
    @FXML
    public void cbSoteViewFermettureOnAction(Event event) {
   
        
        EnseigneDAO enseigneDAO=new EnseigneDAO();
        
        listeEnseignes= enseigneDAO.findEnseigneByHeureFermetture(cbSoteViewFermetture.getSelectionModel().getSelectedItem().toString());
        
        cbSoteViewFermettureOnClick(event);

        tblViewEnseigne.setItems(listeEnseignes);
    }
    
    
    
    
}
