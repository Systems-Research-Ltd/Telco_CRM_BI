package com.srpl.crm.ejb.request;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.srpl.crm.ejb.entity.AtStatusORM;

/**
 * Session Bean implementation class ATStatusesDAO
 */
@Stateless
@LocalBean
public class ATStatusDAO extends GenericDAO<AtStatusORM> {
       
    /**
     * @see GenericDAO#GenericDAO()
     */
    public ATStatusDAO() {
        super();
        // TODO Auto-generated constructor stub
    }
       
    //================= List Statuses =======================//
    public List<AtStatusORM> list(){
    	List<AtStatusORM> statuses = findAll();
    	return statuses;
    }
    
    //================= Get Completed Status =======================//
    public AtStatusORM completedStatus(){
    	AtStatusORM cStatus = em.createQuery("SELECT s FROM AtStatusORM s WHERE title = 'completed'",AtStatusORM.class).getSingleResult();
    	return cStatus;
    }

    
    //================= Get In Process Status =======================//
    public AtStatusORM inProcessStatus(){
    	AtStatusORM cStatus = em.createQuery("SELECT s FROM AtStatusORM s WHERE title = 'in process'",AtStatusORM.class).getSingleResult();
    	return cStatus;
    }

    
    //================= Get escalated Status =======================//
    public AtStatusORM escalatedStatus(){
    	AtStatusORM cStatus = em.createQuery("SELECT s FROM AtStatusORM s WHERE title = 'escalated'",AtStatusORM.class).getSingleResult();
    	return cStatus;
    }

}
