// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 19/8/2005 10:15:27
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   EMailUtil.java

package br.com.vortice.ijuri.core.abstracao.util.email;

import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.vortice.core.exception.AplicacaoException;

public class EmailUtil {

    public EmailUtil(){}

    public static void enviarEmailComAutenticacao(String smtphost, String username, 
                                                  String password, String subject, String from, String fromText, 
                                                  String to, String text) throws AplicacaoException {
    	try{
            String mailer = "JavaMail API";
            Properties props = System.getProperties();
            props.put("mail.smtp.auth", "true");
           

            Session session = Session.getDefaultInstance(props, null);
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from, fromText));
            msg.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(to, false));
            msg.setSubject(subject);
            msg.setText(text);
            msg.setHeader("X-Mailer", mailer);
            msg.setSentDate(new Date());
            Transport tr = session.getTransport("smtp");
            tr.connect(smtphost, username, password);
            msg.saveChanges();
            tr.sendMessage(msg, msg.getAllRecipients());
            tr.close();
        }catch(Exception e){
            e.printStackTrace();
            throw new AplicacaoException("Mensagem de E-mail não enviada", e);
        }    
        
    }

    public static void enviarEmailHTMLFileComAutenticacao(String smtphost, String username, String password, String subject, 
                                                          String from, String fromText, String to, String htmlFileName,
                                                          File images[]) throws AplicacaoException {
        try{
            String mailer = "JavaMail API";
            Properties properties = System.getProperties();
            properties.put("mail.transport.protocol", "SMTP");
            properties.put("mail.smtp.host", smtphost);
            properties.put("mail.smtp.auth", "true");
            Session session = Session.getInstance(properties, null);
            MimeMessage msg = new MimeMessage(session);
            InternetAddress lFrom = new InternetAddress(from);
            InternetAddress lTo = new InternetAddress(to);
            msg.setFrom(lFrom);
            msg.addRecipient(javax.mail.Message.RecipientType.TO, lTo);
            msg.setSubject(subject);
            BodyPart bpHTML = getFileBodyPart(htmlFileName, "text/html");
            MimeMultipart multipart = new MimeMultipart("related");
            multipart.addBodyPart(bpHTML);
            if(images != null) {
                for(int i = 0; i < images.length; i++) {
                    BodyPart messageBodyPart = new MimeBodyPart();
                    javax.activation.DataSource fds = new FileDataSource(images[i]);
                    messageBodyPart.setDataHandler(new DataHandler(fds));
                    messageBodyPart.setHeader("Content-ID", "<" + images[i].getName() + ">");
                    multipart.addBodyPart(messageBodyPart);
                }
                
            }
            msg.setContent(multipart);
            Transport tr = session.getTransport("smtp");
            tr.connect(smtphost, username, password);
            msg.saveChanges();
            tr.sendMessage(msg, msg.getAllRecipients());
            tr.close();
        }catch(Exception e){
            e.printStackTrace();
            throw new AplicacaoException("Mensagem de E-mail não enviada", e);
        }    
       
    }

    public static void enviarEmailHTMLTextComAutenticacao(String smtphost, String username, String password, String subject, 
                                                          String from, String fromText, String to, String htmlText,
                                                          File images[]) throws AplicacaoException {
        try {
            String mailer = "JavaMail API";
            Properties properties = System.getProperties();
            properties.put("mail.transport.protocol", "SMTP");
            properties.put("mail.smtp.host", smtphost);
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable","true");
            //a partir daqui,,
            /*
            properties.put("mail.debug", "true");
            properties.put("mail.smtp.port", "465");
            properties.put("mail.smtp.socketFactory.port", "465");
            properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            properties.put("mail.smtp.socketFactory.fallback", "false");
            */
            Session session = Session.getInstance(properties, null);
            MimeMessage msg = new MimeMessage(session);
            InternetAddress lFrom = new InternetAddress(from, fromText);
            InternetAddress lTo = new InternetAddress(to);
            msg.setFrom(lFrom);
            msg.addRecipient(javax.mail.Message.RecipientType.TO, lTo);
            msg.setSubject(subject);
            BodyPart bpHTML = new MimeBodyPart();
            bpHTML.setContent(htmlText, "text/html");
            MimeMultipart multipart = new MimeMultipart("related");
            multipart.addBodyPart(bpHTML);
            if (images != null) {
                for (int i = 0; i < images.length; i++) {
                    BodyPart messageBodyPart = new MimeBodyPart();
                    javax.activation.DataSource fds = new FileDataSource(
                            images[i]);
                    messageBodyPart.setDataHandler(new DataHandler(fds));
                    messageBodyPart.setHeader("Content-ID", "<"
                            + images[i].getName() + ">");
                    multipart.addBodyPart(messageBodyPart);
                }

            }
            msg.setContent(multipart);
            Transport tr = session.getTransport("smtp");
            tr.connect(smtphost, username, password);
            msg.saveChanges();
            tr.sendMessage(msg, msg.getAllRecipients());
            tr.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new AplicacaoException("Mensagem de E-mail não enviada", e);
        }
        
        return;
    }

    public static void enviarEmailSemAutenticacao(String smtphost, String subject, String from, String fromText, 
                                                  String to, String text) throws AplicacaoException {
        try {
            String mailer = "JavaMail API";
            Properties props = System.getProperties();
            props.put("mail.smtp.auth", "false");
            Session session = Session.getDefaultInstance(props, null);
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from, fromText));
            msg.setRecipients(javax.mail.Message.RecipientType.TO,
                    InternetAddress.parse(to, false));
            msg.setSubject(subject);
            msg.setText(text);
            msg.setHeader("X-Mailer", mailer);
            msg.setSentDate(new Date());
            Transport tr = session.getTransport("smtp");
            msg.saveChanges();
            tr.sendMessage(msg, msg.getAllRecipients());
            tr.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new AplicacaoException("Mensagem de E-mail não enviada", e);
        }
        
       
    }

    private static BodyPart getFileBodyPart(String filename, String contentType) throws MessagingException {
        BodyPart bp = new MimeBodyPart();
        bp.setDataHandler(new DataHandler(new FileDataSource(filename)));
        return bp;
    }

    private static BodyPart getStringBodyPart(String texto, String contentType) throws MessagingException {
        BodyPart bp = new MimeBodyPart();
        bp.setDataHandler(new DataHandler(new FileDataSource(texto)));
        return bp;
    }
}