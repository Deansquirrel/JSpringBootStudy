package com.yuansong.study.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.yuansong.tools.common.DateTool;

@Service
public class LogTestService{
	
	private final static Logger logger = LogManager.getLogger(LogTestService.class);

	public void test() {
		DateTool dt = new DateTool();
		long n = System.currentTimeMillis();
		long c = System.currentTimeMillis();
		do {
			logger.info("log file test " + dt.GetDatetimeWithMillionsecond());
			c = System.currentTimeMillis();
			try {
				Thread.currentThread();
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			}
		}while(c-n<1 * 1000 * 60 * 60);
	}
	
}
