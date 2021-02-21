package com.srpl.crm.web.model;

/**
 * @author Hammad Hassan Khan
 *
 */
import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;

import com.srpl.crm.web.common.InnerTabs;

@ManagedBean(name="customer360")

public class Customer360ViewBackingBean implements Serializable {

	/**
	 * Delete this
	 */
	private static final long serialVersionUID = 1L;
	private static ArrayList<InnerTabs> tabs = new ArrayList<InnerTabs>();
	private int tabIndex = 0;

	static {

		tabs.add(new InnerTabs("Details",
				"/view/customer/contacts/c360view/details/index.xhtml",
				"detailsContainer"));

		tabs.add(new InnerTabs("Subscribe",
				"/view/customer/contacts/c360view/subscribe/index.xhtml",
				"subscribeContainer"));

		tabs.add(new InnerTabs("Invoice",
				"/view/customer/contacts/c360view/billing/index.xhtml",
				"billingContainer"));

		tabs.add(new InnerTabs("Payment History",
				"/view/customer/contacts/c360view/paymentHistory/index.xhtml",
				"payHisContainer"));

		tabs.add(new InnerTabs("Pay Now",
				"/view/customer/contacts/c360view/payNow/index.xhtml",
				"payNowContainer"));

		tabs.add(new InnerTabs("Orders",
				"/view/customer/contacts/c360view/orders/index.xhtml",
				"ordersContainer"));

		tabs.add(new InnerTabs("Support",
				"/view/customer/contacts/c360view/support/index.xhtml",
				"supportContainer"));
		
		tabs.add(new InnerTabs("Billing",
				"/view/customer/contacts/c360view/billing/customerBill.xhtml",
				"billContainer"));

	}

	public int getTabIndex() {
		return tabIndex;
	}

	public void setTabIndex(int tabIndex) {
		this.tabIndex = tabIndex;
	}

	public ArrayList<InnerTabs> getTabs() {
		return tabs;
	}

	public void setTabs(ArrayList<InnerTabs> tabs) {
		Customer360ViewBackingBean.tabs = tabs;
	}

}
