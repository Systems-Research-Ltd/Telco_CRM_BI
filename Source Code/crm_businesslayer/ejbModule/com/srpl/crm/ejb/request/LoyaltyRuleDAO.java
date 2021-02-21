package com.srpl.crm.ejb.request;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.srpl.crm.ejb.entity.CsContactORM;
import com.srpl.crm.ejb.entity.LoyaltyORM;
import com.srpl.crm.ejb.entity.LoyaltyRuleORM;
import com.srpl.crm.ejb.exceptions.LoyaltyNotFoundException;

@Stateless
@LocalBean
public class LoyaltyRuleDAO extends GenericDAO<LoyaltyRuleORM> {
	/**
	 * Default constructor.
	 */
	public LoyaltyRuleDAO() {
		// TODO Auto-generated constructor stub
		super(LoyaltyRuleORM.class);
	}

	public void updateLoyaltyRule(LoyaltyRuleORM loyaltyRule) {
		System.out.println("updateLoyaltyRule called");
		System.out.println(loyaltyRule.getLoyaltyRuleId() + "rule");
		System.out.println(loyaltyRule.getLoyalty().getLoyaltyId()
				+ "loyalty ID");
		update(loyaltyRule);
		System.out.println("+++");
		// updateLoyaltyRule(loyaltyRule);
		// return loyaltyRule.getLoyaltyRuleId();
	}

	public LoyaltyRuleORM loyaltyRuleDetail(Long loyaltyRuleId)
			throws LoyaltyNotFoundException {
		System.out.println(" DAO loyalty details called" + loyaltyRuleId);
		LoyaltyRuleORM loyaltyRule = null;
		if (loyaltyRuleId == null) {
			throw new LoyaltyNotFoundException("Invalid loyalty Id");
		}
		loyaltyRule = find(loyaltyRuleId);
		System.out.println(loyaltyRule);
		return loyaltyRule;
	}

	// commented timebeing
	/*
	 * public List<LoyaltyRuleORM> retrieveSingleRuleDetail(LoyaltyORM loyalty){
	 * System.out.println("retrieveSingleRuleDetail called");
	 * List<LoyaltyRuleORM> loyaltyDetails; loyaltyDetails =
	 * em.createQuery("from LoyaltyRuleORM where loyalty =:loyalty"
	 * ,LoyaltyRuleORM.class).setParameter("loyalty", loyalty).getResultList();
	 * return loyaltyDetails;
	 * 
	 * }
	 */

	public List<LoyaltyRuleORM> retrieveSingleRuleDetail(LoyaltyORM loyalty) {
		System.out.println("retrieveSingleRuleDetail called");
		//LoyaltyRuleORM loyaltyDetails = new LoyaltyRuleORM();
		List<LoyaltyRuleORM> loyaltyList = new ArrayList<LoyaltyRuleORM>();
		loyaltyList = em
				.createQuery("from LoyaltyRuleORM where loyalty =:loyalty",
						LoyaltyRuleORM.class).setParameter("loyalty", loyalty)
				.getResultList();
		/*if (loyaltyList.size() > 0) {
			System.out.println("list size*** " + loyaltyList.size());
			return loyaltyList.get(0);
		}*/
		return loyaltyList;

	}

	public LoyaltyRuleORM retrieveSingleRuleDetail1(LoyaltyORM loyalty) {
		LoyaltyRuleORM loyaltyDetails;
		loyaltyDetails = em
				.createQuery("from LoyaltyRuleORM where loyalty =:loyalty",
						LoyaltyRuleORM.class).setParameter("loyalty", loyalty)
				.getSingleResult();
		return loyaltyDetails;

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
		updateLoyaltyRule(loyaltyrule);

	}

	public List<LoyaltyRuleORM> getRulesListByLoyalty(LoyaltyORM loyalty) {
		List<LoyaltyRuleORM> rulesList = new ArrayList<LoyaltyRuleORM>();
		rulesList = em.createQuery("SELECT lr FROM LoyaltyRuleORM lr WHERE loyalty = :loyalty", LoyaltyRuleORM.class)
				.setParameter("loyalty", loyalty)
				.getResultList();
		return rulesList;

	}

	/*
	 * public boolean checkLoyalty(String query){ boolean isLoyalty = false;
	 * Connection con = DBConnection.getConnection(); Statement statement =
	 * null; ResultSet resultset; try { statement = con.createStatement(); }
	 * catch (SQLException e1) { // TODO Auto-generated catch block
	 * e1.printStackTrace(); } try { resultset = statement.executeQuery(query);
	 * if(resultset.next()){ isLoyalty = true; } } catch (SQLException e1) { //
	 * TODO Auto-generated catch block e1.printStackTrace(); } try {
	 * con.close(); } catch (SQLException e) { // TODO Auto-generated catch
	 * block e.printStackTrace(); } return isLoyalty; }
	 */

	public boolean checkLoyalty(CsContactORM customer, String rule,
			String condition, String value) {
		boolean loyalty = false;
		//String orm = "CcContactORM";
		//switch (orm) {
		//case "CsContactORM":
			List<CsContactORM> list = em
					.createQuery(
							"from CsContactORM WHERE " + rule + " " + condition
									+ " '" + value
									+ "' AND contactId = :customerId",
							CsContactORM.class)
					.setParameter("customerId", customer.getContactId())
					.getResultList();
			if (list.size() > 0) {
				loyalty = true;
			}
			//break;
		//}
		return loyalty;
	}

}
