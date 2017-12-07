package getmail.handler;

import java.util.HashMap;
import java.util.Map;

import getmail.beans.MessageDTO;
import getmail.mail.SendMailTLS;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

/**
 * Receives messages from ActiveMQ and relays them to appropriate users.
 */
@Component(value = "queueHandler")
public class QueueHandler {
	
	private final Logger logger = LoggerFactory.getLogger(QueueHandler.class);

	@Value("${username.mail}")
	private String user;
	@Value("${password.mail}")
	private String pass;

    private static Map<String, Object> defaultHeaders;

    static {
        defaultHeaders = new HashMap<String, Object>();
        // add the Content-Type: application/json header by default
        defaultHeaders.put(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON);
    }

    public void handle(Exchange exchange) {
        Message camelMessage = exchange.getIn();
        String data = camelMessage.getBody(String.class);
        
        logger.info("[QueueHandler-data] (" + data + ")");
        MessageDTO mDTO = new MessageDTO(data);
        
        SendMailTLS sMTLS = new SendMailTLS();
        sMTLS.sendTOGmailTLS(mDTO,user,pass);
                
    }
}
