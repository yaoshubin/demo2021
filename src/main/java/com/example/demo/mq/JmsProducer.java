package com.example.demo.mq;


import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public  class JmsProducer {

  //  public static final String ACTIVEMQ_URL = "tcp://192.168.174.136:61616";
    public static final String ACTIVEMQ_URL = "tcp://localhost:61616";
    public static final String QUEUE_NAME = "QUEUE_01";


    public static void main(String[] args) throws JMSException {

        try {
            //工厂
            ActiveMQConnectionFactory mqFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
            //建立连接
            Connection connection = mqFactory.createConnection();
            connection.start();
            //获取Session
            Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            //获得destination  是queue 还是topic
            Queue queue = session.createQueue(QUEUE_NAME);
            MessageProducer producer = session.createProducer(queue);

            for (int i = 0; i < 3; i++) {
                TextMessage textMessage = session.createTextMessage("----msg:" + i);
                producer.send(textMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("消息发布OK!");
        }

    }
}
