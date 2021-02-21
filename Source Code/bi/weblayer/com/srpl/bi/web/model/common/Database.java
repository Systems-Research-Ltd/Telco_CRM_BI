package com.srpl.bi.web.model.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
	Connection conn = null;
	private String url = "jdbc:postgresql://localhost:5432/";
	private String dbName = "bi_db";
	private String driver = "org.postgresql.Driver";
	private String userName = "root";
	private String password = "root";
	private String sql;

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
	
	public Connection connectDB(){
		System.out.println("connect db called");
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager
					.getConnection(url + dbName, userName, password);

		} catch(Exception e){
			System.out.println("Exception while establishing connection with database "+e);
		}
	
		return conn;
	}
	
	public ResultSet executeQuery(){
		ResultSet rs = null;
		try {
			rs = conn.createStatement().executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public void closeConnection(){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
