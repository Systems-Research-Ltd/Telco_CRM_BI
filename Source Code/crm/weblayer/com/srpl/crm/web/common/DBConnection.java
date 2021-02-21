package com.srpl.crm.web.common;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class DBConnection {
	private static String url = "";
	private static String userName = "";
	private static String password = "";
	private static String host = "";
	private static String database = "";
	
	public static Connection getConnection(){
		Connection con = null;
		InitialContext initialContext = null;
		DataSource dataSource = null;
		try {
			initialContext = new InitialContext();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			dataSource = (DataSource)initialContext.lookup("java:/SRPLDS");
		
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		try {
			con = dataSource.getConnection();
			url = con.getMetaData().getURL();
			String temp[] = url.split("//");
			String temp1[] = temp[1].split("/");
			host = temp1[0];
			database = temp1[1];
			userName = con.getMetaData().getUserName();
			System.out.println("Url "+url);
			System.out.println("Hostname "+host);
			System.out.println("database "+database);
			System.out.println("User name "+userName);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	}
	


}
