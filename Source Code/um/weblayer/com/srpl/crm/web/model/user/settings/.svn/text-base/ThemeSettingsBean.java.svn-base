package com.srpl.crm.web.model.user.settings;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;

import com.bitguiders.util.jsf.JSFBeanInterface;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;

@ManagedBean(name = "themesettings")
public class ThemeSettingsBean extends JSFBeanSupport implements JSFBeanInterface, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String themeId;
	private Long userId;
	
	public ThemeSettingsBean() {
		setCurrentAction(WebConstants.ACTION_SECURITY,this.getClass());
	}

	
	public ThemeSettingsBean(String themeId, Long userId) {
		this.themeId = themeId;
		this.userId = userId;
	}
	
    public ThemeSettingsBean(int id, String themeId, Long userId) {
    	this.id = id;
    	this.themeId = themeId;
    	this.userId = userId;
    }
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getThemeId() {
		return themeId;
	}


	public void setThemeId(String themeId) {
		this.themeId = themeId;
	}


	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}


	@Override
	public String actionListener() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<?> getList() {
		// TODO Auto-generated method stub
		return null;
	}

}