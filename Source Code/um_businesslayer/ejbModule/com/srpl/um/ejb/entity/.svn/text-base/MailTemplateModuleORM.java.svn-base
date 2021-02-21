package com.srpl.um.ejb.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the mail_template_modules database table.
 * 
 */
@Entity
@Table(name="mail_template_modules")
public class MailTemplateModuleORM implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MAIL_TEMPLATE_MODULES_MODULEID_GENERATOR",sequenceName="mail_template_modules_module_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MAIL_TEMPLATE_MODULES_MODULEID_GENERATOR")
	@Column(name="module_id", unique=true, nullable=false)
	private Long moduleId;

	@Column(name="class_name", length=255)
	private String className;

	@Column(length=90)
	private String title;

	//bi-directional many-to-one association to MailTemplate
	@OneToMany(mappedBy="mailTemplateModule")
	private Set<MailTemplateORM> mailTemplates;

    public MailTemplateModuleORM() {
    }

	public Long getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<MailTemplateORM> getMailTemplates() {
		return this.mailTemplates;
	}

	public void setMailTemplates(Set<MailTemplateORM> mailTemplates) {
		this.mailTemplates = mailTemplates;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((className == null) ? 0 : className.hashCode());
		result = prime * result
				+ ((mailTemplates == null) ? 0 : mailTemplates.hashCode());
		result = prime * result
				+ ((moduleId == null) ? 0 : moduleId.hashCode());
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
		MailTemplateModuleORM other = (MailTemplateModuleORM) obj;
		if (className == null) {
			if (other.className != null)
				return false;
		} else if (!className.equals(other.className))
			return false;
		if (moduleId == null) {
			if (other.moduleId != null)
				return false;
		} else if (!moduleId.equals(other.moduleId))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	
}