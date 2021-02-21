package com.srpl.um.ejb.request;

import java.lang.reflect.Method;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.srpl.um.ejb.entity.MailTemplateModuleORM;
import com.srpl.um.ejb.entity.MailTemplateORM;
import com.srpl.um.ejb.entity.TEMPLATE_SECTION;
import com.srpl.um.ejb.entity.UmCompany;

@LocalBean
@Stateless
public class MailTemplateDAO extends GenericDAO<MailTemplateORM> {

	@EJB
	CompanyDAO companyDao;
	@EJB
	MailTemplateModuleDAO moduleDao;

	public MailTemplateDAO() {
		super(MailTemplateORM.class);
	}

	public List<MailTemplateORM> list(long companyId) {
		UmCompany company = companyDao.companyDetails(companyId);
		return list(company);
	}

	private List<MailTemplateORM> list(UmCompany company) {
		List<MailTemplateORM> templates = null;
		templates = em
				.createQuery("FROM MailTemplateORM WHERE company = :c",
						MailTemplateORM.class).setParameter("c", company)
				.getResultList();
		return templates;
	}

	public List<MailTemplateORM> list(long companyId, String className) {
		UmCompany company = companyDao.companyDetails(companyId);
		return list(company, className);
	}

	private List<MailTemplateORM> list(UmCompany company, String className) {
		List<MailTemplateORM> templates = null;
		List<MailTemplateModuleORM> module = null;
		module = moduleDao.list(className);
		templates = em
				.createQuery(
						"FROM MailTemplateORM WHERE company = :c AND mailTemplateModule IN (:m)",
						MailTemplateORM.class).setParameter("c", company)
				.setParameter("m", module).getResultList();
		return templates;
	}

	public long create(MailTemplateORM template) {
		template.setTemplateId(null);
		save(template);
		em.flush();
		return template.getTemplateId();
	}

	public MailTemplateORM details(long id) {
		return find(id);
	}

	public MailTemplateORM details(long cid, TEMPLATE_SECTION section) {
		List<MailTemplateORM> templateList = null;
		MailTemplateORM template = null;
		try {
			UmCompany company = companyDao.companyDetails(cid);
			templateList = em
					.createQuery(
							"FROM MailTemplateORM WHERE company = :c AND section = :s",
							MailTemplateORM.class).setParameter("c", company)
					.setParameter("s", section).getResultList();
			template = templateList.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return template;
	}

	public void update(MailTemplateORM template) {
		super.update(template);
	}

	public void delete(long id) {
		super.delete(id);
	}

	public MailTemplateORM detailsViaModuleName(String moduleName) {
		MailTemplateORM template = null;
		MailTemplateModuleORM module = null;
		module = moduleDao.detailsViaModuleName(moduleName);

		template = em
				.createQuery(
						"FROM MailTemplateORM WHERE mailTemplateModule = :m",
						MailTemplateORM.class).setParameter("m", module)
				.getSingleResult();

		return template;
	}

	public String getMessageWithMailTemplate(Object obj) {
		StringBuilder message = new StringBuilder("");
		String msgString = "";
		// recognize obj
		String className = obj.getClass().getName();
		// getTemplate via module
		MailTemplateORM template = detailsViaModuleName(className);
		// create message
		message.append(template.getTemplate());
		msgString = message.toString();
		// replace placeholders
		try {
			for (Method m : obj.getClass().getMethods()) {
				if (m.getName().contains("get")) {
					Object temp = null;
					try {
						temp = m.invoke(obj);
						if (temp != null
								&& !m.getName().contentEquals(
										"getMailTemplates")) {
							String placeholder = "[" + m.getName() + "]";
							msgString = msgString.replace(placeholder,
									temp.toString());
						}
					} catch (Exception e) {
						System.out.println("some exception.");
					}
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		return msgString;
	}

	public String getMessageWithMailTemplate(MailTemplateORM template,
			Object obj) {
		StringBuilder message = new StringBuilder("");
		String msgString = "";
		// recognize obj
		// no need
		// getTemplate via module
		// already provided
		// create message
		message.append(template.getTemplate());
		msgString = message.toString();
		// replace placeholders
		try {
			for (Method m : obj.getClass().getMethods()) {
				if (m.getName().contains("get")) {
					Object temp = null;
					try {
						temp = m.invoke(obj);
						if (temp != null
								&& !m.getName().contentEquals(
										"getMailTemplates")) {
							String placeholder = "[" + m.getName() + "]";
							msgString = msgString.replace(placeholder,
									temp.toString());
						}
					} catch (Exception e) {
						System.out.println("some exception.");
					}
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		return msgString;
	}
}
