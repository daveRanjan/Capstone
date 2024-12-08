package com.scaler.cartservice.configs;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {
    @Bean
    public NewTopic cartEventsTopic() {
        return new NewTopic("cart-events", 1, (short) 1);
    }
}
