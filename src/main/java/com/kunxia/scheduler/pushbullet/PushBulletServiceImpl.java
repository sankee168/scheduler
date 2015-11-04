package com.kunxia.scheduler.pushbullet;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.kunxia.scheduler.job.CheckInNotificationJob;

import net.iharder.jpushbullet2.Callback;
import net.iharder.jpushbullet2.Push;
import net.iharder.jpushbullet2.PushbulletClient;
import net.iharder.jpushbullet2.PushbulletException;

@Component
public class PushBulletServiceImpl implements PushBulletService{
	
	private static final Logger logger = Logger
			.getLogger(PushBulletServiceImpl.class);

	
	private PushbulletClient client;
	
	@Autowired
	public PushBulletServiceImpl(@Value("${pushbullet.apikey}") String apikey){
		client = new PushbulletClient(apikey);
	}
    
	@Override
	public List<Push> retrievePushes() {
		try {
			return client.getNewPushes();
		} catch (PushbulletException e) {
			logger.error(e.getMessage());
			return null;
		}
		/*
		List<Push> allPushes = new ArrayList<Push>();
        Future<?> fut = client.getPushesAsync(new Callback<List<Push>>() {
            @Override
            public void completed(List<Push> pushes, PushbulletException ex) {
                for( Push push : pushes ){
                   logger.info(push.getBody());
                    allPushes.add(push);
                }
            }
        });
        return allPushes;
        */
	}
	@Override
	public String sendPush(String body) {

        String result=null;
		try {
			result = client.sendNote(null, "SchedulerPush", body);
		} catch (PushbulletException e) {
			logger.error(e.getMessage());
		}
		logger.info("Scheduler has sent a push to all theregistered devices:"+result);
        return result;
	}
}