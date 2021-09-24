package com.example.demo.mq;


import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 阻塞式 ：TextMessage textMessage = (TextMessage) consumer.receive();
 * 消费完后会一直等着
 */

public class JmsConsumer {

    public static final String ACTIVEMQ_URL = "tcp://192.168.174.136:61616";
    public static final String QUEUE_NAME = "QUEUE_01";


    public static void main(String[] args) throws JMSException {

        Connection connection = null;
        Session session = null;
        MessageConsumer consumer= null;
        try {
            //工厂
            ActiveMQConnectionFactory mqFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
            //建立连接
            connection = mqFactory.createConnection();
            connection.start();
            //获取Session
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //获得destination  是queue 还是topic
            Queue queue = session.createQueue(QUEUE_NAME);
            consumer = session.createConsumer(queue);
            while (true){
                TextMessage textMessage = (TextMessage) consumer.receive(3000L);
                if(textMessage != null){
                    System.out.println("消费消息:"+textMessage.getText());
                }else{
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            consumer.close();
            session.close();
            connection.close();
        }
    }
}
