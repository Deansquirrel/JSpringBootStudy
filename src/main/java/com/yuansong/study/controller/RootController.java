package com.yuansong.study.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yuansong.study.config.SysConfig;
import com.yuansong.study.service.AopTestService;
import com.yuansong.tools.common.DateTool;

@RestController
public class RootController {
	
	private final static Logger logger = LogManager.getLogger(RootController.class);

    private SysConfig sc;
    
    @Autowired
    private AopTestService ats;

    @Autowired
    public void setSc(SysConfig sc) {
        this.sc = sc;
    }

    @GetMapping("")
    public String root(){
        DateTool dt = new DateTool();
        return "Root Test" + " " +
                dt.GetDatetimeWithMillionsecond() +
                " " +
                "TTT" +
                " " +
                sc.getConfigStrOne();
    }

    @GetMapping("hello")
    public String hello(){
        return "Hello Spring boot";
    }
    
    @GetMapping("aopTest")
    public String aopTest() {
    	logger.debug("==================================================");
    	ats.NormalSub();
    	logger.debug("==================================================");
    	logger.debug(ats.NormalSubWithReturn());
    	logger.debug("==================================================");
    	try{
    		ats.ErrSub();
    	}catch(Exception e) {
    		logger.debug(e.getMessage());
    	}
    	logger.debug("==================================================");
    	return "AOP Test";
    }
}
