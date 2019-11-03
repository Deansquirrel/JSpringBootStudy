package com.yuansong.study.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ControllerAdvice
public class ErrorController {
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public void uploadException(MaxUploadSizeExceededException e,HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html);charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.write("上传文件大小超出限制");
		out.flush();
		out.close();
	}
	
	@ExceptionHandler(RuntimeException.class)
	public void exception(Exception e,HttpServletResponse resp) throws IOException {
		System.out.println("get error");
		e.printStackTrace();
//		Gson gson = new Gson();
//		return gson.toJson(e);
		resp.setContentType("text/html);charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.write(e.toString());
		out.flush();
		out.close();
	}
}
