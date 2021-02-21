package com.srpl.bi.web.model.dashboard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;
import com.srpl.bi.web.model.common.ColumnModel;

@ManagedBean(name="dashBoardBean")
public class DashBoardBean extends JSFBeanSupport implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public List<ColumnModel> dashBoardColumns;
	private ArrayList<DashBoardList> dashBoardList;
	private DashBoardList dashboardForm = new DashBoardList();
	
	public DashBoardBean(){
			System.out.println("BI----------------- DashBoardBean called");
			setCurrentAction(WebConstants.ACTION_SECURITY,this.getClass());
		
		dashBoardList = new ArrayList<DashBoardList>();
		dashBoardList.add(new DashBoardList("Company", "Humaid"));
		dashBoardList.add(new DashBoardList("User", "Harris"));
		
		dashBoardColumns = new ArrayList<ColumnModel>();
		dashBoardColumns.add(new ColumnModel("name", "Name"));
		dashBoardColumns.add(new ColumnModel("createdBy", "CreatedBy"));
		
	}

	@PostConstruct
	public void init() {
		String oldAction;
		try {
			oldAction = getParameter("old_action");
			switch (oldAction) {
			case WebConstants.ACTION_CREATE:
				setCreateAction();
				break;
			case WebConstants.ACTION_VIEW:
				setViewAction();
				break;
			case WebConstants.ACTION_EDIT:
				setEditAction();
				break;
			case WebConstants.ACTION_DELETE:
				setDeleteAction();
				break;
			default:
				setViewAction();
			}
		} catch (Exception e) {
			System.out.println("exception on old_action.");
		}
	}
	
	public String actionListener() {

		switch (getAction()) {
		case WebConstants.ACTION_CREATE:
			System.out.println("action create called");
			setCreateAction();
			return WebConstants.ACTION_CREATE;
		case WebConstants.ACTION_VIEW:
			System.out.println("action create called");
			setViewAction();
			return WebConstants.ACTION_VIEW;
		case WebConstants.ACTION_EDIT:
			System.out.println("action create called");
			setViewAction();
			return WebConstants.ACTION_EDIT;
		case WebConstants.ACTION_SAVE:
			setListAction(true);
			return WebConstants.ACTION_LIST;
		case WebConstants.ACTION_CANCEL:
			System.out.println("action cancel called");
			setListAction(true);
			return WebConstants.ACTION_LIST;
		default:
			setListAction(true);
			return WebConstants.ACTION_LIST;
		}
	}

	/**
	* getter/setters
	*/
	public List<ColumnModel> getDashBoardColumns() {
		return dashBoardColumns;
	}

	public ArrayList<DashBoardList> getDashBoardList() {
		return dashBoardList;
	}

	public DashBoardList getDashboardForm() {
		return dashboardForm;
	}

	public void setDashBoardColumns(List<ColumnModel> dashBoardColumns) {
		this.dashBoardColumns = dashBoardColumns;
	}

	public void setDashBoardList(ArrayList<DashBoardList> dashBoardList) {
		this.dashBoardList = dashBoardList;
	}

	public void setDashboardForm(DashBoardList dashboardForm) {
		this.dashboardForm = dashboardForm;
	}
}
