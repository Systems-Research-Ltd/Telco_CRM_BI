package com.srpl.crm.ejb.request;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.srpl.crm.ejb.entity.SupportCaseORM;
import com.srpl.crm.ejb.entity.SupportQueryTypeORM;
import com.srpl.crm.ejb.exceptions.CaseNotFoundException;
import com.srpl.crm.ejb.exceptions.QueryTypeNotFoundException;
import com.srpl.um.ejb.entity.UmCompany;

/**
 * Session Bean implementation class QueryTypeDAO
 */
@Stateless
@LocalBean
public class QueryTypeDAO extends GenericDAO<SupportQueryTypeORM>{

    /**
     * Default constructor. 
     */
    public QueryTypeDAO() {
        // TODO Auto-generated constructor stub
    	super(SupportQueryTypeORM.class);
    }

    public List<SupportQueryTypeORM> listQueryTypes() throws QueryTypeNotFoundException{
       	List<SupportQueryTypeORM> queryTypes = findAll();
       	if(queryTypes.size() == 0){
    		throw new QueryTypeNotFoundException("No query type Record Found");
    	}   
    	return queryTypes;    	
    }
    
    public List<SupportQueryTypeORM> listQueryTypes(UmCompany umCompany) throws QueryTypeNotFoundException{
    	List<SupportQueryTypeORM> queryTypes = em.createQuery("from SupportQueryTypeORM where umCompany = :umCompany",SupportQueryTypeORM.class).
    			setParameter("umCompany", umCompany).
    			getResultList();
       	if(queryTypes.size() == 0){
    		throw new QueryTypeNotFoundException("No query type Record Found");
    	}   
    	return queryTypes;    	
    }
    
    public Long createQueryType(SupportQueryTypeORM queryType){
    	save(queryType);
    	return queryType.getQueryTypeId();
    }
    
    public void updateQuerytype(SupportQueryTypeORM queryType){
    	update(queryType);    	
    }
    
    public void deleteQueryType(Long queryTypeId){
    	delete(queryTypeId);
    }
    
    
    public SupportQueryTypeORM queryTypeDetails(Long queryTypeId) throws QueryTypeNotFoundException{
    	SupportQueryTypeORM queryType = null;
    	if (queryTypeId == null) {
            throw new QueryTypeNotFoundException("Invalid queryTypeId");
        }
    	queryType = find(queryTypeId);
    	return queryType;
    }
    
    
}
