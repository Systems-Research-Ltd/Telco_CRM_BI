package com.srpl.crm.ejb.request;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.srpl.crm.ejb.entity.CsContactORM;
import com.srpl.crm.ejb.entity.CustomerNotesORM;
import com.srpl.crm.ejb.exceptions.ContactNotFoundException;
import com.srpl.um.ejb.entity.UmCompany;
import com.srpl.um.ejb.request.CompanyDAO;

/**
 * Session Bean implementation class PackageDAO
 */
@Stateless
@LocalBean
public class CustomerNotesDAO extends GenericDAO<CustomerNotesORM>{

    /**
     * Default constructor. 
     */
	
	@EJB
	CompanyDAO companyDao;
	@EJB
	ContactDAO contactDao;
	
    public CustomerNotesDAO() {
        //Auto-generated constructor stub
    	super(CustomerNotesORM.class);
    }

	public List<CustomerNotesORM> list(Long company_id, Long customer_id) {
		//List<MCampaign> campaigns = findAll();
		UmCompany company = null;
		CsContactORM	customer = null;
		company = companyDao.companyDetails(company_id);
		try {
			customer = contactDao.contactDetails(customer_id);
		} catch (ContactNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<CustomerNotesORM> results;
		results = em.createQuery("SELECT n FROM CustomerNotesORM n where n.company = :company AND n.customer = :customer",CustomerNotesORM.class)
					.setParameter("company", company)
					.setParameter("customer", customer)
					.getResultList();
		return results;
	}
	
	public CustomerNotesORM details(Long note_id){
		CustomerNotesORM result = find(note_id);
		return result;
	}
	
	public Long create(CustomerNotesORM note, Long company_id, Long customer_id){
		UmCompany company = null;
		CsContactORM	customer = null;
		company = companyDao.companyDetails(company_id);
		try {
			customer = contactDao.contactDetails(customer_id);
		} catch (ContactNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		note.setCompany(company);
		note.setCustomer(customer);
		note.setId(null);
		save(note);
		return note.getId();
	}
	
	public Long create(CustomerNotesORM note){
		note.setId(null);
		save(note);
		return note.getId();
	}
	
	public Long updatePackage(CustomerNotesORM note){
		update(note);
		return note.getId();
	}
	
	public void delete(Long id){
		delete(id);
	}
}
