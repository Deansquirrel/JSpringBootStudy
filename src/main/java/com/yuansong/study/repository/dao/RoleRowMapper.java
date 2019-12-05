package com.yuansong.study.repository.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class RoleRowMapper implements RowMapper<Role> {

	@Override
	public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
		Role role = new Role();
		role.setId(rs.getInt("id"));
		role.setName(rs.getString("name"));
		role.setNameZh(rs.getString("nameZh"));
		return role;
	}

}
