package com.srpl.crm.common.utils;

import java.io.Serializable;

@Deprecated
	public class ReportsDetails implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Integer reportId;
		private String reportTitle;
		private String reportDescription;
		private String reportSummary;
		private String reportColumn;
		private String reportColumnTitles;
		private String reportFrom;
		private String reportWhere;
		private String reportOrderBy;
		private String reportType;
		private String reportTypeColumns;
		private String reportBy;
		

		public ReportsDetails() {
	    }
	    
	    public ReportsDetails(Integer reportId, String reportTitle, String reportDescription, String reportSummary, String reportColumn, String reportColumnTitles, String reportFrom,  String reportWhere, String reportOrderBy, String reportType, String reportTypeColumns, String reportBy) {
	    	this.reportId = reportId;
	    	this.reportTitle = reportTitle;
	    	this.reportDescription = reportDescription;
	    	this.reportSummary = reportSummary;
	    	this.reportColumn = reportColumn;
	    	this.reportColumnTitles = reportColumnTitles;
	    	this.reportFrom = reportFrom;
	    	this.reportWhere = reportWhere;
	    	this.reportOrderBy = reportOrderBy;
	    	this.reportType = reportType;
	    	this.reportTypeColumns = reportTypeColumns;
	    	this.reportBy = reportBy;
	    }
	    
	    public ReportsDetails(String reportTitle, String reportDescription, String reportSummary, String reportColumn, String reportColumnsTitles, String reportFrom, String reportWhere, String reportOrderBy, String reportType, String reportTypeColumns, String reportBy) {
	    	this.reportTitle = reportTitle;
	    	this.reportDescription = reportDescription;
	    	this.reportSummary = reportSummary;
	    	this.reportColumn = reportColumn;
	    	this.reportColumnTitles = reportColumnsTitles;
	    	this.reportFrom = reportFrom;
	    	this.reportWhere = reportWhere;
	    	this.reportOrderBy = reportOrderBy;
	    	this.reportType = reportType;
	    	this.reportTypeColumns = reportTypeColumns;
	    	this.reportBy = reportBy;
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
		
		public String getReportColumnTitles() {
			return reportColumnTitles;
		}

		public void setReportColumnTitles(String reportColumnTitles) {
			this.reportColumnTitles = reportColumnTitles;
		}
		
		public String getReportFrom() {
			return reportFrom;
		}

		public void setReportFrom(String reportFrom) {
			this.reportFrom = reportFrom;
		}
		
		public String getReportWhere() {
			return reportWhere;
		}

		public void setReportWhere(String reportWhere) {
			this.reportWhere = reportWhere;
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

	
	}