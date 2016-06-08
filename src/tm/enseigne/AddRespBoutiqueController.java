package tm.enseigne;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tm.dao.classes.EnseigneDAO;
import tm.dao.classes.ResponsableBoutiqueDAO;
import tm.dao.classes.StockDAO;
import tm.dao.classes.TypeArticleDAO;
import tm.dao.classes.UserDAO;
import tm.dao.interfaces.IEnseigneDAO;
import tm.dao.interfaces.IResponsableBoutiqueDAO;
import tm.dao.interfaces.IStockDAO;
import tm.dao.interfaces.ITypeArticleDAO;
import tm.dao.interfaces.IUserDAO;
import tm.entities.Enseigne;
import tm.entities.ResponsableBoutique;
import tm.entities.Stock;
import tm.entities.User;
import tm.technique.Session;

public class AddRespBoutiqueController implements Initializable{

   
     @FXML
    private Label lbDebut;
      @FXML
    private Label lbFin;

       @FXML
    private Label lbNom;
 @FXML
    private Label lbPrenom; 
  @FXML
    private Label lbAdresse;
   @FXML
    private Label lbTel;
    @FXML
    private Label lbEnseigne;
     @FXML
    private Label lbLogin;
      @FXML
    private Label lbPwd;
       @FXML
    private Label lbEmail;
        
       

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
    private ComboBox cbEnseigne;
    
    
    @FXML
    private ComboBox<String> cbDebut;
    @FXML
    private ComboBox<String> cbFin;
    
    

    @FXML
    private Button btnAjouter;

    @FXML
    private Button btnClose;

      
    ObservableList<Enseigne> listeEnseignes;            

    
    
    
@Override
    public void initialize(URL url, ResourceBundle rb) {
        cbDebut.getItems().addAll("8 H","9 H","10 H","11 H","12 H","13 H", "14 H") ;     
        cbFin.getItems().addAll("12 H","13 H","14 H","15 H","16 H","17 H", "18 H", "19 H","20 H", "21 H","22 H") ;   }    
        
              
    ResponsableBoutiqueDAO RespBoutiqueDAO= new ResponsableBoutiqueDAO();

    
    
    public boolean control()
     {
         IResponsableBoutiqueDAO en = new ResponsableBoutiqueDAO();
     boolean test=true;
        if( tfNom.getText().isEmpty())
    {
        tfNom.setStyle("-fx-border-color: red ;");
         lbNom.setText("Veuillez saisir votre nom !");
        
    test=false;}else {tfNom.setStyle("-fx-border-color: green ;");lbNom.setText("");
}
         if( tfPrenom.getText().isEmpty())
    {
        tfPrenom.setStyle("-fx-border-color: red ;");
         lbPrenom.setText("Veuillez saisir votre prénom !");
        
    test=false;}else {tfPrenom.setStyle("-fx-border-color: green ;");lbPrenom.setText("");
}
         if( tfTelephone.getText().isEmpty())
    {
        tfTelephone.setStyle("-fx-border-color: red ;");
         lbTel.setText("Veuillez saisir votre numéro de téléphone !");
        
    test=false;}else { try {
       int num = Integer.parseInt(tfTelephone.getText());
 tfTelephone.setStyle("-fx-border-color: green ;");
        lbTel.setText("");
    } catch (NumberFormatException e) {
         tfTelephone.setStyle("-fx-border-color: red ;");
                 lbTel.setText("Veuillez saisir un entier !");


    test=false;
    }}
         if(en.verifTel(tfTelephone.getText()) && test==true) {
             tfTelephone.setStyle("-fx-border-color: red ;");lbTel.setText("Ce numéro existe pour un autre employé !"); test=false ;
} else {
        tfTelephone.setStyle("-fx-border-color: red ;");lbTel.setText("");
         }
         
          if(cbDebut.getSelectionModel().isEmpty()){
        cbDebut.setStyle("-fx-border-color: red ;");
               lbDebut.setText("Veuillez sélectionner l'heure de début de service !");

    test=false;
    } 
    else {cbDebut.setStyle("-fx-border-color: green ;");
                   lbDebut.setText("");
}
          if(cbFin.getSelectionModel().isEmpty()){
        cbFin.setStyle("-fx-border-color: red ;");
               lbFin.setText("Veuillez sélectionner l'heure de fin de service !");

    test=false;
    } 
    else {cbFin.setStyle("-fx-border-color: green ;");
                   lbFin.setText("");
}
          
           if( taAdresse.getText().isEmpty())
    {
        taAdresse.setStyle("-fx-border-color: red ;");
         lbAdresse.setText("Veuillez saisir une adresse !");
        
    test=false;}else {taAdresse.setStyle("-fx-border-color: green ;");lbAdresse.setText("");
}
         if( cbEnseigne.getSelectionModel().isEmpty())
    {
        cbEnseigne.setStyle("-fx-border-color: red ;");
         lbEnseigne.setText("Veuillez choisir une enseigne !");
        
    test=false;}else {cbEnseigne.setStyle("-fx-border-color: green ;");lbEnseigne.setText("");
}
         if( tfLogin.getText().isEmpty())
    {
        tfLogin.setStyle("-fx-border-color: red ;");
         lbLogin.setText("Veuillez saisir votre login !");
        
    test=false;}else {tfLogin.setStyle("-fx-border-color: green ;");lbLogin.setText("");
}
         if( tfPwd.getText().isEmpty())
    {
        tfPwd.setStyle("-fx-border-color: red ;");
         lbPwd.setText("Veuillez saisir votre password !");
        
    test=false;}else {tfPwd.setStyle("-fx-border-color: green ;");lbPwd.setText("");
}
         if( tfEmail.getText().isEmpty())
    {
        tfEmail.setStyle("-fx-border-color: red ;");
         lbEmail.setText("Veuillez saisir votre email !");
        
    test=false;}else {tfEmail.setStyle("-fx-border-color: green ;");lbEmail.setText("");
}
         
        return test ;
     }
    
    
    
    
      
    @FXML
     private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
     
     
     @FXML
    public void cbSoteViewNomOnClick(Event event) {
        cbEnseigne.getItems().clear();
        cbEnseigne.getItems().removeAll();
        cbEnseigne.setPromptText("Enseignes à Affecter");
        IEnseigneDAO enseigneDAO = new EnseigneDAO();
   
        
        List<String> listeNom = new ArrayList<>();
        listeNom = enseigneDAO.DisplayCBNom();
                    
            for (String type : listeNom) {
               cbEnseigne.getItems().addAll(type);
               
               
            }  

                                
    }
      

    
    
    @FXML
    void btnAjouterOnAction(ActionEvent event) {
       
       FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/tm/enseigne/GResponsableBoutique.fxml"));
                 if (control()){

        
        String nom=tfNom.getText();      
        String prenom=tfPrenom.getText();      
        String heureDebut=cbDebut.getValue().toString();
        String heureFin=cbFin.getValue().toString();
        String adresse=taAdresse.getText();      
        String email=tfEmail.getText();  
        String login=tfLogin.getText();  
        String pwd=tfPwd.getText();  
        Integer telephone=Integer.parseInt(tfTelephone.getText());
        
        EnseigneDAO enseigneDAO =new EnseigneDAO();
        
        
        Session s = Session.getInstance();

        ResponsableBoutique respBoutique=new ResponsableBoutique();
              
        User usr=new User(login, pwd, email, "","a:1:{i:0;s:13:\"ROLE_BOUTIQUE\";}");

        
        respBoutique.setNom(nom);
        respBoutique.setPrenom(prenom);
        respBoutique.setAdresse(adresse);
        respBoutique.setHeureDebut(heureDebut);
        respBoutique.setHeureFin(heureFin);
        respBoutique.setTel(telephone);
        respBoutique.setEnseigne(cbEnseigne.getValue().toString());
         

        
        IResponsableBoutiqueDAO responsableBoutiqueDAO = new ResponsableBoutiqueDAO();
        responsableBoutiqueDAO.insertResponsableBoutique(respBoutique);
        IUserDAO usrDAO=new UserDAO();
        usrDAO.insertUser(usr);
        
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Ajout avec succes");

            alert.showAndWait();
        
        Stage stage = (Stage) btnAjouter.getScene().getWindow();
        stage.close();       
                 
        }
                 else {
         Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Erreur lors de l'ajout");

            alert.showAndWait();}
        

    
        
        
    }
    
    
   
}
