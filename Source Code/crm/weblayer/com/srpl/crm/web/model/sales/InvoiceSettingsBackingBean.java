package com.srpl.crm.web.model.sales;

import java.io.Serializable;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.srpl.crm.web.controller.BeanFactory;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;
import com.srpl.crm.ejb.entity.CsContactORM;
import com.srpl.crm.ejb.entity.InvoiceSettingsORM;
import com.srpl.um.ejb.entity.UmCompany;
import com.srpl.crm.ejb.exceptions.ContactNotFoundException;
import com.srpl.um.ejb.request.CompanyDAO;
import com.srpl.crm.ejb.request.ContactDAO;
import com.srpl.crm.ejb.request.InvoiceSettingsDAO;
import com.srpl.crm.web.common.SessionDataBean;

@ManagedBean(name = "invoiceSettings")
@ViewScoped
public class InvoiceSettingsBackingBean extends JSFBeanSupport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long[] customerId;
	private CsContactORM customer;
	private Date invoiceDate;
	private Integer dueDays;
	private Integer invoiceDay;
	private Double latePaymentFee;
	
	private List<CsContactORM> customerList;
	
	@EJB ContactDAO contactDao;
	@EJB CompanyDAO companyDao;
	@EJB InvoiceSettingsDAO invoiceSettingsDao;
	
	public InvoiceSettingsBackingBean(){
		setCurrentAction(WebConstants.ACTION_SECURITY,this.getClass());
	}
	
	public Long[] getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long[] customerId) {
		this.customerId = customerId;
	}
	public CsContactORM getCustomer() {
		return customer;
	}
	public void setCustomer(CsContactORM customer) {
		this.customer = customer;
	}
	
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public Integer getDueDays() {
		return dueDays;
	}
	public void setDueDays(Integer dueDays) {
		this.dueDays = dueDays;
	}
	public Integer getInvoiceDay() {
		return invoiceDay;
	}

	public void setInvoiceDay(Integer invoiceDay) {
		this.invoiceDay = invoiceDay;
	}

	public Double getLatePaymentFee() {
		return latePaymentFee;
	}
	public void setLatePaymentFee(Double latePaymentFee) {
		this.latePaymentFee = latePaymentFee;
	}
	public ContactDAO getContactDao() {
		return contactDao;
	}
	public void setContactDao(ContactDAO contactDao) {
		this.contactDao = contactDao;
	}
	public List<CsContactORM> getCustomerList() {
		return customerList;
	}
	public void setCustomerList(List<CsContactORM> customerList) {
		this.customerList = customerList;
	}
	
	// this code is to be optimized
	public String createInvoiceSettings(){
		System.out.println("create invoice settings called");
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		System.out.println("company = "+session.getCompanyId());
		List<InvoiceSettingsORM> invoiceSettingsList = new ArrayList<InvoiceSettingsORM>();
  		invoiceSettingsList = invoiceSettingsDao.listInvoiceSettings();
  		
  		List<Long> createList = new ArrayList<Long>();
	    List<Long> updateList = new ArrayList<Long>();
	  
	    // set create and update the customize invoice settings
	    for(int i = 0; i<customerId.length; i++){
 		    try{
 	    	  Long l = customerId[i];
			  CsContactORM customer = contactDao.contactDetails(l);
			  InvoiceSettingsORM invoiceSettingsOrm = invoiceSettingsDao.getInvoiceSettingByCustomer(customer);
			  if(invoiceSettingsOrm.getCustomer()!= null){
				  if( (invoiceSettingsOrm.getCustomer().getContactId() != null) &&  (invoiceSettingsOrm.getCustomer().getContactId() == l) ){
					  updateList.add(l);
				  }
			  }else{
				  createList.add(l);   
			  } 
			  
 		    }catch(Exception e){
 		    	
 		    }
 	     }  
	   
	   // create customize settings
	   invoiceSettingsList = new ArrayList<InvoiceSettingsORM>();
       UmCompany company = companyDao.companyDetails(session.getCompanyId());
       
  	   Date date = new Date();
  	   for(int i = 0; i<createList.size(); i++){
  		   try{
  		      Long l = createList.get(i);
  		      CsContactORM customer = contactDao.contactDetails(l);
  		      InvoiceSettingsORM invoiceSettingsOrm = new InvoiceSettingsORM(customer, invoiceDate, invoiceDay, dueDays, latePaymentFee, new Timestamp(date.getTime()), null, company);
			  invoiceSettingsList.add(invoiceSettingsOrm);
  		   }catch(ContactNotFoundException e){
  			   
  		   }catch(Exception e){
  			   
  		   }
  	   }
  	   invoiceSettingsDao.createInvoiceSettingsByList(invoiceSettingsList);
  	   // update customize settings
	   invoiceSettingsList = new ArrayList<InvoiceSettingsORM>();
  	   for(int i = 0; i<updateList.size(); i++){
  		 try{
 		      Long l = updateList.get(i);
 		      CsContactORM customer = contactDao.contactDetails(l);
 		      InvoiceSettingsORM invoiceSettingsOrmOld = invoiceSettingsDao.getInvoiceSettingByCustomer(customer);
 		      InvoiceSettingsORM invoiceSettingsOrm = new InvoiceSettingsORM(invoiceSettingsOrmOld.getInvoiceSettingId(), customer, invoiceDate, invoiceDay, dueDays, latePaymentFee, invoiceSettingsOrmOld.getInvoiceSettingCreatedDate(), new Timestamp(date.getTime()), company);
			  invoiceSettingsList.add(invoiceSettingsOrm);
 		   }catch(ContactNotFoundException e){
 			   
 		   }catch(Exception e){
 			   
 		   }
	   }
  	   invoiceSettingsDao.updateInvoiceSettingByList(invoiceSettingsList);
  	   addMessage(getProperty("message.order.invoice.setting.configure"));
  	   return "";
	}
	
	public List<CsContactORM> listCustomers(){
		try{
		BeanFactory beanFactory = BeanFactory.getInstance();	
		SessionDataBean session = beanFactory.getSessionBean();
		customerList = contactDao.listContacts(session.getCompanyId());
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return customerList;
	}
}
