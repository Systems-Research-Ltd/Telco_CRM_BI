package com.srpl.bi.web.common;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;


@ManagedBean(eager = true)
@SessionScoped
public class SessionDataBean implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long companyId;
	private Long userId;
	private String userFname="Mr. ";
	private String userLname="User";
	String row_id;
	
	
	
	private int selectedCountry;
	private int selectedState;
	
	private Long companyForParameter;
	
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserFname() {
		System.out.println(userFname);
		return userFname;
	}
	public void setUserFname(String userFname) {
		this.userFname = userFname;
	}
	public String getUserLname() {
		return userLname;
	}
	public void setUserLname(String userLname) {
		this.userLname = userLname;
	}
	
	public String parameterAction()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		row_id = request.getParameter("row_id");
		System.out.println("row ID ="+ row_id);
		return "compparameter";
	}

	public String getRow_id() {
		return row_id;
	}

	public void setRow_id(String row_id) {
		this.row_id = row_id;
	}
	
	
	public int getSelectedCountry() {
		return selectedCountry;
	}
	
	public void setSelectedCountry(int selectedCountry) {
		this.selectedCountry = selectedCountry;
	}
	public int getSelectedState() {
		return selectedState;
	}
	public void setSelectedState(int selectedState) {
		this.selectedState = selectedState;
	}
	
	public Long getCompanyForParameter() {
		return companyForParameter;
	}
	public void setCompanyForParameter(Long companyForParameter) {
		this.companyForParameter = companyForParameter;
	} 
	
}
