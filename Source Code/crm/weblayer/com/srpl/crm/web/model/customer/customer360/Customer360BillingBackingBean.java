package com.srpl.crm.web.model.customer.customer360;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import com.bitguiders.util.jsf.JSFBeanInterface;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.srpl.crm.web.controller.BeanFactory;
import com.srpl.crm.web.common.InnerTabs;
import com.srpl.crm.web.common.SessionDataBean;

@ManagedBean(name="customer360Billing")
public class Customer360BillingBackingBean extends JSFBeanSupport implements JSFBeanInterface {
	
	private SessionDataBean session;
	
	public Customer360BillingBackingBean(){
		session = BeanFactory.getInstance().getSessionBean();
	}

	@PostConstruct
	public void postConstruct() {
		if (getAction().equals("")) {
			// no action was called, load group data
			if(session.getUserModule_selectedUser() != 0L){
				listPage();
				setViewAction();
			}
		}
	}
	
	@Override
	public String actionListener() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> getList() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void listPage(){
		this.changeTabPath(5, "my path");
		setListAction(true);
	}

	private void changeTabPath(int index, String path) {
		InnerTabs d = session.getContactTabs().get(index);
		d.setPath(path);
		session.getContactTabs().set(index, d);
		session.setCustomerModule_contactTabIndex(5);
	}
	
}
