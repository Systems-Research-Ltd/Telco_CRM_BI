package com.srpl.crm.ejb.request;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.srpl.crm.ejb.entity.SalesOpportunitiesListViewORM;
import com.srpl.um.ejb.entity.UmCompany;
import com.srpl.um.ejb.request.CompanyDAO;

/**
 * Session Bean implementation class OpportunityDAO
 */
@Stateless
@LocalBean
public class OpportunityListViewDAO extends
		GenericDAO<SalesOpportunitiesListViewORM> {

	@EJB
	CompanyDAO companyDao;

	/**
	 * Default constructor.
	 */
	public OpportunityListViewDAO() {
		// TODO Auto-generated constructor stub
		super(SalesOpportunitiesListViewORM.class);
	}

	public List<SalesOpportunitiesListViewORM> list(Long company_id) {
		List<SalesOpportunitiesListViewORM> opportunities = em
				.createQuery(
						"FROM SalesOpportunitiesListViewORM WHERE companyId = :c",
						SalesOpportunitiesListViewORM.class)
				.setParameter("c", company_id).getResultList();
		return opportunities;
	}

}
