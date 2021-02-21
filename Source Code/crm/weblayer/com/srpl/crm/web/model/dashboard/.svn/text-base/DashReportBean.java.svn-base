package com.srpl.crm.web.model.dashboard;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.naming.InitialContext;
import javax.naming.NamingException;


import org.primefaces.context.RequestContext;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.MeterGaugeChartModel;
import org.primefaces.model.chart.PieChartModel;

import com.bitguiders.util.jsf.JSFBeanSupport;
import com.srpl.crm.ejb.entity.CampaignProduct;
import com.srpl.crm.ejb.entity.CsContactORM;
import com.srpl.crm.ejb.entity.MCampaign;
import com.srpl.crm.ejb.entity.OrderORM;
import com.srpl.crm.ejb.entity.SInvoiceORM;
import com.srpl.crm.ejb.entity.SupportCaseORM;
import com.srpl.crm.ejb.entity.UmAlertsAndReminders;
import com.srpl.crm.ejb.request.AlertsAndRemindersDAO;
import com.srpl.crm.ejb.request.CampaignDAO;
import com.srpl.crm.ejb.request.CaseDAO;
import com.srpl.crm.ejb.request.ContactDAO;
import com.srpl.crm.ejb.request.InvoiceDAO;
import com.srpl.crm.ejb.request.OrderDAO;
import com.srpl.crm.ejb.request.UtilsDAO;
import com.srpl.crm.web.common.SessionDataBean;
import com.srpl.crm.web.controller.BeanFactory;
import com.srpl.crm.web.model.common.ColumnModel;
import com.srpl.um.ejb.entity.UmCompany;
import com.srpl.um.ejb.entity.UmFranchise;
import com.srpl.um.ejb.entity.UmUser;
import com.srpl.um.ejb.request.CompanyDAO;
import com.srpl.um.ejb.request.FranchiseDAO;
import com.srpl.um.ejb.request.UserDAO;

@ManagedBean(name = "dashReportBean")
@ViewScoped
public class DashReportBean extends JSFBeanSupport implements Serializable {
	private static final long serialVersionUID = 1L;
	private boolean showpopup = true;
	private boolean showpopupChkBox = true;
	private String invoiceHtml;
	
	/*
	 * private List<AssignedTaskORM> tasks;// private List<ColumnModel>
	 * taskcolumns;
	 */
	SessionDataBean session;
	@EJB
	OrderDAO orderDao;
	/*
	 * @EJB AssignedTaskDAO tasksDAO;
	 */
	@EJB
	UtilsDAO utilsDao;
	@EJB
	CampaignDAO campaignDao;

	public DashReportBean() {

	//	session = BeanFactory.getInstance().getSessionBean();

	}

	@PostConstruct
	void init_() {
		session = BeanFactory.getInstance().getSessionBean();

		/*
		 * Long uId = session.getUserId(); tasks =
		 * tasksDAO.list(session.getUserId());
		 */

	}
	
	// //////////////////////////////////////////////////////////////////////////
		// Report # 1 (Orders List)
		public List<ColumnModel> getReport1Columns() {
			List<ColumnModel> columns = new ArrayList<ColumnModel>();
			columns.add(new ColumnModel("orderId", this.getProperty("label.order.id")));
			columns.add(new ColumnModel("dateString", this.getProperty("label.date")));
			columns.add(new ColumnModel("status", this.getProperty("label.status")));
			return columns;
		}

		public List<OrderORM> getReport1List() {
			
			OrderDAO dao = null;
			CompanyDAO cdao = null;
			try {
				InitialContext ctx;
				ctx = new InitialContext();
				dao = (OrderDAO) ctx
						.lookup("java:app/crm_businesslayer/OrderDAO!com.srpl.crm.ejb.request.OrderDAO");
				cdao = (CompanyDAO) ctx
						.lookup("java:app/um_businesslayer/CompanyDAO!com.srpl.um.ejb.request.CompanyDAO");
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (dao == null)
				return null;
			else {
				List<OrderORM> list = null;
				try 
				{
					UmCompany comp=cdao.companyDetails(session.getCompanyId());
					list = dao.listOrders(comp);
				} 
				catch (Exception e) {
					addError("Unable to Fetch Orders Lists");
				}
				return list;
			}
		}

		// /// End of Report
		// 1/////////////////////////////////////////////////////////////////////////


		// //////////////////////////////////////////////////////////////////////////
		// Report # 2 Meter Guage
		// /////////////////////////////////////////////////////////////////////////
		public MeterGaugeChartModel getReport2() {
			// Init Sales Meter Guage
			List<Number> intervals = new ArrayList<Number>() {
				{
					add(20);
					add(60);
					add(100);
					add(120);
				}
			};
			
			MeterGaugeChartModel orderMeter; // report 2
			int meterSale = orderDao.fetchSalesPerformance(session.getUserId());
			orderMeter = new MeterGaugeChartModel(meterSale, intervals);
			return orderMeter;
		}
		// /// End of Report 2
		// //////////////////////////////////////////////////////////////////////////
		
	// //////////////////////////////////////////////////////////////////////////
	// Report # 3 Monthly Sales
	// /////////////////////////////////////////////////////////////////////////
	public CartesianChartModel getReport3() 
	{
		String monthLabels[];
		monthLabels = new String[12];
		monthLabels[0] = "Jan";
		monthLabels[1] = "FEB";
		monthLabels[2] = "MAR";
		monthLabels[3] = "APR";
		monthLabels[4] = "MAY";
		monthLabels[5] = "JUN";
		monthLabels[6] = "JUL";
		monthLabels[7] = "AUG";
		monthLabels[8] = "SEP";
		monthLabels[9] = "OCT";
		monthLabels[10] = "NOV";
		monthLabels[11] = "DEC";
		
		int totalSales[] = orderDao.fetchAnnualSales(session.getUserId());
		

		CartesianChartModel salesGraph; // report 3
		// Init Sales Bar Graph
		salesGraph = new CartesianChartModel();

		ChartSeries monthlySales = new ChartSeries();
		monthlySales.setLabel("Sales");

		for (int i = 0; i < monthLabels.length; i++) {
			monthlySales.set(monthLabels[i], totalSales[i]);
		}
		salesGraph.addSeries(monthlySales);
		
		return salesGraph;
	}

	// /// End of Report 3
	// //////////////////////////////////////////////////////////////////////////

	// //////////////////////////////////////////////////////////////////////////
	// Report # 4
	// /////////////////////////////////////////////////////////////////////////
	public PieChartModel getReport4() {
		

		PieChartModel pieModel; // report 4
		pieModel = new PieChartModel();
		pieModel.set("Internet", 540);
		pieModel.set("Telephone", 325);
		pieModel.set("VAS", 702);
		pieModel.set("CableTV", 421);
		
		return pieModel;
	}

	// /// End of Report 4
	// //////////////////////////////////////////////////////////////////////////
		

	// //////////////////////////////////////////////////////////////////////////
	// Report # 5 (Company
	// List)/////////////////////////////////////////////////
	public List<ColumnModel> getReport5Columns() {
		List<ColumnModel> companyColumns = new ArrayList<ColumnModel>();
		companyColumns.add(new ColumnModel("companyId", this.getProperty("label.id")));
		companyColumns.add(new ColumnModel("companyName",this.getProperty("label.title")));
		companyColumns.add(new ColumnModel("companyEmail", this.getProperty("label.email")));
		return companyColumns;
	}

	public List<UmCompany> getReport5List() {
		/*
		 * @EJB CompanyDAOh companyDAO;
		 */

		CompanyDAO companyDAO = null;
		try {
			InitialContext ctx;
			ctx = new InitialContext();
			companyDAO = (CompanyDAO) ctx
					.lookup("java:app/um_businesslayer/CompanyDAO!com.srpl.um.ejb.request.CompanyDAO");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (companyDAO == null)
			return null;
		else {
			List<UmCompany> list = null;
			try {
				list = companyDAO.listCompanies();
			} catch (Exception e) {
				addError("Unable to Fetch Company List");
			}
			return list;
		}
	}

	// /// End of Report
	// 5/////////////////////////////////////////////////////////////////////////

	// //////////////////////////////////////////////////////////////////////////
	// Report # 6 (Alerts List)
	public List<ColumnModel> getReport6Columns() {
		List<ColumnModel> alertColumns = new ArrayList<ColumnModel>();
		alertColumns = new ArrayList<ColumnModel>();
		alertColumns.add(new ColumnModel("title", this.getProperty("label.title")));
		alertColumns.add(new ColumnModel("date", this.getProperty("label.date")));
		alertColumns.add(new ColumnModel("isAlert", this.getProperty("label.alert")));
		return alertColumns;
	}

	public List<UmAlertsAndReminders> getReport6List() {

		AlertsAndRemindersDAO alertsAndRemindersDao = null;
		try {
			InitialContext ctx;
			ctx = new InitialContext();
			alertsAndRemindersDao = (AlertsAndRemindersDAO) ctx
					.lookup("java:app/crm_businesslayer/AlertsAndRemindersDAO!com.srpl.crm.ejb.request.AlertsAndRemindersDAO");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (alertsAndRemindersDao == null)
			return null;
		else {
			List<UmAlertsAndReminders> list = null;
			try {
				list = alertsAndRemindersDao.listAlertsAndReminders();
			} catch (Exception e) {
				addError("Unable to Fetch Alerts List");
			}
			return list;
		}
	}

	// /// End of Report
	// 6/////////////////////////////////////////////////////////////////////////

	// Report # 7 (Franchise List)
	public List<ColumnModel> getReport7Columns() {
		List<ColumnModel> cols = new ArrayList<ColumnModel>();
		cols.add(new ColumnModel("franchiseId", this.getProperty("label.id")));
		cols.add(new ColumnModel("franchiseTitle", this.getProperty("label.title")));
		cols.add(new ColumnModel("isLocation", this.getProperty("label.franchise.location")));
		return cols;
	}

	public List<UmFranchise> getReport7List() {

		FranchiseDAO dao = null;
		try {
			InitialContext ctx;
			ctx = new InitialContext();
			dao = (FranchiseDAO) ctx
					.lookup("java:app/um_businesslayer/FranchiseDAO!com.srpl.um.ejb.request.FranchiseDAO");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			addError("Unable to Fetch Franchise List");
			e.printStackTrace();
		}
		if (dao == null)
			return null;
		else {
			List<UmFranchise> list = null;
			try {
				list = dao.list(session.getCompanyId());
			} catch (Exception e) {
				addError("Unable to Fetch Franchise List");
			}
			return list;
		}
	}

	// /// End of Report
	// 7/////////////////////////////////////////////////////////////////////////

	// Report # 8 (User
	// List)////////////////////////////////////////////////////////////////
	public List<ColumnModel> getReport8Columns() {
		List<ColumnModel> cols = new ArrayList<ColumnModel>();
		cols.add(new ColumnModel("userId", this.getProperty("label.id")));
		cols.add(new ColumnModel("userFname", this.getProperty("label.first.name")));
		cols.add(new ColumnModel("userLname",this.getProperty("label.last.name")));
		cols.add(new ColumnModel("userJobtitle", this.getProperty("label.job.title")));
		return cols;
	}

	public List<UmUser> getReport8List() {

		UserDAO dao = null;
		try {
			InitialContext ctx;
			ctx = new InitialContext();
			dao = (UserDAO) ctx
					.lookup("java:app/um_businesslayer/UserDAO!com.srpl.um.ejb.request.UserDAO");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (dao == null)
			return null;
		else {
			List<UmUser> list = null;
			try {
				list = dao.listUsersByCompany(session.getCompanyId());
			} catch (Exception e) {
				addError("Unable to Fetch User List");
			}
			return list;
		}
	}

	// /// End of Report
	// 8/////////////////////////////////////////////////////////////////////////

	// Report # 9 (Launched
	// Campaigns)////////////////////////////////////////////////////////////////
	public List<ColumnModel> getReport9Columns() {
		List<ColumnModel> cols = new ArrayList<ColumnModel>();
		cols.add(new ColumnModel("id", this.getProperty("label.id")));
		cols.add(new ColumnModel("title",  this.getProperty("label.title")));;
		cols.add(new ColumnModel("startDateFormatted",  this.getProperty("label.start.date")));
		cols.add(new ColumnModel("endDateFormatted", this.getProperty("label.end.date")));
		return cols;
	}

	public List<MCampaign> getReport9List() {

		CampaignDAO dao = null;
		try {
			InitialContext ctx;
			ctx = new InitialContext();
			dao = (CampaignDAO) ctx
					.lookup("java:app/crm_businesslayer/CampaignDAO!com.srpl.crm.ejb.request.CampaignDAO");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (dao == null)
			return null;
		else {
			List<MCampaign> list = null;
			try {
				list = dao.listLaunchedCampaigns(session.getCompanyId());
			} catch (Exception e) {
				addError("Unable to Fetch Campaign List");
			}
			return list;
		}
	}

	// /// End of Report
	// 9/////////////////////////////////////////////////////////////////////////

	// /// End of Report
	// 9/////////////////////////////////////////////////////////////////////////

	// Report # 10 (User
	// List)////////////////////////////////////////////////////////////////
	public List<ColumnModel> getReport10Columns() {
		List<ColumnModel> cols = new ArrayList<ColumnModel>();
		cols.add(new ColumnModel("caseTokenNumber", this.getProperty("label.token.no")));
		cols.add(new ColumnModel("customerName", this.getProperty("label.customer.name")));
		cols.add(new ColumnModel("status", this.getProperty("label.status")));
		cols.add(new ColumnModel("caseType", this.getProperty("label.type")));
		return cols;
	}

	public List<SupportCaseORM> getReport10List() {

		CaseDAO dao = null;
		UmUser user = new UmUser();

		try {
			InitialContext ctx;
			ctx = new InitialContext();
			dao = (CaseDAO) ctx
					.lookup("java:app/crm_businesslayer/CaseDAO!com.srpl.crm.ejb.request.CaseDAO");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (dao == null)
			return null;
		else {
			List<SupportCaseORM> list = null;
			try {
				list = dao.retrieveCasesByUser(session.getUserId());

			} catch (Exception e) {
				addError("Unable to Fetch Case List");
			}
			return list;
		}
	}

	// /// End of Report
	// 10/////////////////////////////////////////////////////////////////////////

	
	// //////////////////////////////////////////////////////////////////////////
		// Report # 11 (Invoice List-Customer)
		public List<ColumnModel> getReport11Columns() {
			
			List<ColumnModel> invoiceColumns = new ArrayList<ColumnModel>();
			invoiceColumns.add(new ColumnModel("id",this.getProperty("label.id")));
			invoiceColumns.add(new ColumnModel("title", this.getProperty("label.title")));
			invoiceColumns.add(new ColumnModel("issueDate", this.getProperty("label.issue.date")));
			invoiceColumns.add(new ColumnModel("totalAmount", this.getProperty("label.amount")));
			return invoiceColumns;
		}

		public List<SInvoiceORM> getReport11List() {

			InvoiceDAO invoiceDao = null;
			ContactDAO contactDao=null;
			try {
				InitialContext ctx;
				ctx = new InitialContext();
				invoiceDao = (InvoiceDAO) ctx
						.lookup("java:app/crm_businesslayer/InvoiceDAO!com.srpl.crm.ejb.request.InvoiceDAO");
				contactDao = (ContactDAO) ctx
						.lookup("java:app/crm_businesslayer/ContactDAO!com.srpl.crm.ejb.request.ContactDAO");
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (invoiceDao == null || contactDao==null)
				return null;
			else {
				List<SInvoiceORM> list = null;
				try 
				{
					CsContactORM cust=contactDao.getContactByUserId(session.getUserId());
					cust.getContactId();
					list = invoiceDao.list(cust.getContactId());
				} catch (Exception e) {
					addError("Unable to Fetch Invoice List");
				}
				return list;
			}
		}
		
		public void showinvoice(ActionEvent event)
		{
			Long invoice_id = (Long)event.getComponent().getAttributes().get("row_id");
			invoice_id.toString();
			
			//generateInvoiceHTML
			InvoiceDAO invoiceDao = null;
			try {
				InitialContext ctx;
				ctx = new InitialContext();
				invoiceDao = (InvoiceDAO) ctx
						.lookup("java:app/crm_businesslayer/InvoiceDAO!com.srpl.crm.ejb.request.InvoiceDAO");
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (invoiceDao != null)
			{
				List<SInvoiceORM> list = null;
				try 
				{
					invoiceHtml = invoiceDao.generateInvoiceHTML(invoice_id);
				} catch (Exception e) {
					addError("Unable to Fetch Invoice List");
				}
			}
		}


		// /// End of Report
		// 11/////////////////////////////////////////////////////////////////////////

		
	public void actionListener(ActionEvent event) 
	{
		String reportno= (String)event.getComponent().getAttributes().get("reportno");
		Object row_id= event.getComponent().getAttributes().get("row_id");
		switch (reportno) {
		
		/*case "orderDetails":
			Long campaignId = Long.parseLong(getParameter("campaignId")); 
			System.out.println("CampaignId>>>>>>>>>>>>"+campaignId+"--------"+getParameter("campaignId"));
			if (campaignId != 0) {
				SessionDataBean session = BeanFactory.getInstance()
						.getSessionBean();
				for(CampaignProduct cp: campaignDao.listCampaignProducts(campaignId)){
					session.getCampaignProducts().add(cp.getProductId());
				}
				session.getOrderTabs().get(0).setPath("/view/sales/orders/orderForm.xhtml");

			}
			break;*/
		
		case "1":  // ORDERS LIST
			/*SessionDataBean session = BeanFactory.getInstance()
			.getSessionBean();
			session.setSalesModule_selectedOrder(Long.parseLong(getParameter("row_id")));*/
			try 
	        {
	        	FacesContext.getCurrentInstance().getExternalContext().redirect("/crm/view/sales/orders/orderList.jsf?row_id="+row_id);
	        }
	        catch(IOException e)
	        {
	        	addError("Error redirecting to Order Page");
	        }

			break;
		case "5":  // COMPANY LIST	        
	        try 
	        {
	        	FacesContext.getCurrentInstance().getExternalContext().redirect("/um/view/um/admin/company/index.jsf?company_id="+row_id);
	        }
	        catch(IOException e)
	        {
	        	addError("Error redirecting to Company Page");
	        }
			break;
		case "8":  // USERS LIST
			try 
	        {
	        	FacesContext.getCurrentInstance().getExternalContext().redirect("/um/view/um/admin/user/index.jsf?row_id="+row_id);
	        }
	        catch(IOException e)
	        {
	        	addError("Error redirecting to Users Page");
	        }
			break;
		case "7":   // FRANCHISE LIST
			try 
	        {
	        	FacesContext.getCurrentInstance().getExternalContext().redirect("/um/view/um/admin/franchise/index.jsf?row_id="+row_id);
	        }
	        catch(IOException e)
	        {
	        	addError("Error redirecting to Franchise Page");
	        }
			break;
		case "9":     // LAUNCHED CAMPAIGNS
			try 
	        {
	        	FacesContext.getCurrentInstance().getExternalContext().redirect("/crm/view/marketing/index.jsf?row_id="+row_id);
	        }
	        catch(IOException e)
	        {
	        	addError("Error redirecting to Campaigns Page");
	        }

			break;	
		case "10":    // SUPPORT
			try 
	        {
	        	FacesContext.getCurrentInstance().getExternalContext().redirect("/crm/view/support/cases/tickets.jsf?row_id="+row_id);
	        }
	        catch(IOException e)
	        {
	        	addError("Error redirecting to Campaigns Page");
	        }
			break;
		case "13":
				try 
		        {
					Object cId= event.getComponent().getAttributes().get("campaignId");
					Long campaignId = (Long)cId; 
					//System.out.println("CampaignId>>>>>>>>>>>>"+campaignId+"--------"+cId);
					if (campaignId != 0) {
						SessionDataBean session = BeanFactory.getInstance()
								.getSessionBean();
						for(CampaignProduct cp: campaignDao.listCampaignProducts(campaignId)){
							session.getCampaignProducts().add(cp.getProductId());
						}

					}
		        	FacesContext.getCurrentInstance().getExternalContext().redirect("/crm/view/sales/orders/orderList.jsf");
		        }
		        catch(IOException e)
		        {
		        	addError("Error redirecting to Orders Page");
		        }
			break;
		}
		
	}
		
	// Getter/Setters

	public boolean isShowpopup() {
		SessionDataBean sessionBean = BeanFactory.getInstance()
				.getSessionBean();
		System.out.println(sessionBean.getUserId());
		// Check whether user perferences are set to show pop-up or not
		// also check the session variable, if the pop-up is shown once, dont
		// show it again on current session
		showpopup = sessionBean.isShowPopUp()
				&& utilsDao.openDialog(sessionBean.getUserId());
		sessionBean.setShowPopUp(false);
		return showpopup;
	}

	public void setShowpopup(boolean showpopup) {
		this.showpopup = showpopup;
	}

	public boolean isShowpopupChkBox() {
		return showpopupChkBox;
	}

	public void setShowpopupChkBox(boolean showpopupChkBox) {
		this.showpopupChkBox = showpopupChkBox;
	}

	public void changePreference() {
		SessionDataBean sessionBean = BeanFactory.getInstance()
				.getSessionBean();
		utilsDao.setPreferences(sessionBean.getUserId(), showpopupChkBox);
	}


	/**
	 * @return the invoiceHtml
	 */
	public String getInvoiceHtml() {
		return invoiceHtml;
	}

}
