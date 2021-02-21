package com.srpl.crm.web.model.sales;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;
import com.srpl.crm.web.controller.BeanFactory;
import com.srpl.crm.ejb.entity.BillORM;
import com.srpl.crm.ejb.entity.OrderDetailORM;
import com.srpl.crm.ejb.entity.OrderORM;
import com.srpl.um.ejb.entity.UmUser;
import com.srpl.crm.ejb.exceptions.BillReimbursementNotFoundException;
import com.srpl.um.ejb.exceptions.UserNotFoundException;
import com.srpl.crm.ejb.request.BillDAO;
import com.srpl.crm.ejb.request.OrderDAO;
import com.srpl.crm.ejb.request.OrderDetailDAO;
import com.srpl.crm.web.common.SessionDataBean;
import com.srpl.crm.web.model.common.ColumnModel;
import com.srpl.um.ejb.request.UserDAO;

@ManagedBean(name = "billBean")
@ViewScoped
public class BillBackingBean extends JSFBeanSupport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OrderORM order;
	private OrderDetailORM orderDetail;
	private List<UmUser> userList;
	private BillORM bill;
/*	private Long orderId;
	private Long totalAmount;
	private Long paidAmount;
	private Long remainingAmount;
	private Long receivedBy;
	private Long reimburseBillAmount;*/
	private List<ColumnModel> columns;
	SessionDataBean session;

	@EJB
	OrderDAO orderDao;
	@EJB
	BillDAO billDao;
	@EJB
	UserDAO userDao;
	@EJB
	OrderDetailDAO orderDetailDao;

	public BillBackingBean() {
		setCurrentAction(getParameter("action"),this.getClass());
		columns = new ArrayList<ColumnModel>();
		columns.add(new ColumnModel("orderId", "ID"));
		columns.add(new ColumnModel("customerName", "CUSTOMER"));
		columns.add(new ColumnModel("dateString", "ORDER DATE"));
		columns.add(new ColumnModel("totalOrderAmmount", "ORDER Amount"));
		columns.add(new ColumnModel("orderCreation", "ORDER CREATION"));
		
		session=BeanFactory.getInstance().getSessionBean();
		setCurrentAction(WebConstants.ACTION_SECURITY,this.getClass());
	}

	@PostConstruct
	public void onload() throws BillReimbursementNotFoundException {
		//Get row_id from request param
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String rowIdParam=params.get("row_id");
		if(getParameter("action").equals(WebConstants.ACTION_SAVE))
		{
			setCurrentAction(WebConstants.ACTION_CREATE,this.getClass());
		} 
		if (rowIdParam != null) 
		{
				Long rowId = Long.parseLong(rowIdParam);
				order = orderDao.retrieveOrder(rowId);
				orderDetail = orderDetailDao.retrieveSingleOrderDetail(order);
				bill = billDao.reimburseDetails(rowId);
				if (bill == null) {
					bill = new BillORM();
					bill.setOrderId(rowId);
					bill.setCompanyId(session.getCompanyId());
				}
		}
	}
	
	public String actionListener() {
		setCurrentAction(getParameter("action"),this.getClass());
		System.out.println("Bill ReImbursement actionListner called");
		System.out.println("getAction = " + getAction());
		switch (getCurrentAction())
		{
		  case WebConstants.ACTION_VIEW:
			  return WebConstants.ACTION_CRUD;
		  case WebConstants.ACTION_CREATE:
			  return WebConstants.ACTION_CRUD;
		  case WebConstants.ACTION_SAVE:
			  reimburseAmount();
			  return WebConstants.ACTION_LIST;
		  case WebConstants.ACTION_CANCEL:
				return WebConstants.ACTION_LIST;
		  }
		  return(null);

	} // end actionListner

	
	public boolean reimburseAmount() 
	{
		System.out.println("reimburseBillAmount()");
		System.out.println("Row_id="+getParameter("row_id"));
		//Long row_Id = Long.parseLong(this.getParameter("row_id"));
		try
		{
			billDao.reimburse(bill);
		}
		catch(BillReimbursementNotFoundException e)
		{
			 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,getProperty("message.sales.billreimbursement.failed"),null));
			 e.printStackTrace();
		}
		return true;		
	}
	
	public List<UmUser> listUsers() throws UserNotFoundException {
		System.out.println("listuser called");
		try {
			userList = userDao.listAllUsers();

		} catch (UserNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return userList;
	}
	
	public List<OrderORM> listOrders() {
		List<OrderORM> orderList = orderDao.listOrders();
		System.out.println("Order List size = " + orderList.size());
		return orderList;
	}

	// GETTER & SETTERS
	public List<UmUser> getUserList() {
		return userList;
	}

	public OrderORM getOrder() {
		return order;
	}

	public OrderDetailORM getOrderDetail() {
		return orderDetail;
	}

	public BillORM getBill() {
		return bill;
	}

	public List<ColumnModel> getColumns() {
		return columns;
	}

	


}
