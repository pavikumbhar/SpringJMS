/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pavikumbhar.javaheart.spring.jms;

import com.pavikumbhar.javaheart.spring.jms.client.JMSClient;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 *
 * @author pravinkumbhar
 */
@Component
public class MyCl {

    @Autowired
    private JMSClient jMSClient;
    
    @Autowired
    private Environment environment;

    @PostConstruct
    public void generateMessages() {
        
        System.err.println("#############com.pavikumbhar.javaheart.spring.jms.MyCl.generateMessages()");
         final String textOne = "[Pravin Kumbhar-jms/queueOne]";
            
            Msg msgOne=new Msg();
            msgOne.setMessage(textOne);
            
            String queueNameOne="jms/queueOne";
            
           final String texTwo = "[Pravin Kumbhar-jms/queueTwo]";
            Msg msgTwo=new Msg();
            msgTwo.setMessage(texTwo);
            
            String queueNameTwo="jms/queueTwo";
        
      String[] activeProfiles = environment.getActiveProfiles();
        String profile=activeProfiles[0];
        
        System.out.println("profile: "+profile);
        
        if(profile.equalsIgnoreCase("production")){
        
         jMSClient.publishWeblogicMessage(queueNameOne, msgOne);
        
        jMSClient.publishWeblogicMessage(queueNameTwo, msgTwo);
       
        
        }else if(profile.equalsIgnoreCase("development")){
           jMSClient.publishGlassFishMessage(queueNameOne, msgOne);
           jMSClient.publishGlassFishMessage(queueNameTwo, msgTwo);
        
        }

    }

}
