package com.yuansong.study.global;

import org.springframework.jdbc.core.JdbcTemplate;

import com.yuansong.tools.common.ObjectManager;

public class Global {
	
	private static ObjectManager<JdbcTemplate> _connPool = new ObjectManager<JdbcTemplate>();
	
	public static ObjectManager<JdbcTemplate> ConnPool() {
		return _connPool;
	}

}
