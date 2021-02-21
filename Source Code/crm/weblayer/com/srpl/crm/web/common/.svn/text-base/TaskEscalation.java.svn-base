package com.srpl.crm.web.common;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.srpl.crm.ejb.entity.AssignedTaskORM;
import com.srpl.crm.ejb.entity.AtCategoryORM;
import com.srpl.crm.ejb.entity.OrderORM;
import com.srpl.crm.ejb.entity.SupportCaseORM;
import com.srpl.crm.ejb.entity.UmAlertsAndReminders;
import com.srpl.um.ejb.entity.UmUser;
import com.srpl.crm.ejb.request.ATCategoryDAO;
import com.srpl.crm.ejb.request.ATStatusDAO;
import com.srpl.crm.ejb.request.AlertsAndRemindersDAO;
import com.srpl.crm.ejb.request.AssignedTaskDAO;
import com.srpl.crm.ejb.request.CaseDAO;
import com.srpl.crm.ejb.request.OrderDAO;
import com.srpl.um.ejb.request.UserDAO;

public class TaskEscalation implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("here in taskEscalation execute method");
		AssignedTaskDAO myEjb = null;

		try {
			// Get the context
			InitialContext ctx = new InitialContext();

			// Lookup the bean using its JNDI name
			myEjb = (AssignedTaskDAO) ctx
					.lookup("java:global/srpl/crm_businesslayer/AssignedTaskDAO");
			System.out.println("got the bean");

			// Lookup user dao
			UserDAO userDao = (UserDAO) ctx
					.lookup("java:global/srpl/crm_businesslayer/UserDAO");

			// lookup category dao
			ATCategoryDAO categoryDao = (ATCategoryDAO) ctx
					.lookup("java:global/srpl/crm_businesslayer/ATCategoryDAO");

			// lookup alert dao
			AlertsAndRemindersDAO alertDao = (AlertsAndRemindersDAO) ctx
					.lookup("java:global/srpl/crm_businesslayer/AlertsAndRemindersDAO");

			// lookup case dao
			CaseDAO supportDao = (CaseDAO) ctx
					.lookup("java:global/srpl/crm_businesslayer/CaseDAO");
			
			// lookup status dao
			ATStatusDAO statusDao = (ATStatusDAO) ctx
					.lookup("java:global/srpl/crm_businesslayer/ATStatusDAO");
			
			// lookup orders dao
			OrderDAO ordersDao = (OrderDAO) ctx
					.lookup("java:global/srpl/crm_businesslayer/OrderDAO");

			// Generate ALERTS

			List<AssignedTaskORM> pendingTasks = myEjb.listPendingTasks();
			System.out.println("the number of pending tasks are: "
					+ pendingTasks.size());

			for (AssignedTaskORM x : pendingTasks) {
				// Fetch user so that we can get the id to whom alert will
				x.getAssignedBy();
				try {
					// Populate Alert Data
					UmAlertsAndReminders alert = new UmAlertsAndReminders();
					// update this
					alert.setAlertsRemindersStatus(true);

					Date curJavaDate = new Date();
					Timestamp curDate = new Timestamp(curJavaDate.getTime());

					alert.setDate(curDate);
					alert.setTransmitDate(curDate);
					alert.setTransmitStatus(true);
					alert.setIsAlert(true);

					Long temp = x.getAssignedTo();
					UmUser assignedTo = userDao.umUserDetails(temp);

					UmUser reportsTo = null;
					if (assignedTo.getUserReportsto() != null) {
						reportsTo = userDao.umUserDetails(assignedTo
								.getUserReportsto());
					} else {
						// if reports to is not defined, the alert will generate
						// to himself
						reportsTo = assignedTo;
					}
					// set alert for id
					alert.setUserId(reportsTo.getUserId());

					// understand the task category
					AtCategoryORM tempCategory = x.getCategory();
					switch (tempCategory.getTitle()) {
					case "Support":
						// It was a support task, escalate complaint
						try {
							SupportCaseORM supportDetials = supportDao
									.caseDetails(x.getRefId());

							alert.setTitle(supportDetials.getCustomerName()
									+ " Complaint Pending");
							alertDao.createAlertsAndReminders(alert);
							
							x.setStatus(statusDao.escalatedStatus());
							
							myEjb.update(x);
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
						
					case "Orders":
						// It was a orders task, escalate order
						try {
							OrderORM orderDetails = ordersDao
									.retrieveOrder(x.getRefId());

							alert.setTitle(orderDetails.getOrderTitle()
									+ " Order Pending");
							alertDao.createAlertsAndReminders(alert);
							
							x.setStatus(statusDao.escalatedStatus());
							
							myEjb.update(x);
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
					}

				} catch (Exception e) {
					System.out.println("error fetching the user");
				}

			}

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
