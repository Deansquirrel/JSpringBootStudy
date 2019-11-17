package com.yuansong.study;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.yuansong.study.global.Global;
import com.yuansong.tools.common.CommonTool;
import com.yuansong.tools.cron.Config;
import com.yuansong.tools.cron.Job;

@SpringBootApplication
public class ProjApplication {
	
	private final static Logger logger = LogManager.getLogger(ProjApplication.class);

	public static void main(String[] args) {
		addJob();		
		SpringApplication.run(ProjApplication.class, args);
	}
	
	private static void addJob() {
		CommonTool ct=new CommonTool();
		
		Global.JOB_MANAGER.register(new Config(ct.UUID(), "0/5 * * * * ?", true), getJob("TEST JOB 1"));
		Global.JOB_MANAGER.register(new Config(ct.UUID(), "3/5 * * * * ?", true), getJob("TEST JOB 2"));
		Global.JOB_MANAGER.register(new Config(ct.UUID(), "6/5 * * * * ?", false), getJob("TEST JOB 3"));
	}
	
	private static Job getJob(String msg) {
		return new Job() {

			@Override
			protected void errHandle(Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage());
			}

			@Override
			protected void job() {
				logger.debug(msg);
				try {
					Thread.sleep(8 * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
	}
	

}
