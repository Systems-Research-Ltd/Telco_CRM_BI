package com.srpl.crm.ejb.request;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.srpl.crm.ejb.entity.DocumentORM;
import com.srpl.crm.ejb.exceptions.DocumentNotFoundException;
import com.srpl.um.ejb.entity.UmCompany;
import com.srpl.um.ejb.request.CompanyDAO;

/**
 * Session Bean implementation class DocumentsDAO
 */
@Stateless
@LocalBean
public class DocumentsDAO extends GenericDAO<DocumentORM> {

    /**
     * Default constructor. 
     */
	@EJB
	CompanyDAO companyDao;
	
    public DocumentsDAO() {
    	super(DocumentORM.class);
    }
    
    public List<DocumentORM> listDocuments(long companyId) throws DocumentNotFoundException {
    	UmCompany company = companyDao.companyDetails(companyId);
    	return listDocuments(company);
    }
    
    public List<DocumentORM> listDocuments(UmCompany company) throws DocumentNotFoundException {
    	List<DocumentORM> documents = em.createQuery("FROM DocumentORM WHERE company = :c", DocumentORM.class)
    			.setParameter("c", company).getResultList();
    	if(documents.size() == 0){
    		throw new DocumentNotFoundException("No Document Found");
    	}    	
        return documents;    	
    }
    
    public Long createDocument(DocumentORM document) {
    	save(document);    	
    	return document.getDocumentId();
    }
    
    public void updateDocument(DocumentORM document){
    	update(document);    	
    }    
    
    public void deleteDocument(Long documentId){ 
    	delete(documentId);
    }
    
    public DocumentORM documentDetails(Long documentId) throws DocumentNotFoundException {
    	DocumentORM document = null;
    	if (documentId == null) {
            throw new DocumentNotFoundException("Invalid Document Id");
        }    	
    	document = find(documentId);
    	return document;
    }
}