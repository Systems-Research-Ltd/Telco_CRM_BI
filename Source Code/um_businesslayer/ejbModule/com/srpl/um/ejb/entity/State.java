package com.srpl.um.ejb.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.srpl.um.ejb.request.UmPersistence;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the states database table.
 * 
 */
@Entity
@Table(name="states", schema=UmPersistence.schema)
public class State implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="state_id")
	private Integer stateId;

	@Column(name="state_code")
	private String stateCode;

	@Column(name="state_name")
	private String stateName;
	
	@Column(name = "added_on")
	private Timestamp addedon;

	//bi-directional many-to-one association to City
	@OneToMany(mappedBy="state")
	private List<City> cities;

	@Column(name = "mapped_id")
	private Long mappedId;

	//bi-directional many-to-one association to Country
    @ManyToOne
	@JoinColumn(name="country_id")
	private Country country;
    
    @Transient
	private String isCorrect;

    public State() {
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

	public Integer getStateId() {
		return this.stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public String getStateCode() {
		return this.stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getStateName() {
		return this.stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public List<City> getCities() {
		return this.cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}
	
	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Long getMappedId() {
		return mappedId;
	}

	public void setMappedId(Long mappedId) {
		this.mappedId = mappedId;
	}	
	
}