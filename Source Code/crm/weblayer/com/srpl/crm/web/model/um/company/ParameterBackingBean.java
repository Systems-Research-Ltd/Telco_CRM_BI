package com.srpl.crm.web.model.um.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.bitguiders.util.jsf.JSFBeanInterface;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;
import com.srpl.crm.ejb.exceptions.CompanyNotFoundException;
import com.srpl.crm.web.common.SessionDataBean;
import com.srpl.crm.web.controller.BeanFactory;
import com.srpl.crm.web.model.NewCompanyBackingBean;
import com.srpl.crm.web.model.common.ColumnModel;
import com.srpl.um.ejb.entity.UmCompany;
import com.srpl.um.ejb.entity.UmParameter;
import com.srpl.um.ejb.request.CompanyDAO;
import com.srpl.um.ejb.request.ParameterDAO;

@javax.enterprise.context.SessionScoped
@ManagedBean(name = "parameterBackingBean")
public class ParameterBackingBean extends JSFBeanSupport implements
		JSFBeanInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int parameterId;
	//@NotBlank(message = "Title is Required.")
	private String parameterTitle;
	@NotNull(message = "Value is Required.")
	private String parameterValue;
	private Long companyId;
	private Long selectedCompany;
	@Transient
	private String companyName;
	private int deleteConfirmedId = 0;
	private int updateRowId = 0;
	private List<UmParameter> parametersList;
	ArrayList<ParameterBackingBean> dataTableList;
	/**
	 * 
	 * Getters and Setters
	 */

	@EJB
	ParameterDAO paramterDAO;
	@EJB
	CompanyDAO companyDao;

	public List<ColumnModel> columns;

	{

		columns = new ArrayList<ColumnModel>();
		columns.add(new ColumnModel("parameterTitle", "TITLE"));
		columns.add(new ColumnModel("parameterValue", "VALUE"));
//		columns.add(new ColumnModel("companyName", "COMPANY"));
	}

	public List<ColumnModel> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnModel> columns) {
		this.columns = columns;
	}

	public int getParameterId() {
		return parameterId;
	}

	public void setParameterId(int parameterId) {
		this.parameterId = parameterId;
	}

	public String getParameterTitle() {
		return parameterTitle;
	}

	public void setParameterTitle(String parameterTitle) {
		this.parameterTitle = parameterTitle;
	}

	public String getParameterValue() {
		return parameterValue;
	}

	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public Long getSelectedCompany() {
		return selectedCompany;
	}

	public void setSelectedCompany(Long selectedCompany) {
		this.selectedCompany = selectedCompany;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/*
	 * public void reset(){ parameterId = 0; parameterTitle = ""; parameterValue
	 * = 0L; companyId = null; }
	 */

	public List<UmParameter> getParametersList() {

		return parametersList;
	}

	public void setParametersList(List<UmParameter> paramtersList) {
		this.parametersList = paramtersList;
	}
	@Deprecated
	public List<ParameterBackingBean> getDataTableList() {
		return getList1();
	}

	public ArrayList<NewCompanyBackingBean> companyInParam()
			throws CompanyNotFoundException {
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		ArrayList<NewCompanyBackingBean> myList = new ArrayList<NewCompanyBackingBean>();
		NewCompanyBackingBean cBean;
		try {
			List<UmCompany> cDB = companyDao.listCompanies();

			for (UmCompany co : cDB) {
				cBean = new NewCompanyBackingBean();
				cBean.setCompanyId(co.getCompanyId());
				cBean.setCompanyName(co.getCompanyName());
				myList.add(cBean);

			}
		} catch (Exception e) {
			this.addError("No Company Created Yet.");

		}
		return myList;
	}

	public void setDataTableList(ArrayList<ParameterBackingBean> dataTableList) {
		this.dataTableList = dataTableList;
	}

	private void convert2Bean(UmParameter db) {
		convert2Bean(db, this);
	}

	private void convert2Bean(UmParameter db, ParameterBackingBean bean) {
		bean.setCompanyId(db.getCompanyId());
		bean.setCompanyName(paramterDAO.paramCompany(db.getCompanyId()));
		bean.setParameterId(db.getParameterId());
		bean.setParameterTitle(db.getParameterTitle());
		bean.setParameterValue(db.getParameterValue());
	}

	private void convert2Db(UmParameter db) {
		ParameterBackingBean bean = this;
		db.setCompanyId(bean.getCompanyId());
		db.setParameterId(bean.getParameterId());
		db.setParameterTitle(bean.getParameterTitle());
		db.setParameterValue(bean.getParameterValue());
	}

	public String actionListener() {
		setCurrentAction(getParameter("action"),this.getClass());
		UmParameter db;
		ParameterBackingBean bean = this;
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		switch (getCurrentAction()) {
		case WebConstants.ACTION_CREATE:
			return WebConstants.ACTION_CRUD;
		case WebConstants.ACTION_VIEW:
//			 addWarning("view called");
			setId(Integer.valueOf(getParameter("row_id").toString()));
			try {
				db = paramterDAO.details(getId());
				convert2Bean(db);
			} catch (Exception e) {
				addError("Couldn't Load the Company.");
			}
			return WebConstants.ACTION_CRUD;
		case WebConstants.ACTION_EDIT:
			setId(Integer.valueOf(getParameter("row_id").toString()));
			try {
				db = paramterDAO.details(getId());
				convert2Bean(db);
			} catch (Exception e) {
				addError("Couldn't Load the Company.");
			}

			return WebConstants.ACTION_CRUD;
		case WebConstants.ACTION_SAVE:
			try {

				db = new UmParameter();
				convert2Db(db);
				paramterDAO.create(db);
				addMessage("Parameter Successfully Created");
			} catch (Exception e) {
				// handle exception
				System.out.println("Couldn't create");
				addError("Parameter Creation Failed.");
			}
			return WebConstants.ACTION_LIST;
		case WebConstants.ACTION_CANCEL:
			return WebConstants.ACTION_LIST;
		case WebConstants.ACTION_DELETE:
//			 addWarning("Delete called");
			setId(Integer.valueOf(getParameter("row_id").toString()));
			deleteConfirmedId = getId();
			try {
				db = paramterDAO.details(getId());
				convert2Bean(db);
				setDeleteAction();
			} catch (Exception e) {
				addError("Couldn't Load the Company.");
			}
			return WebConstants.ACTION_CRUD;
		case WebConstants.ACTION_DELETE_CONFIRMED:
			
			deleteConfirmedId = Integer.valueOf(params.get("row_id"));
			try {
				paramterDAO.delete(deleteConfirmedId);
				addMessage("Parameter deleted successfully");
			} catch (Exception e) {
				addError("Couldn't Delete the Parameter.");
				System.out.println("Deletion Failed.");
			}
			return WebConstants.ACTION_LIST;
		case WebConstants.ACTION_UPDATE:
			updateRowId = Integer.valueOf(params.get("row_id"));
			bean.setParameterId(updateRowId);
			try {
				db = new UmParameter();
				convert2Db(db);
				setId(paramterDAO.updates(db));
				addMessage("Parameter Successfully Updated");
			} catch (Exception e) {
				// handle exception
				System.out.println("Couldn't create");
				addError("Parameter Updation Failed.");
			}
			setListAction(true);
			return WebConstants.ACTION_LIST;
		}

		return (null);
	}

	public List<ParameterBackingBean> getList1() {
		// TODO Auto-generated method stub
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		Long companyId = session.getCompanyForParameter();
		System.out.println("------------------------------------" + companyId);
		String companyName = companyDao.companyDetails(companyId)
				.getCompanyName();
		dataTableList = new ArrayList<ParameterBackingBean>();
		parametersList = paramterDAO.list(companyId);
		ParameterBackingBean bean;
		for (UmParameter umParameter : parametersList) {
			bean = new ParameterBackingBean();
			// set bean variables here
			bean.setParameterId(umParameter.getParameterId());
			bean.setParameterTitle(umParameter.getParameterTitle());
			bean.setCompanyName(companyName);
			bean.setCompanyId(companyId);
			bean.setParameterValue(umParameter.getParameterValue());
			dataTableList.add(bean);
		}
		return dataTableList;
	}
	
	@Override
	public List<UmParameter> getList() {
		// TODO Auto-generated method stub
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		Long companyId = session.getCompanyForParameter();
		System.out.println("------------------------------------" + companyId);
		parametersList = paramterDAO.list(companyId);
		return parametersList;
	}
	
}
