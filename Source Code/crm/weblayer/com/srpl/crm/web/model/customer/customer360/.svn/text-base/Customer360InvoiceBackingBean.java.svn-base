package com.srpl.crm.web.model.customer.customer360;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import com.bitguiders.util.jsf.JSFBeanInterface;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;
import com.srpl.crm.ejb.entity.CsContactORM;
import com.srpl.crm.ejb.entity.SInvoiceDetailORM;
import com.srpl.crm.ejb.entity.SInvoiceORM;
import com.srpl.crm.ejb.request.ContactDAO;
import com.srpl.crm.ejb.request.InvoiceDAO;
import com.srpl.crm.ejb.request.Order2DAO;
import com.srpl.crm.ejb.request.PaymentDAO;
import com.srpl.crm.web.common.InnerTabs;
import com.srpl.crm.web.common.SessionDataBean;
import com.srpl.crm.web.controller.BeanFactory;
import com.srpl.crm.web.model.common.ColumnModel;

@ManagedBean(name = "customer360Invoice")
public class Customer360InvoiceBackingBean extends JSFBeanSupport implements
		JSFBeanInterface {

	@EJB
	ContactDAO contactDao;
	@EJB
	InvoiceDAO invoiceDao;
	@EJB
	Order2DAO orderDao;
	@EJB
	PaymentDAO paymentDao;

	private SessionDataBean session;

	private static List<ColumnModel> invoiceColumns;
	private Long invoiceId;
	private CsContactORM subscriber;
	private Date issueDate;
	private Long createdBy;
	private double amount;
	private String title;
	private List<SInvoiceDetailORM> invoiceDetails;
	private String actualInvoice;
	private boolean showDataTable = true;
	private boolean showInvoice = false;
	// Need an order field to create new invoice
	private Long orderId;

	 {

		invoiceColumns = new ArrayList<ColumnModel>();
		invoiceColumns.add(new ColumnModel("id", this.getProperty("label.id")));
		invoiceColumns.add(new ColumnModel("title", this.getProperty("label.title")));
		invoiceColumns.add(new ColumnModel("issueDate", this.getProperty("label.issue.date")));
	}

	// ================= Invoice/Billing Tab =======================//

	public List<ColumnModel> getInvoiceColumns() {
		return invoiceColumns;
	}

	public void setInvoiceColumns(List<ColumnModel> invoiceColumns) {
		Customer360InvoiceBackingBean.invoiceColumns = invoiceColumns;
	}

	public Long getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Long id) {
		this.invoiceId = id;
	}

	public CsContactORM getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(CsContactORM subscriber) {
		this.subscriber = subscriber;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issue_date) {
		this.issueDate = issue_date;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long created_by) {
		this.createdBy = created_by;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long order_id) {
		this.orderId = order_id;
	}

	public List<SInvoiceDetailORM> getInvoiceDetails() {
		return invoiceDetails;
	}

	public void setInvoiceDetails(List<SInvoiceDetailORM> invoiceDetails) {
		this.invoiceDetails = invoiceDetails;
	}

	public void setInvoiceDetails(Set<SInvoiceDetailORM> invoiceDetails) {
		this.invoiceDetails = new ArrayList<SInvoiceDetailORM>(invoiceDetails);
	}

	public String getActualInvoice() {
		return actualInvoice;
	}

	public void setActualInvoice(String actualInvoice) {
		this.actualInvoice = actualInvoice;
	}

	public Customer360InvoiceBackingBean() {
		session = BeanFactory.getInstance().getSessionBean();
	}

	public boolean isShowDataTable() {
		return showDataTable;
	}

	public void setShowDataTable(boolean showDataTable) {
		this.showDataTable = showDataTable;
	}

	public boolean isShowInvoice() {
		return showInvoice;
	}

	public void setShowInvoice(boolean showInvoice) {
		this.showInvoice = showInvoice;
	}

	@PostConstruct
	public void postConstruct() {
		if (getAction().equals("")) {
			// put default action here
			actionView();
			System.out.println("post contruct called..................");
		}
	}

	@Override
	public String actionListener() {
		setCurrentAction(getAction(), this.getClass());
		switch (getCurrentAction()) {
		case WebConstants.ACTION_CREATE:
			actionCreate();
			break;
		case WebConstants.ACTION_SAVE:
			actionSave();
			break;
		case WebConstants.ACTION_VIEW:
			Long id = null;
			id = Long.valueOf(getParameter("row_id").toString());
			session.setCustomerModule_selectedInvoice(id);
			boolean result = actionView();
			if(!result){
				addError("Couldn't Load the Invoice.");
			}
			break;
		case WebConstants.ACTION_EDIT:
			// No Need
			break;
		case WebConstants.ACTION_UPDATE:
			actionUpdate();
			break;
		case WebConstants.ACTION_DELETE_CONFIRMED:
			// No need here
			break;
		case WebConstants.ACTION_LIST:
			listPage();
			break;
		case WebConstants.ACTION_CANCEL:
			listPage();
			break;
		default:
			listPage();
		}
		return null;
	}

	@Override
	public List<SInvoiceORM> getList() {
		System.out.println("Customer360Invoices()");
		// its original state

		// Get packages from DB
		List<SInvoiceORM> listDb = invoiceDao.list(session
				.getCustomerModule_selectedContact());

		return listDb;
	}

	public void listPage() {
		this.changeTabPath(2,
				"/view/customer/contacts/c360view/billing/index.xhtml");
		setListAction(true);
	}

	public void formPage() {
		Long id = null;
		SInvoiceORM db = null;
		id = Long.valueOf(getParameter("row_id").toString());
		Customer360InvoiceBackingBean bean = this;
		try {
			db = invoiceDao.details(id);
			System.out.println("invoice details size in controller: "
					+ db.getDetails().size());
			convert2InvoiceBean(db, bean, true);
			System.out.println("invoice details size in controller -> bean: "
					+ bean.getInvoiceDetails().size());
			// session.setSelectedInvoice(bean);
			setViewAction();
			changeTabPath(2,
					"/view/customer/contacts/c360view/billing/view.xhtml");
			return;
		} catch (Exception e) {
			// handle exception
			addError("Couldn't Load the Invoice.");
			System.out.println("Couldn't Load");
		}
		setListAction(true);
		changeTabPath(2, "/view/customer/contacts/c360view/billing/index.xhtml");
	}

	public boolean actionView() {
		Long id = session.getCustomerModule_selectedInvoice();
		actualInvoice = "";
		try {
			actualInvoice = invoiceDao.generateInvoiceHTML(id);
			if (!actualInvoice.equals("")) {
				actualInvoice = actualInvoice.replace("\"", "'");
				changeTabPath(2,
						"/view/customer/contacts/c360view/billing/view.xhtml");
			}
			setViewAction();
			return true;
		} catch (Exception e) {
			// handle exception
			System.out.println("Couldn't Load");
			setListAction(true);
			changeTabPath(2,
					"/view/customer/contacts/c360view/billing/index.xhtml");
		}
		return false;
	}

	public void actionCreate() {
		reset();
		setCreateAction();
		changeTabPath(2, "/view/customer/contacts/c360view/billing/view.xhtml");
	}

	public void actionSave() {
		try {
			// db = new SInvoiceORM();
			// convert2InvoiceDb(bean, db);
			invoiceId = invoiceDao.create(getOrderId());
			setListAction(true);
			addMessage("Invoice Successfully Created");
		} catch (Exception e) {
			// handle exception
			System.out.println("Couldn't create");
			addError("Invoice Creation Failed.");
		}
		setListAction(true);
		changeTabPath(2, "/view/customer/contacts/c360view/billing/index.xhtml");
	}

	public void actionUpdate() {
		return;
	}

	private void changeTabPath(int index, String path) {
		InnerTabs d = session.getContactTabs().get(index);
		d.setPath(path);
		session.getContactTabs().set(index, d);
		session.setCustomerModule_contactTabIndex(2);
	}

	public void convert2InvoiceBean(SInvoiceORM db,
			Customer360InvoiceBackingBean bean, Boolean withLazy) {
		bean.setInvoiceId(db.getId());
		bean.setAmount(db.getTotalAmount());
		bean.setCreatedBy(db.getCreatedBy());
		bean.setIssueDate(db.getIssueDate());
		bean.setSubscriber(db.getSubscriber());
		bean.setTitle(db.getTitle());
		if (withLazy) {
			bean.setInvoiceDetails(db.getDetails());
		}
	}
}