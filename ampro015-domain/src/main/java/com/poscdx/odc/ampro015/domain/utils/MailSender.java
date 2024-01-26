package com.poscdx.odc.ampro015.domain.utils;

import com.poscdx.odc.ampro015.domain.entity.MailAuthentication;
import org.springframework.beans.factory.annotation.Value;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailSender {

//    @Value("${mail.smtp.host}")
//    public void setHost(String host) {
//        ConstantUtil.MAIL_HOST = host;
//    }

//    @Value("${mail.smtp.address}")
//    public void setAddress(String address) {
//        ConstantUtil.MAIL_ADDRESS = address;
//    }

//    @Value("${mail.smtp.name}")
//    public void setName(String name) {
//        ConstantUtil.MAIL_NAME = name;
//    }

    public static String sendMail(String recipient, String username, String password, String subject, String body) {
        Properties props = new Properties();
        props.put("mail.smtp.host", ConstantUtil.MAIL_HOST);
        props.put("mail.smtp.auth", "true");
        props.setProperty("proxySet", "true");
        props.setProperty("ProxyHost", "forward.posco.net");
        props.setProperty("ProxyPort", "80");

        Authenticator auth = new MailAuthentication("ss", "ss");
//        Authenticator auth = new MailAuthentication(username, password);
        Session session = Session.getInstance(props, auth);
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(ConstantUtil.MAIL_ADDRESS, ConstantUtil.MAIL_NAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject("Your email subject");
            message.setText("Hello, this is the body of the email.");

            Transport.send(message);

        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "Mail sent successfully";
    }
}
