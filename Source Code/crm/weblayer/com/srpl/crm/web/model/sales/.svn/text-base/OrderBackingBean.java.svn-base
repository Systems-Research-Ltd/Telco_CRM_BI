package com.srpl.crm.web.model.sales;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.component.datatable.DataTable;

import com.bitguiders.util.jsf.JSFBeanInterface;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;
import com.srpl.crm.ejb.entity.CsContactORM;
import com.srpl.crm.ejb.entity.CustomerProvisioningORM;
import com.srpl.crm.ejb.entity.OrderDetailORM;
import com.srpl.crm.ejb.entity.OrderORM;
import com.srpl.crm.ejb.entity.ProductORM;
import com.srpl.crm.ejb.entity.SalesOpportunityORM;
import com.srpl.crm.ejb.exceptions.ContactNotFoundException;
import com.srpl.crm.ejb.exceptions.ProductNotFoundException;
import com.srpl.crm.ejb.request.ContactDAO;
import com.srpl.crm.ejb.request.CustomerProvisioningDAO;
import com.srpl.crm.ejb.request.InvoiceDAO;
import com.srpl.crm.ejb.request.OrderDAO;
import com.srpl.crm.ejb.request.OrderDetailDAO;
import com.srpl.crm.ejb.request.ProductDAO;
import com.srpl.crm.web.common.SessionDataBean;
import com.srpl.crm.web.controller.BeanFactory;
import com.srpl.crm.web.model.SecurityBackingBean;
import com.srpl.crm.web.model.common.ColumnModel;
import com.srpl.crm.web.model.loyalty.LoyaltyBackingBean;
import com.srpl.um.ejb.entity.UmCompany;
import com.srpl.um.ejb.entity.UmUser;
import com.srpl.um.ejb.exceptions.UserNotFoundException;
import com.srpl.um.ejb.request.CompanyDAO;
import com.srpl.um.ejb.request.UserDAO;

@ManagedBean(name = "orderBean")
public class OrderBackingBean extends JSFBeanSupport implements
		JSFBeanInterface, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<ColumnModel> columns;
	private List<ColumnModel> columnsCustomerInterface;
	private Long orderId;
	private Long customerId;
	private CsContactORM customer;
	private Long opportunityId;
	private SalesOpportunityORM opportunity;
	private Long productId;
	private ProductORM product;
	private Long companyId;
	private UmCompany umCompany;
	private Integer quantity;
	private Timestamp orderDate;
	private Timestamp orderChangeDate;
	private Long createdById;
	private UmUser createdBy;
	private Long changedById;
	private UmUser changedBy;
	private String status;
	private List<OrderORM> orderList;
	private List<OrderDetailORM> orderProductList;
	private List<CsContactORM> customerList;
	private List<ProductORM> productList;
//	List<OrderDetailORM> orderDetailList;
//	Set<OrderDetailORM> orderDetailList;
	private List<Integer> productQuantities = new ArrayList<Integer>();
	private transient DataTable datatable;
//	private Boolean disabled = true;
	private String orderCreation;
	private Double orderTotalAmount;
	private Double productTotalAmount;
	private Double discount;
	private Double netAmount;
	private Double paidAmount;
	private Double productCost;
	private Boolean disableFields = true;
	private Boolean fromOrderNow = false;
	private SessionDataBean session;

	// ======================= provisioning detail =======================//
	private String MACAddress;
	private String host;
	private String port;
	private String loginName;
	private String password;

	@EJB
	OrderDAO orderDao;
	@EJB
	OrderDetailDAO orderDetailDao;
	@EJB
	ContactDAO contactDao;
	@EJB
	ProductDAO productDao;
	@EJB
	InvoiceDAO invoiceDao;
	@EJB
	UserDAO userDao;
	@EJB
	CustomerProvisioningDAO provisioningDao;
	@EJB CompanyDAO companyDao;

	public OrderBackingBean() {
		columns = new ArrayList<ColumnModel>();
		columns.add(new ColumnModel("orderId", "ID"));
		columns.add(new ColumnModel("customerName", "CUSTOMER"));
		columns.add(new ColumnModel("dateString", "ORDER DATE"));
		columns.add(new ColumnModel("productTitle", "PRODUCT TITLE"));
		columns.add(new ColumnModel("status", "STATUS"));
		String action = getParameter("action");
		columnsCustomerInterface = new ArrayList<ColumnModel>();
		columnsCustomerInterface.add(new ColumnModel("orderId", "ID"));
		columnsCustomerInterface.add(new ColumnModel("status", "STATUS"));
		columnsCustomerInterface
				.add(new ColumnModel("dateString", "ORDER DATE"));
		columnsCustomerInterface.add(new ColumnModel("orderCreation",
				"ORDER CREATION"));
		columnsCustomerInterface.add(new ColumnModel("status", "STATUS"));

		if(action != null && (action.equalsIgnoreCase("edit") || action.equalsIgnoreCase("delete"))){
        	setDisabled(true);
        }
		if(action != null && (action.equalsIgnoreCase("viewProvisioning"))){
			setDisabled(true);
		}
        
		
		if (action != null && (action.equalsIgnoreCase("save"))
				|| (action.equalsIgnoreCase("update"))
				|| (action.equalsIgnoreCase(""))) {
			disableFields = false;
		} else {
			disableFields = true;
		}

		System.out.println("currentAction = " + getAction());
		setCurrentAction(WebConstants.ACTION_SECURITY, this.getClass());
	}

	@PostConstruct
	public void loadOrderOnProvisioning() {
		System.out.println("loadOrderOnProvisioning() called");
		session = BeanFactory.getInstance().getSessionBean();
		Long orderId = session.getSalesModule_selectedOrder();
		if (getAction().equalsIgnoreCase("saveProvisioning")) {
			loadOrder(orderId);
		}
		
		if (session.getCampaignProducts().size() > 0) {
			orderNow();
		}
		// If Redirected from DASHBOARD, load selected Order
		if(!getParameter("row_id").isEmpty())
		{
			session.setSalesModule_selectedOrder(Long.parseLong(getParameter("row_id")));
			orderId = session.getSalesModule_selectedOrder();
			setCurrentAction("view");
	//		actionListener();
			try 
			{
				//Long orderId = session.getSalesModule_selectedOrder();
				loadOrder(orderId);
				//loadOrderProvisioning(orderId);
				session.setSalesModule_orderTabIndex(0);
			} catch (Exception e) {
				// handle exception
				addError(getProperty("message.order.error.load"));
				System.out.println("Exception Occured OrderBackingBean constructor");
			}
		}
		
	}

	public void loadOrder(Long orderId) {
		this.orderId = orderId;
		if (orderId != null && orderId > 0) {
			try {
				OrderORM order = orderDao.retrieveOrder(orderId);
				this.orderId = order.getOrderId();
				this.customer = order.getCustomer();
				this.customerId = order.getCustomer().getContactId();
				this.orderDate = order.getOrderDate();
				this.orderChangeDate = order.getOrderChangeDate();
				this.status = order.getStatus();
				this.orderCreation = order.getOrderCreation();
				this.orderTotalAmount = order.getOrderTotalAmount();
				this.discount = order.getDiscount();
				this.netAmount = order.getNetAmount();
				this.paidAmount = order.getPaidAmount();
				
				session.setOrderDetailList(orderDetailDao.orderDetailsByOrder(order));
				for(OrderDetailORM orderDetail : session.getOrderDetailList()){
					orderDetail.setTempId(orderDetail.getOrderDetailId());
				}
				
				listOrderProducts(order);
				
			} catch (Exception e) {
				System.out.println("exception while loading Order.");
				e.printStackTrace();
			}
		}
	}

	public List<ColumnModel> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnModel> columns) {
		this.columns = columns;
	}

	public List<ColumnModel> getColumnsCustomerInterface() {
		return columnsCustomerInterface;
	}

	public void setColumnsCustomerInterface(
			List<ColumnModel> columnsCustomerInterface) {
		this.columnsCustomerInterface = columnsCustomerInterface;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getOpportunityId() {
		return opportunityId;
	}

	public void setOpportunityId(Long opportunityId) {
		this.opportunityId = opportunityId;
	}

	public CsContactORM getCustomer() {
		return customer;
	}

	public void setCustomer(CsContactORM customer) {
		this.customer = customer;
	}

	public SalesOpportunityORM getOpportunity() {
		return opportunity;
	}

	public void setOpportunity(SalesOpportunityORM opportunity) {
		this.opportunity = opportunity;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public ProductORM getProduct() {
		return product;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public UmCompany getUmCompany() {
		return umCompany;
	}

	public void setUmCompany(UmCompany umCompany) {
		this.umCompany = umCompany;
	}

	public void setProduct(ProductORM product) {
		this.product = product;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public Timestamp getOrderChangeDate() {
		return orderChangeDate;
	}

	public void setOrderChangeDate(Timestamp orderChangeDate) {
		this.orderChangeDate = orderChangeDate;
	}


	public Long getCreatedById() {
		return createdById;
	}

	public void setCreatedById(Long createdById) {
		this.createdById = createdById;
	}

	public UmUser getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UmUser createdBy) {
		this.createdBy = createdBy;
	}

	public Long getChangedById() {
		return changedById;
	}

	public void setChangedById(Long changedById) {
		this.changedById = changedById;
	}

	public UmUser getChangedBy() {
		return changedBy;
	}

	public void setChangedBy(UmUser changedBy) {
		this.changedBy = changedBy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<OrderORM> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<OrderORM> orderList) {
		this.orderList = orderList;
	}

	public List<OrderDetailORM> getOrderProductList() {
		return orderProductList;
	}

	public void setOrderProductList(List<OrderDetailORM> orderProductList) {
		this.orderProductList = orderProductList;
	}

	public List<CsContactORM> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<CsContactORM> customerList) {
		this.customerList = customerList;
	}

	public List<ProductORM> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductORM> productList) {
		this.productList = productList;
	}

	/*public List<OrderDetailORM> getOrderDetailList() {
		return orderDetailList;
	}

	public void setOrderDetailList(List<OrderDetailORM> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}*/

	public List<Integer> getProductQuantities() {
		return productQuantities;
	}

	public void setProductQuantities(List<Integer> productQuantities) {
		this.productQuantities = productQuantities;
	}

	/*
	 * public Integer[] getOnlineProductsQuantities() { return
	 * onlineProductsQuantities; }
	 * 
	 * 
	 * public void setOnlineProductsQuantities(Integer[]
	 * onlineProductsQuantities) { this.onlineProductsQuantities =
	 * onlineProductsQuantities; }
	 */

	public void setDatatable(DataTable datatable) {
		this.datatable = datatable;
	}

	public DataTable getDatatable() {
		return datatable;
	}

	public Double getOrderTotalAmount() {
		return orderTotalAmount;
	}

	public void setOrderTotalAmount(Double orderTotalAmount) {
		this.orderTotalAmount = orderTotalAmount;
	}

	public Double getProductTotalAmount() {
		return productTotalAmount;
	}

	public void setProductTotalAmount(Double productTotalAmount) {
		this.productTotalAmount = productTotalAmount;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(Double netAmount) {
		this.netAmount = netAmount;
	}

	public Double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}

	public Double getProductCost() {
		return productCost;
	}

	public void setProductCost(Double productCost) {
		this.productCost = productCost;
	}

/*	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}
*/
	public String getOrderCreation() {
		return orderCreation;
	}

	public void setOrderCreation(String orderCreation) {
		this.orderCreation = orderCreation;
	}

	public Boolean getDisableFields() {
		return disableFields;
	}

	public void setDisableFields(Boolean disableFields) {
		this.disableFields = disableFields;
	}

	public String getMACAddress() {
		return MACAddress;
	}

	public void setMACAddress(String mACAddress) {
		MACAddress = mACAddress;
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
	
	public String getProductTitle(){
		if(this.product != null){
			return this.product.getProductTitle();
		}else{
			return "";
		}
	}
	

	public List<CsContactORM> listCustomers() {
		try {
			customerList = contactDao.listContacts(session.getCompanyId());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return customerList;
	}

	public List<CsContactORM> listAutoCustomers(String query) {
		try {
			customerList = contactDao.listAutoContacts(session.getCompanyId(),
					query);
		} catch (Exception e) {
			System.out
					.println("Exception occured in OrderBackingBean listAutoCustomers()");
			e.printStackTrace();
		}
		return customerList;
	}

	public List<ProductORM> listProducts() {
		try{
		   umCompany = companyDao.companyDetails(session.getCompanyId());	
		}catch(Exception e){
		   System.out.println(e.getMessage());
		}
		try {
			productList = productDao.listProducts(umCompany);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return productList;
	}

	/*
	 * public DualListModel<ProductORM> listProducts(){ try{
	 * 
	 * productListSource = productDao.listProducts(); productListTarget = new
	 * ArrayList<ProductORM>();
	 * 
	 * productList = new DualListModel<ProductORM>(productListSource,
	 * productListTarget);
	 * 
	 * }catch(ProductNotFoundException e){ System.out.println(e.getMessage()); }
	 * return productList; }
	 */

	public List<OrderDetailORM> listOrderProducts(OrderORM order) {
		orderProductList = orderDetailDao.listOrderProducts(order);
		return orderProductList;

	}

	public Long createOrder() {
		System.out.println("create order called");
		if (FacesContext.getCurrentInstance().getExternalContext().isUserInRole("Customer")) {
			orderCreation = "Online";
			try {
				customer = contactDao.getContactByUserId(session.getUserId());
			} catch (ContactNotFoundException e) {
				System.out.println(e.getMessage());
			}
		} else {
			orderCreation = "Manual";
			try {
				customer = contactDao.contactDetails(getCustomerId());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		if (customer == null) {
			addError(getProperty("message.validate.support.customer.invalid"));
			return -1l;
		} else {
			if (customer.getContactId() == null) {
				addError(getProperty("message.validate.support.customer.invalid"));
				return -1l;
			}
		}

		try{
		   umCompany = companyDao.companyDetails(session.getCompanyId());	
		}catch(Exception e){
		   System.out.println(e.getMessage());
		}
		UmUser createdBy = null;
		UmUser changedBy = null;
		try {
			createdBy = userDao.umUserDetails(session.getUserId());
		} catch (UserNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Date date = new Date();
		
		try {
			product = productDao.productDetails(getProductId());
		} catch (ProductNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		OrderORM order = new OrderORM(opportunity, customer, new Timestamp(
				date.getTime()), createdBy, orderTotalAmount, discount, netAmount, paidAmount, orderChangeDate, changedBy, status, orderCreation, umCompany);

		orderId = orderDao.createOrder(order);

		// order details against this order
		//session.setOrderDetailList(new ArrayList<OrderDetailORM>());
		for(OrderDetailORM orderDetail : session.getOrderDetailList()){
			orderDetail.setOrder(order);
			orderDetailDao.createOrderDetail(orderDetail);
		}

		// Create Invoice against this order
		try {
			invoiceDao.create(orderId);
		} catch (Exception e) {
			addError(getProperty("message.invoice.creation.failed"));
		}
		addMessage(getProperty("message.order.created"));
		return orderId;
	}

	
	public void editOrder(Long orderId) {
		System.out.println("editOrder called()");
		OrderORM savedOrder = orderDao.retrieveOrder(orderId);
		if (FacesContext.getCurrentInstance().getExternalContext()
				.isUserInRole("Customer")) {
			try {
				
				customer = contactDao.getContactByUserId(session
						.getUserId());
			} catch (ContactNotFoundException e) {
				System.out.println(e.getMessage());
			}
		} else {
			try {
				customer = contactDao.contactDetails(getCustomerId());
			} catch (ContactNotFoundException e) {
				System.out.println(e.getMessage());
			}
		}
		try{
		   umCompany = companyDao.companyDetails(session.getCompanyId());	
		}catch(Exception e){
		   System.out.println(e.getMessage());
		}
		Date date = new Date();
		orderChangeDate = new Timestamp(date.getTime());
		
		UmUser changedBy = new UmUser();
		try {
			changedBy = userDao.umUserDetails(session.getUserId());
		} catch (UserNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		createdBy = savedOrder.getCreatedBy();		
		
		OrderORM order = new OrderORM(orderId, savedOrder.getOpportunity(), customer, new Timestamp(
				date.getTime()), createdBy, orderTotalAmount, discount, netAmount, paidAmount, orderChangeDate, changedBy, status, orderCreation, umCompany);

		orderDao.updateOrder(order);
		
		OrderORM updatedOrder = orderDao.retrieveOrder(orderId);

		// order details against this order
		
		List<Long> detailIds = new ArrayList<Long>();
		
		for(OrderDetailORM orderDetail : session.getOrderDetailList()){
			if(orderDetail.getOrderDetailId() != null){
				detailIds.add(orderDetail.getOrderDetailId()); 	
			}
		}
		// delete orderDetails that are deleted in update process
		orderDetailDao.deleteOrderDetail(updatedOrder, detailIds);
		
		for(OrderDetailORM orderDetail : session.getOrderDetailList()){
			if(orderDetail.getOrderDetailId() != null){
			   orderDetailDao.updateOrderDetail(orderDetail);
			}else{
				orderDetail.setOrder(updatedOrder);
				orderDetailDao.createOrderDetail(orderDetail);
			}
		}
		addMessage(getProperty("message.order.updated"));
	}
	
	public void deleteRow(ActionEvent event) {
		System.out.println("OrderBackingBean deleteRow called");
		Long id = (Long) event.getComponent().getAttributes().get("del_id");

		OrderORM search = null;
		for (OrderORM order : orderList) {
			if (order.getOrderId() == id) {
				search = order;
				break;
			}
		}
		if (search != null) {
			// delete from datatable
			orderList.remove(search);
			// delete from OrderDetail
			orderDetailDao.deleteOrderDetail(search);
			// delete from Order
			orderDao.deleteOrder(search.getOrderId());

		}

	}

	public void deleteOrder(Long orderId) {

		System.out.println("OrderBackingBean deleteOrder() called...");
		// Long id = (Long) event.getComponent().getAttributes().get("del_id");
 		// delete from OrderDetail
		//orderDetailDao.deleteOrderDetail(search);
		// delete from Order
		orderDao.deleteOrder(orderId);
		
		addMessage(getProperty("message.order.deleted"));
	}

	public List<OrderORM> listOrders() {
		orderList = orderDao.listOrders();
		return orderList;
	}

	@Override
	public List<OrderORM> getList() {
		// TODO Auto-generated method stub
		try{
			   umCompany = companyDao.companyDetails(session.getCompanyId());	
		}catch(Exception e){
			   System.out.println(e.getMessage());
		}
		
		if (FacesContext.getCurrentInstance().getExternalContext()
				.isUserInRole("Customer")) {
			CsContactORM customer = new CsContactORM();
			try {
				customer = contactDao.getContactByUserId(session.getUserId());
			} catch (Exception e) {
				System.out
						.println("Exception Occured CaseBackingBean getList()");
			}
			try {
				orderList = orderDao.listCustomerOrders(customer);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else {
			try{
			    orderList = orderDao.listOrders(umCompany);
			}catch(Exception e){
				e.printStackTrace();
			}
		}

		return orderList;
	}

	public List<OrderORM> listOrdersAndDetails() {
		orderList = orderDao.listOrdersAndDetails();
		return orderList;
	}

	public List<OrderORM> listOrdersCustomerInterface() {
		Long cId;
		cId = session.getUserId();
		orderList = new ArrayList<OrderORM>();
		orderList = orderDao.listOrders(cId);
		return orderList;
	}
/*
	public void calculateAmmount(Integer index) {
		System.out.println("calculateAmmount() called , index = " + index);
		System.out.println("target list size = " + productListTarget.size());
		System.out.println("onlineProductsQuantities array length = "
				+ onlineProductsQuantities.length);
		System.out.println("onlineProductsQuantities = "
				+ onlineProductsQuantities[index]);

		System.out.println("product cost = "
				+ productListTarget.get(0).getProductCost());
		try {
			ProductORM product = productDao.productDetails(productListTarget
					.get(index).getProductId());

			Long cost = (product.getProductCost())
					* onlineProductsQuantities[index];
			productListTarget.get(index).setProductCost(cost);
			System.out.println("cost = " + cost);
			onlineOrderAmmount = (productListTarget.get(index).getProductCost())
					* onlineProductsQuantities[index];
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("no exception");
	}
*/

	public ProductORM getProductForConverter(Long productId) {
		System.out.println("getProductForConverter() called");
		ProductORM product = new ProductORM();
		try {
			product = productDao.productDetails(productId);
		} catch (ProductNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return product;
	}

	public List<Integer> listproductQuantities() {
		System.out.println("listproductQuantities() called");
		productQuantities = new ArrayList<Integer>();
		for (Integer i = 1; i <= 15; i++) {
			Integer v = new Integer(i);
			productQuantities.add(v);
		}
		return productQuantities;
	}

	public List<OrderDetailORM> orderDetailsByOrder() {
		System.out.println("order Id = " + orderId);
		OrderORM order = orderDao.retrieveOrder(orderId);
		//orderDetailList = orderDetailDao.orderDetailsByOrder(order);
		session.setOrderDetailList(orderDetailDao.orderDetailsByOrder(order));
		return session.getOrderDetailList();
	}

	

	public String quaintityCahnge(String action) {
		System.out.println("quaintityChange called..");
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		request.setAttribute("action", action);
		return actionListener();
	}

	public void saveProvisioning() {
		try {
			OrderORM order = new OrderORM();
			try {
				order = orderDao.retrieveOrder(orderId);
			} catch (Exception e) {

			}
			try {
				product = productDao.productDetails(getProductId());
			} catch (ProductNotFoundException e) {
				System.out.println(e.getMessage());
			}

			try {
				customer = contactDao.contactDetails(getCustomerId());
				System.out.println("customer id" + getCustomerId());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			CustomerProvisioningORM customerProvisioning = provisioningDao.getProvisioningByOrderProduct(order, product);
			customerProvisioning.setCustomer(customer);
			customerProvisioning.setOrder(order);
			customerProvisioning.setProduct(product);
			customerProvisioning.setProductMACAddress(MACAddress);
			customerProvisioning.setHost(host);
			customerProvisioning.setPort(port);
			customerProvisioning.setLoginName(loginName);
			customerProvisioning.setPassword(password);
			
			provisioningDao.saveProvisioning(customerProvisioning);
			
			addMessage(getProperty("message.order.provisioning.configure"));

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Deprecated
	public void loadOrderProvisioning() {
		try {
			Long orderId = session.getSalesModule_selectedOrder();
			OrderORM order = new OrderORM();
			if (orderId > 0) {
				try {
					order = orderDao.retrieveOrder(orderId);
				} catch (Exception e) {

				}
			}
			CustomerProvisioningORM customerProvisioning = provisioningDao
					.getProvisioningByOrderProduct(order, product);
			MACAddress = customerProvisioning.getProductMACAddress();
			host = customerProvisioning.getHost();
			port = customerProvisioning.getPort();
			loginName = customerProvisioning.getLoginName();
			password = customerProvisioning.getPassword();
		} catch (Exception e) {

		}
	}
	
	@Deprecated
	public void loadOrderProvisioning(Long orderId) {
		try {
			OrderORM order = new OrderORM();
			if (orderId > 0) {
				try {
					order = orderDao.retrieveOrder(orderId);
				} catch (Exception e) {

				}
			}
			CustomerProvisioningORM customerProvisioning = provisioningDao
					.getProvisioningByOrderProduct(order, product);
			MACAddress = customerProvisioning.getProductMACAddress();
			host = customerProvisioning.getHost();
			port = customerProvisioning.getPort();
			loginName = customerProvisioning.getLoginName();
			password = customerProvisioning.getPassword();
		} catch (Exception e) {

		}
	}

	public void loadProductProvisioning(Long orderId, Long productId) {
		try {
			OrderORM order = new OrderORM();
			ProductORM product = new ProductORM();
			order = orderDao.retrieveOrder(orderId);
			product = productDao.productDetails(productId);
			this.customerId = Long.valueOf(getParameter("customerId").toString());
			CustomerProvisioningORM provisioning = provisioningDao.getProvisioningByOrderProduct(order, product);
			this.productId = productId;
			this.product = productDao.productDetails(productId);
			this.MACAddress = provisioning.getProductMACAddress();
			this.host = provisioning.getHost();
			this.port =provisioning.getPort();
			this.loginName = provisioning.getLoginName();
			this.password = provisioning.getPassword();
		} catch (Exception e) {
			System.out.println("Exception in OrderBackingBean loadProductProvisioning(Long orderId, Long productId)");
            e.getStackTrace(); 			
		}
	}

	

	public void calculateDiscount() {
		BeanFactory beanFactory = BeanFactory.getInstance();
		LoyaltyBackingBean loyaltyBean = beanFactory.getLoyaltyBackingBean();
		if (customer != null) {
			customerId = customer.getContactId();
		}
		this.discount = loyaltyBean.getDiscount(customerId);
		disableFields = true;
		for(OrderDetailORM orderDetail : session.getOrderDetailList()){
			orderDetail.setProduct(new ProductORM());
			orderDetail.setQuantity(null);
			orderDetail.setProductTotalAmount(null);
		}
		
		clear();
	}

	// this code is to be optimized
/*	public void getProductCost() {
		try {
			if (productId != null && productId > 0) {
				product = productDao.productDetails(productId);
			}
		} catch (Exception e) {

		}
		if (product != null) {
			this.productPrice = product.getProductCost();
		}
		this.quantity = null;
		this.netAmount = null;
		this.totalOrderAmmount = null;

	}
*/
	
	public void changeProduct(int index){
		OrderDetailORM orderDetail = session.getOrderDetailList().get(index);
		productId = orderDetail.getProduct().getProductId();
		try {
			if (orderDetail.getProduct().getProductId() != null && orderDetail.getProduct().getProductId() > 0) {
				product = productDao.productDetails(productId);
			}
		} catch (Exception e) {

		}

		if(product != null){
			orderDetail.getProduct().setProductCost(product.getProductCost());
			orderDetail.setQuantity(null);
			orderDetail.setProductTotalAmount(null);
		}
		disableFields = true;
		this.quantity = null;
		this.netAmount = null;
		this.orderTotalAmount = null;
		this.productTotalAmount = null;
	}


	public void totalOrderAmount() {
		    this.orderTotalAmount = 0d;
			for(OrderDetailORM orderDetail : session.getOrderDetailList()){
				if(orderDetail.getProduct() != null){
					if(orderDetail.getQuantity() != null){
						double productTotalAmount = (double)orderDetail.getProduct().getProductCost() * orderDetail.getQuantity();
						orderDetail.setProductTotalAmount(productTotalAmount);
						this.orderTotalAmount = this.orderTotalAmount + productTotalAmount;
					}
				}
			}
			
			if (discount != null && discount > 0) {
				double discountAmount = discount / 100;
				discountAmount = orderTotalAmount * discountAmount;
				netAmount = orderTotalAmount - (long) discountAmount;
			} else {
				netAmount = orderTotalAmount;
			}
		
		disableFields = true;
	}


	/*
	public Long calculateTotalOrderAmmount() {

		Long totalOrderAmmount = 0l;
		for (OrderDetailORM order : orderProductList) {
			totalOrderAmmount = totalOrderAmmount + order.getOrderAmmount();
		}
		return totalOrderAmmount;
	}*/

	public Double calculateTotalOrderAmmount() {
		return orderTotalAmount;
	}

/*	public void orderNow() {
	//	System.out.println(">>>>>>>>>>>>>>>>>>>>Order Now called");
		reset();
		setCreateAction();
		BeanFactory beanFactory = BeanFactory.getInstance();
		this.productId = session.getCampaignProducts().get(0);
		System.out.println(">>>>>>>>>>>>>>>>>>>>Product" + productId);
		ProductORM product = new ProductORM();
		try {
			product = productDao.productDetails(productId);
		} catch (Exception e) {

		}
		this.productCost = (double)product.getProductCost();
		this.status = "Open";

		if (FacesContext.getCurrentInstance().getExternalContext()
				.isUserInRole("Customer")) {
			

			try {
				this.customer = contactDao.getContactByUserId(session
						.getUserId());
				this.customerId = customer.getContactId();
			} catch (Exception e) {

			}
			LoyaltyBackingBean loyaltyBean = beanFactory.getLoyaltyBackingBean();
			this.discount = loyaltyBean.getDiscount(customerId);

		}
		this.quantity = 1;
		this.orderTotalAmount = (double) product.getProductCost() * quantity;
		if (discount != null && discount > 0) {
			double discountAmount = discount / 100;
			discountAmount = orderTotalAmount * discountAmount;
			netAmount = orderTotalAmount - (long) discountAmount;
		} else {
			netAmount = orderTotalAmount;
		}
		session.getCampaignProducts().clear();
	}
*/
	public void orderNow() {
			reset();
			setCreateAction();
			BeanFactory beanFactory = BeanFactory.getInstance();
			session.setOrderDetailList(new ArrayList<OrderDetailORM>());
			for(int i = 0; i<session.getCampaignProducts().size(); i++){
				OrderDetailORM orderDetail = new OrderDetailORM();
				this.productId = session.getCampaignProducts().get(0);
				try {
					product = productDao.productDetails(productId);
					orderDetail.setProduct(product);
				    orderDetail.setQuantity(1);
				    orderDetail.setProductTotalAmount((double)product.getProductCost());
				    session.getOrderDetailList().add(orderDetail);
				} catch (Exception e) {

				}
			}
			this.status = "Open";
			if (FacesContext.getCurrentInstance().getExternalContext().isUserInRole("Customer")) {
				try {
					this.customer = contactDao.getContactByUserId(session.getUserId());
					this.customerId = customer.getContactId();
				} catch (Exception e) {

				}
				LoyaltyBackingBean loyaltyBean = beanFactory.getLoyaltyBackingBean();
				this.discount = loyaltyBean.getDiscount(customerId);
			}
			totalOrderAmount();
			fromOrderNow = true;
			session.getCampaignProducts().clear();
		}

	
	
	public void clear(){
		this.customerId = null;
		this.customer = null;
		this.productId = null;
		this.product = null;
		this.productCost = null;
		this.quantity = null;
		this.orderTotalAmount = null;
		this.netAmount = null;
		this.paidAmount = null;
		this.status = null;
	}
   
	public void addProduct(){
		OrderDetailORM lastOrderDetail = session.getOrderDetailList().get(session.getOrderDetailList().size() - 1);
		if(session.getOrderDetailList().size() > 9){
			addWarning("Order can not have more than 10 products");
			return;
		}
		OrderDetailORM orderDetail = new OrderDetailORM();
		orderDetail.setOrder(lastOrderDetail.getOrder());
		orderDetail.setProduct(new ProductORM());
		orderDetail.setTempId(lastOrderDetail.getTempId() + 1);
		session.getOrderDetailList().add(orderDetail);
	} 
	public void removeProduct(Long detailId){
		System.out.println("removeProduct called , index = "+detailId);
		if(session.getOrderDetailList().size() < 2){
			addWarning("Order must have atleast one product");
			return;
		}
		Iterator<OrderDetailORM> iter = session.getOrderDetailList().iterator();
		while(iter.hasNext()){
			OrderDetailORM orderDetail = iter.next();
			if(orderDetail.getTempId() == detailId){
				iter.remove();
				break;
			}
		}
		this.totalOrderAmount();
		session.setSalesModule_orderTabIndex(0);
	}
	// =============

	public String actionListener() {
		System.out.println("OrderBackingBean actionListener() called..");
		reset();
		setCurrentAction(getParameter("action"), this.getClass());
		System.out.println("getCurrentAction() = " + getCurrentAction());
		BeanFactory beanFactory = BeanFactory.getInstance();
		
		switch (getCurrentAction()) {
		case WebConstants.ACTION_CREATE:
			if(fromOrderNow){
				// products of campaign already populated 
			}else{
			  clear();
			  if (FacesContext.getCurrentInstance().getExternalContext().isUserInRole("Customer")) {
					try {
						this.customer = contactDao.getContactByUserId(session.getUserId());
						this.customerId = customer.getContactId();
						LoyaltyBackingBean loyaltyBean = beanFactory.getLoyaltyBackingBean();
						this.discount = loyaltyBean.getDiscount(customerId);
					} catch (Exception e) {

					}
			  }
			  session.setOrderDetailList(new ArrayList<OrderDetailORM>());
			  OrderDetailORM orderDetail = new OrderDetailORM();
			  orderDetail.setProduct(new ProductORM());
			  orderDetail.setTempId(1l);
			  session.getOrderDetailList().add(orderDetail);
			}
			setCreateAction();
		    setCancelAction(false);
		    session.setSalesModule_orderTabIndex(0);
		    return null;
		case "addProduct":
			addProduct();
			this.totalOrderAmount();
			reset();
			setEditAction();
		    session.setSalesModule_orderTabIndex(0);
		    return null;
		case "removeProduct":
			Long detailId = Long.valueOf(getParameter("detailId").toString());
			removeProduct(detailId);
			reset();
			setEditAction();
		    session.setSalesModule_orderTabIndex(0);
		    return null;
		case WebConstants.ACTION_SAVE:
			try{
				  Long orderId = createOrder();
				  if(orderId > 0){
					  loadOrder(orderId);
					  reset();
					  setViewAction();
					  setCancelAction(false);
					  session.setSalesModule_orderTabIndex(0);
					  session.setSalesModule_selectedOrder(orderId);
					  return null;   
				  }else{
					  setCreateAction();
				      setCancelAction(false);
				      session.setSalesModule_orderTabIndex(0);
				      return null;
				  }
				  
			      //return WebConstants.ACTION_LIST;
				}catch(Exception e){
					addError(getProperty("message.order.error.create"));
					setCreateAction();
				    setCancelAction(false);
				    return null;
				}
		case WebConstants.ACTION_VIEW:
			try {
				Long orderId = session.getSalesModule_selectedOrder();
				loadOrder(orderId);
				//loadOrderProvisioning(orderId);
				setViewAction();
				setCancelAction(false);
				session.setSalesModule_orderTabIndex(0);
				return null;

			} catch (Exception e) {
				// handle exception
				addError(getProperty("message.order.error.load"));
				System.out.println("Exception Occured OrderBackingBean actionListener()");
				setSaveAction(); // in case of any failure
				return WebConstants.ACTION_LIST;
			}
		case "next":
			try {
				//listSelectedProducts();
				Long orderId = session.getSalesModule_selectedOrder();
				loadOrder(orderId);
				setEditAction();
				return WebConstants.ACTION_CRUD;
			} catch (Exception e) {
				addError(getProperty("message.order.error.load"));
				System.out
						.println("Exception Occured OrderBackingBean actionListener()");
				setSaveAction(); // in case of any failure
				return WebConstants.ACTION_LIST;
			}

		case "changeQuantity":
			try {
				Long orderId = session.getSalesModule_selectedOrder();
				//calculateAmmount(1);
				loadOrder(orderId);
				setEditAction();
				return WebConstants.ACTION_CRUD;
			} catch (Exception e) {
				addError(getProperty("message.order.error.load"));
				System.out
						.println("Exception Occured OrderBackingBean actionListener()");
				setSaveAction(); // in case of any failure
				return WebConstants.ACTION_LIST;
			}
		case WebConstants.ACTION_CANCEL:
			try{
				Long orderId = session.getSalesModule_selectedOrder();
				loadOrder(orderId);
				//loadOrderProvisioning(orderId);
				session.setSalesModule_orderTabIndex(0);
				setViewAction();
				setCancelAction(false);
				return null;
			}catch(Exception e){
					return null;
			}
		case WebConstants.ACTION_EDIT:
			try{
				setEditAction();
				Long orderId = session.getSalesModule_selectedOrder();
				loadOrder(orderId);
				session.setSalesModule_orderTabIndex(0);
				return null;
			}
			catch (Exception e) {
				addError(getProperty("message.order.error.load"));
				System.out.println("Exception Occured OrderBackingBean actionListener()");
				setSaveAction(); // in case of any failure
				session.setSalesModule_orderTabIndex(0);
				return null;
			}

		case WebConstants.ACTION_UPDATE:
			try{
				//Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
				Long orderId = session.getSalesModule_selectedOrder();
				editOrder(orderId);
				loadOrder(orderId);
				//loadOrderProvisioning(orderId);
				reset();
				setViewAction();
				setCancelAction(false);
				session.setSalesModule_orderTabIndex(0);
				return null;
			}
			catch (Exception e) {
				addError(getProperty("message.order.error.load"));
				System.out.println("Exception Occured OrderBackingBean actionListener()");
				setSaveAction(); // in case of any failure in updating
				session.setSalesModule_orderTabIndex(0);
				return null;
			}
			
		case WebConstants.ACTION_DELETE:
			try{
				Long orderId = session.getSalesModule_selectedOrder();
				loadOrder(orderId);
				//loadOrderProvisioning(orderId);
				setDeleteConfirmedAction();
				session.setSalesModule_orderTabIndex(0);
				return null;
			}
			catch (Exception e) {
				addError(getProperty("message.order.error.load"));
				System.out.println("Exception Occured OrderBackingBean actionListener()");
				setSaveAction(); // in case of failure in loading order that is to be deleted
				session.setSalesModule_orderTabIndex(0);
				return null;
			}
		case WebConstants.ACTION_DELETE_CONFIRMED:
			try{
				Long orderId = session.getSalesModule_selectedOrder();
				deleteOrder(orderId);
				setDeleteConfirmedAction();
				setDisabled(false);
				session.setSalesModule_orderTabIndex(0);
				session.setSalesModule_selectedOrder(null);
				return null;
			}
			catch (Exception e) {
				System.out.println("Exception Occured OrderBackingBean actionListener()");
				setSaveAction(); // in case of failure in delete
				session.setSalesModule_orderTabIndex(0);
				return null;
			}
		case "saveProvisioning":
			try {
				saveProvisioning();
				setViewAction();
				return null;
			} catch (Exception e) {
				Long orderId = session.getSalesModule_selectedOrder();
				loadOrder(orderId);
				setViewAction();
				setCancelAction(false);
				session.setSalesModule_orderTabIndex(0);
				return null;

			}
		case "viewProvisioning":
			try {
				this.productId = Long.valueOf(getParameter("productId").toString());
				orderId = session.getSalesModule_selectedOrder();
				loadProductProvisioning(orderId, productId);
				setViewAction();
				return null;
				
			} catch (Exception e) {
              System.out.println("Exception in OrderBackingBean actionListener viewProvisioning");
              Long orderId = session.getSalesModule_selectedOrder();
				loadOrder(orderId);
				setViewAction();
				setCancelAction(false);
				session.setSalesModule_orderTabIndex(0);
				return null;
			}
		}
		return (null);

	} // end actionListener()

	public String billActionListener() {
		setCurrentAction(getParameter("action"), this.getClass());
		System.out.println("Bill ReImbursement actionListner called");
		System.out.println("getAction = " + getAction());
		Long orderId = session.getSalesModule_selectedOrder();
		switch (getCurrentAction()) {
		case WebConstants.ACTION_VIEW:
			// TODO
			loadOrder(orderId);
			return WebConstants.ACTION_CRUD;
		case WebConstants.ACTION_REIMBURSE:
			// TODO
			loadOrder(orderId);
			return WebConstants.ACTION_CRUD;
		case WebConstants.ACTION_UPDATE:
			addMessage(" Updated Successfully ");
			return WebConstants.ACTION_LIST;
		}
		return (null);

	} // end actionListner

	public String getDateString() {
		String S = new SimpleDateFormat("dd/MM/yyyy").format(orderDate);
		return S;
	}
	
	public void validateAccess(){
		try{
			SecurityBackingBean security = BeanFactory.getInstance().getSecurityBackingBean();
		    if(security.getOrder().isRead()){
			  // do nothing
		    }else{
			  // logout from application 
		      //FacesContext.getCurrentInstance().getExternalContext().redirect("/crm/view/dashboard/index.jsf?validate='invalid'");
		      //BeanFactory.getInstance().getIndexBackingBean().logOut();
		    }
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
}
