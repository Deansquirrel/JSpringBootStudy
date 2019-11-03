package com.yuansong.study.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yuansong.study.pojo.Book;
import com.yuansong.study.service.UserService;
import com.yuansong.tools.common.MathTool;


@RestController
public class HelloController {
	
//	@Autowired
//	private SimpleConfig _config;
	
	@Autowired
	UserService _userService;
	
	@GetMapping("/hello")
	public String hello() {
		MathTool mt = new MathTool();	
		System.out.println(_userService.GetUserById(mt.RandInt(10, 100)));
		_userService.DeleteUserById(mt.RandInt(10, 100));
		return "Hello Sping Boot";
	}
	
	@GetMapping("/book")
	public Book book() {	
		Book book = new Book();
		book.setAuthor("罗贯中");
		book.setName("三国演义");
		book.setPrice(32f);
		book.setPublicationDate(new Date());
		
		return book;
	}
	
	@PostMapping("/upload")
	public String upload(MultipartFile uploadFile,HttpServletRequest req) {
		String realPath = req.getSession().getServletContext().getRealPath("/uploadFile");
		System.out.println(realPath);
//		String format = DateTool.GetDateStr();
//		format = format.substring(0,10);
//		File folder = new File(realPath + format);
//		System.out.println(folder.getPath());
//		if(!folder.isDirectory()) {
//			folder.mkdir();
//		}
//		String oldName = uploadFile.getOriginalFilename();
//		String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."),oldName.length());
//		System.out.println(newName);
////		String filePath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/uploadFile" + format + newName;
////		return filePath;
//		try {
//			uploadFile.transferTo(new File(folder,newName));
//			String filePath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/uploadFile" + format + "/" + newName;
//			return filePath;
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		return "上传失败";
	}

	@GetMapping("/errortest")
	public String errorTest() {
		System.out.println("runtime error");
		throw new RuntimeException("test error");
	}
}
