package com.listener.listener.service;

import com.listener.listener.model.Resume;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {

    @RabbitListener(queues = "${com.rabbitmq.queue}")
    public void recievedMessage(Resume resume) {
        System.out.println("Recieved Message From RabbitMQ: " + resume);
    }
}
