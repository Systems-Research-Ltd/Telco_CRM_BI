package com.srpl.crm.web.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.bitguiders.util.jsf.JSFBeanInterface;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;
import com.srpl.crm.web.common.AjaxListStructure;
import com.srpl.crm.web.common.InnerTabs;
import com.srpl.um.ejb.entity.UmFranchise;
import com.srpl.um.ejb.request.FranchiseDAO;
import com.srpl.um.web.common.SessionDataBean;
import com.srpl.um.web.controller.BeanFactory;
@ManagedBean(name = "franchiseBackingBean")
public class FranchiseBackingBean  extends JSFBeanSupport  implements
JSFBeanInterface,
Serializable  {
private static final long serialVersionUID = 1L;
	
	private Long franchiseId;
	private Long companyId;
	@NotBlank(message = "Franchise Address is Required.")
	private String franchiseAddress;
	@NotNull(message = "Franchise City is Required.")
	@Min(message = "Franchise City is Required.", value = 1)
	private Integer franchiseCity;
	@NotNull(message = "Franchise Country is Required.")
	@Min(message = "Franchise Country is Required.", value = 1)
	private Integer franchiseCountry;
	private String franchiseDetails;
	private Integer franchiseState;
	private Boolean franchiseStatus;
	@NotBlank(message = "Franchise Title is Required.")
	private String franchiseTitle;
	private Boolean isLocation;
	private Long longId;
	//Navigation and all
	private SessionDataBean session;
	
	@EJB
	FranchiseDAO franchiseDAO;
	public FranchiseBackingBean(){
		setCurrentAction(WebConstants.ACTION_SECURITY,this.getClass());
		session = BeanFactory.getInstance().getSessionBean();	
	}
	public Long getFranchiseId() {
		return franchiseId;
	}
	public void setFranchiseId(Long franchiseId) {
		this.franchiseId = franchiseId;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public String getFranchiseAddress() {
		return franchiseAddress;
	}
	public void setFranchiseAddress(String franchiseAddress) {
		this.franchiseAddress = franchiseAddress;
	}
	public Integer getFranchiseCity() {
		return franchiseCity;
	}
	public void setFranchiseCity(Integer franchiseCity) {
		this.franchiseCity = franchiseCity;
	}
	public Integer getFranchiseCountry() {
		return franchiseCountry;
	}
	public void setFranchiseCountry(Integer franchiseCountry) {
		this.franchiseCountry = franchiseCountry;
	}
	public String getFranchiseDetails() {
		return franchiseDetails;
	}
	public void setFranchiseDetails(String franchiseDetails) {
		this.franchiseDetails = franchiseDetails;
	}
	public Integer getFranchiseState() {
		return franchiseState;
	}
	public void setFranchiseState(Integer franchiseState) {
		this.franchiseState = franchiseState;
	}
	public Boolean getFranchiseStatus() {
		return franchiseStatus;
	}
	public void setFranchiseStatus(Boolean franchiseStatus) {
		this.franchiseStatus = franchiseStatus;
	}
	public String getFranchiseTitle() {
		return franchiseTitle;
	}
	public void setFranchiseTitle(String franchiseTitle) {
		this.franchiseTitle = franchiseTitle;
	}
	public Boolean getIsLocation() {
		return isLocation;
	}
	public void setIsLocation(Boolean isLocation) {
		this.isLocation = isLocation;
	}
	public Long getLongId() {
		return longId;
	}
	public void setLongId(Long longId) {
		this.longId = longId;
	}
	public SessionDataBean getSession() {
		return session;
	}
	public void setSession(SessionDataBean session) {
		this.session = session;
	}
	
	public void franchiseDetails() {
		System.out.println("++"+ session.getFranchiseModule_selectedFranchise());
		if (session.getFranchiseModule_selectedFranchise()!= 0L) {
			loadFranchise(session.getFranchiseModule_selectedFranchise());
			changeTabPath(0, "/view/um/admin/franchise/franchiseForm.xhtml");
			setViewAction();
		} else {
			session.resetFranchiseModule();
		}
	}
	
	@PostConstruct
	public void postConstruct() {
		String row_id=getParameter("row_id");
		if(!row_id.isEmpty())
		{
			session.setFranchiseModule_selectedFranchise(Long.parseLong(row_id));
		}
		System.out.println("Post construct called in Franchise");
		String act = getAction();
		if (act.equals("")) {
			// no action was called, load group data
			franchiseDetails();
			reset();
			setViewAction();
		}else if(act.equals("actionAjax")){
			setDisabled(false);
		}
	}
	
	private void changeTabPath(int index, String path) {
		InnerTabs d = session.getFranchiseTabs().get(index);
		d.setPath(path);
		session.getFranchiseTabs().set(index, d);
		
		try {
			if (getParameter("fromListing").equals("fromListing")) {
				// don't update index
			} else {
				session.setFranchiseModule_tabIndex(0);
			}
		} catch (Exception e) {
			session.setFranchiseModule_tabIndex(0);
		}
	}
	
	public void loadFranchise(long id) {
		FranchiseBackingBean bean = this;
		UmFranchise db;
		try {
			db = franchiseDAO.details(id);
			convert2Bean(db, bean);


		} catch (Exception e) {
			changeTabPath(0, "/view/um/admin/franchise/franchiseNoSelection.xhtml");
		}
	}
	
	public void resetBean() {
		setFranchiseId(0L);
		setFranchiseAddress("");
		setFranchiseCountry(0);
		setFranchiseState(0);
		setFranchiseCity(0);
		setFranchiseDetails("");
		setFranchiseStatus(null);
		setFranchiseTitle("");
	}
	
	@Override
	public void setViewAction() {
		super.setViewAction();
		setCancelAction(false);
		setResetAction(false);
	}
	
	@Override
	public String actionListener() {
		// TODO Auto-generated method stub
		long franchise_id;
		 UmFranchise db;
		 FranchiseBackingBean bean= this;
		 setCurrentAction(getParameter("action"),this.getClass());
		 switch(getCurrentAction()){
		 case WebConstants.ACTION_CREATE:
				changeTabPath(0, "/view/um/admin/franchise/franchiseForm.xhtml");
				BeanFactory.getInstance().getSessionBean().setSelectedCountry(0);
				BeanFactory.getInstance().getSessionBean().setSelectedState(0);
				resetBean();
				break;
		 case WebConstants.ACTION_SAVE:
			  //TODO
			 createFranchise();
			 break;
		 case WebConstants.ACTION_VIEW:
				franchiseDetails();
				reset();
				setViewAction();
				break;
		 case WebConstants.ACTION_CANCEL:
			 franchiseDetails();
				reset();
				setViewAction();
				break;
		 case WebConstants.ACTION_EDIT:
				franchise_id = Long.valueOf(getParameter("franchise_id"));
				loadFranchise(franchise_id);
				changeTabPath(0, "/view/um/admin/franchise/franchiseForm.xhtml");
				break;
		 case WebConstants.ACTION_UPDATE:
			 franchiseUpdate();
			 break;
		 case WebConstants.ACTION_DELETE:
			 franchiseDetails();
				reset();
				setDeleteAction();
				break;
		  case WebConstants.ACTION_DELETE_CONFIRMED:
			  //TODO
			  setLongId(Long.valueOf(getParameter("franchise_id").toString()));
				try{
					franchiseDAO.remove(getLongId());
					addMessage(getProperty("franchise.successfully.deleted"));
				}
				catch (Exception e) {
					// handle exception
					addError(getProperty("franchise.delete.fail"));
				}
				session.setFranchiseModule_selectedFranchise(0L);
				franchiseDetails();
				reset();
				setViewAction();
			  return null;
		 }
		return (null);
	}

	public ArrayList<AjaxListStructure> getList() {
		ArrayList<AjaxListStructure> myList = new ArrayList<AjaxListStructure>();
		AjaxListStructure u;

		List<UmFranchise> franchiseDb = null;
		try {
			franchiseDb = franchiseDAO.list(session.getCompanyId());
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (UmFranchise db : franchiseDb) {
			u = new AjaxListStructure();
			u.setId(db.getFranchiseId());
			u.setLabel(db.getFranchiseTitle());
			myList.add(u);
		}
		if (myList.size() == 0) {
			u = new AjaxListStructure();
			u.setId(0L);
			u.setLabel(getProperty("no.franchise.found"));
			myList.add(u);
		}
		return myList;
	}

	private void convert2Bean(UmFranchise db, FranchiseBackingBean bean) {

		bean.setCompanyId(db.getUmCompany().getCompanyId());
		bean.setFranchiseAddress(db.getFranchiseAddress());
		bean.setFranchiseCity(db.getFranchiseCity());
		bean.setFranchiseCountry(db.getFranchiseCountry());
		bean.setFranchiseDetails(db.getFranchiseDetails());
		bean.setFranchiseId(db.getFranchiseId());
		bean.setFranchiseState(db.getFranchiseState());
		bean.setFranchiseStatus(db.getFranchiseStatus());
		bean.setFranchiseTitle(db.getFranchiseTitle());
		bean.setIsLocation(db.getIsLocation());

		BeanFactory.getInstance().getSessionBean().setSelectedCountry(bean.getFranchiseCountry());
		BeanFactory.getInstance().getSessionBean().setSelectedState(bean.getFranchiseState());
	}
	private void convert2Db(FranchiseBackingBean bean, UmFranchise db) {

		db.setFranchiseAddress(bean.getFranchiseAddress());
		db.setFranchiseCity(bean.getFranchiseCity());
		db.setFranchiseCountry(bean.getFranchiseCountry());
		db.setFranchiseDetails(bean.getFranchiseDetails());
		db.setFranchiseId(bean.getFranchiseId());
		db.setFranchiseState(bean.getFranchiseState());
		db.setFranchiseStatus(bean.getFranchiseStatus());
		db.setFranchiseTitle(bean.getFranchiseTitle());
		db.setIsLocation(bean.getIsLocation());
	}
	public void franchiseUpdate() {
		FranchiseBackingBean bean = this;
		try {
			UmFranchise db = new UmFranchise();
			 convert2Db(bean, db);
			franchiseDAO.updates(db, session.getCompanyId());
				reset();
			setViewAction();
			  addMessage(getProperty("franchise.successfully.updated"));
		} catch (Exception createExp) {
			// handle exception
			addError(getProperty("franchise.update.fail"));
			reset();
			setEditAction();
		}
	}
	
	public void createFranchise() {
		try {
			UmFranchise db = new UmFranchise();
			FranchiseBackingBean bean = this;
			convert2Db(bean, db);
			db.setFranchiseId(null);
		franchiseId = franchiseDAO.create(db, session.getCompanyId());
		addMessage(getProperty("franchise.successfully.created"));
			session.setUserModule_selectedUser(franchiseId);
			
			reset();
			setViewAction();
		} catch (Exception createExp) {
			// handle exception
			addError(getProperty("franchise.creation.fail"));
			reset();
			setCreateAction();
		}
	}
}
