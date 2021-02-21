package com.srpl.crm.web.model;

/**
 * @author Hammad Hassan Khan
 *
 */
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import com.srpl.crm.ejb.entity.CsContactORM;
import com.srpl.crm.ejb.entity.ProductORM;
import com.srpl.crm.ejb.entity.SPackageORM;

@ManagedBean(name="subscription")
public class SubscriptionBackingBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Boolean isPackage;
	private CsContactORM subscriber;
	private SPackageORM packg;
	private ProductORM product;
	private String packageOrProduct;
	private String title;

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
	
	public Boolean getIsPackage() {
		return isPackage;
	}

	public void setIsPackage(Boolean isPackage) {
		this.isPackage = isPackage;
	}

	public CsContactORM getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(CsContactORM subscriber) {
		this.subscriber = subscriber;
	}

	public SPackageORM getPackg() {
		return packg;
	}

	public void setPackg(SPackageORM packg) {
		this.packg = packg;
	}

	public ProductORM getProduct() {
		return product;
	}

	public void setProduct(ProductORM product) {
		this.product = product;
	}

	public void reset(){
		id = null;
	}

	public String getPackageOrProduct() {
		return packageOrProduct;
	}

	public void setPackageOrProduct(String packageOrProduct) {
		this.packageOrProduct = packageOrProduct;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}

