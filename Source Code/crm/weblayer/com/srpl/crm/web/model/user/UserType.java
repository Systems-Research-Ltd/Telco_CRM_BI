package com.srpl.crm.web.model.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import com.srpl.crm.common.utils.UmCompanyDetails;
import com.srpl.crm.common.utils.UmFranchiseDetails;
import com.srpl.crm.common.utils.UmUserDetails;
import com.srpl.um.ejb.request.CompanyDAO;
import com.srpl.um.ejb.request.FranchiseDAO;

public class UserType  implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<UmCompanyDetails>companyList;
	private List<UmFranchiseDetails>franchiselist;
	
	
	@EJB CompanyDAO companyDao;
	@EJB FranchiseDAO franchiseDao;
	
	
	
	public List<UmCompanyDetails> getCompanyList() {
		return companyList;
	}
	public void setCompanyList(List<UmCompanyDetails> companyList) {
		this.companyList = companyList;
	}
	public List<UmFranchiseDetails> getFranchiselist() {
		return franchiselist;
	}
	public void setFranchiselist(List<UmFranchiseDetails> franchiselist) {
		this.franchiselist = franchiselist;
	}

}
