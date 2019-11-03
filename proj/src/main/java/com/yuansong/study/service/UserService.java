package com.yuansong.study.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	public String GetUserById(Integer id) {
		return "user" + String.valueOf(id);
	}
	
	public void DeleteUserById(Integer id) {
		System.out.println("delete" + " " + String.valueOf(id));
	}
}
