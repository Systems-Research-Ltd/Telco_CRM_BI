package com.srpl.crm.ejb.request;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.srpl.crm.ejb.entity.CsContactORM;
import com.srpl.crm.ejb.entity.SServiceSubscribeORM;
import com.srpl.crm.ejb.entity.SServiceSubscriptionHistoryORM;
import com.srpl.crm.ejb.exceptions.ContactNotFoundException;

/**
 * @author Hammad Hassan Khan
 *
 */

@Stateless
@LocalBean
public class SubscriptionDAO extends GenericDAO<SServiceSubscribeORM> {

	@EJB ContactDAO contactDao;
	@EJB SubscriptionHistoryDAO historyDao;
	/*
	 * Constructor
	 */
	public SubscriptionDAO(){
		super(SServiceSubscribeORM.class);
	}
	
	//================= List Subscriptions =======================//
	public List<SServiceSubscribeORM> list(Long customer_id){
		List<SServiceSubscribeORM> subscription = null;
		try {
			CsContactORM customer = contactDao.contactDetails(customer_id);
			//subscription = (List<SServiceSubscribeORM>) customer.getcServiceSubscribe();
			subscription = new ArrayList<SServiceSubscribeORM>(customer.getSubscription());
			//subscription = findAll();
		} catch (ContactNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return subscription;
	}
	
	//================= Create Subscriptions =======================//
	public Long create(SServiceSubscribeORM s){
		save(s);
		
		//History
		SServiceSubscriptionHistoryORM history = new SServiceSubscriptionHistoryORM();
		history.setIsPackage(s.getIsPackage());
		history.setIsSubscribe(true);
		history.setDate(new Timestamp(new Date().getTime()));
		history.setSubscriber(s.getSubscriber());
		if(history.getIsPackage()){
			history.setPackg(s.getPackg());
		}else{
			history.setProduct(s.getProduct());
		}
		historyDao.create(history);
			
		return s.getId();
	}
	
	//================= Details Subscriptions =======================//
	public SServiceSubscribeORM details(Long s_id){
		SServiceSubscribeORM s;
		s = find(s_id);
		return s;
	}
	
	//================= Update Subscriptions =======================//
	public void updates(Long cId, List<SServiceSubscribeORM> subscriptions){

	    	CsContactORM customer = null;
	    	try {
				customer = contactDao.contactDetails(cId);
				
		    	//TODO temporary store previous packages and update subscription history
		    	List<SServiceSubscribeORM> oldSubsciptions = null;
		    	oldSubsciptions = new ArrayList<SServiceSubscribeORM>(customer.getSubscription());
		    	
//		    	customer.setSubscription(subscriptions);
//		    	contactDao.updateSubscription(customer);
		    	
		    	boolean saveMe, removeMe;
		    	boolean iP,pkg,pr;
		    	for(SServiceSubscribeORM x:subscriptions){
		    		saveMe = true;
		    		for(SServiceSubscribeORM y:oldSubsciptions){

			    		iP = false;
			    		pkg = false;
			    		pr = false;
			    		
		    			if(x.getIsPackage().equals(y.getIsPackage()))
		    				iP = true;
		    			
		    			if(x.getPackg() != null){
		    				try{
		    					if(x.getPackg().getId() == y.getPackg().getId())
		    						pkg = true;
		    				}catch (Exception e) {
								System.out.println("Y package was null, so not equal");
							}
		    			}else{
		    				if(y.getPackg() == null)
		    					pkg = true;
		    			}
		    			
		    			if(x.getProduct() != null){
		    				try{
		    					if(x.getProduct().getProductId() == y.getProduct().getProductId())
		    						pr = true;
		    				}catch (Exception e) {
								System.out.println("Y product was null");
							}
		    			}else{
		    				if(y.getProduct() == null)
		    					pr = true;
		    			}
		    			
		    			System.out.println("pause");
		    			if(iP && pkg && pr){
		    				saveMe = false;
		    				break;
		    			}
		    		}
		    		if(saveMe){
		    			x.setSubscriber(customer);
		    			save(x);

		    			//History
		    			SServiceSubscriptionHistoryORM history = new SServiceSubscriptionHistoryORM();
		    			history.setIsPackage(x.getIsPackage());
		    			history.setIsSubscribe(true);
		    			history.setDate(new Timestamp(new Date().getTime()));
		    			history.setSubscriber(x.getSubscriber());
		    			if(history.getIsPackage()){
		    				history.setPackg(x.getPackg());
		    			}else{
		    				history.setProduct(x.getProduct());
		    			}
		    			historyDao.create(history);
		    				
		    		}
		    	}
		    	
		    	//Remove Unsub
		    	for(SServiceSubscribeORM x:oldSubsciptions){
		    		removeMe = true;
		    		for(SServiceSubscribeORM y:subscriptions){

			    		iP = false;
			    		pkg = false;
			    		pr = false;
			    		
		    			if(x.getIsPackage().equals(y.getIsPackage()))
		    				iP = true;
		    			
		    			if(x.getPackg() != null){
		    				try{
		    					if(x.getPackg().getId() == y.getPackg().getId())
		    						pkg = true;
		    				}catch (Exception e) {
								System.out.println("Y package was null, so not equal");
							}
		    			}else{
		    				if(y.getPackg() == null)
		    					pkg = true;
		    			}
		    			
		    			if(x.getProduct() != null){
		    				try{
		    					if(x.getProduct().getProductId() == y.getProduct().getProductId())
		    						pr = true;
		    				}catch (Exception e) {
								System.out.println("Y product was null");
							}
		    			}else{
		    				if(y.getProduct() == null)
		    					pr = true;
		    			}
		    			
		    			System.out.println("pause");
		    			if(iP && pkg && pr){
		    				removeMe = false;
		    				break;
		    			}
		    		}
		    		if(removeMe){
		    			//x.setSubscriber(customer);
		    			//save(x);
		    			
		    			//History
		    			SServiceSubscriptionHistoryORM history = new SServiceSubscriptionHistoryORM();
		    			history.setIsPackage(x.getIsPackage());
		    			history.setIsSubscribe(false);
		    			history.setDate(new Timestamp(new Date().getTime()));
		    			history.setSubscriber(x.getSubscriber());
		    			if(history.getIsPackage()){
		    				history.setPackg(x.getPackg());
		    			}else{
		    				history.setProduct(x.getProduct());
		    			}
		    			historyDao.create(history);
		    				
		    			delete(x.getId());
		    		}
		    	}
		    	
			} catch (ContactNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	//================= Delete Subscriptions =======================//
	public void deletes(Long s_id){
		
		SServiceSubscribeORM s = find(s_id);

		SServiceSubscriptionHistoryORM history = new SServiceSubscriptionHistoryORM();
		history.setIsPackage(s.getIsPackage());
		history.setIsSubscribe(false);
		history.setDate(new Timestamp(new Date().getTime()));
		history.setSubscriber(s.getSubscriber());
		if(history.getIsPackage()){
			history.setPackg(s.getPackg());
		}else{
			history.setProduct(s.getProduct());
		}
		historyDao.create(history);
			
		
		delete(s_id);
	}
}
