package com.srpl.bi.web.model.user;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@ManagedBean(name="userBackingBean")
@SessionScoped
public class UserBackingBean {

	//Constructor
	public UserBackingBean(){
		
	}
	
	// =============================== user history ===================================== //
	@PostConstruct
	 public void construct() {	  
	 }

	public String signOut(){
		return "signout";
	}


}
