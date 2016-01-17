package com.kunxia.scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run("classpath:beans.xml", args);
        QuartzServer quartzServer = ctx.getBean(QuartzServer.class);
		quartzServer.start();
    }

}
