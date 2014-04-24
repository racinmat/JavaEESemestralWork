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

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class SendEmail
{
    private final String username;
    private final String password;
    private final String name;
    private final String lastname;
    private String sex;                                                         //není final, protože není u všech osob, tedy dodává se později a pouze u někoho
    private final String email;

    /**
     * Creates instance of SendEmail
     * @param username of user
     * @param password of user
     * @param name of user
     * @param lastname of user
     * @param email address of user where shall be email sent
     */
    public SendEmail(String username, String password, String name, String lastname, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
    }
    
    /**
     * 
     * @return correct salutation according to sex of user
     */
    private String salutation(){
        switch (sex) {
            case "muž":
                return "pane ";
            case "žena":
                return "slečno ";
            default:
                return " ";
        }
    }
    
    /**
     * Sends email with username and password to email provided in contructor, used for new applicants.
     * @param sex String determining sex, just because of salutation in email.
     * @throws AddressException when parsing email address fails
     * @throws MessagingException when any part of sending an email fails
     */
    public void sendGmailToApplicant(String sex) throws AddressException, MessagingException{
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
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("testovaciemail666","testovani!!!");
                }
            }
        );
	Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("testovaciemail666@gmail.com"));
        message.setRecipients(
            Message.RecipientType.TO,
            InternetAddress.parse(email)
        );
        message.setSubject("Úspěšné podání elektronické přihlášky");
        message.setText(
            "Dobrý den, "+salutation()+lastname+",\r\n"
            + "vaše elektronická přihláška byla úspěšně podána,\r\n"
            + "vaše přihlašovací jméno je "+username
            + " a vaše heslo je "+password
            + ".\r\n"
            + "Pomocí techto přihlašovacích údajů můžete na stránkách školy pozorovat stav vyřízení své přihlášky.\r\n"
            + "Po přihlášení si můžete změnit své heslo.\r\n"
            + "S pozdravem, automatizovaný systém."
        );
        Transport.send(message);
    }
    
    /**
     * Sends email with username and password to email provided in contructor, used for other users than applicants.
     * @throws AddressException when parsing email address fails
     * @throws MessagingException when any part of sending an email fails
     */
    public void sendGmailToRegisteredUser() throws AddressException, MessagingException{
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
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("testovaciemail666","testovani!!!");
                }
            }
        );
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("testovaciemail666@gmail.com"));
        message.setRecipients(
            Message.RecipientType.TO,
            InternetAddress.parse(email)
        );
        message.setSubject("Úspěšné podání elektronické přihlášky");
        message.setText(
            "Dobrý den, "+salutation()+lastname+",\r\n"
            + "byl jste přidán do školní databáze,\r\n"
            + "vaše přihlašovací jméno je "+username
            + " a vaše heslo je "+password
            + " .\r\n"
            + "Pomocí techto přihlašovacích údajů můžete na stránkách školy přistupovat k obsahu určenému přihlšeným uživatelům.\r\n"
            + "Po přihlášení si můžete změnit své heslo.\r\n"
            + "S pozdravem, automatizovaný systém."
        );
        Transport.send(message);
    }
}