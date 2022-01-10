package com.wuxj.returnlistener;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.wuxj.util.ResourceUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author wuxj6
 * @version 1.0.0
 * @ClassName ReturnListenerBackProducer.java
 * @Description TODO 备份交换机
 * @createTime 2022/1/10 10:44
 */
public class ReturnListenerBackProducer {
    public static void main(String[] args) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri(ResourceUtil.getKey("rabbitmq.uri"));

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder().deliveryMode(2).
                contentEncoding("UTF-8").build();

        // 备份交换机
        channel.exchangeDeclare("ALTERNATE_EXCHANGE","topic", false, false, false, null);
        channel.queueDeclare("ALTERNATE_QUEUE", false, false, false, null);
        channel.queueBind("ALTERNATE_QUEUE","ALTERNATE_EXCHANGE","#");

        // 在声明交换机的时候指定备份交换机
        Map<String,Object> arguments = new HashMap<String,Object>();
        arguments.put("alternate-exchange","ALTERNATE_EXCHANGE");
        channel.exchangeDeclare("TEST_EXCHANGE","topic", false, false, false, arguments);


        // 发送到了默认的交换机上，由于没有任何队列使用这个关键字跟交换机绑定，所以会被退回
        // 第三个参数是设置的mandatory，如果mandatory是false，消息也会被直接丢弃
        channel.basicPublish("TEST_EXCHANGE","wuxj5534",true, properties,"只为更好的你".getBytes());

        TimeUnit.SECONDS.sleep(10);

        channel.close();
        connection.close();
    }
}
