package com.srpl.crm.web.model;

import java.io.Serializable;
import java.util.Date;

import com.srpl.crm.web.model.sales.ProductBackingBean;
import com.srpl.crm.web.model.um.customer.ContactBackingBean;

public class SHistoryBackingBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Date date;
	private Boolean isPackage;
	private Boolean isSubscribe;
	private ContactBackingBean subscriber;
	private PackagesBackingBean packg;
	private ProductBackingBean product;

	
	public void reset(){
		id = null;
		//date = ;
		isPackage = true;
		isSubscribe = true;
		//subscriber = ;
		//private SPackageORM packg;
		//private ProductORM product;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public Boolean getIsPackage() {
		return isPackage;
	}


	public void setIsPackage(Boolean isPackage) {
		this.isPackage = isPackage;
	}


	public Boolean getIsSubscribe() {
		return isSubscribe;
	}


	public void setIsSubscribe(Boolean isSubscribe) {
		this.isSubscribe = isSubscribe;
	}


	public ContactBackingBean getSubscriber() {
		return subscriber;
	}


	public void setSubscriber(ContactBackingBean subscriber) {
		this.subscriber = subscriber;
	}


	public PackagesBackingBean getPackg() {
		return packg;
	}


	public void setPackg(PackagesBackingBean packg) {
		this.packg = packg;
	}


	public ProductBackingBean getProduct() {
		return product;
	}


	public void setProduct(ProductBackingBean product) {
		this.product = product;
	}
	
}
