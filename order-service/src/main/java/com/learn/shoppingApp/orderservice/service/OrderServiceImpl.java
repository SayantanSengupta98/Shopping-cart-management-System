package com.learn.shoppingApp.orderservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.learn.shoppingApp.orderservice.customException.OutOfStockException;
import com.learn.shoppingApp.orderservice.dto.OrderDto;
import com.learn.shoppingApp.orderservice.dto.OrderItemDto;
import com.learn.shoppingApp.orderservice.model.Order;
import com.learn.shoppingApp.orderservice.model.OrderItem;
import com.learn.shoppingApp.orderservice.model.OrderPlacedEvent;
import com.learn.shoppingApp.orderservice.repository.OrderRepository;

import constant.KafkaConstants;

@Service
public class OrderServiceImpl implements OrderService {
	
	OrderRepository  orderRepository;
	RestClient.Builder restClient;
	KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;
	
	@Autowired
	public OrderServiceImpl(OrderRepository orderRepository, RestClient.Builder restClient, KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate) {
	this.orderRepository = orderRepository;
	this.restClient = restClient;
	this.kafkaTemplate = kafkaTemplate;
	}

	@Override
	public String createOrder(OrderDto orderRequest) {
		
		List<OrderItemDto> orderItemRequestDto = orderRequest.getOrderItems();
		List<OrderItem> orderItems = orderItemRequestDto.stream().map(eachItem -> this.mapToOrderItem(eachItem)).toList();
		List<String> skuCodes = orderItems.stream().map(orderItem -> orderItem.getSkuCode()).toList();
			
		List<Integer> availableQuantityList = restClient.build()
				.get()
				.uri(uriBuilder -> uriBuilder
				.queryParam("skuCodes",String.join(",",skuCodes))
			    .build())
				.retrieve().body(new ParameterizedTypeReference<List<Integer>>(){});
		
		if(checkAvailabity(availableQuantityList,orderItems))
		{
			Order order = Order.builder().orderNumber(UUID.randomUUID().toString())
					.orderItems(orderItems).build();

			Order savedOrder = orderRepository.save(order);
			
			String orderNum = savedOrder.getOrderNumber();
			
			kafkaTemplate.send(KafkaConstants.notificationTopic, new OrderPlacedEvent(orderNum));
			
			return "Order Created with id: "+orderNum;
		}
		else
			return "Some Product might not be in stock...";
	}

	private boolean checkAvailabity(List<Integer> availableQuantityList, List<OrderItem> orderItems) {
		for(int i=0; i< availableQuantityList.size();i++)
		{
			if(availableQuantityList.get(i) < orderItems.get(i).getQuantity())
			{
				throw new OutOfStockException("Product "+orderItems.get(i).getSkuCode()+" is not is stock!");
			}
		}
		return true;
	}

	private OrderItem mapToOrderItem(OrderItemDto eachItem) {
		return OrderItem.builder().skuCode(eachItem.getSkuCode())
				.price(eachItem.getPrice())
				.quantity(eachItem.getQuantity()).build();
	}

}
