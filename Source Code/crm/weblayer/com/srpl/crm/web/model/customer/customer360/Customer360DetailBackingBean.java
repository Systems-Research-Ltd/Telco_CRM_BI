package com.srpl.crm.web.model.customer.customer360;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.bitguiders.util.jsf.JSFBeanInterface;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.srpl.crm.web.controller.BeanFactory;
import com.srpl.crm.ejb.entity.CsContactORM;
import com.srpl.crm.ejb.request.ContactDAO;
import com.srpl.crm.web.common.SessionDataBean;

@ManagedBean(name="customer360Details")
public class Customer360DetailBackingBean extends JSFBeanSupport implements JSFBeanInterface {

	@EJB ContactDAO contactDao;
	
	private SessionDataBean session;
	
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
	@NotNull(message="Contact's Email address is required.")
	@Pattern(message="Enter Vali Email Address.", regexp="^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")
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
	@Pattern(regexp="^[a-zA-z0-9_]*$", message="Only Alphanumeric and underscores are allowed in username.")
	private String contact_username;
	private String contact_pass;
	private String contact_confirm_pass;
	private boolean isCompany;
	private String accountTitle;
	
	
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
	public String getAccountTitle() {
		return accountTitle;
	}
	public void setAccountTitle(String accountTitle) {
		this.accountTitle = accountTitle;
	}
	
	public Customer360DetailBackingBean(){
		session = BeanFactory.getInstance().getSessionBean();
	}
	
	@PostConstruct
	public void postConstruct(){
		String act = getAction();
		if (act.equals("") || act.equals("view")) {
			// no action was called, load group data
			customerDetails();
			reset();
			setViewAction();
		}
	}

	@Override
	public String actionListener() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> getList() {
		// TODO Auto-generated method stub
		return null;
	}

	public void customerDetails(){
		if(session.getCustomerModule_selectedContact() != 0) {
			loadCustomer(session.getCustomerModule_selectedContact());
		} else {
			session.resetCustomerContactModule();
		}
	}
	
	public void loadCustomer(Long id){
		Customer360DetailBackingBean bean = this;
		CsContactORM db = null;
		try{
			db = contactDao.contactDetails(id);
			convert2Bean(db, bean);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	private void convert2Bean(CsContactORM x, Customer360DetailBackingBean element) {
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
		element.setAccountTitle(x.getCsAccount().getAccountTitle());

		BeanFactory.getInstance().getSessionBean().setSelectedCountry(element.getContact_country());
		BeanFactory.getInstance().getSessionBean().setSelectedState(element.getContact_state());
	}
}
