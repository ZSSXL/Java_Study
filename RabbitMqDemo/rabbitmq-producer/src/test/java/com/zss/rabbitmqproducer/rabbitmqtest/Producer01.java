package com.zss.rabbitmqproducer.rabbitmqtest;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * rabbitmq 的入门程序
 *
 * @author ZSS
 * @date 2019/10/3 21:02
 * @description 测试rabbitmq生产者
 */
public class Producer01 {

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
        // 建立新连接
        Connection connection = null;
        Channel channel = null;
        try {
            // 建立新连接
            connection = factory.newConnection();
            // 创建会话通道，生产者和mq服务所哟u通信都在channel通道中完成
            channel = connection.createChannel();
            // 声明队列 如果队列在mq中没有，则创建
            // 参数：String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments
            /*
             * 参数明细
             *  1、queue：队列名称
             *  2、durable：是否持久化，如果持久化，重启mq后，队列还在
             *  3、exclusive：是否排他，独占连接，队列允许在连接中访问，如果连接关闭队列自动删除，如果将糍参数设置为true可用于临时队列的创建
             *  4、autoDelete：自动删除，队列不在使用时是否自动删除此队列，如果将此参数和exclusive参数设置为true就可以实现临时队列（队列不用就自动删除）
             *  5、arguments：参数，可以设置一个队列的扩展参数，比如：存活时间
             */
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            // 发送消息
            // String exchange, String routingKey, BasicProperties props, byte[] body
            /*
             *  参数明细
             *  1、exchange 交换机，如果不指定将使用mq默认的交换机(设置为空字符串)
             *  2、routingKey 路由key，交换机根据路由key来将消息转发到指定的队列，如果使用默认交换机，routingKey设置为队列的名称
             *  3、props：消息的属性
             *  4、body：消息内容
             */
            // 消息内容
            String message = "你好吗？";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("send message to mq " + message);
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭通道
                assert channel != null;
                channel.close();
            } catch (IOException | TimeoutException e) {
                e.printStackTrace();
            }
            try {
                // 关闭连接
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("application end");
        }
    }

}
