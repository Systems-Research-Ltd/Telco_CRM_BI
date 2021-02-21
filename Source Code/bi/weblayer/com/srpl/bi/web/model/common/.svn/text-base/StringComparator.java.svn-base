package com.srpl.bi.web.model.common;

import java.util.Comparator;
import java.util.Map;

public class StringComparator implements Comparator<Map<String, String>> {

	String column_property;
	public StringComparator(String colproperty) {
		column_property=colproperty;
	}
	@Override
	public int compare(Map<String, String> a,Map<String, String> b) {
		Integer first=Integer.parseInt(a.get(column_property));
		Integer second=Integer.parseInt(b.get(column_property));
		return first.compareTo(second);
	}

}
