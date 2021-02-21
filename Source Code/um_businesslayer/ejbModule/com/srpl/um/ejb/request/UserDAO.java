package com.srpl.um.ejb.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import com.srpl.um.common.utils.Utils;
import com.srpl.um.ejb.entity.MailTemplateORM;
import com.srpl.um.ejb.entity.TEMPLATE_SECTION;
import com.srpl.um.ejb.entity.UmGroup;
import com.srpl.um.ejb.entity.UmUser;
import com.srpl.um.ejb.entity.UmUserGroup;
import com.srpl.um.ejb.exceptions.UserNotFoundException;

/**
 * Session Bean implementation class UserDAO
 */
// @DeclareRoles({"AccountManager","User"})
@Stateless
@LocalBean
public class UserDAO extends GenericDAO<UmUser> {

	@EJB
	MailTemplateDAO templateDao;

	/**
	 * Default constructor.
	 */
	public UserDAO() {
		// TODO Auto-generated constructor stub
		super(UmUser.class);
	}

	// Updated with company id by maryam
	public List<UmUser> list(Long companyId) throws UserNotFoundException {
		List<UmUser> users = null;
		users = em
				.createQuery(
						"SELECT u FROM UmUser u WHERE u.userCompany = :cid",
						UmUser.class).setParameter("cid", companyId)
				.getResultList();
		return users;
	}

	public List<UmUser> listLeadUsers(Long companyId) {
		List<UmUser> users = null;
		users = em
				.createQuery(
						"SELECT u FROM UmUser u WHERE u.userCompany = :cid",
						UmUser.class).setParameter("cid", companyId)
				.getResultList();
		return users;
	}

	public List<UmUser> listUsers(Long companyId, String filterby,
			String filterfor) throws UserNotFoundException {
		List<UmUser> users = null;
		String qry = "SELECT u FROM UmUser u WHERE u.userCompany = :cid";
		if (!filterby.equals("")) {
			String lowerCaseQuery = filterfor.toLowerCase();
			switch (filterby) {
			case "uid":
				qry += " AND u.userId = :param";
				break;
			case "fname":
				qry += " AND lower(u.userFname) like '" + lowerCaseQuery + "%'";
				break;
			case "lname":
				qry += " AND lower(u.userLname) like '" + lowerCaseQuery + "%'";
				break;
			case "uname":
				qry += " AND lower(u.userName) like '" + lowerCaseQuery + "%'";
				break;
			case "phone":
				qry += " AND lower(u.userPhone) = '" + lowerCaseQuery + "'";
				break;
			case "jobtitle":
				qry += " AND lower(u.userJobtitle) like '" + lowerCaseQuery
						+ "%'";
				break;
			case "email":
				qry += " AND lower(u.userEmail) like '%" + lowerCaseQuery
						+ "%'";
				break;
			default:
				break;
			}
			users = em.createQuery(qry, UmUser.class)
					.setParameter("cid", companyId).getResultList();
		} else {
			users = em.createQuery(qry, UmUser.class)
					.setParameter("cid", companyId).getResultList();
		}
		return users;
	}

	public List<UmUser> listAllUsers() throws UserNotFoundException {
		List<UmUser> users = findAll();
		System.out.println("list size" + users.size() + users.get(0));
		if (users.size() == 0) {
			throw new UserNotFoundException("No User Record Found");
		}
		return users;
	}

	// user onlinestatus Updated...
	public void updateUserOnlineStatus(Long userId, boolean status) {
		UmUser user = find(userId);
		user.setIsOnline(status);
		update(user);
	}

	public void updateProfilePic(Long userId, String picture) {
		UmUser user = find(userId);
		user.setUserPicture(picture);
		update(user);
	}

	public List<UmUser> listUsersByCompany(Long companyId) {
		List<UmUser> users = null;
		users = em
				.createQuery(
						"SELECT u FROM UmUser u WHERE u.userCompany = :cid",
						UmUser.class).setParameter("cid", companyId)
				.getResultList();
		return users;
	}

	public List<UmGroup> listUserGroups(Long uId, Boolean include,
			Long companyId) {
		List<UmGroup> groups = null;
		if (include) {
			groups = getUserGroups(uId, companyId);
		} else {
			List<Long> groupIds = getGroupIds(uId, companyId);
			if (groupIds.size() != 0) {
				groups = em
						.createQuery(
								"SELECT g FROM UmGroup g where g.groupId NOT IN (:glist) AND g.companyId = :cid",
								UmGroup.class).setParameter("glist", groupIds)
						.setParameter("cid", companyId).getResultList();
			} else {
				groups = em.createQuery("from UmGroup", UmGroup.class)
						.getResultList();
			}
		}
		return groups;
	}

	public Long getUserId(String userName) /* throws UserNotFoundException */{
		UmUser usr = em
				.createQuery("from UmUser where userName = :uname",
						UmUser.class).setParameter("uname", userName)
				.getSingleResult();
		Long userId = usr.getUserId();
		return userId;
	}

	// Hammad
	public Long createUser(UmUser user) throws Exception {
		try {
			long uid = 0;
			//user.setUserName(user.getUserName().toLowerCase());
			
			save(user);
			
			UmGroup grp = em
					.createQuery(
							"from UmGroup where groupTitle = :title and companyId = :cid",
							UmGroup.class).setParameter("title", "User Group")
					.setParameter("cid", user.getUserCompany())
					.getSingleResult();
			addUserToGroup(user.getUserId(), grp.getGroupId());
			em.flush();
			uid = user.getUserId();

			StringBuilder body = new StringBuilder();
			body.append("<p>Welcome " + user.getUserFname() + " "
					+ user.getUserLname()
					+ "! your user credentials are as follows.</p>");
			body.append("<table><tr><td> <b>Username</b> </td><td>"
					+ user.getUserName() + "</td></tr>");
			body.append("<tr><td> <b>Password</b> </td><td>"
					+ user.getUserPassword() + "</td></tr></table>");

			String message = "";
			try {
				MailTemplateORM template = templateDao.details(user.getUserCompany(), TEMPLATE_SECTION.create_user);
				message = templateDao
						.getMessageWithMailTemplate(template, user);
			} catch (Exception e) {
				e.printStackTrace();
				message = body.toString();
			}

			Utils.sendMail(user.getUserEmail(), "User Credentials", message,
					true);

			return uid;
		}  catch (Exception e){
			throw e;
		}
	}
	// Hammad
	public Long createCustomer(UmUser details) {
		// User this one
		try {
			long uid = 0;
		save(details);
	
			UmGroup grp = em
					.createQuery(
							"from UmGroup where groupTitle = :title and companyId = :cid",
							UmGroup.class)
					.setParameter("title", "Customer Group")
					.setParameter("cid", details.getUserCompany())
					.getSingleResult();
			addUserToGroup(details.getUserId(), grp.getGroupId());
			em.flush();
			uid = details.getUserId();
			
			StringBuilder body = new StringBuilder();
			body.append("<p>Welcome " + details.getUserFname() + " "
					+ details.getUserLname()
					+ "! your user credentials are as follows.</p>");
			body.append("<table><tr><td> <b>Username</b> </td><td>"
					+ details.getUserName() + "</td></tr>");
			body.append("<tr><td> <b>Password</b> </td><td>"
					+ details.getUserPassword() + "</td></tr></table>");

			String message = "";
			try {
				MailTemplateORM template = templateDao.details(details.getUserCompany(), TEMPLATE_SECTION.create_user);
				message = templateDao.getMessageWithMailTemplate(template,
						details);
			} catch (Exception e) {
				e.printStackTrace();
				message = body.toString();
			}

			Utils.sendMail(details.getUserEmail(), "User Credentials", message,
					true);
			return uid;
		} catch (Exception e) {
			System.out.println("Group not found.");
			throw e;
		}
		
	}

	// Hammad
	public Long createUser(UmUser details, Boolean Company) {
		UmUser user = null;
		user = new UmUser(details.getUserName(), details.getUserPassword(),
				details.getUserFname(), details.getUserLname(),
				details.getUserAddress(), details.getUserCountry(),
				details.getUserState(), details.getUserCity(),
				details.getUserZipcode(), details.getUserEmail(),
				details.getUserPhone(), details.getUserJobtitle(),
				details.getUserReportsto(), details.getUserPicture(),
				details.getIsFranchiseUser(), details.getUserCompany(),
				details.getUserAddedon(), details.getUserStatus(),
				details.getIsOnline(), details.getIsUserCustomer());
		save(user);

		StringBuilder body = new StringBuilder();
		body.append("<p>Welcome " + details.getUserFname() + " "
				+ details.getUserLname()
				+ "! your user credentials are as follows.</p>");
		body.append("<table><tr><td> <b>Username</b> </td><td>"
				+ details.getUserName() + "</td></tr>");
		body.append("<tr><td> <b>Password</b> </td><td>"
				+ details.getUserPassword() + "</td></tr></table>");

		String message = "";
		try {
			MailTemplateORM template = templateDao.details(user.getUserCompany(), TEMPLATE_SECTION.create_user);
			message = templateDao.getMessageWithMailTemplate(template, details);
		} catch (Exception e) {
			e.printStackTrace();
			message = body.toString();
		}

		Utils.sendMail(details.getUserEmail(), "User Credentials", message,
				true);

		return user.getUserId();
	}

	public void updateUser(UmUser details) {
		// System.out.println("user address in dao is "+details.getUserAddress());
		UmUser user = find(details.getUserId());
		user.setUserFname(details.getUserFname());
		user.setUserLname(details.getUserLname());
		user.setUserEmail(details.getUserEmail());
		user.setUserAddress(details.getUserAddress());
		user.setUserCountry(details.getUserCountry());
		user.setUserState(details.getUserState());
		user.setUserCity(details.getUserCity());
		user.setUserZipcode(details.getUserZipcode());
		user.setUserPhone(details.getUserPhone());
		user.setUserJobtitle(details.getUserJobtitle());
		user.setUserReportsto(details.getUserReportsto());
		user.setUserPicture(details.getUserPicture());
		user.setUserStatus(details.getUserStatus());
		user.setIsFranchiseUser(details.getIsFranchiseUser());

		update(user);
	}

	public void updateUserProfile(UmUser details) {
		UmUser user = find(details.getUserId());
		user.setUserFname(details.getUserFname());
		user.setUserLname(details.getUserLname());
		user.setUserEmail(details.getUserEmail());
		user.setUserAddress(details.getUserAddress());
		user.setUserCountry(details.getUserCountry());
		user.setUserState(details.getUserState());
		user.setUserCity(details.getUserCity());
		user.setUserZipcode(details.getUserZipcode());
		user.setUserPhone(details.getUserPhone());
		user.setUserJobtitle(details.getUserJobtitle());
		user.setUserReportsto(details.getUserReportsto());
		user.setUserId(details.getUserId());

		update(user);
	}

	public void deleteUser(Long userId) {
		UmUser user = find(userId);
		List<Long> groupIds = getGroupIds(userId, user.getUserCompany());
		for (Long gId : groupIds) {
			UmGroup group = em.find(UmGroup.class, gId);
			UmUserGroup ugrp = em
					.createQuery(
							"from UmUserGroup where umUser = :usr and umGroup = :grp",
							UmUserGroup.class).setParameter("usr", user)
					.setParameter("grp", group).getSingleResult();
			em.remove(ugrp);
		}
		delete(userId);
	}

	/*
	 * @RolesAllowed("AccountManager") public void deleteUser(Long userId)
	 * throws UserNotFoundException { if (userId == null) { throw new
	 * UserNotFoundException("Invalid User Id"); } delete(userId); }
	 */

	// @RolesAllowed("AccountManager")
	public void addUserToGroup(long uId, long gId) /*
													 * throws
													 * UserNotFoundException,
													 * GroupNotFoundException
													 */{
		/*
		 * if(uId == 0){ throw new UserNotFoundException("Invalid User Id"); }
		 * if(gId == 0){ throw new GroupNotFoundException("Invalid Group Id"); }
		 */
		UmUser user = find(uId);
		UmGroup group = em.find(UmGroup.class, gId);
		UmUserGroup usrgrp = new UmUserGroup(user, group);
		em.persist(usrgrp);
		// user.addGroup(group);

	}

	// @RolesAllowed("AccountManager")
	public void removeUserFromGroup(long uId, long gId) {
		UmUser user = find(uId);
		UmGroup group = em.find(UmGroup.class, gId);
		UmUserGroup grp = em
				.createQuery(
						"from UmUserGroup where umUser = :usr and umGroup = :grp",
						UmUserGroup.class).setParameter("usr", user)
				.setParameter("grp", group).getSingleResult();
		em.remove(grp);
		// user.removeGroup(group);
	}

	public List<UmUser> getGroupUsers(Long gId, Boolean include) {
		List<UmUser> users = null;
		if (include) {
			users = em
					.createQuery(
							"SELECT u FROM UmUser u JOIN u.umUserGroups ug JOIN ug.umGroup g where g.groupId = :gId",
							UmUser.class).setParameter("gId", gId)
					.getResultList();
		} else {
			List<Long> userIds = getUserIds(gId);
			if (userIds.size() != 0) {
				users = em
						.createQuery(
								"SELECT u FROM UmUser u where u.userId NOT IN (:ulist)",
								UmUser.class).setParameter("ulist", userIds)
						.getResultList();
			} else {
				users = em.createQuery("from UmUser", UmUser.class)
						.getResultList();
			}
		}
		return users;
	}

	public List<UmUser> getGroupUsers2(Long gId, Boolean include,
			Long company_id) {
		List<UmUser> users = null;
		// UmGroup group = em.find(UmGroup.class, gId);
		if (include) {
			users = em
					.createQuery(
							"SELECT u FROM UmUser u JOIN u.umUserGroups ug JOIN ug.umGroup g where g.groupId = :gId and u.userCompany = :cId",
							UmUser.class).setParameter("gId", gId)
					.setParameter("cId", company_id).getResultList();
		} else {
			/*
			 * List<Long> userIds = getUserIds(gId); if (userIds.size() != 0) {
			 * userIds.add(0L); }
			 */
			users = em
					.createQuery(
							"SELECT u FROM UmUser u where u.userId NOT IN (SELECT u.userId FROM UmUser u JOIN u.umUserGroups ug JOIN ug.umGroup g where g.groupId = :gid) and u.userCompany = :cId",
							UmUser.class).setParameter("gid", gId)
					.setParameter("cId", company_id)
					.setParameter("cId", company_id).getResultList();

		}
		return users;
	}

	public List<Long> getGroupIds(int uId, long companyId) {
		List<UmGroup> groups = null;
		groups = getUserGroups(uId, companyId);
		return copyGroupIdsToList(groups);
	}

	public List<Long> getUserIds(long gId) {
		List<UmUser> users = null;
		users = getGroupUsers(gId);
		return copyUserIdsToList(users);
	}

	public List<UmGroup> getUserGroups(long uId, long companyId) {
		List<UmGroup> groups = null;
		groups = em
				.createQuery(
						"SELECT g FROM UmGroup g JOIN g.umUserGroups ug JOIN ug.umUser u where u.userId = :uid and g.companyId = :cid",
						UmGroup.class).setParameter("uid", uId)
				.setParameter("cid", companyId).getResultList();
		return groups;
	}

	public List<UmUser> getGroupUsers(long gId) {
		List<UmUser> users = null;
		users = em
				.createQuery(
						"SELECT u FROM UmUser u JOIN u.umUserGroups ug JOIN ug.umGroup g where g.groupId = :gid",
						UmUser.class).setParameter("gid", gId).getResultList();
		return users;
	}

	public List<UmUser> getImportUsers(Timestamp stamp) {
		List<UmUser> users = null;
		users = em
				.createQuery(
						"SELECT u FROM UmUser u where u.userAddedon = :gid",
						UmUser.class).setParameter("gid", stamp)
				.getResultList();
		return users;
	}

	public List<Long> getGroupIds(Long uId, Long companyId) /*
															 * throws
															 * UserNotFoundException
															 */{
		List<UmGroup> groups = null;
		groups = getUserGroups(uId, companyId);
		return copyGroupIdsToList(groups);
	}

	private List<Long> copyGroupIdsToList(List<UmGroup> groups) {
		List<Long> groupsIdList = new ArrayList<Long>();
		Iterator<UmGroup> i = groups.iterator();
		while (i.hasNext()) {
			UmGroup grp = i.next();
			groupsIdList.add(grp.getGroupId());
		}
		return groupsIdList;
	}

	private List<Long> copyUserIdsToList(List<UmUser> users) {
		List<Long> groupsIdList = new ArrayList<Long>();
		Iterator<UmUser> i = users.iterator();
		while (i.hasNext()) {
			UmUser usr = i.next();
			groupsIdList.add(usr.getUserId());
		}
		return groupsIdList;
	}

	public UmUser umUserDetails(Long userId) throws UserNotFoundException {
		UmUser user = null;
		if (userId == null) {
			throw new UserNotFoundException("Invalid User Id");
		}
		user = find(userId);
		return user;
	}

	public UmUser umMappedUserDetails(Long mappedId)
			throws UserNotFoundException {
		return (UmUser) em
				.createQuery("from UmUser where mappedId = :mid", UmUser.class)
				.setParameter("mid", mappedId).getSingleResult();
	}

	public UmUser getAccountUser(long companyId, String grp) {
		return (UmUser) em
				.createQuery(
						"SELECT u FROM UmUser u JOIN u.umUserGroups ug JOIN ug.umGroup g where g.groupTitle = :grp and g.companyId = :cId",
						UmUser.class).setParameter("grp", grp)
				.setParameter("cId", companyId).getSingleResult();
	}

	public UmUser companyAccountManager(Long companyId)
			throws UserNotFoundException {
		UmUser user = null;
		List<UmUser> userList = null;
		try {
			userList = em
					.createQuery(
							"from UmUser where userCompany = :cid ORDER BY userId",
							UmUser.class).setParameter("cid", companyId)
					.getResultList();
			user = userList.get(0);
			System.out.println(userList.size());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("User not found. for company id: " + companyId);
		}
		return user;
	}

	public static boolean compareArrays(String[] arr1, String[] arr2) {
		boolean findcol = false;
		for (int i = 0; i < arr1.length; i++) {
			for (int j = 0; j < arr2.length; j++) {
				if (arr1[i].equals(arr2[j])) {
					findcol = true;
				}
			}
			if (findcol == false) {
				return true;
			}
		}
		return false;
	}

	public List<UmUser> mapUsers(InputStream in) {
		java.util.Date date = new java.util.Date();
		List<UmUser> detailsList = new ArrayList<UmUser>();
		BufferedReader input = new BufferedReader(new InputStreamReader(in));
		String line = null;
		try {
			input.readLine();
			while ((line = input.readLine()) != null) {
				String[] rec = line.split(",");
				UmUser usr = new UmUser();
				usr.setUserName(rec[0]);
				usr.setUserPassword(rec[1]);
				usr.setUserFname(rec[2]);
				usr.setUserLname(rec[3]);
				usr.setUserEmail(rec[4]);
				usr.setUserPhone(rec[5]);
				usr.setUserJobtitle(rec[6]);
				usr.setUserCompany(1L);
				usr.setUserAddedon(new Timestamp(date.getTime()));
				usr.setIsFranchiseUser(false);
				usr.setUserStatus(true);
				detailsList.add(usr);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return detailsList;
	}

	/*
	 * public boolean importUsers(List<UmUserDetails> users) { java.util.Date
	 * date = new java.util.Date(); /* String[] reqlist =
	 * {"user_name","user_password","user_fname","user_lname"
	 * ,"user_email","user_phone","user_jobtitle"}; Iterator<UmUserDetails> itr
	 * = users.iterator(); UmUserDetails usrheader = (UmUserDetails) itr.next();
	 * String[] sublist = {}; if(compareArrays(reqlist, sublist)) return false;
	 * -/ Iterator<UmUserDetails> itr = users.iterator(); while (itr.hasNext())
	 * { UmUserDetails rec = itr.next(); UmUser usr = new UmUser();
	 * usr.setUserName(rec.getUserName());
	 * usr.setUserPassword(rec.getUserPassword());
	 * usr.setUserFname(rec.getUserFname());
	 * usr.setUserLname(rec.getUserLname());
	 * usr.setUserEmail(rec.getUserEmail());
	 * usr.setUserPhone(rec.getUserPhone());
	 * usr.setUserJobtitle(rec.getUserJobtitle()); usr.setUserAddedon(new
	 * Timestamp(date.getTime())); usr.setIsFranchiseUser(false);
	 * usr.setUserStatus(true); save(usr); } return true; }
	 */

	// @RolesAllowed({"AccountManager","User"})
	public boolean importUsers(List<UmUser> users, Boolean isUser) {
		Iterator<UmUser> itr = users.iterator();
		while (itr.hasNext()) {
			UmUser usr = itr.next();
			/*save(usr);
			UmGroup grp = em
					.createQuery(
							"from UmGroup where groupTitle = :title and companyId = :cid",
							UmGroup.class).setParameter("title", "User Group")
					.setParameter("cid", usr.getUserCompany())
					.getSingleResult();
			addUserToGroup(usr.getUserId(), grp.getGroupId());*/
			try {
				createUser(usr);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}

	public boolean importUsers(List<UmUser> users) {
		java.util.Date date = new java.util.Date();
		/*
		 * String[] reqlist =
		 * {"user_name","user_password","user_fname","user_lname"
		 * ,"user_email","user_phone","user_jobtitle"}; Iterator<UmUserDetails>
		 * itr = users.iterator(); UmUserDetails usrheader = (UmUserDetails)
		 * itr.next(); String[] sublist = {}; if(compareArrays(reqlist,
		 * sublist)) return false;
		 */
		Iterator<UmUser> itr = users.iterator();
		while (itr.hasNext()) {
			UmUser rec = itr.next();
			UmUser usr = new UmUser();
			usr.setUserName(rec.getUserName());
			usr.setUserPassword(rec.getUserPassword());
			usr.setUserFname(rec.getUserFname());
			usr.setUserLname(rec.getUserLname());
			usr.setUserEmail(rec.getUserEmail());
			usr.setUserPhone(rec.getUserPhone());
			usr.setUserJobtitle(rec.getUserJobtitle());
			usr.setUserAddedon(new Timestamp(date.getTime()));
			usr.setIsFranchiseUser(false);
			usr.setUserStatus(true);
			save(usr);
		}
		return true;
	}

	public Set<Attribute<? super UmUser, ?>> listTableCols() {
		// TODO temorarly commented by Abdul Kareem,
		// selTable class was missing on my end
		Metamodel metamodel = em.getMetamodel();
		EntityType<UmUser> type = metamodel.entity(UmUser.class);
		Set<Attribute<? super UmUser, ?>> cols = type.getAttributes();
		return cols;

		// return null;
	}

	public Boolean userNameExists(String userName) /*
													 * throws
													 * UserNotFoundException
													 */{
		List<UmUser> usrs = em
				.createQuery("from UmUser where userName = :uname",
						UmUser.class).setParameter("uname", userName)
				.getResultList();
		return (usrs.size() > 0);
	}
	
	public Boolean userNameExists(String userName, long companyId) {
		List<UmUser> usrs = em.createQuery("from UmUser where userName = :uname and userCompany = :ucompany",UmUser.class).setParameter("uname", userName).setParameter("ucompany", companyId).getResultList();
		return (usrs.size() > 0);
	}

	public Boolean userIDExists(Long userId) /* throws UserNotFoundException */{
		List<UmUser> usrs = em
				.createQuery("from UmUser where userId = :uid", UmUser.class)
				.setParameter("uid", userId).getResultList();
		return (usrs.size() > 0);
	}

	public Boolean mappedIDExists(Long mappedId) /* throws UserNotFoundException */{
		List<UmUser> usrs = em
				.createQuery("from UmUser where mappedId = :mid", UmUser.class)
				.setParameter("mid", mappedId).getResultList();
		return (usrs.size() > 0);
	}

}
