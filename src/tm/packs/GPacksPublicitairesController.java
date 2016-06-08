/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.packs;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tm.dao.classes.EnseigneDAO;
import tm.dao.classes.PackPublicitaireAcheteDAO;
import tm.dao.classes.PacksPublicitairesDAO;
import tm.dao.interfaces.IEnseigneDAO;
import tm.dao.interfaces.IPacksPublicitairesDAO;
import tm.entities.Enseigne;
import tm.entities.PackPublicitaire;
import tm.entities.PackPublicitaireAchete;
import tm.technique.Session;


/**
 * FXML Controller class
 *
 * @author Z500
 */
public class GPacksPublicitairesController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    private TableColumn<PackPublicitaire, Date> tblClmDebut;

   
    @FXML
    private TableColumn<PackPublicitaire, Integer> tblClmId;

    @FXML
    private Button btnDesabonner;

    @FXML
    private TableColumn<PackPublicitaire, Date> tblClmFin;

    @FXML
    private Button btnAbonnerEnseigne;

    @FXML
    private TableColumn<PackPublicitaire, Float> tblClmPrix;

   

    @FXML
    private TableView<PackPublicitaire> tblViewPacks;

    

    @FXML
    private TableColumn<PackPublicitaire, String> tblClmPage;

    @FXML
    private TableColumn<PackPublicitaire, String> tblClmPosition;
    @FXML
    private ComboBox cbEnseigneAbonner;

        ObservableList<Enseigne> listeEnseignes;            

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        viewAllPacks();
    }    
      
 
    @FXML
    private void btnAbonnerEnseigneOnAction(ActionEvent event){
                if(!tblViewPacks.getSelectionModel().isEmpty()){

                cbEnseigneAbonner.setVisible(true);
                tblViewPacks.getSelectionModel().getSelectedItem();
                }
                
                 else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Veuillez Selectionner une enseigne !");

            alert.showAndWait();
        }

    }
        
    
    public void viewAllPacks() {
            PackPublicitaire pack=new PackPublicitaire();
            IPacksPublicitairesDAO packDAO=new PacksPublicitairesDAO();
            
            tblViewPacks.setItems(packDAO.DisplayAllPacks());
            
            tblClmId.setCellValueFactory(new PropertyValueFactory<>("idPackPublicitaire"));
            tblClmDebut.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
            tblClmFin.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
            tblClmPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
            tblClmPage.setCellValueFactory(new PropertyValueFactory<>("page"));
            tblClmPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            
            
        }
    
    
     @FXML
    private void tfSearchOnKeyReleased(ActionEvent event) {

    }
    
    @FXML
    public void cbViewEnseigneAbonnerOnClick(Event event) {
        cbEnseigneAbonner.getItems().clear();
        cbEnseigneAbonner.getItems().removeAll();
        cbEnseigneAbonner.setPromptText("Enseignes à Abonner");
        IEnseigneDAO enseigneDAO = new EnseigneDAO();
   
        
        List<String> listeEnseignes = new ArrayList<>();
        listeEnseignes = enseigneDAO.DisplayCBNom();
                    
            for (String type : listeEnseignes) {
               cbEnseigneAbonner.getItems().addAll(type);
               
            }  

                                
    }
    @FXML
    public void cbViewEnseigneAbonnerOnAction(Event event) {
         
        PackPublicitaireAcheteDAO packAcheteDAO=new PackPublicitaireAcheteDAO();
        EnseigneDAO enseigneDAO=new EnseigneDAO();
        PacksPublicitairesDAO pkDAO=new PacksPublicitairesDAO();
        
        
        
         PackPublicitaireAchete pkAchete=new PackPublicitaireAchete();
        
        pkAchete.setIdPackPublicitaire(tblViewPacks.getSelectionModel().getSelectedItem().getIdPackPublicitaire());
        pkAchete.setIdEnseigne(enseigneDAO.findByNom(cbEnseigneAbonner.getSelectionModel().getSelectedItem().toString()).getIdEnseigne());
 

        if (packAcheteDAO.abonnerEnseigne(pkAchete))
        {        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
alert.setHeaderText("Abonnement reussi");
Optional<ButtonType> result = alert.showAndWait();}else{Alert alert = new Alert(Alert.AlertType.ERROR);
alert.setTitle("Erreur");
alert.setHeaderText("Erreur lors de l'abonnement");
    }
    
    
    
}
}