package com.srpl.crm.ejb.request;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.srpl.crm.ejb.entity.CsContactORM;
import com.srpl.crm.ejb.entity.ProductORM;
import com.srpl.crm.ejb.entity.SPackageORM;
import com.srpl.crm.ejb.entity.SServiceSubscribeORM;
import com.srpl.crm.ejb.exceptions.ContactNotFoundException;
import com.srpl.crm.ejb.exceptions.ProductNotFoundException;

/**
 * Session Bean implementation class PackageDAO
 */
@Stateless
@LocalBean
public class PackageDAO extends GenericDAO<SPackageORM>{

	@EJB ContactDAO contactDao;
	@EJB ProductDAO productDao;
    /**
     * Default constructor. 
     */
    public PackageDAO() {
        //Auto-generated constructor stub
    	super(SPackageORM.class);
    }

	public List<SPackageORM> listPackages(Long cid) {
		//List<MCampaign> campaigns = findAll();
		List<SPackageORM> packages;
		packages = em.createQuery("SELECT c FROM SPackageORM c where c.companyId = :companyId",SPackageORM.class).setParameter("companyId", cid).getResultList();
		System.out.println(packages.size()+"-"+cid);
		return packages;
	}
	
	public SPackageORM packageDetails(Long pk_id){
		SPackageORM packages = find(pk_id);
		System.out.println(packages.getProducts().size());
		return packages;
	}
	
	public Long createPackage(SPackageORM pkg){
		pkg.setId(null);
		//Testing for product link
		/*
		ProductORM product;
		try {
			product = productDao.productDetails(1L);
			pkg.getProducts().add(product);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		*/
		save(pkg);
		return pkg.getId();
	}
	
	public Long updatePackage(SPackageORM pkg){
		update(pkg);
		return pkg.getId();
	}
	
	public void deletePackage(Long id){
		delete(id);
	}
	
	public List<SPackageORM> customerPackages(Long cid, Boolean include){
		
		//If include is true, return packages of customer otherwise
		//return all packages which customer is not availing
		
		List<SPackageORM> packages = null;
		List<SPackageORM> tempList = null;
		CsContactORM customer = null;
		try {
			customer = contactDao.contactDetails(cid);
			tempList = em.createQuery("SELECT s.packg FROM SServiceSubscribeORM s WHERE s.subscriber = :c",SPackageORM.class).setParameter("c", customer).getResultList();
			if(include){
				packages = tempList;
			}
			else{
				if(tempList != null && tempList.size() != 0){
					packages = em.createQuery("SELECT p FROM SPackageORM p WHERE p NOT IN (:pList)", SPackageORM.class).setParameter("pList", tempList).getResultList();
				}else{
					packages = em.createQuery("SELECT p FROM SPackageORM p", SPackageORM.class).getResultList();
				}
			}
		} catch (ContactNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return packages;
	}
	
	  
    public boolean importPackages(List<SPackageORM> pmts, Long company) {
    	Iterator<SPackageORM> itr = pmts.iterator();
    	while(itr.hasNext()){
    		SPackageORM pmt = itr.next();   		    		   		
    		save(pmt);
    	}    	
		return true;		
    }
	
	public Boolean packageIDExists(Long pmtId) /* throws UserNotFoundException */{
		List<SPackageORM> pmts = em.createQuery("from SPackageORM where id = :uid",SPackageORM.class).setParameter("uid", pmtId).getResultList();
		return (pmts.size() > 0);
	}
    
    public List<SPackageORM> getImportPackages(Timestamp stamp){
    	List<SPackageORM> cnt = null;
    	cnt = em.createQuery("from SPackageORM where packageAddedon = :stamp",SPackageORM.class).setParameter("stamp", stamp).getResultList();
    	return cnt;
    }
}
