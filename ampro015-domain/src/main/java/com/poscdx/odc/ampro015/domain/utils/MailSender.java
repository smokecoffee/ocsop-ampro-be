package com.poscdx.odc.ampro015.domain.utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class MailSender {

    private static final String MAIL_SMTP_SERVER_KEY = "mail.smtp.host";
    private static final String MAIL_SMTP_SERVER_PORT_KEY = "mail.smtp.port";
    private static final String MAIL_SMTP_SERVER_AUTH_KEY = "mail.smtp.auth";
    private static final String MAIL_SMTP_SERVER_START_TLS_KEY = "mail.smtp.starttls.enable";
    /**
     * Utility method to send simple HTML email
     * @param session
     * @param toEmail
     * @param subject
     * @param body
     */
    public static boolean sendEmail(Session session, String toEmail, String subject, String body){
        try
        {
            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress(Utils.MAIL_SMTP_EMAIL_ID, Utils.MAIL_SMTP_EMAIL_ID_ALIAS));

            //msg.setReplyTo(InternetAddress.parse(ConstantUtil.MAIL_SMTP_EMAIL_ID, false));

            msg.setSubject(subject, "UTF-8");

            msg.setContent(body,"text/html; charset=utf-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            System.out.println("Message is ready");
            Transport.send(msg);
            System.out.println("EMail Sent Successfully!!");
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
        return false;
        }
    }


    public static boolean sendEmailWithAuthentication(String toEmail, String subject, String body){
        Properties props = System.getProperties();
        props.put(MAIL_SMTP_SERVER_KEY, Utils.MAIL_SMTP_SERVER);
        props.put(MAIL_SMTP_SERVER_AUTH_KEY, "true");
        props.put(MAIL_SMTP_SERVER_PORT_KEY, Utils.MAIL_SMTP_SERVER_PORT);
        props.setProperty("proxySet", "true");
        props.setProperty("ProxyHost", "forward.posco.net");
        props.setProperty("ProxyPort", "80");
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Utils.MAIL_SMTP_EMAIL_USERNAME, Utils.MAIL_SMTP_EMAIL_PASSWORD);
            }
        };
        Session session = Session.getInstance(props, auth);
        return  sendEmail(session,toEmail, subject, body);
    }


}
