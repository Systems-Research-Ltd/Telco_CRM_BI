package com.srpl.crm.web.model.um.admin.groups;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import com.bitguiders.util.jsf.JSFBeanSupport;
import com.srpl.crm.web.controller.BeanFactory;
import com.srpl.um.ejb.entity.GroupPermission;
import com.srpl.crm.ejb.request.AuthenticationDAO;
import com.srpl.um.ejb.request.GroupDAO;
import com.srpl.crm.ejb.request.UtilsDAO;
import com.srpl.crm.web.common.SessionDataBean;

@ManagedBean(name = "privilege")
@SessionScoped
public class PrivilegeBackingBean extends JSFBeanSupport implements
		Serializable {
	private static final long serialVersionUID = 1L;
	private long groupFeatureId;
	private String[] permissionsList;
	private List<String> permissionValues;
	private List<GroupPermission> featuresPermissionsList;
	private String[] selectedPermissions;
	private String userPermissions;
	private String[] operationsPopUps = {"one","two","three","four","five","six"};
	

	public String[] getOperationsPopUps() {
		return operationsPopUps;
	}

	public void setOperationsPopUps(String[] operationsPopUps) {
		this.operationsPopUps = operationsPopUps;
	}

	@EJB
	GroupDAO groupDao;
	@EJB
	AuthenticationDAO authDao;
	@EJB
	UtilsDAO utilsDao;

	@PostConstruct
	public void init() {
	}

	public String getUserPermissions() {
		Long groupId = BeanFactory.getInstance().getGroupModuleDetailBean()
				.getGroup_id();
		featuresPermissionsList = groupDao.groupPermissions(groupId);
		return userPermissions;
	}

	public void setUserPermissions(String userPermissions) {
		this.userPermissions = userPermissions;
	}

	public String[] getPermissionsList() {
		return permissionsList;
	}

	public List<String> getPermissionValues() {
		return permissionValues;
	}

	public List<GroupPermission> getFeaturesPermissionsList() {
		return featuresPermissionsList;
	}

	public void setFeaturesPermissionsList(
			List<GroupPermission> featuresPermissionsList) {
		this.featuresPermissionsList = featuresPermissionsList;
	}

	public String[] getSelectedPermissions() {
		return selectedPermissions;
	}

	public void setSelectedPermissions(String[] selectedPermissions) {
		this.selectedPermissions = selectedPermissions;
	}

	public long getGroupFeatureId() {
		return groupFeatureId;
	}

	public void setGroupFeatureId(long groupFeatureId) {
		this.groupFeatureId = groupFeatureId;
	}

	private long encodeNow() {
		for (String x : selectedPermissions) {
			System.out.println("HI " + x);
		}
		long permissionCode = 0;
		if (selectedPermissions != null) {
			for (String code : selectedPermissions) {
				if (code != "") {
					permissionCode += Long.parseLong(code);
				}
			}
		}
		return permissionCode;
	}

	public void listen(AjaxBehaviorEvent event) {
		groupFeatureId = (Long) event.getComponent().getAttributes().get("par");
		for (GroupPermission g : featuresPermissionsList) {
			if (g.getPermissionId() == groupFeatureId) {
				g.setPermissionCode(encodeNow());
				System.out.println(g.getPermissionCode());
			}
		}
	}

	public Boolean isCreate(int oprId) {
		return decodeNow(0, oprId);
		// return true;
	}

	public Boolean isRead(int oprId) {
		return decodeNow(1, oprId);
		// return true;
	}

	public Boolean isUpdate(int oprId) {
		return decodeNow(2, oprId);
		// return true;
	}

	public Boolean isDelete(int oprId) {
		return decodeNow(3, oprId);
		// return true;
	}

	public Boolean isSettings(int oprId) {
		return decodeNow(4, oprId);
		// return true;
	}

	public Boolean isAssign(int oprId) {
		return decodeNow(5, oprId);
		// return true;
	}

	public Boolean decodeNow(int pos, int oprId) {
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		String[] selectedPermissions = new String[6];
		selectedPermissions = utilsDao.modulePermissions(session.getUserId(),
				oprId);
		return (selectedPermissions[pos] != null) ? true : false;
	}

	public boolean showTab() {

		return true;
	}

	public String tabString() {
		return "leads";
	}

	public void applyChanges() {
		for (GroupPermission g : featuresPermissionsList) {
			groupDao.updatePermissions(g);
		}
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Privileges Set Successfully!", null));
	}

	String checkedString;

	public String getCheckedString() {
		return checkedString;
	}

	public void setCheckedString(String checkedString) {
		this.checkedString = checkedString;
	}

	public void selectedCheckBox(){
		int ver = Integer.parseInt(getParameter("vertical"));
		int hor = Integer.parseInt(getParameter("horizontal"));
		boolean checkBoxState = Boolean.parseBoolean(getParameter("item"));
		boolean currenCheckBoxState;
		if(checkBoxState==false){
			currenCheckBoxState = true;
		} else{
			currenCheckBoxState = false;
		}
		//print current value of checkbox
		System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= Orignal CheckBox state      >" + checkBoxState);
		System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= Current CheckBox state      >" + currenCheckBoxState);
		System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= vertical position   > " + hor);
		System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= horizontal position > " + ver );
		if(featuresPermissionsList!=null){
			GroupPermission groupPermission = featuresPermissionsList.get(ver);
			
			//permissionCode = get operaton code from   groupPermission
			long permissionCode = groupPermission.getPermissionCode();
			
			// operationCode = permission.getPermissionCode(operationCode, checkBoxIndex);
			permissionCode = getProfile().getPermission().getPermissionCode(Integer.parseInt(permissionCode+""), hor);
			System.out.println("--------------new permissionCode = "+permissionCode);
			// groupPermission set new operationCode
			groupPermission.setPermissionCode(permissionCode);
			
			System.out.println(groupPermission.getPermissionService().getServiceTitle());
			
			//save to permissions to db
		}
				
		
		
		
		
		
		
	}
}
