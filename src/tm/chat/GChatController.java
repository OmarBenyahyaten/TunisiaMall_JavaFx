/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.chat;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tm.technique.Session;
/**
 * FXML Controller class
 *
 * @author omarblythe
 */
public class GChatController implements Initializable {

 @FXML
    private TitledPane x1;    
    @FXML
    private TitledPane x2;
    @FXML
    private Label heading;
        private ListView<String> onlinebox;
    @FXML
    private TextArea outputbox;
    @FXML
    private TextArea inputbox;
       
    @FXML
    private Button sendMessage;
    @FXML
    private Font x3;
    @FXML
    private Color x4;
    @FXML
    private Accordion nickNameLayer;
    @FXML
    private TitledPane nickTitledPane;
        private Label nickLabel;
    @FXML
    private TextField nickTextFeild;
    
    ClientNickName cl;
    
     Thread nickName;
    @FXML
    private ComboBox<String> OnlineCombo;
    @FXML
    private TextArea OnlineLable;
    private ObservableList xlist = FXCollections.observableArrayList();
    @FXML
    private Label privateLabel;
    @FXML
    private TextField privatemessage;
        private TextArea aboutdeveloper;
    @FXML
    private Label username;
    @FXML
    private Label offline;
    @FXML
    private Font x5;
    @FXML
    private Label online;
    @FXML
    private Label hiddenusername;
    @FXML
    private TextField serverip;
    @FXML
    private Font x6;
    @FXML
    private Label validationShow;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // nickNameLayer.setVisible(true);
      ClientSetting.inputbox = inputbox;
      ClientSetting.outputbox = outputbox; 
      ClientSetting.nickTitledPane = nickTitledPane;
      ClientSetting.nickLabel = nickLabel;
      ClientSetting.sendMessage = sendMessage;
      ClientSetting.OnlineLable = OnlineLable;
      ClientSetting.OnlineCombo = OnlineCombo;
      ClientSetting.xlist = xlist;
      ClientSetting.privateLabel = privateLabel;
      ClientSetting.privatemessage =privatemessage;
      ClientSetting.username = username;
      ClientSetting.offline = offline;
      ClientSetting.online = online;
      ClientSetting.validationShow = validationShow;
      Session s = Session.getInstance();
     String nick=s.getUsername();
     nickTextFeild.setText(nick);
      nickTextFeild.setDisable(true);

    }    


    @FXML
    private void sendNickName(ActionEvent event) {
        
        //populating listview
     String ipaddress = serverip.getText().trim();
     String nick = nickTextFeild.getText().trim();
    
     

     if((ipaddress.length() ==0 || ipaddress.equals("")) &&((nick.length() == 0) && (nick.equals("")))){
     ClientSetting.validationShow.setText("All feilds are required");
     validationShow.setVisible(true);
     return;
     }else{
         System.err.println("ip........................"+ipaddress+"\n"+"****"+nick);
     cl  = new ClientNickName(ipaddress,nick); 
//    ClientSetting.username.setText("WELOME TO KEMP CHAT :\t "+nick);
    hiddenusername.setText(nick);
     nickName = new Thread(cl);
     nickName.start();  
          validationShow.setVisible(false);
nickTitledPane.setVisible(false);
     exitfirstnickName();
     
     } }

    @FXML
    private void OnlineCombo(ActionEvent event) {

                            String count = OnlineCombo.getSelectionModel().getSelectedItem().toString();                          
                            ClientSetting.privatemessage.setText("pvt "+count);
                            ClientSetting.privateLabel.setVisible(true);
                            ClientSetting.privateLabel.setText("SEND  A PRIVATE MESSAGE TO :=>"+count);
                       }

    @FXML
    private void clearMessage(ActionEvent event) {
        ClientSetting.outputbox.setText("");
    }

    
    @FXML
    private void leftTextArea(MouseEvent event) {
       populateCombo();                      
    }

    @FXML
    private void rightTextArea(MouseEvent event) {
    populateCombo();   
    }

    @FXML
    private void textfieldup(MouseEvent event) {
        populateCombo();
    }
    public void populateCombo(){

     xlist.removeAll(xlist);
        ClientSetting.OnlineCombo.getItems().clear();
         String nm = ClientSetting.OnlineLable.getText().toString();
                            String usersx[] = nm.split("\n");
                            for (int a = 0; a < usersx.length; a++) {
                              System.err.println(usersx[a]);
                              xlist.addAll(usersx[a]);                            
                            }
                            String username = hiddenusername.getText().trim();
                            xlist.remove(username);
                            ClientSetting.OnlineCombo.getItems().addAll(xlist);  
    
    }

    @FXML
    private void firstNickname(MouseEvent event) {
        validationShow.setVisible(true);
        validationShow.setText("Enter server ip address if your not on localhost");
    }

    @FXML
    private void exitfirstnickName() {
          validationShow.setVisible(false);
    }    
    
}
