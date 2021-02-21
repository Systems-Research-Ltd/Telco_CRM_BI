package com.srpl.um.ejb.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.srpl.um.ejb.request.UmPersistence;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the countries database table.
 * 
 */
@Entity
@Table(name="countries"  , schema=UmPersistence.schema)
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="country_id")
	private Integer countryId;

	@Column(name="country_code")
	private String countryCode;

	@Column(name="country_name")
	private String countryName;
	
	@Column(name = "added_on")
	private Timestamp addedon;

	@Column(name = "mapped_id")
	private Long mappedId;

	//bi-directional many-to-one association to State
	@OneToMany(mappedBy="country")
	private List<State> states;
	
	@Transient
	private String isCorrect;

    public Country() {
    }    

	public Timestamp getAddedon() {
		return addedon;
	}

	public void setAddedon(Timestamp addedon) {
		this.addedon = addedon;
	}	

	public String getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(String isCorrect) {
		this.isCorrect = isCorrect;
	}

	public Integer getCountryId() {
		return this.countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return this.countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public List<State> getStates() {
		return this.states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}

	public Long getMappedId() {
		return mappedId;
	}

	public void setMappedId(Long mappedId) {
		this.mappedId = mappedId;
	}
	
}