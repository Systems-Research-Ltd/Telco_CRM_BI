package com.srpl.crm.web.model.um.company;

import java.util.List;

import javax.faces.bean.ManagedBean;

import com.bitguiders.util.jsf.JSFBeanInterface;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;
@ManagedBean(name = "companyBean")
public class CompanyBean extends JSFBeanSupport implements
JSFBeanInterface{
	public CompanyBean(){
		setCurrentAction(WebConstants.ACTION_SECURITY,this.getClass());
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

}
