package com.srpl.um.web.controller;

import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;

import com.srpl.crm.web.model.IndexBackingBean;
import com.srpl.crm.web.model.FranchiseBackingBean;
import com.srpl.crm.web.model.NewUserBackingBean;
import com.srpl.crm.web.model.common.ActionListenerBackingBean;
import com.srpl.crm.web.model.common.Address;
import com.srpl.crm.web.model.um.admin.groups.GroupModuleDetailBackingBean;
import com.srpl.crm.web.model.um.admin.groups.ServiceBackingBean;
import com.srpl.crm.web.model.um.company.ParameterBackingBean;
import com.srpl.crm.web.model.user.HistoryBackingBean;
import com.srpl.um.web.common.SessionDataBean;


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
	
	public com.srpl.crm.web.model.IndexBackingBean getIndexBackingBean(){
		return (com.srpl.crm.web.model.IndexBackingBean)getBackingBean("indexBackingBean");
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
	
	public SessionDataBean getSessionBean(){
		return (SessionDataBean)getBackingBean("sessionDataBean");
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
	public ServiceBackingBean getServiceBackingBean(){
		return(ServiceBackingBean)getBackingBean("serviceBackingBean");
	}
	
}

