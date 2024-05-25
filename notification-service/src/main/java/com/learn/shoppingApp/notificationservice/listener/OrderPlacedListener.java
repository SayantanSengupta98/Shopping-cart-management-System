package com.learn.shoppingApp.notificationservice.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.learn.shoppingApp.notificationservice.constant.AppContants;
import com.learn.shoppingApp.notificationservice.model.OrderPlacedEvent;
import com.learn.shoppingApp.notificationservice.service.EMailService;

@Component
public class OrderPlacedListener {
	private EMailService eMailService;
	

	public OrderPlacedListener(EMailService eMailService) {
		this.eMailService = eMailService;
	}


	@KafkaListener(topics = AppContants.notificationTopic, groupId = "group-email-4")
	public void orderPlacedNotification(OrderPlacedEvent orderNotificaton)
	{
		System.out.println(orderNotificaton.getOrderNumber());
		
		eMailService.sendEmail(orderNotificaton.getOrderNumber());
	}
}
