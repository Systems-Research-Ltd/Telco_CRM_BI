package com.srpl.crm.web.model.sales;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import com.srpl.crm.ejb.entity.ProductORM;
import com.srpl.crm.ejb.exceptions.ProductNotFoundException;
import com.srpl.crm.ejb.request.ProductDAO;

@ManagedBean
@RequestScoped
public class OrderProductConverter implements Converter, Serializable{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//OrderBackingBean orderBackingBean = new OrderBackingBean();
	@EJB ProductDAO productDao;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		// TODO Auto-generated method stub
		if(value.trim().equals("")){
           return null;			
		}
		
		ProductORM product = new ProductORM();
		Long productId = Long.parseLong(value);
		System.out.println(productDao);
		try{
		product = productDao.productDetails(productId);
		}catch(ProductNotFoundException e){
			System.out.println(e.getMessage());
		}
		return (Object)product;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		// TODO Auto-generated method stub
		// get product against product id then return productTitle
		//return ((ProductORM) value).getProductId().toString(); 
		/*ProductORM product = new ProductORM();
		try{
			product = productDao.productDetails((Long)value);
			}catch(ProductNotFoundException e){
				System.out.println(e.getMessage());
		}
		System.out.println("title = "+product.getProductTitle());*/
		return value.toString();
	}

}
