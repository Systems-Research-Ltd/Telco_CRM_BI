package com.srpl.crm.ejb.request;



import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import com.srpl.crm.ejb.entity.DashboardORM;


/**
 * Session Bean implementation class ContactDAO
 */
@Stateless
@LocalBean
public class DashboardDAO extends GenericDAO<DashboardORM> 
{

	/**
     * Default constructor. 
     */
    public DashboardDAO() {
        // TODO Auto-generated constructor stub
    	super(DashboardORM.class);
    }
    
    // get user's dashboard reports from DB if no results founds return default Dashboard
    @SuppressWarnings("unchecked")
	public Integer[] listDashboard(Integer userid, Long companyid, int totalreports, String role)
    {
    	  	List<DashboardORM> userdash;
    	  	Query query = em.createQuery("SELECT c FROM DashboardORM c WHERE companyid = :cId AND user=:userid", DashboardORM.class);
    	  	query.setParameter("cId", companyid);
    	  	query.setParameter("userid", userid);
    	  	userdash = (List<DashboardORM>) query.getResultList();
    	  	   	  
    	  	// return Deafulat reports if no entry in DB	
    	  	if(userdash==null || userdash.size()==0 || userdash.size()>totalreports)	
    	  		return getDefaultReports(totalreports, role);
    	  	
    	  	Integer reports[]=new Integer[totalreports];
    	  	for(DashboardORM panel:userdash)
    	  	{
    	  		if(panel.getPosition()<=totalreports && panel.getPosition()>=0 )
    	  		reports[panel.getPosition()-1]=panel.getReport();
    	  	}
    	    return reports;
    }
    
    // Returns default reports based on user role//////
    private Integer[] getDefaultReports(int totalreports, String role)
    {
    	Integer reports[]=new Integer[totalreports];
    	if(role.equals("Administrator"))
    	{
    		reports[0]=5;
	  		reports[1]=6;
	  		reports[2]=0;
	  		reports[3]=0;
    	}
    	if(role.equals("AccountManager"))
    	{
    		reports[0]=7;
	  		reports[1]=8;
	  		reports[2]=9;
	  		reports[3]=0;
    	}
    	if(role.equals("User"))
    	{
    		reports[0]=1;
	  		reports[1]=2;
	  		reports[2]=3;
	  		reports[3]=4;
    	}
    	if(role.equals("Customer"))
    	{
    		reports[0]=11;
	  		reports[1]=0;
	  		reports[2]=0;
	  		reports[3]=0;
    	}
		return reports;
    	
    }
 	
    public void setPanel(DashboardORM dashPanel)
    {
    	DashboardORM panelExists=null;
    	// check if entry already exists
   	  	Query query = em.createQuery("SELECT c FROM DashboardORM c WHERE companyid = :cId AND user=:userid AND position=:position", DashboardORM.class);
	  	query.setParameter("cId", dashPanel.getCompanyid());
	  	query.setParameter("userid", dashPanel.getUser());
	  	query.setParameter("position", dashPanel.getPosition());
	  	try
	  	{
	  		panelExists=(DashboardORM) query.getSingleResult();
	  	
	  	}
	  	catch(Exception e)
	  	{
	  		
	  	}
    	
    	if(panelExists!=null)
    	{
    		dashPanel.setId(panelExists.getId());
    		update(dashPanel);
    	}
    	else
    	{
    		save(dashPanel);
    	}
	  	
    }
    
    public void setDefaultDashboard(Integer user, Long companyId, String role, int totalreports)
    {
    	List<DashboardORM> userdash=null;
    	// check if User's dashboard is empty exists
   	  	Query query = em.createQuery("SELECT c FROM DashboardORM c WHERE companyid = :cId AND user=:userid", DashboardORM.class);
	  	query.setParameter("cId", companyId);
	  	query.setParameter("userid", user);
	  	try
	  	{
	  		userdash = (List<DashboardORM>) query.getResultList();
	  	
	  	}
	  	catch(Exception e)
	  	{
	  		
	  	}
    	
	  	// Update DB with default reports
    	if(userdash==null || userdash.size()==0)
    	{
    		DashboardORM panel;
    		Integer reports[]=getDefaultReports(totalreports, role);
    		for(int i=0;i<totalreports;i++)
    		{
    			 panel=new DashboardORM(user, i+1, reports[i], companyId);
    			 save(panel);
    		}
    	}
    }
    
    
    
}