package com.srpl.crm.ejb.entity;

import java.io.Serializable;
//import java.sql.Date;
import java.sql.Timestamp;
import java.util.Date;

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

@Entity
@Table(name = "orders_invoice_settings")
public class InvoiceSettingsORM implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Default constructor. 
     */
    public InvoiceSettingsORM() {
        // TODO Auto-generated constructor stub
    }

    
   @Id
   @SequenceGenerator(name="ORDERS_INVOICE_SETTINGID_GENERATOR", sequenceName="orders_invoice_settings_invoice_setting_id_seq", allocationSize = 1)
   @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ORDERS_INVOICE_SETTINGID_GENERATOR")
   @Column(name = "invoice_setting_id") 
   private Long invoiceSettingId;
   @ManyToOne
   @JoinColumn(name = "invoice_setting_customer")
   private CsContactORM invoiceSettingCustomer;
   @Column(name = "invoice_setting_date")
   private Date invoiceSettingDate;
   @Column(name = "invoice_day")
   private Integer invoiceDay;
   @Column(name = "invoice_setting_due_days")
   private Integer invoiceSettingDueDays;
   @Column(name = "invoice_setting_late_payment_fee")
   private Double invoiceSettingLatePaymentFee;
   @Column(name = "invoice_setting_created_on")
   private Timestamp invoiceSettingCreatedDate;
   @Column(name = "invoice_setting_changed_on")
   private Timestamp invoiceSettingChangeDate;
   @ManyToOne
   @JoinColumn(name = "invoice_setting_company")
   private UmCompany invoiceSettingCompany;
   
   @Deprecated
   public InvoiceSettingsORM(CsContactORM invoiceSettingCustomer, Date invoiceSettingDate, Integer invoiceSettingDueDays, Double invoiceSettingLatePaymentFee, Timestamp invoiceSettingCreatedDate, Timestamp invoiceSettingChangeDate, UmCompany invoiceSettingCompany){
         this.invoiceSettingCustomer = invoiceSettingCustomer;
         this.invoiceSettingDate = invoiceSettingDate;
         this.invoiceSettingDueDays = invoiceSettingDueDays;
         this.invoiceSettingLatePaymentFee = invoiceSettingLatePaymentFee;
         this.invoiceSettingCreatedDate = invoiceSettingCreatedDate;
         this.invoiceSettingChangeDate = invoiceSettingChangeDate;
         this.invoiceSettingCompany = invoiceSettingCompany;
   }
   public InvoiceSettingsORM(CsContactORM invoiceSettingCustomer, Date invoiceSettingDate, Integer invoiceDay, Integer invoiceSettingDueDays, Double invoiceSettingLatePaymentFee, Timestamp invoiceSettingCreatedDate, Timestamp invoiceSettingChangeDate, UmCompany invoiceSettingCompany){
       this.invoiceSettingCustomer = invoiceSettingCustomer;
       this.invoiceSettingDate = invoiceSettingDate;
       this.invoiceDay = invoiceDay;
       this.invoiceSettingDueDays = invoiceSettingDueDays;
       this.invoiceSettingLatePaymentFee = invoiceSettingLatePaymentFee;
       this.invoiceSettingCreatedDate = invoiceSettingCreatedDate;
       this.invoiceSettingChangeDate = invoiceSettingChangeDate;
       this.invoiceSettingCompany = invoiceSettingCompany;
   }
   
   @Deprecated
   public InvoiceSettingsORM(Long invoiceSettingId, CsContactORM invoiceSettingCustomer, Date invoiceSettingDate, Integer invoiceSettingDueDays, Double invoiceSettingLatePaymentFee, Timestamp invoiceSettingCreatedDate, Timestamp invoiceSettingChangeDate, UmCompany invoiceSettingCompany){
       this.invoiceSettingId = invoiceSettingId;
	   this.invoiceSettingCustomer = invoiceSettingCustomer;
       this.invoiceSettingDate = invoiceSettingDate;
       this.invoiceSettingDueDays = invoiceSettingDueDays;
       this.invoiceSettingLatePaymentFee = invoiceSettingLatePaymentFee;
       this.invoiceSettingCreatedDate = invoiceSettingCreatedDate;
       this.invoiceSettingChangeDate = invoiceSettingChangeDate;
       this.invoiceSettingCompany = invoiceSettingCompany;
   }
   public InvoiceSettingsORM(Long invoiceSettingId, CsContactORM invoiceSettingCustomer, Date invoiceSettingDate, Integer invoiceDay, Integer invoiceSettingDueDays, Double invoiceSettingLatePaymentFee, Timestamp invoiceSettingCreatedDate, Timestamp invoiceSettingChangeDate, UmCompany invoiceSettingCompany){
       this.invoiceSettingId = invoiceSettingId;
	   this.invoiceSettingCustomer = invoiceSettingCustomer;
       this.invoiceSettingDate = invoiceSettingDate;
       this.invoiceDay = invoiceDay;
       this.invoiceSettingDueDays = invoiceSettingDueDays;
       this.invoiceSettingLatePaymentFee = invoiceSettingLatePaymentFee;
       this.invoiceSettingCreatedDate = invoiceSettingCreatedDate;
       this.invoiceSettingChangeDate = invoiceSettingChangeDate;
       this.invoiceSettingCompany = invoiceSettingCompany;
   }
   
   
    public Long getInvoiceSettingId() {
	    return invoiceSettingId; 
    }

	public void setInvoiceSettingId(Long invoiceSettingId) {
		this.invoiceSettingId = invoiceSettingId;
	}

	public CsContactORM getCustomer() {
		return invoiceSettingCustomer;
	}

	public void setCustomer(CsContactORM invoiceSettingCustomer) {
		this.invoiceSettingCustomer = invoiceSettingCustomer;
	}

	public Date getInvoiceSettingDate() {
		return invoiceSettingDate;
	}

	public void setInvoiceSettingDate(Date invoiceSettingDate) {
		this.invoiceSettingDate = invoiceSettingDate;
	}

	public Integer getInvoiceSettingDueDays() {
		return invoiceSettingDueDays;
	}

	public Integer getInvoiceDay() {
		return invoiceDay;
	}

	public void setInvoiceDay(Integer invoiceDay) {
		this.invoiceDay = invoiceDay;
	}

	public void setInvoiceSettingDueDays(Integer invoiceSettingDueDays) {
		this.invoiceSettingDueDays = invoiceSettingDueDays;
	}

	public Double getInvoiceSettingLatePaymentFee() {
		return invoiceSettingLatePaymentFee;
	}

	public void setInvoiceSettingLatePaymentFee(Double invoiceSettingLatePaymentFee) {
		this.invoiceSettingLatePaymentFee = invoiceSettingLatePaymentFee;
	}

	public Timestamp getInvoiceSettingCreatedDate() {
		return invoiceSettingCreatedDate;
	}

	public void setInvoiceSettingCreatedDate(Timestamp invoiceSettingCreatedDate) {
		this.invoiceSettingCreatedDate = invoiceSettingCreatedDate;
	}

	public Timestamp getInvoiceSettingChangeDate() {
		return invoiceSettingChangeDate;
	}

	public void setInvoiceSettingChangeDate(Timestamp invoiceSettingChangeDate) {
		this.invoiceSettingChangeDate = invoiceSettingChangeDate;
	}

	public UmCompany getInvoiceSettingCompany() {
		return invoiceSettingCompany;
	}

	public void setInvoiceSettingCompany(UmCompany invoiceSettingCompany) {
		this.invoiceSettingCompany = invoiceSettingCompany;
	}    
    
}
