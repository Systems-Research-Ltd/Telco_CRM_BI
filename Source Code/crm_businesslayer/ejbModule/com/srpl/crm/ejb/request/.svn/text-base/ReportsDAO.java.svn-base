package com.srpl.crm.ejb.request;

import java.util.List;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import com.srpl.crm.ejb.entity.OrderORM;
import com.srpl.crm.ejb.entity.ReportsORM;
import com.srpl.um.ejb.entity.UmCompany;
import com.srpl.um.ejb.entity.UmUser;

/**
 * Session Bean implementation class UserDAO
 */
@Stateless
@LocalBean
public class ReportsDAO extends GenericDAO<ReportsORM> {

	/**
	 * Default constructor.
	 */
	public ReportsDAO() {
		// TODO Auto-generated constructor stub
		super(ReportsORM.class);
	}

	public List<ReportsORM> listReports(UmCompany company) {
		List<ReportsORM> reports =  em.createQuery("from ReportsORM where company = :company",ReportsORM.class).
    			setParameter("company", company).getResultList();
		return reports;
	}

	public ReportsORM reportDetails(int reportId) {
        ReportsORM report = null;
        report = find(reportId);
        report = new ReportsORM(reportId, report.getReportTitle(),
                        report.getReportDescription(), report.getReportSummary(),
                        report.getReportColumn(), report.getReportColumnsTitles(),
                        report.getReportFrom(), report.getReportWhere(),
                        report.getReportOrderBy(), report.getReportType(),
                        report.getReportTypeColumns(), report.getReportBy(),report.getReportGroupBy(),report.getUser(),report.getCompany());
        return report;
	}

	public Integer createReport(ReportsORM report) {
		save(report);
		return report.getReportId();
	}

	public void updateReport(ReportsORM report) {
		update(report);
	}

	public void deleteReport(int reportId) {
		delete(reportId);
	}

	public Set<EntityType<?>> listTables() {
		Metamodel metamodel = em.getMetamodel();
		Set<EntityType<?>> allEntities = metamodel.getEntities();
		return allEntities;
	}

	public Set<Attribute<? super UmUser, ?>> listTableCols() {
		// TODO temorarly commented by Abdul Kareem,
		// selTable class was missing on my end
		Metamodel metamodel = em.getMetamodel();
		EntityType<UmUser> type = metamodel.entity(UmUser.class);
		Set<Attribute<? super UmUser, ?>> cols = type.getAttributes();
		return cols;

		// return null;
	}

	public <T> List<T> findAllEntities(Class<T> entityClass) {
		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<T> criteria = builder.createQuery(entityClass);
		Root<T> entityRoot = criteria.from(entityClass);

		// criteria.multiselect(entityRoot.get(columnName1),
		// entityRoot.get(columnName2), entityRoot.get(columnName3),
		// entityRoot.get(columnName4), entityRoot.get(columnName5));
		criteria.select(entityRoot);

		return em.createQuery(criteria).getResultList();
	}

	public void deleteReport(long id) {
		delete(id);
	}

}
