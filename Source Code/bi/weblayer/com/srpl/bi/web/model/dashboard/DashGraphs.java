package com.srpl.bi.web.model.dashboard;

import java.io.Serializable;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

@Deprecated
public class DashGraphs implements Serializable
{
	private CartesianChartModel lineModel;  
	private PieChartModel pieModel;  
  
    
  
    
    public DashGraphs() {  
        createCategoryModel();  
        createPieModel();
    }  
  
    private void createPieModel() {  
        pieModel = new PieChartModel();  
  
        pieModel.set("Internet", 540);  
        pieModel.set("Telephone", 325);  
        pieModel.set("VAS", 702);  
        pieModel.set("CableTV", 421);  
    }   
    private void createCategoryModel() {  
        lineModel = new CartesianChartModel();  
  
        ChartSeries sales = new ChartSeries();  
        sales.setLabel("Sales");    
        sales.set("2004", 10);  
        sales.set("2005", 100);  
        sales.set("2006", 44);  
        sales.set("2007", 150);  
        sales.set("2008", 92);  
  
        lineModel.addSeries(sales);  
    }  

    public CartesianChartModel getLineModel() {  
        return lineModel;  
    } 
    public PieChartModel getPieModel() {  
        return pieModel;  
    }  
  
}
