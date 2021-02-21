package com.srpl.crm.web.model.um.company;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.bitguiders.util.jsf.JSFBeanInterface;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;
import com.srpl.crm.web.common.AjaxListStructure;
import com.srpl.crm.web.common.InnerTabs;
import com.srpl.crm.web.model.NewUserBackingBean;
import com.srpl.um.ejb.entity.UmCompany;
import com.srpl.um.ejb.entity.UmParameter;
import com.srpl.um.ejb.entity.UmUser;
import com.srpl.um.ejb.request.CompanyDAO;
import com.srpl.um.ejb.request.ParameterDAO;
import com.srpl.um.ejb.request.UserDAO;
import com.srpl.um.web.common.SessionDataBean;
import com.srpl.um.web.controller.BeanFactory;

@ManagedBean(name = "companyBackingBean")
@RequestScoped
public class CompanyBackingBean extends JSFBeanSupport implements
		JSFBeanInterface, Serializable {
	private static final long serialVersionUID = 1L;

	public CompanyBackingBean() {
		session = BeanFactory.getInstance().getSessionBean();
		// setCurrentAction(WebConstants.ACTION_SECURITY, this.getClass());
	}
                                                                                                                                                                      
	private Long companyId;
	@NotBlank(message = "Company Address is Required.")
	private String company_address;
	@NotNull(message = "Company Country is Required.")
	@Min(message = "Company Country is Required.", value = 1)
	private int companyCountry;
	private int companyState;
	@NotNull(message = "Company City is Required.")
	@Min(message = "Company City is Required.", value = 1)
	private Integer companyCity;
	private String companyDetails;
	@NotBlank(message = "Email Address is Required.")
	private String companyEmail;
	@NotBlank(message = "Company Name is Required.")
	@Pattern(regexp = "^[a-zA-z0-9-_' ']*$", message = "Only Alphanumeric and underscores are allowed in Title.")
	private String companyName;
	@NotBlank(message = "Contact Number is Required.")
	private String companyPhone;
	private Boolean companyStatus;
	private String companyZipcode;
	private UploadedFile file;
	private String companyLogo;
	private String url;
	private SessionDataBean session;
	private String listingTitle;

	private boolean enablePasswordField;

	@EJB
	CompanyDAO companyDAO;
	@EJB
	UserDAO userDAO;
	@EJB
	ParameterDAO parameterDao;

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getCompany_address() {
		return company_address;
	}

	public void setCompany_address(String company_address) {
		this.company_address = company_address;
	}

	public int getCompanyCountry() {
		return companyCountry;
	}

	public void setCompanyCountry(int companyCountry) {
		this.companyCountry = companyCountry;
	}

	public int getCompanyState() {
		return companyState;
	}

	public void setCompanyState(int companyState) {
		this.companyState = companyState;
	}

	public Integer getCompanyCity() {
		return companyCity;
	}

	public void setCompanyCity(Integer companyCity) {
		this.companyCity = companyCity;
	}

	public String getCompanyDetails() {
		return companyDetails;
	}

	public void setCompanyDetails(String companyDetails) {
		this.companyDetails = companyDetails;
	}

	public String getCompanyEmail() {
		return companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyPhone() {
		return companyPhone;
	}

	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}

	public Boolean getCompanyStatus() {
		return companyStatus;
	}

	public void setCompanyStatus(Boolean companyStatus) {
		this.companyStatus = companyStatus;
	}

	public String getCompanyZipcode() {
		return companyZipcode;
	}

	public void setCompanyZipcode(String companyZipcode) {
		this.companyZipcode = companyZipcode;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public String getCompanyLogo() {
		return companyLogo;
	}

	public void setCompanyLogo(String companyLogo) {
		this.companyLogo = companyLogo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public SessionDataBean getSession() {
		return session;
	}

	public void setSession(SessionDataBean session) {
		this.session = session;
	}

	public String getListingTitle() {
		return listingTitle;
	}

	public void setListingTitle(String listingTitle) {
		this.listingTitle = listingTitle;
	}

	public boolean isEnablePasswordField() {
		return enablePasswordField;
	}

	public void setEnablePasswordField(boolean enablePasswordField) {
		this.enablePasswordField = enablePasswordField;
	}

	@PostConstruct
	public void postConstruct() {

		listingTitle = "Companies";
		String row_id=getParameter("company_id");
		if(!row_id.isEmpty())
		{
			session.setCompanyModule_selectedCompany(Long.parseLong(row_id));
		}
		
		String act = getAction();
		if (act.equals("")) {
			// no action was called, load group data
			companyDetails();
			reset();
			setViewAction();
		} else if (act.equals(WebConstants.ACTION_SAVE)) {
			enablePasswordField = true;
		} else if (act.equals("actionAjax")) {
			setDisabled(false);
		}
	}

	public void resetBean() {
		setCompanyId(0L);
		setCompanyName("");
		setCompanyDetails("");
		setCompany_address("");
		setCompanyCountry(0);
		setCompanyState(0);
		setCompanyCity(0);
		setCompanyEmail("");
		setCompanyLogo("");
		setCompanyPhone("");
		setCompanyStatus(false);
		setCompanyZipcode("");
	}

	public void companyDetails() {
		System.out.println("++" + session.getCompanyModule_selectedCompany());
		if (session.getCompanyModule_selectedCompany() != 0L) {
			loadCompany(session.getCompanyModule_selectedCompany());
			changeTabPath(0, "/view/um/admin/company/companyForm.xhtml");
			changeTabPath(1, "/view/um/admin/company/parameter/index.xhtml");
			setViewAction();
		} else {
			session.resetCompanyModule();	
		}
	}

	private void changeTabPath(int index, String path) {
		InnerTabs d = session.getCompanyTabs().get(index);
		d.setPath(path);
		session.getCompanyTabs().set(index, d);

		try {
			if (getParameter("fromListing").equals("fromListing")) {
				// don't update index
			} else {
				session.setCompanyModule_tabIndex(0);

			}
		} catch (Exception e) {
			session.setCompanyModule_tabIndex(0);
		}
	}

	public void loadCompany(Long id) {
		NewUserBackingBean userBean = BeanFactory.getInstance()
				.getUserBackingBean();
		CompanyBackingBean bean = this;
		UmCompany db;
		UmUser user = new UmUser();

		try {
			db = companyDAO.companyDetails(id);
			user = userDAO.companyAccountManager(id);
			convert2Bean(db, bean);
			convert2BeanUser(user, userBean);
		} catch (Exception e) {
			changeTabPath(0, "/view/um/admin/company/companyNoSelection.xhtml");
		}
	}

	@Override
	public void setViewAction() {
		super.setViewAction();
		setCancelAction(false);
		setResetAction(false);
	}

	@Override
	public String actionListener() {
		long company_id;
		setCurrentAction(getParameter("action"), this.getClass());
		switch (getCurrentAction()) {
		case WebConstants.ACTION_CREATE:
			changeTabPath(0, "/view/um/admin/company/companyForm.xhtml");
			listingTitle = "Companies";
			BeanFactory.getInstance().getSessionBean().setSelectedCountry(0);
			BeanFactory.getInstance().getSessionBean().setSelectedState(0);
			session.setCompanyAction(WebConstants.ACTION_CREATE);
			resetBean();
			break;
		case WebConstants.ACTION_SAVE:
			createCompany();
			session.setCompanyAction("");
			break;
		case WebConstants.ACTION_VIEW:
			companyDetails();
			reset();
			setViewAction();
			break;
		case WebConstants.ACTION_CANCEL:
			companyDetails();
			reset();
			setViewAction();
			break;
		case WebConstants.ACTION_EDIT:
			company_id = Long.valueOf(getParameter("company_id"));
			loadCompany(company_id);
			session.setCompanyAction(WebConstants.ACTION_EDIT);
			changeTabPath(0, "/view/um/admin/company/companyForm.xhtml");
			break;
		case WebConstants.ACTION_UPDATE:
			companyUpdate();
			session.setCompanyAction("");
			break;
		case WebConstants.ACTION_DELETE:
			companyDetails();
			reset();
			setDeleteAction();
			break;
		case WebConstants.ACTION_DELETE_CONFIRMED:
			companyDelete();
			break;
		}
		return (null);
	}

	public void createCompany() {
		UmCompany db = new UmCompany();
		CompanyBackingBean companyBean = this;
		NewUserBackingBean userBean = BeanFactory.getInstance()
				.getUserBackingBean();

		UmUser user;

		try {
			String fName;
			String lName;
			if (userBean.getUserFname().indexOf(" ") == -1) {
				fName = userBean.getUserFname();
				lName = "";
				userBean.setUserFname(fName);
				userBean.setUserLname(lName);

			} else {
				String[] value = userBean.getUserFname().split(" ");
				fName = value[0];
				lName = value[1];
				userBean.setUserFname(value[0]);
				userBean.setUserLname(value[1]);
			}

			db = new UmCompany();
			user = new UmUser();

			convert2Db(this, db);
			convert2DbUser(userBean, user);
			if(!session.getCompanyLogo().isEmpty())
			{
				db.setCompanyLogo(session.getCompanyLogo());
				this.setCompanyLogo(session.getCompanyLogo());
			}
			companyDAO.createCompany(db, user);
			session.setCompanyLogo("");
			addMessage(getProperty("company.successfully.created"));
			listingTitle = this.getCompanyName();
			// enablePasswordField = false;
			reset();
			setViewAction();
		} catch (Exception e) {
			// handle exception
			System.out.println("Couldn't create");
			addError(getProperty("company.creation.fail"));
			reset();
			setCreateAction();
		}
		//add company parameters
		try{
			UmUser usr = userDAO.umUserDetails(session.getUserId());
			//admin.email
			UmParameter adminEmail = new UmParameter("application.admin.email", usr.getUserEmail(),session.getCompanyId());
			parameterDao.create(adminEmail);
			//cc.email
			UmParameter ccEmail = new UmParameter("application.support.email", usr.getUserEmail(),session.getCompanyId());
			parameterDao.create(ccEmail);
			//ceo.email
			UmParameter ceoEmail = new UmParameter("company.admin.email", userBean.getUserEmail(),session.getCompanyId());
			parameterDao.create(ceoEmail);
			//billing.email 
			UmParameter billingEmail = new UmParameter("company.support.email", companyBean.companyEmail,session.getCompanyId());
			parameterDao.create(billingEmail);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void companyUpdate() {
		CompanyBackingBean companyBean = this;

		try {
			UmCompany db = new UmCompany();

			convert2Db(companyBean, db);
			if(!session.getCompanyLogo().isEmpty())
			{
				db.setCompanyLogo(session.getCompanyLogo());
				this.companyLogo=session.getCompanyLogo();
			}
			companyDAO.updateCompany(db);
			reset();
			setViewAction();
			addMessage(getProperty("company.successfully.updated"));
		} catch (Exception createExp) {
			// handle exception
			addError(getProperty("company.update.fail"));
			reset();
			setEditAction();
		}
	}

	public void companyDelete() {
		try {
			companyDAO
					.deleteCompany(session.getCompanyModule_selectedCompany());

			addMessage(getProperty("company.delete.successfully"));
		} catch (Exception deleteExpception) {
			addError(getProperty("company.delete.fail"));
		}
		session.setCompanyModule_selectedCompany(0L);
		companyDetails();
		reset();
		setViewAction();
	}

	public ArrayList<AjaxListStructure> getList() {
		ArrayList<AjaxListStructure> myList = new ArrayList<AjaxListStructure>();
		AjaxListStructure u;

		List<UmCompany> com = null;
		com = companyDAO.listCompanies();
		for (UmCompany db : com) {
			u = new AjaxListStructure();
			u.setId(db.getCompanyId());
			u.setLabel(db.getCompanyName());
			myList.add(u);
		}
		if (myList.size() == 0) {
			u = new AjaxListStructure();
			u.setId(0L);
			u.setLabel(getProperty("no.company.found"));
			myList.add(u);
		}
		return myList;
	}

	String getExtension(String fileName) {
		String[] splitFile = fileName.split("\\.");
		String extension = splitFile[splitFile.length - 1];
		return extension;
	}

	public boolean isValidExtension(String extension) {
		boolean isValid = true;
		switch (extension) {
		case "gif":
			break;
		case "GIF":
			break;
		case "jpg":
			break;
		case "jpeg":
			break;
		case "JPG":
			break;
		case "JPEG":
			break;
		case "png":
			break;
		case "PNG":
			break;
		default:
			isValid = false;

		}
		return isValid;
	}

	private void convert2Bean(UmCompany x, CompanyBackingBean bean) {
		bean.setCompany_address(x.getCompanyAddress());

		bean.setCompanyCity(x.getCompanyCity());
		bean.setCompanyCountry(x.getCompanyCountry());
		bean.setCompanyDetails(x.getCompanyDetails());
		bean.setCompanyEmail(x.getCompanyEmail());
		bean.setCompanyId(x.getCompanyId());
		bean.setCompanyName(x.getCompanyName());
		bean.setCompanyPhone(x.getCompanyPhone());
		bean.setCompanyState(x.getCompanyState());
		bean.setCompanyStatus(x.getCompanyStatus());
		bean.setCompanyZipcode(x.getCompanyZipcode());
		bean.setCompanyLogo(x.getCompanyLogo());
		bean.setUrl(x.getCompanyUrl());
		/*
		 * Address address = new Address(); address =
		 * BeanFactory.getInstance().getAddressBean();
		 * address.stateAL(bean.getCompanyCountry()); // populate the cities
		 * according to the value of state
		 * address.cityAL(bean.getCompanyState());
		 */
		
		bean.setListingTitle(x.getCompanyName());

		BeanFactory.getInstance().getSessionBean()
				.setSelectedCountry(bean.getCompanyCountry());
		BeanFactory.getInstance().getSessionBean()
				.setSelectedState(bean.getCompanyState());
	}

	private void convert2Db(CompanyBackingBean bean, UmCompany db) {
		System.out.println("convert2Db called.. ");
		db.setCompanyAddress(bean.getCompany_address());
		db.setCompanyCity(bean.getCompanyCity());
		db.setCompanyCountry(bean.getCompanyCountry());
		db.setCompanyDetails(bean.getCompanyDetails());
		if (bean.getFile() != null) {
			String extension = getExtension(bean.getFile().getFileName());
			if (isValidExtension(extension)) {
				System.out.println("session.getFile().getFileName() = "
						+ bean.getFile().getFileName());
				db.setCompanyLogo(this.getCompanyId() + "." + extension);
			}
		}
		db.setCompanyEmail(bean.getCompanyEmail());
		db.setCompanyId(bean.getCompanyId());
		db.setCompanyName(bean.getCompanyName());
		db.setCompanyPhone(bean.getCompanyPhone());
		db.setCompanyState(bean.getCompanyState());
		db.setCompanyStatus(bean.getCompanyStatus());
		db.setCompanyZipcode(bean.getCompanyZipcode());
		db.setCompanyUrl(bean.getUrl());
		// db.setCompanyLogo(bean.getCompanyLogo());
	}

	private void convert2DbUser(NewUserBackingBean bean, UmUser db) {

		db.setIsFranchiseUser(bean.getIsFranchiseUser());
		// db.setUmUserGroups(bean.get)
		db.setUserAddress(db.getUserAddress());
		db.setUserCity(bean.getUserCity());
		db.setUserCountry(bean.getUserCountry());
		db.setUserEmail(bean.getUserEmail());
		db.setUserFname(bean.getUserFname());
		db.setUserId(bean.getUserId());
		db.setUserJobtitle(bean.getUserJobtitle());
		db.setUserLname(bean.getUserLname());
		db.setUserName(bean.getUserEmail());
		db.setUserPhone(bean.getUserPhone());
		db.setUserState(bean.getUserState());
		db.setUserStatus(bean.getUserStatus());
		db.setUserZipcode(bean.getUserZipcode());
		db.setUserPassword(bean.getUserPassword());
		db.setIsUserCustomer(false);

	}

	private void convert2BeanUser(UmUser db, NewUserBackingBean bean) {

		bean.setIsFranchiseUser(db.getIsFranchiseUser());
		bean.setUserAddress(db.getUserAddress());
		bean.setUserCity(db.getUserCity());
		bean.setUserCountry(db.getUserCountry());
		bean.setUserEmail(db.getUserEmail());
		bean.setUserFname(db.getUserFname());
		bean.setUserId(db.getUserId());
		bean.setUserJobtitle(db.getUserJobtitle());
		bean.setUserLname(db.getUserLname());
		bean.setUserName(db.getUserEmail());
		bean.setUserPhone(db.getUserPhone());
		bean.setUserState(db.getUserState());
		bean.setUserStatus(db.getUserStatus());
		bean.setUserZipcode(db.getUserZipcode());
		bean.setUserPassword(db.getUserPassword());
	}

/*	public void uploadLogo() throws Exception {
		System.out.println("upload logo called..");
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		Long.valueOf(session.getCompanyModule_selectedCompany());
		try {

			String fileName = getFile().getFileName();
			String fileExtension = getExtension(fileName);
			if (!isValidExtension(fileExtension)) {
				addWarning("." + fileExtension
						+ getProperty("file.type.not.allowed"));
				throw new Exception();
			}

			String logo = copyLogo(getFile().getFileName(), getFile()
					.getInputstream());
			this.setCompanyLogo(logo);
		} catch (IOException e) {
			System.out.println("Exception occured in upload logo");
			e.printStackTrace();

		}

	}*/

/*	public String copyLogo(String fileName, InputStream in) {
		ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance()
				.getExternalContext().getContext();
		// SessionDataBean session = BeanFactory.getInstance().getSessionBean();

		String ext = getExtension(fileName);
		fileName = (this.getCompanyId() + "." + ext);
		String ppPath = "/images/companyLogo/";
		String temp[] = ppPath.split("/");
		String path = ctx.getRealPath("../..");
		path = path + "//" + temp[1];

		File images = new File(path);
		if (images.exists()) {
			path += "//" + temp[2];
		} else {
			images.mkdir();
			path += "//" + temp[2];
		}

		File ppics = new File(path);
		if (ppics.exists()) {
			path = path + "//";

		} else {
			ppics.mkdir();
			path = path + "//";
		}
		try {
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
			System.out.println("Exception");
			System.out.println(e.getMessage());
		}

		return fileName;
	}*/
	
	public void changeLogoPic(FileUploadEvent event) {
		Long userId;
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		userId = Long.valueOf(session.getUserId());
		try {
			String pic = copyPic(event.getFile().getFileName(), event.getFile()
					.getInputstream());

			this.setCompanyLogo(pic);
			session.setCompanyLogo(pic);
			if(session.getCompanyAction().equals(WebConstants.ACTION_EDIT))
				companyDAO.updateCompanyLogo(this.companyId, pic);
			setCurrentAction(session.getCompanyAction());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String copyPic(String fileName, InputStream in) {
		ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance()
				.getExternalContext().getContext();
//		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
//		String ext = getExtension(fileName);
//		fileName = (this.companyId.toString() + "." + ext);
		String ppPath = ctx.getInitParameter("companylogoPath");
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

		File logos = new File(path);
		if (!logos.exists()) {
			try {
				logos.mkdir();

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
			
			if(this.getCompanyLogo() != null && !this.getCompanyLogo().equals("")){
				File existingfile = new File(path + this.getCompanyLogo());
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

	public String getUploadedLogo(Long companyId) {
		if (companyId == 0) {
			// companyId = 0 for logo in header Area and this companyId retrieve
			// from session
			BeanFactory.getInstance().getIndexBackingBean();
			SessionDataBean sessionBean = BeanFactory.getInstance()
					.getSessionBean();
			String logo = companyDAO.getCompanyLogo(sessionBean.getCompanyId());
			if (logo == null) {
				return null;
			} else {
				String[] splitFile = logo.split("\\.");
				String ext = splitFile[splitFile.length - 1];
				logo = (sessionBean.getCompanyId() + "." + ext);
				return logo;
			}
		} else {
			// companyId != 0 for logo in company form and this companyId
			// retrieve from parameter passed in method
			String logo = companyDAO.getCompanyLogo(companyId);
			if (logo == null) {
				return null;
			} else {
				String[] splitFile = logo.split("\\.");
				String ext = splitFile[splitFile.length - 1];
				logo = (splitFile[0] + "." + ext);
				return logo;
			}
		}

	}

	/*
	 * public void uploadLogoEvent(FileUploadEvent event) {
	 * session.setFile(event.getFile()); //file = event.getFile(); }
	 */
}
