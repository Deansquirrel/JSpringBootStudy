package com.yuansong.study.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.google.gson.Gson;

@ControllerAdvice
public class ErrorController {
	
	private final static Logger logger = LogManager.getLogger(ErrorController.class);
	
	@ExceptionHandler(Exception.class)
	public void runtimeExceptionHandler(Exception e,HttpServletResponse resp) throws IOException {		
		ErrInfo errInfo = new ErrInfo();
		errInfo.code = 0;
		errInfo.message = this.getStackTrack(e);
		
		Gson gson = new Gson();
		String errMsg = gson.toJson(errInfo);
		
		logger.error(errMsg);
		
		resp.setContentType("application/json;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.write(errMsg);
		out.flush();
		out.close();
	}
	
	@ExceptionHandler(RuntimeException.class)
	public void runtimeExceptionHandler(RuntimeException e,HttpServletResponse resp) throws IOException {
		ErrInfo errInfo = new ErrInfo();
		errInfo.code = 500;
		errInfo.message = this.getStackTrack(e);
		
		Gson gson = new Gson();
		String errMsg = gson.toJson(errInfo);
		
		logger.error(errMsg);
		
		resp.setContentType("application/json;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.write(errMsg);
		out.flush();
		out.close();
	}
	
	private String getStackTrack(Throwable t) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		try {
			t.printStackTrace(pw);
			return sw.toString();
		}finally {
			pw.close();			
		}
	}
	
}


class ErrInfo {
	int code;
	String message;
}