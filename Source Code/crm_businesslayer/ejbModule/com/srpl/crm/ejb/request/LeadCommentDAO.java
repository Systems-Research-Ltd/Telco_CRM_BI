package com.srpl.crm.ejb.request;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.srpl.crm.ejb.entity.SalesLeadCommentORM;
import com.srpl.crm.ejb.entity.SalesLeadORM;
import com.srpl.crm.ejb.entity.SupportCaseCommentORM;
import com.srpl.crm.ejb.entity.SupportCaseORM;


@Stateless
@LocalBean
public class LeadCommentDAO extends GenericDAO<SalesLeadCommentORM> {
	 /**
     * Default constructor. 
     */
	public LeadCommentDAO(){
		  // TODO Auto-generated constructor stub
    	super(SalesLeadCommentORM.class);
	}
	 public List<SalesLeadCommentORM> leadCommentList(SalesLeadORM leads){
		 System.out.println("List<SalesLeadCommentORM> leadCommentList called in DAO" );
	    	List<SalesLeadCommentORM> leadCommentList;
	    	leadCommentList = em.createQuery("from SalesLeadCommentORM where leads =:leads",SalesLeadCommentORM.class).setParameter("leads", leads).getResultList();
	      
	    	//System.out.println(leadCommentList.get(0).getLeadCommentId()+"++}}++");
	    	return leadCommentList;      	
	    }

	 public Long createLeadComment(SalesLeadCommentORM leadComment){
		 System.out.println("createLeadComment() called in DAO");
	    	save(leadComment);
	    	System.out.println(leadComment.getLeadCommentId() + "leadCommentID in DAO");
	    	return leadComment.getLeadCommentId();
	    	
	    }
	 public void deleteLeadComments(SalesLeadORM leads){
	    	try{
	    		System.out.println("deleteLeadComments called Lead id = "+leads.getLeadId());
	    	   int recordsDeleted = em.createQuery("delete SalesLeadCommentORM leadComment where leadComment.leads = :leads").setParameter("leads",leads).executeUpdate();
	    	   System.out.println("records deleted = "+recordsDeleted);
	    	}catch(Exception e){
	    		System.out.println("Exception Occured..");
	    		System.out.println(e.getMessage());
	    	}
	    }
	    
}
