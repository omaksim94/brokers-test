package com.example.kafkaconsumer.listener;

import dto.MyMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.philosophyit.lib.sync.events.NotificationEvent;

@Slf4j
@Component
public class MessageListener {

    @KafkaListener(topics = "${kafka.topic}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void listener(MyMessage message) {
        log.info("Received message from Kafka: " + message.getText());
    }

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void rabbitListener(NotificationEvent<MyMessage> notificationEvent){
        log.info("Received message from RabbitMQ: " + notificationEvent.getSource().getText());
    }
}
