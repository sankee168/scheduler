package com.kunxia.scheduler;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Application Launcher
 * 
 * @author kunx
 *
 */


public class App {
	
		private static final Logger logger = Logger.getLogger(App.class);
		public static ApplicationContext appContext = new ClassPathXmlApplicationContext("com/kunxia/scheduler/config/beans.xml");
		
	
	public static void main(String[] args) {
	
		QuartzServer quartzServer = appContext.getBean(QuartzServer.class);
		quartzServer.start();
		//ReservationDAOImpl reservationDao = (ReservationDAOImpl) context.getBean("reservationDAO");
	}

}
