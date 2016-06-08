/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.stock;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import jdk.nashorn.internal.runtime.regexp.RegExp;
import tm.dao.classes.EnseigneDAO;
import tm.dao.classes.StockDAO;
import tm.dao.classes.TypeArticleDAO;
import tm.dao.interfaces.IEnseigneDAO;
import tm.dao.interfaces.IStockDAO;
import tm.dao.interfaces.ITypeArticleDAO;
import tm.entities.Stock;
import tm.entities.TypeArticle;
import tm.entities.Vente;
import tm.technique.MultipartUtility;
import tm.technique.Session;

/**
 * FXML Controller class
 *
 * @author omarblythe
 */
public class AddStockController implements Initializable {

    
     @FXML private Button start;
	
	@FXML private ImageView imageView;
    /**
     * Initializes the controller class.
     */
     @FXML
    private Label lbVenteDetail;
      @FXML
    private Label lbNom;
       @FXML
    private Label lbEnseigne;
       @FXML
    private Label lbVenteGros;

    @FXML
    private Label lbTVA;

    @FXML
    private Label lbGenre;
     @FXML
    private Label lbQuantite;

    @FXML
    private Label lbAchat;
    @FXML
    private Label lbCategorie;
      @FXML
    private Label lbTaille;

   
@FXML
    private Button btnClose;

    @FXML
    private TextField tfPrixAchat;

    @FXML
    private TextField tfQuantite;

    @FXML
    private TextField tfNom;

    @FXML
    private ComboBox cbEnseigne;

    @FXML
    private TextField tfPrixVenteGros;

    @FXML
    private ComboBox cbCategorie;

    @FXML
    private ComboBox cbTaille;

    @FXML
    private TextField tfPrixVenteDetail;

    @FXML
    private TextField tfTva;

    @FXML
    private ComboBox cbGenre;
    @FXML
    private Button btnImageF;

@FXML
    private Button btnImageS;
 @FXML
    private TextField tfImageF;
 
  @FXML
    private TextField tfImageS;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbCategorie.setDisable(true);
        cbTaille.setDisable(true);
        cbCategorie.setValue("Choisir la categorie");
                cbGenre.setValue("Choisir le genre");
                        cbTaille.setValue("Choisir la taille");
                        cbEnseigne.setValue("Choisir l'enseigne");


    }    
        
@FXML
    private void StartOnAction(ActionEvent event) {
                         WebCamImageProvider imageProvider = new WebCamImageProvider();
				imageView.imageProperty().bind(imageProvider.imageProperty());
                                imageView.setStyle("-fx-border-color : red ; -fx-border : 4px ;");
				//fps.textProperty().bind(imageProvider.fpsProperty());
				
				imageProvider.setOnSucceeded(event1 -> {
                                addToStock(imageProvider.getQrCode());StartOnAction(event);});
				
				Thread t = new Thread(imageProvider);
				t.setDaemon(true);
				t.start();
    }
    public void addToStock(String code ){
        String result ;
       
    result = code.substring(0,code.indexOf("\n")-1);
            System.out.println("result=:"+result);
code=code.substring(code.indexOf("\n")+1, code.length());
    tfNom.setText(result);
    
     result = code.substring(0,code.indexOf("\n")-1);
code=code.substring(code.indexOf("\n")+1, code.length());
    
    cbGenre.setValue(result);
    cbGenre.setDisable(false);
     result = code.substring(0,code.indexOf("\n")-1);
code=code.substring(code.indexOf("\n")+1, code.length());
    
    cbCategorie.setValue(result);
        cbCategorie.setDisable(false);

     result = code.substring(0,code.indexOf("\n")-1);
code=code.substring(code.indexOf("\n")+1, code.length());
    
    cbTaille.setValue(result);
     cbTaille.setDisable(false);
     
    // result = code.substring(0,code.indexOf("\n")-1);
code=code.substring(code.indexOf("\n")+1, code.length());
        System.out.println(code);
    cbEnseigne.setValue(code);
    
    }
    public boolean control()
    {
        boolean test = true ;
        System.out.println("***************");
                  System.out.println(test);
if( tfQuantite.getText().isEmpty())
    {
        tfQuantite.setStyle("-fx-border-color: red ;");
         lbQuantite.setText("veuillez saisir la quantite");
      
    test=false;}else {

    try {
       int num = Integer.parseInt(tfQuantite.getText());
 tfQuantite.setStyle("-fx-border-color: green ;");
        lbQuantite.setText("");
    } catch (NumberFormatException e) {
         tfQuantite.setStyle("-fx-border-color: red ;");
                 lbQuantite.setText("veuillez saisir un nombre");


    test=false;
    }}
            System.out.println(test);
if( tfPrixAchat.getText().isEmpty())
    {
        tfPrixAchat.setStyle("-fx-border-color: red ;");
        lbAchat.setText("veuillez saisir le prix d'achat");
    test=false;
    
    }else {
     try {
       int num = Integer.parseInt(tfPrixAchat.getText());
        tfPrixAchat.setStyle("-fx-border-color: green ;");
        lbAchat.setText("");
    } catch (NumberFormatException e) {
    test=false;
            lbAchat.setText("veuillez saisir un nombre");
     tfPrixAchat.setStyle("-fx-border-color: red ;");

    }}
             System.out.println(test);

             
             
             if( tfPrixVenteDetail.getText().isEmpty())
        { tfPrixVenteDetail.setStyle("-fx-border-color: red ;");
        lbVenteDetail.setText("veuillez saisir le prix de vente detail");
        test=false;
        }
        else  {
           
    
      try {
       int num = Integer.parseInt(tfPrixVenteDetail.getText());
        tfPrixVenteDetail.setStyle("-fx-border-color: green ;");
                                lbVenteDetail.setText("");

    } catch (NumberFormatException e) {
    test=false;
            tfPrixVenteDetail.setStyle("-fx-border-color: red ;");
                        lbVenteDetail.setText("veuillez saisir un nombre");


    }}
              System.out.println(test);
 if( tfPrixVenteGros.getText().isEmpty())
    {
        tfPrixVenteGros.setStyle("-fx-border-color: red ;");
         lbVenteGros.setText("veuillez saisir le prix de vente gros");
      
    test=false;}else {
       try {
       int num = Integer.parseInt(tfPrixVenteGros.getText());
       tfPrixVenteGros.setStyle("-fx-border-color: green ;");
                               lbVenteGros.setText("");


    } catch (NumberFormatException e) {
               tfPrixVenteGros.setStyle("-fx-border-color: red ;");
                                              lbVenteGros.setText("veuillez saisir un nombre");


    test=false;
    }}
               System.out.println(test);

        if( tfTva.getText().isEmpty())
    {
        tfTva.setStyle("-fx-border-color: red ;");
         lbTVA.setText("veuillez saisir le TVA");
        
    test=false;}else {
        try {
       int num = Integer.parseInt(tfTva.getText());
       tfTva.setStyle("-fx-border-color: green ;");
                                      lbTVA.setText("");

    } catch (NumberFormatException e) {
               tfTva.setStyle("-fx-border-color: red ;");
               lbTVA.setText("veuillez saisir un nombre");
    test=false;
    }}
        
         
                System.out.println(test);
        System.out.println(cbGenre.getValue().toString());
    if(cbGenre.getValue().toString().equals("Choisir le genre")){
        cbGenre.setStyle("-fx-border-color: red ;");
                 lbGenre.setText("veuillez selectionner le genre");

    test=false;
    }else {cbGenre.setStyle("-fx-border-color: green ;");
                     lbGenre.setText("");
}
            System.out.println(test);

    if(cbCategorie.getValue().toString().equals("Choisir la categorie")){
        cbCategorie.setStyle("-fx-border-color: red ;");
               lbCategorie.setText("veuillez selectionner la categorie");

    test=false;
    } 
    else {cbCategorie.setStyle("-fx-border-color: green ;");
                   lbCategorie.setText("");
}
    
            System.out.println(test);

    if( tfNom.getText().isEmpty())
    {
        tfNom.setStyle("-fx-border-color: red ;");
                       lbNom.setText("veuillez saisir le nom d'article");

    test=false;}
    else {tfNom.setStyle("-fx-border-color: green ;");
                           lbNom.setText("");
}
    
            System.out.println(test);

    if(cbEnseigne.getValue().toString().equals("Choisir l'enseigne")){
   lbEnseigne.setText("veuillez selectionner l'enseigne");
    test=false;
    cbEnseigne.setStyle("-fx-border-color: red ;");
    }else {
           lbEnseigne.setText("");
    cbEnseigne.setStyle("-fx-border-color: green ;");
    }
    
            System.out.println(test);

    if(cbTaille.getValue().toString().equals("Choisir la taille")){
    test=false;
       lbTaille.setText("veuillez selectionner la taille");
    cbTaille.setStyle("-fx-border-color: red ;");
    }else {cbTaille.setStyle("-fx-border-color: green ;");
           lbTaille.setText("");
}
        
               System.out.println(test);

  
   
     if (!tfPrixVenteDetail.getText().isEmpty()&&!tfPrixAchat.getText().isEmpty()){
    if (Float.parseFloat(tfPrixVenteDetail.getText()) < Float.parseFloat(tfPrixAchat.getText())){
           tfPrixVenteDetail.setStyle("-fx-border-color: red ;");
                        lbVenteDetail.setText("le prix de vente detail doit être supérieur\n au prix d'achat");

    test = false ;}else {tfPrixVenteDetail.setStyle("-fx-border-color: green ;");
             lbVenteDetail.setText("");

}}
             System.out.println(test);
       if (!tfPrixVenteGros.getText().isEmpty()&&!tfPrixVenteDetail.getText().isEmpty()){
    if ((Float.parseFloat(tfPrixVenteGros.getText()) > Float.parseFloat(tfPrixVenteDetail.getText()))||(Float.parseFloat(tfPrixVenteGros.getText()) < Float.parseFloat(tfPrixAchat.getText())))
    {
       lbVenteGros.setText("le prix de vente gros doit être inferieure\n au prix de vente detail");
        tfPrixVenteGros.setStyle("-fx-border-color: red ;");
    test=false ;
    }else { tfPrixVenteGros.setStyle("-fx-border-color: green ;");
           lbVenteGros.setText("");

}
       }
        System.out.println(test);
                System.out.println("***************");

    return test ;
    }
    
     @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
   @FXML
    public void cbGenreOnClick(Event event) {
 cbGenre.getItems().clear();
        cbGenre.getItems().removeAll();
        cbGenre.setPromptText("Choisir le genre");
                    ITypeArticleDAO typeArticleDAO = new TypeArticleDAO();
                    List<String> listeType = new ArrayList<>();
                    listeType = typeArticleDAO.DisplayCBGenre();
                    
            for (String type : listeType) {
               cbGenre.getItems().addAll(type);
            }       
    }
     @FXML
    public void cbGenreOnAction(Event event) {
    if (!cbGenre.getSelectionModel().isEmpty()) {cbCategorie.setDisable(false);}
    else {cbCategorie.setDisable(true); cbTaille.setDisable(true);}
    }
     @FXML
    public void cbCategorieOnAction(Event event) {
    if (!cbCategorie.getSelectionModel().isEmpty()) {cbTaille.setDisable(false);}
    else { cbTaille.setDisable(true);}
    }
      @FXML
    public void cbCategorieOnClick(Event event) {
 cbCategorie.getItems().clear();
        cbCategorie.getItems().removeAll();
        cbCategorie.setPromptText("Choisir la categorie");
        ITypeArticleDAO typeArticleDAO = new TypeArticleDAO();
                    List<String> listeType = new ArrayList<>();
                    listeType = typeArticleDAO.DisplayCBCategorie(cbGenre.getValue().toString());
                   
for (String type : listeType) {
               cbCategorie.getItems().addAll(type);
            }       
    
    }
    
    @FXML
    public void cbEnseigneOnClick(Event event) {
 cbEnseigne.getItems().clear();
        cbEnseigne.getItems().removeAll();
        cbEnseigne.setPromptText("Choisir l'enseigne");
        IEnseigneDAO enseigneDAO = new EnseigneDAO();
                    List<String> listeEnseigne = new ArrayList<>();
                                 Session s = Session.getInstance();
                            
                    listeEnseigne = enseigneDAO.getListEnseigneByID(s.getId());
            for (String type : listeEnseigne) {
               cbEnseigne.getItems().addAll(type);
            }       
            
    
    }
    
     @FXML
    public void cbTailleOnClick(Event event) {
         cbTaille.getItems().clear();
        cbTaille.getItems().removeAll();
        cbTaille.setPromptText("Choisir la taille");
        ITypeArticleDAO typeArticleDAO = new TypeArticleDAO();
                    List<String> listeType = new ArrayList<>();
                    listeType = typeArticleDAO.DisplayCBTaille(cbGenre.getValue().toString(),cbCategorie.getValue().toString());
                    
            for (String type : listeType) {
               cbTaille.getItems().addAll(type);
            }       
    
    }
     @FXML
    private void btnAjouterOnAction(ActionEvent event) {
        if(control()){
            
             
            try {
                List l= MultipartUtility.up(tfImageF.getText());
                
                String path =l.toString();
                path=path.substring(0,path.indexOf("[")) + path.substring(path.indexOf("[") +1);
                path=path.substring(0,path.indexOf("]")) + path.substring(path.indexOf("]") +1);
                
                List l1= MultipartUtility.up(tfImageS.getText());
                
                String path2 =l1.toString();
                path2=path2.substring(0,path2.indexOf("[")) + path2.substring(path2.indexOf("[") +1);
                path2=path2.substring(0,path2.indexOf("]")) + path2.substring(path2.indexOf("]") +1);
                
                ITypeArticleDAO typeArticleDAO = new TypeArticleDAO();
                IStockDAO stockDAO = new StockDAO();
                IEnseigneDAO enseigneDAO = new EnseigneDAO();
                
                Stock s = new Stock();
                s.setNomArticle(tfNom.getText());
                s.setPrixAchat(Float.valueOf(tfPrixAchat.getText()));
                s.setPrixVenteDetail(Float.valueOf(tfPrixVenteDetail.getText()));
                s.setPrixVenteGros(Float.valueOf(tfPrixVenteGros.getText()));
                s.setTva(Integer.parseInt(tfTva.getText()));
                s.setQuantite(Integer.parseInt(tfQuantite.getText()));
                s.setIdType(typeArticleDAO.getTypeByGCT(cbGenre.getValue().toString(), cbCategorie.getValue().toString(), cbTaille.getValue().toString()));
                s.setEnseigne(cbEnseigne.getValue().toString());
                s.setImageF(path);
        s.setImageS(path2);
                if (stockDAO.insertStock(s)){
                    String qrtext= stockDAO.getStock();
                    System.out.println(qrtext);
                    QrGenerator qr = new QrGenerator();
                    qr.generate(qrtext);
                    
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("succes d'ajout");
                    alert.setHeaderText("succes d'ajout");
                    alert.setContentText("Ajout avec succes");
                    Optional<ButtonType> result = alert.showAndWait();
                }else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("erreur d'ajout");
                    alert.setHeaderText("erreur d'ajout");
                    alert.setContentText("erreur lors de l'ajout");
                }   } catch (IOException ex) {
                Logger.getLogger(AddStockController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        }
        
    }
    @FXML
     private void btnImageFOnAction(ActionEvent event) {
        Stage stage = (Stage) btnImageF.getScene().getWindow();
        FileChooser filechooser= new FileChooser();
        filechooser.setTitle("Ouvrir la Source de l'Image");
        filechooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
        File file=filechooser.showOpenDialog(stage);
        tfImageF.setText(file.toString());
        
        
        
    }
     @FXML
     private void btnImageSOnAction(ActionEvent event) {
        Stage stage = (Stage) btnImageS.getScene().getWindow();
        FileChooser filechooser= new FileChooser();
        filechooser.setTitle("Ouvrir la Source de l'Image");
        filechooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
        File file=filechooser.showOpenDialog(stage);
        tfImageS.setText(file.toString());
        
        
        
    }
}
