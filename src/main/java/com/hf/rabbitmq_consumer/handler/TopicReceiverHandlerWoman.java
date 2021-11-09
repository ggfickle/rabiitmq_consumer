package com.hf.rabbitmq_consumer.handler;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "topic.woman")
public class TopicReceiverHandlerWoman {

    @RabbitHandler
    public void process(Map<String, Object> testMessage) {
        System.out.println("TopicManReceiverWoman消费者收到消息  : " + testMessage.toString());
    }
}
