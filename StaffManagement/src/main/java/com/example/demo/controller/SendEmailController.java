package com.example.demo.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Staff;
import com.example.demo.repository.SendEmail;
import com.example.demo.repository.StaffRepository;


@RestController
@RequestMapping(value = "/api")
public class SendEmailController {
	
	@Autowired
	StaffRepository staffRepo;
	
	@Value("${gmail.username}")
	private String username;
	
	@Value("${gmail.password}")
	private String password;
	
	@PostMapping("/sendemail/{staffId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> sendEmail(@RequestBody SendEmail sendEmail,@PathVariable("staffId") Long staffId) throws AddressException, MessagingException, IOException {
		try {
			   sendingEmail(sendEmail, staffId);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	private void sendingEmail(SendEmail sendEmail, Long id) throws AddressException, MessagingException, IOException {
		Optional<Staff> staffData = staffRepo.findById(id);
		
		Properties props = new Properties();
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.starttls.enable", "true");
		   props.put("mail.smtp.host", "smtp.gmail.com");
		   props.put("mail.smtp.port", "587");
		   
		   Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			      protected PasswordAuthentication getPasswordAuthentication() {
			         return new PasswordAuthentication(username, password);
			      }
			   });
		   
		   Message msg = new MimeMessage(session);
		   msg.setFrom(new InternetAddress(username, false));

		   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(staffData.get().getStaffEmail()));
		   msg.setSubject(sendEmail.getSubject());
		   msg.setContent(sendEmail.getBody(), "text/html");
		   msg.setSentDate(new Date());
		   
		   MimeBodyPart messageBodyPart = new MimeBodyPart();
		   messageBodyPart.setContent(sendEmail.getBody(), "text/html");

		   Multipart multipart = new MimeMultipart();
		   multipart.addBodyPart(messageBodyPart);
		   MimeBodyPart attachPart = new MimeBodyPart();

//		   attachPart.attachFile("D:\\baocao.docx");
		   attachPart.attachFile(sendEmail.getImage());
		   multipart.addBodyPart(attachPart);
		   msg.setContent(multipart);
		   Transport.send(msg);
		   
	}
}
