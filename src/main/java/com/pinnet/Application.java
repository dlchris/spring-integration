package com.pinnet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;

public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("config/spring-integration.xml");
        MessageChannel inputChannel = context.getBean("inputChannel", MessageChannel.class);
        inputChannel.send(new GenericMessage<String>("show"));
    }
}
