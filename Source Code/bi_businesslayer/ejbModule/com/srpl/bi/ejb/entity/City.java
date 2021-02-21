package com.srpl.bi.ejb.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cities database table.
 * 
 */
@Entity
@Table(name="cities")
public class City implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="city_id")
	private Integer cityId;

	@Column(name="city_name")
	private String cityName;

	//bi-directional many-to-one association to State
    @ManyToOne
	@JoinColumn(name="state_id")
	private State state;

    public City() {
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
	
}