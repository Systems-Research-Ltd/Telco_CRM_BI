package com.srpl.bi.web.model.reportsbuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import com.bitguiders.util.KeyValueItem;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;
import com.srpl.bi.service.DBService;
import com.srpl.bi.service.DataService;
import com.srpl.bi.service.ReportService;
import com.srpl.bi.web.controller.BeanFactory;

@ManagedBean(name = "orderByBean")
@ViewScoped
public class ReportOrderByBackingBean extends JSFBeanSupport implements
		Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<KeyValueItem> selectedOrderBy = new ArrayList<KeyValueItem>();
	private String selectedTable = "";
	private List<String> selectedColumns = new ArrayList<String>();
	List<KeyValueItem> orderByList = new ArrayList<KeyValueItem>();
	List<KeyValueItem> tablesList = new ArrayList<KeyValueItem>();
	private int orderBySize = 0;
	
	public ReportOrderByBackingBean() {
	}

	public void resetBean() {
		selectedOrderBy.clear();
		orderByList.clear();
		selectedTable = "";
		selectedColumns.clear();
		orderBySize = 0;
		dbOrderByList();
	}

	public List<KeyValueItem> getSelectedOrderBy() {
		return selectedOrderBy;
	}

	public void setSelectedOrderBy(List<KeyValueItem> selectedOrderBy) {
		this.selectedOrderBy = selectedOrderBy;
	}

	public String getSelectedTable() {
		return selectedTable;
	}

	public void setSelectedTable(String selectedTable) {
		this.selectedTable = selectedTable;
	}

	

	public List<String> getSelectedColumns() {
		return selectedColumns;
	}

	public void setSelectedColumns(List<String> selectedColumns) {
		this.selectedColumns = selectedColumns;
	}

	public List<KeyValueItem> getOrderByList() {
		return orderByList;
	}

	public void setOrderByList(List<KeyValueItem> orderByList) {
		this.orderByList = orderByList;
	}

	public List<KeyValueItem> getTablesList() {
		return tablesList;
	}

	public void setTablesList(List<KeyValueItem> tablesList) {
		this.tablesList = tablesList;
	}

	public int getOrderBySize() {
		return selectedOrderBy.size();
	}

	public void setOrderBySize(int orderBySize) {
		this.orderBySize = orderBySize;
	}

	private void resetOrderByList() {
		orderByList = new ArrayList<KeyValueItem>();
	}

	
	public List<KeyValueItem> selectedTableList() {
		ReportDesignerBackingBean rdbb = BeanFactory.getInstance().getReportDesignerBean();	
		ReportService rs = ReportService.getInstance();
		tablesList = rs.getFromByReportId(rdbb.getSelectedReport()); //DataService.getInstance().getTableListByConnectionId(1);
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

	public List<KeyValueItem> columnsList() {
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

	public void listValidator(FacesContext fc, UIComponent ui, Object value)
			throws ValidatorException {

		String action = getAction();
		if (orderByList.size() == 0) {
			throw new ValidatorException(
					getMessage("Order By is required"));
		} else {
			if (action.equals("delete")) {
				@SuppressWarnings("unchecked")
				List<KeyValueItem> l = (ArrayList<KeyValueItem>) value;

				if (l.size() == 0) {
					throw new ValidatorException(
							getMessage("Please select values from List to delete"));
				}
			}
		}
	}

	public void loadOrderBy() {
		resetBean();
	}

	String getOrderByAsString() {
		String orderBy = "";
		for (int i = 0; i < orderByList.size(); i++) {
			orderBy += orderByList.get(i).getValue() + ";";
		}
		if (!orderBy.equals("")) {
			orderBy = orderBy.substring(0, (orderBy.length() - 1));
			//System.out.println("orderBy is "+orderBy);
		}
		return orderBy;
	}

	public List<KeyValueItem> dbOrderByList() {
		resetOrderByList();
		ReportDesignerBackingBean rdbb = BeanFactory.getInstance().getReportDesignerBean();		
		try{
			orderByList = ReportService.getInstance().getOrderByReportId(rdbb.getSelectedReport());
		} catch(Exception e){
			e.printStackTrace();
		}
		return orderByList;
	}

	public void addOrderBy() {
		String label = "";
		String val = "";

		for (String s : getSelectedColumns()) {
			String table = getSelectedTable();
			if(table.indexOf(" ") != -1){
				table = table.split(" ")[1];
			}
			label = table + "." + s;
			val = label;
			boolean matchfound = false;
			for (int i = 0; i < orderByList.size(); i++) {
				if (orderByList.get(i).getValue().equals(val)) {
					matchfound = true;
				}
			}
			if (!matchfound) {
				KeyValueItem i = new KeyValueItem(label, val);
				orderByList.add(i);
			}
		}
	}

	public void saveOrderBy() {
		ReportService rs = ReportService.getInstance();
		ReportDesignerBackingBean rdbb = BeanFactory.getInstance()
				.getReportDesignerBean();
		DataPaletteBackingBean dp = BeanFactory.getInstance().getDataPaletteBean();
		try {
			String orderBy = getOrderByAsString();
			rs.updateReportClauses(rdbb.getSelectedReport(), orderBy, "orderby");
			dp.generateReport();
			addMessage(getProperty("message.report.orderBy.saved"));
		} catch (Exception e) {
			addError(getProperty("message.report.orderBy.saved.failed"));
		}
	}
	
	/*public void editFilter(){
		
		KeyValueItem sel = selectedOrderBy.get(0);
		System.out.println(selectedOrderBy.size()+"------------------------------"+sel.getValue());
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
	}*/

	public void deleteOrderBy() {
		for (int i = 0; i < orderByList.size(); i++) {
			for (int j = 0; j < selectedOrderBy.size(); j++) {
				if (orderByList.get(i).getKey().equals(selectedOrderBy.get(j))) {
					orderByList.remove(i);
				}
			}
		}
		selectedOrderBy = new ArrayList<KeyValueItem>();

		ReportService rs = ReportService.getInstance();
		ReportDesignerBackingBean rdbb = BeanFactory.getInstance()
				.getReportDesignerBean();
		DataPaletteBackingBean dp = BeanFactory.getInstance().getDataPaletteBean();
		try {
			String oby = getOrderByAsString();
			rs.updateReportClauses(rdbb.getSelectedReport(), oby, "orderby");
			dp.generateReport();
			addMessage(getProperty("message.report.orderBy.deleted"));
		} catch (Exception e) {
			addError(getProperty("message.report.orderBy.deletion.failed"));
		}
	}

	public String actionListener() {
		//System.out.println("Filter Action listener called "+getAction());
		setCurrentAction(getAction(),this.getClass());
		switch (getCurrentAction()) {
		case WebConstants.ACTION_CREATE:
			loadOrderBy();
			return null;
		case WebConstants.ACTION_SAVE:
			saveOrderBy();
			return WebConstants.ACTION_SAVE;
		case WebConstants.ACTION_EDIT:
			//editFilter();
			return WebConstants.ACTION_EDIT;
		case WebConstants.ACTION_UPDATE:
			return WebConstants.ACTION_UPDATE;
		case WebConstants.ACTION_DELETE:
			return WebConstants.ACTION_DELETE;
		case WebConstants.ACTION_DELETE_CONFIRMED:
			deleteOrderBy();
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
