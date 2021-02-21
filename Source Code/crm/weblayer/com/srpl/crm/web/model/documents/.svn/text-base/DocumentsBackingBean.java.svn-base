package com.srpl.crm.web.model.documents;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.model.UploadedFile;

import com.bitguiders.util.jsf.JSFBeanInterface;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;
import com.lowagie.text.Document;
import com.srpl.crm.ejb.entity.DocumentORM;
import com.srpl.crm.ejb.exceptions.DocumentNotFoundException;
import com.srpl.crm.ejb.request.DocumentsDAO;
import com.srpl.crm.ejb.request.UtilsDAO;
import com.srpl.crm.web.common.InnerTabs;
import com.srpl.crm.web.common.SessionDataBean;
import com.srpl.crm.web.controller.BeanFactory;
import com.srpl.crm.web.model.common.ColumnModel;
import com.srpl.um.ejb.entity.UmCompany;
import com.srpl.um.ejb.request.CompanyDAO;
import com.srpl.um.ejb.request.UserDAO;

@ManagedBean(name = "documents")
public class DocumentsBackingBean extends JSFBeanSupport implements
		JSFBeanInterface, Serializable {
	private static final long serialVersionUID = 1L;
	private Long documentId;
	private Long documentAddedby;
	private Date documentAddedon;
	private String documentDetails;
	private Date documentEdate;
	private Date documentPdate;
	private Boolean documentStatus;
	private String documentTitle;
	private String documentSource;
	private Boolean renderUploadedDocument = false;
	private UploadedFile file;
	public List<ColumnModel> columns;
	private SessionDataBean session;

	@EJB
	DocumentsDAO documentsDao;
	@EJB
	UserDAO userDao;
	@EJB
	UtilsDAO utilsDao;
	@EJB
	CompanyDAO companyDao;

	public DocumentsBackingBean() {
		// setList();
		columns = new ArrayList<ColumnModel>();
		columns.add(new ColumnModel("documentId", this.getProperty("label.id")));
		columns.add(new ColumnModel("documentTitle", this.getProperty("label.title")));
		columns.add(new ColumnModel("documentAddedby", this.getProperty("label.added.by")));
		columns.add(new ColumnModel("documentPdate", this.getProperty("label.publich.date")));
		columns.add(new ColumnModel("documentEdate", this.getProperty("label.expiry.date")));

		setCurrentAction(WebConstants.ACTION_SECURITY, this.getClass());
		session = BeanFactory.getInstance().getSessionBean();
	}

	public Long getDocumentId() {
		return documentId;
	}
	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}
	public Long getDocumentAddedby() {
		return documentAddedby;
	}
	public void setDocumentAddedby(Long documentAddedby) {
		this.documentAddedby = documentAddedby;
	}
	public Date getDocumentAddedon() {
		return documentAddedon;
	}
	public void setDocumentAddedon(Date documentAddedon) {
		this.documentAddedon = documentAddedon;
	}
	public String getDocumentDetails() {
		return documentDetails;
	}
	public void setDocumentDetails(String documentDetails) {
		this.documentDetails = documentDetails;
	}
	public Date getDocumentEdate() {
		return documentEdate;
	}
	public void setDocumentEdate(Date documentEdate) {
		this.documentEdate = documentEdate;
	}
	public Date getDocumentPdate() {
		return documentPdate;
	}
	public void setDocumentPdate(Date documentPdate) {
		this.documentPdate = documentPdate;
	}
	public Boolean getDocumentStatus() {
		return documentStatus;
	}
	public void setDocumentStatus(Boolean documentStatus) {
		this.documentStatus = documentStatus;
	}
	public String getDocumentTitle() {
		return documentTitle;
	}
	public void setDocumentTitle(String documentTitle) {
		this.documentTitle = documentTitle;
	}
	public String getDocumentSource() {
		return documentSource;
	}
	public void setDocumentSource(String documentSource) {
		this.documentSource = documentSource;
	}
	public Boolean getRenderUploadedDocument() {
		return renderUploadedDocument;
	}
	public void setRenderUploadedDocument(Boolean renderUploadedDocument) {
		this.renderUploadedDocument = renderUploadedDocument;
	}
	public UploadedFile getFile() {
		return file;
	}
	public void setFile(UploadedFile file) {
		this.file = file;
	}
	public List<ColumnModel> getColumns() {
		return columns;
	}
	public void setColumns(List<ColumnModel> columns) {
		this.columns = columns;
	}

	private void changeTabPath(int index, String path) {
		InnerTabs d = session.getDocumentTabs().get(index);
		d.setPath(path);
		session.getDocumentTabs().set(index, d);
		try {
			if (getParameter("fromListing").equals("fromListing")) {
				// don't update index
			} else {
				session.setDocumentModule_tabIndex(index);
			}
		} catch (Exception e) {
			session.setDocumentModule_tabIndex(0);
		}
	}

	public void loadDocument() {
			try {
				DocumentORM docid;
				docid = documentsDao.documentDetails(session.getDocumentModule_selectedDocument());
				this.documentId = session.getDocumentModule_selectedDocument();
				this.documentTitle = docid.getDocumentTitle();
				this.documentDetails = docid.getDocumentDetails();
				this.documentPdate = docid.getDocumentPdate();
				this.documentEdate = docid.getDocumentEdate();
				this.documentSource = docid.getDocumentSource();
				this.documentAddedby = docid.getDocumentAddedby();
				this.documentAddedon = docid.getDocumentAddedon();
				this.documentStatus = docid.getDocumentStatus();
				changeTabPath(0, "/view/documents/documentForm.xhtml");

			} catch (DocumentNotFoundException e) {
				System.out.println("exception while loading document.");
				e.printStackTrace();
				session.setDocumentModule_selectedDocument(0L);
				changeTabPath(0, "/view/documents/documentNoSelection.xhtml");
			}
	}

	public String addDocument() throws Exception {
		String path = FacesContext.getCurrentInstance().getExternalContext()
				.getRealPath("/");
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");
		System.out.println("test");
		if (file == null) {
			addError("Provide the document to be added");
			throw new Exception();
		}
		this.documentSource = fmt.format(new Date()) + file.getFileName();
		File outfile = new File(path + "resources/documents/"
				+ this.documentSource);
		InputStream is;
		OutputStream out;
		byte buf[] = new byte[1024];
		int len;
		try {
			is = file.getInputstream();
			out = new FileOutputStream(outfile);
			while ((len = is.read(buf)) > 0)
				out.write(buf, 0, len);
			is.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		java.util.Date date = new java.util.Date();
		if (getDocumentPdate().compareTo(getDocumentEdate()) > 0) {
			addError("Document cannot be expired before it published, correct the publish and expire date");
			throw new Exception();
		}
		UmCompany company = companyDao.companyDetails(session.getCompanyId());
		DocumentORM doc = new DocumentORM(getDocumentTitle(),
				getDocumentDetails(), new Timestamp(getDocumentEdate()
						.getTime()),
				new Timestamp(getDocumentPdate().getTime()),
				getDocumentSource(), session.getUserId(), new Timestamp(
						date.getTime()), getDocumentStatus(), company);
		Long docid = documentsDao.createDocument(doc);
		doc.setDocumentId(docid);
		session.setDocumentModule_selectedDocument(docid);
		this.addMessage("Document Added Successfully.");
		return "documentList";
	}

	public void editDocument(Long documentId) {
		/* no need to change file 
		if (file != null) {
			String path = FacesContext.getCurrentInstance()
					.getExternalContext().getRealPath("/");
			SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");
			this.documentSource = fmt.format(new Date()) + file.getFileName();
			File outfile = new File(path + "resources/documents/"
					+ this.documentSource);
			InputStream is;
			OutputStream out;
			byte buf[] = new byte[1024];
			int len;
			try {
				is = file.getInputstream();
				out = new FileOutputStream(outfile);
				while ((len = is.read(buf)) > 0)
					out.write(buf, 0, len);
				is.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		*/
		DocumentORM doc = null;
		try {
			doc = documentsDao.documentDetails(documentId);
			doc.setDocumentEdate(new Timestamp(getDocumentEdate()
					.getTime()));
			doc.setDocumentTitle(documentTitle);
			doc.setDocumentDetails(documentDetails);
			doc.setDocumentStatus(documentStatus);
			
			documentsDao.updateDocument(doc);
			this.addMessage("Document Updated Successfully.");
			reset();
			setViewAction();
		} catch (DocumentNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<DocumentORM> getList() {
		List<DocumentORM> list = null;
		try {
			list = documentsDao.listDocuments(session.getCompanyId());
			if(list.size()==0){
				list.add(new DocumentORM("No Records Found."));
			}
		} catch (DocumentNotFoundException e) {
			list = new ArrayList<DocumentORM>();
			list.add(new DocumentORM("No Records Found."));
		}
		return list;
	}

	public void deleteDocument(Long documentId) {
		System.out.println("Delete Document Called");
		documentsDao.deleteDocument(documentId);
	}

	public void deleteRow(ActionEvent event) {
		System.out.println("Delete Document Called");
		Long id = (Long) event.getComponent().getAttributes().get("del_id");
		System.out.println(id);
		List<DocumentORM> data = getList();
		DocumentORM search = null;
		for (DocumentORM comp : data) {
			if (comp.getDocumentId() == id) {
				search = comp;
				break;
			}
		}
		if (search != null) {
			data.remove(search);
			documentsDao.deleteDocument(search.getDocumentId());
		}
	}

	@Override
	public void setViewAction() {
		super.setViewAction();
		setCancelAction(false);
		setResetAction(false);
		renderUploadedDocument = true;
	}

	public String actionListener() {
		System.out.println("DocumentsBackingBean actionListener() called..");
		reset();
		setCurrentAction(getParameter("action"), this.getClass());
		switch (getCurrentAction()) {
		case WebConstants.ACTION_CREATE:
			setCreateAction();
			changeTabPath(0, "/view/documents/documentForm.xhtml");
			break;
		case WebConstants.ACTION_SAVE:
			try {
				addDocument();
			} catch (Exception e) {
				addError("Could not add Document");
			}
			changeTabPath(0, "/view/documents/documentForm.xhtml");
			reset();
			setViewAction();
			break;

		case WebConstants.ACTION_CANCEL:
		case WebConstants.ACTION_VIEW:
			try {
				loadDocument();
			} catch (Exception e) {
				// handle exception
				addError("Could not Load the Document.");
				System.out
						.println("Exception Occured DocumentBackingBean actionListener()");
			}
			reset();
			setViewAction();
			break;
			
		case WebConstants.ACTION_EDIT:
			try {
				setEditAction();
				loadDocument();
			} catch (Exception e) {
				addError("Could not Load the Document");
				System.out
						.println("Exception Occured DocumentBackingBean actionListener()");
			}
			reset();
			setEditAction();
			break;

		case WebConstants.ACTION_UPDATE:
			try {
				Long documentId = session.getDocumentModule_selectedDocument();
				editDocument(documentId);
			} catch (Exception e) {
				System.out
						.println("Exception Occured DocumentBackingBean actionListener()");
			}
			reset();
			setViewAction();
			break;

		case WebConstants.ACTION_DELETE:
			try {
				loadDocument();
				setDeleteConfirmedAction();
				addMessage("Do you really want to delete ");
				reset();
				setDeleteAction();
			} catch (Exception e) {
				addError("Could not Load the Document.");
				System.out
						.println("Exception Occured DocumentBackingBean actionListener()");
				reset();
				setViewAction();
			}
			break;

		case WebConstants.ACTION_DELETE_CONFIRMED:
			Long id = session.getDocumentModule_selectedDocument();
			try {
				deleteDocument(id);
				addMessage("Document deleted successfully");
				session.setDocumentModule_selectedDocument(0L);
				changeTabPath(0, "/view/documents/documentNoSelection.xhtml");
			} catch (Exception e) {
				System.out
						.println("Exception Occured DocumentBackingBean actionListener()");
			}
			reset();
			setViewAction();
			break;

		}
		return (null);
	}

}