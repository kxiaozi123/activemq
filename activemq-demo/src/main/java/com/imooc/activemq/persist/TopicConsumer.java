package com.imooc.activemq.persist;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class TopicConsumer {
    //  linux 上部署的activemq 的 IP 地址 + activemq 的端口号，如果用自己的需要改动
    public static final String ACTIVEMQ_URL = "tcp://192.168.71.131:61616";
    // public static final String ACTIVEMQ_URL = "nio://192.168.17.3:61608";
    public static final String TOP_NAME = "topic01";

    public static void main(String[] args) throws JMSException, IOException {
        System.out.println("z3");
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.setClientID("z3");
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(TOP_NAME);
        TopicSubscriber topicSubscriber = session.createDurableSubscriber(topic, "remark..");
        connection.start();

        Message message = topicSubscriber.receive();
        while (null!=message)
        {
            TextMessage textMessage= (TextMessage) message;
            System.out.println(textMessage.getText());
            message=topicSubscriber.receive(3000L);
        }


        session.close();
        connection.close();
    }
}
