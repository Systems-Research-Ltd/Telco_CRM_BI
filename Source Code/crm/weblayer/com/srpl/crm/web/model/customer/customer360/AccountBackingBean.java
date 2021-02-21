package com.srpl.crm.web.model.customer.customer360;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.bitguiders.util.jsf.JSFBeanInterface;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;
import com.srpl.crm.ejb.entity.CsAccount;
import com.srpl.crm.ejb.exceptions.AccountNotFoundException;
import com.srpl.crm.ejb.request.AccountDAO;
import com.srpl.crm.ejb.request.UtilsDAO;
import com.srpl.crm.web.common.AjaxListStructure;
import com.srpl.crm.web.common.InnerTabs;
import com.srpl.crm.web.common.SessionDataBean;
import com.srpl.crm.web.controller.BeanFactory;
import com.srpl.um.ejb.entity.UmUser;
import com.srpl.um.ejb.exceptions.UserNotFoundException;
import com.srpl.um.ejb.request.UserDAO;
@ManagedBean(name = "accountBackingBean")
@RequestScoped
public class AccountBackingBean extends JSFBeanSupport  implements
JSFBeanInterface,
Serializable  {
	
	private static final long serialVersionUID = 1L;
	private long account_id;
	private String account_title;
	private String account_address;
	@NotNull(message="Account's Country information is required.")
	@Min(value=1, message="Account's Country information is required.")
	private int account_country;
	private int account_state;
	@NotNull(message="Account's City information is required.")
	@Min(value=1, message="Account's City information is required.")
	private int account_city;
	private String account_email;
	private String account_phone;
	private Date account_createdon;
	private Boolean account_status = true;
	private Boolean account_iscompany = true;
	private String account_zipcode;
	private SessionDataBean session;
	private String filterBy;
	private String filterValue;
	
	
	/**
	 * Declare all the dao objects here
	 * 
	 */
	@EJB
	AccountDAO accountDao;
	@EJB
	UtilsDAO utilsDao;
	@EJB
	UserDAO userDao;
	
	public AccountBackingBean(){
		System.out.println("accountBackingBean");
		setCurrentAction(WebConstants.ACTION_SECURITY,this.getClass());
		session = BeanFactory.getInstance().getSessionBean();
	}
	public void resetBean(){
		setAccount_id(0L);
		setAccount_address("");
		setAccount_country(0);
		setAccount_state(0);
		setAccount_city(0);
		setAccount_createdon(null);
		setAccount_email("");
		setAccount_iscompany(null);
		setAccount_phone("");
		setAccount_status(null);
		setAccount_title("");
		setAccount_zipcode("");
		
	}
	@PostConstruct
	public void postConstruct() {
		System.out.println("post construct");;
		if (getAction().equals("")) {
			// no action was called, load group data
			accountDetails();
			reset();
			setViewAction();
		}
	}
	
	public void accountDetails() {
		System.out.println("++"+ session.getCustomerModule_selectedAccount());
		if (session.getCustomerModule_selectedAccount()!= 0L) {
			loadAccount(session.getCustomerModule_selectedAccount());
			changeTabPath(0, "/view/customer/accounts/customerAccountForm.xhtml");
			setViewAction();
		} else {
			session.resetCustomerAccountModule();
		}
	}
	private void changeTabPath(int index, String path) {
		InnerTabs d = session.getAccountTabs().get(index);
		d.setPath(path);
		session.getAccountTabs().set(index, d);
		try {
			if (getParameter("fromListing").equals("fromListing")) {
				// don't update index
			} else {
				session.setCustomerModule_accountTabIndex(0);
				
			}
		} catch (Exception e) {
			session.setCustomerModule_accountTabIndex(0);
		}
	}
	
	public void loadAccount(Long id) {
		AccountBackingBean bean= this;
		CsAccount db;
		try {
			db = accountDao.accountDetails(id);
			convert2Bean(db, bean);
			
		} catch (Exception e) {
			changeTabPath(0, "/view/customer/accounts/accountNoSelection.xhtml");
		}
	}
	public long getAccount_id() {
		return account_id;
	}

	public void setAccount_id(long account_id) {
		this.account_id = account_id;
	}

	public String getAccount_title() {
		return account_title;
	}

	public void setAccount_title(String account_title) {
		this.account_title = account_title;
	}

	public String getAccount_address() {
		return account_address;
	}

	public void setAccount_address(String account_address) {
		this.account_address = account_address;
	}

	public int getAccount_country() {
		return account_country;
	}

	public void setAccount_country(int account_country) {
		this.account_country = account_country;
	}

	public int getAccount_state() {
		return account_state;
	}

	public void setAccount_state(int account_state) {
		this.account_state = account_state;
	}

	public int getAccount_city() {
		return account_city;
	}

	public void setAccount_city(int account_city) {
		this.account_city = account_city;
	}

	public String getAccount_email() {
		return account_email;
	}

	public void setAccount_email(String account_email) {
		this.account_email = account_email;
	}

	public String getAccount_phone() {
		return account_phone;
	}

	public void setAccount_phone(String account_phone) {
		this.account_phone = account_phone;
	}

	public Date getAccount_createdon() {
		return account_createdon;
	}

	public void setAccount_createdon(Date account_createdon) {
		this.account_createdon = account_createdon;
	}

	public Boolean getAccount_status() {
		return account_status;
	}

	public void setAccount_status(Boolean account_status) {
		this.account_status = account_status;
	}

	public Boolean getAccount_iscompany() {
		return account_iscompany;
	}

	public void setAccount_iscompany(Boolean account_iscompany) {
		this.account_iscompany = account_iscompany;
	}

	public String getAccount_zipcode() {
		return account_zipcode;
	}

	public void setAccount_zipcode(String account_zipcode) {
		this.account_zipcode = account_zipcode;
	}

	public SessionDataBean getSession() {
		return session;
	}

	public void setSession(SessionDataBean session) {
		this.session = session;
	}
	
	public String getFilterBy() {
		return filterBy;
	}
	public void setFilterBy(String filterBy) {
		this.filterBy = filterBy;
	}
	public String getFilterValue() {
		return filterValue;
	}
	public void setFilterValue(String filterValue) {
		this.filterValue = filterValue;
	}
	
	
	@Override
	public void setViewAction() {
		super.setViewAction();
		setCancelAction(false);
		setResetAction(false);
	}
	@Override
	public String actionListener() {
		// TODO Auto-generated method stub
		long account_id;
		setCurrentAction(getParameter("action"),this.getClass());
		switch (getCurrentAction()) {
		case WebConstants.ACTION_CREATE:
			changeTabPath(0, "/view/customer/accounts/customerAccountForm.xhtml");
			BeanFactory.getInstance().getSessionBean().setSelectedCountry(0);
			BeanFactory.getInstance().getSessionBean().setSelectedState(0);
			resetBean();
			break;
		case WebConstants.ACTION_SAVE:
			createAccount();
			break;
		case WebConstants.ACTION_VIEW:
			accountDetails();
			reset();
			setViewAction();
			break;
		case WebConstants.ACTION_CANCEL:
			accountDetails();
			reset();
			setViewAction();
			break;
		case WebConstants.ACTION_EDIT:
			account_id = Long.valueOf(getParameter("account_id"));
			loadAccount(account_id);
			changeTabPath(0, "/view/customer/accounts/customerAccountForm.xhtml");
			break;
		case WebConstants.ACTION_UPDATE:
			editAccount();
			reset();
			setViewAction();
			break;
		case WebConstants.ACTION_DELETE:
			accountDetails();
			reset();
			setDeleteAction();
			break;
		case WebConstants.ACTION_DELETE_CONFIRMED:
			deleteAccount();
			break;
		}
		
		return (null);
	}


public long createAccount() {
	System.out.println("Create Account CustomerListingsBackingBean");
	CsAccount cs;
	AccountBackingBean a = this;
	Long aId = 0L;
	java.sql.Timestamp createdOn;
	a.setAccount_createdon(new Date());
	createdOn = new Timestamp(a.getAccount_createdon().getTime());
	// session
	SessionDataBean session = BeanFactory.getInstance().getSessionBean();
	UmUser createdBy = new UmUser();
	try {
		 createdBy = userDao.umUserDetails(session.getUserId());
	} catch (UserNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	cs = new CsAccount(session.getCompanyId(), a.getAccount_title(),
			a.getAccount_address(), a.getAccount_country(),
			a.getAccount_state(), a.getAccount_city(),
			a.getAccount_zipcode(), a.getAccount_email(),
			a.getAccount_phone(), createdOn, a.getAccount_iscompany(),
			a.getAccount_status(), createdBy);
	try {
		aId = accountDao.createAccount(cs);
		if (aId > 0) {
			a.setAccount_id(aId);
			this.addMessage(getProperty("message.account.created"));
		}
	} catch (Exception e) {
		this.addError(getProperty("message.account.creation.failed"));
	}
	return aId;
}
public void editAccount() {
	CsAccount accountDb;
	AccountBackingBean a = this;
	Long aId;
	aId = a.getAccount_id();
	try {
		accountDb = accountDao.accountDetails(aId);
		convert2Db(a, accountDb);
		try {
			accountDao.updateAccount(accountDb);
			this.addMessage(getProperty("message.account.updated"));
		} catch (Exception e) {
			this.addError(getProperty("message.account.updation.failed"));
		}
	} catch (AccountNotFoundException e) {
		System.out.println("account not found.");
		this.addError(e.getMessage());
	}
}
public void deleteAccount() {
	try {
		accountDao.deleteAccount(session.getCustomerModule_selectedAccount());
		this.addMessage(getProperty("message.account.deleted"));	
	} catch (Exception deleteExpception) {
		this.addError(getProperty("message.account.deletion.failed"));
	}
	session.setCustomerModule_selectedAccount(0L);
	accountDetails();
	reset();
	setViewAction();
}

public void clearFilter(){
	filterBy = "";
	filterValue = "";
}

public ArrayList<AjaxListStructure> getList() {
	ArrayList<AjaxListStructure> myList = new ArrayList<AjaxListStructure>();
	AjaxListStructure u;

	List<CsAccount> account = null;
	try {
		account = accountDao.listAccounts(session.getCompanyId(), filterBy, filterValue);
	} catch (AccountNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		account = new ArrayList<CsAccount>();
	}
	
	
	for (CsAccount db:account) {
		u = new AjaxListStructure();
		u.setId(db.getAccountId());
		u.setLabel(db.getAccountTitle());
		myList.add(u);
	}
	if (myList.size() == 0) {
		u = new AjaxListStructure();
		u.setId(0L);
		u.setLabel(getProperty("no account found"));
		myList.add(u);
	}
	return myList;
}
	private void convert2Bean(CsAccount db, AccountBackingBean bean) {
		bean.setAccount_id(db.getAccountId());
		bean.setAccount_title(db.getAccountTitle());
		bean.setAccount_address(db.getAccountAddress());
		bean.setAccount_country(db.getAccountCountry());
		bean.setAccount_state(db.getAccountState() == null ? 0 : db
				.getAccountState());
		bean.setAccount_city(db.getAccountCity());
		bean.setAccount_email(db.getAccountEmail());
		bean.setAccount_phone(db.getAccountPhone());
		bean.setAccount_createdon(db.getAccountCreatedon());
		bean.setAccount_status(db.getAccountStatus());
		bean.setAccount_iscompany(db.getAccountIscompany());
		bean.setAccount_zipcode(db.getAccountZipcode());

		BeanFactory.getInstance().getSessionBean().setSelectedCountry(bean.getAccount_country());
		BeanFactory.getInstance().getSessionBean().setSelectedState(bean.getAccount_state());
	}
	private void convert2Db(AccountBackingBean bean, CsAccount db) {
		db.setAccountAddress(bean.getAccount_address());
		db.setAccountCity(bean.getAccount_city());
		db.setAccountCountry(bean.getAccount_country());
		try{
		db.setAccountCreatedon(new Timestamp(bean.getAccount_createdon()
				.getTime()));
		}catch (Exception e) {
			// TODO: handle exception
		}
		db.setAccountEmail(bean.getAccount_email());
		db.setAccountId(bean.getAccount_id());
		db.setAccountIscompany(bean.getAccount_iscompany());
		db.setAccountPhone(bean.getAccount_phone());
		db.setAccountState(bean.getAccount_state());
		db.setAccountStatus(bean.getAccount_status());
		db.setAccountTitle(bean.getAccount_title());
		db.setAccountZipcode(bean.getAccount_zipcode());
	}
}
