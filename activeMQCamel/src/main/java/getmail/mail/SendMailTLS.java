package getmail.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import getmail.beans.MessageDTO;
import getmail.handler.QueueHandler;


public class SendMailTLS {

	//RECORDAR: HAY QUE DESHABILITAR ACCESO SEGURO
	
	private final Logger logger = LoggerFactory.getLogger(SendMailTLS.class);
	

	
	private String username;
	private String password;
	
	public void sendTOGmailTLS(MessageDTO mDTO, String _user, String _pass) {
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		username = _user;
		password = _pass;
		
		logger.info("[SendMailTLS] try to connect TLS smtp.gmail.com:587 ({}:{})",username,password);

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("from-email@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(mDTO.to));
			message.setSubject(mDTO.subject);
			message.setText(mDTO.content);
			
			Transport.send(message);
			logger.info("[SendMailTLS] Send Mail [{}] Done!",mDTO.toString());

		} catch (MessagingException e) {
			logger.warn("[SendMailTLS] error [{}]",e.getMessage());
		}
	}
}