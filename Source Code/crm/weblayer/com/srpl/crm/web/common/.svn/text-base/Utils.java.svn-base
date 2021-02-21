package com.srpl.crm.web.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.srpl.crm.ejb.entity.CsContactORM;
import com.srpl.crm.web.model.BillBackingBean;

public class Utils {

	private static Connection conn = null;
	private static String url = "jdbc:postgresql://localhost:5432/";
	private static String dbName = "customer1_db";
	private static String driver = "org.postgresql.Driver";
	private static String userName = "root";
	private static String password = "root";

	private static Connection getConnection() throws Exception {
		Class.forName(driver).newInstance();
		return DriverManager.getConnection(url + dbName, userName, password);
	}

	public static List<BillBackingBean> getCustomerBillsFromCustomerDb(
			CsContactORM customer) {
		System.out
				.println("Utils -> getCustomerBillsFromCustomerDb() called..");
		List<BillBackingBean> customerBills = new ArrayList<BillBackingBean>();
		try {
			conn = getConnection();
			String query = "select cust_contact_id from customer_contacts where cust_contact_cnic_no = '"
					+ customer.getContactCnic() + "'";
			ResultSet rs = conn.createStatement().executeQuery(query);
			int customerId = -1;
			while (rs.next()) {
				customerId = Integer.parseInt(rs.getString(1));
				break;
			}
			System.out.println("customerId = " + customerId);
			query = "select * from customer_bill where bill_customer = "
					+ customerId;
			rs = conn.createStatement().executeQuery(query);
			while (rs.next()) {
				BillBackingBean billBackingBean = new BillBackingBean();
				billBackingBean.setBillId(rs.getString(1));
				billBackingBean.setBillCustomer(rs.getString(2));
				billBackingBean.setBillAmount(rs.getString(3));
				billBackingBean.setBillPaidAmount(rs.getString(4));
				billBackingBean.setBillArrears(rs.getString(5));
				billBackingBean.setBillDueDate(rs.getString(6));
				customerBills.add(billBackingBean);
			}
			rs.close();
			conn.close();
		} catch (Exception e) {
			System.out
					.println("Exception occured Utils -> getCustomerBillsFromCustomerDb()");
			e.printStackTrace();
			e.getMessage();
		}
		System.out.println("customerBills size = " + customerBills.size());
		return customerBills;
	}

}
