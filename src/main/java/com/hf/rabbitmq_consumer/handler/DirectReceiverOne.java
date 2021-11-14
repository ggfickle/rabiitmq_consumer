package com.hf.rabbitmq_consumer.handler;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "TestDirectQueue")
public class DirectReceiverOne {

    @RabbitHandler
    public void receiveMessage(String json) {
        System.out.println("第一个消费者接收到消息" + json);
    }
}
