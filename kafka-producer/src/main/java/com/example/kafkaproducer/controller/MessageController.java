package com.example.kafkaproducer.controller;

import dto.MyMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.philosophyit.lib.sync.events.NotificationEvent;
import ru.philosophyit.lib.sync.service.Gateway;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/message")
public class MessageController {

    private final KafkaTemplate<String, MyMessage> kafkaTemplate;
    private final Gateway gateway;

    @PostMapping
    public void publish(@RequestBody MyMessage message) {
        kafkaTemplate.send("message-topic", message);
        NotificationEvent<MyMessage> notificationEvent = new NotificationEvent<>("myUsername", message);
        gateway.send(notificationEvent);
    }
}
