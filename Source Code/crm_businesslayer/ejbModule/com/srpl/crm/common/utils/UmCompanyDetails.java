package com.srpl.crm.common.utils;

import java.io.Serializable;

import java.util.List;
import java.util.Set;


/**
 * The persistent class for the um_companies database table.
 * 
 */

@Deprecated
public class UmCompanyDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long companyId;
	private String companyAddress;
	private Integer companyCity;
	private Integer companyCountry;
	private String companyDetails;
	private String companyEmail;
	private String companyName;
	private String companyPhone;
	private Integer companyState;
	private Boolean companyStatus;
	private String companyZipcode;
	private List<Integer> umFranchises;
	private List<Integer> umLocations;

    public UmCompanyDetails() {
    }

    // to update company
    public UmCompanyDetails(Long companyId, String companyAddress, Integer companyCity, Integer companyCountry, String companyDetails, String companyEmail, String companyName, String companyPhone, Integer companyState, Boolean companyStatus, String companyZipcode){
    	this.companyId = companyId;
    	this.companyAddress = companyAddress;
    	this.companyCity = companyCity;
    	this.companyCountry = companyCountry;
    	this.companyDetails = companyDetails;
    	this.companyEmail = companyEmail;
    	this.companyName = companyName;
    	this.companyPhone = companyPhone;
    	this.companyState = companyState;
    	this.companyStatus = companyStatus;
    }
    
    // to create company
    public UmCompanyDetails(String companyAddress, Integer companyCity, Integer companyCountry, String companyDetails, String companyEmail, String companyName, String companyPhone, Integer companyState, Boolean companyStatus, String companyZipcode){
    	this.companyAddress = companyAddress;
    	this.companyCity = companyCity;
    	this.companyCountry = companyCountry;
    	this.companyDetails = companyDetails;
    	this.companyEmail = companyEmail;
    	this.companyName = companyName;
    	this.companyPhone = companyPhone;
    	this.companyState = companyState;
    	this.companyStatus = companyStatus;
    }
    
    public UmCompanyDetails(Long companyId, String companyAddress, Integer companyCity, Integer companyCountry, String companyDetails, String companyEmail, String companyName, String companyPhone, Integer companyState, Boolean companyStatus, String companyZipcode, List<Integer> umFranchises, List<Integer> umLocations){
    	this.companyId = companyId;
    	this.companyAddress = companyAddress;
    	this.companyCity = companyCity;
    	this.companyCountry = companyCountry;
    	this.companyDetails = companyDetails;
    	this.companyEmail = companyEmail;
    	this.companyName = companyName;
    	this.companyPhone = companyPhone;
    	this.companyState = companyState;
    	this.companyStatus = companyStatus;
    	this.umFranchises = umFranchises;
    	this.umLocations = umLocations;
    }
    
	public Long getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyAddress() {
		return this.companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public Integer getCompanyCity() {
		return this.companyCity;
	}

	public void setCompanyCity(Integer companyCity) {
		this.companyCity = companyCity;
	}

	public Integer getCompanyCountry() {
		return this.companyCountry;
	}

	public void setCompanyCountry(Integer companyCountry) {
		this.companyCountry = companyCountry;
	}

	public String getCompanyDetails() {
		return this.companyDetails;
	}

	public void setCompanyDetails(String companyDetails) {
		this.companyDetails = companyDetails;
	}

	public String getCompanyEmail() {
		return this.companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyPhone() {
		return this.companyPhone;
	}

	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}

	public Integer getCompanyState() {
		return this.companyState;
	}

	public void setCompanyState(Integer companyState) {
		this.companyState = companyState;
	}

	public Boolean getCompanyStatus() {
		return this.companyStatus;
	}

	public void setCompanyStatus(Boolean companyStatus) {
		this.companyStatus = companyStatus;
	}

	public String getCompanyZipcode() {
		return this.companyZipcode;
	}

	public void setCompanyZipcode(String companyZipcode) {
		this.companyZipcode = companyZipcode;
	}

	public List<Integer> getUmFranchises() {
		return this.umFranchises;
	}

	public void setUmFranchises(List<Integer> umFranchises) {
		this.umFranchises = umFranchises;
	}
	
	public List<Integer> getUmLocations() {
		return this.umLocations;
	}

	public void setUmLocations(List<Integer> umLocations) {
		this.umLocations = umLocations;
	}
	
}