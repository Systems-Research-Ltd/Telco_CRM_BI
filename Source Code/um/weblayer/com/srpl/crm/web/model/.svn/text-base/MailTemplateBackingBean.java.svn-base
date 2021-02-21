package com.srpl.crm.web.model;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import com.bitguiders.util.jsf.JSFBeanInterface;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;
import com.srpl.crm.web.common.InnerTabs;
import com.srpl.um.ejb.entity.MailTemplateModuleORM;
import com.srpl.um.ejb.entity.MailTemplateORM;
import com.srpl.um.ejb.entity.TEMPLATE_SECTION;
import com.srpl.um.ejb.entity.UmCompany;
import com.srpl.um.ejb.request.CompanyDAO;
import com.srpl.um.ejb.request.MailTemplateDAO;
import com.srpl.um.ejb.request.MailTemplateModuleDAO;
import com.srpl.um.web.common.SessionDataBean;
import com.srpl.um.web.controller.BeanFactory;

@ManagedBean(name = "mailTemplate")
public class MailTemplateBackingBean extends JSFBeanSupport implements
		JSFBeanInterface, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3870858992135517419L;
	private long templateId;
	private String title;
	private MailTemplateModuleORM module;
	private String template;
	private String selectedPlaceholder;
	private TEMPLATE_SECTION section;
	private SessionDataBean session;

	@EJB
	MailTemplateModuleDAO moduleDao;
	@EJB
	MailTemplateDAO templateDao;
	@EJB
	CompanyDAO companyDao;

	public long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(long templateId) {
		this.templateId = templateId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public MailTemplateModuleORM getModule() {
		return module;
	}

	public void setModule(MailTemplateModuleORM module) {
		this.module = module;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String tempalte) {
		this.template = tempalte;
	}

	public String getSelectedPlaceholder() {
		return selectedPlaceholder;
	}

	public void setSelectedPlaceholder(String selectedPlaceholder) {
		this.selectedPlaceholder = selectedPlaceholder;
	}

	public TEMPLATE_SECTION getSection() {
		return section;
	}

	public SessionDataBean getSession() {
		return session;
	}

	public void setSession(SessionDataBean session) {
		this.session = session;
	}

	public void setSection(TEMPLATE_SECTION section) {
		this.section = section;
		if (this.section != null) {
			switch (section.toString()) {
			case "create_user":
			case "change_password":
			case "forget_password":
				this.module = moduleDao.details(2);
				break;
			case "campaign":
				this.module = moduleDao.details(1);
				break;
			case "loyalty":
				this.module = moduleDao.details(4);
				break;
			case "case_registration":
				this.module = moduleDao.details(3);
				break;
			case "invoice":
				this.module = moduleDao.details(5);
				break;
			}
		}
	}

	public MailTemplateBackingBean() {
		module = null;
		session = BeanFactory.getInstance().getSessionBean();
	}

	@PostConstruct
	public void postConstruct() {
		String temp = "";
		try {
			temp = getParameter("section" + "");
			switch (temp) {
			case "campaign":
				this.module = moduleDao.details(1);
				break;
			case "create_user":
			case "change_password":
			case "forget_password":
				this.module = moduleDao.details(2);
				break;
			case "case_registration":
				this.module = moduleDao.details(3);
				break;
			case "loyalty":
				this.module = moduleDao.details(4);
				break;
			case "invoice":
				this.module = moduleDao.details(5);
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (getAction().equals("")) {
			// No action param passed
			try {
				if (session.getMtModule_selectedTemplate() > 0) {
					load(session.getMtModule_selectedTemplate());
					setViewAction();
				} else {
					session.setMtModule_selectedTemplate(0L);
					changeTabPath(0,
							"/view/mailTemplates/mailTemplatesNoSelection.xhtml");
				}
			} catch (Exception e) {
				session.setMtModule_selectedTemplate(0L);
				changeTabPath(0,
						"/view/mailTemplates/mailTemplatesNoSelection.xhtml");
			}
		}
	}

	public List<MailTemplateModuleORM> getRegisteredModulesList() {
		List<MailTemplateModuleORM> modules = null;
		modules = moduleDao.list();
		return modules;
	}

	public List<Method> getPlaceholders() {
		List<Method> fieldsList = new ArrayList<Method>();
		Method[] methods = null;
		if (this.module != null) {
			try {
				Class<?> selectedClass = Class.forName(module.getClassName());
				methods = selectedClass.getMethods();
				for (Method x : methods) {
					if (x.getName().contains("get")) {
						fieldsList.add(x);
					}
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return fieldsList;
	}

	private void changeTabPath(int index, String path) {
		InnerTabs d = session.getMtTabs().get(index);
		d.setPath(path);
		session.getMtTabs().set(index, d);
		try {
			if (getParameter("fromListing").equals("fromListing")) {
				// don't update index
			} else {
				session.setMtModule_tabIndex(index);
			}
		} catch (Exception e) {
			session.setMtModule_tabIndex(0);
		}
	}

	@Override
	public void setViewAction() {
		super.setViewAction();
		setCancelAction(false);
		setResetAction(false);
	}

	@Override
	public String actionListener() {
		System.out.println("MailTemplateBackingBean actionListener() called..");
		reset();
		setCurrentAction(getParameter("action"), this.getClass());
		switch (getCurrentAction()) {
		case WebConstants.ACTION_CREATE:
			setCreateAction();
			changeTabPath(0, "/view/mailTemplates/mailTemplatesForm.xhtml");
			break;
		case WebConstants.ACTION_SAVE:
			try {
				save();
				addMessage("Template Successfully Created.");
			} catch (Exception e) {
				addError("Could not add Mail Template");
			}
			changeTabPath(0, "/view/mailTemplates/mailTemplatesForm.xhtml");
			reset();
			setViewAction();
			break;

		case WebConstants.ACTION_CANCEL:
		case WebConstants.ACTION_VIEW:
			try {
				view();
			} catch (Exception e) {
				// handle exception
				addError("Could not Load the Template.");
				System.out
						.println("Exception Occured MailTempalteBackingBean actionListener()");
			}
			reset();
			setViewAction();
			break;

		case WebConstants.ACTION_EDIT:
			try {
				view();
			} catch (Exception e) {
				addError("Could not Load the Template");
				System.out
						.println("Exception Occured MailTemplateBackingBean actionListener()");
			}
			// reset();
			setEditAction();
			break;

		case WebConstants.ACTION_UPDATE:
			try {
				Long mtId = session.getMtModule_selectedTemplate();
				update(mtId);
				addMessage("Template Successfully Updated.");
			} catch (Exception e) {
				System.out
						.println("Exception Occured MailTemplateBackingBean actionListener()");
			}
			reset();
			setViewAction();
			break;

		case WebConstants.ACTION_DELETE:
			try {
				view();
				addMessage("Do you really want to delete ");
				reset();
				setDeleteAction();
			} catch (Exception e) {
				addError("Could not Load the Template.");
				System.out
						.println("Exception Occured MailTemplateBackingBean actionListener()");
				reset();
				setViewAction();
			}
			break;

		case WebConstants.ACTION_DELETE_CONFIRMED:
			Long id = session.getMtModule_selectedTemplate();
			try {
				delete(id);
				addMessage("Template deleted successfully");
				session.setMtModule_selectedTemplate(0L);
				changeTabPath(0,
						"/view/mailTemplates/mailTemplatesNoSelection.xhtml");
			} catch (Exception e) {
				System.out
						.println("Exception Occured MailTemplateBackingBean actionListener()");
			}
			reset();
			setViewAction();
			break;
		}
		return null;
	}

	public void view() {
		load(session.getMtModule_selectedTemplate());
	}

	private void load(long id) {
		try {
			if (session.getMtModule_selectedTemplate() != null
					&& session.getMtModule_selectedTemplate() != 0) {
				MailTemplateORM temp = templateDao.details(id);
				this.setModule(temp.getMailTemplateModule());
				this.setTemplate(temp.getTemplate());
				this.setTemplateId(temp.getTemplateId());
				this.setTitle(temp.getTitle());
				this.setSection(temp.getSection());
				changeTabPath(0, "/view/mailTemplates/mailTemplatesForm.xhtml");
			} else {
				changeTabPath(0,
						"view/mailTemplates/mailTemplatesNoSelection.xhtml");
			}
		} catch (Exception e) {
			e.printStackTrace();
			changeTabPath(0,
					"view/mailTemplates/mailTemplatesNoSelection.xhtml");
		}
	}

	public void save() {
		MailTemplateORM temp = new MailTemplateORM();
		UmCompany company = companyDao.companyDetails(session.getCompanyId());
		temp.setMailTemplateModule(module);
		temp.setTemplate(template);
		temp.setTemplateId(0L);
		temp.setTitle(title);
		temp.setCompany(company);
		temp.setSection(section);
		templateDao.create(temp);
		session.setMtModule_selectedTemplate(temp.getTemplateId());
	}

	public void update(Long mtId) {
		MailTemplateORM temp = null;
		try {
			temp = templateDao.details(mtId);
			// temp.setMailTemplateModule(module);
			temp.setTemplate(template);
			temp.setTitle(title);
			templateDao.update(temp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(Long id) {
		System.out.println("Delete Template Called");
		templateDao.delete(id);
		changeTabPath(0, "view/mailTemplates/mailTemplatesNoSelection.xhtml");
		session.setMtModule_selectedTemplate(0L);
	}

	@Override
	public List<MailTemplateORM> getList() {
		return templateDao.list(session.getCompanyId());
	}

}
