package com.srpl.crm.web.common;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.srpl.um.ejb.entity.MailTemplateORM;

public class MailTemplateConverter implements Converter, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -732414195877139337L;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		System.out.println("MailTemplate converter called");
		if(value == null){
			return null;
		}
		if (value.trim().equals("")) {
			return null;
		}
		try {
			MailTemplateORM mailTemplate = new MailTemplateORM();
			String[] splitedVals = value.split(";");
			for (String x : splitedVals) {
				if (!value.trim().equals("")){
					String[] innerVals = x.split(":");
					switch (innerVals[0].trim()) {
					case "getTemplate":
						mailTemplate.setTemplate(innerVals[1]);
						break;
					case "getTemplateId":
						mailTemplate.setTemplateId(Long.valueOf(innerVals[1].trim()));
						break;
					case "getTitle":
						mailTemplate.setTitle(innerVals[1]);
						break;
					}
				}
			}
			return mailTemplate;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		MailTemplateORM service = (MailTemplateORM) value;
		StringBuffer sb = new StringBuffer(" ");
		try {
			for (Method m : service.getClass().getMethods()) {
				if(m.getName().contains("get")){
					if(m.getReturnType().isPrimitive()){
						Object temp = m.invoke(service);
						if(temp != null && !m.getName().contentEquals("getMailTemplates")){
							sb.append(m.getName());
							sb.append(":");
							sb.append(temp.toString());
							sb.append("; ");
						}
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
