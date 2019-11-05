package com.yuansong.study.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.yuansong.study.dao.BookDao;
import com.yuansong.study.pojo.Book;
import com.yuansong.study.service.UserService;
import com.yuansong.tools.common.DateTool;
import com.yuansong.tools.common.MathTool;


@RestController
public class HelloController {
	
//	@Autowired
//	private SimpleConfig _config;
	
	@Autowired
	UserService _userService;
	
	@Autowired
	private BookDao _bookDao;
	
	@GetMapping("/hello")
	public String hello() {
		MathTool mt = new MathTool();	
		System.out.println(_userService.GetUserById(mt.RandInt(10, 100)));
		_userService.DeleteUserById(mt.RandInt(10, 100));
		return "Hello Sping Boot";
	}
	
	@GetMapping("/book")
	public String book() {	
		String strReturn = "|";
		Gson gson = new Gson();
		MathTool mt = new MathTool();
		DateTool dt = new DateTool();
		String bookNum = String.valueOf(mt.RandInt(100, 1000));
		Book book = _bookDao.getBookById(1);
		strReturn = strReturn +  gson.toJson(book) + "|";
		
		List<Book> list = _bookDao.getAllBooks();
		int delId = 2;
		for(Book _book : list) {
			if(_book.getId() > 2 && _book.getId() > delId) {
				delId = _book.getId();
			}
		}
		int r = _bookDao.deleteBookById(delId);
		strReturn = strReturn +  String.valueOf(r) + "|";
		
		book.setAuthor("Author" + bookNum);
		book.setName("Name" + bookNum);
		r = _bookDao.addBook(book);
		strReturn = strReturn +  String.valueOf(r) + "|";
		
		book.setId(1);
		book.setName("三国演义");
		book.setAuthor("罗贯中" + dt.GetDatetimeWithMillionsecond());
		r = _bookDao.updateBook(book);
		strReturn = strReturn +  String.valueOf(r) + "|";
		
		return strReturn;
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
