package com.srpl.crm.ejb.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


/**
 * The persistent class for the s_packages database table.
 * 
 */
@Entity
@Table(name="s_packages")
public class SPackageORM implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="S_PACKAGES_ID_GENERATOR", sequenceName="S_PACKAGES_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="S_PACKAGES_ID_GENERATOR")
	@Column(name="id")
	private Long id;

	@Column(name="cost")
	private double cost;

	@Column(name="title")
	private String title;
	
	@Column(name="company_id")
	private Long companyId;
	
	@Column(name="package_addedon")
	private Timestamp packageAddedon;

	@Column(name = "mapped_id")
	private Long mappedId;
	
	@Transient
	private String isCorrect; 

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	//New Mapping MKYONG STYLE
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "s_package_products", joinColumns = { 
			@JoinColumn(name = "package_id", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "product_id", 
					nullable = false, updatable = false) })
	private Set<ProductORM> products = new HashSet<ProductORM>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "packg")
	Set<SServiceSubscribeORM> subsciptions = new HashSet<SServiceSubscribeORM>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "packg")
	Set<SServiceSubscriptionHistoryORM> sHistory = new HashSet<SServiceSubscriptionHistoryORM>(0);
	
	//IT ENDS HERE
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getCost() {
		return this.cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<ProductORM> getProducts() {
		return products;
	}

	public void setProducts(Set<ProductORM> products) {
		this.products = products;
	}

	public Set<SServiceSubscribeORM> getSubsciptions() {
		return subsciptions;
	}

	public void setSubsciptions(Set<SServiceSubscribeORM> subsciptions) {
		this.subsciptions = subsciptions;
	}

	public Set<SServiceSubscriptionHistoryORM> getsHistory() {
		return sHistory;
	}

	public void setsHistory(Set<SServiceSubscriptionHistoryORM> sHistory) {
		this.sHistory = sHistory;
	}

	public Timestamp getPackageAddedon() {
		return packageAddedon;
	}

	public void setPackageAddedon(Timestamp packageAddedon) {
		this.packageAddedon = packageAddedon;
	}

	public String getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(String isCorrect) {
		this.isCorrect = isCorrect;
	}

	public Long getMappedId() {
		return mappedId;
	}

	public void setMappedId(Long mappedId) {
		this.mappedId = mappedId;
	}	
	
}