package com.srpl.crm.ejb.request;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.srpl.um.ejb.entity.City;
import com.srpl.um.ejb.entity.Country;
import com.srpl.crm.ejb.entity.CsAccount;
import com.srpl.crm.ejb.entity.CustomerPaymentORM;
//import com.srpl.crm.ejb.entity.DBConfiguration;
import com.srpl.crm.ejb.entity.DBConfiguration;
import com.srpl.um.ejb.entity.GroupPermission;
import com.srpl.um.ejb.entity.State;
import com.srpl.crm.ejb.entity.TableMapping;
import com.srpl.crm.ejb.entity.ThemesORM;
import com.srpl.um.ejb.entity.UmCompany;
import com.srpl.um.ejb.entity.UmGroup;
import com.srpl.um.ejb.entity.UmParameter;
import com.srpl.um.ejb.entity.UmService;
import com.srpl.um.ejb.entity.UmRole;
import com.srpl.um.ejb.entity.UmUser;
import com.srpl.um.ejb.entity.UserPreference;
import com.srpl.crm.ejb.entity.UsersThemeORM;

/**
 * Session Bean implementation class UtilsDAO
 */
@Stateless
@LocalBean
public class UtilsDAO {
	private final static String UNIT_NAME = "businesslayer";	 
    @PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;
    /**
     * Default constructor. 
     */
    public UtilsDAO() {
        // TODO Auto-generated constructor stub
    }
    
    public List<Long> permCode(Integer oprId, List<UmGroup> grps){
    //	System.out.println("PID : "+oprId);
    	/*for(UmGroup g : grps){
    		//System.out.println("GID : "+g.getGroupTitle());
    	}*/
    	List<Long> permCodes = new ArrayList<Long>();
    	UmService opr = (UmService)em.find(UmService.class, oprId);
    	List<GroupPermission> perms = em.createQuery("from GroupPermission p where p.permissionService = :opr and p.permissionGroup IN (:grps)",GroupPermission.class).setParameter("opr", opr).setParameter("grps", grps).getResultList();
    	for(GroupPermission p : perms){
    		permCodes.add(p.getPermissionCode());
    	}	
    	return permCodes;
    }
    
    public List<Country> listCountries() {
    	List<Country> countries = em.createQuery("from Country",Country.class).getResultList();
    	return countries;
    } 
    
    public List<State> listStates(int countryId) {
    	Country country = (Country)em.find(Country.class, countryId);
    	List<State> states = em.createQuery("from State where country = :selcountry",State.class).setParameter("selcountry", country).getResultList();
    	return states;
    }
    
    public List<City> listCities(int stateId) {
    	State state = (State)em.find(State.class, stateId);
    	List<City> cities = em.createQuery("from City where state = :selstate",City.class).setParameter("selstate", state).getResultList();
    	return cities;
    }
    
    public List<CsAccount> listAccountsByType(boolean isCompany, long companyId) {    	
    	List<CsAccount> accnts = em.createQuery("from CsAccount where accountIscompany = :seltype and company_id = :cId",CsAccount.class).setParameter("seltype", isCompany).setParameter("cId", companyId).getResultList();
    	return accnts;
    }
    
    public Country ctryDetails(int countryId){
    	return (Country)em.find(Country.class, countryId);
    }
    
    public String getCountry(int countryId){
    	String countryName = "";
    	try{
    		Country country = (Country)em.find(Country.class, countryId);
    		countryName = country.getCountryName();
    	}
    	catch(Exception e){
    		System.out.println("error fetching country.");
    	}
    	return countryName;
    }
    
    public State stsDetails(int stateId){
    	return (State)em.find(State.class, stateId);
    }
    
    public String getState(int stateId){
    	String stateName="";
    	try{
    	State state = (State)em.find(State.class, stateId);
    	stateName= state.getStateName();
    	//System.out.println("Utils dao "+stateName+"---"+stateId);
    	}
    	catch(Exception e){
    		System.out.println("error fetching state.");
    	}
    	return stateName;
    }
    
    public City cityDetails(int cityId){
    	return (City)em.find(City.class, cityId);
    }
    
    public String getCity(int cityId){
    	String stateCity="";
    	try{
    	City city = (City)em.find(City.class, cityId);
    	stateCity=city.getCityName();
    	}
    	catch(Exception e){
    		System.out.println("error fetching city.");
    	}
    	return stateCity;
    }
    
    public String getCompany(Long companyId){
    	String company="";
    	try{
    		UmCompany details = (UmCompany)em.find(UmCompany.class, companyId);
    		company = details.getCompanyName();
    	}
    	catch(Exception e){
    		System.out.println("error fetching company.");
    	}
    	return company;
    }
    
    public Long saveMapping(String title, String table, String pos){
    	TableMapping tm = new TableMapping();
    	tm.setMappingTitle(title);
    	tm.setMappingTable(table);
    	tm.setMappingPositions(pos);
    	em.persist(tm);
    	em.flush();
    	return tm.getMappingId();
    }
    
    public void saveConfig(Long companyId, String dbquery, String dbconfig, String user, String pass, String drv, Long mappingId){
    	DBConfiguration dc = new DBConfiguration();
    	dc.setCompanyId(companyId);
    	dc.setConfigDB(dbconfig);
    	dc.setConfigQuery(dbquery);
    	dc.setUserName(user);
    	dc.setPassword(pass);
    	dc.setDriver(drv);
    	dc.setTableMapping(em.find(TableMapping.class, mappingId));
    	em.persist(dc);
    }
    
    public List<TableMapping> getMappings(String tmap){
    	List<TableMapping> maps = em.createQuery("from TableMapping where mappingTable = :tmap and mappingTitle != ''",TableMapping.class).setParameter("tmap", tmap).getResultList();
    	return maps;
    }
    
    public String savedMap(Long mapId){
    	TableMapping maps = em.createQuery("from TableMapping where mappingId = :tmap",TableMapping.class).setParameter("tmap", mapId).getSingleResult();
    	return maps.getMappingPositions();
    }
    
    public String mapTitle(Long mapId){
    	TableMapping maps = em.createQuery("from TableMapping where mappingId = :tmap",TableMapping.class).setParameter("tmap", mapId).getSingleResult();
    	return maps.getMappingTitle();
    }
    
    public void deleteMap(Long mapId){
    	TableMapping maps = em.createQuery("from TableMapping where mappingId = :tmap",TableMapping.class).setParameter("tmap", mapId).getSingleResult();
    	em.remove(maps);
    }
    
    public List<CustomerPaymentORM> CustomerPaymentList() {
    	List<CustomerPaymentORM> list = em.createQuery("from CustomerPaymentORM",CustomerPaymentORM.class).getResultList();
    	return list;
    }
    
    public List<UmRole> userRoles(){
    	List<UmRole> roles = em.createQuery("SELECT r FROM UmRole r WHERE r.roleId != 1",UmRole.class).getResultList();
    	return roles;
    }
    
    public List<UmGroup> listUserGroups(Long userId){        
        List<UmGroup> groups = null;
        groups = em.createQuery("SELECT g FROM UmGroup g JOIN g.umUserGroups ug JOIN ug.umUser u where u.userId = :userId",UmGroup.class).setParameter("userId", userId).getResultList();
        return groups;        
    }
    
    public String[] modulePermissions(Long userId, Integer opr){
    	String[] selectedPermissions = new String[6];
    	List<UmGroup> usrgroups = listUserGroups(userId);	
		List<Long> permCodes = permCode(opr, usrgroups);
	    List<String> permissionValues = new ArrayList<String>(Arrays.asList("32","16","8","4","2","1"));
	    for(Long code : permCodes){
	 	   	String binary = Long.toBinaryString(code);
			char[] binaryArray = binary.toCharArray();
			int loc=5;
		    for(int i=binaryArray.length-1;i>=0;i--){
		    	if(binaryArray[i]=='1'){
		    		switch(permissionValues.get(loc)){
				 		case "32" : 
				 			selectedPermissions[0] = "32"; break;
				 		case "16" : 
				 			selectedPermissions[1] = "16"; break;
				 		case "8" : 
				 			selectedPermissions[2] = "8"; break;
				 		case "4" : 
				 			selectedPermissions[3] = "4"; break;
				 		case "2" : 
				 			selectedPermissions[4] = "2"; break;
				 		case "1" : 
				 			selectedPermissions[5] = "1"; break;
			 		}
			 	}
			 	loc--;
		     }
	     }
	    return selectedPermissions;
    }
    
    public List<ThemesORM> themesList() {
    	List<ThemesORM> list = em.createQuery("from ThemesORM",ThemesORM.class).getResultList();
    	return list;
    }
/*    
    public List<ThemesORM> listAlertsAndReminders(Long userId){
    	List<UmAlertsAndReminders> alertsandreminderstransmitStatus = findAll();
    	//Iterator alertsAndRemindersIterator<UmAlertsAndReminders> = new Iterator(alertsandreminderstransmitStatus);
    	
    	for(UmAlertsAndReminders alertsAndReminders:alertsandreminderstransmitStatus){
    			if(!alertsAndReminders.getTransmitStatus()) {    		

   				List<ThemesORM> list = em.createQuery("from ThemesORM where userId = :uId",ThemesORM.class).setParameter("uId", userId).getResultList();
                return list;    	
    	
    }
*/    
    public ThemesORM userTheme(Long userId){
    	List<UsersThemeORM> uTheme = em.createQuery("from UsersThemeORM where userId = :uId", UsersThemeORM.class).setParameter("uId", userId).getResultList();
    	ThemesORM sid = (uTheme.isEmpty()) ? defaultTheme() : themeData(uTheme.get(0).getThemeId());
    	return sid;
    }
    
    public ThemesORM defaultTheme(){
    	return em.createQuery("from ThemesORM where isDefault = true",ThemesORM.class).getSingleResult();
    }
    
    public ThemesORM themeData(Long themeId){
    	return em.createQuery("from ThemesORM where id = :tid",ThemesORM.class).setParameter("tid", themeId).getSingleResult();
    }    

    public Long themeId(Long userId) {
    	List<UsersThemeORM> uTheme = em.createQuery("from UsersThemeORM where userId = :uId", UsersThemeORM.class).setParameter("uId", userId).getResultList();
    	return uTheme.isEmpty() ? null : uTheme.get(0).getId();    	
    }

    public void createUsersTheme(UsersThemeORM details){    	
		em.persist(details);
	}
    
    public void updateUsersTheme(Long id, Long themeId){
    	UsersThemeORM usersTheme = (UsersThemeORM)em.find(UsersThemeORM.class, id);    	
    	usersTheme.setThemeId(themeId);
		em.merge(usersTheme);
	}
    
    public boolean openDialog(Long userId){
    	List<UserPreference> preferences = em.createQuery("from UserPreference where userId = :uid",UserPreference.class).setParameter("uid", userId).getResultList();
    	System.out.println((preferences.size() == 0) ? true : preferences.get(0).getShowPopup());
    	return (preferences.size() == 0) ? true : preferences.get(0).getShowPopup();
    }
    
    public void updateStatus(Long userId){
    	System.out.println("EJB "+userId);
    	UmUser usr = (UmUser)em.find(UmUser.class, userId);
    	usr.setIsOnline(false);
    	em.merge(usr);
    }
    
    public boolean importCountries(List<Country> countries) {
    	Iterator<Country> itr = countries.iterator();
    	while(itr.hasNext()){
    		Country ctry = itr.next();
    		em.persist(ctry);
    	}    	
		return true;		
    }
    
    public List<Country> getImportCountries(Timestamp stamp){
    	List<Country> ctry = null;
    	ctry = em.createQuery("select a from Country a where a.addedon = :stamp",Country.class).setParameter("stamp", stamp).getResultList();
    	return ctry;
    }
    
    public boolean importStates(List<State> states) {
    	Iterator<State> itr = states.iterator();
    	while(itr.hasNext()){
    		State sts = itr.next();   	
    		em.persist(sts);
    	}    	
		return true;		
    }
    
    public List<State> getImportStates(Timestamp stamp){
    	List<State> sts = null;
    	sts = em.createQuery("select a from State a where a.addedon = :stamp",State.class).setParameter("stamp", stamp).getResultList();
    	return sts;
    }
    
    public boolean importCities(List<City> cities) {
    	Iterator<City> itr = cities.iterator();
    	while(itr.hasNext()){
    		City cty = itr.next();
    		em.persist(cty);
    	}    	
		return true;		
    }
    
    public List<City> getImportCities(Timestamp stamp){
    	List<City> cty = null;
    	cty = em.createQuery("select a from City a where a.addedon = :stamp",City.class).setParameter("stamp", stamp).getResultList();
    	return cty;
    }
    
    public void setPreferences(Long userId, Boolean showPopup){
    	List<UserPreference> preferences = em.createQuery("from UserPreference where userId = :uid",UserPreference.class).setParameter("uid", userId).getResultList();    	
    	if(preferences.size() == 0){
    		UserPreference prf = new UserPreference();
    		prf.setUserId(userId);
    		prf.setShowPopup(showPopup);
    		em.persist(prf);
    	}else{
    		UserPreference prf = preferences.get(0);
    		prf.setShowPopup(showPopup);
    		em.merge(prf);
    	}
    }
    
    public Map<String,String> companyParameters(Long companyId){
    	Map<String,String> params = new HashMap<String,String>();    	
    	List<UmParameter> parameters = em.createQuery("from UmParameter where companyId = :cid",UmParameter.class).setParameter("cid", companyId).getResultList();    	
    	for(UmParameter param : parameters){
    		params.put(param.getParameterTitle(), param.getParameterValue());
    	}
    	return params;
    }
}