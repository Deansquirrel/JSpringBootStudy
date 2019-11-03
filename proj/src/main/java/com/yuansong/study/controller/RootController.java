package com.yuansong.study.controller;

import java.text.ParseException;
import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yuansong.tools.common.DateTool;

@RestController
public class RootController {
	
	private void printStr(String str) {
		System.out.println(str);
	}
	
	@GetMapping("/")
	public String root() {
		
		
		Date d = new Date();
		DateTool dTool = new DateTool();
	
		
		try {
			d = dTool.ParseDatetimeWithMillionsecond("2019-11-03 10:26:22.139");
		} catch (ParseException e) {
			e.printStackTrace();
			return "error";
		}
		
		
		printStr(dTool.GetStr(d));
		printStr(dTool.GetDateStr(d));
		printStr(dTool.GetDatetimeStr(d));
		printStr(dTool.GetDatetimeWithMillionsecond(d));
		return "root";
	}
}
