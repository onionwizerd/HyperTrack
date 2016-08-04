package com.company.email;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by josh on 2016/08/01.
 */
public class Email {


    public Email() {
    }

    public void sendEmail(String sendAddress, String messageText, String messageSubject){
        final String username = "hypertrack@zoho.com";
        final String password = "/home/josh/Downloads/javax.mail.jar";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.zoho.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("hypertrack@zoho.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(sendAddress));
            message.setSubject(messageSubject);
            message.setText(messageText);

            Transport.send(message);

            System.out.println("Email sent");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isValidAddress(String emailAddress){
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(emailAddress);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

}
