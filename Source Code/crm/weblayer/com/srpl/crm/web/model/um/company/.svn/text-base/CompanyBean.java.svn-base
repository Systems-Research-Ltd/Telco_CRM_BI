package com.srpl.crm.web.model.um.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.bitguiders.util.jsf.WebConstants;
import com.srpl.crm.web.controller.BeanFactory;
import com.srpl.crm.web.model.common.Address;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.srpl.crm.common.utils.UmCompanyDetails;
import com.srpl.crm.common.utils.UmParameterDetails;
import com.srpl.um.ejb.entity.UmParameter;
import com.srpl.um.ejb.request.CompanyDAO;
import com.srpl.um.ejb.request.ParameterDAO;
import com.srpl.um.ejb.entity.UmCompany;



@ManagedBean(name = "companyBean")
@ViewScoped
@Deprecated
public class CompanyBean extends JSFBeanSupport implements Serializable {
	private static final long serialVersionUID = 1L;
	private String title;
	private String description;
	private String contact;
	private String phone;
	private String email;
	private String address;
	@NotNull(message="Country information is required.")
	@Min(value=1, message="Country information is required.")
	private int city;
	@NotNull(message="State information is required.")
	@Min(value=1, message="State information is required.")
	private int state;
	@NotNull(message="City information is required.")
	@Min(value=1, message="City information is required.")
	private int country;
	private String zipcode;
	private String status;
	
	//Create Parameter fields
	private String parameterTitle;
	private long value;

	private Long param_companyid=-1L;
	
	//@ManagedProperty(value="#{param.companyid}")
//	private String paramtest;
	
	@EJB
	CompanyDAO comDAO;
	@EJB 
	ParameterDAO parameterDao;

	// Constructor
	public CompanyBean() 
	{
		
	}

	@PostConstruct
	public void init() {
		 Map<String,String> params=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		
		/* switch(getAction())
		 {
		 	case WebConstants.ACTION_CREATE:
				setCreateAction();
				break;
		 	case WebConstants.ACTION_EDIT:
		 		setEditAction();
		 		break;
		 	case WebConstants.ACTION_VIEW:
		 		setViewAction();
		 		break;	
		 }*/
		 
		 if(params.get("row_id")!=null)
			 param_companyid =Long.valueOf(params.get("row_id").toString());
		 else
			 param_companyid=-1L;
		
	/*	BeanFactory bean = BeanFactory.getInstance();
		param_companyid = bean.getSessionDataBean().getCompany_id();
		bean.getSessionDataBean().setCompany_id(-1);*/

		//if page in edit mode
		if (param_companyid >= 0L) 
		{
			UmCompany comp = comDAO.companyDetails(param_companyid);
			if(comp!=null)
			{
				title = comp.getCompanyName();
				description = comp.getCompanyDetails();
				// contact;
				phone = comp.getCompanyPhone();
				email = comp.getCompanyEmail();
				address = comp.getCompanyAddress();
				state= comp.getCompanyState();
				zipcode = comp.getCompanyZipcode();
				city = comp.getCompanyCity();
				country= comp.getCompanyCountry();
				status = (comp.getCompanyStatus()==true ? "1" : "0");
				
				Address a = BeanFactory.getInstance().getAddressBean();
				a.stateAL(country);
				a.cityAL(state);
			}
			else
			{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Sample error message", "PrimeFaces makes no mistakes"));
			}
		}
	}

	public String createCompany() 
	{
		// Call EJB to save company
		UmCompanyDetails com = new UmCompanyDetails(address, city, country, description, email, title, phone, state, Integer.parseInt(status) != 0, zipcode);
//		comDAO.createCompany(com);
		
//		System.out.println("CreateCompanyBean createCompanyActionController success");
	//	addMessage(getProperty("message.company.created"));
		return WebConstants.ACTION_LIST;
	}

	/*public String editCompany() {
		UmCompany com = new UmCompany(param_companyid, address, city, country, description, email, title, phone,state, Integer.parseInt(status) != 0, zipcode);

		comDAO.updateCompany(com);
		System.out.println("CreateCompanyBean createCompanyActionController success");
		return WebConstants.ACTION_LIST;
		// Call EJB to edit company
		UmCompanies com = new UmCompanies();
		com.setCompany_id(param_companyid);
		com.setCompany_name(title);
		com.setCompany_email(email);
		com.setCompany_phone(phone);
		com.setCompany_description(description);
		com.setCompany_address(address);
		com.setCompany_city(Integer.parseInt(city));
		com.setCompany_status(Integer.parseInt(status) != 0);

		comDAO.updateCompanies(com,param_companyid);
		System.out.println("CreateCompanyBean editCompanyActionController success");
		
	}*/

	public String cancel() 
	{
		return WebConstants.ACTION_LIST;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getCity() {
		return city;
	}

	public void setCity(int city) {
		this.city = city;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

/*	public CompaniesDao getComDAO() {
		return comDAO;
	}

	public void setComDAO(CompaniesDao comDAO) {
		this.comDAO = comDAO;
	}*/

	public Long getParam_companyid() {
		return param_companyid;
	}

	public void setParam_companyid(Long param_companyid) {
		this.param_companyid = param_companyid;
	}

	public String getParameterTitle() {
		return parameterTitle;
	}

	public int getCountry() {
		return country;
	}

	public void setCountry(int country) {
		this.country = country;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public void setParameterTitle(String parameterTitle) {
		this.parameterTitle = parameterTitle;
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

/*	public String getParamtest() {
		return paramtest;
	}

	public void setParamtest(String paramtest) {
		this.paramtest = paramtest;
	}*/

/*	public String actionListener(){
		System.out.println("Create Button");
		switch(getAction()){
		case WebConstants.ACTION_CREATE:
			return createCompany();
		case WebConstants.ACTION_EDIT:
			return editCompany();
		}

		return null;
	}*/
	
	
	public List<ParameterBackingBean> getCompanyParameters(){
		  List<ParameterBackingBean> myList = new ArrayList<ParameterBackingBean>();
		  ParameterBackingBean cp;
		  List<UmParameter> ComparamDB =parameterDao.list(param_companyid);
		  for (UmParameter p : ComparamDB) {
				cp=new ParameterBackingBean();
				System.out.println("new param id"+p.getParameterId());
				cp.setParameterTitle(p.getParameterTitle());
				cp.setParameterValue(p.getParameterValue());
				cp.setParameterId(p.getParameterId());
				cp.setCompanyId(p.getCompanyId());
				System.out.println("new company id"+p.getCompanyId());
				myList.add(cp);
				
		  }
		
		return myList;
	}
}
