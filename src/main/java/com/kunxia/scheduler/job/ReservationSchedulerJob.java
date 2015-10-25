package com.kunxia.scheduler.job;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.SimpleScheduleBuilder.*;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.kunxia.scheduler.App;
import com.kunxia.scheduler.dao.ReservationDAOImpl;
import com.kunxia.scheduler.model.Reservation;

@Component
public class ReservationSchedulerJob extends QuartzJobBean {
	
	
		
	private static final Logger logger = Logger.getLogger(ReservationSchedulerJob.class);
	
	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		
		ReservationDAOImpl reservationDAO = (ReservationDAOImpl) App.appContext.getBean("reservationDAO");
		List<Reservation> reservations = reservationDAO.findAllReservations();
		try {
			Scheduler scheduler = new StdSchedulerFactory("com/kunxia/scheduler/config/quartz.properties").getScheduler();
			for(Reservation r:reservations){
				if(StringUtils.isEmpty(r.getQuartzRef())){
					logger.info("Registering Reservation: "+r);
					// For Check-In
					
					JobDetail notifyJob1 = newJob(CheckInNotificationJob.class)
							.withIdentity("checkinJob_"+r.getId(), "notifyJobs")
							.withDescription("checkin notification for "+r.getGuestName())
							.usingJobData("guestName", r.getGuestName())
							.usingJobData("guestPhone",r.getGuestPhone())
							.usingJobData("guestEmail",r.getGuestEmail())
							.build();
					
					
					String checkinDate = r.getCheckin();
					//String input = "2015-10-25T16:30:00";
					DateTimeZone timeZone = DateTimeZone.forID( "Asia/Hong_Kong" );
					DateTime checkInDateTime = new DateTime( checkinDate, timeZone );
					Trigger notifyTrigger1 = (SimpleTrigger) newTrigger() 
						    .withIdentity("checkinTrigger_"+r.getId(), "notifyTriggers")
						    .startAt(checkInDateTime.toDate())  
						    .withSchedule(
						            simpleSchedule().withMisfireHandlingInstructionIgnoreMisfires()
						            )
						    .forJob("checkinJob_"+r.getId(), "notifyJobs") 
						    .build();
					scheduler.scheduleJob(notifyJob1, notifyTrigger1);
					logger.info("Successfully registered a "+scheduler.getJobDetail(notifyJob1.getKey()).getDescription()+" with check-in date"+checkInDateTime.toDate());
					
					//For Check-out.
					String checkoutDate = r.getCheckout();

					JobDetail notifyJob2 = newJob(CheckOutNotificationJob.class)
							.withIdentity("checkoutJob_"+r.getId(), "notifyJobs")
							.withDescription("checkout notification for "+r.getGuestName())
							.usingJobData("guestName", r.getGuestName())
							.usingJobData("guestPhone",r.getGuestPhone())
							.usingJobData("guestEmail",r.getGuestEmail())
							.build();
					
					DateTime checkOutDateTime = new DateTime( checkoutDate, timeZone );
					Trigger notifyTrigger2 = (SimpleTrigger) newTrigger() 
						    .withIdentity("checkoutTrigger_"+r.getId(), "notifyTriggers")
						    .startAt(checkOutDateTime.toDate())  
						    .withSchedule(
						            simpleSchedule().withMisfireHandlingInstructionIgnoreMisfires()
						            )
						    .forJob("checkoutJob_"+r.getId(), "notifyJobs") 
						    .build();
					scheduler.scheduleJob(notifyJob2, notifyTrigger2);
					logger.info("Successfully registered a "+scheduler.getJobDetail(notifyJob2.getKey()).getDescription()+" with check-out date"+checkOutDateTime.toDate());
				
					//Update reservation after Quartz registrations.
					if(scheduler.getJobDetail(notifyJob1.getKey())!=null && scheduler.getJobDetail(notifyJob2.getKey())!=null){
						reservationDAO.updateReservation(r.getId(), "quartz"+r.getId());
					}
				}
			}
		} catch (SchedulerException e) {
			logger.error(e.getMessage());
		}
		
	}

}
