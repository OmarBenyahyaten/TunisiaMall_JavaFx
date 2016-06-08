/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.stock;

import java.awt.Desktop.Action;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import org.controlsfx.dialog.Dialogs;
import tm.dao.classes.EnseigneDAO;
import tm.dao.classes.StockDAO;
import tm.dao.classes.TypeArticleDAO;
import tm.dao.interfaces.IStockDAO;
import tm.dao.interfaces.ITypeArticleDAO;
import tm.entities.Enseigne;
import tm.entities.Stock;
import tm.entities.TypeArticle;
import tm.technique.HttpDownloadUtility;


/**
 * FXML Controller class
 *
 * @author omarblythe
 */
public class GStockController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TableView<Stock> tblView;
     @FXML
    private TableColumn<Stock,String> tblClmIDArticle;
      @FXML
    private TableColumn<Stock,Integer> tblClmQuantite;
      @FXML
    private TableColumn<Stock, Integer> tblClmIDEnseigne;
    @FXML
    private TableColumn<Stock, String> tblClmNomArticle;
    @FXML
    private TableColumn<Stock,Float> tblClmPrixAchat;
    @FXML
    private TableColumn<Stock, Integer> tblClmIDType;
    @FXML
    private TableColumn<Stock, Integer> tblClmTVA;
    @FXML
    private TableColumn<Stock, Float> tblClmPrixVenteGros;
    @FXML
    private TableColumn<Stock, Float> tblClmPrixVenteDetail;
     @FXML
    private TableColumn<Stock, String> tblClmImageF;
      @FXML
    private TableColumn<Stock, String> tblClmImageS;

@FXML TextField tfSearch ;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 viewStock();
    download();
     }    
    
    @FXML
    private void btnDeleteOnAction(ActionEvent event) {
        
        if (tblView.getSelectionModel().isEmpty())
        {Alert alert = new Alert(Alert.AlertType.ERROR);
alert.setContentText("Veuillez selectionner un article");
Optional<ButtonType> result = alert.showAndWait();}
        else{
        Alert alert = new Alert(Alert.AlertType.WARNING);
alert.setContentText("etes vous sur de vouloir supprimer l'article ?");
Optional<ButtonType> result = alert.showAndWait();
       if (result.get() == ButtonType.OK){
            int id = tblView.getSelectionModel().getSelectedItem().getIdArticle();
            IStockDAO stockDAO = new StockDAO();
            stockDAO.deleteSotck(id);
            viewStock();
        
       }
        }
       
           

    }
    public void download(){ IStockDAO stockDAO = new StockDAO();

            ObservableList<Stock> listStock;  
            listStock = stockDAO.DisplayStocks(tfSearch.getText());
for (Stock item : listStock) {
        System.out.println("**********************IMAGE f***********************");

                        String fileURL = "https://tunmall.azurewebsites.net/tnmall/uploads/"+item.getImageF();
                System.out.println(fileURL);
                String saveDir = System.getProperty("user.dir");
                saveDir=saveDir+"\\src\\images";
                System.out.println(saveDir);
                File f =  new File("src/images/"+item.getImageF());
                
                            System.out.println(f.exists());
if (!f.exists())
                   {
                  

                            try {
                                if(HttpDownloadUtility.downloadFile(fileURL, saveDir))
                                {
                                     saveDir = System.getProperty("user.dir");
                             }
                            } catch (IOException ex) {
                                Logger.getLogger(GStockController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
    System.out.println("**********************IMAGE s***********************");
   fileURL = "https://tunmall.azurewebsites.net/tnmall/uploads/"+item.getImageS();
                System.out.println(fileURL);
                 saveDir = System.getProperty("user.dir");
                saveDir=saveDir+"\\src\\images";
                System.out.println(saveDir);
                File f1 =  new File("src/images/"+item.getImageS());
                
                            System.out.println(f1.exists());
if (!f1.exists())
                   {
                  

                            try {
                                if(HttpDownloadUtility.downloadFile(fileURL, saveDir))
                                {
                                     saveDir = System.getProperty("user.dir");
                             }
                            } catch (IOException ex) {
                                Logger.getLogger(GStockController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

}}
    public void viewStock()
    {
                IStockDAO stockDAO = new StockDAO();
download();
           
            tblClmIDArticle.setCellValueFactory(new PropertyValueFactory<>("idArticle"));
            tblClmNomArticle.setCellValueFactory(new PropertyValueFactory<>("nomArticle"));
            tblClmIDEnseigne.setCellValueFactory(new PropertyValueFactory<>("Enseigne"));
            tblClmIDType.setCellValueFactory(new PropertyValueFactory<>("idType"));
            tblClmPrixAchat.setCellValueFactory(new PropertyValueFactory<>("prixAchat"));
            tblClmPrixVenteDetail.setCellValueFactory(new PropertyValueFactory<>("prixVenteDetail"));
            tblClmPrixVenteGros.setCellValueFactory(new PropertyValueFactory<>("prixVenteGros"));
            tblClmQuantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
            tblClmTVA.setCellValueFactory(new PropertyValueFactory<>("tva"));
             tblClmImageF.setCellValueFactory(new PropertyValueFactory<>("imageF"));
             tblClmImageF.setCellFactory(new Callback<TableColumn<Stock, String>, TableCell<Stock, String>>() {
            @Override
            public TableCell<Stock, String> call(TableColumn<Stock, String> col) {
                final TableCell<Stock, String> cell = new TableCell<Stock, String>() {
                    @Override
                    public void updateItem(String firstName, boolean empty) {
                        super.updateItem(firstName, empty);
                        if (empty) {
                            setText(null);
                        } else {
                         
       
                            try {
                                System.out.println("aaaaa :"+firstName);
                                //  Image profil  = new Image(getClass().getResourceAsStream("/images/"+firstName));
                                //Image profil = new Image(getClass().getResource("/images/"+firstName).toString(), true);
                                //  Image profil = new Image( getClass().getResource( "/images/"+firstName).toExternalForm());
                                File ff=new File("src/images/"+firstName);
                                Image profil =new Image(ff.toURI().toURL().toExternalForm());
                                
                                ImageView imageView = new ImageView(profil);
                                imageView.setFitHeight(50);
                                imageView.setFitWidth(50);  
                                setGraphic(imageView);
                            } catch (MalformedURLException ex) {
                                Logger.getLogger(GStockController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                                    
                   }
                  }
                    
                };
return cell; }});
             tblClmImageS.setCellValueFactory(new PropertyValueFactory<>("imageS"));
             tblClmImageS.setCellFactory(new Callback<TableColumn<Stock, String>, TableCell<Stock, String>>() {
            @Override
            public TableCell<Stock, String> call(TableColumn<Stock, String> col) {
                final TableCell<Stock, String> cell = new TableCell<Stock, String>() {
                    @Override
                    public void updateItem(String firstName, boolean empty) {
                        super.updateItem(firstName, empty);
                        if (empty) {
                            setText(null);
                        } else {
                               
                            try {
                                System.out.println("aaaaa :"+firstName);
                                // Image profil = new Image(getClass().getResource("/images/"+firstName).toString(), true);
                                //  Image profil  = new Image(getClass().getResourceAsStream("/images/"+firstName));
                                //  Image profil = new Image( getClass().getResource( "/images/"+firstName).toExternalForm());
                                File ff=new File("src/images/"+firstName);
                                Image profil =new Image(ff.toURI().toURL().toExternalForm());
                                ImageView imageView = new ImageView(profil);
                                imageView.setFitHeight(50);
                                imageView.setFitWidth(50);
                                setGraphic(imageView);  
                            } catch (MalformedURLException ex) {
                                Logger.getLogger(GStockController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                   
                 }
                    }
                };
return cell; }});
            
                                tblView.setItems(stockDAO.DisplayStocks(tfSearch.getText()));
tblView.refresh();
           // tblView.getColumns().addAll(tblClmIDArticle);

    }
     @FXML
    private void btnAddNewOnAction(ActionEvent event) {
        AddStockController apc = new AddStockController();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/tm/stock/AddStock.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            AddStockController addStockController = fxmlLoader.getController();
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
    private void btnUpdateOnAction(ActionEvent event) {
        if (tblView.getSelectionModel().isEmpty()){
          Alert alert = new Alert(Alert.AlertType.ERROR);
alert.setContentText("Veuillez selectionner un article");
Optional<ButtonType> result = alert.showAndWait();
        }else{
        UpdateStockController apc = new UpdateStockController();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/tm/stock/UpdateStock.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            UpdateStockController updateStockController = fxmlLoader.getController();
            updateStockController.tfNom.setText(tblView.getSelectionModel().getSelectedItem().getNomArticle());
            updateStockController.tfPrixAchat.setText(Float.toString(tblView.getSelectionModel().getSelectedItem().getPrixAchat()));
            updateStockController.tfPrixVenteDetail.setText(Float.toString(tblView.getSelectionModel().getSelectedItem().getPrixVenteDetail()));
            updateStockController.tfPrixVenteGros.setText(Float.toString(tblView.getSelectionModel().getSelectedItem().getPrixVenteGros()));
            updateStockController.tfQuantite.setText(Integer.toString(tblView.getSelectionModel().getSelectedItem().getQuantite()));
            updateStockController.tfTva.setText(Integer.toString(tblView.getSelectionModel().getSelectedItem().getTva()));
            updateStockController.cbEnseigne.setValue(tblView.getSelectionModel().getSelectedItem().getEnseigne());
            updateStockController.tfIDArticle.setText(Integer.toString(tblView.getSelectionModel().getSelectedItem().getIdArticle()));
            ITypeArticleDAO typeDAO = new TypeArticleDAO();
            TypeArticle t = new TypeArticle();
                    t=typeDAO.getTypeByID(tblView.getSelectionModel().getSelectedItem().getIdType());
            updateStockController.cbGenre.setValue(t.getGenre());
            updateStockController.cbCategorie.setValue(t.getCategorie());
            updateStockController.cbTaille.setValue(t.getTaille());

            Stage nStage = new Stage();
//            addProductController.addSupplyerStage(nStage);
            nStage.setScene(scene);
            nStage.initModality(Modality.APPLICATION_MODAL);
            nStage.initStyle(StageStyle.TRANSPARENT);
            nStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }}
     @FXML
    private void btnRefreshOnAction(ActionEvent event) {
    viewStock();
    tblView.refresh();
    }
     
   @FXML
    private void tfSearchOnKeyReleased(KeyEvent event) {
        IStockDAO stockDAO=new  StockDAO();
        tblView.setItems(stockDAO.DisplayStocks(tfSearch.getText()));
    }
    
}
