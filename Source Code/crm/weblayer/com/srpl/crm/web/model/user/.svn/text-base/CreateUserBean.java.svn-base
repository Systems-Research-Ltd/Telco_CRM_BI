package com.srpl.crm.web.model.user;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.srpl.crm.web.controller.BeanFactory;
import com.srpl.crm.common.utils.UmFranchiseDetails;
import com.srpl.crm.common.utils.UmUserDetails;
import com.srpl.um.ejb.request.FranchiseDAO;
import com.srpl.um.ejb.request.UserDAO;
import com.srpl.crm.ejb.request.UtilsDAO;
import com.srpl.um.ejb.entity.UmFranchise;
import com.srpl.um.ejb.entity.UmUser;

@ManagedBean(name = "createUserBean")
@RequestScoped
@Deprecated
public class CreateUserBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	java.util.Date date = new java.util.Date();
	private Long userId;
	private Boolean isFranchiseUser;
	private Timestamp userAddedon = new Timestamp(date.getTime());
	private String userAddress;
	@NotNull(message="Country is required.")
	@Min(value=1, message="Country is required.")
	private int userCountry = 0;
	private int userState = 0;
	@NotNull(message="City is required.")
	@Min(value=1, message="City is required.")
	private int userCity = 0;
	private Long userCompany;
    private String userEmail;
	private String userFname;
	private String userJobtitle;
	private String userLname;
	private String userName;
	private String userPassword;
	private String userConPassword;
	@NotBlank(message="Contact Number is Required.")
	@Pattern(message="The valid pattern for contact number is 012-345-6789, 0123-456-7890, 111-222-333", regexp="\\d{4}-\\d{3}-\\d{4}||\\d{3}-\\d{3}-\\d{4}||\\d{3}-\\d{3}-\\d{3}")
	private String userPhone;
	private String userPicture;
	@NotNull(message="Reports To information is required.")
	@Min(value=1, message="Reports To information is required.")
	private Long userReportsto;
	private Boolean isLoggedIn; 
	//private String keyword;
	
	
	

	private Boolean userStatus = true;
	private String userZipcode;
	private long sr;
	private String signInTime;
	private String signOutTime;
	private String sessionTime;
	private String selectedFranchise;
	private Boolean userType=true;
	private long franchiseId;
	//private ArrayList<SelectItem> franchiseList = null;

	private String selectedCountry;
	private String selectedState;
	private String selectedCity;
	private String selecteduser;	
	

	private List<UmFranchiseDetails>franchiseList;

	// @ManagedProperty(value="#{param.userId}")
	// private String paramtest

	@EJB
	UserDAO userDao;
	@EJB FranchiseDAO franchiseDao;
	@EJB
	UtilsDAO utils;

	public CreateUserBean() {

		
	}
	
	public String getSelecteduser() {
		//System.out.println("Hello "+this.userReportsto);
		//UmUserDetails usr=userDao.userDetails(this.userReportsto);
	//	UmUserDetails usr=userDao.userDetails(this.userId);
		String retVal;
		try{
			UmUser usr = userDao.umUserDetails(this.userReportsto);
			retVal = usr.getUserName();
			
			System.out.println("For check---------");
			
			System.out.println(usr.getUserName());
			System.out.println(usr.getUserEmail());
			System.out.println(usr.getIsOnline());
			
			System.out.println("For check---------");
		}catch (Exception e) {
			System.out.println("exception in createUserBean->getSelectedUser");
			retVal = "not found";
		}
		
		return retVal;
		
	}

	
	
	public void setSelecteduser(String selecteduser) {
		this.selecteduser = selecteduser;
	}

	public String getSelectedCountry() {
		return this.selectedCountry;
	}

	public void setSelectedCountry(String selectedCountry) {
		this.selectedCountry = selectedCountry;
	}

	public String getSelectedFranchise() {
		System.out.println("getSelectedFranchise() called");
		//Long franchise=Long.parseLong(selectedFranchise);
		
		
		UmFranchise userFranchise=franchiseDao.details(this.franchiseId);
		System.out.println("franchise id is" + franchiseId);
		return userFranchise.getFranchiseTitle();
	}

	public void setSelectedFranchise(String selectedFranchise) {
		this.selectedFranchise = selectedFranchise;
	}

	/*public ArrayList<SelectItem> getFranchiseList() {
		franchiseList = new ArrayList<SelectItem>();
		franchiseList.add(new SelectItem("0", "G-9 Markez ISB"));
		franchiseList.add(new SelectItem("1", "F-10 Markez ISB"));
		return franchiseList;
	}

	public void setFranchiseList(ArrayList<SelectItem> franchiseList) {
		this.franchiseList = franchiseList;
	}*/

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Boolean getIsFranchiseUser() {
		return isFranchiseUser;
	}

	public void setIsFranchiseUser(Boolean isFranchiseUser) {
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

	public int getUserCity() {
		return userCity;
	}

	public void setUserCity(int userCity) {
		this.userCity = userCity;
	}

	public Long getUserCompany() {
		return userCompany;
	}

	public void setUserCompany(Long userCompany) {
		this.userCompany = userCompany;
	}

	public int getUserCountry() {
		return userCountry;
	}

	public void setUserCountry(int userCountry) {
		this.userCountry = userCountry;
		this.userState = 0;
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

	public int getUserState() {
		return userState;
	}

	public void setUserState(int userState) {
		this.userState = userState;
		this.userCity = 0;
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

	public long getSr() {
		return sr;
	}

	public void setSr(long sr) {
		this.sr = sr;
	}

	public String getSignInTime() {
		return signInTime;
	}

	public void setSignInTime(String signInTime) {
		this.signInTime = signInTime;
	}

	public String getSignOutTime() {
		return signOutTime;
	}

	public void setSignOutTime(String signOutTime) {
		this.signOutTime = signOutTime;
	}

	public String getSessionTime() {
		return sessionTime;
	}

	public void setSessionTime(String sessionTime) {
		this.sessionTime = sessionTime;
	}

	public String getSelectedState() {
		return this.selectedState;
	}

	public void setSelectedState(String selectedState) {
		this.selectedState = selectedState;
	}

	public String getSelectedCity() {
		return this.selectedCity;
	}

	public void setSelectedCity(String selectedCity) {
		this.selectedCity = selectedCity;
	}

	

	public long getFranchiseId() {
		return franchiseId;
	}

	public void setFranchiseId(long franchiseId) {
		this.franchiseId = franchiseId;
	}

	/*public void deleteUser() {
		System.out.println(getUserId() + "deleteUser() called");
//		try {
			userDao.deleteUser(this.getUserId());
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Sample error message", "PrimeFaces makes no mistakes"));
			e.printStackTrace();			
		}
	}*/

	public Boolean getUserType() {
		return userType;
	}

	public void setUserType(Boolean userType) {
		this.userType = userType;
	}

	public void franchiseUser(){
		franchiseList=new ArrayList<UmFranchiseDetails>();
	}
		
		
	public List<UmFranchiseDetails> getFranchiseList() {
		return franchiseList;
	}

	public void setFranchiseList(List<UmFranchiseDetails> franchiseList) {
		this.franchiseList = franchiseList;
	}

	public String getUserConPassword() {
		return userConPassword;
	}

	public void setUserConPassword(String userConPassword) {
		this.userConPassword = userConPassword;
	}

	public Boolean getIsLoggedIn() {
		return isLoggedIn;
	}

	public void setIsLoggedIn(Boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	
}
