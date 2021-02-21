package com.srpl.bi.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnectionService {
	// String driver =
	// "JDBC:ODBC:Driver={MicroSoft Access Driver (*.mdb)};DBQ= resources/academy_db.mdb";

	private String driver = "org.postgresql.Driver";// "sun.jdbc.odbc.JdbcOdbcDriver"
	private String user = "root";
	private String password = "root";
	private String database = "tbaas_demo_db";
	private String url = "jdbc:postgresql://localhost/";

	Connection connection;
	Statement statement;
	ResultSet resultset;
	PreparedStatement preparedStatement;

	private static DBConnectionService dbConnectionService = new DBConnectionService();

	private DBConnectionService() {

	}

	public static DBConnectionService getInstance() {
		return dbConnectionService;
	}
	
	private void connectDB(){
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url + database, user, password);
			statement = connection.createStatement();
			statement.executeQuery("SET search_path TO bi");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Connection getConnection(){
		connectDB();
		return connection;
	}

	public void open() {
		try {
			connectDB();
			statement = connection.createStatement();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public ResultSet executeQuery(String query) {
		try {
			resultset = statement.executeQuery(query);
		} catch (SQLException sqlex) {
			System.out.println(sqlex.getMessage());
		}
		return resultset;
	}

	public ResultSet insertQuery(String query) {
		try {
			preparedStatement = connection.prepareStatement(query,
					PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.executeUpdate();
			resultset = preparedStatement.getGeneratedKeys();
		} catch (SQLException sqlex) {
			System.out.println(sqlex.getMessage());
		}
		return resultset;

	}

	public void executeUpdate(String query) {
		try {
			statement.executeUpdate(query);
		} catch (SQLException sqlex) {
			System.out.println(sqlex.getMessage());
		}
	}

	public void close() {
		try {
			connection.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}

	public static void main(String arg[]) {
		DBConnectionService connection = DBConnectionService.getInstance();
		try {
			connection.open();
			ResultSet rs = connection
					.executeQuery("SELECT * FROM bi_data_source");
			while (rs.next()) {
				System.out.println(rs.getString(1));
			}
			// System.out.println("current report id "+connection.insert());
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
}