package com.srpl.um.ejb.request;

import java.util.List;
import java.util.Random;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.srpl.um.common.utils.Utils;
import com.srpl.um.ejb.entity.GroupPermission;
import com.srpl.um.ejb.entity.MailTemplateORM;
import com.srpl.um.ejb.entity.TEMPLATE_SECTION;
import com.srpl.um.ejb.entity.UmUser;

/**
 * Session Bean implementation class AuthenticationDAO
 */
@Stateless
@LocalBean
public class AuthenticationDAO extends UmPersistence {

	@EJB
	MailTemplateDAO templateDao;
	@EJB
	MailTemplateModuleDAO moduleDao;
	/**
	 * Default constructor.
	 */
	public AuthenticationDAO() {
		// TODO Auto-generated constructor stub

	}
	

	public boolean userExists(String username) {
		List<UmUser> usr = em
				.createQuery("from UmUser where userName = :uname",
						UmUser.class).setParameter("uname", username)
				.getResultList();
		return !((usr.size() == 0) ? true : false);
	}

	public boolean emailExists(String email) {
		List<UmUser> usr = em
				.createQuery("from UmUser where userEmail = :email",
						UmUser.class).setParameter("email", email)
				.getResultList();
		return !((usr.size() == 0) ? true : false);
	}

	private Boolean passExists(Long userid, String password) {
		UmUser usr = (UmUser) em.find(UmUser.class, userid);
		return ((usr.getUserPassword().equals(password)) ? true : false);
	}

	public boolean resetPassword(Long userid, String oldpass, String newpass) {
		if (passExists(userid, oldpass)) {
			UmUser usr = (UmUser) em.find(UmUser.class, userid);
			if (usr.getUserName().isEmpty() == false) {
				usr.setUserPassword(newpass);
				em.merge(usr);
				
				try{
					String message = "";
					try{
						MailTemplateORM template = templateDao.details(usr.getUserCompany(), TEMPLATE_SECTION.change_password);
						message = templateDao.getMessageWithMailTemplate(template, usr);
					}catch(Exception e){
						e.printStackTrace();
						message = "Dear "
								+ usr.getUserFname()
								+ ",\n\nYour Password has been changed. Your new Password is as follows:\n\n"
								+ newpass;
					}

					Utils.sendMail(usr.getUserEmail(), "User Credentials",
							message, true);
				}catch (Exception e) {
					e.printStackTrace();
				}
				
				return true;
			} else
				return false;
		} else
			return false;
	}

	public boolean forgotPassword(String email) {
		if (emailExists(email)) {
			UmUser usr = em
					.createQuery("from UmUser where userEmail = :email",
							UmUser.class).setParameter("email", email)
					.getSingleResult();
			String str = new String("ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
			StringBuffer sb = new StringBuffer();
			Random rand = new Random();
			int te = 0;
			for (int i = 1; i <= 6; i++) {
				te = rand.nextInt(36);
				sb.append(str.charAt(te));
			}
			String pswd = sb.toString();
			usr.setUserPassword(pswd);
			em.merge(usr);

			try {
				String message = "";
				try{
					MailTemplateORM template = templateDao.details(usr.getUserCompany(), TEMPLATE_SECTION.forget_password);
					message = templateDao.getMessageWithMailTemplate(template, usr);
				}catch(Exception e){
					e.printStackTrace();
					message = "Dear "
							+ usr.getUserFname()
							+ ",\n\nYour Password has been reset. Your new Password is as follows:\n\n"
							+ pswd;
				}

				Utils.sendMail(usr.getUserEmail(), "User Credentials",
						message, true);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		} else
			return false;
	}

	public void setPermissions(Long pId, Long code) {
		System.out.println("PID" + pId);
		System.out.println("CODE" + code);
		GroupPermission per = em.find(GroupPermission.class, pId);
		per.setPermissionCode(code);
		em.merge(per);
	}

	/*
	 * there is no place for this function in this file Hammad Here
	 * 
	 * public void getResults(){ List<OrderORM> orders = em.createQuery(
	 * "select * from OrderORM where EXTRACT(YEAR FROM orderDate) = :year;"
	 * ,OrderORM.class).setParameter("year",2012).getResultList();
	 * System.out.println("ORDER SIZE : "+orders.size()); }
	 */
}