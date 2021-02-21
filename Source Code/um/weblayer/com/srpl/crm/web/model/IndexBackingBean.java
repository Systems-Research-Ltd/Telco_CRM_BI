package com.srpl.crm.web.model;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.bitguiders.util.jsf.JSFBeanSupport;
import com.srpl.um.ejb.entity.UmCompany;
import com.srpl.um.ejb.entity.UmUser;
import com.srpl.um.ejb.entity.UmUserHistory;
import com.srpl.um.ejb.request.AuthenticationDAO;
import com.srpl.um.ejb.request.CompanyDAO;
import com.srpl.um.ejb.request.GroupDAO;
import com.srpl.um.ejb.request.ServiceDAO;
import com.srpl.um.ejb.request.UmUtilsDAO;
import com.srpl.um.ejb.request.UserDAO;
import com.srpl.um.ejb.request.UserHistoryDAO;
import com.srpl.um.web.common.SessionDataBean;
import com.srpl.um.web.controller.BeanFactory;




@ManagedBean(eager=true)
@SessionScoped
public class IndexBackingBean extends JSFBeanSupport {
	private List<String> list;
	private String user;
	private String pwd;
	private String email;
	private String oldPwd;
	private String newPwd;
	private String confirmPwd;
	private String newPwdConfirm;
	private Long userId;
	Integer historyId;
	private Long uid;
	private String uname;
	private String fname;
	private String lname;
	private Long companyId;
	private String companyName;
	private String userAddress;
	DashBean dashBean;
	private String companyLogo;
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	@EJB AuthenticationDAO auth;
	@EJB UserHistoryDAO userHistoryDao;
	@EJB UserDAO userDAO;
	@EJB GroupDAO groupDAO;
	@EJB ServiceDAO serviceDAO;
	@EJB CompanyDAO comapnyDAO;
    @EJB UmUtilsDAO utilsDao;	
	
	private StringBuilder log;	
	
	
	{ 
		list = new ArrayList<String>();
		log = new StringBuilder();
	}
	
	public IndexBackingBean(){
	//	HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		/*if(session != null){
			session.invalidate();
		}*/
//		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	//	Principal principal = request.getUserPrincipal();
		
	//	System.out.println(principal.getName());
	}
	
// =============================== user history ===================================== //
	
	@PostConstruct
	 public void construct() {	  
	  try{
		    System.out.println("sample backing bean construct called");
			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			Principal principal = request.getUserPrincipal();	
			System.out.println("Name : " + principal.getName());
	    	Date date = new Date();
	    	getProfile().setDAOInstances(userDAO,groupDAO,serviceDAO);
	    	try{
				uid = userDAO.getUserId(principal.getName());
				System.out.println(uid);
				userDAO.updateUserOnlineStatus(uid, true);
		    	UmUser udata = userDAO.umUserDetails(uid);
		    	UmUserHistory userHistoryDetails = new UmUserHistory(new Timestamp(date.getTime()),udata);
		    	Integer userHistoryId = userHistoryDao.createUserHistory(userHistoryDetails);
		    	userHistoryDetails.setUserHistoryId(userHistoryId);
		    	HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		    	this.fname = udata.getUserFname();
		    	this.lname = udata.getUserLname();
		    	this.user = udata.getUserName();
		    	uname = fname + " " + lname;
		    	this.companyId = udata.getUserCompany();
		    	UmCompany uCompany = comapnyDAO.companyDetails(companyId);
		    	//Put user data into the session data bean;
		    	SessionDataBean sessionBean = BeanFactory.getInstance().getSessionBean();
		    	//sessionBean.setCompanyLogo(uCompany.getCompanyLogo());
		    	
		    	//Setting company logo in the session
		    	if(uCompany == null){
		    		sessionBean.setCompanyLogo("sysres-logo.jpg");
		    	}else{
		    		sessionBean.setCompanyLogo(uCompany.getCompanyLogo());
		    	}
		    	this.companyLogo = sessionBean.getCompanyLogo();
		    	System.out.println("Current Company Logo: " + sessionBean.getCompanyLogo());
		    	try{
		    		companyName = uCompany.getCompanyName();
		    	}catch (Exception e) {
					// TODO: handle exception
		    		System.out.println("Error while fetching company name in index backing bean");
				}
		    	System.out.println("----------------------------------" + companyId + " " + companyName);
		    	String city = utilsDao.getCity(udata.getUserCity());
		    	String country = utilsDao.getCountry(udata.getUserCountry());
		    	this.userAddress = udata.getUserAddress() + " " + city + " " + country ;
		    	this.email = udata.getUserEmail();
		     	
//		    	dashBean = new DashBean(uname,companyName,userAddress,email);
		    	session.setAttribute("userdata", udata);
		    	session.setAttribute("uId", uid);
		    	historyId = new Integer(userHistoryId);
		    	
		    	//Update this
		    	sessionBean.setCompanyId(Long.valueOf(udata.getUserCompany().toString()));
		    	//sessionBean.setFranchise(franchise);
		    	sessionBean.setUserId(udata.getUserId());
		    	sessionBean.setUserFname(udata.getUserFname());
		    	//System.out.println(sessionBean.getUserFname());
		    	sessionBean.setUserLname(udata.getUserLname());
	    	}catch(Exception e){
					// TODO: handle exception
		    		e.printStackTrace();
	    	}
	    	
	    		    	
	   }catch(Exception e){
		    System.out.println("Exception occured"); 
			System.out.println(e.getMessage());
	  }
	 }
	
	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public void logOut(){
		try{
		System.out.println("log out called");	
		Date date = new Date();
		//temporary
		Timestamp loginTime = userHistoryDao.getLoginTime(historyId);
    	UmUser udata = userDAO.umUserDetails(uid);
        UmUserHistory historyDetails = new UmUserHistory(historyId,loginTime,new Timestamp(date.getTime()),udata);
		userHistoryDao.updateUserHistory(historyDetails);
		userDAO.updateUserOnlineStatus(uid, false);
   	    /*HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.sendRedirect("logout.jsp");*/
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
			getProfile().flush();
	   	    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);   	    
			if(session != null){
				session.invalidate();
			}
			FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "/index.jsp");
		}
	}
	
	
// =============================== user history ===================================== //	
	
	public void add(String action){
		if(list==null){
			list = new ArrayList<String>();
		}
		list.add(action);
		log.append("Last Action >> ").append(action);
	}
	
	public String goAddOperations(){
		return "operationsIndex.xhtml";
	}
	
	public String goaddUsersHistory(){
		return "userHistory.xhtml";
	}
	
	
	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getname() {
		String name ;
		name = fname + " "+lname;
		return name;
	}
	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getLog(){
		return log.toString();
	}
	public void setLog(String log){
	}
	public List<String> getList() {
		System.out.println("getList called");		
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public String getNewPwdConfirm() {
		return newPwdConfirm;
	}

	public void setNewPwdConfirm(String newPwdConfirm) {
		this.newPwdConfirm = newPwdConfirm;
	}
	public String getConfirmPwd() {
		return confirmPwd;
	}

	public void setConfirmPwd(String confirmPwd) {
		this.confirmPwd = confirmPwd;
	}
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	/*public String actionListener(){
		
		System.out.println("actionListener called");
		return null; //navigation rule target, null means stay on same page
	}
	
	public String actionListener(String param){
		System.out.println("actionListener called "+param);
		BeanFactory.getInstance().getSampleBackingBean().add(param);
		return null;
	}*/
	
	

	public String getCompanyLogo() {
		return companyLogo;
	}

	public void setCompanyLogo(String companyLogo) {
		this.companyLogo = companyLogo;
	}

	public void validateEmailData(FacesContext context,UIComponent componentToValidate,
            Object value)
            		throws ValidatorException {
		String email=(String)value;
		System.out.println("validatEmaileData() called" + email);
		Pattern p=Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher m=p.matcher(email);
		boolean matchFound=m.matches();
		if(!matchFound)
		{
			FacesMessage message= new FacesMessage();
			message.setDetail(getProperty("email.not.valid"));
			message.setSummary(getProperty("email.not.valid"));
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
					
			 throw new ValidatorException(message);
        }
	}
	public void PasswordValidate(){
		System.out.println("PasswordValidate() called");
		FacesContext context = FacesContext.getCurrentInstance();
		if(getOldPwd().equals("")){
			context.addMessage(null,new FacesMessage(getProperty("old.password.required")));
		}
		if(getNewPwd().equals("")){
			context.addMessage(null, new FacesMessage(getProperty("New Password Required")));
		}
		if(getConfirmPwd().equals(""))
		{
			context.addMessage(null, new FacesMessage(getProperty("confirm.password.required")));
		}
		if(!confirmPwd.equals(newPwd)){
			context.addMessage(null, new FacesMessage(getProperty("password.not.match")));
		}		
	}
	public void forgotpassword(){
		FacesContext context = FacesContext.getCurrentInstance();
		if(auth.forgotPassword(getEmail())){				
			context.addMessage(null, new FacesMessage(getProperty("password.sent")));
	    }else{
			context.addMessage(null, new FacesMessage(getProperty("provided.email.not.exist")));
	    }
	}
	public void changePassword(){
		FacesContext context = FacesContext.getCurrentInstance();		
		if(auth.resetPassword(uid, getOldPwd(), getNewPwd())){				
			context.addMessage(null, new FacesMessage(getProperty("password.updated")));
	    }else{
			context.addMessage(null, new FacesMessage(getProperty("invalid.old.password")));
	    }
	}
   
	public void initiate(){
		System.out.println("SampleBackingBean initiate() called..");
	}
	
	public String selectedApp(){
		SessionDataBean sessionBean = BeanFactory.getInstance().getSessionBean();
		return sessionBean.getSelectedApplication();
	}
}
