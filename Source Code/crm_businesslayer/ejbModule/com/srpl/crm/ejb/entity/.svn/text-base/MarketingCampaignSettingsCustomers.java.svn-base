package com.srpl.crm.ejb.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "marketing_campaign_settings_customers")
public class MarketingCampaignSettingsCustomers {
	
	@Id
	@SequenceGenerator(name = "CAMPAIGNSETTINGSCUSTOMERSID", sequenceName = "campaign_settings_customers_seq", allocationSize = 1)
	@GeneratedValue(strategy= GenerationType.SEQUENCE , generator = "CAMPAIGNSETTINGSCUSTOMERSID")
	@Column(name = "campaign_settings_customers_id")
	private Long campaignSettingsCustomersId;
	
	@ManyToOne
	@JoinColumn(name = "campaign_settings_id")
	private MarketingCampaignSettings marketingCampaignSettings;
	
	@ManyToOne
	@JoinColumn(name = "campaign_settings_customers")
	private CsContactORM csContact;
	
	
	public MarketingCampaignSettingsCustomers(MarketingCampaignSettings marketingCampaignSettings, CsContactORM csContact){
		this.marketingCampaignSettings = marketingCampaignSettings;
		this.csContact = csContact;
    	
	}
	
    public MarketingCampaignSettingsCustomers(Long campaignSettingsCustomersId, MarketingCampaignSettings marketingCampaignSettings, CsContactORM csContact){
    	this.campaignSettingsCustomersId = campaignSettingsCustomersId;
    	this.marketingCampaignSettings = marketingCampaignSettings;
    	this.csContact = csContact;
    	
	}
    
    public MarketingCampaignSettingsCustomers(){
		
	}
	
	public Long getCampaignSettingsCustomersId() {
		return campaignSettingsCustomersId;
	}
	public void setCampaignSettingsCustomersId(Long campaignSettingsCustomersId) {
		this.campaignSettingsCustomersId = campaignSettingsCustomersId;
	}
	
	
	public MarketingCampaignSettings getMarketingCampaignSettings() {
		return marketingCampaignSettings;
	}
	public void setMarketingCampaignSettings(
			MarketingCampaignSettings marketingCampaignSettings) {
		this.marketingCampaignSettings = marketingCampaignSettings;
	}

	public CsContactORM getCsContact() {
		return csContact;
	}

	public void setCsContact(CsContactORM csContact) {
		this.csContact = csContact;
	}
	

	
}
