package com.srpl.crm.web.controller;

/**
 * @author Hammad Hassan Khan
 *
 */
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.DualListModel;

import com.bitguiders.util.jsf.JSFBeanInterface;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;
import com.srpl.crm.ejb.entity.CsContactORM;
import com.srpl.crm.ejb.entity.OrderORM;
import com.srpl.crm.ejb.entity.PaymentORM;
import com.srpl.crm.ejb.entity.SInvoiceORM;
import com.srpl.crm.ejb.entity.SPackageORM;
import com.srpl.crm.ejb.entity.SServiceSubscribeORM;
import com.srpl.crm.ejb.request.ContactDAO;
import com.srpl.crm.ejb.request.InvoiceDAO;
import com.srpl.crm.ejb.request.Order2DAO;
import com.srpl.crm.ejb.request.PackageDAO;
import com.srpl.crm.ejb.request.PaymentDAO;
import com.srpl.crm.ejb.request.SubscriptionDAO;
import com.srpl.crm.web.common.InnerTabs;
import com.srpl.crm.web.common.SessionDataBean;
import com.srpl.crm.web.common.Utils;
import com.srpl.crm.web.model.BillBackingBean;
import com.srpl.crm.web.model.Customer360ViewBackingBean;
import com.srpl.crm.web.model.InvoiceBackingBean;
import com.srpl.crm.web.model.PaymentBackingBean;
import com.srpl.crm.web.model.SubscriptionBackingBean;
import com.srpl.crm.web.model.common.ColumnModel;

@Deprecated
@ManagedBean(name = "customer360controller")
public class Customer360ViewController extends JSFBeanSupport implements
JSFBeanInterface,
		Serializable {

	/**
	 * 
	 */
	@EJB
	SubscriptionDAO subscriptionDao;
	@EJB
	ContactDAO contactDao;
	@EJB
	PackageDAO packagesDao;
	@EJB
	InvoiceDAO invoiceDao;
	@EJB
	Order2DAO orderDao;
	@EJB
	PaymentDAO paymentDao;

	@PostConstruct
	public void init() {
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		String oldAction;
		try {
			oldAction = getParameter("old_action");
			switch (oldAction) {
			case WebConstants.ACTION_CREATE:
				setCreateAction();
				break;
			case WebConstants.ACTION_VIEW:
				setViewAction();
				break;
			case WebConstants.ACTION_EDIT:
				setEditAction();
				break;
			case WebConstants.ACTION_DELETE:
				setDeleteAction();
				break;
			default:
				setViewAction();
			}
		} catch (Exception e) {
			System.out.println("exception on old_action.");
		}

		// Populate Orders
		try {
			orders = orderDao.listOrders(session.getSelectedCustomer());
		} catch (Exception e) {
			System.out
					.println("Exception while fetching orders for customer with id: "
							+ session.getSelectedCustomer());
		}

	}

	private static final long serialVersionUID = 1L;

	// ======= Controller Vaiables and Functions ===========//

	private void changeTabPath(int index, String path) {
		Customer360ViewBackingBean bean = BeanFactory.getInstance()
				.getCustomer360BeackingBean();
		ArrayList<InnerTabs> tabs = bean.getTabs();
		InnerTabs d = tabs.get(index);
		d.setPath(path);
		tabs.set(index, d);
		bean.setTabs(tabs);
		bean.setTabIndex(index);
	}

	// ================= Subscription Vars =======================//
	private static List<ColumnModel> subscriptionColumns;
	private static List<ColumnModel> subscriptionHistoryColumns;
	private Boolean subscription_update = true;
	private List<SubscriptionBackingBean> subscriptionsList;
	private DualListModel<SPackageORM> packages = new DualListModel<SPackageORM>();

	static {
		subscriptionColumns = new ArrayList<ColumnModel>();
		subscriptionColumns.add(new ColumnModel("id", "ID"));
		subscriptionColumns.add(new ColumnModel("packageOrProduct",
				"PACKAGE/PRODUCT"));
		subscriptionColumns.add(new ColumnModel("title", "TITLE"));
	}

	static {
		subscriptionHistoryColumns = new ArrayList<ColumnModel>();
		subscriptionHistoryColumns.add(new ColumnModel("id", "ID"));
		subscriptionHistoryColumns.add(new ColumnModel("packageOrProduct",
				"PACKAGE/PRODUCT"));
		subscriptionHistoryColumns.add(new ColumnModel("title", "TITLE"));
		subscriptionHistoryColumns.add(new ColumnModel("isSubscribe",
				"Subscribe"));
	}

	// ================= Subscription Tab =======================//
	// TODO

	public Boolean getSubscription_update() {
		return subscription_update;
	}

	public void setSubscription_update(Boolean subscription_update) {
		this.subscription_update = subscription_update;
	}

	public DualListModel<SPackageORM> getPackages() {
		return packages;
	}

	public void setPackages(DualListModel<SPackageORM> packages) {
		this.packages = packages;
	}

	public List<ColumnModel> getSubscriptionColumns() {
		return subscriptionColumns;
	}

	public void setSubscriptionColumns(List<ColumnModel> subscriptionColumns) {
		Customer360ViewController.subscriptionColumns = subscriptionColumns;
	}

	public List<ColumnModel> getSubscriptionHistoryColumns() {
		return subscriptionHistoryColumns;
	}

	public void setSubscriptionHistoryColumns(
			List<ColumnModel> subscriptionHistoryColumns) {
		Customer360ViewController.subscriptionHistoryColumns = subscriptionHistoryColumns;
	}

	// ================= Subscription Functions =======================//

	public List<SubscriptionBackingBean> getSubscriptionsList() {
		System.out.println("Customer360CB->getSubscriptions()");
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();

		ArrayList<SubscriptionBackingBean> myList = new ArrayList<SubscriptionBackingBean>();
		SubscriptionBackingBean bean;
		int listSize = 0;
		int dbSize = 0;

		// Get packages from DB
		List<SServiceSubscribeORM> listDb = subscriptionDao.list(session
				.getCustomerModule_selectedContact());
		try {
			dbSize = listDb.size();
			listSize = subscriptionsList.size();
		} catch (Exception e) {
			// handle exception
			System.out.println("packages not set yet, cant get size");
		}
		if ((listSize != dbSize || subscription_update == true) && dbSize != 0) {
			// update packages list
			for (SServiceSubscribeORM x : listDb) {
				bean = new SubscriptionBackingBean();
				convert2SubscriptionBean(x, bean);
				myList.add(bean);
			}
			setSubscription_update(false);
			setSubscriptionsList(myList);
		}
		return subscriptionsList;
	}

	public void setSubscriptionsList(
			List<SubscriptionBackingBean> subscriptionsList) {
		this.subscriptionsList = subscriptionsList;
	}

	// ================= Subscription ActionListener =======================//

	public void SubscriptionActionListener() {

		SubscriptionBackingBean bean = BeanFactory.getInstance()
				.getSubscriptionBackingBean();
		SServiceSubscribeORM db;
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		List<SPackageORM> packagesSource = null;
		List<SPackageORM> packagesTarget = null;
		Long id;

		try {
			CsContactORM customer = contactDao.contactDetails(session
					.getSelectedCustomer());
			bean.setSubscriber(customer);
		} catch (Exception e) {
			System.out.println("couldn't get the customer.");
		}

		switch (getAction()) {

		case WebConstants.ACTION_CREATE:
			// No subscription will be created, just updates are required
			changeTabPath(1,
					"/view/customer/contacts/c360view/subscribe/index.xhtml");
			break;
		case WebConstants.ACTION_SAVE:
			// How can you save when you aint creating anything?
			changeTabPath(1,
					"/view/customer/contacts/c360view/subscribe/index.xhtml");
			break;

		case WebConstants.ACTION_VIEW:
			id = Long.valueOf(getParameter("row_id").toString());
			try {
				db = subscriptionDao.details(id);
				convert2SubscriptionBean(db, bean);
				setViewAction();
				changeTabPath(1,
						"/view/customer/contacts/c360view/subscribe/index.xhtml");
				break;
			} catch (Exception e) {
				// handle exception
				addError("Couldn't Load the Company.");
				System.out.println("Couldn't Load");
			}
			setListAction(true);
			changeTabPath(1,
					"/view/customer/contacts/c360view/subscribe/index.xhtml");
			break;

		case WebConstants.ACTION_CANCEL:
			setListAction(true);
			changeTabPath(1,
					"/view/customer/contacts/c360view/subscribe/index.xhtml");
			break;

		case WebConstants.ACTION_EDIT:
			bean.reset();
			reset();

			packagesSource = packagesDao.customerPackages(
					session.getSelectedCustomer(), false);
			packagesTarget = packagesDao.customerPackages(
					session.getSelectedCustomer(), true);
			packages = new DualListModel<SPackageORM>(packagesSource,
					packagesTarget);

			setCreateAction();
			changeTabPath(1,
					"/view/customer/contacts/c360view/subscribe/subscribeForm.xhtml");
			break;

		case WebConstants.ACTION_UPDATE:
			// id = Long.valueOf(getParameter("row_id").toString());
			// bean.setId(id);
			List<SServiceSubscribeORM> subscriptions = new ArrayList<SServiceSubscribeORM>();

			packagesSource = packages.getSource();
			packagesTarget = packages.getTarget();

			SServiceSubscribeORM tempSub = null;
			for (SPackageORM x : packagesTarget) {
				tempSub = new SServiceSubscribeORM();
				tempSub.setIsPackage(true);
				tempSub.setPackg(packagesDao.packageDetails(x.getId()));
				subscriptions.add(tempSub);
			}
			try {
				// db = new SServiceSubscribeORM();
				// convert2SubscriptionDb(bean, db);
				// id = subscriptionDao.updates(db);
				subscriptionDao.updates(session.getSelectedCustomer(),
						subscriptions);
				setSubscription_update(true);
				addMessage("Subscription Successfully Updated");
			} catch (Exception e) {
				// handle exception
				System.out.println("Couldn't create");
				addError("Package Updation Failed.");
			}
			setListAction(true);
			changeTabPath(1,
					"/view/customer/contacts/c360view/subscribe/index.xhtml");
			break;

		case WebConstants.ACTION_DELETE:
			id = Long.valueOf(getParameter("row_id").toString());
			try {
				db = subscriptionDao.details(id);
				convert2SubscriptionBean(db, bean);
				setDeleteAction();
				changeTabPath(1,
						"/view/customer/contacts/c360view/subscribe/index.xhtml");
				break;
			} catch (Exception e) {
				// handle exception
				addError("Couldn't Load the Reference.");
				System.out.println("Couldn't Load");
			}
			setListAction(true);
			changeTabPath(1,
					"/view/customer/contacts/c360view/subscribe/index.xhtml");
			break;

		case WebConstants.ACTION_DELETE_CONFIRMED:
			id = Long.valueOf(getParameter("row_id").toString());
			try {
				subscriptionDao.deletes(id);
			} catch (Exception e) {
				// handle exception
				addError("Couldn't Delete the Company.");
				System.out.println("Deletion Failed.");
			}
			setListAction(true);
			changeTabPath(1,
					"/view/customer/contacts/c360view/subscribe/index.xhtml");
			break;

		default:
			setListAction(true);
			changeTabPath(1,
					"/view/customer/contacts/c360view/subscribe/index.xhtml");
			break;
		}
	}

	public void convert2SubscriptionBean(SServiceSubscribeORM db,
			SubscriptionBackingBean bean) {
		bean.setId(db.getId());
		bean.setSubscriber(db.getSubscriber());
		bean.setIsPackage(db.getIsPackage());
		bean.setPackg(db.getPackg());
		bean.setProduct(db.getProduct());

		bean.setPackageOrProduct((bean.getIsPackage()) ? "Package" : "Product");
		bean.setTitle((bean.getIsPackage()) ? bean.getPackg().getTitle() : bean
				.getProduct().getProductTitle());
	}

	public void convert2SubscriptionDb(SubscriptionBackingBean bean,
			SServiceSubscribeORM db) {
		db.setId(bean.getId());
		db.setSubscriber(bean.getSubscriber());
		db.setIsPackage(bean.getIsPackage());
		db.setPackg(bean.getPackg());
		db.setProduct(bean.getProduct());
	}

	// ================= Subscription Ends =======================//

	// ================= S. History Vars =======================//
	private static List<ColumnModel> sHistoryColumns;
	private Boolean sHistoryUpdate = true;
	private List<SubscriptionBackingBean> sHistoryList;
	private DualListModel<SPackageORM> historyPackages = new DualListModel<SPackageORM>();

	static {

		sHistoryColumns = new ArrayList<ColumnModel>();
		sHistoryColumns.add(new ColumnModel("id", "ID"));
		sHistoryColumns.add(new ColumnModel("packageOrProduct",
				"PACKAGE/PRODUCT"));
		sHistoryColumns.add(new ColumnModel("title", "TITLE"));
	}

	// ================= S. History Tab =======================//
	// TODO

	public Boolean getSHistoryUpdate() {
		return sHistoryUpdate;
	}

	public void setSHistoryUpdate(Boolean subscription_update) {
		this.sHistoryUpdate = subscription_update;
	}

	public DualListModel<SPackageORM> getHistoryPackages() {
		return historyPackages;
	}

	public void setHistoryPackages(DualListModel<SPackageORM> packages) {
		this.historyPackages = packages;
	}

	public List<ColumnModel> getSHistoryColumns() {
		return sHistoryColumns;
	}

	public void setSHistoryColumns(List<ColumnModel> subscriptionColumns) {
		Customer360ViewController.sHistoryColumns = subscriptionColumns;
	}

	public List<SubscriptionBackingBean> getSHistoryList() {
		System.out.println("Customer360CB->getSubscriptions()");
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();

		ArrayList<SubscriptionBackingBean> myList = new ArrayList<SubscriptionBackingBean>();
		SubscriptionBackingBean bean;
		int listSize = 0;
		int dbSize = 0;

		// Get packages from DB
		List<SServiceSubscribeORM> listDb = subscriptionDao.list(session
				.getSelectedCustomer());
		try {
			dbSize = listDb.size();
			listSize = subscriptionsList.size();
		} catch (Exception e) {
			// handle exception
			System.out.println("packages not set yet, cant get size");
		}
		if ((listSize != dbSize || subscription_update == true) && dbSize != 0) {
			// update packages list
			for (SServiceSubscribeORM x : listDb) {
				bean = new SubscriptionBackingBean();
				convert2SubscriptionBean(x, bean);
				myList.add(bean);
			}
			setSubscription_update(false);
			setSubscriptionsList(myList);
		}
		return sHistoryList;
	}

	public void setSHistoryList(List<SubscriptionBackingBean> subscriptionsList) {
		this.sHistoryList = subscriptionsList;
	}

	// ================= S. History ActionListener =======================//

	public void SHistoryActionListener() {

		SubscriptionBackingBean bean = BeanFactory.getInstance()
				.getSubscriptionBackingBean();
		SServiceSubscribeORM db;
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		List<SPackageORM> packagesSource = null;
		List<SPackageORM> packagesTarget = null;
		Long id;

		try {
			CsContactORM customer = contactDao.contactDetails(session
					.getSelectedCustomer());
			bean.setSubscriber(customer);
		} catch (Exception e) {
			System.out.println("couldn't get the customer.");
		}

		switch (getAction()) {

		case WebConstants.ACTION_CREATE:
			// No subscription will be created, just updates are required
			changeTabPath(1,
					"/view/customer/contacts/c360view/subscribe/index.xhtml");
			break;
		case WebConstants.ACTION_SAVE:
			// How can you save when you aint creating anything?
			changeTabPath(1,
					"/view/customer/contacts/c360view/subscribe/index.xhtml");
			break;

		case WebConstants.ACTION_VIEW:
			id = Long.valueOf(getParameter("row_id").toString());
			try {
				db = subscriptionDao.details(id);
				convert2SubscriptionBean(db, bean);
				setViewAction();
				changeTabPath(1,
						"/view/customer/contacts/c360view/subscribe/index.xhtml");
				break;
			} catch (Exception e) {
				// handle exception
				addError("Couldn't Load the Company.");
				System.out.println("Couldn't Load");
			}
			setListAction(true);
			changeTabPath(1,
					"/view/customer/contacts/c360view/subscribe/index.xhtml");
			break;

		case WebConstants.ACTION_CANCEL:
			setListAction(true);
			changeTabPath(1,
					"/view/customer/contacts/c360view/subscribe/index.xhtml");
			break;

		case WebConstants.ACTION_EDIT:
			bean.reset();
			reset();

			packagesSource = packagesDao.customerPackages(
					session.getSelectedCustomer(), false);
			packagesTarget = packagesDao.customerPackages(
					session.getSelectedCustomer(), true);
			packages = new DualListModel<SPackageORM>(packagesSource,
					packagesTarget);

			setCreateAction();
			changeTabPath(1,
					"/view/customer/contacts/c360view/subscribe/subscribeForm.xhtml");
			break;

		case WebConstants.ACTION_UPDATE:
			// id = Long.valueOf(getParameter("row_id").toString());
			// bean.setId(id);
			List<SServiceSubscribeORM> subscriptions = new ArrayList<SServiceSubscribeORM>();

			packagesSource = packages.getSource();
			packagesTarget = packages.getTarget();

			SServiceSubscribeORM tempSub = null;
			for (SPackageORM x : packagesTarget) {
				tempSub = new SServiceSubscribeORM();
				tempSub.setIsPackage(true);
				tempSub.setPackg(packagesDao.packageDetails(x.getId()));
				subscriptions.add(tempSub);
			}
			try {
				// db = new SServiceSubscribeORM();
				// convert2SubscriptionDb(bean, db);
				// id = subscriptionDao.updates(db);
				subscriptionDao.updates(session.getSelectedCustomer(),
						subscriptions);
				setSubscription_update(true);
				addMessage("Subscription Successfully Updated");
			} catch (Exception e) {
				// handle exception
				System.out.println("Couldn't create");
				addError("Package Updation Failed.");
			}
			setListAction(true);
			changeTabPath(1,
					"/view/customer/contacts/c360view/subscribe/index.xhtml");
			break;

		case WebConstants.ACTION_DELETE:
			id = Long.valueOf(getParameter("row_id").toString());
			try {
				db = subscriptionDao.details(id);
				convert2SubscriptionBean(db, bean);
				setDeleteAction();
				changeTabPath(1,
						"/view/customer/contacts/c360view/subscribe/index.xhtml");
				break;
			} catch (Exception e) {
				// handle exception
				addError("Couldn't Load the Reference.");
				System.out.println("Couldn't Load");
			}
			setListAction(true);
			changeTabPath(1,
					"/view/customer/contacts/c360view/subscribe/index.xhtml");
			break;

		case WebConstants.ACTION_DELETE_CONFIRMED:
			id = Long.valueOf(getParameter("row_id").toString());
			try {
				subscriptionDao.deletes(id);
			} catch (Exception e) {
				// handle exception
				addError("Couldn't Delete the Company.");
				System.out.println("Deletion Failed.");
			}
			setListAction(true);
			changeTabPath(1,
					"/view/customer/contacts/c360view/subscribe/index.xhtml");
			break;

		default:
			setListAction(true);
			changeTabPath(1,
					"/view/customer/contacts/c360view/subscribe/index.xhtml");
			break;
		}
	}

	public void convert2SHistoryBean(SServiceSubscribeORM db,
			SubscriptionBackingBean bean) {
		bean.setId(db.getId());
		bean.setSubscriber(db.getSubscriber());
		bean.setIsPackage(db.getIsPackage());
		bean.setPackg(db.getPackg());
		bean.setProduct(db.getProduct());

		bean.setPackageOrProduct((bean.getIsPackage()) ? "Package" : "Product");
		bean.setTitle((bean.getIsPackage()) ? bean.getPackg().getTitle() : bean
				.getProduct().getProductTitle());
	}

	public void convert2SHistoryDb(SubscriptionBackingBean bean,
			SServiceSubscribeORM db) {
		db.setId(bean.getId());
		db.setSubscriber(bean.getSubscriber());
		db.setIsPackage(bean.getIsPackage());
		db.setPackg(bean.getPackg());
		db.setProduct(bean.getProduct());
	}

	// ================= S. History Ends =======================//

	// ================= Invoice/Billing Vars =======================//
	// TODO
	private static List<ColumnModel> invoiceColumns;
	private Boolean invoice_update = true;
	private List<InvoiceBackingBean> invoicesList;
	private List<OrderORM> orders;

	static {

		invoiceColumns = new ArrayList<ColumnModel>();
		invoiceColumns.add(new ColumnModel("id", "ID"));
		invoiceColumns.add(new ColumnModel("title", "TITLE"));
		invoiceColumns.add(new ColumnModel("issueDate", "ISSUE DATE"));
		invoiceColumns.add(new ColumnModel("amount", "AMOUNT"));
	}

	// ================= Invoice/Billing Tab =======================//

	public Boolean getInvoice_update() {
		return invoice_update;
	}

	public void setInvoice_update(Boolean subscription_update) {
		this.invoice_update = subscription_update;
	}

	public List<ColumnModel> getInvoiceColumns() {
		return invoiceColumns;
	}

	public void setInvoiceColumns(List<ColumnModel> subscriptionColumns) {
		Customer360ViewController.invoiceColumns = subscriptionColumns;
	}

	public List<InvoiceBackingBean> getInvoiceList() {
		System.out.println("Customer360CB->getInvoices()");
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		// Temporary save the contents of backing bean, so it can be reset to
		// its original state
		try {
			InvoiceBackingBean temp = BeanFactory.getInstance()
					.getInvoiceBackingBean();
			System.out.println("bean id in getInvoiceList: " + temp.getId());
		} catch (Exception e) {
			System.out
					.println("error while printing invoice value in getInvoiceList");
		}

		ArrayList<InvoiceBackingBean> myList = new ArrayList<InvoiceBackingBean>();
		InvoiceBackingBean bean;
		int listSize = 0;
		int dbSize = 0;

		// Get packages from DB
		List<SInvoiceORM> listDb = invoiceDao.list(session
				.getCustomerModule_selectedContact());
		try {
			dbSize = listDb.size();
			listSize = invoicesList.size();
		} catch (Exception e) {
			// handle exception
			System.out.println("invoices not set yet, cant get size");
		}
		if ((listSize != dbSize || invoice_update == true) && dbSize != 0) {
			// update packages list
			for (SInvoiceORM x : listDb) {
				bean = new InvoiceBackingBean();
				convert2InvoiceBean(x, bean);
				myList.add(bean);
			}
			setInvoice_update(false);
			setInvoicesList(myList);
		}
		return invoicesList;
	}

	public void setInvoicesList(List<InvoiceBackingBean> subscriptionsList) {
		this.invoicesList = subscriptionsList;
	}

	// ================= Invoice ActionListener =======================//

	public void InvoiceActionListener() {

		InvoiceBackingBean bean = BeanFactory.getInstance()
				.getInvoiceBackingBean();
		SInvoiceORM db;
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		Long id;

		try {
			CsContactORM customer = contactDao.contactDetails(session
					.getSelectedCustomer());
			bean.setSubscriber(customer);
		} catch (Exception e) {
			System.out.println("couldn't get the customer.");
		}

		switch (getAction()) {
		case WebConstants.ACTION_CREATE:
			bean.reset();
			reset();

			setCreateAction();
			changeTabPath(2,
					"/view/customer/contacts/c360view/billing/view.xhtml");
			break;

		case WebConstants.ACTION_SAVE:
			try {
				// db = new SInvoiceORM();
				// convert2InvoiceDb(bean, db);
				id = invoiceDao.create(bean.getOrderId());
				bean.setId(id);
				setInvoice_update(true);
				setListAction(true);
				addMessage("Invoice Successfully Created");
			} catch (Exception e) {
				// handle exception
				System.out.println("Couldn't create");
				addError("Invoice Creation Failed.");
			}
			setListAction(true);
			changeTabPath(2,
					"/view/customer/contacts/c360view/billing/index.xhtml");
			break;

		case WebConstants.ACTION_VIEW:
			id = Long.valueOf(getParameter("row_id").toString());
			bean = new InvoiceBackingBean();
			try {
				db = invoiceDao.details(id);
				System.out.println("invoice details size in controller: "
						+ db.getDetails().size());
				convert2InvoiceBean(db, bean, true);
				System.out
						.println("invoice details size in controller -> bean: "
								+ bean.getInvoiceDetails().size());
				session.setSelectedInvoice(bean);
				setViewAction();
				changeTabPath(2,
						"/view/customer/contacts/c360view/billing/view.xhtml");
				break;
			} catch (Exception e) {
				// handle exception
				addError("Couldn't Load the Invoice.");
				System.out.println("Couldn't Load");
			}
			setListAction(true);
			changeTabPath(2,
					"/view/customer/contacts/c360view/billing/index.xhtml");
			break;

		case WebConstants.ACTION_CANCEL:
			setListAction(true);
			changeTabPath(2,
					"/view/customer/contacts/c360view/billing/index.xhtml");
			break;

		case WebConstants.ACTION_EDIT:
			// No need to edit an invoice
			changeTabPath(2,
					"/view/customer/contacts/c360view/billing/index.xhtml");
			break;

		case WebConstants.ACTION_UPDATE:
			// if not edit, how can we update? :p
			changeTabPath(2,
					"/view/customer/contacts/c360view/billing/index.xhtml");
			break;

		case WebConstants.ACTION_DELETE:
			id = Long.valueOf(getParameter("row_id").toString());
			try {
				db = invoiceDao.details(id);
				convert2InvoiceBean(db, bean);
				setDeleteAction();
				changeTabPath(2,
						"/view/customer/contacts/c360view/billing/view.xhtml");
				break;
			} catch (Exception e) {
				// handle exception
				addError("Couldn't Load the Invoice.");
				System.out.println("Couldn't Load");
			}
			setListAction(true);
			changeTabPath(2,
					"/view/customer/contacts/c360view/billing/index.xhtml");
			break;

		case WebConstants.ACTION_DELETE_CONFIRMED:
			id = Long.valueOf(getParameter("row_id").toString());
			try {
				invoiceDao.delete(id);
			} catch (Exception e) {
				// handle exception
				addError("Couldn't Delete the Invoice.");
				System.out.println("Deletion Failed.");
			}
			setListAction(true);
			changeTabPath(2,
					"/view/customer/contacts/c360view/billing/index.xhtml");
			break;

		default:
			setListAction(true);
			changeTabPath(2,
					"/view/customer/contacts/c360view/billing/index.xhtml");
			break;
		}
	}

	public void convert2InvoiceBean(SInvoiceORM db, InvoiceBackingBean bean) {
		convert2InvoiceBean(db, bean, false);
	}

	public void convert2InvoiceBean(SInvoiceORM db, InvoiceBackingBean bean,
			Boolean withLazy) {
		bean.setId(db.getId());
		bean.setAmount(db.getTotalAmount());
		bean.setCreatedBy(db.getCreatedBy());
		bean.setIssueDate(db.getIssueDate());
		bean.setSubscriber(db.getSubscriber());
		bean.setTitle(db.getTitle());
		if (withLazy) {
			bean.setInvoiceDetails(db.getDetails());
		}
	}

	public void convert2InvoiceDb(InvoiceBackingBean bean, SInvoiceORM db) {
		db.setId(bean.getId());
		db.setTotalAmount(bean.getAmount());
		db.setCreatedBy(bean.getCreatedBy());
		db.setIssueDate(new Timestamp(bean.getIssueDate().getTime()));
		db.setSubscriber(bean.getSubscriber());
		db.setTitle(bean.getTitle());
	}

	// ================= Invoice/Billing Ends =======================//

	// ===================== Billing =============================== //
	
		public List<BillBackingBean> listCustomerBills(){
			System.out.println("Customer360ViewController -> listCustomerBills() called");
			SessionDataBean session = BeanFactory.getInstance().getSessionBean();
			CsContactORM customer = new CsContactORM();
			try{
			customer = contactDao.contactDetails(session.getSelectedCustomer());
			}catch(Exception e){
				
			}
		    List<BillBackingBean> customerBillList = new ArrayList<BillBackingBean>();
			customerBillList = Utils.getCustomerBillsFromCustomerDb(customer);
			return customerBillList;
		}
		
     // ===================== Billing Ends ========================== //
	
	// ================= Payment Vars =======================//
	// TODO
	private static List<ColumnModel> paymentColumns;
	private Boolean payment_update = true;
	private List<PaymentBackingBean> paymentList;
	private List<SInvoiceORM> unpaidInvoiceList;

	static {

		paymentColumns = new ArrayList<ColumnModel>();
		paymentColumns.add(new ColumnModel("id", "ID"));
		paymentColumns.add(new ColumnModel("title", "TITLE"));
		paymentColumns.add(new ColumnModel("issueDate", "ISSUE DATE"));
		paymentColumns.add(new ColumnModel("amount", "AMOUNT"));
	}

	// ================= Payment Tab =======================//

	public Boolean getPayment_update() {
		return payment_update;
	}

	public void setPayment_update(Boolean subscription_update) {
		this.payment_update = subscription_update;
	}

	public List<ColumnModel> getPaymentColumns() {
		return paymentColumns;
	}

	public void setPaymentColumns(List<ColumnModel> subscriptionColumns) {
		Customer360ViewController.paymentColumns = subscriptionColumns;
	}

	public List<PaymentBackingBean> getPaymentList() {
		System.out.println("Customer360CB->getPayments()");
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		// Temporary save the contents of backing bean, so it can be reset to
		// its original state
		try {
			PaymentBackingBean temp = BeanFactory.getInstance()
					.getPaymentBackingBean();
			System.out.println("bean id in getPaymentList: " + temp.getId());
		} catch (Exception e) {
			System.out
					.println("error while printing payment value in getPaymentList");
		}

		ArrayList<PaymentBackingBean> myList = new ArrayList<PaymentBackingBean>();
		PaymentBackingBean bean;
		int listSize = 0;
		int dbSize = 0;

		// Get payments from DB
		List<PaymentORM> listDb = paymentDao
				.list(session.getSelectedCustomer());
		try {
			dbSize = listDb.size();
			listSize = paymentList.size();
		} catch (Exception e) {
			// handle exception
			System.out.println("payments not set yet, cant get size");
		}
		if ((listSize != dbSize || invoice_update == true) && dbSize != 0) {
			// update packages list
			for (PaymentORM x : listDb) {
				bean = new PaymentBackingBean();
				convert2PaymentBean(x, bean);
				myList.add(bean);
			}
			setPayment_update(false);
			setPaymentList(myList);
		}
		return paymentList;
	}

	public void setPaymentList(List<PaymentBackingBean> subscriptionsList) {
		this.paymentList = subscriptionsList;
	}

	public List<SInvoiceORM> getUnpaidInvoiceList() {
		try {
			SessionDataBean session = BeanFactory.getInstance()
					.getSessionBean();
			List<SInvoiceORM> list = invoiceDao.listWithPaidCheck(
					session.getSelectedCustomer(), true);
			setUnpaidInvoiceList(list);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return unpaidInvoiceList;
	}

	public void setUnpaidInvoiceList(List<SInvoiceORM> unpaidInvoiceList) {
		this.unpaidInvoiceList = unpaidInvoiceList;
	}

	public void populatePayment() {
		try {
			PaymentBackingBean payment = BeanFactory.getInstance().getPaymentBackingBean();
			if(payment.getInvoiceId() != null){
				SInvoiceORM invoice = invoiceDao.details(payment.getInvoiceId());
				payment.setSInvoice(invoice);
				payment.setCompanyId(invoice.getCompanyId());
				payment.setInvoiceAmount(invoice.getTotalAmount());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// ================= Payment ActionListener =======================//

	public void PaymentActionListener() {

		PaymentBackingBean bean = BeanFactory.getInstance()
				.getPaymentBackingBean();
		//PaymentORM db;
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		Long id;

		try {
			CsContactORM customer = contactDao.contactDetails(session
					.getSelectedCustomer());
			bean.setSubscriber(customer);
		} catch (Exception e) {
			System.out.println("couldn't get the customer.");
		}

		switch (getAction()) {
		case WebConstants.ACTION_CREATE:
			bean.reset();
			reset();

			setCreateAction();
			changeTabPath(3,
					"/view/customer/contacts/c360view/payment/index.xhtml");
			break;

		case WebConstants.ACTION_SAVE:
			try {
				//db = new PaymentORM();
				// convert2PaymentDb(bean, db);
	      		
				//id = invoiceDao.update(bean.getInvoiceId(), bean.getPaidAmount());
				id = paymentDao.create(bean.getInvoiceId(), bean.getPaidAmount());
				bean.setId(id);
				addMessage("Invoice Successfully Paid");
			} catch (Exception e) {
				// handle exception
				System.out.println("Couldn't create");
				addError("Invoice Payment Failed.");
			}
			setListAction(true);
			changeTabPath(4,
					"/view/customer/contacts/c360view/payNow/index.xhtml");
			break;

		case WebConstants.ACTION_VIEW:
			/*
			 * id = Long.valueOf(getParameter("row_id").toString()); bean = new
			 * InvoiceBackingBean(); try { db = invoiceDao.details(id);
			 * System.out.println("invoice details size in controller: " +
			 * db.getDetails().size()); convert2InvoiceBean(db, bean, true);
			 * System.out
			 * .println("invoice details size in controller -> bean: " +
			 * bean.getInvoiceDetails().size());
			 * session.setSelectedInvoice(bean); setViewAction();
			 * changeTabPath(2,
			 * "/view/customer/contacts/c360view/billing/view.xhtml"); break; }
			 * catch (Exception e) { // handle exception
			 * addError("Couldn't Load the Invoice.");
			 * System.out.println("Couldn't Load"); } setListAction(true);
			 * changeTabPath(2,
			 * "/view/customer/contacts/c360view/billing/index.xhtml");
			 */

			// No need of view
			break;

		case WebConstants.ACTION_CANCEL:
			setListAction(true);
			changeTabPath(2,
					"/view/customer/contacts/c360view/billing/index.xhtml");
			break;

		case WebConstants.ACTION_EDIT:
			// No need to edit an invoice
			changeTabPath(2,
					"/view/customer/contacts/c360view/billing/index.xhtml");
			break;

		case WebConstants.ACTION_UPDATE:
			// if not edit, how can we update? :p
			changeTabPath(2,
					"/view/customer/contacts/c360view/billing/index.xhtml");
			break;

		case WebConstants.ACTION_DELETE:
			id = Long.valueOf(getParameter("row_id").toString());
			/*
			 * try { db = invoiceDao.details(id); convert2InvoiceBean(db, bean);
			 * setDeleteAction(); changeTabPath(2,
			 * "/view/customer/contacts/c360view/billing/view.xhtml"); break; }
			 * catch (Exception e) { // handle exception
			 * addError("Couldn't Load the Invoice.");
			 * System.out.println("Couldn't Load"); } setListAction(true);
			 * changeTabPath(2,
			 * "/view/customer/contacts/c360view/billing/index.xhtml");
			 */
			// no need
			break;

		case WebConstants.ACTION_DELETE_CONFIRMED:
			id = Long.valueOf(getParameter("row_id").toString());
			/*
			 * try { invoiceDao.delete(id); } catch (Exception e) { // handle
			 * exception addError("Couldn't Delete the Invoice.");
			 * System.out.println("Deletion Failed."); } setListAction(true);
			 * changeTabPath(2,
			 * "/view/customer/contacts/c360view/billing/index.xhtml");
			 */
			// no need
			break;

		default:
			setListAction(true);
			changeTabPath(4,
					"/view/customer/contacts/c360view/payment/index.xhtml");
			break;
		}
	}

	public void convert2PaymentBean(PaymentORM db, PaymentBackingBean bean) {
		bean.setCompanyId(db.getCompanyId());
		bean.setId(db.getId());
		bean.setInvoiceAmount(db.getInvoiceAmount());
		bean.setPaidAmount(db.getPaidAmount());
		bean.setPaidOnDate(db.getPaidOnDate());
		bean.setRemainingAmount(db.getRemainingAmount());
		bean.setSInvoice(db.getSInvoice());
		bean.setSubscriber(db.getSubscriber());
	}

	public void convert2PaymentDb(PaymentBackingBean bean, PaymentORM db) {
		db.setCompanyId(bean.getCompanyId());
		db.setId(bean.getId());
		db.setInvoiceAmount(bean.getInvoiceAmount());
		db.setPaidAmount(bean.getPaidAmount());
		db.setPaidOnDate(bean.getPaidOnDate());
		db.setRemainingAmount(bean.getRemainingAmount());
		db.setSInvoice(bean.getSInvoice());
		db.setSubscriber(bean.getSubscriber());
	}
	// ================= Invoice/Billing Ends =======================//

	@Override
	public String actionListener() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> getList() {
		// TODO Auto-generated method stub
		return null;
	}

}
