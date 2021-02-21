package com.srpl.crm.ejb.request;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import com.srpl.crm.ejb.entity.CsContactORM;
import com.srpl.crm.ejb.entity.OrderORM;
import com.srpl.crm.ejb.entity.ProductORM;
import com.srpl.crm.ejb.entity.SalesOpportunityORM;
import com.srpl.um.ejb.entity.UmCompany;
import com.srpl.um.ejb.entity.UmUser;
import com.srpl.crm.ejb.exceptions.ContactNotFoundException;
import com.srpl.crm.ejb.exceptions.OrderNotFoundException;
import com.srpl.um.ejb.exceptions.UserNotFoundException;
import com.srpl.um.ejb.request.UserDAO;

/**
 * Session Bean implementation class OrderDAO
 */
@Stateless
@LocalBean
public class OrderDAO extends GenericDAO<OrderORM>{

	@EJB ContactDAO customerDao;
	@EJB
	UserDAO userDao;
    /**
     * Default constructor. 
     */
    public OrderDAO() {
        // TODO Auto-generated constructor stub
    	super(OrderORM.class);
    }

    public List<OrderORM> listOrders(){
    	List<OrderORM> ordersList = findAll();
    	return ordersList;
    }
    
    public List<OrderORM> listOrders(UmCompany umCompany)throws Exception{
    	List<OrderORM> ordersList = em.createQuery("from OrderORM where umCompany = :umCompany",OrderORM.class).
    			setParameter("umCompany", umCompany).getResultList();
    	return ordersList;
    }
    
    public List<OrderORM> listCustomerOrders(CsContactORM customer){
    	List<OrderORM> ordersList = new ArrayList<OrderORM>();
    	try{
    	  ordersList = em.createQuery("from OrderORM where customer = :customer", OrderORM.class).setParameter("customer", customer).getResultList();
    	}catch(Exception e){
    		  
    	}
    	return ordersList;
    	
    }
    public List<OrderORM> listOrdersAndDetails(){
    	List<OrderORM> orderAndDetailList;
    	orderAndDetailList = em.createQuery("SELECT o, od FROM OrderORM o, OrderDetailORM od WHERE od MEMBER OF o").getResultList();
    	return orderAndDetailList;
    	
    }

    
    
    //order of specific customer
    public List<OrderORM> listOrders(Long cId){
    	CsContactORM customer;
    	List<OrderORM> ordersList = null;
		try {
			customer = customerDao.contactDetails(cId);
			ordersList = em.createQuery("SELECT o FROM OrderORM o WHERE o.customer = :cId", OrderORM.class)
	    			.setParameter("cId", customer)
	    			.getResultList();
		} catch (ContactNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return ordersList;
    }
    
    public Long createOrder(OrderORM order){
    	save(order);
    	return order.getOrderId();
    }
    
    public void updateOrder(OrderORM order){
    	update(order);
    }
    
    public void deleteOrder(Long orderId){
    	delete(orderId);
    }
    
    public OrderORM retrieveOrder(Long orderId){
    	OrderORM order;
    	order = find(orderId);
    	return order;
    }
    
    public void deleteOrderOpportunity(SalesOpportunityORM opportunity){
    	try{
    	System.out.println("opportunity id ="+ opportunity.getOpportunityId());	
    	List<OrderORM> orders = em.createQuery("from OrderORM where opportunity = :opportunity",OrderORM.class).setParameter("opportunity",opportunity).getResultList();
    	System.out.println("OrderDAO order id = "+orders.get(0).getOrderId());	
    	delete(orders.get(0).getOrderId());
    	//em.createQuery("Delete from OrderORM where OrderORM.opportunity =: opportunity", OrderORM.class).setParameter("opportunity", opportunity);
    	
    	}catch(Exception e){
            System.out.println(e.getMessage());    		
    	}
    }
    
    public OrderORM retrieveOrderOfOpportunity(SalesOpportunityORM opportunity){
    	  OrderORM order = new OrderORM(); 
    	  List<OrderORM> orders = em.createQuery("from OrderORM where opportunity = :opportunity",OrderORM.class).setParameter("opportunity",opportunity).getResultList();
    	  if(orders.size() > 0){
    		  order = orders.get(0);
    	  }
          return order;
    	
    }
    
    public void deleteOrderByOrderList(List<OrderORM> listOfOrders){
    	for(OrderORM order:listOfOrders){
    		delete(order.getOrderId());
    	}
    }  
    public boolean importOrders(List<OrderORM> pmts, Long company) {
    	Iterator<OrderORM> itr = pmts.iterator();
    	while(itr.hasNext()){
    		OrderORM pmt = itr.next();   		    		   		
    		save(pmt);
    	}    	
		return true;		
    }
	
	public Boolean orderIDExists(Long pmtId) /* throws UserNotFoundException */{
		List<OrderORM> pmts = em.createQuery("from OrderORM where orderId = :uid",OrderORM.class).setParameter("uid", pmtId).getResultList();
		return (pmts.size() > 0);
	}
    
    public List<OrderORM> getImportOrders(Timestamp stamp){
    	List<OrderORM> cnt = null;
    	cnt = em.createQuery("from OrderORM where orderAddedon = :stamp",OrderORM.class).setParameter("stamp", stamp).getResultList();
    	return cnt;
    }
    
    public int fetchSalesPerformance(Long userId)
    {
    	Calendar now = Calendar.getInstance();  
    	int curMonth=now.get(Calendar.MONTH)+1;
    	int curYear=now.get(Calendar.YEAR);
    	UmUser user = null;
    	try
    	{
    		user=userDao.umUserDetails(userId);
    	}
    	catch(UserNotFoundException ex)
    	{

    	}
    	
    	Query query=em.createQuery("select count(ord.orderId) from OrderORM ord where EXTRACT(MONTH FROM ord.orderDate) = :month and EXTRACT(YEAR FROM ord.orderDate) = :year and createdBy = :userId");
        query.setParameter("month",curMonth);
        query.setParameter("year",curYear);
        query.setParameter("userId",user);
        Long weekSales = (Long) query.getSingleResult();
        return weekSales.intValue();

    }
    
    public int[] fetchAnnualSales(Long userId)
    {
    	Calendar now = Calendar.getInstance();  
    	int curMonth=now.get(Calendar.MONTH)+1;
    	int curYear=now.get(Calendar.YEAR);
    	int monthSales[]=new int[12];
    	
    	
    	Query query=em.createNativeQuery("select EXTRACT(MONTH FROM orders.order_Date), count(EXTRACT(MONTH FROM orders.order_Date)) from crm.orders where EXTRACT(YEAR FROM orders.order_Date) = :year and created_by = :userId GROUP BY EXTRACT(MONTH FROM orders.order_Date) ");
        query.setParameter("year",curYear);
        query.setParameter("userId",userId);
    	List<Object[]> list= query.getResultList();
    	for(int i=0;i<list.size();i++)
    	{
    		Object[] row=list.get(i);
    		int month=((Double)row[0]).intValue()-1;
    		int sale=((BigInteger)row[1]).intValue();
    		monthSales[month]=sale;
    	}
        return monthSales;
  }
    
}
