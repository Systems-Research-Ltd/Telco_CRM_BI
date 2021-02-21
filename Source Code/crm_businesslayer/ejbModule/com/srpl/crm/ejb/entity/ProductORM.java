package com.srpl.crm.ejb.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.srpl.um.ejb.entity.UmCompany;

@Entity
@Table(name = "products")
public class ProductORM implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="PRODUCTS_PRODUCTID_GENERATOR", sequenceName="products_product_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRODUCTS_PRODUCTID_GENERATOR")
	@Column(name = "product_id")
	private Long productId;
	@Column(name = "product_title")
	private String productTitle;
	@Column(name = "product_cost")
	private Long productCost;
	@Column(name = "product_description")
	private String productDescription;
	@Column(name = "product_type")
	private String productType;
	@Column(name = "product_addedon")
	private Timestamp productAddedon;
	@Column(name = "product_image")
	private String productImage;
	@Column(name = "product_stock")
	private Long productStock;
	@Column(name = "mapped_id")
	private Long mappedId;
	@ManyToOne
	@JoinColumn(name="product_company_id")
	private UmCompany umCompany;
	
	@Transient
	private String isCorrect;

	//Chnages from Hammad Hassan
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "products")
	private Set<SPackageORM> packages = new HashSet<SPackageORM>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	private Set<SServiceSubscribeORM> subscription = new HashSet<SServiceSubscribeORM>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	private Set<SServiceSubscriptionHistoryORM> sHistory = new HashSet<SServiceSubscriptionHistoryORM>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	private Set<SInvoiceDetailORM> invoiceDetails = new HashSet<SInvoiceDetailORM>(0);
	
	//Changes ends here
	
	
	public ProductORM(){
		
	}
	
	@Deprecated
    public ProductORM(Long productId, String productTitle, Long productCost, String productDescription, String productType, String productImage){
		this.productId = productId;
		this.productTitle = productTitle;
		this.productCost = productCost;
    	this.productDescription = productDescription;
    	this.productType = productType;
    	this.productImage = productImage;
		
	}
    
    public ProductORM(Long productId, String productTitle, Long productCost, String productDescription, String productType, String productImage, UmCompany umCompany){
		this.productId = productId;
		this.productTitle = productTitle;
		this.productCost = productCost;
    	this.productDescription = productDescription;
    	this.productType = productType;
    	this.productImage = productImage;
    	this.umCompany = umCompany;
		
	}
    @Deprecated
    public ProductORM(String productTitle, Long productCost, String productDescription, String productType, String productImage){
    	this.productTitle = productTitle;
    	this.productCost = productCost;
    	this.productDescription = productDescription;
    	this.productType = productType;
    	this.productImage = productImage;
    }
	
    public ProductORM(String productTitle, Long productCost, String productDescription, String productType, String productImage, UmCompany umCompany){
    	this.productTitle = productTitle;
    	this.productCost = productCost;
    	this.productDescription = productDescription;
    	this.productType = productType;
    	this.productImage = productImage;
    	this.umCompany = umCompany;
    }
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductTitle() {
		return productTitle;
	}
	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	public Long getProductCost() {
		return productCost;
	}

	public void setProductCost(Long productCost) {
		this.productCost = productCost;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	
	public Set<SPackageORM> getPackages() {
		return packages;
	}

	public void setPackages(Set<SPackageORM> packages) {
		this.packages = packages;
	}

	public Set<SServiceSubscribeORM> getSubscription() {
		return subscription;
	}

	public void setSubscription(Set<SServiceSubscribeORM> subscription) {
		this.subscription = subscription;
	}

	public Set<SServiceSubscriptionHistoryORM> getsHistory() {
		return sHistory;
	}

	public void setsHistory(Set<SServiceSubscriptionHistoryORM> sHistory) {
		this.sHistory = sHistory;
	}

	public Set<SInvoiceDetailORM> getInvoiceDetails() {
		return invoiceDetails;
	}

	public void setInvoiceDetails(Set<SInvoiceDetailORM> invoiceDetails) {
		this.invoiceDetails = invoiceDetails;
	}

	public String getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(String isCorrect) {
		this.isCorrect = isCorrect;
	}

	public Timestamp getProductAddedon() {
		return productAddedon;
	}

	public void setProductAddedon(Timestamp productAddedon) {
		this.productAddedon = productAddedon;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public Long getProductStock() {
		return productStock;
	}

	public void setProductStock(Long productStock) {
		this.productStock = productStock;
	}

	public UmCompany getUmCompany() {
		return umCompany;
	}

	public void setUmCompany(UmCompany umCompany) {
		this.umCompany = umCompany;
	}

	public Long getMappedId() {
		return mappedId;
	}

	public void setMappedId(Long mappedId) {
		this.mappedId = mappedId;
	}	
	
	

}
