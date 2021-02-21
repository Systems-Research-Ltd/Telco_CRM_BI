package com.srpl.bi.web.model.dashboard;

import java.io.Serializable;

public class DashBoardList implements Serializable{
	
	private Long id;
	private String name;
	private String createdBy;
	
	public DashBoardList(){
		
	}
	
	public DashBoardList(String name,String createdBy){
		this.name = name;
		this.createdBy = createdBy;
	}
	
	/**
	 * getter/setters
	 */
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

}
