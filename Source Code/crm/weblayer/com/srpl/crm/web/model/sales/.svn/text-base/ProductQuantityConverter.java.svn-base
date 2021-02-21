package com.srpl.crm.web.model.sales;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
@RequestScoped
public class ProductQuantityConverter implements Converter, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		// TODO Auto-generated method stub
		if(value.trim().equals("")){
	           return null;			
		}
	    Integer i = Integer.parseInt(value.toString());
        System.out.println("i = "+i);	 	
		return (Object)i;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		// TODO Auto-generated method stub
		return value.toString();
	}

}
