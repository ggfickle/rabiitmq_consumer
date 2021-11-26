package com.hf.rabbitmq_consumer.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @ClassName: MyAckReceiver
 * @author: xiehongfei
 * @description:
 **/
@Component
@Slf4j
public class MyAckReceiver implements ChannelAwareMessageListener {
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            byte[] bytes = message.getBody();
            String result = new String(bytes, StandardCharsets.UTF_8);
            JSONObject deserialize = JSON.parseObject(result);

            if ("TestDirectQueue".equals(message.getMessageProperties().getConsumerQueue())) {
                log.info("消费的消息来自的队列名为:{}, deliveryTag: {}", message.getMessageProperties().getConsumerQueue(), deliveryTag);
                log.info("消息成功消费到  id:" + deserialize.get("id") + "  name:" + deserialize.get("name") + "  age:" + deserialize.get("age"));
                log.info("执行TestDirectQueue中的消息的业务处理流程......");
            }

            if ("topic.woman".equals(message.getMessageProperties().getConsumerQueue())) {
                log.info("消费的消息来自的队列名为:{}, deliveryTag: {}", message.getMessageProperties().getConsumerQueue(), deliveryTag);
                log.info("消息成功消费到  id:" + deserialize.get("id") + "  name:" + deserialize.get("name") + "  age:" + deserialize.get("age"));
                log.info("执行topic.woman中的消息的业务处理流程......");
            }

            if ("topic.man".equals(message.getMessageProperties().getConsumerQueue())) {
                log.info("消费的消息来自的队列名为:{}, deliveryTag: {}", message.getMessageProperties().getConsumerQueue(), deliveryTag);
                log.info("消息成功消费到  id:" + deserialize.get("id") + "  name:" + deserialize.get("name") + "  age:" + deserialize.get("age"));
                log.info("执行topic.man中的消息的业务处理流程......");
            }

            //第二个参数，手动确认可以被批处理，当该参数为 true 时，则可以一次性确认 delivery_tag 小于等于传入值的所有消息
            channel.basicAck(deliveryTag, true);
        } catch (Exception e) {
            // channel.basicReject(deliveryTag, true);  拒绝消费当前消息，如果第二参数传入true，就是将数据重新丢回队列里，那么下次还会消费这消息。
            // 设置false，就是告诉服务器，我已经知道这条消息数据了，因为一些原因拒绝它，而且服务器也把这个消息丢掉就行。 下次不想再消费这条消息了。
            channel.basicReject(deliveryTag, false);
            log.error(e.getLocalizedMessage());
        }
    }
}
