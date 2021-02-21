package com.srpl.crm.ejb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.srpl.um.ejb.entity.UmCompany;

@Entity
@Table(name="customer_notes")
public class CustomerNotesORM implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CUSTOMER_NOTES_ID_GENERATOR", sequenceName="CUSTOMER_PAYMENT_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CUSTOMER_NOTES_ID_GENERATOR")
	@Column(name="id")
	private Long id;
	
	@Column(name="note")
	private String notes;
	
	@Transient
	private String excerpt;
	
	@ManyToOne
	@JoinColumn(name="company_id")
	private UmCompany company;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private CsContactORM customer;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getExcerpt() {
		excerpt = (notes.length() > 30) ? notes.substring(0, 30)+"...":notes;
		return excerpt;
	}
	public void setExcerpt(String excerpt) {
		this.excerpt = excerpt;
	}
	public UmCompany getCompany() {
		return company;
	}
	public void setCompany(UmCompany company) {
		this.company = company;
	}
	public CsContactORM getCustomer() {
		return customer;
	}
	public void setCustomer(CsContactORM customer) {
		this.customer = customer;
	}
	
	

}
