package com.srpl.crm.ejb.request;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.srpl.crm.ejb.entity.CsContactORM;

import com.srpl.crm.ejb.entity.CustomerProvisioningORM;
import com.srpl.crm.ejb.entity.OrderORM;

import com.srpl.crm.ejb.entity.ProductORM;

@Stateless
@LocalBean
public class CustomerProvisioningDAO extends
		GenericDAO<CustomerProvisioningORM> {

	public CustomerProvisioningDAO() {
		super(CustomerProvisioningORM.class);
	}

	public List<CustomerProvisioningORM> listProvisioningByCustomer(
			CsContactORM customer) {
		List<CustomerProvisioningORM> p;
		p = em.createQuery(
				"FROM CustomerProvisioningORM where customer = :customer",
				CustomerProvisioningORM.class)
				.setParameter("customer", customer).getResultList();
		// System.out.println("Product title is -----------------------------------"+p.get(0).getProduct().getProductTitle());
		return p;
	}

	public Long saveProvisioning(CustomerProvisioningORM customerProvisioning) {
		update(customerProvisioning);
		return customerProvisioning.getCustomerProvisiongId();
	}

	public CustomerProvisioningORM getProvisioningByOrderProduct(
			OrderORM order, ProductORM product) {
		CustomerProvisioningORM customerProvisioning = new CustomerProvisioningORM();
		try {
			customerProvisioning = em
					.createQuery(
							"from CustomerProvisioningORM where order =:order and product =:product",
							CustomerProvisioningORM.class)
					.setParameter("order", order)
					.setParameter("product", product).getSingleResult();
		} catch (Exception e) {
			System.out
					.println("Exception in CustomerProvisioningDAO getProvisioningByOrderProduct()");
			System.out.println(e.getMessage());
		}
		return customerProvisioning;
	}
	
	
}