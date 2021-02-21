package com.srpl.um.ejb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.srpl.um.ejb.request.UmPersistence;

@Entity
@Table(name = "um_parameters" , schema=UmPersistence.schema)
public class UmParameter implements Serializable  {
	private static final long serialVersionUID = 1L;
    
	@Id
	@SequenceGenerator(name = "PARAMETERS_PK_SEQUENCE", sequenceName = "um_parameters_parameter_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PARAMETERS_PK_SEQUENCE")
	@Column(name = "parameter_id")
	private int parameterId;
	@Column(name = "parameter_title")
	private String parameterTitle;
	@Column(name = "parameter_value")
	private String parameterValue;
	@Column(name = "company_id")
	private Long companyId;
	
	
//	================ companyName ==================
	public String getCompanyName(){
		return "";
	}
	
//  ================ companyName ==================			
    
	
	
	public UmParameter(){
		
	}
	
	public UmParameter(String parameterTitle, String parameterValue, Long companyId){
    	this.parameterTitle = parameterTitle;
    	this.parameterValue = parameterValue;
    	this.companyId = companyId;
	}
	
	public UmParameter(Integer parameterId, String parameterTitle, String parameterValue, Long companyId){
		this.parameterId = parameterId;
    	this.parameterTitle = parameterTitle;
    	this.parameterValue = parameterValue;
    	this.companyId = companyId;
	}
	
	public int getParameterId() {
		return parameterId;
	}
	public void setParameterId(int parameterId) {
		this.parameterId = parameterId;
	}
	public String getParameterTitle() {
		return parameterTitle;
	}
	public void setParameterTitle(String parameterTitle) {
		this.parameterTitle = parameterTitle;
	}
	public String getParameterValue() {
		return parameterValue;
	}
	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}	
}
