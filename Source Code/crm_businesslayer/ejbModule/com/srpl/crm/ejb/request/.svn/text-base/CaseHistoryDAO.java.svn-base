package com.srpl.crm.ejb.request;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.srpl.crm.ejb.entity.SupportCaseHistoryORM;
import com.srpl.crm.ejb.entity.SupportCaseORM;

/**
 * Session Bean implementation class CaseHistoryDAO
 */
@Stateless
@LocalBean
public class CaseHistoryDAO extends GenericDAO<SupportCaseHistoryORM>{

    /**
     * Default constructor. 
     */
    public CaseHistoryDAO() {
        // TODO Auto-generated constructor stub
    	super(SupportCaseHistoryORM.class);
    }

    public List<SupportCaseHistoryORM> caseHistoryList(SupportCaseORM cases){
    	System.out.println("CaseHistoryDAO caseHistoryList() called");
    	List<SupportCaseHistoryORM> caseHistoryList;
    	try{
    	    caseHistoryList = em.createQuery("from SupportCaseHistoryORM where cases =:cases order by caseAssignedToDate", SupportCaseHistoryORM.class).setParameter("cases",cases).getResultList();
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    		caseHistoryList = em.createQuery("from SupportCaseHistoryORM where cases =:cases order by caseAssignedToDate", SupportCaseHistoryORM.class).setParameter("cases",cases).getResultList();
    	}
    	return caseHistoryList; 
    }
    
    public Long createCaseHistory(SupportCaseHistoryORM caseHistory){
    	save(caseHistory);
    	return caseHistory.getCaseHistoryId();
    }
    
    public void updateCase(SupportCaseHistoryORM caseHistory){
    	update(caseHistory);    	
    }
    
    public void deleteCaseHistory(SupportCaseORM cases){
    	try{
    		System.out.println("deleteCaseHistory called case id = "+cases.getCaseId());
    	   int recordsDeleted = em.createQuery("delete SupportCaseHistoryORM caseHistory where caseHistory.cases = :cases").setParameter("cases",cases).executeUpdate();
    	   System.out.println("records deleted = "+recordsDeleted);
    	}catch(Exception e){
    		System.out.println("Exception Occured..");
    		System.out.println(e.getMessage());
    	}
    }
    
    public SupportCaseHistoryORM retreiveCaseHistory(SupportCaseORM cases){
    	SupportCaseHistoryORM caseHistory;
    	caseHistory = em.createQuery("from SupportCaseHistoryORM where cases =:cases", SupportCaseHistoryORM.class).setParameter("cases",cases).getSingleResult();
        return caseHistory;     	
    }
    
    
    public SupportCaseHistoryORM caseHistoryDetails(Long caseHistoryId){
    	SupportCaseHistoryORM supportCaseHistory;    	
    	supportCaseHistory = find(caseHistoryId);
    	return supportCaseHistory;
    }
    
}
