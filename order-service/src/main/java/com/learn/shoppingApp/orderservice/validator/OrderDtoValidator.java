package com.learn.shoppingApp.orderservice.validator;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.learn.shoppingApp.orderservice.dto.OrderDto;
import com.learn.shoppingApp.orderservice.dto.OrderItemDto;

@Component
public class OrderDtoValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		
		return OrderDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors e) {
		//ValidationUtils.rejectIfEmpty(e, "skuCode", "skuCode.empty");
		
		OrderDto orderDto = (OrderDto)target;
		List<OrderItemDto> orderItems = orderDto.getOrderItems();
		
		for (OrderItemDto orderItem : orderItems) {
			
			if(orderItem.getQuantity() == null || orderItem.getQuantity() <= 0)
				e.rejectValue("orderItems", "quantity.invalid", "Quantity should be greater than 0");
			
			if(orderItem.getPrice() == null || orderItem.getPrice() <= 0)
				e.rejectValue("orderItems", "price.invalid", "Price should be greater than 0");
		}
	}

}
