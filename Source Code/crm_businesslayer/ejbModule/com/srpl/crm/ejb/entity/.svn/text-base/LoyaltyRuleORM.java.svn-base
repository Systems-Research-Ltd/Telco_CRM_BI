package com.srpl.crm.ejb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "loyalty_rule")
public class LoyaltyRuleORM implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name = "LOYALTY_RULE_LOYALTY_RULE_ID_GENERATOR",

	sequenceName = "LOYALTY_RULE_LOYALTY_RULE_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LOYALTY_RULE_LOYALTY_RULE_ID_GENERATOR")
	@Column(name = "loyalty_rule_id")
	private Long loyaltyRuleId;

	@Column(name = "loyalty_rule")
	private String loyaltyRule;

	@Column(name = "loyalty_rule_condition")
	private String loyaltyRuleCondition;

	@Column(name = "loyalty_rule_value")
	private String loyaltyRuleValue;

	// bi-directional many-to-one association to LoyaltyORM
	@ManyToOne
	@JoinColumn(name = "loyalty_id")
	private LoyaltyORM loyalty;

	public LoyaltyRuleORM() {

	}

	public LoyaltyRuleORM(LoyaltyORM loyalty, String loyaltyRule,
			String loyaltyRuleCondition, String
			loyaltyRuleValue) {

		this.loyalty = loyalty;
		this.loyaltyRule = loyaltyRule;
		this.loyaltyRuleCondition = loyaltyRuleCondition;
		this.loyaltyRuleValue = loyaltyRuleValue;
	}

	public LoyaltyRuleORM(LoyaltyORM loyalty, Long loyaltyRuleId,
			String loyaltyRule, String loyaltyRuleCondition, String
			loyaltyRuleValue) {

		this.loyalty = loyalty;
		this.loyaltyRuleId = loyaltyRuleId;
		this.loyaltyRule = loyaltyRule;
		this.loyaltyRuleCondition = loyaltyRuleCondition;
		this.loyaltyRuleValue = loyaltyRuleValue;
	}

	public String toString() {
		return "loyaltyRule" + getLoyaltyRule();
	}

	public Long getLoyaltyRuleId() {
		return loyaltyRuleId;
	}

	public void setLoyaltyRuleId(Long loyaltyRuleId) {
		this.loyaltyRuleId = loyaltyRuleId;
	}

	public String getLoyaltyRule() {
		return loyaltyRule;
	}

	public void setLoyaltyRule(String loyaltyRule) {
		this.loyaltyRule = loyaltyRule;
	}

	public String getLoyaltyRuleCondition() {
		return loyaltyRuleCondition;
	}

	public void setLoyaltyCondition(String loyaltyRuleCondition) {
		this.loyaltyRuleCondition = loyaltyRuleCondition;
	}

	public String getLoyaltyRuleValue() {
		return loyaltyRuleValue;
	}

	public void setLoyaltyValue(String loyaltyRuleValue) {
		this.loyaltyRuleValue = loyaltyRuleValue;
	}

	public LoyaltyORM getLoyalty() {
		return loyalty;
	}

	public void setLoyalty(LoyaltyORM loyalty) {
		this.loyalty = loyalty;
	}

}
