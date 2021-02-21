package com.srpl.crm.ejb.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Time;
import java.sql.Timestamp;


/**
 * The persistent class for the payment database table.
 * 
 */
@Entity
@Table(name="payment")
public class PaymentORM implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PAYMENT_ID_GENERATOR", sequenceName="PAYMENT_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PAYMENT_ID_GENERATOR")
	private Long id;

	@Column(name="company_id")
	private Long companyId;

	@Column(name="invoice_amount")
	private double invoiceAmount;

	@Column(name="paid_amount")
	private double paidAmount;

	@Column(name="paid_on_date")
	private Timestamp paidOnDate;
	
	@Column(name="payment_createdon")
	private Timestamp paymentCreatedon;

	@Column(name="remaining_amount")
	private double remainingAmount;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="subscriber_id")
	CsContactORM subscriber;

	@Column(name = "mapped_id")
	private Long mappedId;

	//bi-directional one-to-one association to SInvoice
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="invoice_id")
	SInvoiceORM invoice;
	
	@Transient
	private String isCorrect;

    public PaymentORM() {
    }

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public double getInvoiceAmount() {
		return this.invoiceAmount;
	}

	public void setInvoiceAmount(double invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public double getPaidAmount() {
		return this.paidAmount;
	}

	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}

	public Timestamp getPaidOnDate() {
		return paidOnDate;
	}

	public void setPaidOnDate(Timestamp paidOnDate) {
		this.paidOnDate = paidOnDate;
	}	

	public Timestamp getPaymentCreatedon() {
		return paymentCreatedon;
	}

	public void setPaymentCreatedOn(Timestamp paymentCreatedon) {
		this.paymentCreatedon = paymentCreatedon;
	}

	public double getRemainingAmount() {
		return this.remainingAmount;
	}

	public void setRemainingAmount(double remainingAmount) {
		this.remainingAmount = remainingAmount;
	}

	public CsContactORM getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(CsContactORM subscriber) {
		this.subscriber = subscriber;
	}

	public SInvoiceORM getSInvoice() {
		return this.invoice;
	}

	public void setSInvoice(SInvoiceORM SInvoice) {
		this.invoice = SInvoice;
	}

	public String getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(String isCorrect) {
		this.isCorrect = isCorrect;
	}

	public Long getMappedId() {
		return mappedId;
	}

	public void setMappedId(Long mappedId) {
		this.mappedId = mappedId;
	}	
	
	
	
}