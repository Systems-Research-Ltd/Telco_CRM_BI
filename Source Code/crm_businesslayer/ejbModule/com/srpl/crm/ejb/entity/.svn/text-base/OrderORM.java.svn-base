package com.srpl.crm.ejb.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.srpl.um.ejb.entity.UmCompany;
import com.srpl.um.ejb.entity.UmUser;

@Entity
@Table(name = "orders")
public class OrderORM implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@SequenceGenerator(name="ORDERS_ORDERID_GENERATOR", sequenceName="orders_order_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ORDERS_ORDERID_GENERATOR")
	@Column(name = "order_id")
	private Long orderId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id")
	private CsContactORM customer;
	@OneToOne
	@JoinColumn(name = "opportunity_id")
	private SalesOpportunityORM opportunity;

	@Column(name = "status")
	private String status;
	@Column(name = "order_creation")
	private String orderCreation;

	
	@Column(name = "order_total_amount")
	private Double orderTotalAmount;
	@Column(name = "discount")
	private Double discount;
	@Column(name = "paid_amount")
	private Double paidAmount;
	@Column(name = "net_amount")
	private Double netAmount;
	
	@Column(name = "order_date")
	private Timestamp orderDate;
	@Column(name = "order_change_date")
	private Timestamp orderChangeDate;
	
	@Column(name = "order_addedon")
	private Timestamp orderAddedon;
	
	@Column(name = "mapped_id")
	private Long mappedId;
	@Transient
	private String isCorrect;	
	
	@ManyToOne
	@JoinColumn(name = "created_by")
	private UmUser createdBy;
	
	@ManyToOne
	@JoinColumn(name = "changed_by")
	private UmUser changedBy;
	
	@ManyToOne
	@JoinColumn(name="order_company_id")
	private UmCompany umCompany;
	
	@ManyToOne
	@JoinColumn(name = "assigned_to")
	private UmUser assignedTo;
	
	@OneToMany(mappedBy="order", fetch=FetchType.EAGER)
	private List<OrderDetailORM> orderDetailList = new ArrayList<OrderDetailORM>();

	public OrderORM(){
		
	}
	
	
	//======================== ========================//
			public String getCustomerName(){
				  return customer.getContactFname();
			}
			public String getProductTitle(){
				for(OrderDetailORM orderDetail : orderDetailList){
					return orderDetail.getProductTitle();	
				}
				return "";
			}
			
			@Deprecated
			public Boolean getSaveOrderTitle(){
				return false;
			}
			
	//======================= ========================//

/*	@Deprecated		 
	public Long getTotalOrderAmmount(){
		orderDetailList.size();
		Long totalAmount = 0l;
		for(OrderDetailORM orderDetail : orderDetailList){
			totalAmount = totalAmount + orderDetail.getOrderAmmount();
		}
		
		return totalAmount;
	}
	*/
	@Deprecated		
	public Long getTotalOrderAmmount(){
			return 0l;
	}
	
	
	@Deprecated
	public OrderORM(Long orderId, SalesOpportunityORM opportunity, CsContactORM customer, Timestamp orderDate, Timestamp orderChangeDate, String status, String orderCreation, String orderTitle, Boolean saveOrderTitle, UmCompany umCompany){
        this.orderId = orderId;
        this.opportunity = opportunity;
        this.customer = customer;
        this.orderDate = orderDate;
        this.orderChangeDate = orderChangeDate;
        this.status = status;
        this.orderCreation = orderCreation;
        this.umCompany = umCompany;
	}
	@Deprecated
	public OrderORM(SalesOpportunityORM opportunity, CsContactORM customer, Timestamp orderDate, Timestamp orderChangeDate, String status, String orderCreation, String orderTitle, Boolean saveOrderTitle, UmCompany umCompany){
        this.customer = customer;
        this.opportunity = opportunity;
        this.orderDate = orderDate;
        this.orderChangeDate = orderChangeDate;
        this.status = status;
        this.orderCreation = orderCreation;
        this.umCompany = umCompany;
	}
	
	public OrderORM(Long orderId, SalesOpportunityORM opportunity, CsContactORM customer, Timestamp orderDate, UmUser createdBy, Double orderTotalAmount, Double discount, Double netAmount, Double paidAmount, Timestamp orderChangeDate, UmUser changedBy, String status, String orderCreation, UmCompany umCompany){
        this.orderId = orderId;
        this.opportunity = opportunity;
        this.customer = customer;
        this.orderTotalAmount = orderTotalAmount;
        this.discount = discount;
        this.netAmount = netAmount;
        this.paidAmount = paidAmount;
        this.orderDate = orderDate;
        this.createdBy = createdBy;
        this.orderChangeDate = orderChangeDate;
        this.changedBy = changedBy;
        this.status = status;
        this.orderCreation = orderCreation;
        this.umCompany = umCompany;
	}

	public OrderORM(SalesOpportunityORM opportunity, CsContactORM customer, Timestamp orderDate, UmUser createdBy, Double orderTotalAmount, Double discount, Double netAmount, Double paidAmount, Timestamp orderChangeDate, UmUser changedBy, String status, String orderCreation, UmCompany umCompany){
        this.opportunity = opportunity;
        this.customer = customer;
        this.orderTotalAmount = orderTotalAmount;
        this.discount = discount;
        this.netAmount = netAmount;
        this.paidAmount = paidAmount;
        this.orderDate = orderDate;
        this.createdBy = createdBy;
        this.orderChangeDate = orderChangeDate;
        this.changedBy = changedBy;
        this.status = status;
        this.orderCreation = orderCreation;
        this.umCompany = umCompany;
	}
	
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	
	
	public CsContactORM getCustomer() {
		return customer;
	}

	public void setCustomer(CsContactORM customer) {
		this.customer = customer;
	}

	public SalesOpportunityORM getOpportunity() {
		return opportunity;
	}

	public void setOpportunity(SalesOpportunityORM opportunity) {
		this.opportunity = opportunity;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}
	public Timestamp getOrderChangeDate() {
		return orderChangeDate;
	}


	public void setOrderChangeDate(Timestamp orderChangeDate) {
		this.orderChangeDate = orderChangeDate;
	}


	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	
	public String getOrderCreation() {
		return orderCreation;
	}


	public void setOrderCreation(String orderCreation) {
		this.orderCreation = orderCreation;
	}

	
	public Double getOrderTotalAmount() {
		return orderTotalAmount;
	}


	public void setOrderTotalAmount(Double orderTotalAmount) {
		this.orderTotalAmount = orderTotalAmount;
	}


	public Double getDiscount() {
		return discount;
	}


	public void setDiscount(Double discount) {
		this.discount = discount;
	}


	public Double getPaidAmount() {
		return paidAmount;
	}


	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}


	public Double getNetAmount() {
		return netAmount;
	}


	public void setNetAmount(Double netAmount) {
		this.netAmount = netAmount;
	}


	public Timestamp getOrderAddedon() {
		return orderAddedon;
	}


	public void setOrderAddedon(Timestamp orderAddedon) {
		this.orderAddedon = orderAddedon;
	}


	public UmUser getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(UmUser createdBy) {
		this.createdBy = createdBy;
	}


	public UmUser getChangedBy() {
		return changedBy;
	}


	public void setChangedBy(UmUser changedBy) {
		this.changedBy = changedBy;
	}


	public UmUser getAssignedTo() {
		return assignedTo;
	}


	public void setAssignedTo(UmUser assignedTo) {
		this.assignedTo = assignedTo;
	}
	
	public List<OrderDetailORM> getOrderDetailList() {
		return orderDetailList;
	}


	public void setOrderDetailList(List<OrderDetailORM> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}


	public UmCompany getUmCompany() {
		return umCompany;
	}


	public void setUmCompany(UmCompany umCompany) {
		this.umCompany = umCompany;
	}


	public Timestamp getOrderAddedOn() {
		return orderAddedon;
	}


	public void setOrderAddedOn(Timestamp orderAddedOn) {
		this.orderAddedon = orderAddedOn;
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


	public String getDateString()
	{
		String S = new SimpleDateFormat("dd/MM/yyyy").format(orderDate);
		return S;
	}


	

}

