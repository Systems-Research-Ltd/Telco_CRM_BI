package com.srpl.crm.ejb.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the assigned_tasks database table.
 * 
 */
@Entity
@Table(name="themes")
public class ThemesORM implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="THEMES_ID_GENERATOR", sequenceName="THEMES_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="THEMES_ID_GENERATOR")
	private Long id;

	@Column(name="title")
	private String title;
	
	@Column(name="image")
	private String image;
	
	@Column(name="is_default")
	private Boolean isDefault; 
	
	@Column(name="css_path")
	private String cssPath; 
	
	@Column(name="css")
	private String css; 

    public ThemesORM() {
    }
    
    public ThemesORM(Long id, String title, String image, Boolean isDefault) {
    	this.id = id;
    	this.title = title;
    	this.image = image;
    	this.isDefault = isDefault;
    }
    
    public ThemesORM(String title, String image, Boolean isDefault) {
    
    	this.title = title;
    	this.image = image;
    	this.isDefault = isDefault;
    }

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

	public String getCssPath() {
		return cssPath;
	}

	public void setCssPath(String cssPath) {
		this.cssPath = cssPath;
	}

	public String getCss() {
		return css;
	}

	public void setCss(String css) {
		this.css = css;
	}
	
	
}