package com.srpl.crm.ejb.request;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;


import com.srpl.crm.common.utils.UmLocationDetails;
import com.srpl.um.ejb.entity.UmCompany;

import com.srpl.um.ejb.entity.UmLocation;

/**
 * Session Bean implementation class LocationDAO
 */
@Stateless
@LocalBean
public class LocationDAO extends GenericDAO<UmLocation> {
       
    /**
     * @see GenericDAO#GenericDAO()
     */
    public LocationDAO() {
        super(UmLocation.class);
        // TODO Auto-generated constructor stub
    }
       
  
    public Integer createLocation(UmLocationDetails details){
    	System.out.println("dao creatLocation called");
    	UmLocation location = null;
    	location = new UmLocation(details.getLocationAddress(),details.getLocationCity(),details.getLocationCountry(),details.getLocationState(),details.getLocationDetails(),details.getLocationStatus(),details.getLocationTitle(),details.getIsHeadOffice(),details.getUmCompany());
        UmCompany umCompany = em.find(UmCompany.class, details.getCompanyId());
    	location.setUmCompany(umCompany);
        save(location);
    	return location.getLocationId();
    } 
    
    public Integer createLocation(UmLocation location){
    	System.out.println("dao creatLocation called");
    	
    	UmCompany umCompany = em.find(UmCompany.class,location.getUmCompany().getCompanyId());
    	location.setUmCompany(umCompany);
        save(location);
    	return location.getLocationId();
    }
    
    
}
