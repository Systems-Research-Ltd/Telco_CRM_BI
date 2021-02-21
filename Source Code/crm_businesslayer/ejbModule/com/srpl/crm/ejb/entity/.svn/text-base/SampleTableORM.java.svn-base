package com.srpl.crm.ejb.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;


/**
 * The persistent class for the s_packages database table.
 * 
 */
@Entity
@Table(name="sampletable")
public class SampleTableORM implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SAMPLETABLE_ID_GENERATOR", sequenceName="SAMPLETABLE_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SAMPLETABLE_ID_GENERATOR")
	@Column(name="id")
	private Long id;

	@Column(name="title")
	private String title;
	
	@Column(name="value")
	private String value;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
}