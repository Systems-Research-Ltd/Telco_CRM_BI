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

package com.srpl.crm.web.model.report;

import java.awt.Color;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import ar.com.fdvs.dj.core.DJConstants;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.core.layout.LayoutManager;
import ar.com.fdvs.dj.domain.AutoText;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.builders.StyleBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;

import com.srpl.crm.web.common.DBConnection;

public class DynamicJasperReport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JasperPrint jp;
	protected JasperReport jr;
	protected DynamicReport dr;
	private List<String> stringDataTypes = new ArrayList<String>();
	private List<String> numberDataTypes = new ArrayList<String>();

	protected Map params = new HashMap();
	private String type;
	private String xAxis;
	private String yAxis;
	private String pieChartCol;

	public String getPieChartCol() {
		return pieChartCol;
	}

	public void setPieChartCol(String pieChartCol) {
		this.pieChartCol = pieChartCol;
	}

	public Map getParams() {
		return params;
	}

	public DynamicReport buildReport(String title, String description,
			String summary, String query, ArrayList<String[]> arr,
			String reportType, String reportTypeColumns) throws Exception {
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
		subtitleStyle.setHorizontalAlign(HorizontalAlign.LEFT);

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
	
		for (int i = 0; i < arr.size(); i++) {
			int index = arr.get(i)[1].indexOf(".");
			System.out.println("\n\n" + arr.get(i)[1]);
			String col = null;
			String colTitle = arr.get(i)[0];
			String colType = arr.get(i)[2];
			String width = arr.get(i)[3];

			if (index == -1) {
				col = arr.get(i)[1];
				System.out.println("col in if is " + col);
			} else {
				try {
					String temp[] = arr.get(i)[1].split("\\.");
					col = temp[1];
				} catch (Exception e) {
					System.out.println("Exception while splitting values"
							+ arr.get(i)[1]);
				}
			}
			System.out.println("Col type is col " + colType);

			if (isString(colType)) {
				//System.out.println("String type col ----" + colType);
				AbstractColumn ac = ColumnBuilder.getNew()
						.setColumnProperty(col, String.class.getName())
						.setTitle(colTitle).setWidth(new Integer(85))
						.setStyle(detailStyle).setHeaderStyle(headerStyle)
						.build();
				drb.addColumn(ac);
				xAxis.add(ac);
			} else if (isNumber(colType)) {
				//System.out.println("Int type col ----" + colType);
				AbstractColumn ac = ColumnBuilder.getNew()
						.setColumnProperty(col, Long.class.getName())
						.setTitle(colTitle).setWidth(new Integer(85))
						.setStyle(detailStyle).setHeaderStyle(headerStyle)
						.build();
				drb.addColumn(ac);
				yAxis.add(ac);
			} else {
				//System.out.println("else");
				AbstractColumn ac = ColumnBuilder.getNew()
						.setColumnProperty(col, String.class.getName())
						.setTitle(colTitle).setWidth(new Integer(85))
						.setStyle(detailStyle).setHeaderStyle(headerStyle)
						.build();
				drb.addColumn(ac);
			}

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

		
		Style atStyle2 = new StyleBuilder(true).setFont(
				new Font(9, Font._FONT_TIMES_NEW_ROMAN, false, true, false))
				.build();
		
		/**
		 * Adding many autotexts in the same position (header/footer and
		 * aligment) makes them to be one on top of the other
		 */

		/**
		 * Adding many autotexts in the same position (header/footer and
		 * aligment) makes them to be one on top of the other
		 */
		drb.addAutoText("\u00a9 All Rights Reserved. SRPL",
				AutoText.POSITION_FOOTER, AutoText.ALIGNMENT_RIGHT, 200);
		drb.addAutoText(AutoText.AUTOTEXT_PAGE_X_OF_Y,
				AutoText.POSITION_FOOTER, AutoText.ALIGMENT_LEFT, 40, 30,
				atStyle2);

		if (xAxis.size() > 0 && yAxis.size() > 0) {
			if (getType() != null) {
				switch (getType()) {
				case "bar":
					Bar3DChartBuilder bc = new Bar3DChartBuilder();
					bc.setTitle(title);
					bc.setxAxisTitle("x axis");
					bc.setyAxisTitle("y axis");
					bc.create3DBarChart(xAxis, yAxis, drb);
					break;
				case "line":
					LineChartBuilder lc = new LineChartBuilder();
					lc.setTitle(title);
					lc.setxAxisTitle("x axis");
					lc.setyAxisTitle("y axis");
					lc.create3DBarChart(xAxis, yAxis, drb);
					break;
				case "area":
					AreaChartBuilder ac = new AreaChartBuilder();
					ac.setTitle(title);
					ac.setxAxisTitle("x axis");
					ac.setyAxisTitle("y axis");
					ac.create3DBarChart(xAxis, yAxis, drb);
					break;

				}
			}
		}
		DynamicReport dr = drb.build();

		return dr;
	}

	public static void main(String[] args) throws Exception {
		DynamicJasperReport test = new DynamicJasperReport();
		test.setType("pie");
		test.setxAxis("ord.order_id");
		test.setyAxis("ord.created_by");
		test.setPieChartCol("ord.order_id");
		ArrayList<String[]> arr = new ArrayList<String[]>();
		String summary = "Oder id: 1122; Title: DSL; Label3: Value 3; Label4: Value4";
		arr.add(new String[] { "Order Id", "ord.order_id", "30" });
		arr.add(new String[] { "Title", "ord.order_title", "30" });
		arr.add(new String[] { "Status", "ord.assigned_to", "30" });
		arr.add(new String[] { "Order Creation", "ord.created_by", "30" });
		String name = test
				.generateReport(
						"test report title",
						"test report description",
						summary,
						"SELECT ord.order_id, ord.order_title, ord.assigned_to, ord.created_by FROM crm.orders ord order by ord.order_id",
						arr, "", "", "group", "ord.order_title");
		JasperViewer.viewReport(test.jp);
		// String jrxml = JRXmlWriter.writeReport(test.jr, "UTF-8");
		// JasperDesignViewer.viewReportDesign(DynamicJasperHelper
		// .generateJasperReport(test.dr, test.getLayoutManager(),
		// new HashMap()));
	}

	public String generateReport(String title, String description,
			String summary, String query, ArrayList<String[]> arr, String path,
			String exportType, String reportType, String reportTypeColumns)
			throws Exception {
		System.out.println("Report type columns are " + reportTypeColumns);
		Connection con = null;

		String fileName = this.getClass().getName() + "." + exportType;
		String filePath = path + "" + fileName;
		try {
			dr = buildReport(title, description, summary, query, arr,
					reportType, reportTypeColumns);
			con = DBConnection.getConnection();
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getxAxis() {
		return xAxis;
	}

	public void setxAxis(String xAxis) {
		this.xAxis = xAxis;
	}

	public String getyAxis() {
		return yAxis;
	}

	public void setyAxis(String yAxis) {
		this.yAxis = yAxis;
	}

	protected LayoutManager getLayoutManager() {
		return new ClassicLayoutManager();
	}

	private boolean isString(String colType) {
		boolean matched = false;

		stringDataTypes.add("character varying");
		stringDataTypes.add("varchar");
		stringDataTypes.add("date");
		stringDataTypes.add("timestamp");

		for (String s : stringDataTypes) {
			if (s.equals(colType)) {
				matched = true;
			}
		}
		return matched;
	}

	private boolean isNumber(String colType) {
		boolean matched = false;
		numberDataTypes.add("integer");
		numberDataTypes.add("int");
		numberDataTypes.add("bigint");
		numberDataTypes.add("int4");
		numberDataTypes.add("int8");

		numberDataTypes.add("serial");
		numberDataTypes.add("bigserial");

		numberDataTypes.add("double");
		numberDataTypes.add("float8");
		for (String s : numberDataTypes) {
			if (s.equals(colType)) {
				matched = true;
			}
		}
		return matched;
	}
	/*
	 * public static Connection createSQLConnection() throws Exception {
	 * Connection con = null; Class.forName("org.postgresql.Driver"); con =
	 * DriverManager.getConnection( "jdbc:postgresql://localhost:5432/srpl_db",
	 * "root", "root");
	 * 
	 * return con; }
	 */

}