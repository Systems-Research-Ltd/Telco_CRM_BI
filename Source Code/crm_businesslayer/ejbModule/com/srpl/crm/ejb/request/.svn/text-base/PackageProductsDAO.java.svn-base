package com.srpl.crm.ejb.request;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.srpl.crm.ejb.entity.SPackageProductORM;

/**
 * Session Bean implementation class PackageProductsDAO
 */
@Stateless
@LocalBean
public class PackageProductsDAO extends GenericDAO<SPackageProductORM>{

    /**
     * Default constructor. 
     */
    public PackageProductsDAO() {
        //Auto-generated constructor stub
    	super(SPackageProductORM.class);
    }

	public List<SPackageProductORM> listCampaigns() {
		//List<MCampaign> campaigns = findAll();
		List<SPackageProductORM> packageProducts;
		packageProducts = em.createQuery("SELECT c FROM SPackageProductORM c ",SPackageProductORM.class).getResultList();
		return packageProducts;
	}
	
	public SPackageProductORM packageProductsDetails(Long packageProducts_id){
		SPackageProductORM packageProduct = find(packageProducts_id);
		return packageProduct;
	}
	
	public Long createCampaign(SPackageProductORM pP){
		save(pP);
		return pP.getId();
	}
	
	public Long updateCampaign(SPackageProductORM pP){
		update(pP);
		return pP.getId();
	}
	
	public void deleteCampaign(Long id){
		delete(id);
	}
}
