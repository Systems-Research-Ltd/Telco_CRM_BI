package com.srpl.bi.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bitguiders.util.KeyValueItem;

public class DataService {

	private static DataService dataService = new DataService();
	private DBConnectionService dbConnectionService = DBConnectionService
			.getInstance();

	private DataService() {

	}

	public static DataService getInstance() {
		return dataService;
	}

	public List<KeyValueItem> getDatabaseConnectionListByUserId(Long userId, Long companyId) {
		int parentId = 0;
		return getList("select * from bi_database_connection where company_id="
				+ companyId, parentId);
	}

	public List<KeyValueItem> getDataSourceListByUserId(Long userId, Long companyId) {

		return getList("select * from bi_data_source where company_id=" + companyId,
				0);
	}

	public List<KeyValueItem> getSelectedTableListByDataSourceId(int dataSourceId) {
		return getTableList("SELECT dc.*,dt.* FROM bi_data_source_connection ds,bi_database_connection dc,bi_database_type dt where ds.bi_database_connection_id = dc.bi_database_connection_id and dc.bi_database_type_id = dt.bi_database_type_id and ds.bi_data_source_id=" + dataSourceId, dataSourceId);		
	}

	public List<KeyValueItem> getTableListByConnectionId(int databaseConnectionId) {
		return getTableList("SELECT dc.*,dt.* FROM bi_database_connection dc,bi_database_type dt where dc.bi_database_type_id = dt.bi_database_type_id and dc.bi_database_connection_id=" + databaseConnectionId, databaseConnectionId);		
	}

	public List<KeyValueItem> getColumnListByTableName(int databaseConnectionId, String tableName) {
		return getColumnList("SELECT dc.*,dt.* FROM bi_database_connection dc,bi_database_type dt where dc.bi_database_type_id = dt.bi_database_type_id and dc.bi_database_connection_id=" + databaseConnectionId,tableName,databaseConnectionId);
	}
	
	public ResultSet getQueryResults(String qry, int dataSourceId) throws Exception {
		String connqry = "SELECT dc.*,dt.* FROM bi_data_source_connection ds,bi_database_connection dc,bi_database_type dt where ds.bi_database_connection_id = dc.bi_database_connection_id and dc.bi_database_type_id = dt.bi_database_type_id and ds.bi_data_source_id=" + dataSourceId;
		return getQueryData(connqry, qry);
	}

	public List<KeyValueItem> getDatabaseTypeList() {

		return getList("SELECT * FROM bi_database_type", 0);
	}

	public List<KeyValueItem> getDriverListByDatabaseTypeId(int databaseTypeId) {

		return getList(
				"SELECT database_driver,database_url FROM bi_database_type where bi_database_type_id="
						+ databaseTypeId, 0);
	}

	public List<KeyValueItem> getFilterByDataSourceId(int dataSourceId) {

		return getSplitterList(
				"SELECT c.where,c.where FROM bi_data_source_connection c where bi_data_source_id="
						+ dataSourceId, 0);
	}

	public List<KeyValueItem> getFromByDataSourceId(int dataSourceId) {

		return getSplitterList(
				"SELECT c.from,c.from FROM bi_data_source_connection c where bi_data_source_id="
						+ dataSourceId, 0);
	}

	public List<KeyValueItem> getJoinByDataSourceId(int dataSourceId) {

		return getList(
				"SELECT c.join,c.join FROM bi_data_source_connection c where bi_data_source_id="
						+ dataSourceId, 0);
	}

	public List<KeyValueItem> getGroupByDataSourceId(int dataSourceId) {

		return getList(
				"SELECT group_by,group_by FROM bi_data_source_connection where bi_data_source_id="
						+ dataSourceId, 0);
	}
	
	public List<KeyValueItem> getFormulaByDataSourceId(int dataSourceId) {

		return getList(
				"SELECT formula,formula FROM bi_data_source_connection where bi_data_source_id="
						+ dataSourceId, 0);
	}

	public List<KeyValueItem> getOrderByDataSourceId(int dataSourceId) {

		return getList(
				"SELECT order_by,order_by FROM bi_data_source_connection where bi_data_source_id="
						+ dataSourceId, 0);
	}

	public List<KeyValueItem> getConnectionByDataSourceId(int dataSourceId) {

		return getList(
				"SELECT bi_database_connection_id,bi_database_connection_id FROM bi_data_source_connection where bi_data_source_id="
						+ dataSourceId, 0);
	}

	/*public KeyValueItem getSQL(int reportId) {
		ReportService rs = ReportService.getInstance();
		String sql = rs.getSql(reportId);
		return new KeyValueItem(reportId, "" + reportId, sql);
	}*/

	private List<KeyValueItem> getList(String sql, int parentId) {

		List<KeyValueItem> items = new ArrayList<KeyValueItem>();
		try {
			dbConnectionService.open();
			ResultSet rs = dbConnectionService.executeQuery(sql);
			while (rs.next()) {
				items.add(new KeyValueItem(parentId, rs.getString(1), rs
						.getString(2)));
			}
			dbConnectionService.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

		return items;
	}
	
	public List<KeyValueItem> getTableList(String sql, int parentId) {
		List<KeyValueItem> items = new ArrayList<KeyValueItem>();
		Map<String,String> connparams = getConnectionParameters(sql);
		DBConnectionDataService dbDataService = new DBConnectionDataService(connparams.get("driver"), connparams.get("user"), connparams.get("password"), connparams.get("url"), connparams.get("schema"));		
		try {
			dbDataService.open();
			ResultSet rs = null;
			int i = 0;
			if(Integer.parseInt(connparams.get("connId")) != 4 && Integer.parseInt(connparams.get("connId")) != 6){
				rs = dbDataService.executeQuery(connparams.get("tableqry").replace("%schema%", connparams.get("schema")));
				i = 1;
			}else{
				rs = dbDataService.executeMetaData();	
				i = 3;
			}
			while (rs.next()) {
				items.add(new KeyValueItem(parentId, rs.getString(i), rs.getString(i)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		dbDataService.close();
		return items;
	}
	
	public List<KeyValueItem> getColumnList(String sql, String table, int parentId) {
		List<KeyValueItem> items = new ArrayList<KeyValueItem>();
		Map<String,String> connparams = getConnectionParameters(sql);
		DBConnectionDataService dbDataService = new DBConnectionDataService(connparams.get("driver"), connparams.get("user"), connparams.get("password"), connparams.get("url"), connparams.get("schema"));		
		try {
			dbDataService.open();
			ResultSet rs = null;
			try {
				rs = dbDataService.executeQuery(connparams.get("colqry").replace("%table%", table));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			while (rs.next()) {
				items.add(new KeyValueItem(parentId, rs.getString(1), rs.getString(1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbDataService.close();
		return items;
	}
	
	public ResultSet getQueryData(String connsql, String datasql) throws Exception {
		Map<String,String> connparams = getConnectionParameters(connsql);
		DBConnectionDataService dbDataService = new DBConnectionDataService(connparams.get("driver"), connparams.get("user"), connparams.get("password"), connparams.get("url"), connparams.get("schema"));		
		dbDataService.open();
		return dbDataService.executeQuery(datasql);
	}
	
	public Map<String,String> getConnectionParameters(String sql){
		Map<String,String> conn = new HashMap<String,String>();
		try {
			dbConnectionService.open();
			ResultSet rs = dbConnectionService.executeQuery(sql);
			if(rs.next()) {
				String url = rs.getString("database_url");
				int dataSource  = Integer.parseInt(rs.getString("bi_database_type_id"));
				System.out.println("Port "+rs.getString("port"));
				long port = (rs.getString("port") != null) ? Long.parseLong(rs.getString("port")) : 0L;
				int start = (dataSource != 3) ? url.indexOf("//")+2 : url.indexOf('@')+1;
				int end = (dataSource == 3) ? url.lastIndexOf(':') : ((dataSource == 5) ? url.lastIndexOf(';') : url.lastIndexOf('/'));
				String prt = (port != 0) ? ":"+port : "";
				String uri = (dataSource != 4 && dataSource != 6) ? url.replace(url.substring(start, end), rs.getString("host")+prt) : url;
				conn.put("driver", rs.getString("database_driver"));
				conn.put("user", rs.getString("user"));
				conn.put("password", rs.getString("password"));
				conn.put("schema", rs.getString("schema"));
				conn.put("url", uri.replace("database", rs.getString("database")));
				conn.put("tableqry", rs.getString("show_table_sql"));
				conn.put("colqry", rs.getString("show_column_sql"));
				conn.put("connId", rs.getString("bi_database_type_id"));
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbConnectionService.close();
		return conn;
	}

	public List<KeyValueItem> getSplitterList(String sql, int parentId) {

		List<KeyValueItem> items = new ArrayList<KeyValueItem>();
		try {
			dbConnectionService.open();
			ResultSet rs = dbConnectionService.executeQuery(sql);
			while (rs.next()) {
				if(rs.getString(2) != null) {
					String values[] = rs.getString(2).split(";");
					for (String value : values) {
						if(!value.trim().equals("")){
							items.add(new KeyValueItem(parentId, value, value));
						}	
					}
				}	
			}
			dbConnectionService.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

		return items;
	}
	
	public String getColumnType(String tableName, String colName){
		String colType = null;
		String sql = new String("SELECT DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE table_name = '"+tableName+"' AND COLUMN_NAME = '"+colName+"';");
		try {
			dbConnectionService.open();
			ResultSet rs = dbConnectionService.executeQuery(sql);
			if (rs.next()) {
				colType = rs.getString(1);
			}
			dbConnectionService.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return colType;
	}

	public static void main(String[] arg) {
		for (KeyValueItem item : DataService.getInstance()
				.getOrderByDataSourceId(1)) {
			System.out.println(item.getParentId() + "-" + item.getKey() + " - "
					+ item.getValue());
		}
	}
}
