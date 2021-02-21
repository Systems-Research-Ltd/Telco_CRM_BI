package com.srpl.bi.web.model.reportsbuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.event.ActionEvent;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

import com.bitguiders.util.jsf.JSFBeanSupport;
import com.srpl.bi.web.controller.BeanFactory;
import com.srpl.bi.web.model.reportsbuilder.DataPaletteBackingBean.ColumnModel;

// POJO class for Graph

public class ReportGraph  implements Serializable 
{

	// max min values for Y axis
	int minY=0;
	int maxY=1000;
	boolean chartvalid=false;
	//List of dimensions & Measures
	List<Integer> measures=new ArrayList<Integer>();
	List<Integer> dimensions=new ArrayList<Integer>();
	List<String> xLabels=new ArrayList<String>();
	
	String selectedGraph = "5";
	CartesianChartModel chart=new CartesianChartModel();
	
	// Table Columns
	List<ColumnModel> dndColumns;
	// Table Row Data
	List<Map<String, String>> rows;
	
	public void clear()
	{
		measures.clear();
		dimensions.clear();
		xLabels.clear();
		chart.clear();
		minY=0;
		maxY=0;
		selectedGraph = "5";
		chartvalid=false;
	}
	
	
	// creates Chart based on rows and dndcolumns datastructures
	public void populateGraph(List<ColumnModel> dndColumns, List<Map<String, String>> rows)
	{		
		if (dndColumns == null || rows == null) {
			clear();
			return;
		}
		if (dndColumns.size() == 0 || rows.size() == 0) {
			clear();
			return;
		}
			clear();
			
			this.dndColumns=dndColumns;
			this.rows=rows;
		
			seperateColumns();
			calcuateMinMax();
			createXLabels();
			
			//this.chart = new CartesianChartModel();
 
			if(this.measures.size()==0 || this.dimensions.size()==0)
			{
				this.chartvalid=false;
				return;
			}
			for (Integer measure :this.getMeasures()) 
			{ 
				int count = 0;
				ColumnModel measureObj=this.dndColumns.get(measure);
				ChartSeries cs = new ChartSeries();

				for (Map<String, String> row : this.rows) 
				{
					cs.set(xLabels.get(count), Float.parseFloat(row.get(measureObj.getProperty()))); 
					count++; 
				}
				cs.setLabel(measureObj.getHeader());
				this.chart.addSeries(cs);
			}
			this.chartvalid=true;
			System.out.println("Graph Populated");
		}
		
		
		
		private void seperateColumns()
		{
			int i=0;
			for (ColumnModel column : dndColumns) 
			{
				if(column.getProperty().equals("serialNo"))
					continue;
				String colType=column.getPropertyType();
				// if Column is NUMERIC then put it in measures list else add it in dimensions List
				if(checkColType(colType)==true)
				{
					measures.add(i);
				}
				else
				{
					dimensions.add(i);
				}
				i++;
			}
		}

		// Calculate Min & Max Value for Graphs Y axis
		private void calcuateMinMax()
		{
			maxY=0;
			minY=0;
			boolean firstTime=true;
			for(Integer column:measures)
			{
				ColumnModel col = dndColumns.get(column);
				for(Map<String,String> rdata:rows)
				{
					Double colVal=Double.parseDouble(rdata.get(col.getProperty()));
					//int colVal=fval.intValue();
					if(firstTime)
					{
						minY=colVal.intValue();
						firstTime=false;
					}
					maxY=Math.max(maxY, colVal.intValue());
					minY=Math.min(minY, colVal.intValue());
					
				}
			}
			// increase & decrease range by 15%
			maxY=Math.round((float)maxY*(float)1.08);
			minY=Math.round(((float)minY*(float)0.92)); 
		}
		
		public static double roundToDecimals(double d, int c)  
		{   
		   int temp = (int)(d * Math.pow(10 , c));  
		   return ((double)temp)/Math.pow(10 , c);  
		}
		
		private void createXLabels()
		{
			for(Map<String,String> row:rows)
			{
				StringBuilder label=new StringBuilder();
				for(Integer dim:dimensions)
				{
					ColumnModel dimension=dndColumns.get(dim.intValue());
//					String colproperty=dimension.getProperty();
					if(!label.toString().isEmpty())
						label.append("/");
					String rowValue=row.get(dimension.getProperty());
					label.append(rowValue);
				}
				xLabels.add(label.toString());
			}
		}
		
		private void createCategoryModel() {
			
	/*		  categoryModel = new CartesianChartModel();
			  String[] years = {"2008","2009", "2010", "2011", "2012"}; 
			  for (ColumnModel column :dndColumns) 
			  { 
				  int count = 0; 
				  ChartSeries cs = new ChartSeries();
				  cs.setLabel(column.property);
				  for (Map<String, String> row : rows) 
				  {
					  cs.set(years[count], Integer.parseInt(row.get(column.property))); 
					  count++; 
				  }
				  categoryModel.addSeries(cs);
			  }	*/	 
		}
		
		// return zero if string or 1 if numeric
		private boolean checkColType(String type)
		{
			switch(type.toLowerCase().trim())
			{
			case "integer":
				return true;
			case "decimal":
				return true;
			case "bigint":
				return true;
			case "serial":
				return true;
			case "bigserial":
				return true;
			case "varchar":
				return false;
			case "text":
				return false;
			case "boolean":
				return true;
			case "timestamp":
				return false;
			case "date":
				return false;
			case "time":
				return false;
			case "real":
				return true;
			case "float4":
				return true;	
			case "character varying":
				return false;
			case "timestamp without time zone":
				return false;	
				
			}
			return true;
		}
	
	//GETTERS & SETTERS
	public int getMinY() {
		return minY;
	}
	public void setMinY(int minY) {
		this.minY = minY;
	}
	public int getMaxY() {
		return maxY;
	}
	public void setMaxY(int maxY) {
		this.maxY = maxY;
	}
	public List<Integer> getMeasures() {
		return measures;
	}
	public void setMeasures(List<Integer> measures) {
		this.measures = measures;
	}
	public List<Integer> getDimensions() {
		return dimensions;
	}
	public void setDimensions(List<Integer> dimensions) {
		this.dimensions = dimensions;
	}
	public List<String> getxLabels() {
		return xLabels;
	}
	public void setxLabels(List<String> xLabels) {
		this.xLabels = xLabels;
	}
	public CartesianChartModel getChart() {
		return chart;
	}
	public void setChart(CartesianChartModel chart) {
		this.chart = chart;
	}
	public boolean isChartvalid() {
		return chartvalid;
	}
	public void setChartvalid(boolean chartvalid) {
		this.chartvalid = chartvalid;
	}
	public String getSelectedGraph() {
		return selectedGraph;
	}
	public void setSelectedGraph(String selectedGraph) {
		this.selectedGraph = selectedGraph;
	}
	
}
