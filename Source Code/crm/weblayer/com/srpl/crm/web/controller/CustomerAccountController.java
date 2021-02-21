package com.srpl.crm.web.controller;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.bitguiders.util.jsf.JSFBeanInterface;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;
import com.srpl.crm.ejb.entity.CsAccount;
import com.srpl.crm.ejb.exceptions.AccountNotFoundException;
import com.srpl.crm.ejb.request.AccountDAO;
import com.srpl.crm.ejb.request.UtilsDAO;
import com.srpl.crm.web.common.SessionDataBean;
import com.srpl.crm.web.model.common.ColumnModel;
import com.srpl.um.ejb.entity.UmUser;
import com.srpl.um.ejb.exceptions.UserNotFoundException;
import com.srpl.um.ejb.request.UserDAO;

/**
 * @author Hammad Hassan Khan
 * 
 */
@ManagedBean(name = "customerAccountController")
public class CustomerAccountController extends JSFBeanSupport implements
		JSFBeanInterface, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static List<ColumnModel> accountColumns;
	public Boolean update = true;

	static {
		accountColumns = new ArrayList<ColumnModel>();
		accountColumns.add(new ColumnModel("accountId", "ID"));
		accountColumns.add(new ColumnModel("accountTitle", "TITLE"));
		accountColumns.add(new ColumnModel("accountEmail", "EMAIL"));
		accountColumns.add(new ColumnModel("accountPhone", "PHONE NO"));
	}
	

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
		System.out.println(this.account_state);
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

	public CustomerAccountController(){
		setCurrentAction(WebConstants.ACTION_SECURITY,this.getClass());
	}
	/**
	 * All the class variables i.e. selected list are declared here
	 */
	public List<CustomerAccountController> accountList;

	/**
	 * Getters Setters
	 */
	public long createAccount(CsAccount cs, CustomerAccountController a) {
		System.out.println("Create Account CustomerListingsBackingBean");
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

	public void editAccount(CsAccount accountDb, CustomerAccountController a) {
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

	@Override
	public List<CsAccount> getList() {
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		List<CsAccount> accountDbList = null;
		try {
			accountDbList = accountDao.listAccounts(session
					.getCompanyId());
		} catch (AccountNotFoundException e) {
			this.addError(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return accountDbList;
	}

	public void deleteAccount(long id) {
		try {
			accountDao.deleteAccount(id);
			this.addMessage(getProperty("message.account.deleted"));
		} catch (Exception e) {
			this.addError(getProperty("message.account.deletion.failed"));
		}
	}

	public List<ColumnModel> getAccountColumns() {
		return accountColumns;
	}

	public void setAccountColumns(List<ColumnModel> accountColumns) {
		CustomerAccountController.accountColumns = accountColumns;
	}

	public String actionListener() {

		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		CustomerAccountController bean = this;
		CsAccount db;
		Long id;
		setCurrentAction(getAction(),this.getClass());
		switch (getCurrentAction()) {
		case WebConstants.ACTION_CREATE:
			bean.resetBean();
			//address drop down fixation
			session.setSelectedCountry(0);
			session.setSelectedState(0);
			return WebConstants.ACTION_CRUD;

		case WebConstants.ACTION_SAVE:
			db = new CsAccount();
			id = createAccount(db, bean);
			bean.setAccount_id(id);
			return WebConstants.ACTION_LIST;

		case WebConstants.ACTION_VIEW:
			id = Long.valueOf(getParameter("row_id").toString());
			try {
				db = accountDao.accountDetails(id);
				convert2Bean(db, bean);
				return WebConstants.ACTION_CRUD;
			} catch (Exception e) {
				// handle exception
				addError(getProperty("message.account.load"));
			}
			return WebConstants.ACTION_LIST;

		case WebConstants.ACTION_CANCEL:
			return WebConstants.ACTION_LIST;

		case WebConstants.ACTION_EDIT:
			id = Long.valueOf(getParameter("row_id").toString());
			try {
				db = accountDao.accountDetails(id);
				convert2Bean(db, bean);
				return WebConstants.ACTION_CRUD;
			} catch (Exception e) {
				// handle exception
				addError(getProperty("message.account.load"));
			}
			return WebConstants.ACTION_LIST;

		case WebConstants.ACTION_UPDATE:
			id = Long.valueOf(getParameter("row_id").toString());
			db = new CsAccount();
			//bean.setAccount_id(id);
			editAccount(db, bean);
			return WebConstants.ACTION_LIST;

		case WebConstants.ACTION_DELETE:
			id = Long.valueOf(getParameter("row_id").toString());
			try {
				db = accountDao.accountDetails(id);
				convert2Bean(db, bean);
				return WebConstants.ACTION_CRUD;
			} catch (Exception e) {
				// handle exception
				addError(getProperty("message.account.load"));
			}
			setListAction(true);
			return WebConstants.ACTION_LIST;

		case WebConstants.ACTION_DELETE_CONFIRMED:
			id = Long.valueOf(getParameter("row_id").toString());
			deleteAccount(id);
			return WebConstants.ACTION_LIST;

		default:
			setListAction(true);
			return WebConstants.ACTION_LIST;
		}
	}

	private void resetBean() {
		this.account_id = 0L;
		this.account_title = "";
		this.account_address = ""; 
		this.account_country = 0;
		this.account_state = 0;
		this.account_city = 0;
		this.account_email = "";
		this.account_phone = "";
		this.account_createdon = new Date();
		this.account_status = true;
		this.account_iscompany = true;
		this.account_zipcode = "";
	}
	private void convert2Bean(CsAccount db, CustomerAccountController bean) {
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

	private void convert2Db(CustomerAccountController bean, CsAccount db) {
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
