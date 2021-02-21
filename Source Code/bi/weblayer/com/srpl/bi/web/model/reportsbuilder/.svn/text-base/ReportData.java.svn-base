package com.srpl.bi.web.model.reportsbuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.srpl.bi.web.model.reportsbuilder.DataPaletteBackingBean.ColumnModel;

// POJO Class for Table Class
public class ReportData implements Serializable
{
	// Table Columns
	List<ColumnModel> dndColumns=new ArrayList<ColumnModel>();
	// Table Row Data
	List<Map<String, String>> rows=new ArrayList<Map<String,String>>();
	
	int selectedGraph=1;
	
	public int getSelectedGraph() {
		return selectedGraph;
	}
	public void setSelectedGraph(int selectedGraph) {
		this.selectedGraph = selectedGraph;
	}
	//Getters & Setters
	public List<ColumnModel> getDndColumns() {
		return dndColumns;
	}
	public void setDndColumns(List<ColumnModel> dndColumns) {
		this.dndColumns = dndColumns;
	}
	public List<Map<String, String>> getRows() {
		return rows;
	}
	public void setRows(List<Map<String, String>> rows) {
		this.rows = rows;
	}

}
