package com.srpl.crm.ejb.request;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.srpl.crm.ejb.entity.SalesLeadHistoryORM;
import com.srpl.crm.ejb.entity.SalesLeadORM;
import com.srpl.crm.ejb.entity.SupportCaseHistoryORM;
import com.srpl.crm.ejb.entity.SupportCaseORM;

@Stateless
@LocalBean
public class LeadHistoryDAO extends GenericDAO<SalesLeadHistoryORM>{
	/**
     * Default constructor. 
     */
    public LeadHistoryDAO() {
        // TODO Auto-generated constructor stub
    	super(SalesLeadHistoryORM.class);
    }
    
    public List<SalesLeadHistoryORM> leadHistoryList(SalesLeadORM leads){
    	System.out.println("leadHistoryDAO leadHistoryList() called");
    	List<SalesLeadHistoryORM> leadHistoryList;
    	try{
    		leadHistoryList = em.createQuery("from SalesLeadHistoryORM where leads =:leads", SalesLeadHistoryORM.class).setParameter("leads",leads).getResultList();
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    		leadHistoryList = em.createQuery("from SalesLeadHistoryORM where leads =:leads ", SalesLeadHistoryORM.class).setParameter("leads",leads).getResultList();
    	}
    	return leadHistoryList; 
    }

    public Long createLeadHistory(SalesLeadHistoryORM leadHistory){
    	System.out.println("createLeadHistory() Called in DAO");
    	save(leadHistory);
    	return leadHistory.getLeadHistoryId();
    }
    public void updateLead(SalesLeadHistoryORM leadHistory){
    	update(leadHistory);    	
    }
    
    public void deleteLeadHistory(SalesLeadORM leads){
    	try{
    		System.out.println("deleteleadHistory called case id = "+leads.getLeadId());
    	   int recordsDeleted = em.createQuery("delete SalesLeadHistoryORM leadHistory where leadHistory.leads = :leads").setParameter("leads",leads).executeUpdate();
    	   System.out.println("records deleted = "+recordsDeleted);
    	}catch(Exception e){
    		System.out.println("Exception Occured..");
    		System.out.println(e.getMessage());
    	}
    }
    public SalesLeadHistoryORM retreiveLeadHistory(SalesLeadORM leads){
    	SalesLeadHistoryORM leadHistory;
    	leadHistory = em.createQuery("from SalesLeadHistoryORM where leads =:leads", SalesLeadHistoryORM.class).setParameter("leads",leads).getSingleResult();
        return leadHistory;     	
    }
    
    public SalesLeadHistoryORM leadHistoryDetails(Long leadHistoryId){
    	SalesLeadHistoryORM salesLeadsHistory;    	
    	salesLeadsHistory = find(leadHistoryId);
    	return salesLeadsHistory;
    }



}
