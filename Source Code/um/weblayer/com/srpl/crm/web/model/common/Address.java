package com.srpl.crm.web.model.common;

/**
 * @author Hammad Hassan Khan
 *
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.bitguiders.util.jsf.JSFBeanSupport;
import com.srpl.um.ejb.request.UmUtilsDAO;
import com.srpl.um.web.common.SessionDataBean;
import com.srpl.um.web.controller.BeanFactory;

@ManagedBean(name="addressBean")
@ViewScoped
public class Address extends JSFBeanSupport implements Serializable
{
	private static final long serialVersionUID = 1L;

	private List<com.srpl.crm.web.model.common.Country>countryList;
	private List<com.srpl.crm.web.model.common.State>stateList;
	private List<com.srpl.crm.web.model.common.City>cityList;
	
	@EJB
	UmUtilsDAO util;
	
	public Address()
	{
		if(util == null){
			// Get the context
			try {
				InitialContext ctx = new InitialContext();
				String path = ctx.getNameInNamespace();
				System.out.println("address bean path is: " + path);
				//util = (UtilsDAO) ctx.lookup("java:app/crm_businesslayer/UtilDAO");
				util = (UmUtilsDAO) ctx.lookup("java:global/srpl/um_businesslayer/UmUtilsDAO");
				
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
	}
	
	@PostConstruct
	public void init(){
		countryAL();
		System.out.println("here in address.");

		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		try{
			stateAL(session.getSelectedCountry());
			cityAL(session.getSelectedState());
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public List<com.srpl.crm.web.model.common.Country> getCountryList() {
		return countryList;
	}

	public void setCountryList(
			List<com.srpl.crm.web.model.common.Country> countryList) {
		this.countryList = countryList;
	}

	public List<com.srpl.crm.web.model.common.State> getStateList() {
		return stateList;
	}

	public void setStateList(List<com.srpl.crm.web.model.common.State> stateList) {
		this.stateList = stateList;
	}

	public List<com.srpl.crm.web.model.common.City> getCityList() {
		return cityList;
	}

	public void setCityList(List<com.srpl.crm.web.model.common.City> cityList) {
		this.cityList = cityList;
	}

	public void countryAL(){
		countryList = new ArrayList<com.srpl.crm.web.model.common.Country>();
		
		List<com.srpl.um.ejb.entity.Country>dbList = util.listCountries();
		for(com.srpl.um.ejb.entity.Country x:dbList){
			com.srpl.crm.web.model.common.Country e = new com.srpl.crm.web.model.common.Country(x.getCountryId(),x.getCountryName(),x.getCountryCode());
			countryList.add(e);
		}
		
		if(countryList.size() == 0){
			com.srpl.crm.web.model.common.Country e = new com.srpl.crm.web.model.common.Country(0,"No Country in Found","NCF");
			countryList.add(e);
		}
	}
	
	public void stateAL(){
		stateAL(0);
	}
	public void stateAL(int id){
		System.out.println("here in get state from address referencing " + this.getClass().getName() + " with id :" + id);
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		session.setSelectedCountry(id);
		stateList = new ArrayList<com.srpl.crm.web.model.common.State>();
		
		List<com.srpl.um.ejb.entity.State> dbList = util.listStates(id);
		for(com.srpl.um.ejb.entity.State x : dbList){
			com.srpl.crm.web.model.common.State e = new com.srpl.crm.web.model.common.State(x.getStateId(),x.getCountry().getCountryId(),x.getStateName(),x.getStateCode());
			stateList.add(e);
		}
		
		if(stateList.size() == 0){
			com.srpl.crm.web.model.common.State e = new com.srpl.crm.web.model.common.State(-1,0,getProperty("select.country.first"),"SCF");
			stateList.add(e);
		}
		cityAL(0);
	}
	
	public void cityAL(){
		cityAL(0);
	}
	public void cityAL(int id){
		System.out.println("here in get city from address referencing " + this.getClass().getName() + " with id :" + id);
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		session.setSelectedState(id);
		cityList = new ArrayList<com.srpl.crm.web.model.common.City>();
		
		List<com.srpl.um.ejb.entity.City>dbList = util.listCities(id);
		for(com.srpl.um.ejb.entity.City x:dbList){
			com.srpl.crm.web.model.common.City e = new com.srpl.crm.web.model.common.City(x.getCityId(),x.getState().getStateId(),x.getCityName());
			cityList.add(e);
		}
		
		if(cityList.size() == 0){
			com.srpl.crm.web.model.common.City e = new com.srpl.crm.web.model.common.City(-1,0,getProperty("select.state.first"));
			cityList.add(e);
		}
	}
	public void cityAL(int countryId, int id){
		System.out.println("here in get city from address with id :" + id + " and countryId : " + countryId);
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		session.setSelectedCountry(countryId);
		session.setSelectedState(id);
		cityList = new ArrayList<com.srpl.crm.web.model.common.City>();
		
		List<com.srpl.um.ejb.entity.City>dbList = util.listCities(id);
		for(com.srpl.um.ejb.entity.City x:dbList){
			com.srpl.crm.web.model.common.City e = new com.srpl.crm.web.model.common.City(x.getCityId(),x.getState().getStateId(),x.getCityName());
			cityList.add(e);
		}
		
		if(cityList.size() == 0){
			com.srpl.crm.web.model.common.City e = new com.srpl.crm.web.model.common.City(-1,0,getProperty("select.state.first"));
			cityList.add(e);
		}
	}
	
	public String retCountry(String val){
		int id;
		id = Integer.valueOf(val);
		return retCountry(id);
	}
	public String retCountry(int id){
		String countryName;
		try{
			countryName = util.getCountry(id);
		}
		catch(Exception e){
			System.out.println("unable to connect to db."+e);
			countryName = "";
		}
		System.out.println(countryName);
		return countryName;
	}

	public String retState(String val){
		int id;
		id = Integer.valueOf(val);
		return retState(id);
	}
	public String retState(int id){
		String stateName;
		try{
			stateName = util.getState(id);
		}
		catch(Exception e){
			System.out.println("unable to connect to db."+e);
			stateName = "";
		}
		return stateName;
	}

	public String retCity(String val){
		System.out.println("here in string retCity");
		int id;
		id = Integer.valueOf(val);
		return retCity(id);
	}
	public String retCity(int id){
		String cityName;
		try{
			cityName = util.getCity(id);
		}
		catch(Exception e){
			System.out.println("unable to connect to db."+e);
			cityName = "";
		}
		return cityName;
	}

}