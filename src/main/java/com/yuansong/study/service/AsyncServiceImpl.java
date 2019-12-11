package com.yuansong.study.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncServiceImpl implements AsyncService {
	
	private final static Logger logger = LogManager.getLogger(AsyncServiceImpl.class);

	@Async("taskExecutor")
	@Override
	public void ExecuteAsync() {
		logger.info("start executeAsync");
        try {
            System.out.println("当前运行的线程名称：" + Thread.currentThread().getName());
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("end executeAsync");
	}

}
