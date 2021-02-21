package com.srpl.crm.web.model.customer.customer360;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.model.DualListModel;

import com.bitguiders.util.jsf.JSFBeanInterface;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;
import com.srpl.crm.web.controller.BeanFactory;
import com.srpl.crm.ejb.entity.SPackageORM;
import com.srpl.crm.ejb.entity.SServiceSubscribeORM;
import com.srpl.crm.ejb.entity.SServiceSubscriptionHistoryORM;
import com.srpl.crm.ejb.request.PackageDAO;
import com.srpl.crm.ejb.request.SubscriptionDAO;
import com.srpl.crm.ejb.request.SubscriptionHistoryDAO;
import com.srpl.crm.web.common.InnerTabs;
import com.srpl.crm.web.common.SessionDataBean;
import com.srpl.crm.web.model.common.ColumnModel;

@ManagedBean(name = "customer360Subscribe")
public class Customer360SubscribeBackingBean extends JSFBeanSupport implements
		JSFBeanInterface {

	@EJB
	SubscriptionDAO subscriptionDao;
	@EJB
	SubscriptionHistoryDAO historyDao;
	@EJB
	PackageDAO packagesDao;

	private SessionDataBean session;

	// ================= Subscription Vars =======================//
	private static List<ColumnModel> subscriptionColumns;
	private static List<ColumnModel> subscriptionHistoryColumns;
	private DualListModel<SPackageORM> packages = new DualListModel<SPackageORM>();

	 {
		subscriptionColumns = new ArrayList<ColumnModel>();
		subscriptionColumns.add(new ColumnModel("id", this.getProperty("label.id")));
		subscriptionColumns.add(new ColumnModel("packageOrProduct",
				this.getProperty("label.package.product")));
		subscriptionColumns.add(new ColumnModel("title", this.getProperty("label.title")));
	}

	 {
		subscriptionHistoryColumns = new ArrayList<ColumnModel>();
		subscriptionHistoryColumns.add(new ColumnModel("id", this.getProperty("label.id")));
		subscriptionHistoryColumns.add(new ColumnModel("packageOrProduct",
				this.getProperty("label.package.product")));
		subscriptionHistoryColumns.add(new ColumnModel("title",this.getProperty("label.title")));
		subscriptionHistoryColumns.add(new ColumnModel("isSubscribe",
				this.getProperty("title.subscribe")));
	}

	// ================= Getter Setters =======================//

	public List<ColumnModel> getSubscriptionColumns() {
		return subscriptionColumns;
	}

	public static void setSubscriptionColumns(
			List<ColumnModel> subscriptionColumns) {
		Customer360SubscribeBackingBean.subscriptionColumns = subscriptionColumns;
	}

	public List<ColumnModel> getSubscriptionHistoryColumns() {
		return subscriptionHistoryColumns;
	}

	public static void setSubscriptionHistoryColumns(
			List<ColumnModel> subscriptionHistoryColumns) {
		Customer360SubscribeBackingBean.subscriptionHistoryColumns = subscriptionHistoryColumns;
	}

	public DualListModel<SPackageORM> getPackages() {
		return packages;
	}

	public void setPackages(DualListModel<SPackageORM> packages) {
		this.packages = packages;
	}

	public Customer360SubscribeBackingBean() {
		session = BeanFactory.getInstance().getSessionBean();
	}

	@PostConstruct
	public void postConstruct() {
		if (getAction().equals("")) {
			// no action was called, load group data
			if (session.getUserModule_selectedUser() != 0L) {
				listPage();
				setViewAction();
			}
		}
	}

	@Override
	public String actionListener() {
		setCurrentAction(getAction(), this.getClass());
		switch (getCurrentAction()) {
		case WebConstants.ACTION_CREATE:
			// No need here
			break;
		case WebConstants.ACTION_VIEW:

			try {
				Long row_id = Long.valueOf(getParameter("row_id"));
				if(row_id == null || row_id < 1){
					throw new Exception("No row id provided.");
				}
				FacesContext
						.getCurrentInstance()
						.getExternalContext()
						.redirect(
								"/crm/view/sales/products/productList.jsf?row_id="
										+ row_id);
			} catch (IOException e) {
				addError("Error redirecting to Products Page");
			} catch (Exception e){
				addError("No product id provided.");
			}
			break;
		case WebConstants.ACTION_EDIT:
			formPage();
			break;
		case WebConstants.ACTION_UPDATE:
			actionUpdate();
			break;
		case WebConstants.ACTION_SAVE:
			actionSave();
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
	public List<SServiceSubscribeORM> getList() {
		System.out.println("Customer360CB->getSubscriptions()");
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();

		// Get packages from DB
		List<SServiceSubscribeORM> listDb = subscriptionDao.list(session
				.getCustomerModule_selectedContact());
		return listDb;
	}

	public List<SServiceSubscriptionHistoryORM> getHistoryList() {
		System.out.println("Customer360CB->getSubscriptionHistory()");
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();

		// Get packages from DB
		List<SServiceSubscriptionHistoryORM> listDb = historyDao.list(session
				.getCustomerModule_selectedContact());
		return listDb;
	}

	public void listPage() {
		this.changeTabPath(1,
				"/view/customer/contacts/c360view/subscribe/index.xhtml");
		setListAction(true);
	}

	public void formPage() {
		this.changeTabPath(1,
				"/view/customer/contacts/c360view/subscribe/subscribeForm.xhtml");

		List<SPackageORM> packagesSource = null;
		List<SPackageORM> packagesTarget = null;

		packagesSource = packagesDao.customerPackages(
				session.getCustomerModule_selectedContact(), false);
		packagesTarget = packagesDao.customerPackages(
				session.getCustomerModule_selectedContact(), true);
		packages = new DualListModel<SPackageORM>(packagesSource,
				packagesTarget);
		setListAction(true);
	}

	public void actionSave() {
		this.changeTabPath(1, "my path");
		setListAction(true);
	}

	public void actionUpdate() {

		// List<SPackageORM> packagesSource = null;
		List<SPackageORM> packagesTarget = null;
		List<SServiceSubscribeORM> subscriptions = new ArrayList<SServiceSubscribeORM>();

		// packagesSource = packages.getSource();
		packagesTarget = packages.getTarget();

		SServiceSubscribeORM tempSub = null;
		for (SPackageORM x : packagesTarget) {
			tempSub = new SServiceSubscribeORM();
			tempSub.setIsPackage(true);
			tempSub.setPackg(packagesDao.packageDetails(x.getId()));
			subscriptions.add(tempSub);
		}
		try {
			subscriptionDao.updates(
					session.getCustomerModule_selectedContact(), subscriptions);
			addMessage("Subscription Successfully Updated");
		} catch (Exception e) {
			// handle exception
			System.out.println("Couldn't create");
			addError("Package Updation Failed.");
		}
		setListAction(true);
		changeTabPath(1,
				"/view/customer/contacts/c360view/subscribe/index.xhtml");
	}

	private void changeTabPath(int index, String path) {
		InnerTabs d = session.getContactTabs().get(index);
		d.setPath(path);
		session.getContactTabs().set(index, d);
		session.setCustomerModule_contactTabIndex(1);
	}

}
