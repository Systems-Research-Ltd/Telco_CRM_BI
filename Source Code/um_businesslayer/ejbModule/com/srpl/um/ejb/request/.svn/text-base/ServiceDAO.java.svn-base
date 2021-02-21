package com.srpl.um.ejb.request;

import java.util.Iterator;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.srpl.um.ejb.entity.GroupPermission;
import com.srpl.um.ejb.entity.UmGroup;
import com.srpl.um.ejb.entity.UmService;

/**
 * Session Bean implementation class OperationDAO
 */
@Stateless
@LocalBean
public class ServiceDAO extends GenericDAO<UmService> {

	/**
	 * Default constructor.
	 */
	public ServiceDAO() {
		super(UmService.class);
	}

	// ============ new listOperations ==================//
	public List<UmService> listServices() {
		List<UmService> operations = findAll();
		return operations;
	}

	// ============ end new listOperations ==================//

	// ============= new createOperation ==========//
	public Long createService(UmService service) {
		System.out.println(service.getServiceTitle() + "dao title"
				+ service.getServiceDescription() + "dao dec");
		List<UmGroup> groups = null;
		save(service);
		System.out.println(service.getServiceTitle() + "dao title"
				+ service.getServiceDescription() + "dao dec---");

		groups = em.createQuery("from UmGroup", UmGroup.class).getResultList();
		Iterator<UmGroup> i = groups.iterator();
		while (i.hasNext()) {
			UmGroup group = (UmGroup) i.next();
			GroupPermission perm = new GroupPermission(service, group, 0L);
			em.persist(perm);
		}
		return service.getServiceId();
	}

	// ============= end new createOperation =======//

	// ============== new update Service ===============//
	public void updateService(UmService service) {
		update(service);
	}

	// ============== end new update Service ===============//

	public void deleteService(int serviceId) {
		List<GroupPermission> delperm = null;
		UmService opr = em.find(UmService.class, serviceId);
		delperm = em
				.createQuery(
						"SELECT g FROM GroupPermission g where g.permissionService = :opr",
						GroupPermission.class).setParameter("opr", opr)
				.getResultList();
		for (GroupPermission y : delperm) {
			em.remove(y);
		}
		delete(serviceId);
	}
	public void deleteService1(Long serviceId) {
		List<GroupPermission> delperm = null;
		UmService opr = em.find(UmService.class, serviceId);
		delperm = em
				.createQuery(
						"SELECT g FROM GroupPermission g where g.permissionService = :opr",
						GroupPermission.class).setParameter("opr", opr)
				.getResultList();
		for (GroupPermission y : delperm) {
			em.remove(y);
		}
		delete(serviceId);
	}

	public UmService serviceDetails(Integer serviceId) {
		UmService service = find(serviceId);
		return service;
	}

	public UmService serviceDetails1(Long serviceId) {
		UmService service = find(serviceId);
		return service;
	}
	// ================== Update newGrouoOperation ===========================//
	public Long updates(UmService s) {
		UmService service = find(s.getServiceId());
		service.setServiceTitle(s.getServiceTitle());
		service.setServiceDescription(s.getServiceDescription());
		update(service);
		return service.getServiceId();
	}

}
