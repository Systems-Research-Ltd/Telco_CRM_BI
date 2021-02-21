package com.srpl.crm.web.model.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

import com.bitguiders.util.KeyValueItem;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;
import com.srpl.crm.ejb.entity.ReportsORM;
import com.srpl.crm.ejb.request.ReportsDAO;
import com.srpl.crm.web.common.SessionDataBean;
import com.srpl.crm.web.controller.BeanFactory;
import com.srpl.um.ejb.entity.UmCompany;
import com.srpl.um.ejb.entity.UmUser;
import com.srpl.um.ejb.exceptions.UserNotFoundException;
import com.srpl.um.ejb.request.CompanyDAO;
import com.srpl.um.ejb.request.UserDAO;

@ManagedBean(name = "reportBean")
@ViewScoped
public class ReportBackingBean extends JSFBeanSupport implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DualListModel<ColumnsBean> columns;
	private String selectedTable;
	private int id;
	private String title;
	private String description;
	private String summary;
	private String cols;
	private String colsTitles;
	private String from;
	private String where;
	private String exportOption;
	private int selectedReport;
	private String tableAlias;
	private String selectedTableAlias;
	private String orderBy;
	private String reportTypeColumns;
	List<ColumnsBean> columnsSource;
	List<ColumnsBean> columnsTarget;
	private String action;
	private String reportType = "detail";
	private String labels;
	private String values;
	private String label;
	private String value;
	private int labelId;
	private boolean isEditMode;
	private int summaryCounter; //
	private String reportBy;
	private boolean isDate;
	private boolean showGraph = false;
	private String xAxis;
	private String yAxis;
	private String graphType;
	private String pieChartCol;
	
	private List<String> selectedGroupByColumns;
	private List<String> selectedOrderByColumns;
	private List<String> selectedFilter;
	private String groupBy;
	private boolean showImage = false;
	private List<String> currentFilter = new ArrayList<String>();
	List<String> filterList = new ArrayList<String>();
	private String selectedOperator;
	private int currentFilterSize = 0;
	private String filterValue;
	private List<String> selectedColumn = new ArrayList<String>();
	private String selectedFilterTable;
	List<KeyValueItem> tablesList = new ArrayList<KeyValueItem>();
	private String ASC;
	private String DESC;
	private String orderBySorting;
	

	List<ReportBackingBean> summaryList = new ArrayList<ReportBackingBean>();
	
	@EJB
	ReportsDAO reportsDao;
	@EJB
	UserDAO userDao;
	@EJB CompanyDAO companyDao;

	public ReportBackingBean() {
		createNewReport();
		setCurrentAction(WebConstants.ACTION_SECURITY, this.getClass());

	}

	public ReportBackingBean(int id, String title) {
		this.setId(id);
		this.setTitle(title);
	}
	
	@PostConstruct
	public void init(){
	

		ReportBackingBean temp = new ReportBackingBean();
		int temp_id = 0;
		String temp_label = null;
		String temp_value = null;
		
		try{
			temp_id = Integer.valueOf(this.getParameter("temp_id"));
			temp_label = this.getParameter("temp_label");
			temp_value = this.getParameter("temp_value");
			
			temp.setLabelId(temp_id);
			temp.setLabel(temp_label);
			temp.setValue(temp_value);
			
			this.summaryList.add(temp);
		
		}catch (Exception e) {
			System.out.println("exception while fetching values.");
		}
		
	}
	

	/**
	 * @return the selectedTable
	 */
	public String getSelectedTable() {
		return selectedTable;
	}

	/**
	 * @param selectedTable
	 *            the selectedTable to set
	 */
	public void setSelectedTable(String selectedTable) {
		this.selectedTable = selectedTable;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	/**
	 * @return the cols
	 */
	public String getCols() {
		return cols;
	}

	/**
	 * @param cols
	 *            the cols to set
	 */
	public void setCols(String cols) {
		this.cols = cols;
	}

	public String getColsTitles() {
		return colsTitles;
	}

	public void setColsTitles(String colsTitles) {
		this.colsTitles = colsTitles;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getWhere() {
		return where;
	}

	public void setWhere(String where) {
		this.where = where;
	}

	/**
	 * @return the exportOption
	 */
	public String getExportOption() {
		return exportOption;
	}

	/**
	 * @param exportOption
	 *            the exportOption to set
	 */
	public void setExportOption(String exportOption) {
		this.exportOption = exportOption;
	}

	public int getSelectedReport() {
		return selectedReport;
	}

	public List<String> getSelectedFilter() {
		return selectedFilter;
	}

	public void setSelectedFilter(List<String> selectedFilter) {
		this.selectedFilter = selectedFilter;
	}

	public void setSelectedReport(int selectedReport) {
		this.selectedReport = selectedReport;
	}

	public String getTableAlias() {
		return tableAlias;
	}

	public void setTableAlias(String tableAlias) {
		this.tableAlias = tableAlias;
	}

	public String getSelectedTableAlias() {
		return selectedTableAlias;
	}

	public void setSelectedTableAlias(String selectedTableAlias) {
		this.selectedTableAlias = selectedTableAlias;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public String getReportTypeColumns() {
		return reportTypeColumns;
	}

	public void setReportTypeColumns(String reportTypeColumns) {
		this.reportTypeColumns = reportTypeColumns;
	}
	
	public String getLabels() {
		return labels;
	}

	public void setLabels(String labels) {
		this.labels = labels;
	}

	public String getValues() {
		return values;
	}

	public void setValues(String values) {
		this.values = values;
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public int getLabelId() {
		return labelId;
	}

	public void setLabelId(int labelId) {
		this.labelId = labelId;
	}
	
	public String getASC() {
		return ASC;
	}

	public void setASC(String aSC) {
		ASC = aSC;
	}

	public String getDESC() {
		return DESC;
	}

	public void setDESC(String dESC) {
		DESC = dESC;
	}

	public String getOrderBySorting() {
		return orderBySorting;
	}

	public void setOrderBySorting(String orderBySorting) {
		this.orderBySorting = orderBySorting;
	}

	public boolean isEditMode() {
		return isEditMode;
	}

	public void setEditMode(boolean isEditMode) {
		this.isEditMode = isEditMode;
	}
	
	public int getSummaryCounter() {
		return summaryCounter;
	}

	public void setSummaryCounter(int summaryCounter) {
		this.summaryCounter = summaryCounter;
	}
	
	public List<String> getSelectedGroupByColumns() {
		return selectedGroupByColumns;
	}

	public void setSelectedGroupByColumns(List<String> selectedGroupByColumns) {
		this.selectedGroupByColumns = selectedGroupByColumns;
	}

	public List<String> getSelectedOrderByColumns() {
		return selectedOrderByColumns;
	}

	public void setSelectedOrderByColumns(List<String> selectedOrderByColumns) {
		this.selectedOrderByColumns = selectedOrderByColumns;
	}

	public String getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}

	public String getReportBy() {
		return reportBy;
	}

	public void setReportBy(String reportBy) {
		this.reportBy = reportBy;
	}
	
	public boolean isDate() {
		return isDate;
	}

	public void setDate(boolean isDate) {
		this.isDate = isDate;
	}
	
	public boolean isShowGraph() {
		return showGraph;
	}

	public void setShowGraph(boolean showGraph) {
		this.showGraph = showGraph;
	}

	public String getxAxis() {
		return xAxis;
	}

	public void setxAxis(String xAxis) {
		this.xAxis = xAxis;
	}

	public String getyAxis() {
		return yAxis;
	}

	public void setyAxis(String yAxis) {
		this.yAxis = yAxis;
	}
	
	public String getGraphType() {
		return graphType;
	}

	public void setGraphType(String graphType) {
		this.graphType = graphType;
	}
	
	public String getPieChartCol() {
		return pieChartCol;
	}

	public void setPieChartCol(String pieChartCol) {
		this.pieChartCol = pieChartCol;
	}


	public boolean isShowImage() {
		return showImage;
	}

	public void setShowImage(boolean showImage) {
		this.showImage = showImage;
	}

	public List<String> getCurrentFilter() {
		return currentFilter;
	}

	public void setCurrentFilter(List<String> currentFilter) {
		this.currentFilter = currentFilter;
	}

	public String getSelectedOperator() {
		return selectedOperator;
	}

	public void setSelectedOperator(String selectedOperator) {
		this.selectedOperator = selectedOperator;
	}

	public int getCurrentFilterSize() {
		return currentFilterSize;
	}

	public void setCurrentFilterSize(int currentFilterSize) {
		this.currentFilterSize = currentFilterSize;
	}

	public String getFilterValue() {
		return filterValue;
	}

	public void setFilterValue(String filterValue) {
		this.filterValue = filterValue;
	}

	public List<String> getSelectedColumn() {
		return selectedColumn;
	}

	public void setSelectedColumn(List<String> selectedColumn) {
		this.selectedColumn = selectedColumn;
	}

	public String getSelectedFilterTable() {
		return selectedFilterTable;
	}

	public void setSelectedFilterTable(String selectedFilterTable) {
		this.selectedFilterTable = selectedFilterTable;
	}

	public Map<String, Object> getTables() {
		ReportSql rs = new ReportSql();
		Map<String, Object> tables = new LinkedHashMap<String, Object>();
		List<String> tbls = rs.listTables();
		for (String t : tbls) {
			String tbl = rs.TableAliasing(t);
			if(t.equals("crm.reports")){
				tables.remove("crm.reports");
			}
			else{
			tables.put(tbl, t);
			}
		}
		return tables;
	}
	
	public void resetField(){
		setEditMode(false);
	}

	public void columnListener() {
		columnsSource = new ArrayList<ColumnsBean>();
		columnsTarget = new ArrayList<ColumnsBean>();
		ReportSql rs = new ReportSql();
		String temp[] = getSelectedTable().split("\\.");
		String table = temp[1];
		String schema = temp[0];
		List<String> cols = rs.listTableColumns(schema, table);
		String tableAlias = rs.TableDBAlias(table);
		setSelectedTableAlias(rs.TableDBAlias(table));
		String tCols = getCols();
		if (tCols.equals("")) {
			for (String c : cols) {
			String colVal = table + "." + c;
			if(c.equals("user_password")){
				columnsSource.remove("user_password");
			}
			else
				{
				columnsSource.add(new ColumnsBean(colVal, c));
				
				}
			}
		} else {
			String tColArr[] = tCols.split(";");
			for (int i = 0; i<cols.size(); i++) {
				boolean match = false;
				String colVal = table + "." + cols.get(i);
				for (int j=0; j < tColArr.length; j++ ) {
					if (tColArr[j].equals(colVal)) {
						match = true;
						break;
					}
				}
				if(match == false){
					columnsSource.add(new ColumnsBean(colVal, cols.get(i)));
				}
			}
		}
		

		columns = new DualListModel<ColumnsBean>(columnsSource, columnsTarget);
	}
	

	public DualListModel<ColumnsBean> getColumns() {
		return columns;
	}

	public void setColumns(DualListModel<ColumnsBean> columns) {
		this.columns = columns;
	}
	

	public void createNewReport() {
		summaryList = new ArrayList<ReportBackingBean>();
		columnsTarget = new ArrayList<ColumnsBean>();
		setAction("create");
		setTitle("Report Title");
		setDescription("Report Description");
		setCols("");
		setColsTitles("");
		setExportOption("pdf");
		setSelectedTable("um_crm.um_users");
		setFrom("");
		setWhere("");
		setTableAlias("");
		setSelectedTableAlias("u");
		setOrderBy("");
		setReportType("");
		setReportTypeColumns("");
		setSummary("");
		setLabels("Label");
		setValues("Value");
		setReportBy("");
		columnListener();
		setEditMode(false);
		setSummaryCounter(1);
		setShowGraph(false);
		setGroupBy("");
		setShowImage(false);
		setSelectedFilterTable("um_crm.um_users");
		filterList.clear();
		currentFilter.clear();
		selectedGroupByColumns = new ArrayList<String>();
		selectedOrderByColumns = new ArrayList<String>();
		currentFilterSize = 0;
		setSelectedOperator( "");
		setFilterValue("");
		setOrderBySorting("");
		setGraphType("");
		//ReportSql rs = new ReportSql();
		//System.out.println("Order change date is "+rs.getColumnType("orders", "order_change_date"));
		//System.out.println("Target List size in on create new 2  "+columns.getTarget());
	}

	public String saveReport() {
		//generateWhereClause();
		getFilterAsString();
		generateSummary();
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		UmUser user = null;
		try {
			user = userDao.umUserDetails(session.getUserId());
		} catch (UserNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		UmCompany company = companyDao.companyDetails(session.getCompanyId());
		//System.out.println("save report from and where "+this.getFrom()+"------"+this.getWhere());
		ReportsORM details = new ReportsORM(this.getTitle(),
				this.getDescription(), this.getSummary(), this.getCols(), this.getColsTitles(),
				this.getFrom(), this.getWhere(), this.getOrderBy(),
				this.getReportType(), this.getReportTypeColumns(), this.getReportBy(),this.getGroupBy(), user, company);
		// System.out.println(this.getTitle()+"--"+
		// this.getDescription()+"--"+this.getCols()+"--"+this.getColsTitles()+"--"+this.getFrom()+"--"+this.getWhere()+"--"+this.getOrderBy());
		String value;
		try {	
			reportsDao.createReport(details);
			this.addMessage(getProperty("message.report.created"));
			value = "success";
		} catch (Exception e) {
			this.addError(getProperty("message.report.creation.failed"));
			value = "failure";
		}
		
		return value;
	}

	public void editReport() {
		// System.out.println("Edit report called");
		createNewReport();
		int id = this.getSelectedReport();
		ReportsORM r = reportsDao.reportDetails(id);
		this.setTitle(r.getReportTitle());
		this.setDescription(r.getReportDescription());
		this.setSummary(r.getReportSummary());
		this.setCols(r.getReportColumn());
		this.setColsTitles(r.getReportColumnsTitles());
		this.setFrom(r.getReportFrom());
		//this.setWhere(this.getWhere());
		this.setWhere(r.getReportWhere());
		System.out.println("getWhere() in edit REport" + getWhere());
		this.setOrderBy(r.getReportOrderBy());
		setEditMode(true);
		this.setGroupBy(r.getReportGroupBy());
		this.setReportType(r.getReportType());
		this.setReportTypeColumns(r.getReportTypeColumns());
		this.setReportBy(r.getReportBy());
		setSummaryCounter(1);
		String colsArr[] = getCols().split(";");
		String colsArrTitles[] = getColsTitles().split(";");
		for (int i = 0; i < colsArr.length; i++) {
			// System.out.println("cols in update target list"+colsArr[i]);
			columnsTarget.add(new ColumnsBean(colsArr[i], colsArrTitles[i]));
		}
		columns.setTarget(columnsTarget);
		String sum = this.getSummary();
		if(sum.indexOf(";") != -1) {
			String s[] = sum.split(";");
			for(int j=0;j<s.length; j++){
				String t[] = s[j].split(":");
				ReportBackingBean e = new ReportBackingBean();
				e.setLabelId(summaryCounter++);
				e.setLabel(t[0].trim());
				e.setValue(t[1].trim());
				summaryList.add(e);
			}
		}	
		
		String tempGroupBy[] = getGroupBy().split(";");
		List<String> gb = new ArrayList<String>();
		for(String g: tempGroupBy){
			gb.add(g);
		}
		this.selectedGroupByColumns = gb;
		if(orderBy != null){
		String tempOrderBy[] = getOrderBy().split(";");
		List<String> ob = new ArrayList<String>();
		for(String o : tempOrderBy){
			ob.add(o);
		}
		this.selectedOrderByColumns = ob ;
		}
		String tempFilter[] = getWhere().split(";");
		List<String> fb = new ArrayList<String>();
		for(String f :tempFilter){
			fb.add(f);
		}
		this.filterList = fb;
	}

	public String updateReport() {
		//generateWhereClause();
		getFilterAsString();
		generateSummary();
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		UmUser user = null;
		try {
			user = userDao.umUserDetails(session.getUserId());
		} catch (UserNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		UmCompany company = companyDao.companyDetails(session.getCompanyId());
		// System.out.println("Where in update report"+this.getWhere());
		ReportsORM details = new ReportsORM(this.getSelectedReport(),
				this.getTitle(), this.getDescription(), this.getSummary(), this.getCols(),
				this.getColsTitles(), this.getFrom(), this.getWhere(),
				this.getOrderBy(), this.getReportType(),
				this.getReportTypeColumns(), this.getReportBy(),this.getGroupBy(),user,company);
		String value;
		try {	
			reportsDao.updateReport(details);
			this.addMessage(getProperty("message.report.updated"));
			createNewReport();
			value = "success";
		} catch (Exception e) {
			this.addError(getProperty("message.report.updation.failed"));
			value = "failure";
		}
		return value;

	}
	
	
	public String deleteReport() {
		String value;
		try {	
			reportsDao.deleteReport(this.getSelectedReport());
			this.addMessage(getProperty("message.report.deleted"));
			value = "success";
			createNewReport();
			
		} catch (Exception e) {
			this.addError(getProperty("message.report.deletion.failed"));
			value = "failure";
		}
		return value;

	}
	

	public List<ReportBackingBean> savedReports() {
		List<ReportBackingBean> repMap = new ArrayList<ReportBackingBean>();
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		UmCompany company = companyDao.companyDetails(session.getCompanyId());
		List<ReportsORM> reports = reportsDao.listReports(company);

		for (ReportsORM r : reports) {
			ReportBackingBean e = new ReportBackingBean();
			e.setId(r.getReportId());
			e.setTitle(r.getReportTitle());
			repMap.add(e);
		}
		return repMap;
	}

	public String createQuery() {
		String whereKeyWord = "";
		String whereSeparator = " AND ";
		StringBuilder sb = new StringBuilder();
		String cols = getCols();
		cols = getCols().replace(";", ", ");	
		getFilterAsString();
		String from = getFrom().replace(";", ", ");
		String where = getFilterAsString();
		if (!isEmpty(where)) {
			where = getClauseAsString(where, ";", " AND ");
			sb.append(" WHERE " + where);
		} else {
			whereKeyWord = " WHERE ";
			whereSeparator = "";
		}
		this.setWhere(where);
		String orderBy = OrderByColumns().replace(";", ",");
		this.setOrderBy(orderBy);
		//System.out.println("orderby in createQuery++" + orderBy);
		String reportBy = getReportBy();
		String groupBy = GroupByColumns().replace(";",",");
		boolean b = checkColType();
		String date = "";
		String d = "";
		Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DATE);
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        int lastDay = cal.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);
		if(b){
			switch(reportBy){
			case "date":
				date = " to_char("+getReportTypeColumns()+", 'Mon DD, YYYY') AS fdate";
				break;
			case "month":
				d = "'"+year+"-07-"+"01' AND '"+year+"-07-"+lastDay+"'";
				date = " , to_char(date_trunc('"+reportBy+"', "+getReportTypeColumns()+"), 'Mon, YYYY') AS fdate";
				break;
			case "year":
				date = " , to_char(date_trunc('"+reportBy+"', "+getReportTypeColumns()+"), 'YYYY') AS fdate";
				break;	
			case "current-month":
				date = " , to_char(date_trunc('month', "+getReportTypeColumns()+"), 'YYYY') AS fdate";
				d = "'"+year+"-"+month+"-"+"01' AND '"+year+"-"+month+"-"+lastDay+"'";
				break;	
			case "current-year":
				date = " , to_char(date_trunc('year', "+getReportTypeColumns()+"), 'YYYY') AS fdate";
				d = "'"+year+"-01-"+"01' AND '"+year+"-12-31'";
				break;		
			}
			
			if(where != "") {
				where = " AND "+getReportTypeColumns()+" BETWEEN "+d;
			} else {
				where =  getReportTypeColumns()+" BETWEEN "+d;
			}	
			
			if(orderBy.equals(getReportTypeColumns()) && orderBy != null){
				orderBy = "fdate";
			}
			 
	        System.out.println(d+"----date is "+day+"-"+month+"-"+year);
			//int index = cols.indexOf("u.user_addedon");
			//cols = cols.substring(index, (getReportTypeColumns().length()));
			//System.out.println("Index is "+index+" Cols are "+cols);
			setReportTypeColumns("fdate");
			this.cols += ",fdate";
			this.colsTitles += ",fdate";
		}
System.out.println("orderBY in create query" + orderBy);
		String query = "SELECT " + cols + date + " FROM " + from;
		if (where != null && !where.equals("")) {
			query += " WHERE " + where;
		}
		if(groupBy != null  && !groupBy.equals("") ){
			query += " GROUP By " +groupBy;
		}
		if(orderBy != null && !orderBy.equals("")) {
			query += " ORDER BY " + orderBy +" "+getOrderBySorting();
		}
		 System.out.println("Query with where clause is "+query);
		return query;
	}

	/*private void generateWhereClause() {

		String where = "";
		String from = getFrom();
		System.out.println("from in generate where clause "+from);
		String tables[] = from.split(";");
		ReportSql rs = new ReportSql();
		for (int i = 0; i < tables.length; i++) {
			for (int j = i + 1; j < tables.length; j++) {
				String tbl1[] = tables[i].split("\\.");
				String tbl2[] = tables[j].split("\\.");
				String table1 = tables[i].trim();
				String table2 = tables[j].trim();
				String table1Alias = tbl1[1].trim();
				String table2Alias = tbl2[1].trim();
				String cons = rs.WhichForeignKeyMapping(table1, table2);
				System.out.println("============Constatraint is "+cons +"-----"+table1Alias+"------"+table2Alias);
				if (cons != null) {
					String arr[] = cons.split(";");
					//System.out.println("array is "+arr);
					if (where != "") {
						where += " AND ";
					}
					for (int k = 0; k < filterList.size(); k++) {
						where += filterList.get(i).getValue() + ";";
					}
					if (!where.equals("")) {
						where = where.substring(0, (where.length() - 1));
						System.out.println("Where is "+where);
					}
					if(table1Alias.equals(arr[1]) && table2Alias.equals(arr[2])){
						where += table1Alias + "." + arr[1].trim() + " = "
							+ table2Alias + "." + arr[3].trim();
						where += table2Alias + "." + arr[1].trim() + " = "
								+ table1Alias + "." + arr[3].trim();
						
					}	
				}
			}
		}
		System.out.println("where clause is "+where);
		this.setWhere(where);
	}
	*/
	public void generateSummary(){
		String sumry = "";
		if(summaryList.size() > 0){
			for (ReportBackingBean r : summaryList) {
					sumry += r.getLabel()+": "+r.getValue()+";";
			}
			sumry = sumry.substring(0, (sumry.length()-1));
		}	
		this.setSummary(sumry);
	}

	public String generateReport(String action) throws Exception {
		System.out.println("Cols are "+this.getCols() +"  titles "+this.getColsTitles()+" title "+getTitle() + " desc "+this.getDescription());
		String cd = "inline";
		if (action.equals("download")) {
			cd = "attachment";
		}
		generateSummary();
		FacesContext ctx = FacesContext.getCurrentInstance();
		ExternalContext ectx = ctx.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ectx.getRequest();
		HttpServletResponse response = (HttpServletResponse) ectx.getResponse();
		String repTitle = this.getTitle();
		String exportType = this.getExportOption();
		ServletContext ctx1 = (ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext();
		String serverPath = ctx1.getRealPath("");
		serverPath = serverPath.substring(0, serverPath.indexOf("standalone"));
		String pathSeparator = File.separator;
		if (pathSeparator.equals("\\")) {
			pathSeparator = "\\\\";
		}
		serverPath += "standalone" + pathSeparator + "deployments";
		String contentType = "";
		switch (exportType) {
		case "pdf":
			contentType = "application/pdf";
			break;
		case "html":
			contentType = "text/html";
			break;
		case "rtf":
			contentType = "application/rtf";
			break;
		case "xls":
			contentType = "vnd.ms-excel"; // application/msexcel
			break;
		case "doc":
			contentType = "application/msword"; // application/msexcel
			break;
		default:
			contentType = "application/pdf";
		}
		String path = serverPath + pathSeparator+"reports";
		File mainDir = new File(path);
		if (!mainDir.exists()) {
			try {
				mainDir.mkdir();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		path = path + pathSeparator+"crm";
		File app = new File(path);
		if (!app.exists()) {
			try {
				app.mkdir();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		path = path + pathSeparator;
		String query = createQuery();
		String cols[] = this.getCols().split(";");
		String colsTitles[] = this.getColsTitles().split(";");
		ArrayList<String[]> colsList = new ArrayList<String[]>();
		for (int i = 0; i < cols.length; i++) {
			colsList.add(new String[] { colsTitles[i].toUpperCase(), cols[i], getColumnType(cols[i]),"30" });
		}
		DynamicJasperReport djr = new DynamicJasperReport();
		if(!getGraphType().equals("")){
			djr.setType(getGraphType());
			djr.setxAxis(getxAxis());
			djr.setyAxis(getyAxis());
			djr.setPieChartCol(getPieChartCol());
		}
	String name = djr.generateReport(getTitle(), getDescription(), getSummary(), query,
				colsList, path, exportType, this.getReportType(),
				this.getReportTypeColumns());

		File file = new File(path + name);
		byte[] fileData = new byte[(int) file.length()];
		FileInputStream fis = new FileInputStream(file);
		System.out.println("file as input stream "+fis);
		try {
			fis.read(fileData);
		} catch (IOException e) {
			e.printStackTrace();
		}
		response.reset();
		response.setContentType(contentType);
		response.setContentLength(fileData.length);
		response.setHeader("Content-Disposition", cd + ";filename="+ this.getTitle().replace(" ", "_") + "."
						+ getExportOption());
		ServletOutputStream outputStream = response.getOutputStream();

		try {
			outputStream.write(fileData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			outputStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ctx.responseComplete();
		return "reports";
	}
	

	public Map<String, String> populateDropDown() {
		Map<String, String> cols = new LinkedHashMap<String, String>();
		String colArr[] = this.getCols().split(";");
		String colTitleArr[] = this.getColsTitles().split(";");
		for (int i = 0; i < colArr.length; i++) {
			if (!colArr[i].equals("")) {
				cols.put(colTitleArr[i], colArr[i]);
			}
		}
		return cols;
	}

	public void onTransfer(TransferEvent event) {
		String cols = getCols();
		String clTitles = getColsTitles();
		String from = getFrom();
		ReportSql rs = new ReportSql();
		for(int j =0; j < event.getItems().size(); j++){
			String c = event.getItems().get(j).toString();
			if(!c.equals("") && c !=  null) {
				if(event.isAdd()) {
					String table = "";
						String temp[] = c.split("\\.");
						String colTitle = temp[1].trim();
						if (cols.equals("")) {
							cols = c;
							clTitles = colTitle;
						} else {
							cols += ";" + c;
							clTitles += ";" + colTitle;
						}
						table = getSelectedTable();
						if(from.equals("")){
							from = table;
						} else {
							if(from.indexOf(table) == -1){
								from += ";"+table;
							}
						}
					
				} else if(event.isRemove()){
					String colsArr[] = cols.split(";");
					String titlesArr[] = clTitles.split(";");
					String tempCols = "";
					String tempTitles = "";
					String table = "";
					String tempFrom = "";
					for(int i=0; i<colsArr.length; i++) {
						if(!c.equals(colsArr[i])) {
							String temp[] = colsArr[i].split("\\.");
							String tableName = rs.getTableNameByAlias(temp[0].trim());
							if(tempCols.equals("")){
								tempCols = colsArr[i];
								tempTitles = titlesArr[i];
							} else {
								tempCols += ";"+colsArr[i];
								tempTitles += ";"+titlesArr[i];
							}
						//	table =  tableName+" "+temp[0];
							table = getFrom();
							if(tempFrom.equals("")){
								tempFrom = table;
							} else {
								if(tempFrom.indexOf(table) == -1){
									tempFrom += ";"+table;
								}
							}
						}
						
						
					}
					cols = tempCols;
					clTitles = tempTitles;
					from = tempFrom;
				}	
				
				setCols(cols);
				setColsTitles(clTitles);
				setFrom(from);
			}
		}
	}
	
	public void addSummary(){
		System.out.println("add summary called");
		ReportBackingBean e = new ReportBackingBean();
		e.setLabelId(summaryCounter++);
		e.setLabel(this.getLabels());
		e.setValue(this.getValues());
		summaryList.add(e);
		this.setEditMode(true);
	}
	
	public void editSummary(){
		int id = Integer.parseInt(getParameter("temp_id")) - 1;
		summaryList.get(id).setLabel(summaryList.get(id).getLabel());
		summaryList.get(id).setValue(summaryList.get(id).getValue());
		this.setEditMode(true);
		
	}
	
	public void resetSummary(){
		setEditMode(true);
	}
	
	public List<ReportBackingBean> getReportSummary() {
		return summaryList;
	}
	
	public String getColumnType(String col){
		String temp[] = null;
		String type = "";
		if(!col.equals("")){
			try{
				temp = col.split("\\.");
			} catch (Exception e){
				System.out.println("Exception in splitting alias");
			}	
			ReportSql rs = new ReportSql();
			String tableName = rs.getTableNameByAlias(temp[0]);
			type = rs.getColumnType(tableName, temp[1]);
		}
		return type;
	}
	
	public boolean checkColType(){
		setDate(false);
		String col = getReportTypeColumns();
		String temp[] = null;
		if(!col.equals("")){
			try{
				temp = col.split("\\.");
			} catch (Exception e){
				System.out.println("Exception in splitting alias");
			}	
			ReportSql rs = new ReportSql();
			String tableName = rs.getTableNameByAlias(temp[0]);
			String type = rs.getColumnType(tableName, temp[1]);
			String types[] = new String[3];
			types[0] = "date";
			types[1] = "timestamp without time zone";
			types[2] = "timestamp with time zone";
			for(String t:types){
				if(t.equals(type)){
					setDate(true);
					return true;
				}
			}
		}
		return false;
	}
	
	public String GroupByColumns(){
		String value="";
		for (String con : getSelectedGroupByColumns()) {
			value += con + ";";
		}
		if(value.length() > 0){
			value = value.substring(0, value.length() -1 );
		}
		
		setGroupBy(value);
		System.out.println("GBY colus" + value);

		return value;	
		
	}
	
	
	public String OrderByColumns(){
		String value ="";
		for(String con :getSelectedOrderByColumns()){
			value += con + ";";
		}
		if(value.length() > 0){
			value = value.substring(0, value.length() -1 );
		}
		setOrderBy(value);
		System.out.println("OBY colus" + value);
		return value;
		
	}
	
	String getFilterAsString() {
		String where = "";
		for (int i = 0; i < filterList.size(); i++) {
			where += filterList.get(i) + ";";
		}
		if (!where.equals("")) {
			where = where.substring(0, (where.length() - 1));
			System.out.println("Where is "+where);
		}
		
		return where;
	}
	private boolean isEmpty(String str) {
		if (str == null) {
			return true;
		} else {
			str = str.trim();
			if (str.equals("")) {
				return true;
			}
		}
		return false;
	}

	
	public String getClauseAsString(String clause, String splitter,
			String separator) {
		String temp[] = clause.split(splitter);
		String str = "";
		for (int i = 0; i < temp.length; i++) {
			temp[i] = temp[i].trim();
			if(!temp[i].equals("")){
				str += temp[i];
				if (i < temp.length - 1) {
					str += separator;
				}
			}	
		}
		return str;
	}
	public String getSortedString(String clause,String separator,String splitter){
		String temp[] = clause.split(splitter);
		String str = " ";
		for(int i=0;i <temp.length;i++){
			temp[i] = temp[i].concat(separator);
			if(!temp[i].equals("")){
				str += temp[i];
				if(i < temp.length -1){
					str += separator;
				}
			}
		}
		return str;
	}
	
	public void filterColumnListener() {
		System.out.println("listener table++" + getSelectedFilterTable());
		List<String> fcl = new ArrayList<String>();
		ReportSql rs = new ReportSql();
		
		String temp[] = getSelectedFilterTable().split("\\.");
		String table = temp[1];
		String schema = temp[0];
		List<String> cols = rs.listTableColumns(schema, table);
		String tableAlias = rs.TableDBAlias(table);
		setSelectedTableAlias(rs.TableDBAlias(table));
		
		
		String value ="";
		for(String con :getSelectedColumn()){
			value += con + ";";
		}
		String tColArr[] = value.split(";");
		for (int i = 0; i<cols.size(); i++) {
			boolean match = false;
			String colVal = table + "." + cols.get(i);
			for (int j=0; j < tColArr.length; j++ ) {
				if (tColArr[j].equals(colVal)) {
					match = true;
					break;
				}
			}
			
			if(match == false){
				if(colVal.equals("um_users.user_password"))
				{
					fcl.remove("um_users.user_password");
				}
				else{
				fcl.add(colVal);
				}
				
			}
		}
		/*String tCols = getCols();
		if (tCols.equals("")) {
			for (String c : cols) {
			String colVal = table + "." + c;
				fcl.add(colVal);
				
			}
		} else {
			String tColArr[] = tCols.split(";");
			for (int i = 0; i<cols.size(); i++) {
				boolean match = false;
				String colVal = table + "." + cols.get(i);
				for (int j=0; j < tColArr.length; j++ ) {
					if (tColArr[j].equals(colVal)) {
						match = true;
						break;
					}
				}
				if(match == false){
					fcl.add(colVal);
					
				}
			}
		}*/
				
	}
	
	public List<String> ColumnList(){
		System.out.println("filtertable " + getSelectedFilterTable());
		columnsSource = new ArrayList<ColumnsBean>();
		columnsTarget = new ArrayList<ColumnsBean>();
		List<String> connList = new ArrayList<String>();
		ReportSql rs = new ReportSql();
		String temp[] =getSelectedFilterTable().split("\\.");
		String table = temp[1];
		String schema = temp[0];
		List<String> cols = rs.listTableColumns(schema, table);
		String tableAlias = rs.TableDBAlias(table);
		setSelectedTableAlias(rs.TableDBAlias(table));
		String value ="";
		for(String con :getSelectedColumn()){
			value += con + ";";
		}
		String tColArr[] = value.split(";");
		for (int i = 0; i<cols.size(); i++) {
			boolean match = false;
			String colVal = table + "." + cols.get(i);
			for (int j=0; j < tColArr.length; j++ ) {
				if (tColArr[j].equals(colVal)) {
					match = true;
					break;
				}
			}
			if(match == false){
				if(colVal.equals("um_users.user_password"))
				{
					connList.remove("um_users.user_password");
				}
				else{
					connList.add(colVal);
				}
			
				
			}
		}
		/*String tCols = getCols();
		if (tCols.equals("")) {
			for (String c : cols) {
			String colVal = table + "." + c;
			connList.add(colVal);
				
			}
		} else {
			String tColArr[] = tCols.split(";");
			for (int i = 0; i<cols.size(); i++) {
				boolean match = false;
				String colVal = table + "." + cols.get(i);
				for (int j=0; j < tColArr.length; j++ ) {
					if (tColArr[j].equals(colVal)) {
						match = true;
						break;
					}
				}
				if(match == false){
					connList.add(colVal);
					
				}
			}
		}*/
		return connList;
		
	}
	
	public HashMap<String, String> operatorList() {
		HashMap<String, String> repMap = new HashMap<String, String>();
		repMap.put("LIKE", "LIKE");
		repMap.put("ILIKE", "ILIKE");
		repMap.put("=", "=");
		repMap.put("<", "<");
		repMap.put(">", ">");
		repMap.put("<=", "<=");
		repMap.put(">=", ">=");
		repMap.put("!=", "!=");
		return repMap;
	}
	
	public void addFilter() {
		String label = "";
		String val = "";
		String value = " ";

		for (String s : getSelectedColumn()) {
			String table = getSelectedFilterTable();
			if(table.indexOf(" ") != -1){
				table = table.split(" ")[1];
				
			}
			
			label =s +" " + getSelectedOperator() + " " + getFilterValue();
			val = label;
			boolean matchfound = false;
			for (int i = 0; i < filterList.size(); i++) {
				if (filterList.get(i).equals(val)) {
					matchfound = true;
				}
			}
			if (!matchfound) {
			
				filterList.add(val);
			}
		}
	}
		
		
	
	public List<String> getCurrentFilterList() {
		return filterList;
	}
	

public void listValidator(FacesContext fc, UIComponent ui, Object value)
		throws ValidatorException {

	String action = getAction();
	if (filterList.size() == 0) {
		throw new ValidatorException(
				getMessage("Current Filter is required"));
	} else {
		if (action.equals("delete")) {
			List<String> l = (ArrayList<String>) value;

			if (l.size() == 0) {
				throw new ValidatorException(
						getMessage("Please select values from current filter to delete"));
			}
		}
	}
}



public void saveFilter() {
ReportSql rs = ReportSql.getInstance();

	try {
		String where = getFilterAsString();
		setWhere(where);
		addMessage(getProperty("message.report.filter.saved"));
	} catch (Exception e) {
		addError(getProperty("message.report.filter.saved.failed"));
	}
}

public void editFilter(){
	
	String sel = currentFilter.get(0);
	System.out.println(currentFilter.size()+"------------------------------"+sel);
	String selectedFilter = "s.case_id = 1";
	String temp[] = selectedFilter.split(" ");
	String alias = temp[0].split("\\.")[0].trim();
	String table = "";
	for(KeyValueItem i: tablesList){
		table = i.getValue();
		String tblAlias = table.substring(table.indexOf(" "), table.length());
		if(tblAlias.endsWith(alias)){
			break;
		}
	}
	setSelectedFilterTable(table);
	List<String> selCols = new ArrayList<String>();
	selCols.add(temp[0].split("\\.")[1].trim());
	setSelectedColumn(selCols);
	setSelectedOperator(temp[1].trim());
	setValue(temp[2].trim());
}
public List<String> getSelectedFilterList() {
	return currentFilter;
}

public void deleteFilter() {
	for (int i = 0; i < filterList.size(); i++) {
		for (int j = 0; j < currentFilter.size(); j++) {
			if (filterList.get(i).equals(currentFilter.get(j))) {
				filterList.remove(i);
			}
		}
	}
	currentFilter = new ArrayList<String>();


	try {
		String where = getFilterAsString();
	this.setWhere(where);
		addMessage(getProperty("message.report.filter.deleted"));
	} catch (Exception e) {
		addError(getProperty("message.report.filter.deletion.failed"));
	}
}
}
