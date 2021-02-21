package com.srpl.crm.web.model.sales;

import com.bitguiders.util.jsf.JSFBeanInterface;
import com.bitguiders.util.jsf.JSFBeanSupport;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.bitguiders.util.jsf.JSFBeanInterface;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;
import com.srpl.crm.ejb.entity.CampaignProduct;
import com.srpl.crm.ejb.entity.CsAccount;
import com.srpl.crm.ejb.entity.CsContactORM;
import com.srpl.crm.ejb.entity.MCampaign;
import com.srpl.crm.ejb.entity.SalesLeadCommentORM;
import com.srpl.crm.ejb.entity.SalesLeadHistoryORM;
import com.srpl.crm.ejb.entity.SalesLeadORM;
import com.srpl.crm.ejb.entity.SalesOpportunityORM;
import com.srpl.crm.ejb.exceptions.AccountNotFoundException;
import com.srpl.crm.ejb.exceptions.LeadNotFoundException;
import com.srpl.crm.ejb.exceptions.ProductNotFoundException;
import com.srpl.crm.ejb.request.AccountDAO;
import com.srpl.crm.ejb.request.CampaignDAO;
import com.srpl.crm.ejb.request.ContactDAO;
import com.srpl.crm.ejb.request.LeadCommentDAO;
import com.srpl.crm.ejb.request.LeadHistoryDAO;
import com.srpl.crm.ejb.request.LeadsDAO;
import com.srpl.crm.ejb.request.OpportunityDAO;
import com.srpl.crm.ejb.request.ProductDAO;
import com.srpl.crm.ejb.request.UtilsDAO;
import com.srpl.crm.web.common.AjaxListStructure;
import com.srpl.crm.web.common.InnerTabs;
import com.srpl.crm.web.common.SessionDataBean;
import com.srpl.crm.web.controller.BeanFactory;
import com.srpl.crm.web.controller.MarketingControllerBean;
import com.srpl.crm.web.model.CampaignBackingBean;
import com.srpl.crm.web.model.common.ColumnModel;
import com.srpl.crm.web.model.um.admin.users.UsersListingBackingBean;
import com.srpl.um.ejb.entity.UmCompany;
import com.srpl.um.ejb.entity.UmUser;
import com.srpl.um.ejb.exceptions.UserNotFoundException;
import com.srpl.um.ejb.request.CompanyDAO;
import com.srpl.um.ejb.request.UserDAO;
@ManagedBean(name = "leadBackingBean")
@RequestScoped
public class LeadsBackingBean extends JSFBeanSupport  implements JSFBeanInterface, Serializable{
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
private String campaignSource ;
List<SalesLeadHistoryORM> leadHistoryList;
List<SalesLeadCommentORM> leadCommentList;
private UmUser umUser;
private String userComments;
private UmCompany company;
private Timestamp leadsAssignedDate  = new Timestamp(date.getTime());;
private Boolean renderCommentsForm = false;
// Navigation and all
private SessionDataBean session;

@EJB LeadsDAO leadsDao;
@EJB UserDAO userDao;
@EJB UtilsDAO utilsDao;
@EJB CampaignDAO campaignDao;
@EJB AccountDAO accountDao;
@EJB ContactDAO contactDao;
@EJB OpportunityDAO opportunityDao;
@EJB LeadHistoryDAO leadHistoryDao;
@EJB LeadCommentDAO leadCommentDao;
@EJB CompanyDAO companyDao;
@EJB ProductDAO productDao;

public LeadsBackingBean(){
	session = BeanFactory.getInstance().getSessionBean();
}

public void leadDetails() {
	loadLead(session.getSalesModule_selectedLead());
	changeTabPath(0, "/view/sales/leads/leadForm.xhtml");
	setViewAction();
	renderCommentsForm = true;
}

@Override
public void setViewAction(){
	super.setViewAction();
	setResetAction(false);
	setCancelAction(false);
}

@PostConstruct
public void postConstruct() {
	String act = getAction();
	if(act.equals("")){
		if(session.getSalesModule_selectedLead() != 0L){
			leadDetails();
			reset();
			setViewAction();
		}else
			session.resetSalesLeadModule();
	}
}
private void changeTabPath(int index, String path) {
	InnerTabs d = session.getLeadTabs().get(index);
	d.setPath(path);
	session.getLeadTabs().set(index, d);
	try {
		if (getParameter("fromListing").equals("fromListing")) {
			// don't update index
		} else {
			session.setSalesModule_leadTabIndex(0);
		}
	} catch (Exception e) {
		session.setSalesModule_leadTabIndex(0);
	}
}

public void loadLead(Long id) {
	System.out.println("loadLead " + id);
	LeadsBackingBean bean= this;
	bean.resetBean();
	try{
		SalesLeadORM lid = leadsDao.leadDetails(id);
		convert2Bean(lid, bean);
		listLeadsHistory(lid);
		listLeadComments(lid);		
	} catch(Exception e) {
		e.printStackTrace();
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
public String getCampaignSource() {
	return campaignSource;
}
public void setCampaignSource(String campaignSource) {
	this.campaignSource = campaignSource;
}
public List<SalesLeadHistoryORM> getLeadHistoryList() {
	return leadHistoryList;
}
public void setLeadHistoryList(List<SalesLeadHistoryORM> leadHistoryList) {
	this.leadHistoryList = leadHistoryList;
}
public List<SalesLeadCommentORM> getLeadCommentList() {
	return leadCommentList;
}
public void setLeadCommentList(List<SalesLeadCommentORM> leadCommentList) {
	this.leadCommentList = leadCommentList;
}
public UmUser getUmUser() {
	return umUser;
}
public void setUmUser(UmUser umUser) {
	this.umUser = umUser;
}
public String getUserComments() {
	return userComments;
}
public void setUserComments(String userComments) {
	this.userComments = userComments;
}
public Timestamp getLeadsAssignedDate() {
	return leadsAssignedDate;
}
public void setLeadsAssignedDate(Timestamp leadsAssignedDate) {
	this.leadsAssignedDate = leadsAssignedDate;
}
public UmCompany getCompany() {
	return company;
}

public void setCompany(UmCompany company) {
	this.company = company;
}

public SessionDataBean getSession() {
	return session;
}
public void setSession(SessionDataBean session) {
	this.session = session;
}
public Boolean getRenderCommentsForm() {
	return renderCommentsForm;
}

public void setRenderCommentsForm(Boolean renderCommentsForm) {
	this.renderCommentsForm = renderCommentsForm;
}

public void setList(List<SalesLeadORM> list) {
	this.list = list;
}
public void listLeadsHistory(SalesLeadORM leads){
	leadHistoryList = leadHistoryDao.leadHistoryList(leads);
	System.out.println("lead history list size = "+leadHistoryList.size());
	
}
public void listLeadComments(SalesLeadORM leads){
	System.out.println("listLeadComments() called..");
	leadCommentList = leadCommentDao.leadCommentList(leads);
	
}
private void convert2Bean(SalesLeadORM db,LeadsBackingBean bean) {
	bean.setLeadId(db.getLeadId());
	bean.setLeadAddress(db.getLeadAddress());
	bean.setLeadEmail(db.getLeadEmail());
	bean.setLeadName(db.getLeadName());
	bean.setLeadPhone(db.getLeadPhone());
	bean.setLeadSource(db.getLeadSource());
	bean.setLeadCompany(db.getLeadCompany());
	bean.setLeadStatus(db.getLeadStatus());
	bean.setLeadAddedon(db.getLeadAddedOn());
	bean.setCampaignSource(db.getCampaignSource());
	bean.setLeadAssigned(db.getLeadAssigned());
	
}	
public String strStatus(){
	switch(this.leadStatus){
		case "open" : return "Open"; 
		case "hot" : return "Hot Lead"; 
		case "won" : return "Interested";
		case "lost" : return "Not Interested";
	}
	return null;
}
public void setList() {
	try {
		this.list = leadsDao.listLeads(session.getCompanyId());
	} catch (LeadNotFoundException e) {			
		//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage(), e.getMessage()));
	}
}	
public void resetBean(){
	setLeadId(0L);
	setLeadAddress("");
	setLeadAssigned(0L);
	setLeadAddedon(null);
	setLeadCompany(0L);
	setLeadEmail("");
	setLeadName("");
	setLeadPhone("");
	setLeadsAssignedDate(null);
	setLeadSource(0L);
	setLeadStatus(null);
}
@Override
public String actionListener() {
	// TODO Auto-generated method stub
	long lead_id;
	System.out.println("Lead Action "+getAction());
	setCurrentAction(getAction(),this.getClass());
	switch (getCurrentAction()) {
	case WebConstants.ACTION_CREATE:
		resetBean();
		session.setSalesModule_selectedLead(0L);
		changeTabPath(0, "/view/sales/leads/leadForm.xhtml");
		renderCommentsForm = false;
		break;
	case WebConstants.ACTION_SAVE:
		createLead();
		reset();
		leadDetails();
		renderCommentsForm = true;
		break;
	case WebConstants.ACTION_VIEW:
		leadDetails();
		renderCommentsForm = true;
		break;
	case "postComments":
		try{
	    	Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			Long leadId = Long.valueOf(params.get("lead_id"));
			System.out.println("comments = "+getUserComments());
	    	postComments(leadId);
	    	loadLead(leadId);
	    	setViewAction();
			//setCancelAction(false);
			//setEditAction();
			renderCommentsForm = true;
			return null;
	    }catch(Exception e){
	    	addError(getProperty("message.leads.error.load.lead"));
			System.out.println("Exception Occured CaseBackingBean actionListener()");
			setSaveAction(); // in case of any failure
			return null;
	    }
	case WebConstants.ACTION_CANCEL:
		lead_id = Long.parseLong(getParameter("lead_id").toString());
		if(lead_id != 0L){
			session.setSalesModule_selectedLead(lead_id);
			leadDetails();
		}else{
			session.resetSalesLeadModule();
		}
		renderCommentsForm = true;
		break;
	case WebConstants.ACTION_EDIT:
		lead_id = Long.parseLong(getParameter("lead_id").toString());
		leadDetails();
		reset();
		setEditAction();
		renderCommentsForm = true;
		break;
	case WebConstants.ACTION_UPDATE:
		editLead(session.getSalesModule_selectedLead());
		reset();
		leadDetails();
		renderCommentsForm = true;
		break;
	case WebConstants.ACTION_DELETE:
		lead_id = Long.valueOf(getParameter("lead_id").toString());
		loadLead(lead_id);
		changeTabPath(0, "/view/sales/leads/leadForm.xhtml");
		reset();
		setDeleteAction();
		renderCommentsForm = true;
		break;
	case WebConstants.ACTION_DELETE_CONFIRMED:
		deleteLead();
		session.resetSalesLeadModule();
		renderCommentsForm = false;
		break;
	}
	return null;
	
}
@Override
public List<AjaxListStructure> getList() {
	ArrayList<AjaxListStructure> myList = new ArrayList<AjaxListStructure>();
	AjaxListStructure e;
	List<SalesLeadORM> leadDbList;
	try {
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		leadDbList = leadsDao.listLeads(session.getCompanyId());
		if(leadDbList.size() > 0){
			for(SalesLeadORM l : leadDbList){
				e = new AjaxListStructure();
				e.setId(l.getLeadId());
				e.setLabel(l.getLeadName());
				myList.add(e);
			}
		}
	} catch (LeadNotFoundException e1) {
		// TODO Auto-generated catch block
					e1.printStackTrace();
	}
	
	return myList;
}

 public void createLead(){
    	String company = utilsDao.getCompany(session.getCompanyId());
    	LeadsBackingBean l = this;
    	l.setLeadCompany(session.getCompanyId());
    	l.setLeadStatus("open");
    	SalesLeadORM slo = new SalesLeadORM(l.getLeadAddress(),l.getLeadAssigned(),l.getLeadEmail(),l.getLeadName(),l.getLeadPhone(),l.getLeadSource(),l.getLeadStatus(),l.getLeadAddedon(),l.getLeadCompany(),l.getCampaignSource(), new Timestamp(date.getTime()));
    	Long lid = leadsDao.createLead(slo,company);
    	l.setLeadId(lid);
    	if(l.getLeadAssigned() != null){
    		SalesLeadHistoryORM leadHistory = new SalesLeadHistoryORM(slo,l.getLeadAssigned(), new Timestamp(date.getTime()));
    		leadHistoryDao.createLeadHistory(leadHistory);
    		 
    	}
    	this.addMessage(getProperty("message.leads.created"));
    	session.setSalesModule_selectedLead(lid);
    }
    
 public void editLead(Long leadId){
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		LeadsBackingBean l = this;
    	l.setLeadCompany(session.getCompanyId());
		String[] name = l.getLeadName().split(" ",2);
		if(l.getLeadStatus().equals("won")){			
			try {
				CsAccount cs = new CsAccount(session.getCompanyId(),l.getLeadName(), l.getLeadAddress(), 169, 3021, 14548, null, l.getLeadEmail(), l.getLeadPhone(), l.getLeadAddedon(), false, true, userDao.umUserDetails(session.getUserId()));
				Long aId = accountDao.createAccount(cs);    		
				cs.setAccountId(aId);
				CsContactORM csc = new CsContactORM(((!name[1].isEmpty()) ? name[0] : null), ((!name[1].isEmpty()) ? name[1] : name[0]), null, l.getLeadAddress(), 169, 3021, 14548, null, l.getLeadEmail(), l.getLeadPhone(), "Not Available", null, l.getLeadAddedon(), l.getLeadAddedon(), true, session.getCompanyId(), l.getLeadEmail(), "123456", userDao.umUserDetails(session.getUserId()));
				Long cId;
				UmUser usr;
				cId = contactDao.createContact(csc, aId);
				csc.setContactId(cId);   
				usr = (l.getLeadAssigned() != 0) ? userDao.umUserDetails((long)l.getLeadAssigned()) : userDao.umUserDetails(session.getUserId());
				UmCompany umCompany = companyDao.companyDetails(session.getCompanyId());
				List<CampaignProduct> listProducts = campaignDao.listCampaignProducts(l.getLeadSource()); 
				SalesOpportunityORM soo = new SalesOpportunityORM(productDao.productDetails(listProducts.get(0).getProductId()),"pending",0,0,"Lead get converted to opportunity",usr,csc,umCompany);
	        	Long oId = opportunityDao.create(soo);
	        	soo.setOpportunityId(oId);
			} catch (AccountNotFoundException e) {
				e.printStackTrace();
			} catch (UserNotFoundException e) {
				e.printStackTrace();
			} catch (ProductNotFoundException e) {
				e.printStackTrace();
			}	
		}
			SalesLeadORM leads = new SalesLeadORM();
			try{
				leads = leadsDao.leadDetails(leadId);
		   	 }catch(LeadNotFoundException e){
		   	    System.out.println(e.getMessage());
		   	} 
		
			Date date = new Date();
	    	leadsAssignedDate = leads.getLeadAssignedDate();
	    	if(leads.getLeadAssigned()!= 0){
	    		SalesLeadHistoryORM leadHistory = new SalesLeadHistoryORM(leads,l.getLeadAssigned(), new Timestamp(date.getTime()));
	    		leadHistoryDao.createLeadHistory(leadHistory);
	    		System.out.println("createLeadHistory called" + leadHistory.getLeadHistoryId());
	    		leadsAssignedDate = new Timestamp(date.getTime());
	    	}else{
	    		SalesLeadHistoryORM leadHistory =  new SalesLeadHistoryORM(leads,l.getLeadAssigned(), new Timestamp(date.getTime()));
	    		leadHistoryDao.createLeadHistory(leadHistory);
	    		System.out.println("createLeadHistory called" + leadHistory.getLeadHistoryId());
	    		leadsAssignedDate = new Timestamp(date.getTime());
	    		}
		SalesLeadORM slo = new SalesLeadORM(leadId,l.getLeadAddress(),l.getLeadAssigned(),l.getLeadEmail(),l.getLeadName(),l.getLeadPhone(),l.getLeadSource(),l.getLeadStatus(),l.getLeadAddedon(),l.getLeadCompany(),l.getCampaignSource(),l.getLeadsAssignedDate());
		leadsDao.updateLead(slo);    	
		this.addMessage(getProperty("message.leads.updated"));
		
	}
 public String getUserName(Long leadAssigned) throws UserNotFoundException{
    	UmUser luser = userDao.umUserDetails(leadAssigned);
    	return luser.getUserFname()+" " +luser.getUserLname();
    }
 public List<UmUser> listUsers(){
    	UsersListingBackingBean l = BeanFactory.getInstance().getUsersListingBackingBean();
    	return l.getLeadUsers();
    }
 public List<CampaignBackingBean> listSources(){
    	MarketingControllerBean l = BeanFactory.getInstance().getMarketingControllerBean();  
    	return l.getCampaigns();
    } 
 public String assignedTo(){
		UmUser user = null;
		try {
			user = (this.leadAssigned != 0) ? userDao.umUserDetails((long)this.leadAssigned) : null;
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (user != null) ? user.getUserName() : "";
	}
 public String source(){
		MCampaign camp = (this.leadSource != 0L) ? campaignDao.campaignDetails(this.leadSource) : null;
		return (camp != null) ? camp.getTitle() : "";
	}
 public void deleteLead(){
		SalesLeadORM leads = new SalesLeadORM();
			try{
				leads = leadsDao.leadDetails(leadId);
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
			 //delete comments from leadUserComments
	        leadCommentDao.deleteLeadComments(leads);
	        // delete from leadHistory
	        leadHistoryDao.deleteLeadHistory(leads);
	        //delete from DB
	        leadsDao.deleteLead(leadId);
	        addMessage(getProperty("message.leads.deleted"));
	        /*session.setSalesModule_selectedLead(0L);
			leadDetails();
			reset();
			setViewAction();*/
			
		}
 public Boolean decodeNow(int pos){
	 SessionDataBean session = BeanFactory.getInstance().getSessionBean();
     String[] selectedPermissions = new String[6];
     selectedPermissions = utilsDao.modulePermissions(session.getUserId(), 9);		 
     return (selectedPermissions[pos] != null) ? true : false;
}
	public void postComments(Long leadId){
		System.out.println("LeadBackingBean postComments() called");
		if(getUserComments() == null || getUserComments().equalsIgnoreCase("")){
            addWarning(getProperty("message.validate.leads.comments")); 
			return;			
		}
		SessionDataBean sessionBean = BeanFactory.getInstance().getSessionBean();
		SalesLeadORM leads = new SalesLeadORM();
		try{
			leads = leadsDao.leadDetails(leadId);
		
		} catch (LeadNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(leads.getLeadAssigned() == null){
			addWarning(getProperty("message.validate.leads.leadNotAssigned"));
			return;
		}
		listLeadsHistory(leads);
		SalesLeadHistoryORM leadHistory = new SalesLeadHistoryORM();
		for(SalesLeadHistoryORM leadHistoryORM:leadHistoryList){
			leadHistory = leadHistoryORM;
		}
		UmUser user = new UmUser();
		try{
		  user = userDao.umUserDetails(sessionBean.getUserId());
        }catch(UserNotFoundException e){
        	System.out.println(e.getMessage());
        }
		System.out.println("in post comment method tryin to catch error before date()" + user.getUserId());
		Date date = new Date();	
		SalesLeadCommentORM salesLeadComment = new SalesLeadCommentORM(leads, user, leadHistory, new Timestamp(date.getTime()), getUserComments());
		leadCommentDao.createLeadComment(salesLeadComment);
		setUserComments(null);
		addMessage(getProperty("message.leads.comment.added"));
	}
 public HashMap<String, String> campaignSourceList() {
		HashMap<String, String> repMap = new HashMap<String, String>();
		repMap.put("Email", "Email");
		repMap.put("Phone", "Phone");
		repMap.put("WebSite", "WebSite");
		repMap.put("NewsPaper", "NewsPaper");
		repMap.put("Advertisement", "Advertisement");
		repMap.put("Friends", "Friends");
		repMap.put("Others", "Others");
		return repMap;
	}

}

