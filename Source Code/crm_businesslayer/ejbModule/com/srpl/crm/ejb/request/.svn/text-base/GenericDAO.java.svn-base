package com.srpl.crm.ejb.request;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 * Session Bean implementation class GenericDAO
 * @param <T>
 */
@Stateless
@LocalBean
public class GenericDAO<T> {

	private final static String UNIT_NAME = "businesslayer";
	 
    @PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;
 
    private Class<T> entityClass;
    
    public GenericDAO(){
    	
    }
 
    public GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
 
    protected void save(T entity) {
        em.persist(entity);
    }
 
    protected void delete(Long entityID) {
        T entityToBeRemoved = em.find(entityClass, entityID);
        em.remove(entityToBeRemoved);
    }
    
    protected void delete(Integer entityID) {
        T entityToBeRemoved = em.find(entityClass, entityID);
        em.remove(entityToBeRemoved);
    }
 
    protected void update(T entity) {
    	em.merge(entity);
    }
 
    protected T find(Long entityID) {
        return em.find(entityClass, entityID);
    }
 
    protected T find(Integer entityID) {
        return em.find(entityClass, entityID);
    }
    
    @SuppressWarnings("unchecked")
    protected List<T> findAll() {
        CriteriaQuery<T> cq = (CriteriaQuery<T>) em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return em.createQuery(cq).getResultList();
    }
 
    @SuppressWarnings("unchecked")
    protected T findOneResult(String namedQuery, Map<String, Object> parameters) {
        T result = null;
 
        try {
            Query query = em.createNamedQuery(namedQuery);
 
            // Method that will populate parameters if they are passed not null and empty
            if (parameters != null && !parameters.isEmpty()) {
                populateQueryParameters(query, parameters);
            }
 
            result = (T) query.getSingleResult();
 
        } catch (Exception e) {
            System.out.println("Error while running query: " + e.getMessage());
            e.printStackTrace();
        }
 
        return result;
    }
 
    private void populateQueryParameters(Query query, Map<String, Object> parameters) {
 
        for (Entry<String, Object> entry : parameters.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
    }
 }
