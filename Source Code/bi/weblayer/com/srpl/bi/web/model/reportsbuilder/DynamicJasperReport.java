/*
 * DynamicJasper: A library for creating reports dynamically by specifying
 * columns, groups, styles, etc. at runtime. It also saves a lot of development
 * time in many cases! (http://sourceforge.net/projects/dynamicjasper)
 *
 * Copyright (C) 2008  FDV Solutions (http://www.fdvsolutions.com)
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 *
 * License as published by the Free Software Foundation; either
 *
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 *
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 *
 *
 */

package com.srpl.bi.web.model.reportsbuilder;

import java.awt.Color;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import ar.com.fdvs.dj.core.DJConstants;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.core.layout.HorizontalBandAlignment;
import ar.com.fdvs.dj.core.layout.LayoutManager;
import ar.com.fdvs.dj.domain.AutoText;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.ImageBanner;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.builders.StyleBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.ImageScaleMode;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import ar.com.fdvs.dj.util.customexpression.RecordsInPageCustomExpression;

import com.srpl.bi.service.DBConnectionDataService;
import com.srpl.bi.service.DataService;

public class DynamicJasperReport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JasperPrint jp;
	protected JasperReport jr;
	protected DynamicReport dr;
	private String exportType;
	private String title;
	private String description;
	private String summary;
	private String query;
	private List<String[]> cols;
	private String path;
	private String reportLogo;

	private int logoWidth;
	private int logoHeight;
	private int selectedGraph;
	private boolean showLogo;
	private boolean serialNo;
	private int dataSource;
	private int reportId;
	protected Map params = new HashMap();
	private List<String> stringDataTypes = new ArrayList<String>();
	private List<String> numberDataTypes = new ArrayList<String>();
	//private List<String> dataDataTypes = new ArrayList<String>();

	public Map getParams() {
		return params;
	}

	public String getType() {
		return exportType;
	}

	public void setType(String type) {
		this.exportType = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public List<String[]> getCols() {
		return cols;
	}

	public void setCols(List<String[]> cols) {
		this.cols = cols;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getReportLogo() {
		return reportLogo;
	}

	public int getDataSource() {
		return dataSource;
	}

	public void setDataSource(int dataSource) {
		this.dataSource = dataSource;
	}

	public void setReportLogo(String reportLogo) {
		this.reportLogo = reportLogo;
	}

	public int getLogoWidth() {
		return logoWidth;
	}

	public void setLogoWidth(int logoWidth) {
		this.logoWidth = logoWidth;
	}

	public int getLogoHeight() {
		return logoHeight;
	}

	public void setLogoHeight(int logoHeight) {
		this.logoHeight = logoHeight;
	}

	public int getSelectedGraph() {
		return selectedGraph;
	}

	public void setSelectedGraph(int selectedGraph) {
		this.selectedGraph = selectedGraph;
	}
	
	public boolean isShowLogo() {
		return showLogo;
	}

	public void setShowLogo(boolean showLogo) {
		this.showLogo = showLogo;
	}

	public boolean isSerialNo() {
		return serialNo;
	}

	public void setSerialNo(boolean serialNo) {
		this.serialNo = serialNo;
	}

	public DynamicJasperReport() {
		this.title = "";
		this.description = "";
		this.summary = "";
		this.query = "";
		this.cols = null;
		this.exportType = "pdf";
		this.path = "";
		this.reportLogo = "ll";
		this.logoWidth = 100;
		this.logoHeight  = 100;
		this.serialNo = false;
	}

	public DynamicJasperReport(String title, String description,
			String summary, String query, List<String[]> cols,
			String exportType, String path, String logo, int logoWidth,
			int logoHeight, boolean showLogo, boolean serialNo, int reportId, int dataSource) {
		this.title = title;
		this.description = description;
		this.summary = summary;
		this.query = query;
		this.cols = cols;
		this.exportType = exportType;
		this.path = path;
		this.reportLogo = logo;
		this.logoWidth = logoWidth;
		this.logoHeight = logoHeight;
		this.showLogo = showLogo;
		this.serialNo = serialNo;
		this.reportId = reportId;
		this.dataSource = dataSource;
	}

	public DynamicReport buildReport() throws Exception {
		//System.out.println("Query is "+query);
		String q[] = query.split("FROM");
		String newSel = " SELECT ";
		boolean dateCol = false;
		List<AbstractColumn> xAxis = new ArrayList<AbstractColumn>();
		List<AbstractColumn> yAxis = new ArrayList<AbstractColumn>();

		String sumry[] = summary.split(";");
		String sumStr = "";
		if (!description.equals("")) {
			sumStr = description + "\\n\\n";
		}
		for (int j = 0; j < sumry.length; j++) {
			sumStr += sumry[j] + "\\n\\n";
		}

		Style titleStyle = new Style();
		titleStyle.setFont(Font.ARIAL_BIG_BOLD);
		titleStyle.setHorizontalAlign(HorizontalAlign.CENTER);

		Style subtitleStyle = new Style();
		subtitleStyle.setFont(Font.ARIAL_MEDIUM_BOLD);
		subtitleStyle.setHorizontalAlign(HorizontalAlign.CENTER);

		Style detailStyle = new Style();
		Style headerStyle = new Style();
		headerStyle.setFont(Font.ARIAL_MEDIUM_BOLD);
		headerStyle.setBorderTop(Border.PEN_2_POINT());
		headerStyle.setBorderBottom(Border.THIN());
		headerStyle.setTransparency(Transparency.OPAQUE);
		headerStyle.setHorizontalAlign(HorizontalAlign.CENTER);
		headerStyle.setVerticalAlign(VerticalAlign.MIDDLE);

		Style numberStyle = new Style();
		numberStyle.setHorizontalAlign(HorizontalAlign.RIGHT);
		Style amountStyle = new Style();
		amountStyle.setHorizontalAlign(HorizontalAlign.RIGHT);
		amountStyle.setBackgroundColor(Color.cyan);
		amountStyle.setTransparency(Transparency.OPAQUE);
		Style oddRowStyle = new Style();
		oddRowStyle.setBorder(Border.THIN());
		Color veryLightBlue = new Color(204, 223, 245);
		oddRowStyle.setBackgroundColor(veryLightBlue);
		oddRowStyle.setTransparency(Transparency.OPAQUE);

		DynamicReportBuilder drb = new DynamicReportBuilder();
		//System.out.println("------------------------"+serialNo);
		if(serialNo){
		//	System.out.println("------------------------"+serialNo);
			AbstractColumn sn = ColumnBuilder.getNew()
					.setCustomExpression(new RecordsInPageCustomExpression())
					.setTitle("Serial No.").setWidth(new Integer(85))
					.setStyle(detailStyle).setHeaderStyle(headerStyle)
					.build();
			drb.addColumn(sn);
		}
		
		for (int i = 0; i < cols.size(); i++) {
			int index = cols.get(i)[1].indexOf(".");
			
			String col = null;
			String colTitle = cols.get(i)[0];
			String colType = cols.get(i)[2];
			String width = cols.get(i)[3];
			System.out.println("Column type is "+colType);
			if (index == -1) {
				col = cols.get(i)[1];
			} else {
				try {
					String temp[] = cols.get(i)[1].split("\\.");
					col = temp[1];
				} catch (Exception e) {
					System.out.println("Exception while splitting values"
							+ cols.get(i)[1]);
				}
			}
			if (isString(colType)) {
				//System.out.println("String type col ----"+colType);
				AbstractColumn ac = ColumnBuilder
						.getNew()
						.setColumnProperty(col,
								String.class.getName()).setTitle(colTitle)
						.setWidth(new Integer(85)).setStyle(detailStyle)
						.setHeaderStyle(headerStyle).build();
				drb.addColumn(ac);
				xAxis.add(ac);
				if(colType.equals("date") || colType.equals("timestamp")) {
					dateCol = true;
					newSel += " to_char("+col+", 'DD-MM-YYYY') AS "+col+",";
				} else {
					newSel += col+ ",";
				}	
			} else if(isNumber(colType)) {
				//System.out.println("Int type col ----"+colType);
				AbstractColumn ac = ColumnBuilder.getNew()
						.setColumnProperty(col, Long.class.getName())
						.setTitle(colTitle).setWidth(new Integer(85))
						.setStyle(detailStyle).setHeaderStyle(headerStyle)
						.build();
				drb.addColumn(ac);
				yAxis.add(ac);
				newSel += col+ ",";
			} else {
				//System.out.println("else");
				AbstractColumn ac = ColumnBuilder.getNew()
						.setColumnProperty(col, String.class.getName())
						.setTitle(colTitle).setWidth(new Integer(85))
						.setStyle(detailStyle).setHeaderStyle(headerStyle)
						.build();
				drb.addColumn(ac);
				newSel += col+ ",";
			}
		}
		
		if(query.indexOf("date_trunc") == -1 && dateCol){
			newSel = newSel.substring(0, newSel.length()-1);
			query = newSel+" FROM "+q[1];
			//System.out.println(newSel+"...........Query is "+query);
		}	
        
		drb.setTitle(title)
				.setSubtitle(sumStr)
				.setDefaultStyles(titleStyle, subtitleStyle, headerStyle,
						detailStyle)
				.setPrintBackgroundOnOddRows(true)
				.setOddRowBackgroundStyle(oddRowStyle)
				.setColumnsPerPage(new Integer(1))
				.setColumnSpace(new Integer(5))
				.setQuery(query, DJConstants.QUERY_LANGUAGE_SQL)
				.setWhenNoData("No data found for this report", null, true,
						true).setUseFullPageWidth(true);
		if(isShowLogo()){
			if(!reportLogo.equals("")){
				drb.addImageBanner(reportLogo, 100, 50, ImageBanner.ALIGN_RIGHT, ImageScaleMode.FILL);
			}
		}	

		Style atStyle2 = new StyleBuilder(true).setFont(
				new Font(9, Font._FONT_TIMES_NEW_ROMAN, false, true, false))
				.build();
		//System.out.println(AutoText.POSITION_FOOTER+"----====--"+AutoText.POSITION_HEADER+"====="+AutoText.ALIGNMENT_RIGHT);
			//
			/*AutoText at = new AutoText((byte)4, (byte)0, HorizontalBandAlignment.LEFT);
		at.setMessageKey("testing----");
		drb.addAutoText(at);*/
		drb.addAutoText("\u00a9 All Rights Reserved. SRPL",
				AutoText.POSITION_FOOTER, AutoText.ALIGNMENT_RIGHT, 200);
		drb.addAutoText(AutoText.AUTOTEXT_PAGE_X_OF_Y,
				AutoText.POSITION_FOOTER, AutoText.ALIGMENT_LEFT, 40, 30,
				atStyle2);
		
		if(xAxis.size() > 0 && yAxis.size() > 0){ 
		/*	GroupBuilder gb1 = new GroupBuilder(); 
			 DJGroup g1 = gb1.setCriteriaColumn((PropertyColumn) xAxis.get(0)).build();
			 drb.addGroup(g1);
			 
			 GroupBuilder gb2 = new GroupBuilder(); 
			 DJGroup g2 = gb2.setCriteriaColumn((PropertyColumn) yAxis.get(0)).build();
			 drb.addGroup(g2);*/
			 
			switch (getSelectedGraph()) {
			case 3:
				Bar3DChartBuilder bc = new Bar3DChartBuilder();
				bc.setTitle(title);
				bc.setxAxisTitle("x axis");
				bc.setyAxisTitle("y axis");
				bc.create3DBarChart(xAxis, yAxis, drb);
				break;
			case 5:
				LineChartBuilder lc = new LineChartBuilder();
				lc.setTitle(title);
				lc.setxAxisTitle("x axis");
				lc.setyAxisTitle("y axis");
				lc.create3DBarChart(xAxis, yAxis, drb);
				break;
			case 6:
				AreaChartBuilder ac = new AreaChartBuilder();
				ac.setTitle(title);
				ac.setxAxisTitle("x axis");
				ac.setyAxisTitle("y axis");
				ac.create3DBarChart(xAxis, yAxis, drb);
				break;
	
			}
		}	
		DynamicReport dr = drb.build();
		return dr;
	}

	public static void main(String[] args) throws Exception {
		System.out.println();
		String url = "jdbc:postgresql://localhost/srpl_db";
		//String temp[] = url.split("/");
		int si = url.indexOf("//");
		int ei = url.indexOf("/") ;
		System.out.println(url.indexOf("//")+"----"+url.substring(si, ei) );
		/*DynamicJasperReport test = new DynamicJasperReport();
		test.cols = new ArrayList<String[]>();
		String colType = DataService.getInstance()
				.getColumnType("um_users", "user_country");
		String colType1 = DataService.getInstance()
				.getColumnType("um_users", "user_state");
		String colType2 = DataService.getInstance()
				.getColumnType("um_users", "user_jobtitle");
		String colType3 = DataService.getInstance()
				.getColumnType("um_users", "user_id");
		
		String colType4 = DataService.getInstance()
				.getColumnType("um_users", "user_fname");
		System.out.println(colType);
		test.summary = "Order id: 1122; Title: DSL; Label3: Value 3; Label4: Value4";
		test.cols.add(new String[] { "user_country", "um_users.user_country", colType,"30" });
		test.cols.add(new String[] { "user_state", "um_users.user_state", colType1,"30" });
		test.cols.add(new String[] { "Job Title", "um_users.user_jobtitle", colType2,"30" });
		//test.cols.add(new String[] { "User Id", "user_id", colType3,"30" });
		//test.cols.add(new String[] { "User Name", "user_fname", colType4,"30" });
		test.title = "This is a big title to test the position of the image";
		test.description = "Report Description";
		test.query = "SELECT um_users.user_country,um_users.user_state, um_users.user_jobtitle FROM um_bi.um_users ORDER BY um_users.user_country,um_users.user_state";
		test.setSelectedGraph(3);
		//test.reportLogo = "77.jpg";
		String name = test.generateReport();
		JasperViewer.viewReport(test.jp);*/
	}

	public String generateReport() throws Exception {
	//	DBConnectionService dbConnectionService = DBConnectionService
	//			.getInstance();
		Connection con = null;

		String fileName = reportId + "." + exportType;//this.getClass().getName() + "." + exportType;
		String filePath = path + "" + fileName;
		try {
			con = getConnection();//dbConnectionService.getConnection();
			dr = buildReport();
			jp = DynamicJasperHelper.generateJasperPrint(dr,
					new ClassicLayoutManager(), con, params);
			switch (exportType) {
			case "pdf":
				ReportExporter.exportReport(jp, filePath);
				break;
			case "html":
				ReportExporter.exportReportHtml(jp, filePath);
				break;
			case "rtf":
				break;
			case "xls":
				ReportExporter.exportReportXls(jp, filePath);
				break;
			case "doc":
				ReportExporter.exportReportDoc(jp, filePath);
				break;
			default:
				ReportExporter.exportReport(jp, filePath);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				con.close();
			} catch (Exception e1) {
			}
		}
		return fileName;
	}

	protected LayoutManager getLayoutManager() {
		return new ClassicLayoutManager();
	}
	
	private boolean isString(String colType){
		boolean matched = false;
		
		stringDataTypes.add("character varying");
		stringDataTypes.add("varchar");
		stringDataTypes.add("date");
		stringDataTypes.add("timestamp");
		stringDataTypes.add("text");
		
		for(String s: stringDataTypes){
			if(s.equals(colType)){
				matched = true;
			}
		}
		return matched;
	}
	
	private boolean isNumber(String colType){
		boolean matched = false;
		numberDataTypes.add("integer");
		numberDataTypes.add("int");
		numberDataTypes.add("bigint");
		numberDataTypes.add("int4");
		numberDataTypes.add("int8");
		
		numberDataTypes.add("serial");
		numberDataTypes.add("bigserial");
		
		numberDataTypes.add("double");
		numberDataTypes.add("float4");
		numberDataTypes.add("float8");
		for(String s: numberDataTypes){
			if(s.equals(colType)){
				matched = true;
			}
		}
		return matched;
	}
	
	public Connection getConnection(){
		Connection con = null;
		DataService ds = DataService.getInstance();
		String connqry = "SELECT dc.*,dt.* FROM bi_data_source_connection ds,bi_database_connection dc,bi_database_type dt where ds.bi_database_connection_id = dc.bi_database_connection_id and dc.bi_database_type_id = dt.bi_database_type_id and ds.bi_data_source_id=" + this.dataSource;
		Map<String,String> connparams = ds.getConnectionParameters(connqry);
		DBConnectionDataService dbDataService = new DBConnectionDataService(connparams.get("driver"), connparams.get("user"), connparams.get("password"), connparams.get("url"), connparams.get("schema"));		
		dbDataService.open();
		con = dbDataService.getConnection();
		return con;
	}

	/*public static Connection createSQLConnection() throws Exception {
		DBConnectionService dbConnectionService = DBConnectionService
				.getInstance();
		Connection con = dbConnectionService.getConnection();
		Class.forName("org.postgresql.Driver");
		con = DriverManager.getConnection(
				"jdbc:postgresql://localhost:5432/bi_db", "root", "root");
		return con;
	}*/

}