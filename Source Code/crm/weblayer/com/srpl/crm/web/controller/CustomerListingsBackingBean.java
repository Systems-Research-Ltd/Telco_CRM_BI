package com.srpl.crm.web.controller;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import com.bitguiders.util.jsf.JSFBeanSupport;
import com.srpl.crm.web.controller.BeanFactory;
import com.srpl.crm.ejb.entity.CsAccount;
import com.srpl.crm.ejb.entity.CsContactORM;
import com.srpl.crm.ejb.exceptions.AccountNotFoundException;
import com.srpl.crm.ejb.exceptions.ContactNotFoundException;
import com.srpl.crm.ejb.request.AccountDAO;
import com.srpl.crm.ejb.request.ContactDAO;
import com.srpl.crm.ejb.request.UtilsDAO;
import com.srpl.crm.web.common.SessionDataBean;
import com.srpl.crm.web.model.common.ActionListenerBackingBean;
import com.srpl.crm.web.model.common.ColumnModel;
import com.srpl.crm.web.model.um.customer.AccountBackingBean;
import com.srpl.crm.web.model.um.customer.ContactBackingBean;
import com.srpl.um.ejb.entity.UmUser;
import com.srpl.um.ejb.exceptions.UserNotFoundException;
import com.srpl.um.ejb.request.UserDAO;

@Deprecated
@ManagedBean(name = "customerListings")
@ViewScoped
public class CustomerListingsBackingBean extends JSFBeanSupport implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public List<AccountBackingBean> accountList = new ArrayList<AccountBackingBean>();
	public List<ContactBackingBean> contactList = new ArrayList<ContactBackingBean>();
	public static List<ColumnModel> accountColumns;
	public static List<ColumnModel> contactColumns;
	
	static{

		accountColumns=new ArrayList<ColumnModel>();
		accountColumns.add(new ColumnModel("account_id", "ID"));
		accountColumns.add(new ColumnModel("account_title", "TITLE"));
		accountColumns.add(new ColumnModel("account_email", "EMAIL"));
		accountColumns.add(new ColumnModel("account_phone", "PHONE NO"));
		
		contactColumns=new ArrayList<ColumnModel>();
		contactColumns.add(new ColumnModel("contact_id", "ID"));
		contactColumns.add(new ColumnModel("contact_fname", "TITLE"));
		contactColumns.add(new ColumnModel("contact_email", "EMAIL"));
		contactColumns.add(new ColumnModel("contact_phone", "PHONE NO"));
	}
	

	public List<ColumnModel> getAccountColumns() {
		return accountColumns;
	}

	public void setAccountColumns(List<ColumnModel> accountColumns) {
		CustomerListingsBackingBean.accountColumns = accountColumns;
	}


	public List<ColumnModel> getContactColumns() {
		return contactColumns;
	}

	public void setContactColumns(List<ColumnModel> contactColumns) {
		CustomerListingsBackingBean.contactColumns = contactColumns;
	}


	@EJB AccountDAO accountDao;
	@EJB ContactDAO contactDao;
	@EJB UtilsDAO utilsDao;
	@EJB UserDAO userDao;

	public String createAccount(){
		System.out.println("Create Account CustomerListingsBackingBean");
		Long aId;
		java.sql.Timestamp createdOn;
		AccountBackingBean a = BeanFactory.getInstance().getAccountBean();
		a.setAccount_createdon(new Date());
		createdOn = new Timestamp(a.getAccount_createdon().getTime());
		
		//session
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		UmUser createdBy = new UmUser();
		try {
			 createdBy = userDao.umUserDetails(session.getUserId());
		} catch (UserNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//Call ejb and upon completion of db entry, return status
						//CsAccount(String accountTitle, String accountAddress, Integer accountCountry, Integer accountState, Integer accountCity, String accountZipcode, String accountEmail, String accountPhone, Timestamp accountCreatedon, Boolean accountIscompany, Boolean accountStatus)
		CsAccount cs = new CsAccount(session.getCompanyId(), a.getAccount_title(), a.getAccount_address(), a.getAccount_country(), a.getAccount_state(), a.getAccount_city(), a.getAccount_zipcode(), a.getAccount_email(), a.getAccount_phone(), createdOn, a.getAccount_iscompany(), a.getAccount_status(), createdBy);
		try{
			aId = accountDao.createAccount(cs);
			if(aId > 0){
				a.setAccount_id(aId);
				this.addMessage("Account Successfully Created.");
			}
		}
		catch(Exception e){
			this.addError("Account Creation Failed.");
		}
		
		ActionListenerBackingBean al = BeanFactory.getInstance().getUserProfileBackingBean();
		al.setSelectedAction("");
		return "accountList";
	}
	
	public String editAccount(){
		System.out.println("Edit Account CustomerListingsBackingBean");
		Long aId;
		java.sql.Timestamp createdOn;
		AccountBackingBean a = BeanFactory.getInstance().getAccountBean();
		createdOn = new Timestamp(a.getAccount_createdon().getTime());
		
		CsAccount accountDb = new CsAccount();
		
		//aId = Long.valueOf(this.getParameter("aId"));
		aId = a.getAccount_id();
		try{
			accountDb = accountDao.accountDetails(aId);
			
			accountDb.setAccountAddress(a.getAccount_address());
			accountDb.setAccountCity(a.getAccount_city());
			accountDb.setAccountCountry(a.getAccount_country());
			accountDb.setAccountCreatedon(createdOn);
			accountDb.setAccountEmail(a.getAccount_email());
			//accountDb.setAccountId(aId);
			accountDb.setAccountIscompany(a.getAccount_iscompany());
			accountDb.setAccountPhone(a.getAccount_phone());
			accountDb.setAccountState(a.getAccount_state());
			accountDb.setAccountStatus(a.getAccount_status());
			accountDb.setAccountTitle(a.getAccount_title());
			accountDb.setAccountZipcode(a.getAccount_zipcode());
			
			try{
				accountDao.updateAccount(accountDb);
				this.addMessage("Account Successfully Updated.");
			}
			catch(Exception e){
				this.addError("Account Update Failed.");
			}
		}
		catch(AccountNotFoundException e){
			System.out.println("account not found.");
			this.addError(e.getMessage());
		}

		ActionListenerBackingBean al = BeanFactory.getInstance().getUserProfileBackingBean();
		al.setSelectedAction("");
		return "accountList";
	}


	public List<AccountBackingBean> accountList(){
		System.out.println("here in getAccountList");
		AccountBackingBean element;
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		try{
			List<CsAccount> accountDbList = accountDao.listAccounts(session.getCompanyId());
			System.out.println("List found.");
			System.out.println(accountDbList.size());
			if(accountDbList.size() != accountList.size()){
				accountList.clear();
				for(CsAccount x:accountDbList){
					element = new AccountBackingBean();
					
					element.setAccount_id(x.getAccountId());
					element.setAccount_title(x.getAccountTitle());
					element.setAccount_address(x.getAccountAddress());
					element.setAccount_country(x.getAccountCountry());
					element.setAccount_state(x.getAccountState()==null ? 0:x.getAccountState());
					element.setAccount_city(x.getAccountCity());
					element.setAccount_email(x.getAccountEmail());
					element.setAccount_phone(x.getAccountPhone());
					element.setAccount_createdon(x.getAccountCreatedon());
					element.setAccount_status(x.getAccountStatus());
					element.setAccount_iscompany(x.getAccountIscompany());
					element.setAccount_zipcode(x.getAccountZipcode());
					
					//System.out.println(element.getAccount_title());
					accountList.add(element);
				}
			}
		}
		catch(AccountNotFoundException e){
			this.addError(e.getMessage());
		}
		catch(Exception e){
			System.out.println("error accessing the db.");
			e.printStackTrace();
		}

		ActionListenerBackingBean al = BeanFactory.getInstance().getUserProfileBackingBean();
		al.setSelectedAction("");
		return accountList;
	}

	public void deleteAccount(ActionEvent event)
	{
		Long id = 0L;
		Object val;
		try{
			val = event.getComponent().getAttributes().get("del_id");
			id = Long.valueOf(val.toString());
		}
		catch(Exception e){
			System.out.println("exception here.");
			e.printStackTrace();
		}
		
		try{
			accountDao.deleteAccount(id);
			this.addMessage("Contact Successfully Deleted.");
		}
		catch(Exception e){
			this.addError("Contact Deletion Failed.");
		}
		System.out.println("ends.");
	}

	public List<AccountBackingBean> accountListByType(Boolean c){
		System.out.println("here in getAccountListByType");
		AccountBackingBean element;
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		try{
			List<CsAccount> accountDbList = utilsDao.listAccountsByType(c, session.getCompanyId());
			System.out.println("List found.");
			System.out.println(accountDbList.size());
			if(accountDbList.size() != accountList.size()){
				accountList.clear();
				for(CsAccount x:accountDbList){
					element = new AccountBackingBean();
					
					element.setAccount_id(x.getAccountId());
					element.setAccount_title(x.getAccountTitle());
					element.setAccount_address(x.getAccountAddress());
					element.setAccount_country(x.getAccountCountry());
					element.setAccount_state(x.getAccountState()==null ? 0:x.getAccountState());
					element.setAccount_city(x.getAccountCity());
					element.setAccount_email(x.getAccountEmail());
					element.setAccount_phone(x.getAccountPhone());
					element.setAccount_createdon(x.getAccountCreatedon());
					element.setAccount_status(x.getAccountStatus());
					element.setAccount_iscompany(x.getAccountIscompany());
					element.setAccount_zipcode(x.getAccountZipcode());
					
					//System.out.println(element.getAccount_title());
					accountList.add(element);
				}
			}
		}
		catch(Exception e){
			System.out.println("error accessing the db.");
		}
		
		ActionListenerBackingBean al = BeanFactory.getInstance().getUserProfileBackingBean();
		al.setSelectedAction("");
		return accountList;
	}
	
	public String createContact(){
		System.out.println("Create Contact CustomerListingsBackingBean");
		Long cId;
		java.sql.Timestamp createdOn;
		java.sql.Timestamp contactDob;
		AccountBackingBean a = BeanFactory.getInstance().getAccountBean();
		ContactBackingBean c = BeanFactory.getInstance().getContactBean();
		c.setContact_createdon(new Date());
		createdOn = new Timestamp(c.getContact_createdon().getTime());
		contactDob = new Timestamp(c.getContact_dob().getTime());
		
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		
		//Now check if the contact is individual, if so create an account against contact
		if(a.getAccount_iscompany()){
			//its a company account
			//set account id
			c.setAccount_id(a.getAccount_id());
		}
		else{
			//if the account type is individual
			//populate account
			a.setAccount_address(c.getContact_address());
			a.setAccount_city(c.getContact_city());
			a.setAccount_country(c.getContact_country());
			a.setAccount_createdon(c.getContact_createdon());
			a.setAccount_email(c.getContact_email());
			a.setAccount_phone(c.getContact_phone());
			a.setAccount_state(c.getContact_state());
			a.setAccount_status(c.getContact_status());
			a.setAccount_title(c.getContact_fname()+" "+c.getContact_lname());
			a.setAccount_zipcode(c.getContact_zipcode());
			
			this.createAccount();
			c.setAccount_id(a.getAccount_id());
		}
		UmUser createdBy = new UmUser();
		try {
			 createdBy = userDao.umUserDetails(session.getUserId());
		} catch (UserNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//Call ejb and upon completion of db entry, return status
		CsContactORM cs = new CsContactORM(c.getContact_fname(), c.getContact_lname(), c.getContact_father_name(), c.getContact_address(), c.getContact_country(), c.getContact_state(), c.getContact_city(), c.getContact_zipcode(), c.getContact_email(), c.getContact_phone(), c.getContact_cnic(), c.getContact_cnic_copy(), contactDob, createdOn, c.getContact_status(), session.getCompanyId(), c.getContact_username(), c.getContact_pass(), createdBy);
		try{
			cId = contactDao.createContact(cs, c.getAccount_id());
			if(cId > 0){
				c.setContact_id(cId);
				this.addMessage("Contact Successfully Created.");
			}
		}
		catch(AccountNotFoundException e){
			this.addError(e.getMessage());
		}
		catch(Exception e){
			this.addError("Contact Creation Failed.");
		}

		ActionListenerBackingBean al = BeanFactory.getInstance().getUserProfileBackingBean();
		al.setSelectedAction("");
		return "contactList";
	}
	
	public String editContact(){
		System.out.println("Edit Contact CustomerListingsBackingBean");
		Long cId;
		java.sql.Timestamp contactDob;
		ContactBackingBean c = BeanFactory.getInstance().getContactBean();
		AccountBackingBean a = BeanFactory.getInstance().getAccountBean();
		contactDob = new Timestamp(c.getContact_dob().getTime());
		
		cId = Long.valueOf(this.getParameter("cId"));
		CsContactORM contactDb = new CsContactORM();
		
		try{
			contactDb = contactDao.contactDetails(cId);
			
			//Account id is not set here.
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
				 createdBy = userDao.umUserDetails(BeanFactory.getInstance().getSessionBean().getUserId());
			} catch (UserNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try{
				contactDao.updateContact(contactDb, c.getAccount_id(), contactDb.getCsAccount().getAccountId());
				this.addMessage("Contact Successfully Updated.");
				if(a.getAccount_country() != c.getContact_country() || a.getAccount_state() != c.getContact_state() || a.getAccount_city() != c.getContact_city()){
					a.setAccount_country(c.getContact_country());
					a.setAccount_state(c.getContact_state());
					a.setAccount_city(c.getContact_city());
					
					try{
						this.editAccount();
					}
					catch(Exception e){
						this.addError("Account Update Failed.");
					}
				}
			}
			catch(AccountNotFoundException e){
				this.addError(e.getMessage());
			}
			catch(Exception e){
				this.addError("Contact Update Failed.");
			}
		}catch(ContactNotFoundException e){
			this.addError(e.getMessage());
		}

		ActionListenerBackingBean al = BeanFactory.getInstance().getUserProfileBackingBean();
		al.setSelectedAction("");
		return "contactList";
	}

	public void deleteContact(ActionEvent event)
	{
		Long id = 0L;
		Object val;
		try{
			val = event.getComponent().getAttributes().get("del_id");
			id = Long.valueOf(val.toString());
		}
		catch(Exception e){
			System.out.println("exception here.");
			e.printStackTrace();
		}
		
		try{
			contactDao.deleteContact(id);
			this.addMessage("Contact Successfully Deleted.");
		}
		catch(Exception e){
			this.addError("Contact Deletion Failed.");
		}
	}

	public List<ContactBackingBean> contactList(){
		System.out.println("here in getContactList");
		ContactBackingBean element;
		List<CsContactORM> contactDbList;
		
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		try{
			contactDbList = contactDao.listContacts(session.getCompanyId());
			System.out.println("List found.");
			System.out.println(contactDbList.size());
			if(contactDbList.size() != contactList.size()){
				contactList.clear();
				for(CsContactORM x:contactDbList){
					element = new ContactBackingBean();
					
					//element.setAccount_id(x.getAccountId());
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

					contactList.add(element);
				}
			}
		}
		catch(Exception e){
			System.out.println("error accessing the db.");
		}
		
		ActionListenerBackingBean al = BeanFactory.getInstance().getUserProfileBackingBean();
		al.setSelectedAction("");
		return contactList;
	}
}
