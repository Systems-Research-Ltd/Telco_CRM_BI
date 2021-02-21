package com.srpl.crm.web.model.um.customer;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.srpl.crm.web.controller.BeanFactory;
import com.srpl.crm.web.model.common.Address;
import com.srpl.crm.common.utils.CsAccountDetails;
import com.srpl.crm.ejb.entity.CsAccount;
import com.srpl.crm.ejb.exceptions.AccountNotFoundException;
import com.srpl.crm.ejb.request.AccountDAO;

@ManagedBean(name="accounts")
public class AccountBackingBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long account_id;
	@NotBlank(message="Account's Title is required.")
	private String account_title;
	private String account_address;
	@NotNull(message="Account's Country information is required.")
	@Min(value=1, message="Account's Country information is required.")
	private int account_country;
	private int account_state;
	@NotNull(message="Account's City information is required.")
	@Min(value=1, message="Account's City information is required.")
	private int account_city;
	@NotNull(message="Account's Email address is required.")
	@Pattern(message="Enter Valid Email Address.", regexp="^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")
	private String account_email;
	@NotBlank(message="Contact Number is Required.")
	@Pattern(message="The valid pattern for contact number is 012-345-6789, 0123-456-7890, 111-222-333", regexp="\\d{4}-\\d{3}-\\d{4}||\\d{3}-\\d{3}-\\d{4}||\\d{3}-\\d{3}-\\d{3}")
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

}
