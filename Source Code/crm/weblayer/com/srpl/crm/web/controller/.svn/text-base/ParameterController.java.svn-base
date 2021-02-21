package com.srpl.crm.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import com.srpl.crm.web.controller.BeanFactory;
import com.srpl.um.ejb.entity.UmCompany;
import com.srpl.um.ejb.entity.UmParameter;
import com.srpl.crm.ejb.exceptions.CompanyNotFoundException;
import com.srpl.crm.web.common.SessionDataBean;
import com.srpl.crm.web.model.common.ColumnModel;
import com.srpl.crm.web.model.um.company.ParameterBackingBean;
import com.srpl.um.ejb.request.CompanyDAO;
import com.srpl.um.ejb.request.ParameterDAO;
import com.bitguiders.util.jsf.JSFBeanSupport;

/**
 * @author Hammad Hassan Khan
 * @param <NewCompanyBackingBean>
 * 
 */
@ManagedBean(name = "parameterController")
@Deprecated
public class ParameterController<NewCompanyBackingBean> extends JSFBeanSupport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static List<ColumnModel> columns;
	public Boolean update = true;

	static {

		columns = new ArrayList<ColumnModel>();
		columns.add(new ColumnModel("parameterTitle", "TITLE"));
		columns.add(new ColumnModel("parameterValue", "VALUE"));
		columns.add(new ColumnModel("companyName", "COMPANY"));
	}

/*	@PostConstruct
	public void init() {
		String oldAction;
		try {
			oldAction = getParameter("old_action");
			switch (oldAction) {
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
		} catch (Exception e) {
			System.out.println("exception on old_action.");
		}

	}*/

	/**
	 * Declare all the dao objects here
	 * 
	 */
	@EJB
	ParameterDAO dao;
	@EJB CompanyDAO companyDao;

	/**
	 * All the class variables i.e. selected list are declared here
	 */
	private ArrayList<ParameterBackingBean> parameters;

	/**
	 * Getters Setters
	 */
	public ArrayList<ParameterBackingBean> getParameters() {
		// System.out.println("ParameterCB->getParameters()");
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		Long companyId = session.getCompanyForParameter();
		ArrayList<ParameterBackingBean> myList = new ArrayList<ParameterBackingBean>();
		ParameterBackingBean bean;
		int parametersSize = 0;

		// Get packages from DB
		List<UmParameter> parameterDb = dao.list(companyId);
		try {
			parametersSize = parameters.size();
		} catch (Exception e) {
			// handle exception
			System.out.println("parameters not set yet, cant get size");
		}
		if (parametersSize != parameterDb.size() || update == true) {
			// update packages list
			for (UmParameter x : parameterDb) {
				bean = new ParameterBackingBean();
				convert2Bean(x, bean);
				myList.add(bean);
			}
			setUpdate(false);
			setParameters(myList);
		}
		if(parameters.size() > 0){
			System.out.println("the first parameter value is: " + parameters.get(0).getParameterValue());
			System.out.println("the company id is: " + companyId);
		}
		return parameters;
	}

	public void setParameters(ArrayList<ParameterBackingBean> parameters) {
		try {
			this.parameters.clear();
		} catch (Exception e) {
			// handle exception
			System.out.println("how can clear an empty list.");
		}
		this.parameters = parameters;
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
		ParameterController.columns = columns;
	}

	/*public String actionListener() {

		NewParameterBackingBean bean = BeanFactory.getInstance()
				.getParameterBackingBean();
		reset();

		UmParameter db;
		int id;
		switch (getAction()) {
		case WebConstants.ACTION_CREATE:
			bean.reset();
			setCreateAction();
			return WebConstants.ACTION_CREATE;

		case WebConstants.ACTION_SAVE:
			try {
				db = new UmParameter();
				convert2Db(bean, db);
				dao.create(db);
				setUpdate(true);
				setListAction(true);
				addMessage("Parameter Successfully Created");
			} catch (Exception e) {
				// handle exception
				System.out.println("Couldn't create");
				addError("Parameter Creation Failed.");
			}
			setListAction(true);
			return WebConstants.ACTION_LIST;

		case WebConstants.ACTION_VIEW:
			id = Integer.valueOf(getParameter("row_id").toString());
			try {
				db = dao.details(id);
				convert2Bean(db, bean);
				setViewAction();
				return WebConstants.ACTION_VIEW;
			} catch (Exception e) {
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
			id = Integer.valueOf(getParameter("row_id").toString());
			try {
				db = dao.details(id);
				convert2Bean(db, bean);
				setEditAction();
				return WebConstants.ACTION_EDIT;
			} catch (Exception e) {
				// handle exception
				addError("Couldn't Load the Company.");
				System.out.println("Couldn't Load");
			}
			setListAction(true);
			return WebConstants.ACTION_LIST;

		case WebConstants.ACTION_UPDATE:
			id = Integer.valueOf(getParameter("row_id").toString());
			bean.setParameterId(id);
			try {
				db = new UmParameter();
				convert2Db(bean, db);
				id = dao.updates(db);
				setUpdate(true);
				addMessage("Parameter Successfully Updated");
			} catch (Exception e) {
				// handle exception
				System.out.println("Couldn't create");
				addError("Parameter Updation Failed.");
			}
			setListAction(true);
			return WebConstants.ACTION_LIST;

		case WebConstants.ACTION_DELETE:
			id = Integer.valueOf(getParameter("row_id").toString());
			try {
				db = dao.details(id);
				convert2Bean(db, bean);
				setDeleteAction();
				return WebConstants.ACTION_DELETE;
			} catch (Exception e) {
				// handle exception
				addError("Couldn't Load the Parameter.");
				System.out.println("Couldn't Load");
			}
			setListAction(true);
			return WebConstants.ACTION_LIST;

		case WebConstants.ACTION_DELETE_CONFIRMED:
			id = Integer.valueOf(getParameter("row_id").toString());
			try {
				dao.delete(id);
				addMessage("Parameter deleted successfully");
			} catch (Exception e) {
				// handle exception
				addError("Couldn't Delete the Parameter.");
				System.out.println("Deletion Failed.");
			}
			setListAction(true);
			return WebConstants.ACTION_LIST;

		default:
			setListAction(true);
			return WebConstants.ACTION_LIST;
		}
	}*/

	
	private void convert2Bean(UmParameter db, ParameterBackingBean bean) {

		bean.setCompanyId(db.getCompanyId());
		bean.setCompanyName(dao.paramCompany(db.getCompanyId()));
		bean.setParameterId(db.getParameterId());
		bean.setParameterTitle(db.getParameterTitle());
		bean.setParameterValue(db.getParameterValue());
	}

	private void convert2Db(ParameterBackingBean bean, UmParameter db) {

		db.setCompanyId(bean.getCompanyId());
		db.setParameterId(bean.getParameterId());
		db.setParameterTitle(bean.getParameterTitle());
		db.setParameterValue(bean.getParameterValue());
	}
	public ArrayList<String> companyInParam() throws CompanyNotFoundException {
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		ArrayList<String> myList = new ArrayList<String>();
		try{
		List<UmCompany> cDB = companyDao.listCompanies();
		
		for (UmCompany co : cDB) {
			myList.add(co.getCompanyName());

		}
		}catch(Exception e){
			this.addError("No Contact Created Yet.");
			
		}
		return myList;
	}
}
