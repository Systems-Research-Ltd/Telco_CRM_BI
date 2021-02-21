package com.srpl.crm.common.utils;

import java.io.Serializable;

@Deprecated
public class UmOperationDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer operationId;
	private String operationTitle;
	private String operationDescription;
	
	public UmOperationDetails(){
		
	}
	
	public UmOperationDetails(Integer operationId, String operationTitle, String operationDescription){
		this.operationId = operationId;
		this.operationTitle = operationTitle;
		this.operationDescription = operationDescription;
	}
	
    public UmOperationDetails(String operationTitle, String operationDescription){
		this.operationTitle = operationTitle;
		this.operationDescription = operationDescription;
	}
	
	public int getOperationId() {
		return operationId;
	}
	public void setOperationId(int operationId) {
		this.operationId = operationId;
	}
	public String getOperationTitle() {
		return operationTitle;
	}
	public void setOperationTitle(String operationTitle) {
		this.operationTitle = operationTitle;
	}
	public String getOperationDescription() {
		return operationDescription;
	}
	public void setOperationDescription(String operationDescription) {
		this.operationDescription = operationDescription;
	}
	
}
