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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.design.JRDesignChart;
import net.sf.jasperreports.engine.design.JRDesignGroup;
import net.sf.jasperreports.engine.design.JRDesignVariable;
import ar.com.fdvs.dj.domain.DynamicJasperDesign;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.StringExpression;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.builders.GroupBuilder;
import ar.com.fdvs.dj.domain.chart.DJChart;
import ar.com.fdvs.dj.domain.chart.DJChartOptions;
import ar.com.fdvs.dj.domain.chart.builder.DJAreaChartBuilder;
import ar.com.fdvs.dj.domain.chart.plot.DJAxisFormat;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.entities.DJGroup;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import ar.com.fdvs.dj.domain.entities.columns.PropertyColumn;

public class AreaChartBuilder {
	private DynamicReportBuilder drb;
	private JRDesignChart chart;
	private String title;
	private String xAxisTitle;
	private String yAxisTitle;

	protected void setUp() throws Exception {
		drb = new DynamicReportBuilder();

		AbstractColumn columnState = ColumnBuilder.getNew()
				.setColumnProperty("state", String.class.getName())
				.setTitle("State").setWidth(new Integer(85)).build();
		AbstractColumn columnBranch = ColumnBuilder.getNew()
				.setColumnProperty("branch", String.class.getName())
				.setTitle("Branch").setWidth(new Integer(85)).build();
		AbstractColumn columnaQuantity = ColumnBuilder.getNew()
				.setColumnProperty("quantity", Long.class.getName())
				.setTitle("Quantity").setWidth(new Integer(80)).build();
		AbstractColumn columnAmount = ColumnBuilder.getNew()
				.setColumnProperty("amount", Float.class.getName())
				.setTitle("Amount").setWidth(new Integer(90)).build();

		drb.addColumn(columnState);
		drb.addColumn(columnBranch);
		drb.addColumn(columnaQuantity);
		drb.addColumn(columnAmount);

		GroupBuilder gb1 = new GroupBuilder();
		DJGroup g1 = gb1.setCriteriaColumn((PropertyColumn) columnState)
		// .addFooterVariable(columnAmount, DJCalculation.SUM)
		// .addFooterVariable(columnaQuantity, DJCalculation.SUM)
		// .addVariable("group_state_name", columnState,
		// DJCalculation.FIRST)
		// .setGroupLayout(GroupLayout.VALUE_IN_HEADER_WITH_HEADERS)
				.build();
		/*
		 * GroupBuilder gb2 = new GroupBuilder(); DJGroup g2 =
		 * gb2.setCriteriaColumn((PropertyColumn) columnBranch)
		 * .setGroupLayout(GroupLayout.VALUE_FOR_EACH) .build();
		 */

		drb.addGroup(g1);
		// drb.addGroup(g2);

		drb.setUseFullPageWidth(true);
		List<AbstractColumn> aa = new ArrayList<AbstractColumn>();
		aa.add(columnaQuantity);
		aa.add(columnAmount);
		//create3DBarChart(columnBranch, aa, drb);

	}

	public void create3DBarChart(List<AbstractColumn> xAxis, List<AbstractColumn> yAxis,
			DynamicReportBuilder drb) {
		DJAxisFormat categoryAxisFormat = new DJAxisFormat(getxAxisTitle());
		categoryAxisFormat.setLabelFont(Font.ARIAL_SMALL);
		categoryAxisFormat.setLabelColor(Color.DARK_GRAY);
		categoryAxisFormat.setTickLabelFont(Font.ARIAL_SMALL);
		categoryAxisFormat.setTickLabelColor(Color.DARK_GRAY);
		categoryAxisFormat.setTickLabelMask("");
		categoryAxisFormat.setLineColor(Color.DARK_GRAY);

		DJAxisFormat valueAxisFormat = new DJAxisFormat(getyAxisTitle());
		valueAxisFormat.setLabelFont(Font.ARIAL_SMALL);
		valueAxisFormat.setLabelColor(Color.DARK_GRAY);
		valueAxisFormat.setTickLabelFont(Font.ARIAL_SMALL);
		valueAxisFormat.setTickLabelColor(Color.DARK_GRAY);
		valueAxisFormat.setTickLabelMask("#,##0.0");
		valueAxisFormat.setLineColor(Color.DARK_GRAY);
		DJAreaChartBuilder cb = new DJAreaChartBuilder();

		// chart
		cb.setX(20)
				.setY(10)
				.setWidth(500)
				.setHeight(250)
				.setCentered(false)
				.setBackColor(Color.LIGHT_GRAY)
				.setShowLegend(true)
				.setPosition(DJChartOptions.POSITION_FOOTER)
				.setTitle(new StringExpression() {
					public Object evaluate(Map fields, Map variables,
							Map parameters) {
						return variables.get("group_state_name");
					}
				})
				.setTitleColor(Color.DARK_GRAY)
				.setTitleFont(Font.ARIAL_BIG_BOLD)
				.setSubtitle(getTitle())
				.setSubtitleColor(Color.DARK_GRAY)
				.setSubtitleFont(Font.COURIER_NEW_BIG_BOLD)
				.setLegendColor(Color.DARK_GRAY)
				.setLegendFont(Font.COURIER_NEW_MEDIUM_BOLD)
				.setLegendBackgroundColor(Color.WHITE)
				.setLegendPosition(DJChartOptions.EDGE_BOTTOM)
				.setTitlePosition(DJChartOptions.EDGE_TOP)
				.setLineStyle(DJChartOptions.LINE_STYLE_DOTTED)
				.setLineWidth(1)
				.setLineColor(Color.DARK_GRAY)
				.setPadding(5)
				// dataset
				.setCategory((PropertyColumn) xAxis.get(0))
				// .addSerie(aa.get(0))
				// .addSerie(aa.get(1))
				// plot
				.setCategoryAxisFormat(categoryAxisFormat)
				.setValueAxisFormat(valueAxisFormat);

		for (AbstractColumn ya : yAxis) {
			cb.addSerie(ya);
		}

		DJChart djChart = cb.build();
		drb.addChart(djChart);
		HashMap vars = new HashMap();
		for (AbstractColumn a : yAxis) {
			vars.put(a, new JRDesignVariable());
		}
		JRDesignGroup group = new JRDesignGroup();
		chart = djChart.transform(new DynamicJasperDesign(), "", group, group,
				vars, 0);
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the xAxisTitle
	 */
	public String getxAxisTitle() {
		return xAxisTitle;
	}

	/**
	 * @param xAxisTitle
	 *            the xAxisTitle to set
	 */
	public void setxAxisTitle(String xAxisTitle) {
		this.xAxisTitle = xAxisTitle;
	}

	/**
	 * @return the yAxisTitle
	 */
	public String getyAxisTitle() {
		return yAxisTitle;
	}

	/**
	 * @param yAxisTitle
	 *            the yAxisTitle to set
	 */
	public void setyAxisTitle(String yAxisTitle) {
		this.yAxisTitle = yAxisTitle;
	}

	public DynamicReport buildReport() throws Exception {
		return drb.build();
	}

	public static void main(String[] args) throws Exception {
		AreaChartBuilder test = new AreaChartBuilder();
		test.setTitle("Test Report");
		test.setxAxisTitle("Test x axis title");
		test.setyAxisTitle("Test y axis title");
		test.setUp();
		// test.testReport();
		// JasperViewer.viewReport(test.jp);
	}
}
