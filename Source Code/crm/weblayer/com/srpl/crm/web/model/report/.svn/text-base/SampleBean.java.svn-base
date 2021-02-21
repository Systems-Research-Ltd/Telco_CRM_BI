package com.srpl.crm.web.model.report;

import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;


/*import com.srpl.ccm.common.utils.UmUserDetails;
import com.srpl.ccm.ejb.entity.UmUser;
import com.srpl.ccm.ejb.request.UserDAO;
import com.srpl.ccm.ejb.request.GenericDAO;*/

import com.srpl.crm.ejb.request.ReportsDAO;
import com.srpl.um.ejb.entity.UmUser;

@ManagedBean
public class SampleBean {
       private String userdata;
       
//       @EJB GenericDAO genericDao;
       @EJB ReportsDAO reportsDao;
       
      public String getUserdata() {
    	  String cols = "UserId,UserJobtitle,UserEmail";
    	  String table = "UmUser";
    	  String colsArr[] = cols.split(",");
    	   
           //try {
//    	   Class<?> c = Class.forName("UmGroup.class");
//         Object t = c.newInstance();
    	   List<?> reportsdata = reportsDao.findAllEntities(UmUser.class);
           Iterator<?> i = reportsdata.iterator();
           while (i.hasNext()) {  
        	   UmUser t = (UmUser) i.next();
        	   for(int j = 0; j<colsArr.length; j++) {
		      	    //new UmGroup();
		//           Method m = UmGroup.class.getMethod("getUserId", "getUserFname", "getUserLname");
		      	   //System.out.println("cols "+colsArr[j]);
        		   String col = colsArr[j].toString();
        		   col = "get"+col;
        		   System.out.println( colsArr[j]+"    "+col);
		          // Method m = UmUser.class.getMethod(col);
		          // String returnVal = (String) m.invoke(t).toString();
		          // System.out.println(returnVal + " data ");
		         /*  m = UmUser.class.getMethod("getUserFname");
		           String returnVal2 = (String) m.invoke(t).toString();
		           System.out.print(returnVal2 + " ");
		           m = UmUser.class.getMethod("getUserLname");
		           returnVal2 = (String) m.invoke(t).toString();
		           System.out.println(returnVal2);*/
        	   }  
           }
           // } catch (SecurityException e1) {
    	// } catch (NoSuchMethodException e2) {
    	// } catch (IllegalArgumentException e3) {
    	// } catch (IllegalAccessException e4) {
    	// } catch (InvocationTargetException e5) {    		 
//       	 } catch (ClassNotFoundException e6) {    		 
       //	 }
         
       
    	 return userdata;
       }
  
       
 /*      
       public String getUserdata() {
    	   
           try {
    	   Class<?> c = Class.forName("UmUser.class");
    	   List<?> reportsdata = reportsDao.findAllEntities(c);
           Iterator<?> i = reportsdata.iterator();
           while (i.hasNext()) {  

////      	   Class<?> t = (Class<?>) i.next(); //new UmGroup();
//           UmUser t = (UmUser) i.next(); //new UmGroup();
           c.equals(i.next());
//           Method m = UmGroup.class.getMethod("getUserId", "getUserFname", "getUserLname");
           Method m = c.getMethod("getUserId");
           long returnVal = (Long) m.invoke(c);
           System.out.print(returnVal + " ");
           m = c.getMethod("getUserFname");
           String returnVal2 = (String) m.invoke(c);
           System.out.print(returnVal2 + " ");
           m = c.getMethod("getUserLname");
           returnVal2 = (String) m.invoke(c);
           System.out.println(returnVal2);
           }
    	 } catch (SecurityException e1) {
    	 } catch (NoSuchMethodException e2) {
    	 } catch (IllegalArgumentException e3) {
    	 } catch (IllegalAccessException e4) {
    	 } catch (InvocationTargetException e5) {    		 
       	 } catch (ClassNotFoundException e6) {   
       	 }
         
       
    	 return userdata;
       }
*/       
/*       public String getUserdata() {
    	   
           try {
//    	   Class<?> c = Class.forName("UmGroup.class");
//         Object t = c.newInstance();
    	   List<?> reportsdata = reportsDao.findAllEntities(UmUser.class);
           Iterator<?> i = reportsdata.iterator();
           while (i.hasNext()) {  

      	   UmUser t = (UmUser) i.next(); //new UmGroup();
//           Method m = UmGroup.class.getMethod("getUserId", "getUserFname", "getUserLname");
           Method m = UmUser.class.getMethod("getUserId");
           long returnVal = (Long) m.invoke(t);
           System.out.print(returnVal + " ");
           m = UmUser.class.getMethod("getUserFname");
           String returnVal2 = (String) m.invoke(t);
           System.out.print(returnVal2 + " ");
           m = UmUser.class.getMethod("getUserLname");
           returnVal2 = (String) m.invoke(t);
           System.out.println(returnVal2);
           }
    	 } catch (SecurityException e1) {
    	 } catch (NoSuchMethodException e2) {
    	 } catch (IllegalArgumentException e3) {
    	 } catch (IllegalAccessException e4) {
    	 } catch (InvocationTargetException e5) {    		 
//       	 } catch (ClassNotFoundException e6) {    		 
       	 }
         
       
    	 return userdata;
       }*/
  
          
/*       public String getUserdata() {  
    	   List<?> reportsdata = reportsDao.findAllEntities(UmGroup.class);
           Iterator<?> i = reportsdata.iterator();
           while (i.hasNext()) {  
       try {
    	   
    	   
  //  	   UmGroup user = (UmGroup) i.next();
    	   Class<?> c = Class.forName("UmGroup.class");
    	   Method  method = c.getDeclaredMethod ("getGroupDetails", null);
    	   Object o = method.invoke(null, null);
    	   System.out.println("Output: " + o);
    	   
    	   
      	   UmGroup t = (UmGroup) i.next(); //new UmGroup();
           Method m = UmGroup.class.getMethod("getGroupDetails");
           String returnVal = (String) m.invoke(t);
           System.out.println(returnVal);
           
    	 } catch (SecurityException e1) {
    	 } catch (NoSuchMethodException e2) {
    	 } catch (IllegalArgumentException e3) {
    	 } catch (IllegalAccessException e4) {
    	 } catch (InvocationTargetException e5) {    		 
//       	 } catch (ClassNotFoundException e6) {    		 
       	 }
          }
       
    	 return userdata;
       }
  */   
    	   
//           List<UmGroup> reportsdata = reportsDao.findAllEntities(UmGroup.class, "companyId", "groupDetails", "groupStatus", "groupTitle", "umRole"); //new ArrayList<UmUserDetails>();

        public void setUserdata(String userdata) {
                this.userdata = userdata;
        }        
 }