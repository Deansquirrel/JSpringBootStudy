package com.yuansong.study.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yuansong.study.dao.RowMapper.BookRowMapper;
import com.yuansong.study.pojo.Book;

@Repository
public class BookDao {
	
	private String insertSql = "insert into book(fname,fauthor) values(?,?)";
	private String updateSql = "UPDATE book SET fname=?,fauthor=? WHERE fid=?";
	private String deleteSql = "DELETE FROM book WHERE fid = ?";
	private String getBookSql = "SELECT fid,fname,fauthor FROM book WHERE fid=?";
	private String getAllSql = "SELECT fid,fname,fauthor FROM book ORDER BY fid";
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public int addBook(Book book) {
		return jdbcTemplate.update(insertSql,book.getName(),book.getAuthor());
	}
	
	public int updateBook(Book book) {
		return jdbcTemplate.update(updateSql,book.getName(),book.getAuthor(),book.getId());
	}
	
	public int deleteBookById(Integer id) {
		return jdbcTemplate.update(deleteSql,id);
	}
	
	public Book getBookById(Integer id) {
		return jdbcTemplate.queryForObject(getBookSql, new BookRowMapper(), id);
	}
	
	public List<Book> getAllBooks(){
		return jdbcTemplate.query(getAllSql, new BookRowMapper());
	}
	
}
