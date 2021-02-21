package com.srpl.crm.web.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.primefaces.model.DualListModel;

import com.bitguiders.util.jsf.JSFBeanInterface;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;
import com.srpl.crm.ejb.entity.CsContactORM;
import com.srpl.crm.ejb.entity.MCampaign;
import com.srpl.crm.ejb.entity.MarketingCampaignSettings;
import com.srpl.crm.ejb.entity.MarketingCampaignSettingsCustomers;
import com.srpl.crm.ejb.request.CampaignDAO;
import com.srpl.crm.ejb.request.CampaignSettingsCustomersDAO;
import com.srpl.crm.ejb.request.CampaignSettingsDAO;
import com.srpl.crm.ejb.request.ContactDAO;
import com.srpl.crm.web.common.InnerTabs;
import com.srpl.crm.web.common.SessionDataBean;
import com.srpl.crm.web.controller.BeanFactory;
import com.srpl.um.ejb.entity.MailTemplateModuleORM;
import com.srpl.um.ejb.entity.MailTemplateORM;
import com.srpl.um.ejb.entity.UmUser;
import com.srpl.um.ejb.request.MailTemplateDAO;
import com.srpl.um.ejb.request.MailTemplateModuleDAO;


@ManagedBean(name = "campaignSettings")
@RequestScoped
public class MarketingModuleSettingsBackingBean extends JSFBeanSupport implements JSFBeanInterface,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	private Date campaignSettingsDate;

	private List<CsContactORM> customerSourceList = new ArrayList<CsContactORM>();
	private List<CsContactORM> customerTargetList = new ArrayList<CsContactORM>();
	private DualListModel<CsContactORM> customerList = new DualListModel<CsContactORM>(customerSourceList, customerTargetList);
	private Integer[] customersIds;
	private List<UmUser> usersList;
	private SessionDataBean session;
	private String potentialemails;
	private long selectedTemplate;
	private Date currentDate = new Date();
		
	@EJB ContactDAO contactDao;
	@EJB CampaignSettingsDAO campaignSettingsDao;
	@EJB CampaignSettingsCustomersDAO campaignSettingsCustomersDAO;
	@EJB CampaignDAO campaignDao;
	@EJB MailTemplateDAO templateDao;
	@EJB MailTemplateModuleDAO templateModuleDao;
	
	public MarketingModuleSettingsBackingBean(){
		System.out.println("MarketingModuleSettingsBackingBean() called");
		session = BeanFactory.getInstance().getSessionBean();	  
	}	

	public Date getCurrentDate() {
		return currentDate;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCampaignSettingsDate() {
		return campaignSettingsDate;
	}

	public void setCampaignSettingsDate(Date campaignSettingsDate) {
		this.campaignSettingsDate = campaignSettingsDate;
	}

	public DualListModel<CsContactORM> getCustomerList() {
		return listCustomers();
	}

	public void setCustomerList(DualListModel<CsContactORM> customerList) {
		this.customerList = customerList;
	}
	

	public List<CsContactORM> getCustomerSourceList() {
		return customerSourceList;
	}

	public void setCustomerSourceList(List<CsContactORM> customerSourceList) {
		this.customerSourceList = customerSourceList;
	}

	public List<CsContactORM> getCustomerTargetList() {
		return customerTargetList;
	}

	public void setCustomerTargetList(List<CsContactORM> customerTargetList) {
		this.customerTargetList = customerTargetList;
	}

	public Integer[] getCustomersIds() {
		return customersIds;
	}

	public void setCustomersIds(Integer[] customersIds) {
		this.customersIds = customersIds;
	}

	public List<UmUser> getUsersList() {
		return usersList;
	}

	public void setUsersList(List<UmUser> usersList) {
		this.usersList = usersList;
	}
	
	public String getPotentialemails() {
		return potentialemails;
	}

	public void setPotentialemails(String potentialemails) {
		this.potentialemails = potentialemails;
	}
	
	public long getSelectedTemplate() {
		return selectedTemplate;
	}

	public void setSelectedTemplate(long selectedTemplate) {
		this.selectedTemplate = selectedTemplate;
	}

	public DualListModel<CsContactORM> listCustomers(){
		System.out.println("MarketingModuleSettingsBackingBean listCustomers() called...");
		List<CsContactORM> customerSourceList = new ArrayList<CsContactORM>();
		List<CsContactORM> customerTargetList = new ArrayList<CsContactORM>();
		try{
		  BeanFactory beanFactory = BeanFactory.getInstance(); 	
		  SessionDataBean session = beanFactory.getSessionBean();
		  MCampaign campaign = campaignDao.campaignDetails(session.getMarketingModule_selectedCampaign());
		  MarketingCampaignSettings campaignSettings = campaignSettingsDao.campaignSettingsDetail(campaign);
		  if(campaignSettings.getCampaignSettingsId() != null){
			 this.message = campaignSettings.getCampaignSettingsMessage();
			 this.campaignSettingsDate = campaignSettings.getCampaignSettingsDate(); 
			 this.selectedTemplate = campaignSettings.getMailTemplate();
			 this.potentialemails = campaign.getPotentialCustomerEmailIds();
		     customerSourceList = contactDao.listContacts(session.getCompanyId());
		     List<MarketingCampaignSettingsCustomers> campaignSettingsCustomers = campaignSettingsCustomersDAO.listCampaignSettingsCustomers(campaignSettings);
		     for(MarketingCampaignSettingsCustomers campaignSettingsCustomer : campaignSettingsCustomers){
			    customerTargetList.add(campaignSettingsCustomer.getCsContact());
		     }
		     for(CsContactORM targetContact : customerTargetList){
			    try{
                   for(CsContactORM sourceContact : customerSourceList){
                	   Long sourceContactId = sourceContact.getContactId();
   				       Long targertContactId = targetContact.getContactId();
   				       if(sourceContactId == targertContactId){
                         customerSourceList.remove(sourceContact);
				       }
                   } // end inner for
			     }catch(ConcurrentModificationException e){
				    
			     } 
		     } // end outer for
		  }else{
			  customerSourceList = contactDao.listContacts(session.getCompanyId());
			  this.message = campaign.getDescription();
		  }  
	    }catch(Exception e){
	    	System.out.println("Exception occur");
			System.out.println(e.getMessage());
		}
		customerList = new DualListModel<CsContactORM>(customerSourceList, customerTargetList);
		return customerList;
	}	
	
	public void loadCampaignSetting(Long campaignSettingsId){
		System.out.println("loadCampaignSetting() called");
		MarketingCampaignSettings marketingCampaignSetting = campaignSettingsDao.campaignSettingsDetail(campaignSettingsId);
		this.message = marketingCampaignSetting.getCampaignSettingsMessage();
		this.campaignSettingsDate = marketingCampaignSetting.getCampaignSettingsDate();
		this.selectedTemplate = marketingCampaignSetting.getMailTemplate();
		System.out.println("test");
		marketingCampaignSetting.getCampaignSettingsCustomersList();
		System.out.println("list size = "+marketingCampaignSetting.getCampaignSettingsCustomersList().size());
		
	}
	
	/*public void createCampaignSettings(){
		System.out.println("createCampaignSettings()");
		Long cId;
		MCampaign campaign = campaignDao.campaignDetails(session.getMarketingModule_selectedCampaign());
	    Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try{
			date = dateFormat.parse(dateFormat.format(date));
		}catch(Exception e){
			e.printStackTrace();
		}
		if(campaignSettingsDate.compareTo(date) < 0){
			addError(getProperty("message.marketing.campaign.setting.date.error"));
			return;
		}
		
//		List<MarketingCampaignSettings> campaignSettingsList = campaignSettingsDao.listCampaignSettings(campaign);
		MarketingCampaignSettings campaignSettings = campaignSettingsDao.campaignSettingsDetail(campaign);
		if(campaignSettings.getCampaignSettingsId() != null){
			campaignSettingsDao.deleteCampaignSettings(campaign);
		}	
		MarketingCampaignSettings marketingCampaignSetting = new MarketingCampaignSettings(message, campaignSettingsDate, campaign);
		campaignSettingsDao.createCampaignSettings(marketingCampaignSetting);
		MarketingCampaignSettingsCustomers campaignSettingsCustomers;
		for(String str : customerList.getTarget()){
			if((cId = getContactID(str)) != 0){
				CsContactORM contact;
				try {
					contact = contactDao.contactDetails(cId);
					campaignSettingsCustomers = new MarketingCampaignSettingsCustomers(marketingCampaignSetting , contact);
					campaignSettingsCustomersDAO.createCampaignSettingCustomers(campaignSettingsCustomers);
				} catch (ContactNotFoundException e) {
					e.printStackTrace();
				}
			}	
		}
		addMessage(getProperty("message.marketing.campaign.setting.saved"));
	}*/
	
	public void createCampaignSettings(){
		System.out.println("createCampaignSettings()");
		MCampaign campaign = campaignDao.campaignDetails(session.getMarketingModule_selectedCampaign());
		if(potentialemails.length() > 0){
			campaign.setPotentialCustomerEmailIds(potentialemails);
			campaignDao.updateCampaign(campaign);
		}
	    /*Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try{
			date = dateFormat.parse(dateFormat.format(date));
		}catch(Exception e){
			e.printStackTrace();
		}
		if(campaignSettingsDate.compareTo(date) < 0){
			addError("date can not be less than current date");
			return;
		}*/
		MarketingCampaignSettings campaignSettings = campaignSettingsDao.campaignSettingsDetail(campaign);
		if(campaignSettings.getCampaignSettingsId() != null)
			campaignSettingsDao.deleteCampaignSettings(campaign);
		MarketingCampaignSettings marketingCampaignSetting = new MarketingCampaignSettings(message, campaignSettingsDate, campaign, selectedTemplate);
		campaignSettingsDao.createCampaignSettings(marketingCampaignSetting);
		MarketingCampaignSettingsCustomers campaignSettingsCustomers;
		for(CsContactORM contact : customerList.getTarget()){
			campaignSettingsCustomers = new MarketingCampaignSettingsCustomers(marketingCampaignSetting , contact);
			campaignSettingsCustomersDAO.createCampaignSettingCustomers(campaignSettingsCustomers);
		}		
		addMessage("Campaign settings saved");
	}
	
	public void launchCampaign(){
		StringBuilder emails = new StringBuilder();
		MCampaign campaign = campaignDao.campaignDetails(session.getMarketingModule_selectedCampaign());
		if(potentialemails.length() > 0){			
			campaign.setPotentialCustomerEmailIds(potentialemails);
			campaignDao.updateCampaign(campaign);
			if(potentialemails.length() > 0) emails.append(potentialemails);
		}
		if(customerList.getTarget().size() > 0) {
			for(CsContactORM contact : customerList.getTarget()){
				if(contact.getContactEmail() != "") {
					if(emails.length() > 0) emails.append(";");
					emails.append(contact.getContactEmail());
				}
			}
		}
		if(emails.length() == 0){
			FacesMessage fmessage = new FacesMessage("Please select Customers or provide Potential Customers email(s)");
			FacesContext.getCurrentInstance().addMessage("csettings", fmessage);
			return;
		}
		final String username = "rizwan.softwareengineer05@gmail.com";
		final String password = "systemsresearchltd";
    	Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.user", username);
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.debug", true);   
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");        
        Session session = Session.getInstance(props, null);
        session.setDebug(true);            
        try {         
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("rizwan.softwareengineer05@gmail.com"));
			msg.addRecipients(RecipientType.TO, InternetAddress.parse(emails.toString().replace(";", ",")));
	        msg.setSubject(campaign.getTitle());
	        msg.setSentDate(new Date());
	        msg.setContent(message,"text/html");
	        try{
	        	MailTemplateORM tempModule = null;
	        	tempModule = templateDao.details(selectedTemplate);
	        	msg.setContent(templateDao.getMessageWithMailTemplate(tempModule,campaign),"text/html");
	        }catch (Exception e) {
				e.printStackTrace();
			}
	        msg.saveChanges();
	        Transport transport = session.getTransport("smtp");
	        transport.connect("smtp.gmail.com", username, password);
	        transport.sendMessage(msg, msg.getAllRecipients());
	        transport.close();
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
        FacesMessage message = new FacesMessage(getProperty("message.marketing.campaign.emailConfirm"));
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public List<MarketingCampaignSettings> campaignSettingsList(){
		System.out.println("campaignSettingsList() called");
		List<MarketingCampaignSettings> campaignSettingsList = new ArrayList<MarketingCampaignSettings>();
		MCampaign campaign = campaignDao.campaignDetails(session.getMarketingModule_selectedCampaign());
		campaignSettingsList = campaignSettingsDao.listCampaignSettings(campaign);
		return campaignSettingsList;
	}
	
	
	@Override
	public String actionListener() {
		// TODO Auto-generated method stub
		setCurrentAction(getAction(),this.getClass());
		System.out.println("actionListener() = getCurrentAction() = "+getCurrentAction());
		switch(getCurrentAction()){
		case WebConstants.ACTION_SAVE:
			try{
			  createCampaignSettings();
			  session.setMarketingModule_tabIndex(1);
			  changeTabPath(0, "/view/marketing/campaign/campaignForm.xhtml");
			  changeTabPath(1, "/view/marketing/settings/campaignSettingsForm.xhtml");
			  changeTabPath(2, "/view/marketing/leads/campaignLeadsList.xhtml");
			  changeTabPath(3, "/view/marketing/products/campaignProducts.xhtml");
			  setSaveAction();
		      break;
			}catch(Exception e){
				addError(getProperty("message.marketing.campaign.save.fail"));
				setSaveAction();
			    return WebConstants.ACTION_LIST;
			}
		/*case WebConstants.ACTION_VIEW:	
           try{
           Map<String,String> params=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
           Long campaignSettingsId = Long.parseLong(params.get("row_id"));
           System.out.println("campaignSettingsId = "+campaignSettingsId);
		   loadCampaignSetting(campaignSettingsId);
           session.setMarketingModule_tabIndex(1);
		   changeTabPath(0, "/view/marketing/campaign/campaignForm.xhtml");
		   changeTabPath(1, "/view/marketing/settings/campaignSettingsForm.xhtml");
		   changeTabPath(2, "/view/marketing/leads/campaignLeadsList.xhtml");
		   changeTabPath(3, "/view/marketing/products/campaignProducts.xhtml");
           break;
           }catch(Exception e){
        	   addError(getProperty("message.marketing.campaign.error.load.setting"));
			   setSaveAction();
			   return WebConstants.ACTION_LIST;
           }*/
		case WebConstants.ACTION_LAUNCH:
			launchCampaign();
			return WebConstants.ACTION_LIST;
		}
		
		return(null);

	}
	
	public List<MailTemplateORM> getTemplatesList(){
		List<MailTemplateORM> templateList = null;
		templateList = templateDao.list(session.getCompanyId(),"com.srpl.crm.ejb.entity.MCampaign");
		return templateList;
	}

	@Override
	public List<?> getList() {
		// TODO Auto-generated method stub
		return null;
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
			System.out.println("exception in change path");
			session.setMarketingModule_tabIndex(0);
		}
	}
	
}
