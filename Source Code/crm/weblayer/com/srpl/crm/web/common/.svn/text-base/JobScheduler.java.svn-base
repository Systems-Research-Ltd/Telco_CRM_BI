package com.srpl.crm.web.common;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class JobScheduler implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("Application Undeployed!");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("Application Deployed!");
		/*try {
			Scheduler scheduler = new StdSchedulerFactory().getScheduler();
			JobDetail job = JobBuilder.newJob(AlertsandRemindersJobScheduler.class).withIdentity("AlertsandRemindersJob", "AlertsandRemindersGroup").build();
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("AlertsandRemindersTrigger", "AlertsandRemindersGroup").withSchedule(CronScheduleBuilder.cronSchedule("0/20 * * * * ?")).build();
			scheduler.start();
			//scheduler.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

}
