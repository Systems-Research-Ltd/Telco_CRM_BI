package com.srpl.crm.common.utils;

import com.srpl.um.ejb.entity.UmCompany;

@Deprecated
public class UmLocationDetails {

	private Integer locationId;
	private Boolean isHeadOffice;
	private String locationAddress;
	private Integer locationCity;
	private Integer locationCountry;
	private String locationDetails;
	private Integer locationState;
	private Boolean locationStatus;
	private String locationTitle;
	private UmCompany umCompany;
	private Integer companyId;
	
	
	public UmLocationDetails(){
		
	}
	
	public UmLocationDetails(String locationAddress, Integer locationCity, Integer locationCountry, Integer locationState, String locationDetails, Boolean locationStatus, String locationTitle, Boolean isHeadOffice, Integer companyId){
         this.locationAddress = locationAddress;
         this.locationCity = locationCity;
         this.locationCountry = locationCountry;
         this.locationState = locationState;
         this.locationDetails = locationDetails;
         this.locationStatus = locationStatus;
         this.locationTitle = locationTitle;
         this.isHeadOffice = isHeadOffice;
         this.companyId = companyId;
         
	}
	
	public Integer getLocationId() {
		return locationId;
	}
	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}
	public Boolean getIsHeadOffice() {
		return isHeadOffice;
	}
	public void setIsHeadOffice(Boolean isHeadOffice) {
		this.isHeadOffice = isHeadOffice;
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
	public UmCompany getUmCompany() {
		return umCompany;
	}
	public void setUmCompany(UmCompany umCompany) {
		this.umCompany = umCompany;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	
}
