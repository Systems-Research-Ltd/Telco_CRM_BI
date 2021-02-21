package com.srpl.crm.ejb.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;
//import java.util.Set;


/**
 * The persistent class for the alerts_and_reminders database table.
 * 
 */
@Entity
@Table(name="alerts_and_reminders")
public class UmAlertsAndReminders implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="UM_ALERTSANDREMINDERS_ID_GENERATOR", sequenceName="ALERTS_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="UM_ALERTSANDREMINDERS_ID_GENERATOR")
	@Column(name="id")
	private Long id;

	@Column(name="title")
	private String title;
	
	@Column(name="user_id")
	private Long userId;

	@Column(name="is_alert")
	private Boolean isAlert;

	@Column(name="transmit_status")
	private Boolean transmitStatus;

	@Column(name="date")
	private Timestamp date;

	@Column(name="transmit_date")
	private Timestamp transmitDate;
	
	@Column(name="alerts_reminders_status")
	private Boolean alertsRemindersStatus;
	
	@Column(name="object_id")
	private Long objectId;
	
	@Column(name="object_type")
	private String objectType;
	
	@Column(name="company_id")
	private Integer companyId;
	
	@Column(name="end_date")
	private Timestamp endDate;
	
		
    public UmAlertsAndReminders() {
    }

    public UmAlertsAndReminders(String title, Timestamp date, Long userId, Boolean isAlert, Boolean transmitStatus, Timestamp transmitDate,Boolean alertsRemindersStatus){
    	this.title = title;
    	this.date = date;
    	this.userId = userId;
    	this.isAlert = isAlert;    	
    	this.transmitStatus = transmitStatus;
    	this.transmitDate = transmitDate;
    	this.alertsRemindersStatus = alertsRemindersStatus;
    }
    
    public UmAlertsAndReminders(String title, Timestamp date, Long userId, Boolean isAlert, Boolean transmitStatus, Timestamp transmitDate,Boolean alertsRemindersStatus,Long objectId, String objectType, Integer companyId, Timestamp endDate){
    	this.title = title;
    	this.date = date;
    	this.userId = userId;
    	this.isAlert = isAlert;    	
    	this.transmitStatus = transmitStatus;
    	this.transmitDate = transmitDate;
    	this.alertsRemindersStatus = alertsRemindersStatus;
    	this.objectId = objectId;
    	this.objectType = objectType;
    	this.companyId = companyId;
    	this.endDate = endDate;
    }
    
    public UmAlertsAndReminders(Long id, String title, Timestamp date, Long userId, Boolean isAlert, Boolean transmitStatus, Timestamp transmitDate, Boolean alertsRemindersStatus){
    	this.id = id;
    	this.title = title;
    	this.date = date;
    	this.userId = userId;
    	this.isAlert = isAlert;    	
    	this.transmitStatus = transmitStatus;
    	this.transmitDate = transmitDate;
    	this.alertsRemindersStatus = alertsRemindersStatus;
    }
    
    public UmAlertsAndReminders(Long id, Boolean alertsRemindersStatus){
    	this.id = id;
    	this.alertsRemindersStatus= alertsRemindersStatus;
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public Boolean getIsAlert() {
		return isAlert;
	}

	public void setIsAlert(Boolean isAlert) {
		this.isAlert = isAlert;
	}

	public Boolean getTransmitStatus() {
		return transmitStatus;
	}

	public void setTransmitStatus(Boolean transmitStatus) {
		this.transmitStatus = transmitStatus;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public Timestamp getTransmitDate() {
		return transmitDate;
	}

	public void setTransmitDate(Timestamp transmitDate) {
		this.transmitDate = transmitDate;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Boolean getAlertsRemindersStatus() {
		return alertsRemindersStatus;
	}

	public void setAlertsRemindersStatus(Boolean alertsRemindersStatus) {
		this.alertsRemindersStatus = alertsRemindersStatus;
	}

	public Long getObjectId() {
		return objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}
}