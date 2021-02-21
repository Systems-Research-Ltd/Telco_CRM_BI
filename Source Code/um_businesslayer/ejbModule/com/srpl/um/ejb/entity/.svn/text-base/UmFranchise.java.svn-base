package com.srpl.um.ejb.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.srpl.um.ejb.request.UmPersistence;


/**
 * The persistent class for the um_franchises database table.
 * 
 */
@Entity
@Table(name="um_franchises" , schema=UmPersistence.schema)
public class UmFranchise implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="UM_FRANCHISES_FRANCHISEID_GENERATOR", sequenceName="UM_FRANCHISES_FRANCHISE_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="UM_FRANCHISES_FRANCHISEID_GENERATOR")
	@Column(name="franchise_id")
	private Long franchiseId;

	@Column(name="franchise_address")
	private String franchiseAddress;

	@Column(name="franchise_city")
	private Integer franchiseCity;

	@Column(name="franchise_country")
	private Integer franchiseCountry;

	@Column(name="franchise_details")
	private String franchiseDetails;

	@Column(name="franchise_state")
	private Integer franchiseState;

	@Column(name="franchise_status")
	private Boolean franchiseStatus;

	@Column(name="franchise_title")
	private String franchiseTitle;
	
	@Column(name="is_location")
	private Boolean isLocation;

	//bi-directional many-to-one association to UmCompany
    @ManyToOne
	@JoinColumn(name="company_id")
	private UmCompany umCompany;
    
    
    
    public UmFranchise() {
    }
    
    public UmFranchise(String franchiseAddress, Integer franchiseCity, Integer franchiseCountry, String franchiseDetails, Integer franchiseState,Boolean franchiseStatus, String franchiseTitle, UmCompany umCompany){
        this.franchiseAddress = franchiseAddress;
        this.franchiseCity = franchiseCity;
        this.franchiseCountry = franchiseCountry;
        this.franchiseDetails = franchiseDetails;
        this.franchiseState = franchiseState;
        this.franchiseStatus = franchiseStatus;
        this.franchiseTitle = franchiseTitle;
        this.umCompany = umCompany;
        
    }
    
    // to create franchise
    public UmFranchise(String franchiseAddress, Integer franchiseCity, Integer franchiseCountry, String franchiseDetails, Integer franchiseState,Boolean franchiseStatus, String franchiseTitle, Long companyId){
        this.franchiseAddress = franchiseAddress;
        this.franchiseCity = franchiseCity;
        this.franchiseCountry = franchiseCountry;
        this.franchiseDetails = franchiseDetails;
        this.franchiseState = franchiseState;
        this.franchiseStatus = franchiseStatus;
        this.franchiseTitle = franchiseTitle;
        
    }
    
    // to update
    public UmFranchise(Long franchiseId,String franchiseAddress, Integer franchiseCity, Integer franchiseCountry, String franchiseDetails, Integer franchiseState,Boolean franchiseStatus, String franchiseTitle, Long companyId){
        this.franchiseId = franchiseId;
    	this.franchiseAddress = franchiseAddress;
        this.franchiseCity = franchiseCity;
        this.franchiseCountry = franchiseCountry;
        this.franchiseDetails = franchiseDetails;
        this.franchiseState = franchiseState;
        this.franchiseStatus = franchiseStatus;
        this.franchiseTitle = franchiseTitle;
        
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

	public Boolean getIsLocation() {
		return isLocation;
	}

	public void setIsLocation(Boolean isLocation) {
		this.isLocation = isLocation;
	}

	public UmCompany getUmCompany() {
		return this.umCompany;
	}

	public void setUmCompany(UmCompany umCompany) {
		this.umCompany = umCompany;
	}
	
}