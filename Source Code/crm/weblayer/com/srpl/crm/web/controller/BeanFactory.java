package com.srpl.crm.web.controller;


import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;

import com.srpl.crm.web.model.IndexBackingBean;




import com.srpl.crm.web.common.SessionDataBean;
import com.srpl.crm.web.controller.CompanyController;
import com.srpl.crm.web.controller.MarketingControllerBean;
import com.srpl.crm.web.model.AlertsAndRemindersBackingBean;
import com.srpl.crm.web.model.CampaignBackingBean;
import com.srpl.crm.web.model.Customer360ViewBackingBean;
import com.srpl.crm.web.model.FranchiseBackingBean;
import com.srpl.crm.web.model.InvoiceBackingBean;
import com.srpl.crm.web.model.NewCompanyBackingBean;
import com.srpl.crm.web.model.NewUserBackingBean;
import com.srpl.crm.web.model.PackagesBackingBean;
import com.srpl.crm.web.model.PaymentBackingBean;
import com.srpl.crm.web.model.SampleBackingBean;
import com.srpl.crm.web.model.SecurityBackingBean;
import com.srpl.crm.web.model.SubscriptionBackingBean;
import com.srpl.crm.web.model.common.ActionListenerBackingBean;
import com.srpl.crm.web.model.common.Address;
import com.srpl.crm.web.model.documents.DocumentsBackingBean;
import com.srpl.crm.web.model.loyalty.LoyaltyBackingBean;
import com.srpl.crm.web.model.loyalty.TemplateBackingBean;
import com.srpl.crm.web.model.sales.LeadsBackingBean;
import com.srpl.crm.web.model.sales.OrderBackingBean;
import com.srpl.crm.web.model.um.admin.groups.GroupBackingBean;
import com.srpl.crm.web.model.um.admin.groups.GroupModuleDetailBackingBean;
import com.srpl.crm.web.model.um.admin.users.UsersListingBackingBean;
import com.srpl.crm.web.model.um.company.ParameterBackingBean;
import com.srpl.crm.web.model.um.customer.AccountBackingBean;
import com.srpl.crm.web.model.user.CreateUserBean;
import com.srpl.crm.web.model.user.HistoryBackingBean;

//Factory Pattern
public class BeanFactory {

	private static BeanFactory backingBeanUtil = new BeanFactory();
	
	//Singelton Pattern
	private BeanFactory(){
	}
	
	public static BeanFactory getInstance(){
		return backingBeanUtil;
	}
	
	private Object getBackingBean( String beanName )
	{
	        FacesContext context = FacesContext.getCurrentInstance();
	        Application app = context.getApplication();
	        ValueExpression expression = app.getExpressionFactory().createValueExpression(context.getELContext(),
	                        String.format("#{%s}", beanName), Object.class);
	        return expression.getValue(context.getELContext());
	}

	public ActionListenerBackingBean getUserProfileBackingBean(){
		return (ActionListenerBackingBean)getBackingBean("actionListener");
	}

	public SampleBackingBean getSampleBackingBean(){
		return (SampleBackingBean)getBackingBean("sampleBackingBean");
	}
	
	public IndexBackingBean getIndexBackingBean(){
		return (IndexBackingBean)getBackingBean("indexBackingBean");
	}
	
	public AccountBackingBean getAccountBean(){
		return (AccountBackingBean)getBackingBean("accounts");
	}
	
	public GroupBackingBean getGroupBean(){
		return (GroupBackingBean)getBackingBean("groups");
	}

	public CreateUserBean getCreateUserBean(){
		return (CreateUserBean)getBackingBean("createUserBean");
	}
	
	public GroupModuleDetailBackingBean getGroupModuleDetailBean(){
		return (GroupModuleDetailBackingBean)getBackingBean("groupDetails");
	}
	
	/*public ManageServicesBackingBean getGroupOperationBackingBean(){
		return (ManageServicesBackingBean)getBackingBean("groupOperationBackingBean");
	}*/
	

	public Address getAddressBean(){
		return (Address)getBackingBean("addressBean");
	}
	
	public CampaignBackingBean getCampaignBean(){
		return (CampaignBackingBean)getBackingBean("campaign");
	}
	
	public SessionDataBean getSessionBean(){
		return (SessionDataBean)getBackingBean("sessionDataBean");
	}
	
	public DocumentsBackingBean getDocumentsBackingBean(){
		return (DocumentsBackingBean)getBackingBean("Documents");
	}
	
	public LeadsBackingBean getLeadsBackingBean(){
		return (LeadsBackingBean)getBackingBean("Leads");
	}
	
	public UsersListingBackingBean getUsersListingBackingBean(){
		return (UsersListingBackingBean)getBackingBean("userListings");
	}
	
	public MarketingControllerBean getMarketingControllerBean(){
		return (MarketingControllerBean)getBackingBean("marketingControllerBean");
	}
	
	public PackagesBackingBean getPackagesBackingBean(){
		return (PackagesBackingBean)getBackingBean("packages");
	}
	
	public NewCompanyBackingBean getCompanyBackingBean(){
		return (NewCompanyBackingBean)getBackingBean("company");
	}
	
	public NewUserBackingBean getUserBackingBean(){
		return (NewUserBackingBean)getBackingBean("userB");
	}
	
	public ParameterBackingBean getParameterBackingBean(){
		return (ParameterBackingBean)getBackingBean("parameterBackingBean");
	}
	
	public FranchiseBackingBean getFranchiseBackingBean(){
		return (FranchiseBackingBean)getBackingBean("franchiseBackingBean");
	}
	

	
	public HistoryBackingBean getHistoryBackingBean(){
		return(HistoryBackingBean)getBackingBean("history");
	}
	
	public AlertsAndRemindersBackingBean getAlertsAndRemindersBackingBean(){
		return(AlertsAndRemindersBackingBean)getBackingBean("alertsAndReminders");
	}
	
	public SubscriptionBackingBean getSubscriptionBackingBean(){
		return(SubscriptionBackingBean)getBackingBean("subscription");
	}
	
	public InvoiceBackingBean getInvoiceBackingBean(){
		return(InvoiceBackingBean)getBackingBean("invoice");
	}
	
	public Customer360ViewBackingBean getCustomer360BeackingBean(){
		return(Customer360ViewBackingBean)getBackingBean("customer360");
	}
	
	
	public OrderBackingBean getOrderBackingBean(){
		return(OrderBackingBean)getBackingBean("orderBackingBean");
	}
	
	public PaymentBackingBean getPaymentBackingBean(){
		return(PaymentBackingBean)getBackingBean("payment");
	}
	
	public CompanyController getCompanyController(){
		return(CompanyController)getBackingBean("companyController");
	}
	public NewCompanyBackingBean getNewCompanyBackingBean(){
		return(NewCompanyBackingBean)getBackingBean("newCompanyBackingBean");
	}
	public LoyaltyBackingBean getLoyaltyBackingBean(){
		return(LoyaltyBackingBean)getBackingBean("loyaltyBackingBean");
	}
	public TemplateBackingBean getTemplateBackingBean(){
		return(TemplateBackingBean)getBackingBean("templateBackingBean");
	}
	
	public SecurityBackingBean getSecurityBackingBean(){
		return(SecurityBackingBean)getBackingBean("security");
	}
	/*public UserController getUserController(){
	return(UserController)getBackingBean("userController");
}*/
}

