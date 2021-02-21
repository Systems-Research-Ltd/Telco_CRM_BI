package com.srpl.crm.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import org.hibernate.validator.util.privilegedactions.SetAccessibility;

import com.bitguiders.util.jsf.WebConstants;
import com.srpl.crm.web.controller.BeanFactory;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.srpl.crm.ejb.entity.MCampaign;
import com.srpl.crm.ejb.request.CampaignDAO;
import com.srpl.crm.web.common.InnerTabs;
import com.srpl.crm.web.common.SessionDataBean;
import com.srpl.crm.web.model.CampaignBackingBean;
import com.srpl.crm.web.model.common.BeanSupportH;

@ManagedBean(name="marketingControllerBean")
@Deprecated
public class MarketingControllerBean extends JSFBeanSupport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor, we might need to change this initialization later
	 */
	public MarketingControllerBean(){
		marketingTabs = BeanFactory.getInstance().getSessionBean().getMarketingTabs();
	}
	
	@PostConstruct
	public void init(){
		String action;
		try{
			action = getAction();
			switch(action){
			case WebConstants.ACTION_SAVE:
				setCreateAction();
				break;
			case WebConstants.ACTION_EDIT:
				setViewAction();
				break;
			case WebConstants.ACTION_UPDATE:
				setEditAction();
				break;
			case WebConstants.ACTION_DELETE_CONFIRMED:
				setViewAction();
				break;
			case WebConstants.ACTION_CANCEL:
//				setAjaxCancelAction(true);
				break;
			default:
				changeTabPath(0, "/view/marketing/campaign/campaignNoSelection.xhtml");
			}
		}
		catch(Exception e){
			System.out.println("exception on old_action.");
		}
	}
	
	
	/**
	 * Declare all the dao objects here
	 * 
	 */
	@EJB CampaignDAO campaignDao;

	/**
	 * The Tab for the view are declare and set here
	 */
	private ArrayList<InnerTabs> marketingTabs;
	private int tabIndex = 0;
	
	/**
	 * All the class variables i.e. selected campaign are declared here
	 */
	private Long selectedCampaign;
	private Long selectedLead;
	private Boolean update;
	private ArrayList<CampaignBackingBean> campaigns = new ArrayList<CampaignBackingBean>();

	
	/**
	 * Getters Setters
	 */
	
	public Long getSelectedCampaign() {
		return selectedCampaign;
	}

	public void setSelectedCampaign(Long selectedCampaign) {
		this.selectedCampaign = selectedCampaign;
		if(selectedCampaign == 0L){
			changeTabPath(0, "/view/marketing/campaign/campaignNoSelection.xhtml");
		}
	}

	public Long getSelectedLead() {
		return selectedLead;
	}

	public void setSelectedLead(Long selectedLead) {
		this.selectedLead = selectedLead;
	}

	public Boolean getUpdate() {
		if(update == null){
			setUpdate(true); // set to default
		}
		return update;
	}

	public void setUpdate(Boolean update) {
		this.update = update;
	}

	public ArrayList<InnerTabs> getMarketingTabs() {
		return marketingTabs;
	}

	public void setMarketingTabs(ArrayList<InnerTabs> tabs) {
		this.marketingTabs = tabs;
	}

	public int getTabIndex() {
		return tabIndex;
	}

	public void setTabIndex(int tabIndex) {
		this.tabIndex = tabIndex;
	}
	
	public ArrayList<CampaignBackingBean> getCampaigns() {
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		ArrayList<CampaignBackingBean> myList = new ArrayList<CampaignBackingBean>();

		CampaignBackingBean bean;
		int campaignSize = 0;
		Boolean upd = true;
		
		//Get Campaign List from DB.
		List<MCampaign> campaignDbList = campaignDao.listCampaigns(session.getCompanyId());
		try{
			upd = getUpdate();
		}
		catch(Exception exp){
			System.out.println("update is null.");
		}
		
		try{
			campaignSize = campaigns.size(); 
		}
		catch (Exception exp2) {
			// TODO: handle exception
			System.out.println("couldn't get campaign size.");
		}
		
		if(campaignDbList.size() != campaignSize || upd == true){
			
			for (MCampaign x : campaignDbList) {
				bean = new CampaignBackingBean();
				// populate e the new object from db
				convertToBean(x, bean);
				myList.add(bean);
			}
			if(campaignDbList.size() > 0){
				setUpdate(false);
				//setSelectedCampaign(myList.get(0).getId());
				setCampaigns(myList);
				//loadCampaign(campaigns.get(0).getId());
				setUpdate(false);
			}
		}
		
		try{
			campaignSize = campaigns.size();
		}catch (Exception e) {
			// TODO: handle exception
		}
		if(campaignSize == 0){
			bean = new CampaignBackingBean();
			bean.setTitle("No Campaign found.");
			bean.setId(0L);
			campaigns.add(bean);
		}
		return campaigns;
	}

	public void setCampaigns(ArrayList<CampaignBackingBean> x) {
		try{
			campaigns.clear();
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("how can i clear uninitialized array? :p");
		}
		campaigns = x;
	}
	
	public void loadCampaign(Long id){
		setSelectedCampaign(id);
		CampaignBackingBean campaignBean = BeanFactory.getInstance().getCampaignBean();
		MCampaign db;
		try{
			db = campaignDao.campaignDetails(id);
			//campaign found in db
			convertToBean(db, campaignBean);
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("invalid id for campaign");
			changeTabPath(0, "/view/marketing/campaign/campaignNoSelection.xhtml");
		}
	}
	
	public void actionListener(){

		CampaignBackingBean campaignBean = BeanFactory.getInstance().getCampaignBean();
		reset();
		Long campaignId;
		MCampaign db;
		
		switch(getAction()){
		case WebConstants.ACTION_CREATE:
			//Goto Create Page
			campaignBean.reset();
			setCreateAction();
			setSelectedCampaign(0L);
			changeTabPath(0, "/view/marketing/campaign/campaignForm.xhtml");
			break;
		case WebConstants.ACTION_SAVE:
			//save backing bean and update the view
			try{
				db = new MCampaign();
				convertToDb(campaignBean, db);
				db.setId(null);
				campaignId = campaignDao.createCampaign(db);
				campaignBean.setId(campaignId);
				setViewAction();
				setUpdate(true);
				addMessage("Campaign Successfully Created.");
			} catch (Exception createExp) {
				// handle exception
				addError("Campaign Creation Failed.");
				setSelectedCampaign(0L);
			}
			break;
		case WebConstants.ACTION_VIEW:
			//Goto View Page
			//on view page we want to show edit, delete buttons
			loadCampaign(getSelectedCampaign());
			changeTabPath(0, "/view/marketing/campaign/campaignForm.xhtml");
			setViewAction();
			break;
		case WebConstants.ACTION_EDIT:
			//Goto Edit Page
			try{
				campaignId = Long.valueOf(getParameter("campaign_id").toString());
				loadCampaign(campaignId);
				changeTabPath(0, "/view/marketing/campaign/campaignForm.xhtml");
				reset();
				setEditAction();
			}
			catch (Exception e) {
				//handle exception
				addError("Invalid Campaign.");
				changeTabPath(0, "/view/marketing/campaign/campaignNoSelection.xhtml");
			}
			break;
		case WebConstants.ACTION_DELETE:
			//Goto Delete Page
			try{
				campaignId = Long.valueOf(getParameter("campaign_id").toString());
				loadCampaign(campaignId);
				changeTabPath(0, "/view/marketing/campaign/campaignForm.xhtml");
				reset();
				setDeleteAction();
			}
			catch (Exception e) {
				//handle exception
				addError("Invalid Campaign.");
				changeTabPath(0, "/view/marketing/campaign/campaignNoSelection.xhtml");
			}
			break;
		case WebConstants.ACTION_DELETE_CONFIRMED:
			//when delete is confirmed
			try{
				campaignDao.deleteCampaign(campaignBean.getId());
				addMessage("Campaign Successfully deleted.");
				setSelectedCampaign(0L);
			}
			catch(Exception deleteExpception){
				addError("Campaign Deletion Failed.");
				changeTabPath(0, "/view/marketing/campaign/campaignNoSelection.xhtml");
			}
			setViewAction();
			changeTabPath(0, "/view/marketing/campaign/campaignForm.xhtml");
			break;
		case WebConstants.ACTION_LIST:
			//Goto List Page
			//there is no List Page
			break;
		case WebConstants.ACTION_CANCEL:
			//if cancel button is pressed go back to view
			try{
				campaignId = Long.valueOf(getParameter("campaign_id".toString()));
				loadCampaign(campaignId);
				changeTabPath(0, "/view/marketing/campaign/campaignForm.xhtml");
				setViewAction();
			}catch(Exception e){
				changeTabPath(0, "/view/marketing/campaign/campaignNoSelection.xhtml");	
			}
			break;
		case WebConstants.ACTION_UPDATE:
			//update action is called.
			//campaignBean = BeanFactory.getInstance().getCampaignBean();
			try{
				db = new MCampaign();
				convertToDb(campaignBean, db);
				//db.setId(null);
				campaignId = campaignDao.updateCampaign(db);
				//campaignBean.setId(campaignId);
				setViewAction();
				setUpdate(true);
				addMessage("Campaign Successfully Updated.");
			}
			catch (Exception createExp) {
				// handle exception
				addError("Campaign Update Failed.");
				changeTabPath(0, "/view/marketing/campaign/campaignNoSelection.xhtml");
			}
			break;
		}
	}
	
	private void convertToDb(CampaignBackingBean bean, MCampaign db){
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		
		db.setId(bean.getId());
		db.setCompany_id(session.getCompanyId());
		db.setTitle(bean.getTitle());
		db.setStatus(bean.getStatus());
		db.setStartDate(bean.getStartDate());
		db.setEndDate(bean.getEndDate());
		db.setLaunchDate(bean.getLaunchDate());
		db.setcType(bean.getType());
		db.setCurrency(bean.getCurrency());
		db.setBudget(bean.getBudget());
		db.setExpectedCost(bean.getExpectedCost());
		db.setActualCost(bean.getActualCost());
		db.setExpectedRevenue(bean.getExpectedRevenue());
		db.setObjective(bean.getObjective());
		db.setDescription(bean.getDescription());
		
	}
	
	private void convertToBean(MCampaign db, CampaignBackingBean bean){

		bean.setId(db.getId());
		bean.setCompany_id(db.getCompany_id());
		bean.setTitle(db.getTitle());
		bean.setStatus(db.getStatus());
		bean.setStartDate(db.getStartDate());
		bean.setEndDate(db.getEndDate());
		bean.setLaunchDate(db.getLaunchDate());
		bean.setType(db.getcType());
		bean.setCurrency(db.getCurrency());
		bean.setBudget(db.getBudget());
		bean.setExpectedCost(db.getExpectedCost());
		bean.setActualCost(db.getActualCost());
		bean.setExpectedRevenue(db.getExpectedRevenue());
		bean.setObjective(db.getObjective());
		bean.setDescription(db.getDescription());
		
	}

	private void changeTabPath(int index, String path) {
		InnerTabs d = marketingTabs.get(index);
		d.setPath(path);
		marketingTabs.set(index, d);
	}
}
