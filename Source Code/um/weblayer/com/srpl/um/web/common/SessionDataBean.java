package com.srpl.um.web.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.bitguiders.util.ResourceBundle;
import com.srpl.crm.web.common.InnerTabs;

/**
 * @author sysres-21
 *
 */
@ManagedBean
@SessionScoped
public class SessionDataBean implements Serializable 
{
	/**
	 * 
	 */
	
	public SessionDataBean() {
		this.showPopUp = true;
		
		//TODO
		//Set Company Tabs
		companyTabs = new ArrayList<InnerTabs>();
		initializeCompanyModule();
		//Set Marketing Tabs
		marketingTabs = new ArrayList<InnerTabs>();
		initializeMarketingModule();

		//Set Group Tabs
		groupTabs = new ArrayList<InnerTabs>();
		initializeGroupModule();
		//Set Users Tabs
		userTabs = new ArrayList<InnerTabs>();
		initializeUserModule();
		//Set Loyalty Template Tabs
		loyaltyTemplateTabs = new ArrayList<InnerTabs>();
		initializeLoyaltyTemplateModule();
		System.out.println("Construct....");
		//Set Services Tabs
		serviceTabs = new ArrayList<InnerTabs>();
		initializeServiceModule();
		//Set Franchise Tabs
		franchiseTabs = new ArrayList<InnerTabs>();
		initializeFranchiseModule();
		//Set MailTempalte Tabs
		mtTabs = new ArrayList<InnerTabs>();
		initializeMTModule();
		
	}
	private static final long serialVersionUID = 1L;
	
	private boolean showPopUp;
	private Long companyId;
	private Long franchiseId;
	private Long userId;
	private String userFname;
	private String userLname;
	String row_id;
	private Long selectedCustomer;
	private String cssPath;
	private String themeTitle;
	private String selectedApplication;	
	
	private int selectedCountry;
	private int selectedState;
	
	private Long companyForParameter;
	private String companyLogo;
	private String companyAction;
	public String getCompanyAction() {
		return companyAction;
	}
	public void setCompanyAction(String companyAction) {
		this.companyAction = companyAction;
	}
	public String getCompanyLogo() {
		return companyLogo;
	}
	public void setCompanyLogo(String companyLogo) {
		this.companyLogo = companyLogo;
	}
	public String getThemeTitle() {
		return themeTitle;
	}
	public void setThemeTitle(String themeTitle) {
		this.themeTitle = themeTitle;
	}
	public boolean isShowPopUp() {
		return showPopUp;
	}
	public void setShowPopUp(boolean showPopUp) {
		this.showPopUp = showPopUp;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public Long getFranchiseId() {
		return franchiseId;
	}
	public void setFranchiseId(Long franchiseId) {
		this.franchiseId = franchiseId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserFname() {
		System.out.println(userFname);
		return userFname;
	}
	public void setUserFname(String userFname) {
		this.userFname = userFname;
	}
	public String getUserLname() {
		return userLname;
	}
	public void setUserLname(String userLname) {
		this.userLname = userLname;
	}
	public Long getCompanyForParameter() {
		return companyForParameter;
	}
	public void setCompanyForParameter(Long companyForParameter) {
		this.companyForParameter = companyForParameter;
	}
	

	public String getProperty(String property){
		ResourceBundle resourceBundle = ResourceBundle.getInstance();		  
		  HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		  Locale locale = request.getLocale();
		  System.out.println(locale);		 
	//	resourceBundle.setLocale(FacesContext.getCurrentInstance().getViewRoot().getLocale());
		  resourceBundle.setLocale(locale);		  		  
		if(resourceBundle.getPropertyValue(property)==null){
			return "Error: sid.properties doesn't contains property "+property;
		}
			//return property;
			return ResourceBundle.getInstance().getPropertyValue(property);
	}
	
	//TABS TODO
	//company modeule session variables
	private ArrayList<InnerTabs> companyTabs;
	private Long companyModule_selectedCompany;
	private int companyModule_selectedParameter;
	private int companyModule_tabIndex;
	//marketing module session variables
	private ArrayList<InnerTabs> marketingTabs;
	private Long marketingModule_selectedCampaign;
	private int marketingModule_tabIndex;
	//group module session variables
	private ArrayList<InnerTabs> groupTabs;
	private Long groupModule_selectedGroup;
	private Long groupModule_selectedUser;
	private Integer groupModule_tabIndex;
	//user module session variables
	private ArrayList<InnerTabs> userTabs;
	private Long userModule_selectedUser;
	private Integer userModule_tabIndex;
	//loyalty template module session variables
	private ArrayList<InnerTabs> loyaltyTemplateTabs;
	private Long loyaltyModule_selectedTemplate;
	private Integer loyaltyModule_tabIndex;
	private Boolean showEditAction = true;
	private Boolean showUpdateAction = true;
	private String persistence;
	//service module session variables
	private ArrayList<InnerTabs> serviceTabs;
	private Long serviceModule_selectedService;
	private Integer serviceModule_tabIndex;
	//franchise module session variables
	private ArrayList<InnerTabs> franchiseTabs;
	private Long franchiseModule_selectedFranchise;
	private Integer franchiseModule_tabIndex;
	//mailTemplates session variables
	private ArrayList<InnerTabs> mtTabs;
	private Long mtModule_selectedTemplate;
	private int mtModule_tabIndex;
	
	
	public String parameterAction()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		row_id = request.getParameter("row_id");
		System.out.println("row ID ="+ row_id);
		return "compparameter";
	}

	public String getRow_id() {
		return row_id;
	}

	public void setRow_id(String row_id) {
		this.row_id = row_id;
	}
	
	public Long getSelectedCustomer() {
		return selectedCustomer;
	}
	
	public void setSelectedCustomer(Long selectedCustomer) {
		this.selectedCustomer = selectedCustomer;
	}
	
	public int getSelectedCountry() {
		return selectedCountry;
	}
	
	public void setSelectedCountry(int selectedCountry) {
		this.selectedCountry = selectedCountry;
	}
	public int getSelectedState() {
		return selectedState;
	}
	public void setSelectedState(int selectedState) {
		this.selectedState = selectedState;
	}
	public String getCssPath() {
		return cssPath;
	}
	public void setCssPath(String cssPath) {
		this.cssPath = cssPath;
	}
	public ArrayList<InnerTabs> getCompanyTabs() {
		return companyTabs;
	}
	public void setCompanyTabs(ArrayList<InnerTabs> companyTabs) {
		this.companyTabs = companyTabs;
	}
	public Long getCompanyModule_selectedCompany() {
		return companyModule_selectedCompany;
	}
	public void setCompanyModule_selectedCompany(Long companyModule_selectedCompany) {
		this.companyModule_selectedCompany = companyModule_selectedCompany;
	}
	public int getCompanyModule_selectedParameter() {
		return companyModule_selectedParameter;
	}
	public void setCompanyModule_selectedParameter(
			int companyModule_selectedParameter) {
		this.companyModule_selectedParameter = companyModule_selectedParameter;
	}
	public int getCompanyModule_tabIndex() {
		return companyModule_tabIndex;
	}
	public void setCompanyModule_tabIndex(int companyModule_tabIndex) {
		this.companyModule_tabIndex = companyModule_tabIndex;
	}
	public ArrayList<InnerTabs> getMarketingTabs() {
		return marketingTabs;
	}
	public void setMarketingTabs(ArrayList<InnerTabs> marketingTabs) {
		this.marketingTabs = marketingTabs;
	}
	public ArrayList<InnerTabs> getGroupTabs() {
		return groupTabs;
	}
	public void setGroupTabs(ArrayList<InnerTabs> groupTabs) {
		this.groupTabs = groupTabs;
	}
	public Long getGroupModule_selectedGroup() {
		return groupModule_selectedGroup;
	}
	public void setGroupModule_selectedGroup(Long groupModule_selectedGroup) {
		this.groupModule_selectedGroup = groupModule_selectedGroup;
	}
	public Long getGroupModule_selectedUser() {
		return groupModule_selectedUser;
	}
	public void setGroupModule_selectedUser(Long groupModule_selectedUser) {
		this.groupModule_selectedUser = groupModule_selectedUser;
	}
	public Integer getGroupModule_tabIndex() {
		return groupModule_tabIndex;
	}
	public void setGroupModule_tabIndex(Integer groupModule_tabIndex) {
		this.groupModule_tabIndex = groupModule_tabIndex;
	}
	public ArrayList<InnerTabs> getUserTabs() {
		return userTabs;
	}
	public void setUserTabs(ArrayList<InnerTabs> userTabs) {
		this.userTabs = userTabs;
	}
	public Long getUserModule_selectedUser() {
		return userModule_selectedUser;
	}
	public void setUserModule_selectedUser(Long userModule_selectedUser) {
		this.userModule_selectedUser = userModule_selectedUser;
	}
	public Integer getUserModule_tabIndex() {
		return userModule_tabIndex;
	}
	public void setUserModule_tabIndex(Integer userModule_tabIndex) {
		this.userModule_tabIndex = userModule_tabIndex;
	}
	public ArrayList<InnerTabs> getLoyaltyTemplateTabs() {
		return loyaltyTemplateTabs;
	}
	public void setLoyaltyTemplateTabs(ArrayList<InnerTabs> loyaltyTemplateTabs) {
		this.loyaltyTemplateTabs = loyaltyTemplateTabs;
	}
	public Long getLoyaltyModule_selectedTemplate() {
		return loyaltyModule_selectedTemplate;
	}
	public void setLoyaltyModule_selectedTemplate(
			Long loyaltyModule_selectedTemplate) {
		this.loyaltyModule_selectedTemplate = loyaltyModule_selectedTemplate;
	}
	public Integer getLoyaltyModule_tabIndex() {
		return loyaltyModule_tabIndex;
	}
	public void setLoyaltyModule_tabIndex(Integer loyaltyModule_tabIndex) {
		this.loyaltyModule_tabIndex = loyaltyModule_tabIndex;
	}
	public Boolean getShowEditAction() {
		return showEditAction;
	}
	public void setShowEditAction(Boolean showEditAction) {
		this.showEditAction = showEditAction;
	}
	public Boolean getShowUpdateAction() {
		return showUpdateAction;
	}
	public void setShowUpdateAction(Boolean showUpdateAction) {
		this.showUpdateAction = showUpdateAction;
	}
	public Long getMarketingModule_selectedCampaign() {
		return marketingModule_selectedCampaign;
	}
	public void setMarketingModule_selectedCampaign(
			Long marketingModule_selectedCampaign) {
		this.marketingModule_selectedCampaign = marketingModule_selectedCampaign;
	}
	public int getMarketingModule_tabIndex() {
		return marketingModule_tabIndex;
	}
	public void setMarketingModule_tabIndex(int marketingModule_tabIndex) {
		this.marketingModule_tabIndex = marketingModule_tabIndex;
	}
	
	public ArrayList<InnerTabs> getServiceTabs() {
		return serviceTabs;
	}
	public void setServiceTabs(ArrayList<InnerTabs> serviceTabs) {
		this.serviceTabs = serviceTabs;
	}
	public Long getServiceModule_selectedService() {
		return serviceModule_selectedService;
	}
	public void setServiceModule_selectedService(Long serviceModule_selectedService) {
		this.serviceModule_selectedService = serviceModule_selectedService;
	}
	public Integer getServiceModule_tabIndex() {
		return serviceModule_tabIndex;
	}
	public void setServiceModule_tabIndex(Integer serviceModule_tabIndex) {
		this.serviceModule_tabIndex = serviceModule_tabIndex;
	}
	public ArrayList<InnerTabs> getFranchiseTabs() {
		return franchiseTabs;
	}
	public void setFranchiseTabs(ArrayList<InnerTabs> franchiseTabs) {
		this.franchiseTabs = franchiseTabs;
	}
	public Long getFranchiseModule_selectedFranchise() {
		return franchiseModule_selectedFranchise;
	}
	public void setFranchiseModule_selectedFranchise(
			Long franchiseModule_selectedFranchise) {
		this.franchiseModule_selectedFranchise = franchiseModule_selectedFranchise;
	}
	public Integer getFranchiseModule_tabIndex() {
		return franchiseModule_tabIndex;
	}
	public void setFranchiseModule_tabIndex(Integer franchiseModule_tabIndex) {
		this.franchiseModule_tabIndex = franchiseModule_tabIndex;
	}
	public String getPersistence() {
		return persistence;
	}
	public void setPersistence(String persistence) {
		this.persistence = persistence;
	}	
	
	public String getSelectedApplication() {
		return selectedApplication;
	}
	public void setSelectedApplication(String selectedApplication) {
		this.selectedApplication = selectedApplication;
	}
	public ArrayList<InnerTabs> getMtTabs() {
		return mtTabs;
	}
	public void setMtTabs(ArrayList<InnerTabs> mtTabs) {
		this.mtTabs = mtTabs;
	}
	public Long getMtModule_selectedTemplate() {
		return mtModule_selectedTemplate;
	}
	public void setMtModule_selectedTemplate(Long mtModule_selectedTemplate) {
		this.mtModule_selectedTemplate = mtModule_selectedTemplate;
	}
	public int getMtModule_tabIndex() {
		return mtModule_tabIndex;
	}
	public void setMtModule_tabIndex(int mtModule_tabIndex) {
		this.mtModule_tabIndex = mtModule_tabIndex;
	}
	//extra functions
	public void initializeGroupModule(){
		groupTabs.add(new InnerTabs(getProperty("title.details"),
				"/view/um/admin/groups/groupNoSelection.xhtml", "detailsContainer"));
		groupTabs.add(new InnerTabs(getProperty("title.users"),
				"/view/um/admin/groups/groupNoSelection.xhtml", "usersContainer"));
		groupTabs.add(new InnerTabs(getProperty("title.manageServices"),
				"/view/um/admin/groups/groupsOperations/groupOperationList.xhtml",
				"groupOperationsContainer"));
		groupTabs.add(new InnerTabs(getProperty("title.permissions"),
				"/view/um/admin/groups/groupNoSelection.xhtml", "privilegesContainer"));
		
		groupModule_selectedGroup = new Long(0L);
		groupModule_selectedUser = new Long(0L);
		groupModule_tabIndex = new Integer(0);
	}
	public void resetGroupModule(){
		groupTabs.get(0).setPath("/view/um/admin/groups/groupNoSelection.xhtml");
		groupTabs.get(1).setPath("/view/um/admin/groups/groupNoSelection.xhtml");
		groupTabs.get(2).setPath("/view/um/admin/groups/groupsOperations/groupOperationList.xhtml");
		groupTabs.get(3).setPath("/view/um/admin/groups/groupNoSelection.xhtml");
		groupModule_selectedGroup = 0L;
		groupModule_selectedUser = 0L;
		groupModule_tabIndex = 0;
	}
	public void initializeUserModule(){
		userTabs.add(new InnerTabs(getProperty("title.details"), "/view/um/admin/user/userNoSelection.xhtml",
				"detailsContainer"));
		userTabs.add(new InnerTabs(getProperty("title.groups"),
				"/view/um/admin/user/userNoSelection.xhtml",
				"groupsContainer"));
		userTabs.add(new InnerTabs(getProperty("title.userHistory"),
				"/view/um/admin/user/userNoSelection.xhtml",
				"usersHistoryContainer"));
		
		userModule_selectedUser = 0L;
		userModule_tabIndex = 0;
	}
	public void resetUserModule(){
		userTabs.get(0).setPath("/view/um/admin/user/userNoSelection.xhtml");
		userTabs.get(1).setPath("/view/um/admin/user/userNoSelection.xhtml");
		userTabs.get(2).setPath("/view/um/admin/user/userNoSelection.xhtml");
		
		userModule_selectedUser = 0L;
		userModule_tabIndex = 0;
	}
	
	public void initializeLoyaltyTemplateModule(){
		loyaltyTemplateTabs.add(new InnerTabs(getProperty("title.details"),
				"/view/loyalty/messageTemplate/templateNoSelection.xhtml", "templateContainer"));
		
		loyaltyModule_selectedTemplate = new Long(0L);
		loyaltyModule_tabIndex = new Integer(0);
	}
	public void resetLoyaltyTemplateModule(){
		loyaltyTemplateTabs.get(0).setPath("/view/loyalty/messageTemplate/templateNoSelection.xhtml");
		
		loyaltyModule_selectedTemplate = 0L;
		loyaltyModule_tabIndex = 0;
		showEditAction = true;
		showUpdateAction = true;
	}
	
	public void initializeMarketingModule() {
		marketingTabs.add(new InnerTabs(getProperty("title.details"),
				"/view/marketing/campaign/campaignNoSelection.xhtml", "detailsContainer"));
		marketingTabs.add(new InnerTabs(getProperty("title.setings"),
				"/view/marketing/campaign/campaignNoSelection.xhtml", "settingsContainer"));
		marketingTabs.add(new InnerTabs(getProperty("title.leads"),
				"/view/marketing/campaign/campaignNoSelection.xhtml", "leadsContainer"));
		
		marketingModule_selectedCampaign = 0L;
	}
	
	public void resetMarketingModule() {
		marketingTabs.get(0).setPath("/view/marketing/campaign/campaignNoSelection.xhtml");
		marketingTabs.get(1).setPath("/view/marketing/campaign/campaignNoSelection.xhtml");
		marketingTabs.get(2).setPath("/view/marketing/campaign/campaignNoSelection.xhtml");
		
		marketingModule_selectedCampaign = 0L;
	}
	
	public void initializeServiceModule(){
		serviceTabs.add(new InnerTabs(getProperty("title.details"),"/view/um/admin/manageServices/serviceNoSelection.xhtml","serviceContainer"));
		serviceModule_selectedService = new Long(0L);
		serviceModule_tabIndex = new Integer(0);
		
	}
	
	public void resetServiceModule(){
		serviceTabs.get(0).setPath("/view/um/admin/manageServices/serviceNoSelection.xhtml");
		serviceModule_selectedService = 0L;
		serviceModule_tabIndex = 0;
	}
	public void initializeCompanyModule(){
		companyTabs.add(new InnerTabs(getProperty("title.company"),"/view/um/admin/company/companyNoSelection.xhtml","companyContainer"));
		companyTabs.add(new InnerTabs(getProperty("title.parameter"),"/view/um/admin/company/companyNoSelection.xhtml","parameterContainer"));
		
		companyModule_selectedCompany = new Long(0L);
		companyModule_selectedParameter = 0;
		companyModule_tabIndex = new Integer(0);
	}
	public void resetCompanyModule(){
		companyTabs.get(0).setPath("/view/um/admin/company/companyNoSelection.xhtml");
		companyTabs.get(1).setPath("/view/um/admin/company/companyNoSelection.xhtml");
		companyModule_selectedCompany = 0L;
		companyModule_selectedParameter = 0;
		companyModule_tabIndex = 0;
		
	}
	
	public void initializeFranchiseModule(){
		franchiseTabs.add(new InnerTabs(getProperty("title.details"),"/view/um/admin/franchise/franchiseNoSelection.xhtml","franchiseCOntainer"));
		franchiseModule_selectedFranchise = new Long(0L);
		franchiseModule_tabIndex = new Integer(0);
	}
	public void resetFranchiseModule(){
		franchiseTabs.get(0).setPath("/view/um/admin/franchise/franchiseNoSelection.xhtml");
		franchiseModule_selectedFranchise = 0L;
		franchiseModule_tabIndex = 0;
	}
	public void initializeMTModule(){
		mtTabs.add(new InnerTabs(getProperty("title.details"), "/view/mailTemplates/mailTemplatesNoSelection.xhtml", "templateContainer"));
		mtModule_selectedTemplate = new Long(0L);
		mtModule_tabIndex = new Integer(0);
	}
	public void resetMTModule(){
		mtTabs.get(0).setPath("view/mailTemplates/mailTemplatesNoSelection.xhtml");
		mtModule_selectedTemplate = 0L;
		mtModule_tabIndex = 0;
	}
}
