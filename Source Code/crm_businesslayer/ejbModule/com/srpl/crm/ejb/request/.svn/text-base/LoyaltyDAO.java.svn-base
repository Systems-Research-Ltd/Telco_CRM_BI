package com.srpl.crm.ejb.request;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import com.srpl.crm.ejb.entity.CsContactORM;
import com.srpl.crm.ejb.entity.LoyaltyORM;
import com.srpl.crm.ejb.entity.LoyaltyRuleORM;
import com.srpl.crm.ejb.exceptions.LoyaltyNotFoundException;
import com.srpl.um.ejb.entity.UmCompany;

@Stateless
@LocalBean
public class LoyaltyDAO extends GenericDAO<LoyaltyORM> {
	/**
	 * Default constructor.
	 */
	public LoyaltyDAO() {
		// TODO Auto-generated constructor stub
		super(LoyaltyORM.class);
	}

	public List<LoyaltyORM> listLoyalty(UmCompany company)
			throws LoyaltyNotFoundException {

		System.out.println("loyalityList called..");

		List<LoyaltyORM> loyaltyList = em
				.createQuery(
						"SELECT l FROM LoyaltyORM l WHERE company = :company",
						LoyaltyORM.class).setParameter("company", company)
				.getResultList();
		System.out.println("loyaltyDao test");
		if (loyaltyList.size() == 0) {
			throw new LoyaltyNotFoundException("No Loyalty Record Found");
		}

		return loyaltyList;
	}

	public List<LoyaltyORM> getActiveLoyalties() {
		List<LoyaltyORM> list = new ArrayList<LoyaltyORM>();
		try {
			list = em.createQuery(
					"SELECT l FROM LoyaltyORM l  WHERE loyaltyStatus = true ",
					LoyaltyORM.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public Long createLoyalty(LoyaltyORM loyalty) {
		save(loyalty);
		return loyalty.getLoyaltyId();

	}

	public Long createLoyaltyRule(LoyaltyRuleORM loyaltyRule) {
		em.persist(loyaltyRule);
		return loyaltyRule.getLoyaltyRuleId();
	}

	public void updateLoyalty1(LoyaltyORM loyalty) {
		update(loyalty);
	}

	public Long updateLoyalty(LoyaltyORM loyalty) {
		System.out.println("LoyaltyDao updatefun called");
		update(loyalty);
		return loyalty.getLoyaltyId();

	}

	public Long updateNewLoyalty(LoyaltyORM loyalty, Long loyaltyRuleId)
			throws LoyaltyNotFoundException {
		if (loyaltyRuleId == null) {
			throw new LoyaltyNotFoundException("Invalid loyaltyRule Id");

		}

		LoyaltyRuleORM selloyaltyRule = em.find(LoyaltyRuleORM.class,
				loyaltyRuleId);
		loyalty.setLoyaltyId(loyaltyRuleId);
		update(loyalty);

		return loyalty.getLoyaltyId();
	}

	public void deleteLoyalty(Long loyaltyId) {
		delete(loyaltyId);
	}

	public List<LoyaltyRuleORM> getLoyaltyRules(long lrId) {
		List<LoyaltyRuleORM> LRule = null;
		LRule = em
				.createQuery(
						"SELECT r FROM LoyaltyRuleORM r JOIN r.loyalty lr where lr.loyaltyId = :lid ",
						LoyaltyRuleORM.class).setParameter("lid", lrId)
				.getResultList();
		System.out.println(LRule.size());
		return LRule;
	}

	public List<Long> getLoyaltyRuleIds(Long rId) /* throws UserNotFoundException */{
		List<LoyaltyRuleORM> LRule = null;
		LRule = getLoyaltyRules(rId);
		System.out.println("loyaltyRuleId++" + LRule);
		return copyRuleIdsToList(LRule);
	}

	private List<Long> copyRuleIdsToList(List<LoyaltyRuleORM> LRule) {
		List<Long> ruleIdList = new ArrayList<Long>();
		Iterator<LoyaltyRuleORM> i = LRule.iterator();
		while (i.hasNext()) {
			LoyaltyRuleORM lro = (LoyaltyRuleORM) i.next();
			ruleIdList.add(lro.getLoyaltyRuleId());

		}
		return ruleIdList;
	}

	public void deleteLoyalty(LoyaltyORM loyalty) {
		int recordsDeleted = em.createQuery("DELETE  LoyaltyRuleORM lr  WHERE lr.loyalty = :loyalty")
				.setParameter("loyalty", loyalty).executeUpdate();
		delete(loyalty.getLoyaltyId());
	}

	public void updateLoyaltyInfo(LoyaltyORM loyalty) {
		Long loyaltyId = null;
		System.out.println("Loyalty Id :::::" + loyaltyId);
		List<Long> ruleIds = getLoyaltyRuleIds(loyaltyId);
		for (Long rId : ruleIds) {
			LoyaltyRuleORM slr = em
					.createQuery(
							"Select l from LoyaltyRuleORM l where l.loyaltyRuleId = :lId",
							LoyaltyRuleORM.class).setParameter("lId", rId)
					.getSingleResult();
			// em.remove(slr);
			em.merge(slr);
		}
		update(loyalty);

	}

	public LoyaltyORM loyaltyDetail(Long loyaltyId)
			throws LoyaltyNotFoundException {
		System.out.println("loyalty details called" + loyaltyId);
		LoyaltyORM loyalty = null;
		if (loyaltyId == null) {
			throw new LoyaltyNotFoundException("Invalid loyalty Id");
		}
		loyalty = find(loyaltyId);
		return loyalty;
	}

	public List<LoyaltyRuleORM> listloyaltyRules1(Long loyaltyId) /*
																 * throws
																 * UserNotFoundException
																 */{
		List<LoyaltyRuleORM> loyaltyRules = null;
		loyaltyRules = em
				.createQuery(
						"SELECT lr FROM loyalty_rule lr WHERE lr.loyaltyId = :lid",
						LoyaltyRuleORM.class).setParameter("lid", loyaltyId)
				.getResultList();

		return loyaltyRules;
	}

	public Set<Attribute<? super CsContactORM, ?>> listTableCols() {
		// TODO temorarly commented by Abdul Kareem,
		// selTable class was missing on my end
		Metamodel metamodel = em.getMetamodel();
		EntityType<CsContactORM> type = metamodel.entity(CsContactORM.class);
		Set<Attribute<? super CsContactORM, ?>> cols = type.getAttributes();
		return cols;

		// return null;
	}

	public List<LoyaltyRuleORM> listLoyaltyRules(Long loyaltyId)
			throws LoyaltyNotFoundException {
		System.out.println("loyaltyRuleList called");

		List<LoyaltyRuleORM> loyaltyrule = null;
		LoyaltyORM loyal = em.find(LoyaltyORM.class, loyaltyId);
		System.out.println(loyal + "loyaltyID in Dao");
		if (loyaltyId == null) {
			throw new LoyaltyNotFoundException("Invalid loyalty Id");
		}
		loyaltyrule = em
				.createQuery(
						"SELECT lr FROM LoyaltyRuleORM lr WHERE lr.loyalty = :lid",
						LoyaltyRuleORM.class).setParameter("lid", loyal)
				.getResultList();

		return loyaltyrule;
	}

	public LoyaltyRuleORM loyaltyRuleDetails(Long loyaltyId)
			throws LoyaltyNotFoundException {
		LoyaltyRuleORM loyaltyrule = null;
		LoyaltyORM loyal = em.find(LoyaltyORM.class, loyaltyId);
		if (loyaltyId == null) {
			throw new LoyaltyNotFoundException("Invalid loyalty Id");
		}
		loyaltyrule = em
				.createQuery(
						"SELECT lr FROM LoyaltyRuleORM lr WHERE lr.loyalty = :lid",
						LoyaltyRuleORM.class).setParameter("lid", loyal)
				.getSingleResult();

		return loyaltyrule;
	}

	public Boolean getSelectColumn(String loyaltyRule, String loyaltyCondition,
			String loyaltyConditionValue) throws ParseException {
		List<CsContactORM> csContacts = new ArrayList<CsContactORM>();
		String str = loyaltyConditionValue;
		System.out.println(loyaltyRule + "    " + loyaltyConditionValue);
		if (loyaltyRule.equals("contactId") || loyaltyRule.equals("accountId")) {
			Long l = Long.parseLong(loyaltyConditionValue);
			// csContacts =
			// em.createQuery("Select cs From CsContactORM cs Where cs."+loyaltyRule+" =:csName",CsContactORM.class).setParameter("csName",
			// l).getResultList();
			csContacts = em
					.createQuery(
							"Select cs From CsContactORM cs Where cs."
									+ loyaltyRule + loyaltyCondition
									+ ":csName", CsContactORM.class)
					.setParameter("csName", l).getResultList();
			System.out.println("csContact result 1st " + csContacts.size());
			System.out.println(l + ">>>>>>>>>>>--" + loyaltyCondition);
		} else if (loyaltyRule.equals("contactCreatedon")
				|| loyaltyRule.equals("contactDob")) {
			DateFormat ts = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			Date date = ts.parse(str);
			System.out.println("pasre date gives date object" + date);
			String a = ts.format(date);
			System.out.println("a is +" + a);
			System.out.println("outputText" + date);
			System.out.println("date in dao method get column" + date);
			csContacts = em
					.createQuery(
							"Select cs From CsContactORM cs Where cs."
									+ loyaltyRule + loyaltyCondition
									+ ":csName", CsContactORM.class)
					.setParameter("csName", date).getResultList();
			System.out.println("csContact result 2nd " + csContacts.size()
					+ "csContact itme(s)" + csContacts.get(0).toString()
					+ "++++" + csContacts.get(0).toString());
		} else if (loyaltyRule.equals("contactStatus")) {
			boolean b = Boolean.parseBoolean(loyaltyConditionValue);
			csContacts = em
					.createQuery(
							"Select cs From CsContactORM cs Where cs."
									+ loyaltyRule + loyaltyCondition
									+ ":csName", CsContactORM.class)
					.setParameter("csName", b).getResultList();
			System.out.println(b + ">>>>>>>>>>>-- csContact result 2nd"
					+ csContacts.size() + "//" + csContacts.get(0)
					+ csContacts.get(1));
		} else {
			csContacts = em
					.createQuery(
							"Select cs From CsContactORM cs Where cs."
									+ loyaltyRule + loyaltyCondition
									+ ":csName", CsContactORM.class)
					.setParameter("csName", loyaltyConditionValue)
					.getResultList();
			System.out.println("csContact result last " + csContacts.size());
		}
		System.out.println(loyaltyConditionValue);
		return ((csContacts.isEmpty()) ? false : true);
	}

	public Long getContactLastName(String contactFname) {
		CsContactORM contact = em
				.createQuery("from CsContactORM where contactFname = :cname",
						CsContactORM.class).setParameter("cname", contactFname)
				.getSingleResult();
		Long contactId = contact.getContactId();
		return contactId;

	}

	public List<CsContactORM> listcontactsFname() /* throws UserNotFoundException */{
		List<CsContactORM> cont = null;
		cont = em.createQuery("select contactFname from CsContactORM",
				CsContactORM.class).getResultList();
		return cont;
	}

	/*----*/
	public LoyaltyORM loyaltyNewDetails(Long loyaltyId) {
		LoyaltyORM loyalty = find(loyaltyId);
		return loyalty;
	}

	public List<LoyaltyORM> listLoyaltyR() {
		List<LoyaltyORM> loyalty = findAll();
		return loyalty;
	}

	public LoyaltyORM retrieveLoyalty(Long loyaltyId) {
		System.out.println("loyalty id in reterive" + loyaltyId);
		LoyaltyORM loyalty;
		loyalty = find(loyaltyId);
		return loyalty;
	}

	public void loyaltyRuleUpdate(Long loyaltyId)
			throws LoyaltyNotFoundException {
		System.out.println("loyaltyruleupdate");
		LoyaltyRuleORM loyaltyrule = null;
		LoyaltyORM loyal = em.find(LoyaltyORM.class, loyaltyId);
		if (loyaltyId == null) {
			throw new LoyaltyNotFoundException("Invalid loyalty Id");
		}
		loyaltyrule = em
				.createQuery(
						"SELECT lr FROM LoyaltyRuleORM lr WHERE lr.loyalty = :lid",
						LoyaltyRuleORM.class).setParameter("lid", loyal)
				.getSingleResult();
		em.persist(loyaltyId);

	}

	public LoyaltyORM getLoyaltyByTitle(String loyaltyTitle) {
		System.out.println("getLoyaltyByTitle() called");
		// System.out.println("OrderTitle = "+orderTitle);
		LoyaltyORM loyalty;
		loyalty = em
				.createQuery(
						"from LoyaltyORM where loyaltyTitle = :loyaltyTitle",
						LoyaltyORM.class)
				.setParameter("loyaltyTitle", loyaltyTitle).setMaxResults(1)
				.getSingleResult();
		System.out.println(" title = " + loyalty.getLoyaltyId());
		return loyalty;
	}

}
