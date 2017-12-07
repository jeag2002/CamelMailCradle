package getmail.components;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CamelRouter extends RouteBuilder{
	
	@Value("${username.mail}")
	private String user;
	@Value("${password.mail}")
	private String pass;

	@Override
	public void configure() throws Exception {
		 from("activemq:topic:rtmessages")
		 .to("bean:queueHandler")
		 .to("smtps:smtp.gmail.com:465?username="+user+"&password="+pass+"&to="+user+"&subject=TestCamel&debugMode=true&mail.smtp.auth=true&mail.smtp.starttls.enable=true");
		 
		 from("activemq:queue:rtmessages")
		 .to("bean:queueHandler");
	}

}
