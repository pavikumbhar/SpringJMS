package com.pavikumbhar.javaheart.spring.jms.client;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.pavikumbhar.javaheart.spring.jms.Msg;
import java.util.Properties;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 *
 * @author pravinkumbhar
 */
@Component
public class JMSClient {

    protected final static Logger log = Logger.getLogger(JMSClient.class);

    public void publishWeblogicMessage(String queueName, Msg msg) {

        try {

            String url;
            url = "t3://localhost:7001";
            Properties h = new Properties();
            h.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
            h.put(Context.PROVIDER_URL, url);
            Context ic = new InitialContext(h);;

            QueueConnectionFactory connectionFactory = (QueueConnectionFactory) ic.lookup("jms/ConnectionFactory");
             // ConnectionFactory connectionFactory = (ConnectionFactory) ic.lookup("jms/ConnectionFactory");
            
            Queue queue = (Queue) ic.lookup(queueName);
             System.err.println("#######queue: "+queue);
            javax.jms.Connection connection = connectionFactory.createConnection();
            javax.jms.Session session = connection.createSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(queue);

            ObjectMessage message = session.createObjectMessage();
             System.out.println("#######com.pavikumbhar.javaheart.spring.jms.client.JMSClient.publishWeblogicMessage()");
            
             System.out.println("#######queueName: "+queueName);

            message.setObject(msg);
            messageProducer.send(message);
            messageProducer.close();
            connection.close();

        } catch (JMSException ex) {
            ex.printStackTrace(System.out);
        } catch (NamingException ex) {

        } catch (Exception exp) {
            log.error(exp.getMessage(), exp);

        }

    }

//    public static InitialContext getInitialContext() throws javax.naming.NamingException {
//        String url;
//        url = "t3://localhost:7001";
//        Properties h = new Properties();
//        h.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
//        h.put(Context.PROVIDER_URL, url);
//        return (new InitialContext());
//    }
    public void  publishGlassFishMessage(String queueName, Msg msg) {

        try {

            Context ic = new InitialContext();

            ConnectionFactory connectionFactory = (ConnectionFactory) ic.lookup("jms/ConnectionFactory");
            Queue queue = (Queue) ic.lookup(queueName);
            System.err.println("#######queue: "+queue);
            javax.jms.Connection connection = connectionFactory.createConnection();
            javax.jms.Session session = connection.createSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(queue);

            ObjectMessage message = session.createObjectMessage();
            System.out.println("#######com.pavikumbhar.javaheart.spring.jms.client.JMSClient.publishGlassFishMessage()");
            
             System.out.println("#######queueName: "+queueName);
            message.setObject(msg);
            messageProducer.send(message);
            messageProducer.close();
            connection.close();

        } catch (JMSException ex) {
            ex.printStackTrace(System.out);
        } catch (NamingException ex) {

        }

    }

}
