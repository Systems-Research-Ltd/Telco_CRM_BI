package com.srpl.um.ejb.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.srpl.um.ejb.request.UmPersistence;


/**
 * The persistent class for the user_preferences database table.
 * 
 */
@Entity
@Table(name="user_preferences" , schema=UmPersistence.schema)
public class UserPreference implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USER_PREFERENCES_USERPREFERENCESID_GENERATOR", sequenceName="USER_PREFERENCES_USER_PREFERENCES_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USER_PREFERENCES_USERPREFERENCESID_GENERATOR")
	@Column(name="user_preferences_id")
	private Long userPreferencesId;

	@Column(name="show_popup")
	private Boolean showPopup;

	@Column(name="theme_id")
	private Integer themeId;

	@Column(name="user_id")
	private Long userId;

    public UserPreference() {
    }

	public Long getUserPreferencesId() {
		return this.userPreferencesId;
	}

	public void setUserPreferencesId(Long userPreferencesId) {
		this.userPreferencesId = userPreferencesId;
	}

	public Boolean getShowPopup() {
		return this.showPopup;
	}

	public void setShowPopup(Boolean showPopup) {
		this.showPopup = showPopup;
	}

	public Integer getThemeId() {
		return this.themeId;
	}

	public void setThemeId(Integer themeId) {
		this.themeId = themeId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}