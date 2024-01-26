package com.poscdx.odc.ampro015.domain.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import java.io.Console;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class SendEmail {
    private static final String MAIL_SMTP_SERVER_KEY = "mail.smtp.host";
    private static final String MAIL_SMTP_SERVER_PORT_KEY = "mail.smtp.port";
    private static final String MAIL_SMTP_SERVER_AUTH_KEY = "mail.smtp.auth";
    private static final String MAIL_SMTP_SERVER_START_TLS_KEY = "mail.smtp.starttls.enable";



    public static void LoadTemplate() throws IOException {
        try {
            String html = new String(Files.readAllBytes(ResourceUtils.getFile("classpath:password-reset.html").toPath()));
            sendEmailWithoutAuthentication("xuan.nguyenthanh@posco.net","Reset Password",html);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Utility method to send simple HTML email
     * @param session
     * @param toEmail
     * @param subject
     * @param body
     */
    public static void sendEmail(Session session, String toEmail, String subject, String body){
        try
        {
            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress(ConstantUtil.MAIL_SMTP_EMAIL_ID, ConstantUtil.MAIL_SMTP_EMAIL_ID_ALIAS));

            msg.setReplyTo(InternetAddress.parse(ConstantUtil.MAIL_SMTP_EMAIL_ID, false));

            msg.setSubject(subject, "UTF-8");

            msg.setText(body, "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            System.out.println("Message is ready");
            Transport.send(msg);

            System.out.println("EMail Sent Successfully!!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Utility method to send email with attachment
     * @param session
     * @param toEmail
     * @param subject
     * @param body
     */
    public static void sendAttachmentEmail(Session session, String toEmail, String subject, String body){
        try{
            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress(ConstantUtil.MAIL_SMTP_EMAIL_ID, ConstantUtil.MAIL_SMTP_EMAIL_ID_ALIAS));

            msg.setReplyTo(InternetAddress.parse(ConstantUtil.MAIL_SMTP_EMAIL_ID, false));

            msg.setSubject(subject, "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));

            // Create the message body part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Fill the message
            messageBodyPart.setText(body);

            // Create a multipart message for attachment
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Second part is attachment
            messageBodyPart = new MimeBodyPart();
            String filename = "abc.txt";
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            msg.setContent(multipart);

            // Send message
            Transport.send(msg);
            System.out.println("EMail Sent Successfully with attachment!!");
        }catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static void sendEmailWithoutAuthentication(String toEmail, String subject, String body){
        Properties props = System.getProperties();
        props.put(MAIL_SMTP_SERVER_KEY, ConstantUtil.MAIL_SMTP_SERVER);
        Session session = Session.getInstance(props, null);
        sendEmail(session,toEmail, subject, body);
    }

    public static void sendEmailWithAuthenticationTLS(String toEmail, String subject, String body){
        Properties props = new Properties();
        props.put(MAIL_SMTP_SERVER_KEY, ConstantUtil.MAIL_SMTP_SERVER); //SMTP Host
        props.put(MAIL_SMTP_SERVER_PORT_KEY, ConstantUtil.MAIL_SMTP_SERVER_PORT); //TLS Port
        props.put(MAIL_SMTP_SERVER_AUTH_KEY, "true"); //enable authentication
        props.put(MAIL_SMTP_SERVER_START_TLS_KEY, "true"); //enable STARTTLS

        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(ConstantUtil.MAIL_SMTP_EMAIL_ID, ConstantUtil.MAIL_SMTP_EMAIL_PASSWORD);
            }
        };
        Session session = Session.getInstance(props, auth);
        sendEmail(session,toEmail, subject, body);
    }

    public static void sendEmailWithAuthenticationSSL(String toEmail, String subject, String body) {
        Properties props = new Properties();
        props.put(MAIL_SMTP_SERVER_KEY, ConstantUtil.MAIL_SMTP_SERVER); //SMTP Host
        props.put("mail.smtp.socketFactory.port", ConstantUtil.MAIL_SMTP_SERVER_PORT); //SSL Port
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
        props.put(MAIL_SMTP_SERVER_AUTH_KEY, "true"); //Enabling SMTP Authentication
        props.put(MAIL_SMTP_SERVER_PORT_KEY, ConstantUtil.MAIL_SMTP_SERVER_PORT); //SMTP Port

        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(ConstantUtil.MAIL_SMTP_EMAIL_ID, ConstantUtil.MAIL_SMTP_EMAIL_PASSWORD);
            }
        };

        Session session = Session.getDefaultInstance(props, auth);
        sendEmail(session, toEmail,subject, body);

        /*
        * final String fromEmail = "myemailid@gmail.com"; //requires valid gmail id
		final String password = "mypassword"; // correct password for gmail id
		final String toEmail = "myemail@yahoo.com"; // can be any email id

		System.out.println("SSLEmail Start");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
		props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
		props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
		props.put("mail.smtp.port", "465"); //SMTP Port

		Authenticator auth = new Authenticator() {
			//override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};

		Session session = Session.getDefaultInstance(props, auth);
		System.out.println("Session created");
	        EmailUtil.sendEmail(session, toEmail,"SSLEmail Testing Subject", "SSLEmail Testing Body");

	        EmailUtil.sendAttachmentEmail(session, toEmail,"SSLEmail Testing Subject with Attachment", "SSLEmail Testing Body with Attachment");

	        EmailUtil.sendImageEmail(session, toEmail,"SSLEmail Testing Subject with Image", "SSLEmail Testing Body with Image");
*/
    }


}
