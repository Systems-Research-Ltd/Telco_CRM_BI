package com.srpl.crm.web.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.faces.bean.ManagedBean;

import com.srpl.crm.ejb.entity.CsContactORM;
import com.srpl.crm.ejb.entity.SInvoiceDetailORM;

@ManagedBean(name = "invoice")
public class InvoiceBackingBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private CsContactORM subscriber;
	private Date issueDate;
	private Long createdBy;
	private double amount;
	private String title;
	private List<SInvoiceDetailORM> invoiceDetails;
	//Need an order field to create new invoice
	private Long orderId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public CsContactORM getSubscriber() {
		return subscriber;
	}
	public void setSubscriber(CsContactORM subscriber) {
		this.subscriber = subscriber;
	}
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issue_date) {
		this.issueDate = issue_date;
	}
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long created_by) {
		this.createdBy = created_by;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long order_id) {
		this.orderId = order_id;
	}
	public List<SInvoiceDetailORM> getInvoiceDetails() {
		return invoiceDetails;
	}
	public void setInvoiceDetails(List<SInvoiceDetailORM> invoiceDetails) {
		this.invoiceDetails = invoiceDetails;
	}
	public void setInvoiceDetails(Set<SInvoiceDetailORM> invoiceDetails) {
		this.invoiceDetails = new ArrayList<SInvoiceDetailORM>(invoiceDetails);
	}
	
	public void reset(){

		id = null;
		subscriber = null;
		//issue_date = new Date();
		createdBy = null;
		amount = 0.00;
		title = "";
	}
}
