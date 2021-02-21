package com.srpl.crm.web.model.support;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.ToggleEvent;

import com.bitguiders.util.jsf.JSFBeanInterface;
import com.bitguiders.util.jsf.WebConstants;
import com.srpl.crm.web.controller.BeanFactory;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.srpl.crm.ejb.entity.CsContactORM;
import com.srpl.crm.ejb.entity.ProductORM;
import com.srpl.crm.ejb.entity.SupportCaseCommentORM;
import com.srpl.crm.ejb.entity.SupportCaseHistoryORM;
import com.srpl.crm.ejb.entity.SupportCaseORM;
import com.srpl.crm.ejb.entity.SupportQueryTypeORM;
import com.srpl.crm.ejb.entity.UmAlertsAndReminders;
import com.srpl.um.ejb.entity.UmUser;
import com.srpl.crm.ejb.exceptions.CaseNotFoundException;
import com.srpl.crm.ejb.exceptions.ContactNotFoundException;
import com.srpl.crm.ejb.exceptions.ProductNotFoundException;
import com.srpl.crm.ejb.exceptions.QueryTypeNotFoundException;
import com.srpl.um.ejb.exceptions.UserNotFoundException;
import com.srpl.crm.ejb.request.AlertsAndRemindersDAO;
import com.srpl.crm.ejb.request.CaseCommentDAO;
import com.srpl.crm.ejb.request.CaseDAO;
import com.srpl.crm.ejb.request.CaseHistoryDAO;
import com.srpl.crm.ejb.request.ContactDAO;
import com.srpl.crm.ejb.request.ProductDAO;
import com.srpl.crm.ejb.request.QueryTypeDAO;
import com.srpl.crm.web.common.SessionDataBean;
import com.srpl.crm.web.model.common.ColumnModel;
import com.srpl.um.ejb.request.UserDAO;

@ManagedBean(name = "caseEscalationBean")
public class CaseEscalationBackingBean extends JSFBeanSupport implements
		JSFBeanInterface, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long caseId;
	private String caseTokenNumber;
	private Long customerId;
	private Long productId;
	private Long userId;
	private Long queryTypeId;
	private CsContactORM customer;
	private ProductORM product;
	private UmUser umUser;
	private SupportQueryTypeORM queryType;
	private String caseType;
	private String status;
	private String mobileNumber;
	private String customerQuery;
	private String comments;
	private String userComments;
	private String assignedcomments;
	private Timestamp caseCreatDate;
	private Timestamp caseAssignedDate;
	private Timestamp caseResolvedDate;
	private List<SupportCaseORM> caseList;
	private List<SupportCaseORM> customerCaseList;
	List<SupportCaseHistoryORM> caseHistoryList;
	List<SupportCaseCommentORM> caseCommentList;
	private List<ColumnModel> columns;
	private List<CsContactORM> customerList;
	private List<ProductORM> productList;
	private List<UmUser> usersList;
	private List<SupportQueryTypeORM> queryTypeList;
	private Boolean renderSupport = true;

	@EJB
	CaseDAO caseDao;
	@EJB
	CaseHistoryDAO caseHistoryDao;
	@EJB
	CaseCommentDAO caseCommentDao;
	@EJB
	ContactDAO contactDao;
	@EJB
	UserDAO userDao;
	@EJB
	ProductDAO productDao;
	@EJB
	QueryTypeDAO queryTypeDao;
	@EJB
	AlertsAndRemindersDAO alertsAndRemindersDAO;;

	public CaseEscalationBackingBean() {
		columns = new ArrayList<ColumnModel>();
		columns.add(new ColumnModel("caseTokenNumber", "Token NO"));
		columns.add(new ColumnModel("productName", "PRODUCT"));
		columns.add(new ColumnModel("caseType", "TYPE"));
		columns.add(new ColumnModel("userName", "ASSIGNED TO"));
		columns.add(new ColumnModel("status", "STATUS"));
		setCurrentAction(WebConstants.ACTION_SECURITY,this.getClass());
	}

	public Long getCaseId() {
		return caseId;
	}

	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}

	public String getCaseTokenNumber() {
		return caseTokenNumber;
	}

	public void setCaseTokenNumber(String caseTokenNumber) {
		this.caseTokenNumber = caseTokenNumber;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getQueryTypeId() {
		return queryTypeId;
	}

	public void setQueryTypeId(Long queryTypeId) {
		this.queryTypeId = queryTypeId;
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

	public UmUser getUmUser() {
		return umUser;
	}

	public void setUmUser(UmUser umUser) {
		this.umUser = umUser;
	}

	public SupportQueryTypeORM getQueryType() {
		return queryType;
	}

	public void setQueryType(SupportQueryTypeORM queryType) {
		this.queryType = queryType;
	}

	public String getCaseType() {
		return caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getCustomerQuery() {
		return customerQuery;
	}

	public void setCustomerQuery(String customerQuery) {
		this.customerQuery = customerQuery;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getAssignedcomments() {
		return null;
	}

	public void setAssignedcomments(String assignedcomments) {
		this.assignedcomments = assignedcomments;
	}

	public Timestamp getCaseCreatDate() {
		return caseCreatDate;
	}

	public void setCaseCreatDate(Timestamp caseCreatDate) {
		this.caseCreatDate = caseCreatDate;
	}

	public Timestamp getCaseAssignedDate() {
		return caseAssignedDate;
	}

	public void setCaseAssignedDate(Timestamp caseAssignedDate) {
		this.caseAssignedDate = caseAssignedDate;
	}

	public Timestamp getCaseResolvedDate() {
		return caseResolvedDate;
	}

	public void setCaseResolvedDate(Timestamp caseResolvedDate) {
		this.caseResolvedDate = caseResolvedDate;
	}

	public List<SupportCaseORM> getCaseList() {
		return caseList;
	}

	public List<SupportCaseORM> getCustomerCaseList() {
		return customerCaseList;
	}

	public void setCustomerCaseList(List<SupportCaseORM> customerCaseList) {
		this.customerCaseList = customerCaseList;
	}

	public void setCaseList(List<SupportCaseORM> caseList) {
		this.caseList = caseList;
	}

	public List<SupportCaseHistoryORM> getCaseHistoryList() {
		return caseHistoryList;
	}

	public void setCaseHistoryList(List<SupportCaseHistoryORM> caseHistoryList) {
		this.caseHistoryList = caseHistoryList;
	}

	public List<SupportCaseCommentORM> getCaseCommentList() {
		return caseCommentList;
	}

	public void setCaseCommentList(List<SupportCaseCommentORM> caseCommentList) {
		this.caseCommentList = caseCommentList;
	}

	public String getUserComments() {
		return userComments;
	}

	public void setUserComments(String userComments) {
		this.userComments = userComments;
	}

	public List<ColumnModel> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnModel> columns) {
		this.columns = columns;
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

	public List<UmUser> getUsersList() {
		return usersList;
	}

	public void setUsersList(List<UmUser> usersList) {
		this.usersList = usersList;
	}

	public List<SupportQueryTypeORM> getQueryTypeList() {
		return queryTypeList;
	}

	public void setQueryTypeList(List<SupportQueryTypeORM> queryTypeList) {
		this.queryTypeList = queryTypeList;
	}

	public Boolean getRenderSupport() {
		return renderSupport;
	}

	public void setRenderSupport(Boolean renderSupport) {
		this.renderSupport = renderSupport;
	}
	
	/*----------------------------------------------------*/

	public List<SupportCaseORM> getList() {
		try {
			caseList = caseDao.listEscalatedCases();
		} catch (CaseNotFoundException e) {
			this.addError(e.getMessage());
		}
		return caseList;
	}

	public void loadCase(Long id) {
			try {

				SupportCaseORM caseView = caseDao.caseDetails(id);

				this.caseId = caseView.getCaseId();
				this.caseTokenNumber = caseView.getCaseTokenNumber();
				this.customer = caseView.getcustomer();
				this.customerId = caseView.getcustomer().getContactId();
				this.product = caseView.getProduct();
				this.productId = caseView.getProduct().getProductId();
				this.queryType = caseView.getQueryType();
				this.queryTypeId = caseView.getQueryType().getQueryTypeId();
				this.umUser = caseView.getUmUser();
				if (umUser != null) {
					this.userId = caseView.getUmUser().getUserId();
				}
				this.caseType = caseView.getCaseType();
				this.status = caseView.getStatus();
				this.mobileNumber = caseView.getMobileNumber();
				this.customerQuery = caseView.getCustomerQuery();
				this.comments = caseView.getComments();
				this.assignedcomments = caseView.getComments();
				this.caseCreatDate = caseView.getCaseCreateDate();
				this.caseAssignedDate = caseView.getCaseAssignedDate();
				this.caseResolvedDate = caseView.getCaseResolvedDate();

			} catch (CaseNotFoundException e) {
				System.out.println("exception while loading case.");
				e.printStackTrace();
			}
	}

	public List<UmUser> listUsersByCompany() {
		BeanFactory beanFactory = BeanFactory.getInstance();
		SessionDataBean session = beanFactory.getSessionBean();
		usersList = userDao.listUsersByCompany(session.getCompanyId());
		return usersList;
	}

	public List<SupportQueryTypeORM> listQueryTypes() {
		try {
			queryTypeList = queryTypeDao.listQueryTypes();
		} catch (QueryTypeNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return queryTypeList;
	}

	public List<CsContactORM> listCustomers() {
		try {
			BeanFactory beanFactory = BeanFactory.getInstance();
			SessionDataBean session = beanFactory.getSessionBean();
			customerList = contactDao.listContacts(session.getCompanyId());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return customerList;
	}

	public List<ProductORM> listProducts() {
		try {
			productList = productDao.listProducts();
		} catch (ProductNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return productList;
	}

	public String assignCase() {
		try {
			customer = contactDao.contactDetails(getCustomerId());
		} catch (ContactNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			umUser = userDao.umUserDetails(getUserId());
		} catch (UserNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			product = productDao.productDetails(getProductId());
		} catch (ProductNotFoundException e) {
			System.out.println(e.getMessage());
		}
		Date date = new Date();
		SupportCaseORM newCase = new SupportCaseORM(getCaseId(),
				getCaseTokenNumber(), customer, umUser, product, getCaseType(),
				queryType, "Pending", getMobileNumber(), getCustomerQuery(),
				getAssignedcomments(), new Timestamp(date.getTime()),
				new Timestamp(date.getTime()), getCaseResolvedDate());
		caseDao.updateCase(newCase);
		return "escalations";
	}

	public void openCaseHistory(ToggleEvent event) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Case History", "Visibility:" + event.getVisibility());

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void createCaseAlert(UmUser user) {
		System.out.println("createCaseAlert() called..");
		Date date = new Date();
		UmAlertsAndReminders umAlertsAndReminders = new UmAlertsAndReminders(
				"Case Alert", new Timestamp(date.getTime()), user.getUserId(),
				true, false, null, false);
		alertsAndRemindersDAO.createAlertsAndReminders(umAlertsAndReminders);

	}

	public String actionListener() {
		reset();
		setCurrentAction(getParameter("action"),this.getClass());
		Long id;
		switch (getCurrentAction()) {
		case WebConstants.ACTION_CANCEL:
			setSaveAction();
			return WebConstants.ACTION_LIST;

		case WebConstants.ACTION_ASSIGN:
			// TODO
			id = Long.valueOf(getParameter("row_id").toString());
			loadCase(id);
			return WebConstants.ACTION_ASSIGN;
			
		case WebConstants.ACTION_ASSIGN_CONFIRMED:
			this.assignCase();
			return WebConstants.ACTION_LIST;
		}

		return (null);

	} // end actionListener()

}
