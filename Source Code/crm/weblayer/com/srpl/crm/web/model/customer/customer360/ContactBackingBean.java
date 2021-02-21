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
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.bitguiders.util.jsf.JSFBeanInterface;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;
import com.srpl.crm.ejb.entity.CsAccount;
import com.srpl.crm.ejb.entity.CsContactORM;
import com.srpl.crm.ejb.exceptions.AccountNotFoundException;
import com.srpl.crm.ejb.exceptions.ContactNotFoundException;
import com.srpl.crm.ejb.request.AccountDAO;
import com.srpl.crm.ejb.request.ContactDAO;
import com.srpl.crm.ejb.request.UtilsDAO;
import com.srpl.crm.web.common.AjaxListStructure;
import com.srpl.crm.web.common.InnerTabs;
import com.srpl.crm.web.common.SessionDataBean;
import com.srpl.crm.web.controller.BeanFactory;
import com.srpl.um.ejb.entity.UmUser;
import com.srpl.um.ejb.exceptions.UserNotFoundException;
import com.srpl.um.ejb.request.UserDAO;

@ManagedBean(name = "contactBackingBean")
@RequestScoped
public class ContactBackingBean extends JSFBeanSupport  implements
JSFBeanInterface,
Serializable {
	private static final long serialVersionUID = 1L;
	private long contact_id;
	private long account_id;
	private long company_id;
	@Pattern(regexp="^[a-zA-z0-9_ ]*$", message="Only Alphanumeric and underscores are allowed in First Name.")
	private String contact_fname;
	@Pattern(regexp="^[a-zA-z0-9_ ]*$", message="Only Alphanumeric and underscores are allowed in Last Name.")
	private String contact_lname;
	@Pattern(regexp="^[a-zA-z0-9_ ]*$", message="Only Alphanumeric and underscores are allowed in Father Name.")
	private String contact_father_name;
	private String contact_address;
	@NotNull(message="Contact's Country information is required.")
	@Min(value=1, message="Contact's Country information is required.")
	private int contact_country;
	private int contact_state;
	@NotNull(message="Contact's City information is required.")
	@Min(value=1, message="Contact's City information is required.")
	private int contact_city;
	private String contact_zipcode;
	/*@NotNull(message="Contact's Email address is required.")
	@Pattern(message="Enter Vali Email Address.", regexp="^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")*/
	private String contact_email;
	private String contact_phone;
	@NotNull(message="Date of Birth is required.")
	private Date contact_dob;
	private String contact_cnic;
	private String contact_cnic_copy;
	private Date contact_createdon;
	private Boolean contact_status = true;
	@NotBlank(message="Username is required.")
	private long contact_user_id;
	//@Pattern(regexp="^[a-zA-z0-9_]*$", message="Only Alphanumeric and underscores are allowed in username.")
	private String contact_username;
	private String contact_pass;
	private String contact_confirm_pass;
	private boolean isCompany;
	private SessionDataBean session;
	private boolean enablePasswordField;
	private String filterBy;
	private String filterValue;
	
	public enum status {
	    ACTIVE, DISABLE
	}
	
	/**
	 * Declare all the dao objects here
	 * 
	 */
	@EJB
	AccountDAO accountDao;
	@EJB
	ContactDAO contactDao;
	@EJB
	UtilsDAO utilsDao;
	@EJB
	UserDAO userDao;
	
	public ContactBackingBean(){
		isCompany = true;
		setCurrentAction(WebConstants.ACTION_SECURITY,this.getClass());
		session = BeanFactory.getInstance().getSessionBean();
	}

	public void resetBean(){
		setContact_address("");
		setContact_city(0);
		setContact_cnic("");
		setContact_cnic_copy("");
		setContact_confirm_pass("");
		setContact_country(0);
		setContact_createdon(null);
		setContact_dob(null);
		setContact_email("");
		setContact_father_name("");
		setContact_fname("");
		setContact_id(0L);
		setContact_lname("");
		setContact_pass("");
		setContact_phone("");
		setContact_state(0);
		setContact_status(false);
		setContact_user_id(0);
		setContact_username("");
		setContact_zipcode("");
		}
	
	@PostConstruct
	public void postConstruct() {
		String act = getAction();
		if (act.equals("")) {
			// no action was called, load group data
			contactDetails();
			reset();
			setViewAction();
		}else if(act.equals(WebConstants.ACTION_SAVE)){
			enablePasswordField = true;
		}else if(act.equals("actionAjax")){
			setDisabled(false);
		}
	}
	
	public void contactDetails() {
		System.out.println("++"+ session.getCustomerModule_selectedContact());
		if (session.getCustomerModule_selectedContact()!= 0L) {
			loadContact(session.getCustomerModule_selectedContact());
			changeTabPath(0, "/view/customer/contacts/customerContactForm.xhtml");
			changeTabPath(1, "/view/customer/contacts/c360view/subscribe/index.xhtml");
			changeTabPath(2, "/view/customer/contacts/c360view/billing/index.xhtml");
			changeTabPath(3, "/view/customer/contacts/c360view/notes/index.xhtml");
			changeTabPath(4, "/view/customer/contacts/c360view/provisioning/index.xhtml");
			changeTabPath(5, "/view/customer/contacts/c360view/billing/customerBill.xhtml");
			//changeTabPath(2, "/view/customer/contacts/c360view/paymenyHistory/index.xhtml");
			/*changeTabPath(4, "/view/customer/contacts/c360view/payNow/index.xhtml");
			changeTabPath(5, "/view/customer/contacts/c360view/orders/index.xhtml");
			changeTabPath(6, "/view/customer/contacts/c360view/support/index.xhtml");*/
			setViewAction();
		} else {
			session.resetCustomerContactModule();
		}
	}
	
	private void changeTabPath(int index, String path) {
		InnerTabs d = session.getContactTabs().get(index);
		d.setPath(path);
		session.getContactTabs().set(index, d);
		try {
			if (getParameter("fromListing").equals("fromListing")) {
				// don't update index
			} else {
				session.setCustomerModule_contactTabIndex(0);
			}
		} catch (Exception e) {
			session.setCustomerModule_contactTabIndex(0);
		}
	}
	
	public void loadContact(Long id) {
		ContactBackingBean bean= this;
		CsContactORM db;
		try {
			db = contactDao.contactDetails(id);
			convert2ContactBean(db, bean);
		} catch (Exception e) {
			changeTabPath(0, "/view/customer/contacts/contactNoSelection.xhtml");
		}
	}
	
	public long getContact_id() {
		return contact_id;
	}

	public void setContact_id(long contact_id) {
		this.contact_id = contact_id;
	}

	public long getAccount_id() {
		return account_id;
	}

	public void setAccount_id(long account_id) {
		this.account_id = account_id;
	}

	public long getCompany_id() {
		return company_id;
	}

	public void setCompany_id(long company_id) {
		this.company_id = company_id;
	}

	public String getContact_fname() {
		return contact_fname;
	}

	public void setContact_fname(String contact_fname) {
		this.contact_fname = contact_fname;
	}

	public String getContact_lname() {
		return contact_lname;
	}

	public void setContact_lname(String contact_lname) {
		this.contact_lname = contact_lname;
	}

	public String getContact_father_name() {
		return contact_father_name;
	}

	public void setContact_father_name(String contact_father_name) {
		this.contact_father_name = contact_father_name;
	}

	public String getContact_address() {
		return contact_address;
	}

	public void setContact_address(String contact_address) {
		this.contact_address = contact_address;
	}

	public int getContact_country() {
		return contact_country;
	}

	public void setContact_country(int contact_country) {
		this.contact_country = contact_country;
	}

	public int getContact_state() {
		return contact_state;
	}

	public void setContact_state(int contact_state) {
		this.contact_state = contact_state;
	}

	public int getContact_city() {
		return contact_city;
	}

	public void setContact_city(int contact_city) {
		this.contact_city = contact_city;
	}

	public String getContact_zipcode() {
		return contact_zipcode;
	}

	public void setContact_zipcode(String contact_zipcode) {
		this.contact_zipcode = contact_zipcode;
	}

	public String getContact_email() {
		return contact_email;
	}

	public void setContact_email(String contact_email) {
		this.contact_email = contact_email;
	}

	public String getContact_phone() {
		return contact_phone;
	}

	public void setContact_phone(String contact_phone) {
		this.contact_phone = contact_phone;
	}

	public Date getContact_dob() {
		return contact_dob;
	}

	public void setContact_dob(Date contact_dob) {
		this.contact_dob = contact_dob;
	}

	public String getContact_cnic() {
		return contact_cnic;
	}

	public void setContact_cnic(String contact_cnic) {
		this.contact_cnic = contact_cnic;
	}

	public String getContact_cnic_copy() {
		return contact_cnic_copy;
	}

	public void setContact_cnic_copy(String contact_cnic_copy) {
		this.contact_cnic_copy = contact_cnic_copy;
	}

	public Date getContact_createdon() {
		return contact_createdon;
	}

	public void setContact_createdon(Date contact_createdon) {
		this.contact_createdon = contact_createdon;
	}

	public Boolean getContact_status() {
		return contact_status;
	}

	public void setContact_status(Boolean contact_status) {
		this.contact_status = contact_status;
	}

	public long getContact_user_id() {
		return contact_user_id;
	}

	public void setContact_user_id(long contact_user_id) {
		this.contact_user_id = contact_user_id;
	}

	public String getContact_username() {
		return contact_username;
	}

	public void setContact_username(String contact_username) {
		this.contact_username = contact_username;
	}

	public String getContact_pass() {
		return contact_pass;
	}

	public void setContact_pass(String contact_pass) {
		this.contact_pass = contact_pass;
	}

	public String getContact_confirm_pass() {
		return contact_confirm_pass;
	}

	public void setContact_confirm_pass(String contact_confirm_pass) {
		this.contact_confirm_pass = contact_confirm_pass;
	}

	public boolean isCompany() {
		return isCompany;
	}

	public void setCompany(boolean isCompany) {
		this.isCompany = isCompany;
	}

	public SessionDataBean getSession() {
		return session;
	}

	public void setSession(SessionDataBean session) {
		this.session = session;
	}

	public boolean isEnablePasswordField() {
		return enablePasswordField;
	}

	public void setEnablePasswordField(boolean enablePasswordField) {
		this.enablePasswordField = enablePasswordField;
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
		long contact_id;
		setCurrentAction(getParameter("action"),this.getClass());
		switch (getCurrentAction()) {
		case WebConstants.ACTION_CREATE:
			changeTabPath(0, "/view/customer/contacts/customerContactForm.xhtml");
			BeanFactory.getInstance().getSessionBean().setSelectedCountry(0);
			BeanFactory.getInstance().getSessionBean().setSelectedState(0);
			resetBean();
			break;
		case WebConstants.ACTION_SAVE:
			createContact();
			break;
		case WebConstants.ACTION_VIEW:
			contactDetails();
			reset();
			setViewAction();
			break;
		case WebConstants.ACTION_CANCEL:
			contactDetails();
			reset();
			setViewAction();
			break;
		case WebConstants.ACTION_EDIT:
			contact_id = Long.valueOf(getParameter("contact_id"));
			loadContact(contact_id);
			changeTabPath(0, "/view/customer/contacts/customerContactForm.xhtml");
			break;
		case WebConstants.ACTION_UPDATE:
			editContact();
			break;
		case WebConstants.ACTION_DELETE:
			contactDetails();
			reset();
			setDeleteAction();
			break;
		case WebConstants.ACTION_DELETE_CONFIRMED:
			deleteContact();
			break;
		}
		
		return (null);
	}

	public void clearFilter(){
		filterBy = "";
		filterValue = "";
	}	
	
	public ArrayList<AjaxListStructure> getList() {
		ArrayList<AjaxListStructure> myList = new ArrayList<AjaxListStructure>();
		AjaxListStructure u;

		List<CsContactORM> contact = null;
		contact = contactDao.listContacts(session.getCompanyId(), getFilterBy(), getFilterValue());
		System.out.println("contact list size is "+contact);
		for (CsContactORM db:contact) {
			u = new AjaxListStructure();
			u.setId(db.getContactId());
			u.setLabel(db.getContactFname()+" "+db.getContactLname());
			myList.add(u);
		}
		if (myList.size() == 0) {
			u = new AjaxListStructure();
			u.setId(0L);
			u.setLabel(getProperty("message.no.conact.found"));
			myList.add(u);
		}
		return myList;
	}
	
	public Long createContact() {
		CsContactORM cs;
		ContactBackingBean c = this;
		Long cId = 0L;
		java.sql.Timestamp createdOn;
		java.sql.Timestamp contactDob;
		//AccountBackingBean a = BeanFactory.getInstance().getAccountBean();
		CsAccount accountDb;
		// get account

		c.setContact_createdon(new Date());
		createdOn = new Timestamp(c.getContact_createdon().getTime());
		contactDob = new Timestamp(c.getContact_dob().getTime());

		SessionDataBean session = BeanFactory.getInstance().getSessionBean();

		// Now check if the contact is individual, if so create an account
		// against contact
		UmUser createdBy = new UmUser();
		try {
			 createdBy = userDao.umUserDetails(session.getUserId());
		} catch (UserNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (!c.isCompany()) {
			// if the account type is individual
			// populate account
			

			Long aId = 0L;
			//a.setAccount_createdon(new Date());
			accountDb = new CsAccount(session.getCompanyId(), c.getContact_fname()+" "+c.getContact_lname(),
					c.getContact_address(), c.getContact_country(),
					c.getContact_state(), c.getContact_city(),
					c.getContact_zipcode(), c.getContact_email(),
					c.getContact_phone(), createdOn, c.isCompany(),
					c.getContact_status(), createdBy);
			try {
				aId = accountDao.createAccount(accountDb);
				if (aId > 0) {
					c.setAccount_id(aId);
					this.addMessage(getProperty("message.account.created"));
				}
			} catch (Exception e) {
				this.addError(getProperty("message.account.creation.failed"));
			}
		}
		/*UmUser user = null;
		Long uId = 0L;
        user = new UmUser(c.getContact_username(), c.getContact_pass(),  c.getContact_fname(), c.getContact_lname(),
                        c.getContact_address(),  c.getContact_country(), c.getContact_state(), c.getContact_city(), c.getContact_zipcode(), c.getContact_email(),  c.getContact_phone(),
                        " ", 0L, " " , false, session.getCompanyId(), createdOn, false, false, true);
        try{
                uId =userDao.createCustomer(user);
                if(uId > 0){
                        c.setContact_user_id(uId);
                        this.addMessage("User Successfully Created.");
                }
        
        }catch(Exception e){
                this.addError("User Creation Failed.");
        }*/

		// Call ejb and upon completion of db entry, return status
		cs = new CsContactORM(c.getContact_fname(), c.getContact_lname(),
				c.getContact_father_name(), c.getContact_address(),
				c.getContact_country(), c.getContact_state(),
				c.getContact_city(), c.getContact_zipcode(),
				c.getContact_email(), c.getContact_phone(),
				c.getContact_cnic(), c.getContact_cnic_copy(), contactDob,
				createdOn, c.getContact_status(), session.getCompanyId(),
				c.getContact_username(), c.getContact_pass(), createdBy);
		try {
			cId = contactDao.createCustomerContact(cs, c.getAccount_id());
			//cId = contactDao.createCustomerContact(cs, c.getAccount_id(),uId);
			if (cId > 0) {
				c.setContact_id(cId);
				this.addMessage(getProperty("message.contact.created"));
			}
		} catch (AccountNotFoundException e) {
			this.addError(e.getMessage());
		} catch (Exception e) {
			this.addError(getProperty("message.contact.creatiom.failed"));
		}

		return cId;
	}
	
	public String editContact() {
		CsContactORM contactDb;
		ContactBackingBean c= this;
		Long cId;
		java.sql.Timestamp contactDob;
		contactDob = new Timestamp(c.getContact_dob().getTime());

		//cId = Long.valueOf(this.getParameter("cId"));
		cId = this.contact_id;
		contactDb = new CsContactORM();

		try {
			contactDb = contactDao.contactDetails(cId);

			// Account id is not set here.
			contactDb.setContactAddress(c.getContact_address());
			contactDb.setContactCity(c.getContact_city());
			contactDb.setContactCnic(c.getContact_cnic());
			contactDb.setContactCnicCopy(c.getContact_cnic_copy());
			contactDb.setContactCountry(c.getContact_country());
			contactDb.setContactDob(contactDob);
			contactDb.setContactEmail(c.getContact_email());
			contactDb.setContactFatherName(c.getContact_father_name());
			contactDb.setContactFname(c.getContact_fname());
			contactDb.setContactLname(c.getContact_lname());
			contactDb.setContactPhone(c.getContact_phone());
			contactDb.setContactState(c.getContact_state());
			contactDb.setContactStatus(c.getContact_status());
			contactDb.setContactZipcode(c.getContact_zipcode());
			
			UmUser createdBy = new UmUser();
			try {
				 createdBy = userDao.umUserDetails(session.getUserId());
			} catch (UserNotFoundException e1) {
				e1.printStackTrace();
			}

			try {
				contactDao.updateContact(contactDb, contactDb.getCsAccount().getAccountId(),
						contactDb.getCsAccount().getAccountId());
				this.addMessage(getProperty("message.contact.updated"));
			} catch (Exception e) {
				this.addError(getProperty("message.contact.updation.failed"));
			}
		} catch (ContactNotFoundException e) {
			this.addError(e.getMessage());
		}

		return "contactList";
	}
	
	public void deleteContact() {
		try {
			contactDao.deleteContact(session.getCustomerModule_selectedContact());
			this.addMessage(getProperty("message.contact.deleted"));	
		} catch (Exception deleteExpception) {
			this.addError(getProperty("message.contact.deletion.failed"));
		}
		session.setCustomerModule_selectedContact(0L);
		contactDetails();
		reset();
		setViewAction();
	}
	private void convert2ContactBean(CsContactORM x, ContactBackingBean element) {
		element.setContact_address(x.getContactAddress());
		element.setContact_city(x.getContactCity());
		element.setContact_cnic(x.getContactCnic());
		element.setContact_cnic_copy(x.getContactCnicCopy());
		element.setContact_country(x.getContactCountry());
		element.setContact_createdon(x.getContactCreatedon());
		element.setContact_dob(x.getContactDob());
		element.setContact_email(x.getContactEmail());
		element.setContact_father_name(x.getContactFatherName());
		element.setContact_fname(x.getContactFname());
		element.setContact_id(x.getContactId());
		element.setContact_lname(x.getContactLname());
		element.setContact_phone(x.getContactPhone());
		element.setContact_state(x.getContactState());
		element.setContact_status(x.getContactStatus());
		element.setContact_zipcode(x.getContactZipcode());
		element.setCompany(x.getCsAccount().getAccountIscompany());
		element.setContact_username(x.getContactUser().getUserName());

		BeanFactory.getInstance().getSessionBean().setSelectedCountry(element.getContact_country());
		BeanFactory.getInstance().getSessionBean().setSelectedState(element.getContact_state());
	}
	public List<AjaxListStructure> getCorporateAccountList(){
		ArrayList<AjaxListStructure> myList = new ArrayList<AjaxListStructure>();
		AjaxListStructure element;
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		try{
			List<CsAccount> accountDbList = utilsDao.listAccountsByType(true, session.getCompanyId());
				for(CsAccount x:accountDbList){
					element = new AjaxListStructure();
					
					element.setId(x.getAccountId());
					element.setLabel(x.getAccountTitle());
					myList.add(element);
				}
		}
		catch(Exception e){
			System.out.println("error accessing the db.");
		}
		
		return myList;
	}
}
