package com.srpl.crm.ejb.request;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.srpl.crm.ejb.entity.OrderDetailORM;
import com.srpl.crm.ejb.entity.OrderORM;
import com.srpl.crm.ejb.entity.ProductORM;
import com.srpl.crm.ejb.entity.SupportCaseHistoryORM;
import com.srpl.crm.ejb.entity.SupportCaseORM;

/**
 * Session Bean implementation class OrderDetailDAO
 */
@Stateless
@LocalBean
public class OrderDetailDAO extends GenericDAO<OrderDetailORM>{

    /**
     * Default constructor. 
     */
    public OrderDetailDAO() {
        // TODO Auto-generated constructor stub
    	super(OrderDetailORM.class);
    }
    
    public Long createOrderDetail(OrderDetailORM orderDetail){
    	save(orderDetail);
    	return orderDetail.getOrderDetailId();
    }
    
    public void updateOrderDetail(OrderDetailORM orderDetail){
    	update(orderDetail);
    }
    
    public void deleteOrderDetail(OrderORM order){
    	try{
    		System.out.println("deleteOrderDetail called orderId = "+order.getOrderId());
    	   int recordsDeleted = em.createQuery("delete OrderDetailORM orderDetail where orderDetail.order = :order").setParameter("order",order).executeUpdate();
    	   System.out.println("records deleted = "+recordsDeleted);
    	}catch(Exception e){
    		System.out.println("Exception Occured..");
    		System.out.println(e.getMessage());
    	}
    }
    
    public void deleteOrderDetail(OrderORM order, List<Long> detailIds){
    	try{
    		System.out.println("deleteOrderDetail called orderId = "+order.getOrderId());
    	   int recordsDeleted = em.createQuery("delete OrderDetailORM orderDetail where orderDetail.order = :order and orderDetail.orderDetailId not in(:detailIds)").setParameter("order",order).setParameter("detailIds", detailIds).executeUpdate();
    	   System.out.println("records deleted = "+recordsDeleted);
    	}catch(Exception e){
    		System.out.println("Exception Occured..");
    		System.out.println(e.getMessage());
    	}
    }
 
    public void deleteOrderDetail(Long orderDetailId){
    	delete(orderDetailId);
    }
    
    public List<OrderDetailORM> listOrderProducts(OrderORM order){
       List<OrderDetailORM> orderProductsList;
       orderProductsList = em.createQuery("from OrderDetailORM where order =:order", OrderDetailORM.class).setParameter("order",order).getResultList();
       return orderProductsList;	
    }
    
    public List<ProductORM> getProductsByOrder(OrderORM order){
    	List<ProductORM> productList = new ArrayList<ProductORM>();
    	List<OrderDetailORM> orderDetailList = em.createQuery("from OrderDetailORM where order =:order", OrderDetailORM.class).setParameter("order",order).getResultList();
    	for(OrderDetailORM orderDetail : orderDetailList){
    		productList.add(orderDetail.getProduct());
    	}
    	return productList;
    }
    
    public OrderDetailORM retrieveSingleOrderDetail(OrderORM order){
    	OrderDetailORM orderDetail;
    	orderDetail = em.createQuery("from OrderDetailORM where order =:order", OrderDetailORM.class).setParameter("order",order).getSingleResult();
    	return orderDetail;
    	
    }
    
    public List<OrderDetailORM> retrieveOrderDetailByProduct(ProductORM product){
    	List<OrderDetailORM> orderDetailList = new ArrayList<OrderDetailORM>();
    	orderDetailList = em.createQuery("from OrderDetailORM where product =:product", OrderDetailORM.class).setParameter("product",product).getResultList();
    	return orderDetailList;
    }
    
    public void deleteOrderDetailByProduct(ProductORM product){
    	try{
    	   int recordsDeleted = em.createQuery("delete OrderDetailORM orderDetail where orderDetail.product = :product").setParameter("product",product).executeUpdate();
    	}catch(Exception e){
    		System.out.println("Exception Occured..");
    		System.out.println(e.getMessage());
    	}
    }
    
    public List<OrderORM> retrieveOrderByProduct(ProductORM product){
    	List<OrderORM> orderList = new ArrayList<OrderORM>();
    	orderList = em.createQuery("select distinct orderDetailOrm.order from OrderDetailORM orderDetailOrm where orderDetailOrm.product =:product", OrderORM.class).setParameter("product",product).getResultList();
    	return orderList;
    	
    }
    
    public List<OrderDetailORM> orderDetailsByOrder(OrderORM order){
    	List<OrderDetailORM> orderDetails = new ArrayList<OrderDetailORM>();
    	orderDetails = em.createQuery("from OrderDetailORM where order =:order order by orderDetailId", OrderDetailORM.class).setParameter("order", order).getResultList();
    	return orderDetails;
    }
    
    public OrderDetailORM retrieveSingleOrderDetail1(List<OrderORM> order){
    	OrderDetailORM orderDetail;
    	orderDetail = em.createQuery("from OrderDetailORM where order =:order", OrderDetailORM.class).setParameter("order",order).getSingleResult();
    	return orderDetail;
    	
    }
    
}
