package com.srpl.bi.web.model.common;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;

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
		if (WebConstants.TAB_DASHBOARD.equals(action)) {
			return WebConstants.TAB_DASHBOARD;
		} else if (WebConstants.TAB_REPORTS.equals(action)) {
			return WebConstants.TAB_REPORTS;
		} else if (WebConstants.TAB_DATASOURCES.equals(action)) {
			return WebConstants.TAB_DATASOURCES;
		} else if (WebConstants.TAB_ADMIN.equals(action)) {
			return WebConstants.TAB_ADMIN;
//		} else if (WebConstants.TAB_SETTINGS.equals(action)) {
//			return WebConstants.TAB_SETTINGS;
		}
		return "failure";
	}

	public String menuListener() {
		String action = getAction();

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
		}  else if (WebConstants.MENU_COMPANIES.equals(action)) {
			return WebConstants.MENU_COMPANIES;
		} else if (WebConstants.MENU_GROUPS.equals(action)) {
			return WebConstants.MENU_GROUPS;
		} else if (WebConstants.MENU_USERS.equals(action)) {
			return WebConstants.MENU_USERS;
		} else if (WebConstants.MENU_THEMESSETTINGS.equals(action)) {
			return WebConstants.MENU_THEMESSETTINGS;
		} else if (WebConstants.MENU_PROFILE.equals(action)) {
			return WebConstants.MENU_PROFILE;
		} else if (WebConstants.MENU_CHANGEPASSWORD.equals(action)) {
			return WebConstants.MENU_CHANGEPASSWORD;
		}else if (WebConstants.MENU_CONNECTION.equals(action)) {
			return WebConstants.MENU_CONNECTION;
		}else if (WebConstants.MENU_DATASET.equals(action)) {
			return WebConstants.MENU_DATASET;
		}else if (WebConstants.MENU_MANAGESERVICES.equals(action)) {
			return WebConstants.MENU_MANAGESERVICES;
		}else if (WebConstants.MENU_DASHBOARD.equals(action)) {
			return WebConstants.MENU_DASHBOARD;	
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
