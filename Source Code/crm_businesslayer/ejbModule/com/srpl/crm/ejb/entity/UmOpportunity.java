package com.srpl.crm.ejb.entity;

import java.io.Serializable;

public class UmOpportunity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Long opportunityId;
	private String opportunityTitle;
	private String status;
	private String assignedTo;
	private String customer;
	
	public UmOpportunity(long opportunityId, String opportunityTitle, String status, String assignedTo, String customer){
		this.opportunityId = opportunityId;
		this.opportunityTitle = opportunityTitle;
		this.status = status;
		this.assignedTo = assignedTo;
		this.customer = customer;
	}
	
	public Long getOpportunityId() {
		return opportunityId;
	}
	public void setOpportunityId(Long opportunityId) {
		this.opportunityId = opportunityId;
	}
	public String getOpportunityTitle() {
		return opportunityTitle;
	}
	public void setOpportunityTitle(String opportunityTitle) {
		this.opportunityTitle = opportunityTitle;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	

	
}
