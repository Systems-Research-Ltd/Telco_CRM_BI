package com.srpl.crm.web.common;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.srpl.um.ejb.entity.UmService;

public class UmServiceConverter implements Converter, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3537991139484905993L;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		System.out.println("UmService converter called");
		if (value.trim().equals("")) {
			return null;
		}
		try {
			UmService service = new UmService();
			String[] splitedVals = value.split(";");
			for (String x : splitedVals) {
				if (!value.trim().equals("")){
					String[] innerVals = x.split(":");
					switch (innerVals[0].trim()) {
					case "getServiceId":
						service.setServiceId(Long.valueOf(innerVals[1].trim()));
						break;
					case "getServiceName":
						service.setServiceName(innerVals[1]);
						break;
					case "getServiceTitle":
						service.setServiceTitle(innerVals[1]);
						break;
					case "getServiceDescription":
						service.setServiceDescription(innerVals[1]);
						break;
					case "getIsParent":
						service.setIsParent(Boolean.getBoolean(innerVals[1]));
						break;
					case "getServiceParentId":
						String temp = innerVals[1].trim();
						service.setServiceParentId(Integer.valueOf(temp));
						break;
					}
				}
			}
			return service;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		UmService service = (UmService) value;
		StringBuffer sb = new StringBuffer(" ");
		try {
			for (Method m : service.getClass().getMethods()) {
				if(m.getName().contains("get")){
					Object temp = m.invoke(service);
					if(temp != null){
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
