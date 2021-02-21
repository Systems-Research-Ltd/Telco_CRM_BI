package com.srpl.crm.web.model;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.bitguiders.util.jsf.JSFBeanInterface;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;
import com.srpl.crm.web.controller.BeanFactory;
import com.srpl.crm.web.common.SessionDataBean;
import com.srpl.crm.web.model.common.ColumnModel;
import com.srpl.um.ejb.request.FranchiseDAO;
import com.srpl.um.ejb.entity.UmFranchise;

/**
 * @author Waqas Ahmed
 * 
 */
@ManagedBean(name = "franchiseBackingBean")
public class FranchiseBackingBean extends JSFBeanSupport implements JSFBeanInterface {
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
	@Pattern(regexp="^[a-zA-z0-9_ ]*$", message="Only Alphanumeric and underscores are allowed in Title.")
	private String franchiseTitle;
	private Boolean isLocation;
	private Long longId;
	
	public Long getLongId() {
		return longId;
	}

	public void setLongId(Long longId) {
		this.longId = longId;
	}

	// private static final long serialVersionUID = 1L;
	public List<ColumnModel> columns;
    ArrayList<FranchiseBackingBean> franchisesList;

	@EJB
	FranchiseDAO franchiseDAO;

	{

		columns = new ArrayList<ColumnModel>();
		columns.add(new ColumnModel("franchiseId", "ID"));
		columns.add(new ColumnModel("franchiseTitle", "TITLE"));
		columns.add(new ColumnModel("isLocation", "LOCATION/FRANCHISE"));
	}

	public List<ColumnModel> getColumns() {
		return columns;
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

	/**
	 * Getters Setters
	 */
	public void setFranchisesList(ArrayList<FranchiseBackingBean> franchisesList) {
		this.franchisesList = franchisesList;
	}
	@Deprecated
	public List<FranchiseBackingBean> getFranchisesList() {
		
		return getList1();
}
	
	 public String actionListener() {
		 UmFranchise db;
		 FranchiseBackingBean bean = BeanFactory.getInstance().getFranchiseBackingBean();
		 SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		  setCurrentAction(getParameter("action"),this.getClass());
		  System.out.println("===========================================" + getCurrentAction());
		  switch(getCurrentAction()){
		  case WebConstants.ACTION_CREATE:
			  BeanFactory.getInstance().getSessionBean().setSelectedCountry(0);
			  BeanFactory.getInstance().getSessionBean().setSelectedState(0);
			  return WebConstants.ACTION_CRUD;
		  case WebConstants.ACTION_VIEW:
			  //TODO
			   setLongId(Long.valueOf(getParameter("row_id").toString()));
				try{
					db = franchiseDAO.details(getLongId());
					convert2Bean(db, bean);
				}
				catch (Exception e) {
					// handle exception
					addError("Couldn't Load the Franchise.");
					System.out.println("Couldn't Load");
				}
			  
			  return WebConstants.ACTION_CRUD;
		  case WebConstants.ACTION_EDIT:
			  
			  //TODO
			  setLongId(Long.valueOf(getParameter("row_id").toString()));
				try{
					db = franchiseDAO.details(getLongId());
					convert2Bean(db, bean);
				}
				catch (Exception e) {
					// handle exception
					addError("Couldn't Load the Company.");
					System.out.println("Couldn't Load");
				}
				setListAction(true);
				
			  return WebConstants.ACTION_CRUD;
		  case WebConstants.ACTION_SAVE:
			  
			  //TODO
			  try{
					db = new UmFranchise();
					convert2Db(bean, db);
					setLongId(franchiseDAO.create(db, session.getCompanyId()));
					bean.setFranchiseId(getLongId());
					setListAction(true);
					addMessage("Franchise Successfully Created");
				}
				catch (Exception e) {
					//handle exception
					System.out.println("Couldn't create");
					addError("Franchise Creation Failed.");
				}
				setListAction(true);
			  return WebConstants.ACTION_LIST;
		  case WebConstants.ACTION_CANCEL:
			  return WebConstants.ACTION_LIST;
		  case WebConstants.ACTION_DELETE:
			  setLongId(Long.valueOf(getParameter("row_id").toString()));
				try{
					db = franchiseDAO.details(getLongId());
					convert2Bean(db, bean);
					setDeleteConfirmedAction();
				}
				catch (Exception e) {
					// handle exception
					addError("Couldn't Load the Franchise.");
					System.out.println("Couldn't Load");
				}
			 
			  addMessage("Do you really want to delete ");
			  return WebConstants.ACTION_CRUD;
		  case WebConstants.ACTION_DELETE_CONFIRMED:
			  //TODO
			  setLongId(Long.valueOf(getParameter("row_id").toString()));
				try{
					franchiseDAO.remove(getLongId());
					addMessage("Franchise Successfully Deleted.");
				}
				catch (Exception e) {
					// handle exception
					addError("Couldn't Delete the Franchise.");
				}
			  return WebConstants.ACTION_LIST;
		  case WebConstants.ACTION_UPDATE:
			  setLongId(Long.valueOf(getParameter("row_id").toString()));
				bean.setFranchiseId(getLongId());
				try{
					db = new UmFranchise();
					convert2Db(bean, db);
					setLongId(franchiseDAO.updates(db, bean.getCompanyId()));
					addMessage("Franchise Successfully Updated");
				}
				catch (Exception e) {
					//handle exception
					System.out.println("Couldn't create");
					addError("Franchise Updation Failed.");
				}
				setListAction(true);
			  return WebConstants.ACTION_LIST;
		  }
		  return(null);
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

	
	public List<FranchiseBackingBean> getList1() {
		// TODO Auto-generated method stub
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		Long companyId = 0L;
		try{
			companyId = session.getCompanyId();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		franchisesList = new ArrayList<FranchiseBackingBean>();
		FranchiseBackingBean bean;
		List<UmFranchise> franchiseDb = franchiseDAO.list(companyId);
		try{
			for(UmFranchise x:franchiseDb){
				bean = new FranchiseBackingBean();
				convert2Bean(x, bean);
				franchisesList.add(bean);
			}
			setFranchisesList(franchisesList);
		}
		catch (Exception e) {
			//handle exception
			System.out.println("franchises not set yet, cant get size");
		}
		
		return franchisesList;
	}
	

	
	@Override
	public List<UmFranchise> getList() {
		// TODO Auto-generated method stub
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		Long companyId = 0L;
		try{
			companyId = session.getCompanyId();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		List<UmFranchise> franchiseDb = franchiseDAO.list(companyId);
		return franchiseDb;
	}

	

}
