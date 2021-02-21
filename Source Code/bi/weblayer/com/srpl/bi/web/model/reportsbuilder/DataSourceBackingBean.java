package com.srpl.bi.web.model.reportsbuilder;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Size;

import org.primefaces.component.picklist.PickList;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

import com.bitguiders.util.KeyValueItem;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;
import com.srpl.bi.service.DBService;
import com.srpl.bi.service.DataService;
import com.srpl.bi.web.common.InnerTabs;
import com.srpl.bi.web.common.SessionDataBean;
import com.srpl.bi.web.controller.BeanFactory;

@ManagedBean(name = "dataSourceBean")
@ViewScoped
public class DataSourceBackingBean extends JSFBeanSupport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String dataSourceName;
	private Integer selectedConnection;
	private String tableAlias = null;
	private DualListModel<String> availableConnTables = new DualListModel<>();
	private List<String> conTableSource = new ArrayList<String>();
	private List<String> conTableTarget = new ArrayList<String>();
	private List<KeyValueItem> joinClause = new ArrayList<KeyValueItem>();
	private String joinType = "inner";
	private String leftTable;
	private String rightTable;
	private List<String> leftColumns = new ArrayList<String>();
	private List<String> rightColumns = new ArrayList<String>();
	private String leftColumn;
	private String rightColumn;
	private String selectedOperator;
	private ArrayList<InnerTabs> tabs = new ArrayList<InnerTabs>();
	private int tabIndex = 0;
	private int activeIndex = 0;
	private int savedDataSource;
	List<KeyValueItem> JoinList = new ArrayList<KeyValueItem>();
	private List<KeyValueItem> joinClauzes = new ArrayList<KeyValueItem>();
	private List<String> joinClauze = new ArrayList<String>(); 
	private Integer selectConnection;
	private Integer dataSourceId;
	private String currAction = "create";
	private Integer pickSize = 0;
	{
		savedDataSource = 0;//(savedDataSources().size() > 0 ) ? Integer.parseInt(savedDataSources().get(0).getKey()) : 0;
	}
	
	public DataSourceBackingBean() {
	}	

	public void tabAction(long ind) {
		activeIndex = (int)ind;
		conTableTarget = availableConnTables.getTarget();
		String trg = availableConnTables.getTarget().get(0);
		leftTable = trg;
		rightTable = trg;
	}

	public HashMap<String, String> selectedOperator() {
		HashMap<String, String> repMap = new HashMap<String, String>();
		repMap.put("Like", "Like");
		repMap.put("=", "=");
		repMap.put("<", "<");
		repMap.put(">", ">");
		repMap.put("<=", "<=");
		repMap.put(">=", ">=");
		repMap.put("!=", "!=");
		return repMap;
	}

	public List<String> selectedJoinTableList() {
		List<String> colms = availableConnTables.getTarget();
		if (colms.size() > 0) {
			if (getLeftTable().equals("")) {
				setLeftTable(colms.get(0));
			}

			if (getRightTable().equals("")) {
				setRightTable(colms.get(0));
			}
		}
		return colms;

	}

	public List<KeyValueItem> dbConnectionList() {
		ArrayList<KeyValueItem> dbConList = new ArrayList<KeyValueItem>();
		DataService ds = DataService.getInstance();
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		Integer l = Integer.valueOf(session.getUserId().intValue());
		//System.out.println("user id in dbConnectionList()" + l);
		DataSourceBackingBean dsb;
		List<KeyValueItem> connList = ds.getDatabaseConnectionListByUserId(session.getUserId(), session.getCompanyId());
		for (KeyValueItem d : connList) {
			dsb = new DataSourceBackingBean();
			dsb.setSelectedConnection(d.getParentId());
			dbConList.add(d);

		}
		return dbConList;
	}
	
	public List<KeyValueItem> savedDataSources(){
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		return DataService.getInstance().getDataSourceListByUserId(session.getUserId(), session.getCompanyId());
	}

	public List<String[]> joinTypeList() {
		List<String[]> joinList = new ArrayList<String[]>();
		joinList.add(new String[] { "inner", "inner" });
		joinList.add(new String[] { "outer", "outer" });
		//System.out.println(getJoinType() + "jointype value" + joinList.size()
		//		+ "list size");
		return joinList;

	}

	public List<KeyValueItem> rightColumnList() {
		DataService ds = DataService.getInstance();
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		Integer l = Integer.valueOf(session.getUserId().intValue());
		String str = (rightTable != null && rightTable.contains(" ")) ? rightTable.substring(0, rightTable.indexOf(" ")) : rightTable;
		List<KeyValueItem> connList = (str != null) ? ds.getColumnListByTableName(selectConnection, str) : null;
		return connList;
	}

	public List<KeyValueItem> leftColumnList() {
		DataService ds = DataService.getInstance();
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		Integer l = Integer.valueOf(session.getUserId().intValue());
		String str = (leftTable != null && leftTable.contains(" ")) ? leftTable.substring(0, leftTable.indexOf(" ")) : leftTable;
		List<KeyValueItem> connList = (str != null) ? ds.getColumnListByTableName(selectConnection, str) : null;
		return connList;
	}

	public void columnListener() throws SQLException {
		conTableSource.clear();
		conTableTarget.clear();
		List<KeyValueItem> dsList = DataService.getInstance().getTableListByConnectionId(selectConnection);
		for (KeyValueItem x : dsList) {
			List<String> connTables = new ArrayList<String>();
			String str = x.getValue();
			connTables.add(str);
			conTableSource.add(str);
		}
	}

	public void dbJoinClauseList() {
		List<KeyValueItem> tableJoinList = DataService.getInstance()
				.getJoinByDataSourceId(1);

		for (KeyValueItem d : tableJoinList) {
			joinClause.add(d);
			JoinList.add(d);
		}
	}

	public List<KeyValueItem> getJoinClauseList() {
		return JoinList;
	}

	public void addTableJoins() {
		//System.out.println("addTableJoins() called" + "right Columns size is"
		//		+ getRightColumns().size() + " left columns size is "
		//		+ getLeftColumns().size());
		String label = "";
		String val = "";
		for (String s : getRightColumns()) {

			label = getRightTable() + "." + s + " " + getSelectedOperator()
					+ "" + getLeftTable() + "" + getLeftColumns();
			val = label;
			boolean matchfound = false;
			for (int i = 0; i < JoinList.size(); i++) {
				if (JoinList.get(i).getValue().equals(val)) {
					matchfound = true;
				}
				//System.out.println(JoinList.get(i).toString()
					//	+ "joinList value in add fun");
			}
			if (!matchfound) {
				KeyValueItem i = new KeyValueItem(label, val);
				JoinList.add(i);
			}
		}
	}
	
	public void tableJoins(){
		String lstr = (leftTable.trim().indexOf(" ") != -1) ? leftTable.trim().substring(leftTable.indexOf(" ")+1) : leftTable.trim();
		String rstr = (rightTable.trim().indexOf(" ") != -1) ? rightTable.trim().substring(rightTable.indexOf(" ")+1) : rightTable.trim();
		String clause = lstr+"."+leftColumn+" "+selectedOperator+" "+rstr+"."+rightColumn;
		KeyValueItem kv = new KeyValueItem(clause, clause);
		if(!joinClauzes.contains(kv))
			joinClauzes.add(kv);
	}

	public void deleteJoins() {
		for (int i = 0; i < JoinList.size(); i++) {
			for (int j = 0; j < joinClause.size(); j++) {
				if (JoinList.get(i).getKey().equals(joinClause.get(j))) {
					JoinList.remove(i);
				}
			}
		}
	}
	
	public void deleteClauzeList(){
		for(KeyValueItem x : joinClauzes){
			if(joinClauze.contains(x.getKey()))
				joinClauzes.remove(x);
		}
	}
	
	public void handleClose(CloseEvent event) {
		FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove("dataSourceBean");  
    }

	public void createDataSource(){
		StringBuilder builder1 = new StringBuilder();
		StringBuilder builder2 = new StringBuilder();
		for(int i = 0; i < availableConnTables.getTarget().size(); i++){
			builder1.append(availableConnTables.getTarget().get(i).trim());
			if(i < availableConnTables.getTarget().size()-1) builder1.append(";");			
		}
		if(joinClauzes.size() > 0){
			for(int j = 0; j < joinClauzes.size(); j++){
				builder2.append(joinClauzes.get(j).getKey());
				if(j < joinClauzes.size()-1) builder2.append(";");			
			}
		}
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		DBService.getInstance().createDataSource(dataSourceName, selectConnection, builder1.toString(), builder2.toString(), session.getUserId(), session.getCompanyId());
	}
	
	public void updateDataSource(){
		StringBuilder builder1 = new StringBuilder();
		StringBuilder builder2 = new StringBuilder();
		for(int i = 0; i < availableConnTables.getTarget().size(); i++){
			builder1.append(availableConnTables.getTarget().get(i).trim());
			if(i < availableConnTables.getTarget().size()-1) builder1.append(";");			
		}
		if(joinClauzes.size() > 0){
			for(int j = 0; j < joinClauzes.size(); j++){
				builder2.append(joinClauzes.get(j).getKey());
				if(j < joinClauzes.size()-1) builder2.append(";");			
			}
		}
		DBService.getInstance().updateDataSource(savedDataSource, dataSourceName, selectConnection, builder1.toString(), builder2.toString());
	}
	
	public void deleteDataSource(){
		DBService.getInstance().deleteDataSource(savedDataSource);
	}
	
	public void applySourceValues(){
		List<String> dsfrom = new ArrayList<String>();
		List<String> dsjoin = new ArrayList<String>();
		currAction = "update";
		ResultSet ds = DBService.getInstance().getDataSource(savedDataSource);
		ResultSet dsc = DBService.getInstance().getDataSourceConnection(savedDataSource);
		try {
			if(ds.next()) dataSourceName = ds.getString("data_source_name");
			if(dsc.next()) {
				selectConnection = dsc.getInt("bi_database_connection_id");
				dsfrom = Arrays.asList(dsc.getString("from").split(";"));
				dsjoin = Arrays.asList(dsc.getString("join").split(";"));
			}
			List<KeyValueItem> dsList = DataService.getInstance().getTableListByConnectionId(selectConnection);
			for (KeyValueItem x : dsList) {
				Boolean added = false;
				String str = x.getValue();
				for(String y : dsfrom){
					String tmpstr = (y.indexOf(" ") != -1) ? y.substring(0, y.indexOf(" ")) : y;
					if(tmpstr.equals(str)) {
						conTableTarget.add(y);
						added = true;
					}
				}
				if(!added) conTableSource.add(str);
			}
			for(String z : dsjoin)
				joinClauzes.add(new KeyValueItem(z,z));
			pickSize = conTableTarget.size();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updatePickSize(TransferEvent event){
		int size = event.getItems().size();
		if(event.isAdd()){
			for(Object x : event.getItems()){
				String str = (String)x;
				availableConnTables.getSource().remove(str);
				str = (size == 1 && tableAlias != null) ? str+" "+tableAlias : str;
				availableConnTables.getTarget().add(str);
			}	
			conTableSource = availableConnTables.getSource();
			conTableTarget = availableConnTables.getTarget();
			pickSize += size;
		}	
		if(event.isRemove()){
			for(Object x : event.getItems()){
				String str = (String)x;
				availableConnTables.getTarget().remove(str);
				str = (str.contains(" ")) ? str.substring(0, str.indexOf(" ")) : str;
				availableConnTables.getSource().add(str);
			}
			conTableSource = availableConnTables.getSource();
			conTableTarget = availableConnTables.getTarget();
			pickSize -= size;
		}
		tableAlias = "";
	}	

	public List<KeyValueItem> getJoinClauzes() {
		return joinClauzes;
	}

	public void setJoinClauzes(List<KeyValueItem> joinClauzes) {
		this.joinClauzes = joinClauzes;
	}

	public List<String> getJoinClauze() {
		return joinClauze;
	}

	public void setJoinClauze(List<String> joinClauze) {
		this.joinClauze = joinClauze;
	}

	public String getCurrAction() {
		return currAction;
	}

	public void setCurrAction(String currAction) {
		this.currAction = currAction;
	}	

	public Integer getPickSize() {
		return pickSize;
	}

	public void setPickSize(Integer pickSize) {
		this.pickSize = pickSize;
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

	public String getDataSourceName() {
		return dataSourceName;
	}

	public void setDataSourceName(String dataSourceName) {
		this.dataSourceName = dataSourceName;
	}

	public Integer getSelectedConnection() {
		return selectedConnection;
	}

	public void setSelectedConnection(Integer selectedConnection) {
		this.selectedConnection = selectedConnection;
	}

	public String getTableAlias() {
		return tableAlias;
	}

	public void setTableAlias(String tableAlias) {
		this.tableAlias = (tableAlias != "") ? tableAlias : null;
	}

	public DualListModel<String> getAvailableConnTables() {
		availableConnTables = new DualListModel<String>(conTableSource,	conTableTarget);
		return availableConnTables;
	}

	public void setAvailableConnTables(DualListModel<String> availableConnTables) {
		this.availableConnTables = availableConnTables;
	}

	public List<String> getConTableSource() {
		return conTableSource;
	}

	public void setConTableSource(List<String> conTableSource) {
		this.conTableSource = conTableSource;
	}

	public List<String> getConTableTarget() {
		return conTableTarget;
	}

	public void setConTableTarget(List<String> conTableTarget) {
		this.conTableTarget = conTableTarget;
	}

	public List<KeyValueItem> getJoinClause() {
		return joinClause;
	}

	public void setJoinClause(List<KeyValueItem> joinClause) {
		this.joinClause = joinClause;
	}

	public String getJoinType() {
		return joinType;
	}

	public void setJoinType(String joinType) {
		this.joinType = joinType;
	}

	public String getLeftTable() {
		return leftTable;
	}

	public void setLeftTable(String leftTable) {
		this.leftTable = leftTable;
	}

	public String getRightTable() {
		return rightTable;
	}

	public void setRightTable(String rightTable) {
		this.rightTable = rightTable;
	}

	public List<String> getLeftColumns() {
		return leftColumns;
	}

	public void setLeftColumns(List<String> leftColumns) {
		this.leftColumns = leftColumns;
	}

	public List<String> getRightColumns() {
		return rightColumns;
	}

	public void setRightColumns(List<String> rightColumns) {
		this.rightColumns = rightColumns;
	}	

	public String getLeftColumn() {
		return leftColumn;
	}

	public void setLeftColumn(String leftColumn) {
		this.leftColumn = leftColumn;
	}

	public String getRightColumn() {
		return rightColumn;
	}

	public void setRightColumn(String rightColumn) {
		this.rightColumn = rightColumn;
	}

	public String getSelectedOperator() {
		return selectedOperator;
	}

	public void setSelectedOperator(String selectedOperator) {
		this.selectedOperator = selectedOperator;
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

	public int getActiveIndex() {
		return activeIndex;
	}

	public void setActiveIndex(int activeIndex) {
		this.activeIndex = activeIndex;
	}

	public int getSavedDataSource() {
		return savedDataSource;
	}

	public void setSavedDataSource(int savedDataSource) {
		this.savedDataSource = savedDataSource;
	}

	public List<KeyValueItem> getJoinList() {
		return JoinList;
	}

	public void setJoinList(List<KeyValueItem> joinList) {
		JoinList = joinList;
	}
}
