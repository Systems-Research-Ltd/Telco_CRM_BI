package com.srpl.crm.web.model.support;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;

import org.primefaces.event.ToggleEvent;

import com.bitguiders.util.jsf.JSFBeanInterface;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;
import com.srpl.crm.web.controller.BeanFactory;
import com.srpl.crm.ejb.entity.CsContactORM;
import com.srpl.crm.ejb.entity.ProductORM;
import com.srpl.crm.ejb.entity.SupportCaseCommentORM;
import com.srpl.crm.ejb.entity.SupportCaseHistoryORM;
import com.srpl.crm.ejb.entity.SupportCaseORM;
import com.srpl.crm.ejb.entity.SupportQueryTypeORM;
import com.srpl.crm.ejb.entity.UmAlertsAndReminders;
import com.srpl.crm.ejb.exceptions.CaseNotFoundException;
import com.srpl.crm.ejb.exceptions.ContactNotFoundException;
import com.srpl.crm.ejb.exceptions.ProductNotFoundException;
import com.srpl.crm.ejb.exceptions.QueryTypeNotFoundException;
import com.srpl.crm.ejb.request.AlertsAndRemindersDAO;
import com.srpl.crm.ejb.request.CaseCommentDAO;
import com.srpl.crm.ejb.request.CaseDAO;
import com.srpl.crm.ejb.request.CaseHistoryDAO;
import com.srpl.crm.ejb.request.ContactDAO;
import com.srpl.crm.ejb.request.ProductDAO;
import com.srpl.crm.ejb.request.QueryTypeDAO;
import com.srpl.crm.web.common.SessionDataBean;
import com.srpl.crm.web.model.common.ColumnModel;
import com.srpl.crm.web.model.report.ReportSql;
import com.srpl.um.ejb.entity.UmCompany;
import com.srpl.um.ejb.entity.UmGroup;
import com.srpl.um.ejb.entity.UmUser;
import com.srpl.um.ejb.exceptions.UserNotFoundException;
import com.srpl.um.ejb.request.CompanyDAO;
import com.srpl.um.ejb.request.UserDAO;

@ManagedBean(name = "caseBean")
public class CaseBackingBean extends JSFBeanSupport implements JSFBeanInterface,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long caseId;
	private String caseTokenNumber;
	private Long customerId;
	private Long productId;
	private Long userId;
	private Long queryTypeId;
	private CsContactORM customer;
	private ProductORM product;
	private UmUser umUser;
	private Long companyId;
	private UmCompany umCompany;
	private SupportQueryTypeORM queryType;
	private String caseType;
	private String status;
	private String mobileNumber;
	private String customerQuery;
	private String comments;
	private String userComments;
	private String assignedcomments;
	private Timestamp caseCreatDate;
	private Timestamp caseAssignedDate;
	private Timestamp caseResolvedDate;
	private List<SupportCaseORM> caseList;
	private List<SupportCaseORM> customerCaseList;
	List<SupportCaseHistoryORM> caseHistoryList;
	List<SupportCaseCommentORM> caseCommentList;
	private List<ColumnModel> columns;
	private List<CsContactORM> customerList;
	private List<ProductORM> productList;
	private List<UmUser> usersList;
	private List<SupportQueryTypeORM> queryTypeList;
	private Boolean renderSupport = true;
	private Boolean renderClosedTicketForm = false;
	private Boolean renderCommentsForm = false;
	private String feedBackComments;
	private String serviceRating;
	private Integer daysOldTicket;
	private SessionDataBean session;
	
	@EJB CaseDAO caseDao;
	@EJB CaseHistoryDAO caseHistoryDao;
	@EJB CaseCommentDAO caseCommentDao;
	@EJB ContactDAO contactDao;
	@EJB UserDAO userDao;
	@EJB ProductDAO productDao;
	@EJB QueryTypeDAO queryTypeDao;
	@EJB AlertsAndRemindersDAO alertsAndRemindersDAO;;
	@EJB CompanyDAO companyDao;
	
	public CaseBackingBean(){
		columns=new ArrayList<ColumnModel>();
		columns.add(new ColumnModel("caseTokenNumber", "Token NO"));
        columns.add(new ColumnModel("productName", "PRODUCT"));
        columns.add(new ColumnModel("caseType", "TYPE"));
        columns.add(new ColumnModel("userName", "ASSIGNED TO"));
        columns.add(new ColumnModel("status", "STATUS"));
        String action = getParameter("action"); 

        if(action != null && (action.equalsIgnoreCase("edit") || action.equalsIgnoreCase("closeTicket") || action.equalsIgnoreCase("submitReview") || action.equalsIgnoreCase("delete"))){
        	setDisabled(true);
        }
        /*        if(getParameter("action") != null && getParameter("action").equalsIgnoreCase("postComments")){
        	if(FacesContext.getCurrentInstance().getExternalContext().isUserInRole("Customer")){
                     //renderSupport = false;            	
            }     
        	
        }*/
        session = BeanFactory.getInstance().getSessionBean();
        setCurrentAction(WebConstants.ACTION_SECURITY,this.getClass());
        
	}
	
	@PostConstruct
	public void postConstruct() {
		if (getAction().equalsIgnoreCase("support")) {
			// no action was called,
			session.setSupportModule_selectedCase(null);
		}
		String qTypeId = this.getParameter("p_queryType");
		if(qTypeId != null && !qTypeId.isEmpty()){
			try{
			  this.queryTypeId = Long.valueOf(qTypeId);
			}catch(Exception e){
				System.out.println("Exception in caseBackingBean postConstruct()");
			}  
		}
		String row_id=getParameter("row_id");
		if(!row_id.isEmpty())
		{
			session.setSupportModule_selectedCase(Long.parseLong(row_id));
			try{
				 Long caseId = session.getSupportModule_selectedCase();
				 loadCase(caseId);
				 setViewAction();
				 setCancelAction(false);
				 renderCommentsForm = true;
				 session.setSupportModule_tabIndex(0);
				}
				catch (Exception e) {
					// handle exception
					addError(getProperty("message.support.error.load.case"));

				}
		}
	}
	
	public void loadCase(Long caseId){
		System.out.println("loadCase called()..");
		this.caseId = caseId;
		//caseId = 
		System.out.println("caseId = "+caseId);
		if(caseId != null && caseId > 0){
			try {
				SupportCaseORM caseView = caseDao.caseDetails(caseId);
				listCasesHistory(caseView);
				listCaseComments(caseView);
				//listCasesHistoryWithComments(caseView);
				this.caseId = caseView.getCaseId();
				this.caseTokenNumber = caseView.getCaseTokenNumber();
				this.customer = caseView.getcustomer();
				this.customerId = caseView.getcustomer().getContactId();
				this.product = caseView.getProduct();
				this.productId = caseView.getProduct().getProductId();
				this.queryType = caseView.getQueryType();
				this.queryTypeId = caseView.getQueryType().getQueryTypeId();
				this.umUser = caseView.getUmUser();
				if(umUser !=null){
					this.userId = caseView.getUmUser().getUserId();	
				}
				this.umCompany = caseView.getUmCompany();
				if(umCompany !=null){
					this.companyId = umCompany.getCompanyId(); 
				}
				this.caseType = caseView.getCaseType();
				this.status = caseView.getStatus();
				this.mobileNumber = caseView.getMobileNumber();
				this.customerQuery = caseView.getCustomerQuery();
				this.comments = caseView.getComments();
				this.assignedcomments = caseView.getComments();
				this.caseCreatDate = caseView.getCaseCreateDate();
				try{
				  this.daysOldTicket = numberOfDaysPassed(this.caseCreatDate);
				}catch(Exception e){
					System.out.println("Exception in numberOfDaysPassed()");
				}
				this.caseAssignedDate = caseView.getCaseAssignedDate();
				this.caseResolvedDate = caseView.getCaseResolvedDate();
				this.serviceRating = caseView.getServiceRating();
				this.feedBackComments = caseView.getFeedBack();
			       
			} catch (CaseNotFoundException e) {
				System.out.println("exception while loading case.");
				e.printStackTrace();
			}
		}
	}
	
	public Long getCaseId() {
		return caseId;
	}
	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}
	public String getCaseTokenNumber() {
		return caseTokenNumber;
	}
	public void setCaseTokenNumber(String caseTokenNumber) {
		this.caseTokenNumber = caseTokenNumber;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getQueryTypeId() {
		return queryTypeId;
	}


	public void setQueryTypeId(Long queryTypeId) {
		this.queryTypeId = queryTypeId;
	}


	public CsContactORM getCustomer() {
		return customer;
	}
	public void setCustomer(CsContactORM customer) {
		this.customer = customer;
	}
	public ProductORM getProduct() {
		return product;
	}
	public void setProduct(ProductORM product) {
		this.product = product;
	}
	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public UmCompany getUmCompany() {
		return umCompany;
	}

	public void setUmCompany(UmCompany umCompany) {
		this.umCompany = umCompany;
	}

	public UmUser getUmUser() {
		return umUser;
	}
	public void setUmUser(UmUser umUser) {
		this.umUser = umUser;
	}
	public SupportQueryTypeORM getQueryType() {
		return queryType;
	}


	public void setQueryType(SupportQueryTypeORM queryType) {
		this.queryType = queryType;
	}


	public String getCaseType() {
		return caseType;
	}
	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}
	public String getStatus() {
		return status;
	}
	public SessionDataBean getSession() {
		return session;
	}

	public void setSession(SessionDataBean session) {
		this.session = session;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}


	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}


	public String getCustomerQuery() {
		return customerQuery;
	}
	public void setCustomerQuery(String customerQuery) {
		this.customerQuery = customerQuery;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}	
	public String getAssignedcomments() {
		return null;
	}
	public void setAssignedcomments(String assignedcomments) {
		this.assignedcomments = assignedcomments;
	}
	public Timestamp getCaseCreatDate() {
		return caseCreatDate;
	}
	public void setCaseCreatDate(Timestamp caseCreatDate) {
		this.caseCreatDate = caseCreatDate;
	}
	public Timestamp getCaseAssignedDate() {
		return caseAssignedDate;
	}


	public void setCaseAssignedDate(Timestamp caseAssignedDate) {
		this.caseAssignedDate = caseAssignedDate;
	}


	public Timestamp getCaseResolvedDate() {
		return caseResolvedDate;
	}
	public void setCaseResolvedDate(Timestamp caseResolvedDate) {
		this.caseResolvedDate = caseResolvedDate;
	}
	public List<SupportCaseORM> getCaseList() {
		return caseList;
	}
	public List<SupportCaseORM> getCustomerCaseList() {
		return customerCaseList;
	}


	public void setCustomerCaseList(List<SupportCaseORM> customerCaseList) {
		this.customerCaseList = customerCaseList;
	}


	public void setCaseList(List<SupportCaseORM> caseList) {
		this.caseList = caseList;
	}
	public List<SupportCaseHistoryORM> getCaseHistoryList() {
		return caseHistoryList;
	}


	public void setCaseHistoryList(List<SupportCaseHistoryORM> caseHistoryList) {
		this.caseHistoryList = caseHistoryList;
	}


	public List<SupportCaseCommentORM> getCaseCommentList() {
		return caseCommentList;
	}


	public void setCaseCommentList(List<SupportCaseCommentORM> caseCommentList) {
		this.caseCommentList = caseCommentList;
	}


	public String getUserComments() {
		return userComments;
	}


	public void setUserComments(String userComments) {
		this.userComments = userComments;
	}


	public List<ColumnModel> getColumns() {
		return columns;
	}
	public void setColumns(List<ColumnModel> columns) {
		this.columns = columns;
	}
	public List<CsContactORM> getCustomerList() {
		return customerList;
	}
	public void setCustomerList(List<CsContactORM> customerList) {
		this.customerList = customerList;
	}
	public List<ProductORM> getProductList() {
		return productList;
	}
	public void setProductList(List<ProductORM> productList) {
		this.productList = productList;
	}
	public List<UmUser> getUsersList() {
		return usersList;
	}
	public void setUsersList(List<UmUser> usersList) {
		this.usersList = usersList;
	}
	
	
	public List<SupportQueryTypeORM> getQueryTypeList() {
		return queryTypeList;
	}


	public void setQueryTypeList(List<SupportQueryTypeORM> queryTypeList) {
		this.queryTypeList = queryTypeList;
	}


	public Boolean getRenderSupport() {
		return renderSupport;
	}



	public void setRenderSupport(Boolean renderSupport) {
		this.renderSupport = renderSupport;
	}



	public Boolean getRenderClosedTicketForm() {
		return renderClosedTicketForm;
	}



	public void setRenderClosedTicketForm(Boolean renderClosedTicketForm) {
		this.renderClosedTicketForm = renderClosedTicketForm;
	}



	public Boolean getRenderCommentsForm() {
		return renderCommentsForm;
	}



	public void setRenderCommentsForm(Boolean renderCommentsForm) {
		this.renderCommentsForm = renderCommentsForm;
	}



	public String getFeedBackComments() {
		return feedBackComments;
	}



	public void setFeedBackComments(String feedBackComments) {
		this.feedBackComments = feedBackComments;
	}



	public String getServiceRating() {
		return serviceRating;
	}



	public void setServiceRating(String serviceRating) {
		this.serviceRating = serviceRating;
	}



	public Integer getDaysOldTicket() {
		return daysOldTicket;
	}



	public void setDaysOldTicket(Integer daysOldTicket) {
		this.daysOldTicket = daysOldTicket;
	}



	
	public List<SupportCaseORM> listCases(){
		try{
			caseList = caseDao.listCases();
	    }catch(CaseNotFoundException e){
	    	System.out.println("Exception Occured : CaseBackingBean listCases()");
			//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage(), e.getMessage()));
		}
		return caseList;
	}
	
	public List<SupportCaseORM> listActiveCases(){
		UmCompany umCompany = null;
		try{
			umCompany = companyDao.companyDetails(session.getCompanyId());
		}catch(Exception e){
			
		}
		if(FacesContext.getCurrentInstance().getExternalContext().isUserInRole("Customer")){
			CsContactORM customer = new CsContactORM();
			try{
			   customer = contactDao.getContactByUserId(session.getUserId());
			   caseList = caseDao.listCustomerActiveCases(customer);
		      }catch(Exception e){
		    	  System.out.println("Exception Occured : CaseBackingBean listCases()");
				  //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage(), e.getMessage()));
			}
		}else{
		  try{
			  List<UmGroup> userGroups = new ArrayList<UmGroup>();
			  userGroups = userDao.getUserGroups(session.getUserId(), session.getCompanyId());
			  long userGroupId = 0;
			  caseList = caseDao.listActiveCases(umCompany);
			  Iterator<SupportCaseORM> caseIterator = caseList.iterator();
			  SupportCaseORM ticket = new SupportCaseORM();
			  boolean remove = false;
			  while(caseIterator.hasNext()){
				  try{
					ticket = caseIterator.next();
				    long ticketGroupId = ticket.getQueryType().getUmGroup().getGroupId();
				    for(UmGroup umGroup : userGroups){
				    	userGroupId = umGroup.getGroupId();
				    	if(userGroupId == ticketGroupId){
				    		remove = false;
						    break;
					    }else{
					    	remove = true;
					    }
				    }
				    if(remove){
				    	caseIterator.remove();
				    }
				    if(ticket.getUmUser() != null){
				    	// ticket user id is the user to whom this ticket is assigned
                        // session.getUserId is the logged in user
				    	long userId = ticket.getUmUser().getUserId();
				    	if(userId == session.getUserId()){
				    		// this ticket is assigned to the logged in user
				    		ticket.setAssignedToLoginUser("*");
				    	}
				    }
				  }catch(Exception e){
					 System.out.println("Exception in while");
					 System.out.println(e.getMessage());
				  }  
			  }
	      }catch(Exception e){
	    	  System.out.println("Exception Occured : CaseBackingBean listCases()");
			  //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage(), e.getMessage()));
	    	  System.out.println(e.getMessage());
		  }
		}  
		return caseList;
	}
	
	public List<SupportCaseORM> listClosedCases(){
		UmCompany umCompany = null;
		try{
			umCompany = companyDao.companyDetails(session.getCompanyId());
		}catch(Exception e){
			
		}
		
		if(FacesContext.getCurrentInstance().getExternalContext().isUserInRole("Customer")){
			CsContactORM customer = new CsContactORM();
			try{
			   customer = contactDao.getContactByUserId(session.getUserId());
			}catch(ContactNotFoundException e){
				System.out.println("Exception Occured CaseBackingBean listCustomerCases()");
			}
			try{
				  caseList = caseDao.listCustomerClosedCases(customer);
		      }catch(CaseNotFoundException e){
		    	  System.out.println("Exception Occured : CaseBackingBean listCases()");
				  //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage(), e.getMessage()));
			}
		}else{
			try{
				caseList = caseDao.listClosedCases(umCompany);
		    }catch(CaseNotFoundException e){
		    	System.out.println("Exception Occured : CaseBackingBean listCases()");
				//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage(), e.getMessage()));
			}
		}
		return caseList;
	}
	
	public List<SupportCaseORM> listCustomerCases(){
		try{
			System.out.println("CaseBackingBean listCustomerCases() called...");
			BeanFactory beanFactory = BeanFactory.getInstance(); 	
			SessionDataBean session = beanFactory.getSessionBean();
			CsContactORM customer = new CsContactORM();
			try{
			   customer = contactDao.getContactByUserId(session.getUserId());
			}catch(ContactNotFoundException e){
				System.out.println("Exception Occured CaseBackingBean listCustomerCases()");
			}
			customerCaseList = caseDao.listCustomerCases(customer);
	    }catch(CaseNotFoundException e){
	    	System.out.println("Exception Occured : CaseBackingBean listCases()");
			//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage(), e.getMessage()));
		}   
		return customerCaseList;
	}
	
	@Override
	public List<SupportCaseORM> getList() {
		// TODO Auto-generated method stub
		if(FacesContext.getCurrentInstance().getExternalContext().isUserInRole("Customer")){
			try{
				BeanFactory beanFactory = BeanFactory.getInstance(); 	
				SessionDataBean session = beanFactory.getSessionBean();
				CsContactORM customer = new CsContactORM();
				try{
				   customer = contactDao.getContactByUserId(session.getUserId());
				}catch(ContactNotFoundException e){
					System.out.println("Exception Occured CaseBackingBean getList()");
				}
				customerCaseList = caseDao.listCustomerCases(customer);
		    }catch(CaseNotFoundException e){
		    	System.out.println("Exception Occured : CaseBackingBean getList()");
				//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage(), e.getMessage()));
			}   
			return customerCaseList;
		}else{
			try{
				caseList = caseDao.listCases();
		    }catch(CaseNotFoundException e){
		    	System.out.println("Exception Occured : CaseBackingBean getList()");
				//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage(), e.getMessage()));
			}
			return caseList;	
		}
	}
	
	public void listCasesHistory(SupportCaseORM cases){
		caseHistoryList = caseHistoryDao.caseHistoryList(cases);
	}
	
	public void listCasesHistoryWithComments(SupportCaseORM cases){
		System.out.println("listCasesHistoryWithComments called..");
		
	}
	
	
	public void listCaseComments(SupportCaseORM cases){
		System.out.println("listCaseComments() called..");
		caseCommentList = caseCommentDao.caseCommentList(cases);
		
	}
	
	public List<SupportCaseORM> listEscalatedCases(){
		try{
			caseList = caseDao.listEscalatedCases();
	    }catch(CaseNotFoundException e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage(), e.getMessage()));
		}   
		return caseList;
	}
	
	public void deleteCase(Long caseId){
		System.out.println("CaseBacking deleteCase() called");
        //Long id = (Long)event.getComponent().getAttributes().get("del_id");
		
        SupportCaseORM supportCase = new SupportCaseORM();
        try{
            supportCase = caseDao.caseDetails(caseId);
        }catch(Exception e){
        	System.out.println(e.getMessage());
        }
        //delete comments from caseUserComments
		caseCommentDao.deleteCaseComments(supportCase);
		// delete from caseHistory
		caseHistoryDao.deleteCaseHistory(supportCase);
		//delete from DB
		caseDao.deleteCase(supportCase.getCaseId());
		
		
	}
	
	
	public List<UmUser> listUsers(){
		try{
		usersList = userDao.listAllUsers();
		}catch(UserNotFoundException e){
			System.out.println(e.getMessage());
		}
		return usersList;
	}
	
	public List<UmUser> listUsersByGroup(){
		try{
			SupportQueryTypeORM queryType = queryTypeDao.queryTypeDetails(queryTypeId);
			UmGroup group = queryType.getUmGroup();
			usersList = userDao.getGroupUsers2(group.getGroupId(), true, session.getCompanyId());
		}catch(Exception e){
			System.out.println(e.getMessage());
	    }
		return usersList;
	}
	
	public List<UmUser> listUsersByCompany(){
		BeanFactory beanFactory = BeanFactory.getInstance(); 	
		SessionDataBean session = beanFactory.getSessionBean();
		usersList = userDao.listUsersByCompany(session.getCompanyId());
		return usersList;
	}
	
	public List<SupportQueryTypeORM> listQueryTypes(){
		UmCompany umCompany = null;
		try{
			umCompany = companyDao.companyDetails(session.getCompanyId());
		}catch(Exception e){
			
		}
		try{
			queryTypeList = queryTypeDao.listQueryTypes(umCompany);
		}catch(QueryTypeNotFoundException e){
			System.out.println(e.getMessage());
		}
		return queryTypeList;
	}
	
    public List<CsContactORM> listAutoCustomers(String query){
    	try{
    	BeanFactory beanFactory = BeanFactory.getInstance(); 	
		SessionDataBean session = beanFactory.getSessionBean();
    	customerList = contactDao.listAutoContacts(session.getCompanyId(), query);
    	}catch(Exception e){
    		System.out.println("Exception occured in CaseBackingBean listAutoCustomers()");
    		e.printStackTrace();
    	}
    	return customerList;
    }
	
	public List<CsContactORM> listCustomers(){
		try{
		BeanFactory beanFactory = BeanFactory.getInstance(); 	
		SessionDataBean session = beanFactory.getSessionBean();
		customerList = contactDao.listContacts(session.getCompanyId());
	    }catch(Exception e){
			System.out.println(e.getMessage());
		}
		return customerList;
	}

	/* this code for the listUsersNotInCustomer()
       function below has to be optimized, it requires 
	   to select the users that are not customers
	   and there should be isCustomer like field
	   in UmUser then it would be easy to filter
	   the users.........
	*/   
	public List<UmUser> listUsersNotInCustomer(){
		List<UmUser> listUsersNotInCustomer = new ArrayList<UmUser>();
	    try{
	     List<UmUser> usersList = new ArrayList<UmUser>();
	     List<CsContactORM> contactList = new ArrayList<CsContactORM>();
	     usersList = listUsersByCompany();
	     contactList = listCustomers();
	     for(CsContactORM csContact : contactList){
	    	try{
	    	  for(UmUser user : usersList){
	    		Long userId = user.getUserId();
	    		long userIdInCustomer = csContact.getContactUser().getUserId();
	    		if(userId == userIdInCustomer){
	    			usersList.remove(user);
	    		}		
	    	  }
	    	}catch(ConcurrentModificationException e){
				
			}
	     }
	     listUsersNotInCustomer = usersList;
	    }catch(Exception e){
	       System.out.println("Exception occured in listUsersNotInCustomer()");
	    } 
	    return listUsersNotInCustomer;
	}
	
	public List<ProductORM> listProducts(){
		try{
		   umCompany = companyDao.companyDetails(session.getCompanyId());	
		}catch(Exception e){
		   System.out.println(e.getMessage());
		}
		try{
		productList = productDao.listProducts(umCompany);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return productList;
	}
	
	public Long createCase(){
	    System.out.println("CaseBackingBean createCase() called...");
	    BeanFactory beanFactory = BeanFactory.getInstance();
		SessionDataBean session = beanFactory.getSessionBean();
		if(FacesContext.getCurrentInstance().getExternalContext().isUserInRole("Customer")){
		     try{
		        customer = contactDao.getContactByUserId(session.getUserId());
		     }catch(ContactNotFoundException e){
				System.out.println(e.getMessage());
		     }
		}else{
		     try{
		        customer = contactDao.contactDetails(getCustomerId());
		     }catch(ContactNotFoundException e){
			    System.out.println(e.getMessage());
		     }
		}
		
		if(customer == null){
			addError(getProperty("message.validate.support.customer.invalid"));
			return -1l;
		}else{
			if(customer.getContactId() == null){
				  addError(getProperty("message.validate.support.customer.invalid"));
				  return -1l;
		    }
		}
		
		try{
		   umUser = userDao.umUserDetails(getUserId());	
		}catch(UserNotFoundException e){
			System.out.println(e.getMessage());
		}
		try{
		   product = productDao.productDetails(getProductId());	
		}catch(ProductNotFoundException e){
			System.out.println(e.getMessage());
		}
		try{
		   umCompany = companyDao.companyDetails(session.getCompanyId());	
		}catch(Exception e){
		   System.out.println(e.getMessage());
		}
		
		try{
			   queryType = queryTypeDao.queryTypeDetails(getQueryTypeId());	
		}catch(QueryTypeNotFoundException e){
				System.out.println(e.getMessage());
		}
		
		ReportSql reportSql = new ReportSql();
		String token = "";
		try{
 		  List<String> list = reportSql.QueryResultSql("select nextval('crm.support_cases_token_number_seq')");
          token = list.get(0);// next value of support_cases_token_number_seq sequence
          System.out.println("token number ="+token);
		}catch(Exception e){
			System.out.println("token number creation failed");
			return -1l;
		}
 		caseTokenNumber = queryType.getQueryTypeAlias()+"-"+token;
 		
		Date date = new Date();
		if(status == null){
			status = "Pending";
		}
		SupportCaseORM newCase = new SupportCaseORM(getCaseTokenNumber(), customer, umUser, product, getCaseType(), queryType, getStatus(), getMobileNumber(), getCustomerQuery(), getComments(),new Timestamp(date.getTime()), new Timestamp(date.getTime()), getCaseResolvedDate(), umCompany);
		// create case
		caseId = caseDao.createCase(newCase);
		caseDao.mailCaseToCustomer(newCase); // Acknowladge Query to customer
//		SupportCaseHistoryORM(SupportCaseORM cases, UmUser user, Timestamp caseAssignedToDate)
		if(umUser != null){
		SupportCaseHistoryORM caseHistory = new SupportCaseHistoryORM(newCase,umUser,new Timestamp(date.getTime()));
		caseHistoryDao.createCaseHistory(caseHistory);
            try{
        	 //  createCaseAlert(umUser);
            }catch(Exception e){
            	System.out.println("Exception in CaseBackingBean createCase()");
            }   
        }
		
        addMessage(getProperty("message.support.case.created"));
        return caseId;
	}
	
	public String createCustomerCase(){
		
		BeanFactory beanFactory = BeanFactory.getInstance();
		SessionDataBean session = beanFactory.getSessionBean();
		
		
		try{
			   customer = contactDao.getContactByUserId(session.getUserId());
		}catch(ContactNotFoundException e){
				System.out.println(e.getMessage());
		}
		
			
		try{
		   product = productDao.productDetails(getProductId());	
		}catch(ProductNotFoundException e){
			System.out.println(e.getMessage());
		}
			
		try{
			   queryType = queryTypeDao.queryTypeDetails(getQueryTypeId());	
		}catch(QueryTypeNotFoundException e){
				System.out.println(e.getMessage());
		}
			
		ReportSql reportSql = new ReportSql();
	 	List<String> list = reportSql.QueryResultSql("select nextval('support_cases_token_number_seq')");
	    String token = list.get(0);// next value of support_cases_token_number_seq sequence
	 	caseTokenNumber = queryType.getQueryTypeAlias()+"-"+token;  
	 	Date date = new Date();
		SupportCaseORM newCase = new SupportCaseORM(getCaseTokenNumber(), customer, null, product, getCaseType(), queryType, "Pending", getMobileNumber(), getCustomerQuery(), getComments(),new Timestamp(date.getTime()), new Timestamp(date.getTime()), getCaseResolvedDate());
			// create case
		caseId = caseDao.createCase(newCase);
		//caseDao.mailCaseToCustomer(newCase); // Acknowladge Query to customer
   	    SupportCaseHistoryORM caseHistory = new SupportCaseHistoryORM(newCase,null,new Timestamp(date.getTime()));
	    caseHistoryDao.createCaseHistory(caseHistory);
	    createCaseAlert(umUser);
	    try{
       	  createCaseAlert(umUser);
        }catch(Exception e){
           	System.out.println("Exception in CaseBackingBean editCase() createCaseAlert(umUser)");
        }
		addMessage(getProperty("message.support.case.created"));
		return "caseList";
	}
	
	public void reOpenTicket(Long caseId){
		SupportCaseORM supportCase = new SupportCaseORM();
		try{
		      supportCase = caseDao.caseDetails(caseId);	
		    }catch(CaseNotFoundException e){
			     System.out.println(e.getMessage());
		}
		supportCase.setStatus("Pending");
		caseDao.updateCase(supportCase);
	}
	
	public void closeTicket(Long caseId){
		SupportCaseORM supportCase = new SupportCaseORM();
		try{
		      supportCase = caseDao.caseDetails(caseId);	
		    }catch(CaseNotFoundException e){
			     System.out.println(e.getMessage());
		}
		supportCase.setStatus("Resolved");
		caseDao.updateCase(supportCase);
	}
	
	public String editCase(Long caseId){
		    System.out.println("CaseBackingBean editCase() Called");
		    BeanFactory beanFactory = BeanFactory.getInstance();
			SessionDataBean session = beanFactory.getSessionBean();
		    try{
			   customer = contactDao.contactDetails(getCustomerId());
			}catch(ContactNotFoundException e){
				System.out.println(e.getMessage());
			}
			
			try{
			   umUser = userDao.umUserDetails(getUserId());	
			}catch(UserNotFoundException e){
				System.out.println(e.getMessage());
			}
			
			try{
			   product = productDao.productDetails(getProductId());	
			}catch(ProductNotFoundException e){
				System.out.println(e.getMessage());
			}
		
			try{
			   umCompany = companyDao.companyDetails(session.getCompanyId());	
			}catch(Exception e){
			   System.out.println(e.getMessage());
			}
			
			try{
				   queryType = queryTypeDao.queryTypeDetails(getQueryTypeId());	
			}catch(QueryTypeNotFoundException e){
					System.out.println(e.getMessage());
			}
		    
			SupportCaseORM supportCase = new SupportCaseORM();
			try{
		      supportCase = caseDao.caseDetails(caseId);	
		    }catch(CaseNotFoundException e){
			     System.out.println(e.getMessage());
		    }
		    
   	    
		Date date = new Date();
		if(getStatus() != null){
		   if((getStatus().equalsIgnoreCase("Resolved")) && !(supportCase.getStatus().equalsIgnoreCase("Resolved"))){
			 caseResolvedDate = new Timestamp(date.getTime());
			 System.out.println("case resolved date = "+getCaseResolvedDate());
		   }
		}else{
			setStatus(supportCase.getStatus());
		}

		caseAssignedDate = supportCase.getCaseAssignedDate();
		
		if(FacesContext.getCurrentInstance().getExternalContext().isUserInRole("Customer")){
			umUser = supportCase.getUmUser();
			customer = supportCase.getcustomer();
			 try{
			        customer = contactDao.getContactByUserId(session.getUserId());
			 }catch(ContactNotFoundException e){
					System.out.println(e.getMessage());
			 }
			
		}else{
			// when case is edited and assigned to an other user then this case is inserted in caseHistory AND caseAssignedDate is updated with current date
	        if(supportCase.getUmUser() != null){
	        	if((long)supportCase.getUmUser().getUserId() != (long)umUser.getUserId()){
	        		SupportCaseHistoryORM caseHistory = new SupportCaseHistoryORM(supportCase,umUser,new Timestamp(date.getTime()));
	                caseHistoryDao.createCaseHistory(caseHistory);
	    			caseAssignedDate = new Timestamp(date.getTime());
	    			try{
	    		       createCaseAlert(umUser);
	    		    }catch(Exception e){
	    		           	System.out.println("Exception in CaseBackingBean editCase() createCaseAlert(umUser)");
	    		    }
	    		}	
	        }else{
	        	
	        }	
		}
		SupportCaseORM newCase = new SupportCaseORM(caseId ,supportCase.getCaseTokenNumber(), customer, umUser, product, getCaseType(), queryType, getStatus(), getMobileNumber(), getCustomerQuery(), getComments(),supportCase.getCaseCreateDate(), caseAssignedDate, getCaseResolvedDate(), umCompany);
		caseDao.updateCase(newCase);
		return "caseList";
	}
	
	public String assignCase(){
		
	    try{
		   customer = contactDao.contactDetails(getCustomerId());
		}catch(ContactNotFoundException e){
			System.out.println(e.getMessage());
		}
		
		try{
		   umUser = userDao.umUserDetails(getUserId());	
		}catch(UserNotFoundException e){
			System.out.println(e.getMessage());
		}
		
		try{
		   product = productDao.productDetails(getProductId());	
		}catch(ProductNotFoundException e){
			System.out.println(e.getMessage());
		}
		
		try{
		   umCompany = companyDao.companyDetails(session.getCompanyId());	
		}catch(Exception e){
		   System.out.println(e.getMessage());
		}
	
	
	Date date = new Date();
	SupportCaseORM newCase = new SupportCaseORM(getCaseId() ,getCaseTokenNumber(), customer, umUser, product, getCaseType(), queryType, "Pending", getMobileNumber(), getCustomerQuery(), getAssignedcomments(),new Timestamp(date.getTime()),new Timestamp(date.getTime()), getCaseResolvedDate(), umCompany);
	caseDao.updateCase(newCase);
	return "escalations";
}
	
	
	public void openCaseHistory(ToggleEvent event) {  
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Case History", "Visibility:" + event.getVisibility());  
  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }
	
	public void createCaseAlert(UmUser user){
		System.out.println("createCaseAlert() called..");
		Date date = new Date();
		UmAlertsAndReminders umAlertsAndReminders = new UmAlertsAndReminders("Case Alert", new Timestamp(date.getTime()), user.getUserId(),true,false,null,false);
		alertsAndRemindersDAO.createAlertsAndReminders(umAlertsAndReminders);
		
	}
	
	public void postComments(Long caseId){
		System.out.println("CaseBackingBean postComments() called");
		if(getUserComments() == null || getUserComments().equalsIgnoreCase("")){
            addWarning(getProperty("message.validate.support.comments")); 
			return;			
		}
		SessionDataBean sessionBean = BeanFactory.getInstance().getSessionBean();
		SupportCaseORM cases = new SupportCaseORM();
		try{
		cases = caseDao.caseDetails(caseId);
		}catch(CaseNotFoundException e){
			System.out.println(e.getMessage());
		}
		
		if(cases.getUmUser() == null){
			addWarning(getProperty("message.validate.support.caseNotAssigned"));
			return;
		}
		//caseDao.caseDetails(caseId);
		listCasesHistory(cases);
		SupportCaseHistoryORM caseHistory = new SupportCaseHistoryORM();
		for(SupportCaseHistoryORM caseHistoryORM:caseHistoryList){
			caseHistory = caseHistoryORM;
		}
		UmUser user = new UmUser();
		try{
		  user = userDao.umUserDetails(sessionBean.getUserId());
        }catch(UserNotFoundException e){
        	System.out.println(e.getMessage());
        }
		//SupportCaseHistoryORM casesHistory = caseHistoryDao.caseHistoryDetails(2l);
		Date date = new Date();	
		SupportCaseCommentORM supportCaseComment = new SupportCaseCommentORM(cases,user,caseHistory,new Timestamp(date.getTime()),getUserComments());	
		Long caseCommentId = caseCommentDao.createCaseComment(supportCaseComment);
		setUserComments(null);
		addMessage(getProperty("message.support.case.comment.added"));
	}
	
	public void submitReview(Long caseId){
		System.out.println("caseBackingBean submitReview() called..");
		SessionDataBean sessionBean = BeanFactory.getInstance().getSessionBean();
		SupportCaseORM cases = new SupportCaseORM();
		try{
		cases = caseDao.caseDetails(caseId);
		}catch(CaseNotFoundException e){
			System.out.println(e.getMessage());
		}
		
		cases.setServiceRating(serviceRating);
		cases.setFeedBack(feedBackComments);
		
		caseDao.feedBackCase(cases);
		
	}
	
	public int numberOfDaysPassed(Date caseCreateDate){
		
		Date currentDate = new Date();
		Calendar currentCal = Calendar.getInstance();
		Calendar createCal = Calendar.getInstance();
		currentCal.setTime(currentDate);
		createCal.setTime(caseCreateDate);
		
		
		return daysBetween(currentCal.getTime(),createCal.getTime()); 
	}
	
	public int daysBetween(Date currentDate, Date createDate){
		int daysBetween = (int) ((createDate.getTime() - currentDate.getTime()) / (1000 * 60 * 60 * 24));
		return daysBetween;
	}
	
	public void customerMobileNumber(){
		try{
		  System.out.println("customer = "+customer);
		  System.out.println("customer Mobile = "+customer.getContactPhone());
		  this.mobileNumber = customer.getContactPhone();
		}catch(Exception e){
			
		}
	}
	
	public void clear(){
		this.customer = null;
		this.customerId = null;
		this.product = null;
		this.productId = null;
		this.caseType = null;
		this.queryType = null;
		this.queryTypeId = null;
		this.mobileNumber = null;
		this.comments = null;
		this.customerQuery = null;
		
	}
	
	public String actionListener(){
		  reset();
		  //setCurrentAction(getParameter("action"),this.getClass());
		  setCurrentAction(getParameter("action"));
		  System.out.println("getCurrentAction() = "+getCurrentAction());
		  switch(getCurrentAction()){
		    case WebConstants.ACTION_CREATE:
		      clear();
		      if (FacesContext.getCurrentInstance().getExternalContext().isUserInRole("Customer")) {
					try {
						this.customer = contactDao.getContactByUserId(session.getUserId());
						this.customerId = customer.getContactId();
						this.mobileNumber = customer.getContactPhone();
					}catch(Exception e){
					
					}	
		      }		
		      setCreateAction();
		      setCancelAction(false);
		      renderCommentsForm = false;
		      session.setSupportModule_tabIndex(0);
		      return null;
			  //return WebConstants.ACTION_CRUD;
			case WebConstants.ACTION_SAVE:
				try{
				  Long caseId = createCase();
				  if(caseId > 0){
					  loadCase(caseId);
					  reset();
					  setViewAction();
					  setCancelAction(false);
					  renderCommentsForm = true;
					  session.setSupportModule_tabIndex(0);
					  session.setSupportModule_selectedCase(caseId);
					  return null;   
				  }else{
					  setCreateAction();
				      setCancelAction(false);
				      renderCommentsForm = false;
				      session.setSupportModule_tabIndex(0);
				      return null;
				  }
				  
			      //return WebConstants.ACTION_LIST;
				}catch(Exception e){
					addError(getProperty("message.support.error.register"));
					setCreateAction();
				    setCancelAction(false);
				    renderCommentsForm = false;
				    return null;
				}
			case WebConstants.ACTION_VIEW:
				try{
				 Long caseId = session.getSupportModule_selectedCase();
				 loadCase(caseId);
				 setViewAction();
				 setCancelAction(false);
				 renderCommentsForm = true;
				 session.setSupportModule_tabIndex(0);
				 return null;
				 //return WebConstants.ACTION_CRUD;
				}
				catch (Exception e) {
					// handle exception
					addError(getProperty("message.support.error.load.case"));
					System.out.println("Exception Occured CaseBackingBean actionListener()");
					setSaveAction(); // in case of any failure
					session.setSupportModule_tabIndex(0);
					return null;
					//return WebConstants.ACTION_LIST;
				}
			case "viewCLosedTicket":
				try{
				 Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
				 Long caseId = Long.valueOf(params.get("row_id"));
				 loadCase(caseId);
				 setViewAction();
				 renderClosedTicketForm = true;
				 renderCommentsForm = true;
				 session.setSupportModule_selectedCase(null);
				 session.setSupportModule_tabIndex(1);
				 return null;
				 //return WebConstants.ACTION_CRUD;
				}
				catch (Exception e) {
					// handle exception
					addError(getProperty("message.support.error.load.case"));
					System.out.println("Exception Occured CaseBackingBean actionListener()");
					setSaveAction(); // in case of any failure
					session.setSupportModule_tabIndex(1);
					return null;
					//return WebConstants.ACTION_LIST;
				}
			case "reopen":
				try{
				 Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
				 Long caseId = Long.valueOf(params.get("row_id"));
				 reOpenTicket(caseId);
				 renderClosedTicketForm = false;
				 renderCommentsForm = false;
				 session.setSupportModule_tabIndex(1);
				 addMessage(getProperty("message.support.ticket.reopened"));
				 
				 setDeleteConfirmedAction(); //
				 session.setSupportModule_selectedCase(null);
				 renderCommentsForm = false; //
				 return null;
				 //return WebConstants.ACTION_CRUD;
				}
				catch (Exception e) {
					// handle exception
					addError(getProperty("message.support.error.load.case"));
					System.out.println("Exception Occured CaseBackingBean actionListener()");
					setSaveAction(); // in case of any failure
					session.setSupportModule_tabIndex(1);
					return null;
					//return WebConstants.ACTION_LIST;
				}
				
			case "closeTicket":
				try{
				 Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
				 Long caseId = Long.valueOf(params.get("ticketId"));
				 closeTicket(caseId);
				 session.setSupportModule_tabIndex(0);
				 addMessage(getProperty("message.support.ticket.closed"));
				 
				 setDeleteConfirmedAction(); //
				 session.setSupportModule_tabIndex(0);
				 session.setSupportModule_selectedCase(null);
				 renderCommentsForm = false; //
					
				 
				 return null;
				 //return WebConstants.ACTION_CRUD;
				}
				catch (Exception e) {
					// handle exception
					addError(getProperty("message.support.ticket.error.close"));
					System.out.println("Exception Occured CaseBackingBean actionListener()");
					session.setSupportModule_tabIndex(0);
					return null;
					//return WebConstants.ACTION_LIST;
				}
				
			case "postComments":
			    try{
			    	Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
					Long caseId = Long.valueOf(params.get("ticketId"));
					postComments(caseId);
					loadCase(caseId);
					setViewAction();
					setCancelAction(false);
					session.setSupportModule_tabIndex(0);
					renderCommentsForm = true;
			    	return null;
					//return WebConstants.ACTION_CRUD;
			    }catch(Exception e){
			    	addError(getProperty("message.support.error.load.case"));
					System.out.println("Exception Occured CaseBackingBean actionListener()");
					return null;
					//return WebConstants.ACTION_LIST;
			    }
			case WebConstants.ACTION_CANCEL:
				//setSaveAction();
				try{
				Long caseId = session.getSupportModule_selectedCase();
				loadCase(caseId);
				session.setSupportModule_tabIndex(0);
				setViewAction();
				setCancelAction(false);
				renderCommentsForm = true;
				return null;
				}catch(Exception e){
					return null;
				}
			    //return WebConstants.ACTION_LIST;
			case WebConstants.ACTION_EDIT:
				try{
					setEditAction();
					Long caseId = session.getSupportModule_selectedCase();
					loadCase(caseId);
					session.setSupportModule_tabIndex(0);
					renderCommentsForm = true;
					return null;
				//	return WebConstants.ACTION_CRUD;
				}
				catch (Exception e) {
					addError(getProperty("message.support.error.load.case"));
					System.out.println("Exception Occured CaseBackingBean actionListener()");
					setSaveAction(); // in case of any failure
					session.setSupportModule_tabIndex(0);
					return null;
             //		return WebConstants.ACTION_LIST;
				}
				
			case WebConstants.ACTION_UPDATE:
				try{
					Long caseId = session.getSupportModule_selectedCase();
					editCase(caseId);
					loadCase(caseId);
					reset();
					setViewAction();
					setCancelAction(false);
				    addMessage(getProperty("message.support.ticket.updated"));
				    session.setSupportModule_tabIndex(0);
					renderCommentsForm = true;
					return null;
					//return WebConstants.ACTION_LIST;
				}
				catch (Exception e) {
					addError("Could not Load Case");
					System.out.println("Exception Occured CaseBackingBean actionListener()");
					System.out.println(e.getMessage());
					setSaveAction(); // in case of any failure in updating
					session.setSupportModule_tabIndex(0);
					return null;
					//return WebConstants.ACTION_LIST;
				}
				
			case WebConstants.ACTION_DELETE:
				try{
					Long caseId = session.getSupportModule_selectedCase();
					loadCase(caseId);
					setDeleteConfirmedAction();
					session.setSupportModule_tabIndex(0);
					renderCommentsForm = true;
					return null;
					//return WebConstants.ACTION_CRUD;
				}
				catch (Exception e) {
					addError(getProperty("message.support.error.load.case"));
					System.out.println("Exception Occured CaseBackingBean actionListener()");
					setSaveAction(); // in case of failure in loading case that is to be deleted
					session.setSupportModule_tabIndex(0);
					return null;
				    //return WebConstants.ACTION_LIST;
				}
				
			  
			case WebConstants.ACTION_DELETE_CONFIRMED:
				Long id = session.getSupportModule_selectedCase();
				try{
					setDeleteConfirmedAction();
					deleteCase(id);
					addMessage(getProperty("message.support.ticket.deleted"));
					session.setSupportModule_tabIndex(0);
					session.setSupportModule_selectedCase(null);
					renderCommentsForm = false;
					return null;
					//return WebConstants.ACTION_LIST;
				}
				catch (Exception e) {
					System.out.println("Exception Occured CaseBackingBean actionListener()");
					setSaveAction(); // in case of failure in delete
					session.setSupportModule_tabIndex(0);
					return null;
				    //return WebConstants.ACTION_LIST;
				}
			
			case "submitReview":
				try{
				System.out.println("submit review");
				Long caseId = session.getSupportModule_selectedCase();
				submitReview(caseId);
				addMessage(getProperty("message.support.case.feedBack.added"));
				
				loadCase(caseId);
				setViewAction();
				setCancelAction(false);
				renderCommentsForm = true;
				session.setSupportModule_tabIndex(0);
				 
				return null;
				}catch(Exception e){
					return null;
				}
			}
				  
			return(null);
			
		} // end actionListener()

	
	public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }

    public void beforePhase(PhaseEvent event) {
        System.out.println("START PHASE " + event.getPhaseId());
    }

    public void afterPhase(PhaseEvent event) {
        System.out.println("END PHASE " + event.getPhaseId());
    }

    
}
