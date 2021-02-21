package com.bitguiders.util;

import java.util.Locale;
import java.util.MissingResourceException;

import javax.faces.context.FacesContext;

public class ResourceBundle {
	
	java.util.ResourceBundle resourceBundle =null;
	private static ResourceBundle resourseBundle = new ResourceBundle();
	private String resourcePath = "resources.sid";
	private Locale locale = Locale.US;
	private ResourceBundle(){
		loadBundle();
	}
	private void loadBundle(){
		resourceBundle =  java.util.ResourceBundle.getBundle(resourcePath, locale);
	}
	public static ResourceBundle getInstance(){
		return resourseBundle;
	}
	
	public void setResourcePath(String resourcePath){
		this.resourcePath = resourcePath;
		loadBundle();
	}
	public void setLocale(Locale locale){
		this.locale = locale;
		loadBundle();
	}
	public void refresh(){
		loadBundle();
	}
	public String getPropertyValue(String key){
		try{
			return resourceBundle.getString(key);
		}catch(MissingResourceException ex){
			System.out.println(ex.getMessage());
			return null;
		}
	}
	
	public static void main(String[] arg){
		ResourceBundle rb = ResourceBundle.getInstance();
		System.out.println(rb.getPropertyValue("topMenu.um.users"));
	}
}
