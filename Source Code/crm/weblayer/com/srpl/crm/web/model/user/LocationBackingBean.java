package com.srpl.crm.web.model.user;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name="locationBackingBean")
@ViewScoped
public class LocationBackingBean implements Serializable  {
    private static final long serialVersionUID = 1L;
    private Integer locationId;
	private Boolean isHeadoffice;
	private String locationAddress;
	private Integer locationCity;
	private Integer locationCountry;
	private String locationDetails;
	private Integer locationState;
	private Boolean locationStatus;
	private String locationTitle;
	private Integer companyId;
	
	public Integer getLocationId() {
		return locationId;
	}
	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}
	public Boolean getIsHeadoffice() {
		return isHeadoffice;
	}
	public void setIsHeadoffice(Boolean isHeadoffice) {
		this.isHeadoffice = isHeadoffice;
	}
	public String getLocationAddress() {
		return locationAddress;
	}
	public void setLocationAddress(String locationAddress) {
		this.locationAddress = locationAddress;
	}
	public Integer getLocationCity() {
		return locationCity;
	}
	public void setLocationCity(Integer locationCity) {
		this.locationCity = locationCity;
	}
	public Integer getLocationCountry() {
		return locationCountry;
	}
	public void setLocationCountry(Integer locationCountry) {
		this.locationCountry = locationCountry;
	}
	public String getLocationDetails() {
		return locationDetails;
	}
	public void setLocationDetails(String locationDetails) {
		this.locationDetails = locationDetails;
	}
	public Integer getLocationState() {
		return locationState;
	}
	public void setLocationState(Integer locationState) {
		this.locationState = locationState;
	}
	public Boolean getLocationStatus() {
		return locationStatus;
	}
	public void setLocationStatus(Boolean locationStatus) {
		this.locationStatus = locationStatus;
	}
	public String getLocationTitle() {
		return locationTitle;
	}
	public void setLocationTitle(String locationTitle) {
		this.locationTitle = locationTitle;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

}
