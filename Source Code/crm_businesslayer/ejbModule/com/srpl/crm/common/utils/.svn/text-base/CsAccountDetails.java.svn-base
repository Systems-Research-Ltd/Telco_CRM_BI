package com.srpl.crm.common.utils;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Deprecated
public class CsAccountDetails implements Serializable 
{
	private Long accountId;
	private String accountAddress;
	private Integer accountCity;
	private Integer accountCountry;
	private Timestamp accountCreatedon;
	private String accountEmail;
	private String accountPhone;
	private String accountZipcode;
	private Integer accountState;
	private Boolean accountStatus;
	private String accountTitle;
	private Boolean accountIscompany;
	private List<Long> contactIds;
	
	public CsAccountDetails(){
		
	}
	
	public CsAccountDetails(Long accountId, String accountTitle, String accountAddress, Integer accountCountry, Integer accountState, Integer accountCity, String accountEmail, String accountPhone, String accountZipcode, Timestamp accountCreatedon, Boolean accountIscompany, Boolean accountStatus){
		this.accountId = accountId;
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
	
	public CsAccountDetails(String accountTitle, String accountAddress, Integer accountCountry, Integer accountState, Integer accountCity, String accountEmail, String accountPhone, String accountZipcode, Timestamp accountCreatedon, Boolean accountIscompany, Boolean accountStatus){		
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
	
	public CsAccountDetails(Long accountId, String accountTitle, String accountAddress, Integer accountCountry, Integer accountState, Integer accountCity, String accountEmail, String accountPhone, String accountZipcode, Timestamp accountCreatedon, Boolean accountIscompany, Boolean accountStatus, List<Long> contactIds){
		this.accountId = accountId;
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
		this.setContactIds(contactIds);
	}
	
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
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
		return accountAddress;
	}
	public void setAccountAddress(String accountAddress) {
		this.accountAddress = accountAddress;
	}
	public Integer getAccountCity() {
		return accountCity;
	}
	public void setAccountCity(Integer accountCity) {
		this.accountCity = accountCity;
	}
	public Integer getAccountCountry() {
		return accountCountry;
	}
	public void setAccountCountry(Integer accountCountry) {
		this.accountCountry = accountCountry;
	}
	public Timestamp getAccountCreatedon() {
		return accountCreatedon;
	}
	public void setAccountCreatedon(Timestamp accountCreatedon) {
		this.accountCreatedon = accountCreatedon;
	}
	public String getAccountEmail() {
		return accountEmail;
	}
	public void setAccountEmail(String accountEmail) {
		this.accountEmail = accountEmail;
	}
	public String getAccountPhone() {
		return accountPhone;
	}
	public void setAccountPhone(String accountPhone) {
		this.accountPhone = accountPhone;
	}
	public Integer getAccountState() {
		return accountState;
	}
	public void setAccountState(Integer accountState) {
		this.accountState = accountState;
	}
	public Boolean getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(Boolean accountStatus) {
		this.accountStatus = accountStatus;
	}
	public String getAccountTitle() {
		return accountTitle;
	}
	public void setAccountTitle(String accountTitle) {
		this.accountTitle = accountTitle;
	}
	public List<Long> getContactIds() {
		return contactIds;
	}
	public void setContactIds(List<Long> contactIds) {
		this.contactIds = contactIds;
	}
}
