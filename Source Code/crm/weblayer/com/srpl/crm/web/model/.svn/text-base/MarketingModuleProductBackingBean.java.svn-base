package com.srpl.crm.web.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.bitguiders.util.jsf.JSFBeanInterface;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;
import com.srpl.crm.ejb.entity.CampaignProduct;
import com.srpl.crm.ejb.entity.MCampaign;
import com.srpl.crm.ejb.entity.ProductORM;
import com.srpl.crm.ejb.request.CampaignDAO;
import com.srpl.crm.ejb.request.ProductDAO;
import com.srpl.crm.web.common.InnerTabs;
import com.srpl.crm.web.common.SessionDataBean;
import com.srpl.crm.web.controller.BeanFactory;
import com.srpl.um.ejb.entity.UmCompany;
import com.srpl.um.ejb.request.CompanyDAO;

@ManagedBean(name="campaignProducts")
@RequestScoped
public class MarketingModuleProductBackingBean extends JSFBeanSupport implements JSFBeanInterface, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String campaignProductType;
	private Map<Long,String> isChecked = new HashMap<Long,String>();
	private List<ProductORM> products = new ArrayList<ProductORM>();
	@EJB CampaignDAO campaignDao;
	@EJB ProductDAO productDao;
	@EJB CompanyDAO companyDao;
	private SessionDataBean session;
	
	public MarketingModuleProductBackingBean(){ 	
		session = BeanFactory.getInstance().getSessionBean();
	}

	public String getCampaignProductType() {
		return campaignProductType;
	}

	public void setCampaignProductType(String campaignProductType) {
		this.campaignProductType = campaignProductType;
	}	

	public Map<Long,String> getIsChecked() {
		return isChecked;
	}

	public List<ProductORM> getProducts() {		
		return products;
	}

	public List<ProductORM> productList(Boolean isPostBack){ 	
		Long campaignId = session.getMarketingModule_selectedCampaign();
		UmCompany company = companyDao.companyDetails(session.getCompanyId());
		System.out.println("CID "+campaignId);
		products = productDao.listProductsByType(campaignProductType, company);
		if(!isPostBack){
			for(ProductORM p : products){
				isChecked.put(p.getProductId(), (campaignDao.productExists(campaignId, p.getProductId()).size() > 0) ? "true" : "false");				
			}
		}	
		return products;
	}
	
	public void saveCampaignProducts(Boolean isPostBack){
		productList(isPostBack);
		System.out.println("Saving Campaign Products");
		BeanFactory beanFactory = BeanFactory.getInstance(); 	
		Long campaignId = beanFactory.getSessionBean().getMarketingModule_selectedCampaign();
		for(ProductORM prod : products){
			CampaignProduct cp = new CampaignProduct(campaignId, prod.getProductId());
			campaignDao.deleteCampaignProduct(cp);
			String check = isChecked.get(prod.getProductId());
			if(check == "true") {
				campaignDao.addCampaignProduct(cp);
			}	
		}
		FacesMessage message = new FacesMessage("Successfully add selected products to campaign") ;
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	@Override
	public String actionListener() {
		// TODO Auto-generated method stub		  
		return null;
	}

	@Override
	public List<?> getList() {
		// TODO Auto-generated method stub
		return null;
	}	
	
}
