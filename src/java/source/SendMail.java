/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package source;
/*
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
*/
/**
 *
 * @author Azathoth
 *//*
public class SendMail {

    public SendMail() {
    }
    
    public void mail()
   {    
        try {
            // Recipient's email ID needs to be mentioned.
            String to = "glimmervoid@seznam.cz";
            
            // Sender's email ID needs to be mentioned
            String from = "memnarch@seznam.cz";
            
            // Assuming you are sending email from localhost
            String host = "localhost";
            
            // Get system properties
            Properties properties = System.getProperties();
            
            // Setup mail server
            properties.setProperty("mail.smtp.host", host);
            
            // Get the default Session object.
            Session session = Session.getDefaultInstance(properties);
            
            
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);
            
            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));
            
            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));
            
            // Set Subject: header field
            message.setSubject("Testování informování o registraci nového uchazeče");
            
            // Now set the actual message
            message.setText("This is actual message");
            
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException ex) {
            Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex);
        }
      
   }
}*/
