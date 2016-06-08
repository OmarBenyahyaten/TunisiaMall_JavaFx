/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.chat;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author MWESIGYE JOHN BOSCO
 */
public class ClientNickName implements Runnable {

    Socket st = null;
    DataInputStream dis = null;
    DataOutputStream dos = null;
    private String nickname = null;
    private String ip = null;

    public ClientNickName(String ip ,String nickname) {
        this.nickname = nickname;
        this.ip =ip;
    }

   
   
    public void ClientNickNameMain() {
        try {

            boolean accepted = false;
            do {
                accepted = false;
                dos.writeUTF(nickname);
                // reading back from server
                String reply = dis.readUTF();

                if (reply.contains("currently")) {
                    // set the Nickname UI
                    ClientSetting.nickLabel.setText("USERNAME IS IN USE TRY AGAIN !!!!");
                    // it will loop back to accepted = false till all true
                    accepted = false;
                } else {
                    ClientSetting.outputbox.appendText(reply + "\n");
                    
                    // kill the nick UI
                    ClientSetting.nickTitledPane.setVisible(false);

                    ClientSetting.sendMessage.setVisible(true);
                    accepted = true;
                }
            } while (accepted == false);


            try {
                while (true) {
                    String reply = dis.readUTF();
                    if (reply.isEmpty()) {
                       
                        ClientSetting.outputbox.appendText("Client  is empty boss" + reply + "\n");
                    } else {
                        if(reply.startsWith("pase2347")){
//                            ClientSetting.OnlineCombo.getItems().clear();
//                            ClientSetting.xlist.removeAll(ClientSetting.xlist);
                            ClientSetting.OnlineLable.setText("");
                           String usersx[] = reply.replace("pase2347", "").split(",");
                            for (int a = 0; a < usersx.length; a++) {
                              System.err.println(usersx[a]);
                              ClientSetting.OnlineLable.appendText(usersx[a]+"\n");
                              ClientSetting.xlist.add(usersx[a]);
                            }
                            // populate combo
//                            ClientSetting.OnlineCombo.setItems(ClientSetting.xlist);
                        
                        }else{
                        ClientSetting.outputbox.appendText(reply + "\n");
                        System.out.println("........................." + reply);
                        }
                    }

                    // send messages to server
                    // send message through server to all clients
                    ClientSetting.sendMessage.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                           String privatemessage = ClientSetting.privatemessage.getText().trim();
                            String message = ClientSetting.inputbox.getText().trim();
                            String fullmessage = privatemessage+" "+message;
                            ClientSetting.inputbox.setText("");
                            ClientSetting.privatemessage.setText("");
                             ClientSetting.privateLabel.setText("");
                            ClientSetting.privateLabel.setVisible(false);
                            try {
                                
                                dos.writeUTF(fullmessage);

                            } catch (IOException ex) {
                                Logger.getLogger(ClientNickName.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });



                }
            } catch (Exception ex) {
                ClientSetting.outputbox.appendText(""
                        + "          WARNING :\n"
                        + "***********************************\n"
                        + "**The server has been switched off*\n "
                        + "** Please close the application ***\n"
                        + "**   and open it again.************\n"
                        + "***********************************");
                ClientSetting.OnlineLable.setText("");
               ClientSetting.online.setVisible(false);
                 ClientSetting.offline.setVisible(true);
                 ClientSetting.sendMessage.setDisable(true);

            }


        } catch (Exception ex) {
            //ex.printStackTrace();
            ClientSetting.outputbox.appendText("Server connected\n");
ClientSetting.validationShow.setVisible(false);
        }

    }

    @Override
    public void run() {

        try {
            // connecting to the server
            st = new Socket(ip, 8881);
            //Creating the dis and dos objects foe the clients
            dis = new DataInputStream(st.getInputStream());
            dos = new DataOutputStream(st.getOutputStream());
        } catch (Exception e) {
//            ClientSetting.validationShow.setVisible(true);
            ClientSetting.outputbox.appendText("Server Authetication Failed Please Try to login again\n");
//            ClientSetting.validationShow.setText("Your ip address or username is incorrect");
            
        }
        ClientNickNameMain();
    }
}
