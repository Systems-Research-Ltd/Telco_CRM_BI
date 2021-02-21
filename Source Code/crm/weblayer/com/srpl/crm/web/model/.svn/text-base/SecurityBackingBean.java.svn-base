package com.srpl.crm.web.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;
import com.bitguiders.util.security.Permission;
import com.srpl.crm.web.controller.CustomerAccountController;
import com.srpl.crm.web.controller.CustomerContactController;
import com.srpl.crm.web.model.customer.customer360.AccountBackingBean;
import com.srpl.crm.web.model.customer.customer360.ContactBackingBean;
import com.srpl.crm.web.model.dashboard.DashBean;
import com.srpl.crm.web.model.documents.DocumentsBackingBean;
import com.srpl.crm.web.model.loyalty.LoyaltyBackingBean;
import com.srpl.crm.web.model.loyalty.LoyaltyBean;
import com.srpl.crm.web.model.loyalty.TemplateBackingBean;
import com.srpl.crm.web.model.marketing.MarketingBackingBean;
import com.srpl.crm.web.model.report.ReportBackingBean;
import com.srpl.crm.web.model.sales.InvoiceSettingsBackingBean;
import com.srpl.crm.web.model.sales.LeadsBackingBean;
import com.srpl.crm.web.model.sales.OpportunityBackingBean;
import com.srpl.crm.web.model.sales.OrderBackingBean;
import com.srpl.crm.web.model.sales.PackageBackingBean;
import com.srpl.crm.web.model.sales.ProductBackingBean;
import com.srpl.crm.web.model.sales.SalesBackingBean;
import com.srpl.crm.web.model.support.CaseBackingBean;
import com.srpl.crm.web.model.support.CaseEscalationBackingBean;
import com.srpl.crm.web.model.support.SupportBackingBean;
import com.srpl.crm.web.model.um.customer.CustomerBackingBean;

@ManagedBean(name="security")
@SessionScoped
public class SecurityBackingBean extends JSFBeanSupport {

	public Permission getOrder(){
		System.out.println("--------------order security called");
		setCurrentAction(WebConstants.ACTION_SECURITY,OrderBackingBean.class);
		return getProfile().getPermission();
	}
	public Permission getDashboard(){
		System.out.println("--------------dashboard security called");
		setCurrentAction(WebConstants.ACTION_SECURITY,DashBean.class);
		return getProfile().getPermission();
	}
	
	public Permission getSupport(){
		System.out.println("--------------support security called");
		setCurrentAction(WebConstants.ACTION_SECURITY,SupportBackingBean.class);
		return getProfile().getPermission();
	}
	
	public Permission getMarketing(){
		System.out.println("--------------marketing security called");
		setCurrentAction(WebConstants.ACTION_SECURITY,MarketingBackingBean.class);
		return getProfile().getPermission();
	}
	
	public Permission getSales(){
		System.out.println("--------------sales security called");
		setCurrentAction(WebConstants.ACTION_SECURITY,SalesBackingBean.class);
		return getProfile().getPermission();
	}
	
	public Permission getCustomer(){
		System.out.println("--------------customer security called");
		setCurrentAction(WebConstants.ACTION_SECURITY,CustomerBackingBean.class);
		return getProfile().getPermission();
	}
	
	public Permission getImport(){
		System.out.println("--------------import security called");
		setCurrentAction(WebConstants.ACTION_SECURITY,ImportBackingBean.class);
		return getProfile().getPermission();
	}
	
	public Permission getLoyalty(){
		System.out.println("--------------loyalty security called");
		setCurrentAction(WebConstants.ACTION_SECURITY,LoyaltyBean.class);
		return getProfile().getPermission();
	}
	
	public Permission getReports(){
		System.out.println("--------------reports security called");
		setCurrentAction(WebConstants.ACTION_SECURITY,ReportBackingBean.class);
		return getProfile().getPermission();
	}
	
	//topMenu permission methods
	public Permission getContact(){
		System.out.println("--------------contact security called");
		setCurrentAction(WebConstants.ACTION_SECURITY,ContactBackingBean.class);
		return getProfile().getPermission();
	}
	
	public Permission getAccount(){
		System.out.println("--------------account security called");
		setCurrentAction(WebConstants.ACTION_SECURITY,AccountBackingBean.class);
		return getProfile().getPermission();
	}
	
	public Permission getLeads(){
		System.out.println("--------------leads security called");
		setCurrentAction(WebConstants.ACTION_SECURITY,LeadsBackingBean.class);
		return getProfile().getPermission();
	}
	
	public Permission getOpportunity(){
		System.out.println("--------------opportunity security called");
		setCurrentAction(WebConstants.ACTION_SECURITY,OpportunityBackingBean.class);
		return getProfile().getPermission();
	}
	
	public Permission getProducts(){
		System.out.println("--------------products security called");
		setCurrentAction(WebConstants.ACTION_SECURITY,ProductBackingBean.class);
		return getProfile().getPermission();
	}
	
	public Permission getPackages(){
		System.out.println("--------------packages security called");
		setCurrentAction(WebConstants.ACTION_SECURITY,PackageBackingBean.class);
		return getProfile().getPermission();
	}
	
	public Permission getInvoiceSettings(){
		System.out.println("--------------invoicesettings security called");
		setCurrentAction(WebConstants.ACTION_SECURITY,InvoiceSettingsBackingBean.class);
		return getProfile().getPermission();
	}
	
	public Permission getBillReimbursement(){
		System.out.println("--------------billreimbursement security called");
		setCurrentAction(WebConstants.ACTION_SECURITY,BillBackingBean.class);
		return getProfile().getPermission();
	}
	
	public Permission getCases(){
		System.out.println("--------------case security called");
		setCurrentAction(WebConstants.ACTION_SECURITY,CaseBackingBean.class);
		return getProfile().getPermission();
	}
	
	public Permission getEscalations(){
		System.out.println("--------------escalations security called");
		setCurrentAction(WebConstants.ACTION_SECURITY,CaseEscalationBackingBean.class);
		return getProfile().getPermission();
	}
	
	public Permission getLoyaltyRule(){
		System.out.println("--------------loyaltyrule security called");
		setCurrentAction(WebConstants.ACTION_SECURITY,LoyaltyBackingBean.class);
		return getProfile().getPermission();
	}
	
	public Permission getMessageTemplate(){
		System.out.println("--------------messageTemplate security called");
		setCurrentAction(WebConstants.ACTION_SECURITY,TemplateBackingBean.class);
		return getProfile().getPermission();
	}
	
	public Permission getDocument(){
		System.out.println("--------------document security called");
		setCurrentAction(WebConstants.ACTION_SECURITY,DocumentsBackingBean.class);
		return getProfile().getPermission();
	}
}
