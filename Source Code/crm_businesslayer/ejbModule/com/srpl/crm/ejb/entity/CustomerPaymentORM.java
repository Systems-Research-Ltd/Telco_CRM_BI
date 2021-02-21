package com.srpl.crm.ejb.entity;



	import java.util.Date;
	import javax.persistence.Column;
	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import javax.persistence.Id;
	import javax.persistence.SequenceGenerator;
	import javax.persistence.Table;

@Entity
@Table(name = "customer_payment")
public class CustomerPaymentORM {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		
		@Id
		@SequenceGenerator(name="ID_GENERATOR", sequenceName="id_seq", allocationSize = 1)
		@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ID_GENERATOR")
		@Column(name = "id")
		private Long Id;
		@Column(name = "customer_id")
		private Long customerId;
		@Column(name = "payment_date")
		private Date paymentDate;
		@Column(name = "total_amount")
		private Long totalAmount;
		@Column(name = "paid_amount")
		private Long paidAmount;
	
				
		
		public CustomerPaymentORM(){
			
		}		
		
		
		public CustomerPaymentORM(Long Id, Long customerId, Date paymentDate, Long totalAmount, Long paidAmount) {
	        this.Id = Id;
	        this.customerId = customerId;
	        this.paymentDate = paymentDate;
	        this.totalAmount = totalAmount;
	        this.paidAmount = paidAmount;

		}
		
		public CustomerPaymentORM(Long customerId, Date paymentDate, Long totalAmount, Long paidAmount) {
	        this.customerId = customerId;
	        this.paymentDate = paymentDate;
	        this.totalAmount = totalAmount;
	        this.paidAmount = paidAmount;
		}


		public Long getId() {
			return Id;
		}


		public void setId(Long id) {
			Id = id;
		}


		public Long getCustomerId() {
			return customerId;
		}


		public void setCustomerId(Long customerId) {
			this.customerId = customerId;
		}


		public Date getPaymentDate() {
			return paymentDate;
		}


		public void setPaymentDate(Date paymentDate) {
			this.paymentDate = paymentDate;
		}


		public Long getTotalAmount() {
			return totalAmount;
		}


		public void setTotalAmount(Long totalAmount) {
			this.totalAmount = totalAmount;
		}


		public Long getPaidAmount() {
			return paidAmount;
		}


		public void setPaidAmount(Long paidAmount) {
			this.paidAmount = paidAmount;
		}


}


