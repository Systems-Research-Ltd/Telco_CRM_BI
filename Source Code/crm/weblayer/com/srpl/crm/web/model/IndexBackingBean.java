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
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.bitguiders.util.jsf.JSFBeanSupport;
import com.srpl.crm.web.controller.BeanFactory;
import com.srpl.crm.ejb.entity.CsContactORM;
import com.srpl.crm.ejb.request.AuthenticationDAO;
import com.srpl.crm.ejb.request.CampaignDAO;
import com.srpl.crm.ejb.request.ContactDAO;
import com.srpl.crm.web.common.SessionDataBean;
import com.srpl.um.ejb.entity.UmCompany;
import com.srpl.um.ejb.entity.UmUser;
import com.srpl.um.ejb.entity.UmUserHistory;
import com.srpl.um.ejb.request.CompanyDAO;
import com.srpl.um.ejb.request.GroupDAO;
import com.srpl.um.ejb.request.ServiceDAO;
import com.srpl.um.ejb.request.UserDAO;

@ManagedBean(eager = true)
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
	private String companyLogo;
	private String companyName;
	private String userRole;
	private Long launchedCampaignsCounter = 0L;

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@EJB
	AuthenticationDAO auth;
	@EJB
	com.srpl.um.ejb.request.UserHistoryDAO userHistoryDao;
	@EJB
	UserDAO userDAO;
	@EJB
	ContactDAO customerDao;
	@EJB
	GroupDAO groupDAO;
	@EJB
	ServiceDAO serviceDAO;
	@EJB
	CompanyDAO companyDao;
	@EJB CampaignDAO campaignDao;

	private StringBuilder log;

	{
		list = new ArrayList<String>();
		log = new StringBuilder();
	}

	public IndexBackingBean() {
		// HttpSession session = (HttpSession)
		// FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		/*
		 * if(session != null){ session.invalidate(); }
		 */
		// HttpServletRequest request = (HttpServletRequest)
		// FacesContext.getCurrentInstance().getExternalContext().getRequest();
		// Principal principal = request.getUserPrincipal();

		// System.out.println(principal.getName());
	}

	// =============================== user history
	// ===================================== //

	@PostConstruct
	public void construct() {
		try {
			System.out.println("sample backing bean construct called");
			HttpServletRequest request = (HttpServletRequest) FacesContext
					.getCurrentInstance().getExternalContext().getRequest();
			Principal principal = request.getUserPrincipal();
			System.out.println("Name : " + principal.getName());
			Date date = new Date();
			/*
			 * DashBean is called before index backing bean and set the DAO
			 * instances So there is no need to call it from here
			 */
			// getProfile().setDAOInstances(userDAO,groupDAO, serviceDAO);

			try {
				uid = userDAO.getUserId(principal.getName());
				System.out.println(uid);
				userDAO.updateUserOnlineStatus(uid, true);
				UmUser udata = userDAO.umUserDetails(uid);
				UmUserHistory userHistoryDetails = new UmUserHistory(
						new Timestamp(date.getTime()), udata);
				Integer userHistoryId = userHistoryDao
						.createUserHistory(userHistoryDetails);
				userHistoryDetails.setUserHistoryId(userHistoryId);
				HttpSession session = (HttpSession) FacesContext
						.getCurrentInstance().getExternalContext()
						.getSession(false);
				this.fname = udata.getUserFname();
				this.lname = udata.getUserLname();
				this.uname = udata.getUserName();
				this.companyId = udata.getUserCompany();
				UmCompany uCompany = companyDao.companyDetails(companyId);

				if (FacesContext.getCurrentInstance().getExternalContext()
						.isUserInRole("Administrator")) {
					userRole = "Administrator";
				} else if (FacesContext.getCurrentInstance()
						.getExternalContext().isUserInRole("User")) {
					companyName = uCompany.getCompanyName();
					userRole = "CSR of";
				} else if (FacesContext.getCurrentInstance()
						.getExternalContext().isUserInRole("AccountManager")) {
					companyName = uCompany.getCompanyName();
					userRole = "Account Manager of";
				} else if (FacesContext.getCurrentInstance()
						.getExternalContext().isUserInRole("Customer")) {
					companyName = uCompany.getCompanyName();
					userRole = "Customer of";
				} else {
					companyName = uCompany.getCompanyName();
					userRole = "User of";
				}
				// Put user data into the session data bean;
				SessionDataBean sessionBean = BeanFactory.getInstance()
						.getSessionBean();
				// sessionBean.setCompanyLogo(uCompany.getCompanyLogo());

				// Setting company logo in the session
				if (uCompany == null) {
					sessionBean.setCompanyLogo("sysres-logo.jpg");
				} else {
					sessionBean.setCompanyLogo(uCompany.getCompanyLogo());
				}
				this.companyLogo = sessionBean.getCompanyLogo();
				System.out.println("Current Company Logo: "
						+ sessionBean.getCompanyLogo());

				session.setAttribute("userdata", udata);
				session.setAttribute("uId", uid);
				historyId = new Integer(userHistoryId);

				// Update this
				sessionBean.setCompanyId(Long.valueOf(udata.getUserCompany()
						.toString()));
				// sessionBean.setFranchise(franchise);
				sessionBean.setUserId(udata.getUserId());
				sessionBean.setUserFname(udata.getUserFname());
				// System.out.println(sessionBean.getUserFname());
				sessionBean.setUserLname(udata.getUserLname());
			} catch (Exception e) {
				// this might be a customer so try for customer
				try {
					uid = customerDao.getContactId(principal.getName());
					System.out.println(uid);
					// userDao.updateUserOnlineStatus(uid, true);
					// UmUserHistoryDetails userHistoryDetails = new
					// UmUserHistoryDetails(new Timestamp(date.getTime()),uid);
					// Integer userHistoryId =
					// userHistoryDao.createUserHistory(userHistoryDetails);
					// userHistoryDetails.setUserHistoryId(userHistoryId);
					HttpSession session = (HttpSession) FacesContext
							.getCurrentInstance().getExternalContext()
							.getSession(false);
					CsContactORM udata = customerDao.contactDetails(uid);
					this.fname = udata.getContactFname();
					this.lname = udata.getContactLname();
					// this.companyId = udata.get;
					session.setAttribute("userdata", udata);
					// historyId = new Integer(userHistoryId);

					// Put user data into the session data bean;
					SessionDataBean sessionBean = BeanFactory.getInstance()
							.getSessionBean();
					// Update this
					// sessionBean.setCompanyId(Long.valueOf(udata.getUserCompany().toString()));
					// sessionBean.setFranchise(franchise);
					sessionBean.setUserId(udata.getContactId());
					sessionBean.setUserFname(udata.getContactFname());
					// System.out.println(sessionBean.getUserFname());
					sessionBean.setUserLname(udata.getContactLname());
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}

		} catch (Exception e) {
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

	public void logOut() {
		try {
			System.out.println("log out called");
			Date date = new Date();
			// temporary
			Timestamp loginTime = userHistoryDao.getLoginTime(historyId);
			UmUser udata = userDAO.umUserDetails(uid);
			UmUserHistory historyDetails = new UmUserHistory(historyId,
					loginTime, new Timestamp(date.getTime()), udata);
			userHistoryDao.updateUserHistory(historyDetails);
			userDAO.updateUserOnlineStatus(uid, false);
			/*
			 * HttpServletResponse response = (HttpServletResponse)
			 * FacesContext.
			 * getCurrentInstance().getExternalContext().getResponse();
			 * response.sendRedirect("logout.jsp");
			 */
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			getProfile().flush();
			HttpSession session = (HttpSession) FacesContext
					.getCurrentInstance().getExternalContext()
					.getSession(false);
			if (session != null) {
				session.invalidate();
			}
			// FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(),
			// null, "/index.jsp");
			// Following is a try to flush both crm and um's session on single event.
			// it works but throws some exception in the console.
			String url = ("/../um/logout.jsp");
			FacesContext fc = FacesContext.getCurrentInstance();
			ExternalContext ec = fc.getExternalContext();
			try {
				ec.redirect(url);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// =============================== user history
	// ===================================== //

	public void add(String action) {
		if (list == null) {
			list = new ArrayList<String>();
		}
		list.add(action);
		log.append("Last Action >> ").append(action);
	}

	public String goAddOperations() {
		return "operationsIndex.xhtml";
	}

	public String goaddUsersHistory() {
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

	public String getname() {
		String name;
		name = fname + " " + lname;
		return name;
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

	public String getLog() {
		return log.toString();
	}

	public void setLog(String log) {
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

	/*
	 * public String actionListener(){
	 * 
	 * System.out.println("actionListener called"); return null; //navigation
	 * rule target, null means stay on same page }
	 * 
	 * public String actionListener(String param){
	 * System.out.println("actionListener called "+param);
	 * BeanFactory.getInstance().getSampleBackingBean().add(param); return null;
	 * }
	 */

	public String getCompanyLogo() {
		return companyLogo;
	}

	public void setCompanyLogo(String companyLogo) {
		this.companyLogo = companyLogo;
	}
	
	public Long getLaunchedCampaignsCounter(){
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		launchedCampaignsCounter = campaignDao.getLaunchedCampaignsCounter(session.getCompanyId());
		return launchedCampaignsCounter;
	}

	public void validateEmailData(FacesContext context,
			UIComponent componentToValidate, Object value)
			throws ValidatorException {
		String email = (String) value;
		System.out.println("validatEmaileData() called" + email);
		Pattern p = Pattern
				.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher m = p.matcher(email);
		boolean matchFound = m.matches();
		if (!matchFound) {
			FacesMessage message = new FacesMessage();
			message.setDetail("Email not valid");
			message.setSummary("Email not valid");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);

			throw new ValidatorException(message);
		}
	}

	public void PasswordValidate() {
		System.out.println("PasswordValidate() called");
		FacesContext context = FacesContext.getCurrentInstance();
		if (getOldPwd().equals("")) {
			context.addMessage(null, new FacesMessage("Old Password Required"));
		}
		if (getNewPwd().equals("")) {
			context.addMessage(null, new FacesMessage("New Password Required"));
		}
		if (getConfirmPwd().equals("")) {
			context.addMessage(null, new FacesMessage(
					"Confirm Password Required"));
		}
		if (!confirmPwd.equals(newPwd)) {
			context.addMessage(null, new FacesMessage("Password not match"));
		}
	}

	public void forgotpassword() {
		FacesContext context = FacesContext.getCurrentInstance();
		if (auth.forgotPassword(getEmail())) {
			context.addMessage(null, new FacesMessage(
					"Password Sent at your provided email address."));
		} else {
			context.addMessage(null, new FacesMessage(
					"Provided Email does not exist in our database."));
		}
	}

	public void changePassword() {
		FacesContext context = FacesContext.getCurrentInstance();
		if (auth.resetPassword(uid, getOldPwd(), getNewPwd())) {
			context.addMessage(null, new FacesMessage(
					"Password Updated Successfully."));
		} else {
			context.addMessage(null, new FacesMessage("Invalid Old Password"));
		}
	}

	public void initiate() {
		System.out.println("SampleBackingBean initiate() called..");
	}
}
