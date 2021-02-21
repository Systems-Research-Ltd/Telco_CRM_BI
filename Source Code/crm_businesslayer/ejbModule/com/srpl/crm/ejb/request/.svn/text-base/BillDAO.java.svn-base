package com.srpl.crm.ejb.request;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.srpl.crm.ejb.entity.BillORM;
import com.srpl.crm.ejb.entity.OrderDetailORM;
import com.srpl.crm.ejb.entity.OrderORM;
import com.srpl.crm.ejb.exceptions.BillReimbursementNotFoundException;

@Stateless
@LocalBean
public class BillDAO extends GenericDAO<BillORM>{
	 /**
     * Default constructor. 
     */
	@EJB OrderDAO orderDao;
	@EJB OrderDetailDAO orderDetailsDao;
	public BillDAO(){
		// TODO Auto-generated constructor stub
    	super(BillORM.class); 
	}
	
/*	public List<BillORM> listOfBillReimbursement()throws BillReimbursementNotFoundException
	    {
	    	System.out.println("ListOfBillReimbursement called"); 
	    	List<BillORM> billReimburseList = findAll();
	    	
	    	System.out.println("billreimburseDao test");
	    	if(billReimburseList.size()==0)
	    	{
	    		throw new BillReimbursementNotFoundException("Not Found");
	    	}
	    		
	    	return billReimburseList;
	    }*/
	
	private Long create(BillORM reimburseamount){
    	save(reimburseamount);
    	return reimburseamount.getOrderId();
    }
	
	 public BillORM reimburseDetails(Long orderId) throws BillReimbursementNotFoundException {
	    	BillORM bill =null;
	    	if(orderId == null){
	    	
	            throw new BillReimbursementNotFoundException("invalid id");
	        }  
	    	bill = find(orderId);
	    	return bill;
	    }
	 
	/* public BillORM reimburseAmountDetailsByOrder(Long orderId) throws BillReimbursementNotFoundException {
	    	List<BillORM> bill =null;
	    	OrderORM order =em.find(OrderORM.class,orderId);
	    	System.out.println("orderId in reimburse Doa is "+ order);
	    	if (orderId == null) {
	    		 throw new BillReimbursementNotFoundException("Invalid order Id");
	        }  
	    	bill = em.createQuery("SELECT ord From BillORM ord WHERE ord.order =:orid",BillORM.class).setParameter("orid", order).getResultList();
	    	if(bill.size()==0)
	    		return null;
	    	return bill.get(0);
	    }*/
	
	 private Long updateOrderBillReimburse(BillORM orderreimburse){
	       	BillORM obr =null;
	       	
	    	update(orderreimburse);
	    	return orderreimburse.getOrderId();
	 }
	 
	 public boolean reimburse(BillORM bill) throws BillReimbursementNotFoundException
	 {
		 BillORM billexist=reimburseDetails(bill.getOrderId());
		 // bill doesnot exist SAVE else UPDATE row
		 if(billexist==null)
		 {
			 save(bill);
		 }
		 else
		 {
			 update(bill);
		 }
		 //update OrderDetails
		 OrderORM order=orderDao.retrieveOrder(bill.getOrderId());
		 if(order==null)
			 throw  new BillReimbursementNotFoundException("Invalid Order ID");
		 OrderDetailORM orderDetail=orderDetailsDao.retrieveSingleOrderDetail(order);
		 if(orderDetail==null)
			 throw  new BillReimbursementNotFoundException("Invalid Order Details");
		 if(orderDetail.getPaidAmount()>=bill.getReimburseAmount())
		 {
			 orderDetail.setPaidAmount(orderDetail.getPaidAmount()-bill.getReimburseAmount());
			 orderDetailsDao.updateOrderDetail(orderDetail);
		 }
		 else
		 {
			 throw  new BillReimbursementNotFoundException("Invalid Reimburse Amount");
		 }
		 
		 
		return true;
	 }
	 
}
