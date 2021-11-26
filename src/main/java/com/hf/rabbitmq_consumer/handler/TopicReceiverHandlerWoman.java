package com.hf.rabbitmq_consumer.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
//@RabbitListener(queues = "topic.woman")
@Slf4j
public class TopicReceiverHandlerWoman {

//    @RabbitHandler
    public void process(String testMessage) {
        log.info("TopicManReceiverWoman消费者收到消息  : " + testMessage);
    }
}
