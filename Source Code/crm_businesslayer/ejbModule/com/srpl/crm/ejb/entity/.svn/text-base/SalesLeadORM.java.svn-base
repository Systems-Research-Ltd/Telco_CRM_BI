package com.srpl.crm.ejb.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;


/**
 * The persistent class for the sales_leads database table.
 * 
 */
@Entity
@Table(name="sales_leads")
public class SalesLeadORM implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SALES_LEADS_LEADID_GENERATOR", sequenceName="SALES_LEADS_LEAD_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SALES_LEADS_LEADID_GENERATOR")
	@Column(name="lead_id")
	private Long leadId;

	@Column(name="lead_address")
	private String leadAddress;
	
	@Column(name="lead_assigned")
	private Long leadAssigned;
	//private  UmUser umUser;

	@Column(name="lead_email")
	private String leadEmail;

	@Column(name="lead_name")
	private String leadName;

	@Column(name="lead_phone")
	private String leadPhone;

	@Column(name="lead_source")
	private Long leadSource;
	
	@Column(name="lead_company")
	private Long leadCompany;

	@Column(name="lead_status")
	private String leadStatus;
	
	@Column(name="lead_addedon")
	private Timestamp leadAddedOn;
	
	@Column(name = "campaign_source")
	private String campaignSource;
	

	@Column(name = "lead_assigned_date")
	private Timestamp leadAssignedDate;
	
	

    public SalesLeadORM() {
    }
    
    public SalesLeadORM(Long leadId, String leadAddress, Long leadAssigned, String leadEmail, String leadName, String leadPhone, Long leadSource, String leadStatus, Timestamp leadAddedOn, Long leadCompany, String campaignSource,Timestamp leadAssignedDate) {
    	this.leadId = leadId;
    	this.leadAddress = leadAddress;
    	//this.umUser = umUser;
    	this.leadAssigned = leadAssigned;
    	this.leadEmail = leadEmail;
    	this.leadName = leadName;
    	this.leadPhone = leadPhone;
    	this.leadSource = leadSource;
    	this.leadStatus = leadStatus;
    	this.leadAddedOn = leadAddedOn;
    	this.leadCompany = leadCompany;
    	this.campaignSource = campaignSource;
    	this.leadAssignedDate = leadAssignedDate;
    }
    
    public SalesLeadORM(String leadAddress, Long leadAssigned, String leadEmail, String leadName, String leadPhone, Long leadSource, String leadStatus, Timestamp leadAddedOn, Long leadCompany, String campaignSource,Timestamp leadAssignedDate) {
    	this.leadAddress = leadAddress;
    	//this.umUser = umUser;
    	this.leadAssigned = leadAssigned;
    	this.leadEmail = leadEmail;
    	this.leadName = leadName;
    	this.leadPhone = leadPhone;
    	this.leadSource = leadSource;
    	this.leadStatus = leadStatus;
    	this.leadAddedOn = leadAddedOn;
    	this.leadCompany = leadCompany;
    	this.campaignSource =campaignSource;
    	this.leadAssignedDate = leadAssignedDate;
    }

	public Long getLeadId() {
		return this.leadId;
	}

	public void setLeadId(Long leadId) {
		this.leadId = leadId;
	}

	public String getLeadAddress() {
		return this.leadAddress;
	}

	public void setLeadAddress(String leadAddress) {
		this.leadAddress = leadAddress;
	}

	public Long getLeadAssigned() {
		return this.leadAssigned;
	}

	public void setLeadAssigned(Long leadAssigned) {
		this.leadAssigned = leadAssigned;
	}

	public String getLeadEmail() {
		return this.leadEmail;
	}

	public void setLeadEmail(String leadEmail) {
		this.leadEmail = leadEmail;
	}

	public String getLeadName() {
		return this.leadName;
	}

	public void setLeadName(String leadName) {
		this.leadName = leadName;
	}

	public String getLeadPhone() {
		return this.leadPhone;
	}

	public void setLeadPhone(String leadPhone) {
		this.leadPhone = leadPhone;
	}

	public Long getLeadSource() {
		return this.leadSource;
	}

	public void setLeadSource(Long leadSource) {
		this.leadSource = leadSource;
	}	

	public Long getLeadCompany() {
		return leadCompany;
	}

	public void setLeadCompany(Long leadCompany) {
		this.leadCompany = leadCompany;
	}

	public String getLeadStatus() {
		return this.leadStatus;
	}

	public void setLeadStatus(String leadStatus) {
		this.leadStatus = leadStatus;
	}

	public Timestamp getLeadAddedOn() {
		return leadAddedOn;
	}

	public void setLeadAddedOn(Timestamp leadAddedOn) {
		this.leadAddedOn = leadAddedOn;
	}

	public String getCampaignSource() {
		return campaignSource;
	}

	public void setCampaignSource(String campaignSource) {
		this.campaignSource = campaignSource;
	}

	public Timestamp getLeadAssignedDate() {
		return leadAssignedDate;
	}

	public void setLeadAssignedDate(Timestamp leadAssignedDate) {
		this.leadAssignedDate = leadAssignedDate;
	}

	
}