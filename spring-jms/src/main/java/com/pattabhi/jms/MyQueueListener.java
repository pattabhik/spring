package com.pattabhi.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MyQueueListener {
    @JmsListener(destination = "${jms.queue.name}")
    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
    }
}
