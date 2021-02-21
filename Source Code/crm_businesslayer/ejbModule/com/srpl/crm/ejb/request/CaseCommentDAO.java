package com.srpl.crm.ejb.request;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.srpl.crm.ejb.entity.SupportCaseCommentORM;
import com.srpl.crm.ejb.entity.SupportCaseHistoryORM;
import com.srpl.crm.ejb.entity.SupportCaseORM;

/**
 * Session Bean implementation class CaseCommentDAO
 */
@Stateless
@LocalBean
public class CaseCommentDAO extends GenericDAO<SupportCaseCommentORM>{

    /**
     * Default constructor. 
     */
    public CaseCommentDAO() {
        // TODO Auto-generated constructor stub
    	super(SupportCaseCommentORM.class);
    }
    
    public List<SupportCaseCommentORM> caseCommentList(SupportCaseORM cases){
    	List<SupportCaseCommentORM> caseCommentList;
    	caseCommentList = em.createQuery("from SupportCaseCommentORM where cases =:cases order by caseCommentsDate",SupportCaseCommentORM.class).setParameter("cases", cases).getResultList();
        return caseCommentList;      	
    }
    
    public Long createCaseComment(SupportCaseCommentORM caseComment){
    	save(caseComment);
    	return caseComment.getCaseCommentId();
    }
    
    
    public void deleteCaseComments(SupportCaseORM cases){
    	try{
    		System.out.println("deleteCaseComments called case id = "+cases.getCaseId());
    	   int recordsDeleted = em.createQuery("delete SupportCaseCommentORM caseComment where caseComment.cases = :cases").setParameter("cases",cases).executeUpdate();
    	   System.out.println("records deleted = "+recordsDeleted);
    	}catch(Exception e){
    		System.out.println("Exception Occured..");
    		System.out.println(e.getMessage());
    	}
    }
    
   
}
