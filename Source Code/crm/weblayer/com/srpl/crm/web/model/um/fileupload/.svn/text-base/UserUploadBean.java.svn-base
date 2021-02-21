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
import com.srpl.crm.common.utils.UmUserDetails;
import com.srpl.um.ejb.entity.UmUser;
import com.srpl.um.ejb.request.UserDAO;

@ManagedBean(name="userUploadBean")
@ViewScoped
public class UserUploadBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	List<UmUser> usersList;
	
	@EJB UserDAO userDao;
	
	boolean importbtnflag=false;
	public UserUploadBean()
	{
		
	}
	
	//Users
	public void handleFileUpload(FileUploadEvent event) {  
        try 
        {
			usersList = userDao.mapUsers(event.getFile().getInputstream());
			FacesMessage msg = new FacesMessage("Succesful!", event.getFile().getFileName() + " is uploaded.");  
		    FacesContext.getCurrentInstance().addMessage(null, msg);
		    importbtnflag=true;
		} 
        catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	public void importUsers(ActionEvent e)
	{
		userDao.importUsers(usersList);
		FacesMessage msg = new FacesMessage("Succesful!!","Users added to Database");  
        FacesContext.getCurrentInstance().addMessage(null, msg);
        importbtnflag=false;
        usersList=null;
	}
	
	
	
	
	
	
	// Getters and Setters
	public List<UmUser> getUsersList() {
		return usersList;
	}
	public void setUsersList(List<UmUser> usersList) {
		this.usersList = usersList;
	}

	public boolean isImportbtnflag() {
		return importbtnflag;
	}

	public void setImportbtnflag(boolean importbtnflag) {
		this.importbtnflag = importbtnflag;
	}

}
