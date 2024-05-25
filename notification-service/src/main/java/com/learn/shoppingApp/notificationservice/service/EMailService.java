package com.learn.shoppingApp.notificationservice.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EMailService {
	
	private JavaMailSender javaMailSender;
	private String fromMail;
	private String body;
	
	public EMailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
		this.fromMail = "no-reply";
		this.body = """
				Dear Customer,
				
				Your order is successfully placed from our system.
				Payment will be processed within the next 5 minutes.
				Your Order number is -  """;
	}
		
	public void sendEmail(String orderNumber)
	{
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		
		mailMessage.setFrom(fromMail);
		mailMessage.setTo("sayantan474@gmail.com");
		mailMessage.setSubject("Order Placed Successfully!");
		mailMessage.setText(body+orderNumber);
		
		 javaMailSender.send(mailMessage);
	}


}
