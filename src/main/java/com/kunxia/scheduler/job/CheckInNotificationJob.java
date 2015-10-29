package com.kunxia.scheduler.job;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.kunxia.scheduler.App;
import com.kunxia.scheduler.dao.ReservationDAOImpl;
import com.kunxia.scheduler.twillo.TwilloClient;

public class CheckInNotificationJob implements Job{
	private static final Logger logger = Logger
			.getLogger(CheckInNotificationJob.class);
	
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		TwilloClient twilloClient = (TwilloClient) App.appContext.getBean("twilloClient");
		JobDataMap jobDataMap = context.getMergedJobDataMap();

		String guestPhone = jobDataMap.getString("guestPhone");
		String guestName = jobDataMap.getString("guestName");
		
		if(StringUtils.isNotEmpty(guestPhone)){
			String notification = "";
			if(StringUtils.isNotEmpty(guestName)){
				 notification = "Dear "+guestName+", this is an automatic reminder to check-in Chloe's Suite "
						+ "tomorrow at 535 Canton Rd, Suite 6C, Hong Kong SAR."
						+ "If you've any questions, feel free to reach Chloe at +852 9513 4248";
			}else{
				 notification = "Dear guest, this is an automatic reminder to check-in Chloe's Suite "
						+ "tomorrow at 535 Canton Rd, Suite 6C, Hong Kong SAR."
						+ "If you've any questions, feel free to reach Chloe at +852 9513 4248";
			}
			if(StringUtils.isNotEmpty(notification)){
				twilloClient.sendSmsMessage(guestPhone, guestName, notification);
				twilloClient.sendSmsMessage("+852 9513 4248", "Chloe", "Please be noticed your guest "+guestName+" has been notified to checkin tomorrow. Please clean the room and arrange the access card pickup accordingly.");
			}
		}
	}

}
