package com.hf.rabbitmq_consumer.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "TestDirectQueue")
@Slf4j
public class DirectReceiverOne {

    @RabbitHandler
    public void receiveMessage(String json) {
        log.info("第一个消费者接收到消息" + json);
    }
}
