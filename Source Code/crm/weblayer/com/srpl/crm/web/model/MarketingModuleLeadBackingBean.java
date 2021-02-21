package com.srpl.crm.web.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.bitguiders.util.jsf.JSFBeanInterface;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;
import com.srpl.crm.web.controller.BeanFactory;
import com.srpl.crm.ejb.entity.SalesLeadORM;
import com.srpl.um.ejb.entity.UmUser;
import com.srpl.crm.ejb.exceptions.LeadNotFoundException;
import com.srpl.crm.ejb.request.CampaignDAO;
import com.srpl.crm.ejb.request.ContactDAO;
import com.srpl.crm.ejb.request.LeadsDAO;
import com.srpl.crm.web.common.InnerTabs;
import com.srpl.crm.web.common.SessionDataBean;
import com.srpl.crm.web.controller.MarketingControllerBean;
import com.srpl.crm.web.model.common.ColumnModel;
import com.srpl.crm.web.model.um.admin.users.UsersListingBackingBean;
import com.srpl.um.ejb.request.UserDAO;

	@ManagedBean(name="campaignLeads")
	@RequestScoped
	public class MarketingModuleLeadBackingBean extends JSFBeanSupport implements JSFBeanInterface,Serializable {	
		private static final long serialVersionUID = 1L;
		java.util.Date date = new java.util.Date();
		private long leadId;
		private String leadAddress;
		//private int leadAssigned = 0;
		private Long leadAssigned = 0L;
		private String leadEmail;
		private String leadName;
		private String leadPhone;
		private Long leadSource = 0L;
		private String leadStatus;
		private Long leadCompany;
		private Timestamp leadAddedon = new Timestamp(date.getTime());
		private List<SalesLeadORM> list;
		public List<ColumnModel> columns;
		private String tempStatus;
		private Boolean renderLeadForm = false;
		
		@EJB LeadsDAO leadsDao;
		@EJB UserDAO userDao;
		@EJB CampaignDAO campaignDao;
		@EJB ContactDAO contactDao;
		
		
		private SessionDataBean session;
		
		
		public MarketingModuleLeadBackingBean(){
			session = BeanFactory.getInstance().getSessionBean();
			System.out.println("renderLeadForm = "+renderLeadForm);
		}
		
		public void loadLeads(){
			Long l;
			Map<String,String> params=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			if(params.get("row_id")!=null){
				 l = Long.valueOf(params.get("row_id"));
			}else{
				 l = (long) 0;
			}		
			if(l > 0){
				try {
					System.out.println("id = "+l);
					System.out.println("leadsDao = "+leadsDao);

					SalesLeadORM lid = leadsDao.leadDetails(l);
					this.leadId = l;
					this.leadAddress = lid.getLeadAddress();
					this.leadAssigned = lid.getLeadAssigned();
					this.leadEmail = lid.getLeadEmail();
					this.leadName = lid.getLeadName();
					this.leadPhone = lid.getLeadPhone();
					this.leadSource = lid.getLeadSource();
					this.leadStatus = lid.getLeadStatus();
					this.leadCompany = lid.getLeadCompany();
					this.tempStatus = lid.getLeadStatus();			
				} catch (LeadNotFoundException e) {
					System.out.println("exception while loading leads.");
					e.printStackTrace();
				}
			}
		}
		
		public java.util.Date getDate() {
			return date;
		}

		public void setDate(java.util.Date date) {
			this.date = date;
		}

		public long getLeadId() {
			return leadId;
		}

		public void setLeadId(long leadId) {
			this.leadId = leadId;
		}

		public String getLeadAddress() {
			return leadAddress;
		}

		public void setLeadAddress(String leadAddress) {
			this.leadAddress = leadAddress;
		}

		public Long getLeadAssigned() {
			return leadAssigned;
		}

		public void setLeadAssigned(Long leadAssigned) {
			this.leadAssigned = leadAssigned;
		}

		public String getLeadEmail() {
			return leadEmail;
		}

		public void setLeadEmail(String leadEmail) {
			this.leadEmail = leadEmail;
		}

		public String getLeadName() {
			return leadName;
		}

		public void setLeadName(String leadName) {
			this.leadName = leadName;
		}

		public String getLeadPhone() {
			return leadPhone;
		}

		public void setLeadPhone(String leadPhone) {
			this.leadPhone = leadPhone;
		}

		public Long getLeadSource() {
			return leadSource;
		}

		public void setLeadSource(Long leadSource) {
			this.leadSource = leadSource;
		}

		public String getLeadStatus() {
			return leadStatus;
		}

		public void setLeadStatus(String leadStatus) {
			this.leadStatus = leadStatus;
		}

		public Boolean getRenderLeadForm() {
			return renderLeadForm;
		}

		public void setRenderLeadForm(Boolean renderLeadForm) {
			this.renderLeadForm = renderLeadForm;
		}

		public Long getLeadCompany() {
			return leadCompany;
		}

		public void setLeadCompany(Long leadCompany) {
			this.leadCompany = leadCompany;
		}

		public Timestamp getLeadAddedon() {
			return leadAddedon;
		}

		public void setLeadAddedon(Timestamp leadAddedon) {
			this.leadAddedon = leadAddedon;
		}

		public List<ColumnModel> getColumns() {
			return columns;
		}

		public void setColumns(List<ColumnModel> columns) {
			this.columns = columns;
		}

		public String getTempStatus() {
			return tempStatus;
		}

		public void setTempStatus(String tempStatus) {
			this.tempStatus = tempStatus;
		}

		public LeadsDAO getLeadsDao() {
			return leadsDao;
		}

		public void setLeadsDao(LeadsDAO leadsDao) {
			this.leadsDao = leadsDao;
		}

		public UserDAO getUserDao() {
			return userDao;
		}

		public void setUserDao(UserDAO userDao) {
			this.userDao = userDao;
		}

		public CampaignDAO getCampaignDao() {
			return campaignDao;
		}

		public void setCampaignDao(CampaignDAO campaignDao) {
			this.campaignDao = campaignDao;
		}

		
		public ContactDAO getContactDao() {
			return contactDao;
		}

		public void setContactDao(ContactDAO contactDao) {
			this.contactDao = contactDao;
		}

		
		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		public void setList(List<SalesLeadORM> list) {
			this.list = list;
		}
		
		public List<UmUser> listUsers(){
	    	UsersListingBackingBean l = BeanFactory.getInstance().getUsersListingBackingBean();
	    	return l.getLeadUsers();
	    }
		
		public List<CampaignBackingBean> listSources(){
	    	MarketingControllerBean l = BeanFactory.getInstance().getMarketingControllerBean();  
	    	return l.getCampaigns();
	    }

		
		public List<SalesLeadORM> listCampaignLeads(){
			
			try {
				this.list = leadsDao.listCampaignLeads(session.getMarketingModule_selectedCampaign());
			} catch (Exception e) {			
				//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage(), e.getMessage()));
			}
			//setList();
			return list;
		}

	
	@Override
	public String actionListener() {
		// TODO Auto-generated method stub
		setCurrentAction(getAction(),this.getClass());
		System.out.println("leadsTabActionListener() = getCurrentAction() = "+getCurrentAction());
		switch(getCurrentAction()){
           case WebConstants.ACTION_VIEW:
			   loadLeads();
			   changeTabPath(0, "/view/marketing/campaign/campaignForm.xhtml");
			   changeTabPath(1, "/view/marketing/settings/campaignSettingsForm.xhtml");
			   changeTabPath(2, "/view/marketing/leads/campaignLeadsList.xhtml");
			   setViewAction();
			   this.setRenderLeadForm(true);
			   session.setMarketingModule_tabIndex(2);
			   setResetAction(false);
			   break;
		   case WebConstants.ACTION_CANCEL:
			   loadLeads();
			   changeTabPath(0, "/view/marketing/campaign/campaignForm.xhtml");
			   changeTabPath(2, "/view/marketing/leads/campaignLeadsList.xhtml");
			   setViewAction();
			   this.setRenderLeadForm(false);
			   session.setMarketingModule_tabIndex(2);
			   setResetAction(false);
			   setCancelAction(false);
			   break;

		}
		
		return(null);

	}
	
	private void changeTabPath(int index, String path) {
		InnerTabs d = session.getMarketingTabs().get(index);
		d.setPath(path);
		session.getMarketingTabs().set(index, d);
		try{
			if(getParameter("fromListing").equals("fromListing")){
				//don't update index
			}else{
				//session.setMarketingModule_tabIndex(0);
			}
		}catch(Exception e){
			session.setMarketingModule_tabIndex(0);
		}
	}

	@Override
	public List<SalesLeadORM> getList() {
		// TODO Auto-generated method stub
		try {
			this.list = leadsDao.listLeads();
		} catch (LeadNotFoundException e) {			
			//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage(), e.getMessage()));
		}
		return list;
	}

}
