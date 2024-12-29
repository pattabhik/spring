package com.pattabhi.jms;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringJmsApplicationTests {
    @Autowired
    MessageSender messageSender;

    @Test
    void testSendAndReceive() {
        messageSender.convertAndSend("Hello World");
    }

}
