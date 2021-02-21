package com.srpl.crm.ejb.request;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import com.srpl.crm.ejb.entity.CsContactORM;
import com.srpl.crm.ejb.entity.ProductORM;
import com.srpl.crm.ejb.entity.SupportCaseORM;
import com.srpl.crm.ejb.entity.SupportQueryTypeORM;
import com.srpl.crm.ejb.exceptions.CaseNotFoundException;
import com.srpl.um.ejb.entity.UmCompany;
import com.srpl.um.common.utils.Utils;
import com.srpl.um.ejb.entity.MailTemplateORM;
import com.srpl.um.ejb.request.MailTemplateDAO;

/**
 * Session Bean implementation class CaseDAO
 */
@Stateless
@LocalBean
public class CaseDAO extends GenericDAO<SupportCaseORM>{

	@EJB
	MailTemplateDAO templateDao;
    /**
     * Default constructor. 
     */
    public CaseDAO() {
        // TODO Auto-generated constructor stub
    	super(SupportCaseORM.class);
    }
    
    public List<SupportCaseORM> listCases() throws CaseNotFoundException {
    	List<SupportCaseORM> cases = findAll();
    	if(cases.size() == 0){
    		throw new CaseNotFoundException("No case Record Found");
    	}    	
        return cases;    	
    }
    
    @Deprecated
    public List<SupportCaseORM> listActiveCases() throws CaseNotFoundException {
    	List<SupportCaseORM> cases = em.createQuery("from SupportCaseORM where status != :resolved",SupportCaseORM.class).setParameter("resolved", "Resolved").getResultList();
    	if(cases.size() == 0){
    		throw new CaseNotFoundException("No case Record Found");
    	}
    	return cases;
    }
    
    public List<SupportCaseORM> listActiveCases(UmCompany umCompany) throws CaseNotFoundException {
    	List<SupportCaseORM> cases = em.createQuery("from SupportCaseORM where umCompany = :umCompany and status != :resolved",SupportCaseORM.class).
    			setParameter("resolved", "Resolved").setParameter("umCompany", umCompany).
    			getResultList();
    	if(cases.size() == 0){
    		throw new CaseNotFoundException("No case Record Found");
    	}
    	return cases;
    }
    
    @Deprecated
    public List<SupportCaseORM> listClosedCases() throws CaseNotFoundException {
    	List<SupportCaseORM> cases = em.createQuery("from SupportCaseORM where status = :resolved",SupportCaseORM.class).setParameter("resolved", "Resolved").getResultList();
    	if(cases.size() == 0){
    		throw new CaseNotFoundException("No case Record Found");
    	}    	
        return cases;
    }
    
    public List<SupportCaseORM> listClosedCases(UmCompany umCompany) throws CaseNotFoundException {
    	List<SupportCaseORM> cases = em.createQuery("from SupportCaseORM where umCompany = :umCompany and status = :resolved",SupportCaseORM.class).
    			setParameter("resolved", "Resolved").setParameter("umCompany", umCompany)
    			.getResultList();
    	if(cases.size() == 0){
    		throw new CaseNotFoundException("No case Record Found");
    	}    	
        return cases;
    }
    
    
    public List<SupportCaseORM> listCustomerClosedCases(CsContactORM customer) throws CaseNotFoundException {
    	List<SupportCaseORM> cases = em.createQuery("from SupportCaseORM where customer = :customer and status = :resolved",SupportCaseORM.class).setParameter("resolved", "Resolved").setParameter("customer", customer).getResultList();
    	if(cases.size() == 0){
    		throw new CaseNotFoundException("No case Record Found");
    	}    	
        return cases;
    }
    
    public List<SupportCaseORM> listCustomerCases(CsContactORM customer) throws CaseNotFoundException {
    	List<SupportCaseORM> customerCases = em.createQuery("from SupportCaseORM where customer = :customer",SupportCaseORM.class).setParameter("customer", customer).getResultList();
    	if(customerCases.size() == 0){
    		throw new CaseNotFoundException("No case Record Found");
    	}    	
        return customerCases;    	
    }

    public List<SupportCaseORM> listCustomerActiveCases(CsContactORM customer) throws Exception {
    	List<SupportCaseORM> customerActiveCases = new ArrayList<SupportCaseORM>();
    	try{
    	  customerActiveCases = em.createQuery("from SupportCaseORM where customer = :customer and status != :resolved",SupportCaseORM.class).setParameter("customer", customer).setParameter("resolved", "Resolved").getResultList();
    	}  
    	catch(Exception e){
    	  System.out.println("Exception in CaseDAO listCustomerActiveCases");  
    	}
        return customerActiveCases;
          
    }

    public List<SupportCaseORM> listEscalatedCases() throws CaseNotFoundException {
    	List<SupportCaseORM> cases = em.createQuery("from SupportCaseORM where status = :st",SupportCaseORM.class).setParameter("st", "Escalated").getResultList();
    	if(cases.size() == 0){
    		throw new CaseNotFoundException("No Escalated Case Record Found");
    	}    	
        return cases;    	
    }
    
    public List<SupportCaseORM> retrieveCasesByUser(Long userId){
    	List<SupportCaseORM> caseList = new ArrayList<SupportCaseORM>();
    	caseList = em.createQuery("select c from SupportCaseORM c where umUser.userId =:userId", SupportCaseORM.class).setParameter("userId",userId).getResultList();
    	return caseList;
    }
    public Long createCase(SupportCaseORM newCase){
    	
        save(newCase);
    	return newCase.getCaseId();
    }
    
    public void deleteCase(Long caseId){
    	delete(caseId);
    }
    
    public void updateCase(SupportCaseORM newCase){
    	update(newCase);    	
    }
    
    public void feedBackCase(SupportCaseORM feedBackCase){
    	update(feedBackCase);    	
    }
    
    public void deleteCaseByProduct(ProductORM product){
    	try{
    	   int recordsDeleted = em.createQuery("delete SupportCaseORM supportCase where supportCase.product = :product").setParameter("product",product).executeUpdate();
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    	}   
    }
    
    public List<SupportCaseORM> retrieveCasesByProduct(ProductORM product){
    	List<SupportCaseORM> caseList = new ArrayList<SupportCaseORM>();
    	caseList = em.createQuery("from SupportCaseORM where product =:product", SupportCaseORM.class).setParameter("product",product).getResultList();
    	return caseList;
    }
    
    public List<SupportCaseORM> retrieveCasesByQueryType(SupportQueryTypeORM queryType){
    	List<SupportCaseORM> caseList = new ArrayList<SupportCaseORM>();
    	caseList = em.createQuery("from SupportCaseORM where queryType =:queryType", SupportCaseORM.class).setParameter("queryType",queryType).getResultList();
    	return caseList;
    }
    
    public SupportCaseORM caseDetails(Long caseId) throws CaseNotFoundException {
    	SupportCaseORM caseView = null;
    	if (caseId == null) {
            throw new CaseNotFoundException("Invalid case Id");
        }    	
    	caseView = find(caseId);
    	return caseView;
    }
    

//================================================== ================================================================//
    public void mailCaseToCustomer(SupportCaseORM cases){
    	
    	System.out.println("CaseDAO mailCaseToCustomer() called");
    	if(cases.getcustomer().getContactEmail() == null || cases.getcustomer().getContactEmail().equalsIgnoreCase("")){
                 System.out.println("email adress not given");    		   
    	}else{
    	    //CsContactORM customer = em.createQuery("from CsContactORM where contactEmail = :contactEmail", CsContactORM.class).setParameter("contactEmail",cases.getcustomer().getContactEmail()).getSingleResult();
    		CsContactORM customer = cases.getcustomer();

	        try {
	    		String message = "";
	    		try{
	    			//TODO get template id some how
	    			MailTemplateORM template = templateDao.details(5);
	    			message = templateDao.getMessageWithMailTemplate(template, cases);
	    		}catch(Exception e){
	    			e.printStackTrace();
	    			message = "Dear "+customer.getContactFname()+",\n\nYour Case has been registered. Your Token number is as follows:\n\n"+cases.getCaseTokenNumber();
	    		}

	    		Utils.sendMail(cases.getcustomer().getContactEmail(), "Ticket Created, Tocken number : "+cases.getCaseTokenNumber(),
	    				message, true);
	    		
	        } catch (Exception e) {
	        	System.out.println("Exception occured : "+e.getMessage());
	        	e.printStackTrace();
	        }
    	  }// end else	    
    }
//================================================== ================================================================//    
    
    
    
}
