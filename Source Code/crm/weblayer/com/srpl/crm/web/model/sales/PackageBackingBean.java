package com.srpl.crm.web.model.sales;

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

import com.srpl.crm.ejb.entity.SPackageORM;

import com.srpl.crm.ejb.request.PackageDAO;
import com.srpl.crm.web.common.AjaxListStructure;
import com.srpl.crm.web.common.InnerTabs;
import com.srpl.crm.web.common.SessionDataBean;
import com.srpl.crm.web.controller.BeanFactory;

@ManagedBean(name = "packageBean")
@RequestScoped
public class PackageBackingBean extends JSFBeanSupport implements JSFBeanInterface,Serializable {
	public PackageBackingBean(){
		setCurrentAction(WebConstants.ACTION_SECURITY,this.getClass());
		session = BeanFactory.getInstance().getSessionBean();
	}
	
private static final long serialVersionUID = 1L;
	
	private SessionDataBean session;
	private Long packageId;
	private String title;
	private double cost;
	private Long company_id;

	/**
	 * Declare all the dao objects here
	 * 
	 */
	@EJB PackageDAO packageDao;
	
	public void packageDetails() {
		System.out.println("++"+ session.getSalesModule_selectedPackage());
		if (session.getSalesModule_selectedPackage()!= 0L) {
			loadPackage(session.getSalesModule_selectedPackage());
			changeTabPath(0, "/view/sales/packages/packagesForm.xhtml");
		//	changeTabPath(0, "/view/sales/leads/leadForm.xhtml");
			setViewAction();
		} else {
			session.resetPackageModule();
		}
	}

	@PostConstruct
	public void postConstruct() {
		String act = getAction();
		if (act.equals("")) {
			// no action was called, load group data
			packageDetails();
			reset();
			setViewAction();
		}	
	}
	
	private void changeTabPath(int index, String path) {
		InnerTabs d = session.getPackageTabs().get(index);
		d.setPath(path);
		session.getPackageTabs().set(index, d);
		try {
			if (getParameter("fromListing").equals("fromListing")) {
				// don't update index
			} else {
				session.setSalesModule_packageTabIndex(0);
			}
		} catch (Exception e) {
			session.setSalesModule_packageTabIndex(0);
		}
	}
	
	public void loadPackage(Long id) {
		System.out.println("loadLead " + id);
		PackageBackingBean bean= this;
		SPackageORM pId;
		try{
			pId = packageDao.packageDetails(id);
			convert2Bean(pId, bean);
			} catch(Exception e) {
			changeTabPath(0, "/view/sales/packages/packageNoSelection.xhtml");
		}
	}
	/**
	 * Getters Setters
	 */
	public SessionDataBean getSession() {
		return session;
	}

	public void setSession(SessionDataBean session) {
		this.session = session;
	}

	public Long getPackageId() {
		return packageId;
	}

	public void setPackageId(Long packageId) {
		this.packageId = packageId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public Long getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}

	public void resetBean(){
	setCompany_id(0L);
	setCost(0.00F);
	setPackageId(0L);
	setTitle("");
	}
	@Override
	public void setViewAction() {
		super.setViewAction();
		setCancelAction(false);
	}
	@Override
	public String actionListener() {
		// TODO Auto-generated method stub
		//PackageBackingBean bean = this;
		Long package_id;
		
		setCurrentAction(getParameter("action"),this.getClass());
		switch (getCurrentAction()) {
		case WebConstants.ACTION_CREATE:
			System.out.println("NewpackageBean create action called" + getCurrentAction());
			changeTabPath(0, "/view/sales/packages/packagesForm.xhtml");
			resetBean();
			System.out.println("reset called() :/");
			break;
		case WebConstants.ACTION_SAVE:
			createPackage();
			break;
		case WebConstants.ACTION_VIEW:
			packageDetails();
			reset();
			setViewAction();
			break;
		case WebConstants.ACTION_CANCEL:
			packageDetails();
			reset();
			setViewAction();
			break;
		case WebConstants.ACTION_EDIT:
			package_id = Long.parseLong(this.getParameter("package_id"));
			loadPackage(package_id);
			this.changeTabPath(0, "/view/sales/packages/packagesForm.xhtml");
			setEditAction();
			break;
		case WebConstants.ACTION_UPDATE:
			updatePackage();
			break;
		case WebConstants.ACTION_DELETE:
			/*packageId = Long.parseLong(this.getParameter("package_id"));
			this.loadPackage(packageId);
			this.changeTabPath(0, "/view/sales/packages/packagesForm.xhtml");
			setDeleteAction();
			break;*/
			packageDetails();
			reset();
			setDeleteAction();
			break;
		case WebConstants.ACTION_DELETE_CONFIRMED:
		packageDelete();
		break;

		}
		
		return null;
	}
	public void createPackage(){
	
		try{
			SPackageORM db = new SPackageORM();
			this.setCompany_id(session.getCompanyId());
			convert2Db(this, db);
			packageId = packageDao.createPackage(db);
			this.setPackageId(packageId);
			addMessage(getProperty("message.sales.package.created"));
			session.setSalesModule_selectedPackage(packageId);
			reset();
			setViewAction();
		}
		catch (Exception e) {
			addError(getProperty("message.sales.package.creation.failed"));
			reset();
			setCreateAction();
		}
	}

	public void updatePackage(){
		PackageBackingBean bean = this;
		try {
			SPackageORM db = new SPackageORM();
			convert2Db(bean, db);
			packageId = packageDao.updatePackage(db);
			reset();
			setViewAction();
			addMessage(getProperty("message.sales.package.updated"));
		} catch (Exception createExp) {
			// handle exception
			addError(getProperty("message.sales.package.update.fail"));
			reset();
			setEditAction();
		}
		
	}
	
	public void packageDelete() {
		try {
			packageDao.deletePackage(session.getSalesModule_selectedPackage());
			addMessage(getProperty("message.sales.package.deleted"));
			
		} catch (Exception deleteExpception) {
			addError(getProperty("message.sales.package.deletion.fail"));
		}
		session.setSalesModule_selectedPackage(0L);
		packageDetails();
		reset();
		setViewAction();
	}

	@Override
	public List<AjaxListStructure> getList() {
		ArrayList<AjaxListStructure> myList = new ArrayList<AjaxListStructure>();
		AjaxListStructure e;
	List<SPackageORM> packageDbList = packageDao.listPackages(session.getCompanyId());
		if(packageDbList.size() != myList.size()){
			for(SPackageORM sp : packageDbList){
				e = new AjaxListStructure();
				e.setId(sp.getId());
				e.setLabel(sp.getTitle());
				myList.add(e);
			}
		}
		if(myList.size() == 0){
			// if No entries
			e = new AjaxListStructure();
			e.setId(0L);
			e.setLabel(getProperty("message.no.package.found"));
			myList.add(e);
			changeTabPath(0, "/view/sales/packages/packageNoSelection.xhtml");
		}
		return myList;
	}
	private void convert2Bean(SPackageORM x, PackageBackingBean bean) {
		// Auto-generated method stub
	//	SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		bean.setCompany_id(session.getCompanyId());
		bean.setCost(x.getCost());
		bean.setPackageId(x.getId());
		bean.setTitle(x.getTitle());		
	}
	
	private void convert2Db(PackageBackingBean bean, SPackageORM x){
	//	SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		x.setCompanyId(session.getCompanyId());
		x.setCost(bean.getCost());
		x.setId(bean.getPackageId());
		x.setTitle(bean.getTitle());
	}

}
