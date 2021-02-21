package com.srpl.crm.ejb.request;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.srpl.crm.ejb.entity.CsContactORM;
import com.srpl.crm.ejb.entity.InvoiceSettingsORM;
import com.srpl.crm.ejb.entity.SupportCaseORM;
import com.srpl.um.ejb.entity.UmCompany;

/**
 * Session Bean implementation class InvoiceSettingsDAO
 */
@Stateless
@LocalBean
public class InvoiceSettingsDAO extends GenericDAO<InvoiceSettingsORM>{

    /**
     * Default constructor. 
     */
    public InvoiceSettingsDAO() {
       super(InvoiceSettingsORM.class);
    }

    
    public List<InvoiceSettingsORM> listInvoiceSettings(){
    	List<InvoiceSettingsORM> invoiceSettingsList = findAll();
    	return invoiceSettingsList;
    }
    
    public long createInvoiceSettings(){
    	return 1l;
    }
    
    public void updateInvoiceSettings(InvoiceSettingsORM invoiceSettingsOrm){
    	update(invoiceSettingsOrm);
    }
    
    public void createInvoiceSettingsByList(List<InvoiceSettingsORM> invoiceSettingsList){
       for(InvoiceSettingsORM invoiceSettingsOrm : invoiceSettingsList){
    	   save(invoiceSettingsOrm);
       }    	
    }
    
    public void updateInvoiceSettingByList(List<InvoiceSettingsORM> invoiceSettingsList){
    	try{
    	 for(InvoiceSettingsORM invoiceSettingsOrm : invoiceSettingsList){
    	     System.out.println("updateInvoiceSettingByList invoiceSettingsId = "+invoiceSettingsOrm.getInvoiceSettingId());	
     	   update(invoiceSettingsOrm);
         }
    	}catch(Exception e){
    		System.out.println("Exception Occured in InvoiceSettingsDAO updateInvoiceSettingByList()");
    		System.out.println(e.getMessage());
    	}
    }
    
    public InvoiceSettingsORM getInvoiceSettingByCustomer(CsContactORM customer){
    	System.out.println("getInvoiceSettingByCustomer() called..");
    	InvoiceSettingsORM invoiceSettingsOrm = new InvoiceSettingsORM();
    	try{
    	  invoiceSettingsOrm = em.createQuery("from InvoiceSettingsORM where invoiceSettingCustomer =:customer", InvoiceSettingsORM.class).setParameter("customer",customer).getSingleResult();
    	  System.out.println("invoiceSettingsOrm = "+invoiceSettingsOrm);
    	}catch(Exception e){
    		System.out.println("Exception in InvoiceSettinsDAO getInvoiceSettingByCustomer()");
    		System.out.println(e.getMessage());
    	}
    	return invoiceSettingsOrm;
    }
    
    public InvoiceSettingsORM getInvoiceSettingByCompanyCustomer(UmCompany company, CsContactORM customer){
    	System.out.println("getInvoiceSettingByCustomer() called..");
    	InvoiceSettingsORM invoiceSettingsOrm = new InvoiceSettingsORM();
    	try{
    	  invoiceSettingsOrm = em.createQuery("from InvoiceSettingsORM where invoiceSettingCustomer =:customer and invoiceSettingCompany =:company", InvoiceSettingsORM.class).setParameter("customer",customer).setParameter("company", company).getSingleResult();
    	  System.out.println("invoiceSettingsOrm = "+invoiceSettingsOrm);
    	}catch(Exception e){
    		System.out.println("Exception in InvoiceSettinsDAO getInvoiceSettingByCustomer()");
    		System.out.println(e.getMessage());
    	}
    	return invoiceSettingsOrm;
    }
    
}
