package com.srpl.bi.web.model.common;

import java.io.Serializable;

public class Country implements Serializable {
	private int id;
	private String countryName;
	private String countryCode;

	public Country(){
		
	}
	public Country(int i, String name, String code){
		this.setId(i);
		this.setCountryName(name);
		this.setCountryCode(code);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

}
