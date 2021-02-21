package com.srpl.crm.web.model.customer.customer360;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import com.bitguiders.util.jsf.JSFBeanInterface;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;
import com.srpl.crm.ejb.entity.CsContactORM;
import com.srpl.crm.ejb.entity.CustomerProvisioningORM;
import com.srpl.crm.ejb.request.ContactDAO;
import com.srpl.crm.ejb.request.CustomerProvisioningDAO;
import com.srpl.crm.web.common.SessionDataBean;
import com.srpl.crm.web.controller.BeanFactory;
import com.srpl.crm.web.model.common.ColumnModel;

@ManagedBean(name = "customer360Provisioning")
public class Customer360ProvisioningBackingBean extends JSFBeanSupport implements JSFBeanInterface {
	private static List<ColumnModel> columns;
	
	@EJB CustomerProvisioningDAO provisioningDAO;
	@EJB ContactDAO contactDAO;
	{
		columns = new ArrayList<ColumnModel>();
		columns.add(new ColumnModel("customerProvisiongId", this.getParameter("label.id")));
		columns.add(new ColumnModel("productTitle", this.getProperty("title.products")));
		columns.add(new ColumnModel("productMACAddress", this.getProperty("label.product.mac.address")));
		columns.add(new ColumnModel("host", this.getProperty("label.host")));
		columns.add(new ColumnModel("port", this.getProperty("label.port")));
		columns.add(new ColumnModel("loginName", this.getProperty("label.login.name")));
		columns.add(new ColumnModel("password", this.getProperty("label.password")));
	}	
	
	
	public List<ColumnModel> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnModel> columns) {
		Customer360ProvisioningBackingBean.columns = columns;
	}
	
	@Override
	public List<CustomerProvisioningORM> getList() {
		SessionDataBean sessionBean = BeanFactory.getInstance().getSessionBean();
		CsContactORM customer = new CsContactORM();
		try {
			customer = contactDAO.contactDetails(sessionBean.getCustomerModule_selectedContact());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<CustomerProvisioningORM> list = provisioningDAO.listProvisioningByCustomer(customer);
		return list;
	}
	
	@Override
	public String actionListener() {
		//System.out.println("Provisioning action listener called");
		setCurrentAction(getAction(), this.getClass());
		switch(getCurrentAction()){
		case WebConstants.ACTION_VIEW:
			BeanFactory bf = BeanFactory.getInstance();
			Long id = Long.parseLong(getParameter("row_id"));
			bf.getSessionBean().setSalesModule_selectedOrder(id);
			//System.out.println("view action of provisioning called..............................");
			//bf.getSessionBean().getOrderTabs().get(0).setPath("/view/sales/orders/orderForm.xhtml");
			return "orderdetail";
			
		}	
		//System.out.println("------------------------------====");
		return "orderdetail";
	}	

}
