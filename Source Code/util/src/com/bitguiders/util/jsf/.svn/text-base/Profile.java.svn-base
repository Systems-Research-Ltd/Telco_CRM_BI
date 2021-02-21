package com.bitguiders.util.jsf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.bitguiders.util.ResourceBundle;
import com.bitguiders.util.security.Permission;
import com.srpl.um.ejb.entity.GroupPermission;
import com.srpl.um.ejb.entity.UmGroup;
import com.srpl.um.ejb.entity.UmService;
import com.srpl.um.ejb.entity.UmUser;
import com.srpl.um.ejb.entity.UmUserGroup;
import com.srpl.um.ejb.exceptions.UserNotFoundException;
import com.srpl.um.ejb.request.GroupDAO;
import com.srpl.um.ejb.request.ServiceDAO;
import com.srpl.um.ejb.request.UserDAO;

@ManagedBean(name = "profileSecurity", eager = true)
@SessionScoped
public class Profile {

	// private static Profile profile = new Profile();
	@EJB
	UserDAO userDAO;
	@EJB
	GroupDAO groupDAO;
	@EJB
	ServiceDAO serviceDAO;
	// @EJB SecurityDAO securityDAO;

	Hashtable<String, Integer> currentlyAccessedServices;

	private Permission permission;
	private String help;

	public Profile() {
		currentlyAccessedServices = new Hashtable<String, Integer>();
		permission = new Permission();
		permission.setOperationList(ResourceBundle.getInstance()
				.getPropertyValue("secure.operations"));
	}

	public static Profile getInstance() {
		// return this.profile;
		Profile currentInstance = null;
		FacesContext fc = FacesContext.getCurrentInstance();
		Application app = fc.getApplication();
		ValueExpression expression = app.getExpressionFactory()
				.createValueExpression(fc.getELContext(),
						String.format("#{%s}", "profileSecurity"), Object.class);
		currentInstance = (Profile) expression.getValue(fc.getELContext());
		return currentInstance;
	}

	public Permission getPermission() {
		return this.permission;
	}

	public void setDAOInstances(UserDAO userDAO, GroupDAO groupDAO,
			ServiceDAO serviceDAO) {

		System.out.println("----------------before--1----" + this.userDAO);
		if (this.userDAO == null) {
			this.userDAO = userDAO;
			this.groupDAO = groupDAO;
			this.serviceDAO = serviceDAO;
			// this.securityDAO = securityDAO;
			System.out.println("----------------setter--1----"
					+ this.userDAO.getUserId(getUserName()));
			System.out.println("----------------setter------" + this.userDAO);
		}
	}

	public String getUserName() {
		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		Principal principal = request.getUserPrincipal();
		return principal.getName();
	}

	public void setPermissions(Class classInstance) {
		// get Service from services list
		String serviceName = classInstance.getName();
		// default Service permission code
		int permissionCode = -1;

		if (currentlyAccessedServices.containsKey(serviceName)) {
			// currentlyAccessedServices
			permissionCode = currentlyAccessedServices.get(serviceName);
			this.help = "<b>Help:</b> <br>"
					+ ResourceBundle.getInstance()
							.getPropertyValue(serviceName);
			System.out.println("----already Accessed Service = " + serviceName);
		} else {

			System.out.println("----currently Accessed Service = "
					+ serviceName);

			// on the basis of user id get All Groups which belongs to this user
			UmUser user = null;
			try {
				user = userDAO.umUserDetails(userDAO.getUserId(getUserName()));
			} catch (UserNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			UmGroup userGroup = null;
			// get user groups
			groupsLoop: for (UmUserGroup group : user.getUmUserGroups()) {
				Long groupId = group.getUmGroup().getGroupId();

				userGroup = group.getUmGroup();
				// get group permissions
				List<GroupPermission> featuresPermissionsList = groupDAO
						.groupPermissions(groupId);
				for (GroupPermission service : featuresPermissionsList) {
					// search service
					if (service.getPermissionService().getServiceName()
							.equals(serviceName)) {

						// permissionCode =
						// Integer.parseInt(service.getPermissionCode() + "");
						// TODO Hammad here
						// kareem bhai please comment the above line permission
						// = Integer.parseInt(service.getPermissionCode() + "");
						// and uncomment following code.

						if (permissionCode == -1) { // if permission code is
													// default, reset it to zero
													// so that bitwise or works
													// fine.
							permissionCode = 0;
						}
						int currentGroupPermission = Integer.parseInt(service
								.getPermissionCode() + "");
						// bitwise or the currentGroupPermission with
						// permissionCode
						// so that the two values add up logically not
						// arithmetically.
						permissionCode |= currentGroupPermission;

						this.help = "<br>"
								+ service.getPermissionService()
										.getServiceTitle()
								+ "</b><br>"
								+ service.getPermissionService()
										.getServiceDescription();
						System.out.println("Service name "
								+ service.getPermissionService()
										.getServiceName()
								+ "  Service title "
								+ service.getPermissionService()
										.getServiceName());
						// break groupsLoop;

						// TODO Hammad Here
						// Kareem bhai, the value of permissionCode is not final
						// yet.
						// The current value just reflects the permissionCode in
						// this particular group
						// some other group might have more rights for this user
						// so we should add the permissionCode in HashTable when
						// the loop iterations are finished
						// please comment the following line
						// currentlyAccessedServices.put(serviceName,
						// permissionCode);

						// The following comments are not mine (Hammad)
						// if some service found again in another group handle
						// that logic here
						// and pick the heighest permissionCode 1248 16 32
						/*
						 * if(currentlyAccessedServices.containsKey(serviceName))
						 * { if(currentlyAccessedServices.get(serviceName)<16){
						 * int tmpPermissionCode =
						 * currentlyAccessedServices.get(
						 * serviceName)+permissionCode;
						 * currentlyAccessedServices.remove(serviceName);
						 * currentlyAccessedServices
						 * .put(serviceName,tmpPermissionCode ); }else
						 * if(currentlyAccessedServices
						 * .get(serviceName)<permissionCode){
						 * currentlyAccessedServices.remove(serviceName);
						 * currentlyAccessedServices
						 * .put(serviceName,permissionCode ); } }else{
						 * currentlyAccessedServices.put(serviceName,
						 * permissionCode); }
						 */

						// add property if doesn't exists in sid
						if (ResourceBundle.getInstance().getPropertyValue(
								serviceName) == null) {
							addServiceIntoProperties(serviceName, this.help);
						}

					} else {
						// TODO Hammad here
						// Kareem bhai please comment the following code.
						// The current service might not have the permission in
						// this particular group
						// and permission given in some other group won't
						// reflect accordingly.
						// please comment the following if statement.
						// add services into Hash Table for quick search
						/*
						 * if(!currentlyAccessedServices.containsKey(service.
						 * getPermissionService().getServiceName())){
						 * currentlyAccessedServices
						 * .put(service.getPermissionService().getServiceName(),
						 * Integer.parseInt(service .getPermissionCode() + ""));
						 * }
						 */
					}
				}// end services loop
			}// end groupsLoop

			if (permissionCode == -1) {
				// service not found create new one
				UmService umService = new UmService();
				umService.setServiceName(serviceName);
				umService.setServiceTitle(serviceName);
				umService.setServiceDescription(serviceName);
				serviceDAO.createService(umService);
				permissionCode = 0;
			}

			// TODO Hammad Here
			// Kareem Bhai, now we have traversed through all the group to which
			// user belongs
			// the permissionCode is final, we can safely add the permission
			// code to the HashTable
			// please un-comment the following line
			currentlyAccessedServices.put(serviceName, permissionCode);
			// TODO Hammad Here,
			// My changes ends here. please try these changes.

		}// HashTable check

		// populate permissions for this service
		getPermission().getPermissionList(permissionCode);

		// testing
		System.out.println("Permission code is = " + permissionCode
				+ ":::Create: " + getPermission().isPermission("C") + " Read: "
				+ getPermission().isPermission("R") + " Update: "
				+ getPermission().isPermission("U") + " Delete : "
				+ getPermission().isPermission("D") + " Save: "
				+ getPermission().isPermission("S") + " Assign: "
				+ getPermission().isPermission("A"));

	}

	private void addServiceIntoProperties(String propertyName,
			String propertyValue) {
		// add property into sid.properties
		Properties prop = new Properties();
		FacesContext ctx = FacesContext.getCurrentInstance();
		ExternalContext ectx = ctx.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ectx.getRequest();
		HttpServletResponse response = (HttpServletResponse) ectx.getResponse();
		try {

			ServletContext ctx1 = (ServletContext) FacesContext
					.getCurrentInstance().getExternalContext().getContext();

			String realPath = ctx1.getRealPath("");
			prop.load(new FileInputStream(realPath
					.concat("/WEB-INF/classes/resources/sid.properties")));
			String key = " ";
			Enumeration e = prop.propertyNames();
			while (e.hasMoreElements()) {
				key = (String) e.nextElement();
				System.out.println(key + " " + prop.getProperty(key));
			}

			prop.setProperty(propertyName, propertyValue);
			File file = new File(
					realPath.concat("/WEB-INF/classes/resources/sid.properties"));
			FileOutputStream fileOut = new FileOutputStream(file);
			prop.store(fileOut, null);
			fileOut.close();
			// System.out.println(prop.getProperty(key) + "prop+++");

			// reload bundle to load newly saved properties
			ResourceBundle.getInstance().refresh();
		} catch (IOException e) {
			e.printStackTrace();

		}

	}

	// flush temporarely saved resources
	public void flush() {
		currentlyAccessedServices.clear();
	}

	public String getHelp() {
		return this.help;
	}

	public static void main(String[] arg) throws IOException {
		BASE64Encoder enc = new BASE64Encoder();
		BASE64Decoder dec = new BASE64Decoder();
		String str = "tmm~tmm";
		System.out.println(str);
		str = enc.encode(str.getBytes());
		System.out.println(str);
		str = new String(dec.decodeBuffer(str));
		System.out.println(str);
	}
}