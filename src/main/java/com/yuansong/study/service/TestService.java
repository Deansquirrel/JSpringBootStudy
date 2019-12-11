package com.yuansong.study.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class TestService {
	
	private final static Logger logger = LogManager.getLogger(TestService.class);
	
	private List<String> list;
	
	private Map<String,String> map;
	
	public TestService() {
		this.init();
	}
	
	private void init() {
		this.list = new ArrayList<>();
		this.list.add(",1");
		this.list.add("2");
		
		this.map = new HashMap<>();
		this.map.put("1", "1-value");
		this.map.put("2", "2-value");
		this.map.put("1", "1-newvalue");
	}
	
	public void SubTest() {
		// ====================================================
		Iterator<String> iterator = this.list.iterator();
		while(iterator.hasNext()) {
			String item = iterator.next();
			if(item == "1") {
				iterator.remove();
			}
		}
		// ====================================================
		String str = "a,b,c,,";
		String[] ary = str.split(",");
		logger.debug(ary.length);
		// ====================================================
		long count = 1000;
		
		String strOne = "";
		long s1 = System.currentTimeMillis();
		for(int i = 0; i < count; i++) {
			strOne = strOne + "hello";
		}
		logger.debug(System.currentTimeMillis()-s1);
		StringBuilder sb = new StringBuilder();
		sb.append("");
		s1 = System.currentTimeMillis();
		for(int i = 0; i < count; i++) {
			sb.append("hello ");
		}
		logger.debug(System.currentTimeMillis()-s1);
		// ====================================================
//		logger.debug("====================================================");
//		ExecutorService pool = crawlExecutorPool();
//		for (int i=0; i < 200; i++) {
//			final int currT = i;
//			pool.execute(new Runnable() {
//				@Override
//				public void run() {
//					try {
//						Thread.sleep(100);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//					logger.debug(Thread.currentThread().getName() + " " + String.valueOf(currT)  + "======定时任务执行完成======");
//				}
//			});
//			pool.execute(() -> {				
//				try {
//					Thread.sleep(3000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				logger.debug(Thread.currentThread().getName() + " " + String.valueOf(i)  + "======定时任务执行完成======");
//			};
//		}
//		logger.debug("====================================================");
		// ====================================================
	}

	@Override
	public String toString() {
		return this.list.toString();
	}
	
//	private ExecutorService  crawlExecutorPool() {
//		int curSystemThreads = Runtime.getRuntime().availableProcessors() * 10;
//		logger.debug(curSystemThreads);
//		ExecutorService pool = Executors.newFixedThreadPool(curSystemThreads);
//		
//		return pool;
//	}
	
}
