package com.srpl.crm.ejb.request;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import com.srpl.crm.ejb.entity.SalesLeadORM;
import com.srpl.um.ejb.entity.UmUser;
import com.srpl.crm.ejb.exceptions.LeadNotFoundException;

/**
 * Session Bean implementation class LeadsDAO
 */
@Stateless
@LocalBean
public class LeadsDAO extends GenericDAO<SalesLeadORM> {
	@EJB
	CampaignDAO campaignDao;

	public LeadsDAO() {
		super(SalesLeadORM.class);
	}

	public List<SalesLeadORM> listLeads(long companyId) throws LeadNotFoundException {
		List<SalesLeadORM> leads = em.createQuery("SELECT s FROM SalesLeadORM s where s.leadCompany = :leadCompany and s.leadStatus != 'won'",SalesLeadORM.class).setParameter("leadCompany", companyId).getResultList();
		if (leads.size() == 0) {
			throw new LeadNotFoundException("No Lead Record Found");
		}
		return leads;
	}

	public Long createLead(SalesLeadORM lead, String company) {
		save(lead);
		if (lead.getLeadEmail() != "") {
			// MCampaign camp = (lead.getLeadSource() != 0) ?
			// campaignDao.campaignDetails((long)lead.getLeadSource()) : null;
			final String username = "rizwan.softwareengineer05@gmail.com";
			final String password = "systemsresearchltd";
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "465");
			props.put("mail.smtp.user", username);
			props.put("mail.smtp.auth", true);
			props.put("mail.smtp.starttls.enable", true);
			props.put("mail.smtp.debug", true);
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");
			Session session = Session.getInstance(props, null);
			session.setDebug(true);
			try {
				MimeMessage msg = new MimeMessage(session);
				msg.setFrom(new InternetAddress(
						"rizwan.softwareengineer05@gmail.com"));
				msg.addRecipient(RecipientType.TO,
						new InternetAddress(lead.getLeadEmail()));
				msg.setSubject("Interested in " + company
						+ " products/services");
				msg.setSentDate(new Date());
				msg.setText("Dear "
						+ lead.getLeadName()
						+ ",\n\nWe have kept your record in response to your interest in our products.\n\nOur representative will contact you shortly to help you out in selecting the package that best match your needs.\n\n"
						+ company);
				msg.saveChanges();
				Transport transport = session.getTransport("smtp");
				transport.connect("smtp.gmail.com", username, password);
				transport.sendMessage(msg, msg.getAllRecipients());
				transport.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return lead.getLeadId();
	}

	public void updateLead(SalesLeadORM lead) {
		update(lead);
	}

	public void deleteLead(Long leadId) {
		System.out.println(leadId);
		delete(leadId);
	}

	public SalesLeadORM leadDetails(Long leadId) throws LeadNotFoundException {
		SalesLeadORM lead = null;
		if (leadId == null) {
			throw new LeadNotFoundException("Invalid Lead Id");
		}
		lead = find(leadId);
		return lead;
	}

	public List<SalesLeadORM> listCampaignLeads(Long selectedCampaign) {
		List<SalesLeadORM> campaignLeadList = new ArrayList<SalesLeadORM>();
		campaignLeadList = em
				.createQuery(
						"from SalesLeadORM where leadSource =:selectedCampaign",
						SalesLeadORM.class)
				.setParameter("selectedCampaign", selectedCampaign)
				.getResultList();
		return campaignLeadList;
	}

	public List<UmUser> listAssignedToUser(Long leadAssigned){
		List<UmUser> assignedTo = new ArrayList<UmUser>();
		assignedTo = em.createQuery("from UmUser where userId =: leadAssigned",UmUser.class).setParameter("leadAssigned", leadAssigned).getResultList();
		return assignedTo ;
	}
	public List<SalesLeadORM> list(Long leadSource) {
    	String Str = "";
		List<SalesLeadORM> campaignleads = null;
		/*if(leadSource.equals(-1)){
			campaignleads = findAll();
		//campaignleads = em.createQuery("SELECT s FROM SalesLeadORM ",SalesLeadORM.class).getResultList();
		
	} */
		if(leadSource!= null && !leadSource.equals("")){
			campaignleads = em.createQuery("SELECT s FROM SalesLeadORM s where s.leadSource = :leadSource",SalesLeadORM.class).setParameter("leadSource", leadSource).getResultList();
		}
		else{
			campaignleads = em.createQuery("SELECT s FROM SalesLeadORM s where s.leadSource = 0",SalesLeadORM.class).getResultList();
		}
		return campaignleads;
	}

}
