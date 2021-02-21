package com.srpl.bi.web.model;



import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;
import com.bitguiders.util.security.Permission;
import com.srpl.bi.web.model.dashboard.DashBoardBean;
import com.srpl.bi.web.model.reports.ReportBean;

@ManagedBean(name="security")
@SessionScoped
public class SecurityBackingBean extends JSFBeanSupport {

	public Permission getDashboard(){
		System.out.println("--------------dashboard security called");
		setCurrentAction(WebConstants.ACTION_SECURITY,DashBoardBean.class);
		return getProfile().getPermission();
	}
	
	public Permission getReports(){
		System.out.println("--------------reports security called");
		setCurrentAction(WebConstants.ACTION_SECURITY,ReportBean.class);
		return getProfile().getPermission();
	}
	}
