package com.srpl.crm.ejb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.srpl.um.ejb.entity.UmCompany;
import com.srpl.um.ejb.entity.UmUser;

@Entity
@Table(name = "sales_opportunities")
public class SalesOpportunityORM implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SALES_OPPORTUNITIES_OPPORTUNITYId_GENERATOR", sequenceName = "sales_opportunities_opportunity_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SALES_OPPORTUNITIES_OPPORTUNITYId_GENERATOR")
	@Column(name = "opportunity_id")
	private Long opportunityId;
	// @Column(name = "opportunity_title")
	// private String opportunityTitle;
	@Column(name = "status")
	private String status;
	@Column(name = "opportunity_revenue")
	private Integer opportunityExpectedRevenue;
	@Column(name = "opportunity_success")
	private Integer percentOfSuccess;
	@Column(name = "comments")
	private String comments;

	// @Column(name = "assigned_to")
	// private Integer assignedTo;
	// @Column(name = "customer_id")
	// private Integer customerId;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private CsContactORM csContact;

	@ManyToOne
	@JoinColumn(name = "opportunity_product_id")
	private ProductORM product;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UmUser umUser;

	@ManyToOne
	@JoinColumn(name = "company_id")
	private UmCompany company;

	// ======================== ========================//
	public String getUserName() {
		return (umUser != null) ? umUser.getUserName() : "";
	}

	public String getContactName() {
		System.out.println("ORM contactFname = " + csContact.getContactFname());
		return (csContact != null) ? csContact.getContactFname() : "";

	}

	public String getProductName() {
		return (product != null) ? product.getProductTitle() : "";
	}

	// ======================= ========================//

	public SalesOpportunityORM() {

	}

	public SalesOpportunityORM(Long opportunityId, ProductORM product,
			String status, Integer opportunityExpectedRevenue,
			Integer percentOfSuccess, String comments, UmUser user,
			CsContactORM customer, UmCompany company) {
		this.opportunityId = opportunityId;
		this.product = product;
		this.status = status;
		this.opportunityExpectedRevenue = opportunityExpectedRevenue;
		this.percentOfSuccess = percentOfSuccess;
		this.comments = comments;
		this.umUser = user;
		this.csContact = customer;
		this.company = company;

	}

	public SalesOpportunityORM(ProductORM product, String status,
			Integer opportunityExpectedRevenue, Integer percentOfSuccess,
			String comments, UmUser user, CsContactORM customer, UmCompany company) {
		this.product = product;
		this.status = status;
		this.opportunityExpectedRevenue = opportunityExpectedRevenue;
		this.percentOfSuccess = percentOfSuccess;
		this.comments = comments;
		this.umUser = user;
		this.csContact = customer;
		this.company = company;
	}

	public Long getOpportunityId() {
		return opportunityId;
	}
	public void setOpportunityId(Long opportunityId) {
		this.opportunityId = opportunityId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getOpportunityExpectedRevenue() {
		return opportunityExpectedRevenue;
	}
	public void setOpportunityExpectedRevenue(Integer opportunityExpectedRevenue) {
		this.opportunityExpectedRevenue = opportunityExpectedRevenue;
	}
	public Integer getPercentOfSuccess() {
		return percentOfSuccess;
	}
	public void setPercentOfSuccess(Integer percentOfSuccess) {
		this.percentOfSuccess = percentOfSuccess;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public CsContactORM getCsContact() {
		return csContact;
	}
	public void setCsContact(CsContactORM csContact) {
		this.csContact = csContact;
	}
	public ProductORM getProduct() {
		return product;
	}
	public void setProduct(ProductORM product) {
		this.product = product;
	}
	public UmUser getUmUser() {
		return umUser;
	}
	public void setUmUser(UmUser umUser) {
		this.umUser = umUser;
	}
	public UmCompany getCompany() {
		return company;
	}
	public void setCompany(UmCompany company) {
		this.company = company;
	}

}
