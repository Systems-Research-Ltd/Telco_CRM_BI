package com.srpl.crm.ejb.request;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.srpl.crm.ejb.entity.MCampaign;
import com.srpl.crm.ejb.entity.MarketingCampaignSettings;

/**
 * Session Bean implementation class CampaignSettingsDAO
 */
@Stateless
@LocalBean
public class CampaignSettingsDAO extends GenericDAO<MarketingCampaignSettings>{

    /**
     * Default constructor. 
     */
    public CampaignSettingsDAO() {
        // TODO Auto-generated constructor stub
    	super(MarketingCampaignSettings.class);
    }

    public Long createCampaignSettings(MarketingCampaignSettings marketingCampaignSettings){
    	save(marketingCampaignSettings);
    	return marketingCampaignSettings.getCampaignSettingsId();
    }
    
    public MarketingCampaignSettings campaignSettingsDetail(Long campaignSettingsId){
    	MarketingCampaignSettings campaignSettings = find(campaignSettingsId);
    	return campaignSettings;
    } 
    
    public List<MarketingCampaignSettings> listCampaignSettings(MCampaign campaign){
    	List<MarketingCampaignSettings> campaignSettingsList = new ArrayList<MarketingCampaignSettings>();
    	campaignSettingsList = em.createQuery("from MarketingCampaignSettings where campaign =:campaign", MarketingCampaignSettings.class).setParameter("campaign", campaign).getResultList();
    	return campaignSettingsList;
    }
    
    public List<MarketingCampaignSettings> listCampaignSettings(){
    	List<MarketingCampaignSettings> campaignSettingsList = new ArrayList<MarketingCampaignSettings>();
    	campaignSettingsList = findAll();
    	return campaignSettingsList;
    }
    
    public MarketingCampaignSettings campaignSettingsDetail(MCampaign campaign){
    	List<MarketingCampaignSettings> campaignSettingsList = new ArrayList<MarketingCampaignSettings>();
    	campaignSettingsList = em.createQuery("from MarketingCampaignSettings where campaign =:campaign", MarketingCampaignSettings.class).setParameter("campaign", campaign).getResultList();
    	MarketingCampaignSettings campaignSettings = new MarketingCampaignSettings();
    	if(campaignSettingsList.size() > 0){
    		campaignSettings = campaignSettingsList.get(0);
    	}
    	return campaignSettings;
    }
    
    public void deleteCampaignSettings(MCampaign campaign){
    	try{
     	   int recordsDeleted = em.createQuery("delete  MarketingCampaignSettings campaignSettings where campaignSettings.campaign = :campaign").setParameter("campaign",campaign).executeUpdate();
     	}catch(Exception e){
     		System.out.println("Exception Occured..");
     		System.out.println(e.getMessage());
     	}
    }
    
    
}
