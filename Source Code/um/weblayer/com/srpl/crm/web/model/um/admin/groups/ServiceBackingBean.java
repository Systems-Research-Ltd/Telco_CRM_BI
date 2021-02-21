package com.srpl.crm.web.model.um.admin.groups;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.bitguiders.util.jsf.JSFBeanInterface;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;
import com.srpl.crm.web.common.AjaxListStructure;
import com.srpl.crm.web.common.InnerTabs;
import com.srpl.um.ejb.entity.UmGroup;
import com.srpl.um.ejb.entity.UmRole;
import com.srpl.um.ejb.entity.UmService;
import com.srpl.um.ejb.request.ServiceDAO;
import com.srpl.um.web.common.SessionDataBean;
import com.srpl.um.web.controller.BeanFactory;

@ManagedBean(name = "serviceBackingBean")
@RequestScoped
public class ServiceBackingBean extends JSFBeanSupport  implements
JSFBeanInterface,
Serializable  {
	
	
	private static final long serialVersionUID = 1L;
	public ServiceBackingBean(){
		session = BeanFactory.getInstance().getSessionBean();
	setCurrentAction(WebConstants.ACTION_SECURITY, this.getClass());	
	}
	
	@PostConstruct
	public void postConstruct() {
		System.out.println("post construct");;
		if (getAction().equals("")) {
			// no action was called, load group data
			serviceDetails();
			reset();
			setViewAction();
		}
	}
	private String title;
	private String description;
	private Long serviceId;
	// Navigation and all
	private SessionDataBean session;
	private List<UmRole> userRoles = new ArrayList<UmRole>();

	
	@EJB
	ServiceDAO serviceDao;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public SessionDataBean getSession() {
		return session;
	}

	public void setSession(SessionDataBean session) {
		this.session = session;
	}

	public List<UmRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UmRole> userRoles) {
		this.userRoles = userRoles;
	}

	public void resetBean(){
		setServiceId(0L);
		setTitle("");
		setDescription("");
	}
	@Override
	public void setViewAction() {
		super.setViewAction();
		setCancelAction(false);
	}
	

	public void serviceDetails() {
		System.out.println("++"+ session.getServiceModule_selectedService());
		if (session.getServiceModule_selectedService()!= 0L) {
			loadServices(session.getServiceModule_selectedService());
			changeTabPath(0, "/view/um/admin/manageServices/serviceForm.xhtml");
			setViewAction();
		} else {
			session.resetServiceModule();
		}
	}
	
	private void changeTabPath(int index, String path) {
		InnerTabs d = session.getServiceTabs().get(index);
		d.setPath(path);
		session.getServiceTabs().set(index, d);
		
		try {
			if (getParameter("fromListing").equals("fromListing")) {
				// don't update index
			} else {
				session.setServiceModule_tabIndex(0);
			}
		} catch (Exception e) {
			session.setServiceModule_tabIndex(0);
		}
	}
	
	public void loadServices(long id) {

		ServiceBackingBean bean = this;
		//bean.resetBean();
		try {
			UmService db = serviceDao.serviceDetails1(id);
			bean.setServiceId(db.getServiceId());
			bean.setTitle(db.getServiceTitle());
			bean.setDescription(db.getServiceDescription());


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public String actionListener() {
		// TODO Auto-generated method stub
		System.out.println("serviceBackingBean actionListener called");
		ServiceBackingBean bean;
		UmService db;
		long id;
		setCurrentAction(getParameter("action"),this.getClass());
		switch (getCurrentAction()){ 
		case WebConstants.ACTION_CREATE:
			System.out.println("action create called");
			changeTabPath(0, "/view/um/admin/manageServices/serviceForm.xhtml");
			System.out.println("tab path is" );
			bean = this;
			bean.resetBean();
			break;
	 case WebConstants.ACTION_SAVE:
			bean = this;
			// TODO
			UmService ms = new UmService(" ", bean.getTitle(), bean.getDescription());
			
			try {
				bean.setServiceId(serviceDao.createService(ms));
				
				this.addMessage(getProperty("group.services.created.successfully"));
			} catch (Exception e) {
				this.addError(getProperty("group.service.creation.fail"));
			}
			this.serviceDetails();
			reset();
			setViewAction();
			break;
		case WebConstants.ACTION_VIEW:
			this.serviceDetails();
			setViewAction();
			break;
		case WebConstants.ACTION_CANCEL:
			this.serviceDetails();
			setViewAction();
			break;
		case WebConstants.ACTION_EDIT:
			id = Long.parseLong(this.getParameter("row_id"));
			this.loadServices(id);
			this.changeTabPath(0, "/view/um/admin/manageServices/serviceForm.xhtml");
			setEditAction();
			break;
		case WebConstants.ACTION_UPDATE:
			bean = this;
			db = serviceDao.serviceDetails1(session.getServiceModule_selectedService());
			db.setServiceTitle(bean.getTitle());
			db.setServiceDescription(bean.getDescription());
		try {
			serviceDao.updates(db);	
			
			addMessage(getProperty("service.successfully.updated"));
			} catch (Exception e) {
				addError(getProperty("service.updation.fail"));
			}
			reset();
			this.serviceDetails();
			break;
		case WebConstants.ACTION_DELETE:
			id = Long.parseLong(this.getParameter("row_id"));
			this.loadServices(id);
			this.changeTabPath(0, "/view/um/admin/manageServices/serviceForm.xhtml");
			setDeleteAction();
			break;
		case WebConstants.ACTION_DELETE_CONFIRMED:
			try {
				serviceDao.deleteService1(session.getServiceModule_selectedService());
				
				addMessage(getProperty("group.service.successfully.deleted"));
			} catch (Exception e) {
				addError(getProperty("could.not.delete.group.service"));
			}
			session.resetServiceModule();
			this.resetBean();
			break;


		}
		
		return null;
	}
	private void convert2Db(UmService db) {
		db.setServiceId(this.getServiceId());
		db.setServiceTitle(this.getTitle());
		db.setServiceDescription(this.getDescription());
	}

	private void convert2Bean(UmService db) {
		this.setServiceId(db.getServiceId());
		this.setTitle(db.getServiceTitle());
		this.setDescription(db.getServiceDescription());
	}
	
	@Override
	public List<AjaxListStructure> getList() {
		ArrayList<AjaxListStructure> myList = new ArrayList<AjaxListStructure>();
		AjaxListStructure e;
		SessionDataBean s1 = BeanFactory.getInstance().getSessionBean();
		List<UmService> serviceDbList = serviceDao.listServices();
		if(serviceDbList.size() != myList.size()){
			for(UmService ms : serviceDbList){
				e = new AjaxListStructure();
				e.setId(ms.getServiceId());
				e.setLabel(ms.getServiceTitle());
				myList.add(e);
			}
		}
		if(myList.size() == 0){
			// if No entries
			e = new AjaxListStructure();
			e.setId(0L);
			e.setLabel(getProperty("no service found"));
			myList.add(e);
			changeTabPath(0, "/view/um/admin/manageServices/serviceNoSelection.xhtml");
		}
		return myList;
	}

}
