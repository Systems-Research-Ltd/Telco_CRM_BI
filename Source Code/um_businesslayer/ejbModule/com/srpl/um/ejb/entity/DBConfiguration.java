package com.srpl.um.ejb.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.srpl.um.ejb.request.UmPersistence;


/**
 * The persistent class for the db_configuration database table.
 * 
 */
@Entity
@Table(name="db_configuration" , schema=UmPersistence.schema)
public class DBConfiguration implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DB_CONFIGURATION_CONFIGID_GENERATOR", sequenceName="DB_CONFIGURATION_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DB_CONFIGURATION_CONFIGID_GENERATOR")
	@Column(name="config_id")
	private Integer configId;

	@Column(name="company_id")
	private Long companyId;

	@Column(name="config_db")
	private String configDB;

	@Column(name="config_query")
	private String configQuery;

	@Column(name="config_title")
	private String configTitle;

    public DBConfiguration() {
    }

	public Integer getConfigId() {
		return this.configId;
	}

	public void setConfigId(Integer configId) {
		this.configId = configId;
	}

	public Long getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getConfigDB() {
		return this.configDB;
	}

	public void setConfigDB(String configDb) {
		this.configDB = configDb;
	}

	public String getConfigQuery() {
		return this.configQuery;
	}

	public void setConfigQuery(String configQuery) {
		this.configQuery = configQuery;
	}

	public String getConfigTitle() {
		return this.configTitle;
	}

	public void setConfigTitle(String configTitle) {
		this.configTitle = configTitle;
	}

}