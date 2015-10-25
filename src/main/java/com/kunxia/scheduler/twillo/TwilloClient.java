package com.kunxia.scheduler.twillo;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;

@Component
public class TwilloClient {

	@Value("${twillo.accountsid}")
	private String accountSID;
	
	@Value("${twillo.authtoken}")
	private String authToken;
	
	private static final Logger logger = Logger.getLogger(TwilloClient.class);

	public Message sendSmsMessage(String to, String guestName, String notification){
		TwilioRestClient client = new TwilioRestClient(accountSID, authToken); 
		 
		 // Build the parameters 
		 List<NameValuePair> params = new ArrayList<NameValuePair>(); 
		 params.add(new BasicNameValuePair("To", to)); 
		 params.add(new BasicNameValuePair("From", "Chloe Jiang")); 
		 params.add(new BasicNameValuePair("Body", notification));   
	 
		try {
			MessageFactory messageFactory = client.getAccount().getMessageFactory(); 
			Message message = messageFactory.create(params);
			logger.info("A message has been sent to "+to+" content "+notification+". Twillo Ref:"+message.getSid());
			return message;
		} catch (TwilioRestException e) {
			logger.error(e.getMessage());
			return null;
		} 
		  
	}
}
