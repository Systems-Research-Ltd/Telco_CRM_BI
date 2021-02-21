package com.srpl.crm.web.model.um.admin.users;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.srpl.crm.web.common.AjaxListStructure;
import com.srpl.um.ejb.entity.UmUser;
import com.srpl.um.ejb.exceptions.UserNotFoundException;
import com.srpl.um.ejb.request.UserDAO;

@ManagedBean(name="listConverter")
public class ListConverter implements Converter {
	@EJB
	private UserDAO userDao;
	
	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
		AjaxListStructure ajx = null;
		try {
			UmUser usr = userDao.umUserDetails(Long.valueOf(submittedValue));
			ajx = new AjaxListStructure();
			ajx.setId(usr.getUserId());
			ajx.setLabel(usr.getUserName() + " - " + usr.getUserFname() + ' ' + usr.getUserLname());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ajx;
    }  
	@Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {  
    	System.out.println("Submit Val "+value);
        if (value == null || value.equals("")) {  
            return "";  
        } else {  
        	return String.valueOf(((AjaxListStructure) value).getId());  
        }  
    } 
}
