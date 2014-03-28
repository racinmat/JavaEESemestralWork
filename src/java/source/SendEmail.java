/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package source;

/**
 *
 * @author Azathoth
 */
// File Name SendEmail.java

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class SendEmail
{
    private String username;
    private String password;
    private String name;
    private String lastname;
    private String sex;
    private String email;

    /**
     * Vytvoří instanci SendEmail
     * @param username uživatelské jméno
     * @param password heslo
     * @param name jméno
     * @param lastname příjmení
     * @param email email
     */
    public SendEmail(String username, String password, String name, String lastname, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
    }
    
    private String osloveni(){
        if ("muž".equals(sex)) {
            return "pane ";
        }
        else if ("žena".equals(sex)){
            return "slečno ";
        }
        else {
            return " ";
        }
    }
    
    public void sendGmailToApplicant(String sex){
        this.sex=sex;
        Properties props = new Properties();
	props.put("mail.smtp.host", "smtp.gmail.com");
	props.put("mail.smtp.socketFactory.port", "465");
	props.put(
            "mail.smtp.socketFactory.class",
            "javax.net.ssl.SSLSocketFactory"
        );
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.port", "465");
        props.put("mail.imaps.ssl.trust", "*");
 
	Session session = Session.getDefaultInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("testovaciemail666","testovani!!!");
                }
            }
        );
	try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("testovaciemail666@gmail.com"));
            message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(email)
            );
            message.setSubject("Úspěšné podání elektronické přihlášky");
            message.setText(
                "Dobrý den, "+osloveni()+lastname+",\r\n"
                + "vaše elektronická přihláška byla úspěšně podána,\r\n"
                + "vaše přihlašovací jméno je "+username
                + " a vaše heslo je "+password
                + " .\r\n"
                + "Pomocí techto přihlašovacích údajů můžete na stránkách školy pozorovat stav vyřízení své přihlášky.\r\n"
                + "Po přihlášení si můžete změnit své heslo.\r\n"
                + "S pozdravem, automatizovaný systém."
            );
            Transport.send(message);
            System.out.println("Done");
	} catch (MessagingException e) {
            throw new RuntimeException(e);
	}
    }
    
    public void sendGmailToRegisteredUser(){
        Properties props = new Properties();
	props.put("mail.smtp.host", "smtp.gmail.com");
	props.put("mail.smtp.socketFactory.port", "465");
	props.put(
            "mail.smtp.socketFactory.class",
            "javax.net.ssl.SSLSocketFactory"
        );
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.port", "465");
        props.put("mail.imaps.ssl.trust", "*");
 
	Session session = Session.getDefaultInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("testovaciemail666","testovani!!!");
                }
            }
        );
	try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("testovaciemail666@gmail.com"));
            message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(email)
            );
            message.setSubject("Úspěšné podání elektronické přihlášky");
            message.setText(
                "Dobrý den, "+osloveni()+lastname+",\r\n"
                + "byl jste přidán do školní databáze,\r\n"
                + "vaše přihlašovací jméno je "+username
                + " a vaše heslo je "+password
                + " .\r\n"
                + "Pomocí techto přihlašovacích údajů můžete na stránkách školy přistupovat k obsahu určenému přihlšeným uživatelům.\r\n"
                + "Po přihlášení si můžete změnit své heslo.\r\n"
                + "S pozdravem, automatizovaný systém."
            );
            Transport.send(message);
            System.out.println("Done");
	} catch (MessagingException e) {
            throw new RuntimeException(e);
	}
    }
   
}