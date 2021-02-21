package com.srpl.crm.ejb.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;


/**
 * The persistent class for the payment_reimburse database table.
 * 
 */

public class PaymentReimburse implements Serializable {
	private static final long serialVersionUID = 1L;


	private Long id;

	
	private Long invoiceId;

	
	private Boolean isReimburse;

	
	private Integer orderId;

	
	private Integer paidAmount;

	
	private Integer reimburseAmount;

	
	private Integer remaningAmount;

	
	private Integer totalAmount;
	
	
	private Timestamp paymentCreatedon;
	
	
	private String isCorrect;
		
    public Timestamp getPaymentCreatedon() {
		return paymentCreatedon;
	}

	public void setPaymentCreatedon(Timestamp paymentCreatedon) {
		this.paymentCreatedon = paymentCreatedon;
	}

	public PaymentReimburse() {
    }    

	public String getIsCorrect() {
		return isCorrect;
	}
	
	public void setIsCorrect(String isCorrect) {
		this.isCorrect = isCorrect;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getInvoiceId() {
		return this.invoiceId;
	}

	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}

	public Boolean getIsReimburse() {
		return this.isReimburse;
	}

	public void setIsReimburse(Boolean isReimburse) {
		this.isReimburse = isReimburse;
	}

	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getPaidAmount() {
		return this.paidAmount;
	}

	public void setPaidAmount(Integer paidAmount) {
		this.paidAmount = paidAmount;
	}

	public Integer getReimburseAmount() {
		return this.reimburseAmount;
	}

	public void setReimburseAmount(Integer reimburseAmount) {
		this.reimburseAmount = reimburseAmount;
	}

	public Integer getRemaningAmount() {
		return this.remaningAmount;
	}

	public void setRemaningAmount(Integer remaningAmount) {
		this.remaningAmount = remaningAmount;
	}

	public Integer getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

}