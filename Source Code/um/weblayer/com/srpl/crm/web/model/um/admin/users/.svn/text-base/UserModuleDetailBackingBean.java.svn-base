package com.srpl.crm.web.model.um.admin.users;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.validation.ConstraintViolationException;

import org.primefaces.event.ToggleEvent;

import com.bitguiders.util.jsf.JSFBeanInterface;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;
import com.srpl.crm.web.common.AjaxListStructure;
import com.srpl.crm.web.common.InnerTabs;
import com.srpl.um.ejb.entity.GENDER;
import com.srpl.um.ejb.entity.HONORIFIC_TITLE;
import com.srpl.um.ejb.entity.UmGroup;
import com.srpl.um.ejb.entity.UmUser;
import com.srpl.um.ejb.exceptions.UserNotFoundException;
import com.srpl.um.ejb.request.GroupDAO;
import com.srpl.um.ejb.request.UserDAO;
import com.srpl.um.web.common.SessionDataBean;
import com.srpl.um.web.controller.BeanFactory;

@ManagedBean(name = "userDetails")
public class UserModuleDetailBackingBean extends JSFBeanSupport implements
		JSFBeanInterface, Serializable {

	/**
	 * 
	 */
	@EJB
	UserDAO userDao;
	@EJB
	GroupDAO groupDao;

	private static final long serialVersionUID = 1L;

	private Long userId;
	private boolean isFranchiseUser;
	private Long userCompany;
	private Date userAddedon;
	private String userAddress;
	private int userCountry = 0;
	private int userState = 0;
	private int userCity = 0;
	private String userZipcode;
	private String userEmail;
	private String userFname;
	private String userJobtitle;
	private String userLname;
	private String userName;
	private String userPassword;
	private String userConPassword;
	private String userPhone;
	private String userPicture;
	private Long userReportsto;
	private boolean isOnline;
	private boolean userStatus;
	private HONORIFIC_TITLE honorificTitle;
	private GENDER gender;

	private boolean enablePasswordField;

	private SessionDataBean session;

	public String searchById;
	private String searchByName;
	private String searchByJob;
	private String searchByReportsTo;
	private String searchByUserGroup;
	

	private String searchByUserStatus;
	
	private String filterBy = "";
	private String filterFor = "";	

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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Boolean getIsFranchiseUser() {
		return isFranchiseUser;
	}

	public void setIsFranchiseUser(Boolean isFranchiseUser) {
		this.isFranchiseUser = isFranchiseUser;
	}

	public Long getUserCompany() {
		return userCompany;
	}

	public void setUserCompany(Long userCompany) {
		this.userCompany = userCompany;
	}

	public Date getUserAddedon() {
		return userAddedon;
	}
	
	public void setUserAddedon(Date val){
		userAddedon = val;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public int getUserCountry() {
		return userCountry;
	}

	public void setUserCountry(int userCountry) {
		this.userCountry = userCountry;
	}

	public int getUserState() {
		return userState;
	}

	public void setUserState(int userState) {
		this.userState = userState;
	}

	public int getUserCity() {
		return userCity;
	}

	public void setUserCity(int userCity) {
		this.userCity = userCity;
	}

	public String getUserZipcode() {
		return userZipcode;
	}

	public void setUserZipcode(String userZipcode) {
		this.userZipcode = userZipcode;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserFname() {
		return userFname;
	}

	public void setUserFname(String userFname) {
		this.userFname = userFname;
	}

	public String getUserJobtitle() {
		return userJobtitle;
	}

	public void setUserJobtitle(String userJobtitle) {
		this.userJobtitle = userJobtitle;
	}

	public String getUserLname() {
		return userLname;
	}

	public void setUserLname(String userLname) {
		this.userLname = userLname;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserConPassword() {
		return userConPassword;
	}

	public void setUserConPassword(String userConPassword) {
		this.userConPassword = userConPassword;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserPicture() {
		return userPicture;
	}

	public void setUserPicture(String userPicture) {
		this.userPicture = userPicture;
	}

	public Long getUserReportsto() {
		return userReportsto;
	}

	public void setUserReportsto(Long userReportsto) {
		this.userReportsto = userReportsto;
	}

	public boolean getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}

	public boolean isUserStatus() {
		return userStatus;
	}

	public void setUserStatus(boolean userStatus) {
		this.userStatus = userStatus;
	}

	public HONORIFIC_TITLE getHonorificTitle() {
		return honorificTitle;
	}

	public void setHonorificTitle(HONORIFIC_TITLE honorificTitle) {
		this.honorificTitle = honorificTitle;
	}

	public GENDER getGender() {
		return gender;
	}

	public void setGender(GENDER gender) {
		this.gender = gender;
	}

	public boolean isEnablePasswordField() {
		return enablePasswordField;
	}

	public void setEnablePasswordField(boolean enablePasswordField) {
		this.enablePasswordField = enablePasswordField;
	}

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

	public String getSearchByUserStatus() {
		return searchByUserStatus;
	}

	public void setSearchByUserStatus(String searchByUserStatus) {
		this.searchByUserStatus = searchByUserStatus;
	}

	public UserModuleDetailBackingBean() {
		session = BeanFactory.getInstance().getSessionBean();
		setCurrentAction(WebConstants.ACTION_SECURITY, this.getClass());
	}

	@PostConstruct
	public void postConstruct() {
		String row_id=getParameter("row_id");
		if(!row_id.isEmpty())
		{
			session.setUserModule_selectedUser(Long.parseLong(row_id));
		}
		String act = getAction();
		if (act.equals("")) {
			// no action was called, load group data
			userDetails();
			reset();
			setViewAction();
		}else if(act.equals(WebConstants.ACTION_SAVE)){
			enablePasswordField = true;
		}else if(act.equals("actionAjax")){
			setDisabled(false);
		}
	}

	public void resetBean() {

		userId = 0L;
		isFranchiseUser = false;
		userCompany = 0L;
		userAddedon = null;
		userAddress = "";
		userCountry = 0;
		userState = 0;
		userCity = 0;
		userZipcode = "";
		userEmail = "";
		userFname = "";
		userJobtitle = "";
		userLname = "";
		userName = "";
		userPassword = "";
		userConPassword = "";
		userPhone = "";
		userPicture = "";
		userReportsto = 0L;
		isOnline = false;
		userStatus = false;
	}

	private void changeTabPath(int index, String path) {
		InnerTabs d = session.getUserTabs().get(index);
		d.setPath(path);
		session.getUserTabs().set(index, d);
		try {
			if (getParameter("fromListing").equals("fromListing")) {
				// don't update index
			} else {
				session.setUserModule_tabIndex(0);
			}
		} catch (Exception e) {
			session.setUserModule_tabIndex(0);
		}
	}

	private void convert2Bean(UmUser db, UserModuleDetailBackingBean bean) {
		bean.setUserId(db.getUserId());
		bean.setIsFranchiseUser(db.getIsFranchiseUser());
		bean.setUserAddedon(db.getUserAddedon());
		bean.setUserCity(db.getUserCity());
		bean.setUserState(db.getUserState());
		bean.setUserCountry(db.getUserCountry());
		bean.setUserZipcode(db.getUserZipcode());
		bean.setUserCompany(db.getUserCompany());
		bean.setUserAddress(db.getUserAddress());
		bean.setUserEmail(db.getUserEmail());
		bean.setUserName(db.getUserEmail());
		bean.setUserFname(db.getUserFname());
		bean.setUserJobtitle(db.getUserJobtitle());
		bean.setUserLname(db.getUserLname());
		bean.setUserPassword(db.getUserPassword());
		bean.setUserPhone(db.getUserPhone());
		bean.setUserReportsto(db.getUserReportsto());
		bean.setUserState(db.getUserState());
		bean.setUserStatus(db.getUserStatus());
		bean.setIsOnline(db.getIsOnline());
		bean.setHonorificTitle(db.getHonorificTitle());
		bean.setGender(db.getGender());

		BeanFactory.getInstance().getSessionBean().setSelectedCountry(bean.getUserCountry());
		BeanFactory.getInstance().getSessionBean().setSelectedState(bean.getUserState());
	}

	private void convert2Db(UserModuleDetailBackingBean bean, UmUser db) {
		db.setUserId(bean.getUserId());
		db.setIsFranchiseUser(bean.getIsFranchiseUser());
		if(userAddedon != null){
			db.setUserAddedon(new Timestamp(bean.getUserAddedon().getTime()));
		}else{
			db.setUserAddedon(new Timestamp(new Date().getTime()));
		}
		db.setUserCity(bean.getUserCity());
		db.setUserCompany(bean.getUserCompany());
		db.setUserCountry(bean.getUserCountry());
		db.setUserEmail(bean.getUserEmail());
		db.setUserName(bean.getUserEmail());
		db.setUserFname(bean.getUserFname());
		db.setUserJobtitle(bean.getUserJobtitle());
		db.setUserLname(bean.getUserLname());
		db.setUserPassword(bean.getUserPassword());
		db.setUserPhone(bean.getUserPhone());
		db.setUserReportsto(bean.getUserReportsto());
		db.setUserState(bean.getUserState());
		db.setUserStatus(bean.isUserStatus());
		db.setUserZipcode(bean.getUserZipcode());
		db.setUserAddress(bean.getUserAddress());
		db.setIsOnline(bean.getIsOnline());
		db.setUserAddress(bean.getUserAddress());
		db.setIsUserCustomer(false);
		db.setGender(bean.getGender());
		db.setHonorificTitle(bean.getHonorificTitle());
	}

	public void updateEmail(){
		System.out.println("updateEmail called" + userEmail);
		userEmail = userName;
	}
	public void loadUser(Long id) {
		UserModuleDetailBackingBean bean = this;
		UmUser db;
		try {
			db = userDao.umUserDetails(id);
			convert2Bean(db, bean);
		} catch (Exception e) {
			changeTabPath(0, "/view/um/admin/user/userNoSelection.xhtml");
		}
	}
	
	public void clearFilter(){
		filterBy = "";
		filterFor = "";
	}	
	
	public ArrayList<AjaxListStructure> getList() {
		ArrayList<AjaxListStructure> myList = new ArrayList<AjaxListStructure>();
		AjaxListStructure u;

		List<UmUser> usrs = null;
		try {
			usrs = userDao.listUsers(session.getCompanyId(), filterBy, filterFor);
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (UmUser db : usrs) {
			u = new AjaxListStructure();
			u.setId(db.getUserId());
			u.setLabel(db.getUserName() + " - " + db.getUserFname() + ' '
					+ db.getUserLname());
			myList.add(u);
		}
		if (myList.size() == 0) {
			u = new AjaxListStructure();
			u.setId(0L);
			u.setLabel(getProperty("no.user.found"));
			myList.add(u);
		}
		return myList;
	}

	/*public ArrayList<AjaxListStructure> getList() {
		ArrayList<AjaxListStructure> myList = new ArrayList<AjaxListStructure>();
		AjaxListStructure u;

		List<UmUser> usrs = null;
		try {
			usrs = userDao.listUsers(session.getCompanyId(),
					getSearchById(), getSearchByName(), getSearchByJob(),
					getSearchByUserGroup(), getSearchByReportsTo(),
					getSearchByUserStatus());
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (UmUser db : usrs) {
			u = new AjaxListStructure();
			u.setId(db.getUserId());
			u.setLabel(db.getUserName() + " - " + db.getUserFname() + ' '
					+ db.getUserLname());
			myList.add(u);
		}
		if (myList.size() == 0) {
			u = new AjaxListStructure();
			u.setId(0L);
			u.setLabel("No User Found.");
			myList.add(u);
		}
		return myList;
	}*/

	public ArrayList<AjaxListStructure> getGroupsList() {
		ArrayList<AjaxListStructure> myList = new ArrayList<AjaxListStructure>();
		AjaxListStructure element;
		List<UmGroup> dbList = groupDao.listGroups1(session.getCompanyId());
		for (UmGroup db : dbList) {
			element = new AjaxListStructure();
			element.setId(db.getGroupId());
			element.setLabel(db.getGroupTitle());
			myList.add(element);
		}
		return myList;
	}

	public ArrayList<AjaxListStructure> getUserReportsToList() {
		ArrayList<AjaxListStructure> myList = new ArrayList<AjaxListStructure>();
		AjaxListStructure element;
		List<UmUser> dbList;
		try {
			dbList = userDao.list(session.getCompanyId());
			for (UmUser db : dbList) {
				element = new AjaxListStructure();
				element.setId(db.getUserId());
				element.setLabel(db.getUserName() + " - " + db.getUserFname()
						+ ' ' + db.getUserLname());
				myList.add(element);
			}
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myList;
	}

	public void handleToggle(ToggleEvent event) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Fieldset Toggled", "Visibility:" + event.getVisibility());

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	@Override
	public void setViewAction() {
		super.setViewAction();
		setCancelAction(false);
		setResetAction(false);
	}

	@Override
	public String actionListener() {
		long user_id;
		setCurrentAction(getParameter("action"),this.getClass());
		switch (getCurrentAction()) {
		case WebConstants.ACTION_CREATE:
			changeTabPath(0, "/view/um/admin/user/UserForm.xhtml");
			BeanFactory.getInstance().getSessionBean().setSelectedCountry(0);
			BeanFactory.getInstance().getSessionBean().setSelectedState(0);
			resetBean();
			break;
		case WebConstants.ACTION_SAVE:
			createUser();
			break;
		case WebConstants.ACTION_VIEW:
			userDetails();
			reset();
			setViewAction();
			break;
		case WebConstants.ACTION_CANCEL:
			userDetails();
			reset();
			setViewAction();
			break;
		case WebConstants.ACTION_EDIT:
			user_id = Long.valueOf(getParameter("user_id"));
			loadUser(user_id);
			changeTabPath(0, "/view/um/admin/user/UserForm.xhtml");
			break;
		case WebConstants.ACTION_UPDATE:
			userUpdate();
			break;
		case WebConstants.ACTION_DELETE:
			userDetails();
			reset();
			setDeleteAction();
			break;
		case WebConstants.ACTION_DELETE_CONFIRMED:
			userDelete();
			break;
		}
		return (null);
	}

	@Override
	public void setCreateAction() {
		super.setCreateAction();
		enablePasswordField = true;
	}

	public void createUser() {
		try {
			UmUser db = new UmUser();
			UserModuleDetailBackingBean userBean = this;
			userBean.setUserCompany(session.getCompanyId());
			convert2Db(userBean, db);
			db.setUserId(null);
			userId = userDao.createUser(db);
			addMessage(getProperty("user.successfully.created"));
			session.setUserModule_selectedUser(userId);
			enablePasswordField = false;
			reset();
			setViewAction();
		} catch (Exception createExp) {
			if(createExp.getCause().toString().contains("already exists.")){
				//constraint voilation occured.
				addError(getProperty("user.already.exists"));
	
			}else{
				addError(getProperty("user.creation.fail"));
			}
			createExp.printStackTrace();
			reset();
			setCreateAction();
		}
	}

	public void userDetails() {
		if (session.getUserModule_selectedUser() != 0) {
			loadUser(session.getUserModule_selectedUser());
			changeTabPath(1, "/view/um/admin/user/groups/userGroupList.xhtml");
			changeTabPath(2, "/view/um/admin/user/usersHistory.xhtml");
			changeTabPath(0, "/view/um/admin/user/UserForm.xhtml");
		} else {
			session.resetUserModule();
		}
	}

	public void userUpdate() {
		UserModuleDetailBackingBean userBean = this;
		try {
			UmUser db = new UmUser();
			convert2Db(userBean, db);
			userDao.updateUser(db);
			reset();
			setViewAction();
			addMessage(getProperty("user.successfully.updated"));
		} catch (Exception createExp) {
			// handle exception
			addError(getProperty("user.update.fail"));
			reset();
			setEditAction();
		}
	}

	public void userDelete() {
		try {
			userDao.deleteUser(session.getUserModule_selectedUser());
			addMessage(getProperty("user.successfully.deleted"));
		} catch (Exception deleteExpception) {
			addError(getProperty("user.deletion.fail"));
		}
		session.setUserModule_selectedUser(0L);
		userDetails();
		reset();
		setViewAction();
	}
	
	public SessionDataBean getSession() {
		return session;
	}

	public void setSession(SessionDataBean session) {
		this.session = session;
	}

}
