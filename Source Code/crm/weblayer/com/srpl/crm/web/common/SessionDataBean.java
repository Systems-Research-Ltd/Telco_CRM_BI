package com.srpl.crm.web.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;


import com.srpl.crm.ejb.entity.OrderDetailORM;

import com.bitguiders.util.ResourceBundle;
import com.srpl.crm.web.model.InvoiceBackingBean;
import com.srpl.crm.web.model.loyalty.LoyaltyRules;

/**
 * @author sysres-21
 * 
 */
@ManagedBean(eager = true)
@SessionScoped
public class SessionDataBean implements Serializable {
	/**
	 * 
	 */

	public SessionDataBean() {
		this.showPopUp = true;
		
		//TODO
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
		//Set Contact Tabs
		contactTabs = new ArrayList<InnerTabs>();
		initializeCustomerContactModule();
		//Set Account Tabs
		accountTabs = new ArrayList<InnerTabs>();
		initializeCustomerAccountModule();
		//Set Lead Tabs
		leadTabs = new ArrayList<InnerTabs>();;
		initializeSalesLeadModule();
		//Set Product Tabs
		productTabs = new ArrayList<InnerTabs>();;
		initializeSalesProductModule();
		//Set Opportunity Tabs
		opportunityTabs = new ArrayList<InnerTabs>();
		initializeOpportunityModule();
		//Set order Tabs
		orderTabs = new ArrayList<InnerTabs>();
		initializeSalesOrderModule();
		//Set Document Tabs
		documentTabs = new ArrayList<InnerTabs>();
		initializeDocumentModule();
		//Set Loyalty Tabs
		loyaltyTabs = new ArrayList<InnerTabs>();
		initializeLoyaltyModule();
		//Set Package Tabs
		packageTabs =  new ArrayList<InnerTabs>();
		initializePackageModule();
		
		
	}
	private static final long serialVersionUID = 1L;
	
	private boolean showPopUp;
	private Long companyId;
	private Long franchiseId;
	private Long userId;
	private String userFname;
	private String userLname;
	private String userPicture;
	String row_id;
	private String cssPath;
	private String themeTitle;
	private String companyLogo;
	
	
	private int selectedCountry;
	private int selectedState;
	
	private Long supportModule_selectedCase;
	
	private Long companyForParameter;
	private Long campaingForLead;
	
	private InvoiceBackingBean selectedInvoice;
	
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
	public String getUserPicture() {
		return userPicture;
	}
	public void setUserPicture(String userPicture) {
		this.userPicture = userPicture;
	}
	public Long getCompanyForParameter() {
		return companyForParameter;
	}
	public void setCompanyForParameter(Long companyForParameter) {
		this.companyForParameter = companyForParameter;
	}
	
	public String getCompanyLogo() {
		return companyLogo;
	}
	public void setCompanyLogo(String companyLogo) {
		this.companyLogo = companyLogo;
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
	//marketing module session variables
	private ArrayList<InnerTabs> marketingTabs;
	private Long marketingModule_selectedCampaign;
	private int marketingModule_tabIndex;
	private int supportModule_tabIndex;
	//group module session variables
	private ArrayList<InnerTabs> groupTabs;
	private Long groupModule_selectedGroup;
	private Long groupModule_selectedUser;
	private Integer groupModule_tabIndex;
	//group module session variables
	private ArrayList<InnerTabs> userTabs;
	private Long userModule_selectedUser;
	private Integer userModule_tabIndex;
	//loyalty template module session variables
	private ArrayList<InnerTabs> loyaltyTemplateTabs;
	private Long loyaltyModule_selectedTemplate;
	private Integer loyaltyModule_tabIndex;
	private Boolean showEditAction = true;
	private Boolean showUpdateAction = true;
	//contacts session variable
	private ArrayList<InnerTabs> contactTabs;
	private Long customerModule_selectedContact;
	private Integer customerModule_contactTabIndex;
	private long customerModule_selectedInvoice;
	//accounts session variable
	private ArrayList<InnerTabs> accountTabs;
	private Long customerModule_selectedAccount;
	private Integer customerModule_accountTabIndex;
	//leads session variable
	private ArrayList<InnerTabs> leadTabs;
	private Long salesModule_selectedLead;
	private Integer salesModule_leadTabIndex;
	//products session variable
	private ArrayList<InnerTabs> productTabs;
	private Long salesModule_selectedProduct;
	private Integer salesModule_productTabIndex;
	//opportunity seesion variables
	private ArrayList<InnerTabs> opportunityTabs;
	private Long opportunityModule_selectedOpportunity;
	private int opportunityModule_opportunityTabIndex;
	//order session variable
	private ArrayList<InnerTabs> orderTabs;
	private Long salesModule_selectedOrder; 
	private int salesModule_orderTabIndex;
	private List<OrderDetailORM> orderDetailList = new ArrayList<OrderDetailORM>();
	//document session variables
	private ArrayList<InnerTabs> documentTabs;
	private Long documentModule_selectedDocument;
	private int documentModule_tabIndex;
	//loyalty session variables
	private ArrayList<InnerTabs> loyaltyTabs;
	private Long loyaltyModule_selectedLoyalty;
	private Integer loyaltyModule_loyaltyTabIndex;
	private List<LoyaltyRules> loyaltyModule_loyaltyRules;
	//package session variables
	private ArrayList<InnerTabs> packageTabs;
	private Long salesModule_selectedPackage;
	private Integer salesModule_packageTabIndex;
	
	
	
	//Campaign Products
	private List<Long> campaignProducts = new ArrayList<Long>();
	
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

	public InvoiceBackingBean getSelectedInvoice() {
		return selectedInvoice;
	}

	public void setSelectedInvoice(InvoiceBackingBean selectedInvoice) {
		this.selectedInvoice = selectedInvoice;
	}

	public String getCssPath() {
		return cssPath;
	}

	public void setCssPath(String cssPath) {
		this.cssPath = cssPath;
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

	public Long getSupportModule_selectedCase() {
		return supportModule_selectedCase;
	}

	public void setSupportModule_selectedCase(Long supportModule_selectedCase) {
		this.supportModule_selectedCase = supportModule_selectedCase;
	}

	public int getMarketingModule_tabIndex() {
		return marketingModule_tabIndex;
	}

	public void setMarketingModule_tabIndex(int marketingModule_tabIndex) {
		this.marketingModule_tabIndex = marketingModule_tabIndex;
	}

	public int getSupportModule_tabIndex() {
		return supportModule_tabIndex;
	}

	public void setSupportModule_tabIndex(int supportModule_tabIndex) {
		this.supportModule_tabIndex = supportModule_tabIndex;
	}

	public Long getCampaingForLead() {
		return campaingForLead;
	}

	public void setCampaingForLead(Long campaingForLead) {
		this.campaingForLead = campaingForLead;
	}
	public ArrayList<InnerTabs> getContactTabs() {
		return contactTabs;
	}

	public void setContactTabs(ArrayList<InnerTabs> contactTabs) {
		this.contactTabs = contactTabs;
	}

	public Long getCustomerModule_selectedContact() {
		return customerModule_selectedContact;
	}

	public void setCustomerModule_selectedContact(
			Long customerModule_selectedContact) {
		this.customerModule_selectedContact = customerModule_selectedContact;
	}

	public Integer getCustomerModule_contactTabIndex() {
		return customerModule_contactTabIndex;
	}

	public void setCustomerModule_contactTabIndex(
			Integer customerModule_contactTabIndex) {
		this.customerModule_contactTabIndex = customerModule_contactTabIndex;
	}

	public long getCustomerModule_selectedInvoice() {
		return customerModule_selectedInvoice;
	}
	public void setCustomerModule_selectedInvoice(long customerModule_selectedInvoice) {
		this.customerModule_selectedInvoice = customerModule_selectedInvoice;
	}
	public ArrayList<InnerTabs> getAccountTabs() {
		return accountTabs;
	}

	public void setAccountTabs(ArrayList<InnerTabs> accountTabs) {
		this.accountTabs = accountTabs;
	}

	public Long getCustomerModule_selectedAccount() {
		return customerModule_selectedAccount;
	}

	public void setCustomerModule_selectedAccount(
			Long customerModule_selectedAccount) {
		this.customerModule_selectedAccount = customerModule_selectedAccount;
	}

	public Integer getCustomerModule_accountTabIndex() {
		return customerModule_accountTabIndex;
	}

	public void setCustomerModule_accountTabIndex(
			Integer customerModule_accountTabIndex) {
		this.customerModule_accountTabIndex = customerModule_accountTabIndex;
	}
	public ArrayList<InnerTabs> getLeadTabs() {
		return leadTabs;
	}
	public void setLeadTabs(ArrayList<InnerTabs> leadTabs) {
		this.leadTabs = leadTabs;
	}
	public Long getSalesModule_selectedLead() {
		return salesModule_selectedLead;
	}
	public void setSalesModule_selectedLead(Long salesModule_selectedLead) {
		this.salesModule_selectedLead = salesModule_selectedLead;
	}
	public Integer getSalesModule_leadTabIndex() {
		return salesModule_leadTabIndex;
	}
	public void setSalesModule_leadTabIndex(Integer salesModule_leadTabIndex) {
		this.salesModule_leadTabIndex = salesModule_leadTabIndex;
	}
	
	public ArrayList<InnerTabs> getProductTabs() {
		return productTabs;
	}
	public void setProductTabs(ArrayList<InnerTabs> productTabs) {
		this.productTabs = productTabs;
	}
	public Long getSalesModule_selectedProduct() {
		return salesModule_selectedProduct;
	}
	public void setSalesModule_selectedProduct(Long salesModule_selectedProduct) {
		this.salesModule_selectedProduct = salesModule_selectedProduct;
	}
	public Integer getSalesModule_productTabIndex() {
		return salesModule_productTabIndex;
	}
	public void setSalesModule_productTabIndex(Integer salesModule_productTabIndex) {
		this.salesModule_productTabIndex = salesModule_productTabIndex;
	}
	public ArrayList<InnerTabs> getOpportunityTabs() {
		return opportunityTabs;
	}
	public void setOpportunityTabs(ArrayList<InnerTabs> opportunityTabs) {
		this.opportunityTabs = opportunityTabs;
	}
	public Long getOpportunityModule_selectedOpportunity() {
		return opportunityModule_selectedOpportunity;
	}
	public void setOpportunityModule_selectedOpportunity(
			Long opportunityModule_selectedOpportunity) {
		this.opportunityModule_selectedOpportunity = opportunityModule_selectedOpportunity;
	}
	public int getOpportunityModule_opportunityTabIndex() {
		return opportunityModule_opportunityTabIndex;
	}
	public void setOpportunityModule_opportunityTabIndex(int opportunityModuule_tabIndex) {
		this.opportunityModule_opportunityTabIndex = opportunityModuule_tabIndex;
	}
	public List<Long> getCampaignProducts() {
		return campaignProducts;
	}
	public void setCampaignProducts(List<Long> campaignProducts) {
		this.campaignProducts = campaignProducts;
	}
	
	public ArrayList<InnerTabs> getOrderTabs() {
		return orderTabs;
	}
	public void setOrderTabs(ArrayList<InnerTabs> orderTabs) {
		this.orderTabs = orderTabs;
	}
	public Long getSalesModule_selectedOrder() {
		return salesModule_selectedOrder;
	}
	public List<OrderDetailORM> getOrderDetailList() {
		return orderDetailList;
	}
	public void setOrderDetailList(List<OrderDetailORM> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}
	public void setSalesModule_selectedOrder(Long salesModule_selectedOrder) {
		this.salesModule_selectedOrder = salesModule_selectedOrder;
	}
	public int getSalesModule_orderTabIndex() {
		return salesModule_orderTabIndex;
	}
	public void setSalesModule_orderTabIndex(int salesModule_orderTabIndex) {
		this.salesModule_orderTabIndex = salesModule_orderTabIndex;
	}
	public ArrayList<InnerTabs> getDocumentTabs() {
		return documentTabs;
	}
	public void setDocumentTabs(ArrayList<InnerTabs> documentTabs) {
		this.documentTabs = documentTabs;
	}
	public Long getDocumentModule_selectedDocument() {
		return documentModule_selectedDocument;
	}
	public void setDocumentModule_selectedDocument(
			Long documentModule_selectedDocument) {
		this.documentModule_selectedDocument = documentModule_selectedDocument;
	}
	public int getDocumentModule_tabIndex() {
		return documentModule_tabIndex;
	}
	public void setDocumentModule_tabIndex(int documentModule_tabIndex) {
		this.documentModule_tabIndex = documentModule_tabIndex;
	}
	public ArrayList<InnerTabs> getLoyaltyTabs() {
		return loyaltyTabs;
	}
	public void setLoyaltyTabs(ArrayList<InnerTabs> loyaltyTabs) {
		this.loyaltyTabs = loyaltyTabs;
	}
	public Long getLoyaltyModule_selectedLoyalty() {
		return loyaltyModule_selectedLoyalty;
	}
	public void setLoyaltyModule_selectedLoyalty(Long loyaltyModule_selectedLoyalty) {
		this.loyaltyModule_selectedLoyalty = loyaltyModule_selectedLoyalty;
	}
	public Integer getLoyaltyModule_loyaltyTabIndex() {
		return loyaltyModule_loyaltyTabIndex;
	}
	public void setLoyaltyModule_loyaltyTabIndex(
			Integer loyaltyModule_loyaltyTabIndex) {
		this.loyaltyModule_loyaltyTabIndex = loyaltyModule_loyaltyTabIndex;
	}
	public List<LoyaltyRules> getLoyaltyModule_loyaltyRules() {
		return loyaltyModule_loyaltyRules;
	}
	public void setLoyaltyModule_loyaltyRules(
			List<LoyaltyRules> loyaltyModule_loyaltyRules) {
		this.loyaltyModule_loyaltyRules = loyaltyModule_loyaltyRules;
	}
	public ArrayList<InnerTabs> getPackageTabs() {
		return packageTabs;
	}
	public void setPackageTabs(ArrayList<InnerTabs> packageTabs) {
		this.packageTabs = packageTabs;
	}
	public Long getSalesModule_selectedPackage() {
		return salesModule_selectedPackage;
	}
	public void setSalesModule_selectedPackage(Long salesModule_selectedPackage) {
		this.salesModule_selectedPackage = salesModule_selectedPackage;
	}
	public Integer getSalesModule_packageTabIndex() {
		return salesModule_packageTabIndex;
	}
	public void setSalesModule_packageTabIndex(Integer salesModule_packageTabIndex) {
		this.salesModule_packageTabIndex = salesModule_packageTabIndex;
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
		loyaltyModule_loyaltyRules = new ArrayList<LoyaltyRules>();
		loyaltyTemplateTabs.add(new InnerTabs(getProperty("title.details"),
				"/view/loyalty/messageTemplate/templateNoSelection.xhtml", "templateContainer"));
		
		loyaltyModule_selectedTemplate = new Long(0L);
		loyaltyModule_tabIndex = new Integer(0);
	}
	public void resetLoyaltyTemplateModule(){
		loyaltyModule_loyaltyRules = new ArrayList<LoyaltyRules>();
		loyaltyTemplateTabs.get(0).setPath("/view/loyalty/messageTemplate/templateNoSelection.xhtml");
		
		loyaltyModule_selectedTemplate = 0L;
		loyaltyModule_tabIndex = 0;
		showEditAction = true;
		showUpdateAction = true;
	}
	
	public void initializeMarketingModule() {
		marketingTabs.add(new InnerTabs(getProperty("title.details"),
				"/view/marketing/campaign/campaignNoSelection.xhtml", "detailsContainer"));
		marketingTabs.add(new InnerTabs(getProperty("title.settings"),
				"/view/marketing/campaign/campaignNoSelection.xhtml", "settingsContainer"));
		marketingTabs.add(new InnerTabs(getProperty("title.leads"),
				"/view/marketing/campaign/campaignNoSelection.xhtml","leadsContainer"));
		marketingTabs.add(new InnerTabs(getProperty("title.products"),
				"/view/marketing/campaign/campaignNoSelection.xhtml","productsContainer"));
		
		marketingModule_selectedCampaign = 0L;
	}
	
	public void resetMarketingModule() {
		marketingTabs.get(0).setPath("/view/marketing/campaign/campaignNoSelection.xhtml");
		marketingTabs.get(1).setPath("/view/marketing/campaign/campaignNoSelection.xhtml");
		marketingTabs.get(2).setPath("/view/marketing/campaign/campaignNoSelection.xhtml");
		marketingTabs.get(3).setPath("/view/marketing/campaign/campaignNoSelection.xhtml");
		
		marketingModule_selectedCampaign = 0L;
	}

	public void initializeCustomerContactModule(){
		contactTabs.add(new InnerTabs(getProperty("title.details"),"/view/customer/contacts/contactNoSelection.xhtml","contactContainer"));
		contactTabs.add(new InnerTabs(getProperty("title.subscribe"),"/view/customer/contacts/c360view/subscribe/index.xhtml","subscribeContainer"));
		contactTabs.add(new InnerTabs(getProperty("title.invoice"),"/view/customer/contacts/c360view/billing/index.xhtml","invoiceContainer"));
		contactTabs.add(new InnerTabs(getProperty("title.notes"),"/view/customer/contacts/c360view/notes/index.xhtml","notesContainer"));
		contactTabs.add(new InnerTabs(getProperty("title.provisioning"),"/view/customer/contacts/c360view/provisioning/index.xhtml","provisioningContainer"));
		//contactTabs.add(new InnerTabs("Payment History","/view/customer/contacts/contactNoSelection.xhtml","payHisContainer"));
		/*contactTabs.add(new InnerTabs("Pay Now","/view/customer/contacts/contactNoSelection.xhtml","payNowContainer"));
		contactTabs.add(new InnerTabs("Orders","/view/customer/contacts/contactNoSelection.xhtml","ordersContainer"));
		contactTabs.add(new InnerTabs("Support","/view/customer/contacts/contactNoSelection.xhtml","supportContainer"));*/
		contactTabs.add(new InnerTabs(getProperty("title.billing"),"/view/customer/contacts/c360view/billing/customerBill.xhtml","billingContainer"));
		customerModule_selectedContact = new Long(0L);
		customerModule_contactTabIndex = new Integer(0);
		customerModule_selectedInvoice = 0;
	}
	
	public void resetCustomerContactModule(){
		contactTabs.get(0).setPath("/view/customer/contacts/contactNoSelection.xhtml");
		contactTabs.get(1).setPath("/view/customer/contacts/c360view/subscribe/index.xhtml");
		contactTabs.get(2).setPath("/view/customer/contacts/c360view/billing/index.xhtml");
		contactTabs.get(3).setPath("/view/customer/contacts/c360view/notes/index.xhtml");
		contactTabs.get(4).setPath("/view/customer/contacts/c360view/provisioning/index.xhtml");
		contactTabs.get(5).setPath("/view/customer/contacts/c360view/billing/customerBill.xhtml");
	/*	contactTabs.get(4).setPath("/view/customer/contacts/contactNoSelection.xhtml");
		contactTabs.get(5).setPath("/view/customer/contacts/contactNoSelection.xhtml");
		contactTabs.get(6).setPath("/view/customer/contacts/contactNoSelection.xhtml");
		contactTabs.get(7).setPath("/view/customer/contacts/contactNoSelection.xhtml");
	*/	
		customerModule_selectedContact =0L;
		customerModule_contactTabIndex =0;
		customerModule_selectedInvoice = 0;
	}
	public void initializeCustomerAccountModule(){
		accountTabs.add(new InnerTabs(getProperty("title.details"),"/view/customer/accounts/accountNoSelection.xhtml","accountContainer"));
		customerModule_selectedAccount = new Long(0L);
		customerModule_accountTabIndex = new Integer(0);
	}
	public void resetCustomerAccountModule(){
		accountTabs.get(0).setPath("/view/customer/accounts/accountNoSelection.xhtml");
		customerModule_selectedAccount = 0L;
		customerModule_accountTabIndex = 0;
	}
	public void initializeSalesLeadModule(){
		leadTabs.add(new InnerTabs(getProperty("title.details"),"/view/sales/leads/leadNoSelection.xhtml","leadContainer"));
		salesModule_selectedLead = new Long(0L);
		salesModule_leadTabIndex = new Integer(0);
	}
	public void resetSalesLeadModule(){
		leadTabs.get(0).setPath("/view/sales/leads/leadNoSelection.xhtml");
		salesModule_selectedLead =0L;
		salesModule_leadTabIndex =0;
	}
	public void initializeSalesProductModule(){
		productTabs.add(new InnerTabs(getProperty("title.details"),"/view/sales/products/productNoSelection.xhtml","productContainer"));
		salesModule_selectedProduct = new Long(0L);
		salesModule_productTabIndex = new Integer(0);
	}
	public void resetSalesProductModule(){
		productTabs.get(0).setPath("/view/sales/products/productNoSelection.xhtml");
		salesModule_selectedProduct =0L;
		salesModule_productTabIndex =0;
	}
	public void initializeOpportunityModule(){
		opportunityTabs.add(new InnerTabs(getProperty("title.details"), "/view/sales/opportunities/opportunityNoSelection.xhtml", "opportunityContainer"));
		opportunityModule_selectedOpportunity = new Long(0L);
		opportunityModule_opportunityTabIndex = new Integer(0);
	}
	public void resetOpportunityModule(){
		opportunityTabs.get(0).setPath("/view/sales/opportunities/opportunityNoSelection.xhtml");
		opportunityModule_selectedOpportunity = 0L;
		opportunityModule_opportunityTabIndex = 0;
	}
	public void initializeSalesOrderModule(){
		orderTabs.add(new InnerTabs(getProperty("title.details"),"/view/sales/orders/orderNoSelection.xhtml","orderContainer"));
		salesModule_selectedOrder = new Long(0L);
		salesModule_orderTabIndex = new Integer(0);
	}
	public void resetSalesOrderModule(){
		orderTabs.get(0).setPath("/view/sales/orders/orderNoSelection.xhtml");
		salesModule_selectedOrder = 0L;
		salesModule_orderTabIndex = 0;
		
	}
	public void initializeDocumentModule(){
		documentTabs.add(new InnerTabs(getProperty("title.details"), "/view/documents/documentNoSelection.xhtml", "opportunityContainer"));
		documentModule_selectedDocument = new Long(0L);
		documentModule_tabIndex = new Integer(0);
	}
	public void resetDocumentModule(){
		documentTabs.get(0).setPath("view/documents/documentNoSelection.xhtml");
		documentModule_selectedDocument = 0L;
		documentModule_tabIndex = 0;
	}
	public void initializeLoyaltyModule(){
		loyaltyTabs.add(new InnerTabs(getProperty("title.details"),"/view/loyalty/loyaltyNoSelection.xhtml","loyaltyContainer"));
		loyaltyModule_selectedLoyalty = new Long(0L);
		loyaltyModule_tabIndex = new Integer(0);
		
	}
	public void resetLoyaltyModule(){
		loyaltyTabs.get(0).setPath("/view/loyalty/loyaltyNoSelection.xhtml");
		loyaltyModule_selectedLoyalty = 0L;
		loyaltyModule_tabIndex = 0;
	}
	public void initializePackageModule(){
		packageTabs.add(new InnerTabs(getProperty("title.details"),"/view/sales/packages/packageNoSelection.xhtml","packageContainer"));
		salesModule_selectedPackage = new Long(0L);
		salesModule_packageTabIndex = new Integer(0);
		
	}
	public void resetPackageModule(){
		packageTabs.get(0).setPath("/view/sales/packages/packageNoSelection.xhtml");
		salesModule_selectedPackage = 0L;
		salesModule_packageTabIndex = 0;
	}
}
