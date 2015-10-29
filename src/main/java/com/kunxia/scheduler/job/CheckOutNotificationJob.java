package com.kunxia.scheduler.job;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.kunxia.scheduler.App;
import com.kunxia.scheduler.twillo.TwilloClient;

public class CheckOutNotificationJob implements Job {
	private static final Logger logger = Logger
			.getLogger(CheckOutNotificationJob.class);
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		TwilloClient twilloClient = (TwilloClient) App.appContext.getBean("twilloClient");
		JobDataMap jobDataMap = context.getMergedJobDataMap();
		String guestPhone = jobDataMap.getString("guestPhone");
		String guestName = jobDataMap.getString("guestName");
		
		if(StringUtils.isNotEmpty(guestPhone)){
			String notification= "";
			if(StringUtils.isNotEmpty(guestName)){
				 notification = "Dear "+guestName+", this is an automatic reminder for check-out. "
						+ "Please leave the access card on the desk and close the door."
						+ "Thanks for your stay with us and enjoy the rest of your trip. Don't forget to leave your review on Airbnb :)";
				
			}else{
				 notification = "Dear guest, this is an automatic reminder for check-out. "
						+ "Please leave the access card on the desk and close the door."
						+ "Thanks for your stay with us and enjoy the rest of your trip. Don't forget to leave your review on Airbnb :)";
				
			}
			if(StringUtils.isNotEmpty(notification)){
				twilloClient.sendSmsMessage(guestPhone, guestName, notification);
				twilloClient.sendSmsMessage("+852 9513 4248", "Chloe", "Please be noticed your guest "+guestName+" has been notified to checkout today and leave the access card on the desk.");
			}
		}
		
	}

}
