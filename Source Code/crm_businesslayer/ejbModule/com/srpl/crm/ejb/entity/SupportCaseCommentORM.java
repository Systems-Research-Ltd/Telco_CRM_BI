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
@Table(name = "support_case_user_comments")
public class SupportCaseCommentORM implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name="SUPPORT_CASE_USER_COMMENTS_CASE_COMMENT_ID_GENERATOR", sequenceName="support_case_user_comments_case_comment_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SUPPORT_CASE_USER_COMMENTS_CASE_COMMENT_ID_GENERATOR")
	@Column(name = "case_comment_id")	
	private Long caseCommentId;
	@Column(name = "comments")
	private String caseComments;
	@ManyToOne
	@JoinColumn(name = "case_id")
	private SupportCaseORM cases;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UmUser user;
	@Column(name = "case_comments_date")
	private Timestamp caseCommentsDate;
	
	@ManyToOne
	@JoinColumn(name = "case_history_id")
	private SupportCaseHistoryORM caseHistory;
	

	public SupportCaseCommentORM(){
		
	}
	
    public SupportCaseCommentORM(SupportCaseORM cases, UmUser user,SupportCaseHistoryORM caseHistory,Timestamp caseCommentDate, String caseComments){
		this.cases = cases;
		this.user = user;
		this.caseHistory = caseHistory;
		this.caseCommentsDate = caseCommentDate;
		this.caseComments = caseComments;
	}
	
	public Long getCaseCommentId() {
		return caseCommentId;
	}
	public void setCaseCommentId(Long caseCommentId) {
		this.caseCommentId = caseCommentId;
	}
	public String getCaseComments() {
		return caseComments;
	}
	public void setCaseComments(String caseComments) {
		this.caseComments = caseComments;
	}
	public SupportCaseORM getCases() {
		return cases;
	}
	public void setCaseHistory(SupportCaseORM cases) {
		this.cases = cases;
	}
	public UmUser getUser() {
		return user;
	}
	public void setUser(UmUser user) {
		this.user = user;
	}
	public Timestamp getCaseCommentsDate() {
		return caseCommentsDate;
	}
	public void setCaseCommentsDate(Timestamp caseCommentsDate) {
		this.caseCommentsDate = caseCommentsDate;
	}

	public SupportCaseHistoryORM getCaseHistory() {
		return caseHistory;
	}

	public void setCaseHistory(SupportCaseHistoryORM caseHistory) {
		this.caseHistory = caseHistory;
	}
	
	
	
}
