package com.srpl.crm.ejb.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the s_package_products database table.
 * 
 */
@Entity
@Table(name="s_package_products")
public class SPackageProductORM implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="S_PACKAGE_PRODUCTS_ID_GENERATOR", sequenceName="S_PACKAGE_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="S_PACKAGE_PRODUCTS_ID_GENERATOR")
	private Long id;

	//bi-directional many-to-one association to SPackage
    @ManyToOne
	@JoinColumn(name="package_id")
	private SPackageORM SPackage;

	//bi-directional many-to-one association to Product
    @ManyToOne
	@JoinColumn(name="product_id")
	private ProductORM product;

    public SPackageProductORM() {
    }

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SPackageORM getSPackage() {
		return this.SPackage;
	}

	public void setSPackage(SPackageORM SPackage) {
		this.SPackage = SPackage;
	}
	
	public ProductORM getProduct() {
		return this.product;
	}

	public void setProduct(ProductORM product) {
		this.product = product;
	}
	
}