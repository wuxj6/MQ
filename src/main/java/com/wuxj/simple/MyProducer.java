package com.wuxj.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.wuxj.util.ResourceUtil;

/**
 * @author wuxj6
 * @version 1.0.0
 * @ClassName MyProducer.java
 * @Description TODO
 * @createTime 2022/1/7 17:41
 */
public class MyProducer {
    private final static String EXCHANGE_NAME = "SIMPLE_EXCHANGE";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri(ResourceUtil.getKey("rabbitmq.uri"));
        // 建立连接
        Connection connection = factory.newConnection();
        // 创建信道
        Channel channel = connection.createChannel();
        // 发送消息
        String msg = "Hello world, Rabbit MQ";
        // String exchange, String routingKey, BasicProperties props, byte[] body
        channel.basicPublish(EXCHANGE_NAME, "wuxj.best", null, msg.getBytes());

        channel.close();
        connection.close();
    }
}
