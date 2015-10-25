package com.kunxia.scheduler;

import java.util.Properties;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

public class Test {
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
    
            			//System.out.println(scheduler.getJobDetail(job.getKey()));
            			scheduler.deleteJob(new JobKey("checkoutJob_1", "notifyJobs"));
            			scheduler.deleteJob(new JobKey("checkoutJob_2", "notifyJobs"));
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