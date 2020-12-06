package dbx.zzzz.controller;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class CronJobMin  implements Job{

	    @Override
	    public void execute(JobExecutionContext context) {
	        //log.info("task min......");
	    }	 
}
