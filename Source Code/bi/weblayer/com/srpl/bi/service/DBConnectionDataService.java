package com.srpl.bi.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.DatabaseMetaData;

public class DBConnectionDataService {
	private String driver;
	private String user;
	private String password;
	private String url;
	private String schema;

	private Connection connection;
	private Statement statement;
	private ResultSet resultset;

	public DBConnectionDataService() {

	}
	
	public DBConnectionDataService(String drv, String usr, String pswd, String ul, String shma) {
		driver = drv;
		user = usr;
		password = pswd;
		url = ul;
		schema = shma;
	}
	
	public Connection getConnection(){
		return connection;
	}

	public void open() {
		try {
			Class.forName(driver);
			System.out.println("Connection " + url);
			connection = DriverManager.getConnection(url, user, password);
			if(connection != null) System.out.println("Success");
			statement = connection.createStatement();
			switch(driver){
				case "org.postgresql.Driver": statement.executeQuery("SET search_path TO "+schema); break;
			}
		} catch (Exception e) {
			System.out.println("Failure");
			System.out.println(e.getMessage());
		}

	}

	public ResultSet executeQuery(String query) throws Exception {
		statement = connection.createStatement();
		resultset = statement.executeQuery(query);
		return resultset;
	}

	public ResultSet executeMetaData() throws Exception {
		resultset = connection.getMetaData().getTables(null, null, "%", null);
		System.out.println("Meta ");
		return resultset;
	}

	public void close() {
		try {
			connection.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
}
