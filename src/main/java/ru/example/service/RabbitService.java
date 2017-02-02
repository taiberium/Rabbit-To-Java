package ru.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

import static java.util.Collections.singletonMap;

/**
 * Created by rakhmetov on 02.02.17.
 */
@Service
public class RabbitService {

    private final RabbitTemplate rabbitTemplate;
    private final String queueName;

    @Autowired
    public RabbitService(@Value("${rabbit.tone.queue.name}") String queueName, RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.queueName = queueName;
    }

    @RabbitListener(queues = "${rabbit.tone.queue.name}")
    public void handleMessage(Map message) throws IOException {
        System.out.println(message);
    }

    public void publishMessage(String message) throws JsonProcessingException {
        rabbitTemplate.convertAndSend(queueName, singletonMap("message", message));
    }
}
