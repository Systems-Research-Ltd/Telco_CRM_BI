package com.srpl.crm.ejb.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


/**
 * The persistent class for the s_invoice database table.
 * 
 */
@Entity
@Table(name="s_invoice")
public class SInvoiceORM implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="S_INVOICE_ID_GENERATOR", sequenceName="S_INVOICE_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="S_INVOICE_ID_GENERATOR")
	private Long id;

	@Column(name="amount_after_due_date")
	private double amountAfterDueDate;

	@Column(name="arrears")
	private double arrears;

	@Column(name="company_id")
	private Long companyId;

	@Column(name="created_by")
	private Long createdBy;

	@Column(name="current_charges")
	private double currentCharges;

	@Column(name="due_date")
	private Timestamp dueDate;

	@Column(name="is_bill")
	private Boolean isBill;

	@Column(name="issue_date")
	private Timestamp issueDate;

	@Column(name = "status") 
	private Boolean status; // unpaid or paid

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="subscriber_id")
	private CsContactORM subscriber;

	@Column(name="title")
	private String title;

	@Column(name="total_amount")
	private double totalAmount;
	
	@Column(name="paid_amount")
	private double paidAmount;
	
	@Column(name="discount")
	private double discount;
	
	@Column(name="net_amount")
	private double netAmount;
	
	@Column(name="order_id")
	private long orderID;

	//bi-directional one-to-one association to Payment
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "invoice")
	private PaymentORM payments;
	
	@OneToMany(mappedBy = "invoice", fetch = FetchType.EAGER)
	private Set<SInvoiceDetailORM> details = new HashSet<SInvoiceDetailORM>(0);
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "previous_invoice_id")
	private SInvoiceORM previousInvoice;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "previousInvoice")
	private SInvoiceORM nextInvoice;

    public SInvoiceORM() {
    }

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getAmountAfterDueDate() {
		return this.amountAfterDueDate;
	}

	public void setAmountAfterDueDate(double amountAfterDueDate) {
		this.amountAfterDueDate = amountAfterDueDate;
	}

	public double getArrears() {
		return this.arrears;
	}

	public void setArrears(double arrears) {
		this.arrears = arrears;
	}

	public Long getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public double getCurrentCharges() {
		return this.currentCharges;
	}

	public void setCurrentCharges(double currentCharges) {
		this.currentCharges = currentCharges;
	}

	public Timestamp getDueDate() {
		return dueDate;
	}

	public void setDueDate(Timestamp dueDate) {
		this.dueDate = dueDate;
	}

	public Boolean getIsBill() {
		return this.isBill;
	}

	public void setIsBill(Boolean isBill) {
		this.isBill = isBill;
	}

	public Timestamp getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Timestamp issueDate) {
		this.issueDate = issueDate;
	}

	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public CsContactORM getSubscriber() {
		return this.subscriber;
	}

	public void setSubscriber(CsContactORM subscriberId) {
		this.subscriber = subscriberId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(double netAmount) {
		this.netAmount = netAmount;
	}

	public PaymentORM getPayments() {
		return payments;
	}

	public void setPayments(PaymentORM payments) {
		this.payments = payments;
	}

	public Set<SInvoiceDetailORM> getDetails() {
		return details;
	}

	public void setDetails(Set<SInvoiceDetailORM> details) {
		this.details = details;
	}

	public SInvoiceORM getPreviousInvoice() {
		return previousInvoice;
	}

	public void setPreviousInvoice(SInvoiceORM previousInvoice) {
		this.previousInvoice = previousInvoice;
	}

	public SInvoiceORM getNextInvoice() {
		return nextInvoice;
	}

	public void setNextInvoice(SInvoiceORM nextInvoice) {
		this.nextInvoice = nextInvoice;
	}

	public long getOrderID() {
		return orderID;
	}

	public void setOrderID(long orderID) {
		this.orderID = orderID;
	}	
	
}