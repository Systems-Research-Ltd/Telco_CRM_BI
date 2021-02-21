package com.srpl.crm.web.model.um.admin.groups;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.bitguiders.util.jsf.JSFBeanInterface;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;
import com.srpl.crm.web.controller.BeanFactory;
import com.srpl.crm.ejb.request.UtilsDAO;
import com.srpl.crm.web.common.AjaxListStructure;
import com.srpl.crm.web.common.InnerTabs;
import com.srpl.crm.web.common.SessionDataBean;
import com.srpl.um.ejb.entity.UmGroup;
import com.srpl.um.ejb.entity.UmRole;
import com.srpl.um.ejb.request.GroupDAO;
import com.srpl.um.ejb.request.ServiceDAO;
import com.srpl.um.ejb.request.UserDAO;

@ManagedBean(name = "groupDetails")
@RequestScoped
public class GroupModuleDetailBackingBean extends JSFBeanSupport implements JSFBeanInterface, Serializable {

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

	public GroupModuleDetailBackingBean() {
		session = BeanFactory.getInstance().getSessionBean();
	}

	@PostConstruct
	public void postConstruct() {
		System.out.println("post construct");;
		if (getAction().equals("")) {
			// no action was called, load group data
			detailView();
			reset();
			setViewAction();
		}
	}
	
	@Override
	public void setViewAction() {
		super.setViewAction();
		setCancelAction(false);
	}

	private long group_id;
	private List<String> role_ids = new ArrayList<String>();
	private Long company_id;
	@NotBlank(message="Title is required.")
	@Pattern(regexp="^[a-zA-z0-9_ ]*$", message="Only Alphanumeric and underscores are allowed in Title.")
	private String group_title;
	private String group_details;
	private Boolean group_status = true;

	// Navigation and all
	private SessionDataBean session;
	private List<UmRole> userRoles = new ArrayList<UmRole>();

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

	private void changeTabPath(int index, String path) {
		InnerTabs d = session.getGroupTabs().get(index);
		d.setPath(path);
		session.getGroupTabs().set(index, d);
		try{
			if(getParameter("fromListing").equals("fromListing")){
				//don't update index
			}else{
				session.setGroupModule_tabIndex(0);
			}
		}catch(Exception e){
			session.setGroupModule_tabIndex(0);
		}
	}

	// TODO Action Listener
	@Override
	public String actionListener() {
		GroupModuleDetailBackingBean bean;
		UmGroup db;
		long group_id;
		setCurrentAction(getAction(),this.getClass());
		switch (getCurrentAction()) {
		case WebConstants.ACTION_CREATE:
			changeTabPath(0, "/view/um/admin/groups/groupForm.xhtml");
			bean = this;
			bean.resetBean();
			break;

		case WebConstants.ACTION_SAVE:
			bean = this;
			// TODO
			UmGroup sx = new UmGroup(session.getCompanyId(),
					bean.getGroup_details(), bean.getGroup_status(),
					bean.getGroup_title());
			try {
				bean.setGroup_id(groupDao.createGroup(sx, bean.getRole_ids()));
				this.addMessage("Group Successfully Created.");
			} catch (Exception e) {
				this.addError("Group Creation Failed.");
			}
			this.detailView();
			reset();
			setViewAction();
			break;

		case WebConstants.ACTION_VIEW:
			this.detailView();
			setViewAction();
			break;

		case WebConstants.ACTION_CANCEL:
			this.detailView();
			setViewAction();
			break;

		case WebConstants.ACTION_EDIT:
			group_id = Long.parseLong(this.getParameter("group_id"));
			this.loadGroup(group_id);
			this.changeTabPath(0, "/view/um/admin/groups/groupForm.xhtml");
			setEditAction();
			break;
			
		case WebConstants.ACTION_UPDATE:
			bean = this;
			db = groupDao.groupDetails(session.getGroupModule_selectedGroup());

			db.setCompanyId(session.getCompanyId());
			db.setGroupDetails(bean.getGroup_details());
			db.setGroupStatus(bean.getGroup_status());
			db.setGroupTitle(bean.getGroup_title());
			try {
				groupDao.updateGroup(db, bean.getRole_ids());
				this.addMessage("Group Successfully Updated.");
			} catch (Exception e) {
				this.addError("Group Update Failed.");
			}
			reset();
			this.detailView();
			break;

		case WebConstants.ACTION_DELETE:
			group_id = Long.parseLong(this.getParameter("group_id"));
			this.loadGroup(group_id);
			this.changeTabPath(0, "/view/um/admin/groups/groupForm.xhtml");
			setDeleteAction();
			break;

		case WebConstants.ACTION_DELETE_CONFIRMED:
			try {
				groupDao.deleteGroup(session.getGroupModule_selectedGroup());
				this.addMessage("Group Successfully Deleted.");
			} catch (Exception e) {
				this.addError("Group Deletion Failed.");
			}
			session.resetGroupModule();
			this.resetBean();
			break;
		}
		return null;
	}

	// Group Roles
	public List<String> getGroupRoles(Long grpId) {
		return groupDao.getGroupRoles(grpId);
	}

	// Load Group
	public void loadGroup(long id) {

		GroupModuleDetailBackingBean bean = this;
		bean.resetBean();
		try {
			UmGroup db = groupDao.groupDetails(id);

			bean.setGroup_id(db.getGroupId());
			bean.setCompany_id(db.getCompanyId());
			bean.setRole_ids(getGroupRoles(db.getGroupId()));
			bean.setGroup_title(db.getGroupTitle());
			bean.setGroup_status(db.getGroupStatus());
			bean.setGroup_details(db.getGroupDetails());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Users Page
	public void listUsersPage() {
		this.changeTabPath(1, "/view/um/admin/groups/users/usersList.xhtml");
	}

	// Group Operations Page
	/* no need
	public void listGroupOperationsPage() {
		this.changeTabPath(2,"/view/um/admin/groups/groupsOperations/operationsList.xhtml");
	}
	*/
	
	// Group Privilege
	public void listGroupPrivilegesPage() {
		this.changeTabPath(3,"/view/um/admin/groups/privileges/index.xhtml");
	}

	// Details Page
	public void detailView() {
		if (session.getGroupModule_selectedGroup() != 0L) {
			loadGroup(session.getGroupModule_selectedGroup());
			// Reset Other tabs too
			this.listUsersPage();
			//this.listGroupOperationsPage();
			this.listGroupPrivilegesPage();
			changeTabPath(0, "/view/um/admin/groups/groupForm.xhtml");
			setViewAction();
		} else {
			session.resetGroupModule();
		}
	}

	public List<UmRole> getUserRoles() {
		userRoles = utilsDao.userRoles();
		return userRoles;
	}

	@Override
	public List<AjaxListStructure> getList() {
		ArrayList<AjaxListStructure> myList = new ArrayList<AjaxListStructure>();
		AjaxListStructure e;
		SessionDataBean s1 = BeanFactory.getInstance().getSessionBean();
		List<UmGroup> groupDbList = groupDao.listGroups1(s1.getCompanyId());
		if(groupDbList.size() != myList.size()){
			for (UmGroup x : groupDbList) {
				e = new AjaxListStructure();
	
				e.setId(x.getGroupId());
				e.setLabel(x.getGroupTitle());
	
				myList.add(e);
			}
		}
		if(myList.size() == 0){
			// if No entries
			e = new AjaxListStructure();
			e.setId(0L);
			e.setLabel("No Group Found.");
			
			myList.add(e);
			changeTabPath(0, "/view/um/admin/groups/groupNoSelection.xhtml");
		}
		return myList;
	}
}
