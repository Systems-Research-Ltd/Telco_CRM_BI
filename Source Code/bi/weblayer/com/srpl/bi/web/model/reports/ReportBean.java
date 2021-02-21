package com.srpl.bi.web.model.reports;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;
import com.srpl.bi.web.model.common.ColumnModel;

@ManagedBean(name="reportBean")
public class ReportBean extends JSFBeanSupport implements Serializable {
	public ReportBean() {
		// TODO Auto-generated constructor stub
		System.out.println("BI----------------- ReportBean called");
		setCurrentAction(WebConstants.ACTION_SECURITY,this.getClass());
	}
}
