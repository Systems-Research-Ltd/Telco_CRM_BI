package com.srpl.um.ejb.request;

/**
 * @author Hammad Hassan Khan
 *
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;

import com.srpl.um.ejb.entity.GroupPermission;
import com.srpl.um.ejb.entity.UmUser;
import com.srpl.um.ejb.entity.UmUserGroup;
import com.srpl.um.ejb.exceptions.UserNotFoundException;
/**
 * Session Bean implementation class CompanyDAO
 */
@Stateful
@LocalBean
public class Security {
	
	@EJB UserDAO userDAO;
	@EJB GroupDAO groupDAO;

    public Security() {
    	System.out.println("-------------security---------------");
        // TODO Auto-generated constructor stub
    }
    
    public UmUser getUserByName(String userName){
			return getUserById(userDAO.getUserId(userName));
    }
    public UmUser getUserById(Long userId){
		try {
			return userDAO.umUserDetails(userId);
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }

    public Set<UmUserGroup> getUserGroupsByUserId(Long userId){
    	return getUserById(userId).getUmUserGroups();
    }
    
    public List<GroupPermission> getUserServicesByUserId(Long userId){
    	
    	Long groupId =null;
    	groupsLoop:
    	for(UmUserGroup group: getUserGroupsByUserId(userId)){
    		groupId = group.getUmGroup().getGroupId();
    		break groupsLoop;
    	}
    	List<GroupPermission> featuresPermissionsList = groupDAO.groupPermissions(groupId);
    	List<GroupPermission> allowedServices = new ArrayList<GroupPermission>();
    	for(GroupPermission groupPermission:featuresPermissionsList){
    		//remove service on which user have no authorization
    		if(groupPermission.getPermissionCode()>0){
    			allowedServices.add(groupPermission);
    		}
    	}
    	return allowedServices;
    }
}
