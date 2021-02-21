package com.srpl.crm.common.utils;

import java.io.Serializable;
import com.srpl.um.ejb.entity.UmCompany;

@Deprecated
public class UmFranchiseDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long franchiseId;
	private String franchiseAddress;
	private Integer franchiseCity;
	private Integer franchiseCountry;
	private String franchiseDetails;
	private Integer franchiseState;
	private Boolean franchiseStatus;
	private String franchiseTitle;
	private Long companyId;
	private UmCompany umCompany;

    public UmFranchiseDetails() {
    }
    
    public UmFranchiseDetails(Long franchiseId,String franchiseAddress, Integer franchiseCity, Integer franchiseCountry, String franchiseDetails, Integer franchiseState,Boolean franchiseStatus, String franchiseTitle){
    	   this.franchiseId = franchiseId;
           this.franchiseAddress = franchiseAddress;
           this.franchiseCity = franchiseCity;
           this.franchiseCountry = franchiseCountry;
           this.franchiseDetails = franchiseDetails;
           this.franchiseState = franchiseState;
           this.franchiseStatus = franchiseStatus;
           this.franchiseTitle = franchiseTitle;
           
    }
    
    // for create
    public UmFranchiseDetails(String franchiseAddress, Integer franchiseCity, Integer franchiseCountry, String franchiseDetails, Integer franchiseState,Boolean franchiseStatus, String franchiseTitle, Long companyId){
        this.franchiseAddress = franchiseAddress;
        this.franchiseCity = franchiseCity;
        this.franchiseCountry = franchiseCountry;
        this.franchiseDetails = franchiseDetails;
        this.franchiseState = franchiseState;
        this.franchiseStatus = franchiseStatus;
        this.franchiseTitle = franchiseTitle;
        this.companyId = companyId;
    }
    
    // for update 
    public UmFranchiseDetails(Long franchiseId,String franchiseAddress, Integer franchiseCity, Integer franchiseCountry, String franchiseDetails, Integer franchiseState,Boolean franchiseStatus, String franchiseTitle, Long companyId){
 	    this.franchiseId = franchiseId;
        this.franchiseAddress = franchiseAddress;
        this.franchiseCity = franchiseCity;
        this.franchiseCountry = franchiseCountry;
        this.franchiseDetails = franchiseDetails;
        this.franchiseState = franchiseState;
        this.franchiseStatus = franchiseStatus;
        this.franchiseTitle = franchiseTitle;
        this.companyId = companyId;
        
    }
    
    
    
    // for listing and franchise details,
    public UmFranchiseDetails(Long franchiseId,String franchiseAddress, Integer franchiseCity, Integer franchiseCountry, String franchiseDetails, Integer franchiseState,Boolean franchiseStatus, String franchiseTitle, UmCompany umCompany){
  	     this.franchiseId = franchiseId;
         this.franchiseAddress = franchiseAddress;
         this.franchiseCity = franchiseCity;
         this.franchiseCountry = franchiseCountry;
         this.franchiseDetails = franchiseDetails;
         this.franchiseState = franchiseState;
         this.franchiseStatus = franchiseStatus;
         this.franchiseTitle = franchiseTitle;
         this.umCompany = umCompany;
         
     }
    
    
	public Long getFranchiseId() {
		return this.franchiseId;
	}

	public void setFranchiseId(Long franchiseId) {
		this.franchiseId = franchiseId;
	}

	public String getFranchiseAddress() {
		return this.franchiseAddress;
	}

	public void setFranchiseAddress(String franchiseAddress) {
		this.franchiseAddress = franchiseAddress;
	}

	public Integer getFranchiseCity() {
		return this.franchiseCity;
	}

	public void setFranchiseCity(Integer franchiseCity) {
		this.franchiseCity = franchiseCity;
	}

	public Integer getFranchiseCountry() {
		return this.franchiseCountry;
	}

	public void setFranchiseCountry(Integer franchiseCountry) {
		this.franchiseCountry = franchiseCountry;
	}

	public String getFranchiseDetails() {
		return this.franchiseDetails;
	}

	public void setFranchiseDetails(String franchiseDetails) {
		this.franchiseDetails = franchiseDetails;
	}

	public Integer getFranchiseState() {
		return this.franchiseState;
	}

	public void setFranchiseState(Integer franchiseState) {
		this.franchiseState = franchiseState;
	}

	public Boolean getFranchiseStatus() {
		return this.franchiseStatus;
	}

	public void setFranchiseStatus(Boolean franchiseStatus) {
		this.franchiseStatus = franchiseStatus;
	}

	public String getFranchiseTitle() {
		return this.franchiseTitle;
	}

	public void setFranchiseTitle(String franchiseTitle) {
		this.franchiseTitle = franchiseTitle;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public UmCompany getUmCompany() {
		return umCompany;
	}

	public void setUmCompany(UmCompany umCompany) {
		this.umCompany = umCompany;
	}

	
	
}