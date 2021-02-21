package com.srpl.crm.web.model.dashboard;

public class ReportSelect {
	String reportLabel;  // Report Label
	int reportValue;     // Report ID
	
	ReportSelect(int reportValue, String reportLabel)
	{
		this.reportLabel=reportLabel;
		this.reportValue=reportValue;
	}
	
	public String getReportLabel() {
		return reportLabel;
	}
	public void setReportLabel(String reportLabel) {
		this.reportLabel = reportLabel;
	}
	public int getReportValue() {
		return reportValue;
	}
	public void setReportValue(int reportValue) {
		this.reportValue = reportValue;
	}

}
