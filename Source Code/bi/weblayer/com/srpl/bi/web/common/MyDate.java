package com.srpl.bi.web.common;

import java.util.Date;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class MyDate {

	public Date getMyDate(){
		return new Date();
	}
}