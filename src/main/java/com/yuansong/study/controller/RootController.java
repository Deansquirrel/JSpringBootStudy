package com.yuansong.study.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yuansong.study.config.SysConfig;
import com.yuansong.study.service.AopTestService;
import com.yuansong.study.service.TestService;
import com.yuansong.tools.common.DateTool;

@RestController
public class RootController {
	
	private final static Logger logger = LogManager.getLogger(RootController.class);

	@Autowired
    private SysConfig sc;
    
    @Autowired
    private AopTestService ats;
    
    @Autowired
    private TestService ts;

    @GetMapping("")
    public String root(){
    	logger.debug(ts.toString());
    	ts.SubTest();
    	logger.debug(ts.toString());
    	
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
    	logger.debug("hello log test");
		logger.info("hello log test");
		logger.warn("hello log test");
		logger.error("hello log test");
		logger.info("中文测试");
        return "Hello Spring boot";
    }
    
    @GetMapping("aopTest")
    public String aopTest() {
    	logger.debug("==================================================");
    	ats.NormalSub();
    	logger.debug("==================================================");
    	logger.debug(ats.NormalSubWithReturn());
    	logger.debug("==================================================");
//    	try{
    		ats.ErrSub();
//    	}catch(Exception e) {
//    		logger.debug(e.getMessage());
//    	}
    	logger.debug("==================================================");
    	return "AOP Test";
    }
    
    @GetMapping("mat")
    public String ModelAttributeTest(Model model) {
    	return (String) model.getAttribute("modelAttributeTest");
    }
    
    @GetMapping("/admin/hello")
    public String admin() {
    	return "admin hello";
    }
    
    @GetMapping("/user/hello")
    public String user() {
    	return "user hello";
    }
    
    @GetMapping("/db/hello")
    public String db() {
    	return "db hello";
    }
}
