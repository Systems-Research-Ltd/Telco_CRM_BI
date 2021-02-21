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
import javax.persistence.Transient;

@Entity
@Table(name = "orders_detail")
public class OrderDetailORM implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ORDERS_DETAIL_ORDERDETAILID_GENERATOR", sequenceName="orders_detail_order_detail_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ORDERS_DETAIL_ORDERDETAILID_GENERATOR")
	@Column(name = "order_detail_id")
	private Long orderDetailId;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private OrderORM order;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private ProductORM product;
	
	
	@Column(name = "quantity")
	private Integer quantity;

	@Column(name = "product_total_amount")
	private Double productTotalAmount; 
	
	@Transient
	private Long tempId;
	
	public OrderDetailORM(){
		
	}
	public String getProductTitle(){
		  return product.getProductTitle();
	}
	
	@Deprecated
    public OrderDetailORM(OrderORM order, ProductORM product, Integer quantity, Long orderAmmount, Long paidAmount, Double discount, Long netAmount){
    	this.order = order;
    	this.product = product;
    	this.quantity = quantity;
   	}
	@Deprecated
    public OrderDetailORM(Long orderDetailId, OrderORM order, ProductORM product, Integer quantity, Long orderAmmount, Long paidAmount, Double discount, Long netAmount){
    	this.orderDetailId = orderDetailId;
    	this.order = order;
    	this.product = product;
    	this.quantity = quantity;
    	
	}
    
    public OrderDetailORM(OrderORM order, ProductORM product, Integer quantity, Double productTotalAmount){
    	this.order = order;
    	this.product = product;
    	this.quantity = quantity;
    	this.productTotalAmount = productTotalAmount;
   	}
    public OrderDetailORM(Long orderDetailId, OrderORM order, ProductORM product, Integer quantity, Double productTotalAmount){
    	this.orderDetailId = orderDetailId;
    	this.order = order;
    	this.product = product;
    	this.quantity = quantity;
    	this.productTotalAmount = productTotalAmount;
    	
	}
 
//    public OrderDetailORM(OrderORM order, ProductORM product, Integer quantity){
	
//    }
	
	
	public Long getOrderDetailId() {
		return orderDetailId;
	}
	public void setOrderDetailId(Long orderDetailId) {
		this.orderDetailId = orderDetailId;
	}
	public OrderORM getOrder() {
		return order;
	}
	public void setOrder(OrderORM order) {
		this.order = order;
	}
	public ProductORM getProduct() {
		return product;
	}
	public void setProduct(ProductORM product) {
		this.product = product;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getProductTotalAmount() {
		return productTotalAmount;
	}
	public void setProductTotalAmount(Double productTotalAmount) {
		this.productTotalAmount = productTotalAmount;
	}
	public Long getTempId() {
		return tempId;
	}
	public void setTempId(Long tempId) {
		this.tempId = tempId;
	}

}
