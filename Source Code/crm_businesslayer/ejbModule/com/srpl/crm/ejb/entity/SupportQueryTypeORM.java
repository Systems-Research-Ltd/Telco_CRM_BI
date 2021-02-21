package com.srpl.crm.ejb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.srpl.um.ejb.entity.UmCompany;
import com.srpl.um.ejb.entity.UmGroup;


@Entity
@Table(name = "support_query_type")
public class SupportQueryTypeORM implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name="SUPPORT_QUERY_TYPE_QUERYTYPEID_GENERATOR", sequenceName="support_query_type_query_type_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SUPPORT_QUERY_TYPE_QUERYTYPEID_GENERATOR")
	@Column(name = "query_type_id")
	private Long queryTypeId;
	@Column(name = "query_type_title")
	private String queryTypeTitle;
	@Column(name = "query_type_alias")
	private String queryTypeAlias;
	
	@ManyToOne
	@JoinColumn(name="query_type_group")
	private UmGroup umGroup;
	
	@ManyToOne
	@JoinColumn(name="query_type_company_id")
	private UmCompany umCompany;
	
	public SupportQueryTypeORM(){
		
	}
	
	@Deprecated
	public SupportQueryTypeORM(String queryTypeTitle, String queryTypeAlias, UmGroup umGroup){
		this.queryTypeTitle = queryTypeTitle;
		this.queryTypeAlias = queryTypeAlias;
		this.umGroup = umGroup;
	}
	public SupportQueryTypeORM(String queryTypeTitle, String queryTypeAlias, UmGroup umGroup, UmCompany umCompany){
		this.queryTypeTitle = queryTypeTitle;
		this.queryTypeAlias = queryTypeAlias;
		this.umGroup = umGroup;
		this.umCompany = umCompany;
	}
	@Deprecated
	public SupportQueryTypeORM(Long queryTypeId,String queryTypeTitle, String queryTypeAlias, UmGroup umGroup){
		this.queryTypeId = queryTypeId;
		this.queryTypeTitle = queryTypeTitle;
		this.queryTypeAlias = queryTypeAlias;
		this.umGroup = umGroup;
	}
	
	public SupportQueryTypeORM(Long queryTypeId,String queryTypeTitle, String queryTypeAlias, UmGroup umGroup, UmCompany umCompany){
		this.queryTypeId = queryTypeId;
		this.queryTypeTitle = queryTypeTitle;
		this.queryTypeAlias = queryTypeAlias;
		this.umGroup = umGroup;
		this.umCompany = umCompany;
	}
	
	public Long getQueryTypeId() {
		return queryTypeId;
	}
	public void setQueryTypeId(Long queryTypeId) {
		this.queryTypeId = queryTypeId;
	}
	public String getQueryTypeTitle() {
		return queryTypeTitle;
	}
	public void setQueryTypeTitle(String queryTypeTitle) {
		this.queryTypeTitle = queryTypeTitle;
	}

	public String getQueryTypeAlias() {
		return queryTypeAlias;
	}

	public void setQueryTypeAlias(String queryTypeAlias) {
		this.queryTypeAlias = queryTypeAlias;
	}

	public UmGroup getUmGroup() {
		return umGroup;
	}

	public void setUmGroup(UmGroup umGroup) {
		this.umGroup = umGroup;
	}

	public UmCompany getUmCompany() {
		return umCompany;
	}

	public void setUmCompany(UmCompany umCompany) {
		this.umCompany = umCompany;
	}
	
}
