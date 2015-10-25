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
		String guestEmail = jobDataMap.getString("guestEmail");
		String guestPhone = jobDataMap.getString("guestPhone");
		String guestName = jobDataMap.getString("guestName");
		
		if(StringUtils.isNotEmpty(guestPhone)){
			if(StringUtils.isNotEmpty(guestName)){
				String notification = "Dear "+guestName+", this is an automatic reminder to check-in Chloe's flat "
						+ "today at 535 Canton Rd, Suite 6C, Hong Kong SAR."
						+ "If you've any questions, feel free to reach Chloe at +852 9513 4248";
				twilloClient.sendSmsMessage(guestPhone, guestName, notification);
			}else{
				String notification = "Dear guest, this is an automatic reminder to check-in Chloe's flat "
						+ "today at 535 Canton Rd, Suite 6C, Hong Kong SAR."
						+ "If you've any questions, feel free to reach Chloe at +852 9513 4248";
				twilloClient.sendSmsMessage(guestPhone, guestName, notification);
			}
		}
	}

}
