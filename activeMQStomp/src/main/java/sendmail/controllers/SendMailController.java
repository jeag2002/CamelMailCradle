package sendmail.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import sendmail.beans.MessageDTO;
import sendmail.config.Sender;

import org.apache.camel.CamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class SendMailController{
	
	private final Logger logger = LoggerFactory.getLogger(SendMailController.class);
	
    @MessageMapping("/sendmail")
    @SendTo("/topic/entries")
    public MessageDTO guestbook(String message) {
        
    	logger.info("[SendMailController] Received message: (" + message + ") send to activemq:rt_messages");
        //sender.send("activemq:topic:rtmessages", message);
    	try{
    	Sender send = new Sender(message);
    	}catch(Exception e){
    		logger.warn("Error connection ",e);
    	}
        return new MessageDTO(message);
    }
}
