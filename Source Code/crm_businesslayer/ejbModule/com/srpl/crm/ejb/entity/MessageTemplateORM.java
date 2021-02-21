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

@Entity
@Table(name="message_template")
public class MessageTemplateORM implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name="MESSAGE_TEMPLATE_TEMPLATE_ID_GENERATOR", sequenceName="MESSAGE_TEMPLATE_TEMPLATE_ID_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MESSAGE_TEMPLATE_TEMPLATE_ID_GENERATOR")
	@Column(name="template_id")
	private Long templateId;
	
	@Column(name="title")
	private String title;
	
	@Column(name="message")
	private String message;
	
	@Column(name="send_to")
	private String sendTo;
	
	@Column(name="company_id")
	private Long companyId;
	
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private CsContactORM customer;

	public MessageTemplateORM(){
		
	}
	public MessageTemplateORM(Long templateId,String title,String message,String sendTo,CsContactORM customer,Long company_id){
		this.templateId = templateId;
		this.title = title;
		this.message = message;
		this.sendTo = sendTo;
		this.customer = customer;
		this.companyId = company_id;
		
	}
	
	public MessageTemplateORM(String title,String message,String sendTo,CsContactORM customer,Long company_id){
		this.title = title;
		this.message = message;
		this.sendTo = sendTo;
		this.customer = customer;
		this.companyId = company_id;
		
	}
	
	public MessageTemplateORM(String title,String message,String sendTo){
		this.title = title;
		this.message = message;
		this.sendTo = sendTo;
		
	}
	//getters & setters
	public Long getTemplateId() {
		return templateId;
	}

	public String getTitle() {
		return title;
	}

	public String getMessage() {
		return message;
	}

	public String getSendTo() {
		return sendTo;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}
	public CsContactORM getCustomer() {
		return customer;
	}
	public void setCustomer(CsContactORM customer) {
		this.customer = customer;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

}
