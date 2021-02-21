package com.srpl.bi.service;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bitguiders.util.KeyValueItem;
import com.srpl.bi.web.model.reportsbuilder.DataPaletteBackingBean.ColumnModel;

public class ReportService {

	private static ReportService reportService = new ReportService();
	private DBConnectionService dbConnectionService = DBConnectionService
			.getInstance();

	private ReportService() {

	}

	public static ReportService getInstance() {
		return reportService;
	}

	public List<KeyValueItem> getReportListByUserId(Long userId, Long companyId) {
		return getList("SELECT bi_report_id, bi_report_title FROM bi_report WHERE company_id ="+companyId,
				0);
	}

	private List<KeyValueItem> getList(String sql, int parentId) {

		List<KeyValueItem> items = new ArrayList<KeyValueItem>();
		try {
			dbConnectionService.open();
			ResultSet rs = dbConnectionService.executeQuery(sql);
			while (rs.next()) {
				items.add(new KeyValueItem(parentId, rs.getString(2), rs
						.getString(1)));
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

		return items;
	}

/*	public KeyValueItem getReportById(int reportId) {

		ReportService.getInstance().getReportListByUserId(1);
		KeyValueItem item = new KeyValueItem(reportId, "", "");
		return item;
	}*/

	public int saveReport(String title, String description, int dataSourceId,
			String summary, String graph, String graphData, String logoExt,
			boolean showLogo, boolean serialNo, Long userId, Long companyId) {
		int reportId = 0;
		String logo = "";
		String sqlReport = "INSERT INTO bi_report"
				+ "(bi_report_title, bi_report_description, user_id, company_id)" + "VALUES ('"
				+ title + "', '" + description + "', "+userId+", "+companyId+")";

		try {
			dbConnectionService.open();
			ResultSet r = dbConnectionService.insertQuery(sqlReport);
			if (r.next()) {
				reportId = r.getInt(1);
			}
			if (reportId != 0) {
				if (!logoExt.equals("")) {
					logo = reportId + "." + logoExt;
				}
				String sqlReportDetails = "INSERT INTO bi_report_detail"
						+ "(bi_report_id, report_summary, report_graph, graph_data, report_logo, report_show_logo, report_serial_no)"
						+ " VALUES (" + reportId + ", '" + summary + "', '"
						+ graph + "', '" + graphData + "', '" + logo + "', "
						+ showLogo + ","+serialNo+")";
				dbConnectionService.executeQuery(sqlReportDetails);

				String sqlReportDS = "INSERT INTO bi_report_data_source"
						+ "(bi_report_id, bi_data_source_id)" + " VALUES ("
						+ reportId + "," + dataSourceId + ")";
				dbConnectionService.executeQuery(sqlReportDS);

			}
			dbConnectionService.close();
		} catch (Exception ex) {
			System.out.println("Exception in SAVE Report is " + ex);
		}
		return reportId;
	}

	public Map<String, String> editReport(int id) {
		Map<String, String> report = new HashMap<String, String>();
		String sql = "SELECT r.*, rd.*, rds.* FROM bi_report r, bi_report_detail rd, bi_report_data_source rds WHERE r.bi_report_id ="
				+ id
				+ " AND r.bi_report_id = rd.bi_report_id  AND r.bi_report_id = rds.bi_report_id";
		System.out.println("Query in edit report is " + sql);
		try {
			dbConnectionService.open();
			ResultSet rs = dbConnectionService.executeQuery(sql);
			ResultSetMetaData md = rs.getMetaData();
			int columnCount = md.getColumnCount();
			if (rs.next()) {
				for (int i = 1; i <= columnCount; i++) {
					report.put(md.getColumnName(i),
							rs.getString(md.getColumnName(i)));
					// System.out.println(
					// md.getColumnName(i)+"---"+rs.getString(md.getColumnName(i)));
				}
			}
			dbConnectionService.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return report;
	}

	public void updateReport(int reportId, String title, String description,
			int dataSourceId, String summary, String graph, String graphData,
			String logo, boolean showLogo, boolean serialNo, boolean reportSaved) {
		String sql = "UPDATE bi_report SET  bi_report_title = '" + title
				+ "', bi_report_description = '" + description + "'"
				+ ", report_saved = " + reportSaved + " WHERE bi_report_id = "
				+ reportId;
		String sqlReportDetails = "UPDATE bi_report_detail"
				+ " SET report_summary = '" + summary + "', report_graph = '"
				+ graph + "', graph_data = '" + graphData
				+ "', report_logo = '" + logo + "', report_show_logo = "
				+ showLogo + ", report_serial_no = "+serialNo+" WHERE bi_report_id = " + reportId;
		try {
			dbConnectionService.open();
			dbConnectionService.executeUpdate(sql);
			dbConnectionService.executeUpdate(sqlReportDetails);
			dbConnectionService.close();
		} catch (Exception ex) {
			System.out.println("Exception in Update Report is " + ex);
		}
	}

	public void deleteReport(int id) {
		String sql = "DELETE FROM bi_report  WHERE bi_report_id = " + id;
		String sqlReportDetail = "DELETE FROM bi_report_detail  WHERE bi_report_id = "
				+ id;
		String sqlReportDS = "DELETE FROM bi_report_data_source  WHERE bi_report_id = "
				+ id;
		try {
			dbConnectionService.open();
			dbConnectionService.executeQuery(sqlReportDS);
			dbConnectionService.executeQuery(sqlReportDetail);
			dbConnectionService.executeQuery(sql);
			dbConnectionService.close();
		} catch (Exception ex) {
			System.out.println("Exception in Delete Report is " + ex);
		}
	}

	public void saveFilter(String where, int reportId) throws SQLException {
		String sql = "Update  bi_report_data_source SET \"where\" = '"
				+ where + "' WHERE bi_report_id = " + reportId;
		// System.out.println(sql);

		dbConnectionService.open();
		dbConnectionService.executeUpdate(sql);
		dbConnectionService.close();

	}

	public String getReportData(int reportId, List<Map<String, String>> rows,
			List<ColumnModel> columns) throws NumberFormatException, Exception {
		
		String exception = "";
		/*String sql = "SELECT rds.*, dsc.join as join  FROM bi_report_data_source rds, bi_data_source_connection dsc WHERE bi_report_id = "
				+ reportId
				+ " AND rds.bi_data_source_id = dsc.bi_data_source_id";*/

		String sql = "SELECT rds.* FROM bi_report_data_source rds WHERE bi_report_id = "+ reportId;
		String sqlDataSource = "";
		//try {
			dbConnectionService.open();
			ResultSet rs = dbConnectionService.executeQuery(sql);
			dbConnectionService.close();
			if (rs.next()) {
				sqlDataSource = generateSql(rs);
				String select[] = rs.getString("select").split(";");
				ResultSet rs1 = DataService.getInstance().getQueryResults(sqlDataSource, Integer.parseInt(rs.getString("bi_data_source_id")));
				if(rs1 != null){
					ResultSetMetaData md = rs1.getMetaData();
					int columnCount = md.getColumnCount();
					for (int i = 1; i <= columnCount; i++) {
						String temp[] = select[i - 1].split("\\.");
						String tbl = getColumnTable(reportId, temp[0]);
						String tbls[] = tbl.split(";"); 
						System.out.println(temp[0]+"Get Report Data "+ tbls[0]+"-------"+tbls[1]);
						columns.add(new ColumnModel(md.getColumnName(i)
								.toUpperCase(), md.getColumnName(i), md
								.getColumnTypeName(i), tbls[0], tbls[1]));
					}
					int rowCount = 0;
					while (rs1.next()) {
						int count = 1;
						rowCount++;
						Map<String, String> row = new HashMap<String, String>();
						for (ColumnModel column : columns) {
							if (column.getProperty().equals("serialNo")) {
								Integer temp = rowCount;
								row.put(column.getProperty(), temp.toString());
							} else {
								if(column.getPropertyType().equals("date")){
									String date = "";
									if(rs1.getDate(count) != null){
										java.util.Date newDate =  rs1.getDate(count);
										SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
									    date = dt1.format(newDate);
								   } 
									row.put(column.getProperty(), date);
								} else if(column.getPropertyType().equals("timestamp") || column.getPropertyType().equals("timestamp with timezone") ||column.getPropertyType().equals("timestamp without timezone") ){
									String date = "";
									if(rs1.getTimestamp(count) != null) {
										java.util.Date newDate =  rs1.getTimestamp(count);
										SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
									    date = dt1.format(newDate);
									}
									row.put(column.getProperty(), date);
								} else{
									row.put(column.getProperty(), rs1.getString(count));
								}
								count++;
							}
						}
						rows.add(row);
					}
				} else {
					
				}	
			}
		/*} catch (SQLException ex) {
			System.out.println("Exception "+ex.getMessage());
			exception = ex.getMessage();
		}*/
		return exception;
	}
	

	public String getSql(int reportId) {
		String sql = "SELECT rds.*  FROM bi_report_data_source rds WHERE bi_report_id = "+ reportId;
		String sqlDataSource = "";
		try {
			dbConnectionService.open();
			ResultSet rs = dbConnectionService.executeQuery(sql);
			if (rs.next()) {
				sqlDataSource = generateSql(rs);
				// System.out.println(sqlDataSource);
			}
			dbConnectionService.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return sqlDataSource;
	}

	private String generateSql(ResultSet rs) {
		String select = null;
		String selectGraph = null;
		String from = null;
		String where = null;
		String join = null;
		String groupBy = null;
		String orderBy = null;
		String sql = null;
		String formula = null;

		String selectKeyWord = "";
		String selectColSeparator = ", ";
		String whereKeyWord = "";
		String whereSeparator = " AND ";

		StringBuilder sb = new StringBuilder();

		try {
			select = rs.getString("select");
			selectGraph = rs.getString("select_graph");
			from = rs.getString("from");
			where = rs.getString("where");
			join = rs.getString("join");
			groupBy = rs.getString("group_by");
			orderBy = rs.getString("order_by");
			formula = rs.getString("formula");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(!isEmpty(select) && !isEmpty(formula)){
			select = getSelectClause(select, formula);
			sb.append("SELECT " + select);
		} else {
			if (!isEmpty(select)) {
				select = getClauseAsString(select, ";", ", ");
				sb.append("SELECT " + select);
			} else {
				selectKeyWord = " SELECT ";
				selectColSeparator = "";
			}
			if (!isEmpty(formula)) {
				formula = getClauseAsString(formula, ";", ", ");
				sb.append(selectKeyWord + selectColSeparator + formula);
			}
		}	
		if (!isEmpty(from)) {
			from = getClauseAsString(from, ";", ", ");
			sb.append(" FROM " +from);
		}
		if (!isEmpty(where)) {
			where = getClauseAsString(where, ";", " AND ");
			where = where.replace("~", "'");
			sb.append(" WHERE " + where);
		} else {
			whereKeyWord = " WHERE ";
			whereSeparator = "";
		}
		if (!isEmpty(join)) {
			join = getClauseAsString(join, ";", " AND ");
			sb.append(whereKeyWord + whereSeparator + join);
		}
		if (!isEmpty(groupBy)) {
			groupBy = getClauseAsString(groupBy, ";", ", ");
			sb.append(" GROUP BY " + groupBy);
		}
		if (!isEmpty(orderBy)) {
			orderBy = getClauseAsString(orderBy, ";", ", ");
			sb.append(" ORDER BY " + orderBy);
		}
		sql = sb.toString();
		System.out.println("Sql "+sql);
		return sql;
	}

	private boolean isEmpty(String str) {
		if (str == null) {
			return true;
		} else {
			str = str.trim();
			if (str.equals("")) {
				return true;
			}
		}
		return false;
	}
	
	public String getSelectClause(String select, String formula){
		String selectArr[] = select.split(";");
		String formulaArr[] = formula.split(";");
		String sel = "";
		for(int i = 0; i < selectArr.length; i++){
			boolean colMatched = false;
			for(int j = 0; j < formulaArr.length; j++){
				String forCol = "";
				if(formulaArr[j].indexOf("(") != -1 && formulaArr[j].indexOf(")") != -1 ){
					forCol = formulaArr[j].substring(formulaArr[j].lastIndexOf("(")+1, formulaArr[j].indexOf(")"));
					if(forCol.indexOf(",") != -1){
						forCol = forCol.substring(forCol.indexOf(",")+1, forCol.length()).trim();
					}
				}
				if(forCol.equals(selectArr[i].trim())){
					formulaArr[j] = formulaArr[j].replace("~", "'");
					sel += formulaArr[j]+", ";
					colMatched = true;
				}
			}
			if(!colMatched){
				sel += selectArr[i]+", ";
			}
		}
		if(sel.length() > 0){
			sel = sel.substring(0, sel.length() -2);
		}
		return sel;
	}

	public String getClauseAsString(String clause, String splitter,
			String separator) {
		String temp[] = clause.split(splitter);
		String str = "";
		for (int i = 0; i < temp.length; i++) {
			temp[i] = temp[i].trim();
			if(!temp[i].equals("")){
				str += temp[i];
				if (i < temp.length - 1) {
					str += separator;
				}
			}	
		}
		return str;
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
		case "where":
			clause = "\"where\" = '"+ str+ "'";
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
			String upd = "UPDATE bi_report_data_source SET " + clause
					+ " WHERE bi_report_id = " + reportId;
			System.out.println("Query is " + upd);
			dbConnectionService.open();
			dbConnectionService.executeUpdate(upd);
			dbConnectionService.close();
		}
	}

	public ResultSet getReportClausesByReportId(int id) {
		String sql = "SELECT * FROM bi_report_data_source WHERE bi_report_id = "
				+ id;
		ResultSet rs = null;

		dbConnectionService.open();
		rs = dbConnectionService.executeQuery(sql);
		dbConnectionService.close();

		return rs;
	}
	
	public String getColumnTable(int reportId, String alias){
		String str = "";
		alias = alias.trim();
		for(KeyValueItem i : getFromByReportId(reportId)){
			String tempStr =i.getKey().trim();
			if(tempStr.indexOf(" ") != -1){
				String temp[] = tempStr.split(" ");
				temp[0] = temp[0].trim();
				temp[1] = temp[1].trim();
				if(temp[0].equals(alias) || temp[1].equals(alias)){
					str = temp[0]+";"+temp[1];
					break;
				}			
			} else if(tempStr.equals(alias)){
				str += tempStr+";"+tempStr;
				break;
			}
		}
		return str;
	}

	
	public List<KeyValueItem> getFromByReportId(int reportId) {
		DataService ds = DataService.getInstance();
		return ds.getSplitterList(
				"SELECT c.from, c.from FROM bi_report_data_source c where bi_report_id="
						+ reportId, 0);
	}
	
	public List<KeyValueItem> getFilterByReportId(int reportId) {
		DataService ds = DataService.getInstance();
		return ds.getSplitterList(
				"SELECT c.where, c.where FROM bi_report_data_source c where bi_report_id="
						+ reportId, 0);
	}
	
	public List<KeyValueItem> getGroupByReportId(int reportId) {

		DataService ds = DataService.getInstance();
		return ds.getSplitterList(
				"SELECT group_by, group_by FROM bi_report_data_source where bi_report_id="
						+ reportId, 0);
	}
	
	public List<KeyValueItem> getOrderByReportId(int reportId) {

		DataService ds = DataService.getInstance();
		return ds.getSplitterList(
				"SELECT order_by, order_by FROM bi_report_data_source where bi_report_id="
						+ reportId, 0);
	}
	
	public List<KeyValueItem> getFormulaByReportId(int reportId) {

		DataService ds = DataService.getInstance();
		return ds.getSplitterList(
				"SELECT formula, formula FROM bi_report_data_source where bi_report_id="
						+ reportId, 0);
	}
	
	public String changeDateFromat(String format, String datetoFromat){
		String date = "";
		String date_s = datetoFromat;

        // *** note that it's "yyyy-MM-dd hh:mm:ss" not "yyyy-mm-dd hh:mm:ss"  
        SimpleDateFormat dt = new SimpleDateFormat(format);
        Date d = null;
		try {
			d = dt.parse(date_s);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        // *** same for the format String below
        SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
        date = dt1.format(d);
		return date;
	}

	public static void main(String[] arg) {
		ReportService rs1 = ReportService.getInstance();
		String sql = "SELECT rds.* FROM bi_report_data_source rds WHERE bi_report_id = 174";
		String sqlDataSource = "";
		DBConnectionService dbConnectionService = DBConnectionService
				.getInstance();
		try {
			dbConnectionService.open();
			ResultSet rs = dbConnectionService.executeQuery(sql);
			dbConnectionService.close();
			if (rs.next()) {
				sqlDataSource = rs1.generateSql(rs);
				System.out.println(sqlDataSource);
			}	
		} catch(Exception e){
			e.printStackTrace();
		}	
	}

}
