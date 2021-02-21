package com.srpl.um.ejb.request;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.srpl.um.ejb.entity.UmCompany;
import com.srpl.um.ejb.entity.UmParameter;

/**
 * Session Bean implementation class ParameterDAO
 */
@Stateless
@LocalBean
public class ParameterDAO extends GenericDAO<UmParameter> {
	@EJB
	CompanyDAO companyDao;

	/**
	 * Default constructor.
	 */
	public ParameterDAO() {
		super(UmParameter.class);
	}

	// ======================== List Parameters =========================//
	public List<UmParameter> list(Long companyId) {
		List<UmParameter> parameters = null;
		parameters = em.createQuery("SELECT p FROM UmParameter p where p.companyId = :companyId",UmParameter.class).setParameter("companyId", companyId).getResultList();
		return parameters;
	}

	// ======================== Create Parameters =========================//
	public void create(UmParameter details) {
		System.out.println("create parameter called");
		List<UmCompany> companyList = null;
		UmParameter newP = null;
		companyList = companyDao.listCompanies();
		
		for(UmCompany x:companyList){
			newP = new UmParameter(details.getParameterTitle(), details.getParameterValue(),x.getCompanyId());
			save(newP);
		}
	}

	// ============================ Details Parameter =============================//
	public UmParameter details(int id){
		UmParameter details = null;
		details = find(id);
		return details;
	}
	// ============================ Update Parameter =============================//

	public int updates(UmParameter details) {
		update(details);
		return details.getParameterId();
	}
	/*public int updates(UmParameter details) {
		List<UmCompany> companyList = null;
		//UmParameter details = null;
		companyList = companyDao.listCompanies();

		for(UmCompany x:companyList){
			details = new UmParameter(details.getParameterTitle(), details.getParameterValue(),x.getCompanyId());
			update(details);
		}
		
		//update(details);
		return details.getParameterId();
	}*/
	// ============ Delete Parameter ==========//

	public void delete(int parameterId) {
		super.delete(parameterId);
	}
	
	public String paramCompany(long companyId){
		UmCompany comp = em.find(UmCompany.class, companyId);
		return comp.getCompanyName();
	}

}
