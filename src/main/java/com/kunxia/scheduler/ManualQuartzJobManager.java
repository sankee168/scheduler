package com.kunxia.scheduler;

import java.util.Properties;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.CronScheduleBuilder.cronSchedule;

import com.kunxia.scheduler.job.CheckPushesJob;

public class ManualQuartzJobManager {
	    public static void main(String[] args) {
		        try {
     
            //Quartz Server Properties
            			Properties prop = new Properties();
            			prop.put("org.quartz.scheduler.rmi.proxy", "true");
            			prop.put("org.quartz.scheduler.rmi.registryHost", "localhost");
            			prop.put("org.quartz.scheduler.rmi.registryPort", "1099");
            			prop.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
            			prop.put("org.quartz.threadPool.threadCount", "5");

            			Scheduler scheduler = new StdSchedulerFactory(prop).getScheduler();
            			//System.out.println(scheduler.getTriggersOfJob(new JobKey("checkinJob_1", "notifyJobs")).get(0).getStartTime());
    
            			JobDetail job = newJob(CheckPushesJob.class)
            				    .withIdentity("checkPushesJob", "adminJobs")
            				    .build();

            				CronTrigger trigger = newTrigger()
            				    .withIdentity("checkPushesTrigger", "adminTriggers")
            				    .withSchedule(cronSchedule("0/10 * * * * ?"))
            				    .build();
            			scheduler.scheduleJob(job,trigger);
            			//System.out.println(scheduler.getJobDetail(job.getKey()));
            			//scheduler.deleteJob(new JobKey("checkoutJob_1", "notifyJobs"));
            			//scheduler.deleteJob(new JobKey("checkoutJob_2", "notifyJobs"));
            			//scheduler.deleteJob(new JobKey("checkoutJob_10", "notifyJobs"));
            			//scheduler.deleteJob(new JobKey("checkoutJob_4", "notifyJobs"));
            			//scheduler.deleteJob(new JobKey("checkoutJob_5", "notifyJobs"));
            			//scheduler.deleteJob(new JobKey("checkoutJob_6", "notifyJobs"));
            			//scheduler.deleteJob(new JobKey("checkinJob_10", "notifyJobs"));
            			//scheduler.deleteJob(new JobKey("checkinJob_9", "notifyJobs"));
            			//scheduler.deleteJob(new JobKey("myJob", "group1"));
        		} 
        catch (SchedulerException e) {
            e.printStackTrace();
        		}
    	}
}