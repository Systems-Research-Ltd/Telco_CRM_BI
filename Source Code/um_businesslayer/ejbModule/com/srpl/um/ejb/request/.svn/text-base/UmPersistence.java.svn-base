package com.srpl.um.ejb.request;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UmPersistence {
	@Resource
	SessionContext sessionContext;

	@PersistenceContext(unitName = "um_bi_persistence")
	protected EntityManager emBI;
	@PersistenceContext(unitName = "um_crm_persistence")
	protected EntityManager emCRM;

	public static final String schema = "um_crm";	
	protected EntityManager em = emCRM;
	

	// ENTITY_MANAGERS
//	public static final short ENTITY_MANAGER_CRM = 1;
//	public static final short ENTITY_MANAGER_BI = 2;
	
	@PostConstruct
	public void init() {
		setEntityManager();
	}

	public void setEntityManager() {
		System.out.println("Persistence called ----------------------"+Application.getInstance().getApplicationType()); 
		switch (Application.getInstance().getApplicationType()) {
		case "crm" :
			em = emCRM;
			break;
		case "bi":
			em = emBI;
			break;
		default :
			em = emCRM;
			break;
		}
	}
	
	

}
