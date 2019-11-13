package com.yuansong.study.worker;

import com.yuansong.study.service.LogTestService;

public class LogTestWorker extends Thread{
	
	@Override
	public void run() {
		LogTestService ls = new LogTestService();
		ls.test();
	}

}
