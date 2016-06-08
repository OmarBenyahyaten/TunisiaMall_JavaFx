package tm.technique;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;

import com.sun.mail.pop3.POP3Store;
import java.util.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.mail.Flags;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.search.FlagTerm;
import tm.entities.User;
import tm.entities.mail;

public class EmailReceiveTest {

    public static ObservableList<mail> receiveEmail(String pop3Host, String storeType, String user, String password) throws NoSuchProviderException, MessagingException, IOException {
        ObservableList<mail> listemail = FXCollections.observableArrayList();
        Session session = Session.getDefaultInstance(new Properties());
        Store store = session.getStore("imaps");
        store.connect("pop.gmail.com", 993, user, password);
        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_WRITE);

        // Fetch unseen messages from inbox folder
        Message[] messages = inbox.getMessages();
int i=0;
        // Sort messages from recent to oldest
        for (Message message : messages) {
            if (message.isSet(Flags.Flag.SEEN)) {
                System.out.println("seen"
                        + "sendDate: " + message.getSentDate()
                        + " subject:" + message.getSubject());
                
               i++;
                mail m = new mail(i,InternetAddress.toString(message.getFrom()),message.getSubject(),message.getContent().toString(),"seen");
                listemail.add(m);
                
            } else {
                i++;
                mail m = new mail(i,InternetAddress.toString(message.getFrom()),message.getSubject(),message.getContent().toString(),"unseen");

                listemail.add(m);
                message.setFlag(Flags.Flag.SEEN, false);
            }
            if(i==10)
                return listemail;
        }

        return listemail;

    }
    public static mail receive(String pop3Host, String storeType, String user, String password,int i) throws NoSuchProviderException, MessagingException, IOException {
        Session session = Session.getDefaultInstance(new Properties());
        Store store = session.getStore("imaps");
        store.connect("pop.gmail.com", 993, user, password);
        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_WRITE);

        // Fetch unseen messages from inbox folder
        Message[] messages = inbox.getMessages();
        mail m =new mail(i,InternetAddress.toString(messages[i].getFrom()),messages[i].getSubject(),messages[i].getContent().getClass().toString(),"seen");
        return m;
    }
}
