package com.srpl.crm.web.model.common;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;
import com.srpl.um.ejb.request.Application;

//eager attribute will initialize this at project level before any request with @ApplicationScoped
//@ManagedBean(name="actionListener", eager=true)
@ManagedBean(name = "actionListener")
@SessionScoped
public class ActionListenerBackingBean extends JSFBeanSupport {

	private String selectedTab = WebConstants.TAB_DASHBOARD;
	private String selectedMenu = "";
	private String selectedMenuItem = "";
	private String selectedAction = "";
	private String selectedSubMenu = "";

	public String getSelectedTab() {
		return selectedTab;
	}

	public void setSelectedTab(String selectedTab) {
		this.selectedTab = selectedTab;
	}

	public String getSelectedMenu() {
		return selectedMenu;
	}

	public void setSelectedMenu(String selectedMenu) {
		this.selectedMenu = selectedMenu;
	}

	public String getSelectedMenuItem() {
		return selectedMenuItem;
	}

	public void setSelectedMenuItem(String selectedMenuItem) {
		this.selectedMenuItem = selectedMenuItem;
	}

	public String getSelectedAction() {
		return selectedAction;
	}

	public String getSelectedSubMenu() {
		return selectedSubMenu;
	}

	public void setSelectedSubMenu(String selectedSubMenu) {
		this.selectedSubMenu = selectedSubMenu;
	}

	public void setSelectedAction(String selectedAction) {
		this.selectedAction = selectedAction;
	}

	public String tabListener() {
		
	//	setCurrentAction(getParameter("action"),this.getClass());
		String action = getAction();
		System.out.println("action in ActionListenerBackingBean tabListener() = "+action);
		
		
		//Set Top Menu
		String toolbar = getParameter("toolbar");
		if(toolbar.isEmpty()){
			setSelectedMenu("");
		}
		else{
			setSelectedMenu(toolbar);
		}
		//Clear Sub Menu
		setSelectedSubMenu("");
		//Clear Action
		setSelectedAction("");

		setSelectedTab(action);
//		BeanFactory.getInstance().getSampleBackingBean().add(action);
		if (WebConstants.TAB_MARKETING.equals(action)) {
			return WebConstants.TAB_MARKETING;
		} else if (WebConstants.TAB_REGISTER.equals(action)) {
			return WebConstants.TAB_REGISTER;
		} else if (WebConstants.TAB_DASHBOARD.equals(action)) {
			return WebConstants.TAB_DASHBOARD;
		} else if (WebConstants.TAB_SALES.equals(action)) {
			return WebConstants.TAB_SALES;
		} else if (WebConstants.TAB_SUPPORT.equals(action)) {
			return WebConstants.TAB_SUPPORT;
		} else if (WebConstants.TAB_CUSTOMER.equals(action)) {
			return WebConstants.TAB_CUSTOMER;
		} else if (WebConstants.TAB_ADMIN.equals(action)) {
			return WebConstants.TAB_ADMIN;
		} else if (WebConstants.TAB_LOYALTY.equals(action)) {
			return WebConstants.TAB_LOYALTY;
		} else if (WebConstants.TAB_ACCOUNT_SETTINGS.equals(action)) {
			return WebConstants.TAB_ACCOUNT_SETTINGS;
		} else if (WebConstants.TAB_REPORTS.equals(action)) {
			return WebConstants.TAB_REPORTS;
		} else if (WebConstants.TAB_ORDERS.equals(action)) {
			return WebConstants.TAB_ORDERS;
		} else if (WebConstants.TAB_HOME.equals(action)) {
			System.out.println(Application.getInstance().getApplicationType());
			System.out.println((Application.getInstance().getApplicationType() == WebConstants.APPLICATION_CRM) ? WebConstants.APPLICATION_CRM : WebConstants.APPLICATION_BI);
			return (Application.getInstance().getApplicationType() == WebConstants.APPLICATION_CRM) ? WebConstants.APPLICATION_CRM : WebConstants.APPLICATION_BI;
		}
		return "failure";
	}

	public String menuListener() {
		String action = getAction();
		System.out.println(">>> "+action);
		//Clear Sub Menu
		setSelectedSubMenu("");
		//Clear Action
		setSelectedAction("");
		
		setSelectedMenu(action);
//		BeanFactory.getInstance().getSampleBackingBean().add(action);
		if (WebConstants.MENU_1.equals(action)) {
			return WebConstants.MENU_1;
		} else if (WebConstants.MENU_2.equals(action)) {
			return WebConstants.MENU_2;
		} else if (WebConstants.MENU_3.equals(action)) {
			return WebConstants.MENU_3;
		} else if (WebConstants.MENU_CONTACTS.equals(action)) {
			return WebConstants.MENU_CONTACTS;
		} else if (WebConstants.MENU_ACCOUNTS.equals(action)) {
			return WebConstants.MENU_ACCOUNTS;
		} else if (WebConstants.MENU_ALERTSREMINDERS.equals(action)) {
			
			return WebConstants.MENU_ALERTSREMINDERS;
		} else if (WebConstants.MENU_ADMINALERTSREMINDERS.equals(action)) {
			System.out.println(WebConstants.MENU_ADMINALERTSREMINDERS);
			return WebConstants.MENU_ADMINALERTSREMINDERS;
		} else if (WebConstants.MENU_COMPANIES.equals(action)) {
			return WebConstants.MENU_COMPANIES;
		} else if (WebConstants.MENU_IMPORTACCOUNTS.equals(action)) {
			return WebConstants.MENU_IMPORTACCOUNTS;
		} else if (WebConstants.MENU_IMPORTCONTACTS.equals(action)) {
			return WebConstants.MENU_IMPORTCONTACTS;
		} else if (WebConstants.MENU_IMPORTUSERS.equals(action)) {
			return WebConstants.MENU_IMPORTUSERS;	
		} else if (WebConstants.MENU_DOCUMENTS.equals(action)) {
			return WebConstants.MENU_DOCUMENTS;
		} else if (WebConstants.MENU_FRANCHISES.equals(action)) {
			return WebConstants.MENU_FRANCHISES;
		} else if (WebConstants.MENU_GROUPS.equals(action)) {
			return WebConstants.MENU_GROUPS;
		} else if (WebConstants.MENU_UPLOADLOGO.equals(action)) {
			return WebConstants.MENU_UPLOADLOGO;
		} else if (WebConstants.MENU_USERS.equals(action)) {
			return WebConstants.MENU_USERS;
		} else if (WebConstants.MENU_THEMESSETTINGS.equals(action)) {
			return WebConstants.MENU_THEMESSETTINGS;
		} else if (WebConstants.MENU_PROFILE.equals(action)) {
			return WebConstants.MENU_PROFILE;
		} else if (WebConstants.MENU_CHANGEPASSWORD.equals(action)) {
			return WebConstants.MENU_CHANGEPASSWORD;
		} else if (WebConstants.MENU_LEADS.equals(action)) {
			return WebConstants.MENU_LEADS;
		} else if (WebConstants.MENU_OPPORTUNITIES.equals(action)) {
			return WebConstants.MENU_OPPORTUNITIES;
		} else if (WebConstants.MENU_ORDERS.equals(action)) {
			return WebConstants.MENU_ORDERS;
		}else if (WebConstants.MENU_INVOICE_SETTINGS.equals(action)) {
			return WebConstants.MENU_INVOICE_SETTINGS;
		} else if (WebConstants.MENU_PRODUCTS.equals(action)) {
			return WebConstants.MENU_PRODUCTS;
		} else if (WebConstants.MENU_PACKAGES.equals(action)) {
			return WebConstants.MENU_PACKAGES;
		} else if (WebConstants.MENU_CASES.equals(action)) {
			return WebConstants.MENU_CASES;
		} else if (WebConstants.MENU_ESCALATIONS.equals(action)) {
			return WebConstants.MENU_ESCALATIONS;
		}else if (WebConstants.MENU_TICKETS.equals(action)) {
			return WebConstants.MENU_TICKETS;
		}else if (WebConstants.MENU_IMPORT.equals(action)) {
			return WebConstants.MENU_IMPORT;
		} else if (WebConstants.MENU_BILLREIMBURSEMENT.equals(action)) {
			return WebConstants.MENU_BILLREIMBURSEMENT;
		}else if (WebConstants.MENU_LOYALTY.equals(action)) {
			return WebConstants.MENU_LOYALTY;
		}else if (WebConstants.MENU_MESSAGETEMPLATE.equals(action)) {
			return WebConstants.MENU_MESSAGETEMPLATE;
		}else if (WebConstants.MENU_DATASOURCE.equals(action)) {
			return WebConstants.MENU_DATASOURCE;
		}/*else if(WebConstants.MENU_CUSTOMER_ORDERS.equals(action)){
			return WebConstants.MENU_CUSTOMER_ORDERS;
		}else if(WebConstants.MENU_CUSTOMER_ALERTSREM.equals(action)){
			return WebConstants.MENU_CUSTOMER_ALERTSREM;
		}*/
		else if (WebConstants.MENU_MANAGESERVICES.equals(action)) {
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> i am about to return MENU_MANAGESERVICES");
			return WebConstants.MENU_MANAGESERVICES;
		}
		else if (WebConstants. MENU_MAILTEMPLATES.equals(action)) {
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> i am about to return MENU_MAILTEMPLATES");
			return WebConstants.MENU_MAILTEMPLATES;
		}
		return "failure";
	}

	public String menuItemListener() {
		String action = getAction();
		setSelectedMenuItem(action);
//		BeanFactory.getInstance().getSampleBackingBean().add(action);
		if (WebConstants.MENU_ITEM_1.equals(action)) {
			return WebConstants.MENU_ITEM_1;
		} else if (WebConstants.MENU_ITEM_2.equals(action)) {
			return WebConstants.MENU_ITEM_2;
		} else if (WebConstants.MENU_ITEM_3.equals(action)) {
			return WebConstants.MENU_ITEM_3;
		} 
		return "failure";
	}
	
	public boolean renderText(String txt){
		if(txt == "tick.jpg" || txt == "cross.jpg")
			return false;
		return true;
	}

}
