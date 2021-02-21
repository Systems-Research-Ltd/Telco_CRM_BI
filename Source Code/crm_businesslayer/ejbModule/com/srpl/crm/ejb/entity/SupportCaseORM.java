package com.srpl.crm.ejb.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
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
import javax.persistence.Transient;

import com.srpl.um.ejb.entity.UmCompany;
import com.srpl.um.ejb.entity.UmUser;

@Entity
@Table(name = "support_cases")
public class SupportCaseORM implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="SUPPORT_CASES_CASEID_GENERATOR", sequenceName="support_cases_case_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SUPPORT_CASES_CASEID_GENERATOR")
	@Column(name = "case_id")
	private Long caseId;
	@Column(name = "case_token_number")
	private String caseTokenNumber;
	@Column(name = "case_type")
	private String caseType;
	@Column(name = "case_status")
	private String status;
	@Column(name = "case_mobile_number")
	private String mobileNumber;
	@Column(name = "customer_query")
	private String customerQuery;
	@Column(name = "comments")
	private String comments;
	@Column(name = "service_rating")
	private String serviceRating;
    @Column(name = "feedback")
    private String feedBack;
	@Column(name = "case_create_date")
	private Timestamp caseCreateDate;
	@Column(name = "case_assigned_date")
	private Timestamp caseAssignedDate;
	@Column(name = "case_resolved_date")
	private Timestamp caseResolvedDate;
	@ManyToOne
	@JoinColumn(name="case_customer_id")
	private CsContactORM customer;
	@ManyToOne
	@JoinColumn(name="case_user_id")
	private UmUser umUser;
	@ManyToOne
	@JoinColumn(name="case_company_id")
	private UmCompany umCompany;
	@ManyToOne
	@JoinColumn(name="case_product_id")
	private ProductORM product;
	@ManyToOne
	@JoinColumn(name="case_query_type_id")
	private SupportQueryTypeORM queryType;
	@Transient
	private String assignedToLoginUser;
	
	
	//======================== ========================//
		public String getUserName()
		{
			if(umUser != null){
			   return umUser.getUserName();
			}else{
				return "N/A";
			}
			
		}
		
		
		public String getCustomerName(){
			  
			  return customer.getContactFname();
			
		}
		
		public String getProductName(){
			return product.getProductTitle();
		}
		
		public int getNumberOfDaysPassed(){
			Date currentDate = new Date();
			Calendar currentCal = Calendar.getInstance();
			Calendar createCal = Calendar.getInstance();
			currentCal.setTime(currentDate);
			createCal.setTime(caseCreateDate);
			
			return daysBetween(currentCal.getTime(),createCal.getTime());
			
		}
		
		//======================= ========================//
	
	
	public SupportCaseORM(){
		
	}

	
	@Deprecated
    public SupportCaseORM(Long caseId, String caseTokenNumber, CsContactORM customer, UmUser umUser, ProductORM product, String caseType, SupportQueryTypeORM queryType, String status, String mobileNumber, String customerQuery, String comments, Timestamp caseCreateDate, Timestamp caseAssignedDate, Timestamp caseResolvedDate){
		this.caseId = caseId;
		this.caseTokenNumber = caseTokenNumber;
		this.customer = customer;
		this.umUser = umUser;
		this.product = product;
		this.queryType = queryType;
		this.caseType = caseType;
		this.status = status;
		this.mobileNumber = mobileNumber;
		this.customerQuery = customerQuery;
		this.comments = comments;
		this.caseCreateDate = caseCreateDate;
		this.caseAssignedDate = caseAssignedDate;
		this.caseResolvedDate = caseResolvedDate;
	}
    
    public SupportCaseORM(Long caseId, String caseTokenNumber, CsContactORM customer, UmUser umUser, ProductORM product, String caseType, SupportQueryTypeORM queryType, String status, String mobileNumber, String customerQuery, String comments, Timestamp caseCreateDate, Timestamp caseAssignedDate, Timestamp caseResolvedDate, UmCompany umCompany){
		this.caseId = caseId;
		this.caseTokenNumber = caseTokenNumber;
		this.customer = customer;
		this.umUser = umUser;
		this.umCompany = umCompany;
		this.product = product;
		this.queryType = queryType;
		this.caseType = caseType;
		this.status = status;
		this.mobileNumber = mobileNumber;
		this.customerQuery = customerQuery;
		this.comments = comments;
		this.caseCreateDate = caseCreateDate;
		this.caseAssignedDate = caseAssignedDate;
		this.caseResolvedDate = caseResolvedDate;
	}
    
    @Deprecated
    public SupportCaseORM(String caseTokenNumber, CsContactORM customer, UmUser umUser, ProductORM product, String caseType, SupportQueryTypeORM queryType, String status, String mobileNumber, String customerQuery, String comments, Timestamp caseCreateDate, Timestamp caseAssignedDate, Timestamp caseResolvedDate){
    	this.caseTokenNumber = caseTokenNumber;
		this.customer = customer;
		this.umUser = umUser;
		this.product = product;
		this.queryType = queryType;
		this.caseType = caseType;
		this.status = status;
		this.mobileNumber = mobileNumber;
		this.customerQuery = customerQuery;
		this.comments = comments;
		this.caseCreateDate = caseCreateDate;
		this.caseAssignedDate = caseAssignedDate;
		this.caseResolvedDate = caseResolvedDate;
    	
    }
	
    public SupportCaseORM(String caseTokenNumber, CsContactORM customer, UmUser umUser, ProductORM product, String caseType, SupportQueryTypeORM queryType, String status, String mobileNumber, String customerQuery, String comments, Timestamp caseCreateDate, Timestamp caseAssignedDate, Timestamp caseResolvedDate, UmCompany umCompany){
    	this.caseTokenNumber = caseTokenNumber;
		this.customer = customer;
		this.umUser = umUser;
		this.umCompany = umCompany;
		this.product = product;
		this.queryType = queryType;
		this.caseType = caseType;
		this.status = status;
		this.mobileNumber = mobileNumber;
		this.customerQuery = customerQuery;
		this.comments = comments;
		this.caseCreateDate = caseCreateDate;
		this.caseAssignedDate = caseAssignedDate;
		this.caseResolvedDate = caseResolvedDate;
    	
    }
	public Long getCaseId() {
		return caseId;
	}
	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}
	public String getCaseTokenNumber() {
		return caseTokenNumber;
	}
	public void setCaseTokenNumber(String caseTokenNumber) {
		this.caseTokenNumber = caseTokenNumber;
	}
	public String getCaseType() {
		return caseType;
	}
	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}


	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}


	public String getCustomerQuery() {
		return customerQuery;
	}
	public void setCustomerQuery(String customerQuery) {
		this.customerQuery = customerQuery;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getServiceRating() {
		return serviceRating;
	}


	public void setServiceRating(String serviceRating) {
		this.serviceRating = serviceRating;
	}


	public String getFeedBack() {
		return feedBack;
	}


	public void setFeedBack(String feedBack) {
		this.feedBack = feedBack;
	}


	public Timestamp getCaseCreateDate() {
		return caseCreateDate;
	}
	public void setCaseCreateDate(Timestamp caseCreateDate) {
		this.caseCreateDate = caseCreateDate;
	}
	public Timestamp getCaseAssignedDate() {
		return caseAssignedDate;
	}


	public void setCaseAssignedDate(Timestamp caseAssignedDate) {
		this.caseAssignedDate = caseAssignedDate;
	}


	public Timestamp getCaseResolvedDate() {
		return caseResolvedDate;
	}
	public void setCaseResolvedDate(Timestamp caseResolvedDate) {
		this.caseResolvedDate = caseResolvedDate;
	}
	public CsContactORM getcustomer() {
		return customer;
	}
	public void setcustomer(CsContactORM customer) {
		this.customer = customer;
	}
	public UmUser getUmUser() {
		return umUser;
	}
	public void setUmUser(UmUser umUser) {
		this.umUser = umUser;
	}
	public UmCompany getUmCompany() {
		return umCompany;
	}


	public void setUmCompany(UmCompany umCompany) {
		this.umCompany = umCompany;
	}


	public ProductORM getProduct() {
		return product;
	}
	public void setProduct(ProductORM product) {
		this.product = product;
	}


	public SupportQueryTypeORM getQueryType() {
		return queryType;
	}


	public void setQueryType(SupportQueryTypeORM queryType) {
		this.queryType = queryType;
	}
	

	public String getAssignedToLoginUser() {
		return assignedToLoginUser;
	}


	public void setAssignedToLoginUser(String assignedToLoginUser) {
		this.assignedToLoginUser = assignedToLoginUser;
	}


	public int daysBetween(Date currentDate, Date createDate){
		int daysBetween = (int) ((currentDate.getTime() - createDate.getTime() ) / (1000 * 60 * 60 * 24));
		return daysBetween;
	}
	
	
}
