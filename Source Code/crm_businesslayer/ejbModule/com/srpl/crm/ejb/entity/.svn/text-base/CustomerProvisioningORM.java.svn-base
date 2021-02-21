package com.srpl.crm.ejb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "customer_provisioning")
public class CustomerProvisioningORM {

	@Id
	@SequenceGenerator(name = "CUSTOMER_PROVISIONINGID_GENERATOR", sequenceName = "CUSTOMER_PROVISIONING_CUSTOMER_PROVISIONING_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTOMER_PROVISIONINGID_GENERATOR")
	@Column(name = "customer_provisioning_id")
	private Long customerProvisiongId;

	@Column(name = "product_mac_address")
	private String productMACAddress;

	@Column(name = "host")
	private String host;

	@Column(name = "port")
	private String port;

	@Column(name = "login_name")
	private String loginName;
	
	@Column(name = "password")
	private String password;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private CsContactORM customer;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private ProductORM product;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private OrderORM order;
	
	
	public CustomerProvisioningORM(){
		
	}
	
    public CustomerProvisioningORM(Long customerProvisiongId, CsContactORM customer, OrderORM order, ProductORM product, String productMACAddress, String host, String port, String loginName, String password){
       this.customerProvisiongId = customerProvisiongId;
       this.customer = customer;
       this.order = order;
       this.product = product;
       this.productMACAddress = productMACAddress;
       this.host = host;
       this.port = port;
       this.loginName = loginName;
       this.password = password;
	}
    
    public CustomerProvisioningORM(CsContactORM customer, OrderORM order, ProductORM product, String productMACAddress, String host, String port, String loginName, String password){
       this.customer = customer;
       this.order = order;
       this.product = product;
       this.productMACAddress = productMACAddress;
       this.host = host;
       this.port = port;
       this.loginName = loginName;
       this.password = password;
	}
	
	public Long getCustomerProvisiongId() {
		return customerProvisiongId;
	}

	public void setCustomerProvisiongId(Long customerProvisiongId) {
		this.customerProvisiongId = customerProvisiongId;
	}

	public String getProductMACAddress() {
		return productMACAddress;
	}

	public void setProductMACAddress(String productMACAddress) {
		this.productMACAddress = productMACAddress;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public CsContactORM getCustomer() {
		return customer;
	}

	public void setCustomer(CsContactORM customer) {
		this.customer = customer;
	}

	public ProductORM getProduct() {
		return product;
	}

	public void setProduct(ProductORM product) {
		this.product = product;
	}

	public OrderORM getOrder() {
		return order;
	}

	public void setOrder(OrderORM order) {
		this.order = order;
	}
	
	public Long getOrderId() {
		return this.order.getOrderId();
	}

	public String getProductTitle() {
		return this.product.getProductTitle();
	}

}
