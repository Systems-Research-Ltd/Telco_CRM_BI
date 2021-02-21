package com.srpl.bi.web.model.reportsbuilder;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.extensions.model.layout.LayoutOptions;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

import com.bitguiders.util.KeyValueItem;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.srpl.bi.service.DataService;
import com.srpl.bi.service.ReportService;
import com.srpl.bi.web.common.InnerTabs;
import com.srpl.bi.web.common.SessionDataBean;
import com.srpl.bi.web.controller.BeanFactory;

@ManagedBean(name = "reportBuilderBean")
@ViewScoped
public class DataPaletteBackingBean extends JSFBeanSupport implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String state1;
	private LayoutOptions lays1;
	private String state2;
	private LayoutOptions lays2;

	private String selectedTable = "";

	private String title;
	private String description;
	private Map<String, List<KeyValueItem>> selectedData;
	private Map<String, String> tableAlias;
	private TreeNode availableTables;
	private List<ColumnModel> dndColumns = new ArrayList<ColumnModel>();
	private List<String> grhColumns = new ArrayList<String>();
	private List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
	private CartesianChartModel categoryModel = new CartesianChartModel();
	private List<String> selectedFilter;
	private List<KeyValueItem> groupBy = new ArrayList<KeyValueItem>();
	private List<KeyValueItem> orderBy = new ArrayList<KeyValueItem>();
	private List<KeyValueItem> appFormula = new ArrayList<KeyValueItem>();
	private boolean groupByCol;
	private boolean orderByCol;
	private String[] formulas = { "AVG", "COUNT", "FIRST", "LAST", "MAX",
			"MIN", "SUM", "DATE", "MONTH", "YEAR" };
	private String selectedFormula;
	private String currentColumn;
	private List<KeyValueItem> from = new ArrayList<KeyValueItem>();
	private ArrayList<InnerTabs> tabs = new ArrayList<InnerTabs>();
	private int tabIndex = 0;
	HashMap<String, String> repMap = new HashMap<String, String>();
	List<String[]> currList = new ArrayList<String[]>();
	private String sql;
	List<KeyValueItem> JoinList = new ArrayList<KeyValueItem>();
	private Integer selectConnection;
	private Integer dataSourceId;
	private int dndColumnsSzie;
	private TreeNode fromTables;
	private TreeNode[] selectedOrderByColumns;

	

	public DataPaletteBackingBean() {
		//System.out.println("JBoss Home: "
		//		+ System.getProperty("jboss.server.home.dir"));
	}

	@PostConstruct
	protected void initialize() {
		// System.out.println("initialize called");
		lays1 = new LayoutOptions();
		LayoutOptions panes = new LayoutOptions();
		panes.addOption("slidable", false);
		panes.addOption("spacing", 6);
		panes.addOption("resizeWhileDragging", true);
		lays1.setPanesOptions(panes);
		LayoutOptions west = new LayoutOptions();
		west.addOption("size", 250);
		west.addOption("minSize", 40);
		west.addOption("maxSize", 250);
		lays1.setWestOptions(west);

		lays2 = new LayoutOptions();
		LayoutOptions panes1 = new LayoutOptions();
		panes1.addOption("slidable", false);
		panes1.addOption("spacing", 6);
		panes1.addOption("resizeWhileDragging", true);
		lays2.setPanesOptions(panes1);
		LayoutOptions north = new LayoutOptions();
		north.addOption("size", 150);
		north.addOption("minSize", 40);
		north.addOption("maxSize", 150);
		lays2.setNorthOptions(north);

		populateDataPalette();

	}

	public void populateDataPalette() {
		int dsId = BeanFactory.getInstance().getDataSourceBean()
				.getSavedDataSource();
		selectedData = new HashMap<String, List<KeyValueItem>>();
		tableAlias = new HashMap<String, String>();
		if (dsId != 0) {
			//System.out.println(dsId);
			//System.out.println("Hello");
			//System.out.println(">>>> "
			//		+ DataService.getInstance().getFromByDataSourceId(dsId)
			//				.get(0).getValue());
			List<KeyValueItem> selectedTables = DataService.getInstance()
					.getFromByDataSourceId(dsId);
			for (KeyValueItem s : selectedTables) {
				String x = s.getKey().trim();
				String tableName = (x.indexOf(" ") != -1) ? x.substring(0,
						x.indexOf(" ")) : x;
				String tempTbl = tableName;
				if (tempTbl.indexOf(".") != -1) {
					tempTbl = tempTbl.split("\\.")[1];
				}
				String tblAlias = (x.indexOf(" ") != -1) ? x.substring(
						x.indexOf(" ") + 1, x.length()) : x;
				List<KeyValueItem> tableColumns = new ArrayList<KeyValueItem>();
				tableColumns = DataService.getInstance()
						.getColumnListByTableName(
								Integer.parseInt(DataService.getInstance()
										.getConnectionByDataSourceId(dsId)
										.get(0).getValue()), tempTbl);
				selectedData.put(tableName, tableColumns);
				tableAlias.put(tableName, tblAlias);
			}
			createAvailableTables();
		}
	}

	public void applyCurrent() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		currentColumn = params.get("alias") + "." + params.get("column");
		groupByCol = findInGroup();
		//orderByCol = findInOrder();
		selectedFormula = findInFormula();
	}

	public void updateGroupings() {
		ListIterator<KeyValueItem> grpitr = groupBy.listIterator();
		ListIterator<KeyValueItem> orditr = orderBy.listIterator();
		ListIterator<KeyValueItem> frmitr = appFormula.listIterator();

		int reportId = BeanFactory.getInstance().getReportDesignerBean()
				.getSelectedReport();
		ReportService rs = ReportService.getInstance();
		
		/*while (orditr.hasNext()) {
			KeyValueItem o = orditr.next();
			if (o.getValue().equals(currentColumn))
				orditr.remove();
		}*/
		while (frmitr.hasNext()) {
			KeyValueItem f = frmitr.next();
			if (f.getValue()
					.equals(findInFormula() + "(" + currentColumn + ")"))
				frmitr.remove();
		}

		/*if (orderByCol)
			orderBy.add(new KeyValueItem(currentColumn, currentColumn));*/
		String tempCol = "";
		if (!selectedFormula.equals("")) {
			String date = "";
			tempCol = "\""+currentColumn.replace(".", "_")+"\"";
			if(selectedFormula.equals("DATE") || selectedFormula.equals("MONTH") || selectedFormula.equals("YEAR")){
				switch(selectedFormula){
				case "DATE":
					date = "to_char(date_trunc(~day~, "+currentColumn+"), ~dd-mm-YYYY~) AS "+tempCol;
					break;
				case "MONTH":
					date = "to_char(date_trunc(~Month~, "+currentColumn+"), ~mm-YYYY~) AS "+tempCol;
					break;
				case "YEAR":
					date = "to_char(date_trunc(~Year~, "+currentColumn+"), ~YYYY~) AS "+tempCol;
					break;	
				}	
				currentColumn = tempCol;
				appFormula.add(new KeyValueItem(date, date));
				groupBy.add(new KeyValueItem(currentColumn, currentColumn));
				populateOrderBy();
				orderBy.add(new KeyValueItem(currentColumn, currentColumn));
				StringBuilder ordClause = new StringBuilder();
				for (int j = 0; j < orderBy.size(); j++) {
					ordClause.append(orderBy.get(j).getValue());
					if (j < orderBy.size() - 1)
						ordClause.append(";");
				}
				rs.updateReportClauses(reportId, ordClause.toString(), "orderby");
			} else {
				
				appFormula.add(new KeyValueItem(selectedFormula + "("
						+ currentColumn + ") AS "+tempCol, selectedFormula + "("
						+ currentColumn + ") AS "+tempCol));
				currentColumn = tempCol;
			}	
		}	
		
		if (groupByCol) {
			boolean groupByColExists = false;
			while (grpitr.hasNext()) {
				KeyValueItem g = grpitr.next();
				if (g.getValue().equals(currentColumn)){
					groupByColExists = true;
				}	
			}
			if(!groupByColExists){
				groupBy.add(new KeyValueItem(currentColumn, currentColumn));
			}
		}
			

		StringBuilder grpClause = new StringBuilder();
		
		StringBuilder frmClause = new StringBuilder();

		for (int i = 0; i < groupBy.size(); i++) {
			grpClause.append(groupBy.get(i).getValue());
			if (i < groupBy.size() - 1)
				grpClause.append(";");
		}
		
		for (int k = 0; k < appFormula.size(); k++) {
			frmClause.append(appFormula.get(k).getValue());
			if (k < appFormula.size() - 1)
				frmClause.append(";");
		}

		

		rs.updateReportClauses(reportId, grpClause.toString(), "groupby");
		//rs.updateReportClauses(reportId, ordClause.toString(), "orderby");
		rs.updateReportClauses(reportId, frmClause.toString(), "formula");
		//generateReport();
	}

	public List<KeyValueItem> getAppFormula() {
		ReportDesignerBackingBean rdbb = BeanFactory.getInstance()
				.getReportDesignerBean();
		int reportId = rdbb.getSelectedReport();
		return ReportService.getInstance().getFormulaByReportId(reportId);
	}

	public void setAppFormula(List<KeyValueItem> appFormula) {
		this.appFormula = appFormula;
	}

	public boolean isGroupByCol() {
		return groupByCol;
	}

	public void setGroupByCol(boolean groupByCol) {
		this.groupByCol = groupByCol;
	}

	public boolean findInGroup() {
		for (KeyValueItem g : groupBy) {
			if (g.getValue().equals(currentColumn))
				return true;
		}
		return false;
	}

	public boolean isOrderByCol() {
		return orderByCol;
	}

	public void setOrderByCol(boolean orderByCol) {
		this.orderByCol = orderByCol;
	}

	public boolean findInOrder() {
		for (KeyValueItem g : orderBy) {
			if (g.getValue().equals(currentColumn))
				return true;
		}
		return false;
	}

	public String findInFormula() {
		String selFormula = "";
		for (KeyValueItem g : appFormula) {
			int oIndex = g.getValue().indexOf("(");
			int cIndex = g.getValue().indexOf(")");
			if (g.getValue().substring(oIndex + 1, cIndex)
					.equals(currentColumn))
				selFormula = g.getValue().substring(0, oIndex);
		}
		return selFormula;
	}

	private void createAvailableTables() {
		TreeNode[] parentNodes = new TreeNode[selectedData.size()];
		TreeNode[][] childNodes;
		int y = 0;
		availableTables = new DefaultTreeNode("Root", null);
		for (Map.Entry<String, List<KeyValueItem>> e : selectedData.entrySet()) {
			int z = 0;
			childNodes = new TreeNode[selectedData.size()][e.getValue().size()];
			parentNodes[y] = new DefaultTreeNode(e.getKey(), availableTables);
			if (y == 0)
				parentNodes[y].setExpanded(true);
			for (KeyValueItem x : e.getValue()) {
				childNodes[y][z] = new DefaultTreeNode("column",
						new ColumnModel(x.getValue().toUpperCase(), x
								.getValue().toLowerCase(), e.getKey()
								.toLowerCase(), "", ""), parentNodes[y]);
				z++;
			}
			y++;
		}
	}

	private void createCategoryModel() {

		categoryModel = new CartesianChartModel();
		String[] years = { "2008", "2009", "2010", "2011", "2012" };
		for (String column : grhColumns) {
			int count = 0;
			ChartSeries cs = new ChartSeries();
			cs.setLabel(column);
			for (int i = 0; i < 5; i++) {
				cs.set(years[count], 20);
				count++;
			}
			categoryModel.addSeries(cs);
		}
	}

	public void treeToTable() {
		ReportService rs = ReportService.getInstance();
		ReportDesignerBackingBean rdbb = BeanFactory.getInstance()
				.getReportDesignerBean();
		int reportId = 0;
		setDndColumnsSzie(dndColumns.size());
		if (dndColumns.size() == 0 && rdbb.getTempReportId() == 0) {
			rdbb.saveReport();
		}
		reportId = rdbb.getSelectedReport();
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String property = params.get("property");
		String table = params.get("table");
		

		int ds = BeanFactory.getInstance().getDataSourceBean()
				.getSavedDataSource();
		boolean tableExists = false;
		String tables = "";
		StringBuilder selectclz = new StringBuilder();

		for (int i = 0; i < dndColumns.size(); i++) {
			if (!dndColumns.get(i).getProperty().equals("serialNo")) {
				selectclz.append(dndColumns.get(i).tableAlias);
				selectclz.append(".");
				selectclz.append(dndColumns.get(i).property);
				selectclz.append(";");
			}
		}

		String tblAlias = getTableAlias(DataService.getInstance()
				.getFromByDataSourceId(ds), table);
		// String tblAlias = "";
		for (TreeNode root : availableTables.getChildren()) {
			for (TreeNode node : root.getChildren()) {
				ColumnModel model = (ColumnModel) node.getData();
				if (model.getProperty().equals(property)) {
					root.getChildren().remove(node);
					break;
				}
			}
		}

		selectclz.append(tblAlias + "." + property);

		//System.out.println(tblAlias + "String in tree to table " + selectclz);
		for (KeyValueItem t : from) {
			String temptbl = t.getKey();
			if (t.getKey().indexOf(" ") != -1) {
				temptbl = t.getKey().substring(0, t.getKey().indexOf(" "));
			}
			if (temptbl.equals(table)) {
				tableExists = true;
			}
		}
		if (!tableExists) {
			KeyValueItem t = new KeyValueItem(0);
			if (!table.equals(tblAlias)) {
				t = new KeyValueItem(0, table + " " + tblAlias, table + " "
						+ tblAlias);
			} else {
				t = new KeyValueItem(0, table, table);
			}
			from.add(t);
			
		}

		for (int i = 0; i < from.size(); i++) {
			tables += from.get(i).getKey();
			if (i < from.size() - 1) {
				tables += ";";
			}
		}

		rs.updateReportClauses(reportId, selectclz.toString(), "select");
		rs.updateReportClauses(reportId, tables, "from");
		if (from.size() > 1) {
			String join = getJoinClause(ds);
			rs.updateReportClauses(reportId, join, "join");
		}
		populateDataTable();
	}

	public String getJoinClause(int dataSourceId) {
		String join = "";
		String tempJoins = DataService.getInstance()
				.getJoinByDataSourceId(dataSourceId).get(0).getKey();
		String joins[] = tempJoins.split(";");
		List<String> aliases = new ArrayList<String>();
		for (KeyValueItem f : from) {
			String alias = f.getKey().trim();
			if (alias.indexOf(" ") != -1) {
				alias = alias.split(" ")[1];
			} else {
				if (alias.indexOf(".") != -1) {
					alias = alias.split(".")[1];
				}
			}
			aliases.add(alias);
		}
		for (String j : joins) {
			String temp = j.trim();
			if(!temp.equals("")){
				String lTbl = temp.substring(0, temp.indexOf(".")).trim();
				String rTbl = temp.substring(temp.lastIndexOf(" "),
						temp.lastIndexOf(".")).trim();
				boolean l = false;
				boolean r = false;
				for (String a : aliases) {
					if (a.equals(lTbl)) {
						l = true;
					}
					if (a.equals(rTbl)) {
						r = true;
					}
				}
				if (l == true && r == true) {
					join += temp + ";";
				}
			}	
		}
		if (join.length() > 0) {
			join = join.substring(0, join.length() - 1);
		}
		return join;
	}

	private String getTableAlias(List<KeyValueItem> tables, String table) {
		String alias = "";
		for (KeyValueItem a : tables) {
			String tbl = a.getKey().trim();
			// System.out.println(">>>>>>--------"+tbl);
			String temptbl = (tbl.indexOf(" ") != -1) ? tbl.substring(0,
					tbl.indexOf(" ")) : tbl;
			temptbl = temptbl.trim();
			if (temptbl.equals(table)) {
				// System.out.println("condition exe");
				if (tbl.indexOf(" ") != -1) {
					alias = tbl.substring(tbl.indexOf(" ") + 1, tbl.length());
				} else {
					alias = (tbl.indexOf(".") != -1) ? tbl.substring(
							alias.indexOf("."), tbl.length()) : tbl;
				}

				break;
			}
		}
		return alias;
	}

	public void populateDataTable() {
		ReportDesignerBackingBean rdbb = BeanFactory.getInstance()
				.getReportDesignerBean();
		ReportService rs = ReportService.getInstance();
		int reportId = rdbb.getSelectedReport();
		dndColumns.clear();
		rows.clear();
		if (rdbb.isSerialNo()) {
			dndColumns
					.add(new ColumnModel("Serial No.", "serialNo", "", "", ""));
		}
		if (reportId != 0) {
			try {
				rs.getReportData(reportId, rows, dndColumns);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Error in report query "+e.getMessage());
				FacesContext.getCurrentInstance().
		        addMessage(null,
		            new FacesMessage(FacesMessage.SEVERITY_ERROR,
		            		e.getMessage(), null));
				//e.printStackTrace();
			}
		}
		rdbb.graph.populateGraph(dndColumns, rows);
		// populateGraph();
	}

	public void generateReport() {
		populateDataTable();

		for (ColumnModel column : dndColumns) {
			for (TreeNode root : availableTables.getChildren()) {
				for (TreeNode node : root.getChildren()) {
					ColumnModel model = (ColumnModel) node.getData();
					if (model.getProperty().equals(column.getProperty())) {
						root.getChildren().remove(node);
						break;
					}
				}
			}
		}
	}

	public void tableToTree() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		int colIndex = Integer.parseInt(params.get("colIndex"));

		// remove from table
		ColumnModel model = this.dndColumns.remove(colIndex);
		if (grhColumns.contains(model.property))
			grhColumns.remove(model.property);
		createCategoryModel();
		// Add to tree
		String table = model.getTable();
		for (TreeNode root : availableTables.getChildren()) {
			if (root.getData().toString().equals(table)) {
				new DefaultTreeNode("column", model, root);
				break;
			}
		}
		String col = model.tableAlias + "." + model.property;
		resetClauses(col, model.table, model.tableAlias);
		ReportDesignerBackingBean rdbb = BeanFactory.getInstance().getReportDesignerBean();
		rdbb.graph.populateGraph(dndColumns, rows);
	}

	public void tableToGraph() {
		ReportService rs = ReportService.getInstance();
		ReportDesignerBackingBean rdbb = BeanFactory.getInstance()
				.getReportDesignerBean();
		int reportId = 0;
		reportId = rdbb.getSelectedReport();
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		int colIndex = Integer.parseInt(params.get("colIndex"));
		ColumnModel model = this.dndColumns.get(colIndex);
		if (!grhColumns.contains(model.property))
			grhColumns.add(model.property);
		String tables = "";
		for (int i = 0; i < grhColumns.size(); i++) {
			tables += grhColumns.get(i);
			if (i < grhColumns.size() - 1) {
				tables += ";";
			}
		}
		rs.updateReportClauses(reportId, tables, "select_graph");
		// createCategoryModel();
	}

	private void resetClauses(String column, String table, String tableAlias) {
		ReportService rs = ReportService.getInstance();
		ReportDesignerBackingBean rdbb = BeanFactory.getInstance()
				.getReportDesignerBean();
		int reportId = rdbb.getSelectedReport();
		ResultSet res = rs.getReportClausesByReportId(reportId);
		try {
			if (res.next()) {
				String select = res.getString("select");
				String from = res.getString("from");
				String join = res.getString("join");
				String where = res.getString("where");
				String groupBy = res.getString("group_by");
				String orderBy = res.getString("order_by");
				String formula = res.getString("formula");
				if (select != null) {
					select = removeColumn(select, column, ";");
					rs.updateReportClauses(reportId, select, "select");
				}
				boolean columnExists = checkOtherColumns(select, tableAlias);
				if (from != null && !columnExists) {
					String tempTbl = table;
					if (!table.equals(tableAlias)) {
						tempTbl = table + " " + tableAlias;
					}
					from = removeColumn(from, tempTbl, ";");
					rs.updateReportClauses(reportId, from, "from");
				}
				if(join != null && !columnExists){
					join = updateJoinClause(join, column);
					rs.updateReportClauses(reportId, join, "join");
				}
				if(where != null && !columnExists){
					where = updateWhereClause(where, column);
					rs.updateReportClauses(reportId, where, "where");
				}
				if (groupBy != null) {
					groupBy = removeColumn(groupBy, column, ";");
					rs.updateReportClauses(reportId, groupBy, "groupby");
				}
				if (orderBy != null) {
					orderBy = removeColumn(orderBy, column, ";");
					rs.updateReportClauses(reportId, orderBy, "orderby");
				}
				if (formula != null) {
					formula = updateFormula(formula, column, ";");
					formula = formula.replace("'", "~");
					rs.updateReportClauses(reportId, formula, "formula");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean checkOtherColumns(String arr, String alias) {
		boolean columnExists = false;
		String temp[] = arr.trim().split(";");
		for (String s : temp) {
			if (s.indexOf("(") != -1 && s.indexOf(")") != -1) {
				s = s.substring(s.indexOf("(") + 1, s.indexOf(")"));
			}
			if(s.length() > 0){
				s = s.trim();
				s = s.substring(0, s.indexOf("."));
				if(s.equals(alias)){
					columnExists = true;
				}
			}
		}
		return columnExists;
	}

	private String removeColumn(String str, String col, String splitter) {
		String[] temp = str.split(splitter);
		String tempStr = "";
		for (String s : temp) {
			if (s.lastIndexOf("(") != -1 && s.indexOf(")") != -1) {
				s = s.substring(s.lastIndexOf("(") + 1, s.indexOf(")"));
				if(s.indexOf(",") != -1){
					s = s.substring(s.indexOf(",")+1, s.length());
				}
			}
			s = s.trim();
			if (!s.equals(col) && !s.equals("")) {
				tempStr += s.trim() + splitter;
			}
		}
		return tempStr;
	}
	
	private String updateFormula(String str, String col, String splitter) {
		String[] temp = str.split(splitter);
		String tempStr = "";
		for (String s : temp) {
			String ts = s;
			if (s.lastIndexOf("(") != -1 && s.indexOf(")") != -1) {
				s = s.substring(s.lastIndexOf("(") + 1, s.indexOf(")"));
				if(s.indexOf(",") != -1){
					s = s.substring(s.indexOf(",")+1, s.length());
				}
			}
			s = s.trim();
			if (!s.equals(col) && !s.equals("")) {
				tempStr += ts.trim() + splitter;
			}
		}
		return tempStr;
	}
	
	private String updateJoinClause(String join, String col){
		String[] temp = join.split(";");
		String tempStr = "";
		for (String s : temp) {
			boolean exists = false;
			String temp1[] = s.split(" ");
			for(String t: temp1){
				if(t.trim().equals(col)){
					exists = true;
				}
			}
			if(!exists){
				tempStr += s +";";
			}
		}
		if(tempStr.length()>0){
			tempStr = tempStr.substring(0, tempStr.length() -1);
		}
		return "";
	}
	
	private String updateWhereClause(String where, String col){
		String[] temp = where.split(";");
		String tempStr = "";
		for (String s : temp) {
			boolean exists = false;
			String temp1[] = s.split(" ");
			for(String t: temp1){
				if(t.trim().equals(col)){
					exists = true;
				}
			}
			if(!exists){
				tempStr += s +";";
			}
		}
		if(tempStr.length() > 0){
			tempStr = tempStr.substring(0, tempStr.length() -1);
		}
		return tempStr;
	}
	

	public List<KeyValueItem> savedDataSourceList() {
		ArrayList<KeyValueItem> myList = new ArrayList<KeyValueItem>();
		DataService ds = DataService.getInstance();
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		Integer l = Integer.valueOf(session.getUserId().intValue());
		DataPaletteBackingBean dpbb;
		// System.out.println("user id " + l);
		List<KeyValueItem> dsList = ds.getDataSourceListByUserId(session.getUserId(), session.getCompanyId());
		/*
		 * for(int i=0;i<10;i++){
		 * 
		 * dsList.get(i); } for(KeyValueItem d:dsList){ dpbb = new
		 * DataPaletteBackingBean(); dpbb.setSavedDataSource(d.getParentId());
		 * myList.add(d); }
		 */
		// return myList;
		return dsList;
	}

	public String[] getFormulas() {
		return formulas;
	}

	public String getSelectedFormula() {
		return selectedFormula;
	}

	public void setSelectedFormula(String selectedFormula) {
		this.selectedFormula = selectedFormula;
	}

	public String getCurrentColumn() {
		return currentColumn;
	}

	public void setCurrentColumn(String currentColumn) {
		this.currentColumn = currentColumn;
	}

	public List<KeyValueItem> getGroupBy() {
		ReportDesignerBackingBean rdbb = BeanFactory.getInstance()
				.getReportDesignerBean();
		int reportId = rdbb.getSelectedReport();
		return ReportService.getInstance().getGroupByReportId(reportId);
	}

	public void setGroupBy(List<KeyValueItem> groupBy) {
		this.groupBy = groupBy;
	}

	public List<KeyValueItem> getOrderBy() {
		ReportDesignerBackingBean rdbb = BeanFactory.getInstance()
				.getReportDesignerBean();
		int reportId = rdbb.getSelectedReport();
		return ReportService.getInstance().getOrderByReportId(reportId);
	}

	public void setOrderBy(List<KeyValueItem> orderBy) {
		this.orderBy = orderBy;
	}

	public List<ColumnModel> getDndColumns() {
		return dndColumns;
	}

	public CartesianChartModel getCategoryModel() {
		return categoryModel;
	}

	public List<Map<String, String>> getRows() {
		return rows;
	}

	public void setRows(List<Map<String, String>> rows) {
		this.rows = rows;
	}

	public TreeNode getAvailableTables() {
		return availableTables;
	}

	public Map<String, List<KeyValueItem>> getSelectedData() {
		return selectedData;
	}
	
	public TreeNode getFromTables() {
		return fromTables;
	}

	public Map<String, String> getTableAlias() {
		return tableAlias;
	}

	public void setTableAlias(Map<String, String> tableAlias) {
		this.tableAlias = tableAlias;
	}

	public void setSelectedData(Map<String, List<KeyValueItem>> selectedData) {
		this.selectedData = selectedData;
	}

	public List<KeyValueItem> getFrom() {
		return from;
	}

	public void setFrom(List<KeyValueItem> from) {
		this.from = from;
	}

	/**
	 * getter/setters
	 */
	public String getState1() {
		return state1;
	}

	public void setState1(String state1) {
		this.state1 = state1;
	}

	public LayoutOptions getLays1() {
		return lays1;
	}

	public String getState2() {
		return state2;
	}

	public void setState2(String state2) {
		this.state2 = state2;
	}

	public LayoutOptions getLays2() {
		return lays2;
	}

	public String getSelectedTable() {
		return selectedTable;
	}

	public void setSelectedTable(String selectedTable) {
		// System.out.println("selectedTable called");
		this.selectedTable = selectedTable;
	}

	public List<KeyValueItem> getJoinList() {
		return JoinList;
	}

	public void setJoinList(List<KeyValueItem> joinList) {
		JoinList = joinList;
	}

	public Integer getSelectConnection() {
		return selectConnection;
	}

	public void setSelectConnection(Integer selectConnection) {
		this.selectConnection = selectConnection;
	}

	public Integer getDataSourceId() {
		return dataSourceId;
	}

	public void setDataSourceId(Integer dataSourceId) {
		this.dataSourceId = dataSourceId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getSelectedFilter() {
		ArrayList<String> myList = new ArrayList<String>();
		myList.add("n");
		return myList;
	}

	public void setSelectedFilter(List<String> selectedFilter) {
		this.selectedFilter = selectedFilter;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<InnerTabs> getTabs() {
		return tabs;
	}

	public void setTabs(ArrayList<InnerTabs> tabs) {
		this.tabs = tabs;
	}

	public int getTabIndex() {
		return tabIndex;
	}

	public void setTabIndex(int tabIndex) {
		this.tabIndex = tabIndex;
	}

	public int getDndColumnsSzie() {
		return dndColumnsSzie;
	}

	public void setDndColumnsSzie(int dndColumnsSzie) {
		this.dndColumnsSzie = dndColumnsSzie;
	}

	public List<String> getGrhColumns() {
		return grhColumns;
	}

	public void setGrhColumns(List<String> grhColumns) {
		this.grhColumns = grhColumns;
	}

	static public class ColumnModel implements Serializable {
		private static final long serialVersionUID = 1L;
		private String header;
		private String property;
		private String table;
		private String propertyType;
		private String tableAlias;

		public ColumnModel(String header, String property, String propertyType,
				String table, String tableAlias) {
			this.header = header;
			this.property = property;
			this.propertyType = propertyType;
			this.table = table;
			this.tableAlias = tableAlias;
		}

		public String getHeader() {
			return header;
		}

		public String getProperty() {
			return property;
		}

		public String getTable() {
			return table;
		}

		public String getPropertyType() {
			return propertyType;
		}

		public String getTableAlias() {
			return tableAlias;
		}

		public boolean equals(DataPaletteBackingBean.ColumnModel b) {
			if (b.getProperty().equals(this.getProperty()))
				return true;
			else
				return false;
		}

	}

	public HashMap<String, String> getRepMap() {
		return repMap;
	}

	public void setRepMap(HashMap<String, String> repMap) {
		this.repMap = repMap;
	}

	public List<String[]> getCurrList() {
		return currList;
	}

	public void setCurrList(List<String[]> currList) {
		this.currList = currList;
	}
	
	public TreeNode[] getSelectedOrderByColumns() {
		return selectedOrderByColumns;
	}

	public void setSelectedOrderByColumns(TreeNode[] selectedOrderByColumns) {
		this.selectedOrderByColumns = selectedOrderByColumns;
	}

	public String getSql() {
		ReportDesignerBackingBean rdbb = BeanFactory.getInstance()
				.getReportDesignerBean();
		ReportService rs = ReportService.getInstance();
		int reportId = rdbb.getSelectedReport();
		String sql = rs.getSql(reportId);
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public void changeDataSource() {
		System.out.println("Change Data Source");
		rows.clear();
		dndColumns.clear();
		groupBy = new ArrayList<KeyValueItem>();
		orderBy = new ArrayList<KeyValueItem>();
		appFormula = new ArrayList<KeyValueItem>();
		from.clear();
		JoinList.clear();
		getSql();
		populateDataPalette();
		createCategoryModel();
	}

	public void getColumnsAsList(String columns) {
		String temp[] = columns.split(",");
		for (String s : temp) {
			String arr[] = s.split("\\.");
			String colType = DataService.getInstance().getColumnType(arr[0],
					arr[1]);
			dndColumns.add(new ColumnModel(arr[1].toUpperCase(), arr[1],
					colType, arr[0], ""));
			for (TreeNode root : availableTables.getChildren()) {
				for (TreeNode node : root.getChildren()) {
					ColumnModel model = (ColumnModel) node.getData();
					if (model.getProperty().equals(s)) {
						root.getChildren().remove(node);
						break;
					}
				}
			}
		}
		createCategoryModel();
	}

	public void resetColumns() {
		dndColumns.clear();
		rows.clear();
		groupBy.clear();
		orderBy.clear();
		appFormula.clear();
		categoryModel = new CartesianChartModel();
		from.clear();
		JoinList.clear();
	}

	public void populateGroupBy() {
		ReportDesignerBackingBean rdbb = BeanFactory.getInstance()
				.getReportDesignerBean();
		int reportId = rdbb.getSelectedReport();
		groupBy = ReportService.getInstance().getGroupByReportId(reportId);
	}

	public void populateOrderBy() {
		ReportDesignerBackingBean rdbb = BeanFactory.getInstance()
				.getReportDesignerBean();
		int reportId = rdbb.getSelectedReport();
		orderBy = ReportService.getInstance().getGroupByReportId(reportId);
	}

	public void populateFormula() {
		ReportDesignerBackingBean rdbb = BeanFactory.getInstance()
				.getReportDesignerBean();
		int reportId = rdbb.getSelectedReport();
		appFormula = ReportService.getInstance().getGroupByReportId(reportId);
		for(KeyValueItem i:appFormula){
			String s = i.getKey().replace("~", "'");
			i.setKey(s);
			i.setValue(s);
		}
	}
}
