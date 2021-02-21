package com.srpl.crm.ejb.request;

/**
 * @author Hammad Hassan Khan
 *
 */
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.srpl.crm.ejb.entity.AtCategoryORM;

/**
 * Session Bean implementation class ATCategoryDAO
 */
@Stateless
@LocalBean
public class ATCategoryDAO extends GenericDAO<AtCategoryORM> {
       
    /**
     * @see GenericDAO#GenericDAO()
     */
    public ATCategoryDAO() {
        super();
        // TODO Auto-generated constructor stub
    }

    //================= List categories =======================//
    public List<AtCategoryORM> list(){
    	List<AtCategoryORM> categories = findAll();
    	return categories;
    }

    //================= category details =======================//
    public AtCategoryORM details(int x){
    	AtCategoryORM categories = find(x);
    	return categories;
    }

}
