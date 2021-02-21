/**
 * 
 */
package com.srpl.crm.web.model.common;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;
import com.srpl.crm.web.controller.BeanFactory;

/**
 * @author Hammad Hassan Khan
 * 
 */
public abstract class BeanSupportH extends JSFBeanSupport {

	private boolean isCreateAction;
	private boolean isViewAction;
	private boolean isEditAction;
	private boolean isDeleteAction;
	private boolean isListAction;
	private boolean isDisableAllFields;
	private boolean isDisableSpecificFields;
	private boolean isEnableSpecificFields;

	private String rowid;
	private String currentAction;
	private boolean isSendAction;
	// create action for BI
	private boolean isCreateDSFAction;

	public BeanSupportH() {
		System.out.println("here in " + this.getClass().getName()
				+ "'s beanSupporH construct");
		String action;
		try {
			action = getAction();
			if (action != "") {
				// determine the previous action by using current action.
				// we'll cover only the important ones
				switch (action) {
				case WebConstants.ACTION_SAVE:
					setCreateAction();
					setEnableSpecificFields(true);
					break;
				case WebConstants.ACTION_UPDATE:
					setEditAction();
					break;
				case WebConstants.ACTION_DELETE_CONFIRMED:
					setDeleteAction();
					break;
				default:
					this.isCreateAction = true;
					this.isViewAction = true;
					this.isEditAction = true;
					this.isDeleteAction = true;
					this.isListAction = true;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public String getCurrentAction() {
		return currentAction;
	}

	public void setCurrentAction(String currentAction) {
		this.currentAction = currentAction;
	}

	public boolean isCreateAction() {
		return isCreateAction;
	}

	public void setCreateAction(boolean isCreateAction) {
		this.isCreateAction = isCreateAction;
		this.currentAction = WebConstants.ACTION_CREATE;
	}

	public boolean isViewAction() {
		return isViewAction;
	}

	public void setViewAction(boolean isViewAction) {
		this.isDisableAllFields = isViewAction;
		this.isViewAction = isViewAction;
		this.currentAction = WebConstants.ACTION_VIEW;
	}

	public boolean isEditAction() {
		return isEditAction;
	}

	public void setEditAction(boolean isEditAction) {
		this.isEditAction = isEditAction;
		this.currentAction = WebConstants.ACTION_EDIT;
	}

	public boolean isDeleteAction() {
		return isDeleteAction;
	}

	public void setDeleteAction(boolean isDeleteAction) {
		this.isDisableAllFields = isDeleteAction;
		this.isDeleteAction = isDeleteAction;
		this.currentAction = WebConstants.ACTION_DELETE;
	}

	public boolean isListAction() {
		return isListAction;
	}

	public void setListAction(boolean isListAction) {
		this.isListAction = isListAction;
		this.currentAction = WebConstants.ACTION_LIST;
	}

	public boolean isDisableAllFields() {
		return isDisableAllFields;
	}

	public void setDisableAllFields(boolean isDisableAllFields) {
		this.isDisableAllFields = isDisableAllFields;
	}

	public boolean isDisableSpecificFields() {
		return isDisableSpecificFields;
	}

	public void setDisableSpecificFields(boolean isDisableSpecificFields) {
		this.isDisableSpecificFields = isDisableSpecificFields;
	}

	public boolean isEnableSpecificFields() {
		return isEnableSpecificFields;
	}

	public void setEnableSpecificFields(boolean isEnableSpecificFields) {
		this.isEnableSpecificFields = isEnableSpecificFields;
	}

	public void reset() {

		this.isCreateAction = false;
		this.isViewAction = false;
		this.isEditAction = false;
		this.isDeleteAction = false;
		this.isListAction = false;
		this.isDisableAllFields = false;
		this.isDisableSpecificFields = false;
		setRowid("");
		currentAction = "";
	}

	public void addMessage(String message) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				message, message));
	}

	public void addError(String message) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
				message, message));
	}

	public void addWarning(String message) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
				message, message));
	}

	public String getAction() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		String action = request.getParameter("action");
		action = (action != null ? action.trim() : "");
		// System.out.println("getting action in bean support"+action);
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

}
