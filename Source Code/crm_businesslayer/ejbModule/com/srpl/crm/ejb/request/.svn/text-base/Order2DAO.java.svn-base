package com.srpl.crm.ejb.request;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import com.srpl.crm.ejb.entity.CsContactORM;
import com.srpl.crm.ejb.entity.OrderORM;
import com.srpl.crm.ejb.entity.SalesOpportunityORM;
import com.srpl.um.ejb.entity.UmUser;
import com.srpl.crm.ejb.exceptions.OrderNotFoundException;
import com.srpl.um.ejb.exceptions.UserNotFoundException;
import com.srpl.um.ejb.request.UserDAO;

/**
 * Session Bean implementation class OrderDAO
 */
@Stateless
@LocalBean
public class Order2DAO extends GenericDAO<OrderORM>{

	@EJB ContactDAO contactDao;
	
	@EJB
	UserDAO userDao;
	
    /**
     * Default constructor. 
     */
    public Order2DAO() {
        // TODO Auto-generated constructor stub
    	super(OrderORM.class);
    }

    public List<OrderORM> listOrders() throws OrderNotFoundException{
    	List<OrderORM> ordersList = findAll();
    	if(ordersList.size() == 0){
    		throw new OrderNotFoundException("No Order Record Found");
    	}
    	return ordersList;
    }

    //Orders of specific customer (Hammad Hassan Khan)
    public List<OrderORM> listOrders(Long customerId){
    	List<OrderORM> ordersList = null;
    	CsContactORM customer = null;
    	try{
	    	customer = contactDao.contactDetails(customerId);
	    	ordersList = em.createQuery("SELECT o FROM OrderORM o WHERE o.customer = :c",OrderORM.class).setParameter("c", customer).getResultList();
    	}catch (Exception e) {
			System.out.println("exception while fetching orders");
		}
    	return ordersList;
    }
    
    public Long createOrder(OrderORM order){
    	save(order);
    	return order.getOrderId();
    }
    
    public void deleteOrder(Long orderId){
    	delete(orderId);
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
          List<OrderORM> orders = em.createQuery("from OrderORM where opportunity = :opportunity",OrderORM.class).setParameter("opportunity",opportunity).getResultList();
          return orders.get(0);
    	
    }
    
    public OrderORM retrieveOrder(Long orderId){
    	OrderORM order;
    	order = find(orderId);
    	return order;
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
    	
//        List<OrderORM> orders = em.createQuery("select ord from OrderORM ord where orderDate = :todayDate",OrderORM.class).setParameter("todayDate",year).getResultList();
        //List<OrderORM> orders = 
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
    	
//        List<OrderORM> orders = em.createQuery("select ord from OrderORM ord where orderDate = :todayDate",OrderORM.class).setParameter("todayDate",year).getResultList();
        //List<OrderORM> orders = 
    	//select EXTRACT(YEAR FROM orders.order_Date) , EXTRACT(MONTH FROM orders.order_Date), count(EXTRACT(MONTH FROM orders.order_Date)) from orders GROUP BY EXTRACT(MONTH FROM orders.order_Date),EXTRACT(YEAR FROM orders.order_Date)
    	//EXTRACT(MONTH FROM ord.orderDate), count(EXTRACT(MONTH FROM ord.orderDate))
    	
    	Query query=em.createNativeQuery("select EXTRACT(MONTH FROM orders.order_Date), count(EXTRACT(MONTH FROM orders.order_Date)) from orders where EXTRACT(YEAR FROM orders.order_Date) = :year and created_by = :userId GROUP BY EXTRACT(MONTH FROM orders.order_Date) ");
 //       query.setParameter("month",curMonth);
        query.setParameter("year",curYear);
        query.setParameter("userId",userId);
    	List<Object[]> list= query.getResultList();
    	for(int i=0;i<list.size();i++)
    	{
    		Object[] row=list.get(i);
    		int month=((Double)row[0]).intValue();
    		int sale=((BigInteger)row[1]).intValue();
    		monthSales[month]=sale;
    	}
        return monthSales;
        

  }
    
}
