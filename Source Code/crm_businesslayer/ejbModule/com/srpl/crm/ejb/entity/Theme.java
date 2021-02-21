package com.srpl.crm.ejb.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the themes database table.
 * 
 */
@Entity
@Table(name="themes")
public class Theme implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="THEMES_THEMEID_GENERATOR", sequenceName="THEMES_THEME_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="THEMES_THEMEID_GENERATOR")
	@Column(name="theme_id")
	private Integer themeId;

	@Column(name="is_default")
	private Boolean isDefault;

	@Column(name="theme_icon")
	private String themeIcon;

	@Column(name="theme_title")
	private String themeTitle;
	
	@Column(name="theme_library")
	private String themeLibrary;

    public Theme() {
    }

	public Integer getThemeId() {
		return this.themeId;
	}

	public void setThemeId(Integer themeId) {
		this.themeId = themeId;
	}

	public Boolean getIsDefault() {
		return this.isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

	public String getThemeIcon() {
		return this.themeIcon;
	}

	public void setThemeIcon(String themeIcon) {
		this.themeIcon = themeIcon;
	}

	public String getThemeTitle() {
		return this.themeTitle;
	}

	public void setThemeTitle(String themeTitle) {
		this.themeTitle = themeTitle;
	}

	public String getThemeLibrary() {
		return themeLibrary;
	}

	public void setThemeLibrary(String themeLibrary) {
		this.themeLibrary = themeLibrary;
	}	

}