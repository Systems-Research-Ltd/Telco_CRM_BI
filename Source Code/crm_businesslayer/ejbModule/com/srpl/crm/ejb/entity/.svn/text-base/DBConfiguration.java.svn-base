package com.srpl.crm.ejb.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.srpl.um.ejb.entity.UmUser;


/**
 * The persistent class for the db_configuration database table.
 * 
 */
@Entity
@Table(name="db_configuration")
public class DBConfiguration implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DB_CONFIGURATION_CONFIGID_GENERATOR", sequenceName="CONFIG_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DB_CONFIGURATION_CONFIGID_GENERATOR")
	@Column(name="config_id")
	private Integer configId;

	@Column(name="company_id")
	private Long companyId;

	@Column(name="config_db")
	private String configDB;

	@Column(name="config_query")
	private String configQuery;

	@Column(name="username")
	private String userName;

	@Column(name="password")
	private String password;

	@Column(name="driver")
	private String driver;
	
	@OneToOne
    @JoinColumn(name = "mapping_id")
    private TableMapping tableMapping;

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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public TableMapping getTableMapping() {
		return tableMapping;
	}

	public void setTableMapping(TableMapping tableMapping) {
		this.tableMapping = tableMapping;
	}

}