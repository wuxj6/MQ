package com.wuxj.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.wuxj.util.ResourceUtil;

public class DeleteTestAllData {
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri(ResourceUtil.getKey("rabbitmq.uri"));
        // 建立连接
        Connection conn = factory.newConnection();
        // 创建消息通道
        Channel channel = conn.createChannel();

        String[] queueNames = {"ORIGIN_QUEUE","GP_FIRST_QUEUE", "GP_FOURTH_QUEUE", "GP_SECOND_QUEUE", "GP_THIRD_QUEUE",
                "MY_FIRST_QUEUE", "MY_FOURTH_QUEUE", "MY_SECOND_QUEUE", "MY_THIRD_QUEUE",
                "SIMPLE_QUEUE","TEST_TTL_QUEUE","TEST_DLX_QUEUE","DLX_QUEUE",
        "GP_ORI_USE_QUEUE","GP_DEAD_LETTER_QUEUE","DELAY_QUEUE","TEST_LIMIT_QUEUE","ALTERNATE_QUEUE",
        "MY_FIVE_QUEUE","MY_SIX_QUEUE"};


        String[] exchangeNames = {"GP_DIRECT_EXCHANGE","GP_FANOUT_EXCHANGE", "GP_TOPIC_EXCHANGE",
                "MY_DIRECT_EXCHANGE", "MY_FANOUT_EXCHANGE", "MY_TOPIC_EXCHANGE",
                "SIMPLE_EXCHANGE","DLX_EXCHANGE","GP_ORI_USE_EXCHANGE","GP_DEAD_LETTER_EXCHANGE",
        "DELAY_EXCHANGE","SIMPLE_EXCHANGE","ALTERNATE_EXCHANGE","TEST_EXCHANGE"};

        for(String queueName : queueNames){
            channel.queueDelete(queueName);
        }

        for(String exchangeName : exchangeNames){
            channel.exchangeDelete(exchangeName);
        }

        channel.close();
        conn.close();

    }
}
