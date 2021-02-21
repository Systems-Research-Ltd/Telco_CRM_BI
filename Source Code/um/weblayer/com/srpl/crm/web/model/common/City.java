package com.srpl.crm.web.model.common;

import java.io.Serializable;

public class City implements Serializable {
	private int cityId;
	private int stateId;
	private String cityName;
	
	public City(){
		
	}
	public City(int cityId,int statId,String cityName){
		this.setCityId(cityId);
		this.setStateId(statId);
		this.setCityName(cityName);
		
	}
	
	
	
  public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public int getStateId() {
		return stateId;
	}
	public void setStateId(int stateId) {
		this.stateId = stateId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
}
