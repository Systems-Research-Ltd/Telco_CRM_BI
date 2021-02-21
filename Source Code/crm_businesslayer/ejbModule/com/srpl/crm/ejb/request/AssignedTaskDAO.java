package com.srpl.crm.ejb.request;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.srpl.crm.ejb.entity.AssignedTaskORM;
import com.srpl.crm.ejb.entity.AtStatusORM;
import com.srpl.um.ejb.request.UserDAO;

/**
 * Session Bean implementation class AssignedTask
 */
@Stateless
@LocalBean
public class AssignedTaskDAO extends GenericDAO<AssignedTaskORM> {
       
	@EJB ATStatusDAO statusDao;
	@EJB UserDAO userDao;
    /**
     * @see GenericDAO#GenericDAO()
     */
    public AssignedTaskDAO() {
        super();
        // TODO Auto-generated constructor stub
    }

    //================= List Assigned Tasks =======================//
    public List<AssignedTaskORM> list(){
    	List<AssignedTaskORM> at = findAll();
    	return at;
    }
    
    //================= List Assigned Tasks For specifc user=======================//
    public List<AssignedTaskORM> list(Long uId){
    	/*UmUser user = null;
    	try
    	{
    		user=userDao.umUserDetails(uId);
    	}
    	catch(UserNotFoundException ex)
    	{

    	}*/
    	List<AssignedTaskORM> at = em.createQuery("SELECT at FROM AssignedTaskORM at WHERE assignedTo = :uId AND status <> 3",AssignedTaskORM.class)
				.setParameter("uId", uId)
				.getResultList();
    	return at;
    }

    //================= List Pending Assigned Tasks =======================//
    public List<AssignedTaskORM> listPendingTasks(){
    	System.out.println("here in pending tasks function");
    	Date currentDate = new Date();
    	AtStatusORM completed = statusDao.completedStatus();
    	AtStatusORM escalated = statusDao.escalatedStatus();
    	List<AtStatusORM> statuses = new ArrayList<AtStatusORM>();
    	statuses.add(completed);
    	statuses.add(escalated);
    	
    	List<AssignedTaskORM> at = em.createQuery("SELECT at FROM AssignedTaskORM at WHERE assignedDate < :curDate AND status NOT IN (:pStatus)",AssignedTaskORM.class)
    								.setParameter("curDate", currentDate)
    								.setParameter("pStatus", statuses)
    								.getResultList();
    	return at;
    }
    
    //================= Create Assigned Tasks =======================//
    public Long create(AssignedTaskORM at){
    	save(at);
    	return at.getId();
    }
    
    //================= View Assigned Tasks =======================//
    public AssignedTaskORM details(Long id){
    	AssignedTaskORM at = null;
    	at = find(id);
    	return at;
    }
    
    //================= Update Assigned Tasks =======================//
    public void update(AssignedTaskORM at){
    	super.update(at);
    }
    
    //================= Delete Assigned Tasks =======================//
    public void delete(Long id){
    	super.delete(id);
    }

}
