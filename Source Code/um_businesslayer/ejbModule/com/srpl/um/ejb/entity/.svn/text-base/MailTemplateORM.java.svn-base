package com.srpl.um.ejb.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.srpl.um.ejb.entity.UmCompany;


/**
 * The persistent class for the mail_template database table.
 * 
 */
@Entity
@Table(name="mail_template")
public class MailTemplateORM implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MAIL_TEMPLATE_TEMPLATEID_GENERATOR",sequenceName="mail_template_template_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MAIL_TEMPLATE_TEMPLATEID_GENERATOR")
	@Column(name="template_id", unique=true, nullable=false)
	private Long templateId;

	@Column(length=2048)
	private String template;

	@Column(length=90)
	private String title;
	
	@Enumerated(value = EnumType.STRING)
	@Column(name="template_section")
	private TEMPLATE_SECTION section;

	//bi-directional many-to-one association to MailTemplateModule
    @ManyToOne
	@JoinColumn(name="ref_module_id")
	private MailTemplateModuleORM mailTemplateModule;
    
	@ManyToOne
	@JoinColumn(name = "company_id")
	private UmCompany company;

    public MailTemplateORM() {
    }
    
    public MailTemplateORM(UmCompany company, String title, String template, TEMPLATE_SECTION section, MailTemplateModuleORM module){
    	this.company = company;
    	this.title = title;
    	this.template = template;
    	this.section = section;
    	this.mailTemplateModule = module;
    }

	public Long getTemplateId() {
		return this.templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public String getTemplate() {
		return this.template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public TEMPLATE_SECTION getSection() {
		return section;
	}

	public void setSection(TEMPLATE_SECTION section) {
		this.section = section;
	}

	public MailTemplateModuleORM getMailTemplateModule() {
		return this.mailTemplateModule;
	}

	public void setMailTemplateModule(MailTemplateModuleORM mailTemplateModule) {
		this.mailTemplateModule = mailTemplateModule;
	}

	public UmCompany getCompany() {
		return company;
	}

	public void setCompany(UmCompany company) {
		this.company = company;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime
				* result
				+ ((mailTemplateModule == null) ? 0 : mailTemplateModule
						.hashCode());
		result = prime * result
				+ ((template == null) ? 0 : template.hashCode());
		result = prime * result
				+ ((templateId == null) ? 0 : templateId.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MailTemplateORM other = (MailTemplateORM) obj;
		if (template == null) {
			if (other.template != null)
				return false;
		} else if (!template.equals(other.template))
			return false;
		if (templateId == null) {
			if (other.templateId != null)
				return false;
		} else if (!templateId.equals(other.templateId))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	
}