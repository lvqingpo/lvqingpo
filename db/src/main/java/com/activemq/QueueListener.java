package com.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * @author: elvin
 */
@Component
public class QueueListener {

    @JmsListener(destination = "publish.queueone", containerFactory = "jmsListenerContainerQueue")
    @SendTo("two")
    public String receive(String text){
        System.out.println("QueueListener: consumer-a 收到一条信息: " + text);
        return "consumer-a received : " + text;
    }
}