package com.zss.rabbitmqconsumer.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @author ZSS
 * @date 2019/10/5 10:20
 * @description 第一个消费者
 */
public class Consumer01 {

    private static final String QUEUE_NAME = "hello_rabbit";

    public static void main(String[] args) {
        // 通过连接工厂拆功能键鑫的连接和mq建立连接
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("60.205.179.156");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("20171109");
        // 设置虚拟机
        factory.setVirtualHost("/");

        Connection connection = null;
        Channel channel = null;
        try {
            // 建立新连接
            connection = factory.newConnection();
            channel = connection.createChannel();
            // 申明队列，在此声明队列是为了防止没有队列
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);

            // 实现消费方法
            DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
                /**
                 * 当接收到消息后，此方法将被调用
                 * @param consumerTag 消费者标签，用来表示消费者，在监听队列时设置channel,basicConsume
                 * @param envelope 信封
                 * @param properties 消息属性
                 * @param body 消息内容
                 * @throws IOException
                 */
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                    // 获取交换机
                    String exchange = envelope.getExchange();
                    // 消息id,mq在channel中用来表示消息的id，可用于确认消息已接收
                    long deliveryTag = envelope.getDeliveryTag();
                    // 消息内容
                    String message = new String(body, StandardCharsets.UTF_8);
                    System.out.println("receive message:" + message);
                }
            };

            // 监听队列
            // 参数：String queue, boolean autoAck, Consumer callback
            /*
             * 参数明细
             * 1、queue：队列名称
             * 2、autoAck：自动回复，当消费者接收到消息后要告诉mq消息已接收，如果将此参数设置为true则表示会自动恢复mq
             * 3、callback：消费方法，当消费者接收消息要执行的方法
             */
            channel.basicConsume(QUEUE_NAME, true, defaultConsumer);
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        } finally {
            System.out.println("application end");
        }

    }
}
