package com.srpl.crm.web.common;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.srpl.crm.web.model.um.alertsandreminders.scanner.AlertScannerService;

public class AlertsandRemindersJobScheduler implements Job {
	private AlertScannerService alertScan = new AlertScannerService();
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		if(!AlertScannerService.IS_SCANNING)
			alertScan.run();
		else
			System.out.println("Data Scanning in Progress!");
	}
}
