package com.srpl.bi.web.model.common;

import java.security.Principal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.srpl.bi.web.common.SessionDataBean;
import com.srpl.bi.web.controller.BeanFactory;
/*import com.srpl.bi.common.utils.UmUserDetails;
import com.srpl.crm.common.utils.UmUserHistoryDetails;
import com.srpl.crm.ejb.entity.CsContactORM;
import com.srpl.crm.ejb.entity.UmUserHistory;
import com.srpl.crm.ejb.request.AssignedTaskDAO;
import com.srpl.crm.ejb.request.AuthenticationDAO;
import com.srpl.crm.ejb.request.ContactDAO;
import com.srpl.crm.ejb.request.UserDAO;
import com.srpl.crm.ejb.request.UserHistoryDAO;*/




@ManagedBean(name="sampleBackingBean")
@SessionScoped
public class SampleBackingBean {
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
	private String lname="M Humaid";
	private Long companyId;
	
//	@EJB AuthenticationDAO auth;
//	@EJB UserHistoryDAO userHistoryDao;
//	@EJB UserDAO userDao;
//	@EJB ContactDAO customerDao;
	
	
	
	private StringBuilder log;	
	
	
	{ 
		list = new ArrayList<String>();
		log = new StringBuilder();
	}
	
	public SampleBackingBean()
	{
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
	    	
	    	/*try{
				uid = userDao.getUserId(principal.getName());
				System.out.println(uid);
				userDao.updateUserOnlineStatus(uid, true);
		    	UmUserHistoryDetails userHistoryDetails = new UmUserHistoryDetails(new Timestamp(date.getTime()),uid);
		    	Integer userHistoryId = userHistoryDao.createUserHistory(userHistoryDetails);
		    	userHistoryDetails.setUserHistoryId(userHistoryId);
		    	HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		    	UmUserDetails udata = userDao.userDetails(uid);
		    	this.fname = udata.getUserFname();
		    	this.lname = udata.getUserLname();
		    	this.companyId = udata.getUserCompany();
		    	session.setAttribute("userdata", udata);
		    	historyId = new Integer(userHistoryId);
		    	
		    	//Put user data into the session data bean;
		    	SessionDataBean sessionBean = BeanFactory.getInstance().getSessionBean();
		    	//Update this
		    	sessionBean.setCompanyId(Long.valueOf(udata.getUserCompany().toString()));
		    	//sessionBean.setFranchise(franchise);
		    	sessionBean.setUserId(udata.getUserId());
		    	sessionBean.setUserFname(udata.getUserFname());
		    	//System.out.println(sessionBean.getUserFname());
		    	sessionBean.setUserLname(udata.getUserLname());
	    	}catch(Exception e){
	    		
	    		e.printStackTrace();
	    	}*/
	    	
	    	//remove this

			// Get the context 
			InitialContext ctx = new InitialContext();

			// Lookup the bean using its JNDI name 
			/*it worked
			try{
				AssignedTaskDAO myEjb = (AssignedTaskDAO) ctx.lookup("java:global/srpl/businesslayer/AssignedTaskDAO");
				System.out.println("got the bean");
			}catch (Exception e) {
				System.out.println("exception");
				e.printStackTrace();
			}
			*/
	    	
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
	/*	try{
		System.out.println("log out called");	
		Date date = new Date();
		//temporary
		Timestamp loginTime = userHistoryDao.getLoginTime(historyId);
        UmUserHistoryDetails historyDetails = new UmUserHistoryDetails(historyId,loginTime,new Timestamp(date.getTime()),uid);
		userHistoryDao.updateUserHistory(historyDetails);
		userDao.updateUserOnlineStatus(uid, false);
   	    HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.sendRedirect("logout.jsp");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
	   	    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);   	    
			if(session != null){
				session.invalidate();
			}
			FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "/index.jsp");
		}*/
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
			message.setDetail("Email not valid");
			message.setSummary("Email not valid");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
					
			 throw new ValidatorException(message);
        }
	}
	public void PasswordValidate(){
		System.out.println("PasswordValidate() called");
		FacesContext context = FacesContext.getCurrentInstance();
		if(getOldPwd().equals("")){
			context.addMessage(null,new FacesMessage("Old Password Required"));
		}
		if(getNewPwd().equals("")){
			context.addMessage(null, new FacesMessage("New Password Required"));
		}
		if(getConfirmPwd().equals(""))
		{
			context.addMessage(null, new FacesMessage("Confirm Password Required"));
		}
		if(!confirmPwd.equals(newPwd)){
			context.addMessage(null, new FacesMessage("Password not match"));
		}		
	}
	public void forgotpassword(){
	/*	FacesContext context = FacesContext.getCurrentInstance();
		if(auth.forgotPassword(getEmail())){				
			context.addMessage(null, new FacesMessage("Password Sent at your provided email address."));
	    }else{
			context.addMessage(null, new FacesMessage("Provided Email does not exist in our database."));
	    }*/
	}
	public void changePassword(){
		/*FacesContext context = FacesContext.getCurrentInstance();		
		if(auth.resetPassword(uid, getOldPwd(), getNewPwd())){				
			context.addMessage(null, new FacesMessage("Password Updated Successfully."));
	    }else{
			context.addMessage(null, new FacesMessage("Invalid Old Password"));
	    }*/
	}
}
