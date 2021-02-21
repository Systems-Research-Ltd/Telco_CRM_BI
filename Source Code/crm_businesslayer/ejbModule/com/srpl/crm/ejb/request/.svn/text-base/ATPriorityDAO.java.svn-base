package com.srpl.crm.ejb.request;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.srpl.crm.ejb.entity.AtPriorityORM;

/**
 * Session Bean implementation class AtPriority
 */
@Stateless
@LocalBean
public class ATPriorityDAO extends GenericDAO<AtPriorityORM> {
       
    /**
     * @see GenericDAO#GenericDAO()
     */
    public ATPriorityDAO() {
        super();
        // TODO Auto-generated constructor stub
    }

    //================= List company =======================//
    public List<AtPriorityORM> list(){
    	List<AtPriorityORM> priorities = findAll();
    	return priorities;
    }

}
