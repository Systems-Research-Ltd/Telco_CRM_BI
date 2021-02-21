package com.srpl.crm.web.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.bitguiders.util.jsf.JSFBeanSupport;
import com.srpl.crm.web.controller.CustomerAccountController;
import com.srpl.crm.web.controller.CustomerContactController;
import com.srpl.crm.web.model.customer.customer360.AccountBackingBean;
import com.srpl.crm.web.model.customer.customer360.ContactBackingBean;
import com.srpl.crm.web.model.documents.DocumentsBackingBean;
import com.srpl.crm.web.model.loyalty.LoyaltyBackingBean;
import com.srpl.crm.web.model.loyalty.TemplateBackingBean;
import com.srpl.crm.web.model.report.ReportBackingBean;
import com.srpl.crm.web.model.sales.BillBackingBean;
import com.srpl.crm.web.model.sales.InvoiceSettingsBackingBean;
import com.srpl.crm.web.model.sales.LeadsBackingBean;
import com.srpl.crm.web.model.sales.OpportunityBackingBean;
import com.srpl.crm.web.model.sales.OrderBackingBean;
import com.srpl.crm.web.model.sales.PackageBackingBean;
import com.srpl.crm.web.model.sales.ProductBackingBean;
import com.srpl.crm.web.model.support.CaseBackingBean;

@ManagedBean(name = "help")
@SessionScoped
public class HelpBackingBean extends JSFBeanSupport {

	public String getOrder() {
		return getProperty(OrderBackingBean.class.getName());
	}

	public String getInvoiceSettings() {
		return getProperty(InvoiceSettingsBackingBean.class.getName());
	}

	public String getBillReimbursement() {
		return getProperty(BillBackingBean.class.getName());
	}

	public String getLead() {
		return getProperty(LeadsBackingBean.class.getName());
	}

	public String getOpportunity() {
		return getProperty(OpportunityBackingBean.class.getName());
	}

	public String getPackage() {
		return getProperty(PackageBackingBean.class.getName());
	}

	public String getProduct() {
		return getProperty(ProductBackingBean.class.getName());
	}

	public String getCase() {
		return getProperty(CaseBackingBean.class.getName());
	}

	public String getReport() {
		return getProperty(ReportBackingBean.class.getName());
	}

	public String getCampaign() {
		return getProperty(MarketingModuleCampaignBackingBean.class.getName());
	}

	public String getLoyaltyRule() {
		return getProperty(LoyaltyBackingBean.class.getName());
	}

	public String getMessageTemplate() {
		return getProperty(TemplateBackingBean.class.getName());
	}

	public String getAccount() {
		return getProperty(AccountBackingBean.class.getName());
	}

	public String getContact() {
		return getProperty(ContactBackingBean.class.getName());
	}
	public String getDocument() {
		return getProperty(DocumentsBackingBean.class.getName());
	}
}
