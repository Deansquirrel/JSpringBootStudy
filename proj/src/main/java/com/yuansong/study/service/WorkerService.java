package com.yuansong.study.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.gson.Gson;
import com.yuansong.study.dao.RowMapper.BookRowMapper;
import com.yuansong.study.global.Global;
import com.yuansong.study.pojo.Book;
import com.yuansong.tools.common.CommonTool;

@Service
public class WorkerService {
	
	private static String _connStr = "jdbc:mysql://192.168.10.138:3306/chapter05?characherEncodeing=utf8&&serverTimezone=Asia/Shanghai";
	
//	private String insertSql = "insert into book(fname,fauthor) values(?,?)";
//	private String updateSql = "UPDATE book SET fname=?,fauthor=? WHERE fid=?";
//	private String deleteSql = "DELETE FROM book WHERE fid = ?";
//	private String getBookSql = "SELECT fid,fname,fauthor FROM book WHERE fid=?";
	private String getAllSql = "SELECT fid,fname,fauthor FROM book ORDER BY fid";
	
//	@Transactional
	public String Test() {
		StringBuilder sb = new StringBuilder();

		List<Book> list;
		try {
			list = getAllBooks();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return e.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return e.toString();
		}
		Gson gson = new Gson();
		for(Book book : list) {
			if(sb.length()>0) {
				sb.append("\n");
				sb.append("<br />");
			}
			sb.append(gson.toJson(book));
		}
		return sb.toString();
	}
	
	private List<Book> getAllBooks() throws NoSuchAlgorithmException, UnsupportedEncodingException{
		JdbcTemplate jdbcTemplate = getJdbcTemplate();
		List<Book> list =  jdbcTemplate.query(getAllSql, new BookRowMapper());
		return list;
	}	
	
//	private JdbcTemplate getJdbcTemplate2() {
//		DataSource ds = DataSourceBuilder.create()
//				.type(com.alibaba.druid.pool.DruidDataSource.class)
//				.url("jdbc:mysql://192.168.10.138:3306/chapter05?characherEncodeing=utf8&&serverTimezone=Asia/Shanghai")
//				.username("tester")
//				.password("84519741")
//				.build();		
//		JdbcTemplate jdbcTemplate = new JdbcTemplate();
//		jdbcTemplate.setQueryTimeout(30);
//		jdbcTemplate.setDataSource(ds);
//		return jdbcTemplate;
//	}
	
	private JdbcTemplate getJdbcTemplate() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		CommonTool ct = new CommonTool();
		String cKey = ct.Md5Encode("|" + _connStr + "|" + "tester" + "|" + "84519741").toUpperCase();
		if(Global.ConnPool().hasKey(cKey)) {
			return Global.ConnPool().getObject(cKey);
		}
		
		DataSource ds = getDataSource(_connStr,"tester","84519741");
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setQueryTimeout(30);
		jdbcTemplate.setDataSource(ds);
		Global.ConnPool().register(cKey, jdbcTemplate);
		return getJdbcTemplate();
	}
	
	private DataSource getDataSource(String url,String username,String password) {
		DruidDataSource ds = new DruidDataSource();
		//初始大小
		ds.setInitialSize(0);
		//最小大小
		ds.setMinIdle(0);
		//最大大小
		ds.setMaxActive(30);
		//获取连接等待超时的时间
		ds.setMaxWait(10000);
		//间隔多久才进行一次检测（毫秒）
		ds.setTimeBetweenEvictionRunsMillis(60000);
		//一个连接在池中最小生存的时间（毫秒）
		ds.setMinEvictableIdleTimeMillis(300000);
		//	是否缓存preparedStatement
		ds.setPoolPreparedStatements(false);
		ds.setValidationQuery("SELECT 	substring_index(HOST, ':', 1) AS host_name, 	state, 	count(*) FROM 	information_schema.PROCESSLIST GROUP BY 	state, 	host_name;");
		ds.setTestOnBorrow(true);
		ds.setTestOnReturn(false);
		ds.setTestWhileIdle(true);
		
		ds.setUrl(url);
		ds.setUsername(username);
		ds.setPassword(password);

		return ds;
    }

}
