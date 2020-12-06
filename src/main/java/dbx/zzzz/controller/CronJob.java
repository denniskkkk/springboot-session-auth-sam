package dbx.zzzz.controller;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CronJob implements Job {

	@Override
	public void execute(JobExecutionContext context) {
	    //log.info("task sec......");
	}
}
