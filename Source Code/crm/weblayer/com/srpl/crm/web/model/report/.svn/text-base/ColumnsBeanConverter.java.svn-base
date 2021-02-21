package com.srpl.crm.web.model.report;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;
import javax.faces.convert.FacesConverter;
@FacesConverter("columnBeanConverter")

public class ColumnsBeanConverter implements Converter {

    

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
	    Object ret = null;
	    if(!arg2.equals("")) {
	    	System.out.println("Converter called");
		    ReportSql rs = new ReportSql();
		    String temp[] = arg2.split("\\.");
			String tableName = rs.getTableNameByAlias(temp[0]);
			int tableIndex = rs.TableIndex(tableName);
			String colTitle = rs.ColumnAliasing(tableIndex, temp[1].trim());
			ColumnsBean c = new ColumnsBean(arg2, colTitle);
			return c;
	    }
	    return ret;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
	    String str = "";
	    if (arg2 instanceof ColumnsBean) {
	        str = "" + ((ColumnsBean) arg2).getColDB();
	    }
	    return str;
	}

}
                    