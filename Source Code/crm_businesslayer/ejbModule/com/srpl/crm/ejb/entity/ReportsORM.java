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
import com.srpl.um.ejb.entity.UmUser;

/**
 * The persistent class for the um_users database table.
 * 
 */
@Entity
@Table(name="reports")
public class ReportsORM implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="REPORTS_ID_GENERATOR", sequenceName="REPORTS_REPORT_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REPORTS_ID_GENERATOR")
	@Column(name="report_id")
	private Integer reportId;

	@Column(name="report_title")
	private String reportTitle;

	@Column(name="report_description")
	private String reportDescription;
	
	@Column(name="report_summary")
	private String reportSummary;

	@Column(name="report_column")
	private String reportColumn;
	
	@Column(name="report_columns_titles")
	private String reportColumnsTitles;
	
	@Column(name="report_from")
	private String reportFrom;
	
	@Column(name="report_where")
	private String reportWhere;

	@Column(name="report_orderby")
	private String reportOrderBy;
	
	@Column(name="report_type")
	private String reportType;

	@Column(name="report_type_columns")
	private String reportTypeColumns;
	
	@Column(name="reportby")
	private String reportBy;

	@Column(name="report_groupby")
	private String reportGroupBy;
	
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UmUser user;
	
	@ManyToOne
	@JoinColumn(name="company_id")
	private UmCompany company;
	
	
	
	public ReportsORM() {
    }
	
    
    public ReportsORM(Integer reportId, String reportTitle, String reportDescription, String reportSummary, String reportColumn, String reportColumnsTitles, String reportFrom, String reportWhere, String reportOrderBy, String reportType, String reportTypeColumns, String reportBy,String reportGroupBy,UmUser user, UmCompany company) {
    	this.reportId = reportId;
    	this.reportTitle = reportTitle;
    	this.reportDescription = reportDescription;
    	this.reportSummary = reportSummary;
    	this.reportColumn = reportColumn; 
    	this.reportColumnsTitles = reportColumnsTitles;
    	this.reportFrom = reportFrom;
    	this.reportWhere = reportWhere;
    	this.reportOrderBy = reportOrderBy;
    	this.reportType = reportType;
    	this.reportTypeColumns = reportTypeColumns;
    	this.reportBy = reportBy;
    	this.reportGroupBy = reportGroupBy;
    	this.user = user;
    	this.company = company;
    }
    
    public ReportsORM(String reportTitle, String reportDescription, String reportSummary, String reportColumn, String reportColumnsTitles , String reportFrom, String reportWhere, String reportOrderBy, String reportType, String reportTypeColumns, String reportBy,String reportGroupBy, UmUser user, UmCompany company) {
    	this.reportTitle = reportTitle;
    	this.reportDescription = reportDescription;
    	this.reportSummary = reportSummary;
    	this.reportColumn = reportColumn; 	
    	this.reportColumnsTitles = reportColumnsTitles;
    	this.reportFrom = reportFrom;
    	this.reportWhere = reportWhere;
    	this.reportOrderBy = reportOrderBy;
    	this.reportType = reportType;
    	this.reportTypeColumns = reportTypeColumns;
    	this.reportBy = reportBy;
    	this.reportGroupBy = reportGroupBy;
    	this.user = user;
    	this.company = company;
    }
    
	public Integer getReportId() {
		return reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	public String getReportTitle() {
		return reportTitle;
	}

	public void setReportTitle(String reportTitle) {
		this.reportTitle = reportTitle;
	}

	public String getReportDescription() {
		return reportDescription;
	}

	public void setReportDescription(String reportDescription) {
		this.reportDescription = reportDescription;
	}
	
	public String getReportSummary() {
		return reportSummary;
	}


	public void setReportSummary(String reportSummary) {
		this.reportSummary = reportSummary;
	}


	public String getReportColumn() {
		return reportColumn;
	}

	public void setReportColumn(String reportColumn) {
		this.reportColumn = reportColumn;
	}
	
	public String getReportColumnsTitles() {
		return reportColumnsTitles;
	}

	public void setReportColumnsTitles(String reportColumnsTitles) {
		this.reportColumnsTitles = reportColumnsTitles;
	}

	public String getReportFrom() {
		return reportFrom;
	}
	
	public String getReportWhere() {
		return reportWhere;
	}

	public void setReportWhere(String reportWhere) {
		this.reportWhere = reportWhere;
	}

	public void setReportFrom(String reportFrom) {
		this.reportFrom = reportFrom;
	}
    public String getReportOrderBy() {
		return reportOrderBy;
	}

	public void setReportOrderBy(String reportOrderBy) {
		this.reportOrderBy = reportOrderBy;
	}
	
	public String getReportType() {
		return reportType;
	}


	public void setReportType(String reportType) {
		this.reportType = reportType;
	}


	public String getReportTypeColumns() {
		return reportTypeColumns;
	}


	public void setReportTypeColumns(String reportTypeColumns) {
		this.reportTypeColumns = reportTypeColumns;
	}
	
	public String getReportBy() {
		return reportBy;
	}


	public void setReportBy(String reportBy) {
		this.reportBy = reportBy;
	}


	public String getReportGroupBy() {
		return reportGroupBy;
	}


	public void setReportGroupBy(String reportGroupBy) {
		this.reportGroupBy = reportGroupBy;
	}


	public UmUser getUser() {
		return user;
	}


	public void setUser(UmUser user) {
		this.user = user;
	}


	public UmCompany getCompany() {
		return company;
	}


	public void setCompany(UmCompany company) {
		this.company = company;
	}

 }
 