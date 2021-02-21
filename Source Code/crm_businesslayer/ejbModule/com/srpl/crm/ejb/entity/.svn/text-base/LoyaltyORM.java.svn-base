package com.srpl.crm.ejb.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.srpl.um.ejb.entity.UmCompany;

@Entity
@Table(name = "loyalty")
public class LoyaltyORM implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "LOYALTY_LOYALTY_ID_GENERATOR", sequenceName = "LOYALTY_LOYALTY_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LOYALTY_LOYALTY_ID_GENERATOR")
	@Column(name = "loyalty_id")
	private Long loyaltyId;

	@Column(name = "template_id")
	private Integer templateId;

	@Column(name = "loyalty_title")
	private String loyaltyTitle;

	@Column(name = "loyalty_details")
	private String loyaltyDetails;

	@Column(name = "loyalty_launch")
	private String loyaltyLaunch;

	@Column(name = "launch_date_time")
	//private Timestamp launchDateTime;
	private Date launchDateTime;

	@Column(name = "loyalty_status")
	private Boolean loyaltyStatus;

	@Column(name = "loyalty_discount")
	private double loyaltyDiscount;
	
	@ManyToOne
	@JoinColumn(name = "company_id")
	private UmCompany company;

	@OneToMany(mappedBy = "loyalty", fetch = FetchType.EAGER)
	private List<LoyaltyRuleORM> loyaltyRulesList = new ArrayList<LoyaltyRuleORM>();

	public LoyaltyORM() {

	}

	public LoyaltyORM(Long loyaltyId, String loyaltyTitle,
			String loyaltyDetails, String loyaltyLaunch,
			Date launchDateTime, Boolean
			loyaltyStatus, double loyaltyDiscount, UmCompany company) {
		this.loyaltyId = loyaltyId;
		this.loyaltyTitle = loyaltyTitle;
		this.loyaltyDetails = loyaltyDetails;
		this.loyaltyLaunch = loyaltyLaunch;
		this.launchDateTime = launchDateTime;
		this.loyaltyStatus = loyaltyStatus;
		this.loyaltyDiscount = loyaltyDiscount;
		this.company = company;
	}

	public LoyaltyORM(String loyaltyTitle, String loyaltyDetails,
			String loyaltyLaunch, Date launchDateTime,
			Boolean loyaltyStatus, double loyaltyDiscount, UmCompany company) {
		this.loyaltyTitle = loyaltyTitle;
		this.loyaltyDetails = loyaltyDetails;
		this.loyaltyLaunch = loyaltyLaunch;
		this.launchDateTime = launchDateTime;
		this.loyaltyStatus = loyaltyStatus;
		this.loyaltyDiscount = loyaltyDiscount;
		this.company = company;
	}

	// Loyalty getter & setters
	public Long getLoyaltyId() {
		return loyaltyId;
	}

	public String getLoyaltyTitle() {
		return loyaltyTitle;
	}

	public String getLoyaltyDetails() {
		return loyaltyDetails;
	}

	public Boolean getLoyaltyStatus() {
		return loyaltyStatus;
	}

	public void setLoyaltyId(Long loyaltyId) {
		this.loyaltyId = loyaltyId;
	}

	public void setLoyaltyTitle(String loyaltyTitle) {
		this.loyaltyTitle = loyaltyTitle;
	}

	public void setLoyaltyDetails(String loyaltyDetails) {
		this.loyaltyDetails = loyaltyDetails;
	}

	public void setLoyaltyStatus(Boolean loyaltyStatus) {
		this.loyaltyStatus = loyaltyStatus;
	}

	/*public Timestamp getLaunchDateTime() {
		return launchDateTime;
	}

	public void setLaunchDateTime(Date launchDateTime) {
		Timestamp launchDT = new Timestamp(launchDateTime.getTime());
		this.launchDateTime = launchDT;
		// this.launchDateTime = launchDateTime;
	}*/

	/*public Boolean getLoyaltyLaunch() {
		return loyaltyLaunch;
	}

	public void setLoyaltyLaunch(Boolean loyaltyLaunch) {
		this.loyaltyLaunch = loyaltyLaunch;
	}*/

	public List<LoyaltyRuleORM> getLoyaltyRulesList() {
		return loyaltyRulesList;
	}

	public void setLoyaltyRulesList(List<LoyaltyRuleORM> loyaltyRulesList) {
		this.loyaltyRulesList = loyaltyRulesList;
	}

	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	public Double getLoyaltyDiscount() {
		return loyaltyDiscount;
	}

	public void setLoyaltyDiscount(double loyaltyDiscount) {
		this.loyaltyDiscount = loyaltyDiscount;
	}

	public UmCompany getCompany() {
		return company;
	}

	public void setCompany(UmCompany company) {
		this.company = company;
	}

	public String getLoyaltyLaunch() {
		return loyaltyLaunch;
	}

	public void setLoyaltyLaunch(String loyaltyLaunch) {
		this.loyaltyLaunch = loyaltyLaunch;
	}

	public Date getLaunchDateTime() {
		return launchDateTime;
	}

	public void setLaunchDateTime(Date launchDateTime) {
		this.launchDateTime = launchDateTime;
	}

}