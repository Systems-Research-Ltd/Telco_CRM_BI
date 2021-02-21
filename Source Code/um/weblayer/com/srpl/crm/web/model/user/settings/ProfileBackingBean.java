package com.srpl.crm.web.model.user.settings;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.primefaces.event.FileUploadEvent;

import com.bitguiders.util.jsf.JSFBeanInterface;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;
import com.srpl.crm.web.model.common.Address;
import com.srpl.um.ejb.entity.UmUser;
import com.srpl.um.ejb.exceptions.UserNotFoundException;
import com.srpl.um.ejb.request.UserDAO;
import com.srpl.um.web.common.SessionDataBean;
import com.srpl.um.web.controller.BeanFactory;

@ManagedBean(name = "profile")
public class ProfileBackingBean extends JSFBeanSupport implements
		JSFBeanInterface, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long userId;
	private String userAddress;
	@NotNull(message = "User City is Required.")
	@Min(message = "User City is Required.", value = 1)
	private Integer userCity;
	@NotNull(message = "User Country is Required.")
	@Min(message = "User Country is Required.", value = 1)
	private Integer userCountry;
	private String userEmail;
	private String userFname;
	private String userJobtitle;
	private String userLname;
	private String userPhone;
	private String userPicture;
	private Long userReportsto;
	@NotNull(message = "User State is Required.")
	@Min(message = "User State is Required.", value = 1)
	private Integer userState;
	private String userZipcode;
	private boolean showProfilePic;
	private boolean isRequired = false;

	@EJB
	UserDAO userDao;

	public ProfileBackingBean() {
    //setCurrentAction(WebConstants.ACTION_SECURITY,this.getClass());
	}

	@PostConstruct
	public void init() {
		String action = getAction();
		if (action.equals("")) {
			viewProfile();
			setCurrentAction(WebConstants.ACTION_VIEW, this.getClass());
			setCancelAction(false);
		}
	}

	public String actionListener() {
		setCurrentAction(getAction(),this.getClass());
		
		switch (getCurrentAction()) {
		case WebConstants.ACTION_EDIT:
			editProfile();
			break;
		case WebConstants.ACTION_UPDATE:
			updateProfile();
			break;
		case WebConstants.ACTION_CANCEL:
			viewProfile();
			break;
		case WebConstants.ACTION_VIEW:
			viewProfile();
			break;
		}
		return null;
	}

	private void convert2DbUser(UmUser db) {
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();

		db.setUserAddress(this.getUserAddress());
		db.setUserCity(this.getUserCity());
		db.setUserCountry(this.getUserCountry());
		db.setUserEmail(this.getUserEmail());
		db.setUserFname(this.getUserFname());
		db.setUserId(Long.valueOf(session.getUserId()));
		db.setUserJobtitle(this.getUserJobtitle());
		db.setUserLname(this.getUserLname());
		db.setUserPhone(this.getUserPhone());
		db.setUserState(this.getUserState());
		db.setUserZipcode(this.getUserZipcode());
		if (this.getUserReportsto() == 0) {
			this.setUserReportsto(null);
		}
		db.setUserReportsto(this.getUserReportsto());
	}

	private void convert2BeanUser(UmUser db) {
		Address add = BeanFactory.getInstance().getAddressBean();
		this.setUserAddress(db.getUserAddress());
		this.setUserEmail(db.getUserEmail());
		this.setUserFname(db.getUserFname());
		this.setUserId(db.getUserId());
		this.setUserJobtitle(db.getUserJobtitle());
		this.setUserLname(db.getUserLname());
		this.setUserPhone(db.getUserPhone());
		this.setUserCountry(db.getUserCountry());
		this.setUserState(db.getUserState());
		this.setUserCity(db.getUserCity());
		this.setUserZipcode(db.getUserZipcode());
		if (db.getUserReportsto() == null || db.getUserReportsto().equals("")) {
			this.setUserReportsto(0L);
		} else {
			this.setUserReportsto(db.getUserReportsto());
		}
		this.setUserPicture(db.getUserPicture());
		add.stateAL(db.getUserCountry());
		add.cityAL(db.getUserState());
	}

	public void viewProfile() {
		resetBean();
		UmUser user;
		Long userId;
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		userId = Long.valueOf(session.getUserId());
		try {
			user = userDao.umUserDetails(userId);
			convert2BeanUser(user);
		} catch (Exception e) {
			// handle exception
			addError(getProperty("could.not.load.user.profile"));
		}
		setViewAction();
		setCancelAction(false);
		setShowProfilePic(true);
		setRequired(false);
	}

	public void editProfile() {
		UmUser user;
		Long userId;
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		userId = Long.valueOf(session.getUserId());
		try {
			user = userDao.umUserDetails(userId);
			convert2BeanUser(user);
		} catch (Exception e) {
			// handle exception
			addError(getProperty("could.not.load.user.profile"));
		}
		setRequired(true);
		setShowProfilePic(false);
	}

	public void updateProfile() {
		UmUser user;
		try {
			user = new UmUser();
			convert2DbUser(user);
			userDao.updateUserProfile(user);
			addMessage(getProperty("profile.updated.successfully"));
		} catch (Exception e) {
			addMessage(getProperty("profile.could.not.updated"));
		}
		viewProfile();
	}

	public Map<String, Object> Reportsto() throws UserNotFoundException {
		Map<String, Object> usersList = new HashMap<String, Object>();
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		Long companyId = session.getCompanyId();

		List<UmUser> uDB = userDao.list(companyId);
		for (UmUser us : uDB) {
			usersList.put((us.getUserFname() + " " + us.getUserLname()), us
					.getUserId().toString());

		}
		return usersList;
	}

	public void changeProfilePic(FileUploadEvent event) {
		Long userId;
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		userId = Long.valueOf(session.getUserId());
		try {
			String pic = copyPic(event.getFile().getFileName(), event.getFile()
					.getInputstream());

			this.setUserPicture(pic);
			userDao.updateProfilePic(userId, pic);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String copyPic(String fileName, InputStream in) {
		ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance()
				.getExternalContext().getContext();
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		String ext = getExtension(fileName);
		fileName = (session.getUserId() + "." + ext);
		String ppPath = ctx.getInitParameter("userProfilePicPath");
		String temp[] = ppPath.split("/");
		String path = ctx.getRealPath("");
		path = path.substring(0, path.indexOf("standalone"));
		
		String pathSeparator = File.separator;
		if (pathSeparator.equals("\\")) {
			pathSeparator = "\\\\";
		} 
		path += "standalone"+pathSeparator+"deployments";
		
		path = path + pathSeparator + temp[1];

		File images = new File(path);
		if (!images.exists()) {
			try {
				images.mkdir();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		path += pathSeparator + temp[2];

		File mainDir = new File(path);
		if (!mainDir.exists()) {
			try {
				mainDir.mkdir();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		path = path + pathSeparator;
		
		String application = BeanFactory.getInstance().getSessionBean().getSelectedApplication();
		
		if(application.equals("crm")){
			path += "crm";
		} else if(application.equals("bi")){
			path += "bi";
		}
		
		File app = new File(path);
		if (!app.exists()) {
			try {
				app.mkdir();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		path = path + pathSeparator;
		
		try {
			
			if(this.getUserPicture() != null && !this.getUserPicture().equals("")){
				File existingfile = new File(path + this.getUserPicture());
				existingfile.delete();
			}	
			
			// write the inputStream to a FileOutputStream
			OutputStream out = new FileOutputStream(new File(path + fileName));

			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}

			in.close();
			out.flush();
			out.close();
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return fileName;
	}


	public String getExtension(String name) {
		String ext = null;
		String s = name;
		int i = s.lastIndexOf('.');

		if (i > 0 && i < s.length() - 1) {
			ext = s.substring(i + 1).toLowerCase();
		}
		return ext;
	}

	public void resetBean() {
		userId = null;
		userAddress = "";
		userCity = null;
		userCountry = 169;
		userEmail = "";
		userFname = "";
		userJobtitle = "";
		userLname = "";
		userPhone = "";
		userPicture = "";
		userReportsto = null;
		userState = 3021;
		userZipcode = "";
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public String getUserZipcode() {
		return userZipcode;
	}

	public void setUserZipcode(String userZipcode) {
		this.userZipcode = userZipcode;
	}

	public boolean isShowProfilePic() {
		return showProfilePic;
	}

	public void setShowProfilePic(boolean showProfilePic) {
		this.showProfilePic = showProfilePic;
	}

	public boolean isRequired() {
		return isRequired;
	}

	public void setRequired(boolean isRequired) {
		this.isRequired = isRequired;
	}

	@Override
	public List<?> getList() {
		// TODO Auto-generated method stub
		return null;
	}


}
