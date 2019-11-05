package com.yuansong.study.dao.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.yuansong.study.pojo.Book;

public class BookRowMapper implements RowMapper<Book> {

	@Override
	public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
		Book book = new Book();
		book.setId(rs.getInt("fid"));
		book.setName(rs.getString("fname"));
		book.setAuthor(rs.getString("fauthor"));
		return book;
	}

}
