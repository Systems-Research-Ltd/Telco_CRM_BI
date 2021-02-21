package com.srpl.bi.ejb.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * The persistent class for the dashboard database table.
 * 
 */

@Entity
@Table(name="dashboard")
public class DashboardORM implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@SequenceGenerator(name="DASHBOARD_GENERATOR", sequenceName="DASHBOARD_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DASHBOARD_GENERATOR")
	@Column(name="id")
	private Long id;
	
	@Column(name="user_id")
	private Integer user;
	@Column(name="panel_position")
	private Integer position;
	
	
	
	
	@Column(name="report")
	private Integer report;

	@Column(name="companyid")
	private Long companyid;
	
	@Column(name="graphtype")
	private Integer graphtype;
   
	public DashboardORM() 
    {
    }
    
    public DashboardORM(Integer user, Integer position, Integer report, Long companyid ,Integer graphtype){
    	this.setUser(user);
		this.setPosition(position);
		this.setReport(report);
		this.setCompanyid(companyid);
		this.setGraphtype(graphtype);
	}

	public Integer getReport() {
		return report;
	}

	public void setReport(Integer report) {
		this.report = report;
	}

	public Long getCompanyid() {
		return companyid;
	}

	public void setCompanyid(Long companyid) {
		this.companyid = companyid;
	}

	public Integer getUser() {
		return user;
	}

	public void setUser(Integer user) {
		this.user = user;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the graphtype
	 */
	public Integer getGraphtype() {
		return graphtype;
	}

	/**
	 * @param graphtype the graphtype to set
	 */
	public void setGraphtype(Integer graphtype) {
		this.graphtype = graphtype;
	}

    
}