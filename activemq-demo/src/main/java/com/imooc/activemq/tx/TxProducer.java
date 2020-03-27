package com.imooc.activemq.tx;


import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class TxProducer {
            //  linux 上部署的activemq 的 IP 地址 + activemq 的端口号，如果用自己的需要改动
    public static final String ACTIVEMQ_URL = "tcp://192.168.71.131:61616";
    // public static final String ACTIVEMQ_URL = "nio://192.168.17.3:61608";
    public static final String QUEUE_NAME = "queue01";


    public static void main(String[] args) throws  Exception{
        ActiveMQConnectionFactory activeMQConnectionFactory=new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        MessageProducer producer = session.createProducer(queue);
        for (int i = 1; i <=3; i++) {
            TextMessage textMessage = session.createTextMessage("消息"+i);
            producer.send(textMessage);
        }
        producer.close();
        session.commit();
        session.close();
        connection.close();
        System.out.println("发送消息完成");
    }
}
