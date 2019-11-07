package com.yuansong.study.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.yuansong.study.dao.BookDao;
import com.yuansong.study.pojo.Book;
import com.yuansong.tools.common.DateTool;
import com.yuansong.tools.common.MathTool;

@Service
public class UserService {
	
	@Autowired
	private BookDao _bookDao;
	
	public String GetUserById(Integer id) {
		return "user" + String.valueOf(id);
	}
	
	public void DeleteUserById(Integer id) {
		System.out.println("delete" + " " + String.valueOf(id));
	}
	
	@Transactional
	public String BookDaoTest() {
		System.out.println("===============================");
		printList();
		System.out.println("===============================");
		
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
		
		System.out.println("===============================");
		printList();
		System.out.println("===============================");
		
		book.setAuthor("Author" + bookNum);
		book.setName("Name" + bookNum);
		r = _bookDao.addBook(book);
		strReturn = strReturn +  String.valueOf(r) + "|";
		
		System.out.println("===============================");
		printList();
		System.out.println("===============================");
		
		
		book.setId(1);
		book.setName("三国演义");
		book.setAuthor("罗贯中" + dt.GetDatetimeWithMillionsecond());
		r = _bookDao.updateBook(book);
		strReturn = strReturn +  String.valueOf(r) + "|";
		
		System.out.println("===============================");
		printList();
		System.out.println("===============================");
		throw new RuntimeException("test runtime exception");
//		
//		return strReturn;
	}
	
	private void printList() {
		List<Book> list = _bookDao.getAllBooks();
		Gson gson = new Gson();
		StringBuilder sb = new StringBuilder();
		for(Book book : list) {
			if(sb.length()>0) {
				sb.append("\n");
			}
			sb.append(gson.toJson(book));
		}
		System.out.println(sb.toString());
	}
}
