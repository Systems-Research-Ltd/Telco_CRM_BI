package com.srpl.crm.web.common;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.srpl.crm.ejb.entity.MarketingCampaignSettings;
import com.srpl.crm.ejb.entity.MarketingCampaignSettingsCustomers;
import com.srpl.crm.ejb.request.CampaignSettingsCustomersDAO;
import com.srpl.crm.ejb.request.CampaignSettingsDAO;
import com.srpl.um.ejb.entity.MailTemplateORM;
import com.srpl.um.ejb.request.MailTemplateDAO;

public class CampaignSettingsJob implements Job {

	CampaignSettingsDAO campaignSettingsDao;
	CampaignSettingsCustomersDAO campaignSettingsCustomersDao;
	MailTemplateDAO templateDao;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub

		try {

			// Get the context
			System.out.println("campaign settings job is executing");

			com.srpl.crm.common.utils.Utils u = new com.srpl.crm.common.utils.Utils();

			InitialContext ctx = new InitialContext();
			// Lookup the bean using its JNDI name
			campaignSettingsDao = (CampaignSettingsDAO) ctx
					.lookup("java:global/srpl/crm_businesslayer/CampaignSettingsDAO");
			campaignSettingsCustomersDao = (CampaignSettingsCustomersDAO) ctx
					.lookup("java:global/srpl/crm_businesslayer/CampaignSettingsCustomersDAO");
			templateDao = (MailTemplateDAO) ctx
					.lookup("java:global/srpl/um_businesslayer/MailTemplateDAO");

			try {
				Date date = new Date();
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				String dateYear = Integer.toString(cal.get(Calendar.YEAR));
				String dateMonth = Integer
						.toString(cal.get(Calendar.MONTH) + 1);
				String dateDay = Integer.toString(cal
						.get(Calendar.DAY_OF_MONTH));
				int currentDate = Integer.parseInt(dateYear + dateMonth
						+ dateDay);
				List<MarketingCampaignSettings> campaignSettingsList = campaignSettingsDao
						.listCampaignSettings();
				for (MarketingCampaignSettings campaignSettings : campaignSettingsList) {
					cal.setTime(campaignSettings.getCampaignSettingsDate());

					String year = Integer.toString(cal.get(Calendar.YEAR));
					String month = Integer
							.toString(cal.get(Calendar.MONTH) + 1);
					String day = Integer.toString(cal
							.get(Calendar.DAY_OF_MONTH));

					int settingDate = Integer.parseInt(year + month + day);

					List<MarketingCampaignSettingsCustomers> campaignSettingsCustomers = campaignSettingsCustomersDao
							.listCampaignSettingsCustomers(campaignSettings);
					String body = "";
					try {
						MailTemplateORM template = templateDao
								.details(campaignSettings.getMailTemplate());
						body = templateDao.getMessageWithMailTemplate(template,
								campaignSettings.getCampaign());
					} catch (Exception e) {
						e.printStackTrace();
						body = campaignSettings.getCampaignSettingsMessage();
					}
					String subject = "";
					boolean isHtml = false;
					if (currentDate == settingDate) {
						for (MarketingCampaignSettingsCustomers campaignSettingsCustomer : campaignSettingsCustomers) {
							System.out.println("Email = "
									+ campaignSettingsCustomer.getCsContact()
											.getContactEmail());
							String sendTo = campaignSettingsCustomer
									.getCsContact().getContactEmail();
							com.srpl.crm.common.utils.Utils.sendMail(sendTo,
									subject, body, isHtml);
						}
					}
				}
			} catch (Exception e) {
				System.out.println("Exception in looping job");
				e.printStackTrace();
			}

			// campaignSettingsList.get(0).getCampaignSettingsCustomersList().get(0).getCsContact().getContactEmail();

		} catch (Exception e) {
			System.out.println("Exception in Campaign settings job");
		}

	}

}
