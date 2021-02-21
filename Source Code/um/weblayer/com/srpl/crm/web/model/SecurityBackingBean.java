package com.srpl.crm.web.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;
import com.bitguiders.util.security.Permission;
import com.srpl.crm.web.model.um.admin.AdminBackingBean;
import com.srpl.crm.web.model.um.admin.AlertsAndRemindersBackingBean;
import com.srpl.crm.web.model.um.admin.groups.GroupModuleDetailBackingBean;
import com.srpl.crm.web.model.um.admin.groups.ServiceBackingBean;
import com.srpl.crm.web.model.um.admin.users.UserModuleDetailBackingBean;
import com.srpl.crm.web.model.um.company.CompanyBean;
import com.srpl.crm.web.model.user.ChangePasswordBackingBean;
import com.srpl.crm.web.model.user.settings.ProfileBackingBean;
import com.srpl.crm.web.model.user.settings.SettingsBackingBean;
import com.srpl.crm.web.model.user.settings.ThemeSettingsBean;

@ManagedBean(name="security")
@SessionScoped
public class SecurityBackingBean extends JSFBeanSupport {
	//tabs security methods
	public Permission getAdmin(){
		System.out.println("--------------admin security called");
		setCurrentAction(WebConstants.ACTION_SECURITY,AdminBackingBean.class);
		return getProfile().getPermission();
	}
	
	public Permission getAccountSettings(){
		System.out.println("--------------accountsettings security called");
		setCurrentAction(WebConstants.ACTION_SECURITY,SettingsBackingBean.class);
		return getProfile().getPermission();
	}
	
	//topMenu security methods
	public Permission getCompanies(){
		System.out.println("--------------company security called");
		setCurrentAction(WebConstants.ACTION_SECURITY,CompanyBean.class);
		return getProfile().getPermission();
	}
	
	public Permission getFranchises(){
		System.out.println("--------------franchise security called");
		setCurrentAction(WebConstants.ACTION_SECURITY,FranchiseBackingBean.class);
		return getProfile().getPermission();
	}
	
	public Permission getUsers(){
		System.out.println("--------------users security called");
		setCurrentAction(WebConstants.ACTION_SECURITY,UserModuleDetailBackingBean.class);
		return getProfile().getPermission();
	}
	
	public Permission getGroups(){
		System.out.println("--------------groups security called");
		setCurrentAction(WebConstants.ACTION_SECURITY,GroupModuleDetailBackingBean.class);
		return getProfile().getPermission();
	}
	
	public Permission getServices(){
		System.out.println("--------------Services security called");
		setCurrentAction(WebConstants.ACTION_SECURITY,ServiceBackingBean.class);
		return getProfile().getPermission();
	}
	
	public Permission getMailTemplates(){
		System.out.println("--------------Mail Template security called");
		setCurrentAction(WebConstants.ACTION_SECURITY, MailTemplateBackingBean.class);
		return getProfile().getPermission();
	}
	
	public Permission getAdminAlertsReminders(){
		System.out.println("--------------adminAlertsReminders security called");
		setCurrentAction(WebConstants.ACTION_SECURITY,AlertsAndRemindersBackingBean.class);
		return getProfile().getPermission();
	}
	
	public Permission getThemesSettings(){
		System.out.println("--------------themesSettings security called");
		setCurrentAction(WebConstants.ACTION_SECURITY,ThemeSettingsBean.class);
		return getProfile().getPermission();
	}
	
	public Permission getUserProfile(){
		System.out.println("--------------userprofile security called");
		setCurrentAction(WebConstants.ACTION_SECURITY,ProfileBackingBean.class);
		return getProfile().getPermission();
	}
	
	public Permission getChangePassword(){
		System.out.println("--------------changepassword security called");
		setCurrentAction(WebConstants.ACTION_SECURITY,ChangePasswordBackingBean.class);
		return getProfile().getPermission();
	}
	
}
