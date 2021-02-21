package com.srpl.crm.web.model;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Hammad Hassan Khan
 *
 */
@ManagedBean(name="campaign")
@ViewScoped
@Deprecated
public class CampaignBackingBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id = 0L;
	private Long company_id = 0L;
	@NotBlank(message="Title is Required.")
	@Pattern(regexp="^[a-zA-z0-9_]*$", message="Only Alphanumeric and underscores are allowed in Title.")
	private String title;
	private Boolean status = true;
	private Date startDate;
	private Date endDate;
	private Date launchDate;
	private int type = 0;
	private int currency = 0;
	@Min(message="Budget is Required.",value=(long) 1.0)
	private float budget;
	@Min(message="Expected Cost is Required.",value=(long) 1.0)
	private float expectedCost;
	private float actualCost;
	
	@Min(message="Expected Revenue is Required.",value=(long) 1.0)
	private float expectedRevenue;
	private String objective;
	private String description;
	
	public CampaignBackingBean(){
		System.out.println("creating new campaing backing bean.");
	}
	
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
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getLaunchDate() {
		//Need to update this.
		if(launchDate == null){
			setLaunchDate(new Date());
		}
		return launchDate;
	}
	public void setLaunchDate(Date launchDate) {
		this.launchDate = launchDate;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
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
	
	public void reset(){
		System.out.println("Resetting the campaignBean.");
		setId(0L);
		setCompany_id(0L);
		setTitle("");
		setStatus(true);
		setStartDate(new Date());
		setEndDate(new Date());
		setLaunchDate(new Date());
		setType(0);
		setCurrency(0);
		setBudget(0.00F);
		setExpectedCost(0.00F);
		setActualCost(0.00F);
		setExpectedRevenue(0.00F);
		setObjective("");
		setDescription("");
		
	}
		
}
