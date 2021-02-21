package com.bitguiders.util;

import java.io.Serializable;

public class KeyValueItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int parentId;
	private String key;
	private String value;
	
	
	public KeyValueItem(int parentId){
		this.parentId=parentId;
	}
	public KeyValueItem(String key,String value){
		this.key = key;
		this.value= value;
	}
	public KeyValueItem(int parentId,String key,String value){
		this.parentId=parentId;
		this.key = key;
		this.value= value;
	}

	
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
