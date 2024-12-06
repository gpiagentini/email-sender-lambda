package com.gpiagentini.services;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSenderService {

    private final String username;
    private final String password;
    private final Properties properties = new Properties();

    public EmailSenderService(String username, String password) {
        this.username = username;
        this.password = password;

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
    }

    public void sendEmail(String subject, String body, String recipient) throws MessagingException {
        try {
            Message message = new MimeMessage(createSession());
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(body);

            // Send the email
            Transport.send(message);
        } catch (MessagingException ex) {
            throw new RuntimeException("An error occurred trying to send the email.");
        }

    }

    private Session createSession() {
        return Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }


}
