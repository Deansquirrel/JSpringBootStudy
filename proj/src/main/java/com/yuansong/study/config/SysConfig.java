package com.yuansong.study.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "sys-config")
@Component
public class SysConfig {
	
	private String rootLogLevel;
	
	
	private String configStrOne;
	private String configStrTwo;
	private Integer configIntOne;
	public String getConfigStrOne() {
		return configStrOne;
	}
	public void setConfigStrOne(String configStrOne) {
		this.configStrOne = configStrOne;
	}
	public String getConfigStrTwo() {
		return configStrTwo;
	}
	public void setConfigStrTwo(String configStrTwo) {
		this.configStrTwo = configStrTwo;
	}
	public Integer getConfigIntOne() {
		return configIntOne;
	}
	public void setConfigIntOne(Integer configIntOne) {
		this.configIntOne = configIntOne;
	}
	public String getRootLogLevel() {
		return rootLogLevel;
	}
	public void setRootLogLevel(String rootLogLevel) {
		rootLogLevel = rootLogLevel.toLowerCase().trim();
		if(rootLogLevel!="debug"&&rootLogLevel!="info"&&rootLogLevel!="warn"&&rootLogLevel!="error") {
			rootLogLevel = "warn";
		}
		this.rootLogLevel = rootLogLevel;
	}
	
}
