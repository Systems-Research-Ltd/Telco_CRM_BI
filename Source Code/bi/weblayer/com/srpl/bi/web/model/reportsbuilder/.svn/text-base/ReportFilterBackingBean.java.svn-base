package com.srpl.bi.web.model.reportsbuilder;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;
import com.bitguiders.util.KeyValueItem;
import com.srpl.bi.service.DBService;
import com.srpl.bi.service.DataService;
import com.srpl.bi.service.ReportService;
import com.srpl.bi.web.controller.BeanFactory;

@ManagedBean(name = "filterBean")
@ViewScoped
public class ReportFilterBackingBean extends JSFBeanSupport implements
		Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<KeyValueItem> currentFilter = new ArrayList<KeyValueItem>();
	private String selectedTable = "";
	private String value;
	private List<String> selectedColumns = new ArrayList<String>();
	private String selectedOperator;
	List<KeyValueItem> filterList = new ArrayList<KeyValueItem>();
	private int currentFilterSize = 0;
	List<KeyValueItem> tablesList = new ArrayList<KeyValueItem>();

	public ReportFilterBackingBean() {
	}

	public String getSelectedTable() {
		return selectedTable;
	}

	public void setSelectedTable(String selectedTable) {
		this.selectedTable = selectedTable;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<String> getSelectedColumns() {
		return selectedColumns;
	}

	public void setSelectedColumns(List<String> selectedColumns) {
		this.selectedColumns = selectedColumns;
	}

	public String getSelectedOperator() {
		return selectedOperator;
	}

	public void setSelectedOperator(String selectedOperator) {
		this.selectedOperator = selectedOperator;
	}

	public void setCurrentFilterSize(int currentFilterSize) {
		this.currentFilterSize = currentFilterSize;
	}

	public int getCurrentFilterSize() {
		return currentFilter.size();
	}

	public List<KeyValueItem> getCurrentFilter() {
		return currentFilter;
	}

	public List<KeyValueItem> getCurrentFilterList() {
		return filterList;
	}

	public List<KeyValueItem> getSelectedFilterList() {
		return currentFilter;
	}

	public void resetBean() {
		currentFilter.clear();
		filterList.clear();
		selectedTable = "um_users";
		value = "";
		selectedColumns.clear();
		selectedOperator = "";
		currentFilterSize = 0;
		dbFilterList();
	}

	private void resetFilterList() {
		filterList = new ArrayList<KeyValueItem>();
	}

	public void setCurrentFilter(List<KeyValueItem> currentFilter) {
		this.currentFilter = currentFilter;
	}

	public List<KeyValueItem> selectedTableList() {
		DataSourceBackingBean ds = BeanFactory.getInstance().getDataSourceBean();
		tablesList = DataService.getInstance().getFromByDataSourceId(ds.getSavedDataSource()); //DataService.getInstance().getTableListByConnectionId(1);
		if(tablesList != null && tablesList.size() > 0){
			for(int i = 0; i < tablesList.size(); i++){
				if(tablesList.get(i).getKey().indexOf(" ") == -1){
					String value = tablesList.get(i).getKey();
					value = value+" "+value;
					tablesList.get(i).setKey(value);
					tablesList.get(i).setValue(value);
				}
			}
			
			setSelectedTable(tablesList.get(0).getValue());		
		} else {
			tablesList = new ArrayList<KeyValueItem>();
		}	
		return tablesList;
	}

	public List<KeyValueItem> ColumnList() {
		List<KeyValueItem> connList = new ArrayList<KeyValueItem>();
		DataService ds = DataService.getInstance();
		DataSourceBackingBean dsbb = BeanFactory.getInstance().getDataSourceBean();
		int dbConnectionId = DBService.getInstance().getConnectionIdByDataSource(dsbb.getSavedDataSource());
		//System.out.println(dbConnectionId+"-------------??????????"+getSelectedTable());
		if(getSelectedTable() != null && !getSelectedTable().equals("")){
			String temp = getSelectedTable().split(" ")[0];
			if(temp.indexOf(".") != -1){
				temp = temp.split("\\.")[1];
			}
			 connList = ds.getColumnListByTableName(dbConnectionId, temp);
				
		}
		return connList;
	}

	public HashMap<String, String> operatorsList() {
		HashMap<String, String> repMap = new HashMap<String, String>();
		repMap.put("LIKE", "LIKE");
		repMap.put("ILIKE", "ILIKE");
		repMap.put("=", "=");
		repMap.put("<", "<");
		repMap.put(">", ">");
		repMap.put("<=", "<=");
		repMap.put(">=", ">=");
		repMap.put("!=", "!=");
		repMap.put("BETWEEN", "BETWEEN");
		return repMap;
	}

	public void listValidator(FacesContext fc, UIComponent ui, Object value)
			throws ValidatorException {

		String action = getAction();
		if (filterList.size() == 0) {
			throw new ValidatorException(
					getMessage("Current Filter is required"));
		} else {
			if (action.equals("delete")) {
				List<KeyValueItem> l = (ArrayList<KeyValueItem>) value;

				if (l.size() == 0) {
					throw new ValidatorException(
							getMessage("Please select values from current filter to delete"));
				}
			}
		}
	}

	public void loadFilter() {
		resetBean();
	}

	String getFilterAsString() {
		String where = "";
		for (int i = 0; i < filterList.size(); i++) {
			where += filterList.get(i).getValue() + ";";
		}
		if (!where.equals("")) {
			where = where.substring(0, (where.length() - 1));
			System.out.println("Where is "+where);
		}
		return where;
	}

	public List<KeyValueItem> dbFilterList() {
		resetFilterList();
		ReportDesignerBackingBean rdbb = BeanFactory.getInstance().getReportDesignerBean();
		List<KeyValueItem> currFilterList =  new ArrayList<KeyValueItem>();
				
		try{
			currFilterList = ReportService.getInstance().getFilterByReportId(rdbb.getSelectedReport());
			for (KeyValueItem d : currFilterList) {
				if (!d.getValue().trim().equals(""))
					d.setValue(d.getValue().replace("~", "'"));
					d.setKey(d.getKey().replace("~", "'"));
					filterList.add(d);
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return currFilterList;
	}

	public void addFilter() {
		String label = "";
		String val = "";

		for (String s : getSelectedColumns()) {
			String table = getSelectedTable();
			if(table.indexOf(" ") != -1){
				table = table.split(" ")[1];
			}
			label = table + "." + s + " " + getSelectedOperator()
					+ " " + getValue();
			val = label;
			boolean matchfound = false;
			for (int i = 0; i < filterList.size(); i++) {
				if (filterList.get(i).getValue().equals(val)) {
					matchfound = true;
				}
			}
			if (!matchfound) {
				KeyValueItem i = new KeyValueItem(label, val);
				filterList.add(i);
			}
		}
	}

	public void saveFilter() {
		ReportService rs = ReportService.getInstance();
		ReportDesignerBackingBean rdbb = BeanFactory.getInstance()
				.getReportDesignerBean();
		DataPaletteBackingBean dp = BeanFactory.getInstance().getDataPaletteBean();
		try {
			String where = getFilterAsString();
			where = where.replace("'", "~");
			rs.saveFilter(where, rdbb.getSelectedReport());
			dp.generateReport();
			addMessage(getProperty("message.report.filter.saved"));
		} catch (Exception e) {
			addError(getProperty("message.report.filter.saved.failed"));
		}
	}
	
	public void editFilter(){
		
		KeyValueItem sel = currentFilter.get(0);
		System.out.println(currentFilter.size()+"------------------------------"+sel.getValue());
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
		setSelectedTable(table);
		List<String> selCols = new ArrayList<String>();
		selCols.add(temp[0].split("\\.")[1].trim());
		setSelectedColumns(selCols);
		setSelectedOperator(temp[1].trim());
		setValue(temp[2].trim());
	}

	public void deleteFilter() {
		for (int i = 0; i < filterList.size(); i++) {
			for (int j = 0; j < currentFilter.size(); j++) {
				if (filterList.get(i).getKey().equals(currentFilter.get(j))) {
					filterList.remove(i);
				}
			}
		}
		currentFilter = new ArrayList<KeyValueItem>();

		ReportService rs = ReportService.getInstance();
		ReportDesignerBackingBean rdbb = BeanFactory.getInstance()
				.getReportDesignerBean();
		DataPaletteBackingBean dp = BeanFactory.getInstance().getDataPaletteBean();
		try {
			String where = getFilterAsString();
			rs.saveFilter(where, rdbb.getSelectedReport());
			dp.generateReport();
			addMessage(getProperty("message.report.filter.deleted"));
		} catch (Exception e) {
			addError(getProperty("message.report.filter.deletion.failed"));
		}
	}

	public String actionListener() {
		System.out.println("Filter Action listener called "+getAction());
		setCurrentAction(getAction(),this.getClass());
		switch (getCurrentAction()) {
		case WebConstants.ACTION_CREATE:
			loadFilter();
			return null;
		case WebConstants.ACTION_SAVE:
			saveFilter();
			return WebConstants.ACTION_SAVE;
		case WebConstants.ACTION_EDIT:
			editFilter();
			return WebConstants.ACTION_EDIT;
		case WebConstants.ACTION_UPDATE:
			return WebConstants.ACTION_UPDATE;
		case WebConstants.ACTION_DELETE:
			return WebConstants.ACTION_DELETE;
		case WebConstants.ACTION_DELETE_CONFIRMED:
			deleteFilter();
			return WebConstants.ACTION_DELETE_CONFIRMED;
		case WebConstants.ACTION_CANCEL:
			resetBean();
			return null;
		default:
			resetBean();
			return null;
		}
	}

}
