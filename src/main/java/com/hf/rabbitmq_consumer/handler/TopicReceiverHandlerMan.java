package com.hf.rabbitmq_consumer.handler;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 注意要加Component注解
 */
@Component
@RabbitListener(queues = "topic.man")
public class TopicReceiverHandlerMan {

    @RabbitHandler
    public void process(String json) {
        System.out.println("TopicManReceiverMan消费者收到消息  : " + json);
    }
}
