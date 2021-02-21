package com.srpl.crm.ejb.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the assigned_tasks database table.
 * 
 */
@Entity
@Table(name="users_theme")
public class UsersThemeORM implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USERS_THEME_ID_GENERATOR", sequenceName="USERS_THEME_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USERS_THEME_ID_GENERATOR")
	private Long id;

	@Column(name="theme_id")
	private Long themeId;

	@Column(name="user_id")
	private Long userId;

    public UsersThemeORM() {
    }
    
    public UsersThemeORM(Long id, Long themeId, Long userId) {
    	this.id = id;
    	this.themeId = themeId;
    	this.userId = userId;
    }
    
    public UsersThemeORM(Long themeId, Long userId) {

    	this.themeId = themeId;
    	this.userId = userId;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getThemeId() {
		return themeId;
	}

	public void setThemeId(Long themeId) {
		this.themeId = themeId;
	}
    
}
