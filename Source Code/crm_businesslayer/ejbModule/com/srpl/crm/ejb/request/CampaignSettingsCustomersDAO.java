package com.srpl.crm.ejb.request;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.srpl.crm.ejb.entity.MarketingCampaignSettings;
import com.srpl.crm.ejb.entity.MarketingCampaignSettingsCustomers;
import com.srpl.crm.ejb.entity.SupportCaseCommentORM;

/**
 * Session Bean implementation class CampaignSettingsCustomersDAO
 */
@Stateless
@LocalBean
public class CampaignSettingsCustomersDAO extends GenericDAO<MarketingCampaignSettingsCustomers>{

    /**
     * Default constructor. 
     */
    public CampaignSettingsCustomersDAO() {
        // TODO Auto-generated constructor stub
    	super(MarketingCampaignSettingsCustomers.class);
    }

    public Long createCampaignSettingCustomers(MarketingCampaignSettingsCustomers campaignSettingsCustomers){
            save(campaignSettingsCustomers);
            return campaignSettingsCustomers.getCampaignSettingsCustomersId();
    }
    
    public List<MarketingCampaignSettingsCustomers> listCampaignSettingsCustomers(MarketingCampaignSettings campaignSettings){
    	List<MarketingCampaignSettingsCustomers> campaignSettingsCustomers;
    	campaignSettingsCustomers = em.createQuery("from MarketingCampaignSettingsCustomers where marketingCampaignSettings =:campaignSettings",MarketingCampaignSettingsCustomers.class).setParameter("campaignSettings", campaignSettings).getResultList();
        return campaignSettingsCustomers;
    } 
    
}
