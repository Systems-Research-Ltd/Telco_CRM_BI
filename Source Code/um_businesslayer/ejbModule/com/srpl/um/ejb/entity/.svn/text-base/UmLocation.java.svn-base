package com.srpl.um.ejb.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.srpl.um.ejb.request.UmPersistence;


/**
 * The persistent class for the um_locations database table.
 * 
 */
@Entity
@Table(name="um_locations" , schema=UmPersistence.schema)
public class UmLocation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="UM_LOCATIONS_LOCATIONID_GENERATOR_UM", sequenceName="UM_LOCATIONS_LOCATION_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="UM_LOCATIONS_LOCATIONID_GENERATOR_UM")
	@Column(name="location_id")
	private Integer locationId;

	@Column(name="is_headOffice")
	private Boolean isHeadOffice;

	@Column(name="location_address")
	private String locationAddress;

	@Column(name="location_city")
	private Integer locationCity;

	@Column(name="location_country")
	private Integer locationCountry;

	@Column(name="location_details")
	private String locationDetails;

	@Column(name="location_state")
	private Integer locationState;

	@Column(name="location_status")
	private Boolean locationStatus;

	@Column(name="location_title")
	private String locationTitle;

	//bi-directional many-to-one association to UmCompany
    @ManyToOne
	@JoinColumn(name="company_id")
	private UmCompany umCompany;

    public UmLocation() {
    }

    public UmLocation(String locationAddress, Integer locationCity, Integer locationCountry, Integer locationState, String locationDetails, Boolean locationStatus, String locationTitle, Boolean isHeadOffice, UmCompany umCompany){
        this.locationAddress = locationAddress;
        this.locationCity = locationCity;
        this.locationCountry = locationCountry;
        this.locationState = locationState;
        this.locationDetails = locationDetails;
        this.locationStatus = locationStatus;
        this.locationTitle = locationTitle;
        this.isHeadOffice = isHeadOffice;
        this.umCompany = umCompany;
        
	}
    
    
	public Integer getLocationId() {
		return this.locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public Boolean getIsHeadOffice() {
		return this.isHeadOffice;
	}

	public void setIsHeadOffice(Boolean isHeadOffice) {
		this.isHeadOffice = isHeadOffice;
	}

	public String getLocationAddress() {
		return this.locationAddress;
	}

	public void setLocationAddress(String locationAddress) {
		this.locationAddress = locationAddress;
	}

	public Integer getLocationCity() {
		return this.locationCity;
	}

	public void setLocationCity(Integer locationCity) {
		this.locationCity = locationCity;
	}

	public Integer getLocationCountry() {
		return this.locationCountry;
	}

	public void setLocationCountry(Integer locationCountry) {
		this.locationCountry = locationCountry;
	}

	public String getLocationDetails() {
		return this.locationDetails;
	}

	public void setLocationDetails(String locationDetails) {
		this.locationDetails = locationDetails;
	}

	public Integer getLocationState() {
		return this.locationState;
	}

	public void setLocationState(Integer locationState) {
		this.locationState = locationState;
	}

	public Boolean getLocationStatus() {
		return this.locationStatus;
	}

	public void setLocationStatus(Boolean locationStatus) {
		this.locationStatus = locationStatus;
	}

	public String getLocationTitle() {
		return this.locationTitle;
	}

	public void setLocationTitle(String locationTitle) {
		this.locationTitle = locationTitle;
	}

	public UmCompany getUmCompany() {
		return this.umCompany;
	}

	public void setUmCompany(UmCompany umCompany) {
		this.umCompany = umCompany;
	}
	
}