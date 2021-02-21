package com.srpl.crm.web.model.um.fileupload;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FileUploadEvent;

import com.srpl.crm.common.utils.CsAccountDetails;
import com.srpl.crm.common.utils.CsContactDetails;
import com.srpl.crm.ejb.request.AccountDAO;
import com.srpl.crm.ejb.request.ContactDAO;

@ManagedBean(name="customerUploadBean")
@ViewScoped
public class CustomerUploadBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	List<CsContactDetails> contactList;
	List<CsAccountDetails> accountList;
	
	@EJB ContactDAO contactDao;
	@EJB AccountDAO accountDao;
	
	boolean importbtnflag=false;
	
	public CustomerUploadBean()
	{
		
	}
	
	public void uploadContacts(FileUploadEvent event) {  
       
        
        try
        {
			contactList = contactDao.mapContacts(event.getFile().getInputstream());
			FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");  
		    FacesContext.getCurrentInstance().addMessage(null, msg);
			importbtnflag=true;
        }
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	public void importContacts(ActionEvent e)
	{
		contactDao.importUsers(contactList);
		FacesMessage msg = new FacesMessage("Succesful!!","Contacts added to Database");  
        FacesContext.getCurrentInstance().addMessage(null, msg);
        importbtnflag=false;
        accountList=null;
	}
	
	public void uploadAccounts(FileUploadEvent event) {  
       
        
        try
        {
			accountList = accountDao.mapAccounts(event.getFile().getInputstream());
			 FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");  
		     FacesContext.getCurrentInstance().addMessage(null, msg);		   
			importbtnflag=true;
        }
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	public void importAccounts(ActionEvent e)
	{
		accountDao.importAccounts(accountList);
		FacesMessage msg = new FacesMessage("Succesful!!","Accounts added to Database");  
        FacesContext.getCurrentInstance().addMessage(null, msg);
        importbtnflag=false;
        accountList=null;
	}
	
	
	
	
	
	// Getters and Setters

	public List<CsContactDetails> getContactList() {
		return contactList;
	}

	public void setContactList(List<CsContactDetails> contactList) {
		this.contactList = contactList;
	}

	public List<CsAccountDetails> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<CsAccountDetails> accountList) {
		this.accountList = accountList;
	}

	public boolean isImportbtnflag() {
		return importbtnflag;
	}

	public void setImportbtnflag(boolean importbtnflag) {
		this.importbtnflag = importbtnflag;
	}
}
