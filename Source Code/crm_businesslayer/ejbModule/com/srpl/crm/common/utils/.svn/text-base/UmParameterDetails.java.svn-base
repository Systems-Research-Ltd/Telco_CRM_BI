 package com.srpl.crm.common.utils;

import java.io.Serializable;

@Deprecated
public class UmParameterDetails implements Serializable {
	private static final long serialVersionUID = 1L;
    
	private Integer parameterId;
	private String parameterTitle;
	private long parameterValue;
	private Long companyId;
	
	public UmParameterDetails(){
		
	}
	
    // update parameter	
    public UmParameterDetails(Integer parameterId, String parameterTitle, long parameterValue, Long companyId){
    	this.parameterId = parameterId;
    	this.parameterTitle = parameterTitle;
    	this.parameterValue = parameterValue;
    	this.companyId = companyId;
	}
    
    // for create
    public UmParameterDetails(String parameterTitle, long parameterValue, Long companyId){
    	this.parameterTitle = parameterTitle;
    	this.parameterValue = parameterValue;
    	this.companyId = companyId;
	}
	
	public int getParameterId() {
		return parameterId;
	}	
	public void setParameterId(Integer parameterId) {
		this.parameterId = parameterId;
	}
	public String getParameterTitle() {
		return parameterTitle;
	}
	public void setParameterTitle(String parameterTitle) {
		this.parameterTitle = parameterTitle;
	}
	public long getParameterValue() {
		return parameterValue;
	}
	public void setParameterValue(long parameterValue) {
		this.parameterValue = parameterValue;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}	
}
