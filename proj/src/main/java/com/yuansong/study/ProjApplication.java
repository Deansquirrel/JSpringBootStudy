package com.yuansong.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.yuansong.study.global.Global;
import com.yuansong.study.worker.MyTestJob;
import com.yuansong.tools.common.CommonTool;
import com.yuansong.tools.cron.Config;

@SpringBootApplication
public class ProjApplication {

	public static void main(String[] args) {
		CommonTool ct=new CommonTool();
		Global.JOB_MANAGER.register(new Config(ct.UUID(), "0/5 * * * * ?", true), new MyTestJob());
		
		SpringApplication.run(ProjApplication.class, args);
	}

}
