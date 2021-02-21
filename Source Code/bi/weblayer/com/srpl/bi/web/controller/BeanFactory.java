package com.srpl.bi.web.controller;


import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;


import com.srpl.bi.web.common.SessionDataBean;
import com.srpl.bi.web.model.common.ActionListenerBackingBean;
import com.srpl.bi.web.model.reportsbuilder.DataPaletteBackingBean;
import com.srpl.bi.web.model.reportsbuilder.DataSourceBackingBean;
import com.srpl.bi.web.model.reportsbuilder.ReportDesignerBackingBean;




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


	public SessionDataBean getSessionBean(){
		return (SessionDataBean)getBackingBean("sessionDataBean");
	}
	
	public ReportDesignerBackingBean getReportDesignerBean(){
		return (ReportDesignerBackingBean)getBackingBean("reportDesignerBean");
	}
	
	public DataPaletteBackingBean getDataPaletteBean(){
		return (DataPaletteBackingBean)getBackingBean("reportBuilderBean");
	}
	public DataSourceBackingBean getDataSourceBean(){
		return (DataSourceBackingBean)getBackingBean("dataSourceBean");
	}
	
}

