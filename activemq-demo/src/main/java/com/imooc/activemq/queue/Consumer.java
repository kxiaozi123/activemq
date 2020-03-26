package com.imooc.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.util.StringUtils;

import javax.jms.*;
import java.io.IOException;

public class Consumer {
    //  linux 上部署的activemq 的 IP 地址 + activemq 的端口号，如果用自己的需要改动
    public static final String ACTIVEMQ_URL = "tcp://192.168.71.131:61616";
    // public static final String ACTIVEMQ_URL = "nio://192.168.17.3:61608";
    public static final String QUEUE_NAME = "queue01";

    public static void main(String[] args) throws JMSException, IOException {
        //System.out.println("我是二号消费者");
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        MessageConsumer consumer = session.createConsumer(queue);
        //第一种方式 同步阻塞，过时不候
//        while (true)
//        {
//            // 这里是 TextMessage 是因为消息发送者是 TextMessage ， 接受处理的
//            // 也应该是这个类型的消息
//            TextMessage message = (TextMessage) consumer.receive(2000L);  // 2秒
//            if (null != message) {
//                System.out.println("****消费者的消息：" + message.getText());
//            } else {
//                break;
//            }
//
//        }
        //第二种方式 设置消费端的监听器
        //第二种方式，异步非阻塞
        consumer.setMessageListener(message -> {
            if(message instanceof TextMessage)
            {
                try {
                    String text = ((TextMessage) message).getText();
                    System.out.println("****消费者的消息：" + text+","+message.getStringProperty("c01"));

                    //System.out.println("****消费者的消息：" + message.getStringProperty("c01"));
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
            if(message instanceof MapMessage)
            {
                try {
                    String text = ((MapMessage) message).getString("k1");
                    System.out.println("****消费者的消息：" + text);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        //防止太快关闭资源 没有接受到消息
        System.in.read();
        consumer.close();
        session.close();
        connection.close();
    }
}
