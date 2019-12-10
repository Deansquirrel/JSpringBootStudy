package com.yuansong.study.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class TestService {
	
	private List<String> list;
	
	private Map<String,String> map;
	
	public TestService() {
		this.init();
	}
	
	private void init() {
		this.list = new ArrayList<>();
		this.list.add(",1");
		this.list.add("2");
		
		this.map = new HashMap<>();
		this.map.put("1", "1-value");
		this.map.put("2", "2-value");
		this.map.put("1", "1-newvalue");
	}
	
	public void SubTest() {
		Iterator<String> iterator = this.list.iterator();
		while(iterator.hasNext()) {
			String item = iterator.next();
			if(item == "1") {
				iterator.remove();
			}
		}
	}

	@Override
	public String toString() {
		return this.list.toString();
	}
	
	
}
