package com.srpl.crm.web.model.um.admin.users;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.ToggleEvent;

import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;
import com.srpl.crm.web.controller.BeanFactory;
import com.srpl.crm.common.utils.UmFranchiseDetails;
import com.srpl.crm.common.utils.UmGroupDetails;
import com.srpl.um.ejb.entity.UmUserHistory;
import com.srpl.um.ejb.exceptions.UserNotFoundException;
import com.srpl.um.ejb.request.CompanyDAO;
import com.srpl.um.ejb.request.FranchiseDAO;
import com.srpl.um.ejb.request.GroupDAO;
import com.srpl.crm.ejb.request.LocationDAO;
import com.srpl.um.ejb.request.UserHistoryDAO;
import com.srpl.crm.web.common.InnerTabs;
import com.srpl.crm.web.common.SessionDataBean;
import com.srpl.crm.web.model.FranchiseBackingBean;
import com.srpl.crm.web.model.NewUserBackingBean;
import com.srpl.crm.web.model.common.Address;
import com.srpl.crm.web.model.user.HistoryBackingBean;
import com.srpl.um.ejb.entity.UmFranchise;
import com.srpl.um.ejb.entity.UmGroup;
import com.srpl.um.ejb.entity.UmUser;
import com.srpl.um.ejb.request.UserDAO;

@ManagedBean(name = "userListings")
@RequestScoped
@Deprecated
public class UsersListingBackingBean extends JSFBeanSupport implements
		Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long selected;
	private static long selectedGroup;
	private ArrayList<InnerTabs> tabs = new ArrayList<InnerTabs>();
	private int tabIndex = 0;
	public String searchById;
	// @Pattern(message="Only digits are allowed",regexp="^(?:[0-9]{5}|[0-9]{10}|)$")
	private String searchByName;
	// @Pattern(message="Only alphabets are allowed",regexp="[a-zA-Z]")
	private String searchByJob;
	private String searchByReportsTo;
	private String searchByUserGroup;
	private String searchByUserStatus;
	private String searchKeyword;
	private String searchColumns;
	private Boolean show;
	private String filterBy = "";
	private String filterFor = "";	
	// private Boolean searchByUserStatus;

	@EJB UserDAO userDao;
	@EJB FranchiseDAO franchiseDao;
	@EJB GroupDAO groupDao;
	@EJB LocationDAO locationDao;
	@EJB CompanyDAO companyDao;
	@EJB UserHistoryDAO userHistoryDao;

	/*
	static {
		tabs.add(new InnerTabs("Details", "/view/um/admin/user/userNoSelection.xhtml",
				"detailsContainer"));
		tabs.add(new InnerTabs("Groups",
				"/view/um/admin/user/groups/userGroupList.xhtml",
				"groupsContainer"));
		tabs.add(new InnerTabs("User History",
				"/view/um/admin/user/usersHistory.xhtml",
				"usersHistoryContainer"));
	}
	*/
	
	@PostConstruct
	public void postConstruct(){
		try{
			switch(getAction()){
			case WebConstants.ACTION_CREATE:
				reset();
				changeTabPath(0, "/view/um/admin/user/UserForm.xhtml");
				setCreateAction();
//				setEnableSpecificFields();
				break;
			case WebConstants.ACTION_VIEW:
			case WebConstants.ACTION_EDIT:
			case WebConstants.ACTION_UPDATE:
			case WebConstants.ACTION_DELETE:
				reset();
				changeTabPath(0, "/view/um/admin/user/UserForm.xhtml");
				setCurrentAction(getAction(),this.getClass());
				break;
			case WebConstants.ACTION_SAVE:
				reset();
				setCreateAction();
				//setEnableSpecificFields();
				changeTabPath(0, "/view/um/admin/user/UserForm.xhtml");
				setCurrentAction(getAction(),this.getClass());
				break;
			case WebConstants.ACTION_CANCEL:
				reset();
				changeTabPath(0, "/view/um/admin/user/UserForm.xhtml");
				changeTabPath(1, "/view/um/admin/user/groups/addGroup.xhtml");
				setCurrentAction(getAction(),this.getClass());
				break;
			case WebConstants.ACTION_ADD_USER_TO_GROUP:
			case WebConstants.ACTION_REMOVE_USER_FROM_GROUP:
			case WebConstants.ACTION_REMOVE_USER_FROM_GROUP_CONFIRMED:
				reset();
				changeTabPath(1, "/view/um/admin/user/groups/userGroupList.xhtml");
				break;
			case WebConstants.ACTION_ADD_USER_TO_GROUP_CONFIRMED:
				reset();
				changeTabPath(1, "/view/um/admin/user/groups/addGroup.xhtml");
				break;
			default:
				changeTabPath(0, "/view/um/admin/user/userNoSelection.xhtml");
			}
			System.out.println("here in " + this.getClass().getName() +"'s post construct.");
			System.out.println("ther value of getAction is: " + getAction());
		}
		catch(Exception e){
			System.out.println("exception on old_action.");
		}
	}

	public ArrayList<InnerTabs> getTabs() {
		return tabs;
	}

	public String getTabss() {
		StringBuilder tempString = new StringBuilder();
		for(InnerTabs t:tabs){
			tempString.append("!~" + t.toString());
		}
		return tempString.toString();
	}
	
	public void setTabss(String tabsData){
		String parts[] = tabsData.split("!~");
		tabs = new ArrayList<InnerTabs>();
		for(String p:parts){
			InnerTabs tempTab = new InnerTabs();
			tempTab.setInnerTab(p);
			tabs.add(tempTab);
		}
	}

	public Long getSelected() {
		return selected;
	}

	public void setSelected(Long selected) {
		this.selected = selected;
	}

	public int getTabIndex() {
		return tabIndex;
	}

	public void setTabIndex(int tabIndex) {
		this.tabIndex = tabIndex;
	}

	public UsersListingBackingBean() {
		// CreateUserBean u = BeanFactory.getInstance().getCreateUserBean();
		// Address address = BeanFactory.getInstance().getAddressBean();
		// address.stateAL(u.getUserCountry());
		// address.cityAL(u.getUserState());
		tabs.add(new InnerTabs("Details", "/view/um/admin/user/userNoSelection.xhtml",
				"detailsContainer"));
		tabs.add(new InnerTabs("Groups",
				"/view/um/admin/user/groups/userGroupList.xhtml",
				"groupsContainer"));
		tabs.add(new InnerTabs("User History",
				"/view/um/admin/user/usersHistory.xhtml",
				"usersHistoryContainer"));
	}

	public long getSelectedGroup() {
		return selectedGroup;
	}

	public void setSelectedGroup(long selectedGroup) {
		UsersListingBackingBean.selectedGroup = selectedGroup;
	}

	public ArrayList<NewUserBackingBean> getUsers()
			throws UserNotFoundException {
		ArrayList<NewUserBackingBean> myList = new ArrayList<NewUserBackingBean>();
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		NewUserBackingBean u;

		List<UmUser> usrs = null;
		try {
			usrs = userDao.listUsers(session.getCompanyId(), filterBy, filterFor);
		} catch (com.srpl.um.ejb.exceptions.UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (UmUser us : usrs) {
			u = new NewUserBackingBean();
			convert2Bean(us, u);
			myList.add(u);
		}
		if(myList.size() == 0){
			u = new NewUserBackingBean();
			u.setUserFname("No User found");
			u.setUserLname(" ");
			u.setUserId(0L);
			myList.add(u);
		}
		return myList;
	}

	@Deprecated
	public List<UmUser> getLeadUsers() {
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		return userDao.listLeadUsers(session.getCompanyId());
	}

	// franchise drop down
	public List<FranchiseBackingBean> listFranchises() {
		List<FranchiseBackingBean> myList = new ArrayList<FranchiseBackingBean>();

		// Deprecated List<UmFranchiseDetails> fDB = franchiseDao.listFranchises();
		List<UmFranchise> fDB = franchiseDao.list(BeanFactory.getInstance().getSessionBean().getCompanyId());
		// System.out.println("List found. bla bla");
		// System.out.println(fDB.size());
		for (UmFranchise us : fDB) {
			// System.out.println("------------------inside loop"+us.getUmCompany());
			FranchiseBackingBean f = new FranchiseBackingBean();
			f.setFranchiseId(us.getFranchiseId());
			f.setFranchiseAddress(us.getFranchiseAddress());
			f.setFranchiseCity(us.getFranchiseCity());
			f.setFranchiseCountry(us.getFranchiseCountry());
			f.setFranchiseDetails(us.getFranchiseDetails());
			f.setFranchiseState(us.getFranchiseState());
			f.setFranchiseStatus(us.getFranchiseStatus());
			f.setFranchiseTitle(us.getFranchiseTitle());
			// missing f.setCompanyid(us.getCompanyId());
			// System.out.println("------------------Company id "
			// + f.getCompanyid());
			myList.add(f);

		}
		if (myList.size() == 0) {
			FranchiseBackingBean f = new FranchiseBackingBean();
			f.setFranchiseTitle("no records found");
			// missing f.setCompanyid((long) 0);
			myList.add(f);
		}
		// System.out.println(myList.size());
		return myList;
	}

	// location dropdown
	public LinkedHashMap<String, Integer> Location() {
		LinkedHashMap<String, Integer> repMap = new LinkedHashMap<String, Integer>();
		// List<UmUserDetails> reportsto = userDao.listUsers();

		/*
		 * for (UmUserDetails u : reportsto) {
		 * repMap.put(u.getUserName(),u.getUserId()); }
		 */
		repMap.put("Islamabad", 1);
		repMap.put("Rawalpindi", 2);
		repMap.put("Lahore", 3);
		return repMap;
	}

	// ReportsTo dropdown
	
	/*
	 * missing userdao
	public ArrayList<NewUserBackingBean> Reportsto() throws UserNotFoundException {
		ArrayList<NewUserBackingBean> myList = new ArrayList<NewUserBackingBean>();
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		NewUserBackingBean cu;
		List<UmUser> uDB = userDao.listUsers1(session.getCompanyId());
		// System.out.println("Franchise List found.");
		// System.out.println(uDB.size());
		for (UmUser us : uDB) {
			cu = new NewUserBackingBean();
			convert2Bean(us, cu);
			myList.add(cu);

		}
		// System.out.println(myList.size());
		return myList;
	}*/
	

	private void setSelected(Integer companyid) {
		// TODO Auto-generated method stub

	}

	public void loadUser(Long id) {

		NewUserBackingBean userBean = BeanFactory.getInstance().getUserBackingBean();
		UmUser db;
		try{
			db = userDao.umUserDetails(id);
			//campaign found in db
			convert2Bean(db, userBean);
			reset();
			changeTabPath(0, "/view/um/admin/user/UserForm.xhtml");
			setViewAction();
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("invalid id for user");
			changeTabPath(0, "/view/um/admin/user/userNoSelection.xhtml");
		}

	}

	public void userListener() {
		this.loadUser(this.getSelected());
		InnerTabs d = tabs.get(0);
		d.setPath("/view/um/admin/user/viewUser.xhtml");
		tabs.set(0, d);

	}

	public void actionListener(){

		reset();
		NewUserBackingBean userBean;
		Long userId;
		
		String action = getAction();
		switch(getAction()){
		case WebConstants.ACTION_CREATE:
			//Goto Create Page
			userBean = BeanFactory.getInstance().getUserBackingBean(); 
			userBean.reset();
			setCreateAction();
//			setEnableSpecificFields();
			setSelected(0L);
			changeTabPath(0, "/view/um/admin/user/UserForm.xhtml");
			this.setTabIndex(0);
			break;
		case WebConstants.ACTION_SAVE:
			//save backing bean and update the view
			SessionDataBean session = BeanFactory.getInstance().getSessionBean();
			try{
				UmUser db = new UmUser();
				userBean = BeanFactory.getInstance().getUserBackingBean(); 
				userBean.setIsFranchiseUser(false);
				userBean.setUserAddedon(new Timestamp(new Date().getTime()));
				userBean.setUserCompany(session.getCompanyId());
				convert2Db(userBean, db);
				db.setUserId(null);
				userId = userDao.createUser(db);
				userBean.setUserId(userId);
				setViewAction();
				addMessage("User Successfully Created.");
			}
			catch (Exception createExp) {
				// handle exception
				System.out.println("couldn't save 1");
				addError("User Creation Failed.");
				setCreateAction();
			}
			this.setTabIndex(0);
			break;
		case WebConstants.ACTION_VIEW:
			//Goto View Page
			//on view page we want to show edit, delete buttons
			loadUser(getSelected());
			reset();
			setViewAction();
			this.setTabIndex(0);
			break;
		case WebConstants.ACTION_EDIT:
			//Goto Edit Page
			try{
				userId = Long.valueOf(getParameter("user_id").toString());
				loadUser(userId);
				reset();
				setEditAction();
			}
			catch (Exception e) {
				//handle exception
				addError("Invalid User.");
			}
			this.setTabIndex(0);
			break;
		case WebConstants.ACTION_DELETE:
			//Goto Delete Page
			try{
				userId = Long.valueOf(getParameter("user_id").toString());
				loadUser(userId);
				reset();
				setDeleteAction();
			}
			catch (Exception e) {
				//handle exception
				addError("Invalid User.");
			}
			this.setTabIndex(0);
			break;
		case WebConstants.ACTION_LIST:
			//Goto List Page
			//there is no List Page
			break;
		case WebConstants.ACTION_CANCEL:
			//if cancel button is pressed go back to view
			loadUser(getSelected());
			setViewAction();
			this.setTabIndex(0);
			break;
		case WebConstants.ACTION_UPDATE:
			//update action is called.
			userBean = BeanFactory.getInstance().getUserBackingBean();
			try{
				UmUser db = new UmUser();
				convert2Db(userBean, db);
				userDao.updateUser(db);
				setViewAction();
				addMessage("User Successfully Updated.");
			}
			catch (Exception createExp) {
				// handle exception
				addError("User Update Failed.");
				setEditAction();
			}
			this.setTabIndex(0);
			break;
		case WebConstants.ACTION_DELETE_CONFIRMED:
			//when delete is confirmed
			try{
				userDao.deleteUser(getSelected());
				addMessage("User Successfully deleted.");
			}
			catch(Exception deleteExpception){
				addError("User Deletion Failed.");
			}
			setViewAction();
			this.setTabIndex(0);
			break;
		}
	}

	public void groupActionListener(){

		reset();
		Long userId;
		
		switch(getAction()){
		case WebConstants.ACTION_ADD_USER_TO_GROUP:
			//Goto Create Page
			setSelectedGroup(0L);
			this.changeTabPath(1, "/view/um/admin/user/groups/addGroup.xhtml");
			this.setTabIndex(1);
			setCreateAction();
			break;
		case WebConstants.ACTION_ADD_USER_TO_GROUP_CONFIRMED:
			long uId = getSelected();
			long gId = getSelectedGroup();
			try {
				userDao.addUserToGroup(uId, gId);
				this.addMessage("User Successfully Added to Group.");

			} catch (Exception e) {
				this.addError("User Addition to Group Failed.");
			}
			this.changeTabPath(1, "/view/um/admin/user/groups/userGroupList.xhtml");
			this.setTabIndex(1);
			break;
		case WebConstants.ACTION_REMOVE_USER_FROM_GROUP_CONFIRMED:
			// System.out.println("removeUserGroupsFromUser()  called");
			Long groupId;
			try {
				groupId = Long.parseLong(this.getParameter("row_id"));
				userId = Long.parseLong(this.getParameter("user_id"));
				try {
					userDao.removeUserFromGroup(userId, groupId);
					this.addMessage("User Successfully Removed from the Group.");
				} catch (Exception e) {
					this.addError("Couldn't Remove User from the Group.");
				}
			} catch (Exception e) {
				System.out.println("can't receive the id.");
			}
			this.changeTabPath(1, "/view/um/admin/user/groups/userGroupList.xhtml");
			this.setTabIndex(1);
			break;
		case WebConstants.ACTION_LIST:
			this.changeTabPath(1, "/view/um/admin/user/groups/userGroupList.xhtml");
			this.setTabIndex(1);
			break;
		case WebConstants.ACTION_CANCEL:
			this.changeTabPath(1, "/view/um/admin/user/groups/userGroupList.xhtml");
			this.setTabIndex(1);
			setListAction(true);
			break;
		}
	}

	private void changeTabPath(int index, String path) {
		InnerTabs d = tabs.get(index);
		d.setPath(path);
		tabs.set(index, d);
	}

	/*
	 * no need
	 *
	// Details Page
	public void detailView(Long id) {
		if (id == null) {
			SessionDataBean session = BeanFactory.getInstance()
					.getSessionBean();
			id = session.getUserId();

		}
		// System.out.println(this.getSelected());
		loadUser(id);
		// loadUser(this.getSelected());
		this.setSearchById(null);
		this.listUserGroupsPage();
		InnerTabs d = tabs.get(0);
		d.setPath("/view/um/admin/user/UserForm.xhtml");
		tabs.set(0, d);
		this.setTabIndex(0);
	}
	*
	*/

	/*
	 * no need
	 * 
	public void createForm() {
		InnerTabs d = tabs.get(0);
		d.setPath("/view/um/admin/user/UserForm.xhtml");
		// System.out.print(d.getPath());
		tabs.set(0, d);
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		CreateUserBean g = BeanFactory.getInstance().getCreateUserBean();
		g.setIsFranchiseUser(null);
		g.setUserFname("");
		g.setUserName("");
		g.setUserStatus(false);
		g.setUserLname("");
		g.setUserPassword(null);
		g.setUserConPassword(null);
		g.setUserAddress("");
		g.setUserCountry(0);
		g.setUserState(0);
		g.setUserCity(0);
		g.setUserZipcode("");
		g.setUserEmail("");
		g.setUserPhone("");
		g.setUserPicture("");
		g.setUserReportsto(null);
		g.setUserJobtitle("");
		g.setUserType(true);
		g.setIsLoggedIn(false);
		// session.setSelectedCountry(0);
		// session.setSelectedState(0);

		// System.out.println("userstate"+ g.getUserState());
		// System.out.println("createform()");

	}
	*
	*/

	/* no need
	 * 
	public String createUser() {
		// call he ejb here. or set pojo as discussed
		// System.out.println("here in create user.");
		String value;
		CreateUserBean g = BeanFactory.getInstance().getCreateUserBean();
		SampleBackingBean sb = BeanFactory.getInstance().getSampleBackingBean();
		UmUserDetails details = new UmUserDetails(null, g.getUserName(),
				g.getUserPassword(), g.getUserFname(), g.getUserLname(),
				g.getUserAddress(), g.getUserCountry(), g.getUserState(),
				g.getUserCity(), g.getUserZipcode(), g.getUserEmail(),
				g.getUserPhone(), g.getUserJobtitle(), g.getUserReportsto(),
				null, false, sb.getCompanyId(), g.getUserAddedon(),
				g.getUserStatus(), false);
		// Integer userId = userDao.createUser(details);
		// details.setUserId(userId);
		try {
			g.setUserId(userDao.createUser(details));
			this.addMessage("User has been created Successfully.");
			value = "success";
			setSelected(g.getUserId());

		} catch (Exception e) {
			this.addError("User Creation Failed.");
			value = "failure";
		}

		this.detailView(g.getUserId());
		return value;
	}
	*
	*/

	/* no need
	 * 
	public void editForm() {
		Long user_Id = Long.parseLong(this.getParameter("user_id"));
		setSelected(user_Id);
		System.out.println("user id in edit form " + user_Id);
		this.loadUser(user_Id);
		Address address = BeanFactory.getInstance().getAddressBean();
		CreateUserBean u = BeanFactory.getInstance().getCreateUserBean();
		int countryId = 0;
		int stateId = 0;

		try {
			countryId = u.getUserCountry();
			address.stateAL(countryId);
		} catch (Exception e) {
			// TODO: handle exception
		}

		try {

			stateId = u.getUserState();
			address.cityAL(stateId);
		} catch (Exception e) {
			// TODO: handle exception
		}
		this.changeTabPath(0, "/view/um/admin/user/UserForm.xhtml");
	}
	*
	*/

	/*
	 * no need
	 * 
	public void editUser() {
		System.out.println("Edit user called");
		System.out.println(this.getSelected());
		CreateUserBean g = BeanFactory.getInstance().getCreateUserBean();
		UmUserDetails details = userDao.userDetails(g.getUserId());

		details.setUserId(g.getUserId());
		System.out.println("User id in update user function " + g.getUserId());
		details.setUserName(g.getUserName());
		details.setUserPassword(g.getUserPassword());
		details.setUserFname(g.getUserFname());
		details.setUserLname(g.getUserLname());
		details.setUserAddress(g.getUserAddress());
		details.setUserCountry(g.getUserCountry());
		System.out.println("edited country"
				+ Integer.toString(details.getUserCountry()));
		details.setUserState(g.getUserState());
		System.out.println("edited state"
				+ Integer.toString(details.getUserState()));
		details.setUserCity(g.getUserCity());
		System.out.println("edited city"
				+ Integer.toString(details.getUserCity()));
		details.setUserZipcode(g.getUserZipcode());
		details.setUserEmail(g.getUserEmail());
		details.setUserPhone(g.getUserPhone());
		details.setUserJobtitle(g.getUserJobtitle());
		details.setUserReportsto(g.getUserReportsto());
		details.setUserPicture(g.getUserPicture());
		details.setIsFranchiseUser(g.getIsFranchiseUser());
		details.setUserCompany(g.getUserCompany());
		details.setUserAddedon(g.getUserAddedon());
		details.setUserStatus(g.getUserStatus());
		details.setIsUserLogin(g.getIsLoggedIn());

		try {
			userDao.updateUser(details);
			System.out.println(details.getUserName());
			this.addMessage("User Successfully Updated.");
			setSelected(details.getUserId());
		} catch (Exception e) {
			this.addError("User Update Failed.");
		}

		this.detailView(g.getUserId());

	}
	*
	*/

	/*
	 * no need
	 *
	public void deleteForm() {
		Long user_Id = Long.parseLong(this.getParameter("user_id"));
		this.loadUser(user_Id);
		setSelected(user_Id);
		this.changeTabPath(0, "/view/um/admin/user/UserForm.xhtml");
	}
	*
	*/

	/*
	 * no need
	 *
	public void deleteUser() {
		// call the ejb here
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		// System.out.println("here in delete user.");
		// System.out.println(this.getSelected());
		Long user_Id = Long.parseLong(this.getParameter("user_id"));

		try {
			// System.out.println("userlisting delete try called");
			userDao.deleteUser(user_Id, session.getCompanyId());
			this.addMessage("User Successfully Deleted.");
		} catch (Exception e) {
			this.addError("User Deletion Failed.");
		}
		this.setSelected(0);
		this.detailView(null);
	}
	*
	*/

	public void importUsers() {
		InnerTabs d = tabs.get(0);
		d.setPath("/view/um/admin/user/importUsers.xhtml");
		// System.out.print(d.getPath());
		tabs.set(0, d);
	}

	public void userHistory() {
		InnerTabs d = tabs.get(0);
		d.setPath("/view/um/admin/user/usersHistory.xhtml");
		// System.out.print(d.getPath());
		tabs.set(0, d);
	}

	public void userSearch() {
		InnerTabs d = tabs.get(0);
		d.setPath("/view/um/admin/user/userSearch2.xhtml");
		// System.out.print(d.getPath());
		tabs.set(0, d);
	}

	// UserGroup page

	/*
	 * no need
	 *
	public void listUserGroupsPage() {
		// System.out.println("here in list users page");
		this.changeTabPath(1, "/view/um/admin/user/groups/userGroupList.xhtml");
		this.setTabIndex(1);
	}
	*
	*/

	// according to new logic
	/* Deprecated
	public List<GroupBackingBean> getUsersGroup() {
		// System.out.println("here in Users Group");
		return listUsersGroup(true);
	}

	public List<GroupBackingBean> getNoUsersInGroup() {
		// System.out.println("here in No users in group");
		return listUsersGroup(false);
	}

	public List<GroupBackingBean> listUsersGroup(Boolean include) {
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		List<GroupBackingBean> myList = new ArrayList<GroupBackingBean>();
		Long userId = selected;
		List<UmGroup> usersGroupDbList = userDao.listUserGroups(userId,
				include, session.getCompanyId());

		GroupBackingBean ug;
		// System.out.println("User exists in groups :" +
		// usersGroupDbList.size());
		for (UmGroup x : usersGroupDbList) {
			// System.out.println("group title :" + x.getGroupTitle());
			ug = new GroupBackingBean();
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
	*/

	/*
	 * no need
	 *
	public void removeUserGroupsFromUser() {
		// System.out.println("removeUserGroupsFromUser()  called");
		Long id;
		String val;
		try {
			val = this.getParameter("row_id");
			id = Long.valueOf(val);
			try {
				userDao.removeUserFromGroup(selected, id);
				this.addMessage("User Successfully Removed from the Group.");
			} catch (Exception e) {
				System.out.println("couldn't remove.");
				this.addError("Couldn't Remove User from the Group.");
			}
		} catch (Exception e) {
			System.out.println("can't receive the id.");
		}
	}
	*
	*/

	/* Deprecated
	public List<GroupBackingBean> getUserGroupsList() {

		List<GroupBackingBean> myList = new ArrayList<GroupBackingBean>();
		List<UmGroupDetails> userGroupsDbList;

		userGroupsDbList = groupDao.listGroups();
		GroupBackingBean ug;

		for (UmGroupDetails x : userGroupsDbList) {
			ug = new GroupBackingBean();
			ug.setCompany_id(x.getCompanyId());
			ug.setGroup_details(x.getGroupDetails());
			ug.setGroup_id(x.getGroupId());
			ug.setGroup_status(x.getGroupStatus());
			ug.setGroup_title(x.getGroupTitle());
			// ug.setRole_id(x.getRoleId());
			myList.add(ug);
		}

		System.out.println(myList.size());
		return myList;
	}
	*/

	/*
	 * no need
	 *
	public void addUserGroupFormPage() {
		// System.out.println("here in add groups in users form page");
		this.changeTabPath(1, "/view/um/admin/user/groups/addGroup.xhtml");
		this.setTabIndex(1);
	}
	*
	*/

	/*
	 * no need
	 *
	public void addUserGroupAction() {
		// System.out.println("here in add groups actions");
		this.changeTabPath(1, "/view/um/admin/user/groups/userGroupList.xhtml");
		this.setTabIndex(1);

		// System.out.println("User :" + getSelected());
		// System.out.println("Group :" +getSelectedGroup());

		long uId = getSelected();
		long gId = getSelectedGroup();
		try {
			userDao.addUserToGroup(uId, gId);
			this.addMessage("User Successfully Added to Group.");

		} catch (Exception e) {
			this.addError("User Addition to Group Failed.");
		}
		this.listUserGroupsPage();
	}
	*
	*/

	// User History
	@Deprecated
	public List<HistoryBackingBean> getUserHistoryList() {
		List<HistoryBackingBean> myList = new ArrayList<HistoryBackingBean>();

		List<UmUserHistory> uhDB = userHistoryDao.listUserHistory(selected);
		System.out.println(uhDB.size());
		for (UmUserHistory ushd : uhDB) {

			HistoryBackingBean uh = new HistoryBackingBean();
			uh.setUserId(ushd.getUser().getUserId());
			uh.setLoginTime(ushd.getLoginTime());
			// uh.setLogoutTime(ushd.getLogout_time());
			uh.setUser(ushd.getUser());
			uh.setUserHistoryId(ushd.getUserHistoryId());

			myList.add(uh);

		}
		return myList;
	}

	private void convert2Bean(UmUser db, NewUserBackingBean bean) {
		Address address = BeanFactory.getInstance().getAddressBean();
		bean.setUserId(db.getUserId());
		bean.setIsFranchiseUser(db.getIsFranchiseUser());
		bean.setUserAddedon(db.getUserAddedon());

		// bean.setSelectedCity(address.retCity(db.getUserCity()));
		// bean.setSelectedState(address.retState(db.getUserState()));
		// bean.setSelectedCountry(address.retCountry(db.getUserCountry()));
		bean.setUserCity(db.getUserCity());
		bean.setUserState(db.getUserState());
		bean.setUserCountry(db.getUserCountry());

		bean.setUserCompany(db.getUserCompany());
		bean.setUserCountry(db.getUserCountry());
		bean.setUserEmail(db.getUserEmail());
		bean.setUserName(db.getUserName());
		bean.setUserFname(db.getUserFname());
		bean.setUserJobtitle(db.getUserJobtitle());
		bean.setUserLname(db.getUserLname());
		bean.setUserPassword(db.getUserPassword());
		bean.setUserPhone(db.getUserPhone());
		bean.setUserReportsto(db.getUserReportsto());
		bean.setUserState(db.getUserState());
		bean.setUserStatus(db.getUserStatus());
		bean.setUserZipcode(db.getUserZipcode());

		// bean.setIsLoggedIn(db.getIsOnline());
		bean.setIsOnline(db.getIsOnline());

		bean.setUserAddress(db.getUserAddress());
		bean.setUserCity(db.getUserCity());

		// bean.setKeyword(db.getUserFname());
	}

	private void convert2Db(NewUserBackingBean bean, UmUser db) {
		db.setUserId(bean.getUserId());
		db.setIsFranchiseUser(bean.getIsFranchiseUser());
		db.setUserAddedon(bean.getUserAddedon());
		db.setUserCity(bean.getUserCity());
		db.setUserCompany(bean.getUserCompany());
		db.setUserCountry(bean.getUserCountry());
		db.setUserEmail(bean.getUserEmail());
		db.setUserName(bean.getUserName());
		db.setUserFname(bean.getUserFname());
		db.setUserJobtitle(bean.getUserJobtitle());
		db.setUserLname(bean.getUserLname());
		db.setUserPassword(bean.getUserPassword());
		db.setUserPhone(bean.getUserPhone());
		db.setUserReportsto(bean.getUserReportsto());
		db.setUserState(bean.getUserState());
		db.setUserStatus(bean.getUserStatus());
		db.setUserZipcode(bean.getUserZipcode());
		db.setUserAddress(bean.getUserAddress());
		db.setIsOnline(bean.getIsOnline());
		// db.setIsOnline(bean.getIsLoggedIn());
		db.setUserAddress(bean.getUserAddress());

	}

	/*
	 public void search() { System.out.println("inside search()");
	 CreateUserBean u = BeanFactory.getInstance().getCreateUserBean();
	 if(getKeyword().equals(u.getUserFname())){
	 System.out.println("username in search is"+u.getUserFname());
	 FacesContext.getCurrentInstance().addMessage(null, new
	 FacesMessage(FacesMessage.SEVERITY_INFO, "User Found","'"+"'"));
	 }
	  
	 throws NumberFormatException, UserNotFoundException }
	 */

	/*
	public ArrayList<CreateUserBean> search() throws NumberFormatException,
			UserNotFoundException {
		System.out.println("inside search()");
		Boolean lk;
		FacesContext context = FacesContext.getCurrentInstance();
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		ArrayList<CreateUserBean> myList = new ArrayList<CreateUserBean>();
		CreateUserBean u = BeanFactory.getInstance().getCreateUserBean();
		if (this.getSearchById().equals("")
				&& this.getSearchByName().equals("")
				&& this.getSearchByJob().equals("")
				&& this.getSearchByReportsTo().equals("")
				&& this.getSearchByUserGroup().equals("")) {
			// context.addMessage(null, new
			// FacesMessage("no user id selected"));
			this.addError("No Searching Criteria Entered. ");

		}
		try {
			List<UmUser> searchUsers = userDao.listUsers(
					session.getCompanyId(), getSearchById(), getSearchByName(),
					getSearchByJob(), getSearchByUserGroup(),
					getSearchByReportsTo(), getSearchByUserStatus());
			this.addError("No items match your search. ");
			for (UmUser us : searchUsers) {
				System.out.println("inside for loop");
				u = new CreateUserBean();
				convert2Bean(us, u);
				myList.add(u);

			}
		} catch (Exception e) {
			this.addError("Searching Fail.");
		}
		return myList;
	}
	*/

	/* Deprecated
	 * 
	public ArrayList<GroupBackingBean> searchByGroup() {
		ArrayList<GroupBackingBean> myList = new ArrayList<GroupBackingBean>();
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		GroupBackingBean ug;
		List<UmGroup> uDB = groupDao.listGroups1(session.getCompanyId());
		for (UmGroup us : uDB) {
			ug = new GroupBackingBean();

			ug.setCompany_id(us.getCompanyId());
			ug.setGroup_details(us.getGroupDetails());
			ug.setGroup_id(us.getGroupId());
			ug.setGroup_status(us.getGroupStatus());
			ug.setGroup_title(us.getGroupTitle());
			// System.out.println("group id in searchByGroup" + us.getGroupId()
			// +us.getGroupTitle() );
			myList.add(ug);

		}
		return myList;
	}
	*/

	/*
	public ArrayList<CreateUserBean> userReportsto()
			throws UserNotFoundException {
		ArrayList<CreateUserBean> myList = new ArrayList<CreateUserBean>();
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		CreateUserBean cu;
		List<UmUser> uDB = userDao.listUsers(session.getCompanyId(),
				getSearchById(), getSearchByName(), getSearchByJob(),
				getSearchByUserGroup(), getSearchByReportsTo(),
				getSearchByUserStatus());

		// System.out.println("Franchise List found.");
		// System.out.println(uDB.size());
		for (UmUser us : uDB) {
			cu = new CreateUserBean();
			convert2Bean(us, cu);
			myList.add(cu);

		}
		// System.out.println(myList.size());
		return myList;
	}
	*/

	public String getSearchById() {
		return searchById;
	}

	public void setSearchById(String searchById) {
		this.searchById = searchById;
	}

	public String getSearchByName() {
		return searchByName;
	}

	public void setSearchByName(String searchByName) {
		this.searchByName = searchByName;
	}

	public String getSearchByJob() {
		return searchByJob;
	}

	public void setSearchByJob(String searchByJob) {
		this.searchByJob = searchByJob;
	}

	public String getSearchByReportsTo() {
		return searchByReportsTo;
	}

	public void setSearchByReportsTo(String searchByReportsTo) {
		this.searchByReportsTo = searchByReportsTo;
	}

	public String getSearchByUserGroup() {
		return searchByUserGroup;
	}

	public void setSearchByUserGroup(String searchByUserGroup) {
		this.searchByUserGroup = searchByUserGroup;
	}

	/*
	 * public Map<String, String> searchUsers() { Map<String, String> c = new
	 * LinkedHashMap<String, String>(); Set<Attribute<? super UmUser, ?>> cols =
	 * userDao.listTableCols(); Iterator<Attribute<? super UmUser, ?>> itr =
	 * cols.iterator(); while (itr.hasNext()) { String col =
	 * itr.next().getName();
	 * 
	 * c.put(col, col); } return c; }
	 */
	/*
	 * public Map<String, String> UserReportsTo() throws UserNotFoundException {
	 * SessionDataBean session = BeanFactory.getInstance().getSessionBean();
	 * Map<String, String> c = new LinkedHashMap<String, String>(); List<UmUser>
	 * cols = userDao.listUsers(session.getCompanyId(),getSearchById(),
	 * getSearchByName(), getSearchByJob(),getSearchByUserGroup(),
	 * getSearchByReportsTo(),getSearchByUserStatus()); Iterator<UmUser> itr =
	 * cols.iterator(); while (itr.hasNext()) { String col =
	 * itr.next().getUserName();
	 * 
	 * c.put(col, col); } return c; }
	 */
	public void handleToggle(ToggleEvent event) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Fieldset Toggled", "Visibility:" + event.getVisibility());

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public String getSearchColumns() {
		return searchColumns;
	}

	public void setSearchColumns(String searchColumns) {
		this.searchColumns = searchColumns;
	}

	public String getSearchByUserStatus() {
		return searchByUserStatus;
	}

	public void setSearchByUserStatus(String searchByUserStatus) {
		this.searchByUserStatus = searchByUserStatus;
	}

	public Boolean getShow() {
		return show;
	}

	public void setShow(Boolean show) {
		this.show = show;
	}

	public String getFilterBy() {
		return filterBy;
	}

	public void setFilterBy(String filterBy) {
		this.filterBy = filterBy;
	}

	public String getFilterFor() {
		return filterFor;
	}

	public void setFilterFor(String filterFor) {
		this.filterFor = filterFor;
	}

}
