package com.example.kafkaproducer.config;


import dto.MyMessage;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {
    @Value("${PRODUCER_KAFKA_HOST}")
    private String PRODUCER_KAFKA_HOST;
    @Value("${PRODUCER_KAFKA_PORT}")
    private String PRODUCER_KAFKA_PORT;

    @Bean
    public ProducerFactory<String, MyMessage> producerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, PRODUCER_KAFKA_HOST + ":" + PRODUCER_KAFKA_PORT);
        props.put("spring.json.add.type.headers", false);
        props.put("spring.json.value.default.type", MyMessage.class);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(props);
    }
    @Bean
    public KafkaTemplate<String, MyMessage> kafkaTemplate(ProducerFactory<String, MyMessage> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }
}
