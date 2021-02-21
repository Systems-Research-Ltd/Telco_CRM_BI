package com.bitguiders.util.jsf;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class JSFBeanValidator implements Validator {

	private static final String EMAIL_REGEXP = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final String PHONE_REGEXP  ="\\d{2}-\\d{3}-\\d{7}";
	private static final String CNIC_REGEXP  ="\\d{5}-\\d{7}-\\d{1}";
	private String dateMask = "99-99-999";
	private String cnicMask = "99999-9999999-9";
	private String phoneMask="99-999-9999999";
	private String TITLE_REGEXP = "^[a-zA-Z0-9_' ']*$" ;

	public String getDateMask() {
		return dateMask;
	}

	public void setDateMask(String dateMask) {
		this.dateMask = dateMask;
	}

	public String getCnicMask() {
		return cnicMask;
	}

	public void setCnicMask(String cnicMask) {
		this.cnicMask = cnicMask;
	}

	public String getPhoneMask() {
		return phoneMask;
	}

	public void setPhoneMask(String phoneMask) {
		this.phoneMask = phoneMask;
	}
	
	@Override
	public void validate(FacesContext fc, UIComponent ui, Object value)
			throws ValidatorException {
//		TODO
		//
	}

	public void dateValidator(FacesContext fc, UIComponent ui, Object value)
			throws ValidatorException {
//		TODO
	}
	public void emailValidator(FacesContext fc, UIComponent ui, Object value) 
			throws ValidatorException {
		String email = (String) value;
		if(email!=null && email.trim().length()>0){
		    if (!matches(EMAIL_REGEXP,email)) {
		    	throw new ValidatorException(getMessage("Enter valid email [ xxx@xxx.xxx ]"));
		    }
		}else{
			throw new ValidatorException(getMessage("Email is required"));
		}
	}
	public void requiredValidator(FacesContext fc, UIComponent ui, Object value)
			throws ValidatorException {
		String val = (String) value;
		if(val!=null && val.trim().length()<=0){
			throw new ValidatorException(getMessage("One of required field is empty"));
		}
	}
	public void numberValidator(FacesContext fc, UIComponent ui, Object value)
			throws ValidatorException {
		try{
			Double floatValue = (Double) value;
		}catch(ValidatorException vex){
			throw new ValidatorException(getMessage("Enter valid number"));
		}
		
	}
	
	public void reportsToValidator (FacesContext fc, UIComponent ui, Object value)
			throws ValidatorException {
		try{
			Long id = Long.valueOf(value.toString());
			if(id<1){
				throw new ValidatorException(getMessage("Reports to is required."));
			}
		}catch(ValidatorException vex){
			throw new ValidatorException(getMessage("Reports to is required."));
		}
	}

	public void phoneValidator(FacesContext fc, UIComponent ui, Object value)
			throws ValidatorException {
		String phone = (String) value;
		if(phone!=null && phone.trim().length()>0){
		    if (!matches(PHONE_REGEXP,phone)) {
		    	throw new ValidatorException(getMessage("Enter valid phone [ xx-xxx-xxxxxxx ]"));
		    }
		}else{
			throw new ValidatorException(getMessage("Phone is required"));
		}
	}

	public void cnicValidator(FacesContext fc, UIComponent ui, Object value)
			throws ValidatorException {
		String cnic = (String) value;
		if(cnic!=null && cnic.trim().length()>0){
		    if (!matches(CNIC_REGEXP,cnic)) {
		    	throw new ValidatorException(getMessage("Enter valid CNIC [ xxxxx-xxxxxxx-x ]"));
		    }
		}else{
			throw new ValidatorException(getMessage("CNIC is required"));
		}
	}

	public void titleValidator(FacesContext fc, UIComponent ui, Object value) 
			throws ValidatorException {
		String title = (String) value;
		if(title!=null && title.trim().length()>0){
		    if (!matches(TITLE_REGEXP,title)) {
		    	throw new ValidatorException(getMessage("Only Alphanumeric and underscores are allowed in Title."));
		    }
		}else{
			throw new ValidatorException(getMessage("Title is required"));
		}
	}
	private boolean matches(String pattern,String value){
	    Pattern patern = null;
	    patern = Pattern.compile(pattern);
	    Matcher matcher = patern.matcher(value);

		return matcher.matches();
	}
	public FacesMessage getMessage(String message){
		return new FacesMessage(FacesMessage.SEVERITY_INFO,message,message);
	}

	public void addMessage(String message){
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,message,message));
	}
	public void addError(String message){
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,message,message));
	}
	public void addWarning(String message){
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,message,message));
	}

	
}
