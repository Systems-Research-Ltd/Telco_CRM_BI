package com.srpl.crm.web.model.support;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.srpl.crm.ejb.entity.CsContactORM;
import com.srpl.crm.ejb.request.ContactDAO;

@ManagedBean
@RequestScoped
public class AutoCompleteCustomerConverter implements Converter, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB ContactDAO contactDao;
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		// TODO Auto-generated method stub
		if(value.trim().equals("")){
	           return null;			
		}
	    
	    CsContactORM customer = new CsContactORM();
	    
		Long contactId = Long.parseLong(value);
		try{
		   customer = contactDao.contactDetails(contactId);
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return (Object)customer;
		
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		// TODO Auto-generated method stub
		return value.toString();
	}

}
