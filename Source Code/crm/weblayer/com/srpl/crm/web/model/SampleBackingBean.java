package com.srpl.crm.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import com.srpl.crm.ejb.entity.SampleTableORM;
import com.srpl.um.ejb.entity.UmParameter;
import com.srpl.um.ejb.request.CompanyDAO;
import com.srpl.um.ejb.request.ParameterDAO;
import com.srpl.crm.ejb.request.SampleDAO;
import com.srpl.crm.web.model.common.ColumnModel;
import com.bitguiders.util.jsf.JSFBeanInterface;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;


@ManagedBean
public class SampleBackingBean extends JSFBeanSupport implements JSFBeanInterface{
  private double amount;
  private String name;
  private String email;
  private String phone;
  private String cnic;
  private Date date;
  private ArrayList<String> titles;
  // Sample list used by datatableHammad
  private List<SampleTableORM> sampleList;
  // Columns to be Displayed in Datable
  public List<ColumnModel> columns;
  
	@EJB
	ParameterDAO dao;
	@EJB CompanyDAO companyDao;
	@EJB SampleDAO sampleDao;
	
    public List<UmParameter> getList() {
	  return dao.list(new Long(38));
  }  
    public List<UmParameter> getProvincesList() {
    	
    	//TODO do some thing on the basis of id
      List<UmParameter> list = dao.list(new Long(38));
      for(UmParameter p: list){
    	  p.setParameterTitle(getId()+"--"+p.getParameterTitle());
      }
      return list;
  }  
    
  @PostConstruct
  public void init()
  {
	  //Populate sampleList from EJB
	  sampleList=sampleDao.list();
	 
	  // ADD COMLUMNS to displayed in Datatable
	  columns = new ArrayList<ColumnModel>();
	  columns.add(new ColumnModel("title", "TITLE"));
	  columns.add(new ColumnModel("value", "VALUE"));
  }
    
  public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCnic() {
		return cnic;
	}

	public void setCnic(String cnic) {
		this.cnic = cnic;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}


  public List<String> getTitles(){
	  titles = new ArrayList<String>();
	  for(int i=0;i<5;i++){
		  titles.add(" Title - "+i);
	  }
	  return titles;
  }
  public String actionListener() {
	  setCurrentAction(getParameter("action"),this.getClass());
	  switch(getCurrentAction()){
	  case WebConstants.ACTION_CREATE:
		  return WebConstants.ACTION_CRUD;
	  case WebConstants.ACTION_VIEW:
		  addWarning("view called");
		  //TODO
		  
		  return WebConstants.ACTION_CRUD;
	  case WebConstants.ACTION_EDIT:
		  
		  //TODO
		  return WebConstants.ACTION_CRUD;
	  case WebConstants.ACTION_SAVE:
		  
		  //TODO
		  
		  addMessage(" Saved Successfully ");
		  return WebConstants.ACTION_LIST;
	  case WebConstants.ACTION_CANCEL:
		  return WebConstants.ACTION_LIST;
	  case WebConstants.ACTION_DELETE:

		  //TODO

		  addMessage("Do you really want to delete ");
		  return WebConstants.ACTION_CRUD;
	  case WebConstants.ACTION_DELETE_CONFIRMED:

		  //TODO
		  addMessage(" Deleted Successfully ");
		  return WebConstants.ACTION_LIST;
	  case WebConstants.ACTION_UPDATE:
		  addMessage(" Updated Successfully ");
		  return WebConstants.ACTION_LIST;
	  }
	  return(null);
  }
  
  public List<SampleTableORM> getSampleList() {
	  return sampleList;
  }
  
  public List<ColumnModel> getColumns() {
		return columns;
	}

}
