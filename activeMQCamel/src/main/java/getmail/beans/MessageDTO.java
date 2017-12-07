package getmail.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MessageDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

    public Date date;
    public String to;
    public String subject;
    public String content;
    

    public MessageDTO(String message) {
        this.date = Calendar.getInstance().getTime();
        String data[] = message.split(":");
        
        this.to = data[0].trim();
        this.subject = data[1].trim();
        this.content = data[2].trim();
    }
    
    public String toString(){
    	SimpleDateFormat sDF = new SimpleDateFormat("ddMMyyyy HH:mm:ss");
    	return sDF.format(date).concat(":").concat(to).concat(":").concat(subject).concat(":").concat(content);
    }
}