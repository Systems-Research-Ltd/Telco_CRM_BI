package com.srpl.crm.web.model.user;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.srpl.crm.web.controller.AlertsAndRemindersAdmin;
import com.srpl.crm.web.model.um.alertsandreminders.scanner.AlertScannerService;

import java.util.Date;

public class ServiceJob implements Job {

	public ServiceJob(){
		
	}
	public void execute(JobExecutionContext arg0) throws JobExecutionException {		
		AlertScannerService alertScan = new AlertScannerService();		 
		if(!AlertScannerService.IS_SCANNING)
			alertScan.run();
		else
			System.out.println("Data Scanning in Progress!");
	}

}
