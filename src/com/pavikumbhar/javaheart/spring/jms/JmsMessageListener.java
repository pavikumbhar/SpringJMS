/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pavikumbhar.javaheart.spring.jms;

import java.util.concurrent.atomic.AtomicInteger;


import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

/**
 *
 * @author pravinkumbhar
 */
@Component
public class JmsMessageListener implements MessageListener { 

    private static final Logger logger = LoggerFactory.getLogger(JmsMessageListener.class);

    @Autowired
    private AtomicInteger counter = null;

    /**
     * Implementation of <code>MessageListener</code>.
     * @param message
     */
    @Override
    public void onMessage(Message message) {
        try {   
        	
        	System.err.println(" JmsMessageListener for Queue->destinationOne ");
            int messageCount = message.getIntProperty(JmsMessageProducer.MESSAGE_COUNT);
            if (message instanceof TextMessage) {
                TextMessage tm = (TextMessage)message;
                String msg = tm.getText();
                logger.info("Processed message '{}'.  value={}", msg, messageCount);
                 System.err.println("$$$$$$$$$$$$$$Processed message '{}'.  value={}"+ msg+" "+ messageCount);
                counter.incrementAndGet();
            }
        } catch (JMSException e) {
            logger.error(e.getMessage(), e);
        }
    }
    
}
