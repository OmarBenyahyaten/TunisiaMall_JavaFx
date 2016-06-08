/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tm.technique;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailTLS {
    
    public static void recupMail(String Email,String pass) {
        
        final String username = "medhedi.timoumi@esprit.tn";
        final String password = "timoumi%%";
        
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "25");
        
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        
        try {
            
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("medhedi.timoumi@esprit.tn"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(Email));
            message.setSubject("new password for your account ");
            message.setText("hello,"
                    + "\n\n that's your new password \n\n"+
                    pass+"\n\n have a nice day");
            
            Transport.send(message);
            
            System.out.println("Done");
            
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean sendMail(String toEmail,String subject,String text ,String password) {
          tm.technique.Session s =tm.technique.Session.getInstance();
        final String username = s.getEmail();
        
        System.out.println(username);
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "25");
        
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        
        try {
            
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(text);
            
            Transport.send(message);
            
           return true;
            
        } catch (MessagingException e) {
            return false;
            
            
        }
        
    }

    
}
