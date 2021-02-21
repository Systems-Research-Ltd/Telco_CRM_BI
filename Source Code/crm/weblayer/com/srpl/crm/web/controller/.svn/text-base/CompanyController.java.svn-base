package com.srpl.crm.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;
import com.srpl.crm.web.controller.BeanFactory;
import com.srpl.um.ejb.entity.UmCompany;
import com.srpl.um.ejb.entity.UmUser;
import com.srpl.um.ejb.request.CompanyDAO;
import com.srpl.crm.web.common.SessionDataBean;
import com.srpl.crm.web.model.NewCompanyBackingBean;
import com.srpl.crm.web.model.NewUserBackingBean;
import com.srpl.crm.web.model.common.Address;
import com.srpl.crm.web.model.common.ColumnModel;
import com.srpl.um.ejb.request.UserDAO;

/**
 * @author Hammad Hassan Khan
 *
 */
@ManagedBean(name="companyController")
public class CompanyController extends JSFBeanSupport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static List<ColumnModel> companyColumns;
	public Boolean update = true;
	
	static{

		companyColumns=new ArrayList<ColumnModel>();
		companyColumns.add(new ColumnModel("companyId", "ID"));
		companyColumns.add(new ColumnModel("companyName", "TITLE"));
        companyColumns.add(new ColumnModel("companyEmail", "EMAIL"));
        companyColumns.add(new ColumnModel("companyPhone", "PHONE NO"));
	}

	/**
	 * Declare all the dao objects here
	 * 
	 */
	@EJB CompanyDAO companyDao;
	@EJB UserDAO userDao;
	

	/**
	 * All the class variables i.e. selected list are declared here
	 */
	private static ArrayList<NewCompanyBackingBean> companies;


	
	/**
	 * Getters Setters
	 */
	public ArrayList<NewCompanyBackingBean> getCompanies() {
		System.out.println("CompanyCB->getCompanies()");
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		
		ArrayList<NewCompanyBackingBean> myList = new ArrayList<NewCompanyBackingBean>();
		NewCompanyBackingBean bean;
		int companiesSize = 0;
		
		//Get packages from DB
		List<UmCompany> companiesDb = companyDao.listCompanies();
		try{
			companiesSize = companies.size();
		}
		catch (Exception e) {
			//handle exception
			System.out.println("packages not set yet, cant get size");
		}
		if(companiesSize != companiesDb.size() || update == true){
			//update packages list
			for(UmCompany x:companiesDb){
				bean = new NewCompanyBackingBean();
				convert2Bean(x, bean);
				myList.add(bean);
			}
			setUpdate(false);
			setCompanies(myList);
		}
		return companies;
	}

	public void setCompanies(ArrayList<NewCompanyBackingBean> companies) {
		try{
			CompanyController.companies.clear();
		}
		catch (Exception e) {
			//handle exception
			System.out.println("how can clear an empty list.");
		}
		CompanyController.companies = companies;
	}
	
	public Boolean getUpdate() {
		return update;
	}

	public void setUpdate(Boolean update) {
		this.update = update;
	}

	public List<ColumnModel> getCompanyColumns() {
		return companyColumns;
	}

	public void setCompanyColumns(List<ColumnModel> columns) {
		CompanyController.companyColumns = columns;
	}

	public String actionListener(){
		
		NewCompanyBackingBean bean = BeanFactory.getInstance().getCompanyBackingBean();
		NewUserBackingBean userBean = BeanFactory.getInstance().getUserBackingBean();
		UmCompany db;
		UmUser user;
		Long companyId;
		setCurrentAction(getAction(),this.getClass());
		switch (getCurrentAction()) {
		case WebConstants.ACTION_CREATE:
			bean.reset();
			return WebConstants.ACTION_CREATE;

		case WebConstants.ACTION_SAVE:
			try{
				String fName;
				String lName;
				if(userBean.getUserFname().indexOf(" ")== -1){
					fName = userBean.getUserFname();
					lName = "";
					userBean.setUserFname(fName);
					userBean.setUserLname(lName);
					
					
				}else{
					String[] value= userBean.getUserFname().split(" ");
					fName =value[0];
					lName =value[1];
					userBean.setUserFname(value[0]);
					userBean.setUserLname(value[1]);
				}
				
				db = new UmCompany();
				user = new UmUser();
				
				try{
				    uploadLogo();
				}catch(Exception e){
					throw new Exception();
				}
				convert2Db(bean, db);
				convert2DbUser(userBean, user);
				companyId = companyDao.createCompany(db,user);
				bean.setCompanyId(companyId);
				setUpdate(true);
				addMessage("Company Successfully Created");
			}
			catch (Exception e) {
				//handle exception
				System.out.println("Couldn't create");
				addError("Company Creation Failed.");
				setCreateAction();
				return WebConstants.ACTION_CRUD;
			}
			setListAction(true);
			return WebConstants.ACTION_LIST;

		case WebConstants.ACTION_VIEW:
			companyId = Long.valueOf(getParameter("row_id").toString());
			try{
				db = companyDao.companyDetails(companyId);
				user = userDao.companyAccountManager(companyId);
				convert2Bean(db, bean);
				convert2BeanUser(user, userBean);
				return WebConstants.ACTION_VIEW;
			}
			catch (Exception e) {
				// handle exception
				addError("Couldn't Load the Company.");
				System.out.println("Couldn't Load");
			}
			return WebConstants.ACTION_LIST;

		case WebConstants.ACTION_CANCEL:
			return WebConstants.ACTION_LIST;

		case WebConstants.ACTION_EDIT:
			companyId = Long.valueOf(getParameter("row_id").toString());
			try{
				db = companyDao.companyDetails(companyId);
				user = userDao.companyAccountManager(companyId);
				convert2Bean(db, bean);
				convert2BeanUser(user, userBean);
				return WebConstants.ACTION_EDIT;
			}
			catch (Exception e) {
				// handle exception
				addError("Couldn't Load the Company.");
				System.out.println("Couldn't Load");
			}
			return WebConstants.ACTION_LIST;

		case WebConstants.ACTION_UPDATE:
			companyId = Long.valueOf(getParameter("row_id").toString());
			bean.setCompanyId(companyId);
			try{
				db = new UmCompany();
				convert2Db(bean, db);
				companyId = companyDao.updateCompany(db);
				setUpdate(true);
				addMessage("Company Successfully Updated");
			}
			catch (Exception e) {
				//handle exception
				System.out.println("Couldn't create");
				addError("Package Updation Failed.");
			}
			return WebConstants.ACTION_LIST;

		case WebConstants.ACTION_DELETE:
			companyId = Long.valueOf(getParameter("row_id").toString());
			try{
				db = companyDao.companyDetails(companyId);
				user = userDao.companyAccountManager(companyId);
				convert2Bean(db, bean);
				convert2BeanUser(user, userBean);
				setDeleteConfirmedAction();
				return WebConstants.ACTION_DELETE;
			}
			catch (Exception e) {
				// handle exception
				addError("Couldn't Load the Company.");
				System.out.println("Couldn't Load");
			}
			setListAction(true);
			return WebConstants.ACTION_LIST;

		case WebConstants.ACTION_DELETE_CONFIRMED:
			companyId = Long.valueOf(getParameter("row_id").toString());
			try{
				companyDao.deleteCompany(companyId);
			}
			catch (Exception e) {
				// handle exception
				addError("Couldn't Delete the Company.");
				System.out.println("Deletion Failed.");
			}
			return WebConstants.ACTION_LIST;
			
		case WebConstants.ACTION_PARAMETER:
			companyId = Long.valueOf(getParameter("row_id"));
			SessionDataBean session = BeanFactory.getInstance().getSessionBean();
			session.setCompanyForParameter(companyId);
			return WebConstants.ACTION_PARAMETER;
		
		case WebConstants.ACTION_UPLOAD_LOGO:
			System.out.println("upload logo");
            return WebConstants.ACTION_UPLOAD_LOGO; 			

		default:
			setListAction(true);
			return WebConstants.ACTION_LIST;
		}
	}


	private void convert2Bean(UmCompany x, NewCompanyBackingBean bean) {
		
		bean.setCompanyAddress(x.getCompanyAddress());
		bean.setCompanyCity(x.getCompanyCity());
		bean.setCompanyCountry(x.getCompanyCountry());
		bean.setCompanyDetails(x.getCompanyDetails());
		bean.setCompanyEmail(x.getCompanyEmail());
		bean.setCompanyId(x.getCompanyId());
		bean.setCompanyName(x.getCompanyName());
		bean.setCompanyPhone(x.getCompanyPhone());
		bean.setCompanyState(x.getCompanyState());
		bean.setCompanyStatus(x.getCompanyStatus());
		bean.setCompanyZipcode(x.getCompanyZipcode());
		
		
		Address address = new Address(); 
		address = BeanFactory.getInstance().getAddressBean();
		address.stateAL(bean.getCompanyCountry());
		//populate the cities according to the value of state
		address.cityAL(bean.getCompanyState());
	}
	
	private void convert2Db(NewCompanyBackingBean bean, UmCompany db){

		db.setCompanyAddress(bean.getCompanyAddress());
		db.setCompanyCity(bean.getCompanyCity());
		db.setCompanyCountry(bean.getCompanyCountry());
		db.setCompanyDetails(bean.getCompanyDetails());
		if(bean.getFile() != null){
			db.setCompanyLogo(bean.getFile().getFileName());
		}
		db.setCompanyEmail(bean.getCompanyEmail());
		db.setCompanyId(bean.getCompanyId());
		db.setCompanyName(bean.getCompanyName());
		db.setCompanyPhone(bean.getCompanyPhone());
		db.setCompanyState(bean.getCompanyState());
		db.setCompanyStatus(bean.getCompanyStatus());
		db.setCompanyZipcode(bean.getCompanyZipcode());
	}
	
	private void convert2DbUser(NewUserBackingBean bean, UmUser db){
		
		db.setIsFranchiseUser(bean.getIsFranchiseUser());
		//db.setUmUserGroups(bean.get)
		db.setUserAddress(db.getUserAddress());
		db.setUserCity(bean.getUserCity());
		db.setUserCountry(bean.getUserCountry());
		db.setUserEmail(bean.getUserEmail());
		db.setUserFname(bean.getUserFname());
		db.setUserId(bean.getUserId());
		db.setUserJobtitle(bean.getUserJobtitle());
		db.setUserLname(bean.getUserLname());
		db.setUserName(bean.getUserName());
		db.setUserPhone(bean.getUserPhone());
		db.setUserState(bean.getUserState());
		db.setUserStatus(bean.getUserStatus());
		db.setUserZipcode(bean.getUserZipcode());
		db.setUserPassword(bean.getUserPassword());
	}
	
	private void convert2BeanUser(UmUser db, NewUserBackingBean bean){

		bean.setIsFranchiseUser(db.getIsFranchiseUser());
		//bean.setUmUserGroups(db.get)
		bean.setUserAddress(db.getUserAddress());
		bean.setUserCity(db.getUserCity());
		bean.setUserCountry(db.getUserCountry());
		bean.setUserEmail(db.getUserEmail());
		bean.setUserFname(db.getUserFname());
		bean.setUserId(db.getUserId());
		bean.setUserJobtitle(db.getUserJobtitle());
		bean.setUserLname(db.getUserLname());
		bean.setUserName(db.getUserName());
		bean.setUserPhone(db.getUserPhone());
		bean.setUserState(db.getUserState());
		bean.setUserStatus(db.getUserStatus());
		bean.setUserZipcode(db.getUserZipcode());
		bean.setUserPassword(db.getUserPassword());
	}
	
	
	public String getCompanyName(long id) {
		if(id != 0) {
			try {
				//System.out.println("company id is"+id +" company dao object "+companyDao);
				UmCompany c = companyDao.companyDetails(id);
				//System.out.println("Company name is"+c.getCompanyName());
				return c.getCompanyName();
			} catch (Exception e) {
				System.out.println("Could not get company name");
				return "";
			}
		}
		return "";
	}
	public ArrayList<NewCompanyBackingBean> compparam() {
		ArrayList<NewCompanyBackingBean> myList = new ArrayList<NewCompanyBackingBean>();
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		NewCompanyBackingBean ug;
		List<UmCompany> uDB = companyDao.listCompanies();
		System.out.println("Group List found.");
		System.out.println(uDB.size());
		for (UmCompany us : uDB) {
			ug = new NewCompanyBackingBean();

			convert2Bean(us, ug);
			myList.add(ug);

		}

		System.out.println(myList.size());
		return myList;
	}
	public void uploadLogo() throws Exception{  
		try{
			System.out.println("uploadlogo called...");
			NewCompanyBackingBean companyBean = BeanFactory.getInstance().getCompanyBackingBean();
			String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
			if(companyBean.getFile() == null){
				return;
			}
		    String fileName = companyBean.getFile().getFileName();
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
		           default :  addError("."+fileExtension+" File type not allowed for company logo");
		                      throw new Exception();
		               
		    }
		    File outputFile = new File(path + "resources/uploadedlogo/" + fileName);
		    InputStream inputStream;
		    OutputStream outputStream;
		    byte buf[] = new byte[1024];
		    int length;
		    inputStream = companyBean.getFile().getInputstream();
		    outputStream = new FileOutputStream(outputFile);
		    while ((length = inputStream.read(buf)) > 0){
			    outputStream.write(buf, 0, length);
		    }
		    
		   // companyDao.updateCompanyLogo(sessionBean.getCompanyId(), companyBean.getFile().getFileName());
		    inputStream.close();
		    outputStream.close();
		    
		   // addMessage("Logo uploaded successfully");
            
		}catch(Exception e){
           System.out.println("CompanyController uploadLogo() Exception occured");
          // addError("Upload failed");
           System.out.println(e.getMessage());
           throw new Exception();
           
		}
    }
	
}
