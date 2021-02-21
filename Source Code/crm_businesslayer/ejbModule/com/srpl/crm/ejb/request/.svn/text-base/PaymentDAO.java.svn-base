package com.srpl.crm.ejb.request;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.srpl.crm.ejb.entity.CsContactORM;
import com.srpl.crm.ejb.entity.PaymentORM;
import com.srpl.crm.ejb.entity.PaymentReimburse;
import com.srpl.crm.ejb.entity.SInvoiceORM;
import com.srpl.crm.ejb.exceptions.ContactNotFoundException;
/**
 * Session Bean implementation class PaymentDAO
 */
@Stateless
@LocalBean
public class PaymentDAO extends GenericDAO<PaymentORM>{
	@EJB ContactDAO contactDao;
	@EJB InvoiceDAO invoiceDao;
    /**
     * Default constructor. 
     */
    public PaymentDAO() {
        // TODO Auto-generated constructor stub
    	super(PaymentORM.class);
    }
    
    //================= List Payments =======================//
    public List<PaymentORM> list(Long subscriberId){
    	CsContactORM subscriber = null;
    	try {
			subscriber = contactDao.contactDetails(subscriberId);
		} catch (ContactNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	List<PaymentORM> payments = em.createQuery("SELECT p FROM PaymentORM WHERE subscriber = :subscriber", PaymentORM.class)
    									.setParameter("subscriber", subscriber)
    									.getResultList();
        return payments;  	
    }

    //================= Create payment =======================//
    //this one is not working
      public Long create(Long invoice_id, double paid_amount){
      	PaymentORM payment = new PaymentORM();
      	
      	//Fetch Invoice populate payment and save.
      	try{
      		SInvoiceORM invoice = invoiceDao.details(invoice_id);
      		
      		//populate payment
      		payment.setCompanyId(invoice.getCompanyId());
      		payment.setInvoiceAmount(invoice.getTotalAmount());
      		payment.setPaidAmount(paid_amount);
      		payment.setPaidOnDate(new Timestamp(new Date().getTime()));
      		payment.setRemainingAmount(invoice.getTotalAmount() - paid_amount);
      		invoice.setStatus(true);
      		payment.setSInvoice(invoice);
      		payment.setSubscriber(invoice.getSubscriber());
      		
      		save(payment);
      		return payment.getId();
      		
      	}catch (Exception e) {
  			System.out.println("Exception in payment creation.");
  		}
      	
      	return -1L;
      } 
      //================= Create payment =======================//
      public Long create(PaymentORM payment){
      	
      	save(payment);
      	try{
      		
      	}catch (Exception e) {
  			System.out.println("Exception in payment creation.");
  		}
      	
      	return payment.getId();
      }  

   //================= Update Company =======================//
    public Long updates(PaymentORM payment){
    	System.out.println(payment.getCompanyId());
    	update(payment);
    	return payment.getId();
    }

    //================= Delete Company =======================//
    public void delete(Long paymentId){
    	super.delete(paymentId);
    }
    

    //================= Details of Company =======================//
    public PaymentORM companyDetails(Long companyId){
    	PaymentORM payment = find(companyId);
    	return payment;
    }
    
    public boolean importPayments(List<PaymentORM> pmts, Long company) {
    	Iterator<PaymentORM> itr = pmts.iterator();
    	while(itr.hasNext()){
    		PaymentORM pmt = itr.next();   		    		   		
    		save(pmt);
    	}    	
		return true;		
    }
	
	public Boolean paymentIDExists(Long pmtId) /* throws UserNotFoundException */{
		List<PaymentORM> pmts = em.createQuery("from PaymentORM where id = :uid",PaymentORM.class).setParameter("uid", pmtId).getResultList();
		return (pmts.size() > 0);
	}
    
    public List<PaymentORM> getImportPayments(Timestamp stamp){
    	List<PaymentORM> cnt = null;
    	cnt = em.createQuery("from PaymentORM where paymentCreatedon = :stamp",PaymentORM.class).setParameter("stamp", stamp).getResultList();
    	return cnt;
    }
    
}
