package com.srpl.um.ejb.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.srpl.um.ejb.request.UmPersistence;


/**
 * The persistent class for the um_companies database table.
 * 
 */
@Entity
@Table(name="um_companies" , schema=UmPersistence.schema)
public class UmCompany implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="UM_COMPANIES_COMPANYID_GENERATOR", sequenceName="UM_COMPANIES_COMPANY_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="UM_COMPANIES_COMPANYID_GENERATOR")
	@Column(name="company_id")
	private Long companyId;

	@Column(name="company_address")
	private String companyAddress;

	@Column(name="company_city")
	private Integer companyCity;

	@Column(name="company_country")
	private Integer companyCountry;

	@Column(name="company_details")
	private String companyDetails;

	@Column(name="company_email")
	private String companyEmail;

	@Column(name="company_name")
	private String companyName;

	@Column(name="company_phone")
	private String companyPhone;

	@Column(name="company_state")
	private Integer companyState;

	@Column(name="company_status")
	private Boolean companyStatus;
	
	@Column(name = "company_logo")
	private String companyLogo;

	@Column(name="company_zipcode")
	private String companyZipcode;

	@Column(name="company_url")
	private String companyUrl;
	
	//bi-directional many-to-one association to UmFranchise
	@OneToMany(mappedBy="umCompany", cascade={CascadeType.ALL})
	private Set<UmFranchise> umFranchises;

	//bi-directional many-to-one association to UmLocation
	@OneToMany(mappedBy="umCompany", cascade={CascadeType.ALL})
	private Set<UmLocation> umLocations;

    public UmCompany() {
    }

    public UmCompany(String companyAddress, Integer companyCity, Integer companyCountry, String companyDetails, String companyEmail, String companyName, String companyPhone, Integer companyState, Boolean companyStatus,String companyLogo, String companyZipcode, String  companyUrl){
    	this.companyAddress = companyAddress;
    	this.companyCity = companyCity;
    	this.companyCountry = companyCountry;
    	this.companyDetails = companyDetails;
    	this.companyEmail = companyEmail;
    	this.companyName = companyName;
    	this.companyPhone = companyPhone;
    	this.companyState = companyState;
    	this.companyStatus = companyStatus;
    	this.companyLogo = companyLogo;
    	this.companyZipcode = companyZipcode;
    	this.companyUrl = companyUrl;
    }
    
    public UmCompany(Long companyId,String companyAddress, Integer companyCity, Integer companyCountry, String companyDetails, String companyEmail, String companyName, String companyPhone, Integer companyState, Boolean companyStatus, String companyZipcode, String  companyUrl){
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
    	this.companyZipcode = companyZipcode;
    	this.companyUrl = companyUrl;
    }
    
    
	public String getCompanyUrl() {
		return companyUrl;
	}

	public void setCompanyUrl(String companyUrl) {
		this.companyUrl = companyUrl;
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

	public String getCompanyLogo() {
		return companyLogo;
	}

	public void setCompanyLogo(String companyLogo) {
		this.companyLogo = companyLogo;
	}

	public String getCompanyZipcode() {
		return this.companyZipcode;
	}

	public void setCompanyZipcode(String companyZipcode) {
		this.companyZipcode = companyZipcode;
	}

	public Set<UmFranchise> getUmFranchises() {
		return this.umFranchises;
	}

	public void setUmFranchises(Set<UmFranchise> umFranchises) {
		this.umFranchises = umFranchises;
	}
	
	public Set<UmLocation> getUmLocations() {
		return this.umLocations;
	}

	public void setUmLocations(Set<UmLocation> umLocations) {
		this.umLocations = umLocations;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((companyAddress == null) ? 0 : companyAddress.hashCode());
		result = prime * result
				+ ((companyCity == null) ? 0 : companyCity.hashCode());
		result = prime * result
				+ ((companyCountry == null) ? 0 : companyCountry.hashCode());
		result = prime * result
				+ ((companyDetails == null) ? 0 : companyDetails.hashCode());
		result = prime * result
				+ ((companyEmail == null) ? 0 : companyEmail.hashCode());
		result = prime * result
				+ ((companyId == null) ? 0 : companyId.hashCode());
		result = prime * result
				+ ((companyLogo == null) ? 0 : companyLogo.hashCode());
		result = prime * result
				+ ((companyName == null) ? 0 : companyName.hashCode());
		result = prime * result
				+ ((companyPhone == null) ? 0 : companyPhone.hashCode());
		result = prime * result
				+ ((companyState == null) ? 0 : companyState.hashCode());
		result = prime * result
				+ ((companyStatus == null) ? 0 : companyStatus.hashCode());
		result = prime * result
				+ ((companyUrl == null) ? 0 : companyUrl.hashCode());
		result = prime * result
				+ ((companyZipcode == null) ? 0 : companyZipcode.hashCode());
		result = prime * result
				+ ((umFranchises == null) ? 0 : umFranchises.hashCode());
		result = prime * result
				+ ((umLocations == null) ? 0 : umLocations.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UmCompany other = (UmCompany) obj;
		if (companyAddress == null) {
			if (other.companyAddress != null)
				return false;
		} else if (!companyAddress.equals(other.companyAddress))
			return false;
		if (companyCity == null) {
			if (other.companyCity != null)
				return false;
		} else if (!companyCity.equals(other.companyCity))
			return false;
		if (companyCountry == null) {
			if (other.companyCountry != null)
				return false;
		} else if (!companyCountry.equals(other.companyCountry))
			return false;
		if (companyDetails == null) {
			if (other.companyDetails != null)
				return false;
		} else if (!companyDetails.equals(other.companyDetails))
			return false;
		if (companyEmail == null) {
			if (other.companyEmail != null)
				return false;
		} else if (!companyEmail.equals(other.companyEmail))
			return false;
		if (companyId == null) {
			if (other.companyId != null)
				return false;
		} else if (!companyId.equals(other.companyId))
			return false;
		if (companyLogo == null) {
			if (other.companyLogo != null)
				return false;
		} else if (!companyLogo.equals(other.companyLogo))
			return false;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
			return false;
		if (companyPhone == null) {
			if (other.companyPhone != null)
				return false;
		} else if (!companyPhone.equals(other.companyPhone))
			return false;
		if (companyState == null) {
			if (other.companyState != null)
				return false;
		} else if (!companyState.equals(other.companyState))
			return false;
		if (companyStatus == null) {
			if (other.companyStatus != null)
				return false;
		} else if (!companyStatus.equals(other.companyStatus))
			return false;
		if (companyUrl == null) {
			if (other.companyUrl != null)
				return false;
		} else if (!companyUrl.equals(other.companyUrl))
			return false;
		if (companyZipcode == null) {
			if (other.companyZipcode != null)
				return false;
		} else if (!companyZipcode.equals(other.companyZipcode))
			return false;
		if (umFranchises == null) {
			if (other.umFranchises != null)
				return false;
		} else if (!umFranchises.equals(other.umFranchises))
			return false;
		if (umLocations == null) {
			if (other.umLocations != null)
				return false;
		} else if (!umLocations.equals(other.umLocations))
			return false;
		return true;
	}
	
	
}