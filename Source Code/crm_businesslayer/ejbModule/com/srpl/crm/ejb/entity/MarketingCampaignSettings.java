package com.srpl.crm.ejb.entity;


import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.srpl.um.ejb.entity.MailTemplateORM;

@Entity
@Table(name = "marketing_campaign_settings")
public class MarketingCampaignSettings {

	@Id
	@SequenceGenerator(name = "CAMPAIGN_SETTINGS_CAMAIGNSETTINGSID", sequenceName = "marketing_campaign_settings_campaign_settings_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CAMPAIGN_SETTINGS_CAMAIGNSETTINGSID")
	@Column(name = "campaign_settings_id")
	private Long campaignSettingsId;
	@Column(name = "campaign_settings_message")
	private String campaignSettingsMessage;
	@Column(name = "campaign_settings_date")
	private Date campaignSettingsDate;
	
	@ManyToOne
	@JoinColumn(name = "campaign_id")
	private MCampaign campaign;
	
	@OneToMany(mappedBy="csContact", fetch=FetchType.EAGER)
	private List<MarketingCampaignSettingsCustomers> campaignSettingsCustomersList = new ArrayList<MarketingCampaignSettingsCustomers>();

	@Column(name="mail_template")
	private long mailTemplate;
	
	public MarketingCampaignSettings(){
		
	}
	
    public MarketingCampaignSettings(String campaignSettingsMessage, Date campaignSettingsDate, MCampaign campaign, long template){
 		this.campaignSettingsMessage = campaignSettingsMessage;
 		this.campaignSettingsDate = campaignSettingsDate;
 		this.campaign = campaign;
 		this.mailTemplate = template;
    }
    
    public MarketingCampaignSettings(Long campaignSettingsId, String campaignSettingsMessage, Date campaignSettingsDate, MCampaign campaign, long template){
    	this.campaignSettingsId = campaignSettingsId;
    	this.campaignSettingsMessage = campaignSettingsMessage;
 		this.campaignSettingsDate = campaignSettingsDate;
 		this.campaign = campaign;
 		this.mailTemplate = template;
	}
	
	public Long getCampaignSettingsId() {
		return campaignSettingsId;
	}
	public void setCampaignSettingsId(Long campaignSettingsId) {
		this.campaignSettingsId = campaignSettingsId;
	}
	public String getCampaignSettingsMessage() {
		return campaignSettingsMessage;
	}
	public void setCampaignSettingsMessage(String campaignSettingsMessage) {
		this.campaignSettingsMessage = campaignSettingsMessage;
	}
	public Date getCampaignSettingsDate() {
		return campaignSettingsDate;
	}
	public void setCampaignSettingsDate(Date campaignSettingsDate) {
		this.campaignSettingsDate = campaignSettingsDate;
	}
	public MCampaign getCampaign() {
		return campaign;
	}
	public void setCampaign(MCampaign campaign) {
		this.campaign = campaign;
	}

	public List<MarketingCampaignSettingsCustomers> getCampaignSettingsCustomersList() {
		return campaignSettingsCustomersList;
	}

	public void setCampaignSettingsCustomersList(
			List<MarketingCampaignSettingsCustomers> campaignSettingsCustomersList) {
		this.campaignSettingsCustomersList = campaignSettingsCustomersList;
	}

	public long getMailTemplate() {
		return mailTemplate;
	}

	public void setMailTemplate(long mailTemplate) {
		this.mailTemplate = mailTemplate;
	}
	
}
