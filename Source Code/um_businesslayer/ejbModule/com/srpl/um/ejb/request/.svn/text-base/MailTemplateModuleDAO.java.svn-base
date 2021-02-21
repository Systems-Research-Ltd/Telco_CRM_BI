package com.srpl.um.ejb.request;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.srpl.um.ejb.entity.MailTemplateModuleORM;
import com.srpl.um.ejb.request.CompanyDAO;

@LocalBean
@Stateless
public class MailTemplateModuleDAO extends GenericDAO<MailTemplateModuleORM> {

	@EJB
	CompanyDAO companyDao;

	public MailTemplateModuleDAO() {
		super(MailTemplateModuleORM.class);
	}

	public List<MailTemplateModuleORM> list() {
		List<MailTemplateModuleORM> modules = findAll();
		return modules;
	}

	public List<MailTemplateModuleORM> list(String className) {
		List<MailTemplateModuleORM> modules = em
				.createQuery("FROM MailTemplateModuleORM WHERE className = :cn", MailTemplateModuleORM.class)
				.setParameter("cn", className).getResultList();
		return modules;
	}

	public long create(MailTemplateModuleORM module) {
		save(module);
		return module.getModuleId();
	}

	public MailTemplateModuleORM details(long id) {
		return find(id);
	}

	public MailTemplateModuleORM detailsViaModuleName(String moduleName) {
		MailTemplateModuleORM module = null;
		List<MailTemplateModuleORM> list = em
				.createQuery(
						"FROM MailTemplateModuleORM WHERE className = :cn",
						MailTemplateModuleORM.class)
				.setParameter("cn", moduleName).getResultList();
		if (list != null) {
			module = list.get(0);
		}
		return module;
	}

	public void update(MailTemplateModuleORM module) {
		super.update(module);
	}

	public void delete(long id) {
		super.delete(id);
	}
}
