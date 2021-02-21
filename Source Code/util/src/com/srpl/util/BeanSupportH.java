/**
 * 
 */
package com.srpl.util;


/**
 * @author Hammad Hassan Khan
 * 
 */
@Deprecated
public abstract class BeanSupportH extends com.bitguiders.util.jsf.JSFBeanSupport {

	private boolean isDisableSpecificFields;
	private boolean isViewAction;
	private boolean isEditAction;
	private boolean isDisableAllFields;

/*	private boolean isCreateAction;
	private boolean isDeleteAction;
	private boolean isListAction;
	

	private String rowid;
	private String currentAction;
	private boolean isSendAction;
	// create action for BI
	private boolean isCreateDSFAction;
*/
	public BeanSupportH() {
		System.out.println("here in " + getClass().getName() + "'s post construct");
		try {
			if (getAction() != "") {
				//isCreateAction = true;
				isViewAction = true;
				isEditAction = true;
//				isDeleteAction = true;
//				isListAction = true;
				//isDisableAllFields = true;
				//isDisableSpecificFields = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

/*	public String getCurrentAction() {
		return currentAction;
	}

	public boolean isCreateAction() {
		return isCreateAction;
	}

	public void setCreateAction(boolean isCreateAction) {
		isCreateAction = isCreateAction;
	}
*/
	public boolean isViewAction() {
		return isViewAction;
	}

	public void setViewAction(boolean isViewAction) {
		isDisableAllFields = isViewAction;
		isViewAction = isViewAction;
	}

/*	public boolean isEditAction() {
		return isEditAction;
	}

	public void setEditAction(boolean isEditAction) {
		isEditAction = isEditAction;
	}

	public boolean isDeleteAction() {
		return isDeleteAction;
	}

	public void setDeleteAction(boolean isDeleteAction) {
		isDisableAllFields = isDeleteAction;
		isDeleteAction = isDeleteAction;
	}

	public boolean isListAction() {
		return isListAction;
	}

	public void setListAction(boolean isListAction) {
		isListAction = isListAction;
	}
*/
	public boolean isDisableAllFields() {
		return isDisableAllFields;
	}

	public void setDisableAllFields(boolean isDisableAllFields) {
		isDisableAllFields = isDisableAllFields;
	}

	public boolean isDisableSpecificFields() {
		return isDisableSpecificFields;
	}

/*	public void setDisableSpecificFields(boolean isDisableSpecificFields) {
		isDisableSpecificFields = isDisableSpecificFields;
	}

	public void setCurrentAction(String currentAction) {
		currentAction = currentAction;
	}
*/
/*	public void reset() {

		isCreateAction = false;
		isViewAction = false;
		isEditAction = false;
		isDeleteAction = false;
		isListAction = false;
		isDisableAllFields = false;
		isDisableSpecificFields = false;
		setRowid("");
		currentAction = "";
	}
*/
/*	public void addMessage(String message) {
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
*/
/*	public String getAction() {
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
*/
/*	public String getRowid() {
		return rowid;
	}

	public void setRowid(String rowid) {
		rowid = rowid;
	}

	public boolean isSendAction() {
		return isSendAction;
	}

	public void setSendAction(boolean isSendAction) {
		isSendAction = isSendAction;
	}

	public boolean isCreateDSFAction() {
		return isCreateDSFAction;
	}

	public void setCreateDSFAction(boolean isCreateDSFAction) {
		isCreateDSFAction = isCreateDSFAction;
	}
*/
}
