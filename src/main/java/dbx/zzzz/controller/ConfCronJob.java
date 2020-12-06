package dbx.zzzz.controller;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

//@Slf4j
//@Configuration
public class ConfCronJob {
	/*
	@Bean (name="sec")
	public JobDetail jobDetails() {
		return JobBuilder.newJob(CronJob.class).withIdentity("cronjob").storeDurably().build();
	}
	@Bean
	public Trigger jobATrigger(@Qualifier("sec") JobDetail jd) {
		return TriggerBuilder.newTrigger().forJob(jd).withIdentity("cronjob")
				.withSchedule(CronScheduleBuilder.cronSchedule("/5 * * ? * * *")).build();
	}
*/
}
