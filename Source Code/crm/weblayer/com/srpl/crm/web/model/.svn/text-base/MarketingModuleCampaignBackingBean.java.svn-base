package com.srpl.crm.web.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Min;
import org.hibernate.validator.constraints.NotBlank;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.srpl.crm.web.controller.BeanFactory;
import com.srpl.crm.ejb.entity.MCampaign;
import com.srpl.crm.ejb.entity.ProductORM;
import com.srpl.crm.ejb.exceptions.ProductNotFoundException;
import com.srpl.crm.ejb.request.CampaignDAO;
import com.srpl.crm.ejb.request.ProductDAO;
import com.srpl.crm.web.common.InnerTabs;
import com.srpl.crm.web.common.SessionDataBean;
import com.bitguiders.util.jsf.JSFBeanInterface;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;

/**
 * @author Hammad Hassan Khan
 *
 */
@ManagedBean(name="campaignDetails")
@RequestScoped
public class MarketingModuleCampaignBackingBean extends JSFBeanSupport implements JSFBeanInterface, Serializable {
	
	/**
	 * 
	 */

	@EJB CampaignDAO campaignDao;
	
	private static final long serialVersionUID = 1L;
	private Long campaignId = 0L;
	private Long company_id = 0L;
	@NotBlank(message="Title is Required.")
	private String title;
	private Boolean status = true;
	private Date startDate;
	private Date endDate;
	private Date launchDate;
	private int type = 0;
	private int currency = 0;
	@Min(message="Budget is Required.",value=(long) 1.0)
	private float budget;
	@Min(message="Expected Cost is Required.",value=(long) 1.0)
	private float expectedCost;
	private float actualCost;
	
	@Min(message="Expected Revenue is Required.",value=(long) 1.0)
	private float expectedRevenue;
	private String objective;
	private String description;
	private Boolean renderLeadForm = false;
	private String campaignImage;
	private UploadedFile file;
	private String filterBy = "";
	private String filterValue = "";
	private SessionDataBean session;
	
	public MarketingModuleCampaignBackingBean(){
		session = BeanFactory.getInstance().getSessionBean();
	}
	
	@PostConstruct
	public void postConstruct() {
		String row_id=getParameter("row_id");
		if(!row_id.isEmpty())
		{
			session.setMarketingModule_selectedCampaign(Long.parseLong(row_id));
			campaignDetails();
			reset();
			setViewAction();
		}
		String act = getAction();
		if(act.equals("")){
			if(session.getMarketingModule_selectedCampaign() != 0L){
				campaignDetails();
				reset();
				setViewAction();
			}else
				session.resetMarketingModule();
		}
	}

	public String getCampaignImage() {
		return (campaignImage != null) ? campaignImage : "defaultProfilePic.png";
	}

	public void setCampaignImage(String campaignImage) {
		this.campaignImage = campaignImage;
	}

	public Long getCampaignId() {
		return campaignId;
	}
	public void setCampaignId(Long campaignId) {
		this.campaignId = campaignId;
	}
	public Long getCompany_id() {
		return company_id;
	}
	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}
	public String getTitle() {
		return title;
	}
	public SessionDataBean getSession() {
		return session;
	}

	public void setSession(SessionDataBean session) {
		this.session = session;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getLaunchDate() {
		//Need to update this.
		if(launchDate == null){
			setLaunchDate(new Date());
		}
		return launchDate;
	}
	public void setLaunchDate(Date launchDate) {
		this.launchDate = launchDate;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getCurrency() {
		return currency;
	}
	public void setCurrency(int currency) {
		this.currency = currency;
	}
	public float getBudget() {
		return budget;
	}
	public void setBudget(float budget) {
		this.budget = budget;
	}
	public float getExpectedCost() {
		return expectedCost;
	}
	public void setExpectedCost(float expectedCost) {
		this.expectedCost = expectedCost;
	}
	public float getActualCost() {
		return actualCost;
	}
	public void setActualCost(float actualCost) {
		this.actualCost = actualCost;
	}
	public float getExpectedRevenue() {
		return expectedRevenue;
	}
	public void setExpectedRevenue(float expectedRevenue) {
		this.expectedRevenue = expectedRevenue;
	}
	public String getObjective() {
		return objective;
	}
	public void setObjective(String objective) {
		this.objective = objective;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Boolean getRenderLeadForm() {
		return renderLeadForm;
	}

	public void setRenderLeadForm(Boolean renderLeadForm) {
		this.renderLeadForm = renderLeadForm;
	}	

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public String getFilterBy() {
		return filterBy;
	}

	public void setFilterBy(String filterBy) {
		this.filterBy = filterBy;
	}

	public String getFilterValue() {
		return filterValue;
	}

	public void setFilterValue(String filterValue) {
		this.filterValue = filterValue;
	}

	public void resetBean(){
		System.out.println("Resetting the campaignBean.");
		setCampaignId(0L);
		setCompany_id(0L);
		setTitle("");
		setStatus(true);
		setStartDate(new Date());
		setEndDate(new Date());
		setLaunchDate(new Date());
		setType(0);
		setCurrency(0);
		setBudget(0.00F);
		setExpectedCost(0.00F);
		setActualCost(0.00F);
		setExpectedRevenue(0.00F);
		setObjective("");
		setDescription("");
		setCampaignImage("defaultProfilePic.png");
		//setFilterBy("");
		//setFilterValue("");
	}

	private void changeTabPath(int index, String path) {
		InnerTabs d = session.getMarketingTabs().get(index);
		d.setPath(path);
		session.getMarketingTabs().set(index, d);
		try{
			if(getParameter("fromListing").equals("fromListing")){
				//don't update index
			}else{
				session.setMarketingModule_tabIndex(0);
			}
		}catch(Exception e){
			session.setMarketingModule_tabIndex(0);
		}
	}
	
	private void convertToDb(MarketingModuleCampaignBackingBean bean, MCampaign db){
		System.out.println(bean.getEndDate());
		db.setId(bean.getCampaignId());
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
		db.setCampaignImage(bean.getCampaignImage());
	}
	
	private void convertToBean(MCampaign db, MarketingModuleCampaignBackingBean bean){
		bean.setCampaignId(db.getId());
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
		bean.setCampaignImage(db.getCampaignImage());
	}
	public void loadCampaign(Long id){
		System.out.println("Load Campaign Called with Campaign ID : "+id);
		MarketingModuleCampaignBackingBean bean = this;
		bean.resetBean();
		try {
			MCampaign db = campaignDao.campaignDetails(id);
			convertToBean(db, bean);			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void setViewAction(){
		super.setViewAction();
		setResetAction(false);
		setCancelAction(false);
	}
	
	public void clearFilter(){
		filterBy = "";
		filterValue = "";
	}

	@Override
	public ArrayList<MCampaign> getList() {
		//MCampaign bean;
		//Get Campaign List from DB.
		ArrayList<MCampaign> campaignDbList = (ArrayList<MCampaign>) campaignDao.listCampaigns(session.getCompanyId(), getFilterBy(), getFilterValue());
		if(campaignDbList.size() > 0 && filterValue != ""){
			session.setMarketingModule_selectedCampaign(campaignDbList.get(0).getId());
			campaignDetails();
		} else if(filterValue != ""){
			session.resetMarketingModule();
		}
		/*if(campaignDbList.size() <= 0){
			bean = new MCampaign();
			bean.setTitle(getProperty("message.marketing.campaign.error.found"));
			bean.setId(0L);
			campaignDbList.add(bean);
		}*/
		//changeTabPath(0, "/view/marketing/campaign/campaignNoSelection.xhtml");
		return campaignDbList;
	}
	
	public boolean costValidation(MarketingModuleCampaignBackingBean campaignBean){
		boolean cont = true;
		if((campaignBean.getBudget() < campaignBean.getExpectedCost()) || (campaignBean.getBudget() < campaignBean.getActualCost())){
			addMessage(getProperty("message.marketing.campaign.error.budget.cost"));
			cont = false;
		}	
		if((campaignBean.getExpectedRevenue() < campaignBean.getExpectedCost()) || (campaignBean.getExpectedRevenue() < campaignBean.getActualCost())){
			addMessage(getProperty("message.marketing.campaign.error.revenue.cost"));
			cont = false;
		}
		return cont;
	}

	@Override
	public String actionListener(){
		System.out.println("Action "+getAction());
		System.out.println("Camp "+session.getMarketingModule_selectedCampaign());
		MarketingModuleCampaignBackingBean campaignBean = this;
			Long campaignId;
			MCampaign db = null;
			setCurrentAction(getAction(),this.getClass());
			System.out.println("Current Action "+getCurrentAction());
			switch(getCurrentAction()){
			case WebConstants.ACTION_CREATE:
				//Goto Create Page
				campaignBean.resetBean();
				session.setMarketingModule_selectedCampaign(0L);
				changeTabPath(0, "/view/marketing/campaign/campaignForm.xhtml");
				break;
			case WebConstants.ACTION_SAVE:
				try{
					if(costValidation(campaignBean)){
						db = new MCampaign();
						if(this.file != null) handleFileUpload();
						convertToDb(campaignBean, db);
						db.setId(null);
						campaignId = campaignDao.createCampaign(db);
						campaignBean.setCampaignId(campaignId);
						session.setMarketingModule_selectedCampaign(campaignId);
						reset();
						campaignDetails();
						addMessage(getProperty("message.marketing.campaign.created"));
					}else
						setCreateAction();
				} catch (Exception createExp) {
					// handle exception
					addError(getProperty("message.marketing.campaign.creation.failed"));
				}
				break;
			case WebConstants.ACTION_VIEW:
				campaignDetails();
				break;
			case WebConstants.ACTION_EDIT:
				//Goto Edit Page
				try{
					campaignId = Long.valueOf(getParameter("campaign_id").toString());
					campaignDetails();
					reset();
					setEditAction();
				}
				catch (Exception e) {
					//handle exception
					/*addError(getProperty("message.marketing.campaign.error.invalid"));
					changeTabPath(0, "/view/marketing/campaign/campaignNoSelection.xhtml");
					changeTabPath(2, "/view/marketing/campaign/campaignNoSelection.xhtml");
					changeTabPath(3, "/view/marketing/campaign/campaignNoSelection.xhtml");*/
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
					addError(getProperty("message.marketing.campaign.error.invalid"));
					changeTabPath(0, "/view/marketing/campaign/campaignNoSelection.xhtml");
				}
				break;
			case WebConstants.ACTION_DELETE_CONFIRMED:
				//when delete is confirmed
				try{
					campaignDao.deleteCampaign(campaignBean.getCampaignId());
					addMessage(getProperty("message.marketing.campaign.deleted"));
					session.resetMarketingModule();
				}
				catch(Exception deleteExpception){
					addError(getProperty("message.marketing.campaign.delete.fail"));
					changeTabPath(0, "/view/marketing/campaign/campaignNoSelection.xhtml");
				}
				break;
			case WebConstants.ACTION_LIST:
				//Goto List Page
				//there is no List Page
				break;
			case WebConstants.ACTION_CANCEL:
				//if cancel button is pressed go back to view
				try{
					campaignId = Long.valueOf(getParameter("campaign_id".toString()));
					if(campaignId != null && campaignId != 0){
						session.setMarketingModule_selectedCampaign(campaignId);
						campaignDetails();
					}else{
						session.resetMarketingModule();
					}
				}catch(Exception e){
					session.resetMarketingModule();
				}
				break;
			case WebConstants.ACTION_UPDATE:
				try{
					if(costValidation(campaignBean)){
						db = new MCampaign();
						if(this.file != null) handleFileUpload();
						campaignId = Long.valueOf(getParameter("campaign_id".toString()));
						convertToDb(campaignBean, db);
						campaignId = campaignDao.updateCampaign(db);
						reset();
						campaignDetails();
						addMessage(getProperty("message.marketing.campaign.updated"));
					}else
						setEditAction();
				}
				catch (Exception createExp) {
					// handle exception
					addError(getProperty("message.marketing.campaign.update.fail"));
					//changeTabPath(0, "/view/marketing/campaign/campaignNoSelection.xhtml");
				}
				break;
			}
		return null;
	}
	
	public void handleFileUpload() {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");
    	this.campaignImage = fmt.format(new Date()) + file.getFileName();
    	System.out.println(this.campaignImage);
		String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("//resources//images//" + this.campaignImage);
    	File outfile = new File(path);
        InputStream is;		
        OutputStream out;		
        byte buf[] = new byte[1024];
        int len;
        try {
        	is = file.getInputstream();
        	out = new FileOutputStream(outfile);
			while ((len = is.read(buf)) > 0)
			    out.write(buf, 0, len);
			is.close();
	        out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void campaignDetails(){		
		loadCampaign(session.getMarketingModule_selectedCampaign());
		changeTabPath(0, "/view/marketing/campaign/campaignForm.xhtml");
		changeTabPath(1, "/view/marketing/settings/campaignSettingsForm.xhtml");
		changeTabPath(2, "/view/marketing/leads/campaignLeadsList.xhtml");
		changeTabPath(3, "/view/marketing/products/campaignProducts.xhtml");
		setViewAction();
	}
	
	public String leadsTabActionListener(){
		setCurrentAction(getAction(),this.getClass());
		System.out.println("leadsTabActionListener() = getCurrentAction() = "+getCurrentAction());
		switch(getCurrentAction()){
		
		   case WebConstants.ACTION_VIEW:
			   loadCampaign(session.getMarketingModule_selectedCampaign());
			   Map<String,String> params=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			   System.out.println("parameter row id = "+params.get("row_id"));
			   changeTabPath(0, "/view/marketing/campaign/campaignForm.xhtml");
			   changeTabPath(2, "/view/marketing/leads/campaignLeadsList.xhtml");
			   changeTabPath(3, "/view/marketing/products/campaignProducts.xhtml");
			   setViewAction();
			   this.setRenderLeadForm(true);
			   setResetAction(false);
			   setCancelAction(false);
			   break;
		   case WebConstants.ACTION_CANCEL:
			   loadCampaign(session.getMarketingModule_selectedCampaign());
			   changeTabPath(0, "/view/marketing/campaign/campaignForm.xhtml");
			   changeTabPath(2, "/view/marketing/leads/campaignLeadsList.xhtml");
			   changeTabPath(3, "/view/marketing/products/campaignProducts.xhtml");
			   setViewAction();
			   this.setRenderLeadForm(false);
			   setResetAction(false);
			   setCancelAction(false);
			   break;

		}
		
		return(null);
	}
	
	public void campaignDetailsDashboard(){
		Long id = getCampaignId();//Long.valueOf(getParameter("campaign_id").toString());
		loadCampaign(id);
		if(this.campaignImage == null){
			this.campaignImage = "";
		}
	}
	public List<MCampaign> getLaunchedCampaigns(){
		//System.out.println("launched campaigns called------------------");
		List<MCampaign> campaignDbList = (ArrayList<MCampaign>) campaignDao.listLaunchedCampaigns(session.getCompanyId());
		return campaignDbList;
	}
}
