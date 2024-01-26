package com.poscdx.odc.ampro015.domain.utils;

import com.poscdx.odc.ampro015.domain.entity.MailAuthentication;
import org.springframework.beans.factory.annotation.Value;

import javax.mail.*;
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
        props.put("mail.smtp.port", "25");
        props.put("proxySet", "true");
        props.put("ProxyHost", "forward.posco.net");
        props.put("ProxyPort", "80");
        /*props.put("mail.smtp.proxy.host", "forward.posco.net");
        props.put("mail.smtp.proxy.port", "80");
        props.put("mail.smtp.proxy.user", "ss");
        props.put("mail.smtp.proxy.password", "ss");*/

        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("ss","ss");
            }
        };
        Session session = Session.getInstance(props, null);
        try {
            MimeMessage message = new MimeMessage(session);
            message.addHeader("Content-type", "text/HTML; charset=UTF-8");
            message.addHeader("format", "flowed");
            message.addHeader("Content-Transfer-Encoding", "8bit");


            message.setFrom(new InternetAddress(ConstantUtil.MAIL_ADDRESS, ConstantUtil.MAIL_NAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("xuan.nguyenthanh@posco.net"));
            message.setSubject("Your email subject","UTF-8");
            message.setText("<b>Hello, this is the body of the email.</b>","UTF-8");

            Transport.send(message);


        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "Mail sent successfully";
    }
}
