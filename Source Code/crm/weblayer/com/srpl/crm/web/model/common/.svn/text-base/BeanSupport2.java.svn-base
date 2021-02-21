/**
 * 
 */
package com.srpl.crm.web.model.common;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.bitguiders.util.jsf.WebConstants;
import com.srpl.crm.web.controller.BeanFactory;

/**
 * @author abdulkareem
 *
 */
@Deprecated
public abstract class BeanSupport2{

	private boolean isCreateAction;
	private boolean isDeleteAction;
	private boolean isViewAction;
	private boolean isEditAction;
	private boolean isListAction;
	private boolean isCancelAction;
	private boolean isSaveAction;
	
	private String rowid;
	private String currentAction;
	private boolean isSendAction;
	// create action for BI
	private boolean isCreateDSFAction;
	
	
	// private ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.sid");
	
/*	@EJB
	protected ApplicationServiceLocal applicationService;
*/	
	/*@PostConstruct
	public void init(){
		//reset();
		setCreateAction();
		setViewAction();
		setEditAction();
		setDeleteAction();
	}*/
	public boolean isCreateAction() {
		return isCreateAction;
	}

	public void setCreateAction(boolean isCreateAction) {
		this.isCreateAction = isCreateAction;
		this.isCancelAction = isCreateAction;
		this.isSaveAction = isCreateAction;
		setCurrentAction(WebConstants.ACTION_CREATE);
	}

	public boolean isDeleteAction() {
		return isDeleteAction;
	}

	public void setDeleteAction(boolean isDeleteAction) {
		this.isDeleteAction = isDeleteAction;
		setCurrentAction(WebConstants.ACTION_DELETE);
	}

	public boolean isViewAction() {
		return isViewAction;
	}

	public void setViewAction(boolean isViewAction) {
		this.isViewAction = isViewAction;
		setCurrentAction(WebConstants.ACTION_VIEW);
	}

	public boolean isEditAction() {
		return isEditAction;
	}

	public void setEditAction(boolean isEditAction) {
		this.isEditAction = isEditAction;
		setCurrentAction(WebConstants.ACTION_EDIT);
	}


	public String getCurrentAction() {
		return currentAction;
	}

	public void setCurrentAction(String currentAction) {
		this.currentAction = currentAction;
	}


	public void reset(){
		  isCreateAction = false;
		  isDeleteAction = false;
		  isViewAction   = false;
		  isEditAction   = false;
		  isListAction	 = false;
		  isCreateDSFAction = false;
		  isSaveAction = false;
		  
		  setRowid("");
		  currentAction="";
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

	/*
	public String getProperty(String key){
		try{
		return resourceBundle.getString(key);
		}catch(Exception ex){
			addMessage(ex.getMessage());
			return "";
		}
	}
	*/
	
	public String getAction() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		String action = request.getParameter("action");
		action = (action != null ? action.trim() : "");
		//System.out.println("getting action in bean support"+action);
		return action;
	}
	
	public String getParameter(String x) {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		String action = request.getParameter(x);
		action = (action != null ? action.trim() : "");
		return action;
	}

	public boolean isListAction() {
		return isListAction;
	}

	public void setListAction(boolean isListAction) {
		setCurrentAction(WebConstants.ACTION_LIST);
		//clear the action area
		ActionListenerBackingBean aL = BeanFactory.getInstance().getUserProfileBackingBean();
		aL.setSelectedAction("");
		this.isListAction = isListAction;
	}

	public String getRowid() {
		return rowid;
	}

	public void setRowid(String rowid) {
		this.rowid = rowid;
	}

	public boolean isSendAction() {
		return isSendAction;
	}

	public void setSendAction(boolean isSendAction) {
		this.isSendAction = isSendAction;
	}

	public boolean isCreateDSFAction() {
		return isCreateDSFAction;
	}

	public void setCreateDSFAction(boolean isCreateDSFAction) {
		this.isCreateDSFAction = isCreateDSFAction;
	}

	public boolean isCancelAction() {
		return isCancelAction;
	}

	public void setCancelAction(boolean isCancelAction) {
		this.isCancelAction = isCancelAction;
	}

	public boolean isSaveAction() {
		return isSaveAction;
	}

	public void setSaveAction(boolean isSaveAction) {
		this.isSaveAction = isSaveAction;
	}

	


}
