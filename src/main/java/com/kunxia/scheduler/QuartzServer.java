package com.kunxia.scheduler;

import org.apache.log4j.Logger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * QuartzServer
 * @author kunx
 *
 */
@Component
public class QuartzServer {
	
	@Value("${scheduler.version}")
	private String version;
	
	private static final Logger logger = Logger.getLogger(QuartzServer.class);

	public void start(){
		 try {
		     logger.info("Starting Scheduler "+version+"...");
             Scheduler scheduler = new StdSchedulerFactory("quartz.properties").getScheduler();
             scheduler.start();
        } catch (SchedulerException e) {
             logger.error(e.getMessage());
        }
	}
}
