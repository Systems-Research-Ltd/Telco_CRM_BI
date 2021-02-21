package com.srpl.crm.ejb.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.srpl.um.ejb.entity.UmUser;

@Entity
@Table(name = "sales_leads_user_comments")
public class SalesLeadCommentORM implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name="SALES_LEADS_USER_COMMENTS_LEAD_COMMENT_ID_GENERATOR", sequenceName="sales_leads_user_comments_lead_comment_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SALES_LEADS_USER_COMMENTS_LEAD_COMMENT_ID_GENERATOR")
	@Column(name = "lead_comment_id")	
	private Long leadCommentId;
	@Column(name = "comments")
	private String leadComments;
	@ManyToOne
	@JoinColumn(name = "lead_id")
	private SalesLeadORM leads;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UmUser user;
	@Column(name = "lead_comments_date")
	private Timestamp leadCommentsDate;
	
	@ManyToOne
	@JoinColumn(name = "lead_history_id")
	private SalesLeadHistoryORM leadHistory;

	public SalesLeadCommentORM(){
		
	}
	
	public SalesLeadCommentORM(SalesLeadORM leads, UmUser user,SalesLeadHistoryORM leadHistory, Timestamp leadCommentsDate,String leadComments){
		this.leads = leads;
		this.user = user;
		this.leadHistory = leadHistory;
		this.leadCommentsDate = leadCommentsDate;
		this.leadComments = leadComments;
	}
	public Long getLeadCommentId() {
		return leadCommentId;
	}

	public void setLeadCommentId(Long leadCommentId) {
		this.leadCommentId = leadCommentId;
	}

	public String getLeadComments() {
		return leadComments;
	}

	public void setLeadComments(String leadComments) {
		this.leadComments = leadComments;
	}

	public SalesLeadORM getLeads() {
		return leads;
	}

	public void setLeads(SalesLeadORM leads) {
		this.leads = leads;
	}

	public UmUser getUser() {
		return user;
	}

	public void setUser(UmUser user) {
		this.user = user;
	}

	public Timestamp getLeadCommentsDate() {
		return leadCommentsDate;
	}

	public void setLeadCommentsDate(Timestamp leadCommentsDate) {
		this.leadCommentsDate = leadCommentsDate;
	}

	public SalesLeadHistoryORM getLeadHistory() {
		return leadHistory;
	}

	public void setLeadHistory(SalesLeadHistoryORM leadHistory) {
		this.leadHistory = leadHistory;
	}
	
	

	

}
