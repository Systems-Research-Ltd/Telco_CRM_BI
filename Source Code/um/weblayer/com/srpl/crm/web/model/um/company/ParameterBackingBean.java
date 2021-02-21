package com.srpl.crm.web.model.um.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.bitguiders.util.jsf.JSFBeanInterface;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;
import com.srpl.crm.web.common.InnerTabs;
import com.srpl.crm.web.model.common.ColumnModel;
import com.srpl.um.ejb.entity.UmCompany;
import com.srpl.um.ejb.entity.UmParameter;
import com.srpl.um.ejb.exceptions.CompanyNotFoundException;
import com.srpl.um.ejb.request.CompanyDAO;
import com.srpl.um.ejb.request.ParameterDAO;
import com.srpl.um.web.common.SessionDataBean;
import com.srpl.um.web.controller.BeanFactory;
@ManagedBean(name = "parameterBackingBean")
public class ParameterBackingBean extends JSFBeanSupport implements
JSFBeanInterface, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SessionDataBean session;
	private int parameterId;
	@NotBlank(message = "Title is Required.")
	private String parameterTitle;
	@NotBlank(message = "Value is Required.")
	private String parameterValue;
	private Long companyId;
	private Long selectedCompany;
	private long selectedParameter;
	private static List<ColumnModel> columns;
	private List<UmParameter> parametersList;
	@Transient
	private String companyName;
	private int deleteConfirmedId = 0;
	private int updateRowId = 0;

	{

		columns = new ArrayList<ColumnModel>();
		columns.add(new ColumnModel("parameterTitle", this.getProperty("label.title")));
		columns.add(new ColumnModel("parameterValue", this.getProperty("title.value")));
		// columns.add(new ColumnModel("companyName", this.getProperty("title.company")));
	}

	@EJB
	ParameterDAO paramterDAO;
	@EJB
	CompanyDAO companyDao;
	
	public ParameterBackingBean(){
		session = BeanFactory.getInstance().getSessionBean();

	}
	public List<ColumnModel> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnModel> columns) {
		ParameterBackingBean.columns = columns;
	}

	public SessionDataBean getSession() {
		return session;
	}

	public void setSession(SessionDataBean session) {
		this.session = session;
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

	public Long getSelectedCompany() {
		return selectedCompany;
	}

	public void setSelectedCompany(Long selectedCompany) {
		this.selectedCompany = selectedCompany;
	}

	public long getSelectedParameter() {
		return selectedParameter;
	}

	public void setSelectedParameter(long selectedParameter) {
		this.selectedParameter = selectedParameter;
	}

	public List<UmParameter> getParametersList() {
		return parametersList;
	}

	public void setParametersList(List<UmParameter> parametersList) {
		this.parametersList = parametersList;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getDeleteConfirmedId() {
		return deleteConfirmedId;
	}

	public void setDeleteConfirmedId(int deleteConfirmedId) {
		this.deleteConfirmedId = deleteConfirmedId;
	}

	public int getUpdateRowId() {
		return updateRowId;
	}

	public void setUpdateRowId(int updateRowId) {
		this.updateRowId = updateRowId;
	}

	private void listParameterPage() {
		this.changeTabPath(1, "/view/um/admin/company/parameter/index.xhtml");
		setListAction(true);
	}

	private void changeTabPath(int index, String path) {
		InnerTabs d = session.getCompanyTabs().get(index);
		d.setPath(path);
		session.getCompanyTabs().set(index, d);
		session.setCompanyModule_tabIndex(1);

	}

	@Override
	public String actionListener() {
		ParameterBackingBean bean = this;
		UmParameter db;
		setCurrentAction(getParameter("action"), this.getClass());
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		switch (getCurrentAction()) {
		case WebConstants.ACTION_CREATE:
			// Goto Create Page
			this.changeTabPath(1,
					"/view/um/admin/company/parameter/parameterForm.xhtml");
			setCreateAction();
			break;
		case WebConstants.ACTION_SAVE:
			try {
				db = new UmParameter();
				convert2Db(db);
				paramterDAO.create(db);
				addMessage(getProperty("parameter.successfully.created"));
			} catch (Exception e) {
				// handle exception
				System.out.println("Couldn't create");
				addError(getProperty("parameter.creation.fail"));
			}
			this.changeTabPath(1,
					"/view/um/admin/company/parameter/index.xhtml");
			break;
		case WebConstants.ACTION_VIEW:
			// addWarning("view called");
			this.changeTabPath(1,
					"/view/um/admin/company/parameter/parameterForm.xhtml");
			setViewAction();
			setId(Integer.valueOf(getParameter("row_id").toString()));
			session.setCompanyModule_selectedParameter(getId());
			try {
				db = paramterDAO.details(getId());
				convert2Bean(db);
				setViewAction();
			} catch (Exception e) {
				addError(getProperty("company.load.fail"));
			}
			break;

		case WebConstants.ACTION_EDIT:
			this.changeTabPath(1,
					"/view/um/admin/company/parameter/parameterForm.xhtml");
			setEditAction();
			setId(Integer.valueOf(getParameter("row_id").toString()));
			session.setCompanyModule_selectedParameter(getId());
			try {
				db = paramterDAO.details(getId());
				convert2Bean(db);
			} catch (Exception e) {
				addError(getProperty("company.load.fail"));
			}

			break;
		case WebConstants.ACTION_UPDATE:
			updateRowId = session.getCompanyModule_selectedParameter();
			bean.setParameterId(updateRowId);
			try {
				db = new UmParameter();
				convert2Db(db);
				setId(paramterDAO.updates(db));
				addMessage(getProperty("parameter.successfully.updated"));
			} catch (Exception e) {
				// handle exception
				System.out.println("Couldn't create");
				addError(getProperty("parameter.updation.fail"));
			}
			// setListAction(true);
			this.changeTabPath(1,
					"/view/um/admin/company/parameter/index.xhtml");
			setUpdateAction();
			break;
		case WebConstants.ACTION_DELETE:
			// addWarning("Delete called");
			this.changeTabPath(1,
					"/view/um/admin/company/parameter/parameterForm.xhtml");
			setDeleteAction();
			setId(Integer.valueOf(getParameter("row_id").toString()));
			deleteConfirmedId = getId();
			try {
				db = paramterDAO.details(getId());
				convert2Bean(db);
				setDeleteAction();
			} catch (Exception e) {
				addError(getProperty("company.load.fail"));
			}
			break;
		case WebConstants.ACTION_DELETE_CONFIRMED:

			deleteConfirmedId = Integer.valueOf(params.get("row_id"));
			try {
				paramterDAO.delete(deleteConfirmedId);
				addMessage(getProperty("parameter.delete.successfully"));
			} catch (Exception e) {
				addError(getProperty("parameter.could.not.delete"));
				System.out.println("Deletion Failed.");
			}
			this.changeTabPath(1,
					"/view/um/admin/company/parameter/index.xhtml");
			setDeleteConfirmedAction();
			break;
		case WebConstants.ACTION_CANCEL:
			listParameterPage();
			break;

		}
		return null;
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

	public ArrayList<CompanyBackingBean> companyInParam()
			throws CompanyNotFoundException {
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		ArrayList<CompanyBackingBean> myList = new ArrayList<CompanyBackingBean>();
		CompanyBackingBean cBean;
		try {
			List<UmCompany> cDB = companyDao.listCompanies();

			for (UmCompany co : cDB) {
				cBean = new CompanyBackingBean();
				cBean.setCompanyId(co.getCompanyId());
				cBean.setCompanyName(co.getCompanyName());
				myList.add(cBean);

			}
		} catch (Exception e) {
			this.addError("No Company Created Yet.");

		}
		return myList;
	}

	@Override
	public List<UmParameter> getList() {
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		parametersList = paramterDAO.list(session
				.getCompanyModule_selectedCompany());
		return parametersList;
	}
}
