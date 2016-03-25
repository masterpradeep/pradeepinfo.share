package com.gourabpaul.web.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailGun {

	public void sendMail(String emailid, String radiovalue) {
		String to = emailid;

	      // Sender's email ID needs to be mentioned
	      String from = "resume@pradeepinfo.sharenodes.com";

	      // Assuming you are sending email from localhost
	      String host = "localhost";

	      // Get system properties
	      Properties properties = System.getProperties();

	      // Setup mail server
	      properties.setProperty("mail.smtp.host", host);
	      properties.setProperty("mail.smtp.port", "25");

	      // Get the default Session object.
	      Session session = Session.getDefaultInstance(properties);

	      try{
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from,"Pradeep Tripathi"));

	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	         message.setSubject("Pradeep Tripathi Resume");
	       //3) create MimeBodyPart object and set your message text     
	         BodyPart messageBodyPart1 = new MimeBodyPart();  
	         messageBodyPart1.setText("Please find the attachment for resume");  
	           

	       //4) create new MimeBodyPart object and set DataHandler object to this object      
	         MimeBodyPart messageBodyPart2 = new MimeBodyPart();  
	       
	         String filename = "/tmp/resume/resume_pradeep."+radiovalue;//change accordingly  
	         try {
				new FileInputStream(filename).close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         DataSource source = new FileDataSource(filename);  
	         messageBodyPart2.setDataHandler(new DataHandler(source));  
	         messageBodyPart2.setFileName("pradeep_resume."+radiovalue);  
	          
	          
	         //5) create Multipart object and add MimeBodyPart objects to this object      
	         Multipart multipart = new MimeMultipart();  
	         multipart.addBodyPart(messageBodyPart1);  
	         multipart.addBodyPart(messageBodyPart2);  
	       
	         //6) set the multiplart object to the message object  
	         message.setContent(multipart );  
	         // Now set the actual message

	         // Send message
	         Transport.send(message);
	         System.out.println("Sent message successfully....");
	      }catch (MessagingException | UnsupportedEncodingException mex) {
	         mex.printStackTrace();
	      }
	}
	public static void main(String args[]){
		MailGun mg = new MailGun();
		mg.sendMail("pk.tripathi700@gmail.com", "pdf");
	}

}
