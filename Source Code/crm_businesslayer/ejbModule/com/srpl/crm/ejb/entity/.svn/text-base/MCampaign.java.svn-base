package com.srpl.crm.ejb.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//import com.sun.jmx.snmp.Timestamp;

@Entity
@Table(name="m_campaign")
public class MCampaign implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="M_CAMPAIGN_ID_GENERATOR", sequenceName="M_CAMPAIGN_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="M_CAMPAIGN_ID_GENERATOR")
	@Column(name="id")
	private Long id;
	
	@Column(name="company_id")
	private Long company_id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="status")
	private Boolean status;

	@Column(name="start_date")
	private Timestamp startDate;

	@Column(name="end_date")
	private Timestamp endDate;

	@Column(name="launch_date")
	private Timestamp launchDate;
	
	@Column(name="c_type")
	private int cType;
	
	@Column(name="currency")
	private int currency;
	
	@Column(name="budget")
	private float budget;
	
	@Column(name="expected_cost")
	private float expectedCost;
	
	@Column(name="actual_cost")
	private float actualCost;
	
	@Column(name="expected_revenue")
	private float expectedRevenue;
	
	@Column(name="objective")
	private String objective;
	
	@Column(name="description")
	private String description;
	
	@Column(name="potential_customer_email_ids")
	private String potentialCustomerEmailIds;
	
	@Column(name="campaign_image")
	private String campaignImage;
	
	/*
	//many to one
	@ManyToOne
	@JoinColumn(name="c_type")
	private CampaignType compaignType;
	*//*
	//many to one
	@ManyToOne
	@JoinColumn(name="currency")
	private CurrencyType currencyType;
*/
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		Timestamp ts_startDate = new Timestamp(startDate.getTime());
		this.startDate = ts_startDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		Timestamp ts_endDate = new Timestamp(endDate.getTime());
		this.endDate = ts_endDate;
	}

	public Timestamp getLaunchDate() {
		return launchDate;
	}

	public void setLaunchDate(Date launchDate) {
		Timestamp ts_launchDate = new Timestamp(startDate.getTime());
		this.launchDate = ts_launchDate;
	}

	public int getcType() {
		return cType;
	}

	public void setcType(int cType) {
		this.cType = cType;
	}

	public int getCurrency() {
		return currency;
	}

	public void setCurrency(int currency) {
		this.currency = currency;
	}

	public float getBudget() {
		return budget;
	}

	public void setBudget(float budget) {
		this.budget = budget;
	}

	public float getExpectedCost() {
		return expectedCost;
	}

	public void setExpectedCost(float expectedCost) {
		this.expectedCost = expectedCost;
	}

	public float getActualCost() {
		return actualCost;
	}

	public void setActualCost(float actualCost) {
		this.actualCost = actualCost;
	}

	public float getExpectedRevenue() {
		return expectedRevenue;
	}

	public void setExpectedRevenue(float expectedRevenue) {
		this.expectedRevenue = expectedRevenue;
	}

	public String getObjective() {
		return objective;
	}

	public void setObjective(String objective) {
		this.objective = objective;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getStartDateFormatted()
	{
		String S = new SimpleDateFormat("dd/MM/yyyy").format(startDate);
		return S;
	}
	
	public String getEndDateFormatted()
	{
		String S = new SimpleDateFormat("dd/MM/yyyy").format(endDate);
		return S;
	}

	public String getPotentialCustomerEmailIds() {
		return potentialCustomerEmailIds;
	}

	public void setPotentialCustomerEmailIds(String potentialCustomerEmailIds) {
		this.potentialCustomerEmailIds = potentialCustomerEmailIds;
	}

	public String getCampaignImage() {
		return campaignImage;
	}

	public void setCampaignImage(String campaignImage) {
		this.campaignImage = campaignImage;
	}	
	
	public String getDescExcerpt(){
		String descExcerpt = "";
		if(description.length() > 100) {
			String temp = description.substring(0, 100);
			descExcerpt = temp.substring(0, temp.lastIndexOf(" "))+" ...";
		} else {
			descExcerpt = description;
		}	
		return descExcerpt;
	}
	
/*
	public CampaignType getCompaignType() {
		return compaignType;
	}

	public void setCompaignType(CampaignType compaignType) {
		this.compaignType = compaignType;
	}

	public CurrencyType getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(CurrencyType currencyType) {
		this.currencyType = currencyType;
	}
	*/
	
}
