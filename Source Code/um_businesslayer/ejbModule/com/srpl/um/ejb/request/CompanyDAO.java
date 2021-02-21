package com.srpl.um.ejb.request;

/**
 * @author Hammad Hassan Khan
 *
 */

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import com.srpl.um.ejb.entity.GroupPermission;
import com.srpl.um.ejb.entity.MailTemplateModuleORM;
import com.srpl.um.ejb.entity.MailTemplateORM;
import com.srpl.um.ejb.entity.TEMPLATE_SECTION;
import com.srpl.um.ejb.entity.UmCompany;
import com.srpl.um.ejb.entity.UmGroup;
import com.srpl.um.ejb.entity.UmService;
import com.srpl.um.ejb.entity.UmRole;
import com.srpl.um.ejb.entity.UmUser;

/**
 * Session Bean implementation class CompanyDAO
 */
@Stateless
@LocalBean
public class CompanyDAO extends GenericDAO<UmCompany> {
	@EJB
	UserDAO userDao;
	@EJB
	GroupDAO grpDao;
	@EJB
	ServiceDAO serviceDao;
	@EJB
	MailTemplateDAO templateDao;
	@EJB
	MailTemplateModuleDAO moduleDao;

	/**
	 * Default constructor.
	 */
	public CompanyDAO() {
		// TODO Auto-generated constructor stub
		super(UmCompany.class);
	}

	// ================= List company =======================//
	public List<UmCompany> listCompanies() {
		List<UmCompany> companies = findAll();
		return companies;
	}

	// ================= Create company =======================//
	public Long createCompany(UmCompany details, UmUser companyUser) {
		UmCompany company = null;
		company = new UmCompany(details.getCompanyAddress(),
				details.getCompanyCity(), details.getCompanyCountry(),
				details.getCompanyDetails(), details.getCompanyEmail(),
				details.getCompanyName(), details.getCompanyPhone(),
				details.getCompanyState(), details.getCompanyStatus(),
				details.getCompanyLogo(), details.getCompanyZipcode(),
				details.getCompanyUrl());
		save(company);

		// Create Mail Templates
		MailTemplateORM newUserTemplate, changePasswordTemplate, forgetPasswordTemplate, campaignTemplate, caseTemplate, loyaltyTemplate = null;
		MailTemplateModuleORM UserModule, campaignModule, caseModule, loyaltyModule = null;

		UserModule = moduleDao.details(2);
		campaignModule = moduleDao.details(1);
		caseModule = moduleDao.details(3);
		loyaltyModule = moduleDao.details(4);

		newUserTemplate = new MailTemplateORM(
				company,
				"New User",
				"<div style=\"font-size: medium; text-align: center;\"><span style=\"font-size: xx-large;\"><font color=\"#000066\">Welcome</font></span></div><div style=\"font-size: 10pt;\"><br/></div><div style=\"font-size: 10pt;\">Your account has been created, following are you user credentials.</div><div style=\"font-size: 10pt;\"><br/></div><div style=\"font-size: 10pt;\"><strong>Username:</strong>&nbsp;[getUserName]</div><div style=\"font-size: 10pt;\"><strong>Password:</strong>&nbsp;[getUserPassword]</div>",
				TEMPLATE_SECTION.create_user, UserModule);
		templateDao.create(newUserTemplate);

		changePasswordTemplate = new MailTemplateORM(
				company,
				"Change Password",
				"<div style=\"text-align: center;\"><font size=\"6\" color=\"#000066\">Credentials Updated</font></div><div style=\"font-size: 10pt; font-weight: normal;\"><br/></div><div style=\"font-size: 10pt; font-weight: normal;\">Dear [getUserFname] [getUserLname]! your password has been changed. You new password is as follows.</div><div style=\"font-size: 10pt; font-weight: normal;\"><br/></div><div style=\"font-size: 10pt;\"><strong>Password:</strong> [getUserPassword]</div>",
				TEMPLATE_SECTION.change_password, UserModule);
		templateDao.create(changePasswordTemplate);

		forgetPasswordTemplate = new MailTemplateORM(
				company,
				"Forget Password",
				"<div style=\"text-align: center;\"><font size=\"6\" color=\"#000066\">Credentials Updated</font></div><div style=\"font-size: 10pt;\"><br/></div><div style=\"font-size: 10pt;\">Dear [getUserFname] [getUserLname]! your password has been reset. Your new password is as follows.</div><div style=\"font-size: 10pt;\"><br/></div><div style=\"font-size: 10pt;\"><strong>Password:</strong>&nbsp;[getUserPassword]</div>",
				TEMPLATE_SECTION.forget_password, UserModule);
		templateDao.create(forgetPasswordTemplate);

		campaignTemplate = new MailTemplateORM(
				company,
				"Campaign",
				"<div style=\"text-align: center;\"><font size=\"6\" color=\"#000066\">Marketing Campaign</font></div><div style=\"font-size: 10pt;\"><br/></div><font size=\"2\">This is campaign title: [getTitle]</font><div style=\"font-size: 10pt;\">This is description: [getDescription]</div>",
				TEMPLATE_SECTION.campaign, campaignModule);
		templateDao.create(campaignTemplate);

		caseTemplate = new MailTemplateORM(
				company,
				"Case Registration",
				"<div style=\"font-family: Arial, Verdana; font-size: 10pt; font-style: normal; font-variant: normal; line-height: normal; font-weight: normal; text-align: center;\"><font size=\"6\" color=\"#000066\">Case Registered</font></div><div style=\"font-family: Arial, Verdana; font-size: 10pt; font-style: normal; font-variant: normal; line-height: normal; font-weight: normal;\"><br/></div><div style=\"font-family: Arial, Verdana; font-size: 10pt; font-style: normal; font-variant: normal; line-height: normal; font-weight: normal;\">Dear Customer you case has been registered. The details are as follows.</div><div style=\"font-family: Arial, Verdana; font-size: 10pt; font-style: normal; font-variant: normal; line-height: normal; font-weight: normal;\"><br/></div><div><div><font face=\"Arial, Verdana\" size=\"2\"><strong>Ticket #:</strong> [getCaseTokenNumber]</font></div><div><font face=\"Arial, Verdana\" size=\"2\"><strong>Created On:</strong> [getCaseCreateDate]</font></div><div><font face=\"Arial, Verdana\" size=\"2\"><strong>Status:</strong> [getStatus]</font></div><div><font face=\"Arial, Verdana\" size=\"2\"><strong>Assigned To:</strong> [getUserName]</font></div><div><font face=\"Arial, Verdana\" size=\"2\"><br/></font></div><div><font face=\"Arial, Verdana\" size=\"2\"><strong>Detailed Description</strong></font></div><div><font face=\"Arial, Verdana\" size=\"2\">[getCustomerQuery]</font></div></div>",
				TEMPLATE_SECTION.case_registration, caseModule);
		templateDao.create(caseTemplate);

		loyaltyTemplate = new MailTemplateORM(
				company,
				"Loyalty",
				"<div style=\"text-align: center;\"><span style=\"font-size: xx-large;\"><font color=\"#000066\">Loyalty Mail</font></span></div><div style=\"font-size: 10pt;\"><br/></div><div style=\"font-size: 10pt;\">[getLoyaltyDetails]</div>",
				TEMPLATE_SECTION.loyalty, loyaltyModule);
		templateDao.create(loyaltyTemplate);

		// Get all the operations
		List<UmService> operations = null;
		try {
			// Get all the operations
			operations = serviceDao.listServices();
		} catch (Exception e) {
			System.out.println("no operations found.");
		}

		// The Role id for Account Manager is 2
		UmRole amRole = em.find(UmRole.class, 2);
		UmGroup accntGrpDetails = new UmGroup(company.getCompanyId(),
				"Account Manager Group Details", true, "Account Manager Group");
		Long accntGrpId = grpDao.createGroup(accntGrpDetails, amRole);
		// The new role is been created, now add permissions against the role.
		// Traverse through all the operations and set permission according to
		// group
		for (UmService x : operations) {
			GroupPermission gp = new GroupPermission();
			gp.setPermissionGroup(accntGrpDetails);
			gp.setPermissionService(x);
			switch (x.getServiceName()) {
			case "com.srpl.crm.web.model.um.admin.AdminBackingBean":
			case "com.srpl.crm.web.model.user.settings.ThemeSettingsBean":
			case "com.srpl.crm.web.model.user.settings.ProfileBackingBean":
			case "com.srpl.crm.web.model.FranchiseBackingBean":
			case "com.srpl.crm.web.model.um.admin.users.UserModuleDetailBackingBean":
			case "com.srpl.crm.web.model.um.admin.groups.GroupModuleDetailBackingBean":
			case "com.srpl.crm.web.model.um.admin.groups.ManageServicesBackingBean":
			case "com.srpl.crm.web.model.ImportBackingBean":
			case "com.srpl.crm.web.model.um.admin.users.UserModuleGroupsBackingBean":
			case "com.srpl.crm.web.model.user.settings.SettingsBackingBean":
			case "com.srpl.crm.web.model.user.ChangePasswordBackingBean":
			case "com.srpl.crm.web.model.dashboard.DashBean":
			case "com.srpl.crm.web.model.um.admin.groups.ServiceBackingBean":
			case "com.srpl.crm.web.model.MailTemplateBackingBean":
				gp.setPermissionCode(60L);
				break;
			default:
				gp.setPermissionCode(0L);
			}
			// save groupPermission
			em.persist(gp);
		}

		// The Role id for User is 3
		UmRole userRole = em.find(UmRole.class, 3);
		UmGroup usrGrpDetails = new UmGroup(company.getCompanyId(),
				"User Group Details", true, "User Group");
		grpDao.createGroup(usrGrpDetails, userRole);
		// The new role is been created, now add permissions against the role.
		// Traverse through all the operations and set permission according to
		// group
		for (UmService x : operations) {
			GroupPermission gp = new GroupPermission();
			gp.setPermissionGroup(usrGrpDetails);
			gp.setPermissionService(x);
			switch (x.getServiceName()) {
			case "com.srpl.bi.web.model.dashboard.DashBoardBean":
			case "com.srpl.crm.web.model.dashboard.DashBean":
			case "com.srpl.crm.web.model.user.settings.SettingsBackingBean":
			case "com.srpl.crm.web.model.user.ChangePasswordBackingBean":
			case "com.srpl.crm.web.model.user.settings.ProfileBackingBean":
			case "com.srpl.crm.web.model.user.settings.ThemeSettingsBean":
			case "com.srpl.crm.web.model.support.CaseBackingBean":
			case "com.srpl.crm.web.model.support.SupportBackingBean":
				gp.setPermissionCode(60L);
				break;
			default:
				gp.setPermissionCode(0L);
			}
			// save groupPermission
			em.persist(gp);
		}

		//TODO marketing manager
		UmGroup mktGrpDetails = new UmGroup(company.getCompanyId(),
				"Marketing Group Details", true, "Marketing");
		grpDao.createGroup(mktGrpDetails, userRole);
		// The new role is been created, now add permissions against the role.
		// Traverse through all the operations and set permission according to
		// group
		for (UmService x : operations) {
			GroupPermission gp = new GroupPermission();
			gp.setPermissionGroup(mktGrpDetails);
			gp.setPermissionService(x);
			switch (x.getServiceName()) {
			case "com.srpl.bi.web.model.dashboard.DashBoardBean":
			case "com.srpl.crm.web.model.dashboard.DashBean":
			case "com.srpl.crm.web.model.user.settings.SettingsBackingBean":
			case "com.srpl.crm.web.model.user.ChangePasswordBackingBean":
			case "com.srpl.crm.web.model.user.settings.ProfileBackingBean":
			case "com.srpl.crm.web.model.user.settings.ThemeSettingsBean":
			case "com.srpl.crm.web.model.support.CaseBackingBean":
			case "com.srpl.crm.web.model.support.SupportBackingBean":
			case "com.srpl.crm.web.model.marketing.MarketingBackingBean":
			case "com.srpl.crm.web.model.MarketingModuleCampaignBackingBean":
			case "com.srpl.crm.web.model.MarketingModuleSettingsBackingBean":
				
				gp.setPermissionCode(60L);
				break;
			default:
				gp.setPermissionCode(0L);
			}
			// save groupPermission
			em.persist(gp);
		}
		
		//TODO sales manager
		UmGroup salGrpDetails = new UmGroup(company.getCompanyId(),
				"Sales Group Details", true, "Sales");
		grpDao.createGroup(salGrpDetails, userRole);
		// The new role is been created, now add permissions against the role.
		// Traverse through all the operations and set permission according to
		// group
		for (UmService x : operations) {
			GroupPermission gp = new GroupPermission();
			gp.setPermissionGroup(salGrpDetails);
			gp.setPermissionService(x);
			switch (x.getServiceName()) {
			case "com.srpl.bi.web.model.dashboard.DashBoardBean":
			case "com.srpl.crm.web.model.dashboard.DashBean":
			case "com.srpl.crm.web.model.user.settings.SettingsBackingBean":
			case "com.srpl.crm.web.model.user.ChangePasswordBackingBean":
			case "com.srpl.crm.web.model.user.settings.ProfileBackingBean":
			case "com.srpl.crm.web.model.user.settings.ThemeSettingsBean":
			case "com.srpl.crm.web.model.support.CaseBackingBean":
			case "com.srpl.crm.web.model.support.SupportBackingBean":
				gp.setPermissionCode(60L);
				break;
			default:
				gp.setPermissionCode(0L);
			}
			// save groupPermission
			em.persist(gp);
		}
		
		//TODO order manager
		UmGroup odrGrpDetails = new UmGroup(company.getCompanyId(),
				"Order Group Details", true, "Order");
		grpDao.createGroup(odrGrpDetails, userRole);
		// The new role is been created, now add permissions against the role.
		// Traverse through all the operations and set permission according to
		// group
		for (UmService x : operations) {
			GroupPermission gp = new GroupPermission();
			gp.setPermissionGroup(odrGrpDetails);
			gp.setPermissionService(x);
			switch (x.getServiceName()) {
			case "com.srpl.bi.web.model.dashboard.DashBoardBean":
			case "com.srpl.crm.web.model.dashboard.DashBean":
			case "com.srpl.crm.web.model.user.settings.SettingsBackingBean":
			case "com.srpl.crm.web.model.user.ChangePasswordBackingBean":
			case "com.srpl.crm.web.model.user.settings.ProfileBackingBean":
			case "com.srpl.crm.web.model.user.settings.ThemeSettingsBean":
			case "com.srpl.crm.web.model.support.CaseBackingBean":
			case "com.srpl.crm.web.model.support.SupportBackingBean":
				gp.setPermissionCode(60L);
				break;
			default:
				gp.setPermissionCode(0L);
			}
			// save groupPermission
			em.persist(gp);
		}
		
		//TODO customer manager
		UmGroup ctmGrpDetails = new UmGroup(company.getCompanyId(),
				"Customer Manager Details", true, "Customer Manager");
		grpDao.createGroup(ctmGrpDetails, userRole);
		// The new role is been created, now add permissions against the role.
		// Traverse through all the operations and set permission according to
		// group
		for (UmService x : operations) {
			GroupPermission gp = new GroupPermission();
			gp.setPermissionGroup(ctmGrpDetails);
			gp.setPermissionService(x);
			switch (x.getServiceName()) {
			case "com.srpl.bi.web.model.dashboard.DashBoardBean":
			case "com.srpl.crm.web.model.dashboard.DashBean":
			case "com.srpl.crm.web.model.user.settings.SettingsBackingBean":
			case "com.srpl.crm.web.model.user.ChangePasswordBackingBean":
			case "com.srpl.crm.web.model.user.settings.ProfileBackingBean":
			case "com.srpl.crm.web.model.user.settings.ThemeSettingsBean":
			case "com.srpl.crm.web.model.support.CaseBackingBean":
			case "com.srpl.crm.web.model.support.SupportBackingBean":
				gp.setPermissionCode(60L);
				break;
			default:
				gp.setPermissionCode(0L);
			}
			// save groupPermission
			em.persist(gp);
		}
		
		//TODO support manager
		UmGroup sprtGrpDetails = new UmGroup(company.getCompanyId(),
				"Support Group Details", true, "Support");
		grpDao.createGroup(sprtGrpDetails, userRole);
		// The new role is been created, now add permissions against the role.
		// Traverse through all the operations and set permission according to
		// group
		for (UmService x : operations) {
			GroupPermission gp = new GroupPermission();
			gp.setPermissionGroup(sprtGrpDetails);
			gp.setPermissionService(x);
			switch (x.getServiceName()) {
			case "com.srpl.bi.web.model.dashboard.DashBoardBean":
			case "com.srpl.crm.web.model.dashboard.DashBean":
			case "com.srpl.crm.web.model.user.settings.SettingsBackingBean":
			case "com.srpl.crm.web.model.user.ChangePasswordBackingBean":
			case "com.srpl.crm.web.model.user.settings.ProfileBackingBean":
			case "com.srpl.crm.web.model.user.settings.ThemeSettingsBean":
			case "com.srpl.crm.web.model.support.CaseBackingBean":
			case "com.srpl.crm.web.model.support.SupportBackingBean":
				gp.setPermissionCode(60L);
				break;
			default:
				gp.setPermissionCode(0L);
			}
			// save groupPermission
			em.persist(gp);
		}
		

		// The Role id for Customer is 4
		UmRole customerRole = em.find(UmRole.class, 4);
		UmGroup customerGrpDetails = new UmGroup(company.getCompanyId(),
				"Customer Group Details", true, "Customer Group");
		grpDao.createGroup(customerGrpDetails, customerRole);
		// The new role is been created, now add permissions against the role.
		// Traverse through all the operations and set permission according to
		// group
		for (UmService x : operations) {
			GroupPermission gp = new GroupPermission();
			gp.setPermissionGroup(customerGrpDetails);
			gp.setPermissionService(x);
			switch (x.getServiceName()) {
			case "com.srpl.bi.web.model.dashboard.DashBoardBean":
			case "com.srpl.crm.web.model.dashboard.DashBean":
			case "com.srpl.crm.web.model.user.settings.SettingsBackingBean":
			case "com.srpl.crm.web.model.user.ChangePasswordBackingBean":
			case "com.srpl.crm.web.model.user.settings.ProfileBackingBean":
			case "com.srpl.crm.web.model.user.settings.ThemeSettingsBean":
			case "com.srpl.crm.web.model.support.CaseBackingBean":
			case "com.srpl.crm.web.model.support.SupportBackingBean":
				gp.setPermissionCode(60L);
				break;
			default:
				gp.setPermissionCode(0L);
			}
			// save groupPermission
			em.persist(gp);
		}

		new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		// companyUser.setUmUserGroups(umUserGroups);
		companyUser.setUserCity(details.getCompanyCity());
		companyUser.setUserCompany(company.getCompanyId());
		companyUser.setUserCountry(details.getCompanyCountry());
		companyUser.setUserPhone(details.getCompanyPhone());
		companyUser.setUserState(details.getCompanyState());
		companyUser.setUserStatus(details.getCompanyStatus());
		companyUser.setUserZipcode(details.getCompanyZipcode());
		companyUser.setUserAddedon(new Timestamp(date.getTime()));
		companyUser.setUserJobtitle("Account Manager");

		if (companyUser.getUserEmail() == null) {
			companyUser.setUserEmail(companyUser.getUserEmail());
		}
		if (companyUser.getUserAddress() == null) {
			companyUser.setUserAddress(details.getCompanyAddress());
		}

		// companyUser.setUserPassword("admin123");

		// UmUser userDetails = new UmUser(company.getCompanyEmail(),
		// "admin123", company.getCompanyName(), company.getCompanyName(),
		// null,0,0,0,null, company.getCompanyEmail(),null,null,null,null,false,
		// company.getCompanyId(), new Timestamp(date.getTime()),true);
		Long userId = userDao.createUser(companyUser, true);

		// ===== end add user ====== //

		// ====== add um_user_group ==========//

		userDao.addUserToGroup(userId, accntGrpId);

		// ====== add um_user_group ==========//

		Long companyId = company.getCompanyId();
		return companyId;
	}

	// ================= Update Company =======================//
	public Long updateCompany(UmCompany company) {
		System.out.println(company.getCompanyId());
		update(company);
		return company.getCompanyId();
	}

	// ================= Delete Company =======================//
	public void deleteCompany(Long companyId) {
		delete(companyId);
	}

	// ================= Details of Company =======================//
	public UmCompany companyDetails(Long companyId) {
		UmCompany company = find(companyId);
		return company;
	}

	public void updateCompanyLogo(Long companyId, String companyLogo) {
		try {
			Query query = em
					.createQuery("Update UmCompany company set company.companyLogo = :companyLogo  where company.companyId = :companyId");
			query.setParameter("companyId", companyId);
			query.setParameter("companyLogo", companyLogo);
			int updated = query.executeUpdate();
		} catch (Exception e) {
			System.out.println("Exception in CompanyDAO updateCompanyLogo()");
		}
	}

	public String getCompanyLogo(Long companyId) {
		try {
			if (companyId == null || companyId == 0) {
				return "company logo not available";
			} else {
				UmCompany company = em
						.createQuery(
								"from UmCompany company where company.companyId = :companyId",
								UmCompany.class)
						.setParameter("companyId", companyId).getSingleResult();
				return company.getCompanyLogo();
			}
		} catch (Exception e) {
			return "company logo not available";
		}
	}

}
