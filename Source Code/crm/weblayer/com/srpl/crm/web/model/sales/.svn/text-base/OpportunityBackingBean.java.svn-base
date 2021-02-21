package com.srpl.crm.web.model.sales;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.bitguiders.util.jsf.JSFBeanInterface;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;
import com.srpl.crm.ejb.entity.CsContactORM;
import com.srpl.crm.ejb.entity.OrderDetailORM;
import com.srpl.crm.ejb.entity.OrderORM;
import com.srpl.crm.ejb.entity.ProductORM;
import com.srpl.crm.ejb.entity.SalesOpportunitiesListViewORM;
import com.srpl.crm.ejb.entity.SalesOpportunityORM;
import com.srpl.crm.ejb.exceptions.ContactNotFoundException;
import com.srpl.crm.ejb.exceptions.OpportunityNotFoundException;
import com.srpl.crm.ejb.exceptions.ProductNotFoundException;
import com.srpl.crm.ejb.request.ContactDAO;
import com.srpl.crm.ejb.request.InvoiceDAO;
import com.srpl.crm.ejb.request.OpportunityDAO;
import com.srpl.crm.ejb.request.OpportunityListViewDAO;
import com.srpl.crm.ejb.request.OrderDAO;
import com.srpl.crm.ejb.request.OrderDetailDAO;
import com.srpl.crm.ejb.request.ProductDAO;
import com.srpl.crm.web.common.InnerTabs;
import com.srpl.crm.web.common.SessionDataBean;
import com.srpl.crm.web.controller.BeanFactory;
import com.srpl.crm.web.model.common.ColumnModel;
import com.srpl.um.ejb.entity.UmCompany;
import com.srpl.um.ejb.entity.UmUser;
import com.srpl.um.ejb.exceptions.UserNotFoundException;
import com.srpl.um.ejb.request.CompanyDAO;
import com.srpl.um.ejb.request.UserDAO;

@ManagedBean(name = "opportunityBean")
@RequestScoped
public class OpportunityBackingBean extends JSFBeanSupport implements
		JSFBeanInterface, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long opportunityId;
	private String opportunityTitle = "";
	private String status = "";
	private Integer opportunityExpectedRevenue;
	private Integer percentOfSuccess;
	private String comments;
	// private List<UmUser> userList; // Assigne opportunity to user
	private Long customerId;
	private Long assignedTo;
	private UmUser umUser;
	private CsContactORM customer;
	private Long productId;
	private ProductORM product;
	private UmCompany company;
	private Double orderAmmount;
	private Integer quantity;
	private List<ColumnModel> columns;
	private List<UmUser> usersList;
	private List<CsContactORM> contactsList;
	private List<ProductORM> productList;
	private Double paidAmount;
	private SessionDataBean session;

	public void resetBean(){
		setOpportunityId(0L);
		setOpportunityTitle("");
		setStatus("");
		setOpportunityExpectedRevenue(0);
		setPercentOfSuccess(0);
		setComments("");
		setCustomerId(0L);
		setAssignedTo(0L);
		setUmUser(null);
		setCustomer(null);
		setProductId(0L);
		setProduct(null);
		setCompany(null);
		setOrderAmmount(0D);
		setQuantity(0);
		setPaidAmount(0D);
	}

	@EJB
	OpportunityDAO opportunityDao;
	@EJB
	UserDAO userDao;
	@EJB
	ContactDAO contactDao;
	@EJB
	ProductDAO productDao;
	@EJB
	OrderDAO orderDao;
	@EJB
	OrderDetailDAO orderDetaiDao;
	@EJB
	CompanyDAO companyDao;
	@EJB
	OpportunityListViewDAO opportunityListViewDao;
	@EJB
	InvoiceDAO invoiceDao;

	public OpportunityBackingBean() {
		session = BeanFactory.getInstance().getSessionBean();
	}
	
	@PostConstruct
	public void init(){
		String act = getAction();
		if(act.equals("")){
			if(session.getOpportunityModule_selectedOpportunity() != 0L){
				opportunityDetails();
				reset();
				setViewAction();
			}else
				session.resetOpportunityModule();
		}
	}
	
	public void opportunityDetails(){		
		loadOpportunity(session.getOpportunityModule_selectedOpportunity());
		changeTabPath(0, "/view/sales/opportunities/opportunityForm.xhtml");
		setViewAction();
	}

	public Long getOpportunityId() {
		return opportunityId;
	}

	public void setOpportunityId(Long opportunityId) {
		this.opportunityId = opportunityId;
	}

	public String getOpportunityTitle() {
		return opportunityTitle;
	}

	public void setOpportunityTitle(String opportunityTitle) {
		this.opportunityTitle = opportunityTitle;
	}

	public String getStatus() {
		System.out.println("get status = " + status);
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getOpportunityExpectedRevenue() {
		return opportunityExpectedRevenue;
	}

	public void setOpportunityExpectedRevenue(Integer opportunityExpectedRevenue) {
		this.opportunityExpectedRevenue = opportunityExpectedRevenue;
	}

	public Integer getPercentOfSuccess() {
		return percentOfSuccess;
	}

	public void setPercentOfSuccess(Integer percentOfSuccess) {
		this.percentOfSuccess = percentOfSuccess;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Long getCustomerId() {
		System.out.println("get customer id = " + customerId);
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(Long assignedTo) {
		this.assignedTo = assignedTo;
	}

	public UmUser getUmUser() {
		return umUser;
	}

	public void setUmUser(UmUser umUser) {
		this.umUser = umUser;
	}

	public CsContactORM getCustomer() {
		System.out.println("getCustomer = " + customer.getContactFname());
		return customer;
	}

	public void setCustomer(CsContactORM customer) {
		this.customer = customer;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Double getOrderAmmount() {
		return orderAmmount;
	}

	public void setOrderAmmount(Double orderAmmount) {
		this.orderAmmount = orderAmmount;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public ProductORM getProduct() {
		return product;
	}

	public void setProduct(ProductORM product) {
		this.product = product;
	}

	public List<ColumnModel> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnModel> columns) {
		this.columns = columns;
	}

	public List<UmUser> getUsersList() {
		return usersList;
	}

	public void setUsersList(List<UmUser> usersList) {
		this.usersList = usersList;
	}

	public List<CsContactORM> getContactsList() {
		return contactsList;
	}

	public void setContactsList(List<CsContactORM> contactsList) {
		this.contactsList = contactsList;
	}

	public List<ProductORM> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductORM> productList) {
		this.productList = productList;
	}

	public UmCompany getCompany() {
		return company;
	}

	public void setCompany(UmCompany company) {
		this.company = company;
	}

	private void changeTabPath(int index, String path) {
		InnerTabs d = session.getUserTabs().get(index);
		d.setPath(path);
		session.getOpportunityTabs().set(index, d);
		try {
			if (getParameter("fromListing").equals("fromListing")) {
				// don't update index
			} else {
				session.setOpportunityModule_opportunityTabIndex(0);
			}
		} catch (Exception e) {
			session.setOpportunityModule_opportunityTabIndex(0);
		}
	}

	public void loadOpportunity(long id) {
		try {
			OpportunityBackingBean bean= this;
			bean.resetBean();
			SalesOpportunityORM opportunity = opportunityDao.details(id);
			this.opportunityId = opportunity.getOpportunityId();
			this.product = opportunity.getProduct();
			this.status = opportunity.getStatus();
			this.productId = opportunity.getProduct().getProductId();
			this.opportunityExpectedRevenue = opportunity.getOpportunityExpectedRevenue();
			this.percentOfSuccess = opportunity.getPercentOfSuccess();
			this.comments = opportunity.getComments();
			this.umUser = opportunity.getUmUser();
			this.customer = opportunity.getCsContact();
			this.customerId = opportunity.getCsContact().getContactId();
			this.assignedTo = opportunity.getUmUser().getUserId();
			this.company = opportunity.getCompany();

		} catch (OpportunityNotFoundException e) {
			/*changeTabPath(0,
					"/view/sales/opportunities/opportunityNoSelection.xhtml");
			System.out.println("exception while loading opportunity.");*/
			e.printStackTrace();
		}
	}

	@Override
	public List<SalesOpportunitiesListViewORM> getList() {
		// TODO Auto-generated method stub
		List<SalesOpportunitiesListViewORM> opportunityList = null;
		try {
			opportunityList = opportunityListViewDao.list(session.getCompanyId());
		} catch (Exception e) {
			/*opportunityList = new ArrayList<SalesOpportunitiesListViewORM>();
			opportunityList.add(new SalesOpportunitiesListViewORM(null, 0, "No Opportunity Found."));*/
		}
		return opportunityList;

	}

	/*
	 * public List<CreateUserBean> listUsers(){ UsersListingBackingBean l =
	 * BeanFactory.getInstance().getUsersListingBackingBean(); return
	 * l.getUsers(); }
	 */

	public List<UmUser> listUsers() {
		System.out.println("list users called");
		usersList = userDao.listUsersByCompany(session.getCompanyId());
		return usersList;
	}

	public List<ProductORM> listProducts() {
		UmCompany company = companyDao.companyDetails(session.getCompanyId());
		productList = productDao.listProducts(company);
		return productList;
	}

	public List<CsContactORM> listContacts() {
		System.out.println("list contacts called");
		try {
			BeanFactory beanFactory = BeanFactory.getInstance();
			SessionDataBean session = beanFactory.getSessionBean();
			contactsList = contactDao.listContacts(session.getCompanyId());
			System.out.println("contact name = "
					+ contactsList.get(0).getContactFname());
			System.out.println("contact id = "
					+ contactsList.get(0).getContactId());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return contactsList;
	}

	public void createOpportunity() throws Exception {
		try {
			product = productDao.productDetails(getProductId());
		} catch (ProductNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try{
			customer = contactDao.contactDetails(getCustomerId());
		}catch(Exception e){
			e.printStackTrace();
		}
		try {
			umUser = userDao.umUserDetails(getAssignedTo());
		} catch (UserNotFoundException e) {
			System.out.println(e.getMessage());
		}
		company = companyDao.companyDetails(session.getCompanyId());
		
		SalesOpportunityORM opportunity = new SalesOpportunityORM(product, getStatus(), getOpportunityExpectedRevenue(), getPercentOfSuccess(), getComments(), umUser, customer, company);
		opportunityId = opportunityDao.create(opportunity);
		
		if (getStatus().equalsIgnoreCase("Won")) {
			Long orderId = 0L;
			orderAmmount = (double)product.getProductCost();
			Date orderDate = new Date();
			System.out.println("date = " + orderDate);
			OrderORM order = new OrderORM(opportunity, customer, new Timestamp(orderDate.getTime()), umUser, orderAmmount, 0D, orderAmmount, orderAmmount, new Timestamp(orderDate.getTime()), umUser, "Pending","Manual", company);
			orderId = orderDao.createOrder(order);
			System.out.println(orderId);
			OrderORM newOrder = orderDao.retrieveOrder(orderId);				
			OrderDetailORM orderDetail = new OrderDetailORM(newOrder, product, 1, orderAmmount);
			orderDetaiDao.createOrderDetail(orderDetail);
			invoiceDao.create(orderId);	
		}
		session.setOpportunityModule_selectedOpportunity(opportunityId);
		addMessage(getProperty("message.sales.opportunity.created"));
	}

	public void editOpportunity(Long opportunityId) throws Exception {
		System.out.println("Edit opportunity Called, id = " + opportunityId);
		SalesOpportunityORM opportunity = null;
		try{
			opportunity = opportunityDao.details(session.getOpportunityModule_selectedOpportunity());
		}catch(Exception e){
			e.printStackTrace();
		}
		try {
			product = productDao.productDetails(getProductId());
		} catch (ProductNotFoundException e) {
			System.out.println(e.getMessage());
		}
		opportunity.setProduct(product);
		opportunity.setOpportunityExpectedRevenue(getOpportunityExpectedRevenue());
		try{
			customer = contactDao.contactDetails(getCustomerId());
			opportunity.setCsContact(customer);
		}catch(Exception e){
			e.printStackTrace();
		}
		opportunity.setPercentOfSuccess(getPercentOfSuccess());
		try {
			umUser = userDao.umUserDetails(getAssignedTo());
			opportunity.setUmUser(umUser);
		} catch (UserNotFoundException e) {
			System.out.println(e.getMessage());
		}
		opportunity.setStatus(getStatus());
		opportunity.setComments(getComments());
		company = companyDao.companyDetails(session.getCompanyId());
		if (getStatus().equalsIgnoreCase("Won")) {
				Long orderId = 0L;
				orderAmmount = (double)product.getProductCost();
				Date orderDate = new Date();
				System.out.println("date = " + orderDate);
				OrderORM order = new OrderORM(opportunity, customer, new Timestamp(orderDate.getTime()), umUser, orderAmmount, 0D, orderAmmount, orderAmmount, new Timestamp(orderDate.getTime()), umUser, "Pending","Manual", company);
				orderId = orderDao.createOrder(order);
				System.out.println(orderId);
				OrderORM newOrder = orderDao.retrieveOrder(orderId);				
				OrderDetailORM orderDetail = new OrderDetailORM(newOrder, product, 1, orderAmmount);
				orderDetaiDao.createOrderDetail(orderDetail);
				invoiceDao.create(orderId);	
		}
		opportunityDao.update(opportunity);
		addMessage(getProperty("message.sales.opportunity.updated"));

	}

	public void deleteOpportunity() {
		System.out.println("OpportunityBackingBean deleteOpportunity() called");
		SalesOpportunityORM opportunity = new SalesOpportunityORM();
		try {
			opportunity = opportunityDao.details(opportunityId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// delete order detail against this opportunity's order
		// get order for this opportunity then delete order details against this
		// order
		OrderORM order = orderDao.retrieveOrderOfOpportunity(opportunity);
		orderDetaiDao.deleteOrderDetail(order);
		// delete order against this opportunity
		orderDao.deleteOrderOpportunity(opportunity);
		opportunityDao.delete(opportunity.getOpportunityId());
		// comDAO.deleteCompany(search.getCompanyId());
		addMessage(getProperty("message.sales.opportunity.deleted"));
	}

	public Double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}

	@Override
	public void setViewAction() {
		super.setViewAction();
		setCancelAction(false);
		setResetAction(false);
	}
	
	public String actionListener() {		
		Long opportunity_id;
		setCurrentAction(getAction(), this.getClass());
		switch (getCurrentAction()) {
		case WebConstants.ACTION_CREATE:
			resetBean();
			session.setOpportunityModule_selectedOpportunity(0L);
			changeTabPath(0, "/view/sales/opportunities/opportunityForm.xhtml");
			break;
		case WebConstants.ACTION_SAVE:
			try {
				createOpportunity();
				reset();
				opportunityDetails();
			} catch (Exception e) {
				addError(getProperty("message.sales.opportunity.creation.failed"));
			}
			break;
		case WebConstants.ACTION_VIEW:
			opportunityDetails();
			break;
		case WebConstants.ACTION_CANCEL:
			opportunity_id = Long.parseLong(getParameter("opportunity_id").toString());
			if(opportunity_id != 0L){
				session.setOpportunityModule_selectedOpportunity(opportunity_id);
				opportunityDetails();
			}else{
				session.resetOpportunityModule();
			}
			break;
		case WebConstants.ACTION_EDIT:
			opportunity_id = Long.parseLong(getParameter("opportunity_id").toString());
			opportunityDetails();
			reset();
			setEditAction();
			break;

		case WebConstants.ACTION_UPDATE:			
			try {
				editOpportunity(session.getOpportunityModule_selectedOpportunity());
				reset();
				opportunityDetails();
			} catch (Exception e) {
				addError(getProperty("message.sales.opportunity.update.fail"));
			}
			break;
		case WebConstants.ACTION_DELETE:
			opportunity_id = Long.parseLong(getParameter("opportunity_id").toString());
			loadOpportunity(opportunity_id);
			changeTabPath(0, "/view/sales/opportunities/opportunityForm.xhtml");
			reset();
			setDeleteAction();
			break;

		case WebConstants.ACTION_DELETE_CONFIRMED:
			deleteOpportunity();
			session.resetOpportunityModule();
			break;
		}

		return (null);

	}

}
