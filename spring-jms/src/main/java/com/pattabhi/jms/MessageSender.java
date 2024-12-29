package com.pattabhi.jms;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {
    @Autowired
    JmsTemplate jmsTemplate;
    @Value("${jms.queue.name}")
    private String jmsQueue;

    public void convertAndSend(String message) {
        jmsTemplate.convertAndSend(jmsQueue, message);
    }

    public void sendMessage(String message){
        /*MessageCreator messageCreator = new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        }*/
        jmsTemplate.send(jmsQueue, session -> session.createTextMessage(message));
    }
}
