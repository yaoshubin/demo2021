package com.example.demo.mq;

import org.apache.activemq.broker.BrokerService;

public class EmbedsBroker {
    public static void main(String[] args) throws Exception {
        BrokerService  brokerService = new BrokerService();
        brokerService.setUseJmx(true);
        brokerService.addConnector("tcp://localhost:61616");
        brokerService.start();
    }
}
