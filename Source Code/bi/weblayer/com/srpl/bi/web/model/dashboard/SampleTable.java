package com.srpl.bi.web.model.dashboard;

import java.io.Serializable;

public class SampleTable implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String id;
	String product;
	String sales;
	String region;
	public SampleTable(String id, String region, String product,String sales)
	{
		this.id=id;
		this.product=product;
		this.sales=sales;
		this.region=region;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getSales() {
		return sales;
	}
	public void setSales(String sales) {
		this.sales = sales;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	
	

}
