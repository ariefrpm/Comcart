package id.ac.bsi.comcart.utils;
import java.util.*;
import java.io.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.mail.util.*;

public class SendMail {
    public static void sendMail(String message, String subject, String to, String from, String host) {

	// create some properties and get the default Session
	Properties props = new Properties();
	props.put("mail.smtp.host", host);	

	Session session = Session.getInstance(props, null);
	
	try {
	    // create a message
	    Message msg = new MimeMessage(session);
	    msg.setFrom(new InternetAddress(from));
	    InternetAddress[] address = {new InternetAddress(to)};
	    msg.setRecipients(Message.RecipientType.TO, address);
	    msg.setSubject(subject);
	    msg.setSentDate(new Date());
	    // If the desired charset is known, you can use
	    // setText(text, charset)
	    //msg.setText(msgText);
	    msg.setDataHandler(new DataHandler(new ByteArrayDataSource(message,"text/html")));	    
	    Transport.send(msg);
	} catch (Exception mex) {
	    System.out.println("\n--Exception handling in msgsendsample.java");
	    mex.printStackTrace();
	}
    }
}
