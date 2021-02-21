/*package com.srpl.crm.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import com.lmkr.crm.web.common.SessionDataBean;
import com.bitguiders.util.jsf.WebConstants;
import com.lmkr.crm.web.controller.BeanFactory;
import com.lmkr.crm.web.model.common.Address;
import com.lmkr.crm.web.model.common.ColumnModel;
import com.srpl.crm.ejb.entity.UmCompany;
import com.srpl.crm.ejb.request.CompanyDAOh;
import com.srpl.crm.ejb.request.UserDAO;
import com.srpl.crm.web.model.NewCompanyBackingBean;
import com.srpl.crm.web.model.ReferenceBackingBean;
import com.bitguiders.util.jsf.JSFBeanSupport;

*//**
 * @author Hammad Hassan Khan
 *
 *//*
@ManagedBean(name="referenceController")
public class ReferenceController extends JSFBeanSupport implements Serializable {

	*//**
	 * 
	 *//*
	private static final long serialVersionUID = 1L;
	public static List<ColumnModel> columns;
	public Boolean update = true;
	
	static{

		columns=new ArrayList<ColumnModel>();
		columns.add(new ColumnModel("companyId", "ID"));
		columns.add(new ColumnModel("companyName", "TITLE"));
        columns.add(new ColumnModel("companyEmail", "EMAIL"));
        columns.add(new ColumnModel("companyPhone", "PHONE NO"));
	}

	@PostConstruct
	public void init(){
		String oldAction;
		try{
			oldAction = getParameter("old_action");
			switch(oldAction){
			case WebConstants.ACTION_CREATE:
				setCreateAction();
				break;
			case WebConstants.ACTION_VIEW:
				setViewAction();
				break;
			case WebConstants.ACTION_EDIT:
				setEditAction();
				break;
			case WebConstants.ACTION_DELETE:
				setDeleteAction();
				break;
			default:
				setViewAction();
			}
		}
		catch(Exception e){
			System.out.println("exception on old_action.");
		}
		
	}
	*//**
	 * Declare all the dao objects here
	 * 
	 *//*
	@EJB CompanyDAOh companyDao;
	@EJB UserDAO userDao;
	

	*//**
	 * All the class variables i.e. selected list are declared here
	 *//*
	private ArrayList<ReferenceBackingBean> references;


	
	*//**
	 * Getters Setters
	 *//*
	public ArrayList<ReferenceBackingBean> getReferences() {
		System.out.println("ReferenceCB->getReferences()");
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		
		ArrayList<ReferenceBackingBean> myList = new ArrayList<ReferenceBackingBean>();
		ReferenceBackingBean bean;
		int referencesSize = 0;
		
		//Get packages from DB
		List<ORM> referenceDb = referenceDao.list();
		try{
			referencesSize = references.size();
		}
		catch (Exception e) {
			//handle exception
			System.out.println("references not set yet, cant get size");
		}
		if(referencesSize != referenceDb.size() || update == true){
			//update packages list
			for(ORM x:referenceDb){
				bean = new ReferenceBackingBean();
				convert2Bean(x, bean);
				myList.add(bean);
			}
			setUpdate(false);
			setReferences(myList);
		}
		return references;
	}

	public void setReferences(ArrayList<ReferenceBackingBean> references) {
		try{
			this.references.clear();
		}
		catch (Exception e) {
			//handle exception
			System.out.println("how can clear an empty list.");
		}
		this.references = references;
	}
	
	public Boolean getUpdate() {
		return update;
	}

	public void setUpdate(Boolean update) {
		this.update = update;
	}

	public List<ColumnModel> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnModel> columns) {
		ReferenceController.columns = columns;
	}

	public String actionListener(){
		
		NewCompanyBackingBean bean = BeanFactory.getInstance().getCompanyBackingBean();
		reset();
		
		ORM db;
		Long id;
		
		switch (getAction()) {
		case WebConstants.ACTION_CREATE:
			bean.reset();
			setCreateAction();
			return WebConstants.ACTION_CREATE;

		case WebConstants.ACTION_SAVE:
			try{
				db = new ORM();
				convert2Db(bean, db);
				id = Dao.create(db);
				bean.setId(id);
				setUpdate(true);
				setListAction(true);
				addMessage("Reference Successfully Created");
			}
			catch (Exception e) {
				//handle exception
				System.out.println("Couldn't create");
				addError("Reference Creation Failed.");
			}
			setListAction(true);
			return WebConstants.ACTION_LIST;

		case WebConstants.ACTION_VIEW:
			id = Long.valueOf(getParameter("row_id").toString());
			try{
				db = Dao.details(id);
				convert2Bean(db, bean);
				setViewAction();
				return WebConstants.ACTION_VIEW;
			}
			catch (Exception e) {
				// handle exception
				addError("Couldn't Load the Company.");
				System.out.println("Couldn't Load");
			}
			setListAction(true);
			return WebConstants.ACTION_LIST;

		case WebConstants.ACTION_CANCEL:
			setListAction(true);
			return WebConstants.ACTION_LIST;

		case WebConstants.ACTION_EDIT:
			id = Long.valueOf(getParameter("row_id").toString());
			try{
				db = Dao.details(id);
				convert2Bean(db, bean);
				setEditAction();
				return WebConstants.ACTION_EDIT;
			}
			catch (Exception e) {
				// handle exception
				addError("Couldn't Load the Company.");
				System.out.println("Couldn't Load");
			}
			setListAction(true);
			return WebConstants.ACTION_LIST;

		case WebConstants.ACTION_UPDATE:
			id = Long.valueOf(getParameter("row_id").toString());
			bean.setCompanyId(id);
			try{
				db = new ORM();
				convert2Db(bean, db);
				id = Dao.update(db);
				setUpdate(true);
				addMessage("Reference Successfully Updated");
			}
			catch (Exception e) {
				//handle exception
				System.out.println("Couldn't create");
				addError("Package Updation Failed.");
			}
			setListAction(true);
			return WebConstants.ACTION_LIST;

		case WebConstants.ACTION_DELETE:
			id = Long.valueOf(getParameter("row_id").toString());
			try{
				db = Dao.details(id);
				convert2Bean(db, bean);
				setDeleteAction();
				return WebConstants.ACTION_DELETE;
			}
			catch (Exception e) {
				// handle exception
				addError("Couldn't Load the Reference.");
				System.out.println("Couldn't Load");
			}
			setListAction(true);
			return WebConstants.ACTION_LIST;

		case WebConstants.ACTION_DELETE_CONFIRMED:
			id = Long.valueOf(getParameter("row_id").toString());
			try{
				Dao.delete(id);
			}
			catch (Exception e) {
				// handle exception
				addError("Couldn't Delete the Company.");
				System.out.println("Deletion Failed.");
			}
			setListAction(true);
			return WebConstants.ACTION_LIST;

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
		db.setCompanyEmail(bean.getCompanyEmail());
		db.setCompanyId(bean.getCompanyId());
		db.setCompanyName(bean.getCompanyName());
		db.setCompanyPhone(bean.getCompanyPhone());
		db.setCompanyState(bean.getCompanyState());
		db.setCompanyStatus(bean.getCompanyStatus());
		db.setCompanyZipcode(bean.getCompanyZipcode());
	}	
}
*/