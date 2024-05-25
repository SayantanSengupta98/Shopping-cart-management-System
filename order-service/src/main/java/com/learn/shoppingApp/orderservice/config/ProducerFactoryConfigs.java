package com.learn.shoppingApp.orderservice.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.learn.shoppingApp.orderservice.model.OrderPlacedEvent;

@Configuration
public class ProducerFactoryConfigs {
	
	@Bean
	public Map<String,Object> producerConfigs()
	{
		Map<String, Object> props= new HashMap<>();
		
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		props.put(JsonSerializer.TYPE_MAPPINGS, "noti:com.learn.shoppingApp.orderservice.model.OrderPlacedEvent");
		return props;
	}
	
	@Bean
	public ProducerFactory<String, OrderPlacedEvent> producerFactory() {
	    return new DefaultKafkaProducerFactory<>(producerConfigs());
	}
	
	@Bean
	public KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate(){
		return new KafkaTemplate<String, OrderPlacedEvent>(producerFactory());
	}
	
	
	
}
