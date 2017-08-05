/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pavikumbhar.javaheart.spring.jms;


import javax.annotation.PostConstruct;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
/**
 *
 * @author pravinkumbhar
 */
@Component
public class JmsQueueProducer {

    private static final Logger logger = LoggerFactory.getLogger(JmsMessageProducer.class);

    protected static final String MESSAGE_COUNT = "messageCount";

    @Autowired
    private JmsTemplate jmsTemplateForQueueTwo= null;
    private int messageCount = 10;

    /**
     * Generates JMS messages
     * @throws javax.jms.JMSException
     */
    @PostConstruct
    public void generateMessages()  {
        for (int i = 0; i < messageCount; i++) {
            final int index = i;
            final String text = "[Pravin Kumbhar] " + i + ".";
            
            final Msg msg=new Msg();
            msg.setMessage(text);

            jmsTemplateForQueueTwo.send(new MessageCreator() {
                public Message createMessage(Session session) throws JMSException {
                     ObjectMessage message = session.createObjectMessage(msg);
                    message.setIntProperty(MESSAGE_COUNT, index);
                    
                    logger.info("Sending message: " + text);
                    System.out.println(".############createMessage()"+text);
                    return message;
                }
            });
        }
    }

}
