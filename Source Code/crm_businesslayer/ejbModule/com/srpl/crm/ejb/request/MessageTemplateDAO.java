package com.srpl.crm.ejb.request;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.srpl.crm.ejb.entity.CsContactORM;
import com.srpl.crm.ejb.entity.MessageTemplateORM;
import com.srpl.crm.ejb.exceptions.MessageTemplateNotFoundException;
import com.srpl.um.ejb.entity.UmUser;
import com.srpl.um.ejb.exceptions.UserNotFoundException;


@Stateless
@LocalBean
public class MessageTemplateDAO extends GenericDAO<MessageTemplateORM> {
	 /**
     * Default constructor. 
     */
	public MessageTemplateDAO(){
		 // TODO Auto-generated constructor stub
    	super(MessageTemplateORM.class); 
	}

	 //start of create messageTemplate
    public Long createTemplate(MessageTemplateORM messageTemplate){
    	System.out.println("Dao template called");
    	//save(messageTemplate);
    	em.persist(messageTemplate);
    	return messageTemplate.getTemplateId();
    	
    }    
    //end 
	
    //start of update messageTemplate
    public void updateTemplate(MessageTemplateORM messageTemplate){
    	System.out.println(" messageTemplate updatefun called");
    	update(messageTemplate);
    	System.out.println("+"+ messageTemplate.getSendTo());
    }
    //end
    
    //start of delete messageTemplate 
    public void deleteTemplate(Long templateId){
    	//em.remove(messageTemplate);
    	delete(templateId);
    }
    //end
    
    public Long updateTemplate1(MessageTemplateORM messageTemplate){
		update(messageTemplate);
		System.out.println(messageTemplate.getSendTo() +"Dao sendTo");
		return messageTemplate.getTemplateId();
	}
    
    /*public List<MessageTemplateORM> listTemplate()throws MessageTemplateNotFoundException
    {
    	System.out.println("templateList called"); 
    	
    	List<MessageTemplateORM> templateList = findAll();
    	System.out.println("messageTemplateDAo test");
    	if(templateList.size()==0)
    	{
    		throw new  MessageTemplateNotFoundException("No template Record Found");
    	}
    		
    	return templateList;
    }*/
    
    public List<MessageTemplateORM> listTemplates() 
    {
    	List<MessageTemplateORM> templateList = null;
    	templateList = em.createQuery("SELECT tl FROM MessageTemplateORM tl",MessageTemplateORM.class).getResultList();
    	System.out.println("loyaltyDao test");
    	/*if(templateList.size()==0){
    		throw new MessageTemplateNotFoundException("No Message Template Found");
    	}*/
    	return templateList;
    }
    
    public MessageTemplateORM messageTemplateDetails(Long templateId)throws MessageTemplateNotFoundException {
    	MessageTemplateORM mto = null;
    	if(templateId == null)
    	{
    		throw new  MessageTemplateNotFoundException("No template Record Found");	
    	}
    	mto = find(templateId);
    	return mto;
    	
    }
    public List<MessageTemplateORM> listTemplates(long companayId){
		return listTemplates(companayId, "", "");
	}
    public List<MessageTemplateORM> listTemplates(long companyId,String filterBy,
			String filterValue)
	/* throws ContactNotFoundException */{
		List<MessageTemplateORM> template = new ArrayList<MessageTemplateORM>();
		String query = "SELECT t FROM MessageTemplateORM t ";
		String where = " WHERE t.companyId = :cId ";
		String join = "";
		if (filterBy != null && !filterBy.equals("")) {
			switch (filterBy) {
			case "title":
				filterValue = filterValue.toLowerCase();
				//where += " AND t.title = :param";
				
				where += " AND lower(t.title) like '"+filterValue+"'";
				break;
			
			}
			 template = em.createQuery(query+join+where, MessageTemplateORM.class)
					.setParameter("cId", companyId).getResultList();
		} else {
			 template = em.createQuery(query+join+where, MessageTemplateORM.class)
						.setParameter("cId", companyId)
						.getResultList();
		}

		
		/*
		 * if (contacts.size() == 0) { throw new
		 * ContactNotFoundException("No Customer Contacts Found"); }
		 */
		return template;
	}

   

}
