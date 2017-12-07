package sendmail.config;

import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import javax.jms.Connection;
import javax.jms.Session;

public class Sender {

  private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);
  
  public Sender(String data) throws Exception{
	  ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
	  Connection conn = connectionFactory.createConnection();
	  conn.setClientID("IDCLIENT1");
	  Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
	  Topic topic = session.createTopic("rtmessages");
	  MessageProducer messageProducer = session.createProducer(topic);
      TextMessage textMessage = session.createTextMessage(data);
      messageProducer.send(textMessage);
      conn.close();
  }
   
}
