package com.srpl.crm.web.model;

/**
 * @author Hammad Hassan Khan
 *
 */
import java.io.Serializable;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="reference")
public class ReferenceBackingBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;

	/**
	 * 
	 * Getters and Setters
	 */
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void reset(){
		id = null;
	}
}
