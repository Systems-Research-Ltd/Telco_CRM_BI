package com.srpl.crm.web.model.report;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import com.bitguiders.util.KeyValueItem;

import com.srpl.crm.web.common.DBConnection;

// import java.org.apache.commons.collections.functors.InstantiateFactory;

public class ReportSql {

	private static ReportSql reportSql = new ReportSql();
	public static ReportSql getInstance() {
		return reportSql;
	}
	String tableArray[][] = new String[55][3];
	List<HashMap<String, String>> nestedHashMap = new LinkedList<HashMap<String, String>>();
	// HashMap<String, String> tableMap = new HashMap<String, String>();
	HashMap<String, String> map0 = new HashMap<String, String>();
	HashMap<String, String> map1 = new HashMap<String, String>();
	HashMap<String, String> map2 = new HashMap<String, String>();
	HashMap<String, String> map3 = new HashMap<String, String>();
	HashMap<String, String> map4 = new HashMap<String, String>();
	HashMap<String, String> map5 = new HashMap<String, String>();
	HashMap<String, String> map6 = new HashMap<String, String>();
	HashMap<String, String> map7 = new HashMap<String, String>();
	HashMap<String, String> map8 = new HashMap<String, String>();
	HashMap<String, String> map9 = new HashMap<String, String>();
	HashMap<String, String> map10 = new HashMap<String, String>();
	HashMap<String, String> map11 = new HashMap<String, String>();
	HashMap<String, String> map12 = new HashMap<String, String>();
	HashMap<String, String> map13 = new HashMap<String, String>();
	HashMap<String, String> map14 = new HashMap<String, String>();
	HashMap<String, String> map15 = new HashMap<String, String>();
	HashMap<String, String> map16 = new HashMap<String, String>();
	HashMap<String, String> map17 = new HashMap<String, String>();
	HashMap<String, String> map18 = new HashMap<String, String>();
	HashMap<String, String> map19 = new HashMap<String, String>();
	HashMap<String, String> map20 = new HashMap<String, String>();
	HashMap<String, String> map21 = new HashMap<String, String>();
	HashMap<String, String> map22 = new HashMap<String, String>();
	HashMap<String, String> map23 = new HashMap<String, String>();
	HashMap<String, String> map24 = new HashMap<String, String>();
	HashMap<String, String> map25 = new HashMap<String, String>();
	HashMap<String, String> map26 = new HashMap<String, String>();
	HashMap<String, String> map27 = new HashMap<String, String>();
	HashMap<String, String> map28 = new HashMap<String, String>();
	HashMap<String, String> map29 = new HashMap<String, String>();
	HashMap<String, String> map30 = new HashMap<String, String>();
	HashMap<String, String> map31 = new HashMap<String, String>();
	HashMap<String, String> map32 = new HashMap<String, String>();
	HashMap<String, String> map33 = new HashMap<String, String>();
	HashMap<String, String> map34 = new HashMap<String, String>();
	HashMap<String, String> map35 = new HashMap<String, String>();
	HashMap<String, String> map36 = new HashMap<String, String>();
	HashMap<String, String> map37 = new HashMap<String, String>();
	HashMap<String, String> map38 = new HashMap<String, String>();
	HashMap<String, String> map39 = new HashMap<String, String>();
	HashMap<String, String> map40 = new HashMap<String, String>();
	HashMap<String, String> map41 = new HashMap<String, String>();
	HashMap<String, String> map42 = new HashMap<String, String>();
	HashMap<String, String> map43 = new HashMap<String, String>();
	HashMap<String, String> map44 = new HashMap<String, String>();
	HashMap<String, String> map45 = new HashMap<String, String>();
	HashMap<String, String> map46 = new HashMap<String, String>();
	HashMap<String, String> map47 = new HashMap<String, String>();
	HashMap<String, String> map48 = new HashMap<String, String>();
	HashMap<String, String> map49 = new HashMap<String, String>();
	HashMap<String, String> map50 = new HashMap<String, String>();
	HashMap<String, String> map51 = new HashMap<String, String>();
	HashMap<String, String> map52 = new HashMap<String, String>();
	HashMap<String, String> map53 = new HashMap<String, String>();
	HashMap<String, String> map54 = new HashMap<String, String>();

	public ReportSql() {

		tableArray[0][0] = "um_companies";//
		tableArray[0][1] = "Companies";
		tableArray[0][2] = "c";
		tableArray[1][0] = "reports";//
		tableArray[1][1] = "Reports";
		tableArray[1][2] = "r";
		tableArray[2][0] = "sales_leads";//
		tableArray[2][1] = "Sales Leads";
		tableArray[2][2] = "sl";
		tableArray[3][0] = "um_users";//
		tableArray[3][1] = "Users";
		tableArray[3][2] = "u";
		tableArray[4][0] = "states";//
		tableArray[4][1] = "States";
		tableArray[4][2] = "s";
		tableArray[5][0] = "cs_contacts";//
		tableArray[5][1] = "Customer Contacts";
		tableArray[5][2] = "cc";
		tableArray[6][0] = "countries";//
		tableArray[6][1] = "Countries";
		tableArray[6][2] = "cnt";
		tableArray[7][0] = "um_groups";//
		tableArray[7][1] = "Groups";
		tableArray[7][2] = "g";
		tableArray[8][0] = "cities";//
		tableArray[8][1] = "Cities";
		tableArray[8][2] = "cts";
		tableArray[9][0] = "um_group_roles";//
		tableArray[9][1] = "Group Roles";
		tableArray[9][2] = "gr";
		tableArray[10][0] = "um_roles";//
		tableArray[10][1] = "Roles";
		tableArray[10][2] = "rls";
		tableArray[11][0] = "m_campaign";//
		tableArray[11][1] = "Campaign";
		tableArray[11][2] = "cmn";
		tableArray[12][0] = "um_user_groups";//
		tableArray[12][1] = "User Group";
		tableArray[12][2] = "ug";
		tableArray[13][0] = "currency_type";//
		tableArray[13][1] = "Currency Type";
		tableArray[13][2] = "ct";
		tableArray[14][0] = "m_campaign_type";//
		tableArray[14][1] = "Campaign Type";
		tableArray[14][2] = "cmt";
		tableArray[15][0] = "um_locations";//
		tableArray[15][1] = "Locations";
		tableArray[15][2] = "l";
		tableArray[16][0] = "cs_accounts";//
		tableArray[16][1] = "Customer Accounts";
		tableArray[16][2] = "ca";
		tableArray[17][0] = "um_parameters";//
		tableArray[17][1] = "Parameter";
		tableArray[17][2] = "p";
		tableArray[18][0] = "um_franchises";//
		tableArray[18][1] = "Franchises";
		tableArray[18][2] = "f";
		tableArray[19][0] = "um_operations";//
		tableArray[19][1] = "Operations";
		tableArray[19][2] = "o";
		tableArray[20][0] = "um_user_history";//
		tableArray[20][1] = "User History";
		tableArray[20][2] = "uh";
		tableArray[21][0] = "sales_opportunities";//
		tableArray[21][1] = "Sales Oppurtunities";
		tableArray[21][2] = "so";
		tableArray[22][0] = "products";//
		tableArray[22][1] = "Products";
		tableArray[22][2] = "pr";
		tableArray[23][0] = "s_package_products";//
		tableArray[23][1] = "Package's Products";
		tableArray[23][2] = "spp";
		tableArray[24][0] = "s_packages";//
		tableArray[24][1] = "Packages";
		tableArray[24][2] = "sp";
		tableArray[25][0] = "orders";//
		tableArray[25][1] = "Orders";
		tableArray[25][2] = "ord";
		tableArray[26][0] = "documents";//
		tableArray[26][1] = "Documents";
		tableArray[26][2] = "d";
		tableArray[27][0] = "loyalty";//
		tableArray[27][1] = "Loyalty";
		tableArray[27][2] = "lo";
		tableArray[28][0] = "loyalty_rule";//
		tableArray[28][1] = "Loyalty Rule";
		tableArray[28][2] = "lor";
		tableArray[29][0] = "message_template";//
		tableArray[29][1] = "Message Template";
		tableArray[29][2] = "mt";
		tableArray[30][0] = "orders_detail";//
		tableArray[30][1] = "Orders Details";
		tableArray[30][2] = "ordd";
		tableArray[31][0] = "alerts_and_reminders";
		tableArray[31][1] = "Alerts and Reminders";
		tableArray[31][2] = "aar";

		tableArray[32][0] = "support_cases";
		tableArray[32][1] = "Support Cases";
		tableArray[32][2] = "suc";

		tableArray[33][0] = "support_cases_history";
		tableArray[33][1] = "Support Cases History";
		tableArray[33][2] = "sch";

		tableArray[34][0] = "support_query_type";
		tableArray[34][1] = "Support Query Type";
		tableArray[34][2] = "sqt";

		tableArray[35][0] = "table_mappings";
		tableArray[35][1] = "Table Mappings";
		tableArray[35][2] = "tm";

		tableArray[36][0] = "assigned_tasks";
		tableArray[36][1] = "Assigned Tasks";
		tableArray[36][2] = "at";

		tableArray[37][0] = "at_categories";
		tableArray[37][1] = "Assigned Tasks Categories";
		tableArray[37][2] = "atc";

		tableArray[38][0] = "at_priority";
		tableArray[38][1] = "Assigned Task Priority";
		tableArray[38][2] = "atp";

		tableArray[39][0] = "at_statuses";
		tableArray[39][1] = "Assigned Task Statuses";
		tableArray[39][2] = "ats";

		tableArray[40][0] = "group_permissions";
		tableArray[40][1] = "Group Permissions";
		tableArray[40][2] = "gp";

		tableArray[41][0] = "s_invoice";
		tableArray[41][1] = "Invoices";
		tableArray[41][2] = "invc";

		tableArray[42][0] = "s_invoice_details";
		tableArray[42][1] = "Invoice Details";
		tableArray[42][2] = "id";

		tableArray[43][0] = "s_service_subscribe";
		tableArray[43][1] = "Service Subscription";
		tableArray[43][2] = "ss";

		tableArray[44][0] = "s_service_subscription_history";
		tableArray[44][1] = "Service Subscription HIstory";
		tableArray[44][2] = "sssh";

		tableArray[45][0] = "support_case_user_comments";
		tableArray[45][1] = "Support Case User Comments";
		tableArray[45][2] = "scuc";

		tableArray[46][0] = "customer_payment";
		tableArray[46][1] = "Customer Payment";
		tableArray[46][2] = "cp";

		tableArray[47][0] = "orders_billreimburse_details";
		tableArray[47][1] = "Order Reimburement Details";
		tableArray[47][2] = "obrd";

		tableArray[48][0] = "orders_invoice_settings";
		tableArray[48][1] = "Orders Invoice Settings";
		tableArray[48][2] = "ois";

		tableArray[49][0] = "users_theme";
		tableArray[49][1] = "User theme";
		tableArray[49][2] = "ut";

		tableArray[50][0] = "themes";
		tableArray[50][1] = "Theme";
		tableArray[50][2] = "theme";

		tableArray[51][0] = "payment";
		tableArray[51][1] = "payment";
		tableArray[51][2] = "pymt";

		tableArray[52][0] = "user_preferences";
		tableArray[52][1] = "User Preferences";
		tableArray[52][2] = "up";

		tableArray[53][0] = "payment_reimburse";
		tableArray[53][1] = "Payment Reimburse";
		tableArray[53][2] = "pr";

		tableArray[54][0] = "s_bill";
		tableArray[54][1] = "S Bill";
		tableArray[54][2] = "sbil";

		map10.put("company_status", "Company Status");
		map10.put("company_phone", "Company Phone");
		map10.put("company_email", "Company e-mail");
		map10.put("company_zipcode", "Company Zipcode");
		map10.put("company_city", "Company's City");
		map10.put("company_state", "Company's State");
		map10.put("company_country", "Company's Country");
		map10.put("company_address", "Company's Address");
		map10.put("company_details", "Company Details");
		map10.put("company_name", "Company Name");
		map10.put("company_id", "Company ID");
		// nestedHashMap.add(TableMapping("um_companies"), map10);
		nestedHashMap.add(0, map10);

		map17.put("report_column", "Report Column");
		map17.put("report_description", "Report Description");
		map17.put("report_title", "Report Title");
		map17.put("report_id", "Report ID");
		// nestedHashMap.add(TableMapping("reports"), map17);
		nestedHashMap.add(1, map17);

		map16.put("lead_addedon", "Lead Added On");
		map16.put("lead_status", "Lead Status");
		map16.put("lead_assigned", "Lead Assigned");
		map16.put("lead_source", "Lead Source");
		map16.put("lead_address", "Lead Address");
		map16.put("lead_phone", "Lead Phone");
		map16.put("lead_email", "Lead e-mail");
		map16.put("lead_name", "Lead Name");
		map16.put("lead_id", "Lead ID");
		// nestedHashMap.add(TableMapping("sales_leads"), map16);
		nestedHashMap.add(2, map16);

		map11.put("user_reportsto", "User Reports To");
		map11.put("user_status", "User Status");
		map11.put("user_addedon", "User's Added On");
		map11.put("user_company", "User's Company");
		map11.put("is_franchise_user", "Is Franchise User");
		map11.put("user_picture", "User's Picture");
		map11.put("user_jobtitle", "User's Job Title");
		map11.put("user_phone", "User's Phone");
		map11.put("user_email", "User e-mail");
		map11.put("user_zipcode", "User Zipcode");
		map11.put("user_city", "User's City");
		map11.put("user_state", "User's State");
		map11.put("user_country", "User's Country");
		map11.put("user_address", "User's Address");
		map11.put("user_lname", "User's Last Name");
		map11.put("user_fname", "User's First Name");
		map11.put("user_password", "User's Password");
		map11.put("user_name", "User Name");
		map11.put("user_id", "User ID");
		map11.put("is_online", "Is Online");
		// nestedHashMap.add(TableMapping("um_users"), map11);//
		nestedHashMap.add(3, map11);

		map2.put("state_code", "State Code");
		map2.put("state_name", "State Name");
		map2.put("country_id", "Country ID");
		map2.put("state_id", "State ID");
		// nestedHashMap.add(TableMapping("states"), map2);//
		nestedHashMap.add(4, map2);

		map3.put("contact_status", "Contact Status");
		map3.put("contact_createdon", "Contact Created On");
		map3.put("contact_cnic_copy", "Contact's CNIC Copy");
		map3.put("contact_cnic", "Contact's CNIC");
		map3.put("contact_dob", "Contact's DOB");
		map3.put("contact_phone", "Contact's Phone");
		map3.put("contact_email", "Contact e-mail");
		map3.put("contact_zipcode", "Contact's Zipcode");
		map3.put("contact_city", "Contact's City");
		map3.put("contact_state", "Contact's State");
		map3.put("contact_country", "Contact's Country");
		map3.put("contact_address", "Contact's Address");
		map3.put("contact_father_name", "Contact's Father Name");
		map3.put("contact_lname", "Contact's Last Name");
		map3.put("contact_fname", "Contact First Name");
		map3.put("account_id", "Account ID");
		map3.put("contact_id", "Contact ID");
		// nestedHashMap.add(TableMapping("cs_contacts"), map3);
		nestedHashMap.add(5, map3);

		map14.put("country_code", "Country Code");
		map14.put("country_name", "Country Name");
		map14.put("country_id", "Country ID");
		// nestedHashMap.add(TableMapping("countries"), map14);
		nestedHashMap.add(6, map14);

		map0.put("group_status", "Group Status");
		map0.put("group_details", "Group Details");
		map0.put("group_details", "Group Title");
		map0.put("company_id", "Company ID");
		map0.put("role_id", "Role ID");
		map0.put("group_id", "Group ID");
		// nestedHashMap.add(TableMapping("um_groups"), map0);
		nestedHashMap.add(7, map0);

		map4.put("city_name", "City Name");
		map4.put("state_id", "State ID");
		map4.put("city_id", "City ID");
		// nestedHashMap.add(TableMapping("cities"), map4);
		nestedHashMap.add(8, map4);

		map15.put("role_id", "Role ID");
		map15.put("group_id", "Group ID");
		map15.put("group_role_id", "Group's Role ID");
		// nestedHashMap.add(TableMapping("um_group_roles"), map15);
		nestedHashMap.add(9, map15);

		map12.put("role_details", "Role Details");
		map12.put("role_title", "Role Title");
		map12.put("role_id", "Role ID");
		// nestedHashMap.add(TableMapping("um_roles"), map12);
		nestedHashMap.add(10, map12);

		map19.put("launch_date", "Launch Date");
		map19.put("end_date", "End Date");
		map19.put("start_date", "Start Date");
		map19.put("company_id", "Company ID");
		map19.put("expected_revenue", "Expected Revenue");
		map19.put("description", "Description");
		map19.put("objective", "Objective");
		map19.put("actual_cost", "Actual Cost");
		map19.put("expected_cost", "Expected Cost");
		map19.put("budget", "Budget");
		map19.put("currency", "Currency");
		map19.put("c_type", "Currency Type");
		map19.put("status", "Status");
		map19.put("title", "Title");
		map19.put("id", "ID");
		// nestedHashMap.add(TableMapping("m_campaign"), map19);
		nestedHashMap.add(11, map19);

		map6.put("group_id", "Group ID");
		map6.put("user_id", "User ID");
		map6.put("user_group_id", "User Group ID");
		// nestedHashMap.add(TableMapping("um_user_groups"), map6);//
		nestedHashMap.add(12, map6);

		map20.put("symbol", "Symbol");
		map20.put("title", "Title");
		map20.put("id", "ID");
		// nestedHashMap.add(TableMapping("currency_type"), map20);
		nestedHashMap.add(13, map20);

		map18.put("title", "Title");
		map18.put("id", "ID");
		// nestedHashMap.add(TableMapping("m_campaign_type"), map18);
		nestedHashMap.add(14, map18);

		map7.put("location_status", "location Status");
		map7.put("is_headoffice", "Is Head Office");
		map7.put("location_city", "Location City");
		map7.put("location_state", "Location's State");
		map7.put("location_country", "Location's Country");
		map7.put("location_address", "Location's Address");
		map7.put("location_details", "Location Details");
		map7.put("location_title", "Location Title");
		map7.put("company_id", "Company ID");
		map7.put("location_id", "Location ID");
		// nestedHashMap.add(TableMapping("um_locations"), map7);
		nestedHashMap.add(15, map7);

		map13.put("account_zipcode", "Account's Zipcode");
		map13.put("account_iscompany", "Account Is Company");
		map13.put("account_status", "Account Status");
		map13.put("account_createdon", "Account Created On");
		map13.put("account_phone", "Account's Phone");
		map13.put("account_email", "Account e-mail");
		map13.put("account_city", "Account's City");
		map13.put("account_state", "Account's State");
		map13.put("account_country", "Account's Country");
		map13.put("account_address", "Account's Address");
		map13.put("account_title", "Account's Title");
		map13.put("account_id", "Account ID");
		// nestedHashMap.add(TableMapping("cs_accounts"), map13);
		nestedHashMap.add(16, map13);

		map8.put("company_id", "Company ID");
		map8.put("parameter_value", "Parameter Value");
		map8.put("parameter_title", "Parameter Title");
		map8.put("parameter_id", "Parameter ID");
		// nestedHashMap.add(TableMapping("um_parameters"), map8);
		nestedHashMap.add(17, map8);

		map1.put("franchise_status", "Franchise Status");
		map1.put("franchise_city", "Franchise's City");
		map1.put("franchise_state", "Franchise's State");
		map1.put("franchise_country", "Franchise's Country");
		map1.put("franchise_address", "Franchise's Address");
		map1.put("franchise_details", "Franchise Details");
		map1.put("franchise_title", "Franchise Title");
		map1.put("company_id", "Company ID");
		map1.put("franchise_id", "Franchise ID");
		// nestedHashMap.add(TableMapping("um_franchises"), map1);
		nestedHashMap.add(18, map1);

		map9.put("operation_description", "Operation Description");
		map9.put("operation_title", "Operation Title");
		map9.put("operation_id", "Operation ID");
		// nestedHashMap.add(TableMapping("um_operations"), map9);
		nestedHashMap.add(19, map9);

		map5.put("logout_time", "Logout Time");
		map5.put("login_time", "Login Time");
		map5.put("user_id", "User ID");
		map5.put("user_history_id", "User History ID");
		// nestedHashMap.add(TableMapping("um_user_history"), map5);//
		nestedHashMap.add(20, map5);

		map21.put("opportunity_date", "Opportunity Date");
		map21.put("comments", "Comments");
		map21.put("opportunity_success", "Opportunity Success");
		map21.put("opportunity_revenue", "Opportunity Revenue");
		map21.put("customer_id", "Customer ID");
		map21.put("user_id", "User ID");
		map21.put("status", "Status");
		map21.put("opportunity_title", "Opportunity Title");
		map21.put("opportunity_id", "Opportunity ID");
		// nestedHashMap.add(TableMapping("sales_opportunities"), map21);//
		nestedHashMap.add(21, map21);

		map22.put("product_title", "Product Title");
		map22.put("product_id", "Product ID");
		// nestedHashMap.add(TableMapping("products"), map22);
		nestedHashMap.add(22, map22);

		map23.put("product_id", "Product ID");
		map23.put("package_id", "Package ID");
		map23.put("id", "ID");
		// nestedHashMap.add(TableMapping("countries"), map14);
		nestedHashMap.add(23, map23);

		map24.put("cost", "Cost");
		map24.put("title", "Title");
		map24.put("id", "ID");
		// nestedHashMap.add(TableMapping("countries"), map14);
		nestedHashMap.add(24, map24);

		map25.put("order_title", "Order Title");
		map25.put("customer_id", "Customer Id");
		map25.put("status", "Status");
		map25.put("order_date", "Order Date");
		map25.put("opportunity_id", "Oppurtunity ID");
		map25.put("order_id", "Order ID");
		map25.put("order_creation", "Order Creation");
		map25.put("order_change_date", "Order Change Date");
		map25.put("created_by", "Created By");
		map25.put("assigned_to", "Assigned To");
		map25.put("save_order_title", "Save Order Title");
		nestedHashMap.add(25, map25);

		map26.put("document_source", "Source");
		map26.put("document_id", "ID");
		map26.put("document_status", "Status");
		map26.put("document_addedon", "Added On");
		map26.put("document_addedby", "Added By");
		map26.put("document_edate", "Expiry Date");
		map26.put("document_pdate", "Published Date");
		map26.put("document_details", "Details");
		map26.put("document_title", "Title");
		// nestedHashMap.add(TableMapping("sales_opportunities"), map21);//
		nestedHashMap.add(26, map26);

		map27.put("loyalty_status", "Status");
		map27.put("launch_date_time", "Date Time");
		map27.put("loyalty_launch", "Launch");
		map27.put("loyalty_details", "Details");
		map27.put("loyalty_title", "Title");
		map27.put("loyalty_id", "ID");
		// nestedHashMap.add(TableMapping("loyalty"), map27);//
		nestedHashMap.add(27, map27);

		map28.put("loyalty_condition_value2", "Condition Value 2");
		map28.put("loyalty_condition2", "Condition 2");
		map28.put("loyalty_rule2", "Rule 2");
		map28.put("loyalty_condition_value", "Condition Value 1");
		map28.put("loyalty_condition", "Condition 1");
		map28.put("loyalty_rule", "Rule");
		map28.put("loyalty_id", "ID");
		map28.put("loyalty_rule_id", "Rule ID");
		// nestedHashMap.add(TableMapping("loyalty_rule"), map28);//
		nestedHashMap.add(28, map28);

		map29.put("send_to", "Send To");
		map29.put("message", "Message");
		map29.put("title", "Title");
		map29.put("template_id", "ID");
		// nestedHashMap.add(TableMapping("message_template"), map29);
		nestedHashMap.add(29, map29);

		map30.put("order_amount", "Order Amount");
		map30.put("quantity", "Quantity");
		map30.put("product_id", "Product ID");
		map30.put("order_id", "Order ID");
		map30.put("order_detail_id", "Order Detail ID");
		nestedHashMap.add(30, map30);

		map31.put("id", "ID");
		map31.put("title", "Title");
		map31.put("transmit_status", "Transmit Status");
		map31.put("is_alert", "Is Alert");
		map31.put("date", "Date");
		map31.put("transmit_date", "Transmit Date");
		map31.put("user_id", "User ID");
		map31.put("expire_date", "Expire Date");
		map31.put("alerts_reminders_status", "Alerts and Reminders Status");
		nestedHashMap.add(31, map31);

		map32.put("case_id", "ID");
		map32.put("case_customer_id", "Customer Id");
		map32.put("case_product_id", "Product Id");
		map32.put("case_type", "Type");
		map32.put("case_user_id", "User Id");
		map32.put("case_status", "Status");
		map32.put("customer_query", "Customer Query");
		map32.put("comments", "Comments");
		map32.put("case_mobile_number", "Mobile No.");
		map32.put("case_create_date", "Creation Date");
		map32.put("case_resolved_date", "Resolved Date");
		map32.put("case_query_type_id", "Query Type Id");
		map32.put("case_token_number", "Token Number");
		map32.put("case_assigned_date", "Assigned Date");
		nestedHashMap.add(32, map32);

		map33.put("case_history_id", "ID");
		map33.put("case_id", "Case Id");
		map33.put("user_id", "User Id");
		map33.put("case_assigned_to_date", "Date");
		nestedHashMap.add(33, map33);

		map34.put("query_type_id", "ID");
		map34.put("query_type_title", "Title");
		map34.put("query_type_alias", "Alias");
		nestedHashMap.add(34, map34);

		map35.put("mapping_id", "ID");
		map35.put("mapping_table", "Table");
		map35.put("mapping_positions", "Positions");
		map35.put("mapping_title", "Title");
		nestedHashMap.add(35, map35);

		map36.put("id", "ID");
		map36.put("ref_id", "Reference Id");
		map36.put("title", "Title");
		map36.put("assigned_to", "Assigned To");
		map36.put("assigned_by", "Assigned By");
		map36.put("category_id", "Category Id");
		map36.put("priority_id", "Priority Id");
		map36.put("status_id", "Status Id");
		map36.put("assigned_date", "Assigned Date");
		map36.put("due_date", "Due Date");
		map36.put("completed_on", "Completed On");
		nestedHashMap.add(36, map36);

		map37.put("id", "ID");
		map37.put("title", "Title");
		map37.put("table_name", "Table");
		nestedHashMap.add(37, map37);

		map38.put("id", "ID");
		map38.put("title", "Title");
		nestedHashMap.add(38, map38);

		map39.put("id", "ID");
		map39.put("title", "Title");
		nestedHashMap.add(39, map39);

		map40.put("permission_id", "ID");
		map40.put("permission_group", "Group");
		map40.put("permission_operation", "Permission");
		map40.put("permission_code", "Code");
		nestedHashMap.add(40, map40);

		map41.put("id", "ID");
		map41.put("subscriber_id", "Subscriber Id");
		map41.put("issue_date", "Issue Date");
		map41.put("created_by", "Created By");
		map41.put("amount", "Amount");
		map41.put("title", "Title");
		nestedHashMap.add(41, map41);

		map42.put("id", "ID");
		map42.put("invoice_id", "Invoice Id");
		map42.put("product_id", "Product Id");
		map42.put("quantity", "Quantity");
		map42.put("product_unit_price", "Unit Price");
		nestedHashMap.add(42, map42);

		map43.put("id", "ID");
		map43.put("customer_id", "Customer Id");
		map43.put("package_id", "Package Id");
		map43.put("product_service_id", "Product Service Id");
		map43.put("is_package", "Is Package");
		nestedHashMap.add(43, map43);

		map44.put("id", "ID");
		map44.put("customer_id", "Customer Id");
		map44.put("date", "Date");
		map44.put("is_subscribe", "Is Subscribe");
		map44.put("package_id", "Package Id");
		map44.put("service_product_id", "Service Product Id");
		map44.put("is_package", "Is Package");
		nestedHashMap.add(44, map44);

		map45.put("case_comment_id", "ID");
		map45.put("comments", "Comments");
		map45.put("case_id", "Case Id");
		map45.put("user_id", "User Id");
		map45.put("case_comments_date", "Comments Date");
		nestedHashMap.add(45, map45);

		map46.put("id", "ID");
		map46.put("customer_id", "Customer Id");
		map46.put("total_amount", "Total Amount");
		map46.put("paid_amount", "Paid Amount");
		map46.put("payment_date", "Payment Date");
		nestedHashMap.add(46, map46);

		map47.put("bill_no", "Bill No.");
		map47.put("order_id", "Order Id");
		map47.put("collection_date", "Collection Date");
		map47.put("recieved_by", "Recieved By");
		map47.put("amount_paid", "Amount Paid");
		map47.put("reimburse_amount", "Reimbure Amount");
		map47.put("billreimburse_id", "Bill reimburse Id");
		nestedHashMap.add(47, map47);

		map48.put("invoice_setting_id", "Id");
		map48.put("invoice_setting_customer", "Customer");
		map48.put("invoice_setting_date", "Date");
		map48.put("invoice_setting_due_days", "Due Days");
		map48.put("invoice_setting_late_payment_fee", "Late Payment Fee");
		map48.put("invoice_setting_created_on", "Created On");
		map48.put("invoice_setting_changed_on", "Changed On");
		map48.put("invoice_setting_company", "Company");
		nestedHashMap.add(48, map48);

		map49.put("id", "Id");
		map49.put("user_id", "User Id");
		map49.put("theme_id", "Theme Id");
		nestedHashMap.add(49, map49);

		map50.put("id", "Id");
		map50.put("title", "Title");
		map50.put("image", "Image");
		map50.put("is_default", "Is Default");
		map50.put("css_path", "Css Path");
		map50.put("css", "Css");
		nestedHashMap.add(50, map50);

		map51.put("id", "Id");
		map51.put("company_id", "Company Id");
		map51.put("invoice_id", "Invoice Id");
		map51.put("paid_on_date", "Paid On Date");
		map51.put("invoice_amount", "Invoice Amount");
		map51.put("paid_amount", "Paid Amount");
		map51.put("remaining_amount", "Remaining Amount");
		map51.put("subscriber_id", "Subscriber Id");
		nestedHashMap.add(51, map51);

		map52.put("user_preferences_id", "Id");
		map52.put("user_id", "User Id");
		map52.put("theme_id", "Theme Id");
		map52.put("show_popup", "Show Popup");
		nestedHashMap.add(52, map52);

		map53.put("id", "Id");
		nestedHashMap.add(53, map53);

		map54.put("id", "Id");
		nestedHashMap.add(54, map54);

	}

	public ArrayList<String> listTables() {
		Connection conn = DBConnection.getConnection();
		ArrayList<String> result = new ArrayList<String>();
		String query = "SELECT TABLE_NAME, TABLE_SCHEMA FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE = 'BASE TABLE' AND (TABLE_SCHEMA='um_crm' OR TABLE_SCHEMA = 'crm') ORDER BY TABLE_NAME;";
		try {
			ResultSet rs = conn.createStatement().executeQuery(query);
			while (rs.next()){
				ArrayList<String> temp = new ArrayList<String>();
				temp.add(rs.getString(1));
				temp.add(rs.getString(2));
				String s = rs.getString(2)+"."+rs.getString(1);
				//System.out.println("Tables name are "+s);
				result.add(s);
			}	
			rs.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		//return (QueryResultSql(query));
	}

	public ArrayList<String> listTableColumns(String schema, String tableName) {
		String query = new String(
				"SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '"
						+ tableName + "'AND TABLE_SCHEMA = '"+schema+"' ORDER BY COLUMN_NAME;");
		return (QueryResultSql(query));
	}

	public ArrayList<String> listPrimaryKey(String tableName) {
		String query = new String(
				"SELECT pg_attribute.attname FROM pg_index, pg_class, pg_attribute WHERE pg_class.oid = '"
						+ tableName
						+ "'::regclass AND indrelid = pg_class.oid AND pg_attribute.attrelid = pg_class.oid AND pg_attribute.attnum = any(pg_index.indkey) AND indisprimary;");
		return (QueryResultSql(query));
	}

	private ArrayList<String> listForeignKeys(String tableName) {
		String temp[] = tableName.split("\\.");
		String query = new String(
				"SELECT kcu.column_name FROM information_schema.table_constraints AS tc JOIN information_schema.key_column_usage AS kcu ON tc.constraint_name = kcu.constraint_name JOIN information_schema.constraint_column_usage AS ccu ON ccu.constraint_name = tc.constraint_name WHERE constraint_type = 'FOREIGN KEY' AND tc.table_name='"
						+ temp[1] + "' AND tc.table_schema = '"+temp[0]+"';");
		//System.out.println("listForeignkeys---------------------------------query is "+query);
		return (QueryResultSql(query));
	}

	public ArrayList<String> listForeignMapping(String tableName) {
		String temp[] = tableName.split("\\.");
		String query = new String(
				"SELECT tc.table_name, kcu.column_name, ccu.table_name AS foreign_table_name, ccu.column_name AS foreign_column_name FROM information_schema.table_constraints AS tc JOIN information_schema.key_column_usage AS kcu ON tc.constraint_name = kcu.constraint_name JOIN information_schema.constraint_column_usage AS ccu ON ccu.constraint_name = tc.constraint_name WHERE constraint_type = 'FOREIGN KEY' AND tc.table_name='"
						+ temp[1] + "' AND tc.table_schema = '"+temp[0]+"';");
		//System.out.println("listForeignMapping---------------------------------query is "+query);
		return (QueryResultSql(query, 4));
	}

	
	public boolean ForeignKeyCheck(String tableName, String columnName) {

		return listForeignKeys(tableName).contains(columnName);

	}

	public String WhichForeignKeyMapping(String table1, String table2) {

		String resultString = null;
		String tableReferenced;
		boolean whichTable = false, tableTriverser = false;

		Iterator<String> fKIterator;

		if (listForeignMapping(table1) != null) {
			fKIterator = listForeignMapping(table1).iterator();
			String temp[] = table2.split("\\.");
			String tbl = temp[1];
			tableReferenced = tbl;
			System.out.println("if");
		} else {
			fKIterator = listForeignMapping(table2).iterator();
			whichTable = true;
			String temp[] = table1.split("\\.");
			String tbl = temp[1];
			tableReferenced = tbl;
			System.out.println("else");
		}

		while (!tableTriverser) {

			while (fKIterator.hasNext()) {
				
				String iteratorString = new String(fKIterator.next());
				String fKTokens[] = iteratorString.split(";");
				//System.out.println("Iterator--------------"+iteratorString+fKTokens.length);
				for (int i = 0; i<fKTokens.length; i++) {
					//System.out.println("fKTokens"+fKTokens.nextToken()+"---"+fKTokens.nextElement());
					if (fKTokens[i].trim().equals(tableReferenced)) {
						resultString = iteratorString;
					//	System.out.println(fKTokens.nextToken()+"Result String "+resultString);
						break;
					}
					if (resultString != null) {
						//System.out.println("Result String "+resultString);
						break;
					}
				}

			}

			if (resultString == null && !whichTable) {
				//System.out.println("rs called");
				fKIterator = listForeignMapping(table2).iterator();
				//System.out.println(fKIterator.next());
				whichTable = true;
				String temp[] = table1.split("\\.");
				String tbl = temp[1].trim();
				tableReferenced = tbl;

			} else {
				tableTriverser = true;
			}
		}
		System.out.println("table triverser "+resultString);
		return resultString;
	}

	public String getColumnType(String tableName, String colName) {
		String colType = null;
		String query = new String(
				"SELECT DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE table_name = '"
						+ tableName + "' AND COLUMN_NAME = '" + colName + "';");
		colType = QueryResultSql(query).get(0);
		return colType;
	}

	public ArrayList<String> QueryResultSql(String query) {
		Connection conn = DBConnection.getConnection();
		 /*Connection conn = null;
		 String url = "jdbc:postgresql://localhost:5432/";
		 String dbName = "srpl_dev_db2";
		    String driver = "org.postgresql.Driver";
		  String userName = "root"; 
		  String password = "root";
		  try {
			Class.forName(driver).newInstance();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		  try {
			conn = DriverManager.getConnection(url+dbName,userName,password);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		ArrayList<String> result = new ArrayList<String>();

		try {
			ResultSet rs = conn.createStatement().executeQuery(query);
			while (rs.next())
				result.add(rs.getString(1));
			rs.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return (result);
	}
	

	private ArrayList<String> QueryResultSql(String query, int numberOfColumns)  {
		Connection conn = DBConnection.getConnection();
		 /*Connection conn = null;
		  String url = "jdbc:postgresql://localhost:5432/";
		 String dbName = "srpl_dev_db2";
		    String driver = "org.postgresql.Driver";
		  String userName = "root"; 
		  String password = "root";
		  try {
				Class.forName(driver).newInstance();
			} catch (InstantiationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			  try {
				conn = DriverManager.getConnection(url+dbName,userName,password);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/
		ArrayList<String> result = new ArrayList<String>();

		try {

			ResultSet rs = conn.createStatement().executeQuery(query);
			String rsRow;

			while (rs.next()) {
				rsRow = new String(rs.getString(1));
				for (int rsIterator = 2; rsIterator <= numberOfColumns; rsIterator++) {

					rsRow = rsRow.concat("; " + rs.getString(rsIterator));
					System.out.println("Result set row "+rsRow);
				}
				result.add(rsRow);
			}
			rs.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return (result);
	}

	public String ColumnAliasing(int tableNumber, String columnName) {

		return nestedHashMap.get(tableNumber).get(columnName);

	}

	public String TableAliasing(String tableName) {

		int tableTriverser;

		for (tableTriverser = 0; tableTriverser < tableArray.length; tableTriverser++) {
			if (tableName.equals(tableArray[tableTriverser][0])) {
				break;
			}
		}

		if (tableTriverser == tableArray.length) {
			return tableName;
		} else {
			return tableArray[tableTriverser][1];
		}

	}

	public int TableIndex(String tableName) {

		int tableTriverser;

		for (tableTriverser = 0; tableTriverser < tableArray.length; tableTriverser++) {
			if (tableName.equals(tableArray[tableTriverser][0])) {
				break;
			}
		}

		return tableTriverser;

	}

	public String TableDBAlias(String tableName) {

		int tableTriverser;

		for (tableTriverser = 0; tableTriverser < tableArray.length; tableTriverser++) {
			if (tableName.equals(tableArray[tableTriverser][0])) {
				break;
			}
		}

		if (tableTriverser == tableArray.length) {
			return tableName;
		} else {
			return tableArray[tableTriverser][2];
		}

	}

	public String getTableNameByAlias(String tableAlias) {
		int tableTriverser;
		for (tableTriverser = 0; tableTriverser < tableArray.length; tableTriverser++) {
			if (tableAlias.equals(tableArray[tableTriverser][2])) {
				break;
			}
		}

		if (tableTriverser == tableArray.length) {
			return tableAlias;
		} else {
			return tableArray[tableTriverser][0];
		}

	}
	
	public static void main(String[] args)  {
		ReportSql rs = new ReportSql();
		rs.WhichForeignKeyMapping("um_crm.um_users",  "um_crm.users_theme");
	}
	
	public void updateReportClauses(int reportId, String str, String clz) {
		String clause = "";
		switch (clz) {
		case "select":
			clause = "\"select\" = '" + str + "'";
			break;
		case "select_graph":
			clause = "select_graph = '" + str + "'";
			break;	
		case "from":
			clause = "\"from\" = '" + str + "'";
			break;
		case "join":
			clause = "\"join\" = '"+ str+ "'";
			break;
		case "groupby":
			clause = "group_by = '" + str + "'";
			break;
		case "orderby":
			clause = "order_by = '" + str + "'";
			break;
		case "formula":
			clause = "formula = '" + str + "'";
			break;
		default:
			clause = "";
		}
		if (clause != "") {
			String upd = "UPDATE crm.reports SET " + clause
					+ " WHERE report_id = " + reportId;
			System.out.println("Query is " + upd);
			
		}
	}
	
	public List<KeyValueItem> getSplitterList(String sql, int parentId) {

		List<KeyValueItem> items = new ArrayList<KeyValueItem>();
		try {
			Connection conn = DBConnection.getConnection();
			ResultSet rs = conn.createStatement().executeQuery(sql);
		
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
			rs.close();
			conn.close();
			
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

		return items;
	}
	public List<KeyValueItem> getGroupByReportId(int reportId) {
		ReportSql rs = new ReportSql();
		
		return rs.getSplitterList(
				"SELECT report_type, report_type FROM crm.reports where report_id="
						+ reportId, 0);
	}
	
	public void saveFilter(String where, int reportId) throws SQLException {
System.out.println("where+++++++++++++" + where +"reportId++++++++++++" +reportId);
		Connection conn = DBConnection.getConnection();
		//String sql = "Select report_where = '" + where + "from crm.reports";
		String sql = "Update  crm.reports SET \"report_where\" = '"
				+ where + "' WHERE report_id = " + reportId;
		// System.out.println(sql);
		ResultSet rs = conn.createStatement().executeQuery(sql);
		rs.close();
		conn.close();
	}
	
	public List<KeyValueItem> getFilterByReportId(int reportId) {
		ReportSql ds =ReportSql.getInstance(); 
		return ds.getSplitterList(
				"SELECT c.report_where, c.report_where FROM crm.reports c where report_id="
						+ reportId, 0);
	}
}