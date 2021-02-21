package com.srpl.crm.ejb.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
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
@Entity
@Table(name = "sales_leads_history")
public class SalesLeadHistoryORM implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name="SALE_LEADS_HISTORY_LEAD_HISTORY_ID_GENERATOR", sequenceName="sales_leads_history_lead_history_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SALE_LEADS_HISTORY_LEAD_HISTORY_ID_GENERATOR")
	@Column(name = "lead_history_id")
	private Long leadHistoryId;
	@ManyToOne
	@JoinColumn(name = "lead_id")
	private SalesLeadORM leads;
	@Column(name = "user_id")
	private Long leadAssigned;
	
	@Column(name = "lead_assigned_to_date")
	private Timestamp leadAssignedToDate;
	
	@OneToMany(mappedBy ="leadHistory", fetch = FetchType.EAGER)
	private List<SalesLeadCommentORM> leadCommentsList = new ArrayList<SalesLeadCommentORM>(0);
	
	public SalesLeadHistoryORM(){
		
	}
	public SalesLeadHistoryORM( SalesLeadORM leads,Long leadAssigned,Timestamp leadAssignedToDate){
		this.leads = leads;
		this.leadAssigned = leadAssigned;
		this.leadAssignedToDate = leadAssignedToDate;
		
	}
	
	public SalesLeadHistoryORM(Long leadHistoryId, SalesLeadORM leads,Long leadAssigned,Timestamp leadAssignedToDate){
		this.leadHistoryId = leadHistoryId;
		this.leads = leads;
		this.leadAssigned = leadAssigned;
		this.leadAssignedToDate = leadAssignedToDate;
		
	}

	public Long getLeadHistoryId() {
		return leadHistoryId;
	}

	public void setLeadHistoryId(Long leadHistoryId) {
		this.leadHistoryId = leadHistoryId;
	}

	public SalesLeadORM getLeads() {
		return leads;
	}

	public void setLeads(SalesLeadORM leads) {
		this.leads = leads;
	}

	public Timestamp getLeadAssignedToDate() {
		return leadAssignedToDate;
	}

	public void setLeadAssignedToDate(Timestamp leadAssignedToDate) {
		this.leadAssignedToDate = leadAssignedToDate;
	}

	public List<SalesLeadCommentORM> getLeadCommentsList() {
		return leadCommentsList;
	}

	public void setLeadCommentsList(List<SalesLeadCommentORM> leadCommentsList) {
		this.leadCommentsList = leadCommentsList;
	}
	public Long getLeadAssigned() {
		return leadAssigned;
	}
	public void setLeadAssigned(Long leadAssigned) {
		this.leadAssigned = leadAssigned;
	}

}
