package com.srpl.crm.ejb.request;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.srpl.crm.ejb.entity.ProductORM;
import com.srpl.crm.ejb.entity.SalesOpportunityORM;
import com.srpl.crm.ejb.exceptions.OpportunityNotFoundException;
import com.srpl.um.ejb.entity.UmCompany;
import com.srpl.um.ejb.request.CompanyDAO;

/**
 * Session Bean implementation class OpportunityDAO
 */
@Stateless
@LocalBean
public class OpportunityDAO extends GenericDAO<SalesOpportunityORM>{

	@EJB CompanyDAO companyDao;
    /**
     * Default constructor. 
     */
    public OpportunityDAO() {
        // TODO Auto-generated constructor stub
    	super(SalesOpportunityORM.class);
    }

    
    public List<SalesOpportunityORM> list(Long company_id) throws OpportunityNotFoundException{
    	UmCompany company = null;
    	company = companyDao.companyDetails(company_id);
    	return this.list(company);
    }
    
    public List<SalesOpportunityORM> list(UmCompany company) throws OpportunityNotFoundException {
    	List<SalesOpportunityORM> opportunities = em.createQuery("FROM SalesOpportunityORM WHERE company = :c", SalesOpportunityORM.class).setParameter("c", company).getResultList();
    	if(opportunities.size() == 0){
    		throw new OpportunityNotFoundException("No Opportunity Record Found");
    	}
    	return opportunities;
    }
    
    
    public Long create(SalesOpportunityORM opportunity) {
    	save(opportunity);
    	return opportunity.getOpportunityId();
    }
    
    public void delete(Long opportunityId){
    	super.delete(opportunityId);
    }
    
    public void update(SalesOpportunityORM opportunity){
    	super.update(opportunity);    	
    }

    public SalesOpportunityORM details(Long opportunityId) throws OpportunityNotFoundException {
    	SalesOpportunityORM opportunity = null;
    	if (opportunityId == null) {
            throw new OpportunityNotFoundException("Invalid opportunity Id");
        }    	
    	opportunity = find(opportunityId);
    	return opportunity;
    }

    public List<SalesOpportunityORM> retrieveOpportunityByProduct(ProductORM product){
    	List<SalesOpportunityORM> opportunityList = new ArrayList<SalesOpportunityORM>();
    	opportunityList = em.createQuery("from SalesOpportunityORM where product =:product", SalesOpportunityORM.class).setParameter("product",product).getResultList();
    	return opportunityList;
    	
    }
    
}
