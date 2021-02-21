package com.srpl.crm.ejb.request;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.srpl.crm.ejb.entity.MCampaign;
import com.srpl.crm.ejb.entity.PaymentORM;
import com.srpl.crm.ejb.entity.ProductORM;
import com.srpl.crm.ejb.entity.SalesOpportunityORM;
import com.srpl.crm.ejb.entity.SupportCaseORM;
import com.srpl.crm.ejb.exceptions.OpportunityNotFoundException;
import com.srpl.crm.ejb.exceptions.ProductNotFoundException;
import com.srpl.um.ejb.entity.UmCompany;

/**
 * Session Bean implementation class ProductDAO
 */
@Stateless
@LocalBean
public class ProductDAO extends GenericDAO<ProductORM>{

    /**
     * Default constructor. 
     */
    public ProductDAO() {
        // TODO Auto-generated constructor stub
    	super(ProductORM.class);
    }
    
    public List<ProductORM> listProducts() throws ProductNotFoundException{
    	List<ProductORM> productsList = findAll();
    	if(productsList.size() == 0){
    		throw new ProductNotFoundException("No Product Record Found");
    	}
    	return productsList;
    }
    public List<ProductORM> listProducts(UmCompany umCompany){
    	List<ProductORM> productsList = em.createQuery("from ProductORM where umCompany = :umCompany",ProductORM.class).
    			setParameter("umCompany", umCompany).getResultList();
    	return productsList;
    }
    
    public List<ProductORM> listProductsByType(String type, UmCompany company) {
    	System.out.println("Type "+type);
    	List<ProductORM> productsList = null;
    	if(type == null || type == "")
    		productsList = listProducts(company);
    	else    		
	    	productsList = em.createQuery("SELECT p FROM ProductORM p where p.productType = :pType and umCompany = :umCompany",ProductORM.class).setParameter("pType", type).setParameter("umCompany", company).getResultList();
	   return productsList;
    }

    public Long createProduct(ProductORM product){
    	save(product);
    	return product.getProductId();
    }
    
    public void updateProduct(ProductORM product){
    	System.out.println("product id = "+product.getProductId());
    	update(product);
    }
    
    public void deleteProduct(Long productId){
    	delete(productId);
    }
    
    public ProductORM productDetails(Long productId) throws ProductNotFoundException {
    	ProductORM product = null;
    	if (productId == null) {
            throw new ProductNotFoundException("Invalid product Id");
        }    	
    	product = find(productId);
    	return product;
    }  
    public boolean importProducts(List<ProductORM> pmts, Long company) {
    	Iterator<ProductORM> itr = pmts.iterator();
    	while(itr.hasNext()){
    		ProductORM pmt = itr.next();   		    		   		
    		save(pmt);
    	}    	
		return true;		
    }
	
	public Boolean productIDExists(Long pmtId) /* throws UserNotFoundException */{
		List<ProductORM> pmts = em.createQuery("from ProductORM where productId = :uid",ProductORM.class).setParameter("uid", pmtId).getResultList();
		return (pmts.size() > 0);
	}
    
    public List<ProductORM> getImportProducts(Timestamp stamp){
    	List<ProductORM> cnt = null;
    	cnt = em.createQuery("from ProductORM where productAddedon = :stamp",ProductORM.class).setParameter("stamp", stamp).getResultList();
    	return cnt;
    }
    
}
