package com.srpl.um.ejb.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

import com.srpl.um.ejb.request.UmPersistence;


/**
 * The persistent class for the cities database table.
 * 
 */
@Entity
@Table(name="cities"  , schema=UmPersistence.schema)
public class City implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="city_id")
	private Integer cityId;

	@Column(name="city_name")
	private String cityName;
	
	@Column(name = "added_on")
	private Timestamp addedon;

	//bi-directional many-to-one association to State
    @ManyToOne
	@JoinColumn(name="state_id")
	private State state;

	@Column(name = "mapped_id")
	private Long mappedId;
    
    @Transient
	private String isCorrect;

    public City() {
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

	public Integer getCityId() {
		return this.cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return this.cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Long getMappedId() {
		return mappedId;
	}

	public void setMappedId(Long mappedId) {
		this.mappedId = mappedId;
	}
	
}