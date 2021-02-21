package com.srpl.crm.web.model;

/**
 * @author Hammad Hassan Khan
 *
 */
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.primefaces.model.UploadedFile;

import com.bitguiders.util.jsf.JSFBeanSupport;

@ManagedBean(name="company")
public class NewCompanyBackingBean extends JSFBeanSupport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long companyId;
	@NotBlank(message="Company Address is Required.")
	private String company_address;
	@NotNull(message="Company Country is Required.")
	@Min(message="Company Country is Required.", value = 1)
	private Integer companyCountry;
	private int companyState;
	@NotNull(message="Company City is Required.")
	@Min(message="Company City is Required.", value = 1)
	private Integer companyCity;
	private String companyDetails;
	@NotBlank(message="Email Address is Required.")
	private String companyEmail;
	@NotBlank(message="Company Name is Required.")
	private String companyName;
	@NotBlank(message="Contact Number is Required.")
	@Pattern(message="The valid pattern for contact number is 012-345-6789, 0123-456-7890, 111-222-333", regexp="\\d{4}-\\d{3}-\\d{4}||\\d{3}-\\d{3}-\\d{4}||\\d{3}-\\d{3}-\\d{3}")
	private String companyPhone;
	private Boolean companyStatus;
	private String companyZipcode;
	private UploadedFile file;
	
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

	public Integer getCompanyCountry() {
		return companyCountry;
	}

	public void setCompanyCountry(Integer companyCountry) {
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

	public void reset(){
		companyId =  null;
		company_address = "";
		companyCity = null;
		companyCountry = null;
		companyDetails = "";
		companyEmail = "";
		companyName = "";
		companyPhone = "";
		companyState = 0;
		companyStatus = true;
		companyZipcode = "";
	}
}
