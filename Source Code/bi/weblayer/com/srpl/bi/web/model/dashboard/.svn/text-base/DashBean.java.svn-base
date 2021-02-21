package com.srpl.bi.web.model.dashboard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

import com.bitguiders.util.KeyValueItem;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.srpl.bi.ejb.entity.DashboardORM;
import com.srpl.bi.ejb.request.DashboardDAO;
import com.srpl.bi.service.ReportService;
import com.srpl.bi.web.common.SessionDataBean;
import com.srpl.bi.web.controller.BeanFactory;
import com.srpl.bi.web.model.IndexBackingBean;
import com.srpl.bi.web.model.reportsbuilder.ReportData;
import com.srpl.bi.web.model.reportsbuilder.DataPaletteBackingBean.ColumnModel;
import com.srpl.bi.web.model.reportsbuilder.ReportGraph;
import com.srpl.um.ejb.entity.UmUser;
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
//	private DashGraphs graphs;
//	boolean showgraph=false;
	int selectedPanel;
	int reportDropdown;
	int selectedGraphType=1;
	final int TOTALREPORTS=4;
	DashboardORM reports[];
//	Integer graphtype[]=new Integer[TOTALREPORTS];
	List<ReportData> reportData=new ArrayList<ReportData>();
	List<ReportGraph> graphs=new ArrayList<ReportGraph>();
	String reportLabels[]=new String[TOTALREPORTS];
	SessionDataBean session;
//	DashReportBean dashReports;
	@EJB
	private DashboardDAO dashDAO;
	
	@EJB 
	UserDAO userDao;
	
	public DashBean() 
	{  
		 // initialize dashboard panels
		 	createDashPanels();
		 	
//		 	graphs=new DashGraphs();
		 	System.out.println("Dashbean constructor");
	 } 
	@PostConstruct
	public void init()
	{
		session = BeanFactory.getInstance().getSessionBean();
		try
		{
			//  get userid and companyid from DB
			FacesContext context = FacesContext.getCurrentInstance();
			String userName = context.getExternalContext().getRemoteUser();
			Long userid=userDao.getUserId(userName);
			UmUser user = userDao.umUserDetails(userid);
			
		
			dashDAO.setDefaultDashboard(user.getUserId().intValue(),user.getUserCompany(), TOTALREPORTS);
			reports=dashDAO.listDashboard(user.getUserId().intValue(),user.getUserCompany(), TOTALREPORTS);
			fetchReportData();
			populateGraphs();
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
	        
	        column1.addWidget("widget1");  
	        column1.addWidget("widget3");  
	          
	        column2.addWidget("widget2");  
	        column2.addWidget("widget4");  

	        model.addColumn(column1);  
	        model.addColumn(column2); 
	        
	 	}
	  	public String userRole()
	   	{
	   		String role="User";
	   		FacesContext context = FacesContext.getCurrentInstance();
	   		
			if(context.getExternalContext().isUserInRole("Administrator"))
				role="Administrator";
			if(context.getExternalContext().isUserInRole("AccountManager"))
				role="AccountManager";
			if(context.getExternalContext().isUserInRole("User"))
				role="User";
			return role;
	   	}
	   
	   	public void fetchReportData()
	   	{
	   		ReportService rservice=ReportService.getInstance();
	   		
	   		for(int i=0;i<reports.length;i++)
	   		{
	   			ReportData rep=new ReportData();
	   			try {
					rservice.getReportData(reports[i].getReport(), rep.getRows(), rep.getDndColumns());
					Map<String,String> reportRow=rservice.editReport(reports[i].getReport());
					reportLabels[reports[i].getPosition()-1]=reportRow.get("bi_report_title");
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	   			reportData.add(i,rep);
	   		}
	   	}
	  	
	   	public List<KeyValueItem> getReportsList() {
			ReportService rs = ReportService.getInstance();
			SessionDataBean session = BeanFactory.getInstance().getSessionBean();
			List<KeyValueItem> list = rs.getReportListByUserId(session.getUserId(), session.getCompanyId());
			return list;
		}
	   	
	 	// Dashboard Panels reorder action listerner
	    public void handleReorder(DashboardReorderEvent event) {  
	    }  
	      
	    
	    // action listener for dialog add report
	    public void addReport(ActionEvent event)
	    {
	    	updateReport(selectedPanel, reportDropdown,selectedGraphType);
	    	reports[selectedPanel-1].setReport(reportDropdown);
	    	reports[selectedPanel-1].setGraphtype(selectedGraphType);
	    	
	    	ReportService rservice=ReportService.getInstance();
	   		ReportData rep=new ReportData();
	   		try {
				rservice.getReportData(reports[selectedPanel-1].getReport(), rep.getRows(), rep.getDndColumns());
				Map<String,String> reportRow=rservice.editReport(reports[selectedPanel-1].getReport());
				reportLabels[selectedPanel-1]=reportRow.get("bi_report_title");
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   		reportData.set(selectedPanel-1,rep);
	   		if(selectedGraphType!=1 && reportDropdown!=0)
	   		{
	   			graphs.get(selectedPanel-1).populateGraph(rep.getDndColumns(), rep.getRows());
	   		}
	    }
	   
		// actionlistener when + button on panel is pressed 
	    public void selectedPanel(ActionEvent event)
	    {
	    	String position= (String)event.getComponent().getAttributes().get("selectedPanel");
	    	selectedPanel= Integer.parseInt(position);
	    	if(reports[selectedPanel-1].getReport()!=0)
	    	{
	    		reportDropdown=reports[selectedPanel-1].getReport();
	    		selectedGraphType=reports[selectedPanel-1].getGraphtype();
	    	}
	    }
	    
	    // actionlisntener when X button on panel is pressed
	    public void clearReport(ActionEvent event)
	    {
	    	String position= (String)event.getComponent().getAttributes().get("selectedPanel");
	    	selectedPanel= Integer.parseInt(position);
	    	reports[selectedPanel-1].setReport(0);
	    	reports[selectedPanel-1].setGraphtype(1);
	    	updateReport(selectedPanel, 0,1);
	    	graphs.get(selectedPanel-1).clear();
	    	reportLabels[selectedPanel-1]="";
	    }
	    
	    // update panel position on DB
	    private void updateReport(int position, int report,int graphtype)
	    {
	    	//(Integer user, Integer position, Integer report, Long companyid ){
	    	DashboardORM panel=new DashboardORM(session.getUserId().intValue(),position,report,session.getCompanyId(),graphtype);
	    	dashDAO.setPanel(panel);
	    }
	    
	    private void populateGraphs()
	    {
	    	for(int i=0;i<TOTALREPORTS;i++)
	    	{
	    		graphs.add(i, new ReportGraph());
	    		if(reports[i].getGraphtype()!=1 && reports[i].getReport()!=0)
	    		{
	    			graphs.get(i).populateGraph(reportData.get(i).getDndColumns(), reportData.get(i).getRows());
	    		}
	    	}
	    }
	    
	    private void addMessage(FacesMessage message) {  
	        FacesContext.getCurrentInstance().addMessage(null, message);  
	    }  
	      
	    public DashboardModel getModel() {  
	        return model;  
	    }
		public DashboardORM[] getReports() {
			return reports;
		}
		public void setReports(DashboardORM[] reports) {
			this.reports = reports;
		}
		public int getReportDropdown() {
			return reportDropdown;
		}
		public void setReportDropdown(int reportDropdown) {
			this.reportDropdown = reportDropdown;
		}
		 public List<ReportData> getReportData() {
				return reportData;
		}
		public void setReportData(List<ReportData> reportData) {
				this.reportData = reportData;
		}
/*		public DashReportBean getDashReports() {
			return dashReports;
		}*/
		
		public List<Map<String, String>> getRowdata()
		{
			return (reportData.size() > 0) ? reportData.get(0).getRows() : null;
		}
		
		public List<ColumnModel> getDnd()
		{
			return (reportData.size() > 0) ? reportData.get(0).getDndColumns() : null;
		}
		public List<ReportGraph> getGraphs() {
			return graphs;
		}
		public int getSelectedGraphType() {
			return selectedGraphType;
		}
		public void setSelectedGraphType(int selectedGraphType) {
			this.selectedGraphType = selectedGraphType;
		}
		public String[] getReportLabels() {
			return reportLabels;
		}
		public void setReportLabels(String[] reportLabels) {
			this.reportLabels = reportLabels;
		}

}
