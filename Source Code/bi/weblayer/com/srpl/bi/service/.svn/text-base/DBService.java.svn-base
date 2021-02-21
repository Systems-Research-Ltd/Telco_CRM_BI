package com.srpl.bi.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBService {
	private static DBService dbService = new DBService();
	private DBConnectionService dbConnectionService = DBConnectionService.getInstance();

	private DBService() {
	}

	public static DBService getInstance() {
		return dbService;
	}
	
	public boolean testConnection(String url, String drv, String uname, String pass){
		Connection conn = null;
		try {
			Class.forName(drv);
			conn = DriverManager.getConnection(url,uname,pass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		} 
		return (conn == null) ? false : true;
	}
	
	public ResultSet getConnection(int connId){
		  dbConnectionService.open();
		  return dbConnectionService.executeQuery("SELECT * FROM bi_database_connection where bi_database_connection_id = "+connId);
	}
	
	public int getConnectionIdByDataSource(int dataSource){
		int connectionId = 0; 
	
		dbConnectionService.open();
		 ResultSet rs =  dbConnectionService.executeQuery("SELECT * FROM bi_data_source_connection where bi_data_source_id = "+dataSource);
		 try {
				if(rs.next()) {
					connectionId = rs.getInt("bi_database_connection_id");
				}
		 } catch(SQLException e){
			 e.printStackTrace();
		 }	
		return connectionId;
	}
	
	public ResultSet getDataSource(int srcId){
		  dbConnectionService.open();
		  return dbConnectionService.executeQuery("SELECT * FROM bi_data_source where bi_data_source_id = "+srcId);		  
	}
	
	public ResultSet getDataSourceConnection(int srcId){
		  dbConnectionService.open();
		  return dbConnectionService.executeQuery("SELECT * FROM bi_data_source_connection where bi_data_source_id = "+srcId);		  
	}
	
	public void createConnection(String conn, String db, String host, Long port, String uname, String pass, String schema, Integer ds, Long userId, Long companyId){		
		String connVals = "'"+conn+"', '"+db+"', '"+host+"', "+port+", '"+uname+"', '"+pass+"', '"+((schema != "") ? schema : "public")+"', "+userId+", "+companyId+", "+ds;
		System.out.println(">>>>>>>>>>>>>>>>>>>>"+connVals);
		String qry = "INSERT INTO bi_database_connection (connection_name, database, host, port, \"user\", password, schema, user_id, company_id, bi_database_type_id) VALUES ("+connVals+")";
		dbConnectionService.open();
		dbConnectionService.executeQuery(qry);
		dbConnectionService.close();
	}
	
	public void updateConnection(Integer connId, String conn, String db, String host, Long port, String uname, String pass, String schema, Integer ds){
		String qry = "UPDATE bi_database_connection SET connection_name = '"+conn+"', database = '"+db+"', host = '"+host+"', port = "+port+", \"user\" = '"+uname+"', password = '"+pass+"', schema = '"+((schema != "") ? schema : "public")+"', user_id = 1, bi_database_type_id = "+ds+" WHERE bi_database_connection_id = "+connId;
		dbConnectionService.open();
		dbConnectionService.executeUpdate(qry);
		dbConnectionService.close();
	}
	
	public void deleteConnection(int connId){
		dbConnectionService.open();
		dbConnectionService.executeQuery("DELETE FROM bi_database_connection where bi_database_connection_id = "+connId);
		dbConnectionService.close();
	}
	
	public void createDataSource(String title, Integer dc, String frm, String join, Long userId, Long companyId){
		String qry1 = "INSERT INTO bi_data_source (data_source_name, user_id, company_id) VALUES ('"+title+"', "+userId+", "+companyId+")";
		dbConnectionService.open();
		ResultSet rs = dbConnectionService.insertQuery(qry1);
		try {
			if(rs.next()){
				int generatedKey = rs.getInt(1);
				String qry2 = "INSERT INTO bi_data_source_connection (bi_data_source_id, bi_database_connection_id, \"from\", \"join\") VALUES ("+generatedKey+", "+dc+", '"+frm+"', '"+join+"')";
				dbConnectionService.executeQuery(qry2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbConnectionService.close();
	}
	
	public void updateDataSource(Integer dsId, String title, Integer dc, String frm, String join){
		String qry1 = "UPDATE bi_data_source SET data_source_name = '"+title+"' WHERE bi_data_source_id = "+dsId;
		dbConnectionService.open();
		dbConnectionService.executeUpdate(qry1);
		String qry2 = "UPDATE bi_data_source_connection SET bi_database_connection_id = "+dc+", \"from\" = '"+frm+"', \"join\" = '"+join+"' WHERE bi_data_source_id = "+dsId;
		dbConnectionService.executeUpdate(qry2);			
		dbConnectionService.close();
	}
	
	public void updateDataSourceClauses(Integer dsId, String sel, String clz){
		String clause = "";
		switch(clz){
			case "select" : clause = "\"select\" = '"+sel+"'"; break;
			case "from" : clause = "\"from\" = '"+sel+"'"; break;
			case "groupby" : clause = "group_by = '"+sel+"'"; break;
			case "orderby" : clause = "order_by = '"+sel+"'"; break;
			case "formula" : clause = "formula = '"+sel+"'"; break;
			default : clause = "";	
		}
		if(clause != ""){
			String upd = "UPDATE bi_data_source_connection SET "+clause+" WHERE bi_data_source_id = "+dsId;
			dbConnectionService.open();
			dbConnectionService.executeUpdate(upd);			
			dbConnectionService.close();
		}	
	}
	
	public void deleteDataSource(int dsId){
		dbConnectionService.open();
		dbConnectionService.executeQuery("DELETE FROM bi_data_source_connection where bi_data_source_id = "+dsId);
		dbConnectionService.executeQuery("DELETE FROM bi_data_source where bi_data_source_id = "+dsId);
		dbConnectionService.close();
	}

	public static void main(String[] arg) {
		DBService ds = DBService.getInstance();
		ds.updateDataSourceClauses(3, "test", "from");
		ResultSet rs = ds.getDataSourceConnection(1);
		try {
			if(rs.next()) {
				String select = rs.getString("select"); 
				String from = rs.getString("from");
				String where = rs.getString("where");
				String groupBy = rs.getString("group_by"); 
				String orderBy = rs.getString("order_by"); 
				String formula = rs.getString("formula");
			//	System.out.println(select);
			}
		}catch(Exception e){
			e.printStackTrace();
		}	
		String str = "um_companies.company_email,  count(*),SUM(company_phone),FIRST(company_email) ";
		String[] temp = str.split(",");
		String tempStr = "";
		for(String s : temp){
			if(s.indexOf("company_email") == -1){
				tempStr += s + ", ";
			}
		}
		tempStr = tempStr.substring(0, (tempStr.length() - 2));
		System.out.println(tempStr);
	}

}
