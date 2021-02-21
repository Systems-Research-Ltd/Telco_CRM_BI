package com.srpl.crm.web.common;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.srpl.crm.ejb.entity.SPackageORM;
import com.srpl.crm.ejb.request.PackageDAO;

@FacesConverter("com.lmkr.crm.web.common.SalesPackageConverter")
public class SalesPackageConverter implements Converter, Serializable {

	@EJB PackageDAO packageDao;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Long packageId = Long.valueOf(arg2);
		SPackageORM packg = null;
		try{
			packg = packageDao.packageDetails(packageId);
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println("Exception while fetching package detials for id: " + packageId);
			packg = new SPackageORM();
			packg.setId(packageId);
		}
		return packg;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		return arg2.toString();
		//SPackageORM temp = (SPackageORM) arg2;
		//return temp.getTitle();
	}

}
