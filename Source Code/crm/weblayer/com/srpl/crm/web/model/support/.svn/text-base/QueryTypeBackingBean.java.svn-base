package com.srpl.crm.web.model.support;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.srpl.crm.ejb.entity.SupportCaseORM;
import com.srpl.crm.ejb.entity.SupportQueryTypeORM;
import com.srpl.crm.ejb.exceptions.CaseNotFoundException;
import com.srpl.crm.ejb.exceptions.QueryTypeNotFoundException;
import com.srpl.crm.ejb.request.CaseDAO;
import com.srpl.crm.ejb.request.QueryTypeDAO;
import com.srpl.crm.web.common.SessionDataBean;
import com.srpl.crm.web.controller.BeanFactory;
import com.srpl.crm.web.model.common.ColumnModel;
import com.srpl.um.ejb.entity.UmCompany;
import com.srpl.um.ejb.entity.UmGroup;
import com.srpl.um.ejb.request.CompanyDAO;
import com.srpl.um.ejb.request.GroupDAO;
import com.bitguiders.util.jsf.JSFBeanInterface;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;

@ManagedBean(name = "queryTypeBean")
public class QueryTypeBackingBean extends JSFBeanSupport implements JSFBeanInterface,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long queryTypeId;
	private String queryTypeTitle;
	private String queryTypeAlias;
	private Long groupId;
	private UmGroup umGroup;
	private Long companyId;
	private UmCompany umCompany;
	
	private List<ColumnModel> columns;
	private List<SupportQueryTypeORM> queryTypeList;
	private Boolean renderQueryTypeForm = false;
	private SessionDataBean session;
	
	@EJB QueryTypeDAO queryTypeDao;
	@EJB GroupDAO groupDao;
	@EJB CaseDAO caseDao;
	@EJB CompanyDAO companyDao;
	
	
	public QueryTypeBackingBean(){
		System.out.println("QueryTypeBackingBean() called");
		columns=new ArrayList<ColumnModel>();
		columns.add(new ColumnModel("queryTypeId", "ID"));
        columns.add(new ColumnModel("queryTypeTitle", "Query Type"));
        columns.add(new ColumnModel("queryTypeAlias", "Query Type Alias"));

        session = BeanFactory.getInstance().getSessionBean();

	}
	
	public void loadQueryType(){
		System.out.println("QueryTypeBackingBean loadQueryType() called");
		Long l;
		Map<String,String> params=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		if(params.get("row_id")!=null){
			 l =Long.valueOf(params.get("row_id"));
		}else{
			 l=(long) 0;
		}		
		if(l > 0){
			try {
				SupportQueryTypeORM queryType = queryTypeDao.queryTypeDetails(l);
				this.queryTypeId = queryType.getQueryTypeId();
				this.queryTypeTitle = queryType.getQueryTypeTitle();
				this.queryTypeAlias = queryType.getQueryTypeAlias();
				try{
				 this.umGroup = queryType.getUmGroup();
				 this.groupId = queryType.getUmGroup().getGroupId();
				 this.umCompany = queryType.getUmCompany();
				 this.companyId = queryType.getUmCompany().getCompanyId();
				}catch(Exception e){
					
				}
				
			       
			} catch (Exception e) {
				System.out.println("exception while loading queryType.");
				e.printStackTrace();
			}
		}
	}
	
	
	public Long getQueryTypeId() {
		return queryTypeId;
	}
	public void setQueryTypeId(Long queryTypeId) {
		this.queryTypeId = queryTypeId;
	}

	public String getQueryTypeTitle() {
		return queryTypeTitle;
	}
	public void setQueryTypeTitle(String queryTypeTitle) {
		this.queryTypeTitle = queryTypeTitle;
	}

	public String getQueryTypeAlias() {
		return queryTypeAlias;
	}

	public void setQueryTypeAlias(String queryTypeAlias) {
		this.queryTypeAlias = queryTypeAlias;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public UmGroup getUmGroup() {
		return umGroup;
	}

	public void setUmGroup(UmGroup umGroup) {
		this.umGroup = umGroup;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public UmCompany getUmCompany() {
		return umCompany;
	}

	public void setUmCompany(UmCompany umCompany) {
		this.umCompany = umCompany;
	}

	public List<ColumnModel> getColumns() {
		return columns;
	}
	public void setColumns(List<ColumnModel> columns) {
		this.columns = columns;
	}

	


	public List<SupportQueryTypeORM> getQueryTypeList() {
		return queryTypeList;
	}

	public void setQueryTypeList(List<SupportQueryTypeORM> queryTypeList) {
		this.queryTypeList = queryTypeList;
	}

	public Boolean getRenderQueryTypeForm() {
		return renderQueryTypeForm;
	}

	public void setRenderQueryTypeForm(Boolean renderQueryTypeForm) {
		this.renderQueryTypeForm = renderQueryTypeForm;
	}

	public List<SupportQueryTypeORM> listQueryTypes(){
		UmCompany umCompany = null;
		try{
			umCompany = companyDao.companyDetails(session.getCompanyId());
		}catch(Exception e){
			
		}
		try{
		   queryTypeList = queryTypeDao.listQueryTypes(umCompany);
		}catch(QueryTypeNotFoundException e){
			System.out.println(e.getMessage());
		}
		return queryTypeList;
	}
	
	public void deleteRow(ActionEvent event){
		System.out.println("CaseBacking deleteRow called");
        Long id = (Long)event.getComponent().getAttributes().get("del_id");
		
        SupportQueryTypeORM search=null;
		for(SupportQueryTypeORM comp : queryTypeList) 
		{  
            if(comp.getQueryTypeId()==id)
            {
            	search=comp;
            	break;
            }
        }
		if(search!=null)
		{
			//delete from datatable
			queryTypeList.remove(search);
			//delete from DB
			queryTypeDao.deleteQueryType(search.getQueryTypeId());
			//comDAO.deleteCompany(search.getCompanyId());
			
			
			
		}
		
			
	}
	
	public void deleteQueryType(Long queryTypeId) throws Exception{
		System.out.println("CaseBacking deleteRow called");
		SupportQueryTypeORM queryType = queryTypeDao.queryTypeDetails(queryTypeId);
		List<SupportCaseORM> caseList = new ArrayList<>();
	    caseList = caseDao.retrieveCasesByQueryType(queryType);
	    if(caseList.size() > 0){
	    	addMessage(getProperty("message.support.queryType.delete.fail.case.registered"));
	    }else{
		    queryTypeDao.deleteQueryType(queryTypeId);
		    addMessage(getProperty("message.support.queryType.deleted"));
	    }
	}
	
	public String createQueryType(){
		UmGroup umGroup = new UmGroup();
		try{
		 umGroup = groupDao.groupDetails(groupId);
		}catch(Exception e){
			
		} 
		try{
		   umCompany = companyDao.companyDetails(session.getCompanyId());	
		}catch(Exception e){
		   System.out.println(e.getMessage());
		}
		
		SupportQueryTypeORM queryType = new SupportQueryTypeORM(getQueryTypeTitle(),getQueryTypeAlias(),umGroup, umCompany);
		queryTypeId = queryTypeDao.createQueryType(queryType);
		return "querySetting";
	}
	
	
	public String editQueryType(Long queryTypeId){
		UmGroup umGroup = new UmGroup();
		try{
		   umGroup = groupDao.groupDetails(groupId);
		}catch(Exception e){
			
		}
		try{
		   umCompany = companyDao.companyDetails(session.getCompanyId());	
		}catch(Exception e){
		   System.out.println(e.getMessage());
		}
		SupportQueryTypeORM queryType = new SupportQueryTypeORM(queryTypeId,getQueryTypeTitle(),getQueryTypeAlias(),umGroup,umCompany);
		queryTypeDao.updateQuerytype(queryType);
		return "querySetting";
	}
	
	public List<UmGroup> getCompanyGroups(){
		List<UmGroup> umGroups = new ArrayList<UmGroup>();
		umGroups = groupDao.listGroups1(session.getCompanyId());
		return umGroups;
	}
	
public String actionListener(){
		  System.out.println("QueryTypeBackingBean actionListener() called..");
		  reset();
		  setCurrentAction(getParameter("action"),this.getClass());
		  System.out.println("currentAction = "+getCurrentAction());
		  switch(getCurrentAction()){
		    case WebConstants.ACTION_CREATE:
		      setCreateAction();
			  renderQueryTypeForm = true;
			  session.setSupportModule_tabIndex(2);
			  return null;
			case WebConstants.ACTION_SAVE:
				try{
				  createQueryType();
				  addMessage(getProperty("message.support.queryType.created"));
				  renderQueryTypeForm = false;
				  session.setSupportModule_tabIndex(2);
				  return null;
				}catch(Exception e){
					addError(getProperty("message.support.queryType.creation.failed"));
					setCreateAction();
					renderQueryTypeForm = true;
					session.setSupportModule_tabIndex(2);
				    return null;
				}
			case WebConstants.ACTION_VIEW:
				try{
				 loadQueryType();
				 setViewAction();
				 renderQueryTypeForm = true;
				 session.setSupportModule_tabIndex(2);
				 return null;
				}
				catch (Exception e) {
					// handle exception
					addError(getProperty("message.support.error.load.queryType"));
					System.out.println("Exception Occured QueryTypeBackingBean actionListener()");
					renderQueryTypeForm = false;
					session.setSupportModule_tabIndex(2);
					return null;
				}
			case WebConstants.ACTION_CANCEL:
				renderQueryTypeForm = false;
				session.setSupportModule_tabIndex(2);
				return null;
			case WebConstants.ACTION_EDIT:
				try{
					loadQueryType();
					setEditAction();
					renderQueryTypeForm = true;
					session.setSupportModule_tabIndex(2);
					return null;
				}
				catch (Exception e) {
					addError(getProperty("message.support.error.load.queryType"));
					System.out.println("Exception Occured QueryTypeBackingBean actionListener()");
					renderQueryTypeForm = false;
					session.setSupportModule_tabIndex(2);
					return null;
				}
				
			case WebConstants.ACTION_UPDATE:
				try{
					Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
					Long queryTypeId = Long.valueOf(params.get("row_id"));
					editQueryType(queryTypeId);
					addMessage(getProperty("message.support.queryType.updated"));
					renderQueryTypeForm = false;
					session.setSupportModule_tabIndex(2);
					return null;
				}
				catch (Exception e) {
					addError(getProperty("message.support.queryType.update.fail"));
					renderQueryTypeForm = true;
					session.setSupportModule_tabIndex(2);
					return null;
				}
			case WebConstants.ACTION_DELETE:
				try{
					loadQueryType();
					setDeleteConfirmedAction();
					addMessage(getProperty("message.support.queryType.deleteConfirm"));
					renderQueryTypeForm = true;
					session.setSupportModule_tabIndex(2);
					return null;
				}
				catch (Exception e) {
					addError("Could not Load the Query Type");
					System.out.println("Exception Occured QueryTypeBackingBean actionListener()");
					renderQueryTypeForm = false;
					session.setSupportModule_tabIndex(2);
					return null;
				}
			case WebConstants.ACTION_DELETE_CONFIRMED:
				Long id = Long.valueOf(getParameter("row_id").toString());
				try{
					setDeleteConfirmedAction();
					try{
					   deleteQueryType(id);
					   renderQueryTypeForm = false;
					   session.setSupportModule_tabIndex(2);
					   return null;
					}catch(Exception e){
						addError(getProperty("message.support.queryType.delete.fail"));
						renderQueryTypeForm = false;
						session.setSupportModule_tabIndex(2);
						return null;
					}
				}
				catch (Exception e) {
					System.out.println("Exception Occured QuerytypeBackingBean actionListener()");
					setSaveAction(); // in case of failure in delete
					renderQueryTypeForm = true;
					session.setSupportModule_tabIndex(2);
				    return null;
				}
			}
			return(null);
		}

@Override
public List<?> getList() {
	// TODO Auto-generated method stub
	return null;
}

	
}
