package com.srpl.crm.web.model.um.alertsandreminders.scanner;
import java.util.List;

import com.srpl.crm.ejb.entity.UmAlertsAndReminders;


public interface AlertScanner {

	public List<UmAlertsAndReminders> start();
	public	void remind(long objectId,int companyId,String endDate);
	public  void alert(long objectId,int companyId,String endDate);
}
