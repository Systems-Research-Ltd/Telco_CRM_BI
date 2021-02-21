package com.srpl.crm.web.model.customer.customer360;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import com.bitguiders.util.jsf.JSFBeanInterface;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;
import com.srpl.crm.ejb.entity.CsContactORM;
import com.srpl.crm.ejb.entity.CustomerNotesORM;
import com.srpl.crm.ejb.request.CustomerNotesDAO;
import com.srpl.crm.web.common.InnerTabs;
import com.srpl.crm.web.common.SessionDataBean;
import com.srpl.crm.web.controller.BeanFactory;
import com.srpl.crm.web.model.common.ColumnModel;
import com.srpl.um.ejb.entity.UmCompany;

@ManagedBean(name="customer360Notes")
public class Customer360NotesBackingBean extends JSFBeanSupport implements JSFBeanInterface {

	@EJB CustomerNotesDAO notesDao;
	private SessionDataBean session;

	private static List<ColumnModel> columns;
	private Long noteId;
	private CsContactORM subscriber;
	
	private UmCompany company;
	private String note;

	 {
		columns = new ArrayList<ColumnModel>();
		columns.add(new ColumnModel("id",this.getProperty("label.id")));
		columns.add(new ColumnModel("excerpt", this.getProperty("label.excerpt")));
	}
	// ================= Notes Tab =======================//

	public List<ColumnModel> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnModel> invoiceColumns) {
		Customer360NotesBackingBean.columns = invoiceColumns;
	}

	public Long getNoteId() {
		return noteId;
	}
	public void setNoteId(Long noteId) {
		this.noteId = noteId;
	}
	public CsContactORM getSubscriber() {
		return subscriber;
	}
	public void setSubscriber(CsContactORM subscriber) {
		this.subscriber = subscriber;
	}	
	public UmCompany getCompany() {
		return company;
	}
	public void setCompany(UmCompany company) {
		this.company = company;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Customer360NotesBackingBean(){
		session = BeanFactory.getInstance().getSessionBean();
	}

	@PostConstruct
	public void postConstruct() {
		if (getAction().equals("")) {
			setCurrentAction(WebConstants.ACTION_CREATE);
		}
	}

	@Override
	public String actionListener() {
		setCurrentAction(getAction(),this.getClass());
		switch(getCurrentAction()){
		case WebConstants.ACTION_CREATE:
			//actionCreate();
			noteId = 0L;
			note = "";
			reset();
			setCreateAction();
			changeTabPath(3, "/view/customer/contacts/c360view/notes/notesForm.xhtml");
			break;
		case WebConstants.ACTION_SAVE:
			actionSave();
			break;
		case WebConstants.ACTION_VIEW:
			long tempId;
			try{
				tempId = Long.valueOf(getParameter("row_id"));
				actionView(tempId);
			}catch (Exception e) {
				e.printStackTrace();
				addError("Unable to load the note.");
				changeTabPath(3, "/view/customer/contacts/c360view/notes/index.xhtml");
			}
			break;
		case WebConstants.ACTION_DELETE_CONFIRMED:
			//No need here
			break;
		case WebConstants.ACTION_LIST:
		case WebConstants.ACTION_CANCEL:
			changeTabPath(3, "/view/customer/contacts/c360view/notes/index.xhtml");
			break;
		default:
			//listPage();
		}
		return null;
	}

	@Override
	public List<CustomerNotesORM> getList() {
		System.out.println("Customer360Notes()");
		// its original state

		// Get packages from DB
		List<CustomerNotesORM> listDb = notesDao.list(session.getCompanyId(), session
				.getCustomerModule_selectedContact());
		
		return listDb;
	}

	public void actionSave(){
		try {
			CustomerNotesORM db = new CustomerNotesORM();
			db.setNotes(this.getNote());
			noteId = notesDao.create(db, session.getCompanyId(), session.getCustomerModule_selectedContact());
			setListAction(true);
			addMessage("Note Successfully Created");
			changeTabPath(3, "/view/customer/contacts/c360view/notes/index.xhtml");
		} catch (Exception e) {
			// handle exception
			System.out.println("Couldn't create");
			addError("Note Creation Failed.");
			changeTabPath(3, "/view/customer/contacts/c360view/notes/index.xhtml");
		}
		setListAction(true);
	}

	public void actionView(long notesId){
		try {
			CustomerNotesORM db = notesDao.details(notesId);
			this.noteId = notesId;
			this.company = db.getCompany();
			this.note = db.getNotes();
			this.subscriber = db.getCustomer();
			changeTabPath(3, "/view/customer/contacts/c360view/notes/notesForm.xhtml");
		} catch (Exception e) {
			// handle exception
			System.out.println("Couldn't create");
			addError("Note Load Failed.");
			changeTabPath(3, "/view/customer/contacts/c360view/notes/index.xhtml");
		}
	}

	private void changeTabPath(int index, String path) {
		InnerTabs d = session.getContactTabs().get(index);
		d.setPath(path);
		session.getContactTabs().set(index, d);
		session.setCustomerModule_contactTabIndex(index);
	}

}
