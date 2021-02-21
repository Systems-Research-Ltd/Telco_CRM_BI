package com.srpl.crm.web.controller;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.srpl.crm.web.common.CampaignSettingsJob;
import com.srpl.crm.web.common.TaskEscalation;
import com.srpl.crm.web.model.user.ServiceJob;

@ManagedBean(eager = true)
@ApplicationScoped
public class QuartzInitializationBean {

	public QuartzInitializationBean(){
		try{
		// Get the context 
		//InitialContext ctx = new InitialContext();

		// Lookup the bean using its JNDI name 

    	//trying to play with quartz
    	try{
    		System.out.println("Quartz step 1");
    		SchedulerFactory sf = new StdSchedulerFactory();
    		Scheduler sched = sf.getScheduler();
    		sched.start();
    		
    		Trigger trigger = TriggerBuilder
    				.newTrigger()
    				.withIdentity("triggerName", "group1")
    				.withSchedule(
    						CronScheduleBuilder.dailyAtHourAndMinute(5, 30)
    						) // execute job daily at 5:30AM
    				.build();
    		
    		//Define Escalation Job Instance
    		JobDetail escalation = JobBuilder
    				.newJob(TaskEscalation.class)
    				.withIdentity("escalation", "group1")
    				.build();
    		
    		// Schedule the job
    		sched.scheduleJob(escalation, trigger);
    		
    		
    	}catch (Exception e) {
			System.out.println("exception in quarts schedual");
		}
		} catch (Exception e) {
			System.out.println("Quartz Initialization Failed.");
			e.printStackTrace();
		}

		try {
			this.schedularTest();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void schedularTest() throws SchedulerException { // to test Schedular

		System.out.println("Inside Schedular Test..*");	
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();

		JobDetail job = newJob(ServiceJob.class).withIdentity("job1", "group2").build();
		Trigger trigger = newTrigger().withIdentity("trigger1", "group2").startNow().withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(00,00)).build();		
		//.withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
		//.withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(12,00))
		//.withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(00,00))
		JobDetail campaignSettings = JobBuilder
				.newJob(CampaignSettingsJob.class)
				.withIdentity("campaignSettings", "group1")
				.build();
		
		Trigger triggerCampaign = TriggerBuilder
				.newTrigger()
				.withIdentity("triggerCampaign", "group1")
				.withSchedule(
						CronScheduleBuilder.dailyAtHourAndMinute(17, 30))
				.build();


		sched.scheduleJob(job, trigger);
		sched.scheduleJob(campaignSettings, triggerCampaign);
		sched.start();

		System.out.println(" Schedular Test..");

	}
}
