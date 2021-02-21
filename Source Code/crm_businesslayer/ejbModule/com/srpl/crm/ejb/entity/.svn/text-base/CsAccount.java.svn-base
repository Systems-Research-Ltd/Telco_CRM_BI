package com.srpl.crm.ejb.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.srpl.um.ejb.entity.UmUser;

/**
 * The persistent class for the cs_accounts database table.
 * 
 */
@Entity
@Table(name = "cs_accounts")
public class CsAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "CS_ACCOUNTS_ACCOUNTID_GENERATOR", sequenceName = "CS_ACCOUNTS_ACCOUNT_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CS_ACCOUNTS_ACCOUNTID_GENERATOR")
	@Column(name = "account_id")
	private Long accountId;

	@Column(name = "company_id")
	private Long companyId;

	@Column(name = "account_address")
	private String accountAddress;

	@Column(name = "account_city")
	private Integer accountCity;

	@Column(name = "account_zipcode")
	private String accountZipcode;

	@Column(name = "account_country")
	private Integer accountCountry;

	@Column(name = "account_createdon")
	private Timestamp accountCreatedon;

	@Column(name = "account_email")
	private String accountEmail;

	@Column(name = "account_phone")
	private String accountPhone;

	@Column(name = "account_state")
	private Integer accountState;

	@Column(name = "account_status")
	private Boolean accountStatus;

	@Column(name = "account_title")
	private String accountTitle;

	@Column(name = "account_iscompany")
	private Boolean accountIscompany;

	@Column(name = "mapped_id")
	private Long mappedId;	
	
	@ManyToOne
	@JoinColumn(name = "created_by")
	private UmUser createdBy;

	// bi-directional many-to-one association to CsContact
	@OneToMany(mappedBy = "csAccount")
	private List<CsContactORM> csContacts;

	@Transient
	private String isCorrect;

	public CsAccount() {
	}

	public CsAccount(Long accountId, Long companyId, String accountTitle,
			String accountAddress, Integer accountCountry,
			Integer accountState, Integer accountCity, String accountZipcode,
			String accountEmail, String accountPhone,
			Timestamp accountCreatedon, Boolean accountIscompany,
			Boolean accountStatus) {
		this.accountId = accountId;
		//this.companyId = companyId;
		this.accountTitle = accountTitle;
		this.accountAddress = accountAddress;
		this.accountCountry = accountCountry;
		this.accountState = accountState;
		this.accountCity = accountCity;
		this.accountZipcode = accountZipcode;
		this.accountEmail = accountEmail;
		this.accountPhone = accountPhone;
		this.accountCreatedon = accountCreatedon;
		this.accountIscompany = accountIscompany;
		this.accountStatus = accountStatus;
	}

	public CsAccount(Long companyId, String accountTitle,
			String accountAddress, Integer accountCountry,
			Integer accountState, Integer accountCity, String accountZipcode,
			String accountEmail, String accountPhone,
			Timestamp accountCreatedon, Boolean accountIscompany,
			Boolean accountStatus, UmUser createdBy) {
		this.companyId = companyId;
		this.accountTitle = accountTitle;
		this.accountAddress = accountAddress;
		this.accountCountry = accountCountry;
		this.accountState = accountState;
		this.accountCity = accountCity;
		this.accountZipcode = accountZipcode;
		this.accountEmail = accountEmail;
		this.accountPhone = accountPhone;
		this.accountCreatedon = accountCreatedon;
		this.accountIscompany = accountIscompany;
		this.accountStatus = accountStatus;
		this.createdBy = createdBy;
	}

	public Long getAccountId() {
		return this.accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}	

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(String isCorrect) {
		this.isCorrect = isCorrect;
	}

	public Boolean getAccountIscompany() {
		return accountIscompany;
	}

	public void setAccountIscompany(Boolean accountIscompany) {
		this.accountIscompany = accountIscompany;
	}

	public String getAccountZipcode() {
		return accountZipcode;
	}

	public void setAccountZipcode(String accountZipcode) {
		this.accountZipcode = accountZipcode;
	}

	public String getAccountAddress() {
		return this.accountAddress;
	}

	public void setAccountAddress(String accountAddress) {
		this.accountAddress = accountAddress;
	}

	public Integer getAccountCity() {
		return this.accountCity;
	}

	public void setAccountCity(Integer accountCity) {
		this.accountCity = accountCity;
	}

	public Integer getAccountCountry() {
		return this.accountCountry;
	}

	public void setAccountCountry(Integer accountCountry) {
		this.accountCountry = accountCountry;
	}

	public Timestamp getAccountCreatedon() {
		return this.accountCreatedon;
	}

	public void setAccountCreatedon(Timestamp accountCreatedon) {
		this.accountCreatedon = accountCreatedon;
	}

	public String getAccountEmail() {
		return this.accountEmail;
	}

	public void setAccountEmail(String accountEmail) {
		this.accountEmail = accountEmail;
	}

	public String getAccountPhone() {
		return this.accountPhone;
	}

	public void setAccountPhone(String accountPhone) {
		this.accountPhone = accountPhone;
	}

	public Integer getAccountState() {
		return this.accountState;
	}

	public void setAccountState(Integer accountState) {
		this.accountState = accountState;
	}

	public Boolean getAccountStatus() {
		return this.accountStatus;
	}

	public void setAccountStatus(Boolean accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getAccountTitle() {
		return this.accountTitle;
	}

	public void setAccountTitle(String accountTitle) {
		this.accountTitle = accountTitle;
	}

	public List<CsContactORM> getCsContacts() {
		return this.csContacts;
	}

	public void setCsContacts(List<CsContactORM> csContacts) {
		this.csContacts = csContacts;
	}

	public UmUser getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UmUser createdBy) {
		this.createdBy = createdBy;
	}

	public Long getMappedId() {
		return mappedId;
	}

	public void setMappedId(Long mappedId) {
		this.mappedId = mappedId;
	}
	
}