package com.zss.rabbitmqproducer.rabbitmqtest;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.zss.rabbitmqproducer.config.RabbitConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author ZSS
 * @date 2019/10/5 16:39
 * @description rabbitmq spring
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class Producer05TopicSpring {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 使用rabbitTemplate发送消息
     */
    @Test
    public void testSendEmail() {
        String message = "你在哪里！！";
        /*
         * 参数
         * 1、交换机名称
         * 2、路由
         * 3、消息内容
         */
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_TOPIC_INFORM, "inform.email", message);
    }

}
