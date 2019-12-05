package com.yuansong.study.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yuansong.study.repository.dao.Role;
import com.yuansong.study.repository.dao.RoleRowMapper;
import com.yuansong.study.repository.dao.User;
import com.yuansong.study.repository.dao.UserRowMapper;

@Repository
public class UserRepositoryImpl implements UserRepository {
	
	private static final String SqlGetUserByUserid = "select id,username,password,enabled,locked from user where id=?";
	private static final String SqlGetUserByUsername = "select id,username,password,enabled,locked from user where username=?";
	private static final String SqlGetUserRoleByUseid = ""
			+ "select id,name,nameZh "
			+ "from role r "
			+ "where EXISTS ( "
			+ "	select * "
			+ "	from user_role ur"
			+ " 	where r.id = ur.rid and uid = 1"
			+ " )";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public User getUserById(Integer id) {
		List<User> list = jdbcTemplate.query(SqlGetUserByUserid, new UserRowMapper(), id);
		if(list.size() == 1) {
			return list.get(0);
		} else if(list.size() > 1) {
			throw new RuntimeException("multiple user by id " + String.valueOf(id));
		}
		return null;
	}

	@Override
	public User getUserByUsername(String username) {
		List<User> list = jdbcTemplate.query(SqlGetUserByUsername, new  UserRowMapper(),username);
		if(list.size() == 1) {
			return list.get(0);
		} else if(list.size() > 1) {
			throw new RuntimeException("multiple user by name " + username);
		}
		return null;
	}

	@Override
	public List<Role> getUserRoleById(Integer id) {
		return jdbcTemplate.query(SqlGetUserRoleByUseid, new RoleRowMapper());
	}

}
