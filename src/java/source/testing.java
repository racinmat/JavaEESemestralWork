/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package source;

import java.io.*;
import javax.mail.*;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sun.net.www.protocol.mailto.MailToURLConnection;

/**
 *
 * @author Azathoth
 */
public class testing extends HttpServlet {
    private String[] headers;

    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
  
            sendGmail();
    
    }

    public void sendOneMoreMail(HttpServletResponse response){
        PrintWriter out = null;
try {
    response.setContentType("text/html");
            out = response.getWriter();
            String docType =
                    "\n";
            out.println(docType +
                    "\n" +
                    "\n" +
                    "\n" );
            Properties props = new Properties();
            out.println("props.put");
            props.put("mail.smtp.host", "localhost");
            Session s = Session.getInstance(props);
            out.println("Session.getInstance");
            try {
                MimeMessage message = new MimeMessage(s);
                
                InternetAddress from = new InternetAddress("memnarch@seznam.cz");
                message.setFrom(from);
                InternetAddress to = new InternetAddress("memnarch@seznam.cz");
                message.addRecipient(Message.RecipientType.TO, to);
                
                message.setSubject("Test from JavaMail.");
                message.setText("Hello from JavaMail!");
                Transport.send(message);
                out.println("Transport.send  ALL DONE");
                
                out.println(" " );
            } catch (Exception ex) {
                ex.printStackTrace();
                
                out.println("exception: " + ex.getMessage() );
                
            }
} catch (IOException ex) {

            Logger.getLogger(testing.class.getName()).log(Level.SEVERE, null, ex);

    } finally {
            out.close();
        }
    }
    
    public void mail()
   {
      
      // Recipient's email ID needs to be mentioned.
      String to = "memnarch108@gmail.com";

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

      try{
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO,
                                  new InternetAddress(to));

         // Set Subject: header field
         message.setSubject("This is the Subject Line!");

         // Send the actual HTML message, as big as you like
         message.setContent("<h1>This is actual message</h1>",
                            "text/html" );

         // Send message
         Transport.send(message);
         System.out.println("Sent message successfully....");
         
         
         
      }catch (MessagingException mex) {
         mex.printStackTrace();
      }
   }
    
    public void sendSMTPMail(){
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.seznam.cz");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
 
        Session session = Session.getInstance(props, new MyAuth());
 
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("memnarch@seznam.cz"));
            InternetAddress[] address = {new InternetAddress("glimmervoid@seznam.cz")};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject("subject");
            msg.setSentDate(new Date());
 
            msg.setText("Message body string");
 
            Transport.send(msg);
        }
        catch (MessagingException e) {}
    }

 

    
    public void sendAnotherMail(){
                java.util.Properties p = new Properties(); 
javax.mail.Session s = javax.mail.Session.getInstance(p); 
javax.mail.internet.MimeMessage message = new javax.mail.internet.MimeMessage(s); 
try { 
   javax.mail.internet.InternetAddress from = new javax.mail.internet.InternetAddress("memnarch108@gmail.com"); 
   message.setFrom(from); 
   message.addRecipient(javax.mail.Message.RecipientType.TO, new javax.mail.internet.InternetAddress("memnarch@seznam.cz")); 
   //message.addRecipient(javax.mail.Message.RecipientType.CC, new javax.mail.internet.InternetAddress("subject")); 
   //message.addRecipient(javax.mail.Message.RecipientType.BCC, new javax.mail.internet.InternetAddress(emailBccList)); 
   message.setSubject("subject"); 
   message.setText("telo"); 
   javax.mail.Transport.send(message); 
} catch (Exception e) { 
   System.out.println("Email failure: " + e.toString()); 
} 
    }
    
    public static void sendMail(String from, String to, String subject, String body, String[] headers) throws IOException {
   System.setProperty("mail.host", "localhost");

   URL u = new URL("mailto:"+to);
   MailToURLConnection con = (MailToURLConnection)u.openConnection();
   OutputStream os = con.getOutputStream();
   OutputStreamWriter w = new OutputStreamWriter(os);

   DateFormat df = new SimpleDateFormat("E, d MMM yyyy H:mm:ss Z");
   Date d = new Date();
   String dt = df.format(d);
   String mid = d.getTime()+from.substring(from.indexOf('@'));

   w.append("Subject: "+subject+"\r\n");
   w.append("Date: " +dt+ "\r\n");
   w.append("Message-ID: <"+mid+ ">\r\n");
   w.append("From: "+from+"\r\n");
   w.append("To: <"+to+">\r\n");
   if(headers!=null) {
      for(String h: headers)
         w.append(h).append("\r\n");
   }
   w.append("\r\n");

   w.append(body.replace("\n", "\r\n"));
   w.flush();
   w.close();
   os.close();
   con.close();
}
    
    public void sendGmail(){
        Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
 
		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("testovaciemail666","testovani!!!");
				}
			});
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("testovaciemail666@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("memnarch@seznam.cz"));
			message.setSubject("Testing Subject");
			message.setText("Dear Mail Crawler," +
					"\n\n No spam to my email, please!");
 
			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
    }
    
    
    
    
}
class MyAuth extends Authenticator {
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication("memnarch","1993201242");
    }
}