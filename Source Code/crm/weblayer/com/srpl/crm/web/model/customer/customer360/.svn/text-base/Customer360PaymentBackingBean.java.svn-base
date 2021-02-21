package com.srpl.crm.web.model.customer.customer360;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import com.bitguiders.util.jsf.JSFBeanInterface;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.srpl.crm.ejb.entity.CsContactORM;
import com.srpl.crm.ejb.entity.PaymentORM;
import com.srpl.crm.ejb.entity.SInvoiceORM;
import com.srpl.crm.ejb.request.InvoiceDAO;
import com.srpl.crm.ejb.request.PaymentDAO;
import com.srpl.crm.web.common.InnerTabs;
import com.srpl.crm.web.common.SessionDataBean;
import com.srpl.crm.web.controller.BeanFactory;
import com.srpl.crm.web.model.PaymentBackingBean;
import com.srpl.crm.web.model.common.ColumnModel;

@ManagedBean(name="customer360Payment")
public class Customer360PaymentBackingBean extends JSFBeanSupport implements JSFBeanInterface {
	
	@EJB
	PaymentDAO paymentDao;
	@EJB
	InvoiceDAO invoiceDao;
	
	private SessionDataBean session;
	
	private Long paymentId;
	private Long companyId;
	private double invoiceAmount;
	private double paidAmount;
	private Timestamp paidOnDate;
	private double remainingAmount;
	private CsContactORM subscriber;
	private SInvoiceORM SInvoice;
	private Long invoiceId;
	
	private static List<ColumnModel> paymentColumns;
	private List<SInvoiceORM> unpaidInvoiceList;

	static {

		paymentColumns = new ArrayList<ColumnModel>();
		paymentColumns.add(new ColumnModel("id", "ID"));
		paymentColumns.add(new ColumnModel("title", "TITLE"));
		paymentColumns.add(new ColumnModel("issueDate", "ISSUE DATE"));
		paymentColumns.add(new ColumnModel("amount", "AMOUNT"));
	}

	/**
	 * 
	 * Getters and Setters
	 */
	
	public Long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Long id) {
		this.paymentId = id;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public double getInvoiceAmount() {
		return invoiceAmount;
	}
	public void setInvoiceAmount(double invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}
	public double getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}
	public Timestamp getPaidOnDate() {
		return paidOnDate;
	}
	public void setPaidOnDate(Timestamp paidOnDate) {
		this.paidOnDate = paidOnDate;
	}
	public double getRemainingAmount() {
		return remainingAmount;
	}
	public void setRemainingAmount(double remainingAmount) {
		this.remainingAmount = remainingAmount;
	}
	public CsContactORM getSubscriber() {
		return subscriber;
	}
	public void setSubscriber(CsContactORM subscriber) {
		this.subscriber = subscriber;
	}
	public SInvoiceORM getSInvoice() {
		return SInvoice;
	}
	public void setSInvoice(SInvoiceORM sInvoice) {
		SInvoice = sInvoice;
	}	
	public Long getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}
	
	public Customer360PaymentBackingBean(){
		session = BeanFactory.getInstance().getSessionBean();
	}

	@PostConstruct
	public void postConstruct() {
		if (getAction().equals("")) {
			// no action was called, load group data
			if(session.getUserModule_selectedUser() != 0L){
				listPage();
				setViewAction();
			}
		}
	}

	public List<ColumnModel> getPaymentColumns() {
		return paymentColumns;
	}

	public void setPaymentColumns(List<ColumnModel> paymentColumns) {
		Customer360PaymentBackingBean.paymentColumns = paymentColumns;
	}

	public List<SInvoiceORM> getUnpaidInvoiceList() {
		try {
			SessionDataBean session = BeanFactory.getInstance()
					.getSessionBean();
			List<SInvoiceORM> list = invoiceDao.listWithPaidCheck(
					session.getCustomerModule_selectedContact(), true);
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
	
	@Override
	public String actionListener() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PaymentORM> getList() {
		System.out.println("Customer360CB->getPayments()");

		// Get payments from DB
		List<PaymentORM> listDb = paymentDao
				.list(session.getCustomerModule_selectedContact());
		return listDb;
	}
	
	public void listPage(){
		this.changeTabPath(3, "my path");
		setListAction(true);
	}

	private void changeTabPath(int index, String path) {
		InnerTabs d = session.getContactTabs().get(index);
		d.setPath(path);
		session.getContactTabs().set(index, d);
		session.setCustomerModule_contactTabIndex(3);
	}
	
}
