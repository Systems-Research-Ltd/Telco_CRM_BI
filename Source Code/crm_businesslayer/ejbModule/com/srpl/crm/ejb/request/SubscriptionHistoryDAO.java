package com.srpl.crm.ejb.request;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.srpl.crm.ejb.entity.CsContactORM;
import com.srpl.crm.ejb.entity.SServiceSubscriptionHistoryORM;
import com.srpl.um.ejb.entity.UmCompany;

/**
 * Session Bean implementation class CompanyDAO
 */
@Stateless
@LocalBean
public class SubscriptionHistoryDAO extends
		GenericDAO<SServiceSubscriptionHistoryORM> {

	/**
	 * Default constructor.
	 */
	public SubscriptionHistoryDAO() {
		// TODO Auto-generated constructor stub
		super(SServiceSubscriptionHistoryORM.class);
	}

	@EJB
	ContactDAO contactDao;

	// ================= List history =======================//
	public List<SServiceSubscriptionHistoryORM> list(long customerId) {

		List<SServiceSubscriptionHistoryORM> history = null;
		try {
			CsContactORM customer = contactDao.contactDetails(customerId);
			history = em
					.createQuery(
							"FROM SServiceSubscriptionHistoryORM WHERE subscriber = :c",
							SServiceSubscriptionHistoryORM.class)
					.setParameter("c", customer).getResultList();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return history;
	}

	// ================= Create history =======================//
	public Long create(SServiceSubscriptionHistoryORM s) {
		save(s);
		return s.getId();
	}

	// ================= Update history =======================//
	public Long updateCompany(UmCompany company) {
		// need to update
		System.out.println(company.getCompanyId());
		em.merge(company);
		return company.getCompanyId();
	}

	// ================= Delete history =======================//
	public void deleteCompany(Long companyId) {
		delete(companyId);
	}

	// ================= Details of history =======================//
	public SServiceSubscriptionHistoryORM details(Long id) {
		SServiceSubscriptionHistoryORM s = find(id);
		return s;
	}

}
