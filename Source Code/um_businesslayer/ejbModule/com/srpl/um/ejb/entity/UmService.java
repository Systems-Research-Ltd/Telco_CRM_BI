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
@Table(name = "um_service", schema=UmPersistence.schema)
public class UmService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SERVICE_PK_SEQUENCE",sequenceName="um_service_service_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SERVICE_PK_SEQUENCE")
	@Column(name = "service_id")
	private Long serviceId;
	@Column(name = "service_name")
	private String serviceName;
	@Column(name = "service_title")
	private String serviceTitle;
	@Column(name = "service_description")
	private String serviceDescription;
	@Column(name = "is_parent")
	private boolean isParent;
	@Column(name = "parent_service_id")
	private Integer parentServiceId;
	
	public boolean getIsParent() {
		return isParent;
	}
	
	public void setIsParent(boolean isParent) {
		this.isParent = isParent;
	}
	
	public Integer getServiceParentId() {
		return parentServiceId;
	}

	public void setServiceParentId(Integer serviceParentId) {
		this.parentServiceId = serviceParentId;
	}

	public UmService(){
		
	}
	public UmService(boolean isParent, Integer serviceParentiId){
		this.isParent = isParent;
		this.parentServiceId = serviceParentiId;
	}
    public UmService(String serviceName, String serviceTitle, String serviceDescription){
    	this.serviceName = serviceName;
		this.serviceTitle = serviceTitle;
		this.serviceDescription = serviceDescription;
	}
    
    public UmService(Long serviceId, String serviceTitle, String serviceDescription){
		this.serviceId = serviceId;
    	this.serviceTitle = serviceTitle;
		this.serviceDescription = serviceDescription;
	}
	
	public Long getServiceId() {
		return serviceId;
	}
	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getServiceTitle() {
		return serviceTitle;
	}
	public void setServiceTitle(String serviceTitle) {
		this.serviceTitle = serviceTitle;
	}
	public String getServiceDescription() {
		return serviceDescription;
	}
	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj==null || obj.getClass() != getClass()){
			return false;
		}
		final UmService other = (UmService) obj;

		if(serviceId == null && other.getServiceId() != null){
			return false;
		}else if(serviceId != other.getServiceId()){
			return false;
		}
		
		if(serviceName == null && other.getServiceName() != null){
			return false;
		}else if(!(serviceName.contentEquals(other.getServiceName()))){
			return false;
		}
		
		if(serviceTitle == null && other.getServiceTitle() != null){
			return false;
		}else if(!(serviceTitle.contentEquals(other.getServiceTitle()))){
			return false;
		}
		
		if(serviceDescription == null && other.getServiceDescription() != null){
			return false;
		}else if(!(serviceDescription.contentEquals(other.getServiceDescription()))){
			return false;
		}
		
		if(isParent != other.getIsParent()){
			return false;
		}
		
		if(parentServiceId == null && other.getServiceParentId() != null){
			return false;
		}else if(parentServiceId != other.getServiceParentId()){
			return false;
		}		
		
		return true;
	}
	
}
