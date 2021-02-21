package com.srpl.crm.web.model.common;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.srpl.um.ejb.request.UmUtilsDAO;
import com.srpl.um.web.controller.BeanFactory;

/**
 * @author Hammad Hassan Khan
 * the original address class was causing some issues
 */

@ManagedBean(name="addressBeanUpdated")
public class AddressUpdate implements Serializable, Validator {

	@EJB UmUtilsDAO utilsDao;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public List<com.srpl.um.ejb.entity.Country> getCountries(){
		List<com.srpl.um.ejb.entity.Country> myList = utilsDao.listCountries();
		return myList;
	}

	public void validateCountry(FacesContext fc, UIComponent ui, Object value)
			throws ValidatorException {
		int countryId = (int) value;
		BeanFactory.getInstance().getSessionBean().setSelectedCountry(countryId);
	}
	
	public List<com.srpl.um.ejb.entity.State> getStates() {
		int countryId = BeanFactory.getInstance().getSessionBean().getSelectedCountry();
		List<com.srpl.um.ejb.entity.State> myList = utilsDao.listStates(countryId);
		return myList;
	}

	public void validateState(FacesContext fc, UIComponent ui, Object value)
			throws ValidatorException {
		int stateId = (int) value;
		BeanFactory.getInstance().getSessionBean().setSelectedState(stateId);
	}
	
	public List<com.srpl.um.ejb.entity.City> getCities() {
		int stateId = BeanFactory.getInstance().getSessionBean().getSelectedState();
		List<com.srpl.um.ejb.entity.City> myList = utilsDao.listCities(stateId);
		return myList;
	}

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		// TODO Auto-generated method stub
	}

}
