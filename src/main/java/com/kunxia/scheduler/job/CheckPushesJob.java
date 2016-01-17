package com.kunxia.scheduler.job;

import java.util.List;

import net.iharder.jpushbullet2.Push;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.kunxia.scheduler.ApplicationContextProvider;
import com.kunxia.scheduler.pushbullet.PushBulletServiceImpl;

public class CheckPushesJob implements Job{
	private static final Logger logger = Logger.getLogger(CheckPushesJob.class);

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		PushBulletServiceImpl pushBulletService = (PushBulletServiceImpl) ApplicationContextProvider.getApplicationContext().getBean("pushBulletService");
		List<Push> pushes = pushBulletService.retrievePushes();
		if(pushes!=null){
			//logger.info("Received "+pushes.size()+" pushes");
			for(Push push:pushes){
				String type = push.getType();
				if(type!=null){
					/*if(type.equalsIgnoreCase("link")){
						logger.info("Push Type:"+push.getType()+" Push content:"+push.getUrl());
					}else if(type.equalsIgnoreCase("note")){
						logger.info("Push Type:"+push.getType()+" Push content:"+push.getBody());
					}else{
						logger.info("Ignored unsupported push type:"+push.getType());
					}*/
				}
				
			}
		}
	}
}
