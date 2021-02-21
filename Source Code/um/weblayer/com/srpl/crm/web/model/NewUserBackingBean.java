package com.srpl.crm.web.model;

/**
 * @author Hammad Hassan Khan
 *
 */
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.bitguiders.util.jsf.JSFBeanInterface;
import com.bitguiders.util.jsf.JSFBeanSupport;

@ManagedBean(name="userB")
public class NewUserBackingBean extends JSFBeanSupport implements
JSFBeanInterface, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long userId;
	private boolean isFranchiseUser;
	private Timestamp userAddedon;
	private String userAddress;
	private Integer userCity;
	private Long userCompany;
	private Integer userCountry;
	@NotNull(message="Email address is required.")
	@Pattern(message="Enter Valid Email Address.", regexp="^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")
	private String userEmail;
	private String userFname;
	private String userJobtitle;
	@NotBlank(message="Name is Required.")
	private String userLname;
	@NotBlank(message="User Name is Required.")
	private String userName;
	@NotBlank(message="Password is Required.")
	private String userPassword;
	private String userPass;
	private String userPhone;
	private String userPicture;
	private Long userReportsto;
	private Integer userState;
	private Boolean userStatus;
	private String userZipcode;
	private String country;
	private String state;
	private String city;
	private String reportsTo;
	private String company;
	private Boolean isOnline = false;
	/**
	 * 
	 * Getters and Setters
	 */
	

	
	
	public void reset(){
		userId = null;
		isFranchiseUser = false;
		//userAddedon = "";
		userAddress = "";
		userCity = null;;
		userCompany = null;
		userCountry = 169;
		userEmail = "";
		userFname = "";
		userJobtitle = "";
		userLname = "";
		userName = "";
		userPassword = "";
		userPhone = "";
		userPicture = "";
		userReportsto = null;
		userState = 3021;
		userStatus = true;
		userZipcode = "";
		country = "";
		state = "";
		city = "";
		reportsTo = "";
		company = "";
		isOnline = false;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public boolean getIsFranchiseUser() {
		return isFranchiseUser;
	}

	public void setIsFranchiseUser(boolean isFranchiseUser) {
		this.isFranchiseUser = isFranchiseUser;
	}

	public Timestamp getUserAddedon() {
		return userAddedon;
	}

	public void setUserAddedon(Timestamp userAddedon) {
		this.userAddedon = userAddedon;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public Integer getUserCity() {
		return userCity;
	}

	public void setUserCity(Integer userCity) {
		this.userCity = userCity;
	}

	public Long getUserCompany() {
		return userCompany;
	}

	public void setUserCompany(Long userCompany) {
		this.userCompany = userCompany;
	}

	public Integer getUserCountry() {
		return userCountry;
	}

	public void setUserCountry(Integer userCountry) {
		this.userCountry = userCountry;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserFname() {
		return userFname;
	}

	public void setUserFname(String userFname) {
		this.userFname = userFname;
	}

	public String getUserJobtitle() {
		return userJobtitle;
	}

	public void setUserJobtitle(String userJobtitle) {
		this.userJobtitle = userJobtitle;
	}

	public String getUserLname() {
		return userLname;
	}

	public void setUserLname(String userLname) {
		this.userLname = userLname;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserPicture() {
		return userPicture;
	}

	public void setUserPicture(String userPicture) {
		this.userPicture = userPicture;
	}

	public Long getUserReportsto() {
		return userReportsto;
	}

	public void setUserReportsto(Long userReportsto) {
		this.userReportsto = userReportsto;
	}

	public Integer getUserState() {
		return userState;
	}

	public void setUserState(Integer userState) {
		this.userState = userState;
	}

	public Boolean getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Boolean userStatus) {
		this.userStatus = userStatus;
	}

	public String getUserZipcode() {
		return userZipcode;
	}

	public void setUserZipcode(String userZipcode) {
		this.userZipcode = userZipcode;
	}
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public String getReportsTo() {
		return reportsTo;
	}

	public void setReportsTo(String reportsTo) {
		this.reportsTo = reportsTo;
	}
	
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Boolean getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(Boolean isOnline) {
		this.isOnline = isOnline;
	}

	@Override
	public String actionListener() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> getList() {
		// TODO Auto-generated method stub
		return null;
	}
}
