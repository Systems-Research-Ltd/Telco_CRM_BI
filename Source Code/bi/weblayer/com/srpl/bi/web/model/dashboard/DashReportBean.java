package com.srpl.bi.web.model.dashboard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.MeterGaugeChartModel;
import org.primefaces.model.chart.PieChartModel;

import com.bitguiders.util.jsf.JSFBeanSupport;
import com.srpl.bi.web.common.SessionDataBean;
import com.srpl.bi.web.controller.BeanFactory;
import com.srpl.bi.web.model.common.ColumnModel;

@ManagedBean(name = "dashReportBean")
@ViewScoped
public class DashReportBean extends JSFBeanSupport implements Serializable {
	private static final long serialVersionUID = 1L;
/*	private boolean showpopup = true;
	private boolean showpopupChkBox = true;*/
	private List<SampleTable> datalist;// report 1
	private List<ColumnModel> columns;// report 1 columns
	/*private List<AssignedTaskORM> tasks;// 
	private List<ColumnModel> taskcolumns;*/
	SessionDataBean session;
/*	@EJB
	OrderDAO orderDao;*/
/*	@EJB
	AssignedTaskDAO tasksDAO;*/
	/*@EJB
	UtilsDAO utilsDao;*/

	private MeterGaugeChartModel orderMeter; // report 2
	private CartesianChartModel salesGraph; //  report 3
	private String monthLabels[];
	
	private PieChartModel pieModel;  // report 4

	public DashReportBean() {

		session = BeanFactory.getInstance().getSessionBean();
		columns = new ArrayList<ColumnModel>();
		columns.add(new ColumnModel("region", "REGION"));
		columns.add(new ColumnModel("product", "PRODUCT"));
		columns.add(new ColumnModel("sales", "SALES (PKR)"));

	/*	taskcolumns = new ArrayList<ColumnModel>();
		taskcolumns.add(new ColumnModel("refId", "ID"));
		taskcolumns.add(new ColumnModel("categoryText", "TYPE"));
		taskcolumns.add(new ColumnModel("statusText", "STATUS"));*/

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

/*		try {
			this.schedularTest();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	    pieModel = new PieChartModel();  
	    
        pieModel.set("Internet", 540);  
        pieModel.set("Telephone", 325);  
        pieModel.set("VAS", 702);  
        pieModel.set("CableTV", 421); 
        init_();
	}

	void init_() {
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		
		// Populate with DUMMY DATA( to be romoved later)
		int meterSale = 92;
		Random randomSales=new Random();
		int totalSales[] = new int[12];
		for(int i=0;i<12;i++)
			totalSales[i]=randomSales.nextInt(197);
		datalist=new ArrayList<SampleTable>();
		datalist.add(new SampleTable("1","Islamabad","DSL","1200Rs"));
		datalist.add(new SampleTable("2","Islamabad","Tele","300Rs"));
		datalist.add(new SampleTable("3","Lahore","DSL","3000Rs"));
		datalist.add(new SampleTable("4","Lahore","Tele","600Rs"));
		datalist.add(new SampleTable("5","Peshawar","DSL","950Rs"));
        //////////////////////////////////////////////////////////////////////////
		// Init Sales Meter Guage
		List<Number> intervals = new ArrayList<Number>() {
			{
				add(20);
				add(60);
				add(100);
				add(120);
			}
		};
		orderMeter = new MeterGaugeChartModel(meterSale, intervals);

		// Init Sales Bar Graph
		salesGraph = new CartesianChartModel();

		ChartSeries monthlySales = new ChartSeries();
		monthlySales.setLabel("Sales");

		for (int i = 0; i < monthLabels.length; i++) {
			monthlySales.set(monthLabels[i], totalSales[i]);
		}
		salesGraph.addSeries(monthlySales);

/*		Long uId = session.getUserId();
		tasks = tasksDAO.list(session.getUserId());*/
	
	}

	// Getter/Setters

/*	public boolean isShowpopup() {
		SessionDataBean sessionBean = BeanFactory.getInstance()
				.getSessionBean();
		System.out.println(sessionBean.getUserId());
		// Check whether user perferences are set to show pop-up or not
		// also check the session variable, if the pop-up is shown once, dont
		// show it again on current session
		showpopup = sessionBean.isShowPopUp() && utilsDao.openDialog(sessionBean.getUserId());
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
	}*/

	public List<ColumnModel> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnModel> columns) {
		this.columns = columns;
	}

	public List<SampleTable> getDatalist() {
		return datalist;
	}

	public MeterGaugeChartModel getOrderMeter() {
		return orderMeter;
	}

	public CartesianChartModel getSalesGraph() {
		return salesGraph;
	}

	
	public PieChartModel getPieModel() {  
	      return pieModel;  
	}  
}
