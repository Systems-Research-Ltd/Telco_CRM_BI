package com.srpl.um.web.common;

import java.util.LinkedHashMap;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name="jaasRealm", eager=true)
@ApplicationScoped
public class JaasRealmSelector {
	private LinkedHashMap<String, String> realmList;
	private String selectedRealm;
	private boolean isBIRealm;
	private boolean isCRMRealm;
	
	public JaasRealmSelector(){
		realmList = new LinkedHashMap<String, String>();
		realmList.put("CRM", "java:/jaas/umCrmDomain");
		realmList.put("BI", "java:/jaas/umBiDomain");
		
		setBIRealm(false);
		setCRMRealm(false);
		
		setSelectedRealm(realmList.get("CRM"));
	}
	public LinkedHashMap<String, String> getRealmList() {
		return realmList;
	}
	public void setRealmList(LinkedHashMap<String, String> realmList) {
		this.realmList = realmList;
	}
	public String getSelectedRealm() {
		return selectedRealm;
	}
	public void setSelectedRealm(String selectedRealm) {
		this.selectedRealm = selectedRealm;
		switch(selectedRealm){
		case "java:/jaas/umCrmDomain":
			setBIRealm(false);
			setCRMRealm(true);
			break;
		case "java:/jaas/umBiDomain":
			setBIRealm(true);
			setCRMRealm(false);
			break;
		}
	}
	public boolean isBIRealm() {
		return isBIRealm;
	}
	public void setBIRealm(boolean isBIRealm) {
		this.isBIRealm = isBIRealm;
	}
	public boolean isCRMRealm() {
		return isCRMRealm;
	}
	public void setCRMRealm(boolean isCRMRealm) {
		this.isCRMRealm = isCRMRealm;
	}
}
