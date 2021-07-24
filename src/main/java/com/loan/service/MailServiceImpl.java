package com.loan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Primary
public class MailServiceImpl implements iMailService {

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendMail(String email) throws MailException {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(email);
		mail.setFrom("edu9vision@gmail.com");
		mail.setSubject("Borrow Loan Company");
		mail.setText("Thanks for Contacting Us...We will Contact u soon");
		javaMailSender.send(mail);
	}

}
