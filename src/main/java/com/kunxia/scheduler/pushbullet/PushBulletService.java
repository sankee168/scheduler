package com.kunxia.scheduler.pushbullet;

import java.util.List;

import net.iharder.jpushbullet2.Push;

public interface PushBulletService {
	List<Push> retrievePushes();
	String sendPush(String body);
}
