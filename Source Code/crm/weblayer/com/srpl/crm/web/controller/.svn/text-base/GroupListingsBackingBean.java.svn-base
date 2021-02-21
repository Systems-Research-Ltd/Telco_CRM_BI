package com.srpl.crm.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;
import com.srpl.crm.web.controller.BeanFactory;
import com.srpl.crm.ejb.request.UtilsDAO;
import com.srpl.crm.web.common.AjaxListStructure;
import com.srpl.crm.web.common.InnerTabs;
import com.srpl.crm.web.common.SessionDataBean;
import com.srpl.crm.web.model.um.admin.groups.GroupBackingBean;

import com.srpl.um.ejb.entity.UmGroup;
import com.srpl.um.ejb.entity.UmRole;
import com.srpl.um.ejb.entity.UmService;
import com.srpl.um.ejb.request.GroupDAO;
import com.srpl.um.ejb.request.ServiceDAO;
import com.srpl.um.ejb.request.UserDAO;

@ManagedBean(name = "groupListings")
@RequestScoped
@Deprecated
public class GroupListingsBackingBean extends JSFBeanSupport implements Serializable {

	/**
	 * 
	 */
	@EJB GroupDAO groupDao;
	@EJB UserDAO usersDao;
	@EJB ServiceDAO serviceDao;
	@EJB UtilsDAO utilsDao;
	
	private static final long serialVersionUID = 1L;

	private Long selected;
	private Long selectedUser;
	private ArrayList<InnerTabs> tabs;
	private Integer tabIndex = 0;
	private List<UmRole> userRoles = new ArrayList<UmRole>();
	
	public GroupListingsBackingBean(){
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		tabs = session.getGroupTabs();
		selected = session.getGroupModule_selectedGroup();
		selectedUser = session.getGroupModule_selectedUser();
	}

	public long getSelected() {
		return selected;
	}

	public void setSelected(long selected) {
		this.selected = selected;
	}

	public int getTabIndex() {
		return tabIndex;
	}

	public void setTabIndex(int tabIndex) {
		this.tabIndex = tabIndex;
	}

	public long getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(long selectedUser) {
		this.selectedUser = selectedUser;
	}

	@Deprecated
	public ArrayList<AjaxListStructure> getGroups() {
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

	public void loadGroup(long id) {

		GroupBackingBean g = BeanFactory.getInstance().getGroupBean();
		g.reset();
		try{
		UmGroup s = groupDao.groupDetails(id);
		
		g.setGroup_id(s.getGroupId());
		g.setCompany_id(s.getCompanyId());
		g.setRole_ids(getGroupRoles(s.getGroupId()));
		g.setGroup_title(s.getGroupTitle());
		g.setGroup_status(s.getGroupStatus());
		g.setGroup_details(s.getGroupDetails());
		
		setViewAction();
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(g.getGroup_id() == 0L){
				// if No entries
				g = new GroupBackingBean();
				g.setGroup_id(0L);
				g.setGroup_title("No Group Found.");
				
				changeTabPath(0, "/view/um/admin/groups/groupNoSelection.xhtml");
			}
		}
	}
	
	public List<String> getGroupRoles(Long grpId){
		return groupDao.getGroupRoles(grpId);
	}

	public void changeTabPath(int index, String path) {
		InnerTabs d = tabs.get(index);
		d.setPath(path);
		tabs.set(index, d);
	}
	
	public void actionListener(){
		GroupBackingBean g;
		UmGroup s;
		SessionDataBean s1 = BeanFactory.getInstance().getSessionBean();
		long group_id;
		reset();
		
		switch(getAction()){
		case WebConstants.ACTION_CREATE:
			//TOOD
			break;
			
		case WebConstants.ACTION_SAVE:
			//TODO
			setViewAction();
			break;
			
		case WebConstants.ACTION_VIEW:
			//TODO
			setViewAction();
			break;
			
		case WebConstants.ACTION_CANCEL:
			//TODO
			setViewAction();
			break;
			
		case WebConstants.ACTION_EDIT:
			//TODO
			setEditAction();
			break;
		case WebConstants.ACTION_UPDATE:
			//TODO
			setViewAction();
			break;
			
		case WebConstants.ACTION_DELETE:
			//TODO
			setDeleteAction();
			break;
			
		case WebConstants.ACTION_DELETE_CONFIRMED:
			//TODO
			setViewAction();
			break;
		}
	}

	/*
	 * no need
	 */
	// Details Page
	/*
	@Deprecated
	public void detailView() {

		loadGroup(this.getSelected());

		//Reset Other tabs too
		this.listUsersPage();
		this.listGroupOperationsPage();
		
		InnerTabs d = tabs.get(0);
		d.setPath("/view/um/admin/groups/groupForm.xhtml");
		tabs.set(0, d);
		this.setTabIndex(0);
		
		setViewAction();

	}
	*/
	/*
	*/
	
	/*
	 * no need
	 *
	public void createForm() {
		System.out.println("here im in create form");
		InnerTabs d = tabs.get(0);
		d.setPath("/view/um/admin/groups/groupCreate.xhtml");
		tabs.set(0, d);

		GroupBackingBean g = BeanFactory.getInstance().getGroupBean();

		g.setGroup_id(0);
		g.setCompany_id(0L);
		g.setRole_ids(null);
		g.setGroup_title("");
		g.setGroup_details("");
		g.setGroup_status(true);
	}
	*
	*/

	/*
	 * no need
	 *
	public String createGroup() {

		// call he ejb here. or set pojo as discussed
		System.out.println("here in create group.");
		String retVal;

		GroupBackingBean g = BeanFactory.getInstance().getGroupBean();
		SessionDataBean s1 = BeanFactory.getInstance().getSessionBean();
		UmGroupDetails s = new UmGroupDetails(s1.getCompanyId(),
				g.getGroup_details(), g.getGroup_status(), g.getGroup_title());

		try{
			g.setGroup_id(groupDao.createGroup(s,g.getRole_ids()));
			this.addMessage("Group Successfully Created.");
			retVal = "success";
		}
		catch(Exception e){
			this.addError("Group Creation Failed.");
			retVal = "failure";
		}

		this.detailView();
		return retVal;
	}
	*
	*/

	/*
	 * no need
	 *
	public void editForm() {
		System.out.println("here im in edit form");
		long group_id = Long.parseLong(this.getParameter("group_id"));
		this.loadGroup(group_id);
		this.changeTabPath(0, "/view/um/admin/groups/groupEdit.xhtml");
	}
	*
	*/

	/*
	 * no need
	 *
	public void editGroup() {
		// call the ejb here
		System.out.println("here in edit group.");
		System.out.println(this.getSelected());
		
		GroupBackingBean g = BeanFactory.getInstance().getGroupBean();
		SessionDataBean s1 = BeanFactory.getInstance().getSessionBean();
		UmGroup s = groupDao.groupDetails(this.getSelected());
//		UmGroupDetails s = new UmGroupDetails(g.getCompany_id(),
//				g.getGroup_details(), g.getGroup_status(), g.getGroup_title(),
//				1);
		
		s.setCompanyId(s1.getCompanyId());
		s.setGroupDetails(g.getGroup_details());
		s.setGroupStatus(g.getGroup_status());
		s.setGroupTitle(g.getGroup_title());
		
		try{
			groupDao.updateGroup(s,g.getRole_ids());
			System.out.println(s.getGroupTitle());
			this.addMessage("Group Successfully Updated.");
		}
		catch(Exception e){
			this.addError("Group Update Failed.");
		}
		
		this.detailView();
	}
	*
	*/


	/*
	 * no need
	 *
	public void deleteForm() {
		System.out.println("here im in delete group form");
		long group_id = Long.parseLong(this.getParameter("group_id"));
		this.loadGroup(group_id);
		this.changeTabPath(0, "/view/um/admin/groups/groupDelete.xhtml");
	}
	*
	*/

	/*
	 * no need
	 *
	public void deleteGroup() {
		// call the ejb here
		System.out.println("here in delete group.");
		System.out.println(this.getSelected());
		
		try{
			groupDao.deleteGroup(this.getSelected());
			this.addMessage("Group Successfully Deleted.");
		}
		catch(Exception e){
			this.addError("Group Deletion Failed.");
		}
		this.setSelected(0);
		this.detailView();
	}
	*
	*/

	// Users Page
	/*
	@Deprecated
	public void listUsersPage() {
		System.out.println("here in list users page");
		this.changeTabPath(1, "/view/um/admin/groups/users/usersList.xhtml");
		this.setTabIndex(1);
	}
	*/
	
	/*
	@Deprecated
	public List<CreateUserBean> getUsersInGroup() {
		System.out.println("here in Users In Group");
		return listGroupUsers(true);
	}
	
	@Deprecated
	public List<CreateUserBean> getUsersNotInGroup() {
		System.out.println("here in Users In Group");
		return listGroupUsers(false);
	}
	
	@Deprecated
	public List<CreateUserBean> listGroupUsers(Boolean include) {

		SessionDataBean s1 = BeanFactory.getInstance().getSessionBean();
		List<CreateUserBean> myList = new ArrayList<CreateUserBean>();
		List<UmUser> usersDbList = usersDao.getGroupUsers2(selected, include, s1.getCompanyId());

		CreateUserBean uO;
		
		for(UmUser x:usersDbList){
			uO = new CreateUserBean();

			uO.setUserFname(x.getUserFname());
			uO.setUserLname(x.getUserLname());
			uO.setUserId(x.getUserId());
			uO.setUserEmail(x.getUserEmail());
			uO.setUserAddress(x.getUserAddress());
			myList.add(uO);
		}
		System.out.println(myList.size());
		return myList;
	}
	*/
	
	/*
	public void removeUserFromGroup(){
		Long id;
		String val;
		try{
			val = this.getParameter("row_id");
			id = Long.valueOf(val);
			try{
				usersDao.removeUserFromGroup(id, selected);
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
	*/
	
	/*
	public List<CreateUserBean> getUsersList() {
		System.out.println("here in userslist from grouplistings");
		List<CreateUserBean> myList = new ArrayList<CreateUserBean>();
		List<UmUserDetails> userDbList;

			userDbList = usersDao.listUsers();
			CreateUserBean u;
			
			for(UmUserDetails x:userDbList){
				u = new CreateUserBean();
				
				u.setUserId(x.getUserId());
				u.setUserName(x.getUserName());
				u.setUserPassword(x.getUserPassword());
				u.setUserFname(x.getUserFname());
				u.setUserLname(x.getUserLname());
				u.setUserAddress(x.getUserAddress());
				u.setUserCountry(x.getUserCountry());
				u.setUserState(x.getUserState());
				u.setUserCity(x.getUserCity());
				u.setUserZipcode(x.getUserZipcode());
				u.setUserEmail(x.getUserEmail());
				u.setUserPhone(x.getUserPhone());
				u.setUserJobtitle(x.getUserJobtitle());
				u.setUserPicture(x.getUserPicture());
				u.setIsFranchiseUser(x.getIsFranchiseUser());
				u.setUserCompany(x.getUserCompany());
				u.setUserAddedon(x.getUserAddedon());
				u.setUserStatus(x.getUserStatus());
				u.setUserReportsto(x.getUserReportsto());
				
				myList.add(u);
			}
		
		System.out.println(myList.size());
		return myList;
	}
	*/

	/*
	@Deprecated
	public void addUsersFormPage() {
		System.out.println("here in add users in groups form page");
		this.changeTabPath(1, "/view/um/admin/groups/users/addUser.xhtml");
		this.setTabIndex(1);
	}

	@Deprecated
	public void addUsersAction() {
		System.out.println("here in add users actions");

		System.out.println("Group :" + getSelected());
		System.out.println("User :" + getSelectedUser());
		
		long gId = getSelected();
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
	*/


	// Group Operations Page
	public void listGroupOperationsPage() {
		System.out.println("here in list GroupOperations.");
		this.changeTabPath(2, "/view/um/admin/groups/groupsOperations/operationsList.xhtml");
		this.setTabIndex(2);
	}
	//public List<ManageServicesBackingBean> getGroupOperationsList() {
	/*	System.out.println("here in grouplistings");
		
		List<ManageServicesBackingBean> myList = new ArrayList<ManageServicesBackingBean>();
		List<UmService> groupOperationsDbList = serviceDao.listServices();
		

		ManageServicesBackingBean gO;
		
		for(UmService x:groupOperationsDbList){
			gO = new ManageServicesBackingBean();
			
			gO.setOperationId(x.getServiceId());
			gO.setTitle(x.getServiceTitle());
			gO.setDescription(x.getServiceDescription());
			myList.add(gO);
		}
		System.out.println(myList.size());
	
		
		return myList;
	}
*/
	/*
	public void addGroupOperationsFormPage() {
		System.out.println("here in add GroupOperations in groups form page");
		
		GroupOperationBackingBean gO = BeanFactory.getInstance().getGroupOperationBean();
		gO.setOperationId(0);
		gO.setDescription("");
		gO.setParam_operationId(0);
		gO.setTitle("");
		
		this.changeTabPath(2, "/view/um/admin/groups/groupsOperations/operationsCreate.xhtml");
		this.setTabIndex(2);
	}

	public void addGroupOperationsAction() {

		GroupOperationBackingBean gO = BeanFactory.getInstance().getGroupOperationBean();
		
		System.out.println("here in add users actions");
		this.changeTabPath(2, "/view/um/admin/groups/groupsOperations/operationsList.xhtml");
		this.setTabIndex(2);
		
		System.out.println("createOperation called");
		UmOperation operationDetails = new UmOperation(gO.getOperationId(),gO.getTitle(),gO.getDescription());
		try{
			gO.setOperationId(operationDao.createOperation(operationDetails));
			this.addMessage("Group Operation Successfully Created.");
			gO.setOperationId(0);
			gO.setDescription("");
			gO.setTitle("");
			gO.setParam_operationId(0);
			/*Integer operationId = operationDao.createOperation(operationDetails);
			operationDetails.setOperationId(operationId);
			this.addMessage("Group Operation Successfully Created.");*-/
		}
		catch(Exception e){
			this.addError("Group Operation Creation Failed.");
		}
		this.listGroupOperationsPage();
	}
	
	public void editGroupOperationsFormPage(){
		System.out.println("here in edit GroupOperations in groups form page.");
		this.changeTabPath(2, "/view/um/admin/groups/groupsOperations/operationsEdit.xhtml");
		this.setTabIndex(2);
		int param_operationId = Integer.valueOf(this.getParameter("row_id"));
		GroupOperationBackingBean gO = BeanFactory.getInstance().getGroupOperationBean();
		UmOperation db =operationDao.operationDetails(param_operationId);
		gO.setOperationId(db.getOperationId());
		gO.setDescription(db.getOperationDescription());
		gO.setTitle(db.getOperationTitle());
		this.setTabIndex(2);
		
		/*System.out.println("here in edit GroupOperations in groups form page.");
		this.changeTabPath(2, "/view/um/admin/groups/groupsOperations/operationsEdit.xhtml");
		this.setTabIndex(2);
		int param_operationId = Integer.valueOf(this.getParameter("row_id"));
		System.out.println("param id" +param_operationId);
		GroupOperationBackingBean gO = BeanFactory.getInstance().getGroupOperationBean();
		UmOperation db = operationDao.operationDetails(param_operationId);
	//	UmOperationDetails db=operationDao.operationDetails(param_operationId );

		gO.setOperationId(db.getOperationId());
		gO.setTitle(db.getOperationTitle());
		gO.setDescription(db.getOperationDescription());
		
		this.setTabIndex(2);*-/
	}
	
	public void editGroupOperationsAction(){

		System.out.println("here in edit GroupOperations in groups form Action.");
		
		GroupOperationBackingBean gO = BeanFactory.getInstance().getGroupOperationBean();
		try{
			UmOperation db = operationDao.operationDetails(gO.getOperationId());
		//	UmOperationDetails db=operationDao.operationDetails(gO.getOperationId());
			System.out.println("operationid" +gO.getOperationId());
			db.setOperationId(gO.getOperationId());
			db.setOperationTitle(gO.getTitle());
			db.setOperationDescription(gO.getDescription());
			
			try{
				operationDao.updateOperation(db);
				this.addMessage("Group Operation Successfully Updated.");
			}
			catch(Exception e){
				this.addError("Group Operation Updation Failed.");
			}
		}
		catch(Exception e){
			System.out.println("unable to accedd db.");
		}
		//this.listGroupOperationsPage();
		this.changeTabPath(2, "/view/um/admin/groups/groupsOperations/operationsList.xhtml");
		this.setTabIndex(2);
	}

	public void deleteGroupOperationsFormPage(){
		System.out.println("here in delete GroupOperations in groups form page.");
		this.changeTabPath(2, "/view/um/admin/groups/groupsOperations/operationsDelete.xhtml");
		this.setTabIndex(2);
		
		int param_operationId = Integer.valueOf(this.getParameter("row_id"));

		GroupOperationBackingBean gO = BeanFactory.getInstance().getGroupOperationBean();
		UmOperation db = operationDao.operationDetails(param_operationId);
	//	UmOperationDetails db=operationDao.operationDetails(param_operationId );

		gO.setOperationId(db.getOperationId());
		gO.setTitle(db.getOperationTitle());
		gO.setDescription(db.getOperationDescription());
	}
	
	public void deleteGroupOperationsAction(){

		System.out.println("here in delete GroupOperations in groups form Action.");
		this.changeTabPath(2, "/view/um/admin/groups/groupsOperations/operationsList.xhtml");
		this.setTabIndex(2);
		
		GroupOperationBackingBean gO = BeanFactory.getInstance().getGroupOperationBean();
		
		try{
			operationDao.deleteOperation(gO.getOperationId());
			System.out.println("delete group id" + gO.getOperationId());
			this.addMessage("Group Operation Successfully Deleted.");
		}
		catch(Exception e){
			this.addError("Group Operation Deletion Failed.");
		}
		//this.listGroupOperationsPage();
	}
	
	/*
	@Deprecated
	public List<UmRole> getUserRoles(){
		userRoles = utilsDao.userRoles();
		return userRoles;
	}
	*/

}
