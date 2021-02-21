package com.srpl.crm.web.model.dashboard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;
import com.srpl.crm.web.controller.BeanFactory;
import com.srpl.crm.ejb.entity.DashboardORM;
import com.srpl.crm.ejb.request.DashboardDAO;
import com.srpl.crm.web.common.SessionDataBean;
import com.srpl.um.ejb.entity.UmUser;
import com.srpl.um.ejb.request.GroupDAO;
import com.srpl.um.ejb.request.ServiceDAO;
import com.srpl.um.ejb.request.UserDAO;


@ManagedBean(name="dashBean")
@SessionScoped
public class DashBean extends JSFBeanSupport implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4607301622939723746L;
	private DashboardModel model;
	private DashGraphs graphs;
	boolean showgraph=false;
	int selectedPanel=0;
	int reportDropdown;
	final int TOTALREPORTS=4;
	Integer reports[];
	SessionDataBean session;
	
	final String reportLabels[]={"",this.getProperty("dashboard.report1"),this.getProperty("dashboard.report2"),this.getProperty("dashboard.report3"),this.getProperty("dashboard.report4"),
			this.getProperty("dashboard.report5"),this.getProperty("dashboard.report6"),this.getProperty("dashboard.report7"),this.getProperty("dashboard.report8"),
			this.getProperty("dashboard.report9"),this.getProperty("dashboard.report10"),this.getProperty("dashboard.report11")};
//	DashReportBean dashReports;
	@EJB
	private DashboardDAO dashDAO;
	
	@EJB 
	UserDAO userDao;
	@EJB GroupDAO groupDAO;
	@EJB ServiceDAO serviceDAO;
	
	public DashBean() 
	{  
//		reportLabels=new String[TOTALREPORTS];
		
		// initialize dashboard panels
		 	createDashPanels();
		 	graphs=new DashGraphs();
		 //	System.out.println("Dashbean constructor");
		 
	 } 
	@PostConstruct
	public void init()
	{
		getProfile().setDAOInstances(userDao,groupDAO, serviceDAO);
		setCurrentAction(WebConstants.ACTION_SECURITY,this.getClass()); 
		session = BeanFactory.getInstance().getSessionBean();
		try
		{
			//  get userid and companyid from DB
			FacesContext context = FacesContext.getCurrentInstance();
			String userName = context.getExternalContext().getRemoteUser();
			Long userid=userDao.getUserId(userName);
			UmUser user = userDao.umUserDetails(userid);
			
		
			dashDAO.setDefaultDashboard(userid.intValue(), user.getUserCompany(), userRole(), TOTALREPORTS);
			reports=dashDAO.listDashboard(user.getUserId().intValue(), user.getUserCompany(), TOTALREPORTS,userRole());
		}
		catch(Exception e)
		{
			addError("Dashboard Loading Failed");
		}
	}
	 public void createDashPanels()
	 {
	 		model = new DefaultDashboardModel();  
	        DashboardColumn column1 = new DefaultDashboardColumn();  
	        DashboardColumn column2 = new DefaultDashboardColumn(); 
	        
	        column1.addWidget("sales");  
	        column1.addWidget("profits");  
	          
	        column2.addWidget("revenue");  
	        column2.addWidget("trend");  
	        
	      
	     //   column2.addWidget("trend");  

	        model.addColumn(column1);  
	        model.addColumn(column2); 
	       
	        
	 	}
	   	public String userRole()
	   	{
	   		String role="";
	   		FacesContext context = FacesContext.getCurrentInstance();
	   		
			if(context.getExternalContext().isUserInRole("Administrator"))
				role="Administrator";
			if(context.getExternalContext().isUserInRole("AccountManager"))
				role="AccountManager";
			if(context.getExternalContext().isUserInRole("User"))
				role="User";
			if(context.getExternalContext().isUserInRole("Customer"))
				role = "Customer";
			return role;
	   	}
	   
	   	// Report Drop Down Menu Items (Based on user Role)
	   	public List<ReportSelect> getReportMenu()
	   	{
	   		List<ReportSelect> reportMenu=new ArrayList<ReportSelect>();
	   		String role=userRole();
	   		if(role.equals("Administrator"))
	   		{
	   			reportMenu.add(new ReportSelect(5,this.getProperty("dashboard.report5")));
	   			reportMenu.add(new ReportSelect(6,this.getProperty("dashboard.report6")));
	   		}
	   		if(role.equals("AccountManager"))
	   		{
	   			reportMenu.add(new ReportSelect(7,this.getProperty("dashboard.report7")));
	   			reportMenu.add(new ReportSelect(8,this.getProperty("dashboard.report8")));
	   			reportMenu.add(new ReportSelect(9,this.getProperty("dashboard.report9")));
	   		}
	   		if(role.equals("User"))
	   		{
	   			reportMenu.add(new ReportSelect(1,this.getProperty("dashboard.report1")));
	   			reportMenu.add(new ReportSelect(2,this.getProperty("dashboard.report2")));
	   			reportMenu.add(new ReportSelect(3,this.getProperty("dashboard.report3")));
	   			reportMenu.add(new ReportSelect(4,this.getProperty("dashboard.report4")));
	   			reportMenu.add(new ReportSelect(9,this.getProperty("dashboard.report9")));
	   			reportMenu.add(new ReportSelect(10, this.getProperty("dashboard.report10")));
	   		}
	   		if(role.equals("Customer"))
	   		{
	   			reportMenu.add(new ReportSelect(11,this.getProperty("dashboard.report1")));
	   		}
	   		
	   		return reportMenu;
	   		/*
	   		 * <p:selectOneListbox id="reportDropdown" style="width:200px" value="#{dashBean.reportDropdown}">
					<f:selectItem itemLabel="No Report" itemValue="0" />
					<f:selectItem itemLabel="Orders List" itemValue="1"/>
					<f:selectItem itemLabel="Revenue(Meter Guage)" itemValue="2"/>
					<f:selectItem itemLabel="Sales Graph(Line Chart)" itemValue="3"/>
					<f:selectItem itemLabel="Product Sales(Pie Chart)" itemValue="4" />
				</p:selectOneListbox>
	   		 */
	   	}
	   	
	 	// Dashboard Panels reorder action listerner
	    public void handleReorder(DashboardReorderEvent event) {  
	    }  
	      
	    
	    // action listener for dialog add report
	    public void addReport(ActionEvent event)
	    {
	    	updateReport(selectedPanel, reportDropdown);
	    	reports[selectedPanel-1]=reportDropdown;
	    }
	    // actionlistener when + button on panel is pressed 
	    public void selectedPanel(ActionEvent event)
	    {
	    	String position= (String)event.getComponent().getAttributes().get("selectedPanel");
	    	selectedPanel= Integer.parseInt(position);
	    }
	    
	    // actionlisntener when X button on panel is pressed
	    public void clearReport(ActionEvent event)
	    {
	    	String position= (String)event.getComponent().getAttributes().get("selectedPanel");
	    	selectedPanel= Integer.parseInt(position);
	    	reports[selectedPanel-1]=0;
	    	updateReport(selectedPanel, reportDropdown);
	    }
	    
	    // update panel position on DB
	    private void updateReport(int position, int report)
	    {
	    	//(Integer user, Integer position, Integer report, Long companyid ){
	    	DashboardORM panel=new DashboardORM(session.getUserId().intValue(),position,report,session.getCompanyId());
	    	dashDAO.setPanel(panel);
	    }
	    
	    private void addMessage(FacesMessage message) {  
	        FacesContext.getCurrentInstance().addMessage(null, message);  
	    }  
	      
	    public DashboardModel getModel() {  
	        return model;  
	    }
		public DashGraphs getGraphs() {
			return graphs;
		}
		public boolean isShowgraph() {
			return showgraph;
		}
		public void setShowgraph(boolean showgraph) {
			this.showgraph = showgraph;
		}
		public Integer[] getReports() {
			return reports;
		}
		public void setReports(Integer[] reports) {
			this.reports = reports;
		}
		public int getReportDropdown() {
			return reportDropdown;
		}
		public void setReportDropdown(int reportDropdown) {
			this.reportDropdown = reportDropdown;
		}
/*		public DashReportBean getDashReports() {
			return dashReports;
		}*/
		public String[] getReportLabels() {
			return reportLabels;
		}

}
