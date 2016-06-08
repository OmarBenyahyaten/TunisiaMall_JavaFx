/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.chat;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;

/**
 *
 * @author MWESIGYE JOHN BOSCO
 */
public class ClientSetting {
    public static  TextArea inputbox; 
    public static  TextArea outputbox;
    public static TitledPane nickTitledPane;
    public static Label nickLabel;
    public static Button sendMessage;
    public static TextArea OnlineLable;
    public static ComboBox<String> OnlineCombo;
    public static ObservableList xlist;
    public static Label username;
    public static Label validationShow;
     // invisible feilds
    public static Label privateLabel;
    public static TextField privatemessage;
    //status
    public static Label offline;
    public static Label online;
    
}
