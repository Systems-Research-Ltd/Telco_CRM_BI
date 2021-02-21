package com.srpl.crm.ejb.request;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.srpl.crm.ejb.entity.SampleTableORM;

/**
 * Session Bean implementation class PackageDAO
 */
@Stateless
@LocalBean
public class SampleDAO extends GenericDAO<SampleTableORM>{

    /**
     * Default constructor. 
     */
    public SampleDAO() {
        //Auto-generated constructor stub
    	super(SampleTableORM.class);
    }

    //================= List Sample Table =======================//
    public List<SampleTableORM> list()
    {
    	List<SampleTableORM> list = findAll();
        return list;  	
    }
}
