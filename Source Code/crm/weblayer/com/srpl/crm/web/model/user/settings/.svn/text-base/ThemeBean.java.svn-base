package com.srpl.crm.web.model.user.settings;
import java.io.Serializable;
import java.security.Principal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.srpl.um.ejb.entity.UsersThemeORM;
import com.srpl.crm.web.common.SessionDataBean;
import com.srpl.crm.web.controller.BeanFactory;
import com.srpl.um.ejb.entity.ThemesORM;
import com.srpl.um.ejb.request.UmUtilsDAO;
import com.srpl.um.ejb.request.UserDAO;

@ManagedBean
@SessionScoped
public class ThemeBean implements  Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<ThemesORM> themes;
	private ThemesORM userTheme;
	
	@EJB UmUtilsDAO utilsDAO;
	@EJB UserDAO userDao;	
	
	public ThemeBean() {	
		
	}	
	
	@PostConstruct
	public void themeInit(){	
		themes = utilsDAO.themesList();
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		Principal principal = request.getUserPrincipal();
		if(principal!=null){
		Long uid = userDao.getUserId(principal.getName());
		userTheme = utilsDAO.userTheme(uid);
		}else{
			userTheme = null;
		}
		System.out.println("User theme in post construct"+userTheme.getTitle());
	}
	
	public ThemesORM getUserTheme() {
		return userTheme;
	}

	public void setUserTheme(ThemesORM userTheme) {
		this.userTheme = userTheme;
	}
	
	public List<ThemesORM> getThemes() {
		return themes;
	}
	public void setThemes(List<ThemesORM> themes) {
		this.themes = themes;
	}
	
	public String changeUserTheme(ThemesORM theme) {
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		Long themeId = utilsDAO.themeId(session.getUserId());
		if(themeId == null) {
			UsersThemeORM details = new UsersThemeORM(theme.getId(), session.getUserId());
			utilsDAO.createUsersTheme(details);			
		}else{
			utilsDAO.updateUsersTheme(themeId, theme.getId());
		}
		userTheme = utilsDAO.userTheme(session.getUserId());
		return null;		
	}
	
}