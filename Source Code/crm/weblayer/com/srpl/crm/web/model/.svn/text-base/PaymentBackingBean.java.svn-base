package com.srpl.crm.web.model;

/**
 * @author Hammad Hassan Khan
 *
 */
import java.io.Serializable;
import java.sql.Timestamp;

import javax.faces.bean.ManagedBean;
import com.srpl.crm.ejb.entity.CsContactORM;
import com.srpl.crm.ejb.entity.SInvoiceORM;

@ManagedBean(name="payment")
public class PaymentBackingBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long companyId;
	private double invoiceAmount;
	private double paidAmount;
	private Timestamp paidOnDate;
	private double remainingAmount;
	private CsContactORM subscriber;
	private SInvoiceORM SInvoice;
	
	private Long invoiceId;

	/**
	 * 
	 * Getters and Setters
	 */
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public double getInvoiceAmount() {
		return invoiceAmount;
	}
	public void setInvoiceAmount(double invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}
	public double getPaidAmount() {
		return paidAmount;
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
	public double getRemainingAmount() {
		return remainingAmount;
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
		return SInvoice;
	}
	public void setSInvoice(SInvoiceORM sInvoice) {
		SInvoice = sInvoice;
	}	
	public Long getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}
	public void reset(){
		id = null;
		companyId = null;
		invoiceAmount = 0.00;
		paidAmount = 0.00;
		//paidOnDate;
		remainingAmount = 0.00;
		subscriber = null;
		SInvoice = null;
	}
}
