package com.santosh.web.controllers;

import com.santosh.ApplicationProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitMQDemoController {
    private final RabbitTemplate rabbitTemplate;
    private final ApplicationProperties properties;

    RabbitMQDemoController(RabbitTemplate rabbitTemplate, ApplicationProperties properties) {

        this.rabbitTemplate = rabbitTemplate;
        this.properties = properties;
    }

    @PostMapping("/send")
    public void sendMessage(@RequestBody MyMessage myMessage) {
        rabbitTemplate.convertAndSend(properties.orderEventsExchange(), myMessage.routingKey(), myMessage.myPayload());
    }
}

record MyMessage(String routingKey, MyPayload myPayload) {}

record MyPayload(String content) {}
