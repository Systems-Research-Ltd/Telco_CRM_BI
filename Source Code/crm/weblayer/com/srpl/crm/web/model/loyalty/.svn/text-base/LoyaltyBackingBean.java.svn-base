package com.srpl.crm.web.model.loyalty;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.persistence.metamodel.Attribute;

import com.bitguiders.util.jsf.JSFBeanInterface;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;
import com.srpl.crm.ejb.entity.CsContactORM;
import com.srpl.crm.ejb.entity.LoyaltyORM;
import com.srpl.crm.ejb.entity.LoyaltyRuleORM;
import com.srpl.crm.ejb.exceptions.ContactNotFoundException;
import com.srpl.crm.ejb.exceptions.LoyaltyNotFoundException;
import com.srpl.crm.ejb.request.ContactDAO;
import com.srpl.crm.ejb.request.LoyaltyDAO;
import com.srpl.crm.ejb.request.LoyaltyRuleDAO;
import com.srpl.crm.web.common.AjaxListStructure;
import com.srpl.crm.web.common.InnerTabs;
import com.srpl.crm.web.common.SessionDataBean;
import com.srpl.crm.web.controller.BeanFactory;
import com.srpl.crm.web.model.common.ColumnModel;
import com.srpl.crm.web.model.report.ReportSql;
import com.srpl.um.ejb.entity.UmCompany;
import com.srpl.um.ejb.request.CompanyDAO;

@ManagedBean(name = "loyaltyBackingBean")
public class LoyaltyBackingBean extends JSFBeanSupport implements
		JSFBeanInterface, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long loyaltyId;
	private String loyaltyTitle;
	private String loyaltyDetails;
	// private Boolean loyaltyLaunch = true;
	private Boolean loyaltyStatus = true;
	private Date launchDate;
	private Date launchDateTime;
	private double loyaltyDiscount;
	private SessionDataBean session;
	private String loyaltyLaunch;

	private LoyaltyORM loyalty;
	private List<ColumnModel> columns;
	public Boolean update = true;
	// private int ruleCounter = 0;
	// private ArrayList<NewLoyaltyBackingBean> loyaltyList;
	// private ArrayList<LoyaltyORM> loyaltyList;
	private Date currentDate = new Date();

	@EJB
	LoyaltyDAO loyaltyDao;
	@EJB
	ContactDAO contactDao;
	@EJB
	LoyaltyRuleDAO loyaltyRuleDao;
	@EJB
	CompanyDAO companyDao;

	public LoyaltyBackingBean() {
		System.out.println("loyalty bean");
		session = BeanFactory.getInstance().getSessionBean();
		setCurrentAction(WebConstants.ACTION_SECURITY, this.getClass());
	}

	@PostConstruct
	public void postConstruct() {
		System.out.println("post construct");
		if (getAction().equals("")){
			this.loyaltyDetails();
			reset();
			setViewAction();
		}
		if (getAction().equals("actionAjax")) {
			setDisabled(false);
		}
	}

	public void loyaltyDetails() {
		System.out.println("++" + session.getLoyaltyModule_selectedLoyalty());
		if (session.getLoyaltyModule_selectedLoyalty() != 0L) {
			loadLoyalty(session.getLoyaltyModule_selectedLoyalty());
			changeTabPath(0, "/view/loyalty/loyaltyForm.xhtml");
			setViewAction();
		} else {
			session.resetLoyaltyModule();
		}
	}

	private void changeTabPath(int index, String path) {
		InnerTabs d = session.getLoyaltyTabs().get(index);
		d.setPath(path);
		session.getLoyaltyTabs().set(index, d);

		try {
			if (getParameter("fromListing").equals("fromListing")) {
				// don't update index
			} else {
				session.setLoyaltyModule_loyaltyTabIndex(0);
			}
		} catch (Exception e) {
			session.setLoyaltyModule_loyaltyTabIndex(0);
		}
	}

	@Override
	public void setViewAction() {
		super.setViewAction();
		setCancelAction(false);
	}

	public void loadLoyalty(long id) {
		System.out.println("loadLoyalty id" + id);
		List<LoyaltyRules> newRules = new ArrayList<LoyaltyRules>();
		session.setLoyaltyModule_loyaltyRules(newRules);
		LoyaltyBackingBean bean = this;
		try {
			LoyaltyORM dbl;
			try {
				dbl = loyaltyDao.loyaltyDetail(id);
				convert2Bean(dbl);
				List<LoyaltyRuleORM> dblr = loyaltyRuleDao
						.retrieveSingleRuleDetail(dbl);
				for (LoyaltyRuleORM lr : dblr) {
					convertToBean(lr);
				}
			} catch (Exception e) {
				e.printStackTrace();
				this.changeTabPath(0, "/view/loyalty/loyaltyNoSelection.xhtml");
			}

		} catch (Exception e) {
			e.printStackTrace();
			this.changeTabPath(0, "/view/loyalty/loyaltyNoSelection.xhtml");
		}
	}

	private void convert2Bean(LoyaltyORM dbl) {
		setLoyaltyId(dbl.getLoyaltyId());
		setLoyaltyTitle(dbl.getLoyaltyTitle());
		setLoyaltyDetails(dbl.getLoyaltyDetails());
		setLoyaltyLaunch(dbl.getLoyaltyLaunch());
		setLaunchDateTime(dbl.getLaunchDateTime());
		setLoyaltyStatus(dbl.getLoyaltyStatus());
		setLoyaltyDiscount(dbl.getLoyaltyDiscount());

	}

	private void convertToBean(LoyaltyRuleORM dblr) {
		LoyaltyRules lr = new LoyaltyRules();
		setLoyalty(dblr.getLoyalty());
		lr.setLoyaltyRuleId(dblr.getLoyaltyRuleId());
		
		lr.setLoyaltyaRule(dblr.getLoyaltyRule());
		lr.setLoyaltyRuleCondition(dblr.getLoyaltyRuleCondition());
		lr.setLoyaltyRuleValue(dblr.getLoyaltyRuleValue());

		session.getLoyaltyModule_loyaltyRules().add(lr);
	}

	// subscriber list
	/*public Map<String, String> Subscriber() {
		Map<String, String> c = new LinkedHashMap<String, String>();
		Set<Attribute<? super CsContactORM, ?>> cols = loyaltyDao
				.listTableCols();
		Iterator<Attribute<? super CsContactORM, ?>> itr = cols.iterator();
		while (itr.hasNext()) {
			String col = itr.next().getName();
			c.put(col, col);
		}
		return c;
	}*/
	
	
	public Map<String, String> Subscriber() {
		
		Map<String, String> subscriber = new LinkedHashMap<String, String>();
		List<CsContactORM> tbls =contactDao.listContacts(session.getCompanyId());
				
		for (CsContactORM t : tbls) {
			subscriber.put("contactCreatedon", "contactCreatedon");
			subscriber.put("contactDob", "contactDob");
				
		}
		return subscriber;
	}

	// subscriber column value list
	public Map<String, String> ContactColumns(String action)
			throws ContactNotFoundException {
		System.out.println(action);
		Map<String, String> repMap = new LinkedHashMap<String, String>();
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		String cont = action;
		if (cont != null && cont != "") {
			List<CsContactORM> contacts = contactDao.listContacts(session
					.getCompanyId());
			switch (cont) {
			case "contactFname":
				for (CsContactORM cs : contacts) {
					repMap.put(cs.getContactFname(), cs.getContactFname());
				}
				break;
			case "contactLname":
				for (CsContactORM cs : contacts) {
					repMap.put(cs.getContactLname(), cs.getContactLname());
				}
				break;
			case "contactFatherName":
				for (CsContactORM cs : contacts) {
					repMap.put(cs.getContactFatherName(),
							cs.getContactFatherName());
				}
				break;
			case "contactCreatedon":
				for (CsContactORM cs : contacts) {
					repMap.put((cs.getContactCreatedon()).toString(),
							(cs.getContactCreatedon()).toString());
				}
				break;
			case "contactDob":
				for (CsContactORM cs : contacts) {
					repMap.put((cs.getContactDob()).toString(),
							(cs.getContactDob()).toString());
				}
				break;
			case "contactEmail":
				for (CsContactORM cs : contacts) {
					repMap.put(cs.getContactEmail(), cs.getContactEmail());
				}
				break;
			case "contactPhone":
				for (CsContactORM cs : contacts) {
					repMap.put(cs.getContactPhone(), cs.getContactPhone());
				}
				break;
			case "contactState":
				for (CsContactORM cs : contacts) {
					repMap.put((cs.getContactState()).toString(),
							(cs.getContactState()).toString());
				}
				break;
			case "contactCountry":
				for (CsContactORM cs : contacts) {
					repMap.put((cs.getContactCountry()).toString(),
							(cs.getContactCountry()).toString());
				}
				break;
			case "contactCity":
				for (CsContactORM cs : contacts) {
					repMap.put((cs.getContactCity()).toString(),
							(cs.getContactCity()).toString());
				}
				break;
			case "contactCnic":
				for (CsContactORM cs : contacts) {
					repMap.put(cs.getContactCnic(), cs.getContactCnic());
				}
				break;
			case "contactCnicCopy":
				for (CsContactORM cs : contacts) {
					repMap.put(cs.getContactCnicCopy(), cs.getContactCnicCopy());
				}
				break;
			case "contactAddress":
				for (CsContactORM cs : contacts) {
					repMap.put(cs.getContactAddress(), cs.getContactAddress());
				}
				break;
			case "contactZipcode":
				for (CsContactORM cs : contacts) {
					repMap.put(cs.getContactZipcode(), cs.getContactZipcode());
				}
				break;
			case "contactStatus":
				for (CsContactORM cs : contacts) {
					repMap.put((cs.getContactStatus()).toString(),
							(cs.getContactStatus()).toString());
				}
				break;
			case "contactId":
				for (CsContactORM cs : contacts) {
					repMap.put((cs.getContactId()).toString(),
							(cs.getContactId()).toString());
				}
				break;
			case "invoices": {
				for (CsContactORM cs : contacts) {
					// repMap.put(cs.getInvoices().toString(),cs.getInvoices().toString());
					repMap.put("No record found in db", "No record found in db");
				}
			}
			case "payments": {
				for (CsContactORM cs : contacts) {
					repMap.put("No record found in db", "No record found in db");
				}
			}
			case "contactUser": {
				for (CsContactORM cs : contacts) {
					repMap.put("No record found in db", "No record found in db");
				}
			}
			case "subscription": {
				for (CsContactORM cs : contacts) {
					repMap.put("No record found in db", "No record found in db");
				}
			}
			case "orders": {
				for (CsContactORM cs : contacts) {
					repMap.put("No record found in db", "No record found in db");
				}
			}
			case "csAccount": {
				for (CsContactORM cs : contacts) {
					repMap.put("No record found in db", "No record found in db");
				}
			}
			case "companyId": {
				for (CsContactORM cs : contacts) {
					repMap.put("No record found in db", "No record found in db");
				}
			}
			case "sHistory": {
				for (CsContactORM cs : contacts) {
					repMap.put("No record found in db", "No record found in db");
				}
			}
			}
		}

		return repMap;
	}

	public void addRule() {
		LoyaltyRules lrs = new LoyaltyRules();
		int ruleCounter = 0;
		try{
			ruleCounter = session.getLoyaltyModule_loyaltyRules().size();
		}catch (Exception e) {
			System.out.println("no loyalty rules in session.");
		}
		lrs.setTempRuleId(++ruleCounter);
		lrs.setLoyaltyRuleId(0L);
		lrs.setLoyaltyaRule("");
		lrs.setLoyaltyRuleCondition("");
		lrs.setLoyaltyRuleValue("");
		session.getLoyaltyModule_loyaltyRules().add(lrs);
	}

	// Start of createLoyalty()
	public String createLoyalty() throws LoyaltyNotFoundException {
		String str = null;
		Boolean lk = null;
		int a;
		System.out.println("createLoyalty() called");
		/*java.sql.Timestamp launchDateTime;
		// LoyaltyBackingBean lb = BeanFactory.getInstance()
		// .getLoyaltyBackingBean();
		setLaunchDateTime(new Date());*/
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try{
			date = dateFormat.parse(dateFormat.format(date));
		}catch(Exception e){
			e.printStackTrace();
		}
		if(launchDateTime.compareTo(date) < 0){
			addError("date can not be less than current date");
			
		}
		try {
			launchDateTime = new Timestamp(getLaunchDateTime().getTime());
			UmCompany company = companyDao.companyDetails(session
					.getCompanyId());
			LoyaltyORM loyalty = new LoyaltyORM(getLoyaltyTitle(),
					getLoyaltyDetails(), getLoyaltyLaunch(), launchDateTime,
					getLoyaltyStatus(), getLoyaltyDiscount(), company);
			Long lid = loyaltyDao.createLoyalty(loyalty);
			session.setLoyaltyModule_selectedLoyalty(lid);
			LoyaltyORM lorm;
			try {
				lorm = loyaltyDao.loyaltyDetail(lid);
				System.out.println("in create action loyalty rule size ====="
						+ session.getLoyaltyModule_loyaltyRules().size());
				for (LoyaltyRules lr : session.getLoyaltyModule_loyaltyRules()) {
					try {
						lk = loyaltyDao.getSelectColumn(lr.getLoyaltyaRule(),
								lr.getLoyaltyRuleCondition(),
								lr.getLoyaltyRuleValue());
						if (lk) {
							LoyaltyRuleORM loyaltyRule = new LoyaltyRuleORM(
									lorm, lr.getLoyaltyaRule(),
									lr.getLoyaltyRuleCondition(),
									lr.getLoyaltyRuleValue());
							loyaltyDao.createLoyaltyRule(loyaltyRule);

						} else {
							// this.addMessage("error");
							this.addError("--------------error");
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						this.addError(getProperty("message.loyalty.rules.violate"));
					}

				}// for
				this.addMessage(getProperty("message.loyalty.created"));
			} catch (LoyaltyNotFoundException e) {
				this.addError(e.getMessage());
				e.printStackTrace();
			}
		} catch (Exception e) {
			this.addError(getProperty("message.loyalty.creation.failed"));
		}
		return "loyaltyList";
	}

	public void delete() {
		// Long id = Long.valueOf(getParameter("row_id").toString());
		LoyaltyORM loyalty = new LoyaltyORM();
		try {
			loyalty = loyaltyDao.loyaltyDetail(session
					.getLoyaltyModule_selectedLoyalty());
		} catch (LoyaltyNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			setDeleteConfirmedAction();
			loyaltyDao.deleteLoyalty(loyalty);
			session.resetLoyaltyModule();
			addMessage(getProperty("message.loyalty.deleted"));
		} catch (Exception e) {
			setSaveAction(); // in case of failure in delete
			addError(getProperty("message.loyalty.deletion.failed"));
		}
	}

	public HashMap<String, String> ConditionList() {
		HashMap<String, String> repMap = new HashMap<String, String>();
		repMap.put("=", "=");
		repMap.put("<", "<");
		repMap.put(">", ">");
		repMap.put("<=", "<=");
		repMap.put(">=", ">=");
		repMap.put("!=", "!=");
		return repMap;
	}

	public double getDiscount(long customerId) {
		List<LoyaltyORM> ls = loyaltyDao.getActiveLoyalties();

		CsContactORM customer = null;
		try {
			customer = contactDao.contactDetails(customerId);
		} catch (ContactNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double discount = 0f;
		for (LoyaltyORM l : ls) {
			List<LoyaltyRuleORM> lrs = loyaltyRuleDao.getRulesListByLoyalty(l);
			boolean loyalty = false;
			for (LoyaltyRuleORM lr : lrs) {
				// String query =
				// "SELECT * FROM crm.cs_contacts WHERE "+lr.getLoyaltyRule()+" "+lr.getLoyaltyRuleCondition()+" '"+lr.getLoyaltyRuleValue()+"' AND contact_id ="+customerId;
				// boolean loyalty = loyaltyRuleDao.checkLoyalty(query);
				loyalty = loyaltyRuleDao.checkLoyalty(customer,
						lr.getLoyaltyRule(), lr.getLoyaltyRuleCondition(),
						lr.getLoyaltyRuleValue());
				if (!loyalty) {
					break;
				}

			}
			if (loyalty) {
				discount = discount + l.getLoyaltyDiscount();
				System.out.println("with orm -------- Discount is " + discount);
			}
		}
		return discount;
	}

	public int getRulesSize() {
		return session.getLoyaltyModule_loyaltyRules().size();
	}

	public Long getLoyaltyId() {
		return loyaltyId;
	}

	public void setLoyaltyId(Long loyaltyId) {
		this.loyaltyId = loyaltyId;
	}

	public String getLoyaltyTitle() {
		return loyaltyTitle;
	}

	public void setLoyaltyTitle(String loyaltyTitle) {
		this.loyaltyTitle = loyaltyTitle;
	}

	public String getLoyaltyDetails() {
		return loyaltyDetails;
	}

	public void setLoyaltyDetails(String loyaltyDetails) {
		this.loyaltyDetails = loyaltyDetails;
	}

	public Boolean getLoyaltyStatus() {
		return loyaltyStatus;
	}

	public void setLoyaltyStatus(Boolean loyaltyStatus) {
		this.loyaltyStatus = loyaltyStatus;
	}

	public Date getLaunchDate() {
		return launchDate;
	}

	public void setLaunchDate(Date launchDate) {
		this.launchDate = launchDate;
	}

	public Date getLaunchDateTime() {
		return launchDateTime;
	}

	public void setLaunchDateTime(Date launchDateTime) {
		this.launchDateTime = launchDateTime;
	}

	public String getLoyaltyLaunch() {
		return loyaltyLaunch;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public void setLoyaltyLaunch(String loyaltyLaunch) {
		this.loyaltyLaunch = loyaltyLaunch;
	}

	public double getLoyaltyDiscount() {
		return loyaltyDiscount;
	}

	public void setLoyaltyDiscount(double loyaltyDiscount) {
		this.loyaltyDiscount = loyaltyDiscount;
	}

	public LoyaltyORM getLoyalty() {
		return loyalty;
	}

	public void setLoyalty(LoyaltyORM loyalty) {
		this.loyalty = loyalty;
	}

	public SessionDataBean getSession() {
		return session;
	}

	public void setSession(SessionDataBean session) {
		this.session = session;
	}

	public List<ColumnModel> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnModel> columns) {
		this.columns = columns;
	}

	/*
	 * public Boolean getLoyaltyLaunch() { return loyaltyLaunch; }
	 * 
	 * public void setLoyaltyLaunch(Boolean loyaltyLaunch) { this.loyaltyLaunch
	 * = loyaltyLaunch; }
	 */

	/*
	 * public int getRuleCounter() { return ruleCounter; }
	 * 
	 * public void setRuleCounter(int ruleCounter) { this.ruleCounter =
	 * ruleCounter; }
	 */

	public void resetBean() {
		setLoyaltyId(0L);
		setLoyaltyDetails("");
		// setLoyaltyLaunch(null);
		setLoyaltyTitle("");
		setLoyaltyStatus(null);
		setLaunchDate(null);
		setLaunchDateTime(null);
		List<LoyaltyRules> newRules = new ArrayList<LoyaltyRules>();
		session.setLoyaltyModule_loyaltyRules(newRules);
	}

	@Override
	public String actionListener() {
		// TODO Auto-generated method stub
		LoyaltyBackingBean bean = this;
		Long id;
		LoyaltyORM dbl;
		LoyaltyRuleORM dblr;
		LoyaltyORM lorm;
		setCurrentAction(getParameter("action"), this.getClass());
		switch (getCurrentAction()) {
		case WebConstants.ACTION_CREATE:
			System.out.println("action create called");
			changeTabPath(0, "/view/loyalty/loyaltyForm.xhtml");
			System.out.println("tab path is" + session.getLoyaltyTabs().get(0).getPath());
			bean = this;
			bean.resetBean();
			break;
		case WebConstants.ACTION_SAVE:
			try {
				createLoyalty();
			} catch (LoyaltyNotFoundException e) {
				// TODO Auto-generated catch block
				addError(getProperty("message.loyalty.load"));
				e.printStackTrace();
			}
			reset();
			setViewAction();
			break;
		case WebConstants.ACTION_VIEW:
			this.loyaltyDetails();
			setViewAction();
			break;
		case WebConstants.ACTION_CANCEL:
			this.loyaltyDetails();
			setViewAction();
			break;
		case WebConstants.ACTION_EDIT:
			id = session.getLoyaltyModule_selectedLoyalty();
			this.changeTabPath(0, "/view/loyalty/loyaltyForm.xhtml");
			this.loadLoyalty(id);
			setEditAction();
			break;
		case WebConstants.ACTION_UPDATE:
			setLoyaltyId(session.getLoyaltyModule_selectedLoyalty());
			java.sql.Timestamp launchDateTime;
			SessionDataBean session = BeanFactory.getInstance()
					.getSessionBean();
			UmCompany company = companyDao.companyDetails(session
					.getCompanyId());
			launchDateTime = new Timestamp(getLaunchDateTime().getTime());
			LoyaltyORM loyalty = new LoyaltyORM(getLoyaltyId(),
					getLoyaltyTitle(), getLoyaltyDetails(), getLoyaltyLaunch(),
					launchDateTime, getLoyaltyStatus(), getLoyaltyDiscount(),
					company);
			try {
				loyaltyDao.updateLoyalty1(loyalty);
				LoyaltyORM updatedLoyalty = loyaltyDao
						.retrieveLoyalty(loyaltyId);
				for (LoyaltyRules lr : session.getLoyaltyModule_loyaltyRules()) {
					LoyaltyRuleORM loyaltyRule = new LoyaltyRuleORM(
							updatedLoyalty, lr.getLoyaltyRuleId(),
							lr.getLoyaltyaRule(), lr.getLoyaltyRuleCondition(),
							lr.getLoyaltyRuleValue());
					loyaltyRuleDao.updateLoyaltyRule(loyaltyRule);
				}
				addMessage(getProperty("message.loyalty.updated"));
				setViewAction();
			} catch (Exception e) {
				addMessage(getProperty("message.loyalty.updation.failed"));
			}
			break;
		case WebConstants.ACTION_DELETE:
			id = Long.parseLong(this.getParameter("row_id"));
			this.loadLoyalty(id);
			this.changeTabPath(0, "/view/loyalty/loyaltyForm.xhtml");
			setDeleteAction();
			break;
		case WebConstants.ACTION_DELETE_CONFIRMED:
			delete();
			break;
		case "addRule":
			System.out.println("case addRule called");
			// TOOD what is this? :p
			/*
			 * LoyaltyRules lrs = new LoyaltyRules();
			 * lrs.setTempRuleId(++ruleCounter); lrs.setLoyaltyRuleId(0L);
			 * lrs.setLoyaltyRule(""); lrs.setLoyaltyRuleCondition("");
			 * lrs.setLoyaltyRuleValue(""); loyaltyRules.add(lrs);
			 */
			break;
		}
		return null;
	}

	@Override
	public List<AjaxListStructure> getList() {
		ArrayList<AjaxListStructure> myList = new ArrayList<AjaxListStructure>();
		AjaxListStructure e;
		/*double f = getDiscount(72L);
		System.out
				.println("////////////////Loyalty-------------------------------------------------"
						+ f);*/
		List<LoyaltyORM> loyaltyDb = new ArrayList<LoyaltyORM>();
	//	List<LoyaltyORM> loyaltyDb = null;
		try {
			SessionDataBean session = BeanFactory.getInstance()
					.getSessionBean();
			UmCompany company = companyDao.companyDetails(session
					.getCompanyId());
			loyaltyDb = loyaltyDao.listLoyalty(company);
		} catch (LoyaltyNotFoundException le) {
			// TODO Auto-generated catch block
			le.printStackTrace();
		}

		if (loyaltyDb.size() != myList.size()) {
			for (LoyaltyORM l : loyaltyDb) {
				e = new AjaxListStructure();
				e.setId(l.getLoyaltyId());
				e.setLabel(l.getLoyaltyTitle());
				myList.add(e);
			}
		}
		if (myList.size() == 0) {
			// if No entries
			e = new AjaxListStructure();
			e.setId(0L);
			e.setLabel(getProperty("message.no.loyalty.found"));
			myList.add(e);
			changeTabPath(0, "/view/loyalty/loyaltyNoSelection.xhtml");
		}
		return myList;
	}

}
