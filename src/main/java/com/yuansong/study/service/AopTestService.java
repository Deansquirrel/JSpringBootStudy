package com.yuansong.study.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.yuansong.tools.common.DateTool;

@Service
public class AopTestService {
	
	private final static Logger logger = LogManager.getLogger(AopTestService.class);
	
	public void NormalSub() {
		logger.info("NormalSubWithNoReturn");
	}
	
	public String NormalSubWithReturn() {
		logger.info("NormalSubWithReturn");
		DateTool dt = new DateTool();
		return dt.GetDatetimeWithMillionsecond();
	}
	
	public void ErrSub() {
		logger.info("ErrSub");
		throw new RuntimeException("ErrSub Test"); 
	}
	
}
