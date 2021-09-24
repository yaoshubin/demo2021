package com.example.demo.mq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * 核心接口
 *  consumer.setMessageListener(new MessageListener() {
 *                 @Override
 *                 public void onMessage(Message message) {
 *                     if(null != message  &&  message instanceof TextMessage){
 *                         try {
 *                             System.out.println(((TextMessage) message).getText());
 *                         } catch (JMSException e) {
 *                             e.printStackTrace();
 *                         }
 *                     }
 *                 }
 *             });
 */
public class JmsConsumListener {

    public static final String ACTIVEMQ_URL = "tcp://192.168.174.136:61616";
    public static final String QUEUE_NAME = "QUEUE_01";


    public static void main(String[] args) throws JMSException, IOException {

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

            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    if(null != message  &&  message instanceof TextMessage){
                        try {
                            System.out.println(((TextMessage) message).getText());
                        } catch (JMSException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.in.read();
            consumer.close();
            session.close();
            connection.close();
        }
    }
}
