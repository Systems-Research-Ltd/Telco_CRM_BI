package com.srpl.crm.web.model.um.admin.groups;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;
import com.srpl.crm.web.controller.BeanFactory;
import com.srpl.crm.ejb.request.UtilsDAO;
import com.srpl.crm.web.common.InnerTabs;
import com.srpl.crm.web.common.SessionDataBean;
import com.srpl.um.ejb.entity.UmUser;
import com.srpl.um.ejb.request.GroupDAO;
import com.srpl.um.ejb.request.ServiceDAO;
import com.srpl.um.ejb.request.UserDAO;

@ManagedBean(name = "groupUsers")
@RequestScoped
public class GroupModuleUserBackingBean extends JSFBeanSupport implements
		Serializable {

	/**
	 * 
	 */
	@EJB
	GroupDAO groupDao;
	@EJB
	UserDAO usersDao;
	@EJB
	ServiceDAO serviceDao;
	@EJB
	UtilsDAO utilsDao;
	private static final long serialVersionUID = 1L;

	public GroupModuleUserBackingBean() {
		session = BeanFactory.getInstance().getSessionBean();
		detailsTab = session.getGroupTabs().get(0);
		selectedGroup = session.getGroupModule_selectedGroup();
		tabIndex = session.getGroupModule_tabIndex();
	}

	@PostConstruct
	public void postConstruct() {
		if (getAction().equals("")) {
			// no action was called, load group data
			if(selectedGroup != 0L){
				listUsersPage();
				setViewAction();
			}
		}
	}

	private long group_id;
	private List<String> role_ids = new ArrayList<String>();
	private Long company_id;
	private String group_title;
	private String group_details;
	private Boolean group_status = true;

	// Navigation and all
	private InnerTabs detailsTab;
	private Long selectedGroup;
	private long selectedUser;
	private Integer tabIndex;
	private SessionDataBean session;

	public long getGroup_id() {
		return group_id;
	}

	public void setGroup_id(long group_id) {
		this.group_id = group_id;
	}

	public List<String> getRole_ids() {
		return role_ids;
	}

	public void setRole_ids(List<String> role_ids) {
		this.role_ids = role_ids;
	}

	public Long getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}

	public String getGroup_title() {
		return group_title;
	}

	public void setGroup_title(String group_title) {
		this.group_title = group_title;
	}

	public String getGroup_details() {
		return group_details;
	}

	public void setGroup_details(String group_details) {
		this.group_details = group_details;
	}

	public Boolean getGroup_status() {
		return group_status;
	}

	public void setGroup_status(Boolean group_status) {
		this.group_status = group_status;
	}

	public void resetBean() {
		setGroup_id(0);
		setCompany_id(0L);
		setRole_ids(null);
		setGroup_title("");
		setGroup_details("");
		setGroup_status(true);
	}

	// Navigation Related

	public InnerTabs getDetailsTab() {
		return detailsTab;
	}

	public void setDetailsTab(InnerTabs detailsTab) {
		this.detailsTab = detailsTab;
	}

	public Long getSelectedGroup() {
		return selectedGroup;
	}

	public void setSelectedGroup(Long selectedGroup) {
		this.selectedGroup = selectedGroup;
	}

	public long getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(long selectedUser) {
		this.selectedUser = selectedUser;
	}

	public Integer getTabIndex() {
		return tabIndex;
	}

	public void setTabIndex(Integer tabIndex) {
		this.tabIndex = tabIndex;
	}

	private void changeTabPath(int index, String path) {
		InnerTabs d = session.getGroupTabs().get(index);
		d.setPath(path);
		session.getGroupTabs().set(index, d);
		session.setGroupModule_tabIndex(1);
	}

	// TODO Action Listener
	public void actionListener() {
		reset();

		switch (getAction()) {
		case WebConstants.ACTION_CREATE:
			addUsersFormPage();
			setCreateAction();
			break;

		case WebConstants.ACTION_SAVE:
			addUsersAction();
			setViewAction();
			break;

		case WebConstants.ACTION_VIEW:
			listUsersPage();
			setViewAction();
			break;

		case WebConstants.ACTION_CANCEL:
			break;

		case WebConstants.ACTION_EDIT:
			setEditAction();
			break;
			
		case WebConstants.ACTION_UPDATE:
			setViewAction();
			break;

		case WebConstants.ACTION_DELETE:
			setDeleteAction();
			break;

		case WebConstants.ACTION_DELETE_CONFIRMED:
			removeUserFromGroup();
			setViewAction();
			listUsersPage();
			GroupModuleDetailBackingBean groupDetailsBean = BeanFactory.getInstance().getGroupModuleDetailBean();
			groupDetailsBean.postConstruct();
			break;
		}
	}

	// Users Page
	public void listUsersPage() {
		this.changeTabPath(1, "/view/um/admin/groups/users/usersList.xhtml");
	}

	// Group Operations Page
	public void listGroupOperationsPage() {
		// this.changeTabPath(2,
		// "/view/um/admin/groups/groupsOperations/operationsList.xhtml");
	}

	// Group Users Page
	public List<UmUser> getUsersInGroup() {
		System.out.println("here in Users In Group");
		return listGroupUsers(true);
	}
	
	public List<UmUser> getUsersNotInGroup() {
		System.out.println("here in Users In Group");
		return listGroupUsers(false);
	}
	
	public List<UmUser> listGroupUsers(Boolean include) {

		List<UmUser> usersDbList = usersDao.getGroupUsers2(selectedGroup, include, session.getCompanyId());

		return usersDbList;
	}
	
	public void removeUserFromGroup(){
		Long id;
		String val;
		try{
			val = this.getParameter("row_id");
			id = Long.valueOf(val);
			try{
				usersDao.removeUserFromGroup(id, selectedGroup);
				this.addMessage("User Successfully Removed from the Group.");
			}
			catch(Exception e){
				System.out.println("couldn't remove.");
				this.addError("Couldn't Remove User from the Group.");
			}
		}
		catch(Exception e){
			System.out.println("can't receive the id.");
		}
	}

	public void addUsersFormPage() {
		this.changeTabPath(1, "/view/um/admin/groups/users/addUser.xhtml");
	}

	public void addUsersAction() {
		
		long gId = getSelectedGroup();
		long uId = getSelectedUser();
		
		try{
			usersDao.addUserToGroup(uId, gId);
			this.addMessage("User Successfully Added to Group.");
		}
		catch(Exception e){
			this.addError("User Addition to Group Failed.");
		}
		this.listUsersPage();
	}
	
}
