package com.yuansong.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.yuansong.study.worker.LogTestWorker;

@SpringBootApplication
public class ProjApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjApplication.class, args);
		LogTestWorker ls = new LogTestWorker();
		ls.start();
	}

}
