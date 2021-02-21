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

import com.srpl.um.ejb.entity.UmUser;

@Entity
@Table(name = "support_cases_history")
public class SupportCaseHistoryORM implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name="SUPPORT_CASES_HISTORY_CASE_HISTORYID_GENERATOR", sequenceName="support_cases_history_case_history_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SUPPORT_CASES_HISTORY_CASE_HISTORYID_GENERATOR")
	@Column(name = "case_history_id")
	private Long caseHistoryId;
	@ManyToOne
	@JoinColumn(name = "case_id")
	private SupportCaseORM cases;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UmUser user;
	@Column(name = "case_assigned_to_date")
	private Timestamp caseAssignedToDate;
	
	@OneToMany(mappedBy ="caseHistory", fetch = FetchType.EAGER)
	private List<SupportCaseCommentORM> caseCommentsList = new ArrayList<SupportCaseCommentORM>(0);
	
	
	
	public SupportCaseHistoryORM(){
		
	}
	
    public SupportCaseHistoryORM(SupportCaseORM cases, UmUser user, Timestamp caseAssignedToDate){
      this.cases = cases;
      this.user = user;
      this.caseAssignedToDate = caseAssignedToDate;
		
	}
    
    
    public SupportCaseHistoryORM(Long caseHistoryId, SupportCaseORM cases, UmUser user, Timestamp caseAssignedToDate){
        this.caseHistoryId = caseHistoryId;
    	this.cases = cases;
        this.user = user;
        this.caseAssignedToDate = caseAssignedToDate;
  		
  	}
	
	public Long getCaseHistoryId() {
		return caseHistoryId;
	}
	public void setCaseHistoryId(Long caseHistoryId) {
		this.caseHistoryId = caseHistoryId;
	}
	public SupportCaseORM getCases() {
		return cases;
	}
	public void setCases(SupportCaseORM cases) {
		this.cases = cases;
	}
	public UmUser getUser() {
		return user;
	}
	public void setUser(UmUser user) {
		this.user = user;
	}
	public Timestamp getCaseAssignedToDate() {
		return caseAssignedToDate;
	}
	public void setCaseAssignedToDate(Timestamp caseAssignedToDate) {
		this.caseAssignedToDate = caseAssignedToDate;
	}

	public List<SupportCaseCommentORM> getCaseCommentsList() {
		return caseCommentsList;
	}

	public void setCaseCommentsList(List<SupportCaseCommentORM> caseCommentsList) {
		this.caseCommentsList = caseCommentsList;
	}

}