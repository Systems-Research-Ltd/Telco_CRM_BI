package com.srpl.um.ejb.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.srpl.um.ejb.request.UmPersistence;

/**
 * The persistent class for the um_users database table.
 * 
 */
@Entity
@Table(name = "um_users", schema=UmPersistence.schema)
public class UmUser implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "UM_USERS_USERID_GENERATOR", sequenceName = "UM_USERS_USER_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UM_USERS_USERID_GENERATOR")
	@Column(name = "user_id")
	private Long userId;

	@Column(name = "is_franchise_user")
	private Boolean isFranchiseUser;

	@Column(name = "user_addedon")
	private Timestamp userAddedon;

	@Column(name = "user_address")
	private String userAddress;

	@Column(name = "user_city")
	private Integer userCity;

	@Column(name = "user_company")
	private Long userCompany;

	@Column(name = "user_country")
	private Integer userCountry;

	@Column(name = "user_email")
	private String userEmail;

	@Column(name = "user_fname")
	private String userFname;

	@Column(name = "user_jobtitle")
	private String userJobtitle;

	@Column(name = "user_lname")
	private String userLname;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "user_password")
	private String userPassword;

	@Column(name = "user_phone")
	private String userPhone;

	@Column(name = "user_picture")
	private String userPicture;

	@Column(name = "user_reportsto")
	private Long userReportsto;

	@Column(name = "user_state")
	private Integer userState;

	@Column(name = "user_status")
	private Boolean userStatus;

	@Column(name = "user_zipcode")
	private String userZipcode;

	@Column(name = " is_online")
	private Boolean isOnline = false;
	
	@Column(name = "is_user_customer")
	private Boolean isUserCustomer ;

	@Column(name = "mapped_id")
	private Long mappedId;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "honorific_title")
	private HONORIFIC_TITLE honorificTitle;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "gender")
	private GENDER gender;

	@Transient
	private String isCorrect;
	
	// bi-directional many-to-one association to UmUserGroup
	@OneToMany(mappedBy = "umUser", fetch = FetchType.EAGER)
	private Set<UmUserGroup> umUserGroups;

	// bi-directional many-to-one association to UmUserHistory
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<UmUserHistory> umUserHistories;
	
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "recievedBy")
//	private Set<OrdersBillreimburseDetails> orderreimburse = new HashSet<OrdersBillreimburseDetails>(0);

	// bi-directional many-to-many association to UmGroup
	/*
	 * @ManyToMany(cascade={CascadeType.PERSIST})
	 * 
	 * @JoinTable( name="um_user_groups" , joinColumns={
	 * 
	 * @JoinColumn(name="user_id") } , inverseJoinColumns={
	 * 
	 * @JoinColumn(name="group_id") } ) private List<UmGroup> umGroups;
	 */

	public UmUser() {
		System.out.println("Persistence schema is "+UmPersistence.schema);
	}

	public UmUser(Long userId, String userName, String userPassword,
			String userFname, String userLname, String userAddress,
			Integer userCountry, Integer userState, Integer userCity,
			String userZipcode, String userEmail, String userPhone,
			String userJobtitle, Long userReportsto, String userPicture,
			Boolean isFranchiseUser, Long userCompany, Timestamp userAddedon,
			Boolean userStatus, Boolean isOnline,Boolean isUserCustomer) {
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userFname = userFname;
		this.userLname = userLname;
		this.userAddress = userAddress;
		this.userCountry = userCountry;
		this.userState = userState;
		this.userCity = userCity;
		this.userZipcode = userZipcode;
		this.userEmail = userEmail;
		this.userPhone = userPhone;
		this.userJobtitle = userJobtitle;
		this.userReportsto = userReportsto;
		this.userPicture = userPicture;
		this.isFranchiseUser = isFranchiseUser;
		this.userCompany = userCompany;
		this.userAddedon = userAddedon;
		this.userStatus = userStatus;
		this.isOnline = isOnline;
		this.isUserCustomer = isUserCustomer;
		System.out.println("Persistence schema is "+UmPersistence.schema);
	}

	public UmUser(String userName, String userPassword, String userFname,
			String userLname, String userAddress, Integer userCountry,
			Integer userState, Integer userCity, String userZipcode,
			String userEmail, String userPhone, String userJobtitle,
			Long userReportsto, String userPicture, Boolean isFranchiseUser,
			Long userCompany, Timestamp userAddedon, Boolean userStatus,
			Boolean isOnline,Boolean isUserCustomer) {
		this.userName = userName;
		this.userPassword = userPassword;
		this.userFname = userFname;
		this.userLname = userLname;
		this.userAddress = userAddress;
		this.userCountry = userCountry;
		this.userState = userState;
		this.userCity = userCity;
		this.userZipcode = userZipcode;
		this.userEmail = userEmail;
		this.userPhone = userPhone;
		this.userJobtitle = userJobtitle;
		this.userReportsto = userReportsto;
		this.userPicture = userPicture;
		this.isFranchiseUser = isFranchiseUser;
		this.userCompany = userCompany;
		this.userAddedon = userAddedon;
		this.userStatus = userStatus;
		this.isOnline = isOnline;
		this.isUserCustomer = isUserCustomer;
		System.out.println("Persistence schema is "+UmPersistence.schema);
	}
	
	public UmUser(Long userId, String userFname,
			String userLname, String userAddress, Integer userCountry,
			Integer userState, Integer userCity, String userZipcode,
			String userEmail, String userPhone, String userJobtitle,
			Long userReportsto, String userPicture) {
		this.userId = userId;
		this.userFname = userFname;
		this.userLname = userLname;
		this.userAddress = userAddress;
		this.userCountry = userCountry;
		this.userState = userState;
		this.userCity = userCity;
		this.userZipcode = userZipcode;
		this.userEmail = userEmail;
		this.userPhone = userPhone;
		this.userJobtitle = userJobtitle;
		this.userReportsto = userReportsto;
		this.userPicture = userPicture;
		//System.out.println("Address in user entity is "+this.userAddress);
		System.out.println("Persistence schema is "+UmPersistence.schema);
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}	

	public String getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(String isCorrect) {
		this.isCorrect = isCorrect;
	}

	public Boolean getIsFranchiseUser() {
		return this.isFranchiseUser;
	}

	public void setIsFranchiseUser(Boolean isFranchiseUser) {
		this.isFranchiseUser = isFranchiseUser;
	}

	public Timestamp getUserAddedon() {
		return this.userAddedon;
	}

	public void setUserAddedon(Timestamp userAddedon) {
		this.userAddedon = userAddedon;
	}

	public String getUserAddress() {
		return this.userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public Integer getUserCity() {
		return this.userCity;
	}

	public void setUserCity(Integer userCity) {
		this.userCity = userCity;
	}

	public Long getUserCompany() {
		return this.userCompany;
	}

	public void setUserCompany(Long userCompany) {
		this.userCompany = userCompany;
	}

	public Integer getUserCountry() {
		return this.userCountry;
	}

	public void setUserCountry(Integer userCountry) {
		this.userCountry = userCountry;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserFname() {
		return this.userFname;
	}

	public void setUserFname(String userFname) {
		this.userFname = userFname;
	}

	public String getUserJobtitle() {
		return this.userJobtitle;
	}

	public void setUserJobtitle(String userJobtitle) {
		this.userJobtitle = userJobtitle;
	}

	public String getUserLname() {
		return this.userLname;
	}

	public void setUserLname(String userLname) {
		this.userLname = userLname;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserPhone() {
		return this.userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserPicture() {
		return this.userPicture;
	}

	public void setUserPicture(String userPicture) {
		this.userPicture = userPicture;
	}

	public Long getUserReportsto() {
		return this.userReportsto;
	}

	public void setUserReportsto(Long userReportsto) {
		this.userReportsto = userReportsto;
	}

	public Integer getUserState() {
		return this.userState;
	}

	public void setUserState(Integer userState) {
		this.userState = userState;
	}

	public Boolean getUserStatus() {
		return this.userStatus;
	}

	public void setUserStatus(Boolean userStatus) {
		this.userStatus = userStatus;
	}

	public String getUserZipcode() {
		return this.userZipcode;
	}

	public void setUserZipcode(String userZipcode) {
		this.userZipcode = userZipcode;
	}

	public Set<UmUserGroup> getUmUserGroups() {
		return this.umUserGroups;
	}

	public void setUmUserGroups(Set<UmUserGroup> umUserGroups) {
		this.umUserGroups = umUserGroups;
	}

	public List<UmUserHistory> getUmUserHistories() {
		return this.umUserHistories;
	}

	public void setUmUserHistories(List<UmUserHistory> umUserHistories) {
		this.umUserHistories = umUserHistories;
	}
	
	public Boolean getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(Boolean isOnline) {
		this.isOnline = isOnline;
	}

	public Boolean getIsUserCustomer() {
		return isUserCustomer;
	}

	public void setIsUserCustomer(Boolean isUserCustomer) {
		this.isUserCustomer = isUserCustomer;
	}

	public Long getMappedId() {
		return mappedId;
	}

	public void setMappedId(Long mappedId) {
		this.mappedId = mappedId;
	}

	public HONORIFIC_TITLE getHonorificTitle() {
		return honorificTitle;
	}

	public void setHonorificTitle(HONORIFIC_TITLE honorificTitle) {
		this.honorificTitle = honorificTitle;
	}

	public GENDER getGender() {
		return gender;
	}

	public void setGender(GENDER gender) {
		this.gender = gender;
	}	

//	public Set<OrdersBillreimburseDetails> getOrderreimburse() {
//		return orderreimburse;
//	}
//
//	public void setOrderreimburse(Set<OrdersBillreimburseDetails> orderreimburse) {
//		this.orderreimburse = orderreimburse;
//	}

	


	

	/*
	 * public List<UmGroup> getUmGroups() { return this.umGroups; }
	 * 
	 * public void setUmGroups(List<UmGroup> umGroups) { this.umGroups =
	 * umGroups; }
	 * 
	 * public void addGroup(UmGroup group) { try { this.umGroups.add(group); }
	 * catch (Exception ex) { throw new EJBException(ex); } }
	 * 
	 * public void removeGroup(UmGroup group) { try {
	 * this.umGroups.remove(group); } catch (Exception ex) { throw new
	 * EJBException(ex); } }
	 * 
	 * public void addUserHistory(UmUserHistory history) { try {
	 * this.umUserHistories.add(history); } catch (Exception ex) { throw new
	 * EJBException(ex); } }
	 * 
	 * public void removeUserHistory(UmUserHistory history) { try {
	 * this.umUserHistories.remove(history); } catch (Exception ex) { throw new
	 * EJBException(ex); } }
	 */
}