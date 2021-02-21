package com.srpl.crm.ejb.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.srpl.um.ejb.entity.UmCompany;


/**
 * The persistent class for the documents database table.
 * 
 */
@Entity
@Table(name="documents")
public class DocumentORM implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="DOCUMENTS_DOCUMENTID_GENERATOR", sequenceName="DOCUMENTS_DOCUMENT_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DOCUMENTS_DOCUMENTID_GENERATOR")
	@Column(name="document_id")
	private Long documentId;

	@Column(name="document_addedby")
	private Long documentAddedby;

	@Column(name="document_addedon")
	private Timestamp documentAddedon;

	@Column(name="document_details")
	private String documentDetails;

	@Column(name="document_edate")
	private Timestamp documentEdate;

	@Column(name="document_pdate")
	private Timestamp documentPdate;

	@Column(name="document_status")
	private Boolean documentStatus;

	@Column(name="document_title")
	private String documentTitle;
	
	@Column(name="document_source")
	private String documentSource;

	@ManyToOne
	@JoinColumn(name = "company_id")
	private UmCompany company;

    public DocumentORM() {
    }

    public DocumentORM(String title) {
    	this.documentId = 0L;
    	this.documentTitle = title;
    }
    
    public DocumentORM(Long documentId, String documentTitle, String documentDetails, Timestamp documentEdate, Timestamp documentPdate, String documentSource, Long documentAddedby, Timestamp documentAddedon, Boolean documentStatus, UmCompany company){
    	this.documentId			= documentId;
    	this.documentTitle		= documentTitle;
    	this.documentDetails	= documentDetails;
    	this.documentEdate		= documentEdate;
    	this.documentPdate		= documentPdate;
    	this.documentSource		= documentSource;
    	this.documentAddedby	= documentAddedby;
    	this.documentAddedon	= documentAddedon;
    	this.documentStatus		= documentStatus;
    	this.company			= company;
	}
    
    public DocumentORM(String documentTitle, String documentDetails, Timestamp documentEdate, Timestamp documentPdate, String documentSource, Long documentAddedby, Timestamp documentAddedon, Boolean documentStatus, UmCompany company){
    	this.documentTitle		= documentTitle;
    	this.documentDetails	= documentDetails;
    	this.documentEdate		= documentEdate;
    	this.documentPdate		= documentPdate;
    	this.documentSource		= documentSource;
    	this.documentAddedby	= documentAddedby;
    	this.documentAddedon	= documentAddedon;
    	this.documentStatus		= documentStatus;
    	this.company			= company;
	}

	public Long getDocumentAddedby() {
		return this.documentAddedby;
	}

	public void setDocumentAddedby(Long documentAddedby) {
		this.documentAddedby = documentAddedby;
	}

	public Timestamp getDocumentAddedon() {
		return this.documentAddedon;
	}

	public void setDocumentAddedon(Timestamp documentAddedon) {
		this.documentAddedon = documentAddedon;
	}

	public String getDocumentDetails() {
		return this.documentDetails;
	}

	public void setDocumentDetails(String documentDetails) {
		this.documentDetails = documentDetails;
	}

	public Timestamp getDocumentEdate() {
		return this.documentEdate;
	}

	public void setDocumentEdate(Timestamp documentEdate) {
		this.documentEdate = documentEdate;
	}

	public Long getDocumentId() {
		return this.documentId;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

	public Timestamp getDocumentPdate() {
		return this.documentPdate;
	}

	public void setDocumentPdate(Timestamp documentPdate) {
		this.documentPdate = documentPdate;
	}

	public Boolean getDocumentStatus() {
		return this.documentStatus;
	}

	public void setDocumentStatus(Boolean documentStatus) {
		this.documentStatus = documentStatus;
	}

	public String getDocumentTitle() {
		return this.documentTitle;
	}

	public void setDocumentTitle(String documentTitle) {
		this.documentTitle = documentTitle;
	}

	public String getDocumentSource() {
		return documentSource;
	}

	public void setDocumentSource(String documentSource) {
		this.documentSource = documentSource;
	}

	public UmCompany getCompany() {
		return company;
	}

	public void setCompany(UmCompany company) {
		this.company = company;
	}	
}