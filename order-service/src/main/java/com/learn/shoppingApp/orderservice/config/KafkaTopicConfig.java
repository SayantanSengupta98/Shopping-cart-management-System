package com.learn.shoppingApp.orderservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import constant.KafkaConstants;

@Configuration
public class KafkaTopicConfig {

	@Bean
	public NewTopic topic() {
        return TopicBuilder.name(KafkaConstants.notificationTopic)
                .partitions(2)
                .build();
    }
}
