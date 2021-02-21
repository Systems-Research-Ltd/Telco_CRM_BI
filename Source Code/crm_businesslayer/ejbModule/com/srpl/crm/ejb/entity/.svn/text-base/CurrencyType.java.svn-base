package com.srpl.crm.ejb.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="currency_type")
public class CurrencyType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="title")
	private String title;
	/*
	//one to many association
	@OneToMany(mappedBy="currencyType")
	private List<MCampaign> MCampaign;
*/
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
/*
	public List<MCampaign> getMCampaign() {
		return MCampaign;
	}

	public void setMCampaign(List<MCampaign> mCampaign) {
		MCampaign = mCampaign;
	}
	
	*/

}
