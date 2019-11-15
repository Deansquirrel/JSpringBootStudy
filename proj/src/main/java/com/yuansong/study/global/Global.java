package com.yuansong.study.global;

import org.springframework.jdbc.core.JdbcTemplate;

import com.yuansong.tools.common.ObjectManager;
import com.yuansong.tools.cron.JobManager;

public class Global {
	
	public static ObjectManager<JdbcTemplate> CONN_POOL = new ObjectManager<JdbcTemplate>();
	
	public static JobManager JOB_MANAGER = new JobManager();

}
