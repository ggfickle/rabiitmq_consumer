package com.hf.rabbitmq_consumer.handler;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "TestDirectQueue")
public class DirectReceiverOne {

    @RabbitHandler
    public void receiveMessage(Map<String,String> map) {
        System.out.println("第一个消费者接收到消息" + map.toString());
    }
}
