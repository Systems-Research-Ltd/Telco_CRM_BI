package com.srpl.crm.ejb.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the s_service_subscription_history database table.
 * 
 */
@Entity
@Table(name="s_service_subscription_history")
public class SServiceSubscriptionHistoryORM implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="S_SERVICE_SUBSCRIPTION_HISTORY_ID_GENERATOR", sequenceName="S_SERVICE_SUBSCRIPTION_HISTORY_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="S_SERVICE_SUBSCRIPTION_HISTORY_ID_GENERATOR")
	private Long id;

	private Timestamp date;

	@Column(name="is_package")
	private Boolean isPackage;

	@Column(name="is_subscribe")
	private Boolean isSubscribe;

	@Transient
	private String packageOrProduct;
	
	@Transient
	private String title;

	/*
	 * Relationship MKYON Style
	 */
	
	//Customer
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id")
	CsContactORM subscriber;
	
	//Packages
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "package_id")
	SPackageORM packg;
	
	//Products
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "service_product_id")
	ProductORM product;
	
	/*
	 * ends here
	 */

    public SServiceSubscriptionHistoryORM() {
    }

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getDate() {
		return this.date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public Boolean getIsPackage() {
		return this.isPackage;
	}

	public void setIsPackage(Boolean isPackage) {
		this.isPackage = isPackage;
	}

	public Boolean getIsSubscribe() {
		return this.isSubscribe;
	}

	public void setIsSubscribe(Boolean isSubscribe) {
		this.isSubscribe = isSubscribe;
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

	public String getPackageOrProduct() {
		packageOrProduct = isPackage ? "Package":"Product";
		return packageOrProduct;
	}

	public void setPackageOrProduct(String packageOrProduct) {
		this.packageOrProduct = packageOrProduct;
	}

	public String getTitle() {
		title = isPackage ? packg.getTitle():product.getProductTitle();
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	

}