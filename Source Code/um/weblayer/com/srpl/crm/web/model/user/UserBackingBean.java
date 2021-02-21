package com.srpl.crm.web.model.user;

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

import com.bitguiders.util.jsf.JSFBeanInterface;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;
import com.srpl.um.ejb.entity.UmUser;
import com.srpl.um.ejb.entity.UmUserHistory;
import com.srpl.um.ejb.request.AuthenticationDAO;
import com.srpl.um.ejb.request.UserDAO;
import com.srpl.um.ejb.request.UserHistoryDAO;
import com.srpl.um.web.common.SessionDataBean;
import com.srpl.um.web.controller.BeanFactory;

@ManagedBean(name="userBackingBean")
@SessionScoped
public class UserBackingBean extends JSFBeanSupport implements JSFBeanInterface{
	private User user;
	private List<String> list;
	private String username;
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
	private Long companyId;
	

	

	@EJB AuthenticationDAO auth;
	@EJB UserHistoryDAO userHistoryDao;
	@EJB UserDAO userDao;
	
	private StringBuilder log;	
	
	
	{ 
		list = new ArrayList<String>();
		log = new StringBuilder();
	}
	
	//Constructor
	public UserBackingBean(){
		setCurrentAction(WebConstants.ACTION_SECURITY,this.getClass());
	}
	
	// =============================== user history ===================================== //
	@PostConstruct
	 public void construct() {	  
	  try{
		    System.out.println("construct called");
			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			Principal principal = request.getUserPrincipal();			
	    	Date date = new Date();
			uid = userDao.getUserId(principal.getName());
	    	UmUser udata = userDao.umUserDetails(uid);
	    	UmUserHistory userHistoryDetails = new UmUserHistory(new Timestamp(date.getTime()),udata);
	    	Integer userHistoryId = userHistoryDao.createUserHistory(userHistoryDetails);
	    	userHistoryDetails.setUserHistoryId(userHistoryId);
	    	HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	    	this.fname = udata.getUserFname();
	    	this.companyId = udata.getUserCompany();
	    	session.setAttribute("userdata", udata);
	    	historyId = new Integer(userHistoryId);
	    	
	    	//Put user data into the session data bean;
	    	SessionDataBean sessionBean = BeanFactory.getInstance().getSessionBean();
	    	
	    	//Update this
	    	sessionBean.setCompanyId(Long.valueOf(udata.getUserCompany().toString()));
	    	//sessionBean.setFranchise(franchise);
	    	sessionBean.setUserId(uid);
	    	sessionBean.setUserFname(udata.getUserFname());
	    	sessionBean.setUserLname(udata.getUserLname());
	    	sessionBean.setPersistence("um_crm_persistence");
	   }catch(Exception e){
		    System.out.println("Exception occured"); 
			System.out.println(e.getMessage());
	  }
	 }
	
	
	public void logOut(){
		try{
		System.out.println("log out called");	
		Date date = new Date();
		Timestamp loginTime = userHistoryDao.getLoginTime(historyId);
    	UmUser udata = userDao.umUserDetails(uid);
        UmUserHistory historyDetails = new UmUserHistory(historyId,loginTime,new Timestamp(date.getTime()),udata);
		userHistoryDao.updateUserHistory(historyDetails);
   	    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);   	    
		if(session != null){
			session.invalidate();
		}
		FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "/index.jsp");
   	    /*HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.sendRedirect("logout.jsp");*/
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	// =============================== user history ===================================== //
	
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
	
	
	public void forgotpassword(){
		System.out.println("forgetpassword() called");
		FacesContext context = FacesContext.getCurrentInstance();
		if(auth.forgotPassword(getEmail())){				
			context.addMessage("SendMailForm:sendBtn", new FacesMessage(getProperty("password.sent")));
	    }else{
			context.addMessage("SendMailForm:sendBtn", new FacesMessage(getProperty("provided.email.not.exist")));
	    }
	}
	
	public void changePassword(){
		System.out.println("changepassword called");
		FacesContext context = FacesContext.getCurrentInstance();		
		if(auth.resetPassword(uid, getOldPwd(), getNewPwd())){				
			context.addMessage(null, new FacesMessage(getProperty("password.updated")));
	    }else{
			context.addMessage(null, new FacesMessage(getProperty("invalid.old.password")));
	    }
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String signOut(){
		return "signout";
	}

	public List<String> getList() {
		return list;
	}

	public String getUsername() {
		return username;
	}

	public String getPwd() {
		return pwd;
	}

	public String getEmail() {
		return email;
	}

	public String getOldPwd() {
		return oldPwd;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public String getConfirmPwd() {
		return confirmPwd;
	}

	public String getNewPwdConfirm() {
		return newPwdConfirm;
	}

	public Long getUserId() {
		return userId;
	}

	public Integer getHistoryId() {
		return historyId;
	}

	public Long getUid() {
		return uid;
	}

	public String getUname() {
		return uname;
	}

	public String getFname() {
		return fname;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public void setConfirmPwd(String confirmPwd) {
		this.confirmPwd = confirmPwd;
	}

	public void setNewPwdConfirm(String newPwdConfirm) {
		this.newPwdConfirm = newPwdConfirm;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setHistoryId(Integer historyId) {
		this.historyId = historyId;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	
	
	 public String actionListener() {
		  setCurrentAction(getParameter("action"), this.getClass());
		  switch(getCurrentAction()){
		  case WebConstants.ACTION_SEND:
			  forgotpassword();
			 return(null);
		  }
		  return(null);
	  }

	

}
