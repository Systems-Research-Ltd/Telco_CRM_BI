package com.srpl.crm.web.model.um.admin.users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import com.bitguiders.util.jsf.JSFBeanInterface;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;
import com.srpl.crm.web.common.InnerTabs;
import com.srpl.crm.web.model.um.admin.groups.GroupModuleDetailBackingBean;
import com.srpl.um.ejb.entity.UmGroup;
import com.srpl.um.ejb.request.CompanyDAO;
import com.srpl.um.ejb.request.FranchiseDAO;
import com.srpl.um.ejb.request.GroupDAO;
import com.srpl.um.ejb.request.UserDAO;
import com.srpl.um.ejb.request.UserHistoryDAO;
import com.srpl.um.web.common.SessionDataBean;
import com.srpl.um.web.controller.BeanFactory;

@ManagedBean(name="userGroups")
public class UserModuleGroupsBackingBean extends JSFBeanSupport implements JSFBeanInterface, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB UserDAO userDao;
	@EJB FranchiseDAO franchiseDao;
	@EJB GroupDAO groupDao;
	@EJB CompanyDAO companyDao;
	@EJB UserHistoryDAO userHistoryDao;
	
	public UserModuleGroupsBackingBean() {
		session = BeanFactory.getInstance().getSessionBean();
	}
	@PostConstruct
	public void postConstruct() {
		if (getAction().equals("")) {
			// no action was called, load group data
			if(session.getUserModule_selectedUser() != 0L){
				listGroupsPage();
				setViewAction();
			}
		}
	}

	private void listGroupsPage() {
		this.changeTabPath(1, "/view/um/admin/user/groups/userGroupList.xhtml");
		setListAction(true);
	}
	private void changeTabPath(int index, String path) {
		InnerTabs d = session.getUserTabs().get(index);
		d.setPath(path);
		session.getUserTabs().set(index, d);
		session.setUserModule_tabIndex(1);
	}
	
	private SessionDataBean session;
	private long selectedGroup;

	public long getSelectedGroup() {
		return selectedGroup;
	}
	public void setSelectedGroup(long selectedGroup) {
		this.selectedGroup = selectedGroup;
	}

	@Override
	public String actionListener() {
		setCurrentAction(getAction(),this.getClass());
		switch(getCurrentAction()){
		case WebConstants.ACTION_CREATE:
			//Goto Create Page
			this.changeTabPath(1, "/view/um/admin/user/groups/addGroup.xhtml");
			setCreateAction();
			break;
		case WebConstants.ACTION_SAVE:
			long gId = getSelectedGroup();
			try {
				userDao.addUserToGroup(session.getUserModule_selectedUser(), gId);
				this.addMessage(getProperty("user.successfully.added"));

			} catch (Exception e) {
				this.addError(getProperty("user.addition.to.group.fail"));
			}
			this.changeTabPath(1, "/view/um/admin/user/groups/userGroupList.xhtml");
			break;
		case WebConstants.ACTION_DELETE_CONFIRMED:
			// System.out.println("removeUserGroupsFromUser()  called");
			Long groupId;
			try {
				groupId = Long.parseLong(this.getParameter("row_id"));
				try {
					userDao.removeUserFromGroup(session.getUserModule_selectedUser(), groupId);
					this.addMessage(getProperty("user.successfully.removed"));
				} catch (Exception e) {
					this.addError(getProperty("could.not.remove.user"));
				}
			} catch (Exception e) {
				System.out.println("can't receive the id.");
			}
			this.changeTabPath(1, "/view/um/admin/user/groups/userGroupList.xhtml");
			break;
		case WebConstants.ACTION_LIST:
			listGroupsPage();
			break;
		case WebConstants.ACTION_CANCEL:
			listGroupsPage();
			break;
		}
		return null;
	}
	
	// according to new logic
	@Override
	public List<GroupModuleDetailBackingBean> getList() {
		// System.out.println("here in Users Group");
		return listUsersGroup(true);
	}

	public List<GroupModuleDetailBackingBean> getNoUsersInGroup() {
		// System.out.println("here in No users in group");
		return listUsersGroup(false);
	}

	public List<GroupModuleDetailBackingBean> listUsersGroup(Boolean include) {
		List<GroupModuleDetailBackingBean> myList = new ArrayList<GroupModuleDetailBackingBean>();
		List<UmGroup> usersGroupDbList = userDao.listUserGroups(session.getUserModule_selectedUser(),
				include, session.getCompanyId());

		GroupModuleDetailBackingBean ug;
		// System.out.println("User exists in groups :" +
		// usersGroupDbList.size());
		for (UmGroup x : usersGroupDbList) {
			// System.out.println("group title :" + x.getGroupTitle());
			ug = new GroupModuleDetailBackingBean();
			ug.setCompany_id(x.getCompanyId());
			ug.setGroup_details(x.getGroupDetails());
			ug.setGroup_id(x.getGroupId());
			ug.setGroup_status(x.getGroupStatus());
			ug.setGroup_title(x.getGroupTitle());
			myList.add(ug);
		}
		// System.out.println(myList.size());
		return myList;
	}

}
