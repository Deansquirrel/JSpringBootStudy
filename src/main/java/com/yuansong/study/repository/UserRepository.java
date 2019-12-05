package com.yuansong.study.repository;

import java.util.List;

import com.yuansong.study.repository.dao.Role;
import com.yuansong.study.repository.dao.User;

public interface UserRepository {
	public User getUserById(Integer id);
	public User getUserByUsername(String username);
	public List<Role> getUserRoleById(Integer id);
}
