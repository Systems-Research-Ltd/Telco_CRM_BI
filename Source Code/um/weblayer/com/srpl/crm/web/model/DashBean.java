package com.srpl.crm.web.model;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.bitguiders.util.jsf.JSFBeanSupport;
import com.srpl.um.ejb.entity.GroupPermission;
import com.srpl.um.ejb.entity.UmCompany;
import com.srpl.um.ejb.entity.UmGroup;
import com.srpl.um.ejb.entity.UmUser;
import com.srpl.um.ejb.entity.UmUserHistory;
import com.srpl.um.ejb.exceptions.UserNotFoundException;
import com.srpl.um.ejb.request.AuthenticationDAO;
import com.srpl.um.ejb.request.CompanyDAO;
import com.srpl.um.ejb.request.GroupDAO;
import com.srpl.um.ejb.request.Security;
import com.srpl.um.ejb.request.ServiceDAO;
import com.srpl.um.ejb.request.UmUtilsDAO;
import com.srpl.um.ejb.request.UserDAO;
import com.srpl.um.ejb.request.UserHistoryDAO;
import com.srpl.um.web.common.SessionDataBean;
import com.srpl.um.web.controller.BeanFactory;

@ManagedBean
public class DashBean extends JSFBeanSupport {
	private List<String> myAlertsAndReminders;
	private List<String>  myTickets;
	private List<String> myGroups;
	private List<String> myLog;
	private List<String> myServices;
	private List<UmGroup> userGroups = new ArrayList<UmGroup>();
	private List<UmUserHistory> userHistoryList = new ArrayList<UmUserHistory>();
	private List<GroupPermission> featuresPermissionsList;
	private String userName ;
	private String company;
	private String address ;
	private String email ;
	private String userPicture;
	private Timestamp lastLogedIn ;
	private  Long userId;
	private  Long companyId;
	private String role;
	private String url;
	private String allowSearch;
	
	@EJB Security security;
	@EJB AuthenticationDAO auth;
	@EJB UserHistoryDAO userHistoryDao;
	@EJB UserDAO userDAO;
	@EJB GroupDAO groupDAO;
	@EJB ServiceDAO serviceDAO;
	@EJB CompanyDAO comapnyDAO;
    @EJB UmUtilsDAO utilsDao;
    @EJB GroupDAO groupDao;
    
	public DashBean() {
		// TODO Auto-generated constructor stub
		System.out.println("----------------------UM DashBean construct called > ED");
	}
   
	@PostConstruct
	 public void construct(){	  
		try {
			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			Principal principal = request.getUserPrincipal();	
			System.out.println("Name : " + principal.getName());
			this.userId = userDAO.getUserId(principal.getName());
			System.out.println(userId);
			UmUser udata = userDAO.umUserDetails(userId);
			this.setUserPicture(udata.getUserPicture());
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			this.userName = udata.getUserFname() + " " + udata.getUserLname();
			this.userHistoryList = userHistoryDao.listUserHistory(userId);
			getlastLoginTime(userHistoryList);
			System.out.println("--------------------------------------- User Last Login Time >>>>>" + getlastLoginTime(userHistoryList));
			
			if(FacesContext.getCurrentInstance().getExternalContext().isUserInRole("Administrator")){
				System.out.println("--------------------------------------- Its Admin role thats why he has 0 company");
			}else{
				
				this.companyId = udata.getUserCompany();
				UmCompany uCompany = comapnyDAO.companyDetails(companyId);
				company = uCompany.getCompanyName();
				url = uCompany.getCompanyUrl();
				System.out.println("--------------------------------------- User Company >>>>>" + companyId);
			}
			getUserRole();
			String city = utilsDao.getCity(udata.getUserCity());
			String country = utilsDao.getCountry(udata.getUserCountry());
			userGroups = utilsDao.listUserGroups(userId);
			this.address = udata.getUserAddress() + " " + city + " " + country ;
			this.email = udata.getUserEmail();
//			System.out.println("--------------------- User's Group > "+userServices());
			
			
			
			System.out.println("----------------------UM DashBean construct called > OD");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//___________________________________________ Setters & getters ___________________________________________________
	
	
	public String getAllowSearch() {
		return allowSearch;
	}

	public void setAllowSearch(String allowSearch) {
		this.allowSearch = allowSearch;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public List<GroupPermission> getUserServices() {
		return security.getUserServicesByUserId(userId);
		
	}
	
	
	
	public Timestamp getlastLoginTime(List<UmUserHistory> userHistoryList2){
	    int max = Integer.MIN_VALUE;
	    for(int i=0; i<userHistoryList.size(); i++){
	        if(userHistoryList.get(i).getUserHistoryId()-1 > max){
	        	lastLogedIn = userHistoryList.get(i).getLoginTime();
	        }
	    }
	    return lastLogedIn;
	}
	
	public List<UmUserHistory> getUserHistoryList() {
		return userHistoryList;
	}

	public void setUserHistoryList(List<UmUserHistory> userHistoryList) {
		this.userHistoryList = userHistoryList;
	}

	public String getUserPicture() {
		return userPicture;
	}

	public void setUserPicture(String userPicture) {
		this.userPicture = userPicture;
	}
	public List<UmGroup> getUserGroups() {
		return userGroups;
	}

	public void setUserGroups(List<UmGroup> userGroups) {
		this.userGroups = userGroups;
	}

	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	
	public List<String> getMyAlertsAndReminders() {
		return myAlertsAndReminders;
	}
	public void setMyAlertsAndReminders(List<String> myAlertsAndReminders) {
		this.myAlertsAndReminders = myAlertsAndReminders;
	}
	public List<String> getMyTickets() {
		return myTickets;
	}
	public void setMyTickets(List<String> myTickets) {
		this.myTickets = myTickets;
	}
	public List<String> getMyGroups() {
		return myGroups;
	}
	public void setMyGroups(List<String> myGroups) {
		this.myGroups = myGroups;
	}
	public List<String> getMyLog() {
		return myLog;
	}
	public void setMyLog(List<String> myLog) {
		this.myLog = myLog;
	}
	public List<String> getMyServices() {
		return myServices;
	}
	public void setMyServices(List<String> myServices) {
		this.myServices = myServices;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Timestamp getLastLogedIn() {
		return lastLogedIn;
	}
	public void setLastLogedIn(Timestamp lastLogedIn) {
		this.lastLogedIn = lastLogedIn;
	}
	
	//___________________________________________ Functions() ___________________________________________________
	
	public void getUserRole(){
		if(FacesContext.getCurrentInstance().getExternalContext().isUserInRole("Administrator")){
			this.role = "Administrator";
			this.allowSearch = "";
		}else if(FacesContext.getCurrentInstance().getExternalContext().isUserInRole("AccountManager")){
			this.role = "Account Manager";
			this.allowSearch = "allowed";
		}else if(FacesContext.getCurrentInstance().getExternalContext().isUserInRole("User")){
			this.role = "CSR";
			this.allowSearch = "allowed";
		}else if(FacesContext.getCurrentInstance().getExternalContext().isUserInRole("Customer")){
			this.role = "Customer";
			this.allowSearch = "";
		}else{
			this.role = "";
			this.allowSearch = "";
		}
	}
	public void getUserLog(){
		
	}
	
	public void getUser(long id){
		
	}
	
	public void getUserData(){
		getUserLog();
		getUserServices();
		getUserRole();
	}
	
	public void actionListener(){
		
	}
}
