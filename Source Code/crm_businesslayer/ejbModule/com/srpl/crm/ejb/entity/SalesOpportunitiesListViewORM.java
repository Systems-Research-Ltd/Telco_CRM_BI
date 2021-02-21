package com.srpl.crm.ejb.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sales_opportunities_list_view database table.
 * 
 */
@Entity
@Table(name="sales_opportunities_list_view")
public class SalesOpportunitiesListViewORM implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="company_id")
	private Long companyId;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private String label;

    public SalesOpportunitiesListViewORM() {
    }

    public SalesOpportunitiesListViewORM(Long cid, Integer id, String label) {
    	this.companyId = cid;
    	this.id = id;
    	this.label = label;
    }

	public Long getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}