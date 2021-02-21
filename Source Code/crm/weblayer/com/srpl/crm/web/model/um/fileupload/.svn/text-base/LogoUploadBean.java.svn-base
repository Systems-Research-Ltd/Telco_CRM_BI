package com.srpl.crm.web.model.um.fileupload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;  
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;  
import org.primefaces.event.FileUploadEvent;  
import org.primefaces.model.UploadedFile;  

import com.srpl.crm.web.controller.BeanFactory;
import com.srpl.crm.web.model.IndexBackingBean;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.srpl.crm.ejb.entity.DocumentORM;
import com.srpl.crm.web.common.SessionDataBean;
import com.srpl.um.ejb.request.CompanyDAO;

@ManagedBean (name = "logoBean")
public class LogoUploadBean extends JSFBeanSupport{
	
	private UploadedFile file;
	
	SessionDataBean sessionBean = BeanFactory.getInstance().getSessionBean();
	
	@EJB 
	CompanyDAO companyDao;

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}
	
	
	public void uploadLogo() {  
		try{
			SessionDataBean sessionBean = BeanFactory.getInstance().getSessionBean();
			String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
			String fileName = file.getFileName();
		    String[] splitFile = fileName.split("\\.");
		    String fileExtension = splitFile[splitFile.length - 1];
		    switch(fileExtension){
		           case "gif" : break;
		           case "GIF" : break;
		           case "jpg" : break;
		           case "jpeg" : break;
		           case "JPG" : break;
		           case "JPEG" : break;
		           case "png" : break;
		           case "PNG" : break;
		           default :  addError("."+fileExtension+" File type not allowed");
		                      throw new Exception();
		               
		    }
		    File outputFile = new File(path + "resources/uploadedlogo/" + fileName);
		    InputStream inputStream;
		    OutputStream outputStream;
		    byte buf[] = new byte[1024];
		    int length;
		    inputStream = file.getInputstream();
		    outputStream = new FileOutputStream(outputFile);
		    while ((length = inputStream.read(buf)) > 0){
			    outputStream.write(buf, 0, length);
		    }
		    companyDao.updateCompanyLogo(sessionBean.getCompanyId(), file.getFileName());
		    inputStream.close();
		    outputStream.close();
		    addMessage("Logo uploaded successfully");
		}catch(Exception e){
           System.out.println("LogoUploadBean Exception occured");
           addError("Upload failed");
           System.out.println(e.getMessage());
		}
    }
	
	
	public String getLogo(){
		SessionDataBean sessionBean = BeanFactory.getInstance().getSessionBean();
		// to get the session SampleBackingBean is initializd b/c session is initialized in SampleBackingBean
		IndexBackingBean sampleBackingBean = BeanFactory.getInstance().getIndexBackingBean();
		String logo = companyDao.getCompanyLogo(sessionBean.getCompanyId());
		//String logoPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/")+"resources\\documents\\"+logo;
		//System.out.println("Logo path = "+logoPath);
		return logo;
	}
	
	
	

}
