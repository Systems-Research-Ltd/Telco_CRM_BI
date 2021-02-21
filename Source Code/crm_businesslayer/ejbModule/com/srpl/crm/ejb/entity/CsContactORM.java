package com.srpl.crm.ejb.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.srpl.um.ejb.entity.UmUser;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * The persistent class for the cs_contacts database table.
 * 
 */
@Entity
@Table(name="cs_contacts")
public class CsContactORM implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="CS_CONTACTS_CONTACTID_GENERATOR", sequenceName="CS_CONTACTS_CONTACT_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CS_CONTACTS_CONTACTID_GENERATOR")
	@Column(name="contact_id")
	private Long contactId;
	
	@Column(name="company_id")
	private Long companyId;

	@Column(name="contact_address")
	private String contactAddress;

	@Column(name="contact_city")
	private Integer contactCity;

	@Column(name="contact_cnic")
	private String contactCnic;

	@Column(name="contact_cnic_copy")
	private String contactCnicCopy;

	@Column(name="contact_country")
	private Integer contactCountry;

	@Column(name="contact_createdon")
	private Timestamp contactCreatedon;

	@Column(name="contact_dob")
	private Timestamp contactDob;

	@Column(name="contact_email")
	private String contactEmail;

	@Column(name="contact_father_name")
	private String contactFatherName;

	@Column(name="contact_fname")
	private String contactFname;

	@Column(name="contact_lname")
	private String contactLname;

	@Column(name="contact_phone")
	private String contactPhone;

	@Column(name="contact_state")
	private Integer contactState;

	@Column(name="contact_status")
	private Boolean contactStatus;

	@Column(name="contact_zipcode")
	private String contactZipcode;
	
	@ManyToOne
	@JoinColumn(name = "created_by")
	private UmUser createdBy;
	
	@Column(name="mapped_id")
	private Long mappedId;

	//bi-directional many-to-one association to CsAccount its wrong
    @ManyToOne
	@JoinColumn(name="account_id")
	private CsAccount csAccount;
    
    /*
     * Relationship using Mkyong Style
     */
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contact_user_id")
    private UmUser contactUser;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "subscriber")
	private Set<SInvoiceORM> invoices = new HashSet<SInvoiceORM>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	private Set<OrderORM> orders = new HashSet<OrderORM>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "subscriber")
	private Set<SServiceSubscribeORM> subscription = new HashSet<SServiceSubscribeORM>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "subscriber")
	private Set<SServiceSubscriptionHistoryORM> sHistory = new HashSet<SServiceSubscriptionHistoryORM>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "subscriber")
	private Set<PaymentORM> payments = new HashSet<PaymentORM>(0);
	
    /*
     * ends here
     */
	
	@Transient
	private String isCorrect;
    
    public CsContactORM() {
    }
    
    public CsContactORM(String fname){
    	this.contactFname = fname;
    }
    
    public CsContactORM(Long contactId, String contactFname, String contactLname, String contactFatherName, String contactAddress, Integer contactCountry, Integer contactState, Integer contactCity, String contactZipcode, String contactEmail, String contactPhone, String contactCnic, String contactCnicCopy, Timestamp contactDob, Timestamp contactCreatedon, Boolean contactStatus){
    	this.contactId = contactId;
		this.contactFname = contactFname;
		this.contactLname = contactLname;
		this.contactFatherName = contactFatherName;
		this.contactAddress = contactAddress;
		this.contactCountry = contactCountry;
		this.contactState = contactState;
		this.contactCity = contactCity;
		this.contactZipcode = contactZipcode;
		this.contactEmail = contactEmail;
		this.contactPhone = contactPhone;
		this.contactCnic = contactCnic;
		this.contactCnicCopy = contactCnicCopy;
		this.contactDob = contactDob;
		this.contactCreatedon = contactCreatedon;
		this.contactStatus = contactStatus;
	}
    
    public CsContactORM(Long contactId, String contactFname, String contactLname, String contactFatherName, String contactAddress, Integer contactCountry, Integer contactState, Integer contactCity, String contactZipcode, String contactEmail, String contactPhone, String contactCnic, String contactCnicCopy, Timestamp contactDob, Timestamp contactCreatedon, Boolean contactStatus, Long companyId){
    	this(contactId, contactFname, contactLname, contactFatherName, contactAddress, contactCountry, contactState, contactCity, contactZipcode, contactEmail, contactPhone, contactCnic, contactCnicCopy, contactDob, contactCreatedon, contactStatus);
    	this.companyId = companyId;
    }
    
    public CsContactORM(Long contactId, String contactFname, String contactLname, String contactFatherName, String contactAddress, Integer contactCountry, Integer contactState, Integer contactCity, String contactZipcode, String contactEmail, String contactPhone, String contactCnic, String contactCnicCopy, Timestamp contactDob, Timestamp contactCreatedon, Boolean contactStatus, Long companyId, Long userId, String username, String pass){
    	this(contactId, contactFname, contactLname, contactFatherName, contactAddress, contactCountry, contactState, contactCity, contactZipcode, contactEmail, contactPhone, contactCnic, contactCnicCopy, contactDob, contactCreatedon, contactStatus, companyId);
    	UmUser user = new UmUser(userId, username, pass, contactFname,
    			contactLname, contactAddress, contactCountry,
    			contactState, contactCity, contactZipcode,
    			contactEmail, contactPhone, "Customer",
    			null, null, false,
    			companyId, contactCreatedon, true,
    			false,true);
    	this.setContactUser(user);
    	
    }
    
    public CsContactORM(String contactFname, String contactLname, String contactFatherName, String contactAddress, Integer contactCountry, Integer contactState, Integer contactCity, String contactZipcode, String contactEmail, String contactPhone, String contactCnic, String contactCnicCopy, Timestamp contactDob, Timestamp contactCreatedon, Boolean contactStatus){
		this.contactFname = contactFname;
		this.contactLname = contactLname;
		this.contactFatherName = contactFatherName;
		this.contactAddress = contactAddress;
		this.contactCountry = contactCountry;
		this.contactState = contactState;
		this.contactCity = contactCity;
		this.contactZipcode = contactZipcode;
		this.contactEmail = contactEmail;
		this.contactPhone = contactPhone;
		this.contactCnic = contactCnic;
		this.contactCnicCopy = contactCnicCopy;
		this.contactDob = contactDob;
		this.contactCreatedon = contactCreatedon;
		this.contactStatus = contactStatus;
	}	
    
    public CsContactORM(String contactFname, String contactLname, String contactFatherName, String contactAddress, Integer contactCountry, Integer contactState, Integer contactCity, String contactZipcode, String contactEmail, String contactPhone, String contactCnic, String contactCnicCopy, Timestamp contactDob, Timestamp contactCreatedon, Boolean contactStatus, Long companyId, UmUser createdBy){
    	this(contactFname, contactLname, contactFatherName, contactAddress, contactCountry, contactState, contactCity, contactZipcode, contactEmail, contactPhone, contactCnic, contactCnicCopy, contactDob, contactCreatedon, contactStatus);
    	this.companyId = companyId;
    	this.createdBy = createdBy;
    }
    
    public CsContactORM(String contactFname, String contactLname, String contactFatherName, String contactAddress, Integer contactCountry, Integer contactState, Integer contactCity, String contactZipcode, String contactEmail, String contactPhone, String contactCnic, String contactCnicCopy, Timestamp contactDob, Timestamp contactCreatedon, Boolean contactStatus, Long companyId, String username, String pass, UmUser createdBy){
    	this(contactFname, contactLname, contactFatherName, contactAddress, contactCountry, contactState, contactCity, contactZipcode, contactEmail, contactPhone, contactCnic, contactCnicCopy, contactDob, contactCreatedon, contactStatus, companyId, createdBy);
    	UmUser user = new UmUser(username, pass, contactFname,
    			contactLname, contactAddress, contactCountry,
    			contactState, contactCity, contactZipcode,
    			contactEmail, contactPhone, "Customer",
    			null, null, false,
    			companyId, contactCreatedon, true,
    			false,true);
    	this.setContactUser(user);
    	
    }

	public String getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(String isCorrect) {
		this.isCorrect = isCorrect;
	}

	public String getContactAddress() {
		return this.contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public Integer getContactCity() {
		return this.contactCity;
	}

	public void setContactCity(Integer contactCity) {
		this.contactCity = contactCity;
	}

	public String getContactCnic() {
		return this.contactCnic;
	}

	public void setContactCnic(String contactCnic) {
		this.contactCnic = contactCnic;
	}

	public String getContactCnicCopy() {
		return this.contactCnicCopy;
	}

	public void setContactCnicCopy(String contactCnicCopy) {
		this.contactCnicCopy = contactCnicCopy;
	}

	public Integer getContactCountry() {
		return this.contactCountry;
	}

	public void setContactCountry(Integer contactCountry) {
		this.contactCountry = contactCountry;
	}

	public Timestamp getContactCreatedon() {
		return this.contactCreatedon;
	}

	public void setContactCreatedon(Timestamp contactCreatedon) {
		this.contactCreatedon = contactCreatedon;
	}

	public Timestamp getContactDob() {
		return this.contactDob;
	}

	public void setContactDob(Timestamp contactDob) {
		this.contactDob = contactDob;
	}

	public String getContactEmail() {
		return this.contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getContactFatherName() {
		return this.contactFatherName;
	}

	public void setContactFatherName(String contactFatherName) {
		this.contactFatherName = contactFatherName;
	}

	public String getContactFname() {
		return this.contactFname;
	}

	public void setContactFname(String contactFname) {
		this.contactFname = contactFname;
	}

	public Long getContactId() {
		return this.contactId;
	}

	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getContactLname() {
		return this.contactLname;
	}

	public void setContactLname(String contactLname) {
		this.contactLname = contactLname;
	}

	public String getContactPhone() {
		return this.contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public Integer getContactState() {
		return this.contactState;
	}

	public void setContactState(Integer contactState) {
		this.contactState = contactState;
	}

	public Boolean getContactStatus() {
		return this.contactStatus;
	}

	public void setContactStatus(Boolean contactStatus) {
		this.contactStatus = contactStatus;
	}

	public String getContactZipcode() {
		return this.contactZipcode;
	}

	public void setContactZipcode(String contactZipcode) {
		this.contactZipcode = contactZipcode;
	}

	public UmUser getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UmUser createdBy) {
		this.createdBy = createdBy;
	}

	public CsAccount getCsAccount() {
		return this.csAccount;
	}

	public void setCsAccount(CsAccount csAccount) {
		this.csAccount = csAccount;
	}

	public Set<SInvoiceORM> getInvoices() {
		return invoices;
	}

	public void setInvoices(Set<SInvoiceORM> invoices) {
		this.invoices = invoices;
	}

	public Set<OrderORM> getOrders() {
		return orders;
	}

	public void setOrders(Set<OrderORM> orders) {
		this.orders = orders;
	}

	public Set<SServiceSubscribeORM> getSubscription() {
		return subscription;
	}

	public void setSubscription(Set<SServiceSubscribeORM> subscription) {
		this.subscription = subscription;
	}
	
	public void setSubscription(List<SServiceSubscribeORM> subscription){
		this.subscription = new HashSet<SServiceSubscribeORM>(subscription);
	}

	public Set<SServiceSubscriptionHistoryORM> getsHistory() {
		return sHistory;
	}

	public void setsHistory(Set<SServiceSubscriptionHistoryORM> sHistory) {
		this.sHistory = sHistory;
	}

	public UmUser getContactUser() {
		return contactUser;
	}

	public void setContactUser(UmUser contactUser) {
		this.contactUser = contactUser;
	}

	public Set<PaymentORM> getPayments() {
		return payments;
	}

	public void setPayments(Set<PaymentORM> payments) {
		this.payments = payments;
	}	
	
	public Long getMappedId() {
		return mappedId;
	}

	public void setMappedId(Long mappedId) {
		this.mappedId = mappedId;
	}

	@Override
	public String toString(){
		return this.contactId.toString();
	}
	
}