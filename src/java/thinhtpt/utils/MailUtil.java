/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinhtpt.utils;

import java.io.Serializable;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.ImageHtmlEmail;

/**
 *
 * @author ThinhTPT
 */
public class MailUtil implements Serializable {

    public static void sendEmail(String host, String port, String userName, String password,
            String toAddress, String subject, String htmlMessage, String textMessage)
            throws EmailException {
        // Create email obj
        ImageHtmlEmail email = new ImageHtmlEmail();

        // Configure server info
        email.setHostName(host);
        email.setSmtpPort(Integer.parseInt(port));
        email.setAuthenticator(new DefaultAuthenticator(userName, password));
        email.setSSLOnConnect(true);

        //  Set sender
        email.setFrom(userName, "Mini Social Network");

        // Set receiver
        email.addTo(toAddress);

        // Set title
        email.setSubject(subject);

        // Set content
        email.setHtmlMsg(htmlMessage);
        email.setTextMsg(textMessage);

        // Send email
        email.send();
    }
}
