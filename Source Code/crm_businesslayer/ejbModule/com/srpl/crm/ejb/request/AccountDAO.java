package com.srpl.crm.ejb.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.srpl.crm.common.utils.CsAccountDetails;
import com.srpl.crm.ejb.entity.CsAccount;
import com.srpl.crm.ejb.entity.CsContactORM;
import com.srpl.crm.ejb.exceptions.AccountNotFoundException;
import com.srpl.um.ejb.entity.UmUser;
import com.srpl.um.ejb.exceptions.UserNotFoundException;

/**
 * Session Bean implementation class AccountDAO
 */
//@DeclareRoles({"AccountManager","User"})
@Stateless
@LocalBean
public class AccountDAO extends GenericDAO<CsAccount> {
	//private Long accountId;
    /**
     * Default constructor. 
     */
    public AccountDAO() {
        // TODO Auto-generated constructor stub
    	super(CsAccount.class);
    }
    
    public List<CsAccount> listAccounts(Long cId) throws AccountNotFoundException {
    	List<CsAccount> accounts = em.createQuery("SELECT a FROM CsAccount a WHERE a.companyId = :cId", CsAccount.class).setParameter("cId", cId).getResultList();
    	if(accounts.size() == 0){
    		throw new AccountNotFoundException("No Customer Account Found");
    	}    	
        return accounts;    	
    }
    
    public List<CsAccount> listAccounts(Long cId, String filterBy, String filterValue) throws AccountNotFoundException {
    	List<CsAccount> accounts = null;
		String query = "SELECT a FROM CsAccount a ";
		String where = " WHERE a.companyId = :cId ";
		String join = "";
		if (filterBy != null && !filterBy.equals("")) {
			switch (filterBy) {
			case "accountTitle":
				where += " AND a.accountTitle = :param";
				break;
			case "accountPhone":
				where += " AND a.accountPhone = :param";
				break;
			case "accountEmail":
				where += " AND a.accountEmail = :param";
				break;
			}
			 accounts = em.createQuery(query+join+where, CsAccount.class)
					.setParameter("cId", cId)
					.setParameter("param", filterValue).getResultList();
		} else {
    	accounts = em.createQuery("SELECT a FROM CsAccount a WHERE a.companyId = :cId", CsAccount.class).setParameter("cId", cId).getResultList();
		}
    	if(accounts.size() == 0){
    		throw new AccountNotFoundException("No Customer Account Found");
    	}    	
        return accounts;    	
    }
    
    public Long createAccount(CsAccount account) {
    	save(account);
    	return account.getAccountId();
    }
    
    public void updateAccount(CsAccount account){
    	update(account);    	
    }
    
    public void deleteAccount(Long accountId){
    	delete(accountId);
    }
    
    public List<CsAccount> getImportAccounts(Timestamp stamp){
    	List<CsAccount> accnt = null;
    	accnt = em.createQuery("select a from CsAccount a where a.accountCreatedon = :stamp",CsAccount.class).setParameter("stamp", stamp).getResultList();
    	return accnt;
    }
    
    public CsAccount accountDetails(Long accountId) throws AccountNotFoundException {
    	CsAccount account = null;
    	if (accountId == null) {
            throw new AccountNotFoundException("Invalid Account Id");
        }    	
    	account = find(accountId);
    	return account;
    }

	public CsAccount csMappedAccountDetails(Long mappedId) throws AccountNotFoundException {
		return (CsAccount)em.createQuery("from CsAccount where mappedId = :mid",CsAccount.class).setParameter("mid", mappedId).getSingleResult();
	}
    
    public List<CsAccountDetails> mapAccounts(InputStream in){
    	java.util.Date date= new java.util.Date();
    	List<CsAccountDetails> detailsList = new ArrayList<CsAccountDetails>();
    	BufferedReader input = new BufferedReader(new InputStreamReader(in));
    	String line = null;    	
    	try {
    		input.readLine();
			while((line = input.readLine()) != null){    		
				String[] rec = line.split(",");
				CsAccountDetails accnt = new CsAccountDetails();
				accnt.setAccountTitle(rec[0]);
	    		accnt.setAccountAddress(rec[1]);
	    		accnt.setAccountEmail(rec[2]);
	    		accnt.setAccountPhone(rec[3]);
	    		accnt.setAccountIscompany(Boolean.valueOf(rec[4]));
	    		accnt.setAccountCreatedon(new Timestamp(date.getTime()));
	    		accnt.setAccountStatus(true);
				detailsList.add(accnt);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    	return detailsList;
    }
    
    public boolean importAccounts(List<CsAccountDetails> accounts){
    	java.util.Date date= new java.util.Date();
    	Iterator<CsAccountDetails> itr = accounts.iterator();
    	while(itr.hasNext()){
    		CsAccountDetails rec = itr.next();    		
    		CsAccount accnt = new CsAccount();
    		accnt.setAccountTitle(rec.getAccountTitle());
    		accnt.setAccountAddress(rec.getAccountAddress());
    		accnt.setAccountEmail(rec.getAccountEmail());
    		accnt.setAccountPhone(rec.getAccountPhone());
    		accnt.setAccountIscompany(true);
    		accnt.setAccountCreatedon(new Timestamp(date.getTime()));
    		accnt.setAccountStatus(true);    		
    		save(accnt);
    	}    	
		return true;		
    }
    
    public boolean importAccounts(List<CsAccount> accounts, Boolean isContact) {
    	Iterator<CsAccount> itr = accounts.iterator();
    	while(itr.hasNext()){
    		CsAccount accnt = itr.next();   		    		
    		save(accnt);
    	}    	
		return true;		
    }
	
	public Boolean accntIDExists(Long accntId) /* throws UserNotFoundException */{
		List<CsAccount> accnts = em.createQuery("from CsAccount where accountId = :aid",CsAccount.class).setParameter("aid", accntId).getResultList();
		return (accnts.size() > 0);
	}
	
	public Boolean mappedIDExists(Long mappedId) /* throws UserNotFoundException */{
		List<CsAccount> accnts = em.createQuery("from CsAccount where mappedId = :mid",CsAccount.class).setParameter("mid", mappedId).getResultList();
		return (accnts.size() > 0);
	}
}
