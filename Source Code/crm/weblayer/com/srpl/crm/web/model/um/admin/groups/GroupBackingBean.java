package com.srpl.crm.web.model.um.admin.groups;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;


@ManagedBean(name="groups")
@RequestScoped
@Deprecated
public class GroupBackingBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GroupBackingBean(){
		
	}
	
	private long group_id;
	private List<String> role_ids = new ArrayList<String>();
	private Long company_id;
	private String group_title;
	private String group_details;
	private Boolean group_status = true;

	public long getGroup_id() {
		return group_id;
	}
	public void setGroup_id(long group_id) {
		this.group_id = group_id;
	}	
	public List<String> getRole_ids() {
		return role_ids;
	}
	public void setRole_ids(List<String> role_ids) {
		this.role_ids = role_ids;
	}
	public Long getCompany_id() {
		return company_id;
	}
	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}
	public String getGroup_title() {
		return group_title;
	}
	public void setGroup_title(String group_title) {
		this.group_title = group_title;
	}
	public String getGroup_details() {
		return group_details;
	}
	public void setGroup_details(String group_details) {
		this.group_details = group_details;
	}
	public Boolean getGroup_status() {
		return group_status;
	}
	public void setGroup_status(Boolean group_status) {
		this.group_status = group_status;
	}
	
	public void reset(){
		setGroup_id(0);
		setCompany_id(0L);
		setRole_ids(null);
		setGroup_title("");
		setGroup_details("");
		setGroup_status(true);
	}
}
