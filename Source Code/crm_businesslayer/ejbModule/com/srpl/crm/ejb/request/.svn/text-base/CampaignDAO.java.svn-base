package com.srpl.crm.ejb.request;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import com.srpl.crm.ejb.entity.CampaignProduct;
import com.srpl.crm.ejb.entity.CsContactORM;
import com.srpl.crm.ejb.entity.MCampaign;
import com.srpl.crm.ejb.entity.MarketingCampaignSettings;
import com.srpl.crm.ejb.entity.MarketingCampaignSettingsCustomers;

@Stateless
@LocalBean
public class CampaignDAO extends GenericDAO<MCampaign> {

	/**
	 * Default Constructor
	 */
	public CampaignDAO(){
		super(MCampaign.class);
	}
	
	public List<MCampaign> listCampaigns(Long companyId) {
		List<MCampaign> campaigns = new ArrayList<MCampaign>();
		
		String query = "SELECT m FROM MCampaign m ";
		String where = " WHERE m.company_id = :cId ";		
		
		campaigns = em.createQuery(query+where, MCampaign.class)
						.setParameter("cId", companyId)
						.getResultList();
		//campaigns = em.createQuery("SELECT c FROM MCampaign c where c.company_id = :companyId",MCampaign.class).setParameter("companyId", companyId).getResultList();
		//System.out.println(campaigns.size()+"-"+companyId);
		return campaigns;
	}
	
	public List<MCampaign> listCampaigns(Long companyId, String filterBy, String filterValue) {
		List<MCampaign> campaigns = new ArrayList<MCampaign>();
		
		String query = "SELECT m FROM MCampaign m ";
		String where = " WHERE m.company_id = :cId ";
		
		if (filterBy != null && !filterBy.equals("")) {
			switch (filterBy) {
			case "title":
				filterValue = filterValue.toLowerCase();
				where += " AND lower(m.title) like '%"+filterValue+"%'";
				break;
			}
			campaigns = em.createQuery(query+where, MCampaign.class)
					.setParameter("cId", companyId)
					.getResultList();
		} else {
			campaigns = em.createQuery(query+where, MCampaign.class)
						.setParameter("cId", companyId)
						.getResultList();
		}
		
		//campaigns = em.createQuery("SELECT c FROM MCampaign c where c.company_id = :companyId",MCampaign.class).setParameter("companyId", companyId).getResultList();
		//System.out.println(campaigns.size()+"-"+companyId);
		return campaigns;
	}
	
	public MCampaign campaignDetails(Long campaign_id){
		MCampaign campaign = find(campaign_id);
		return campaign;
	}
	
	public Long createCampaign(MCampaign campaign){
		save(campaign);
		return campaign.getId();
	}
	
	public Long updateCampaign(MCampaign campaign){
		update(campaign);
		return campaign.getId();
	}
	
	public void deleteCampaign(Long id){
		List<CampaignProduct> cps = campaignProducts(id);
		for(CampaignProduct cp : cps){
			em.remove(cp);
		}
		delete(id);
	}
	
	public void addCampaignProduct(CampaignProduct cp){
		em.persist(cp);
	}
	
	public void deleteCampaignProduct(CampaignProduct cp){
		List<CampaignProduct> campaignProduct = productExists(cp.getCampaignId(), cp.getProductId());
		if(campaignProduct.size() > 0) em.remove(campaignProduct.get(0));
	}
	
	public List<String> campaignTargets(MCampaign campaign){
		List<String> contactList = new ArrayList<String>();
		List<MarketingCampaignSettingsCustomers> mcsc = em.createQuery("SELECT c FROM MarketingCampaignSettingsCustomers c join c.marketingCampaignSettings s where s.campaign = :campaign",MarketingCampaignSettingsCustomers.class).setParameter("campaign", campaign).getResultList();
		for(MarketingCampaignSettingsCustomers mcs : mcsc){
			contactList.add(mcs.getCsContact().getContactEmail());
		}
		for(String potemail : campaignEmails(campaign.getId()).split(";")){
			contactList.add(potemail);
		}
		return contactList;
	}
	
	public String campaignMessage(MCampaign campaign){
		return em.createQuery("SELECT c FROM MarketingCampaignSettings c where c.campaign = :campaign",MarketingCampaignSettings.class).setParameter("campaign", campaign).getSingleResult().getCampaignSettingsMessage();
	}
	
	public String campaignEmails(Long campaignId){
		return em.createQuery("SELECT c FROM MCampaign c where c.id = :campaignId",MCampaign.class).setParameter("campaignId", campaignId).getSingleResult().getPotentialCustomerEmailIds();
	}
	
	public List<CampaignProduct> productExists(Long campId, Long prodId){
		return em.createQuery("SELECT c FROM CampaignProduct c where c.campaignId = :campId and c.productId = :prodId",CampaignProduct.class).setParameter("campId", campId).setParameter("prodId", prodId).getResultList();
	}
	
	public List<CampaignProduct> campaignProducts(Long campId){
		return em.createQuery("SELECT c FROM CampaignProduct c where c.campaignId = :campId",CampaignProduct.class).setParameter("campId", campId).getResultList();
	}
	
	public Long getLaunchedCampaignsCounter(Long companyId){
		Long launchedCampaigns = 0L;
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		Query query = em.createQuery("SELECT count(c) FROM MCampaign c where c.status = true AND c.company_id = :companyId AND c.launchDate <= :launchDate").setParameter("companyId", companyId).setParameter("launchDate", currentTime);
		launchedCampaigns = Long.parseLong(query.getSingleResult().toString());
		return launchedCampaigns;
	}
	
	public List<MCampaign> listLaunchedCampaigns(Long companyId) {
		List<MCampaign> campaigns;
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		campaigns = em.createQuery("SELECT c FROM MCampaign c where c.status = true AND c.company_id = :companyId AND c.launchDate <= :launchDate",MCampaign.class).setParameter("companyId", companyId).setParameter("launchDate", currentTime).getResultList();
		return campaigns;
	}	
	
	public List<CampaignProduct> listCampaignProducts(Long campaignId) {
		List<CampaignProduct> products;
		products = em.createQuery("SELECT cp FROM CampaignProduct cp WHERE  cp.campaignId = :campaignId", CampaignProduct.class).setParameter("campaignId", campaignId).getResultList();
		return products;
	}	
}
