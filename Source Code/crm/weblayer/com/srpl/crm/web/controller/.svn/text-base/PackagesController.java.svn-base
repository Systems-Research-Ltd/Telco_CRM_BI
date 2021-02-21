package com.srpl.crm.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import com.bitguiders.util.jsf.JSFBeanInterface;
import com.bitguiders.util.jsf.WebConstants;
import com.srpl.crm.web.controller.BeanFactory;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.srpl.crm.ejb.entity.SPackageORM;
import com.srpl.crm.ejb.request.PackageDAO;
import com.srpl.crm.web.common.SessionDataBean;
import com.srpl.crm.web.model.common.ColumnModel;

@ManagedBean(name="packagesBean")
public class PackagesController extends JSFBeanSupport implements JSFBeanInterface, Serializable {

	/**
	 * 
	 */
	public PackagesController(){
		session = BeanFactory.getInstance().getSessionBean();
	}
	private static final long serialVersionUID = 1L;
	
	private SessionDataBean session;
	private Long packageId;
	private String title;
	private double cost;
	private Long company_id;
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
		this.setCompany_id(0L);
		this.setCost(0.00F);
		this.setPackageId(0L);
		this.setTitle("");
	}
	
	public static List<ColumnModel> packagesColumns;
	
	static{

		packagesColumns=new ArrayList<ColumnModel>();
		packagesColumns.add(new ColumnModel("id", "ID"));
		packagesColumns.add(new ColumnModel("title", "Title"));
		packagesColumns.add(new ColumnModel("cost", "Cost"));
	}

	/**
	 * Declare all the dao objects here
	 * 
	 */
	@EJB PackageDAO packageDao;

	
	/**
	 * Getters Setters
	 */
	@Override
	public ArrayList<SPackageORM> getList() {
		
		ArrayList<SPackageORM> myList = null;
		//Get packages from DB
		myList = (ArrayList<SPackageORM>) packageDao.listPackages(session.getCompanyId());
		return myList;
	}
	public List<ColumnModel> getPackagesColumns() {
		return packagesColumns;
	}
	public void setPackagesColumns(List<ColumnModel> packagesColumns) {
		PackagesController.packagesColumns = packagesColumns;
	}
	
	@Override
	public String actionListener(){
		
		PackagesController bean = this;
		SPackageORM db;
		Long packageId;
		setCurrentAction(getAction(),this.getClass());
		switch (getCurrentAction()) {
		case WebConstants.ACTION_CREATE:
			bean.resetBean();
			return WebConstants.ACTION_CRUD;

		case WebConstants.ACTION_SAVE:
			try{
				db = new SPackageORM();
				bean.setCompany_id(session.getCompanyId());
				convert2Db(bean, db);
				packageId = packageDao.createPackage(db);
				bean.setPackageId(packageId);
				addMessage(getProperty("message.sales.package.created"));
			}
			catch (Exception e) {
				addError(getProperty("message.sales.package.creation.failed"));
			}
			return WebConstants.ACTION_LIST;

		case WebConstants.ACTION_VIEW:
			packageId = Long.valueOf(getParameter("row_id").toString());
			try{
				db = packageDao.packageDetails(packageId);
				convert2Bean(db, bean);
				return WebConstants.ACTION_CRUD;
			}
			catch (Exception e) {
				// handle exception
				addError(getProperty("message.sales.package.error.load"));
			}
			return WebConstants.ACTION_LIST;

		case WebConstants.ACTION_CANCEL:
			resetBean();
			return WebConstants.ACTION_LIST;

		case WebConstants.ACTION_EDIT:
			packageId = Long.valueOf(getParameter("row_id").toString());
			try{
				db = packageDao.packageDetails(packageId);
				convert2Bean(db, bean);
				return WebConstants.ACTION_CRUD;
			}
			catch (Exception e) {
				addError(getProperty("message.sales.package.error.load"));
			}
			return WebConstants.ACTION_LIST;

		case WebConstants.ACTION_UPDATE:
			//packageId = Long.valueOf(getParameter("row_id").toString());
			//bean.setPackageId(packageId);
			bean.setCompany_id(session.getCompanyId());
			try{
				db = new SPackageORM();
				convert2Db(bean, db);
				packageId = packageDao.updatePackage(db);
				addMessage(getProperty("message.sales.package.updated"));
			}
			catch (Exception e) {
				addError(getProperty("message.sales.package.update.fail"));
			}
			return WebConstants.ACTION_LIST;

		case WebConstants.ACTION_DELETE:
			packageId = Long.valueOf(getParameter("row_id").toString());
			try{
				db = packageDao.packageDetails(packageId);
				convert2Bean(db, bean);
				return WebConstants.ACTION_CRUD;
			}
			catch (Exception e) {
				addError(getProperty("message.sales.package.error.load"));
			}
			return WebConstants.ACTION_LIST;

		case WebConstants.ACTION_DELETE_CONFIRMED:
			packageId = Long.valueOf(getParameter("row_id").toString());
			try{
				packageDao.deletePackage(packageId);
			}
			catch (Exception e) {
				addError(getProperty("message.sales.package.error.load"));
			}
			return WebConstants.ACTION_LIST;

		default:
			setListAction(true);
			return WebConstants.ACTION_LIST;
		}
	}


	private void convert2Bean(SPackageORM x, PackagesController bean) {
		// Auto-generated method stub
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		bean.setCompany_id(session.getCompanyId());
		bean.setCost(x.getCost());
		bean.setPackageId(x.getId());
		bean.setTitle(x.getTitle());		
	}
	
	private void convert2Db(PackagesController bean, SPackageORM x){
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		x.setCompanyId(session.getCompanyId());
		x.setCost(bean.getCost());
		x.setId(bean.getPackageId());
		x.setTitle(bean.getTitle());
	}
	
}
