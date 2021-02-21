package com.srpl.crm.web.common;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.srpl.um.ejb.entity.MailTemplateModuleORM;

public class MailTemplateModuleConverter implements Converter, Serializable {
	
	private static final long serialVersionUID = 8173886576036496001L;

	/**
	 * 
	 */

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		System.out.println("MailTemplateModule converter called");
		if(value == null){
			return null;
		}
		if (value.trim().equals("")) {
			return null;
		}
		try {
			MailTemplateModuleORM mailTemplateModule = new MailTemplateModuleORM();
			String[] splitedVals = value.split(";");
			for (String x : splitedVals) {
				if (!value.trim().equals("")){
					String[] innerVals = x.split(":");
					switch (innerVals[0].trim()) {
					case "getModuleId":
						mailTemplateModule.setModuleId(Long.valueOf(innerVals[1].trim()));
						break;
					case "getClassName":
						mailTemplateModule.setClassName(innerVals[1]);
						break;
					case "getTitle":
						mailTemplateModule.setTitle(innerVals[1]);
						break;
					}
				}
			}
			return mailTemplateModule;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		MailTemplateModuleORM service = (MailTemplateModuleORM) value;
		StringBuffer sb = new StringBuffer(" ");
		try {
			for (Method m : service.getClass().getMethods()) {
				if(m.getName().contains("get")){
					Object temp = m.invoke(service);
					if(temp != null && !m.getName().contentEquals("getMailTemplates")){
						sb.append(m.getName());
						sb.append(":");
						sb.append(temp.toString());
						sb.append("; ");
					}
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.out.println("IllegalAccessEx");
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}

}
