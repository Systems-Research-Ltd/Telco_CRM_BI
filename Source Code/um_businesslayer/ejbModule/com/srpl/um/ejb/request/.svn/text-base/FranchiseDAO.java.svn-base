package com.srpl.um.ejb.request;

/**
 * @author Hammad Hassan Khan
 *
 */
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.srpl.um.ejb.entity.UmCompany;
import com.srpl.um.ejb.entity.UmFranchise;

/**
 * Session Bean implementation class FranchiseDAO
 */
@Stateless
@LocalBean
public class FranchiseDAO extends GenericDAO<UmFranchise>{

	CompanyDAO companyDAO = new CompanyDAO();
	
    /**
     * Default constructor. 
     */
    public FranchiseDAO() {
        // TODO Auto-generated constructor stub
    	super(UmFranchise.class);
    }
    
    
    //============== List Franchises =================//
     
     public List<UmFranchise> list(Long company_id){
     	UmCompany umCompany = em.find(UmCompany.class, company_id);
     	List<UmFranchise> franchises = null;
     	franchises = em.createQuery("SELECT f FROM UmFranchise f where f.umCompany = :cId",UmFranchise.class).setParameter("cId", umCompany).getResultList();
     	
    	 return franchises;
     }   
    
    //================ Create Franchise ===================//
    public Long create(UmFranchise franchise, Long companyId){
        System.out.println("dao creatFranchise called");
        UmCompany company = em.find(UmCompany.class, companyId);
        franchise.setUmCompany(company);
        save(franchise);
        return franchise.getFranchiseId();
        
    }
    
    //================== Update Franchise ===========================//
        public Long updates(UmFranchise franchise, Long companyId){
            UmCompany company = em.find(UmCompany.class, companyId);
            franchise.setUmCompany(company);
        	update(franchise);
        	return franchise.getFranchiseId();
        }

    //================== Delete Franchise ===========================//
    public void remove(Long franchiseId){
    	delete(franchiseId);
    }

    //================== Detail Franchise ===========================//
    public UmFranchise details(Long franchiseId){
    	UmFranchise franchise = find(franchiseId);
    	return franchise;
    }
    
}
