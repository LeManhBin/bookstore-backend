package com.bookstore.BookStoreSpringBoot.object;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;
@Component
public class EmailProvider {
	JavaMailSender mailSender = new JavaMailSender() {
		
		@Override
		public void send(SimpleMailMessage... simpleMessages) throws MailException {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void send(SimpleMailMessage simpleMessage) throws MailException {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void send(MimeMessagePreparator... mimeMessagePreparators) throws MailException {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void send(MimeMessagePreparator mimeMessagePreparator) throws MailException {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void send(MimeMessage... mimeMessages) throws MailException {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void send(MimeMessage mimeMessage) throws MailException {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public MimeMessage createMimeMessage(InputStream contentStream) throws MailException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public MimeMessage createMimeMessage() {
			// TODO Auto-generated method stub
			return null;
		}
	};
	public boolean sendEmail(String emailTo, String subject, String body) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(emailTo);
			message.setSubject(subject);
			message.setText(body);
			mailSender.send(message);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
}
