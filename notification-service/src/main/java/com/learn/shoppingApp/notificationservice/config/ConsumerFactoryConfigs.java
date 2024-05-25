package com.learn.shoppingApp.notificationservice.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.learn.shoppingApp.notificationservice.model.OrderPlacedEvent;

@Configuration
public class ConsumerFactoryConfigs {

	@Bean
	public Map<String,Object> consumerConfigs()
	{
		Map<String, Object> props= new HashMap<>();
		
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.learn.shoppingApp.notificationservice.model");
		props.put(JsonDeserializer.TYPE_MAPPINGS, "noti:com.learn.shoppingApp.notificationservice.model.OrderPlacedEvent");
		return props;
	}
	
	@Bean
	public ConsumerFactory<String, OrderPlacedEvent> consumerFactory() {
	    return new DefaultKafkaConsumerFactory<>(consumerConfigs());
	}
	
   @Bean
    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, OrderPlacedEvent>>
                        kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, OrderPlacedEvent> factory =
                                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
   }
}
