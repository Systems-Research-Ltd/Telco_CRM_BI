package com.srpl.um.ejb.request;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.srpl.um.ejb.entity.GroupPermission;
import com.srpl.um.ejb.entity.UmGroup;
import com.srpl.um.ejb.entity.UmGroupRole;
import com.srpl.um.ejb.entity.UmService;
import com.srpl.um.ejb.entity.UmRole;

/**
 * Session Bean implementation class GroupDAO
 */
@Stateless
@LocalBean
public class GroupDAO extends GenericDAO<UmGroup>{

    /**
     * Default constructor. 
     */
    public GroupDAO() {
       super(UmGroup.class);	
    }
    
    //================== new listGroups ==============//
    public List<UmGroup> listGroups1(){
    	List<UmGroup> groups = findAll();
    	return groups;
    }
    public List<UmGroup> listGroups1(Long company_id){
    	List<UmGroup> groups = null;
    	groups = em.createQuery("SELECT g FROM UmGroup g where g.companyId = :cid",UmGroup.class).setParameter("cid", company_id).getResultList();
    	return groups;
    }
  //================== end new listGroups ==============//
    
    public List<String> getGroupRoles(Long grpId){
    	List<UmGroupRole> roles = null;
    	List<String> roleIds = new ArrayList<String>();
    	UmGroup grp = em.find(UmGroup.class, grpId);
    	roles = em.createQuery("SELECT g FROM UmGroupRole g where g.umGroup = :grp",UmGroupRole.class).setParameter("grp", grp).getResultList();
    	Iterator<UmGroupRole> i = roles.iterator();
        while (i.hasNext()) {
            UmGroupRole grprole = (UmGroupRole) i.next();
            roleIds.add(grprole.getUmRole().getRoleId().toString());
        }
    	return roleIds;
    }
    

    public Long createGroup(UmGroup details, List<String> roles){
    	UmGroup group = null;
    	List<UmService> opr = null;
    	UmGroupRole grprole = null;
    	//group = new UmGroup(details.getCompanyId(),details.getGroupDetails(),details.getGroupStatus(),details.getGroupTitle(),details.getUmRole());
    	group = new UmGroup(details.getCompanyId(),details.getGroupDetails(),details.getGroupStatus(),details.getGroupTitle());
    	//UmRole  umRole = em.find(UmRole.class,details.getRoleId());
    	//group.setUmRole(umRole);    	
    	save(group);    	
    	for(String x : roles){
    		UmRole role = em.find(UmRole.class,Integer.parseInt(x));
    		grprole = new UmGroupRole(group,role);
    		em.persist(grprole);
    	}    	
    	opr = em.createQuery("from UmService",UmService.class).getResultList();
    	Iterator<UmService> i = opr.iterator();
        while (i.hasNext()) {
        	UmService oper = (UmService) i.next();
        	GroupPermission perm = new GroupPermission(oper,group,0L);
        	em.persist(perm);
        }
    	return group.getGroupId();
    }
   
    //=============== new create group ================//
    public Long createGroup(UmGroup group){
    	//UmRole  umRole = em.find(UmRole.class,1);
    	//group.setUmRole(umRole);
    	save(group);
    	return group.getGroupId();
    }
    //=============== end new create group ============//

    //=============== new create group ================//
    public Long createGroupH(UmGroup group){
    	save(group);
    	return group.getGroupId();
    }
    //=============== end new create group ============//
    
    //=============== new create group ================//
    public Long createGroup(UmGroup group, UmRole role){
    	save(group);
		UmGroupRole grprole = new UmGroupRole(group,role);
		em.persist(grprole);
    	return group.getGroupId();
    }
    //=============== end new create group ============//
	
    //================= new update group ================//
    
    public void updateGroup(UmGroup group, List<String> roles){
    	UmGroupRole grprole = null;
    	List<UmGroupRole> delrole = null;
    	try{
    	System.out.println("dao updateGroup called");
    	/*UmRole umRole = em.find(UmRole.class, roleId);
    	group.setUmRole(umRole);*/
    	update(group);
    	delrole = em.createQuery("SELECT g FROM UmGroupRole g where g.umGroup = :grp",UmGroupRole.class).setParameter("grp", group).getResultList();
    	for(UmGroupRole x : delrole){
    		em.remove(x);
    	}
    	for(String x : roles){
    		UmRole role = em.find(UmRole.class,Integer.parseInt(x));
    		grprole = new UmGroupRole(group,role);
    		em.persist(grprole);
    	}
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    	}
    }
    
    //================ end new update group =============//
    
    public void deleteGroup(long l){
    	System.out.println("here in ejb deleteGroup" + l);
    	List<UmGroupRole> delrole = null;
    	List<GroupPermission> delperm = null;
    	UmGroup grp = em.find(UmGroup.class, l);
    	delrole = em.createQuery("SELECT g FROM UmGroupRole g where g.umGroup = :grp",UmGroupRole.class).setParameter("grp", grp).getResultList();
    	for(UmGroupRole x : delrole){
    		em.remove(x);
    	}
    	delperm = em.createQuery("SELECT g FROM GroupPermission g where g.permissionGroup = :grp",GroupPermission.class).setParameter("grp", grp).getResultList();
    	for(GroupPermission y : delperm){
    		em.remove(y);
    	}
    	delete(l);
    }
    
    //=================== new groupDetails =================//
    public UmGroup groupDetails(Long groupId){
       	UmGroup group = find(groupId);
       	return group;
    }
  //=================== end new groupDetails =================//
    
    public List<GroupPermission> groupPermissions(Long groupId){
    	List<GroupPermission> permissions = null;
    	permissions = em.createQuery("SELECT p FROM GroupPermission p JOIN p.permissionService ps where p.permissionGroup = :groupId order by ps.parentServiceId ASC",GroupPermission.class).setParameter("groupId", find(groupId)).getResultList();        
    	return permissions;
    }
    
    public void updatePermissions(GroupPermission g){
    	System.out.println(g.getPermissionCode()+">>>>>>>>>>>");
    	em.merge(g);
    }
    
}
