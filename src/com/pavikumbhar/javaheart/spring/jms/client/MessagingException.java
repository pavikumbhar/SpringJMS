/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pavikumbhar.javaheart.spring.jms.client;

/**
 *
 * @author pravinkumbhar
 */
public class MessagingException extends Exception { 
 
    private static final long serialVersionUID = 1L; 
 
    private int status; 
 
    public MessagingException(String message) { 
        super(message, null); 
    } 
 
    public MessagingException(String message, Throwable cause) { 
        super(message, cause); 
    } 
 
    /**
     * 
     * @param status the HTTP status code of the error. 
     * @param message 
     */ 
    public MessagingException(int status, String message) { 
        super(message); 
        this.status = status; 
    } 
 
    /**
     * 
     * @return the HTTP status code of the error 
     */ 
    public int getStatus() { 
        return status; 
    } 
}