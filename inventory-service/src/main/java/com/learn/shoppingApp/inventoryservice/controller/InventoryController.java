package com.learn.shoppingApp.inventoryservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learn.shoppingApp.inventoryservice.service.InventoryService;

@RestController
@RequestMapping("api/inventory")
public class InventoryController {
	InventoryService inventoryService;
	
	public InventoryController(InventoryService inventoryService) {
		this.inventoryService = inventoryService;
	}


	@GetMapping
	List<Integer> isInStock(@RequestParam(value = "skuCodes") List<String> skuCodes)					
	
	{
		return inventoryService.isEverythingInStock(skuCodes);
	}

}
