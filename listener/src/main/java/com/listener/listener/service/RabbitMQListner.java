package com.listener.listener.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Service;


@Service
public class RabbitMQListner implements MessageListener{

    public void onMessage(Message message) {

    }
}
