package com.zss.rabbitmqconsumer.mq;

import com.rabbitmq.client.Channel;
import com.zss.rabbitmqconsumer.config.RabbitConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


/**
 * @author ZSS
 * @date 2019/10/6 14:25
 * @description 接收rabbitmq中的消息
 */
@Component
public class ReceiveHandler {

    @RabbitListener(queues = {RabbitConfig.QUEUE_INFORM_EMAIL})
    public void getEmail(String msg, Message message, Channel channel) {
        System.out.println("获取到消息：" + msg);
    }

}
