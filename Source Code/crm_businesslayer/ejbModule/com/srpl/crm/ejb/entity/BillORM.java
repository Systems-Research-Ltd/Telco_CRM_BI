package com.srpl.crm.ejb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "s_bill")
public class BillORM  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="order_id")
	private Long orderId;
	
	@Column(name="reimburse_amount")
	private Long reimburseAmount;
	
	@Column(name="received_by")
	private Long billReceivedBy;
	
	
	@Column(name="company_id")
	private Long companyId;
	
	
	public BillORM(){
		
	}
	
	public BillORM( Long orderId ,Long reimburseAmount,Long billReceivedBy,Long companyId){

		this.orderId = orderId;
	//	this.isReimburse = isReimburse;
		this.reimburseAmount = reimburseAmount;
		this.billReceivedBy = billReceivedBy;
		this.companyId=companyId;
	}
	
	
	
	public Long getReimburseAmount() {
		return reimburseAmount;
	}
	public void setReimburseAmount(Long reimburseAmount) {
		this.reimburseAmount = reimburseAmount;
	}
	public Long getbillReceivedBy() {
		return billReceivedBy;
	}
	public void setbillReceivedBy(Long billReceivedBy) {
		this.billReceivedBy = billReceivedBy;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

}
