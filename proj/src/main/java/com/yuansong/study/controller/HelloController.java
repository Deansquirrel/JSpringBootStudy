package com.yuansong.study.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.yuansong.study.pojo.Book;
import com.yuansong.study.pojo.SimpleConfig;
import com.yuansong.tools.common.DateTool;


@RestController
public class HelloController {
	
	@Autowired
	private SimpleConfig _config;
	
	@GetMapping("/hello")
	public String hello() {
		Gson gson = new Gson();
		String strConfig = gson.toJson(_config);
		System.out.println(strConfig);
		
		Book book = new Book();
		book.setAuthor("罗贯中");
		book.setName("三国演义");
		book.setPrice(32f);
		book.setPublicationDate(new Date());
		String strBook = gson.toJson(book);
		System.out.println(strBook);
		
		System.out.println(DateTool.GetDateStr());
			
		return "Hello Sping Boot";
	}
	
	@PostMapping("/upload")
	public String upload(MultipartFile uploadFile,HttpServletRequest req) {
		String realPath = req.getSession().getServletContext().getRealPath("/uploadFile");
		System.out.println(realPath);
		String format = DateTool.GetDateStr();
		format = format.substring(0,10);
		File folder = new File(realPath + format);
		System.out.println(folder.getPath());
		if(!folder.isDirectory()) {
			folder.mkdir();
		}
		String oldName = uploadFile.getOriginalFilename();
		String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."),oldName.length());
		System.out.println(newName);
//		String filePath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/uploadFile" + format + newName;
//		return filePath;
		try {
			uploadFile.transferTo(new File(folder,newName));
			String filePath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/uploadFile" + format + "/" + newName;
			return filePath;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "上传失败";
	}

	@GetMapping("/errortest")
	public String errorTest() {
		System.out.println("runtime error");
		throw new RuntimeException("test error");
	}
}
