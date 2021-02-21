package com.srpl.crm.web.model.loyalty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.UnselectEvent;

import com.srpl.crm.web.controller.BeanFactory;
import com.srpl.crm.ejb.entity.CsContactORM;
import com.srpl.crm.ejb.entity.MessageTemplateORM;
import com.srpl.crm.ejb.exceptions.ContactNotFoundException;
import com.srpl.crm.ejb.request.ContactDAO;
import com.srpl.crm.ejb.request.EmailTemplateDAO;
import com.srpl.crm.ejb.request.MessageTemplateDAO;
import com.srpl.crm.web.common.AjaxListStructure;
import com.srpl.crm.web.common.InnerTabs;
import com.srpl.crm.web.common.SessionDataBean;
import com.bitguiders.util.jsf.JSFBeanInterface;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;

@ManagedBean(name = "templateBackingBean")
@RequestScoped
public class TemplateBackingBean extends JSFBeanSupport implements
		JSFBeanInterface, Serializable {

	@EJB
	MessageTemplateDAO messageDao;
	@EJB
	ContactDAO contactDao;
	@EJB
	EmailTemplateDAO Etemplate;

	private static final long serialVersionUID = 1L;

	public TemplateBackingBean() {
		session = BeanFactory.getInstance().getSessionBean();
		setCurrentAction(WebConstants.ACTION_SECURITY, this.getClass());
	}

	@Override
	public void setViewAction() {
		super.setViewAction();
		setCancelAction(false);
	}

	public void templateDetails() {
		System.out.println("++" + session.getLoyaltyModule_selectedTemplate());
		if (session.getLoyaltyModule_selectedTemplate() != 0) {
			loadTemplate(session.getLoyaltyModule_selectedTemplate());
			changeTabPath(0, "/view/loyalty/messageTemplate/templateForm.xhtml");
			setViewAction();
		} else {
			session.resetLoyaltyTemplateModule();
		}
	}

	private void changeTabPath(int index, String path) {
		InnerTabs d = session.getLoyaltyTemplateTabs().get(index);
		d.setPath(path);
		session.getLoyaltyTemplateTabs().set(index, d);
		try {
			if (getParameter("fromListing").equals("fromListing")) {
			} else {
				session.setLoyaltyModule_tabIndex(0);

			}
		} catch (Exception e) {
			session.setLoyaltyModule_tabIndex(0);
		}
	}

	private String template;
	private Long templateId;
	private String title;
	private String message;
	private String sendTo;
	private List<MessageTemplateORM> templateList;
	private List<String> contactList;
	private String subscriber;
	private String selectedContact;
	private SessionDataBean session;
	private String filterBy;
	private String filterValue;
	private Long companyId;


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

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSendTo() {
		return sendTo;
	}

	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}

	public List<MessageTemplateORM> getTemplateList() {
		return templateList;
	}

	public void setTemplateList(List<MessageTemplateORM> templateList) {
		this.templateList = templateList;
	}

	public List<String> getContactList() {
		return contactList;
	}

	public void setContactList(List<String> contactList) {
		this.contactList = contactList;
	}

	public String getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(String subscriber) {
		this.subscriber = subscriber;
	}

	public String getSelectedContact() {
		return selectedContact;
	}

	public void setSelectedContact(String selectedContact) {
		this.selectedContact = selectedContact;
	}

	public SessionDataBean getSession() {
		return session;
	}

	public void setSession(SessionDataBean session) {
		this.session = session;
	}

	public void resetBean() {
		setTemplateId(0L);
		setTitle("");
		setMessage("");
		setSendTo("");
	}

	public void loadTemplate(Long id) {
		TemplateBackingBean templateBean = this;
		MessageTemplateORM db;
		try {
			db = messageDao.messageTemplateDetails(id);
			convertToBean(db, templateBean);
			String temp[];
			List<String> myList = new ArrayList<String>();
			temp = templateBean.getSendTo().split(",");
			System.out.println("size of temp is +: " + temp.length);
			for (String x : temp) {
				System.out.println(x);
				myList.add(x);
			}
			setContactList(myList);

		} catch (Exception e) {
			// TODO: handle exception
			changeTabPath(0,
					"/view/loyalty/messageTemplate/templateNoSelection.xhtml");
			System.out.println("invalid id for Template");
		}
	}

	// TODO Action Listener
	public String actionListener() {
		System.out.println("action listener called in template");
		TemplateBackingBean bean;
		MessageTemplateORM db;
		long id;
		setCurrentAction(getParameter("action"), this.getClass());
		switch (getCurrentAction()) {
		case WebConstants.ACTION_CREATE:
			System.out.println("create action called");
			changeTabPath(0, "/view/loyalty/messageTemplate/templateForm.xhtml");
			bean = this;
			bean.resetBean();
			break;
		
		case WebConstants.ACTION_SAVE:
			createTemplate();
			break;

		case WebConstants.ACTION_VIEW:
			this.templateDetails();
			setViewAction();
			break;
		case WebConstants.ACTION_CANCEL:
			this.templateDetails();
			setViewAction();
			break;
		case WebConstants.ACTION_EDIT:
			System.out.println("edit template");
			id = Long.parseLong(this.getParameter("template_id"));
			System.out.println(id + "id at edit");
			this.loadTemplate(id);
			this.changeTabPath(0,
					"/view/loyalty/messageTemplate/templateForm.xhtml");
			setEditAction();
			break;
	
		case WebConstants.ACTION_UPDATE:
			bean = this;
			id = Integer.valueOf(this.getParameter("template_id").toString());
			bean.setTemplateId(id);
			try {
				String value = "";
				for (String con : bean.getContactList()) {
					value += con + ",";
				}

				bean.setSendTo(value);
				db = new MessageTemplateORM();
				convertToDb(bean, db);

				id = messageDao.updateTemplate1(db);

				addMessage(getProperty("message.loyalty.messagetemplate.updated"));
			} catch (Exception e) {
				addError(getProperty("message.loyalty.messagetemplate.updation.failed"));
			}
			reset();
			this.templateDetails();
			break;
		

		case WebConstants.ACTION_DELETE:
			System.out.println("new group bean delete action called");
			id = Long.parseLong(this.getParameter("template_id"));
			this.loadTemplate(id);
			this.changeTabPath(0,
					"/view/loyalty/messageTemplate/templateForm.xhtml");
			setDeleteAction();
			break;
		
		case WebConstants.ACTION_DELETE_CONFIRMED:
			System.out.println("confirmed delete action called");
			try {
				messageDao.deleteTemplate(session
						.getLoyaltyModule_selectedTemplate());
				this.addMessage(getProperty("message.loyalty.messagetemplate.deleted"));
			} catch (Exception e) {
				this.addError(getProperty("message.loyalty.messagetemplate.deletion.failed"));
			}
			session.resetLoyaltyTemplateModule();
			this.resetBean();
			break;
	
		case WebConstants.ACTION_SEND:
			id = Long.valueOf(getParameter("template_id").toString());
			this.loadTemplate(id);
			changeTabPath(0, "/view/loyalty/messageTemplate/templateForm.xhtml");
			int length = 0;
			try {
				length = this.getSendTo().length();
			} catch (Exception e) {
				// TODO: handle exception
			}
			Boolean mailSendingSuccess = true;
			if (length > 0) {
				int counter = 0 ;
				for (String email : contactList) {
					
					if (!Etemplate.sendEmail(email, title, message)) {
						mailSendingSuccess = false;
					}
				}
				if(mailSendingSuccess){
					this.addMessage(getProperty("message.loyalty.messagetemplate.email.confirmation"));
				}else{
					this.addError(getProperty("message.loyalty.messagetemplate.email.confirmation.failed"));
				}
				setViewAction();
			break;
			}
		}
		return (null);
		

	}

	public ArrayList<String> ContactsEmail() throws ContactNotFoundException {
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		ArrayList<String> myList = new ArrayList<String>();
		try {
			List<CsContactORM> cDB = contactDao.listContacts(session
					.getCompanyId());
			for (CsContactORM co : cDB) {
				myList.add(co.getContactEmail());

			}
		} catch (Exception e) {
			this.addError(getProperty("message.loyalty.nocontact.found"));

		}
		return myList;
	}

	public void handleUnselect(UnselectEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Unselected:" + event.getObject().toString(), null);

		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	private void convertToBean(MessageTemplateORM db, TemplateBackingBean bean) {
		bean.setCompanyId(session.getCompanyId());
		bean.setTemplateId(db.getTemplateId());
		bean.setTitle(db.getTitle());
		bean.setMessage(db.getMessage());
		bean.setSendTo(db.getSendTo());
	}

	private void convertToDb(TemplateBackingBean bean, MessageTemplateORM db) {
		db.setCompanyId(session.getCompanyId());
		db.setTemplateId(bean.getTemplateId());
		db.setTitle(bean.getTitle());
		db.setMessage(bean.getMessage());
		db.setSendTo(bean.getSendTo());
	}

	public void createTemplate() {
		TemplateBackingBean bean = this;
		long id;
		try {
			String value = "";
			for (String con : getContactList()) {
				value += con + ",";
			}
			setSendTo(value);
			MessageTemplateORM db = new MessageTemplateORM(bean.getTitle(),
					bean.getMessage(), bean.getSendTo(), null,session.getCompanyId());
			id = messageDao.createTemplate(db);
			SessionDataBean session = BeanFactory.getInstance().getSessionBean();
			session.setLoyaltyModule_selectedTemplate(id);
			setTemplateId(id);
			addMessage(getProperty("message.loyalty.messagetemplate.created"));
		} catch (Exception createExp) {
			addError(getProperty("message.loyalty.messagetemplate.creation.failed"));
			setCreateAction();
		}
		this.templateDetails();
		reset();
		setViewAction();
		
	}

	public List<AjaxListStructure> getList() {
		ArrayList<AjaxListStructure> myList = new ArrayList<AjaxListStructure>();
		AjaxListStructure e;
		List<MessageTemplateORM> templateDbList = null;
		templateDbList = messageDao.listTemplates(session.getCompanyId(),
				getFilterBy(), getFilterValue());	
		if(templateDbList.size() != myList.size()){
			for (MessageTemplateORM x : templateDbList) {
				e = new AjaxListStructure();
				e.setId(x.getTemplateId());
				e.setLabel(x.getTitle());
				myList.add(e);
			}
		}
		if(myList.size() == 0){
			e = new AjaxListStructure();
			e.setId(0L);
			e.setLabel("No Template Found.");
			myList.add(e);
			changeTabPath(0, "/view/loyalty/messageTemplate/templateNoSelection.xhtml");
		}
		return myList;
	}

}
