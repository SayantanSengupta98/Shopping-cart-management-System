package com.learn.shoppingApp.apiGatewayservice.routesConfig;

import org.springframework.cloud.gateway.server.mvc.filter.LoadBalancerFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;


@Configuration
public class GatewayRoutesConfig {

	
	@Bean
	public RouterFunction<ServerResponse> orderServiceRouter()
	{
		return GatewayRouterFunctions.route("ORDER-SERVICE").route(RequestPredicates.path("/api/order/**"),
												HandlerFunctions.http())
												.filter(LoadBalancerFilterFunctions.lb("order-service"))
												.build();
	}
	
	@Bean
	public RouterFunction<ServerResponse> productServiceRouter()
	{
		return GatewayRouterFunctions.route("PRODUCT-SERVICE").route(RequestPredicates.path("/api/product/**"),
												HandlerFunctions.http())
												.filter(LoadBalancerFilterFunctions.lb("product-service"))
												.build();
	}
	
	@Bean
	public RouterFunction<ServerResponse> inventoryServiceRouter()
	{
		return GatewayRouterFunctions.route("INVENTORY-SERVICE").route(RequestPredicates.path("/api/inventory/**"),
												HandlerFunctions.http())
												.filter(LoadBalancerFilterFunctions.lb("inventory-service"))
												.build();
	}
}
