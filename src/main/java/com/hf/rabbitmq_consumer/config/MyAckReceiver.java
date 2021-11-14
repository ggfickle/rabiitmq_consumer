package com.hf.rabbitmq_consumer.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName: MyAckReceiver
 * @author: xiehongfei
 * @description:
 **/
@Component
public class MyAckReceiver implements ChannelAwareMessageListener {
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {

            byte[] bytes = message.getBody();
            String result = new String(bytes);
            JSONObject deserialize = JSON.parseObject(result);

            if ("TestDirectQueue".equals(message.getMessageProperties().getConsumerQueue())) {
                System.out.println("消费的消息来自的队列名为：" + message.getMessageProperties().getConsumerQueue());
                System.out.println("消息成功消费到  id:" + deserialize.get("id") + "  name:" + deserialize.get("name") + "  age:" + deserialize.get("age"));
                System.out.println("执行TestDirectQueue中的消息的业务处理流程......");
            }

            if ("topic.woman".equals(message.getMessageProperties().getConsumerQueue())) {
                System.out.println("消费的消息来自的队列名为：" + message.getMessageProperties().getConsumerQueue());
                System.out.println("消息成功消费到  id:" + deserialize.get("id") + "  name:" + deserialize.get("name") + "  age:" + deserialize.get("age"));
                System.out.println("执行fanout.A中的消息的业务处理流程......");

            }

            //第二个参数，手动确认可以被批处理，当该参数为 true 时，则可以一次性确认 delivery_tag 小于等于传入值的所有消息
            channel.basicAck(deliveryTag, true);
            //第二个参数，true会重新放回队列，所以需要自己根据业务逻辑判断什么时候使用拒绝
            // channel.basicReject(deliveryTag, true);
        } catch (Exception e) {
            channel.basicReject(deliveryTag, false);
            e.printStackTrace();
        }
    }
}
