package com.srpl.crm.web.common;

import java.sql.Connection;
import java.sql.DriverManager;

public class Utils {
	  private static Connection conn = null;
	  private static String url = "jdbc:postgresql://68.169.55.222:5432/";
	  private static String dbName = "srpl_dev_db";
	  private static String driver = "org.postgresql.Driver";
	  private static String userName = "root"; 
	  private static String password = "root";
 	
	
	  private static Connection getConnection()throws Exception{
		  Class.forName(driver).newInstance();
		  return DriverManager.getConnection(url+dbName,userName,password);
	  }
	  
	
}
