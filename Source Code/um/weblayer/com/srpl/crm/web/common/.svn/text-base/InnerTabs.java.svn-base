package com.srpl.crm.web.common;

import java.io.Serializable;

public class InnerTabs implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InnerTabs(){
		
	}
	
	public InnerTabs(String t, String p){
		this.setTitle(t);
		this.setPath(p);
	}
	
	public InnerTabs(String t, String p, String c){
		this.setTitle(t);
		this.setPath(p);
		this.setContainer(c);
	}
	
	private String title;
	private String path;
	private String container;
	public String getContainer() {
		return container;
	}

	public void setContainer(String container) {
		this.container = container;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "~!title:" + title + "~!path:" + path + "~!container:" + container;
	}
	
	public void setInnerTab(String innerTabString) {
		String parts[] = innerTabString.split("~!");
		for(String p:parts){
			String furtherParts[] = p.split(":");
			switch(furtherParts[0]){
			case "title":
				title = furtherParts[1];
				break;
			case "path":
				path = furtherParts[1];
				break;
			case "container":
				container = furtherParts[1];
				break;
			}
		}
	}
	
}
