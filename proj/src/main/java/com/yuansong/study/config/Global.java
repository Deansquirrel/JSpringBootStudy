package com.yuansong.study.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class Global {
	@ModelAttribute(value="info")
	public Map<String,String> userInfo(){
		HashMap<String,String> map = new HashMap<>();
		map.put("username", "testname");
		map.put("gender","男");
		return map;
	}
}
