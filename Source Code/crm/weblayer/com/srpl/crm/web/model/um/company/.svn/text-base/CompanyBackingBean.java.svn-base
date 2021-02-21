package com.srpl.crm.web.model.um.company;

/**
 * @author Waqas Ahmed
 *
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotBlank;
import org.primefaces.model.UploadedFile;
import com.srpl.crm.web.controller.BeanFactory;
import com.srpl.um.ejb.entity.UmCompany;
import com.srpl.um.ejb.entity.UmUser;
import com.srpl.um.ejb.request.CompanyDAO;
import com.srpl.um.ejb.request.UserDAO;
import com.srpl.crm.web.common.SessionDataBean;
import com.srpl.crm.web.controller.CompanyController;
import com.srpl.crm.web.model.NewUserBackingBean;
import com.srpl.crm.web.model.common.ColumnModel;
import com.bitguiders.util.jsf.JSFBeanInterface;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;

@ManagedBean(name = "companyBackingBean")
public class CompanyBackingBean extends JSFBeanSupport implements
		JSFBeanInterface {

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
	@Pattern(regexp="^[a-zA-z0-9_ ]*$", message="Only Alphanumeric and underscores are allowed in Title.")
	private String companyName;
	@NotBlank(message = "Contact Number is Required.")
	private String companyPhone;
	private Boolean companyStatus;
	private String companyZipcode;
	private UploadedFile file;
	private String companyLogo;
	private long rowId;

	public long getRowId() {
		return rowId;
	}

	public void setRowId(long rowId) {
		this.rowId = rowId;
	}

	@EJB
	CompanyDAO companyDAO;
	@EJB
	UserDAO userDAO;
	public static List<ColumnModel> companyColumns;

	static {

		companyColumns = new ArrayList<ColumnModel>();
		companyColumns.add(new ColumnModel("companyId", "ID"));
		companyColumns.add(new ColumnModel("companyName", "TITLE"));
		companyColumns.add(new ColumnModel("companyEmail", "EMAIL"));
		companyColumns.add(new ColumnModel("companyPhone", "PHONE NO"));
	}

	public List<ColumnModel> getCompanyColumns() {
		return companyColumns;
	}

	public void setCompanyColumns(List<ColumnModel> columns) {
		CompanyController.companyColumns = columns;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyAddress() {
		return company_address;
	}

	public void setCompanyAddress(String companyAddress) {
		this.company_address = companyAddress;
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

	
	@Override
	public String actionListener() {
		NewUserBackingBean userBean = BeanFactory.getInstance()
				.getUserBackingBean();
		UmCompany db;
		UmUser user;
		setCurrentAction(getParameter("action"),this.getClass());
		switch (getCurrentAction()) {
		case WebConstants.ACTION_CREATE:
			BeanFactory.getInstance().getSessionBean().setSelectedCountry(0);
			BeanFactory.getInstance().getSessionBean().setSelectedState(0);
			return WebConstants.ACTION_CRUD;
		case WebConstants.ACTION_VIEW:
			setRowId(Long.valueOf(getParameter("row_id").toString()));
			try {
				db = companyDAO.companyDetails(getRowId());
				user = userDAO.companyAccountManager(getRowId());
				convert2Bean(db, this);
				convert2BeanUser(user, userBean);
			} catch (Exception e) {
				// handle exception
				addError("Couldn't Load the Company.");
				System.out.println("Couldn't Load");
			}
			return WebConstants.ACTION_CRUD;
		case WebConstants.ACTION_EDIT:
			System.out.println("======================================== "
					+ "Edit Acton called ");
			setRowId(Long.valueOf(getParameter("row_id").toString()));
			System.out.println("======================================== "
					+ getRowId());
			try {
				db = companyDAO.companyDetails(getRowId());
				user = userDAO.companyAccountManager(getRowId());
				convert2Bean(db, this);
				convert2BeanUser(user, userBean);
			} catch (Exception e) {
				// handle exception
				addError("Couldn't Load the Company.");
			}
			return WebConstants.ACTION_CRUD;
		case WebConstants.ACTION_SAVE:
			// TODO
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
				System.out.println("logo before create = "+db.getCompanyLogo());
				setRowId(companyDAO.createCompany(db, user));
				this.setCompanyId(getRowId());
				addMessage("Company Successfully Created");
				try {
					uploadLogo();
				} catch (Exception e) {
					//throw new Exception();
					System.out.println("Exception in upload logo");
				}
			} catch (Exception e) {
				// handle exception
				System.out.println("Couldn't create");
				addError("Company Creation Failed.");
				setCreateAction();
				return WebConstants.ACTION_CRUD;
			}
			setListAction(true);

			return WebConstants.ACTION_LIST;
		case WebConstants.ACTION_CANCEL:
			return WebConstants.ACTION_LIST;
		case WebConstants.ACTION_DELETE:
			addMessage("Do you really want to delete ");
			setRowId(Long.valueOf(getParameter("row_id").toString()));
			try {
				db = companyDAO.companyDetails(getRowId());
				user = userDAO.companyAccountManager(getRowId());
				convert2Bean(db, this);
				convert2BeanUser(user, userBean);
				setDeleteConfirmedAction();
			} catch (Exception e) {
				// handle exception
				addError("Couldn't Load the Company.");
			}
			return WebConstants.ACTION_CRUD;
		case WebConstants.ACTION_DELETE_CONFIRMED:
			setRowId(Long.valueOf(getParameter("row_id").toString()));
			try {
				companyDAO.deleteCompany(getRowId());
			} catch (Exception e) {
				// handle exception
				addError("Couldn't Delete the Company.");
			}
			addMessage(" Deleted Successfully ");
			return WebConstants.ACTION_LIST;
		case WebConstants.ACTION_UPDATE:
			System.out.println("======================================== "
					+ "Update Acton called ");
			setRowId(Long.valueOf(getParameter("row_id").toString()));
			System.out.println("======================================== "
					+ getRowId());
			this.setCompanyId(getRowId());
			try {
				db = new UmCompany();
				try {
					uploadLogo();
				} catch (Exception e) {
					//throw new Exception();
					System.out.println("Exception in upload logo");
				}
				convert2Db(this, db);
				setRowId(companyDAO.updateCompany(db));
			} catch (Exception e) {
				// handle exception
				System.out.println("Couldn't create");
			}
			addMessage("Company Successfully Updated");
			return WebConstants.ACTION_LIST;
		case WebConstants.ACTION_PARAMETER:
			setRowId(Long.valueOf(getParameter("row_id")));
			SessionDataBean session = BeanFactory.getInstance()
					.getSessionBean();
			session.setCompanyForParameter(getRowId());
			return WebConstants.ACTION_PARAMETER;
		}
		return (null);
	}

	@Override
	public List<UmCompany> getList() {
		return companyDAO.listCompanies();
	}

	/*public List<CompanyBackingBean> getList_x() {
		// TODO Auto-generated method stub
		System.out.println("CompanyCB->getCompanies()");
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();

		List<CompanyBackingBean> myList = new ArrayList<CompanyBackingBean>();
		CompanyBackingBean bean;
		int companiesSize = 0;

		// Get packages from DB
		List<UmCompany> companiesDb = companyDAO.listCompanies();
		try {
			companiesSize = companiesList.size();
		} catch (Exception e) {
			// handle exception
			System.out.println("packages not set yet, cant get size");
		}
		for (UmCompany x : companiesDb) {
			bean = new CompanyBackingBean();
			// convert2Bean(x, bean);
			myList.add(bean);
		}
		// setCompanies(myList);
		return companiesList;
	}*/

	private void convert2Bean(UmCompany x, CompanyBackingBean bean) {

		bean.setCompanyAddress(x.getCompanyAddress());
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

		/*
		Address address = new Address();
		address = BeanFactory.getInstance().getAddressBean();
		address.stateAL(bean.getCompanyCountry());
		// populate the cities according to the value of state
		address.cityAL(bean.getCompanyState());
		*/

		BeanFactory.getInstance().getSessionBean().setSelectedCountry(bean.getCompanyCountry());
		BeanFactory.getInstance().getSessionBean().setSelectedState(bean.getCompanyState());
	}

	private void convert2Db(CompanyBackingBean bean, UmCompany db) {
        System.out.println("convert2Db called.. ");
		db.setCompanyAddress(bean.getCompanyAddress());
		db.setCompanyCity(bean.getCompanyCity());
		db.setCompanyCountry(bean.getCompanyCountry());
		db.setCompanyDetails(bean.getCompanyDetails());
		if (bean.getFile() != null) {
			String extension = getExtension(bean.getFile().getFileName());
			if(isValidExtension(extension)){
				System.out.println("bean.getFile().getFileName() = "+bean.getFile().getFileName());
				db.setCompanyLogo(bean.getFile().getFileName());	
			}
		}
		db.setCompanyEmail(bean.getCompanyEmail());
		db.setCompanyId(bean.getCompanyId());
		db.setCompanyName(bean.getCompanyName());
		db.setCompanyPhone(bean.getCompanyPhone());
		db.setCompanyState(bean.getCompanyState());
		db.setCompanyStatus(bean.getCompanyStatus());
		db.setCompanyZipcode(bean.getCompanyZipcode());
		//db.setCompanyLogo(bean.getCompanyLogo());
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
		db.setUserName(bean.getUserName());
		db.setUserPhone(bean.getUserPhone());
		db.setUserState(bean.getUserState());
		db.setUserStatus(bean.getUserStatus());
		db.setUserZipcode(bean.getUserZipcode());
		db.setUserPassword(bean.getUserPassword());
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
		bean.setUserName(db.getUserName());
		bean.setUserPhone(db.getUserPhone());
		bean.setUserState(db.getUserState());
		bean.setUserStatus(db.getUserStatus());
		bean.setUserZipcode(db.getUserZipcode());
		bean.setUserPassword(db.getUserPassword());
	}

	public void uploadLogo() throws Exception{
		System.out.println("upload logo called..");
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		Long.valueOf(session.getCompanyId());
		try {

			String fileName = getFile().getFileName();
			String fileExtension = getExtension(fileName);
			if(!isValidExtension(fileExtension)){
				  addWarning("." + fileExtension + " File type not allowed for company logo");
	              throw new Exception();
			}
			
			String logo = copyLogo(getFile().getFileName(), getFile().getInputstream());
			this.setCompanyLogo(logo);
		} catch (IOException e) {
			System.out.println("Exception occured in upload logo");
			e.printStackTrace();
			
		}
		
	}

	public String copyLogo(String fileName, InputStream in){
		ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
//		SessionDataBean session = BeanFactory.getInstance().getSessionBean();

		String ext = getExtension(fileName);
		fileName = (this.getCompanyId() +"."+ext);
		String ppPath = "/images/companyLogo/";
		String temp[] = ppPath.split("/");
		String path = ctx.getRealPath("");
		
        path = path.substring(0, path.indexOf("standalone"));
		
		String pathSeparator = File.separator;
		if (pathSeparator.equals("\\")) {
			pathSeparator = "\\\\";
		} 
		path += "standalone"+pathSeparator+"deployments";
		
		path = path + pathSeparator + temp[1];
		System.out.println("path = "+ path);

		File images = new File(path);
		if (!images.exists()) {
			images.mkdir();
		}
		path += pathSeparator+temp[2];
		
		File ppics = new File(path);
		if (!ppics.exists()) {
			ppics.mkdir();
		}
		path = path + pathSeparator;
		System.out.println("Company logo path is "+path);
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
	}
	
	public String getUploadedLogo(Long companyId){
		if(companyId == 0){
			// companyId = 0 for logo in header Area and this companyId retrieve from session
			BeanFactory.getInstance().getIndexBackingBean();
			SessionDataBean sessionBean = BeanFactory.getInstance().getSessionBean();
			String logo = companyDAO.getCompanyLogo(sessionBean.getCompanyId());
            if(logo == null){
			   return null;
			}else{
			   String[] splitFile = logo.split("\\.");
			   String ext = splitFile[splitFile.length - 1];
		 	   logo = (sessionBean.getCompanyId() +"."+ext);
			   return logo;
			}	
		}else{
			// companyId != 0 for logo in company form and this companyId retrieve from parameter passed in method
			String logo = companyDAO.getCompanyLogo(companyId);
			if(logo == null){
			   return null;
			}else{
			   String[] splitFile = logo.split("\\.");
			   String ext = splitFile[splitFile.length - 1];
		 	   logo = (companyId +"."+ext);
			   return logo;
			}
		}
		
	}
	
	String getExtension(String fileName){
		String[] splitFile = fileName.split("\\.");
		String extension = splitFile[splitFile.length - 1];
		return extension;
	}
	
	public boolean isValidExtension(String extension){
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

	
}
