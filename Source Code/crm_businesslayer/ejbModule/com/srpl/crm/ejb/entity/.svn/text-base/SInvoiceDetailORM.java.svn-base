package com.srpl.crm.ejb.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the s_invoice_details database table.
 * 
 */
@Entity
@Table(name="s_invoice_details")
public class SInvoiceDetailORM implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="S_INVOICE_DETAILS_ID_GENERATOR", sequenceName="ASSIGNED_TASKS_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="S_INVOICE_DETAILS_ID_GENERATOR")
	private Long id;

	@Column(name="product_unit_price")
	private double productUnitPrice;
	
	@Column(name="quantity")
	private Integer quantity;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="invoice_id")
	private SInvoiceORM invoice;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="product_id")
	private ProductORM product;

    public SInvoiceDetailORM() {
    }

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SInvoiceORM getInvoice() {
		return invoice;
	}

	public void setInvoice(SInvoiceORM invoice) {
		this.invoice = invoice;
	}

	public ProductORM getProduct() {
		return this.product;
	}

	public void setProduct(ProductORM productId) {
		this.product = productId;
	}

	public double getProductUnitPrice() {
		return this.productUnitPrice;
	}

	public void setProductUnitPrice(double productUnitPrice) {
		this.productUnitPrice = productUnitPrice;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}