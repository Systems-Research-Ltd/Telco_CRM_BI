package com.srpl.crm.ejb.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the s_service_subscribe database table.
 * 
 */
@Entity
@Table(name="s_service_subscribe")
public class SServiceSubscribeORM implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="S_SERVICE_SUBSCRIBE_ID_GENERATOR", sequenceName="S_SERVICE_SUBSCRIBE_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="S_SERVICE_SUBSCRIBE_ID_GENERATOR")
	private Long id;

	@Column(name="is_package")
	private Boolean isPackage;

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
	@JoinColumn(name = "product_service_id")
	ProductORM product;
	
	/*
	 * ends here
	 */

    public SServiceSubscribeORM() {
    }

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getIsPackage() {
		return this.isPackage;
	}

	public void setIsPackage(Boolean isPackage) {
		this.isPackage = isPackage;
	}
	
	public String getPackageOrProduct() {
		this.packageOrProduct = isPackage ? "Package":"Product";
		return packageOrProduct;
	}
	
	public String getTitle() {
		this.title = isPackage ? this.packg.getTitle():this.product.getProductTitle();
		return title;
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


}