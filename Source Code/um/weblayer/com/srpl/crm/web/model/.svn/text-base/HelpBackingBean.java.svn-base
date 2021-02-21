package com.srpl.crm.web.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.bitguiders.util.jsf.JSFBeanSupport;
import com.srpl.crm.web.model.um.admin.groups.GroupModuleDetailBackingBean;
import com.srpl.crm.web.model.um.admin.groups.ServiceBackingBean;
import com.srpl.crm.web.model.um.admin.users.UserModuleDetailBackingBean;
import com.srpl.crm.web.model.um.company.CompanyBackingBean;
import com.srpl.crm.web.model.um.company.ParameterBackingBean;

@ManagedBean(name = "help")
@SessionScoped
public class HelpBackingBean extends JSFBeanSupport {

	public String getCompany() {
		return getProperty(CompanyBackingBean.class.getName());
	}

	public String getGroup() {
		return getProperty(GroupModuleDetailBackingBean.class.getName());
	}

	public String getUser() {
		return getProperty(UserModuleDetailBackingBean.class.getName());
	}

	public String getFranchise() {
		return getProperty(FranchiseBackingBean.class.getName());
	}

	public String getParameter() {
		return getProperty(ParameterBackingBean.class.getName());
	}

	public String getServices() {
		return getProperty(ServiceBackingBean.class.getName());
	}
}
